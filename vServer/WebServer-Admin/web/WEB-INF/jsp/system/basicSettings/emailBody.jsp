<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-9-3
  Time: 下午1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.lemsun.web.manager.controller.util.PropsUtils" %>
<div class="maincontent">
    <div class="left">
        <h1 class="pageTitle">后端邮箱设置</h1>
        <form method="post" action="system/basicSettings/edit">
            <div class="form_default">
                <fieldset>
                    <legend>邮箱设置</legend>
                    <p>
                        <label for="sessionTime">SMTP 服务器</label>
                        <input id="sessionTime" type="text" class="sf" name="sessionTime" value="">
                    </p>
                    <p>
                        <label for="port">端口</label>
                        <input type="text" id="port" class="sf" name="port" value="">
                    </p>
                    <p>
                        <label for="verify">验证</label>
                        <input type="checkbox" id="verify"  name="verify" value="">
                    </p>
                    <p>
                        <label for="sAddress">发信人邮件地址</label>
                        <input type="text" id="sAddress" class="sf" name="sAddress" value="">
                    </p>
                    <p>
                        <label for="sEmail">SMTP 身份验证用户名</label>
                        <input type="text" id="sEmail" class="sf" name="sEmail" value="">
                    </p>
                    <p>
                        <label for="sPassword">SMTP 身份验证密码</label>
                        <input type="text" id="sPassword" class="sf" name="sPassword" value="">
                    </p>
                    <p>
                        <label for="containUser">收件人地址中包含用户名</label>
                        <input type="radio" id="containUser"  name="containUser" value="1" checked="checked"> 是
                        <input type="radio"  name="containUser" value="0"> 否
                    </p>
                    <p>
                        <label for="screenError">屏蔽邮件发送中的错误提示</label>
                        <input type="radio" id="screenError"  name="screenError" value="1" checked="checked"> 是
                        <input type="radio"  name="screenError" value="0"> 否
                    </p>
                </fieldset>
            </div><!--form-->
        </form>
        <div class="bottombtn form_default">
            <button  type="submit" >保存</button>
            <button  type="reset" >重置</button>
        </div>
    </div><!--fullpage-->
    <br clear="all">

</div>