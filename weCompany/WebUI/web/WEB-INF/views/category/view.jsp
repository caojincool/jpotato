<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/17
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                        window.location.href = "/category/index/";
                    });
                }
            });
        }
    </script>
</head>
<body class="easyui-layout layout">
<div style="padding: 8px;" border="false" region="center" title="栏目类别信息">
    <form id="forum" method="post" action="/category/save">
        <table>
            <tbody>
            <tr>
                <td>中文名称:
                    <input type="hidden" name="id" value="${category.id}"/></td>
                <td><input class="easyui-validatebox validatebox-text" required="true" name="name" validtype="length[1,30]" value="${category.name}"></td>
            </tr>
            <tr>
                <td>排序号:</td>
                <td><input class="easyui-validatebox validatebox-text" required="true" name="orderId" validtype="length[1,30]" value="${category.orderId}"></td>
            </tr>
            <tr>
                <td>类别:</td>
                <td>
                    <select name="categoryType">
                        <option value="ARTICLE">文章</option>
                        <option value="ARTICLELIST">文章列表</option>
                        <option value="PRODUCTLIST">产品列表</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>所属栏目:</td>
                <td>
                    <select name="forumId">
                        <c:forEach items="${forums}" var="forum">
                            <option value="${forum.id}" ${forum.id==category.forumId ? "selected='true'" : ""}>${forum.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>显示:</td>
                <td>
                    <input type="radio" name="enabled" value="true" ${category.enabled?"checked=\"true\"":""}/>显示
                    <input type="radio" name="enabled" value="false" ${category.enabled?"":"checked=\"true\""}/>隐藏
                </td>
            </tr>
            <tr>
                <td>中文关键字:</td>
                <td><input class="easyui-validatebox validatebox-text" name="keyWord" value="${category.keyWord}"></td>
            </tr>
            <tr>
                <td>中文简介:</td>
                <td><input class="easyui-validatebox validatebox-text" name="description" value="${category.description}"></td>
            </tr>
            <tr>
                <td>中文内容:</td>
                <td><textarea name="content" value="${category.content}"></textarea></td>
            </tr>
            <tr>
                <td>英文名称:</td>
                <td><input class="easyui-validatebox validatebox-text" required="true" name="nameEn" validtype="length[1,30]" value="${category.nameEn}"></td>
            </tr>
            <tr>
                <td>英文关键字:</td>
                <td><input class="easyui-validatebox validatebox-text" name="keyWordEn" value="${category.keyWordEn}"></td>
            </tr>
            <tr>
                <td>英文中文简介:</td>
                <td><input class="easyui-validatebox validatebox-text" name="descriptionEn" value="${category.descriptionEn}"></td>
            </tr>
            <tr>
                <td>英文内容:</td>
                <td><textarea name="contentEn" value="${category.contentEn}"></textarea></td>
            </tr>

            </tbody>
        </table>
    </form>
</div>
<div region="south" border="false" style="text-align: right; height: 40px; line-height: 30px; background-color: #f7f7f7;">
    <table style="width: 100%">
        <tr>
            <td></td>
            <td style="text-align: right">
                <a href="#" class="easyui-linkbutton" iconcls="icon-save" onclick="submit();">提交</a>
                <a href="#" class="easyui-linkbutton" iconcls="icon-save" onclick="submit();">提交</a>
                <a href="#" class="easyui-linkbutton" iconcls="icon-save" onclick="submit();">提交</a>
                <a href="#" class="easyui-linkbutton" iconcls="icon-cancel" onclick="location.href = document.referrer;">返回</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
