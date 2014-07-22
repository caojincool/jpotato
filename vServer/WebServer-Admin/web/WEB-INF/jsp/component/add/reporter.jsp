<%--
  填报组件基础信息界面
  User: Administrator
  Date: 13-1-23
  Time: 上午11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建填报组件内容</title>
    <jsp:include page="../../common/_xmlcodeheader.jsp"/>
    <style type="text/css">
        #context {
            width: 1200px;
            height: 900px;
            margin: 10px auto;
        }
    </style>
    <script type="text/javascript">
        //组件基本信息
        var lr =${lemsunResource};

        if (lr == null || lr == undefined) {
            window.location.href = '${rootPath}component/main/create'
        }
        var sessionid='<%=request.getSession().getId()%>';
        var uploadUrl="${rootPath}component/wpfskin/"+lr.pid+"/attaches/upload;jsessionid="+sessionid;
    </script>
    <script type="text/javascript" src="${rootPath}resource/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/fileupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/common/functions.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/add/reporter.js"></script>
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
    <h1 class="pageTitle">编辑填报组件内容</h1>
    <div id="reporter">
    </div>
    <div  style="margin-top: 20px;">
        <div class="bottombtn">
            <button class="button button_red" type="button" onclick="del();" >删除</button>
            <button id="next" class="button button_blue" type="submit"  >下一步</button>
            <button id="finish" class="button button_black"   type="submit" >完成</button>
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
<jsp:include page="../common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>
</body>
</html>