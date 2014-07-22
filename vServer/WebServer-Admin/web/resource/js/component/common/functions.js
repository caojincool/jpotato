
//开始参数的类型
var paramTypeStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'cate'],
    data: [
        {id: 1, cate: '区域'},
        {id: 2, cate: '表达式'},
        {id: 3, cate: '字符'},
        {id: 4, cate: '数据源'}
    ]
});
//父组件开始参数模型
Ext.define('startParamsModel', {
    extend: 'Ext.data.Model',
    fields: ['name', 'cate', 'value', 'status', {
        name: 'parent',         //控制父组件禁止删除
        type: 'bool',
        defaultValue: false
    }, 'remark'],
    idProperty: 'name'
});

//获取某个组件的开始参数
var startParamsStore = Ext.create('Ext.data.Store', {
    model: 'startParamsModel',
    autoLoad: false,
    //获取全部的开始参数, 除去没用改变的父级参数. 返回定义的数组
    getAllParam: function () {
        var data = [];

        this.each(function (r) {
            var row = {
                name: r.get('name'),
                value: r.get('value'),
                cate: r.get('cate'),
                parent: r.get('parent')
            };
            data.push(row);
        });
        return data;
    }
});
//初始化数据
if(lr.startParam!=null){
    startParamsStore.loadData(lr.startParam);
}
//加载开始参数
if(lr.startParams)
    startParamsStore.loadData(lr.startParams);
//函数体模型定义
Ext.define('functionStatementModel', {
    extend: 'Ext.data.Model',
    fields: ['name', 'nameCH', 'remark', 'pid','funParams','parentPid'],
    idProperty: 'pid'
});

//函数store
var functionStatementStore = Ext.create('Ext.data.Store', {
    model: 'functionStatementModel',
    proxy: {
        type: 'ajax',
        url: rootPath + 'function/'+lr.pid+'/function/list',
        reader: {
            root: 'entity'
        },
        simpleSortMode: true
    },
    autoLoad: true

});



//函数参数的类型
var functionParamTypeStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'cate'],
    data: [
        {id: 1, cate: '数字'},
        {id: 2, cate: '字符'},
        {id: 3, cate: '对象'}

    ]
});
//函数参数模型
Ext.define('functionParamsModel', {
    extend: 'Ext.data.Model',
    fields: ['name', 'cate', 'defaultValue', 'remark'],
    idProperty: 'name'
});

//获取某个函数的参数
var functionParamsStore = Ext.create('Ext.data.Store', {
    model: 'functionParamsModel',
    autoLoad: false,
    //获取全部的开始参数, 除去没用改变的父级参数. 返回定义的数组
    getAllParam: function () {
        var data = [];

        this.each(function (r) {
            var row = {
                name: r.get('name'),
                defaultValue: r.get('defaultValue'),
                cate: r.get('cate'),
                parent: r.get('remark')
            };
            data.push(row);
        });
        return data;
    }
});

