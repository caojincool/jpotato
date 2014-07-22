<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="detailscontent">
        <h1 class="pageTitle">网页脚本组件详情</h1>
        <div class="invoice">
            <div class="invoice_inner">
                <jsp:include page="../common/detailsBase.jsp"/>
                <br clear="all"><br>
                <p><label for="remark"><strong>脚本内容 </strong></label>
                    <textarea rows="5" cols="50" name="context" id="script">${resource.convertToScript()}</textarea>
                    <script>
                        var editor = CodeMirror.fromTextArea(document.getElementById("script"), {
                            lineNumbers: true,
                            matchBrackets: true,
                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                        });
                    </script>
                </p>
                <br clear="all"><br>
                <jsp:include page="../common/useDetails.jsp"/>
            </div><!-- invoice_inner -->
        </div><!-- invoice three_fourth last -->
        <div class="bottombtn">
            <span><a class="iconlink" href="component/${type!=null?'main':fn:toLowerCase(resource.category)}/view">返回</a></span>
        </div>
    <br clear="all">
</div>