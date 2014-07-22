<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-25
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据表组预览</title>
</head>
<body>
<h1>没有安装打开 WPF 表组的控件. 请安装控件</h1>
<h3>ilemsun://command/resource=${resource.pid}&token=${auth.token}&sfdsd=slskdj&action=open</h3>

<div>
    <form id="form" action="" target="_self" method="GET">

    </form>
</div>

<script type="text/javascript">
    var url = 'ilemsun://command/resource=${resource.pid}&token=${auth.token}&sfdsd=slskdj&action=open';
    function doSubmit() {
        var form = document.getElementById('form');
        form.action = url;
        form.submit();
        close();
    }
    doSubmit();
</script>

</body>
</html>