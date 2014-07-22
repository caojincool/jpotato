<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-14
  Time: 上午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        div.one
        {
            width: 1100;
            height: 600;
            background-image: url("resource/swf/bj1.jpg");
            margin-left: 10;
        }
    </style>
</head>

<body>
<script type="text/javascript">
 function ClickFun(obj){
     window.top.location.href=obj.id;
 }
</script>
<div align="center">
<div class="one">
</div>
    <div style="width: 100;height: 50;margin: -403 0 0 230;float: left;cursor: pointer" id="component/componentmain" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -403 0 0 480;float: left;cursor: pointer" id="account/accounts" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -403 0 0 590;float: left;cursor: pointer" id="root/roots" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -250 0 0 240;float: left;cursor: pointer" id="component/navigation" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -250 0 0 400;float: left;cursor: pointer" id="system/category" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -250 0 0 570;float: left;cursor: pointer" id="system/instance" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -330 0 0 200;float: left;cursor: pointer" id="component/componenttype" onclick="ClickFun(this)"></div>
    <div style="width: 100;height: 50;margin: -330 0 0 620;float: left;cursor: pointer" id="role/roles" onclick="ClickFun(this)"></div>
</div>
</body>
</html>
