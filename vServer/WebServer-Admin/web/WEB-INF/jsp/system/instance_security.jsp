<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-16
  Time: 下午4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建系统实例</title>
    <jsp:include page="../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css" />
    <style type="text/css">
        .hiddeninput {
            visibility: collapse;
        }
            /*导航样式*/
        .dh{
            background: url("/resource/images/separator2.png") no-repeat right center;
            margin-right: 10px;display: inline-block;
            color: #069;
            padding: 5px 20px 5px 0;
        }
    </style>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">
    <div >
        <div class="breadcrumbs">
            <a href="javascript:void(0);" style="padding-bottom: 0;">创建系统实例基本信息</a>
            <a href="javascript:void(0);" style="padding-bottom: 0;">创建系统实例安全信息</a>
            <span class="dh">创建系统实例相关注册信息</span>
            <span>完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle" style="margin-bottom: 0;">系统实例安全信息设置</h1>

            <form method="post" action="system/instance/create/reginfo"
                  id="formupdate">

                <div class="form_default">
                    <fieldset
                            style="width :98.5%; margin :5px; padding: 5px; border :1px solid #666; background :#F7F7F7;">
                        <legend style="font-size: 16px;">安全信息</legend>

                        <input type="hidden" id="name" name="name" value="${imodel.getName()}">
                        <input type="hidden" id="categorytype" name="categorytype" value="${imodel.getCategorytype()}">
                        <input type="hidden" id="remark" name="remark" value="${imodel.getRemark()}">
                        <input type="hidden" id="address" name="address" value="${imodel.getAddress()}">
                        <input type="hidden" id="qyDate" name="qyDate" value="${imodel.getQyDate()}">
                        <input type="hidden" id="jsDate" name="jsDate" value="${imodel.getJsDate()}">
                        <input type="hidden" id="startPage" name="startPage" value="${imodel.startPage}"/>
                        <input type="hidden" id="User" name="User" value="${imodel.getUser()}">
                        <input type="hidden" id="jiqicode" name="jiqicode" value="${imodel.getJiqicode()}">
                        <input type="hidden" id="regRemark" name="regRemark" value="${imodel.getRegRemark()}">
                        <input type="hidden" id="logon" name="logon" value="${imodel.logon}">
                        <input type="hidden" id="logout" name="logout" value="${imodel.logout}">
                        <input type="hidden" id="error" name="error" value="${imodel.error}">

                        <p style="color: red;">
                            <label style="color: #000000;" for="ip">IP地址:</label>
                            <input type="text" class="sf  -ip -required;" id="ip" name="ip" value="${imodel.getIp()}">
                        </p>

                        <p style="color: red;">
                            <label style="color: #000000;" for="txKey">通讯密钥:</label>
                            <input type="text" class="sf -required" id="txKey" name="txKey" value="${imodel.getTxKey()}">
                        </p>


                    </fieldset>
                    <div class="bottombtn">
                        <button type="button" onclick="javascript:goup();">上一步</button>

                        <button  type="submit">下一步</button>
                    </div>

                </div>
            </form>


        </div>

    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>

<script type="text/javascript">

    function goup() {
        document.getElementById('formupdate').method = 'post';
        document.getElementById('formupdate').action = 'system/instance/create';
        document.getElementById('formupdate').submit();
    }


</script>