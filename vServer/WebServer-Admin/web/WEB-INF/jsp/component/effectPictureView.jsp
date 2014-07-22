<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-10-23
  Time: 下午3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预览图片</title>
    <jsp:include page="../common/_header.jsp"/>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>
<div class="detailscontent" >
    <div class="breadcrumbs">
        <a href="javascript:void(0);">(${name})效果图</a>
    </div>
    <div style="margin: 15px 0px;">
        <img src="/help/document/${pid}/preView?size=-1">
    </div>

</div>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>