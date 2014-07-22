<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-10-21
  Time: 下午4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>操作结果提示</title>
    <jsp:include page="../common/_header.jsp" />
    <script type="text/javascript">
        var num=5;
        count();
        function count(){
            var timerTxt=jQuery("#timerTxt");
            timerTxt.html(num+"秒后将关闭当前页面！");
            num--;
            setTimeout("count()",1000);
            if(num<=0){
                closeWin();
            }
        }
    </script>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>

<div class="detailscontent">
    <div  style="height: 600px;">

        <div class="marginBreadcrumbs">



            <form method="post" action="">

                <div class="form_default">

                        <c:if test="${success}">
                            <div class="notification msgsuccess">
                                <a class="close"></a>
                                <p>操作成功！  <span id="timerTxt"></span></p>

                            </div>
                        </c:if>
                        <c:if test="${!success}">
                            <div class="notification msgerror">
                                <a class="close"></a>
                                <p> <c:choose>
                                     <c:when test="${message!=null}">
                                         ${message}
                                     </c:when>
                                    <c:otherwise>
                                        操作失败！
                                    </c:otherwise>
                                </c:choose>
                                    <span id="timerTxt"></span></p>

                            </div>
                        </c:if>



                </div>


            </form>
        </div>

    </div>
    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp" />
</div>
</body>
</html></html>