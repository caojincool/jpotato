<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-11
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员管理</title>
    <jsp:include page="../_header.jsp"/>
    <script type="text/javascript">
            var id ="<%=request.getParameter("id")%>";
    </script>
    <script type=text/javascript src="resource/js/account/accountCreate.js"></script>
</head>
<jsp:include page="../_defaultBody.jsp"/>
</html>