<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-3-7
  Time: 上午9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>选择角色</title>
    <jsp:include page="../../_header.jsp"/>
    <%--<link rel="stylesheet" type="text/css" media="screen" href="${rootPath}ext/resources/css/ext-all.css" />--%>
    <link href="jquery/themes/base/jquery-ui.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/account/permissions.js"></script>
    <style type="text/css">
        #context {
            width: 900px;
            height: 600px;
            margin: 10px auto;
        }
        .header{height: 80px}
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div  class="detailscontent">

        <div class="breadcrumbs">
            <a href="dashboard.html">扩展头像</a>
            <a href="dashboard.html">扩展联系方式</a>
            <a href="dashboard.html">加入角色</a>
            <a href="dashboard.html">扩展权限</a>
            <span>完成</span>
        </div>
        <div  class="marginBreadcrumbs">
            <h1 class="pageTitle">选择权限</h1>

            <div id="permissionsPanel"></div>


            <div class="form_default bottombtn">

                    <%--<button type="button" onclick="javascript:location.href=rootPath+'account/expand/roles'">返回</button>--%>
                    <button type="button" id="next">下一步</button>

            </div>

    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>