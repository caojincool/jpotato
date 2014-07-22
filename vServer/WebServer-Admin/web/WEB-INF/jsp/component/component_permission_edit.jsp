<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>组件权限分配</title>
    <jsp:include page="../_header.jsp" />
    <link type="text/css" rel="stylesheet" href="${rootPath}ext/ux/css/CheckHeader.css"/>
    <link href="jquery/themes/base/jquery-ui.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript">
        var pid='${pid}';
        var type='${type}';
    </script>
    <script type="text/javascript" src="${rootPath}resource/js/component/componentPermissions_edit.js"></script>

</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>
<div class="detailscontent">
    <div class="breadcrumbs">
        <a href="#">编辑组件权限</a>
    </div>
    <div style=" margin: 15px 0px;">
        <h1 class="pageTitle">编辑组件权限</h1>
            <div id="panelpermission">
            </div>
            <div class="form_default">
                <div class="bottombtn">
                    <p>

                        <button id="btnNext">保存</button>
                    </p>
                </div>
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