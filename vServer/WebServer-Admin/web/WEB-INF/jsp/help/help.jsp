<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  组件创建基础信息界面
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>组件帮助文档</title>
    <jsp:include page="../_header.jsp" />
    <script type="text/javascript">
        var curPid='${pid}';
        var curName='${curName}';
    </script>
    <script type="text/javascript" src="resource/js/help/help.js">
    </script>
</head>
<body >
</body>
</html>