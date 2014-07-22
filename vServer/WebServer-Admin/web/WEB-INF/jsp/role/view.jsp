<%--
  角色
  User: dpyang
  Date: 13-1-16
  Time: 上午11:22
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
                        <td class="firstColom">角色名称:</td>
                        <td><input class="-required" type="text" id="name"  name="name" tabindex="1"/>
                        </td>
                        <td class="firstColom">说明:</td>
                        <td><input class="-required" type="text" id="describe" name="describe" tabindex="1"/>
                        </td>
                        <td align="right">
                            <a class="iconlink" id="queryAccount">
                                <img src="resource/images/icons/small/white/search.png" class="mgright5" alt="">
                                <span>查询</span>
                            </a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>

        <div class="dataTables_wrapper" id="example_wrapper">
            <table id="accounTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">
                <thead>
                <tr>
                    <th class="head0 " rowspan="1" colspan="1" width="130">角色名称</th>
                    <th class="head1 " rowspan="1" colspan="1" >说明</th>
                    <th class="head0 " rowspan="1" colspan="1" width="40">内置</th>
                    <th class="head1 " rowspan="1" colspan="1" width="140">创建时间</th>
                    <th class="head0 " rowspan="1" colspan="1" width="100">创建者</th>
                    <th class="head1 " rowspan="1" colspan="1" width="130">操作</th>
                </tr>
                </thead>
                <colgroup>
                    <col class="con0">
                    <col class="con1">
                    <col class="con0">
                    <col class="con1">
                    <col class="con0">
                    <col class="con1">
                </colgroup>
                <tfoot>
                <tr>
                    <th class="head0" rowspan="1" colspan="1">角色名称</th>
                    <th class="head1" rowspan="1" colspan="1">说明</th>
                    <th class="head0" rowspan="1" colspan="1">内置</th>
                    <th class="head1" rowspan="1" colspan="1">创建时间</th>
                    <th class="head0" rowspan="1" colspan="1">创建者</th>
                    <th class="head1" rowspan="1" colspan="1">操作</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <!--left-->
    <br clear="all">

</div>