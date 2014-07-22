<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-12-19
  Time: 上午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dataTables_wrapper" id="pTable_wrapper">
    <jsp:include page="../common/toolbar.jsp"/>
    <div class="dataTables_filter" id="example_filter"><label>
        <a class="iconlink" href="component/main/create" target="_blank"><img src="resource/images/icons/small/white/plus.png" class="mgright5" alt=""><span>新增</span></a></label></div>
    <table cellspacing="0" cellpadding="0" border="0" class="dyntable" id="pTable">
        <thead>
        <tr>
            <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 8%;">预览图片</th>
            <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 42%;">组件基本信息</th>
            <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 30%;">说明</th>
            <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 20%;">操作</th>
        </tr>
        </thead>
        <colgroup>
            <col class="con0">
            <col class="con1">
            <col class="con0">
        </colgroup>
        <tfoot>
        <tr>
            <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 8%;">预览图片</th>
            <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 42%;">组件基本信息</th>
            <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 30%;">说明</th>
            <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 20%;">操作</th>
        </tr>
        </tfoot>
        <tbody>
        <c:if test="${data.getTotalElements() > 0}">
            <c:forEach items="${data.getContent()}" varStatus="i" var="item">
                <tr class="odd">
                    <jsp:include page="../common/imageMin.jsp" >
                        <jsp:param name="pid" value="${item.pid}"></jsp:param>
                    </jsp:include>
                    <td style="vertical-align: text-top; ">
                        <div class="tabledivWrapper">
                            <jsp:include page="../common/td.jsp" >
                                <jsp:param name="pid" value="${item.pid}"></jsp:param>
                                <jsp:param name="showName" value="${item.showName}"></jsp:param>
                                <jsp:param name="name" value="${item.name}"></jsp:param>
                                <jsp:param name="category" value="${item.category}"></jsp:param>
                                <jsp:param name="state" value="${item.state}"></jsp:param>
                            </jsp:include>

                            <div class="tabledivText">创建日期：</div>
                            <div class="tabledivValue">  <fmt:formatDate value='${item.updateTime}' pattern='yyyy年MM月dd日'/>
                            </div>
                        </div>

                        <br clear="all">
                    </td>
                    <td style="vertical-align: text-top;" >
                        ${item.remark}
                    </td>
                    <td style="text-align: right">
                        <jsp:include page="../common/operation.jsp" >
                            <jsp:param name="pid" value="${item.pid}"></jsp:param>
                            <jsp:param name="category" value="${item.category}"></jsp:param>
                        </jsp:include>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${data.getTotalElements()<= 0}">
            <jsp:include page="../common/noDataMessage.jsp"/>
        </c:if>
        </tbody>
    </table>
</div>
