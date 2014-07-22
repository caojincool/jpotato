<%--
  User: dpyang
  Date: 13-2-6
  Time: 下午1:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../_header.jsp"/>
    <script type="text/javascript">
        var name = '${name}';
        var describe = "${describe}";
        //这里获取该角色的权限
        var permissionkeys =${permissionKeys};
        //这里获取该角色的用户列表
        var accounts =${accounts};
    </script>
    <script type="text/javascript" src="../resource/js/role/edit.js"></script>

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
                <div class="form_default">

                        <div class="bottombtn">
                    <button type="button" id="back">返回</button>
                    <button type="button" id="submit">提交</button>
                        </div>
                        <script type="text/javascript">
                            //这里获取该角色的权限
                            var permissionkeys =${permissionKeys};
                            //这里获取该角色的用户列表
                            var accounts =${accounts};

                            Ext.fly('back').on('click', function () {
                                location.href = rootPath + 'role/view';
                            })
                            Ext.fly('submit').on('click', function () {
                                var param = {
                                    name: Ext.getCmp('name').getValue(),
                                    describe: Ext.getCmp('describe').getValue()
                                    //permissions:[]
                                };

//                                for(var i=0;i<2;i++){
//                                    param.permissions.push({
//                                        fullkey:'sd',
//                                        permission: 'ds'
//                                    });
//                                }

                                Ext.getBody().mask('正在提交');

                                Ext.Ajax.request({
                                    url: rootPath + 'role/edited/'+name,
                                    params:param,
                                    method:'POST',
                                    success:function(response){
                                        var obj = Ext.JSON.decode(response.responseText);
                                        if(obj.entity=='success')
                                            location.href=rootPath+'role/view';
                                    }
                                });
                                Ext.getBody().unmask();
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