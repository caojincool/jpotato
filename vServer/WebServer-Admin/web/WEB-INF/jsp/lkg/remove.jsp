<%--
  Created by IntelliJ IDEA.
  User: dp
  Date: 13-6-18
  Time: 上午8:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <jsp:include page="../common/_header.jsp"/>

</head>
<body class="detailsbody">
<div style="display: none;" id="cboxOverlay"></div>
<div style="padding-bottom: 20px; padding-right: 0px; display: none;" class="" id="colorbox">
    <div id="cboxWrapper">
        <div>
            <div style="float: left;" id="cboxTopLeft"></div>
            <div style="float: left;" id="cboxTopCenter"></div>
            <div style="float: left;" id="cboxTopRight"></div>
        </div>
        <div style="clear: left;">
            <div style="float: left;" id="cboxMiddleLeft"></div>
            <div style="float: left;" id="cboxContent">
                <div style="width: 0px; height: 0px; overflow: hidden; float: left;" id="cboxLoadedContent"></div>
                <div style="float: left;" id="cboxLoadingOverlay"></div>
                <div style="float: left;" id="cboxLoadingGraphic"></div>
                <div style="float: left;" id="cboxTitle"></div>
                <div style="float: left;" id="cboxCurrent"></div>
                <div style="float: left;" id="cboxNext"></div>
                <div style="float: left;" id="cboxPrevious"></div>
                <div style="float: left;" id="cboxSlideshow"></div>
                <div style="float: left;" id="cboxClose"></div>
            </div>
            <div style="float: left;" id="cboxMiddleRight"></div>
        </div>
        <div style="clear: left;">
            <div style="float: left;" id="cboxBottomLeft"></div>
            <div style="float: left;" id="cboxBottomCenter"></div>
            <div style="float: left;" id="cboxBottomRight"></div>
        </div>
    </div>
    <div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div>
</div>
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">
    <div class="notification msgalert">
        <p>删除改软件包将清除它相关一切相关信息！</p>
    </div>

        <h1 class="pageTitle">组件包详细信息</h1>

        <div class="invoice">
            <div class="invoice_inner">


                <div class="one_fourth">
                    <strong>
                        包名称: <br><br>
                        创建人: <br><br>
                        创建日期: <br><br>
                        开始页面:<br><br>
                        导入脚本:<br><br>
                        导出脚本:<br><br>
                        描述:</strong>
                </div>
                <!-- one_third -->
                <div class="three_fourth last">
                    ${basePackage.name} <br><br>
                    ${basePackage.createUser} <br><br>
                    <fmt:formatDate value='${basePackage.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/><br><br>
                    ${basePackage.startFace}<br><br>
                    ${basePackage.importScript}<br><br>
                    ${basePackage.exportScript}<br><br>
                    ${basePackage.remark}<br>
                </div>
                <!-- three_fourth last -->
            </div>

            <!-- invoice_inner -->
        </div>
        <div class="bottombtn">
            <form action="" method="post">
                <button class="iconlink" id="back" type="button"  >返回</button>
                <button class="iconlink" type="submit">删除</button>
            </form>
        </div>
        <!-- invoice three_fourth last -->

    <br clear="all">
</div>
<br>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
<script type="text/javascript">
    jQuery('#back').bind('click', function() {
     location.href="/package/main/view";
    });
</script>
</body>
</html>