/**
 * wfp创建界面
 * User: dpyang
 * Date: 13-5-8
 * Time: 下午4:37
 */
Ext.onReady(function () {
    Ext.tip.QuickTipManager.init();

    var panel = Ext.fly('wpfSkin');
    var pageSize = 50;
    //组件类别集合
    var categoryTreeStore = Ext.create('Ext.data.TreeStore', {
        fields: ['id', 'pid', 'text', 'remark', 'category','name'],
        root: {
            text: 'Ext JS',
            id: 'src',
            expanded: true
        },
        proxy: {
            type: 'ajax',
            url: rootPath + 'component/componenttype/list/get'
        },
        folderSort: true,
        listeners: {
            load: function (st, root) {
                function temp(node) {
                    if (node == null) return;
                    var icon = rootPath + 'component/componenttype/icon/' + node.get('category').toLowerCase();
                    node.set('icon', icon);
                    if (node.childNodes != null) {
                        for (var i = 0; i < node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                        }
                    }
                }

                temp(root);
            }
        },
        autoLoad: false
    });
    //查询条件
    var resourceParam = {
        navPid:'',
        name: '',
        category: '',
        pid:''
    };
    //被选组件模型
    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'},
            {name: 'createUser'},
            {name: 'updateTime', type: 'date', dateFormat: 'c', defaultValue: null},
            {name: 'hasNav', type: 'bool', defaultValue: false}  //是否已挂载
        ],
        idProperty: 'pid'
    });
    //被选组件数据
    var resourceStore = Ext.create('Ext.data.Store', {
        model: 'Resource',
        pageSize: pageSize,
        proxy: {
            type: 'ajax',
            url: 'component/navigation/get/checkedNavResource',
            reader: {
                root: 'entity.content',
                extraParams: resourceParam,
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false,
        listeners: {
            beforeload: function (store, options) {
                resourceParam.page = options.page;
                Ext.apply(store.proxy.extraParams, resourceParam);
            }
        }
    });
    //------------------------------------------------------------------------------------------------------------------


    //开始参数的类型
    var paramTypeStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'cate'],
        data: [
            {id: 1, cate: '数据源'},
            {id: 2, cate: '字符'},
            {id: 3, cate: '表达式'}
        ]
    });

    //打开方式的类型 弹出窗口=1 合并窗口=2 模式窗口=3
    var openModeStore = Ext.create('Ext.data.Store', {
        fields: [
            {name: 'value', type: 'int'},
            'cate'
        ],
        data: [
            {value: 1, cate: '弹出窗口'},
            {value: 2, cate: '合并窗口'},
            {value: 3, cate: '模式窗口'}
        ]
    });

    //显示位置 窗口居中 = 1 屏幕居中 = 2 随意 = 3
    var showLocationStore = Ext.create('Ext.data.Store', {
        fields: [
            {name: 'value', type: 'int'},
            'cate'
        ],
        data: [
            {value: 1, cate: '窗口居中'},
            {value: 2, cate: '屏幕居中'},
            {value: 3, cate: '随意'}
        ]
    });




    //资源附件模型
    Ext.define('wpfResourceAttachModel', {
        extend: 'Ext.data.Model',
        fields: [
            'name',
            'type',
            {name: 'size', type: 'int'}
        ],
        idProperty: 'name'
    });

    //获取组件的附件
    var wpfResourceAttachStore = Ext.create('Ext.data.Store', {
        model: 'wpfResourceAttachModel',
        proxy: {
            type: 'ajax',
            url: rootPath + 'component/wpfskin/'+lr.pid+'/attaches/all',
            reader: {
                root: 'entity'
            },
            simpleSortMode: true
        },
        autoLoad: false
    });

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
                        fieldLabel: '显示工具栏',
                        xtype: 'checkboxfield',
                        name: 'showToolbar',
                        checked: true,
                        inputValue: true
                    },
                    {
                        fieldLabel: '同步窗口大小',
                        xtype: 'checkboxfield',
                        name: 'synchronismWindowSize',
                        checked: true,
                        inputValue: true
                    },
                    {
                        fieldLabel: '打开方式',
                        xtype: 'combobox',
                        store: openModeStore,
                        queryMode: 'local',
                        displayField: 'cate',
                        valueField: 'value',
                        value: 1,
                        name: 'openMode'
                    },
                    {
                        fieldLabel: '显示位置',
                        xtype: 'combobox',
                        store: showLocationStore,
                        queryMode: 'local',
                        displayField: 'cate',
                        valueField: 'value',
                        value: 1,
                        name: 'showLocation'
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
                    labelWidth: 50,
                    width: '80%'
                },
                items: [
                    {
                        xtype: 'fieldcontainer',
                        fieldLabel: '父组件',
                        layout: 'hbox',
                        defaultType: 'textfield',
                        items: [{
                            name: 'parentPid',
                            id: 'parentPid',
                            xType: 'textfield',
                            value: lr.parentPid ,
                            flex: 7
                        }, {
                            text: '选择',
                            xtype:'button',
                            flex: 3,
                            margins: '0 0 0 6',
                            listeners: {
                                focus:function () {
                                    resourceStore.load();
                                    Ext.widget('window', {
                                        title: '挂载组件',
                                        icon: 'ext/images/add.png',
                                        width: 800,
                                        height: 500,
                                        modal: true,
                                        layout: 'border',
                                        frame: true,
                                        resizable: false,
                                        closeAction: 'hide',
                                        items: [
                                            {
                                                title: '显示类别',
                                                region: 'west',
                                                margins: '2 2 2 2',
                                                width: 200,
                                                split: true,
                                                xtype: 'treepanel',
                                                useArrows: true,
                                                rootVisible: false,
                                                displayField: 'text',
                                                store: categoryTreeStore,
                                                listeners: {
                                                    itemclick: function (view, rc, item) {
                                                        var category = rc.get('category');

                                                        var grid = this.up().down('gridpanel');
                                                        if (category == 'ROOT') {
                                                            resourceParam.name = '';
                                                            grid.setTitle('显示全部的组件信息');
                                                        }
                                                        else {
                                                            resourceParam.category = category;
                                                            grid.setTitle('显示 (' + rc.get('text')+ ') 下的组件信息');
                                                        }
                                                        resourceStore.loadPage(1);
                                                    }
                                                }
                                            },
                                            {
                                                title: '组件列表',
                                                id: 'resourcegrid',
                                                region: 'center',
                                                margins: '2 2 2 2',
                                                store: resourceStore,
                                                xtype: 'gridpanel',
                                                columns: [
                                                    {
                                                        text: '组件名称',
                                                        dataIndex: 'name',
                                                        flex: 1
                                                    },
                                                    {
                                                        text: '编号',
                                                        align: 'left',
                                                        width: 120,
                                                        sortable: false,
                                                        dataIndex: 'pid'
                                                    },
                                                    {
                                                        text: '类型',
                                                        width: 60,
                                                        dataIndex: 'category',
                                                        renderer: function (category) {
                                                            return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="' + rootPath + 'component/componenttype/icon/' + category + '" /> ';
                                                        }
                                                    },
                                                    {
                                                        text: '更新时间',
                                                        width: 120,
                                                        sortable: true,
                                                        xtype: 'datecolumn',
                                                        format: 'Y-m-d H:i:s',
                                                        dataIndex: 'updateTime'
                                                    },
                                                    {
                                                        text: '创建人员',
                                                        width: 80,
                                                        sortable: true,
                                                        align: 'center',
                                                        dataIndex: 'createUser'
                                                    }
                                                ],
                                                dockedItems: [
                                                    {
                                                        xtype: 'pagingtoolbar',
                                                        store: resourceStore,
                                                        dock: 'bottom',
                                                        displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                                                        emptyMsg: '没有数据显示',
                                                        displayInfo: true
                                                    },
                                                    {
                                                        xtype: 'toolbar',
                                                        dock: 'top',
                                                        items: ['->', '组件名称:', {
                                                            xtype: 'textfield',
                                                            name: 'cName',
                                                            emptyText: '请输入组件名称'
                                                        }, {
                                                            text: '查询',
                                                            icon: 'ext/images/view.png',
                                                            handler: function () {
                                                                resourceParam.name = this.up().down('[name=cName]').getValue();
                                                                resourceStore.loadPage(1);
                                                                resourceParam.name = '';
                                                            }
                                                        }]
                                                    }
                                                ]
                                            }
                                        ],
                                        dockedItems: [
                                            {
                                                xtype: 'toolbar',
                                                dock: 'bottom',
                                                ui: 'footer',
                                                items: [
                                                    {
                                                        xtype: 'component',
                                                        flex: 1
                                                    },
                                                    {
                                                        xtype: 'button',
                                                        text: '确定',
                                                        handler: function () {
                                                            var pid=Ext.getCmp('resourcegrid').getSelectionModel().getSelection()[0].get('pid');
                                                            Ext.getCmp('parentPid').setValue(pid);
                                                            this.up('window').hide();
                                                        }
                                                    },
                                                    {
                                                        xtype: 'button',
                                                        text: '取消',
                                                        handler: function () {
                                                            this.up('window').hide();
                                                        }
                                                    }
                                                ]
                                            }
                                        ]
                                    }).show();
                                }
                            }

                        }]
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

    //代码编辑区
    var panelCode = Ext.widget('panel', {
        title: '代码编辑区',
        region: 'center',
        margin: '3 3 2 2',
        layout: 'fit',
        tbar:[{
            xtype:'splitbutton',
            text: '预览',
            iconCls: 'eye-16',
            handler:function (){
                executeSave(function(){
                    var preViewUrl = 'ilemsun://command/resource='+lr.pid.toUpperCase()+'&token=&sfdsd=slskdj&action=open';
                    window.open(preViewUrl);
                });
            },
            menu: [
                {
                    text: '选择实例',
                    handler:function (){
                        executeSave(function(){
                            var preViewUrl = "component/main/"+lr.pid.toUpperCase()+"/instanceChoose";
                            window.open(preViewUrl);
                        });
                    }
                }
            ]
        }],
        items: [
            {
                xtype: 'textareafield',
                id: 'script',
                margin: 2,
                rows: 10,
                name: 'context',
                listeners: {
                    afterrender: function () {
                        window.editor =  CodeMirror.fromTextArea(document.getElementById("script"), {
                            lineNumbers: true,
                            mode: "text/xml",
                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                        });
                        editor.setSize(null,490);
                    },
                    afterlayout: function () {
                        if (this.editorEl != null) {
                            //var s    ize = this.getSize();
    //this.editorEl.setSize(size.width, size.height);
                        }
                    }
}
}
]
})

    //创建开始参数
    function createStartParamWin(el) {
        var win = Ext.create('Ext.Window', {
            height: 300,
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

    //开始参数编辑器
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false,
        saveBtnText: '保存',
        cancelBtnText: '取消',
        errorsText: '错误'
    });

    var strparms;
    //组件开始参数
    var gridStarParms = Ext.widget('grid', {
        store: startParamsStore,
        margins:'2 2',
        plugins: [rowEditing],
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
        title: '组件属性区',
        margin: '3 2 2 3',
        region: 'east',
        width: 380,
        collapsible: true,
        split: true,
        layout: 'fit',
        items: [
            {
                title: '基本信息',
                xtype: 'form',
                border: false,
                bodyPadding: 5,
                defaultType: 'fieldcontainer',
                layout: {
                    type: 'vbox',
                    padding: '20',
                    align: 'center'
                },
                defaults: {
                    margins: '0 0 20 0',
                    width: 280
                },
                fieldDefaults: {
                    labelWidth: 55
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
                        fieldLabel: '组件名称',
                        items: {
                            xtype: 'displayfield',
                            value: lr.name
                        },
                        anchor: '80%'
                    },
                    {
                        fieldLabel: '组件类别',
                        items: {
                            xtype: 'displayfield',
                            value: 'WPF组件'
                        },
                        anchor: '80%'
                    },
                    {
                        fieldLabel: '创建人',
                        items: {
                            xtype: 'displayfield',
                            value: lr.createUser
                        },
                        anchor: '80%'
                    },
                    {
                        fieldLabel: '说明'
                    },
                    {
                        flex: 1 / 2,
                        xtype: 'textareafield',
                        name: 'remark',
                        value: lr.remark
                    },
                    {
                        fieldLabel: '参数设置'
                    },
                    {
                        flex: 1 / 2,
                        xtype: 'textareafield',
                        name: 'strParams',
                        value: lr.strParams
                    }
                ]
            },
            {
                title: '界面附件',
                xtype: 'panel',
                margins:'2 2',
                layout: 'fit',
                items:[
                    {

                        layout: 'fit',
                        store: wpfResourceAttachStore,
                        xtype: 'gridpanel',
                        columns: [
                            {
                                text: '附件名称',
                                dataIndex: 'name',
                                flex: 1,
                                renderer: function (val,cellmeta, rowIndex) {
                                    var name = rowIndex.data.name;
                                    var type = rowIndex.data.type;
                                    return "<a href='component/wpfskin/"+lr.pid+"/"+name+"' target='_blank'>"+val+"</a>";
                                }
                            },
                            {
                                text: '类型',
                                dataIndex: 'type',
                                width: 50
                            },
                            {
                                text: '大小',
                                dataIndex: 'size',
                                renderer: function (val) {
                                    return (val / 1024).toFixed(2) + 'KB';
                                }
                            },
                            {
                                text: '操作',
                                xtype: 'actioncolumn',
                                align: 'center',
                                layout: 'vbox',
                                width: 60,
                                items: [
                                    {
                                        tooltip: '删除',
                                        iconCls: 'action-column-margin',
                                        icon: 'resource/images/icons/deletelayer_16x16.png',
                                        handler: function (grid, rowIndex, colIndex) {
                                            var rec = grid.getStore().getAt(rowIndex).get("name");
                                            Ext.Ajax.request({
                                                url: rootPath + 'component/wpfskin/'+lr.pid+'/attaches/remove',
                                                method: 'POST',
                                                params: {
                                                    filename: rec
                                                },
                                                success: function (response) {
                                                    wpfResourceAttachStore.load();
                                                }
                                            })
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
                                        text: '上传',
                                        iconCls: 'add-16',
                                        handler: function () {
                                            FileDiag.wpfResourceAttachStore = wpfResourceAttachStore;
                                            FileDiag.pid=lr.pid;
                                            FileDiag.show();
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]

            }
          ,
            {
                title: '开始参数',
                layout: 'fit',
                items: [gridStarParms]
            },
            functionManger,
            {
                title: '请求脚本',
                border: false,
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                defaultType: 'textareafield',
                items: [
                    {   title: '开始脚本',
                        xtype:"panel",
                        margins: '2 2',
                        flex: 1 / 2,
                        tbar:[
                            {
                                xtype: 'button',
                                text: '编辑',
                                iconCls: 'edit-1',
                                handler:function (el){
                                    executeFunctionEditWin(beforeScript.getValue(),beforeScript);
                                }
                            }
                        ],
                        items:[
                            {
                                id:"beforeScript",
                                name: 'beforeScript',
                                xtype: 'textareafield',
                                margin: 5,
                                value: lr.beforeScript,
                                height:175,
                                readOnly:true,
                                listeners: {
                                    afterrender: function () {
                                        window.beforeScript =  CodeMirror.fromTextArea(document.getElementById("beforeScript"), {
                                            lineNumbers: true,
                                            mode: "text/javascript",
                                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                        });
                                        beforeScript.setSize(null,182);
                                        if(lr!= undefined&&lr.beforeScript!=undefined){
                                            beforeScript.setValue(lr.beforeScript);
                                        }
                                        //beforeScript.setValue(lr.beforeScript);

                                    }
                                }

                            }
                        ]
                    },
                    {   title: '结束脚本',
                        margins: '2 2',
                        xtype:"panel",
                        flex: 1 / 2,
                        tbar:[
                            {
                                xtype: 'button',
                                text: '编辑',
                                iconCls: 'edit-1',
                                handler:function (el){
                                    executeFunctionEditWin(endScript.getValue(),endScript);
                                }
                            }
                        ],
                        items:[
                            {
                                id: 'endScript',
                                name: 'endScript',
                                xtype: 'textareafield',
                                margin: 5,
                                value: lr.endScript,
                                height:175,
                                readOnly:true,
                                listeners: {
                                    afterrender: function () {
                                        window.endScript =  CodeMirror.fromTextArea(document.getElementById("endScript"), {
                                            lineNumbers: true,
                                            mode: "text/javascript",
                                            extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                        });
                                        endScript.setSize(null,182);
                                        //endScript.setValue(lr.endScript);
                                        if(lr!= undefined&&lr.endScript!=undefined){
                                            endScript.setValue(lr.endScript);
                                        }
                                    }
                                }
                            }
                        ]
                    }

                ]
            }
        ]
    })

    //整个form窗体
    var frm = Ext.widget('form', {
        height: 750,
        layout: 'border',
        frame: true,
        items: [panelBtxc, panelCode, tabpanelProperty],
        renderTo: panel
    })


    Ext.fly('next').on('click', function () {
        var finishUrl=rootPath + 'component/main/'+lr.pid+'/imageAndDetails/add'
        executeSave(function(){
            location.href=finishUrl;
        });
    });

    Ext.fly('finish').on('click', function () {
        var finishUrl=rootPath + 'component/main/'+lr.pid+'/create/finish';
        executeSave(function(){
            location.href=finishUrl;
        });
    });
    //提交
    function executeSave(callback) {
        var form = frm.getForm();

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
        //获取页面中所有的值
        var parms = form.getValues();
        parms.context = editor.getValue();
        //增加开始参数的属性
        parms.startParams = startParams;
        if(window.beforeScript==undefined){
            parms.endScript=lr.beforeScript;
            parms.beforeScript=lr.beforeScript;
        }else{
            parms.endScript=endScript.getValue();
            parms.beforeScript=beforeScript.getValue();
        }
        Ext.Ajax.request({
            url: rootPath + 'component/wpfskin/add',
            jsonData: parms,
            method: 'POST',
            success: function (r, e) {
                var result = Ext.decode(r.responseText);
                if (result.success) {
                    callback();
                } else {
                    Ext.Msg.alert('系统提示!', '保存失败');
                }
            },
            failure: function (r, e) {
                Ext.Msg.alert('系统提示!', '保存失败');
            }
        });
    }
})