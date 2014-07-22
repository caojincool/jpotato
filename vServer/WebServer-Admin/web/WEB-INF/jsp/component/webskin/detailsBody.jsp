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
<div class="detailscontent">
        <h1 class="pageTitle">网页组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <div class="one_fourth">
                    <strong>
                        基本参数:<br>
                        父级组件:<br>
                        输出类型: <br>
                        是否缓存: <br>
                        缓存时间: <br>
                        是否页面: <br>
                        开始脚本:
                    </strong>
                </div>
                <!-- one_third -->

                <div class="three_fourth last">
                    ${resource.strParams}<br>
                    <a href="http://192.168.2.72:8082/${resource.parentPid}" target="_blank">${resource.parentPid}</a>
                    <br>
                    ${resource.contextType} <br>
                    <img name="img" src="resource/images/icons/${resource.cache?'yes':'no'}.png"/>

                    <br>
                    ${resource.cacheTime} （分钟）<br>
                    <img name="img" src="resource/images/icons/${resource.page?'yes':'no'}.png"/>
                    <br>
                    <textarea rows="5" cols="50" name="context" id="initScript">${resource.initScript}</textarea>
                    <script>
                        var initScript = CodeMirror.fromTextArea(document.getElementById("initScript"), {
                            lineNumbers: true,
                            matchBrackets: true,
                            height: 100,
                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                        });
                        initScript.setSize(null, 100);
                    </script>

                </div>
                <!-- three_fourth last -->
                <br clear="all"><br>

                <div class="one_fourth">
                    <strong>
                        结束脚本:
                    </strong>
                </div>
                <!-- one_third -->

                <div class="three_fourth last">

                    <textarea rows="5" cols="50" name="context" id="endScript">${resource.endScript}</textarea>
                    <script>
                        var endScript = CodeMirror.fromTextArea(document.getElementById("endScript"), {
                            lineNumbers: true,
                            matchBrackets: true,
                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                        });
                        endScript.setSize(null, 100);
                    </script>
                </div>
                <!-- three_fourth last -->
                <br clear="all"><br>

                <div class="one_fourth">
                    <strong>
                        请求脚本:
                    </strong>
                </div>
                <!-- one_third -->
                <div class="three_fourth last">
                    <textarea rows="5" cols="50" name="context" id="formScript">${resource.formScript}</textarea>
                    <script>
                        var formScript = CodeMirror.fromTextArea(document.getElementById("formScript"), {
                            lineNumbers: true,
                            matchBrackets: true,
                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                        });
                        formScript.setSize(null, 100);
                    </script>
                </div>
                <!-- three_fourth last -->
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
                                <td><a href="component/webskin/${resource.pid}/${file.name}"
                                       target="_blank">${file.name}</a></td>
                                <td align="right">${file.type}</td>
                                <td align="right">${file.size}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <br clear="all"><br>
                <c:if test="${ resource.startParam.size()>0}">
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
                        <c:forEach items="${ resource.startParam}" varStatus="i" var="item">
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

                <!-- two_third last -->
            </div>
            <!-- invoice_inner -->
        </div>
        <!-- invoice three_fourth last -->
        <div class="bottombtn">
        <span>
            <a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a>
        </span>
        </div>
    <br clear="all">
</div>