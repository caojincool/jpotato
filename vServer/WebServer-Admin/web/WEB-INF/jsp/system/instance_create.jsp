<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-16
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <title>创建系统实例</title>
    <jsp:include page="../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css"/>
    <style type="text/css">
        .dateshow input[type="text"] {
            margin: 0;
            padding: 0
        }

            /*导航样式*/
        .dh {
            background: url("/resource/images/separator2.png") no-repeat right center;
            margin-right: 10px;
            display: inline-block;
            color: #069;
            padding: 5px 20px 5px 0;
        }
    </style>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="javascript:void(0);" style="padding-bottom: 0;">创建系统实例基本信息</a>
            <span class="dh">创建系统实例安全信息</span>
            <span class="dh">创建系统实例相关注册信息</span>
            <span class="dh">完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle" style="margin-bottom: 0;">系统实例基本信息设置</h1>

            <form method="post" action="system/instance/create/security"
                  id="formupdate">

                <div class="form_default">
                    <fieldset>
                        <legend style="font-size: 16px;">实例信息</legend>

                        <p style="color: red;">
                            <label style="color: #000000;" for="name">实例名称:</label>
                            <input type="text" class="sf  -required" id="name" name="name" value="${imodel.name}">
                        </p>

                        <p>
                            <label for="categorytype">系统类型:</label>
                            <select id="categorytype" name="categorytype" tabindex="2" style="width: 260px;">
                                <c:forEach var="category" items="${categorylist}">
                                    <option value="${category.category}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </p>

                        <p>
                            <label for="address">位置:</label>
                            <input type="text" class="sf" id="address" name="address" value="${imodel.address}">
                        </p>

                        <p>
                            <label for="startPage">首页组件:</label>
                            <input type="text" class="sf" id="startPage" name="startPage" value="${imodel.startPage}">
                        </p>

                        <p>
                            <label for="startPage">登录组件:</label>
                            <input type="text" class="sf" id="logon" name="logon" value="${imodel.logon}">
                        </p>

                        <p>
                            <label for="startPage">注销组件:</label>
                            <input type="text" class="sf" id="logout" name="logout" value="${imodel.logout}">
                        </p>

                        <p>
                            <label for="startPage">错误处理组件:</label>
                            <input type="text" class="sf" id="error" name="error" value="${imodel.error}">
                        </p>


                        <input type="hidden" id="ip" name="ip" value="${imodel.ip}">
                        <input type="hidden" id="txKey" name="txKey" value="${imodel.getTxKey()}">

                        <input type="hidden" id="User" name="User" value="${imodel.getUser()}">
                        <input type="hidden" id="jiqicode" name="jiqicode" value="${imodel.getJiqicode()}">
                        <input type="hidden" id="regRemark" name="regRemark" value="${imodel.getRegRemark()}">

                        </p>

                        <p>
                            <label for="remark">说明:</label>
                            <textarea rows="2" cols="35" id="remark"
                                      name="remark">${imodel.getRemark()}</textarea>
                        </p>


                    </fieldset>
                    <div  class="bottombtn">
                        <button  type="button"
                                 onclick="javascript:window.location.href=''+rootPath+'index?current=system/instance';">
                            上一步
                        </button>

                        <button  type="submit">下一步</button>
                    </div>
                </div>
            </form>
        </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>

