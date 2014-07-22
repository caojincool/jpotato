package com.lemsun.client.formula.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.formula.*;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.service.IFormulaService;
import com.lemsun.client.core.service.IRemoteService;
import com.lemsun.client.formula.*;
import com.lemsun.client.formula.parser.ServerFormulaLexer;
import com.lemsun.client.formula.parser.ServerFormulaParser;
import javafx.application.Application;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;


/**
 * 公式服务实现类
 * User: 宗
 * Date: 13-4-23
 * Time: 下午7:18
 *
 */
@Service
public class FormulaServiceImpl implements IFormulaService {

    private IRemoteService remoteService;
    private final String url = "formula/data/get";
    private JsonObjectMapper objectMapper;

    @Autowired
    public FormulaServiceImpl(IRemoteService remoteService, JsonObjectMapper objectMapper) {
        this.remoteService = remoteService;
        this.objectMapper = objectMapper;
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

    @Override
    public ArrayData getFormulaData(Map scope, String formula) throws FormulaException {

        Formula fml = parse(formula);

        return getFormulaData(scope, fml);
    }

    @Override
    public ArrayData getFormulaData(Map scope, IFormula formula) throws FormulaException {

        //首先检查表达式中是否含有公式
        Expression expression = (Expression)formula.getExpression();

        while (expression != null) {

            if(expression.getFormulaValue() != null) {
                expression.setValue(getDataOfTarget(scope, expression.getFormulaValue()));
            } else if(expression.getValue() instanceof List) {
                List items = (List)expression.getValue();

                for(int i=0; i<items.size();i++) {
                    Object item = items.get(i);

                    if(item instanceof IFormula) {
                        items.set(i, getDataOfTarget(scope, (IFormula)item));
                    }

                }

            }

            expression = expression.getNextExpression();
        }
            return getRemoteData(formula);

    }



    /**
     * 使用公式从一个指定目标对象中提取公式描述的值
     */
    protected Object getDataOfTarget(Map scope,IFormula formula) {
        if(scope != null && !formula.isRemote()) {
            return scope.get(formula.getRef());
        }else{
            return getFormulaData(scope,formula).getValue();
        }
    }


    /**
     * todo:此处的FormulaQuery为临时创建.
     * @param formula
     * @return
     * @throws FormulaException
     */
    protected ArrayData getRemoteData(IFormula formula) throws FormulaException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("formula", objectMapper.writeValueAsString(formula));
            FormulaQuery query=new FormulaQuery();
            params.put("query",objectMapper.writeValueAsString(query));
            IResponseEntity<ArrayData> entity =
                    remoteService.getAddressContext(
                            url,params,
                            new TypeReference<IResponseEntity<ArrayData>>() {}
                   );

            if(entity.isSuccess()) {
                return entity.getEntity();
            }
            else {
                throw new FormulaException(entity.getMessage());
            }
        } catch (IOException e) {
            throw new FormulaException(e.getMessage());
        }
    }

    @Override
    public boolean isFormula(String formula) {

        try {

            ServerFormulaParser parser = getParser(formula);
            BaseTree tree = (BaseTree) parser.formula().getTree();

            return tree != null;
        } catch (RecognitionException e) {
            return false;
        }
    }

    /**
     * 解析公式
     * @param formula 公式字符串
     * @return
     * @throws FormulaException
     */
    @Override
    public Formula parse(String formula) throws FormulaException {

        try {

            ServerFormulaParser parser = getParser(formula);

            Tree tree = ((Tree) parser.formulaEof().getTree()).getChild(0);

            return parseFormulaTree(formula, tree);

        } catch (RecognitionException e) {
            throw  new FormulaException(e.getMessage());
        }

    }

    @Override
    public IStatement parseStatement(String statement) {

        ServerFormulaParser parser = getParser(statement);




        return null;
    }


    private ServerFormulaParser getParser(String input) {
        ANTLRStringStream stream = new ANTLRStringStream(input);
        ServerFormulaLexer lexer = new ServerFormulaLexer(stream);
        CommonTokenStream token = new CommonTokenStream(lexer);
        return new ServerFormulaParser(token);
    }

    /**
     * 解析公式生成的树形结构
     */
    protected Formula parseFormulaTree(String input, Tree root) throws FormulaException {

        if(root.getType() != ServerFormulaLexer.FORMULA) {
            throw new FormulaException("解析的节点并不是一个公式节点");
        }


        ColRange colRange = null;
        Expression expression = null;
        boolean isRemote = false;
        ArrayList<IFormulaRef> refs = new ArrayList<>();
        ArrayList<ISoft> softs = new ArrayList<>();
        int joinState = IFormulaRef.START;


        for(int i=0; i < root.getChildCount(); i++) {

            Tree child = root.getChild(i);

            int t = child.getType();

            if(t == ServerFormulaLexer.REF) {
                FormulaRef ref = parseFormulaRef(child);
                ref.setJoinState(joinState);
                refs.add(ref);
            }
            else if(t == ServerFormulaLexer.COLS || t == ServerFormulaLexer.COLRANGE)
            {
                colRange = parseColRange(child);
            }
            else if(t == ServerFormulaLexer.EXPRES) {
                expression = parseExpression(child);
            }
            else if(t == ServerFormulaLexer.Remote) {
                isRemote = true;
            }
            else if(t == ServerFormulaLexer.Local) {
                isRemote = false;
            }
            else if(t == ServerFormulaLexer.SOFT) {
                softs.add(parseFormulaSoft(child));
            }
            else if(t == ServerFormulaLexer.Eq) {
                joinState = IFormulaRef.JOIN;
            }
            else if(t == ServerFormulaLexer.JoinRight) {
                joinState = IFormulaRef.RIGHTJOIN;
            }
            else if(t == ServerFormulaLexer.JoinFull) {
                joinState = IFormulaRef.FULLJOIN;
            }
            else if(t == ServerFormulaLexer.LsEq) {
                joinState = IFormulaRef.LEFTJOIN;
            }
            else if(t == ServerFormulaLexer.JOIN) {

                List<FormulaJoin> joins = parseFormulaJoin(child);

                IFormulaJoin[] fjoins = new IFormulaJoin[joins.size()];

                for(int j=0; j<joins.size(); j++) {
                    fjoins[j] = joins.get(j);
                }

                ((FormulaRef)refs.get(refs.size()-1)).setJoins(fjoins);
            }

        }


        Formula formula = new Formula(input, isRemote, refs.toArray(new IFormulaRef[]{}), colRange, expression, softs.toArray(new ISoft[]{}));

        return formula;
    }


    /**
     * 解析关联对象
     */
    protected List<FormulaJoin> parseFormulaJoin(Tree n) {

        ArrayList<FormulaJoin> joins = new ArrayList<>();

        FCondition condition = FCondition.Start;

        for(int i=0; i<n.getChildCount(); i++) {
            Tree node = n.getChild(i);

            if(node.getType() == ServerFormulaLexer.Condition) {
                condition = FCondition.parse(node.getText());
            }
            else if(node.getType() == ServerFormulaLexer.EXPRE) {
                TempName leftName = parseName(node.getChild(0));
                FOperater op = FOperater.parse(node.getChild(1).getChild(0).getText());
                TempName rightName = parseName(node.getChild(2));

                joins.add(new FormulaJoin(condition,
                        leftName.getRef(),
                        leftName.getName(),
                        rightName.getRef(),
                        rightName.getName(),
                        op));
            }
        }

        return joins;
    }


    /**
     * 解析排序对象
     */
    protected FSoft parseFormulaSoft(Tree n) {
        TempName name = null;
        int soft = ISoft.ASC;


        for(int i=0; i<n.getChildCount(); i++) {

            Tree node = n.getChild(i);


            if(node.getType() == ServerFormulaLexer.NAME) {
                name = parseName(node);
            }
            else if(node.getType() == ServerFormulaLexer.ASC) {
                soft = ISoft.ASC;
            }
            else if(node.getType() == ServerFormulaLexer.DESC) {
                soft = ISoft.DESC;
            }

        }


        return new FSoft(name.getName(), name.getRef(), soft);
    }


    /**
     * 解析引用对象
     * @param refTree
     * @return
     */
    protected FormulaRef parseFormulaRef(Tree refTree) {
        String name = "";
        String alias = null;

        for(int i=0; i<refTree.getChildCount(); i++) {
            Tree refNode = refTree.getChild(i);

            if(refNode.getType() == ServerFormulaLexer.NAME) {
                name = refNode.getText();
            }
            else {
                alias = refNode.getText();
            }

        }

        return new FormulaRef(name, alias);

    }


    /**
     * 解析树形结构中的列区域
     * @param root
     * @return
     */
    protected ColRange parseColRange(Tree root) {

        ColRange colRange = null;

        if(root.getType() == ServerFormulaLexer.COLS) {
            ArrayList<FCol> cols = new ArrayList<>();

            for(int i=0; i<root.getChildCount(); i++) {

                Tree child = root.getChild(i);

                if(child.getType() == ServerFormulaLexer.FUN) {
                    String fname = child.getChild(0).getText();

                    FCol tempC = null;

                    for(int c=0; c < child.getChildCount(); c++) {

                        Tree n = child.getChild(c);
                        if(n.getType() == ServerFormulaLexer.NAME) {
                            TempName name = parseName(n);
                            tempC = new FCol(name.getName(), name.getRef(), fname);
                            cols.add(tempC);
                        }
                        else if(tempC != null) {
                            tempC.setAlias(n.getText());
                        }

                    }
                }
                else if(child.getType() == ServerFormulaLexer.NAME) {
                    TempName name = parseName(child);
                    cols.add(new FCol(name.getName(), name.getRef(), null));
                }
                else if(child.getType() == ServerFormulaLexer.Start) {
                    cols.add(new FCol("*"));
                }
            }

            colRange = new ColRange(cols);
        }
        else if(root.getType() == ServerFormulaLexer.COLRANGE) {
            colRange = new ColRange(parseFCol(root.getChild(0)), parseFCol(root.getChild(1)));
        }

        return colRange;

    }





    /**
     * 解析列的树形结构
     */
    protected FCol parseFCol(Tree root) {

        if(root.getType() == ServerFormulaLexer.FUN) throw new FormulaException("一个公式范围的列不能带有函数");

        return new FCol(root.getText());
    }


    protected Expression parseExpression(Tree root) {

        if(root == null) return null;
        Expression startExpression = null;
        FCondition lastCondition = FCondition.Start;
        Expression lastExpression = null;
        for(int i=0; i<root.getChildCount(); i++)
        {
            Tree ex = root.getChild(i);
            int t = ex.getType();

            if(t == ServerFormulaLexer.OPERATOR
                    || t == ServerFormulaLexer.CODE
                    || t == ServerFormulaLexer.STRING_LITERAL
                    || t == ServerFormulaLexer.ARRAYVALUE
                    || t == ServerFormulaLexer.FORMULA)
            {
                Expression n = commonExpression(ex);
                n.setCondition(lastCondition);
                if(lastExpression != null) {
                    lastExpression.setNextExpression(n);
                }

                if(startExpression == null) startExpression = n;

                lastExpression = n;
            }
            else if(t == ServerFormulaLexer.Condition) {
                lastCondition = FCondition.parse(ex.getText());
            }
        }

        return startExpression;
    }



    protected Expression commonExpression(Tree expre) {


        Expression tempExpression = null;

        int t = expre.getType();

        if(t == ServerFormulaLexer.CODE
                || t == ServerFormulaLexer.STRING_LITERAL
                || t == ServerFormulaLexer.ARRAYVALUE)
        {
            tempExpression = new Expression(parseExpressionValue(expre));
        }
        else if(t == ServerFormulaLexer.OPERATOR) {

            FOperater op = FOperater.parse(expre.getChild(0).getText());
            TempName name = parseName(expre.getChild(1));
            Object value = parseExpressionValue(expre.getChild(2));
            tempExpression = new Expression(FCondition.Start, name.getRef(), name.getName(), op, value, null);
        }
        else  if(t == ServerFormulaLexer.FORMULA)
        {
            tempExpression = new Expression(parseExpressionValue(expre));
        }

        return tempExpression;
    }


    protected Object parseExpressionValue(Tree valueNode) {
        if(valueNode.getType() == ServerFormulaLexer.FORMULA) {
            return parseFormulaTree(valueNode.getText(), valueNode);
        }
        else if(valueNode.getType() == ServerFormulaLexer.ARRAYVALUE) {
            ArrayList values = new ArrayList();
            for(int i=0; i<valueNode.getChildCount(); i++) {
                values.add(parseExpressionValue(valueNode.getChild(i)));
            }
            return values;
        }
        else {
            return valueNode.getText();
        }
    }


    protected TempName parseName(Tree tree) {
        if(tree.getChildCount() > 0) {

            String name = tree.getChild(0).getText();

            String ref = null;

            if(tree.getChildCount() > 1) {
                ref = tree.getChild(1).getText();
            }

            return new TempName(name, ref);

        }

        throw new FormulaException("引用名称节点不能没有内容");
    }


    /**
     * 临时名称
     */
    protected class TempName
    {
        private String name;
        private String ref;

        public TempName(String name, String ref) {
            this.name = name;
            this.ref = ref;
        }


        public String getName() {
            return name;
        }

        public String getRef() {
            return ref;
        }
    }

}
