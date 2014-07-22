<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-13
  Time: 下午3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="../admin/_header.jsp"/>
</head>
<body>
<script type="text/javascript">
    function submitFun(){
        if(username.value!="" && password.value!=""){
            Ext.Ajax.request({
                url :"admin/submitMessage",
                params : {
                    name:username.value,
                    password:password.value
                },
                method : 'POST',
                success : function(resp,opts) {
                var respText = Ext.decode(resp.responseText);
                if(respText.success)
                   window.location.href=""+rootPath+"admin";
                else
                    Ext.Msg.alert("失败", respText.message);
                },
                failure : function() {
                Ext.Msg.alert("提示", "调用失败");
                }
            });
        }

    }
</script>
<div align="center">
<div class="loginlogo">
    <img alt="Logo" src="resource/image/logo.png">
</div>
<div style="color: #ffffff;font-size: 16px">用户名和密码为必填项！</div>
    <div class="loginbox">
        <div class="loginbox_inner">
            <div class="loginbox_content">
                <input type="text" class="username" id="username" name="username" style="background-position: 0pt 0pt;">
                <input type="password" class="password" id="password" name="password" style="background-position: 0pt 0pt;">
                <button class="submit" name="submit" onclick="submitFun()">登 录</button>
            </div>
        </div>
    </div>

    <div class="loginoption">
        <a class="cant" href="">找回密码</a>
        <div style="float: left;">
        <input type="checkbox" name="remember"><label style="margin-left: 3">记住我</label>
        </div>
    </div>
</div>
</body>
</html>