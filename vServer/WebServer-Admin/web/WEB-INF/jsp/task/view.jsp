<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 12-10-19
  Time: 下午6:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="${rootPath}">
    <script type="text/javascript">
        var rootPath = '${rootPath}';
    </script>
    <jsp:include page="../common/_header.jsp"/>
    <script type="text/javascript">
        var accounTable;


        jQuery(document).ready(function () {
            jQuery("#queryAccount").bind("click", function () {
                if (accounTable == null || accounTable == undefined) {

                } else {
                    accounTable.fnDestroy();
                }
                accounTable = jQuery('#accounTable').dataTable({
                    bPaginate: true,
                    bSort: false,
                    "sPaginationType": "full_numbers",
                    bFilter: false,
                    "bProcessing": false,
                    "bServerSide": true,
                    "sAjaxSource": "task/instance/list/get?rand=" + Math.random(),
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({ "name": "page", "value": aoData[3].value / aoData[4].value + 1  });
                        aoData.push({ "name": "limit", "value": aoData[4].value  });
                        aoData.push({ "name": "executeTime", "value": jQuery("#executeTime").val()  });
                        aoData.push({ "name": "state", "value": jQuery("#state").val()  });
                        aoData.push({ "name": "showName", "value": jQuery("#createUser").val()  });
                        aoData.push({ "name": "name", "value": jQuery("#name").val() });
                        jQuery.ajax({
                            "dataType": 'json',
                            "type": "get",
                            "url": sSource,
                            "data": aoData,
                            "success": function (rep) {
                                fnCallback(rep);
                                jQuery("#accounTable_length").after( '<div class="dataTables_filter" id="example_filter"><label>' +
                                        '<a class="iconlink" href="component/main/create?type=TASK" target="_blank">' +
                                        '<img src="resource/images/icons/small/white/plus.png" class="mgright5" alt="">' +
                                        '<span>新增</span></a></label></div>');
                            }
                        });
                    },
                    "sAjaxDataProp": "entity.content",
                    "oLanguage": {
                        "sProcessing": "正在加载数据...",
                        'sSearch': '数据筛选:',
                        "sLengthMenu": "每页显示 _MENU_ 项记录",
                        "sZeroRecords": "没有符合项件的数据...",
                        "sInfo": "当前数据为从第 _START_ 到第 _END_ 项数据；总共有 _TOTAL_ 项记录",
                        "sInfoEmpty": "显示 0 至 0 共 0 项",
                        "sInfoFiltered": "(_MAX_)",
                        oPaginate: {
                            "sFirst": "首页",
                            "sNext": "下一页",
                            "sLast": "末页",
                            "sPrevious": "上一页"
                        }
                    },
                    "aoColumns": [
                        { "mDataProp": "pid", "sClass": "center" },
                        {  "mDataProp": "name", "sClass": "center" },
                        {  "mDataProp": "createTime", "sClass": "center" },
                        {  "mDataProp": "createTime", "sClass": "center" },
                        {  "mDataProp": "createUser", "sClass": "center" },
                        {  "mDataProp": "state1", "sClass": "center",
                            "fnRender": function (obj) {
                                if (obj.aData.state==0) {
                                    return "未启用";
                                } else if((obj.aData.state==1)){
                                    return "已启用";
                                } else if((obj.aData.state==2)){
                                    return "正在运行";
                                }else if((obj.aData.state==3)){
                                    return "暂停";
                                }else if((obj.aData.state==4)){
                                    return "任务恢复中";
                                }
                            }
                        },
                        {
                            "sTitle": "操作",
                            "sClass": "center",
                            "fnRender": function (obj) {
                                var str='';
                                switch (obj.aData.state){
                                    case 1:
                                        str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                                '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',2);"><img alt=\"启用\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                        break;
                                    case 2:
                                        str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                                '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',4);"><img alt=\"暂停\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                        break;
                                    case 4:
                                        str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                                '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',8);"><img alt=\"恢复\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                        break;
                                    case 8:
                                        str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                                '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',4);"><img alt=\"暂停\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                                '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                }
                                return str;
                            }
                        }
                    ]
                });
            })
            accounTable = jQuery('#accounTable').dataTable({
                bPaginate: true,
                bSort: false,
                "sPaginationType": "full_numbers",
                bFilter: false,
                "bProcessing": false,
                "bServerSide": true,
                "sAjaxSource": "task/instance/list/get?rand=" + Math.random(),
                "fnServerData": function (sSource, aoData, fnCallback) {
                    aoData.push({ "name": "page", "value": aoData[3].value / aoData[4].value + 1  });
                    aoData.push({ "name": "limit", "value": aoData[4].value  });
                    jQuery.ajax({
                        "dataType": 'json',
                        "type": "get",
                        "url": sSource,
                        "data": aoData,
                        "success": function (rep) {
                            fnCallback(rep);
                            jQuery("#accounTable_length").after( '<div class="dataTables_filter" id="example_filter"><label>' +
                                    '<a class="iconlink" href="component/main/create?type=TASK">' +
                                    '<img src="resource/images/icons/small/white/plus.png" class="mgright5" alt="">' +
                                    '<span>新增</span></a></label></div>');
                        }
                    });
                },
                "sAjaxDataProp": "entity.content",
                "oLanguage": {
                    "sProcessing": "正在加载数据...",
                    'sSearch': '数据筛选:',
                    "sLengthMenu": "每页显示 _MENU_ 项记录",
                    "sZeroRecords": "没有符合项件的数据...",
                    "sInfo": "当前数据为从第 _START_ 到第 _END_ 项数据；总共有 _TOTAL_ 项记录",
                    "sInfoEmpty": "显示 0 至 0 共 0 项",
                    "sInfoFiltered": "(_MAX_)",
                    oPaginate: {
                        "sFirst": "首页",
                        "sNext": "下一页",
                        "sLast": "末页",
                        "sPrevious": "上一页"
                    }
                },
                "aoColumns": [
                    { "mDataProp": "pid", "sClass": "center" },
                    {  "mDataProp": "name", "sClass": "center" },
                    {  "mDataProp": "createTime", "sClass": "center" },
                    {  "mDataProp": "createTime", "sClass": "center" },
                    {  "mDataProp": "createUser", "sClass": "center" },
                    {  "mDataProp": "state1", "sClass": "center",
                        "fnRender": function (obj) {
                            if (obj.aData.state==1) {
                                return "未启用";
                            } else if((obj.aData.state==2)){
                                return "等待执行";
                            }else if((obj.aData.state==4)){
                                return "暂停";
                            }else if((obj.aData.state==8)){
                                return "恢复";
                            }
                        }
                    },

                    {
                        "sTitle": "操作",
                        "sClass": "center",
                        "fnRender": function (obj) {
                            var str='';
                             switch (obj.aData.state){
                                 case 1:
                                     str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                         '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                         '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',2);"><img alt=\"启用\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                         '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                    break;
                                 case 2:
                                     str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                             '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',4);"><img alt=\"暂停\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                     break;
                                 case 4:
                                     str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                             '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',8);"><img alt=\"恢复\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                                     break;
                                 case 8:
                                     str=  '&nbsp;<a class="iconlink2" href=\"task/instance/' + obj.aData.pid + '/taskStatistics\" target="_blank"><img alt=\"查看\" src=\"resource/images/icons/eye_16x16.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/edit\" target="_blank"><img alt=\"编辑\" src=\"resource/images/icons/small/black/edit.png\"></a>'+
                                             '&nbsp;<a class="iconlink2" href="javascript:updateTaskState(\''+ obj.aData.pid +'\',4);"><img alt=\"暂停\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href="javascript:taskExcute(\''+ obj.aData.pid +'\');"><img alt=\"立即执行\" src=\"resource/images/icons/small/black/settings.png\"></a>' +
                                             '&nbsp;<a class="iconlink2" href=\"component/task/' + obj.aData.pid + '/delete\" target="_blank"><img alt=\"删除\" src=\"resource/images/icons/small/black/close.png\"></a>';
                             }
                             return str;
                        }
                    }
                ]
            });
        });
    </script>
