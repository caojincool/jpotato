<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 12-10-19
  Time: 下午6:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../_header.jsp" />
    <script type="text/javascript">
        var catedata = ${category};
        var navdata = ${navigate};
    </script>
    <script type="text/javascript" src="resource/js/resource/list.js" ></script>
</head>
<body>
    <form id="form" action="resource/create" method="get" style="display: none;">
    </form>
</body>
</html>