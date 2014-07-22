package com.lemsun.data.sqlserver.service.support;

import com.lemsun.core.ArrayData;
import com.lemsun.core.formula.*;
import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.ITransactionManager;

import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.lmstable.TableCategory;

import com.lemsun.data.sqlserver.SqlServerSQLAdapter;
import com.lemsun.data.tables.ColumnCategory;
import com.lemsun.formula.FCol;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableData;
import com.lemsun.sql.builder.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.ParseException;
import java.util.*;

/**
 * 组装公式查询sql帮助类
 */
public class AssemblyQuerySql {
    protected static final Logger log = LoggerFactory.getLogger(AssemblyQuerySql.class);
    protected IAccountCoreService accountService;
    protected boolean hasFunction = false;
    protected String firstKey;
    protected SSelect select = new SSelect();
    protected Map<String, ITableResource> refsMap;
    protected IFormula formula;
    protected ArrayList<ISQLCol> cols = new ArrayList<>();
    protected ArrayList<ISQLCol> sysCols = new ArrayList<>();
    protected List<ISelectCol> allCols = new ArrayList<>();//保存所有查询列
    protected List<ISelectCol> sysAllCols = new ArrayList<>();//保存所有同步查询列
    private FormulaQuery query;//分页查询条件对象
    public AssemblyQuerySql(Map<String, ITableResource> refsMap, IFormula formula, IAccountCoreService accountService) {
        this(refsMap, formula, accountService, null);
    }
    public AssemblyQuerySql(Map<String, ITableResource> refsMap, IFormula formula, IAccountCoreService accountService,FormulaQuery query) {
        this.refsMap = refsMap;
        this.formula = formula;
        this.accountService = accountService;
        this.query=query;
    }
    /**
     * 模板方法
     *
     * @return
     */
    public ArrayData getTable5Data() {
        this.firstKey = refsMap.keySet().iterator().next(); //第一张表key
        Table5Resource resource = (Table5Resource) refsMap.get(firstKey);//第一张表
        IFColRange colRange = formula.getColRange();
        IFCol[] cols = colRange.getAll();
        //获取是否有函数
        hasFunction = HasFunction(cols);

        //组装查询列
        List<SelectCol> selectCols = assemblyQueryColumn(cols);
        //添加查询对象表

        IFormulaRef[] refs = formula.getRefs();
        //组装查询表
        assemblyQueryTables(refs);
        //添加Where 查询条件

        ArrayList<WhereParam> whereParams = new ArrayList<>();

        assemblyCondition(resource, whereParams);
        //系统内置条件
        specialColumnCondtion(resource, whereParams);
        //添加分组
        assemblyGroup(selectCols);
        //添加排序
        assemblyOrder();

        ArrayData data = excuteQuery(resource, whereParams);



        return data;
    }

    /**
     * 执行查询
     *
     * @param resource
     * @param whereParams
     * @return
     */
    protected ArrayData excuteQuery(Table5Resource resource, ArrayList<WhereParam> whereParams) {
        ITransactionManager transactionManager = accountService.getCurrentAccountManager().getTransactionManager();
        Connection conn = null;

        FormulaTableData data = null;
        int rowCount = 0;
        try {
            conn = transactionManager.getConnection(resource.getDbConfig().getPid());
            if(query!=null) {
                //查询总数据量
                select.col(new SCol("1",null,"Count",null));
                List<SOrder> orders=select.getOrders();
                select.clearOrders();//查询总条数不需要排序
                PreparedStatement statement = conn.prepareStatement(select.toSQL(new SqlServerSQLAdapter()));

                int paramIndex = 1;
                for (WhereParam param : whereParams) {
                    statement.setObject(paramIndex, param.getValue(), param.getSqlType());
                    paramIndex++;
                }
                ResultSet rs = statement.executeQuery();
                if(rs.next())
                    rowCount=rs.getInt(1);

                rs.close();

                statement.close();
                //还原排序
                select.order(orders);
                //查询不同步列
                if(query.isPageRequest()){
                    select.page(query.getStart(),query.getLimit());
                }else {
                    select.page(1,200);
                }
            }
            //查询不同步列
            select.clearCols().col(this.cols);
            PreparedStatement statement3 = conn.prepareStatement(select.toSQL(new SqlServerSQLAdapter()));

            int paramIndex3 = 1;
            for (WhereParam param : whereParams) {
                statement3.setObject(paramIndex3, param.getValue(), param.getSqlType());
                paramIndex3++;
            }
            ResultSet rs3 = statement3.executeQuery();

            data = new FormulaTableData(rs3, allCols);

            rs3.close();

            statement3.close();

            //查询不同步列
            if (this.sysCols.size() > 0) {
                select.clearCols().col(this.sysCols);
                PreparedStatement statement2 = conn.prepareStatement(select.toSQL(new SqlServerSQLAdapter()));

                int paramIndex2 = 1;
                for (WhereParam param : whereParams) {
                    statement2.setObject(paramIndex2, param.getValue(), param.getSqlType());
                    paramIndex2++;
                }
                ResultSet rs2 = statement2.executeQuery();

                FormulaTableData data2 = new FormulaTableData(rs2, sysAllCols);

                rs2.close();

                statement2.close();
                data.setSyncTable(data2);
            }
          if(query != null){
              data.setRowCount(rowCount);
          }
          ITableResource tableResource=refsMap.get(this.firstKey);
          String pid=null;
          if (tableResource instanceof Table5Resource){
            Table5Resource table5Resource=(Table5Resource)tableResource;
            pid=table5Resource.getPid();
          }
          data.setTablePid(pid);
        } catch (Exception ex) {
            throw new FormulaException(ex.getMessage(), FormulaException.ExecuteCode);
        } finally {
            transactionManager.finishExcute(conn);
        }
        if (log.isDebugEnabled()) {
            log.debug("公式执行完成, 获取数据行数: " + (data == null ? 0 : data.getRowCount()));
        }
        return data;
    }

