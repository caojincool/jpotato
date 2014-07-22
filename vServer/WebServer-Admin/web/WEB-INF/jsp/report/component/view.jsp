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
        $(function(){
            var data = [
                {name : '网页',value : 35.75,color:'#bc6666'},
                {name : '程序',value : 29.84,color:'#cbab4f'},
                {name : '脚本',value : 24.88,color:'#76a871'},
                {name : '网页脚本',value : 6.77,color:'#9f7961'},
                {name : '全局脚本',value : 2.02,color:'#2ba5a4'},
                {name : '5代表组',value : 2.02,color:'#2ba5a4'},
                {name : '5代表',value : 2.02,color:'#2ba5a4'},
                {name : '数据库源',value : 2.02,color:'#2ba5a4'},
                {name : '图片',value : 0.73,color:'#6f83a5'}
            ];

            new iChart.Column3D({
                render : 'canvasDiv',
                data: data,
                title : "组件使用情况",
                width : 1100,
                height : 500,
                align:'left',
                offsetx:50,
                legend : {
                    enable : true
                },
                sub_option:{
                    label:{
                        color:'#111111'
                    }
                },
                coordinate:{
                    width:1000,
                    scale:[{
                        position:'left',
                        start_scale:0,
                        end_scale:40,
                        scale_space:8,
                        listeners:{
                            parseText:function(t,x,y){
                                return {text:t+"%"}
                            }
                        }
                    }]
                }
            }).draw();
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