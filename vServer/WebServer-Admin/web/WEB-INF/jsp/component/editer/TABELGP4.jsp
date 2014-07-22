<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-12
  Time: 上午9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改4代表</title>
    <jsp:include page="../../common/_header.jsp" />
    <style type="text/css">
        #context {
            width: 900px;
            height: 800px;
            margin: 10px auto;
        }
    </style>
    <script type="text/javascript" src="resource/js/component/editer/tablegp4.js"></script>
</head>
<body>

<div>
    <div id="context">
        <div class="breadcrumbs">
            <a href="dashboard.html">创建基础信息</a>
            <a href="dashboard.html">编辑组件内容</a>
            <a href="dashboard.html">组件权限分配</a>
            <a href="dashboard.html">快捷导航设置</a>
            <span>完成</span>
        </div>
        <div style="width: 870px; margin: 15px;">

            <h1 class="pageTitle">4 代数据表维护</h1>

            <div id="tabelgp">
            </div>
        </div>

    </div>
    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>