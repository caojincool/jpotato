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
<form id="query" name="frmQuery" action="" method="post">
<div class="maincontent">
    <div class="left">
        <div class="form_default">
            <fieldset>
                <legend>查询条件</legend>
                   <table width="100%">
                       <jsp:include page="common/queryCondition.jsp"/>
                       <tr>
                           <td class="firstColom">组件类型:</td>
                           <td>
                               <select id="category" name="category" class="sf" style="height:28px;width: 140px;" >
                                   <option value="" selected="selected">全部</option>
                                   <c:forEach var="cate" items="${categorys2}" >
                                       <option value="${cate.category}"  ${cate.category==query.category?'selected=\"selected\"':''}>${cate.name}</option>
                                   </c:forEach>
                               </select>
                           </td>
                       </tr>
                   </table>
            </fieldset>
        </div>
        <jsp:include page="common/displayModule.jsp"/>
        <jsp:include page="common/componentPagebar.jsp"/>
        <br>
    </div><!--gallery-->
    <br clear="all">
</div><!--left-->
<br clear="all">
</form>
