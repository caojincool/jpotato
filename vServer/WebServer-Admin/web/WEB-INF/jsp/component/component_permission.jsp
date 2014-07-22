<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>组件权限分配</title>
    <jsp:include page="../_header.jsp" />
    <link type="text/css" rel="stylesheet" href="${rootPath}ext/ux/css/CheckHeader.css"/>
    <script type="text/javascript">
        var pid='${pid}';
    </script>
    <script type="text/javascript" src="resource/js/component/componentPermissions.js"></script>
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>

    <div class="detailscontent">
        <div class="breadcrumbs">
            <a href="javascript:void(0);">填写基础信息</a>
            <a href="javascript:void(0);">编辑组件内容</a>
            <a href="javascript:void(0);">编辑预览图和详情</a>
            <a href="javascript:void(0);">组件权限分配</a>
            <a href="javascript:void(0);"><span>加入导航</span></a>
            <span>完成</span>
        </div>
        <div style="margin: 15px 0px;">
            <div id="panelpermission">
            </div>
            <div class="bottombtn">
                <button class="button button_red" type="button" onclick="del();" >删除</button>
                <button id="next" class="button button_blue" type="button"  >下一步</button>
                <button id="finish" class="button button_black"   type="button"  >完成</button>
            </div>
        </div>
    </div>
<div class="extfooter">
    <jsp:include page="../common/_footer.jsp" />
</div>
<jsp:include page="common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>
</body>
</html>