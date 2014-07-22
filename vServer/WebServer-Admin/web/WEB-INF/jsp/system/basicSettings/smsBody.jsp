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
        <h1 class="pageTitle">后端短信设置</h1>
        <form method="post" action="system/basicSettings/edit">
            <div class="form_default">
                <fieldset>
                    <legend>短信设置</legend>
                    <p>
                        <label for="verify">启用</label>
                        <input type="checkbox" id="verify"  name="verify" value="">
                    </p>
                </fieldset>
            </div><!--form-->
        </form>
        <div class="bottombtn form_default">
            <button type="submit" >保存</button>
            <button  type="reset" >重置</button>
        </div>
    </div><!--fullpage-->
    <br clear="all">
</div>