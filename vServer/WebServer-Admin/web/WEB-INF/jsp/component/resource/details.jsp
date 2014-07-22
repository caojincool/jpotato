<%--
  Created by IntelliJ IDEA.
  User: 刘晓宝
  Date: 12-10-19
  Time: 下午6:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <jsp:include page="../../_header.jsp" />
    <script src="resource/js/plugins/colorpicker.js" type="text/javascript"></script>
    <script src="resource/js/plugins/jquery.jgrowl.js" type="text/javascript"></script>
    <script src="resource/js/plugins/jquery.alerts.js" type="text/javascript"></script>
    <script src="resource/js/custom/elements.js" type="text/javascript"></script>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>



<jsp:include page="detailsBody.jsp"/>

<br>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>

</body>
</html>