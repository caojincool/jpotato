<%--
  Created by IntelliJ IDEA.
  User: 刘晓宝
  Date: 12-10-19
  Time: 下午6:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <jsp:include page="../_header.jsp" />
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>

<div class="detailscontent">
<h1 class="pageTitle">任务统计详情</h1>
<div class="invoice ">
    <div class="invoice_inner">

        <div class="one_fourth">
            <strong>
                任务名：<br>
                执行次数:<br>
                成功执行次数:<br>
                失败执行次数: <br>
                最后一次执行时间: <br>
                下次执行时间:
            </strong>
        </div><!-- one_third -->
        <div class="three_fourth last">
            统计常住人口<br>
            10次<br>
            9次<br>
            1次<br>
            2012-02-02 19:08:09<br>
            2013-02-02 19:08:09<br>

        </div><!-- three_fourth last -->


        <br clear="all"><br>


    </div><!-- invoice_inner -->
</div><!-- invoice three_fourth last -->

<br>
</div>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
<jsp:include page="../component/common/dialog.jsp" />
</body>
</html>

