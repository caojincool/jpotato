<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-1-15
  Time: 下午5:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建角色</title>

    <jsp:include page="../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css" />
    <style type="text/css">



        .firstColom {
            width: 20%;
            text-align: right;
            height: 44px;
            padding-right: 18px;
        }

        .divMargin {
            width: 100%;
            margin: 15px 0px 25px 0px;;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div  class="detailscontent">

        <div class="breadcrumbs">
            <a href="javascript:void(0);">角色基本信息</a>
            <span class="dh">选择权限</span>
            <span class="dh">选择帐号</span>
            <span class="dh">完成</span>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle" style="margin-bottom: 0;">角色基本信息</h1>
            <form method="POST" action="role/premission/create">
                <div class="form_default">
                        <fieldset>
                                <legend style="font-size: 16px;">角色基本信息</legend>
                                <div class="divMargin">

                                    <table width="100%">
                                        <tr>
                                            <td class="firstColom">角色名称:</td>
                                            <td> <input id="name" type="text" class="sf -required -ajax;role/create/isExist" name="name">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="firstColom">角色描述:</td>
                                            <td>  <textarea id="describe"  rows="3" cols="35" name="describe"></textarea>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                        </fieldset>
                    <div class="bottombtn">
                        <button type="button" id="black"  onclick="javascript:window.location.href=''+rootPath+'role/view';">返回</button><button type="submit">下一步</button>
                    </div>
                </div>
            </form>
        </div>

    <br clear="all"/>
</div>
<br/>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>