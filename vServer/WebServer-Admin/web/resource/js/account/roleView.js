Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    var panelEl = Ext.get('rolePanel');

    Ext.define('Role', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id'},
            {name: 'name'},
            {name: 'remark'},
            {name: 'createAccount'},
            {name: 'system', type:'boolean' },
            {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
        ],
        idProperty: 'name'
    });
    var pagesize = 18;
    var store = Ext.create('Ext.data.Store', {
        model: 'Role',
        pageSize:  pagesize ,
        proxy: {
            type: 'ajax',
            url: rootPath + 'role/list/get',
            reader: {
                root: 'entity'
            },
            simpleSortMode: true
        },
        autoLoad:true
    });
    //数据参数
    storeParam = {
        name:'',
        sysName:''
    };
    store.on('beforeload', function (store, options) {
        storeParam.page= options.page;
        // resourceParam.name = Ext.getCmp('comname').getValue();
        // resourceParam.category = Ext.getCmp('txtCombox').getValue();
        Ext.apply(store.proxy.extraParams, storeParam);
    });
    var panel = Ext.create('Ext.panel.Panel', {
        id:'rolePanel',
        height:600,
        layout:'fit',
        frame: true,
        renderTo:panelEl,
        items:[{
            title:'角色列表',
            region: 'center',
            margins: '2 2 0 2',
            store: store,
            xtype:'gridpanel',
            tbar:['->', {
                text:'创建角色',
                iconCls:'add-16',
                handler:function(){
                    //location.href = rootPath + "account/create";
                }
            }],
            viewConfig: {
                stripeRows: true,
                enableTextSelection: true
            },
            columns:[{
                text     : '名称',
                align    : 'center',
                width    : 75,
                sortable : false,
                dataIndex: 'name'
            }, {
                text     : '说明',
                flex     : 1,
                sortable : true,
                dataIndex: 'remark'
            }, {
                text     : '内置',
                width    : 100,
                sortable : true,
                dataIndex: 'system'
            }, {
                text     : '创建时间',
                width    : 120,
                sortable : true,
                xtype    : 'datecolumn',
                format   : 'Y-m-d H:i:s',
                dataIndex: 'createTime'
            },  {
                text     : '操作',
                width    : 100,
                align    : 'center',
                sortable : false,
                xtype    : 'actioncolumn',
                layout: 'vbox',
                items    : [{
                    tooltip: '查看详细信息',
                    iconCls:'action-column-margin',
                    icon: 'resource/images/icons/eye_16x16.png'
                }, {
                    tooltip: '重置登录密码',
                    iconCls:'action-column-margin',
                    icon: 'resource/images/icons/password_16x16.png'
                }, {
                    tooltip: '删除用户账号',
                    iconCls:'action-column-margin',
                    icon:'resource/images/icons/deletelayer_16x16.png'
                }]
            }],
            bbar: Ext.create('Ext.PagingToolbar',{
                pageSize: pagesize,
                store: store,
                prependButtons: true,
                displayInfo: true,
                displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                emptyMsg: '没有数据显示'
            })
        }]
    });

    panelEl.on('DOMNodeRemovedFromDocument', function(){
        Ext.getCmp('rolePanel').destroy();
    });

    Ext.EventManager.onWindowResize(function(){
        Ext.getCmp('rolePanel').doLayout();
    });

    ShowContext();
});