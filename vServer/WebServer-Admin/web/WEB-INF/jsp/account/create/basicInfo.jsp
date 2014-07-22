<%--
  User: dpyang
  Date: 13-3-6
  Time: 上午10:35
  创建账户基本信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>账户基本信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <jsp:include page="../../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css" />
    <style type="text/css">


        .divMargin {
            width: 100%;
            margin: 15px 0px 25px 0px;;
        }

        .cellStyel {
            height: 50px;
            width: 80px;
        }

        .divMargin span {
            color: red;
        }

    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="dashboard.html">创建基本账户</a>
        </div>
        <div class="marginBreadcrumbs">
            <h1 class="pageTitle">账号基本信息</h1>
            <form method="post" action="account/create/access" onsubmit="return submitFun()">
                <div class="form_default">
                    <fieldset>
                        <legend>账号信息</legend>
                        <div style="width: 100%; margin: 10px;">
                            <div class="divMargin">
                                <table width="100%">
                                    <tr>
                                        <td class="cellStyel"><label for="showName">账户姓名</label></td>
                                        <td><input type="text" class="sf -required" name="showName" id="showName">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="cellStyel"><label for="account">登陆账号</label></td>
                                        <td><input type="text" class="sf -required" name="account" id="account">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="cellStyel"><label for="password">登录密码</label></td>
                                        <td><input type="password" class="sf -min_length;4 -max_length;8" name="password"
                                                   id="password"></td>
                                    </tr>
                                    <tr>
                                        <td class="cellStyel"><label for="rePassword">确认密码</label></td>
                                        <td><input type="password" class="sf -same_as;password" name="rePassword"
                                                   id="rePassword"></td>
                                    </tr>
                                    <tr>
                                        <td class="cellStyel"><label for="isLock">是否锁定</label></td>
                                        <td><input type="checkbox" name="isLock" id="isLock"></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </fieldset>
                    <div class="bottombtn">
                        <button type="button" id="black">返回</button>
                        <script type="text/javascript">
                            jQuery(function () {
                                jQuery("#black").bind("click", function () {
                                    location.href = rootPath + '/account/view';
                                });
                            })

                        </script>
                        <button type="submit">创建</button>
                    </div>
                </div>
            </form>

    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>