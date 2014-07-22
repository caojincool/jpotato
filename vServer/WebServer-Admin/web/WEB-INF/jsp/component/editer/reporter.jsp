<%--
  Created by IntelliJ IDEA.
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
    <title>编辑程序界面内容</title>
    <jsp:include page="../../common/_xmlcodeheader.jsp"/>
    <script type="text/javascript">
        //组件基本信息
        var lr =${lemsunResource};
        var type='${type}';
        var sessionid='<%=request.getSession().getId()%>';
        var uploadUrl="${rootPath}component/wpfskin/"+lr.pid+"/attaches/upload;jsessionid="+sessionid;
    </script>
    <script type="text/javascript" src="${rootPath}resource/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/fileupload.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/common/functions.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/component/editer/reporter.js"></script>
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>
<div class="detailscontent">
    <div class="breadcrumbs" style="margin-bottom: 15px;">
        <a href="#">编辑程序界面内容</a>
    </div>
    <div style="margin: 15px 0px;">
        <h1 class="pageTitle">编辑程序界面内容</h1>
        <div id="reporter">
        </div>
        <div class="form_default" style="margin-top: 20px;">
            <div class="bottombtn">
                <p>
                    <button id="btnOk" type="button">提交</button>
                </p>
            </div>
        </div>
    </div>
</div>

<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>