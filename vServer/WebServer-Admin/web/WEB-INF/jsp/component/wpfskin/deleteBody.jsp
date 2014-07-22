<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">


        <h1 class="pageTitle">程序组件删除确认</h1>

        <div class="notification msgalert">
            <p>删除改组件可能引起其他使用改组件组件或应用无法正常使用！</p>
        </div>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <div class="one_fourth">
                    <strong>
                        基本参数:<br>
                        父级组件:<br>
                        是否显示工具栏: <br>
                        同步窗口大小: <br>
                        打开方式: <br>
                        显示位置:
                    </strong>
                </div><!-- one_third -->

                <div class="three_fourth last">
                    ${resource.strParams}<br>
                    <a href="ilemsun://command/resource=${resource.parentPid}&token=&sfdsd=slskdj&action=open" target="_blank">${resource.parentPid}</a> <br>
                     <img  name="img" src="resource/images/icons/${resource.showToolbar?'yes':'no'}.png" />
                     <br>
                     <img  name="img" src="resource/images/icons/${resource.synchronismWindowSize?'yes':'no'}.png" />
                    <br>
                    <c:choose>
                        <c:when test="${ resource.openMode==1}">
                            弹出窗口
                        </c:when>
                        <c:when test="${ resource.openMode==2}">
                            合并窗口
                        </c:when>
                        <c:when test="${ resource.openMode==3}">
                            模式窗口
                        </c:when>
                        <c:otherwise>
                            异常参数
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <c:choose>
                        <c:when test="${ resource.showLocation==1}">
                            窗口居中
                        </c:when>
                        <c:when test="${ resource.showLocation==2}">
                            屏幕居中
                        </c:when>

                        <c:otherwise>
                            随意
                        </c:otherwise>
                    </c:choose>
                    <br>

                </div><!-- three_fourth last -->


                <br clear="all"><br>
                <c:if test="${ resourceAttaches.size()>0}">
                    <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                        <thead>
                        <tr>
                            <td width="10%">序号</td>
                            <td width="30%">附件名称</td>
                            <td width="30%" align="right">类型</td>
                            <td width="30%" align="right">大小（字节）</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <c:forEach items="${ resourceAttaches}" varStatus="i" var="file">
                            <tr>
                                <td align="left"><strong>${ i.index + 1}</strong></td>
                                <td><a href="component/wpfskin/${resource.pid}/${file.name}" target="_blank">${file.name}</a></td>
                                <td align="right">${file.type}</td>
                                <td align="right">${file.size}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <br clear="all"><br>
                <c:if test="${ resource.startParams.size()>0}">
                    <table width="100%" cellspacing="0" cellpadding="0" class="invoicetable">
                        <thead>
                        <tr>
                            <td width="10%">序号</td>
                            <td width="30%">参数名称</td>
                            <td width="30%" align="right">类型</td>
                            <td width="30%" align="right">参数值</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <c:forEach items="${ webskinResource.startParam}" varStatus="i" var="item">
                            <tr>
                                <td align="left"><strong>${ i.index + 1}</strong></td>
                                <td>${item.name}</td>
                                <td align="right">${item.cate}</td>
                                <td align="right">${item.value}</td>
                            </tr>


                        </c:forEach>

                        </tbody>
                    </table>
                </c:if>
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>

            </div><!-- invoice_inner -->

        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a>　&nbsp;<a class="iconlink" href="javascript:del();">删除</a></span>
        </div>
    <br clear="all">
</div>