    /**
     * 组装分组
     *
     * @param selectCols
     */
    private void assemblyGroup(List<SelectCol> selectCols) {
        if (hasFunction) {
            if (selectCols.size() > 0) {
                for (SelectCol selectCol : selectCols) {
                    String ref = selectCol.getFormulaCol().getRef();
                    ref = StringUtils.isEmpty(ref) ? firstKey : ref;
                    select.group(new SCol(selectCol.getFormulaCol().getCol(), ref, null));
                }
            }
        }
    }

    /**
     * 添加排序
     */
    private void assemblyOrder() {
        ISoft[] softs = formula.getSofts();
        if (ArrayUtils.isNotEmpty(softs)) {
            for (ISoft soft : softs) {
                String ref = StringUtils.isNotEmpty(soft.getRef()) ? soft.getRef() : firstKey;
                  select.order(new SOrder(new SCol(soft.getName(),ref,null),SOrderType.convert(soft)));
            }
        } else {
            if (!hasFunction) {
                 select.order(new SOrder(new SCol("code",firstKey,null),SOrderType.Asc));
            }
        }
    }

    /**
     * 组装查询条件
     *
     * @param resource
     * @param whereParams
     */
    private void assemblyCondition(Table5Resource resource, ArrayList<WhereParam> whereParams) {
        IFExpression expression = formula.getExpression();
        while (expression != null) {
            String preRef = expression.getRef();

            if (StringUtils.isNotEmpty(preRef)) {//如果只有一个表时候 可以为空
                resource = (Table5Resource) refsMap.get(preRef);
            } else {
                resource = (Table5Resource) refsMap.get(firstKey);
            }
            preRef = StringUtils.isNotEmpty(preRef) ? preRef : firstKey;
            String varCol = expression.getVar();
            if (StringUtils.isEmpty(varCol)) {
                varCol = "code";
            }
            Column tableCol = resource.getColumn(varCol);
            Object value = expression.getValue();
            if (value instanceof String) {
                String str = (String) value;
                int indexRef = str.indexOf(".");
                if (indexRef > 0) {
                    String refName = str.substring(0, indexRef);
                    if (refsMap.get(refName.toUpperCase()) != null && StringUtils.containsOnly(str, ".")) {//如果是表和表之间条件直接拼接
                        FOperater fOperater = expression.getOperater();
                        SExpre sExpre2 = new SExpre(new SCol(varCol, preRef, null), new SCol(str, refName, null), SOperator.convert(fOperater));
                        sExpre2.setCondition(SCondition.And);
                        select.where(sExpre2);

                    } else {

                        appandCondition(expression, whereParams, preRef, varCol, tableCol);
                    }
                } else {
                    appandCondition(expression, whereParams, preRef, varCol, tableCol);
                }
            } else {
                appandCondition(expression, whereParams, preRef, varCol, tableCol);
            }
            expression = expression.getNextExpression();
        }

    }

