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
                        <td class="firstColom">账号:</td>
                        <td><input class="-required" type="text" id="account" name="account"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">昵称:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
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
              <table  id="accounTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">
                <thead>
                <tr>
                    <th class="head0 " rowspan="1" colspan="1" >编码</th>
                    <th class="head1 " rowspan="1" colspan="1" >登录帐号</th>
                    <th class="head0 " rowspan="1" colspan="1">姓名</th>
                    <th class="head1 " rowspan="1" colspan="1" >登录地址</th>
                    <th class="head0 " rowspan="1" colspan="1">登录次数</th>
                    <th class="head0 " rowspan="1" colspan="1" >已加锁</th>
                    <th class="head0 " rowspan="1" colspan="1">创建时间</th>
                    <th class="head0 " rowspan="1" colspan="1" width="150">操作</th>
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
                    <th class="head1 " rowspan="1" colspan="1" >登录帐号</th>
                    <th class="head0 " rowspan="1" colspan="1">姓名</th>
                    <th class="head1 " rowspan="1" colspan="1" >登录地址</th>
                    <th class="head0 " rowspan="1" colspan="1">登录次数</th>
                    <th class="head0 " rowspan="1" colspan="1" >已加锁</th>
                    <th class="head0 " rowspan="1" colspan="1">创建时间</th>
                    <th class="head0 " rowspan="1" colspan="1" >操作</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
    </div><!--left-->

    <br clear="all">

</div>