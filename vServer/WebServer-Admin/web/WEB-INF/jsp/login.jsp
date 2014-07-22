<%--
  用户登录页面
  User: Xudong
  Date: 13-1-17
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <link href="${rootPath}resource/style/login.css" media="screen" rel="stylesheet"/>
    <script type="text/javascript" src="${rootPath}jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/login/login.js"></script>
</head>
<body>
<div class="loginlogo">
    <img src="../../resource/images/logo5.png" alt="Logo">
    <%--<object id="flash_id" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="220" height="90" align="center">&ndash;%&gt;--%>
    <%--<param name="movie" value="resource/swf/logo.swf">--%>
    <%--<param name="quality" value="high">--%>
    <%--<param name="wmode" value="transparent">--%>
    <%--<embed src="resource/swf/logo.swf" width="220" height="90" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>--%>
    <%--</object>--%>
</div>
<script type="text/javascript">
    jQuery.noConflict();

    jQuery(document).ready(function(){
        var msg='${message}';
        if(msg)
            jQuery('.loginNotify').slideDown('fast');
    });
</script>
<div class="notification notifyError loginNotify">${message==null?'请输入用户名和密码!':message}</div>
<form id="loginform" action="" method="post">
    <div class="loginbox">
        <div class="loginbox_inner">
            <div class="loginbox_content">
                <input type="text" class="username" name="username" value="" style="background-position: 0px 0px;">
                <input type="password" class="password" name="password" value=""
                       style="background-position: 0px 0px;">
                <button name="submit" class="submit">登录</button>
            </div>
            <!--loginbox_content-->
        </div>
        <!--loginbox_inner-->
    </div>
    <!--loginbox-->

    <div class="loginoption">
        <a class="cant" href="">无法访问您的账户</a>
        <input type="checkbox" name="remember">记住密码
    </div>
    <!--loginoption-->
</form>
</body>
</html>