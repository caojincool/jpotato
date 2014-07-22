/**
 * 填报组件内容
 * User: dpyang
 * Date: 13-5-8
 * Time: 下午4:37
 */
Ext.onReady(function () {
    Ext.tip.QuickTipManager.init();
    var panel = Ext.fly('reporter');
    var pageSize = 50;

    //必填信息
    var panelBtxc = Ext.widget('panel', {
        title: '必填信息',
        region: 'north',
        margin: '2 2 3 2',
        height: 210,
        layout: {
            type: 'hbox',
            padding: '5',
            align: 'middle'
        },
        defaults: {
            margins: '0 5 0 0',
            height: 148
        },
        defaultType: 'fieldset',
        items: [
            {
                title: '设置',
                flex: 1,
                defaultType: 'fieldcontainer',
                layout: {
                    type: 'vbox',
                    align: 'center'
                },
                defaults: {
                    labelWidth: 85,
                    width: '80%'
                },
                items: [
                    {
                        xtype: 'textfield',
                        name: 'name',
                        value: lr.name,
                        hidden: true
                    },
                    {
                        xtype: 'textfield',
                        name: 'category',
                        value: lr.category,
                        hidden: true
                    },
                    {
                        xtype: 'textfield',
                        name: 'createUser',
                        value: lr.createUser,
                        hidden: true
                    },
                    {
                        xtype: 'textfield',
                        name: 'pid',
                        value: lr.pid,
                        hidden: true
                    },
                    {
                        fieldLabel: '组件编码',
                        xtype: 'displayfield',
                        name: 'pid',
                        value: lr.pid
                    },
                    {
                        fieldLabel: '组件名称',
                        xtype: 'displayfield',
                        name: 'name',
                        value: lr.name
                    },
                    {
                        fieldLabel: '填报文件',
                        xtype: 'filefield',
                        allowBlank: false,
                        name: 'content',
                        emptyText: '选择填报文件',
                        buttonText: '选择',
                        regex: /(.xls|.xlsx|.et)$/,
                        regexText: '请选择excel文件作为填报文件'
                    }
                ]
            },
            {
                title: '预留2',
                flex: 1,
                defaultType: 'textfield',
                layout: {
                    type: 'vbox',
                    align: 'center'
                },
                defaults: {
                    labelWidth: 60,
                    width: '80%'
                },
                items: [
                    {
                        fieldLabel: '文件类型',
                        xtype:'combobox',
                        displayField: 'name',
                        valueField: 'abbr',
                        value: '2',
                        store:Ext.create('Ext.data.Store', {
                            fields: ['abbr', 'name'],
                            data: [
                                {"abbr": "2", "name": "office 2010及2010向后版本"},
                                {"abbr": "1", "name": "office 2010以前的版本"},
                                {"abbr": "3", "name": "WPS版本"}
                            ]
                        }),
                         name: 'fileType'
                    },
                    {
                        fieldLabel: '预留b',
                        name: 'to'
                    },
                    {
                        fieldLabel: '预留c',
                        name: 'to'
                    }
                ]
            },
            {
                title: '预留3',
                flex: 1,
                defaultType: 'textfield',
                layout: {
                    type: 'vbox',
                    align: 'center'
                },
                defaults: {
                    labelWidth: 50,
                    width: '80%'
                },
                items: [
                    {
                        fieldLabel: '预留1',
                        name: 'to'
                    },
                    {
                        fieldLabel: '预留2',
                        name: 'to'
                    },
                    {
                        fieldLabel: '预留3',
                        name: 'to'
                    }
                ]
            }
        ]
    });

    //开始参数的类型
    var paramTypeStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'cate'],
        data: [
            {id: 1, cate: '数据源'},
            {id: 2, cate: '字符'},
            {id: 3, cate: '表达式'}
        ]
    });

    //创建开始参数
    function createStartParamWin(el) {
        var win = Ext.create('Ext.Window', {
            height: 260,
            width: 450,
            title: '开始参数',
            plain: true,
            modal: true,
            layout: 'form',
            defaultType: 'textfield',
            bodyPadding: 5,
            items: [
                {
                    fieldLabel: '名称',
                    disabled: !(el.data == undefined),
                    value: (el.data == undefined) ? '' : el.data.name,
                    listeners: {
                        change: function (t, n, o, e) {
                            if (t.value != '') {
                                t.up().down('button[text=确定]').enable()
                            }
                            startParamsStore.each(function (r) {
                                if (r.get('name') == n) {
                                    t.up().down('button[text=确定]').disable();
                                }
                            })
                        }
                    },
                    itemId: 'paramName'
                },
                {
                    fieldLabel: '类型',
                    itemId: 'paramCate',
                    xtype: 'combo',
                    displayField: 'cate',
                    disabled: !(el.data == undefined),
                    valueField: 'id',
                    store: paramTypeStore,
                    value: (el.data == undefined) ? 1 : el.data.cate
                },
                {
                    fieldLabel: '参数值',
                    value: (el.data == undefined) ? '' : el.data.value,
                    xtype: 'textarea',
                    itemId: 'paramValue'
                },
                {
                    fieldLabel: '说明',
                    value: (el.data == undefined) ? '' : el.data.remark,
                    xtype: 'textarea',
                    itemId: 'paramRemark'
                }
            ],
            buttons: [
                {
                    text: '确定',
                    iconCls: 'ok-16',
                    disabled: (el.data == undefined),
                    handler: function () {
                        var row = {
                            name: win.getComponent('paramName').getValue(),
                            cate: win.getComponent('paramCate').getValue(),
                            value: win.getComponent('paramValue').getValue(),
                            remark: win.getComponent('paramRemark').getValue(),
                            status: 0                                                //状态暂时默认为0,为了防止提交转换失败
                        };
                        if (el.data != undefined)
                            startParamsStore.remove(el);
                        startParamsStore.add(row);
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

    function sysFuncMg(el){
        var win;
        if (!win) {
            win = Ext.create('widget.window', {
                title: '函数管理',
                closable: true,
                width: 800,
                modal: true,
                minWidth: 350,
                height: 600,
                layout: {
                    type: 'border',
                    padding: 5
                },
                items: [{
                    region: 'east',
                    title: '开始参数',
                    margins: '2',
                    xtype:"panel",
                    width: 200,
                    split: true,
                    collapsible: true,
                    floatable: false,
                    flex: 1 / 2,
                    items:[{
                        title:'默认参数',
                        xtype: 'gridpanel',
                        border: false,
                        height: 200,
                        store: Ext.create('Ext.data.ArrayStore', {
                            storeId: 'defaultParam',
                            fields: [
                                'name','cate','defaultValue'
                            ],
                            data:[['sender','对象',''],['e','对象','']]
                        }),
                        columns: [
                            {
                                text: '参数名',
                                width: 80,
                                dataIndex: 'name'
                            },
                            {
                                text: '类型',
                                flex: 1,
                                dataIndex: 'cate'
                            },
                            {
                                text: '默认值',
                                flex: 1,
                                dataIndex: 'defaultValue',
                                draggable: false
                            }
                        ]
                    },{
                        title:'开始参数',
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
                        ]
                    }
                ]
                },{
                        title: '函数体编辑',
                        region: 'center',
                        margins: '2',
                        layout: 'fit',
                        items: [
                            {
                                xtype: 'textareafield',
                                id: 'rpfcontext',
                                name: 'rpfcontext',
                                listeners: {
                                    afterrender: function () {
                                        window.functionContext=CodeMirror.fromTextArea(document.getElementById("rpfcontext"), {
                                            lineNumbers: true,
                                            mode: "text/javascript",
                                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                                        });
                                        functionContext.setSize(null, 470);
                                        functionContext.setValue(el.up().items.items[1].getValue());
                                    }
                                }
                            }
                        ]
                 }],
                buttons:[
                    {
                        text: '确定',
                        iconCls: 'ok-16',
                        handler: function () {
                            var rpcontent=functionContext.getValue();
                            el.up().items.items[1].setValue(rpcontent);
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
        }
        if(win.isVisible()){

        }else{
            win.show();
        }
    }

    //组件开始参数
    var gridStarParms = Ext.widget('grid', {
        store: startParamsStore,
        margins: '2 2',
        columns: [
            {
                text: '名称',
                dataIndex: 'name',
                width: 80,
                draggable: false,
                menuDisabled: true
            },
            {
                text: '类型',
                dataIndex: 'cate',
                width: 60,
                renderer: function (val) {
                    var temp = new Array("数据源", "字符", "表达式");
                    return temp[val - 1];
                },
                draggable: false,
                menuDisabled: true
            },
            {
                text: '值',
                dataIndex: 'express',
                flex: 1,
                dataIndex: 'value',
                draggable: false,
                editor: {
                    allowBlank: false
                },
                menuDisabled: true
            },
            {
                text: '操作',
                width: 40,
                xtype: 'actioncolumn',
                layout: 'vbox',
                align: 'center',
                width: 60,
                items: [
                    {
                        tooltip: '编辑',
                        iconCls: 'action-column-margin',
                        icon: 'ext/images/cog_edit.png',
                        handler: function (grid, rowIndex) {
                            var rec = grid.getStore().getAt(rowIndex);
                            createStartParamWin(rec);
                        }
                    },
                    {
                        tooltip: '删除',
                        itemId: 'btnDel',
                        iconCls: 'action-column-margin',
                        icon: 'resource/images/icons/deletelayer_16x16.png',
                        handler: function (grid, rowIndex) {
                            var rec = grid.getStore().getAt(rowIndex);
                            if (!rec.get('parent'))
                                startParamsStore.remove(rec);
                        }
                    }
                ]
            }
        ],
        dockedItems: [
            {
                xtype: 'toolbar',
                dock: 'top',
                items: [
                    {
                        xtype: 'button',
                        iconCls: 'add-16',
                        text: '创建',
                        handler: function (el) {
                            createStartParamWin(el);
                        }
                    }
                ]
            }
        ]
    })

    //组件属性区
    var tabpanelProperty = Ext.widget('tabpanel', {
        margin: '3 2 2 3',
        region: 'center',
        width: 380,
        layout: 'fit',
        items: [
            {
                title: '开始参数',
                layout: 'fit',
                items: [gridStarParms]
            },
            functionManger,
            {
                title: '填报函数',
                border: false,
                layout: {
                    type: 'vbox',
                    align: 'center'
                },
                defaultType: 'container',
                defaults: {
                    labelWidth: 80,
                    width: '80%'
                },
                items: [
                    {
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'启动函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            fieldLabel:'启动函数',
                            readOnly:true,
                            hidden:true,
                            emptyText:'填报文件启动要做的事儿',
                            name:'beforeScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'修改函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            fieldLabel:'修改函数',
                            readOnly:true,
                            hidden:true,
                            emptyText:'当修改填报文件修改以后做的事儿',
                            name:'updateScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'勾稽函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            hidden:true,
                            fieldLabel:'勾稽关系',
                            readOnly:true,
                            emptyText:'填报文件点击勾稽关系要检查的事儿',
                            name:'articulationScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'审核函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            fieldLabel:'审核函数',
                            readOnly:true,
                            hidden:true,
                            emptyText:'填报文件需要审核,应该做的事儿',
                            name:'checkScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'提交函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            hidden:true,
                            fieldLabel:'提交函数',
                            readOnly:true,
                            emptyText:'填报文件点击保存做的事儿',
                            name:'commitScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'上报函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            hidden:true,
                            fieldLabel:'上报函数',
                            readOnly:true,
                            emptyText:'填报文件点击上报做的事儿',
                            name:'reportedScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    },{
                        layout: 'hbox',
                        defaultType: 'textareafield',
                        items:[{
                            fieldLabel:'关闭函数',
                            margins: '6 5 6 0',
                            xtype:'displayfield',
                            flex:0.2
                        },{
                            hidden:true,
                            fieldLabel:'关闭函数',
                            readOnly:true,
                            emptyText:'填报文件关闭以后做的事儿',
                            name:'endScript',
                            margins: '6 5 6 0',
                            flex:0.8
                        },{
                            width:100,
                            xtype:'button',
                            margins: '6 5 6 6',
                            text:'编辑',
                            handler:sysFuncMg
                        }]
                    }
                ]
            }
        ]
    })

    var frm = Ext.widget('form', {
        height: 680,
        frame: true,
        layout: 'border',
        items: [panelBtxc, tabpanelProperty],
        renderTo: panel
    })

    Ext.fly('next').on('click', function () {
        var finishUrl = rootPath + 'component/main/' + lr.pid + '/imageAndDetails/add'
        executeSave(function () {
            location.href = finishUrl;
        });
    });

    Ext.fly('finish').on('click', function () {
        var finishUrl = rootPath + 'component/main/' + lr.pid + '/create/finish';
        executeSave(function () {
            location.href = finishUrl;
        });
    });

    //提交
    function executeSave(callback) {
        var form = frm.getForm();
        var parms = form.getValues();

        //获取开始参数
        var startParams = [];
        startParamsStore.each(function (record) {
            var wpfParmsModel = {
                name: record.get('name'),
                cate: record.get('cate'),
                value: record.get('value'),
                status: record.get('status'),
                remark: record.get('remark')
            };
            startParams.push(wpfParmsModel);
        });
        if (form.isValid()) {
            form.submit({
                method: 'post',
                params: {data: Ext.JSON.encode(parms)},
                url: rootPath + 'component/reporter/add',
                //waitMsg: '正在保存。。。',
                success: function (r, o) {
                    var result = o.result;
                    if (result.success) {
                        callback();
                    } else {
                        Ext.Msg.alert('系统提示!', result.message);
                    }
                }
            });
        }
    }
})