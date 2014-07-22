/**
 * 帐套管理ext管理界面
 */
Ext.onReady(function () {

    Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', '../ext/ux');
    Ext.require('Ext.ux.CheckColumn');

    var pagesize = 25;
    //组件查询条件
    var resourceParam = {
        navPid:'',
        name: '',
        category: ''
    };

    //账户查询条件
    var accountParam={
        name:'',
        account:'',
        sid:''
    }

    //帐套模型
    Ext.define('setofbooks', {
        extend: 'Ext.data.Model',
        fields: [
            'id',
            {name: 'sid', type: 'string'},
            {name: 'name', type: 'string'},
            {name: 'createTime', type: 'date'} ,
            {name: 'remark', type: 'string'}
        ],
        idProperty: 'sid'
    });

    //帐套组件模型
    Ext.define('setofbooksResoruce', {
        extend: 'Ext.data.Model',
        fields: ['sid', 'resourcePid','name', 'category', 'createUser','id','operator',{name:'hitchTime',type:'datetime',format:'c'},
            {name:'updateTime',type:'datetime',format:'c'}],
        idProperty: 'id'
    });

    //帐套账户模型
    Ext.define('setofbooksAccount', {
        extend: 'Ext.data.Model',
        fields: ['sid', 'account','showName', {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
        ],
        idProperty: 'pid'
    });

    //被选账户模型
    Ext.define('baseAccount',{
        extend:'Ext.data.Model',
        fields:['id','account','showName',
            {name:'createTime',type: 'date',  dateFormat: 'c', defaultValue: null},
            {name:'checked',type:'bool',defaultValue:false}],
        idProperty:'id'
    });

    //被选组件模型
    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'},
            {name: 'createUser'},
            {name: 'allowRoles'},
            {name: 'updateTime', type: 'date', dateFormat: 'c', defaultValue: null},
            {name: 'hasNav', type: 'bool', defaultValue: false}  //是否已挂载
        ],
        idProperty: 'id'
    });

    //被选组件集合
    var resourceStore = Ext.create('Ext.data.Store', {
        model: 'Resource',
        pageSize: pagesize,
        proxy: {
            type: 'ajax',
            url:'setofbooks/lsResource/list',
            reader: {
                root: 'entity.content',
                extraParams:resourceParam,
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false,
        listeners: {
            beforeload:function (store, options){
                resourceParam.page=options.page;
                Ext.apply(store.proxy.extraParams, resourceParam);
            }
        }
    });

    //类别对象
    var categories = {
        RESOURCE: '资源',
        ROOT: '类型',
        DB: '关系数据',
        TABELGP4: '4代数据表',
        DBTABEL: '数据',
        TABELGP5: '5代数据表',
        SCRIPT: '全局脚本',
        WEBSCRIPT: 'WEB脚本',
        WPFSCRIPT: 'WPF脚本',
        WEBSKIN: 'WEB界面',
        WPFSKIN: 'WPF界面',
        IMAGE: '图片'
    };

    //帐套集合
    var setofbooksStore = Ext.create('Ext.data.Store', {
        model: 'setofbooks',
        proxy: {
            type: 'ajax',
            url: 'setofbooks/view',
            reader: {
                root: 'entity',
                totalProperty: 'totalCount'
            }
        },
        autoLoad: true
    });

    //帐套组件集合
    var setofbooksResoruceStore = Ext.create('Ext.data.Store', {
        model: 'setofbooksResoruce',
        proxy: {
            type: 'ajax',
            url: 'setofbooks/sbResource/list',
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'totalCount'
            }
        },
        pageSize:pagesize,
        autoLoad: false
    })

    //某个帐套下的所有组件集合
    var allSetofbooksResoruceStore = Ext.create('Ext.data.Store', {
        model: 'setofbooksResoruce',
        autoLoad: false
    })

    var categoryTreeStore = Ext.create('Ext.data.TreeStore', {
        fields: ['id', 'pid', 'text', 'remark', 'category'],
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

    //帐套的账户集合
    var setofbooksAccountStore = Ext.create('Ext.data.Store', {
        model: 'setofbooksAccount',
        proxy: {
            type: 'ajax',
            url: 'setofbooks/sobAccount/list',
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'totalCount'
            }
        },
        pageSize:pagesize,
        autoLoad: false
    })

    //某个帐套下的所有账户集合
    var allSetOfBooksAccountStore=Ext.create('Ext.data.Store',{
        model: 'baseAccount',
        autoLoad: false
    })

    //被选组件账户
    var baseAccountStrore=Ext.create('Ext.data.Store',{
        model:'baseAccount',
        proxy:{
            type:'ajax',
            url: 'setofbooks/baccount/list',
            reader:{
                type:'json',
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        pageSize:  pagesize ,
        autoLoad:false,
        listeners:{
            beforeload:function(store,options){
                accountParam.page=options.page;
                Ext.apply(store.proxy.extraParams, accountParam);
            }
        }
    })

    //帐套基本验证
    Ext.apply(Ext.form.field.VTypes, {
        checkRepeat: function (v) {
            var b = true;
            setofbooksStore.each(function (re) {
                if (re.get('sid') == v)
                    b = false;
            });
            return b;
        },
        checkRepeatText: '帐套编码已经存在!'
    });


    var setofbooksPanel = Ext.widget('gridpanel', {
        region: 'west',
        margins: '0 2 0 0',
        width: 250,
        store: setofbooksStore,
        dockedItems: [
            {
                xtype: 'toolbar',
                dock: 'top',
                items: ['->', {
                    text: '新增',
                    icon: 'ext/images/add.png',
                    handler: function () {
                        Ext.widget('window', {
                            title: '新增帐套',
                            width: 400,
                            height: 210,
                            modal: true,
                            layout: 'fit',
                            frame: true,
                            resizable: false,
                            closeAction: 'hide',
                            items: [
                                {
                                    xtype: 'form',
                                    border: false,
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
                                            margins: '8 0 0 0',
                                            fieldLabel: '编码',
                                            name: 'sid',
                                            vtype: 'checkRepeat',
                                            allowBlank: false
                                        },
                                        {
                                            margins: '8 0 0 0',
                                            fieldLabel: '名称',
                                            name: 'name',
                                            allowBlank: false
                                        },
                                        {
                                            margins: '8 0 0 0',
                                            xtype: 'textareafield',
                                            fieldLabel: '说明',
                                            name: 'remark'
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
                                                    formBind: true,
                                                    handler: function () {
                                                        var form = this.up('window').down('form').getForm();
                                                        Ext.Ajax.request({
                                                            url: 'setofbooks/add',
                                                            method: 'POST',
                                                            params: form.getValues(),
                                                            success: function (r, e) {
                                                                setofbooksStore.load();
                                                            }
                                                        })

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
                                }
                            ]
                        }).show();
                    }
                },{
                    text:'删除',
                    disabled:true,
                    icon:'ext/images/delete.png',
                    handler:function(){
                        Ext.Msg.confirm('警告','删除帐套将删除该帐套与之相关的组件关系!\r\n您确认删除?',function(btn){
                            if(btn=='yes'){
                                var rec = setofbooksPanel.getSelectionModel().getSelection()[0];
                                Ext.Ajax.request({
                                    url: 'setofbooks/remove',
                                    method: 'POST',
                                    params: {id: rec.get('id')},
                                    success: function (r, e) {
                                        var temp = Ext.decode(r.responseText);
                                        setofbooksStore.load();
                                    }
                                })
                            }
                        })
                    }
                }]
            }
        ],
        columns: [
            {
                header: '编码',
                width: 80,
                dataIndex: 'sid'
            },
            {
                header: '名称', flex: 1, dataIndex: 'name'
            }
        ],
        listeners: {
            selectionchange: function (t, selected) {
                tabPanel.down('button[text=挂载组件]').setDisabled(selected.length == 0);
                tabPanel.down('button[text=挂载账户]').setDisabled(selected.length == 0);
                this.down('[text=删除]').setDisabled(selected.length==0);

                if(selected[0]){

                    var id = selected[0].get('sid');

                    //加载帐套组件
                    setofbooksResoruceStore.load({
                        params: {id: id}
                    });

                    //加载帐套账户
                    setofbooksAccountStore.load({
                        params:{sid:id}
                    });
                }
            }
        }
    });

    var tabPanel = Ext.widget('tabpanel', {
        region: 'center',
        margins: '0 0 0 2',
        enableColumnHide: false,
        sortableColumns: false,
        items: [
            {
                title: '组件',
                xtype: 'gridpanel',
                store: setofbooksResoruceStore,
                enableColumnHide: false,
                sortableColumns: false,
                columns: [
                    {
                        header: '组件PID',
                        width: 120,
                        dataIndex: 'resourcePid'
                    },
                    {
                        header: '组件名称',
                        flex: 1,
                        dataIndex: 'name'
                    },
                    {
                        text: '类型名称',
                        width: 120,
                        dataIndex: 'category',
                        renderer: function (category) {
                            return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="' + rootPath + 'component/componenttype/icon/' + category + '" /> ' + categories[category];
                        }
                    },
                    {
                        header: '创建人',
                        width: 100,
                        dataIndex: 'createUser'
                    },
                    {
                        header: '操作',
                        width: 40,
                        xtype: 'actioncolumn',
                        items: [
                            {
                                iconCls: 'action-column-margin',
                                icon: 'resource/images/icons/deletelayer_16x16.png',
                                tooltip: '删除',
                                align: 'center',
                                handler: function (grid, rowIndex, colIndex) {
                                    var rec = grid.getStore().getAt(rowIndex);
                                    var temp = setofbooksPanel.getSelectionModel().getSelection()[0].get('id');
                                    var param = {
                                        sid:setofbooksPanel.getSelectionModel().getSelection()[0].get('sid'),
                                        resourcePid: rec.get('resourcePid')
                                    };
                                    Ext.Ajax.request({
                                        url: 'setofbooks/sbResource/remove',
                                        method: 'POST',
                                        jsonData: param,
                                        success: function (r, e) {
                                            var temp = Ext.decode(r.responseText);
                                            if (temp.success) {
                                                grid.getStore().remove(rec);
                                            } else {
                                                Ext.Msg.alert('提示', temp.message);
                                            }
                                        }
                                    })
                                }
                            }
                        ]
                    }
                ],
                loadMask: { msg: '正在加载数据，请稍等……' },
                dockedItems: {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: ['->', {
                        text: '挂载组件',
                        icon: 'ext/images/add.png',
                        disabled: true,
                        handler: function () {

                            //加载当前帐套下已有的组件列表
                            var sid = setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');

                            resourceParam.navPid=sid;

                            resourceStore.load();

                            //创建窗体
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

                                                if (category == 'SYS') {
                                                    resourceParam.category='';
                                                    grid.setTitle('显示全部的组件信息');
                                                }
                                                else {
                                                    resourceParam.category = category;
                                                    grid.setTitle('显示 (' + rc.get('name') + ') 下的组件信息');
                                                }
                                                resourceParam.navPid=setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');
                                                resourceStore.loadPage(1);
                                            }
                                        }
                                    },
                                    {
                                        title: '组件列表',
                                        itemId: 'resourcegrid',
                                        region: 'center',
                                        margins: '2 2 2 2',
                                        store: resourceStore,
                                        xtype: 'gridpanel',
                                        viewConfig: {
                                            stripeRows: true,
                                            enableTextSelection: true,
                                            getRowClass: function (record, rowIndex, rowParams, store) {
                                                return record.get("hasNav") == true ? "unenable" : "canenable";
                                            }
                                        },
                                        plugins: Ext.create('Ext.grid.plugin.RowEditing', {
                                            clicksToMoveEditor: 1
                                        }),
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
                                            },
                                            {
                                                xtype: 'checkcolumn',
                                                header: '选择',
                                                dataIndex: 'hasNav',
                                                width: 60,
                                                listeners: {
                                                    checkchange: function (t, r, c, e) {
                                                        var temp = this.up('gridpanel').getStore().getAt(r);
                                                        var sid=setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');
                                                        var index = allSetofbooksResoruceStore.find('resourcePid', temp.get('pid'));
                                                        if (temp.get('hasNav') == true) {
                                                            allSetofbooksResoruceStore.removeAt(index)
                                                            allSetofbooksResoruceStore.add({sid:sid,resourcePid:temp.get('pid')});
                                                        } else {
                                                            allSetofbooksResoruceStore.removeAt(index);
                                                        }
                                                    }
                                                }
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
                                            },{
                                                xtype:'toolbar',
                                                dock:'top',
                                                items:['->',{
                                                    xtype:'textfield',
                                                    fieldLabel:'组件名称',
                                                    labelWidth:60,
                                                    name:'cName',
                                                    emptyText:'请输入组件名称'
                                                },{
                                                    text:'查询',
                                                    icon:'ext/images/view.png',
                                                    handler:function(){
                                                        resourceParam.name=this.up().down('[name=cName]').getValue();
                                                        resourceStore.loadPage(1);
                                                        resourceParam.name='';
                                                    }
                                                }]
                                            }
                                        ],
                                        listeners: {
                                            selectionchange: function (sm, sr) {
                                                if (sr.length) {
                                                }
                                            }
                                        }
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
                                                    var temp = [];
                                                    var sid=setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');

                                                    allSetofbooksResoruceStore.each(function (r) {
                                                        temp.push({
                                                            sid:sid,
                                                            resourcePid: r.get('resourcePid')
                                                        })
                                                    });

                                                    Ext.Ajax.request({
                                                        url: 'setofbooks/sbResource/add',
                                                        method: 'POST',
                                                        jsonData: {resources:temp},
                                                        success: function (r, e) {
                                                            var temp = Ext.decode(r.responseText);
                                                            if (temp.success) {
                                                                Ext.Msg.alert('提示', temp.message,function(){
                                                                    setofbooksResoruceStore.load({
                                                                        params: {id: sid}
                                                                    });
                                                                });
                                                            } else {
                                                                Ext.Msg.alert('提示', temp.message);
                                                            }
                                                        }
                                                    })
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
                    }]
                },
                bbar: Ext.create('Ext.PagingToolbar', {
                    pageSize: pagesize,
                    store: setofbooksResoruceStore,
                    prependButtons: true,
                    displayInfo: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示'
                })
            },
            {
                title: '账户',
                xtype: 'gridpanel',
                store: setofbooksAccountStore,
                enableColumnHide: false,
                sortableColumns: false,
                columns: [
                    { header: '账户', flex: 1, dataIndex: 'account' },
                    { header: '账户姓名', width: 150, dataIndex: 'showName' },
                    { header:'创建日期',width:200,dataIndex:'createTime',xtype: 'datecolumn',format: 'Y-m-d H:i:s'},
                    {header: '操作',
                        width: 40,
                        xtype: 'actioncolumn',
                        items: [
                            {
                                iconCls: 'action-column-margin',
                                icon: 'resource/images/icons/deletelayer_16x16.png',
                                tooltip: '删除',
                                align: 'center',
                                handler: function (grid, rowIndex, colIndex) {
                                    var rec = grid.getStore().getAt(rowIndex);
                                    var temp = setofbooksPanel.getSelectionModel().getSelection()[0].get('id');
                                    var param = {
                                        'sid':setofbooksPanel.getSelectionModel().getSelection()[0].get('sid'),
                                        'account':rec.get('account')
                                    };
                                    Ext.Ajax.request({
                                        url: 'setofbooks/sbAccount/remove',
                                        method: 'POST',
                                        jsonData: param,
                                        success: function (r, e) {
                                            var temp = Ext.decode(r.responseText);
                                            if (temp.success) {
                                                grid.getStore().remove(rec);
                                            } else {
                                                Ext.Msg.alert('提示', temp.message);
                                            }
                                        }
                                    })


                                }
                            }
                        ]}
                ],
                loadMask: { msg: '正在加载数据，请稍等……' },
                tbar: Ext.create('Ext.Toolbar', {
                    items: ['->', {
                        text: '挂载账户',
                        icon: 'ext/images/add.png',
                        tooltip: '挂载账户',
                        id: 'gzzjbtn',
                        disabled: true,
                        handler: function () {
                            //加载当前帐套下所有的账户
                            var sid = setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');

                            accountParam.sid=sid;

                            //被选组件加载的时候判断了被选的是否被选中了
                            baseAccountStrore.load();

                            Ext.widget('window', {
                                title: '挂载账户',
                                icon: 'ext/images/add.png',
                                width: 650,
                                height: 470,
                                modal: true,
                                layout: 'border',
                                frame: true,
                                resizable: false,
                                closeAction: 'hide',
                                items: [
                                    {
                                        region: 'center',
                                        margins: '2 2 2 2',
                                        store: baseAccountStrore,
                                        xtype: 'gridpanel',
                                        viewConfig: {
                                            stripeRows: true,
                                            enableTextSelection: true,
                                            getRowClass: function (record, rowIndex, rowParams, store) {
                                                return record.get("hasNav") == true ? "unenable" : "canenable";
                                            }
                                        },
                                        plugins: Ext.create('Ext.grid.plugin.RowEditing', {
                                            clicksToMoveEditor: 1
                                        }),
                                        columns: [
                                            {
                                                text: '账户编码',
                                                dataIndex: 'id',
                                                flex: 120
                                            },
                                            {
                                                text: '账户名称',
                                                align: 'left',
                                                width: 120,
                                                sortable: false,
                                                dataIndex: 'account'
                                            },
                                            {
                                                text: '姓名',
                                                width: 120,
                                                dataIndex: 'showName'
                                            },
                                            {
                                                text: '创建时间',
                                                width: 200,
                                                sortable: true,
                                                xtype: 'datecolumn',
                                                format: 'Y-m-d H:i:s',
                                                dataIndex: 'createTime'
                                            },
                                            {
                                                xtype: 'checkcolumn',
                                                header: '选择',
                                                dataIndex: 'checked',
                                                width: 60,
                                                listeners: {
                                                    checkchange: function (t, r, c, e) {
                                                        var temp = this.up('gridpanel').getStore().getAt(r);
                                                        var index = allSetOfBooksAccountStore.find('account', temp.get('account'));
                                                        var sid=setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');
                                                        if (temp.get('checked') == true) {
                                                            allSetOfBooksAccountStore.removeAt(index)
                                                            allSetOfBooksAccountStore.add({sid:sid,account:temp.get('account')});
                                                        } else {
                                                            allSetOfBooksAccountStore.removeAt(index);
                                                        }
                                                    }
                                                }
                                            }
                                        ],
                                        dockedItems: [
                                            {
                                                xtype: 'pagingtoolbar',
                                                store: baseAccountStrore,
                                                dock: 'bottom',
                                                displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                                                emptyMsg: '没有数据显示',
                                                displayInfo: true
                                            },{
                                                xtype:'toolbar',
                                                dock:'top',
                                                items:['->',{
                                                    xtype:'textfield',
                                                    fieldLabel:'账户姓名',
                                                    labelWidth:60,
                                                    name:'cName',
                                                    emptyText:'请输入账户姓名'
                                                },{
                                                    text:'查询',
                                                    icon:'ext/images/view.png',
                                                    handler:function(){
                                                        accountParam.name=this.up().down('[name=cName]').getValue();
                                                        baseAccountStrore.loadPage(1);
                                                        accountParam.name='';
                                                    }
                                                }]
                                            }
                                        ],
                                        listeners: {
                                            selectionchange: function (sm, sr) {
                                                if (sr.length) {
                                                }
                                            }
                                        }
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
                                                    var sid=setofbooksPanel.getSelectionModel().getSelection()[0].get('sid');
                                                    var temp=[];
                                                    allSetOfBooksAccountStore.each(function(d){
                                                        temp.push({sid:sid,account: d.get('account')});
                                                    })
                                                    Ext.Ajax.request({
                                                        url: 'setofbooks/sbAccount/add',
                                                        method: 'POST',
                                                        jsonData: {setOfBooksAccount:temp},
                                                        success: function (r, e) {
                                                            var temp = Ext.decode(r.responseText);
                                                            if (temp.success) {
                                                                Ext.Msg.alert('提示', temp.message,function(){
                                                                    setofbooksAccountStore.load({
                                                                        params: {sid: sid}
                                                                    });
                                                                });
                                                            } else {
                                                                Ext.Msg.alert('提示', temp.message);
                                                            }
                                                        }
                                                    })
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
                    }]
                }),
                bbar: Ext.create('Ext.PagingToolbar', {
                    pageSize: pagesize,
                    store: setofbooksAccountStore,
                    prependButtons: true,
                    displayInfo: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示'
                })
            }
        ]
    });

    Ext.widget('panel', {
        id: 'navPanel',
        title: '帐套管理',
        frame: true,
        margin:5,
        layout: 'border',
        height: 790,
        renderTo: Ext.getBody(),
        items: [setofbooksPanel, tabPanel]
    });

    Ext.getBody().on('DOMNodeRemovedFromDocument', function () {
        Ext.getCmp('navPanel').destroy();
        delete categorys;
    });

    Ext.EventManager.onWindowResize(function () {
        Ext.getCmp('navPanel').doLayout();
    });
});