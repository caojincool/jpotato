<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-19
  Time: 下午12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="_adminTitle.jsp"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <div id="navigation"></div>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="_footer.jsp"/>
    </div>
</div>
</body>