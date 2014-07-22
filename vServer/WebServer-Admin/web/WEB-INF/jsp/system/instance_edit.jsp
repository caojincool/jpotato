<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-16
  Time: 下午5:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改系统实例信息</title>
    <jsp:include page="../_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <script type="text/javascript" src="${rootPath}resource/js/system/instance.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation//form.css" />
    <style type="text/css">


        .categoryTitle {
            font-size: 18px;
            border-bottom: 1px solid #225;
        }

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
        .divInput{
            width:50px;
            height:26px;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div class="detailscontent">

        <div>
            <h1 class="pageTitle">修改系统实例信息</h1>

            <form method="post" action="system/instance/update/save"  id="formupdate">
                <div class="form_default" >
                    <fieldset>
                        <div style="width: 100%; margin: 10px;">


                            <div class="divMargin">
                                <input id="pid" name="pid" value="${listone.getId()}" type="hidden"/>
                                <table width="100%">
                                    <tr>
                                        <td class="firstColom">实例名称:</td>
                                        <td><input class="sf -required" type="text" id="name" name="name"
                                                   value="${listone.getName()}" tabindex="1"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">系统类型:</td>
                                        <td>${listone.getOwner().getName()}<input type="hidden" id="categorytype"
                                                                                  name="categorytype"
                                                                                  value="${listone.getOwner().getName()}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">默认主页组件:</td>
                                        <td><input type="text" name="startPage" id="startPage" class="sf"
                                                   value="${listone.startPage}" tabindex="9">
                                            <input type="button" id="delete1" class="divInput" value="选择"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">登录组件:</td>
                                        <td><input type="text" name="logon" id="logon" class="sf"
                                                   value="${listone.logon}" tabindex="9">
                                            <input type="button" id="delete2" class="divInput" value="选择"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">注销组件:</td>
                                        <td><input type="text" name="logout" id="logout" class="sf"
                                                   value="${listone.logout}" tabindex="9">
                                            <input type="button" id="delete3" class="divInput" value="选择"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">错误处理组件:</td>
                                        <td><input type="text" name="error" id="error" class="sf"
                                                   value="${listone.error}" tabindex="9">
                                            <input type="button" id="delete4" class="divInput" value="选择"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">通讯密钥:</td>
                                        <td><input type="text" id="txKey" name="txKey" class="sf -required"
                                                   value="${listone.getTxKey()}" tabindex="11"></td>
                                    </tr>
                                </table>
                            </div>
                            <h2 class="categoryTitle">注册信息</h2>

                            <div class="divMargin">
                                <table width="100%">
                                    <tr>
                                        <td class="firstColom">使用人 :</td>
                                        <td><input type="text" class="sf" id="user" name="user" value="${listone.user}"
                                                   tabindex="13"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">办公地址:</td>
                                        <td><input type="text" class="sf" id="address" name="address"
                                                   value="${listone.getAddress()}" tabindex="15"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">IP地址:</td>
                                        <td><input type="text" class="sf -ip" id="ip" name="ip" value="${listone.getIp()}"
                                                   tabindex="17"></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">机器码 :</td>
                                        <td><input type="text" class="sf" id="jiqicode" name="jiqicode"
                                                   value="${listone.getJiqicode()}" tabindex="19"></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">启用日期:</td>
                                        <td><input type="text" class="sf" id="qyDate" class="-required" name="qyDate"
                                                   value="<fmt:formatDate value='${listone.getQyDate()}' pattern='yyyy-MM-dd HH:mm:ss'/>" tabindex="5"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">结束日期:</td>
                                        <td><input type="text" name="jsDate" class="sf" id="jsDate"
                                                   value="<fmt:formatDate value='${listone.getJsDate()}' pattern='yyyy-MM-dd HH:mm:ss'/>" tabindex="7"/></td>
                                    </tr>
                                    <tr>
                                        <td class="firstColom">说明:</td>
                                        <td><textarea id="cendscript" name="remark" rows="3" cols="35"
                                                >${listone.getRemark()}</textarea></td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                    </fieldset>
                    <div class="bottombtn">
                        <button type="button"
                                onclick="javascript:window.location.href='system/instance';">
                            返回
                        </button>
                        <button type="submit">提交</button>
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
<script>
    jQuery(function() {
        jQuery( "#qyDate" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
                jQuery( "#jsDate" ).datepicker( "option", "minDate", selectedDate );
            }
        });
        jQuery( "#jsDate" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function( selectedDate ) {
                jQuery( "#qyDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });

    });
</script>
<script type="text/javascript">
    String.prototype.trim = function () {
        return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
    }



</script>