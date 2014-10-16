<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/10/12
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>投资计划统计系统</title>
    <link href="../css/main_login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/Jikey.js"></script>
    <script type="text/javascript">
        window.onload = function(){
            J.onBlurText('uname');
            J.onBlurText('pwd');
        }
    </script>
</head>
<body>
<div id="main">
    <div id="wrapper">
        <form action="#" method="Post" id="form1">
            <div id="sys_name">蓝焰煤层气集团<br/>投资计划统计系统</div>
            <ul id="cont">
                <li>
                    <label class="lb" for="uname">用户名</label>
                    <input name="username" id="uname" type="text" class="ip" value="请输入用户名" maxlength="18" />
                </li>
                <li>
                    <label class="lb" for="pwd">密码</label>
                    <input name="password" id="pwd" type="password" class="ip" value="haoqing" maxlength="10" />
                </li>
                <li><span>
          <input  type="image" src="../images/ente.png" title="登录系统"/>
          </span></li>
            </ul>
            <p id="copy">Version 2.0 Copyright &copy; 2010 <a href="http://jikey.cnblogs.com" target="_blank" >林木森</a></p>
        </form>
    </div>
</div>
</body>
</html>