    /**
     * 系统内置条件
     *
     * @param resource
     * @param whereParams
     */
    protected void specialColumnCondtion(Table5Resource resource, ArrayList<WhereParam> whereParams) {
        Set<String> keys = refsMap.keySet();
        Iterator<String> keySet = keys.iterator();

        while (keySet.hasNext()) {
            String key = keySet.next();//别名
            Table5Resource table5Resource = (Table5Resource) refsMap.get(key.toUpperCase());
            boolean isadate = TableCategory.isAdateTable(table5Resource);
            if (isadate) {
                String adate = null;
                if(query != null && !StringUtils.isEmpty(query.getAdate())){
                    try{
                        Date date= DateUtils.parseDate(query.getAdate(), "yyyyMMdd");
                        adate = resource.getAdateSelect(date);
                    }catch (ParseException e){
                        throw new FormulaException("操作日期格式转化失败", "0001");
                    }
                }else{
                    Date currentAdate = accountService.getCurrentAdate();
                    adate = resource.getAdateSelect(currentAdate);
                }
                SExpre sExpre2 = new SExpre(new SCol("_adate", key, null), new SParam(SParam.Parameter), SOperator.Eq);
                sExpre2.setCondition(SCondition.And);
                select.where(sExpre2);
                whereParams.add(new WhereParam(table5Resource.getColumn("_adate"), adate));
            }

        }
    }

    /**
     * 组装查询表
     *
     * @param refs
     */
    private void assemblyQueryTables(IFormulaRef[] refs) {
        SForm sForm = new SForm();
        select.form(sForm);
        for (IFormulaRef ref : refs) {
            Table5Resource table5Resource = (Table5Resource) refsMap.get(ref.getAlias());
            STable sTable = new STable();
            SJoin sJoin = null;
            switch (ref.getJoinState()) {
                case IFormulaRef.START:
                    sJoin = new SJoin(SJoinType.Start);
                    break;
                case IFormulaRef.LEFTJOIN:
                    sJoin = new SJoin(SJoinType.Left);
                    break;
                case IFormulaRef.RIGHTJOIN:
                    sJoin = new SJoin(SJoinType.Right);
                    break;
                case IFormulaRef.FULLJOIN:
                    sJoin = new SJoin(SJoinType.JoinAll);
                    break;
                case IFormulaRef.JOIN:
                    sJoin = new SJoin(SJoinType.Join);
                    break;
            }
            sTable.setName(table5Resource.getDbtable());
            sTable.setAlias(ref.getAlias());
            if (ArrayUtils.isNotEmpty(ref.getJoins())) for (IFormulaJoin jion : ref.getJoins()) {
                String leftRef = jion.getLeftRef();
                String rightRef = jion.getRightRef();

                SExpre sExpre = new SExpre(new SCol(jion.getLeftName(), leftRef, null), new SCol(jion.getRightName(), rightRef, null), SOperator.convert(jion.getOperater()));
                sExpre.setCondition(SCondition.convert(jion.getCondition()));

                SWhere on = new SWhere();
                on.expr(sExpre);
                sJoin.setOn(on);
                sTable.setJoin(sJoin);
            }

            sForm.table(sTable);
        }
    }

    /**
     * 查询列中是否有函数
     *
     * @param cols
     * @return
     */
    private boolean HasFunction(IFCol[] cols) {

        if (ArrayUtils.isNotEmpty(cols)) {
            for (IFCol c : cols) {
                if (StringUtils.isNotEmpty(c.getFun())) {
                    hasFunction = true;
                }
            }
        }
        return hasFunction;
    }

