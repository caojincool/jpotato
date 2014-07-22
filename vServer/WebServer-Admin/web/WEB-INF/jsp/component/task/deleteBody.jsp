<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-9-11
  Time: 下午2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">
    <div>
        <h1 class="pageTitle">计划任务组件删除确认</h1>
        <div class="notification msgalert">
            <p>删除改组件会把它子组件一起删除同时公式和其他使用它的地方将无法正常使用！删除操作一定要慎重！！</p>
        </div>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <jsp:include page="../common/useDetails.jsp"/>
                <!-- two_third last -->
            </div>
            <!-- invoice_inner -->
        </div>
        <!-- invoice three_fourth last -->
        <div class="bottombtn">
                    <span><a class="iconlink"
                             href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a>　&nbsp;<a
                            class="iconlink" href="javascript:del();">删除</a></span>
        </div>
    </div>
    <!--left-->
    <br clear="all">
</div>