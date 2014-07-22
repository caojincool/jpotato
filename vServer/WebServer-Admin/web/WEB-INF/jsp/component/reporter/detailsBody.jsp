<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">
        <h1 class="pageTitle">填报组件详情</h1>
        <div class="invoice ">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
               <c:if test="${ resource.startParams.size()>0}">
                <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                    <thead>
                    <tr>
                        <td width="10%">序号</td>
                        <td width="30%">参数名称</td>
                        <td width="30%" align="right">类型</td>
                        <td width="30%" align="right">参数值</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                    <c:forEach items="${ webskinResource.startParam}" varStatus="i" var="item">
                        <tr>
                            <td align="left"><strong>${ i.index + 1}</strong></td>
                            <td>${item.name}</td>
                            <td align="right">${item.cate}</td>
                            <td align="right">${item.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
               </c:if>
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a></span>
        </div>
    <br clear="all">
</div>