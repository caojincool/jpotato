/**
 * 创建关于web组件的页面布局信息
 * User: dpyang
 * Date: 13-3-19
 * Time: 下午1:40
 */
Ext.onReady(function () {


    //输出类型
    var outStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'oType'],
        data: [
            {'id': 'text/html', 'oType': 'HTML文档'},
            {'id': 'application/octet-stream', 'oType': '任意的二进制数据'}
        ]
    });



    //资源附件模型
    Ext.define('webResourceAttachModel', {
        extend: 'Ext.data.Model',
        fields: [
            'name',
            'type',
            {name: 'size', type: 'int'}
        ],
        idProperty: 'name'
    });

    //获取组件的附件
    var webResourceAttachStore = Ext.create('Ext.data.Store', {
        model: 'webResourceAttachModel',
        proxy: {
            type: 'ajax',
            extraParams:{ pid: lr.pid },
            url: rootPath + 'component/webskin/attaches/all',
            reader: {
                root: 'entity'
            },
            simpleSortMode: true
        },
        autoLoad: true
    });

    //父组件的开始参数,选中的开始组件参数
    var strparms, checkedparm = {name: '', cate: 1, value: '', remark: ''};

    //当父组件文本框离开焦点获取或删除父组件的开始参数
    var parentPidTextBlurEvent = function (value, insert) {
        Ext.Ajax.request({
            url: rootPath + 'component/webskin/'+value+'/startParams/all',
            success: function (response, e1) {
                strparms = Ext.decode(response.responseText).entity;

                if (strparms != '') {
                    for (var i = 0; i < strparms.length; i++) {
                        var name = strparms[i].name;
                        var cate = strparms[i].cate;
                        var r = startParamsStore.getById(name);

                        //如果本组件的开始参数与父组件的开始参数相同,那么先移除本组件的开始参数
                        if (r != null && cate == 1) {
                            startParamsStore.remove(r);
                        }
                        //如果插入开始参数,并且父组件的开始参数类型为区域那么就插入一条开始参数
                        if (insert == true && cate == 1) {
                            strparms[i].parent = true;
                            startParamsStore.insert(0, strparms[i]);
                        }
                    }
                }
            }
        })
    }
    //------------------------------------------------------------------------------------------------------------------
    Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', '../ext/ux');
    Ext.require('Ext.ux.CheckColumn');
    var panelEl = Ext.get('webEditor');
    var pageSize = 50;
    var pid = "";
    //导航组件
    Ext.define('NavigateComponentModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'string'},
            {name: 'resourcePid'},
            {name: 'name'},
            {name: 'category', type: 'string'},
            {name: 'createUser'},
            {name: 'updateTime'},
            {name: 'system', type: 'bool'}
        ],
        idProperty:'id'
    });
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

    var webResourceform = Ext.widget('form', {
        height: 750,
        frame: true,
        layout: 'border',
        items: [
            {
                title: '必填信息',
                xype: 'panel',
                height: 200,
                margin: '2 2 5 2',
                region: 'north',
                layout: {
                    type: 'hbox',
                    padding: '5',
                    align: 'middle'
                },
                defaults: {
                    margins: '0 5 0 5',
                    height: 146
                },
                defaultType: 'fieldset',
                items: [
                    {
                        title: '页面属性',
                        flex: 1,
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
                                fieldLabel: '输出类型',
                                xtype: 'combobox',
                                store: outStore,
                                queryMode: 'local',
                                displayField: 'oType',
                                valueField: 'id',
                                value: lr.contextType,
                                name: 'contextType'
                            },
                            {
                                fieldLabel: '是否缓存',
                                xtype: 'checkboxfield',
                                name: 'cache',
                                inputValue: true,
                                checked: lr.cache
                            },
                            {
                                xtype: 'fieldcontainer',
                                fieldLabel: '缓存时间',
                                layout: 'hbox',
                                combineErrors: false,
                                defaults: {
                                    hideLabel: true
                                },
                                items: [
                                    {
                                        xtype: 'numberfield',
                                        name: 'cacheTime',
                                        value: lr.cacheTime,
                                        flex: 4 / 5,
                                        allowBlank: false
                                    },
                                    {
                                        xtype: 'displayfield',
                                        value: '分钟',
                                        width: 30,
                                        margin: '0 0 0 10'
                                    }
                                ]
                            },
                            {
                                fieldLabel: '是否页面',
                                xtype: 'checkboxfield',
                                inputValue: true,
                                checked: lr.page,
                                name: 'page'
                            }
                        ]},
                    {
                        title: '组件关系',
                        flex: 1,
                        margin: '3 0',
                        layout: {
                            type: 'vbox',
                            align: 'center'
                        },
                        defaults: {
                            labelWidth: 60,
                            width: '80%'
                        },
                        defaultType: 'textfield',
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
                                fieldLabel: '预留',
                                name: 'yu'
                            },
                            {
                                fieldLabel: '预留',
                                name: 'yu'
                            },
                            {
                                fieldLabel: '预留',
                                name: 'yu'
                            }
                        ]
                    },
                    {
                        title: '预留属性',
                        flex: 1,
                        margin: 3,
                        layout: {
                            type: 'vbox',
                            align: 'center'
                        },
                        defaults: {
                            labelWidth: 60,
                            width: '80%'
                        },
                        defaultType: 'textfield',
                        items: [
                            {
                                fieldLabel: '预留',
                                name: 'yu'
                            },
                            {
                                fieldLabel: '预留2',
                                name: 'yu'
                            },
                            {
                                fieldLabel: '预留3',
                                name: 'yu'
                            },
                            {
                                fieldLabel: '预留4',
                                name: 'yu'
                            }
                        ]
                    }
                ]
            },
            {
                title: '网页编辑器',
                region: 'center',
                id: 'webpanel',
                layout: 'fit',
                tbar:[
                    {
                        xtype:'splitbutton',
                        text: '预览',
                        iconCls: 'eye-16',
                        handler:function (){
                            executeSave(function(){
                            var preViewUrl = clientUrl +"/"+ lr.pid.toUpperCase();
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
                    }
                    ],
                items: [
                    {
                        xtype: 'textareafield',
                        id: 'script',
                        margins: '2',
                        name: 'content',
                        listeners: {
                            afterrender: function () {
                                window.editor =  CodeMirror.fromTextArea(document.getElementById("script"), {
                                    lineNumbers: true,
                                    mode: "text/html",
                                    autoCloseTags: true,
                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}
                                });
                                editor.setSize(null,480);
                                Ext.Ajax.request({
                                   url: rootPath + 'component/webskin/'+lr.pid+'/getContext',
                                    method: 'get',
                                    success: function (r) {
                                        editor.setValue(r.responseText);
                                    }
                                });
                            }
                        }
                    }
                ]
            },
            {
                title: '网页属性区',
                width: 380,
                collapsible: true,
                split: true,
                region: 'east',
                xtype: 'tabpanel',
                items: [
                    {
                        title: '基本信息',
                        defaultType: 'fieldcontainer',
                        flex: 1,
                        layout: {
                            type: 'vbox',
                            align: 'left',
                            defaultMargins: {top: 15, right: 5, bottom: 0, left: 3}
                        },
                        defaults: {
                            labelWidth: 58,
                            layout: 'hbox',
                            width: '90%'
                        },
                        items: [
                            {
                                xtype: 'textfield',
                                name: 'pid',
                                hidden: true,
                                value: lr.pid
                            },
                            {
                                fieldLabel: '组件编号',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    xtype: 'displayfield',
                                    value: lr.pid
                                }
                            },
                            {
                                fieldLabel: '组件名称',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    name: 'name',
                                    xtype: 'textfield',
                                    value: lr.name
                                }
                            },
                            {
                                xtype: 'textfield',
                                name: 'category',
                                hidden: true,
                                value: lr.category
                            },
                            {
                                fieldLabel: '组件类别',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    xtype: 'displayfield',
                                    value: 'WEB组件'
                                }
                            },
                            {
                                xtype: 'textfield',
                                name: 'createUser',
                                hidden: true,
                                value: lr.createUser
                            },
                            {
                                fieldLabel: '创建人',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    xtype: 'displayfield',
                                    value: lr.createUser
                                }
                            },
                            {
                                xtype: 'textfield',
                                name: 'updateTime',
                                hidden: true,
                                value: lr.updateTime
                            },
                            {
                                fieldLabel: '创建时间',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    xtype: 'displayfield',
                                    value: lr.updateTime
                                }
                            },
                            {
                                fieldLabel: '参数',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    width: 260,
                                    height: 75,
                                    xtype: 'textareafield',
                                    value: lr.strParams,
                                    name: 'strParams'
                                }
                            },
                            {
                                fieldLabel: '说明',
                                labelStyle : "text-align:right;width:58;",
                                items: {
                                    width: 260,
                                    height: 150,
                                    xtype: 'textareafield',
                                    value: lr.remark,
                                    name: 'remark'
                                }
                            }
                        ]
                    },
                    {
                        title:'页面附件',
                        layout:'fit',
                        margins:'2 2',
                        items:
                            [
                                {
                                    layout: 'fit',
                                    store: webResourceAttachStore,
                                    xtype: 'gridpanel',
                                    columns: [
                                        {
                                            text: '附件名称',
                                            dataIndex: 'name',
                                            flex: 1,
                                            renderer: function (val,cellmeta, rowIndex) {
                                                var name = rowIndex.data.name;
                                                var type = rowIndex.data.type;
                                                return "<a href='component/webskin/"+lr.pid+"/"+name+"' target='_blank'>"+val+"</a>";
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
                                            layout: 'vbox',
                                            align: 'center',
                                            width: 60,
                                            items: [
                                                {
                                                    tooltip: '删除',
                                                    tips: '删除',
                                                    align:'center',
                                                    iconCls: 'action-column-margin',
                                                    icon: 'resource/images/icons/deletelayer_16x16.png',
                                                    handler: function (grid, rowIndex, colIndex) {
                                                        var rec = grid.getStore().getAt(rowIndex).get("name");
                                                        Ext.Ajax.request({
                                                            url: rootPath + 'component/webskin/'+lr.pid+'/attach/remove',
                                                            method: 'POST',
                                                            params: {
                                                                filename: rec
                                                            },
                                                            success: function (response) {
                                                                webResourceAttachStore.load();
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
                                                        FileDiag.wpfResourceAttachStore = webResourceAttachStore;
                                                        FileDiag.pid=lr.pid;
                                                        FileDiag.show();
                                                    }
                                                }
                                            ]
                                        }
                                    ]}
                            ]
                    } ,
                    {
                        title: '开始参数',
                        layout: 'border',
                        items: [
                            {
                                xtype: 'gridpanel',
                                region: 'center',
                                margins: '2 2',
                                height: 200,
                                store: startParamsStore,
                                tbar: [
                                    {
                                        iconCls: 'add-16',
                                        text: '创建',
                                        handler: function (el) {
                                            createStartParamWin(el);
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
                                                handler: function (grid, rowIndex, colIndex) {
                                                    var rec = grid.getStore().getAt(rowIndex);
                                                    createStartParamWin(rec);
                                                }
                                            },
                                            {
                                                tooltip: '删除',
                                                itemId: 'btnDel',
                                                iconCls: 'action-column-margin',
                                                icon: 'resource/images/icons/deletelayer_16x16.png',
                                                handler: function (grid, rowIndex, colIndex) {
                                                    var rec = grid.getStore().getAt(rowIndex);

                                                    if (!rec.get('parent'))
                                                        startParamsStore.remove(rec);
                                                }
                                            }
                                        ]
                                    }
                                ],
                                listeners: {
                                    selectionchange: function (m, selectedRecord) {
                                        if (selectedRecord.length) {
                                            checkedparm = selectedRecord[0].data;
                                            Ext.getCmp("checkedparmName").setValue(checkedparm.name);
                                            Ext.getCmp("checkedparmCate").setValue(paramTypeStore.data.items[checkedparm.cate - 1].data.cate)
                                            Ext.getCmp("checkedparmValue").setValue(checkedparm.value)
                                            Ext.getCmp("checkedparmRemark").setValue(checkedparm.remark)

                                        }
                                    }
                                }
                            },
                            {
                                title: '详细信息',
                                xtype: 'panel',
                                height: 200,
                                margins: '2 2',
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
                                    width: '90%'
                                },
                                items: [
                                    {
                                        fieldLabel: '名称',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield',
                                            id:"checkedparmName"
                                        }
                                    },
                                    {
                                        fieldLabel: '类别',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield',
                                            id:"checkedparmCate",
                                            name: 'cate'
                                        }
                                    },
                                    {
                                        fieldLabel: '值',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            xtype: 'displayfield',
                                            name: 'value',
                                            id:"checkedparmValue"
                                        }
                                    },
                                    {
                                        fieldLabel: '说明',
                                        labelStyle : "text-align:right;width:28;",
                                        items: {
                                            width: 260,
                                            height: 75,
                                            readOnly:true,
                                            xtype: 'textareafield',
                                            id:"checkedparmRemark"
                                        }
                                    }
                                ]
                            }
                        ]
                    },
                    functionManger,
                    {
                        title: '请求脚本',
                        layout: {
                            type: 'vbox',
                            align: 'stretch'
                        },
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
                                            executeFunctionEditWin(initScript.getValue(),initScript);
                                        }
                                    }
                                ],
                                items:[
                                        {
                                            id:"initScript",
                                            name: 'initScript',
                                            xtype: 'textareafield',
                                            value: lr.initScript,
                                            readOnly:true,
                                            listeners: {
                                                afterrender: function () {
                                                    window.initScript =  CodeMirror.fromTextArea(document.getElementById("initScript"), {
                                                        lineNumbers: true,
                                                        mode: "text/javascript",
                                                        extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                                    });
                                                    initScript.setSize(null,182);

                                                    if(lr!= undefined&&lr.initScript!=undefined){
                                                        initScript.setValue(lr.initScript);
                                                    }

                                                }
                                            }

                                        }
                                ]
                            },
                            {   title: '结束脚本',
                                xtype:"panel",
                                margins: '2 2',
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
                                        value: lr.endScript,
                                        readOnly:true,
                                        listeners: {
                                            afterrender: function () {
                                                window.endScript =  CodeMirror.fromTextArea(document.getElementById("endScript"), {
                                                    lineNumbers: true,
                                                    mode: "text/javascript",
                                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                                });
                                                endScript.setSize(null,182);

                                                if(lr!= undefined&&lr.endScript!=undefined){
                                                    endScript.setValue(lr.endScript);
                                                }

                                            }
                                        }
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        title: '提交脚本',
                        layout: {
                            type: 'vbox',
                            align: 'stretch'
                        },
                        items: [
                            {
                                xtype:"panel",
                                flex: 1,
                                margins: '2 2',
                                tbar:[
                                    {
                                        xtype: 'button',
                                        text: '编辑',
                                        iconCls: 'edit-1',
                                        handler:function (el){
                                            executeFunctionEditWin(formScript.getValue(),formScript);
                                        }
                                    }
                                ],
                                items:[
                                    {
                                        id:"formScript",
                                        name: 'formScript',
                                        xtype: 'textareafield',
                                        margins: '2 2',
                                        value: lr.formScript,
                                        height:410,
                                        readOnly:true,
                                        listeners: {
                                            afterrender: function () {
                                                window.formScript =  CodeMirror.fromTextArea(document.getElementById("formScript"), {
                                                    lineNumbers: true,
                                                    mode: "text/javascript",
                                                    extraKeys: {"Enter": "newlineAndIndentContinueComment"}

                                                });
                                                formScript.setSize(null,450);

                                                if(lr!= undefined&&lr.formScript!=undefined){
                                                    formScript.setValue(lr.formScript);
                                                }
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


    //创建开始参数窗口
    function createStartParamWin(el) {
        var win = Ext.create('Ext.Window', {
            height: 300,
            width: 400,
            title: '创建或者编辑开始参数',
            plain: true,
            modal: true,
            layout: 'form',
            defaultType: 'textfield',
            bodyPadding: 5,
            items: [
                {
                    fieldLabel: '参数名称',
                    itemId: 'paramName',
                    disabled: !(el.data == undefined),
                    value: (el.data == undefined) ? '' : el.data.name,
                    listeners: {
                        change: function (t, n, o, e) {
                            if (t.value != '') {
                                t.up().down('button[text=确定]').enable()
                            }
                            if(startParamsStore.data!=null){
                                startParamsStore.each(function (r) {
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
                    store: paramTypeStore,
                    disabled: !(el.data == undefined),
                    value: (el.data == undefined) ? 1 : el.data.cate
                },
                {
                    fieldLabel: '参数值',
                    xtype: 'textarea',
                    itemId: 'paramValue',
                    value: (el.data == undefined) ? '' : el.data.value
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
                        var row = {
                            name: win.getComponent('paramName').getValue(),
                            cate: win.getComponent('paramCate').getValue(),
                            value: win.getComponent('paramValue').getValue(),
                            remark: win.getComponent('paramRemark').getValue(),
                            parent: (el.data == undefined) ? false : el.get('parent')
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
    //保存
    Ext.fly('btnOk').on('click', function () {
        executeSave( function (){
            location.href = "/component/main/operatingResults?success=true";
        });
    });
    function executeSave(callback){
        //开始参数
        var  webPageParams = [];
        if (startParamsStore.data != null) {
            startParamsStore.each(function (record) {
                var webPageParam = {
                    name: record.get('name'),
                    cate: record.get('cate'),
                    value: record.get('value'),
                    parent: record.get('parent'),
                    remark: record.get('remark')
                };
                webPageParams.push(webPageParam);
            });
        }
        //获取参数
        var param = webResourceform.getForm().getValues();
        param.startParams = webPageParams;
        param.context=editor.getValue();
        if(window.endScript==undefined){
            param.endScript=lr.endScript;
            param.initScript=lr.initScript;
        }else{
            param.endScript=endScript.getValue();
            param.initScript=initScript.getValue();
        }
        if(window.formScript==undefined){
            param.formScript=lr.formScript;
        }else{
            param.formScript=formScript.getValue();
        }
        Ext.Ajax.request({
            url: rootPath + 'component/webskin/edit',
            jsonData: param,
            method: 'POST',
            success: function (r, e) {
                var result = Ext.decode(r.responseText);
                if (result.success) {
                    callback();
                } else {
                   Ext.Msg.alert("操作失败！");
                }
            },
            failure: function (r, e) {
                Ext.Msg.alert("操作失败！");
            }
        });
    }
});