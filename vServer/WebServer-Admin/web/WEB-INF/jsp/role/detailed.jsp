<%--
  角色详细信息页面
  User: dpyang
  Date: 13-1-21
  Time: 上午10:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>角色详细信息</title>
    <jsp:include page="../_header.jsp"/>
    <script type="text/javascript" src="../resource/js/role/detailed.js"></script>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent"  >

        <div class="breadcrumbs">
            <a href="role/detailed">角色详细信息</a>
        </div>
        <div class="marginBreadcrumbs">
            <div style="background-color: rgb(247, 247, 247);padding:5px;">
                <h1 class="pageTitle">角色详细信息</h1>

                <div><p>
                    <label for="name" style="width: auto; float: none;">角色名称</label>
                    <label id="name" class="sf" style="width: auto;float: none;">${name}</label>
                    <%--<input id="name" type="text" class="sf" name="name">--%>
                </p>
                    <br/>

                    <p>
                        <label for="describe" style="width: auto;float: none;">角色说明</label>
                        <label id="describe" class="sf" style="width: auto;float: none;">${describe}</label>
                        <%--<textarea id="describe" rows="10" cols="30" class="sf" name="describe"></textarea>--%>
                    </p></div>
                <br/>

                <div id="permissionsPanel"></div>
                <br/>

                <div id="accountsPanel"></div>

                <div class="form_default">

                    <p>
                        <button type="button" id="back">返回</button>
                        <button type="button" id="update">编辑</button>
                        <script type="text/javascript">
                            //这里获取该角色的权限
                            var permissionkeys =${permissionKeys};
                            //这里获取该角色的用户列表
                            var accounts =${accounts};
                            Ext.fly('back').on('click', function () {
                                location.href = rootPath + 'role/view';
                            })
                            Ext.fly('update').on('click', function () {

                            })
                        </script>
                    </p>
                </div>
        </div>
    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>