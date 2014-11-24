<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/15
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
    <script type="text/javascript">
        function submit() {
            $('#forum').form('submit', {
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (msg) {
                    $.messager.alert('提示', "保存成功", 'info', function () {
                        window.location.href = "/forum/index/";
                    });
                }
            });
        }
    </script>
</head>
<body class="easyui-layout layout">
<div style="padding: 8px;" border="false" region="center" title="栏目信息">
    <form id="forum" method="post" action="/forum/save">
        <table>
            <tbody><tr>
                <td>中文名称:<input type="hidden" name="id" value="${forum.id}"/> </td>
                <td><input class="easyui-validatebox validatebox-text" required="true" name="name" validtype="length[1,30]" value="${forum.name}"></td>
            </tr>
            <tr>
                <td>英文名称:</td>
                <td><input class="easyui-validatebox validatebox-text" required="true" name="nameEn" validtype="length[1,30]" value="${forum.nameEn}"></td>
            </tr>
            <tr>
                <td>显示:</td>
                <td>
                        <input type="radio" name="enabled" value="true" ${forum.enabled?"checked=\"true\"":""}/>显示
                        <input type="radio" name="enabled" value="false" ${forum.enabled?"":"checked=\"true\""}/>隐藏
                </td>
            </tr>
            </tbody></table>
    </form>
</div>
<div region="south" style="text-align: right; height: 40px; line-height: 30px; background-color: #f7f7f7;">
    <table style="width: 100%">
        <tr>
            <td></td>
            <td style="text-align: right">
                <a href="#" class="easyui-linkbutton" iconcls="icon-save" onclick="submit();">提交</a>
                <a href="#" class="easyui-linkbutton" iconcls="icon-cancel" onclick="location.href = document.referrer;">返回</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
