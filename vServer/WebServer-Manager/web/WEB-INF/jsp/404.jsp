<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-24
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<html>
<head>
    <title></title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <script type="text/javascript">
        var rootPath = '<%= basePath %>';
    </script>
    <base href="<%=basePath%>">
</head>
<%
    response.setStatus(HttpServletResponse.SC_OK);
    %>
<body>
<div align="center">
<div style="background-image: url('resource/swf/404.jpg');width: 600;height: 450;margin-bottom: auto;margin-top: auto;margin-left: auto;margin-right: auto;">
<p></p>
    <div style="margin-top: 20">
<a href="admin" style="color: #ffffff;" >主页</a>
<a href="javascript:history.go(-1)" style="color: #ffffff;">返回</a>
    </div>
</div>
</div>
</body>
</html>