<%--
  User: Xudong
  Date: 13-1-12
  Time: 上午9:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改5代表</title>
    <jsp:include page="../../_header.jsp"/>
    <script type="text/javascript" src="${rootPath}resource/js/component/editer/tablesgp5.js"></script>
    <script type="text/javascript">
        var pid='${pid}';
    </script>
    <%--这里是系统内置组件的行样式--%>
    <style type="text/css">
        .child-row .x-grid-cell {
            background-color: #ffe2e2;
            color: #900;
        }
    </style>




</head>
<body class="detailsbody">


<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
    <div>
        <div class="breadcrumbs">
            <a href="#">编辑5代表组</a>
        </div>
        <div style="width: 950px; margin: 15px;">
            <h1 class="pageTitle">5 代数据表组修改</h1>
            <div id="webEditors">
            </div>

        <div class="bottombtn">

               <button id="btnOk" class="button button_black"   type="button" >保存</button>

        </div>

    </div>
    <br clear="all"/>
</div>
 </div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>