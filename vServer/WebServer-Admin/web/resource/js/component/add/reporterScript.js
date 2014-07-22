/**
 * 创建填报脚本内容
 * Created by dpyang on 2014/5/21.
 */
var webResourceform;
//函数参数的类型
var functionParamTypeStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'cate'],
    data: [
        {id: 1, cate: '数字'},
        {id: 2, cate: '数组'},
        {id: 3, cate: '字符'},
        {id: 4, cate: '对象'}
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
Ext.onReady(function () {


    if(lr.funParams!=undefined){
        functionParamsStore.loadData(lr.funParams);
    }

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
    var panelEl = Ext.get('webEditor');

    webResourceform = Ext.widget('form', {
        height: 750,
        frame: true,
        layout: 'border',
        items: [
            {
                title: '基本信息',
                region: 'center',
                id: 'webpanel',
                layout: {
                    type: 'border',
                    align: 'stretch'
                },

                items: [
                    {
                        xype: 'panel',
                        height: 270,
                        region: 'north',
                        margins: '2',
                        layout: {
                            type: 'hbox',
                            padding: '5 5 5 5',
                            align: 'middle'
                        },
                        defaults: {
                            margins: '5 5 5 5',
                            height: 234,
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
                                        name:'name',
                                        allowBlank:false,//不允许为空
                                        blankText:"组件名称不能为空!",
                                        value: (lr== undefined) ? '' :lr.name
                                    },
                                    {
                                        fieldLabel: '别名',
                                        xtype: 'textfield',
                                        name:'nameCH',
                                        //allowBlank:false,//不允许为空
                                        blankText:"组件名称不能为空!",
                                        value: (lr== undefined) ? '' :lr.nameCH

                                    },
                                    {
                                        fieldLabel: '函数类型',
                                        name:'scriptType',
                                        xtype: 'textfield',
                                        value: (lr== undefined) ? '' :lr.scriptType
                                    },
                                    {

                                        fieldLabel: '系统参数',
                                        name:'strParams',
                                        xtype: 'textarea',
                                        height:40,
                                        value: (lr== undefined) ? '' :lr.strParams
                                    },
                                    {

                                        fieldLabel: '说明',
                                        height:50,
                                        name:'remark',
                                        xtype: 'textarea',
                                        value: (lr== undefined) ? '' :lr.remark
                                    }
                                ]
                            }


                        ]
                    },
                    {
                        title:"函数体编辑器",
                        xype: 'panel',
                        region: 'center',
                        margins: '2',
                        layout: "fit",
                        items:[
                            {
                                id:"script",
                                name: 'context',
                                xtype: 'textareafield',
                                margin: 5,
                                listeners: {
                                    afterrender: function () {
                                        window.script =  CodeMirror.fromTextArea(document.getElementById("script"), {
                                            lineNumbers: true,
                                            mode: "text/javascript",
                                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                        });
                                        script.setSize(null,764);
                                        if(lr!= undefined&&lr.context!=undefined){
                                            script.setValue(lr.context);
                                        }
                                    }
                                }

                            }

                        ]
                    }


                ]
            },
            {
                title: '属性区',
                width: 380,
                collapsible: true,
                split: true,
                region: 'east',
                xtype: 'panel',
                layout:{
                    type:"vbox",
                    align:'stretch'
                },
                items: [
                    {   title: '函数参数',
                        xtype:"panel",
                        margins: '2',
                        flex: 1 / 4,
                        items:[
                            {
                                xtype: 'gridpanel',
                                border: false,
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
                    },
                    {
                        xtype:"tabpanel",
                        flex: 3 / 4,
                        items:[
                            {   title: '开始脚本',
                                xtype:"panel",
                                layout:"fit",
                                margins: '2 2',
                                items:[
                                    {
                                        id:"initScript",
                                        name: 'initScript',
                                        xtype: 'textareafield',
                                        margin: 5,
                                        listeners: {
                                            afterrender: function () {
                                                window.initScript =  CodeMirror.fromTextArea(document.getElementById("initScript"), {
                                                    lineNumbers: true,
                                                    mode: "text/javascript",
                                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                                });
                                                initScript.setSize(null,564);
                                                if(lr!= undefined&&lr.initScript!=undefined){
                                                    initScript.setValue(lr.initScript);
                                                }
                                                // initScript.setValue(lr.initScript);

                                            }
                                        }

                                    }
                                ]
                            },
                            {   title: '结束脚本',
                                margins: '2 2',
                                xtype:"panel",
                                layout:"fit",
                                items:[
                                    {
                                        id: 'endScript',
                                        name: 'endScript',
                                        xtype: 'textareafield',
                                        margin: 5,
                                        value:lr!= undefined&&lr.endScript!=undefined?lr.endScript:'',
                                        listeners: {
                                            afterrender: function () {
                                                window.endScript =  CodeMirror.fromTextArea(document.getElementById("endScript"), {
                                                    lineNumbers: true,
                                                    mode: "text/javascript",
                                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                                });
                                                endScript.setSize(null,564);
                                                if(lr!= undefined&&lr.endScript!=undefined){
                                                    endScript.setValue(lr.endScript);
                                                }
                                                //endScript.setValue(lr.endScript);

                                            }
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ],
        renderTo: panelEl
    })


});
function executeSave(callback){

    //函数参数
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
    //获取参数
    var param = webResourceform.getForm().getValues();
    param.pid=lr.pid;
    param.funParams = functionParams;
    param.context=script.getValue();
    if(window.endScript==undefined){
        param.endScript=lr.endScript;
    }else{
        param.endScript=endScript.getValue();
    }

    param.initScript=initScript.getValue();

    Ext.Ajax.request({
        url: rootPath + 'component/'+lr.category.toLocaleLowerCase()+'/'+lr.pid+'/edit',
        jsonData: param,
        method: 'POST',
        success: function (r, e) {
            var result = Ext.decode(r.responseText);
            if (result.success) {
                callback();
            } else {
                Ext.Msg.alert("系统提示","操作失败！");
            }
        },
        failure: function (r, e) {
            Ext.Msg.alert("操作失败！");
        }
    });
}