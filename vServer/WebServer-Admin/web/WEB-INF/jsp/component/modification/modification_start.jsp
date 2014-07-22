<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改组件</title>
    <jsp:include page="../../common/_header.jsp" />
    <style type="text/css">
        #context {
            width: 900px;
            height: 500px;
            margin: 10px auto;
        }
    </style>
    <script type="text/javascript">
      function checkSubmit(){
        //验证
        var cpname= document.getElementById("name").value.trim();
       if(cpname=='')
        {
         alert("请输入类型名称！");
         return false;
         }
     return true;
 }
    </script>
</head>
<body>

<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div>
    <div id="context" style="height: 600px;">
        <div class="breadcrumbs">
            <a href="dashboard.html">修改组件</a>
            <span>完成</span>
        </div>
        <div style="width: 870px; margin: 15px;">

            <h1 class="pageTitle">修改组件信息</h1>

            <form method="post" action="" onsubmit="return checkSubmit();" enctype="multipart/form-data">

                <div class="form_default">
                    <fieldset>
                        <legend>修改组件</legend>
                        <p>
                            <label for="name">名称</label>
                            <input id="name" type="text" class="sf" name="name" value="${category.name}">
                        </p>
                        <p>
                            <label for="remark">说明文字</label>
                            <textarea id="remark" rows="" cols="" class="mf" name="remark">${category.remark}</textarea>
                        </p>
                        <p>
                            <label>图片</label>
                            <input type="file" name="file">
                              <input value="${category.pid}" type="hidden" name="parentpid">
                        </p>
                        <p>
                            <button type="submit">完成</button>
                        </p>

                    </fieldset>
                </div>
            </form>
        </div>
    </div>
    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>