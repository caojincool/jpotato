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
                        <td class="firstColom">程序名称:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">导入日期:</td>
                        <td><input class="-required" type="text" id="name"  name="name"
                                   tabindex="1"/>
                        </td>
                        <td class="firstColom">导入人:</td>
                        <td>  <input class="-required" type="text" id="name"  name="name"
                                     tabindex="1"/>
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
        <div id="accounTable_wrapper" class="dataTables_wrapper">
            <div id="accounTable_length" class="dataTables_length"><label>每页显示 <select name="accounTable_length" size="1"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> 项记录</label></div>
            <div class="dataTables_filter" id="example_filter"><label>

                <a class="iconlink" href="javascript:void(0);">
                    <img src="resource/images/icons/small/white/plus.png" class="mgright5" alt="">
                    <span>导入</span></a></label>
            </div>

            <div class="dataTables_wrapper" id="example_wrapper">
                <table  id="accounTable" class="dyntable" cellspacing="0" cellpadding="0" border="0">
                    <thead>
                    <tr>
                        <th class="head0 " rowspan="1" colspan="1" >编码</th>
                        <th class="head1 " rowspan="1" colspan="1" >程序名称</th>
                        <th class="head0 " rowspan="1" colspan="1">导入日期</th>
                        <th class="head0 " rowspan="1" colspan="1">导入人</th>
                        <th class="head1 " rowspan="1" colspan="1" >说明</th>
                        <th class="head0 " rowspan="1" colspan="1" width="150">操作</th>
                    </tr>
                    </thead>
                    <colgroup>
                        <col class="con0">
                        <col class="con1">
                        <col class="con0">
                        <col class="con1">
                        <col class="con0">
                    </colgroup>
                    <tfoot>
                    <tr>

                        <th class="head0 " rowspan="1" colspan="1" >编码</th>
                        <th class="head1 " rowspan="1" colspan="1" >程序名称</th>
                        <th class="head0 " rowspan="1" colspan="1">导入日期</th>
                        <th class="head0 " rowspan="1" colspan="1">导入人</th>
                        <th class="head1 " rowspan="1" colspan="1" >说明</th>
                        <th class="head0 " rowspan="1" colspan="1" width="150">操作</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr class="odd">
                        <td class="center">A00000150</td>
                        <td class="center">财务软件</td>
                        <td class="center">2013年3月2日</td>
                        <td class="center">张三</td>
                        <td class="center">公司使用</td>
                        <td class="center" style="text-align: right">
                            <a href="account/accountInfo?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/user.png" alt="查看"></a>
                            &nbsp;<a href="account/list/del?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/close.png" alt="删除"></a>
                        </td>
                    </tr>
                    <tr class="even">
                        <td class="center">A00000150</td>
                        <td class="center">餐饮软件</td>
                        <td class="center">2013年3月2日</td>
                        <td class="center">张三</td>
                        <td class="center">公司使用</td>
                        <td class="center" style="text-align: right">
                            <a href="account/accountInfo?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/user.png" alt="查看"></a>
                            &nbsp;<a href="account/list/del?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/close.png" alt="删除"></a>
                        </td>
                    </tr>
                    <tr class="odd">
                        <td class="center">A00000150</td>
                        <td class="center">餐饮软件</td>
                        <td class="center">2013年3月2日</td>
                        <td class="center">张三</td>
                        <td class="center">公司使用</td>
                        <td class="center" style="text-align: right">
                            <a href="account/accountInfo?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/user.png" alt="查看"></a>
                            &nbsp;<a href="account/list/del?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/close.png" alt="删除"></a>
                        </td>
                    </tr>
                    <tr class="even">
                        <td class="center">A00000150</td>
                        <td class="center">餐饮软件</td>
                        <td class="center">2013年3月2日</td>
                        <td class="center">张三</td>
                        <td class="center">公司使用</td>
                        <td class="center" style="text-align: right">
                            <a href="account/accountInfo?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/user.png" alt="查看"></a>
                            &nbsp;<a href="account/list/del?accountId=A00000150" class="iconlink2"><img src="resource/images/icons/small/black/close.png" alt="删除"></a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="dataTables_info" id="accounTable_info">当前数据为从第 1 到第 4 项数据；总共有 4 项记录</div>
                <div class="dataTables_paginate paging_full_numbers" id="accounTable_paginate">
                    <span class="first paginate_button paginate_button_disabled" id="accounTable_first">首页</span>
                    <span class="previous paginate_button paginate_button_disabled" id="accounTable_previous">上一页</span>
                <span><span class="paginate_active ">1</span>

                </span><span class="next paginate_button" id="accounTable_next">下一页</span>
                    <span class="last paginate_button" id="accounTable_last">末页</span>
                </div>
            </div>
        </div>
    </div><!--left-->

</div><!--left-->

<br clear="all">

</div>