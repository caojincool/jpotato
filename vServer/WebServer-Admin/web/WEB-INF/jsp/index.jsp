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
<jsp:include page="common/_header.jsp"/>
<script src="jquery/ichart.1.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
    var dataMain = [
        {name: '网页', value: 35.75, color: '#bc6666'},
        {name: '程序', value: 29.84, color: '#cbab4f'},
        {name: '脚本', value: 24.88, color: '#76a871'},
        {name: '网页脚本', value: 6.77, color: '#9f7961'},
        {name: '全局脚本', value: 2.02, color: '#2ba5a4'},
        {name: '5代表组', value: 2.02, color: '#2ba5a4'},
        {name: '5代表', value: 2.02, color: '#2ba5a4'},
        {name: '数据库源', value: 2.02, color: '#2ba5a4'},
        {name: '图片', value: 0.73, color: '#6f83a5'}
    ];

    new iChart.Column3D({
        render: 'mainDiv',
        data: dataMain,
        animation: true,
        animation_duration: 2000,
        title: "组件使用情况",
        width: 490,
        height: 250,
        align: 'left',
        offsetx: 50,
        gradient: true,
        sub_option: {
            label: {
                color: '#111111'
            }
        },
        coordinate: {
            scale: [
                {
                    position: 'left',
                    start_scale: 0,
                    end_scale: 40,
                    scale_space: 8,
                    listeners: {
                        parseText: function (t, x, y) {
                            return {text: t + "%"}
                        }
                    }
                }
            ]
        }
    }).draw();
});
$(function () {
    var dataFlow = [
        {name: '请假流程', value: 0.73, color: '#4572a7'},
        {name: '借款流程', value: 30.75, color: '#aa4643'},
        {name: '报销流程', value: 20.75, color: '#89a54e'},
        {name: '项目上线流程', value: 10.75, color: '#80699b'},
        {name: '借车流程', value: 9.75, color: '#a5c2d5'},
        {name: '项目下线流程', value: 1.75, color: '#8f588a'},
        {name: '其他', value: 1.75, color: '#7e4779'}
    ];

    var chart = new iChart.Pie3D({
        render : 'flowDiv',
        data: dataFlow,
        title : '流程使用统计',
        footnote : {
            text : 'www.lemsun.com',
            color : '#486c8f',
            padding : '0 38'
        },
        sub_option : {
            mini_label_threshold_angle : 40,//迷你label的阀值,单位:角度
            mini_label:{//迷你label配置项
                fontweight:600,
                color : '#ffffff'
            },
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
            },
            listeners:{
                parseText:function(d, t){
                    return d.get('value')+"%";//自定义label文本
                }
            }
        },
        legend:{
            enable:true,
            padding:0,
            offsetx:20,
            offsety:50,
            color:'#3e576f',
            sign_size:20,//小图标大小
            line_height:28,//设置行高
            sign_space:10,//小图标与文本间距
            border:false,
            align:'left',
            background_color : null//透明背景
        },
        animation : true,//开启过渡动画
        animation_duration:1800,//800ms完成动画
        shadow : true,
        shadow_blur : 6,
        shadow_color : '#aaaaaa',
        background_color:'#f1f1f1',
        align:'rihgt',//右对齐
        offsetx:20,//设置向x轴负方向偏移位置60px
        offsety:20,//设置向x轴负方向偏移位置60px
        offset_angle:-90,//逆时针偏移120度
        width : 490,
        height : 250
    });
    //利用自定义组件构造右侧说明文本
//    chart.plugin(new iChart.Custom({
//        drawFn:function(){
//            //在右侧的位置，渲染说明文字
//            chart.target.textAlign('start')
//                    .textBaseline('top')
//                    .textFont('600 20px Verdana')
//                    .fillText('Market Fragmentation:\nTop Mobile\nOperating Systems',120,80,false,'#be5985',false,24)
//                    .textFont('600 12px Verdana')
//                    .fillText('Source:ComScore,2012',120,160,false,'#999999');
//        }
//    }));
    chart.draw();

//    new iChart.Pie2D({
//        render: 'flowDiv',
//        data: dataFlow,
//        title: '流程使用统计',
//        sub_option: {
//            label: {
//                background_color: null,
//                sign: false,//设置禁用label的小图标
//                padding: '0 4',
//                border: {
//                    enable: false,
//                    color: '#666666'
//                },
//                fontsize: 11,
//                fontweight: 600,
//                color: '#4572a7'
//            },
//            border: {
//                width: 2,
//                color: '#ffffff'
//            }
//        },
//        animation: true,
//        showpercent: true,
//        decimalsnum: 2,
//        width: 500,
//        height: 250,
//        radius: 140
//    }).draw();
});
var dataTask = [
    {
        name: '统计用户报表任务',
        value: [4, 16, 18, 20, 32, 36, 38, 38, 36, 26, 20, 14],
        color: '#dad81f'
    },
    {
        name: '发邮件',
        value: [2, 12, 14, 20, 28, 32, 34, 36, 33, 24, 14, 4],
        color: '#1f7e92'
    },
    {
        name: '删除垃圾文件',
        value: [1, 12, 18, 20, 28, 34, 36, 34, 31, 27, 24, 6],
        color: '#76a871'
    },
    {
        name: '统计组件使用情况',
        value: [3, 13, 14, 20, 28, 32, 34, 36, 30, 24, 14, 4],
        color: '#6f83a5'
    }
];
var labelsTask = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
$(function () {
    var chart = new iChart.Area2D({
        render: 'taskDiv',
        data: dataTask,
        title: '任务执行时间分布图',
        width: 490,
        height: 250,
        area_opacity: 0.15,
        tip: {
            enable: true,
            listeners: {
                //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
                parseText: function (tip, name, value, text, i) {
                    return "<span style='color:#005268;font-size:11px;font-weight:400;'>" + name +
                            "</span>&nbsp;<span style='color:#005268;font-size:20px;font-weight:400;'>" + value + "</span>";
                }
            }
        },
        sub_option: {
            label: false
        },
        crosshair: {
            enable: true,
            line_color: '#62bce9'
        },
        coordinate: {
            axis: {
                width: [0, 0, 2, 0]
            },
            background_color: '#ffffff',
            valid_width: '94%',
            scale2grid: false,
            grids: {
                horizontal: {
                    way: 'share_alike',
                    value: 8
                }
            },
            scale: [
                {
                    position: 'left',
                    start_scale: 0,
                    end_scale: 40,
                    scale_space: 5,
                    listeners: {
                        parseText: function (t, x, y) {
                            return {text: t}
                        }
                    }
                },
                {
                    position: 'bottom',
                    start_scale: 1,
                    end_scale: 12,
                    parseText: function (t, x, y) {
                        return {textY: y + 10}
                    },
                    labels: labelsTask
                }
            ]
        }
    });
    chart.draw();
});
$(function () {
    var data = [
        {name: '3天未登录', value: 35.75, color: '#a5c2d5'},
        {name: '5天未登录', value: 29.84, color: '#cbab4f'},
        {name: '10天未登录', value: 24.88, color: '#76a871'},
        {name: '1月未登录', value: 6.77, color: '#9f7961'},
        {name: '3月以上未登录', value: 2.02, color: '#a56f8f'}
    ];

    new iChart.Column2D({
        render: 'userDiv',
        data: data,
        title: '2013年10月份用户登录情况统计',
        showpercent: true,
        decimalsnum: 2,
        width: 490,
        height: 250,
        coordinate: {
            background_color: '#fefefe',
            scale: [
                {
                    position: 'left',
                    start_scale: 0,
                    end_scale: 40,
                    scale_space: 8,
                    listeners: {
                        parseText: function (t, x, y) {
                            return {text: t + "%"}
                        }
                    }
                }
            ]
        }
    }).draw();
});
</script>
<style type="text/css">
    .bodygrey {
        background: #EEEEEE
    }
</style>
</head>
<body class="bodygrey">

<div class="headerspace"></div>
<jsp:include page="common/top.jsp"/>
<jsp:include page="body.jsp"/>
<div class="footer">
    <jsp:include page="common/_footer.jsp"/>
</div>

</body>
</html>