<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-14
  Time: 上午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>main</title>
    <jsp:include page="_header.jsp"/>
</head>
<body class="bodygrey">
<div class="defaultbody">
    <div class="header">
        <jsp:include page="_adminTitle.jsp"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <jsp:include page="_adminRight.jsp"/>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="_footer.jsp"/>
    </div>
</div>
</body>
</html>