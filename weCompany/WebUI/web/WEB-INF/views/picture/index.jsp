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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/uploadify/uploadify.css">
    <style type="text/css">
        <!--
        * {
            margin:0;
            padding:0;
            font-size:12px;
            text-decoration:none;
        }
        #products {
            list-style: none;
            margin:10px 0px;
        }
        #products li {
            width:210px;
            height:210px;
            margin-left:30px;
            margin-top: 50px;
            display:inline-block;
            margin-right:0px;
            _margin-right:-3px;
            float: left;
        }
        #products li a {
            display:block;
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
    <script type="text/javascript" src="/uploadify/jquery.uploadify.js"></script>

    <script type="text/javascript">
        $(function(){

            $('#dialogUpload').dialog({
                title:'对话框',
                width:350,
                height:260,

                modal:true,
                closed:true,
                buttons:[{
                    text:'关闭',
                    handler:function(){
                        $('#dialogUpload').dialog('close');
                    }
                },{
                    text:'上传',
                    handler:function(){
                        $('#uploadify').uploadify('upload','*');
                    }
                }]
            });

            $('#uploadify').uploadify({
                queueID:'queue',
                buttonText:'浏览',

                swf:'/uploadify/uploadify.swf',
                uploader:'/abc/abc',
                fileTypeExts:'*.gif; *.jpg; *.png',
                width:120,
                auto:false,
                height:10,
                onUploadStart:function(f){
                    alert('fff'+ f.name);
                },
                onUploadComplete:function(f){
                    alert('abc'+ f.name);
                },
                onSelect:function(f){
                    $('#temp').hide();
                },
                multi: true
            });

            $('#btnOpen').click(function(){
                $('#dialogUpload').dialog('open');
            });
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" border="false" style="background:#fafafa;padding:5px">
    <a href="#" id="btnOpen"  class="easyui-linkbutton" plain="true" iconCls="icon-add">上传文件</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除文件</a>
</div>

<div region="center" title="" border="false">
    <div id="dialogUpload">
        <div id="temp"><input type="file" name="uploadify" id="uploadify" /></div>
        <div id="queue"></div>
    </div>
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
