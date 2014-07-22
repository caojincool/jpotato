<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-24
  Time: 下午3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isErrorPage="true" %>
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
<body>
<div align="center">
<div style="margin-top:auto; margin-bottom:auto;margin-left:auto; margin-right:auto;background-image: url('resource/swf/500.png');width: 700;height: 400">
    <div>
<textarea type="text" readonly="readonly" style="width: 400;overflow: hidden;margin-top: 230;margin-left: 210;background: transparent;color: #ffffff;border: 0"><%=exception.getMessage()%></textarea>
    </div>
    <div style="margin-left: auto;margin-top: 90">
<a href="javascript:history.go(-1)" style="color: #ffffff" >返回</a>
    </div>
</div>
</div>
</body>
</html>