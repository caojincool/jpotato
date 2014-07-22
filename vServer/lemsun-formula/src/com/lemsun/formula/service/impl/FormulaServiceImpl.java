package com.lemsun.formula.service.impl;

import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.formula.*;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.formula.FCol;
import com.lemsun.formula.FExpression;
import com.lemsun.formula.FormulaJoin;
import com.lemsun.formula.service.IFormulaService;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.FormulaQuery;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.service.IFormulaExcuter;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * 实现公式的执行接
 * User: xudong
 * Date: 13-4-22
 * Time: 下午1:42
 */
@Service
public class FormulaServiceImpl implements IFormulaService {

    private DbManager dbManager;
    private ILmsTableService lmsTableService;
    private IAccountService accountService;
    private Map<DbCategory, IFormulaExcuter> formulaExcuterMap = new HashMap<>();

    private static Logger log = LoggerFactory.getLogger(FormulaServiceImpl.class);

    @Autowired
    public FormulaServiceImpl(ILmsTableService lmsTableService,
                              IAccountService accountService,
                              Collection<IFormulaExcuter> formulaExcuters,
                              DbManager dbManager) {
        this.lmsTableService = lmsTableService;
        this.dbManager = dbManager;
        this.accountService = accountService;

        for(IFormulaExcuter excuter : formulaExcuters)
        {
            formulaExcuterMap.put(excuter.getSupportCategory(), excuter);
        }

    }

    /**
     * 1、查询出对应查询对象
     * 2、校验查询对象及 查询列 和条件是否存在
     * 3、组装sql
     * 4、执行查询
     * @param formula
     * @return
     */
    @Override
    public ArrayData getArrayData(IFormula formula) {
        Map<String,ITableResource> refsMap=new LinkedHashMap<>();
        if(verifyFormaultData(formula,refsMap,null)){
            Table5Resource resource=(Table5Resource)refsMap.values().iterator().next();
             IFormulaExcuter excuter = formulaExcuterMap.get(resource.getDbConfig().getDbCategory());//暂时只支持相同数据库
            return excuter.getData(refsMap, formula);
        }else{
            throw new FormulaException("公式数据校验失败", "0001");
        }

    }

    @Override
    public ArrayData getArrayData(IFormula formula, FormulaQuery query) {
        Map<String,ITableResource> refsMap=new LinkedHashMap<>();
        if(verifyFormaultData(formula,refsMap,query)){
            Table5Resource resource=(Table5Resource)refsMap.values().iterator().next();
            IFormulaExcuter excuter = formulaExcuterMap.get(resource.getDbConfig().getDbCategory());//暂时只支持相同数据库
            return excuter.getData(refsMap, formula,query);
        }else{
            throw new FormulaException("公式数据校验失败", "0001");
        }
    }