var functionManger={
        title: '函数管理',
        layout: 'fit',
        items: [
        {
            xtype: 'gridpanel',
            margins: '2 2',
            height: 200,
            store: functionStatementStore,
            tbar: [
                {
                    iconCls: 'add-16',
                    text: '新增',
                    handler: function (el) {
                        createFunctionWin(el);
                    }
                }
            ],
            viewConfig: {
                stripeRows: true,
                forceFit: true,
                enableRowBody: true,
                getRowClass: function (record) {
                    return record.get('parent') == true ? 'child-row' : '';
                }
            },
            columns: [
                {
                    text: '函数名',
                    flex: 1,
                    dataIndex: 'name'
                },
                {
                    text: '别名',
                    width: 120,
                    dataIndex: 'nameCH'
                },
                {
                    text: '操作',
                    width: 120,
                    xtype: 'actioncolumn',
                    layout: 'vbox',
                    align: 'center',
                    items: [
                        {
                            tooltip: '查看',
                            iconCls: 'action-column-margin',
                            icon: 'resource/images/icons/eye_16x16.png',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                showFunctionWin(rec);
                            }
                        },
                        {
                            tooltip: '编辑',
                            iconCls: 'action-column-margin',
                            icon: 'ext/images/cog_edit.png',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                updateFunctionWin(rec);
                            }
                        },
                        {
                            tooltip: '删除',
                            itemId: 'btnDel',
                            iconCls: 'action-column-margin',
                            icon: 'resource/images/icons/deletelayer_16x16.png',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                Ext.Msg.confirm('系统提示', '确定删除？', function(btn){
                                    if (btn == 'yes'){
                                        Ext.Ajax.request({
                                            url: rootPath + 'function/'+rec.data.parentPid+'/delete',
                                            params:{
                                                name:rec.data.name
                                            },
                                            method: 'POST',
                                            success: function (r) {
                                                functionStatementStore.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.alert("操作失败！");
                                            }
                                        });
                                    }
                                });

                            }
                        }
                    ]
                }
            ]
        }
    ]
}
//脚本执行编辑区 内容和编辑ID
function executeFunctionEditWin(context,editId) {
    var win = Ext.create('Ext.Window', {
        height: 650,
        width: 850,
        title: '脚本执行编辑区',
        plain: true,
        modal: true,
        layout: 'border',
        bodyPadding: 5,
        items: [

            {
                region: 'center',
                layout: 'fit',
                margins: '2 5 2 2',
                items: [
                    {
                        xtype: 'textareafield',
                        id: 'script3',
                        name: 'content',
                        listeners: {
                            afterrender: function () {
                                window.editor3 =  CodeMirror.fromTextArea(document.getElementById("script3"), {
                                    lineNumbers: true,
                                    mode: "text/javascript",
                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                                });
                                editor3.setValue(context);
                            }
                        }
                    }
                ]
            },
            {
                title: '函数属性区',
                width: 300,
                collapsible: true,
                split: true,
                region: 'east',
                xtype: 'tabpanel',
                items: [
                    {
                        title: '开始参数',
                        layout: 'border',
                        items: [
                            {
                                xtype: 'gridpanel',
                                region: 'center',
                                border: false,
                                height: 200,
                                store: startParamsStore,

                                viewConfig: {
                                    stripeRows: true,
                                    forceFit: true,
                                    enableRowBody: true,
                                    getRowClass: function (record) {
                                        return record.get('parent') == true ? 'child-row' : '';
                                    }
                                },
                                columns: [
                                    {
                                        text: '名称',
                                        width: 80,
                                        dataIndex: 'name'
                                    },
                                    {
                                        text: '类型',
                                        width: 60,
                                        dataIndex: 'cate',
                                        renderer: function (val) {
                                            return  paramTypeStore.data.items[val - 1].data.cate;
                                        }
                                    },
                                    {
                                        text: '参数值',
                                        flex: 1,
                                        dataIndex: 'value',
                                        draggable: false
                                    }
                                ],
                                listeners: {
                                    selectionchange: function (m, selectedRecord) {
                                        if (selectedRecord.length) {
                                            checkedparm = selectedRecord[0].data;


                                        }
                                    }
                                }
                            },
                            {
                                title: '详细信息',
                                xtype: 'panel',
                                height: 200,
                                border: false,
                                region: 'south',
                                defaultType: 'fieldcontainer',
                                flex: 1,
                                layout: {
                                    type: 'vbox',
                                    align: 'left',
                                    defaultMargins: {top: 5, right: 5, bottom: 0, left: 15}
                                },
                                defaults: {
                                    labelWidth: 28,
                                    layout: 'hbox',
                                    width: '100%'
                                },
                                items: [
                                    {
                                        fieldLabel: '名称',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield'
                                        }
                                    },
                                    {
                                        fieldLabel: '类别',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield',

                                            name: 'cate'
                                        }
                                    },
                                    {
                                        fieldLabel: '值',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield',
                                            name: 'value'
                                        }
                                    },
                                    {
                                        fieldLabel: '说明',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            width: 210,
                                            height: 75,
                                            readOnly:true,
                                            xtype: 'textareafield'
                                        }
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        title: '函数列表',
                        layout: 'fit',
                        items: [
                            {
                                xtype: 'gridpanel',
                                border: false,
                                height: 200,
                                store: functionStatementStore,

                                viewConfig: {
                                    stripeRows: true,
                                    forceFit: true,
                                    enableRowBody: true,
                                    getRowClass: function (record) {
                                        return record.get('parent') == true ? 'child-row' : '';
                                    }
                                },
                                columns: [
                                    {
                                        text: '函数名',
                                        width: 120,
                                        dataIndex: 'name'
                                    },
                                    {
                                        text: '别名',
                                        width: 100,
                                        dataIndex: 'nameCH'
                                    },   {
                                        text: '操作',
                                        xtype: 'actioncolumn',
                                        layout: 'vbox',
                                        align: 'center',
                                        width: 75,
                                        items: [
                                            {
                                                tooltip: '查看',
                                                iconCls: 'action-column-margin',
                                                icon: 'resource/images/icons/eye_16x16.png',
                                                handler: function (grid, rowIndex, colIndex) {
                                                    var rec = grid.getStore().getAt(rowIndex);
                                                    showFunctionWin(rec);
                                                }
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }


                ]
            }
        ],
        buttons: [
            {
                text: '确定',
                iconCls: 'ok-16',
                handler: function () {
                    editId.setValue(editor3.getValue());
                    win.close();
                }
            },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    win.close();
                }
            }
        ]
    });

    win.show();
}
/**
 * 自定义函数窗口
 */
Ext.define("Custom.FunctionWin", {
    extend: "Ext.window.Window",
    height: 600,
    width: 800,
    title: '查看函数',
    plain: true,
    modal: true,
    layout: 'border',
    el:null,
    parentPid:null,
    functionName:null,
    paramPanl:null,
    bodyPadding: 5,
    initComponent: function () {//构造函数
        var mes = this;
        if(mes.el != undefined){
            mes.parentPid=mes.el.data.parentPid;
            mes.functionName=mes.el.data.name;
        }
        if(mes.paramPanl==undefined){
            mes.paramPanl= {   title: '函数参数',
                xtype:"panel",
                flex: 1 / 2,
                items:[
                    {
                        xtype: 'gridpanel',
                        border: false,
                        height: 200,
                        store: functionParamsStore,

                        columns: [
                            {
                                text: '参数名',
                                width: 80,
                                dataIndex: 'name'
                            },
                            {
                                text: '类型',
                                flex: 1,
                                dataIndex: 'cate',
                                draggable: false,
                                renderer: function (val) {
                                    return  functionParamTypeStore.data.items[val - 1].data.cate;
                                }
                            },
                            {
                                text: '默认值',
                                flex: 1,
                                dataIndex: 'defaultValue',
                                draggable: false
                            }

                        ]
                    }
                ]
            };
        }
        mes.items = [
            {
                region: 'center',
                layout: 'border',
                border: false,
                items: [
                    {
                        xype: 'panel',
                        height: 200,
                        region: 'north',
                        margins: '2',
                        layout: {
                            type: 'hbox',
                            padding: '5 5 5 5',
                            align: 'middle'
                        },
                        defaults: {
                            margins: '5 5 5 5',
                            height: 170,
                            width: '95%'
                        },
                        items: [

                            {
                                xtype: 'fieldset',
                                title: '必填信息',
                                defaults: {
                                    labelWidth: 60,
                                    width: '100%'
                                },
                                items: [
                                    {
                                        fieldLabel: '函数名',
                                        xtype: 'textfield',
                                        allowBlank:false,//不允许为空
                                        blankText:"函数名称不能为空!",
                                        id:'functionName',
                                        disabled:mes.el != undefined,
                                        value: (mes.el == undefined) ? '' : mes.el.data.name
                                    },
                                    {
                                        id:'functionNameCH',
                                        fieldLabel: '别名',
                                        xtype: 'textfield',
                                        value: (mes.el == undefined) ? '' : mes.el.data.nameCH
                                    },
                                    {
                                        id:'functionRemark',
                                        fieldLabel: '说明',
                                        height:50,
                                        xtype: 'textarea',
                                        value: (mes.el == undefined) ? '' : mes.el.data.remark
                                    }
                                ]
                            }


                        ]
                    },
                    {
                        title: '函数体编辑器',
                        region: 'center',
                        margins: '2',
                        layout: 'fit',
                        items: [
                            {
                                xtype: 'textareafield',
                                id: 'functionContext',
                                name: 'content',
                                listeners: {
                                    afterrender: function () {
                                        window.functionContext =  CodeMirror.fromTextArea(document.getElementById("functionContext"), {
                                            lineNumbers: true,
                                            mode: "text/javascript",
                                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                        });
                                        functionContext.setSize(null,300);
                                        if(( mes.parentPid!= undefined)){
                                            Ext.Ajax.request({
                                                url: rootPath + 'function/'+mes.parentPid+'/getContext',
                                                params:{
                                                    name:mes.functionName
                                                },
                                                method: 'get',
                                                success: function (r) {
                                                    functionContext.setValue(r.responseText);
                                                },
                                                failure: function (r, e) {
                                                    Ext.Msg.alert("查询失败！");
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        ]
                    }
                ]
            },

            {
                title: '函数属性区',
                width: 300,
                collapsible: true,
                split: true,
                region: 'east',
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                items: [
                    {
                        title:"开始参数",
                        margins: '2',
                        xtype:"panel",
                        flex: 1 / 2,
                        items:[
                            {
                                xtype: 'gridpanel',
                                region: 'center',
                                border: false,
                                height: 200,
                                store: startParamsStore,

                                viewConfig: {
                                    stripeRows: true,
                                    forceFit: true,
                                    enableRowBody: true,
                                    getRowClass: function (record) {
                                        return record.get('parent') == true ? 'child-row' : '';
                                    }
                                },
                                columns: [
                                    {
                                        text: '名称',
                                        width: 80,
                                        dataIndex: 'name'
                                    },
                                    {
                                        text: '类型',
                                        width: 60,
                                        dataIndex: 'cate',
                                        renderer: function (val) {
                                            return  paramTypeStore.data.items[val - 1].data.cate;
                                        }
                                    },
                                    {
                                        text: '参数值',
                                        flex: 1,
                                        dataIndex: 'value',
                                        draggable: false
                                    }
                                ],
                                listeners: {
                                    selectionchange: function (m, selectedRecord) {
                                        if (selectedRecord.length) {


                                        }
                                    }
                                }
                            }
                        ]
                    },
                    mes.paramPanl

                ]
            }
        ]; //将grid添加窗体的items中
        mes.callParent(arguments);
    }
});
function showFunctionWin(el) {
    if((el.data != undefined&&el.data.funParams!=null)){
        functionParamsStore.loadData(el.data.funParams);
    }
    var functionWin=Ext.create('Custom.FunctionWin', {
        el: el,
        buttons:[
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                }
            }
        ]
    }).show();

}
//创建函数窗口
function createFunctionWin(el) {
    if((el.data != undefined&&el.data.funParams!=null)){
        functionParamsStore.loadData(el.data.funParams);
    }else{
        functionParamsStore.removeAll();
    }
    var functionWin=Ext.create('Custom.FunctionWin', {
        title:"函数编辑",
        buttons:[
            {
            text: '确定',
            iconCls: 'ok-16',
            handler: function () {
                var functionBody={};
                functionBody.name=Ext.getCmp("functionName").getValue();
                if ( Ext.String.trim(functionBody.name)== '') {
                    Ext.Msg.alert('系统提示', '请输入函数名称！');
                    return;
                }
                functionBody.nameCH=Ext.getCmp("functionNameCH").getValue();
                functionBody.remark=Ext.getCmp("functionRemark").getValue();
                functionBody.parentPid=lr.pid;
                //开始参数
                var  functionParams = [];
                if (functionParamsStore.data != null) {
                    functionParamsStore.each(function (record) {
                        var webPageParam = {
                            name: record.get('name'),
                            cate: record.get('cate'),
                            defaultValue: record.get('defaultValue'),
                            remark: record.get('remark')
                        };
                        functionParams.push(webPageParam);
                    });
                }
                functionBody.funParams=functionParams;
                var url=rootPath + 'function/add';
                if(el.data != undefined){
                    functionBody.pid=el.data.pid;
                    url=rootPath + 'function/update';
                }
                functionBody.context=functionContext.getValue();
                Ext.Ajax.request({
                    url: url,
                    jsonData: functionBody,
                    method: 'POST',
                    success: function (r, e) {
                        var result = Ext.decode(r.responseText);
                        if (result.success) {
                            functionStatementStore.reload();
                            functionWin.close();
                        } else {
                            Ext.Msg.alert("系统提示",result.message);
                        }
                    },
                    failure: function (r, e) {
                        Ext.Msg.alert("操作失败！");
                    }
                });

            }
        },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                }
            }
        ],
        paramPanl:  {
            title: '函数参数',
            xtype:"panel",
            margins: '2',
            flex: 1 / 2,
            items:[
                {
                    xtype: 'gridpanel',
                    border: false,
                    height: 200,
                    store: functionParamsStore,
                    tbar: [
                        {
                            iconCls: 'add-16',
                            text: '新增',
                            handler: function (el) {
                                createFuncitonParamWin(el);
                            }
                        }
                    ],
                    columns: [
                        {
                            text: '参数名',
                            width: 80,
                            dataIndex: 'name'
                        },
                        {
                            text: '类型',
                            flex: 1,
                            dataIndex: 'cate',
                            draggable: false,
                            renderer: function (val) {
                                return  functionParamTypeStore.data.items[val - 1].data.cate;
                            }
                        },
                        {
                            text: '默认值',
                            flex: 1,
                            dataIndex: 'defaultValue',
                            draggable: false
                        },
                        {
                            text: '操作',
                            width: 40,
                            xtype: 'actioncolumn',
                            layout: 'vbox',
                            align: 'center',
                            width: 90,
                            items: [
                                {
                                    tooltip: '编辑',
                                    iconCls: 'action-column-margin',
                                    icon: 'ext/images/cog_edit.png',
                                    handler: function (grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                        createFuncitonParamWin(rec);
                                    }
                                },
                                {
                                    tooltip: '删除',
                                    itemId: 'btnDel',
                                    iconCls: 'action-column-margin',
                                    icon: 'resource/images/icons/deletelayer_16x16.png',
                                    handler: function (grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                        functionParamsStore.remove(rec);
                                    }
                                }
                            ]
                        }
                    ],
                    listeners: {
                        selectionchange: function (m, selectedRecord) {

                        }
                    }
                }
            ]
    }
    }).show();

}
function updateFunctionWin(el) {
    if((el.data != undefined&&el.data.funParams!=null)){
        functionParamsStore.loadData(el.data.funParams);
    }else{
        functionParamsStore.removeAll();
    }
    var functionWin=Ext.create('Custom.FunctionWin', {
        el: el,
        title:"函数编辑",
        buttons:[
            {
                text: '确定',
                iconCls: 'ok-16',
                handler: function () {
                    var functionBody={};
                    functionBody.name=Ext.getCmp("functionName").getValue();
                    if (functionBody.name == '') {
                        Ext.Msg.alert('系统提示', '请输入函数名称！');
                        return;
                    }
                    functionBody.nameCH=Ext.getCmp("functionNameCH").getValue();
                    functionBody.remark=Ext.getCmp("functionRemark").getValue();
                    functionBody.parentPid=lr.pid;
                    //开始参数
                    var  functionParams = [];
                    if (functionParamsStore.data != null) {
                        functionParamsStore.each(function (record) {
                            var webPageParam = {
                                name: record.get('name'),
                                cate: record.get('cate'),
                                defaultValue: record.get('defaultValue'),
                                remark: record.get('remark')
                            };
                            functionParams.push(webPageParam);
                        });
                    }
                    functionBody.funParams=functionParams;
                    var url=rootPath + 'function/add';
                    if(el.data != undefined){
                        functionBody.pid=el.data.pid;
                        url=rootPath + 'function/update';
                    }
                    functionBody.context=functionContext.getValue();
                    Ext.Ajax.request({
                        url: url,
                        jsonData: functionBody,
                        method: 'POST',
                        success: function (r, e) {
                            var result = Ext.decode(r.responseText);
                            if (result.success) {
                                functionStatementStore.reload();
                                functionWin.close();
                            } else {
                                Ext.Msg.alert("操作失败！");
                            }
                        },
                        failure: function (r, e) {
                            Ext.Msg.alert("操作失败！");
                        }
                    });

                }
            },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    functionWin.close();
                }
            }
        ],
        paramPanl:  {   title: '函数参数',
            xtype:"panel",
            margins: '2',
            flex: 1 / 2,
            items:[
                {
                    xtype: 'gridpanel',
                    border: false,
                    height: 200,
                    store: functionParamsStore,
                    tbar: [
                        {
                            iconCls: 'add-16',
                            text: '新增',
                            handler: function (el) {
                                createFuncitonParamWin(el);
                            }
                        }
                    ],
                    columns: [
                        {
                            text: '参数名',
                            width: 80,
                            dataIndex: 'name'
                        },
                        {
                            text: '类型',
                            flex: 1,
                            dataIndex: 'cate',
                            draggable: false,
                            renderer: function (val) {
                                return  functionParamTypeStore.data.items[val - 1].data.cate;
                            }
                        },
                        {
                            text: '默认值',
                            flex: 1,
                            dataIndex: 'defaultValue',
                            draggable: false
                        },
                        {
                            text: '操作',
                            width: 40,
                            xtype: 'actioncolumn',
                            layout: 'vbox',
                            align: 'center',
                            width: 90,
                            items: [
                                {
                                    tooltip: '编辑',
                                    iconCls: 'action-column-margin',
                                    icon: 'ext/images/cog_edit.png',
                                    handler: function (grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                        createFuncitonParamWin(rec);
                                    }
                                },
                                {
                                    tooltip: '删除',
                                    itemId: 'btnDel',
                                    iconCls: 'action-column-margin',
                                    icon: 'resource/images/icons/deletelayer_16x16.png',
                                    handler: function (grid, rowIndex, colIndex) {
                                        var rec = grid.getStore().getAt(rowIndex);
                                        functionParamsStore.remove(rec);
                                    }
                                }
                            ]
                        }
                    ],
                    listeners: {
                        selectionchange: function (m, selectedRecord) {

                        }
                    }
                }
            ]
        }
    }).show();

}
//创建函数参数窗口
function createFuncitonParamWin(el) {
    var win = Ext.create('Ext.Window', {
        height: 300,
        width: 400,
        title: '编辑函数参数',
        plain: true,
        modal: true,
        layout: 'form',
        defaultType: 'textfield',
        bodyPadding: 5,
        items: [
            {
                fieldLabel: '参数名称',
                itemId: 'paramName',
                allowBlank:false,//不允许为空
                blankText:"参数名称不能为空!",
                disabled: !(el.data == undefined),
                value: (el.data == undefined) ? '' : el.data.name,
                listeners: {
                    change: function (t, n, o, e) {
                        if (t.value != '') {
                            t.up().down('button[text=确定]').enable()
                        }
                        if(functionParamsStore.data!=null){
                            functionParamsStore.each(function (r) {
                                    if (r.get('name') == n) {
                                        t.up().down('button[text=确定]').disable();
                                    }
                                }
                            )}
                    }
                }
            },
            {
                fieldLabel: '参数类型',
                itemId: 'paramCate',
                xtype: 'combo',
                displayField: 'cate',
                valueField: 'id',
                store: functionParamTypeStore,
                disabled: !(el.data == undefined),
                value: (el.data == undefined) ? 1 : el.data.cate
            },
            {
                fieldLabel: '默认值',
                xtype: 'textarea',
                itemId: 'defaultValue',
                value: (el.data == undefined) ? '' : el.data.defaultValue
            },
            {
                fieldLabel: '参数说明',
                xtype: 'textarea',
                itemId: 'paramRemark',
                value: (el.data == undefined) ? '' : el.data.remark
            }
        ],
        buttons: [
            {
                text: '确定',
                iconCls: 'ok-16',
                disabled: (el.data == undefined),
                handler: function () {
                    if(Ext.String.trim(win.getComponent('paramName').getValue())==""){
                        Ext.Msg.alert('系统提示', '请输入函数参数名称！');
                        return;
                    }
                    var row = {
                        name: win.getComponent('paramName').getValue(),
                        cate: win.getComponent('paramCate').getValue(),
                        defaultValue: win.getComponent('defaultValue').getValue(),
                        remark: win.getComponent('paramRemark').getValue()
                    };
                    if (el.data != undefined)
                        functionParamsStore.remove(el);
                    functionParamsStore.add(row);
                    win.close();
                }
            },
            {
                text: '取消',
                iconCls: 'cancel-16',
                handler: function () {
                    win.close();
                }
            }
        ]
    });

    win.show();
}



