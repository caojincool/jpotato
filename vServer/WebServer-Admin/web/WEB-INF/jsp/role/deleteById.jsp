<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-2-6
  Time: 上午10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>删除用户确认</title>
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
<div class="detailscontent"    >

    <div class="notification msgalert">
        <p>删除改角色可能引起其他使用改角色的用户或组件无法正常使用！</p>
    </div>
        <div class="marginBreadcrumbs">

                <h1 class="pageTitle">角色详细信息</h1>

                <div id="roledetailedByExt"></div>
                <div class="form_default bottombtn">

                        <button type="button" id="back">返回</button>
                        <button type="button" id="delete">删除</button>
                        <script type="text/javascript">
                            //这里获取该角色的权限
                            var permissionkeys =${permissionKeys};
                            //这里获取该角色的用户列表
                            var accounts =${accounts};
                            Ext.fly('back').on('click', function () {
                                location.href = rootPath + 'role/view';
                            })
                            Ext.fly('delete').on('click', function () {
                                Ext.MessageBox.confirm('确认信息', '删除角色将删除该角色的所有权限及角色成员权限!请慎重操作!',
                                        function(e){
                                            if(e=='yes'){
                                                Ext.Ajax.request({
                                                    url: rootPath + 'role/delete',
                                                    method:'POST',
                                                    success: function(){
                                                        Ext.MessageBox.alert('提示','删除成功!');
                                                        location.href=rootPath+'role/view';
                                            },
                                                    params: { name: name }
                                                });
                                            }
                                            else
                                                return;
                                        }
                                );
                            })
                        </script>
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