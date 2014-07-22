<%--
  User: dpyang
  Date: 13-3-7
  Time: 上午9:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建账号</title>
    <jsp:include page="../../common/_header.jsp" />
    <script src="jquery/validation/vanadium.js"></script>
    <script src="jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css" />
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div  class="detailscontent">

        <div class="breadcrumbs">
            <a href="javascript:void(0);">扩展头像</a>
            <a href="javascript:void(0);">扩展联系方式</a>
            <span>加入角色</span>
            <span>扩展权限</span>
            <span>完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle">联系方式</h1>

            <form method="post" action="account/expand/doContact" onsubmit="return submitFun()">
                <div class="form_default">
                    <fieldset>
                        <legend>联系方式</legend>
                        <%--<input type="hidden" class="sf" name="showName" id="showName" value="${model.showName}">--%>
                        <%--<input type="hidden" class="sf" name="account" id="account" value="${model.account}">--%>
                        <%--<input type="hidden" class="sf" name="password" id="password" value="${model.password}">--%>
                        <%--<input type="hidden" class="sf" name="rePassword" id="rePassword" value="${model.rePassword}">--%>
                        <%--<input type="hidden" name="isLock" id="isLock" value="${model.isLock}">--%>
                        <p>
                            <label for="email">电子邮件</label>
                            <input type="text" class="sf -email -required;" name="email" id="email">
                        </p>
                        <p>
                            <label for="mobile">手机号码</label>
                            <input type="text" class="sf" name="mobile" id="mobile">
                        </p>

                        <p>
                            <%--<button id="back" type="button" onclick="javascript:location.href=rootPath+'account/expand/avatar'">上一步</button>--%>

                        </p>

                    </fieldset>
                    <div class="bottombtn">
                        <button type="submit">下一步</button>
                    </div>
                </div>
            </form>


    </div>
    <br clear="all" />
</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>