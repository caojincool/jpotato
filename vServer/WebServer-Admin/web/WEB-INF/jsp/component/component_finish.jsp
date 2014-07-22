<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
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
    <title>组件创建完成</title>
    <jsp:include page="../common/_header.jsp" />

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>
<div class="detailscontent">
    <div id="context" style="height: 600px;">
        <div class="breadcrumbs">
            <a href="javascript:void(0);">填写基础信息</a>
            <a href="javascript:void(0);">编辑组件内容</a>
            <a href="javascript:void(0);">编辑预览图和详情</a>
            <a href="javascript:void(0);">组件权限分配</a>
            <a href="javascript:void(0);">加入导航</a>
            <span style="color: #006699">完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle">组件创建完成</h1>

            <form method="post" action="">

                <div class="form_default">
                    <fieldset>
                        <legend>完成提示</legend>
                         <h2>名称为（${resource.name}）组件创建成功</h2>
                        <div class="bottombtn">
                            <p>
                                <button type="button" onclick="javascript:window.location.href='component/main/create?type=${sessionScope.type}';">
                                    继续创建
                                </button>
                                <button type="button" onclick="javascript:window.location.href='index'">返回首页</button>
                                <button type="button" onclick=" closeWin();">关闭</button>
                            </p>
                        </div>
                    </fieldset>

                </div>


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