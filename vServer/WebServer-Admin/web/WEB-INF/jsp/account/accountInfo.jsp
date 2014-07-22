<%--
  User: Administrator
  Date: 13-2-5
  Time: 上午9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看帐号详细信息</title>
    <jsp:include page="../common/_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 700px;
            margin: 10px auto;
        }

        .categoryTitle {
            font-size: 18px;
            border-bottom: 1px solid #225;
        }

        .firstColom {
            width: 20%;
            text-align: right;
            height: 35px;
            padding-right: 18px;
        }

        .divMargin {
            width: 100%;
            margin: 15px 0px 25px 0px;;
        }

    </style>
</head>
<body class="detailsbody">
<script type="text/javascript">
    function enterClick() {
        window.location.href = "account/list/enterDelete"
    }
</script>
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">
    <div id="context">
        <div class="breadcrumbs">
            <a href="#">帐号详细信息</a>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle">帐号信息</h1>
            <div class="form_default">
                <fieldset>
                    <div style="width: 100%; margin: 10px;">
                        <h2 class="categoryTitle">基本信息</h2>
                        <div class="divMargin">
                            <table width="100%">
                                <tr>
                                    <td width="180px" align="center" valign="middle" rowspan="4">
                                        <img width="100px" height="120px" src="${rootPath}resource/images/accountface/${baseAccount.account}.jpg">
                                    </td>
                                    <td height="30px" width="80px;">帐号姓名</td>
                                    <td><b>${baseAccount.showName}</b></td>
                                </tr>
                                <tr>
                                    <td height="30px;">登录帐号</td>
                                    <td><b>${baseAccount.account}</b></td>
                                </tr>
                                <tr>
                                    <td height="30px;">账号状态</td>
                                    <td><%--启用--%></td>
                                </tr>
                                <tr>
                                    <td height="30px;">创建时间</td>
                                    <td> <fmt:formatDate value="${baseAccount.getCreateTime()}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                                </tr>
                            </table>
                        </div>

                        <h2 class="categoryTitle">联系信息</h2>
                        <div class="divMargin">
                            <table width="100%">
                                <c:forEach items="${expandAccounts}" var="ea" >
                                    <tr>
                                        <td class="firstColom">
                                            电子邮件
                                        </td>
                                        <td>
                                            ${ea.email}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">
                                            手机号码
                                        </td>
                                        <td>
                                            ${ea.mobile}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>

                        <h2 class="categoryTitle">登录信息</h2>
                        <div class="divMargin">
                            <table width="100%">
                                <tr>
                                    <td class="firstColom">
                                        登 录 次 数
                                    </td>
                                    <td>
                                        ${baseAccount.loginCount}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="firstColom" >
                                        上次登录时间
                                    </td>
                                    <td>
                                       <fmt:formatDate value="${baseAccount.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="firstColom" >
                                        登&nbsp;&nbsp;录&nbsp;&nbsp; IP
                                    </td>
                                    <td>
                                        ${baseAccount.loginIp}
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </fieldset>
                <div  class="bottombtn">
                    <button onclick="javascript:location.href = '${rootPath}account/view'" >返回</button>
                    <%--<button id="btnDel" style="display:false; margin-left: 10px;" onclick="enterClick();">删除</button>--%>
                </div>
            </div>
        </div>
    </div>
    <br clear="all"/>
</div>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>