<%--
  选择操作员
  User: dpyang
  Date: 13-1-24
  Time: 上午11:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>选择帐号</title>

    <jsp:include page="../common/_header.jsp"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="${rootPath}ext/ext-all.js"></script>
    <style type="text/css">
            /*导航样式*/
        .dh{
            background: url("resource/images/separator2.png") no-repeat right center;
            margin-right: 10px;display: inline-block;
            color: #069;
            padding: 5px 20px 5px 0;
        }
        .header{height: 80px}
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<link rel="stylesheet" type="text/css" href="../ext/ux/css/CheckHeader.css">
<script type="text/javascript" src="../resource/js/role/accounts.js"></script>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="role/create">角色基本信息</a>
            <a href="role/premission/create">选择权限</a>
            <a href="role/select/account/create">选择帐号</a>
            <span class="dh">完成</span>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle">选择帐号</h1>
            <form method="post" action="role/ok/create">
                <div id="accountPanel"></div>
                <div class="form_default bottombtn">
                        <input type="hidden" id="roleid" value="${roleid}">
                        <button type="button" onclick="javascript:history.go(-1)">返回</button>
                        <button type="button" id="next">下一步</button>
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