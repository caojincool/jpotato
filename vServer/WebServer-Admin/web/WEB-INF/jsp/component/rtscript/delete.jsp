<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-9-11
  Time: 下午2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <jsp:include page="../../_header.jsp" />
    <link rel="stylesheet" href="resource/codemirror/lib/codemirror.css">
    <script src="resource/codemirror/lib/codemirror.js"></script>
    <script src="resource/codemirror/addon/edit/matchbrackets.js"></script>
    <script src="resource/codemirror/addon/edit/continuecomment.js"></script>
    <script src="resource/codemirror/javascript.js"></script>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>
<jsp:include page="deleteBody.jsp" />
<br>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
<jsp:include page="../common/dialog.jsp" />
</body>
</html>