<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 13-1-17
  Time: 上午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>导入4代数据库--选择帐套</title>
    <jsp:include page="../../_header.jsp" />
</head>
<body>
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div>
    <div id="context" class="context" style="height: 600px;">
        <div class="breadcrumbs">
            <span>选择帐套</span>
        </div>
        <div style="width: 870px; margin: 15px;">

            <h1 class="pageTitle">请选择要导入的数据库连接和帐套</h1>

            <form method="get" action="component/importfour/selecttable">

                <div class="form_default" style="height: 450px; padding: 5px; background-color: #f7f7f7;">
                    <fieldset style="margin: 5px;">
                        <legend>选择帐套</legend>
                        <p>
                            <label>数据库</label>
                            <select name="dblink" id="dblink" style="width: 150px;" onchange="getAccountNames()">
                                <c:forEach var="d" items="${db}">
                                    <option value="${d.pid}">${d.name}</option>
                                </c:forEach>
                            </select>
                        </p>

                        <p>
                            <label>帐套选择</label>
                            <select name="account" id="account" style="width: 150px;">
                            </select>
                        </p>

                        <p>
                            <button type="submit">下一步</button>
                        </p>

                    </fieldset>
                </div>



            </form>
        </div>

    </div>

</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
<script type="text/javascript">
    function getAccountNames(){
        Ext.get('account').setHTML('');
        Ext.Ajax.request({
            url: rootPath + 'component/importfour/getaccount',
            params: {
                dbpid:Ext.get('dblink').getValue()
            },
            method :'POST',
            success: function(response){
                var re = Ext.decode(response.responseText);
                var data = re.entity;

                if(data == null) return;

                Ext.DomHelper.useDom=true;
                for(var i=0; i<data.length; i++) {
                    Ext.DomHelper.append('account', {tag:'option', value:data[i].code, html:data[i].name})
                }
            }
        });
    }
    getAccountNames();
</script>
</body>
</html>