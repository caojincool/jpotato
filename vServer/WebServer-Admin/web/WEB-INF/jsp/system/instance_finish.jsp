<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-16
  Time: 下午6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <jsp:include page="../_header.jsp" />

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<div class="detailscontent">
    <div >
        <div class="breadcrumbs">
            <a href="dashboard.html" style="padding-bottom: 0;">系统实例相关信息设置</a>
            <span>完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle">系统实例相关信息设置完成</h1>

            <form method="post" action="">

                <div class="form_default">
                    <fieldset>
                        <legend>完成</legend>
                    </fieldset>
                </div>

                <a href="${rootPath}index?current=system/instance">返回首页</a>
            </form>
        </div>

    </div>
    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp" />
</div>
</body>
</html>