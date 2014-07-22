
Ext.define("Custom.TaskWin", {
    extend: "Ext.window.Window",
    title: '创建或者编辑计划',
    closeAction: 'hide',
    height: 450,
    width: 700,
    layout: 'fit',
    resizable: true,
    modal: true,
    myForm:null,
    dataSrc:null,
    initComponent: function () {//构造函数
        var mes = this;
        this.myForm = Ext.widget('form', {
            layout: 'border',
            border: false,
            bodyPadding: 10,
            items:[
                {
                    region : 'north',
                    height:50,
                    defaultType:'datefield',
                    bodyPadding:'5',
                    layout:'fit',
                    align:"right",
                    margins: '0 2 2 2',
                    items: [
                        {
                            id:'groups',
                            xtype : 'radiogroup',
                            layout:'absolute',
                            bodyPadding:'80',
                            items : [
                                {
                                    x : '25%',      //横坐标为距父容器宽度10%的位置
                                    y : 10,        //纵坐标为距父容器上边缘10像素的位置
                                    boxLabel : '普通人员选择',
                                    name : 'group',
                                    inputValue : '0',
                                    checked:mes.dataSrc == null,
                                    fix:1/2
                                }, {
                                    x : '45%',      //横坐标为距父容器宽度10%的位置
                                    y : 10,        //纵坐标为距父容器上边缘10像素的位置
                                    boxLabel : '高级人员选择',
                                    name : 'group',
                                    inputValue : '1',
                                    fix:1/2
                                } ],

                            listeners :{
                                'change':function(){
                                    var obj = Ext.getCmp("groups").items.items;
                                    for(var i in obj){
                                        if(obj[i].checked){
                                            var cardPanel=Ext.getCmp("cardPanel1");
                                            cardPanel.getLayout().setActiveItem("card1-"+obj[i].inputValue);
                                        }
                                    }
                                }
                            }
                        }]
                },

                {
                    region : 'center',
                    layout: 'card',
                    border:false,
                    xtype:"panel",
                    id:'cardPanel1',
                    margins: '2 2 0 2',
                    items:[
                        {
                            id: 'card1-0',
                            layout:'border',
                            items:[
                                {
                                    region : 'west',
                                    xtype:"panel",
                                    margins: '5 2 5 2',
                                    items:[
                                        {

                                            xtype : 'radiogroup',

                                            layout:'vbox',
                                            bodyPadding:'80',
                                            id:'toppings',

                                            items : [
                                                {
                                                    boxLabel : '一次',
                                                    name : 'performRegular',
                                                    inputValue : '0',
                                                    checked:mes.dataSrc == null,
                                                    fix:1/4
                                                }, {
                                                    boxLabel : '天',
                                                    name : 'performRegular',
                                                    inputValue : '1',
                                                    fix:1/4
                                                }, {
                                                    boxLabel : '周',
                                                    name : 'performRegular',
                                                    inputValue : '2',
                                                    fix:1/4
                                                },{
                                                    boxLabel : '月',
                                                    name : 'performRegular',
                                                    inputValue : '3',
                                                    fix:1/4
                                                } ],

                                            listeners :{
                                                'change':function(){
                                                    var obj = Ext.getCmp("toppings").items.items;
                                                    for(var i in obj){
                                                        if(obj[i].checked){
                                                            var cardPanel=Ext.getCmp("cardPanel");
                                                            cardPanel.getLayout().setActiveItem("card-"+obj[i].inputValue);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    ]
                                },
                                {
                                    region : 'center',
                                    layout: 'border',
                                    border:false,
                                    margins: '5 2 5 2',
                                    items:[
                                        {
                                            region : 'north',
                                            height:50,
                                            defaultType:'datefield',
                                            bodyPadding:'5',
                                            layout:'absolute',
                                            margins: '0 2 2 2',
                                            items: [
                                                {
                                                    x : '15%',      //横坐标为距父容器宽度10%的位置
                                                    y : 10,        //纵坐标为距父容器上边缘10像素的位置
                                                    fieldLabel:'执行时间',
                                                    name: 'time',
                                                    xtype : 'timefield',
                                                    allowBlank:false,
                                                    blankText:'开始时间不允许为空',
                                                    format:'H:i'
                                                }]
                                        },
                                        {
                                            region : 'center',
                                            layout: 'card',
                                            border:false,
                                            xtype:"panel",
                                            id:'cardPanel',
                                            margins: '2 2 0 2',
                                            items:[
                                                {
                                                    id: 'card-0',
                                                    layout:'absolute',
                                                    items:[
                                                        {
                                                            x : '15%',      //横坐标为距父容器宽度10%的位置
                                                            y : 10,        //纵坐标为距父容器上边缘10像素的位置
                                                            fieldLabel: '执行日期',
                                                            name: 'date',
                                                            xtype : 'datefield',
                                                            allowBlank:false,
                                                            blankText:'执行日期不允许为空',
                                                            format:'Y-m-d'
                                                        }
                                                    ]
                                                },
                                                {
                                                    id: 'card-1',
                                                    layout:'absolute',
                                                    items:[
                                                        {
                                                            x : '15%',      //横坐标为距父容器宽度10%的位置
                                                            y : 10,        //纵坐标为距父容器上边缘10像素的位置
                                                            xtype: "numberfield",
                                                            name: "walk",
                                                            width:340,
                                                            labelWidth:150,
                                                            fieldLabel: "间隔（单位：分钟）",
                                                            minValue: 1
                                                        }
                                                    ]
                                                },
                                                {
                                                    id: 'card-2',
                                                    bodyPadding:'25',
                                                    layout:{
                                                        type : 'hbox'
                                                    },
                                                    items:[
                                                        {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期一",
                                                            inputValue:1
                                                        }, {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期二",
                                                            inputValue:2
                                                        },
                                                        {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期三",
                                                            inputValue:3
                                                        }, {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期四",
                                                            inputValue:4
                                                        },
                                                        {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期五",
                                                            inputValue:5
                                                        }, {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期六",
                                                            inputValue:6
                                                        }, {
                                                            xtype: "checkboxfield",
                                                            name: "week",
                                                            boxLabel: "星期日",
                                                            inputValue:7
                                                        }
                                                    ]

                                                },
                                                {
                                                    id: 'card-3',
                                                    layout: {
                                                        type: 'vbox',
                                                        align: 'stretch'
                                                    },
                                                    bodyPadding: 10,

                                                    fieldDefaults: {
                                                        labelAlign: 'top',
                                                        labelWidth: 100,
                                                        labelStyle: 'font-weight:bold'
                                                    },
                                                    items:[
                                                        Ext.create('Ext.form.ComboBox', {
                                                            id:'month',
                                                            fieldLabel: '月',
                                                            store: states,
                                                            queryMode: 'local',
                                                            valueField: 'abbr',
                                                            multiSelect:true,
                                                            width :'200',
                                                            tpl: Ext.create('Ext.XTemplate',
                                                                '<tpl for=".">',
                                                                '<div class="x-boundlist-item">' +
                                                                    '{name}' +
                                                                    '</div>',
                                                                '</tpl>'
                                                            ),
                                                            displayTpl: Ext.create('Ext.XTemplate',
                                                                '<tpl for=".">',
                                                                '{name},',
                                                                '</tpl>'
                                                            )
                                                        }),
                                                        {
                                                            xtype: 'fieldcontainer',
                                                            labelStyle: 'font-weight:bold;padding:0',
                                                            layout: 'hbox',
                                                            items: [{
                                                                id:'days',
                                                                flex: 1,
                                                                name: 'dayAndWeeks',
                                                                xtype:'radiofield',
                                                                checked:true,
                                                                boxLabel: '天：' ,
                                                                inputValue: 1
                                                            },   Ext.create('Ext.form.ComboBox', {
                                                                id:'day',
                                                                flex: 3,
                                                                store: states1,
                                                                queryMode: 'local',
                                                                valueField: 'abbr',
                                                                multiSelect:true,
                                                                width :'200',
                                                                // Template for the dropdown menu.
                                                                // Note the use of "x-boundlist-item" class,
                                                                // this is required to make the items selectable.
                                                                tpl: Ext.create('Ext.XTemplate',
                                                                    '<tpl for=".">',
                                                                    '<div class="x-boundlist-item" style="float:left;">' +
                                                                        '<input id="a{id}" type="checkbox" name="dayCheck" value="{abbr}">{name}' +
                                                                        '</div>',
                                                                    '</tpl>'
                                                                ),
                                                                displayTpl: Ext.create('Ext.XTemplate',
                                                                    '<tpl for=".">',
                                                                    '',
                                                                    '</tpl>'
                                                                ),
                                                                listeners:{
                                                                    blur:function(){
                                                                        var items=document.getElementsByName("dayCheck");
                                                                        var str='';
                                                                        for (var i=0;i<items.length;i++)
                                                                        {
                                                                            if(items[i].checked){
                                                                                str+=items[i].value+",";
                                                                            }
                                                                        }
                                                                        if(str.length>0){
                                                                            str=str.substr(0,str.length-1);
                                                                            str=handlerNumber(str);
                                                                        }
                                                                        //alert(str.split('(\\d)\1{2}'));
                                                                        Ext.getCmp('day').setRawValue(str);
                                                                    },
                                                                    expand:function(){
                                                                        var selVal=Ext.getCmp('day').getRawValue();
                                                                        var arr=selVal.split(',');
                                                                        var arr2=[];
                                                                        for(var i=0;i<arr.length;i++){
                                                                            if(arr[i].indexOf('-')>0){
                                                                                var arr3=arr[i].split('-');
                                                                                for(var j=arr3[0];j<=arr3[1];j++){
                                                                                    arr2.push(j);
                                                                                }
                                                                            }else{
                                                                                arr2.push(arr[i]);
                                                                            }
                                                                        }
                                                                        var items=document.getElementsByName("dayCheck");
                                                                        for (var i=0;i<items.length;i++)
                                                                        {
                                                                            items[i].checked=isExist(arr2,items[i].value)
                                                                        }
                                                                    }
                                                                }
                                                            })]
                                                        },
                                                        {
                                                            xtype: 'fieldcontainer',
                                                            labelStyle: 'font-weight:bold;padding:0',
                                                            layout: 'hbox',
                                                            items: [
                                                                {
                                                                    flex: 1,
                                                                    id:'weaks',
                                                                    name: 'dayAndWeeks',
                                                                    xtype:'radiofield',
                                                                    boxLabel: '在：',
                                                                    inputValue: 2,
                                                                    listeners:{
                                                                        'change' :function(re,newVal,oldVal,opts){
                                                                            var zr=Ext.getCmp("zr");
                                                                            var xq=Ext.getCmp("xq");
                                                                            var day=Ext.getCmp("day");
                                                                            if(newVal){
                                                                                day.setDisabled(true);
                                                                                zr.setDisabled(false);
                                                                                xq.setDisabled(false);
                                                                            }else{
                                                                                day.setDisabled(false);
                                                                                zr.setDisabled(true);
                                                                                xq.setDisabled(true);
                                                                            }
                                                                        }
                                                                    }
                                                                },
                                                                Ext.create('Ext.form.ComboBox', {
                                                                    id:'zr',
                                                                    flex: 2,
                                                                    store: states2,
                                                                    queryMode: 'local',
                                                                    valueField: 'abbr',
                                                                    multiSelect:true,
                                                                    width :'200',
                                                                    disabled:true,
                                                                    // Template for the dropdown menu.
                                                                    // Note the use of "x-boundlist-item" class,
                                                                    // this is required to make the items selectable.
                                                                    tpl: Ext.create('Ext.XTemplate',
                                                                        '<tpl for=".">',
                                                                        '<div class="x-boundlist-item" >' +
                                                                            '{name}' +
                                                                            '</div>',
                                                                        '</tpl>'
                                                                    ),
                                                                    // template for the content inside text field
                                                                    displayTpl: Ext.create('Ext.XTemplate',
                                                                        '<tpl for=".">',
                                                                        '{name},',
                                                                        '</tpl>'
                                                                    )
                                                                }),
                                                                Ext.create('Ext.form.ComboBox', {
                                                                    id:'xq',
                                                                    flex: 3,
                                                                    store: states3,
                                                                    queryMode: 'local',
                                                                    valueField: 'abbr',
                                                                    multiSelect:true,
                                                                    disabled:true,
                                                                    width :'200',
                                                                    // Template for the dropdown menu.
                                                                    // Note the use of "x-boundlist-item" class,
                                                                    // this is required to make the items selectable.
                                                                    tpl: Ext.create('Ext.XTemplate',
                                                                        '<tpl for=".">',
                                                                        '<div class="x-boundlist-item" >' +
                                                                            '{name}' +
                                                                            '</div>',
                                                                        '</tpl>'
                                                                    ),
                                                                    // template for the content inside text field
                                                                    displayTpl: Ext.create('Ext.XTemplate',
                                                                        '<tpl for=".">',
                                                                        '{name},',
                                                                        '</tpl>'
                                                                    )
                                                                })
                                                            ]
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            id: 'card1-1',
                            layout:'vbox',
                            bodyPadding:15,
                            items:[
                                {
                                    id:'',
                                    xtype: "textfield",
                                    name: "cronExpression",
                                    width:340,
                                    labelWidth:150,
                                    fieldLabel: "时间表达式"
                                },
                                {
                                    xtype:'displayfield',
                                    height:200,
                                    width:500,
                                    value:'格式说明: <!-- s m h d m w(?) y(?) -->,分别对应: 秒>分>小时>日>月>周>年\n' +
                                        '1、(*)：可用在所有字段中，表示对应时间域的每一个时刻' +
                                        '2、（?）：该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于点位符' +
                                        '3、(-)：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12' +
                                        '4、(,)：表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五' +
                                        '5、x/y表达一个等步长序列，x为起始值，y为增量步长值。'
                                }
                            ]
                        }
                    ]
                },
                {
                    region : 'south',
                    xtype:"panel",
                    height:60,
                    margins: '5 2 5 2',
                    layout:{
                        type:'fit'
                    },
                    border:false,
                    items:[

                        {
                            xtype:'textarea',
                            labelAlign:'right',
                            labelWidth:30,
                            name:'remark',
                            fieldLabel:'说明'
                        }
                    ]
                }

            ]
        }
        );
        mes.items = [
            this.myForm
        ]; //将grid添加窗体的items中
        mes.callParent(arguments);
    }
});
//创建计划
function createPlanWin() {
    var functionWin=Ext.create('Custom.TaskWin',{
        buttons: [
            {
            text: '确定',
            iconCls: 'ok-16',
            handler: function () {
                var param = functionWin.myForm.getForm().getValues();
                var row={};
                var group=parseInt(param.group);
                row.group=group;
                switch (group){
                    case 0:
                        var enddt=param.time;
                        if (enddt == undefined) {
                            Ext.Msg.alert('系统提示', '开始时间必选！');
                            return;
                        }
                        var arrHours=enddt.split(":");
                        var cronExpressions = '';
                        var performRegular=parseInt(param.performRegular);
                        var minute=parseInt(arrHours[1]);
                        var hour=parseInt(arrHours[0]);
                        row.time=enddt;
                        row.performRegular=performRegular;
                        switch (performRegular){
                            case 0:
                                var startdt=param.date;
                                row.date=startdt;
                                var arrYears=startdt.split('-');
                                if (startdt == undefined) {
                                    Ext.Msg.alert('系统提示', '开始日期必选！');
                                    return;
                                }
                                cronExpressions='0 '+ minute+" "+hour+" "+ arrYears[2] +" "+ arrYears[1] +" ? "+ arrYears[0]+" *";
                                break;
                            case 1:
                                var walk = param['walk'];
                                walk=walk.substr(0,walk.length);
                                row.walk=walk;
                                cronExpressions="0 0/"+walk+ " * * * ?";
                                break;
                            case 2:
                                var week = param['week'];
                                row.week=week;
                                if(week instanceof Array){
                                    week=week.join(",");
                                }else{
                                    if(week == undefined){
                                        Ext.Msg.alert('系统提示', '请选择执行的星期！');
                                        return;
                                    }
                                }
                                cronExpressions='0 '+ minute+" "+hour+" ? * "+week;
                                break;
                            case 3:
                                var month = param['month-inputEl'];
                                row.month=month;
                                if(month instanceof Array){
                                    month=month.join(",");
                                    if(month == ""){
                                        Ext.Msg.alert('系统提示', '请选择执行的月份！');
                                        return;
                                    }
                                }
                                row.dayAndWeeks=param['dayAndWeeks'];
                                if(param['dayAndWeeks'] == 1){
                                    var day = param['day-inputEl'];
                                    if(day == ""){
                                        Ext.Msg.alert('系统提示', '请选择那几天执行！');
                                        return;
                                    }
                                    day=day.substr(0,day.length-1);
                                    row.day=day;
                                    cronExpressions='0 '+ minute+" "+hour+" "+day +" "+month+" ? ";
                                }else{
                                    var zr=param['zr-inputEl'];
                                    row.zr=zr;
                                    if(zr instanceof Array){
                                        zr=zr.join(",");
                                        if(zr == ""){
                                            Ext.Msg.alert('系统提示', '请选择那几个星期执行！');
                                            return;
                                        }
                                    }
                                    var xq=param['xq-inputEl'];
                                    row.xq=xq;
                                    if(xq instanceof Array){
                                        xq=xq.join(",");
                                        if(xq == ""){
                                            Ext.Msg.alert('系统提示', '请选择那星期几执行！');
                                            return;
                                        }
                                    }
                                    cronExpressions='0 '+ minute+" "+hour+" ? "+month+" "+xq+"#"+zr ;
                                }
                        }
                        row.cronExpression=cronExpressions;
                        break;

                    case 1:
                        row.cronExpression=param.cronExpression;
                }

                row.remark=param.remark;
                taskTrigersStore.add(row);
                functionWin.close();
                functionWin.destroy();
            }
        },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                    functionWin.destroy();

                }
            }]
    }).show();
}
function updatePlanWin(rec) {
    var functionWin=Ext.create('Custom.TaskWin',{
        dataSrc:rec,
        buttons: [
            {
                text: '确定',
                iconCls: 'ok-16',
                handler: function () {
                    var param = functionWin.myForm.getForm().getValues();
                    var row={};
                    var group=parseInt(param.group);
                    row.group=group;
                    switch (group){
                        case 0:
                            var enddt=param.time;
                            if (enddt == undefined) {
                                Ext.Msg.alert('系统提示', '开始时间必选！');
                                return;
                            }
                            var arrHours=enddt.split(":");
                            var cronExpressions = '';
                            var performRegular=parseInt(param.performRegular);
                            var minute=parseInt(arrHours[1]);
                            var hour=parseInt(arrHours[0]);
                            row.time=enddt;
                            row.performRegular=performRegular;
                            switch (performRegular){
                                case 0:
                                    var startdt=param.date;
                                    row.date=startdt;
                                    var arrYears=startdt.split('-');
                                    if (startdt == undefined) {
                                        Ext.Msg.alert('系统提示', '开始日期必选！');
                                        return;
                                    }
                                    cronExpressions='0 '+ minute+" "+hour+" "+ arrYears[2] +" "+ arrYears[1] +" ? "+ arrYears[0]+" *";
                                    break;
                                case 1:
                                    var walk = param['walk'];
                                    walk=walk.substr(0,walk.length);
                                    row.walk=walk;
                                    cronExpressions="0 0/"+walk+ " * * * ?";
                                    break;
                                case 2:
                                    var week = param['week'];
                                    row.week=week;
                                    if(week instanceof Array){
                                        week=week.join(",");
                                    }else{
                                        if(week == undefined){
                                            Ext.Msg.alert('系统提示', '请选择执行的星期！');
                                            return;
                                        }
                                    }
                                    cronExpressions='0 '+ minute+" "+hour+" ? * "+week;
                                    break;
                                case 3:
                                    var month = param['month-inputEl'];
                                    row.month=month;
                                    if(month instanceof Array){
                                        month=month.join(",");
                                        if(month == ""){
                                            Ext.Msg.alert('系统提示', '请选择执行的月份！');
                                            return;
                                        }
                                    }
                                    row.dayAndWeeks=param['dayAndWeeks'];
                                    if(param['dayAndWeeks'] == 1){
                                        var day = param['day-inputEl'];
                                        if(day == ""){
                                            Ext.Msg.alert('系统提示', '请选择那几天执行！');
                                            return;
                                        }
                                        day=day.substr(0,day.length-1);
                                        row.day=day;
                                        cronExpressions='0 '+ minute+" "+hour+" "+day +" "+month+" ? ";
                                    }else{
                                        var zr=param['zr-inputEl'];
                                        row.zr=zr;
                                        if(zr instanceof Array){
                                            zr=zr.join(",");
                                            if(zr == ""){
                                                Ext.Msg.alert('系统提示', '请选择那几个星期执行！');
                                                return;
                                            }
                                        }
                                        var xq=param['xq-inputEl'];
                                        row.xq=xq;
                                        if(xq instanceof Array){
                                            xq=xq.join(",");
                                            if(xq == ""){
                                                Ext.Msg.alert('系统提示', '请选择那星期几执行！');
                                                return;
                                            }
                                        }
                                        cronExpressions='0 '+ minute+" "+hour+" ? "+month+" "+xq+"#"+zr ;
                                    }
                            }
                            row.cronExpression=cronExpressions;
                            break;

                        case 1:
                            row.cronExpression=param.cronExpression;
                    }

                    row.remark=param.remark;
                    if(rec.data != undefined){
                        taskTrigersStore.remove(rec);
                    }
                    taskTrigersStore.add(row);
                    functionWin.close();
                    functionWin.destroy();
                }
            },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                    functionWin.destroy();

                }
            }]
    }).show();
    var group=Ext.getCmp("cardPanel1");
    group.getLayout().setActiveItem("card1-"+rec.data.group);

    if(rec.data.group == 0 ){
        var cardPanel=Ext.getCmp("cardPanel");
        cardPanel.getLayout().setActiveItem("card-"+rec.data.performRegular);
        functionWin.myForm.getForm().loadRecord(rec );
        Ext.getCmp('day').setRawValue(rec.data.day);
    }else{
        functionWin.myForm.getForm().loadRecord(rec );
    }

}
function showPlanWin(rec) {
    var functionWin=Ext.create('Custom.TaskWin',{
        dataSrc:rec,
        buttons: [
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                    functionWin.destroy();

                }
            }]
    }).show();
    var group=Ext.getCmp("cardPanel1");
    group.getLayout().setActiveItem("card1-"+rec.data.group);
    if(rec.data.group == 0 ){
        var cardPanel=Ext.getCmp("cardPanel");
        cardPanel.getLayout().setActiveItem("card-"+rec.data.performRegular);
        functionWin.myForm.getForm().loadRecord(rec );
        Ext.getCmp('day').setRawValue(rec.data.day);
    }else{
        functionWin.myForm.getForm().loadRecord(rec );
    }
}
var states = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"1月"},
        {"abbr":"2", "name":"2月"},
        {"abbr":"3", "name":"3月"},
        {"abbr":"4", "name":"4月"},
        {"abbr":"5", "name":"5月"},
        {"abbr":"6", "name":"6月"},
        {"abbr":"7", "name":"7月"},
        {"abbr":"8", "name":"8月"},
        {"abbr":"9", "name":"9月"},
        {"abbr":"10", "name":"10月"},
        {"abbr":"11", "name":"11月"},
        {"abbr":"12", "name":"12月"}
    ]
});
var states1 = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"1"},
        {"abbr":"2", "name":"2"},
        {"abbr":"3", "name":"3"},
        {"abbr":"4", "name":"4"},
        {"abbr":"5", "name":"5"},
        {"abbr":"6", "name":"6"},
        {"abbr":"7", "name":"7"},
        {"abbr":"8", "name":"8"},
        {"abbr":"9", "name":"9"},
        {"abbr":"10", "name":"10"},
        {"abbr":"11", "name":"11"},
        {"abbr":"12", "name":"12"},
        {"abbr":"13", "name":"13"},
        {"abbr":"14", "name":"14"},
        {"abbr":"15", "name":"15"},
        {"abbr":"16", "name":"16"},
        {"abbr":"17", "name":"17"},
        {"abbr":"18", "name":"18"},
        {"abbr":"19", "name":"19"},
        {"abbr":"20", "name":"20"},
        {"abbr":"21", "name":"21"},
        {"abbr":"22", "name":"22"},
        {"abbr":"23", "name":"23"},
        {"abbr":"24", "name":"24"},
        {"abbr":"25", "name":"25"},
        {"abbr":"26", "name":"26"},
        {"abbr":"27", "name":"27"},
        {"abbr":"28", "name":"28"},
        {"abbr":"29", "name":"29"},
        {"abbr":"30", "name":"30"},
        {"abbr":"31", "name":"31"},
        {"abbr":"L", "name":"最后一天"}
    ]
});
var states2 = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"第一周"},
        {"abbr":"2", "name":"第二周"},
        {"abbr":"3", "name":"第三周"},
        {"abbr":"4", "name":"第四周"},
        {"abbr":"5", "name":"最后一周"}
    ]
});
var states3 = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"周一"},
        {"abbr":"2", "name":"周二"},
        {"abbr":"3", "name":"周三"},
        {"abbr":"4", "name":"周四"},
        {"abbr":"5", "name":"周五"},
        {"abbr":"6", "name":"周六"},
        {"abbr":"7", "name":"周日"}
    ]
});
var states4 = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"1", "name":"30秒"},
        {"abbr":"2", "name":"1分钟"},
        {"abbr":"3", "name":"30分钟"},
        {"abbr":"4", "name":"1个小时"},
        {"abbr":"5", "name":"8个小时"},
        {"abbr":"6", "name":"1天"}
    ]
});
var result='';
function handlerNumber(str){

    var arr=str.split(',');
    result='';
    numbers(arr);
    return result;
}
function numbers(arr){
    var flag=0;
    if(arr.length == 0){
        return ;
    }
    for(var i=0;arr.length-1;i++){
        if(parseInt(arr[i])+1 == parseInt(arr[i+1])){
            flag=i+1;
            continue;
        }else{
            break;
        }
    }
    if(flag>0){
        result+=arr[0]+"-"+arr[flag]+",";
        numbers(arr.slice(flag+1,arr.length));
    }else{
        result+=arr[0]+",";
        numbers(arr.slice(flag+1,arr.length));
    }
}
function isExist(arr,val){
    for(var j = 0;j<arr.length;j++){
        if(arr[j] == val){
            return true;
        }else{
            continue;
        }
    }
    return false;
}
