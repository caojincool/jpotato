<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-23
  Time: 上午11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改填报脚本</title>
    <jsp:include page="../../common/_xmlcodeheader.jsp"/>
    <script type="text/javascript">
        //组件基本信息
        var lr =${lemsunResource};
    </script>
    <script type="text/javascript" src="${rootPath}resource/js/component/add/reporterScript.js"></script>
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>


<div class="detailscontent">

    <div class="breadcrumbs" style="margin-bottom: 15px;">
        <a href="#">编辑填报脚本内容</a>
    </div>

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

<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</div>
<script type="text/javascript">
    Ext.fly('btnOk').on('click', function () {
        executeSave( function (){
            location.href = rootPath + 'component/main/operatingResults?success=true';
        });

    });

</script>
</body>
</html>