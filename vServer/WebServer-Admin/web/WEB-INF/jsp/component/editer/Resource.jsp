<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-23
  Time: 上午11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改文件资源</title>
    <jsp:include page="../../common/_header.jsp" />
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

    <div class="detailscontent">
        <div>
            <div class="breadcrumbs" style="margin-bottom: 15px;">
                <a href="#">编辑资源组件</a>
            </div>
            <h1 class="pageTitle">编辑资源组件</h1>
            <form method="post" action="" enctype="multipart/form-data">
                <div class="form_default">
                    <fieldset style="width :98.5%; margin :5px 5px 5px 0; padding: 5px; border :1px solid #666; background :#F7F7F7;">
                        <legend style="font-size: 16px;">上传文件</legend>
                        <p>
                            <label for="name">名称</label>
                            <input type="hidden" name="fileName" id="fileName" value="${fileresource.fileName}">
                            <input type="hidden" name="fileSize" id="fileSize" value="${fileresource.fileSize}">
                            <input type="hidden" name="pid" id="pid" value="${fileresource.pid}">
                            <input type="text" class="sf" name="name" id="name" value="${fileresource.name}"/>
                        </p>
                        <p>
                            <label for="remark">说明</label>
                            <textarea rows="3" cols="50" class="mf"  name="remark" id="remark">${fileresource.remark}</textarea>
                        </p>
                        <p>
                            <label for="param">参数设置</label>
                            <textarea rows="" cols="" class="mf" id="param" name="strParams">${fileresource.strParams}</textarea>
                        </p>
                        <p>
                            <label>已上传的文件</label>
                            ${fileresource.fileName} &nbsp; <a href="component/resource/${fileresource.pid}/down">下载</a>
                        </p>
                        <p>
                            <label for="resourcefile">选择文件</label>

                            <input type="file" class="sf" name="resourcefile" style=" width: 380px;" id="resourcefile" value="${fileresource.fileName}"/>
                            <span style="color: red;">${error}</span>
                        </p>

                    </fieldset>
                    <div class="bottombtn">

                        <button type="submit">修改</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>