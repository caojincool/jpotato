<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">
        <h1 class="pageTitle">资源组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <br clear="all"><br>
                <div class="one_fourth">
                    <strong>
                        文件名:<br>
                        文件大小：
                    </strong>
                </div><!-- one_third -->
                <div class="three_fourth last">
                   <a href="component/resource/${resource.pid}/down"> ${resource.fileName}</a><br>
                    ${resource.fileSize}(字节)
                </div><!-- three_fourth last -->
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a></span>
        </div>
    <br clear="all">
</div>