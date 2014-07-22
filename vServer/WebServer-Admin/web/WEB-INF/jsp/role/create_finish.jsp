<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  创建角色之完成
  User: dpyang
  Date: 13-1-16
  Time: 下午2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建角色</title>
    <jsp:include page="../_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 600px;
            margin: 10px auto;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="role/create">角色基本信息</a>
            <a href="role/premission/create">选择权限</a>
            <a href="role/select/account/create">选择帐号</a>
            <a href="role/finash/create">完成</a>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle">角色创建完成</h1>
            <form method="post" action="role/ok/create">
                <div class="form_default">
                    <div style="padding:5px;">
                    <fieldset>
                        <legend>完成</legend>
                        <p>
                        <h1 class="pageTitle">角色创建完成</h1>
                        </p>
                        <p>
                            <button type="submit">完成</button>
                        </p>
                    </fieldset>
                        </div>
                </div>
            </form>

    </div>
    <br clear="all"/>
</div>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>