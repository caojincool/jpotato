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
                        <td class="firstColom">任务编码:</td>
                        <td><input class="-required" type="text" id="pid"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">任务名称:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">状态:</td>
                        <td>   <select id="state" name="state" tabindex="2" style="width: 150px;">
                            <option value="-1" selected="selected">全部</option>
                            <option value="1" >任务执行成功</option>
                            <option value="2" >任务执行错误</option>
                            <option value="4" >任务执行超时</option>
                            <option value="8" >任务正在运行</option>
                        </select>
                        </td>
                        <td >
                            <a id="queryAccount" class="iconlink">
                                <img src="resource/images/icons/small/white/search.png" class="mgright5" alt="">
                                <span>查询</span>
                            </a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>

        <div id="accounTable_wrapper" class="dataTables_wrapper">
            <div class="dataTables_wrapper" id="example_wrapper">
                <table  id="accounTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">
                    <thead>
                    <tr>
                        <th class="head0 " rowspan="1" colspan="1" >日志编码</th>
                        <th class="head0 " rowspan="1" colspan="1" >任务编码</th>
                        <th class="head1 " rowspan="1" colspan="1" >任务名称</th>
                        <th class="head0 " rowspan="1" colspan="1">执行日期</th>
                        <th class="head0 " rowspan="1" colspan="1">执行完成日期</th>
                        <th class="head0 " rowspan="1" colspan="1">耗时（毫秒）</th>
                        <th class="head1 " rowspan="1" colspan="1" >执行结果</th>
                        <th class="head1 " rowspan="1" colspan="1" >备注</th>

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
                        <th class="head0 " rowspan="1" colspan="1" >日志编码</th>
                        <th class="head0 " rowspan="1" colspan="1" >任务编码</th>
                        <th class="head1 " rowspan="1" colspan="1" >任务名称</th>
                        <th class="head0 " rowspan="1" colspan="1">执行日期</th>
                        <th class="head0 " rowspan="1" colspan="1">执行完成日期</th>
                        <th class="head0 " rowspan="1" colspan="1">耗时（毫秒）</th>
                        <th class="head1 " rowspan="1" colspan="1" >执行结果</th>
                        <th class="head1 " rowspan="1" colspan="1" >备注</th>
                    </tr>
                    </tfoot>
                    <tbody>

                    </tbody>
                </table>

            </div>
        </div>
    </div><!--left-->

</div><!--left-->

<br clear="all">

</div>