<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>退出页面</title>
    <link href="${rootPath}resource/style/base.css" media="screen" rel="stylesheet" />
    <link href="${rootPath}resource/style/style.css" media="screen" rel="stylesheet" />
    <link href="${rootPath}resource/style/login.css" media="screen" rel="stylesheet" />
    <jsp:include page="_header.jsp" />
</head>
<body>

<div align="center" style="margin-top: 200px;font-size: 24px;margin:200px 0px 350px 0px">
    <input type="text" id="timerTxt" name="timerTxt" readonly="readonly" style="border: 0;font-size: 25px;width: 500px;background: transparent">
</div>
 <script type="text/javascript">
        var num=5;
        count();
        function count(){
            var timerTxt=document.getElementById("timerTxt");
            timerTxt.value=num+"秒后将跳转到登录页面";
            num--;
            setTimeout("count()",1000);
            if(num<=0)
                setTimeout("javascript:location.href='login'", 1000);
        }
    </script>

<br clear="all" />
<br/>
<div class="footer">
    <jsp:include page="common/_footer.jsp" />
</div>
</body>
</html>