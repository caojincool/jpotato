<%--
  创建角色之选择权限
  User: dpyang
  Date: 13-1-16
  Time: 下午2:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>权限修改</title>
    <jsp:include page="../_header.jsp" />
    <link href="jquery/themes/base/jquery-ui.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/role/permissions.js"></script>
    <style type="text/css">
            /*导航样式*/
        .dh{
            background: url("/resource/images/separator2.png") no-repeat right center;
            margin-right: 10px;display: inline-block;
            color: #069;
            padding: 5px 20px 5px 0;
        }
        .header{
            height: 80px
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
            <span class="dh">选择帐号</span>
            <span class="dh">完成</span>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle">角色初始化权限</h1>
            <form method="post" action="role/finish/create">
                <input type="hidden" value="${model.name}" id="name" name="name">
                <input type="hidden" value="${model.describe}" id="remark" name="describe">
                <div id="permissionPanel"></div>
                <div class="form_default bottombtn">

                    <button type="button" onclick="javascript:history.go(-1)">返回</button><button type="button" id="next">下一步</button>

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