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

    <jsp:include page="../../common/_header.jsp"/>

    <script src="jquery/ichart.1.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var data = [
            {
                name : '统计用户报表任务',
                value:[4,16,18,20,32,36,38,38,36,26,20,14],
                color:'#dad81f'
            },
            {
                name : '发邮件',
                value:[2,12,14,20,28,32,34,36,33,24,14,4],
                color:'#1f7e92'
            },
            {
                name : '删除垃圾文件',
                value:[1,12,18,20,28,34,36,34,31,27,24,6],
                color:'#76a871'
            },
            {
                name : '统计组件使用情况',
                value:[3,13,14,20,28,32,34,36,30,24,14,4],
                color:'#6f83a5'
            }
        ];

        var labels = ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];

            $(function(){
                var chart = new iChart.Area2D({
                    render : 'canvasDiv',
                    data: data,
                    title : '任务执行时间分布图',
                    width : 1100,
                    height : 500,
                    area_opacity:0.15,
                    legend : {
                        enable : true
                    },
                    tip:{
                        enable : true,
                        listeners:{
                            //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
                            parseText:function(tip,name,value,text,i){
                                return "<span style='color:#005268;font-size:11px;font-weight:600;'>"+name+
                                        "</span>&nbsp;<span style='color:#005268;font-size:20px;font-weight:600;'>"+value+"℃</span>";
                            }
                        }
                    },
                    sub_option:{
                        label:false
                    },
                    crosshair:{
                        enable:true,
                        line_color:'#62bce9'
                    },
                    coordinate:{
                        axis : {
                            width : [0, 0, 2, 0]
                        },
                        background_color:'#ffffff',
                        height:'90%',
                        valid_width:'94%',
                        height : 260,
                        scale2grid:false,
                        grids:{
                            horizontal:{
                                way:'share_alike',
                                value:8
                            }
                        },
                        scale:[{
                            position:'left',
                            start_scale:0,
                            end_scale:40,
                            scale_space:5,
                            listeners:{
                                parseText:function(t,x,y){
                                    return {text:t+"℃"}
                                }
                            }
                        },{
                            position:'bottom',
                            start_scale:1,
                            end_scale:12,
                            parseText:function(t,x,y){
                                return {textY:y+10}
                            },
                            labels:labels
                        }]
                    }
                });
                chart.draw();
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
<jsp:include page="../../common/top.jsp"/>
<jsp:include page="../../common/left.jsp"/>
<jsp:include page="body.jsp"/>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>