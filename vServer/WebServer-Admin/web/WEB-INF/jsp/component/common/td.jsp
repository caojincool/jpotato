<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


        <div class="tabledivText">组件名：</div>
        <div class="tabledivValue">  <a href="component/${fn:toLowerCase(param.category)}/${param.pid}/details?type=main" style=" text-decoration: none;font-size:11px; font-weight:bold;" target="_blank">
            <img style="vertical-align: middle;margin-right: 5px;" name="img" src="component/componenttype/icon/${param.category}" />
            ${param.name}
        </a></div>
        <div class="tabledivText">组件编号：</div>
        <div class="tabledivValue"> ${param.pid}</div>
        <br clear="all">
        <div class="tabledivText">创建人：</div>
        <div class="tabledivValue"> ${param.showName}</div>
        <br clear="all">
        <div class="tabledivText">状态：</div>
        <div class="tabledivValue">
            <c:choose>
                <c:when test="${param.state==1}">
                    完成
                </c:when>
                <c:when test="${param.state==2}">
                    封存
                </c:when>
                <c:when test="${param.state==4}">
                    禁用
                </c:when>
                <c:when test="${param.state==8}">
                    未完成
                </c:when>
                <c:otherwise>
                    非法状态
                </c:otherwise>
            </c:choose>
        </div>
        <br clear="all">

