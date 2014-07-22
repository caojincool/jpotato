<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  帐号基本信息创建成功页面,选择完成或者扩展其他信息
  User: dpyang
  Date: 13-3-9
  Time: 下午1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建成功</title>
    <jsp:include page="../../common/_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 500px;
            margin: 10px auto;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="dashboard.html">账户创建成功</a>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle">联系方式</h1>

            <%--<form method="post" action="" onsubmit="return submitFun()">--%>
            <div class="form_default">
                <fieldset>
                    <legend>创建状态</legend>
                    <%--<input type="hidden" class="sf" name="showName" id="showName" value="${model.showName}">--%>
                    <%--<input type="hidden" class="sf" name="account" id="account" value="${model.account}">--%>
                    <%--<input type="hidden" class="sf" name="password" id="password" value="${model.password}">--%>
                    <%--<input type="hidden" class="sf" name="rePassword" id="rePassword" value="${model.rePassword}">--%>
                    <%--<input type="hidden" name="isLock" id="isLock" value="${model.isLock}">--%>
                    <p>

                    <h2>基本账户创建成功,请选择下一步操作!</h2>
                    </p>
                    <form method="post" action="account/expand/avatar">
                        <div class="bottombtn">
                            <button id="back" type="button" onclick="javascript:location.href='${rootPath}account/view'"  >返回列表
                            </button>
                            <button id="goOn" type="button" onclick="javascript:location.href='${rootPath}account/create/basicInfo'" >
                                继续创建
                            </button>
                            <input type="hidden" name="account" value="${baseAccount}" >
                            <button id="expend" type="submit">扩充账户</button>
                        </div>

                    </form>
                </fieldset>
            </div>
            <%--</form>--%>
        </div>
    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>