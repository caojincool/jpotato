<%--
  User: Administrator
  Date: 13-1-23
  Time: 上午11:51
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>任务修改</title>
    <jsp:include page="../../common/_xmlcodeheader.jsp"/>
    <script type="text/javascript">

        //组件基本信息
        var lr =${lemsunResource};
        var type = '${sessionScope.type}';
    </script>
    <style type="text/css">
        .child-row .x-grid-cell {
            background-color: #ffe2e2;
            color: #900;
        }
    </style>
    <script type="text/javascript" src="${rootPath}resource/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/fileupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/common/functions.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/common/plan.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/editer/task.js"></script>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
    <div class="breadcrumbs">
        <a href="javascript:void(0);">填写基础信息</a>
        <a href="javascript:void(0);">编辑组件内容</a>
        <a href="javascript:void(0);"> <span>编辑预览图和详情</span></a>
        <a href="javascript:void(0);">  <span>组件权限分配</span></a>
        <a href="javascript:void(0);"><span>加入导航</span></a>
        <span>完成</span>
    </div>
    <h1 class="pageTitle">编辑任务内容</h1>
    <div id="webEditor">
    </div>
    <div class="form_default" style="margin-top: 20px;">
        <div class="bottombtn">
            <p>
                <button id="btnOk" type="button">提交</button>
            </p>
        </div>
    </div>
</div>
<div class="extfooter">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
<jsp:include page="../common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>
</body>
</html>