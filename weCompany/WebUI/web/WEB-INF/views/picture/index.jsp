<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/18
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
    <style type="text/css">
        <!--
        * {
            margin:0;
            padding:0;
            font-size:12px;
            text-decoration:none;
        }
        #products {
            margin:10px 0px;
        }
        #products li {
            width:210px;
            height:210px;
            float:left;
            margin-left:30px;
            margin-top: 20px;
            display:inline;
            margin-right:-3px;
            zoom:1;
        }
        #products li a {
            /*display:block;*/
        }
        #products li a img {
            border:1px solid #666;
            padding:1px;
        }
        #products li span a {
            width:200px;
            height:30px;
            line-height:24px;
            text-align:center;
            white-space:nowrap;
            text-overflow:ellipsis;
            overflow: hidden;
        }
        -->
    </style>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#btnMkdir').click(function () {
                location.href="/fo";
            });
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" border="false" style="background:#fafafa;padding:5px">
    |
    <a href="#"  class="easyui-linkbutton" plain="true" iconCls="icon-add">上传文件</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除文件</a>
</div>
<div region="south" border="false" style="height:30px;background:#fafafa;">
    <div id="pp"></div>
</div>
<div region="center" title="" border="false">
    <form id="frmPic">
    <ul id="products">
        <c:forEach items="${picList}" var="s">
                <li><a href="#">
                    <img src="/upload${p}/${s}" alt="" width="200"
                         height="200"/></a>
                    <span><a href="#">${s}</a></span>
                    <input type="checkbox" name="ch1" value="/upload${p}/${s}"/>
                </li>
        </c:forEach>
    </ul>
    </form>
</div>
</body>
</html>