    /**
     * 校验公式数据
     * @param formula
     */
    private boolean verifyFormaultData(IFormula formula,Map<String,ITableResource> refsMap, FormulaQuery query)  {
        IFormulaRef[] refs=formula.getRefs();

        for(IFormulaRef ref:refs){
            LemsunResource current =null;
            if(query !=null &&!StringUtils.isEmpty(query.getAdate())){
                try{
                    Date date=DateUtils.parseDate(query.getAdate(), "yyyyMMdd");
                    current = lmsTableService.getCurrentResource(ref.getName(),date);
                }catch (ParseException e){
                    throw new FormulaException("操作日期格式转化失败", "0001");
                }
            }else{
                current = lmsTableService.getCurrentResource(ref.getName());
            }


            Table5Resource resource = lmsTableService.getResource(current.getPid());
            refsMap.put(ref.getAlias(),resource);
        }
        if(refsMap.values().size()==0){
            if(log.isInfoEnabled()){
                log.info("缺少查询对象");
            }
           return false;
        }
        //校验关联表条件
        String firstKey=refsMap.keySet().iterator().next(); //第一张表key
        Table5Resource resource=(Table5Resource)refsMap.get(firstKey);//第一张表
        for (IFormulaRef ref : refs) {
            Table5Resource table5Resource = (Table5Resource) refsMap.get(ref.getAlias());
            refsMap.put(ref.getAlias(),table5Resource);
            if (ArrayUtils.isNotEmpty(ref.getJoins())) {
                for (IFormulaJoin jion : ref.getJoins()) {
                    String leftRef = jion.getLeftRef();
                    String rightRef = jion.getRightRef();
                    if(StringUtils.isEmpty(leftRef)&&StringUtils.isEmpty(rightRef)){
                        if(log.isInfoEnabled()){
                            log.info("表格关联条件缺少别名！");
                        }
                        return false;
                    }else if(StringUtils.isEmpty(leftRef)){
                        if(jion instanceof FormulaJoin){
                            ((FormulaJoin)jion).setLeftRef(ref.getAlias());
                        }
                        if (verifyTableIFCol(table5Resource, jion.getLeftName())) return false;
                    }else if(StringUtils.isEmpty(rightRef)){
                        if(jion instanceof FormulaJoin){
                            ((FormulaJoin)jion).setRightRef(ref.getAlias());
                        }
                        if (verifyTableIFCol(table5Resource, jion.getRightName())) return false;
                    }else{
                        if (verifyTableIFCol((Table5Resource) refsMap.get(rightRef), jion.getRightName())) return false;
                        if (verifyTableIFCol((Table5Resource) refsMap.get(leftRef), jion.getLeftName())) return false;
                    }
                }
            }
        }
        //校验查询列
        IFCol[] cols=formula.getColRange().getAll();
        if(formula.getColRange()  == null || formula.getColRange().getAll() == null || cols.length == 0)
        {
            if(log.isInfoEnabled()){
                log.info("公式取数的列不能为空");
            }
            return false;
        }
        if(cols.length == 1 && StringUtils.equals(cols[0].getCol(), "*")){
            return true;
        }else{
            for(IFCol c : cols)
            {
                if(StringUtils.isEmpty(c.getRef())){
                    if(c instanceof FCol){
                        ((FCol)c).setRef(firstKey);
                    }
                    if (verifyTableIFCol((Table5Resource)refsMap.get(firstKey), c.getCol())) return false;
                }else{
                      resource= (Table5Resource)refsMap.get(c.getRef());
                    if(resource == null){
                        if(log.isInfoEnabled()){
                            log.info("查询表格"+c.getRef()+"不存在");
                        }
                        return false;
                    }else{
                        if (verifyTableIFCol(resource, c.getCol())) return false;
                    }
                }

            }
        }
        //校验表达式
        IFExpression expression = formula.getExpression();
        while (expression != null) {
            String preRef = expression.getRef();
            if (StringUtils.isNotEmpty(preRef)) {//如果只有一个表时候 可以为空
                resource = (Table5Resource) refsMap.get(preRef);
            }else{
                resource = (Table5Resource) refsMap.get(firstKey);
                if(expression instanceof FExpression){
                 ((FExpression)expression).setRef(preRef);//设置别名
               }
            }
            Object value = expression.getValue();
            if (value instanceof String) { //表达式右边是表格列时进行校验
                String str = (String) value;
                int indexRef = str.indexOf(".");
                if (indexRef > 0) {
                    String refName = str.substring(0, indexRef);
                    Table5Resource  resource2 = (Table5Resource)refsMap.get(refName.toUpperCase());
                    if (resource2 != null && StringUtils.containsOnly(str, ".")) {//如果是表和表之间条件直接拼接
                       String col= str.substring(indexRef, str.length());
                       if (verifyTableIFCol(resource2, col)) return false;
                    }
                }
            }
            if (verifyTableIFCol(resource, expression.getVar())) return false;
            expression = expression.getNextExpression();
        }
        return true;
    }

    /**
     * 校验是否存在该列
     * @param resource
     * @param col
     * @return
     */
    private boolean verifyTableIFCol(Table5Resource resource, String col) {
        Column tableCol = resource.getColumn(col);
        if(tableCol ==null){
            if(log.isInfoEnabled()){
                log.info("表格：{}下物理表名为：{}的列：{}不存在",resource.getPid(),resource.getDbtable(),col);
            }
            return true;
        }
        return false;
    }

    @Override
    public void setFormulaData(IFormula formula, String dataType, String jsonData){
        LemsunResource resource = lmsTableService.getCurrentResource(formula.getRefs()[0].getName());
        Table5Resource table = lmsTableService.getResource(resource.getPid());
        IFormulaExcuter excuter = formulaExcuterMap.get(table.getDbConfig().getDbCategory());
        excuter.update(table, formula, dataType, jsonData);
    }

}
