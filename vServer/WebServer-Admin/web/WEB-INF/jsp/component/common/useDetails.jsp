<%--
  组件被使用详情
  User: 刘晓宝
  Date: 13-9-12
  Time: 下午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="resource/js/component/resourceTree.js" ></script>
    <div class="form_default" id="childResource" ${childsIsEmpty?'style="display: none"':''} >
        <fieldset>
            <legend> <strong>子组件</strong></legend>

                <div style="width: 100%;height: 260px;" id="resourceTree"></div>
        </fieldset>
    </div>
 <br clear="all"><br>
<c:if test="${ bps.size()>0}">
    <div class="form_default">
        <fieldset>
            <legend> <strong>已使用该组件包列表</strong></legend>
            <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                <thead>
                <tr>
                    <td width="10%">序号</td>
                    <td width="30%">包名称</td>
                    <td width="60%" align="right">说明</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="3">&nbsp;</td>
                </tr>
                <c:forEach items="${ bps}" varStatus="i" var="file">
                    <tr>
                        <td align="left"><strong>${ i.index + 1}</strong></td>
                        <td><a href="package/view?lid=${file.lid}" target="_blank">${file.name}</a></td>
                        <td align="right">${file.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </fieldset>
    </div>
    <br clear="all"><br>
</c:if>

<c:if test="${ sbrs.size()>0}">
    <div class="form_default">
        <fieldset>
            <legend><strong>已使用该组件账套列表</strong></legend>
            <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                <thead>
                <tr>
                    <td width="10%">序号</td>
                    <td width="30%">账套名称</td>
                    <td width="60%" align="right">说明</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="3">&nbsp;</td>
                </tr>
                <c:forEach items="${sbrs}" varStatus="i" var="file">
                    <tr>
                        <td align="left"><strong>${ i.index + 1}</strong></td>
                        <td><a href="/setofbooks/index">${file.setOfBooks.name}</a></td>
                        <td align="right">${file.setOfBooks.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </div>
    <br clear="all"/>
</c:if>

<c:if test="${ ncs.size()>0}">
    <div class="form_default">
        <fieldset>
            <legend><strong>已使用该组件的导航</strong></legend>
            <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                <thead>
                <tr>
                    <td width="10%">序号</td>
                    <td width="30%">导航名称</td>
                    <td width="60%" align="right">说明</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="3">&nbsp;</td>
                </tr>
                <c:forEach items="${ncs}" varStatus="i" var="nc">
                    <tr>
                        <td align="left"><strong>${ i.index + 1}</strong></td>
                        <td><a href="/component/navigation/view">${nc.navigation.name}</a></td>
                        <td align="right">${nc.navigation.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </div>
    <br>
</c:if>

