<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-12-19
  Time: 上午10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr>
    <td class="firstColom">组件编号:</td>
    <td><input class="-required" type="text" id="pid" name="pid" tabindex="1" value="${query.pid}"/>
    </td>
    <td class="firstColom">组件名:</td>
    <td>
        <input type="hidden" name="page" value="${data.number}"/>
        <input class="-required" type="text" id="name"  name="name" tabindex="1" value="${query.name}"/>
    </td>


    <td rowspan="2" style="text-align: right">
        <a class="iconlink" href="javascript:sub()">
            <img src="resource/images/icons/small/white/search.png" class="mgright5" alt="">
            <span>查询</span>
        </a>
    </td>
</tr>
<tr>
    <td class="firstColom">创建人:</td>
    <td>
        <input class="-required" type="text" id="showName"  name="showName" tabindex="1" value="${query.showName}"/>
    </td>
    <td class="firstColom">组件状态:</td>
    <td>
        <select id="state" name="state" tabindex="2" style="height:28px; width: 140px;">
            <option value="0" selected="selected">全部</option>
            <option value="1" ${query.state==1?'selected=\"selected\"':''}>完成</option>
            <option value="2" ${query.state==2?'selected=\"selected\"':''}>封存</option>
            <option value="4" ${query.state==4?'selected=\"selected\"':''}>禁用</option>
            <option value="8" ${query.state==8?'selected=\"selected\"':''}>未完成</option>
        </select>
    </td>
</tr>