    /**
     * 组装查询列
     *
     * @param cols
     * @return
     */
    private List<SelectCol> assemblyQueryColumn(IFCol[] cols) {

        List<SelectCol> selectCols = new ArrayList<>();//保存没有函数列
        //第一张表必须查询_adate，code
        if (!hasFunction) {
            Table5Resource resource = (Table5Resource) refsMap.get(firstKey);//第一张表

            Column key = resource.getKeyColumn();
            Column codeColumn = resource.getCodeColumn();
            this.cols.add(new SCol("_KEY", firstKey, null, "_KEY"));
            SelectCol sel = new SelectCol(resource,key, new FCol("_KEY"));
            allCols.add(sel);
            if (codeColumn.getSync() > 0) {
                SelectCol sel2 = new SelectCol(resource,codeColumn, new FCol("Code"));
                this.sysAllCols.add(sel2);
                sysCols.add(new SCol("Code", firstKey, null, "Code"));

            } else {
                SelectCol sel2 = new SelectCol(resource,codeColumn, new FCol("Code"));
                this.allCols.add(sel2);
                this.cols.add(new SCol("Code", firstKey, null, "Code"));
            }

        }
        //有函数不会进
        if (!hasFunction && cols.length == 1 && StringUtils.equals(cols[0].getCol(), "*")) {
            //获取组件的全部列
            Set<String> keys = refsMap.keySet();
            Iterator<String> keySet = keys.iterator();
            while (keySet.hasNext()) {
                String key = keySet.next();//别名
                Table5Resource table5Resource = (Table5Resource) refsMap.get(key);
                for (Column c : table5Resource.getColumns()) {
                    if (c.isKeyCol() || c.isCodeCol() || ColumnCategory.Files == c.getCategory()) continue;
                    if (c.getSync() > 0) {
                        SelectCol sel2 = new SelectCol(table5Resource,c, new FCol(c.getCol()));
                        this.sysAllCols.add(sel2);
                        this.sysCols.add(new SCol(c.getCol(), key, null));

                    } else {
                        SelectCol sel2 = new SelectCol(table5Resource,c, new FCol(c.getCol()));
                        this.allCols.add(sel2);
                        this.cols.add(new SCol(c.getCol(), key, null));
                    }
                }
            }
        } else {
            for (IFCol c : cols) {

                String tableAlias = StringUtils.isEmpty(c.getRef()) ? firstKey : c.getRef();
                tableAlias = tableAlias.toUpperCase();
                Table5Resource table5Resource = (Table5Resource) refsMap.get(tableAlias);
                Column column = table5Resource.getColumn(c.getCol());

                if (StringUtils.isNotEmpty(c.getFun())) {

                    SelectCol sel = new SelectCol(table5Resource,column, c);
                    this.allCols.add(sel);
                    this.cols.add(new SCol(c.getCol(), tableAlias, c.getFun(), c.getCol()));
                } else {
                    String colName = c.getCol();
                    if (StringUtils.equalsIgnoreCase(c.getRef(), firstKey) && StringUtils.equalsIgnoreCase(colName, "_KEY")) {
                        SelectCol selectCol = (SelectCol) allCols.get(0);
                        selectCol.setFlag(false);
                        continue;
                    } else if (StringUtils.equalsIgnoreCase(c.getRef(), firstKey) && StringUtils.equalsIgnoreCase(colName, "Code")) {
                        if (column.getSync() > 0) {
                            SelectCol selectCol = (SelectCol) sysAllCols.get(0);
                            selectCol.setFlag(false);
                        } else {
                            SelectCol selectCol = (SelectCol) allCols.get(1);
                            selectCol.setFlag(false);
                        }

                        continue;
                    } else {

                        SelectCol selectCol = new SelectCol(table5Resource,null, c);
                        selectCols.add(selectCol);

                        if (column.getSync() > 0) {
                            SelectCol sel = new SelectCol(table5Resource,column, c);
                            this.sysAllCols.add(sel);
                            this.sysCols.add(new SCol(c.getCol(), tableAlias, null));

                        } else {
                            SelectCol sel = new SelectCol(table5Resource,column, c);
                            this.allCols.add(sel);
                            this.cols.add(new SCol(c.getCol(), tableAlias, null));
                        }
                    }

                }


            }
        }
        return selectCols;
    }


    /**
     * 添加查询条件
     *
     * @param expression
     * @param whereParams
     * @param preRef      前缀
     * @param varCol      操作列名称
     * @param tableCol    操作物理列对象
     */
    private void appandCondition(IFExpression expression, ArrayList<WhereParam> whereParams, String preRef, String varCol, Column tableCol) {
        FOperater fOperater = expression.getOperater();
        FCondition fCondition = expression.getCondition();
        if (fOperater == FOperater.In || FOperater.UnIn == fOperater) {
            Object value = expression.getValue();
            if (value instanceof ArrayList) {
                ArrayList<String> str = (ArrayList) value;
                ISQLValue[] values = new ISQLValue[str.size()];
                int index = 0;
                for (String val : str) {
                    values[index++] = new SParam(val);
                }
                SExpre sExpre2 = new SExpre(new SCol(varCol, preRef, null), new SParamArray(values), SOperator.convert(fOperater));
                sExpre2.setCondition(SCondition.convert(fCondition));
                select.where(sExpre2);

            }
        } else {
            SExpre sExpre2 = new SExpre(new SCol(varCol, preRef, null), new SParam(SParam.Parameter), SOperator.convert(fOperater));
            sExpre2.setCondition(SCondition.convert(fCondition));
            select.where(sExpre2);
            whereParams.add(new WhereParam(tableCol, expression.getValue()));
        }

    }

}