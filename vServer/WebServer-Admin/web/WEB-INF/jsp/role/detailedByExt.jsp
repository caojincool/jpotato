<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-2-2
  Time: 下午3:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>角色详细信息</title>

    <jsp:include page="../_header.jsp"/>

    <script type="text/javascript">
        var name = '${name}';
        var describe = "${describe}";
        //这里获取该角色的权限
        var permissionkeys =${permissionKeys};
        //这里获取该角色的用户列表
        var accounts =${accounts};
    </script>
    <script type="text/javascript" src="../resource/js/role/detailedByExt.js"></script>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent"  >

        <div class="breadcrumbs">
            <a href="role/detailed">角色详细信息</a>
        </div>
          <div class="marginBreadcrumbs">
            <div style="padding:5px;">
                <h1 class="pageTitle">角色详细信息</h1>

                <div id="roledetailedByExt"></div>
                <div class="form_default bottombtn">

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
                                if (name == 'administrators' || name == 'Guests') {
                                    Ext.Msg.alert('提示', '系统内置角色禁止编辑!');
                                    return;
                                }
                                location.href = rootPath + 'role/edit/' + name;
                            })
                        </script>

                </div>
        </div>
    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</div>
</body>
</html>