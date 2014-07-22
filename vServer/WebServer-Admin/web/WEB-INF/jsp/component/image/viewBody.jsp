<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <ul class="submenu">
        <li  ${query.view==1?'class="current"':''}><a href="javascript:switchView(1);">列表</a></li>
        <li ${query.view==2?'class="current"':''}><a href="javascript: switchView(2);">预览</a></li>
    </ul>
    <div class="dataTables_wrapper" id="pTable_wrapper">
        <jsp:include page="../common/toolbar.jsp"/>

        <div class="dataTables_filter" id="example_filter">
            <label>
                <a class="iconlink" href="component/main/create?type=IMAGE" target="_blank">
                    <img src="resource/images/icons/small/white/plus.png" class="mgright5" alt="">
                    <span>新增</span>
                </a>
            </label>
        </div>
        <table cellspacing="0" cellpadding="0" border="0" class="dyntable" id="list1" >
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
        <div class="gallerys" id="list2" style="display:none;">
            <div class="thumbview" >
                <ul>
                    <c:forEach items="${ data.getContent()}" varStatus="i" var="item">
                        <li>
                            <div class="thumb">
                                <img alt="" src="component/image/content/get?pid=${item.pid}-250X150">
                                <div class="info" style="display: none;">
                                    <p>
                                        <label>组件名:</label>
                                        <span>${item.name}</span>
                                    </p>
                                    <p>
                                        <label>创建人:</label>
                                        <span>${item.showName}</span>
                                    </p>
                                    <p>
                                        <label>文件名:</label>
                                        <span>${item.imageName}</span>
                                    </p>
                                    <p>
                                        <label>文件大小:</label>
                                        <span>${item.imageSize}</span>
                                    </p>
                                    <p>
                                        <label>状态:</label>
                                        <span>
                                            <c:choose>
                                                <c:when test="${item.state==1}">
                                                    发布
                                                </c:when>
                                                <c:when test="${item.state==2}">
                                                    预览
                                                </c:when>
                                                <c:when test="${item.state==3}">
                                                    封存
                                                </c:when>
                                                <c:when test="${item.state==4}">
                                                    禁用
                                                </c:when>
                                                <c:otherwise>
                                                    非法状态
                                                </c:otherwise>
                                            </c:choose>
                                       </span>
                                    </p>
                                    <p>
                                        <label>上传日期:</label>
                                        <span>${item.updateTime}</span>
                                    </p>
                                    <p class="menus">
                                        <a class="views" href="component/image/content/get?pid=${item.pid}" target="_blank"></a>
                                        <a class="edit" href="component/image/${item.pid}/edit" target="_blank"s></a>
                                        <a title="编辑预览图及详情" class="setting"
                                           href="component/main/${item.pid}/imageAndDetails/update" target="_blank"></a>
                                        <a class="setting" href="component/main/${item.pid}/permission/edit?type=image" target="_blank"></a>
                                        <a class="deletes" href="component/image/${item.pid}/delete" target="_blank"></a>

                                    </p>
                                </div><!--info-->
                            </div><!--thumb-->
                        </li>
                    </c:forEach>


                </ul>
            </div><!--gridview-->
            <br clear="all">

        </div><!--gallery-->
</div><!--gallery-->



    <jsp:include page="../common/componentPagebar.jsp"/>

    <br clear="all">

</div><!--left-->

<br clear="all">

</div>
</form>