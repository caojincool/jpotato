/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-3
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
Ext.onReady(function () {
    //------------------------------------------------------------------------------------------------------------------
    Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', '../ext/ux');
    Ext.require('Ext.ux.CheckColumn');
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
    Ext.define('componentList',{
        extend:"Ext.window.Window",
        title: '挂载组件',
        icon: 'ext/images/add.png',
        width: 800,
        height: 500,
        modal: true,
        inp:'',
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
                            document.getElementById(this.up('window').inp).value=pid;
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
    });
    Ext.get("delete1").on("click",function(){
        resourceStore.load();
        Ext.create("componentList",{
            inp:'startPage'
        }).show();
    });
    Ext.get("delete2").on("click",function(){
        resourceStore.load();
        Ext.create("componentList",{
            inp:'logon'
        }).show();
    });
    Ext.get("delete3").on("click",function(){
        resourceStore.load();
        Ext.create("componentList",{
            inp:'logout'
        }).show();
    });
    Ext.get("delete4").on("click",function(){
        resourceStore.load();
        Ext.create("componentList",{
            inp:'error'
        }).show();
    });
});
