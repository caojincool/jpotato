<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-6
  Time: 下午2:47
  导航页面显示
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../../_header.jsp"/>
    <script type="text/javascript">
        var categorys = ${categorys};
        var lids='${lid}';
        var rootFid='${rootFid}';
    </script>
    <link type="text/css" rel="stylesheet" href="${rootPath}ext/ux/css/CheckHeader.css"/>
    <script type=text/javascript src="${rootPath}resource/js/lkg/VirtualDirectory.js"></script>

</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div class="detailscontent">



        <div class="breadcrumbs">
            <a href="package/main/view">组件包管理</a>
            <span class="dh">文件夹管理</span>
        </div>
        <div style=" margin-top: 20px;">
            <div id="nav">
            </div>
            <div class="form_default">
                <div class="bottombtn">
                    <p>
                        <button id="btnCancel">完成</button>
                    </p>
                </div>
            </div>
        </div>

    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
<script type="text/javascript">
    Ext.fly('btnCancel').on('click',function(){
       window.location.href = '/package/main/view';
    })
</script>
</body>
</html>