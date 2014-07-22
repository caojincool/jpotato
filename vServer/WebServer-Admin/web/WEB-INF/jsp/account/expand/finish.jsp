<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-7
  Time: 下午7:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建账号</title>
    <jsp:include page="../../common/_header.jsp" />
    <style type="text/css">
        #context {
            width: 900px;
            height: 500px;
            margin: 10px auto;
        }
    </style>
</head>
<body class="detailsbody">
    <div class="headerspace"></div>
    <jsp:include page="../../common/top.jsp" />
    <div  class="detailscontent">
        <div id="context">
            <div class="breadcrumbs">
                <a href="dashboard.html">扩展头像</a>
                <a href="dashboard.html">扩展联系方式</a>
                <a href="dashboard.html">加入角色</a>
                <a href="dashboard.html">扩展权限</a>
                <span>完成</span>
            </div>
            <div class="marginBreadcrumbs">
                <h1 class="pageTitle">完成</h1>
                <form method="post" action="account/expand/doFinish" onsubmit="return submitFun()">
                    <div class="form_default">
                        <fieldset>
                            <legend>完成</legend>
                            <div id="permissionsPanel"><h1></h1></div>
                            <p>
                                <button type="submit">完成</button>
                            </p>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>
        <br clear="all" />
    </div>
    <br/>
    <div class="footer">
        <jsp:include page="../../common/_footer.jsp"/>
    </div>
</body>
</html>