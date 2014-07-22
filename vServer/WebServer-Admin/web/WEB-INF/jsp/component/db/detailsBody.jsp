<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">

        <h1 class="pageTitle">数据库配置详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <br clear="all"><br>
                <div class="one_fourth">
                    <strong>
                        登陆用户名称:<br>
                        登陆密码:<br>
                        最大启用连接: <br>
                        最大等待连接时间: <br>
                        最大等待连接数: <br>
                        是否是默认数据库:<br>
                        数据库类型:<br>
                        获取服务器地址:<br>
                        获取数据库名称:
                    </strong>
                </div><!-- one_third -->

                <div class="three_fourth last">
                    ${resource.username} <br>
                    ${resource.password} <br>
                    ${resource.maxActive} <br>
                    ${resource.maxIdea} <br>
                    ${resource.maxWait} <br>
                        <c:choose>
                            <c:when test="${ resource.defaultDb}">
                                是
                            </c:when>
                            <c:otherwise>
                                否
                            </c:otherwise>
                        </c:choose>
                        <br>
                    ${resource.dbCategory.name} <br>
                    ${resource.server} <br>
                    ${resource.dbName}
                </div><!-- three_fourth last -->
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink"  href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a></span>
        </div>

    <br clear="all">
</div>