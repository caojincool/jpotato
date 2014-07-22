package com.lemsun.data.sqlserver.service;

import com.lemsun.core.*;
import com.lemsun.core.formula.*;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.ITransactionManager;


import com.lemsun.data.lmstable.Column;

import com.lemsun.data.lmstable.Table5Resource;

import com.lemsun.data.sqlserver.service.support.AssemblyQuerySql;
import com.lemsun.data.sqlserver.service.support.WhereParam;

import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.service.IFormulaExcuter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.*;

/**
 * User: 宗旭东
 * Date: 13-9-22
 * Time: 下午2:18
 */
@Service
public class FormulaExcuterImpl implements IFormulaExcuter {

    @Autowired
    IAccountCoreService accountService;
    static final Logger log = LoggerFactory.getLogger(FormulaExcuterImpl.class);


    @Override
    public void update(ITableResource resource, IFormula formula, String dataType, String json) throws FormulaException {


        if(resource instanceof Table5Resource)
        {
            updateTable5Resource((Table5Resource) resource, formula, dataType, json);
        }

    }
    /**
     * 更新五代组件数据
     * @param resource 执行的组件
     * @param formula 执行的公式
     * @param dataType 数据类型, 支持 int, string, double, date, null, formula, array
     * @param json 传入的数据类型
     * @throws FormulaException
     */
    protected void updateTable5Resource(Table5Resource resource, IFormula formula, String dataType, String json) throws FormulaException
    {

        if(log.isInfoEnabled()) {
            log.info("执行一个公式更新 组件:" + resource.getPid() + " Name:" + resource.getName());
            log.info("数据类型: " + dataType);
            log.info("数据: " + json);
        }

        Table5Resource tableResource = resource;

        StringBuilder sqlBuilder = new StringBuilder("UPDATE ").append(tableResource.getDbtable()).append("\n");

        IFColRange colRange = formula.getColRange();

        IFCol[] cols = colRange.getAll();

        ArrayList<Column> updateColumns = new ArrayList<>();
        //整理更新列
        sqlBuilder.append("SET ");
        boolean isFirst = true;

        if(colRange.isRange()) {
            //将一个区域内的列设置成同一个值
        }
        else if(cols.length == 1 && StringUtils.equals(cols[0].getCol(), "*")) {
            //全部列设置成同一个值
        }
        else {
            for(IFCol col : cols)
            {
                if(!StringUtils.isEmpty(col.getFun()))
                {
                    throw new FormulaException("更新的公式, 不能在字段上包含函数. " + col.getCol(), FormulaException.FormulaException.getCode());
                }

                Column resourceColumn = tableResource.getColumn(col.getCol());

                if(resourceColumn != null)
                {
                    updateColumns.add(resourceColumn);

                    if(!isFirst)
                    {
                        sqlBuilder.append(", \n");
                    }
                    sqlBuilder.append("[").append(resourceColumn.getCol()).append("] = ? ");
                    isFirst = false;
                }
            }
        }


        ArrayList<WhereParam> whereColumns = new ArrayList<>();

        //整理更新条件
        isFirst = true;
        IFExpression expression = formula.getExpression();
        while (expression != null)
        {
            if(isFirst) {
                sqlBuilder.append(" WHERE ");
            }
            else {
                sqlBuilder.append(expression.getCondition().toSQL());
            }

            Column whereCol = StringUtils.isEmpty(expression.getVar())
                    ? tableResource.getCodeColumn()
                    : tableResource.getColumn(expression.getVar());

            whereColumns.add(new WhereParam(whereCol, expression.getValue()));

            //列
            sqlBuilder.append("[").append(whereCol.getCol()).append("]");


            //操作符
            sqlBuilder.append(expression.getOperater().toSQL());

            //值
            sqlBuilder.append(" ? ");


            expression = expression.getNextExpression();
        }

        int paramIndex = 1;


        ITransactionManager transactionManager = accountService.getCurrentAccountManager().getTransactionManager();
        Connection conn = null;
        try
        {

            if(log.isInfoEnabled()) {
                log.info("数据源更新: " + tableResource.getDbConfig().getConnStr());
                log.info("更新的SQL: " + sqlBuilder.toString());
            }

            conn = transactionManager.getConnection(tableResource.getDbConfig().getPid());

            PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString());

            //设置列值
            JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);

