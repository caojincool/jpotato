<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-23
  Time: 上午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改图片资源</title>
    <jsp:include page="../../common/_header.jsp" />
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
        <div >
            <div class="breadcrumbs" style="margin-bottom: 15px;">
                <a href="#">编辑图片资源</a>
            </div>
            <h1 class="pageTitle">修改图片资源</h1>
            <form method="post" action="" enctype="multipart/form-data">
                <div class="form_default">
                    <fieldset >
                        <legend style="font-size: 16px;">上传图片</legend>
                        <p>
                            <label for="name">名称</label>
                            <input type="hidden" name="imageName" id="imageName" value="${imageresource.imageName}">
                            <input type="hidden" name="imageSize" id="imageSize" value="${imageresource.imageSize}">
                            <input type="hidden" name="pid" id="pid" value="${imageresource.pid}">
                            <input type="text" class="sf" name="name" id="name" value="${imageresource.name}"/>
                        </p>
                        <p>
                            <label for="remark">说明</label>
                            <textarea rows="3" cols="50"  class="mf"  name="remark" id="remark">${imageresource.remark}</textarea>
                        </p>
                        <p>
                            <label for="param">参数设置</label>
                            <textarea rows="" cols="" class="mf" id="param" name="strParams">${imageresource.strParams}</textarea>
                        </p>
                        <p>
                            <label>已上传图片</label>
                            ${imageresource.imageName} &nbsp; <a target="_blank" href="component/image/${imageresource.pid}/getview">预览</a>
                        </p>
                        <p>
                            <label for="imagefile">选择图片</label>
                            <input type="file" class="sf" name="imagefile" style=" width: 380px;" id="imagefile"/>
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