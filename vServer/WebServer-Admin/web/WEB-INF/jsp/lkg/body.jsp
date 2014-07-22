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
                        <td class="firstColom">包编码:</td>
                        <td><input class="-required" type="text" id="lid"  name="lid"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">包名称:</td>
                        <td>
                            <input class="-required" type="text" id="name" name="name" tabindex="1"/>
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

            <table id="pTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">

                <thead>
                <tr>
                    <th class="head0" rowspan="1" colspan="1" >编码</th>
                    <th class="head1" rowspan="1" colspan="1" >包名称</th>
                    <th class="head0" rowspan="1" colspan="1" >版本号</th>
                    <th class="head0" rowspan="1" colspan="1">开始页面</th>
                    <th class="head1" rowspan="1" colspan="1" >描述</th>
                    <th class="head0" rowspan="1" colspan="1">导出脚本</th>
                    <th class="head0" rowspan="1" colspan="1">导入脚本</th>
                    <th class="head0" rowspan="1" colspan="1"  >操作</th>
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
                    <th class="head0" rowspan="1" colspan="1" >编码</th>
                    <th class="head1" rowspan="1" colspan="1" >包名称</th>
                    <th class="head0" rowspan="1" colspan="1" >版本号</th>
                    <th class="head0" rowspan="1" colspan="1">开始页面</th>
                    <th class="head1" rowspan="1" colspan="1" >描述</th>
                    <th class="head0" rowspan="1" colspan="1">导出脚本</th>
                    <th class="head0" rowspan="1" colspan="1">导入脚本</th>
                    <th class="head0" rowspan="1" colspan="1"  width="180">操作</th>
                </tr>
                </tr>
                </tfoot>
                <tbody>
                </tbody>

            </table>


        </div>
    </div><!--left-->

    <br clear="all">

</div>