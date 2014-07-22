<%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 13-9-3
  Time: 下午1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="maincontent">
    <div class="left">
        <div class="form_default">
            <fieldset>
                <legend>查询条件</legend>
                <table width="100%">
                    <tr>
                        <td class="firstColom">任务名称:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">状态:</td>
                        <td>   <select id="state" name="state" tabindex="2" style="width: 150px;">
                            <option value="-1" selected="selected">全部</option>
                            <option value="1" >未启用</option>
                            <option value="2" >等待执行</option>
                            <option value="4" >暂停</option>
                            <option value="8" >恢复</option>
                        </select>
                        </td>
                        <td rowspan="2" align="right">
                            <a id="queryAccount" class="iconlink">
                                <img src="resource/images/icons/small/white/search.png" class="mgright5" alt="">
                                <span>查询</span>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td class="firstColom">执行日期:</td>
                        <td><input class="-required" type="text" id="executeTime"  name="executeTime"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">创建人:</td>
                        <td>   <input class="-required" type="text" id="createUser"  name="createUser"
                                      tabindex="1"/>
                        </td>

                    </tr>
                </table>
            </fieldset>
        </div>


        <table  id="accounTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <th class="head0 " rowspan="1" colspan="1" >编码</th>
                <th class="head1 " rowspan="1" colspan="1" >任务名称</th>
                <th class="head0 " rowspan="1" colspan="1">最后执行时间</th>
                <th class="head1 " rowspan="1" colspan="1" >创建日期</th>
                <th class="head0 " rowspan="1" colspan="1">创建人</th>
                <th class="head0 " rowspan="1" colspan="1" >状态</th>
                <th class="head0 " rowspan="1" colspan="1" width="190">操作</th>
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
                <col class="con1">
            </colgroup>
            <tfoot>
            <tr>
                <th class="head0 " rowspan="1" colspan="1" >编码</th>
                <th class="head1 " rowspan="1" colspan="1" >任务名称</th>
                <th class="head0 " rowspan="1" colspan="1">执行日期</th>
                <th class="head1 " rowspan="1" colspan="1" >创建日期</th>
                <th class="head0 " rowspan="1" colspan="1">创建人</th>
                <th class="head0 " rowspan="1" colspan="1" >状态</th>
                <th class="head0 " rowspan="1" colspan="1" width="150">操作</th>
            </tr>
            </tfoot>
            <tbody>

            </tbody>
        </table>

    </div>

</div>

<br clear="all">
