<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lemsun.web.manager.controller.util.WebConstant" %>
<div class="detailscontent">
        <h1 class="pageTitle">5代表组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <br clear="all"><br>

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
                                 <a href="component/db/${resource.dbConfig.pid}/details" target="_blank"> ${resource.dbConfig.name}</a><br>
                                     ${resource.dbtable}<br>
                                     ${resource.code}<br>
                                     <c:set var="tableCategory" value="${resource.cate}" scope="request"></c:set>
                                     <%=WebConstant.tableCategoryMap.get((Integer) request.getAttribute("tableCategory"))%>
                                 <br>
                                 <fmt:formatDate value='${resource.createTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                     ${resource.enable}<br>
                                 <fmt:formatDate value='${resource.enableTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                 <fmt:formatDate value='${resource.dateTime}' pattern='yyyy年MM月dd日 HH:mm:ss'/>
                                 <br>
                                     ${resource.reamrk}
                             </div><!-- three_fourth last -->
                             <br clear="all"><br>
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
                                     <c:forEach items="${resource.columns}" varStatus="i" var="column">
                                         <tr>
                                             <td align="left"><strong>${ i.index + 1}</strong></td>
                                             <td align="left">${column.col}</td>
                                             <td align="left">${column.name}</td>
                                             <td align="left">
                                                 <c:set var="cate" value="${column.category}" scope="request"></c:set>
                                                 <%= WebConstant.columnCategoryMap.get((Integer)request.getAttribute("cate")) %>
                                             </td>
                                             <td align="left">${column.unique}</td>
                                             <td align="left">${column.readOnly}</td>
                                             <td align="left">${column.visible}</td>
                                             <td align="left">${column.defaultValue}</td>
                                         </tr>
                                     </c:forEach>
                                     </tbody>
                                 </table>
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view?page=1&limit=20">返回</a></span>
        </div>

   <br clear="all">
</div>