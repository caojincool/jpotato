<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-9-14
  Time: 上午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dataTables_wrapper" id="example_wrapper">
    <div class="dataTables_info" id="pTable_info">当前数据为从第 ${(data.number-1)*data.size+1} 到第  ${data.totalPages!=data.number?(data.number)*data.size:data.totalElements} 项数据；总共有 ${data.totalElements} 项记录</div>
    <div class="pagination" align="right"  style="position: absolute; bottom: 7px; right: 8px;">
        <c:choose>
            <c:when test="${ data.number==1}">
                <a class="first disabled" href="javascript:void(0);">‹ 首页</a>
            </c:when>
            <c:otherwise>
                <a class="first"  href="javascript:sub(1)">‹ 首页</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${ data.number-1<=0}">
                <a class="prev disabled"  href="javascript:void(0);">‹ 前一页 </a>
            </c:when>
            <c:otherwise>
                <a class="prev"  href="javascript:sub(${data.number-1})">‹ 前一页</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${ data.number+1 > data.totalPages}">
                <a class="next disabled"  href="javascript:void(0);">下一页  › </a>
            </c:when>
            <c:otherwise>
                <a class="next"  href="javascript:sub(${data.number+1})">下一页  ›</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${ data.totalPages==data.number || data.totalPages==0}">
                <a class="last disabled"  href="javascript:void(0);">末页  ›</a>
            </c:when>
            <c:otherwise>
                <a class="last"  href="javascript:sub(${data.totalPages})">末页  › </a>
            </c:otherwise>
        </c:choose>
    </div><!--pagination-->
</div>