<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-9-3
  Time: 下午1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="detailscontent" >
    <div class="notification msginfo">
        <p>以下各种统计信息是最近一月统计结果显示！</p>
    </div>
    <div class="widgetbox" style="width: 490px;height: 250px;float:left; ">
        <h3 class="" style="border-radius: 3px 3px 0px 0px;"><span>组件统计</span></h3>

        <div id="mainDiv" class="content nopadding" style="width: 490px;height: 250px;float:left;">
        </div>
        <!--content-->
    </div>
    <div class="widgetbox" style="width: 490px;height:250px;float:right;">
        <h3 class="" style="border-radius: 3px 3px 0px 0px;"><span>流程统计</span></h3>

        <div id="flowDiv" class="content nopadding" style="width: 490px;height:250px;float:left;">
        </div>
        <!--content-->
    </div>
    <div class="widgetbox" style="width: 490px;height: 285px;float:left; margin-top: 50px;">
        <h3 class="" style="border-radius: 3px 3px 0px 0px;"><span>任务统计</span></h3>

        <div id="taskDiv" class="content nopadding" style="width: 490px;height: 250px;float:left;">


        </div>
        <!--content-->
    </div>
    <div class="widgetbox" style="width: 490;height: 285px;float:right; margin-top: 50px;">
        <h3 class="" style="border-radius: 3px 3px 0px 0px;"><span>用户统计</span></h3>

        <div id="userDiv" class="content nopadding" style="width: 490px;height: 250px;float:left;">
        </div>
        <!--content-->
    </div>
    <br clear="all">
</div>