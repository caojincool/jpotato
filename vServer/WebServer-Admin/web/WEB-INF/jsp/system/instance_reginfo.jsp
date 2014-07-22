<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-16
  Time: 下午4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建系统实例</title>
    <jsp:include page="../common/_header.jsp"/>
    <script src="jquery/validation/vanadium.js"></script>
    <script src="jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="jquery/validation/form.css" />
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
            <a href="javascript:void(0);" style="padding-bottom: 0;">创建系统实例相关注册信息</a>
            <span>完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle" style="margin-bottom: 0;">系统实例相关注册信息设置</h1>

            <form method="post" action="system/instance/create/save"  id="formupdate">

                <div class="form_default">
                    <fieldset
                            style="width :98.5%; margin :5px; padding: 5px; border :1px solid #666; background :#F7F7F7;">
                        <legend style="font-size: 16px;">相关注册信息</legend>
                        <input type="hidden" id="name" name="name" value="${imodel.getName()}">
                        <input type="hidden" id="categorytype" name="categorytype" value="${imodel.getCategorytype()}">
                        <input type="hidden" id="remark" name="remark" value="${imodel.getRemark()}">
                        <input type="hidden" id="address" name="address" value="${imodel.getAddress()}">
                        <input type="hidden" id="ip" name="ip" value="${imodel.getIp()}">
                        <input type="hidden" id="txKey" name="txKey" value="${imodel.getTxKey()}">
                        <input type="hidden" id="startPage" name="startPage" value="${imodel.startPage}"/>
                        <input type="hidden" id="logon" name="logon" value="${imodel.logon}">
                        <input type="hidden" id="logout" name="logout" value="${imodel.logout}">
                        <input type="hidden" id="error" name="error" value="${imodel.error}">

                        <p style="color: red;">
                            <label style="color: #000000;" for="User">使用人:</label>
                            <input type="text" class="sf" id="user" name="user" value="${imodel.user}">
                        </p>

                        <p style="color: red;">
                            <label style="color: #000000;" for="jiqicode">机器码:</label>
                            <input type="text" class="sf -required" id="jiqicode" name="jiqicode" value="${imodel.getJiqicode()}">

                        </p>

                        <p>
                            <label for="divqydate">启用日期:</label>


                        <input class="sf" id="qyDate" type="text" id="divqydate" name="qyDate" value="<fmt:formatDate value='${imodel.qyDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                        </p>

                        <p>
                            <label for="jsDate">结束日期:</label>

                        <input  class="sf"  id="jsDate" type="text" name="jsDate" value="<fmt:formatDate value='${imodel.jsDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">


                        <p>
                            <label for="regRemark">注册说明信息:</label>
                            <textarea rows="" cols="" class="mf" id="regRemark"
                                      name="regRemark">${imodel.getRegRemark()}</textarea>
                        </p>



                    </fieldset>
                    <div  class="bottombtn">
                        <button  type="button" onclick="javascript:goup();">上一步</button>

                        <button  type="submit">完成</button>
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
<script>
    jQuery(function() {
        jQuery( "#qyDate" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
                jQuery( "#jsDate" ).datepicker( "option", "minDate", selectedDate );
            }
        });
        jQuery( "#jsDate" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
                jQuery( "#qyDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });

    });
</script>
<script type="text/javascript">



    function goup() {
        document.getElementById('formupdate').method = 'post';
        document.getElementById('formupdate').action = 'system/instance/create/security';
        document.getElementById('formupdate').submit();
    }


</script>