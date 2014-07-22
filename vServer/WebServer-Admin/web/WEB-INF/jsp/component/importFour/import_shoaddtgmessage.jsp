<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 13-1-17
  Time: 下午4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>导入4代数据库--导入表组信息</title>
    <jsp:include page="../../_header.jsp" />
</head>
<body>

<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div>
    <div id="context" class="context" style="height: 750px;">
        <div class="breadcrumbs">
            <a href="component/importfour/db">选择帐套</a>
            <a href="component/importfour/selecttable?dblink=${dbpid}&account=${vaccount}">选择表组</a>
            <span>导入表组完成</span>
        </div>
        <div style="width: 870px; margin: 15px;">

            <div class="form_default">
                <fieldset>
                    <legend>导入信息</legend>
                    <p> 共${totalcount}条记录,成功${totalcount-errorcount}条</p>
                    <c:if test="${errorcount>0}">
                        <c:forEach var="msg" items="${message}">
                            <p>code:${msg.code};错误信息:${msg.msg}</p>
                        </c:forEach>
                    </c:if>
                    <p>
                        <button type="submit" onclick="upsetup()" style=" margin-left: 550px;">上一步</button>
                        <button type="submit" onclick="nextsetup()" style=" margin-left: 50px;">下一步</button>
                    </p>

                </fieldset>
            </div>
        </div>

    </div>

</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>
<script type="text/javascript">
    function upsetup(){
        window.location.href=""+rootPath+"component/importfour/selecttable?dblink=${dbpid}&account=${vaccount}";
    }
    function nextsetup(){
        var succnum=${totalcount-errorcount};
        if(succnum<=0){
           Ext.Msg.alert("提示","没有可挂导航的组件");
           return;
        }
        var formPanel = Ext.create('Ext.form.Panel', {
            defaults:{
                xtype:'textfield'
            },
            items: [
                {
                    fieldLabel:'codes',
                    name:'codes',
                    id:'codes'
                } ,
                {
                    fieldLabel:'dbpid',
                    name:'dbpid',
                    id:'dbpid'
                },
                {
                    fieldLabel:'accound',
                    name:'account',
                    id:'account'
                }
            ]
        });
        Ext.getCmp("codes").setValue("${codes}");
        Ext.getCmp("dbpid").setValue("${dbpid}");
        Ext.getCmp("account").setValue("${vaccount}");
        formPanel.form.url=rootPath+'component/importfour/addjurisdiction';
        formPanel.form.standardSubmit=true;
        formPanel.form.submit();
    }
</script>