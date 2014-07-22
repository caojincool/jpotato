<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-9-3
  Time: 下午1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="maincontent">
    <div class="left">

        <div class="form_default">
            <fieldset>
                <legend>查询条件</legend>
                <table width="100%">
                    <tr>
                        <td class="firstColom">实例名称:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">系统类型:</td>
                        <td>
                            <select id="categorytype" name="categorytype" tabindex="2" style="width: 260px;">
                                <c:forEach var="category" items="${categorylist}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: right">
                            <a class="iconlink"  id="queryAccount">
                                <img src="resource/images/icons/small/white/search.png" class="mgright5" alt="">
                                    <span>
                                    查询
                                </span></a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>

        <div class="dataTables_wrapper" id="example_wrapper">

            <table class="dyntable" cellspacing="0" cellpadding="0" border="0" id="instanceTable">

                <thead>
                <tr>
                    <th class="head0" rowspan="1" colspan="1" >编码</th>
                    <th class="head1" rowspan="1" colspan="1" >实例名称</th>
                    <th class="head0" rowspan="1" colspan="1">使用系统</th>
                    <th class="head1" rowspan="1" colspan="1" >IP</th>
                    <th class="head0" rowspan="1" colspan="1">地址</th>
                    <th class="head0" rowspan="1" colspan="1" >启用日期</th>
                    <th class="head0" rowspan="1" colspan="1" width="130">操作</th>
                </tr>
                </thead>
                <colgroup>
                    <col class="con0">
                    <col class="con1">
                    <col class="con0">
                    <col class="con1">
                    <col class="con0">
                    <col class="con1">
                    <col class="con0">
                </colgroup>
                <tfoot>
                <tr>
                    <th class="head0" rowspan="1" colspan="1" >编码</th>
                    <th class="head1" rowspan="1" colspan="1" >实例名称</th>
                    <th class="head0" rowspan="1" colspan="1">使用系统</th>
                    <th class="head1" rowspan="1" colspan="1" >IP</th>
                    <th class="head0" rowspan="1" colspan="1">地址</th>
                    <th class="head0" rowspan="1" colspan="1" >启用日期</th>
                    <th class="head0" rowspan="1" colspan="1" width="130">操作</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>

            </table>


        </div>
    </div><!--left-->

    <br clear="all">

</div>