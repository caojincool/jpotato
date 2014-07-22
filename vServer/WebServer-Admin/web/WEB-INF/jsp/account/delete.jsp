<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-9-16
  Time: 上午8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看帐号详细信息</title>
    <jsp:include page="../common/_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 700px;
            margin: 10px auto;
        }

        .categoryTitle {
            font-size: 18px;
            border-bottom: 1px solid #225;
        }

        .firstColom {
            width: 20%;
            text-align: right;
            height: 35px;
            padding-right: 18px;
        }

        .divMargin {
            width: 100%;
            margin: 15px 0px 25px 0px;;
        }

    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<jsp:include page="deleteBody.jsp"/>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>