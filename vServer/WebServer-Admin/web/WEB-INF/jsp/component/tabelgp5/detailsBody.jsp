<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lemsun.web.manager.controller.util.WebConstant" %>
<div class="detailscontent">
        <h1 class="pageTitle">表组组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
                <br clear="all"><br>
                <c:if test="${ table5Resources.size()>0}">
                 <c:forEach items="${ table5Resources}" varStatus="tabIndex" var="table">
                     <div class="form_default">
                         <fieldset>
                             <legend><strong> 第 ${tabIndex.index+1} 张表</strong></legend>
                             <div class="one_fourth">
                                 <strong>
                                     使用关系数据库:<br>
                                     表物理名称:<br>
                                     数据编码:<br>
                                     表类型:<br>
                                     创建日期:<br>
                                     是否启用: <br>
                                     启用日期: <br>
                                     设置表格时间:<br>
                                     表说明：
                                 </strong>
                             </div><!-- one_third -->
                             <div class="three_fourth last">
                                 <a href="component/db/${table.dbConfig.pid}/details" target="_blank"> ${table.dbConfig.name}</a><br>
                                     ${table.dbtable}<br>
                                     ${table.code}<br>
                                 <c:set var="tableCategory" value="${table.cate}" scope="request"></c:set>
                                 <%=WebConstant.tableCategoryMap.get((Integer) request.getAttribute("tableCategory"))%>
                                 <br>
                                 <fmt:formatDate value='${table.createTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                     ${table.enable}<br>
                                 <fmt:formatDate value='${table.enableTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                 <fmt:formatDate value='${table.dateTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                     ${table.reamrk}
                             </div><!-- three_fourth last -->
                             <br clear="all"><br>
                             <c:if test="${ table.columns.size()>0}">
                                 <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                                     <thead>
                                     <tr>
                                         <td width="10%">序号</td>
                                         <td width="20%">列名称</td>
                                         <td width="10%">别名</td>
                                         <td width="20%" >类型</td>
                                         <td width="10%">是否唯一</td>
                                         <td width="10%" >是否只读</td>
                                         <td width="10%">是否可见</td>
                                         <td width="10%" >默认值</td>
                                     </tr>
                                     </thead>
                                     <tbody>
                                     <tr>
                                         <td colspan="8">&nbsp;</td>
                                     </tr>
                                     <c:forEach items="${table.columns}" varStatus="i" var="column">
                                         <tr>
                                             <td align="left"><strong>${ i.index + 1}</strong></td>
                                             <td align="left">${column.col}</td>
                                             <td align="left">${column.name}</td>
                                             <td align="left">


                                             </td>
                                             <td align="left">${column.unique}</td>
                                             <td align="left">${column.readOnly}</td>
                                             <td align="left">${column.visible}</td>
                                             <td align="left">${column.defaultValue}</td>
                                         </tr>
                                     </c:forEach>
                                     </tbody>
                                 </table>
                             </c:if>
                         </fieldset>
                     </div>

                </c:forEach>
              </c:if>
                <br clear="all"><br>

            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a></span>
        </div>
    <br clear="all">
</div>