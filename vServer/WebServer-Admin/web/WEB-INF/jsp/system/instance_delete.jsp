<%--
  实例信息预览界面
  User: gm
  Date: 13-1-16
  Time: 下午6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>删除系统实例信息</title>
    <jsp:include page="../common/_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 680px;
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

<div style="display: none;" id="cboxOverlay"></div><div style="padding-bottom: 20px; padding-right: 0px; display: none;" class="" id="colorbox"><div id="cboxWrapper"><div><div style="float: left;" id="cboxTopLeft"></div><div style="float: left;" id="cboxTopCenter"></div><div style="float: left;" id="cboxTopRight"></div></div><div style="clear: left;"><div style="float: left;" id="cboxMiddleLeft"></div><div style="float: left;" id="cboxContent"><div style="width: 0px; height: 0px; overflow: hidden; float: left;" id="cboxLoadedContent"></div><div style="float: left;" id="cboxLoadingOverlay"></div><div style="float: left;" id="cboxLoadingGraphic"></div><div style="float: left;" id="cboxTitle"></div><div style="float: left;" id="cboxCurrent"></div><div style="float: left;" id="cboxNext"></div><div style="float: left;" id="cboxPrevious"></div><div style="float: left;" id="cboxSlideshow"></div><div style="float: left;" id="cboxClose"></div></div><div style="float: left;" id="cboxMiddleRight"></div></div><div style="clear: left;"><div style="float: left;" id="cboxBottomLeft"></div><div style="float: left;" id="cboxBottomCenter"></div><div style="float: left;" id="cboxBottomRight"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div>

<div class="headerspace"></div>

<jsp:include page="../common/top.jsp" />

<div class="detailscontent">
    <div class="left">

        <div >
            <h1 class="pageTitle">系统实例信息预览</h1>

            <form method="post" action="system/instance/${listone.getId()}/delete" id="formupdate">
                <div class="form_default">
                    <fieldset>
                        <div >
                            <h2 class="categoryTitle">基本信息</h2>

                            <div class="divMargin">
                                <input name="pid" value="${listone.getId()}" type="hidden"/>
                                <table width="100%">
                                    <tr>
                                        <td class="firstColom">实例名称:</td>
                                        <td><b>${listone.getName()}</b></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">系统类型:</td>
                                        <td>${listone.getOwner().getName()}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">启用日期:</td>
                                        <td><fmt:formatDate value='${listone.getQyDate()}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">结束日期:</td>
                                        <td><fmt:formatDate value='${listone.getJsDate()}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">主页组件:</td>
                                        <td>${listone.startPage}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">登录组件:</td>
                                        <td>${listone.logon}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">注销组件:</td>
                                        <td>${listone.logon}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">错误处理组件:</td>
                                        <td>${listone.error}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">通讯密钥:</td>
                                        <td>${listone.getTxKey()}</td>
                                    </tr>
                                </table>
                            </div>
                            <h2 class="categoryTitle">注册信息</h2>

                            <div class="divMargin">
                                <table width="100%">
                                    <tr>
                                        <td class="firstColom">使用人 :</td>
                                        <td>${listone.user}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">办公地址:</td>
                                        <td>${listone.getAddress()}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">IP地址:</td>
                                        <td>${listone.getIp()}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">机器码 :</td>
                                        <td>${listone.getJiqicode()}</td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">说明:</td>
                                        <td>${listone.getRemark()}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                    </fieldset>
                    <div class="bottombtn">
                        <button  type="button" onclick="javascript:window.location.href='system/instance';">返回</button>

                        <button  type="submit">删除</button>
                    </div>
                </div>
            </form>
        </div>


    </div><!--left-->
</div>
<br clear="all">


<br>
<div class="footer">
    <jsp:include page="../common/_footer.jsp" />
</div>

</body>
</html>