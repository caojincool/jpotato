<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-6
  Time: 下午2:47
  导航页面显示
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <jsp:include page="../_header.jsp"/>
    <link type="text/css" rel="stylesheet" href="${rootPath}ext/ux/css/CheckHeader.css"/>
    <script type=text/javascript src="${rootPath}resource/js/component/navigation.js"></script>
     <script type="text/javascript">
        var categorys = ${categorys};

    </script>
    <style type="text/css">
        .navItem {
            color: darkgreen;
            font-family: "Courier New";
            font-size: 10;
            padding: 5 0;
        }
    </style>
</head>
<body>

</body>
</html>