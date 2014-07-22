<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-9-3
  Time: 下午1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">
        <h1 class="pageTitle">计划任务组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>

                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>

                <!-- two_third last -->
            </div>
            <!-- invoice_inner -->
        </div>
        <!-- invoice three_fourth last -->
        <div class="bottombtn">
        <span>
            <a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a>
        </span>
        </div>
    <br clear="all">
</div>