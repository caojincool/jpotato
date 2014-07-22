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
                    <jsp:include page="../common/queryCondition.jsp"/>
                </table>

            </fieldset>
        </div>

        <div class="dataTables_wrapper" id="pTable_wrapper">
            <jsp:include page="../common/toolbar.jsp"/>
            <div class="dataTables_filter" id="example_filter">
                <label>
                    <a class="iconlink" href="component/main/create?type=DB" target="_blank">
                        <img src="resource/images/icons/small/white/plus.png" class="mgright5" alt="">
                        <span>新增</span>
                    </a>
                </label>
            </div>
            <table cellspacing="0" cellpadding="0" border="0" class="dyntable" id="pTable">
                <thead>
                <tr>
                    <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 8%;">预览图片</th>
                    <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 22%;">组件基本信息</th>
                    <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 20%;">网页信息</th>
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
                    <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 22%;">组件基本信息</th>
                    <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 20%;">网页信息</th>
                    <th colspan="1" rowspan="1" class="head1 sorting_disabled center" style="width: 30%;">说明</th>
                    <th colspan="1" rowspan="1" class="head0 sorting_disabled center" style="width: 20%;">操作</th>
                </tr>
                </tfoot>
                <tbody>
                <c:if test="${data.totalElements > 0}">
                <c:forEach items="${ data.getContent()}" varStatus="i" var="item">
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
                        <td  style="vertical-align: text-top;">
                            <div class="tabledivWrapper">

                                <div class="tabledivText">数据库地址：</div>
                                <div class="tabledivValue"> ${item.server}</div>
                                <br clear="all">
                                <div class="tabledivText">数据库名称：</div>
                                <div class="tabledivValue">  ${item.dbName}</div>
                            </div>
                            <br clear="all">
                        </td>

                        <td width="700px" style="vertical-align: text-top;">
                            <label style="color:#000000;font-size:10px;">${item.remark}</label> <br/>
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
                <c:if test="${data.totalElements <= 0}">
                    <jsp:include page="../common/noDataMessage.jsp"/>
                </c:if>
                </tbody>
            </table>
        </div>
        <jsp:include page="../common/componentPagebar.jsp"/>
        <br>
    </div>
    <!--gallery-->
    <br clear="all">
</div>
<!--left-->
<br clear="all">

</div>
</form>