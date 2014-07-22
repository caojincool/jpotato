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
                {name : '请假流程',value : 0.73,color:'#6f83a5'},
                {name : '借款流程',value : 30.75,color:'#a5c2d5'},
                {name : '报销流程',value : 20.75,color:'#a5c2d5'},
                {name : '项目上线流程',value : 10.75,color:'#a5c2d5'},
                {name : '借车流程',value : 9.75,color:'#a5c2d5'},
                {name : '项目下线流程',value : 1.75,color:'#a5c2d5'},
                {name : '其他',value : 1.75,color:'#a5c2d5'}
            ];

            new iChart.Pie2D({
                render : 'canvasDiv',
                data: data,
                title : '流程使用统计',
                legend : {
                    enable : true
                },
                sub_option : {
                    label : {
                        background_color:null,
                        sign:false,//设置禁用label的小图标
                        padding:'0 4',
                        border:{
                            enable:false,
                            color:'#666666'
                        },
                        fontsize:11,
                        fontweight:600,
                        color : '#4572a7'
                    },
                    border : {
                        width : 2,
                        color : '#ffffff'
                    }
                },
                animation:true,
                showpercent:true,
                decimalsnum:2,
                width : 1100,
                height : 500,
                radius:140
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