            if(StringUtils.equals(dataType, "null")) {
                for(Column col : updateColumns)
                {
                    statement.setNull(paramIndex++, col.getSQLType());
                }
            }
            else if(StringUtils.equals(dataType, "int"))
            {
                int value = Integer.parseInt(json);

                for(Column col : updateColumns)
                {
                    statement.setObject(paramIndex++, col.convert(value), col.getSQLType());
                }


            }
            else if(StringUtils.equals(dataType, "double"))
            {
                double value = Double.parseDouble(json);
                for(Column col : updateColumns)
                {
                    statement.setObject(paramIndex++, col.convert(value), col.getSQLType());
                }

            }
            else if(StringUtils.equals(dataType, "string"))
            {
                String value = mapper.readValue(json, String.class);

                for(Column col : updateColumns)
                {
                    statement.setObject(paramIndex++, col.convert(value), col.getSQLType());
                }

            }
            else if(StringUtils.equalsIgnoreCase(dataType, "date")) {
                Date value = mapper.readValue(json, Date.class);

                for(Column col : updateColumns)
                {
                    statement.setObject(paramIndex++, col.convert(value), col.getSQLType());
                }

            }
            else if(StringUtils.equals(dataType, "array") || StringUtils.equals(dataType, "formula"))
            {
                ArrayList value = mapper.readValue(json, ArrayList.class);

                for(Column col : updateColumns)
                {
                    Object d = value.size() >= paramIndex ? value.get(paramIndex-1) : null;
                    statement.setObject(paramIndex, col.convert(d), col.getSQLType());
                    paramIndex++;
                }
            }




            //设置条件值
            for(WhereParam param : whereColumns)
            {
                statement.setObject(paramIndex, param.getValue(), param.getSqlType());

                paramIndex++;
            }

            statement.execute();
            statement.close();

        }
        catch (SQLException e) {
            throw new FormulaException("组件的数据库连接资源获取异常", "00001");
        }
        catch (Exception ex)
        {
            throw new FormulaException("数据格式解析不正确. ", "0101");
        }
        finally {
            transactionManager.finishExcute(conn);
        }


    }


    /**
     * 实现
     * @param refsMap 目标操作的组件
     * @param formula 公式对象
     * @return
     */
    @Override
    public ArrayData getData(Map<String,ITableResource> refsMap, IFormula formula) throws FormulaException {
            return getTable5Data(refsMap, formula);
    }

    @Override
    public ArrayData getData(Map<String, ITableResource> refsMap, IFormula formula, FormulaQuery query) {
        if(log.isDebugEnabled())
        {
            log.debug("开始执行取数公式: " + formula.getRefs());
        }
        AssemblyQuerySql assemblyQuerySql = new AssemblyQuerySql( refsMap,  formula,this.accountService,query);
        return assemblyQuerySql.getTable5Data();
    }


    /**
     * 获取五代表格数据
     * @param refsMap
     * @param formula
     * @return
     * @throws FormulaException
     */
    protected ArrayData getTable5Data(Map<String,ITableResource> refsMap, IFormula formula) throws FormulaException
    {

        if(log.isDebugEnabled())
        {
            log.debug("开始执行取数公式: " + formula.getRefs());
        }
        AssemblyQuerySql assemblyQuerySql = new AssemblyQuerySql( refsMap,  formula,this.accountService);
        return assemblyQuerySql.getTable5Data();
    }



    @Override
    public DbCategory getSupportCategory() {
        return DbCategory.SQLSERVER;
    }


}
