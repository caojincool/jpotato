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
        <form method="post" action="system/basicSettings/edit">
            <div class="form_default">
                <fieldset>
                    <legend>Session过期时间设置</legend>
                    <p>
                        <label for="sessionTime">过期时间（分钟）</label>
                        <input type="text" class="sf" name="sessionTime" value="<%=PropsUtils.readValue("sessionTime")%>">
                    </p>

                </fieldset>
            </div><!--form-->



            <div class="form_default">
                <fieldset>
                    <legend>通讯密钥设置</legend>
                    <p>
                        <label for="encryptKey">通讯密钥设置</label>
                        <input type="password" class="sf" name="encryptKey" value="<%=PropsUtils.readValue("encryptKey")%>">
                    </p>

                </fieldset>
            </div><!--form-->




            <div class="form_default">
                <fieldset>
                    <legend>数据库基本设置</legend>

                    <p>
                        <label for="dbHost">服务IP：</label>
                        <input type="text" class="sf" name="dbHost" value="<%=PropsUtils.readValue("dbHost")%>">
                    </p>
                    <p>
                        <label for="dbPort">端口：</label>
                        <input type="text" class="sf" name="dbPort" value="<%=PropsUtils.readValue("dbPort")%>">
                    </p>
                    <p>
                        <label for="dbUsername">用户名：</label>
                        <input type="text" class="sf" name="dbUsername" value="<%=PropsUtils.readValue("dbUsername")%>">
                    </p>
                    <p>
                        <label for="dbPassword">密码：${org.apache.catalina.filters.CSRF_NONCE }</label>
                        <input type="passwordS" class="sf" name="dbPassword" value="<%=PropsUtils.readValue("dbPassword")%>">
                    </p>

                </fieldset>
            </div><!--form-->
        </form>
        <div class="bottombtn form_default">
            <button  type="reset" >重置</button>
            <button type="submit" >保存</button>
        </div>
    </div><!--fullpage-->

    <br clear="all">

</div>