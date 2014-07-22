<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-13
  Time: 下午2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-修改DB</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery("#dbCategory").find("option").each(function(){
                        if(jQuery(this).val()=='${resource.dbCategory}')
                        {
                            jQuery(this).attr("selected","selected");
                        }
                    }
            );
        });
        function editdb()
        {
            Ext.Ajax.request({
                url :"component/componentmain/db/update",
                params : {
                    pid:'${resource.pid}',
                    server:Ext.get('server').getValue(),
                    dbCategory:jQuery("#dbCategory").val(),
                    username:Ext.get('username').getValue(),
                    password:Ext.get('password').getValue(),
                    maxActive:Ext.get('maxActive').getValue(),
                    maxIdea:Ext.get('maxIdea').getValue(),
                    maxWait:Ext.get('maxWait').getValue()
                },
                method : 'POST',
                success : function(resp,opts) {
                    var respText = Ext.decode(resp.responseText);
                    if(respText.success)
                        Ext.Msg.alert("成功", "修改成功");
                    else
                        Ext.Msg.alert("失败", respText.message);
                },
                failure : function() {
                    Ext.Msg.alert("提示", "方法调用失败");
                }
            });
        }
        function returnurl()
        {
            window.top.location.href=rootPath+ "component/componentmain";
        }
    </script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=修改DB"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <div id="navigation">
            <div></div>
            <div>
                <table class="selftable">
                    <tr>
                        <td class="selftablelef">名称</td>
                        <td>${resource.name}</td>
                    </tr>
                    <tr>
                        <td>数据类型</td>
                        <td><select id="dbCategory" name="dbCategory" style="width: 100px; height: 20px; padding: 0;">
                            <option>SQLSERVER</option>
                            <option>MYSQL</option>
                            <option>ORCALE</option>
                            <option>DB2</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>连接字符串</td>
                        <td><input id="server" type="text" value="${resource.server}"/></td>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td><input id="username" type="text" value="${resource.username}"/></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input id="password" type="text" value="${resource.password}"/></td>
                    </tr>
                    <tr>
                        <td>最大连接数</td>
                        <td><input id="maxActive" type="text" value="${resource.maxActive}"/></td>
                    </tr>
                    <tr>
                        <td>最小连接数</td>
                        <td><input id="maxIdea" type="text" value="${resource.maxIdea}"/></td>
                    </tr>
                    <tr>
                        <td>等待时长</td>
                        <td><input id="maxWait" type="text" value="${resource.maxWait}"/></td>
                    </tr>
                </table></div>
            <div>
                <input class="selfbutton" type="button" onclick="javascript:editdb();" value="修改">
                <input class="selfbutton" type="button" onclick="javascript:returnurl();" value="返回">
            </div>
        </div>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>