</head>
<body class="bodygrey">
<div style="display: none;" id="cboxOverlay"></div>
<div style="padding-bottom: 20px; padding-right: 0px; display: none;" class="" id="colorbox">
    <div id="cboxWrapper">
        <div>
            <div style="float: left;" id="cboxTopLeft"></div>
            <div style="float: left;" id="cboxTopCenter"></div>
            <div style="float: left;" id="cboxTopRight"></div>
        </div>
        <div style="clear: left;">
            <div style="float: left;" id="cboxMiddleLeft"></div>
            <div style="float: left;" id="cboxContent">
                <div style="width: 0px; height: 0px; overflow: hidden; float: left;" id="cboxLoadedContent"></div>
                <div style="float: left;" id="cboxLoadingOverlay"></div>
                <div style="float: left;" id="cboxLoadingGraphic"></div>
                <div style="float: left;" id="cboxTitle"></div>
                <div style="float: left;" id="cboxCurrent"></div>
                <div style="float: left;" id="cboxNext"></div>
                <div style="float: left;" id="cboxPrevious"></div>
                <div style="float: left;" id="cboxSlideshow"></div>
                <div style="float: left;" id="cboxClose"></div>
            </div>
            <div style="float: left;" id="cboxMiddleRight"></div>
        </div>
        <div style="clear: left;">
            <div style="float: left;" id="cboxBottomLeft"></div>
            <div style="float: left;" id="cboxBottomCenter"></div>
            <div style="float: left;" id="cboxBottomRight"></div>
        </div>
    </div>
    <div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div>
</div>
<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<jsp:include page="../common/left.jsp"/>
<jsp:include page="body.jsp"/>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
<div id="dialog-confirm" title="系统提示！">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>确定立即执行吗?</p>
</div>
<div id="dialog-confirm2" title="系统提示！">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>确定暂停吗?</p>
</div>
<script type="text/javascript">
    jQuery( "#dialog-confirm").hide();

    function taskExcute(pid){
        jQuery( "#dialog-confirm" ).dialog({
            resizable: false,
            height:140,
            modal: true,
            buttons: {
                "确定": function() {
                    jQuery( "#dialog-confirm").dialog( "close" );
                    jQuery.ajax({ url: "task/instance/"+pid+"/excute",
                        dataType: "json",
                        type:"POST",
                        success: function(res){
                            if(res.success){
                                location.reload();
                            }else{
                                alert("操作失败");
                            }
                        },
                        error:function(){
                                alert("操作失败");
                        }

                    });

                },
                "取消": function() {
                    jQuery( this ).dialog( "close" );
                }
            }
        });

    }
    function updateTaskState(pid,state){
        jQuery( "#dialog-confirm2" ).dialog({
            resizable: false,
            height:140,
            modal: true,
            buttons: {
                "确定": function() {
                    jQuery( "#dialog-confirm2").dialog( "close" );
                    jQuery.ajax({ url: "task/instance/"+pid+"/udateTaskState/"+state,
                        dataType: "json",
                        type:"POST",
                        success: function(res){
                            if(res.success){
                               location.reload();
                            }else{
                                 alert("操作失败");
                            }
                        },
                        error:function(){
                            alert("操作失败");
                        }

                    });

                },
                "取消": function() {
                    jQuery( this ).dialog( "close" );
                }
            }
        });

    }
</script>
</body>
</html>