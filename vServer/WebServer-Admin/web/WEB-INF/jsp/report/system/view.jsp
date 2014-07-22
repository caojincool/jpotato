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
    <jsp:include page="../../common/_header.jsp"/>

    <script src="jquery/ichart.1.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            var pv=[],ip=[],t;
            //创建随机数据
            for(var i=0;i<61;i++){
                t = Math.floor(Math.random()*(30+((i%12)*5)))+10;
                pv.push(t);
                t = Math.floor(t*0.5);
                t = t-Math.floor((Math.random()*t)/2);
                ip.push(t);
            }

            var data = [
                {
                    name : 'PV',
                    value:pv,
                    color:'#aad0db',
                    line_width:2
                },
                {
                    name : 'IP',
                    value:ip,
                    color:'#f68f70',
                    line_width:2
                }
            ];

            //创建x轴标签文本
            var date = new Date()
            date.setDate(date.getDate()-6);
            var labels = [];
            for(var i=0;i<6;i++){
                date.setDate(date.getDate()+1);
                labels.push(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
            }

            var chart = new iChart.Area2D({
                render : 'canvasDiv',
                data: data,
                align:'center',
                title : '网站访问情况',
                subtitle : '平均每个人访问2-3个页面(访问量单位：万)',
                footnote : '数据来源：模拟数据',
                width : 1100,
                height : 500,
                background_color:'#FEFEFE',
                tip:{
                    enable:true,
                    shadow:true,
                    move_duration:400,
                    border:{
                        enable:true,
                        radius : 5,
                        width:2,
                        color:'#3f8695'
                    },
                    listeners:{
                        //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
                        parseText:function(tip,name,value,text,i){
                            return name+"访问量:"+value+"万";
                        }
                    }
                },
                tipMocker:function(tips,i){
                    return "<div style='font-weight:600'>"+
                            labels[Math.floor(i/12)]+" "+//日期
                            (((i%12)*2<10?"0":"")+(i%12)*2)+":00"+//时间
                            "</div>"+tips.join("<br/>");
                },
                legend : {
                    enable : true,
                    row:1,//设置在一行上显示，与column配合使用
                    column : 'max',
                    valign:'top',
                    sign:'bar',
                    background_color:null,//设置透明背景
                    offsetx:-80,//设置x轴偏移，满足位置需要
                    border : true
                },
                crosshair:{
                    enable:true,
                    line_color:'#3f8695',
                    line_width:2
                },
                sub_option : {
                    label:false,
                    point_size:10
                },
                coordinate:{
                    width:640,
                    height:240,
                    axis:{
                        color:'#dcdcdc',
                        width:1
                    },
                    scale:[{
                        position:'left',
                        start_scale:0,
                        end_scale:100,
                        scale_space:10,
                        scale_size:2,
                        scale_color:'#9f9f9f'
                    },{
                        position:'bottom',
                        labels:labels
                    }]
                }
            });

            //开始画图
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