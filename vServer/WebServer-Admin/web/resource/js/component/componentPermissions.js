/**
 * 组件初始化权限
 * User: dp
 * Date: 13-8-30
 * Time: 下午4:45
 */
Ext.onReady(function () {

    Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', '../ext/ux');
    Ext.require('Ext.ux.CheckColumn');

    var panel = Ext.fly('panelpermission');

    //角色模型
    Ext.define('role', {
        extend: 'Ext.data.Model',
        fields: ['name', {name: 'permission', type: 'int', defaultValue: 2}, 'remark'],
        idProperty: 'name'
    });

    //账户模型
    Ext.define('account', {
        extend: 'Ext.data.Model',
        fields: ['id', 'showName', 'account', {name: 'permission', type: 'int', defaultValue: 2}],
        idProperty: 'id'
    });

    //权限
    var permission = [
        {key: 1, value: "允许"},
        {key: 2, value: "未知"},
        {key: 3, value: "拒绝"},
        {key: 4, value: "隐藏"}
    ];

    var tempp=[
        {key:'Allow',value:1},
        {key:'Unkonw',value:2},
        {key:'Deny',value:3},
        {key:'Hiddle',value:4}
    ];

    //合并账户模型
    Ext.define('merge', {
        extend: 'Ext.data.Model',
        fields: ['id', 'name', 'type', 'permission'],
        idProperty: 'id'
    })

    //角色过滤条件
    var roleParam = {
        name:'',
        names: []
    }

    //账户过滤条件
    var accountParam = {
        account: '',
        name: '',
        accounts:[]
    }

    //已选角色集合
    var haveRoleStore = Ext.create('Ext.data.Store', {
        model: 'merge'

    });

    //已选账户集合
    var haveAccountStore=Ext.create('Ext.data.Store',{
        model:'merge'
    })

    //被选角色集合
    var roleStore = Ext.create('Ext.data.Store', {
        model: 'role',
        proxy: {
            type: 'ajax',
            extraParams: roleParam,
            url: rootPath + 'role/list/get',
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false,
        listeners: {
            beforeload: function (store, options) {
                roleParam.page = options.page;
                Ext.apply(store.proxy.extraParams, roleParam);
            }
        }
    });

    //被选账户集合
    var accountStore = Ext.create('Ext.data.Store', {
        model: 'account',
        proxy: {
            type: 'ajax',
            url: rootPath + 'account/list/get',
            reader: {
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false,
        listeners: {
            beforeload: function (store, options) {
                accountParam.page = options.page;
                Ext.apply(store.proxy.extraParams, accountParam);
            }
        }
    });

    //整个窗体面板
    Ext.widget('panel', {
        title: '组件权限分配',
        frame: true,
        height: 600,
        renderTo: panel,
        layout: 'fit',
        items: [
            {
                xtype: 'tabpanel',
                margins: '0 0 0 2',
                enableColumnHide: false,
                sortableColumns: false,
                items: [
                    {
                        id: 'rolep',
                        title: '角色',
                        xtype: 'grid',
                        store: haveRoleStore,
                        columns: [
                            {text: "角色名称", flex: 1, sortable: true, dataIndex: 'name'},
                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                renderer: function (v) {
                                    return permission[v - 1].value;
                                }
                            },
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
                                            haveRoleStore.removeAt(rowIndex);
                                        }
                                    }
                                ]}
                        ],
                        dockedItems:[
                            {
                                xtype: 'toolbar',
                                dock: 'top',
                                items: [ '->',
                                    {
                                        text: '选择角色',
                                        icon: 'ext/images/add.png',
                                        tooltip: '选择角色',
                                        id: 'gzzjbtn',
                                        handler: function () {

                                            //获取过滤条件
                                            var temp=[];
                                            haveRoleStore.each(function(r){
                                                temp.push(r.get('name'));
                                            });

                                            roleParam.names=temp;
                                            //被选角色加载，过滤已选角色
                                            roleStore.load();

                                            Ext.widget('window', {
                                                title: '选择角色',
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
                                                        store: roleStore,
                                                        xtype: 'gridpanel',
                                                        plugins: Ext.create('Ext.grid.plugin.RowEditing', {
                                                            clicksToEdit: 1,
                                                            //clicksToMoveEditor: 1,
                                                            saveBtnText:'保存',
                                                            autoCancel : false,
                                                            cancelBtnText : '取消'
                                                        }),
                                                        columns: [
                                                            {
                                                                text: '角色名称',
                                                                dataIndex: 'name',
                                                                flex: 120
                                                            },
                                                            {
                                                                text: '角色说明',
                                                                align: 'left',
                                                                width: 120,
                                                                sortable: false,
                                                                dataIndex: 'remark'
                                                            },
                                                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                                                renderer: function (v) {
                                                                    return permission[v - 1].value;
                                                                },
                                                                editor: Ext.widget('combo', {
                                                                        typeAhead: true,
                                                                        transform: 'permission',
                                                                        valueField: 'key',
                                                                        displayField: 'value',
                                                                        listeners: {
                                                                            change: function (t, n, o, e) {
                                                                                var name = this.up('grid').getSelectionModel().getSelection()[0].get('name');
                                                                                if (n != 2) {
                                                                                    var temp = {name: name, type: 2, permission: n};
                                                                                    var h = mergeStore.find('name', name);
                                                                                    if (h > 0) {
                                                                                        mergeStore.removeAt(h);
                                                                                        mergeStore.add(temp);
                                                                                    } else
                                                                                        mergeStore.add(temp);
                                                                                } else {
                                                                                    var h = mergeStore.find('name', name);
                                                                                    mergeStore.removeAt(h);
                                                                                }
                                                                            }
                                                                        },
                                                                        store: Ext.create('Ext.data.Store', {
                                                                            fields: ['key', 'value'],
                                                                            data: permission
                                                                        })
                                                                    }
                                                                ),
                                                                listClass: 'x-combo-list-small'
                                                            },
                                                            {
                                                                xtype: 'checkcolumn',
                                                                header: '选择',
                                                                dataIndex: 'checked',
                                                                width: 60,
                                                                listeners: {
                                                                    checkchange: function (t, r, c, e) {
                                                                        var temp = this.up('gridpanel').getStore().getAt(r);
                                                                        if(c)
                                                                            haveRoleStore.add(temp);
                                                                        else
                                                                            haveRoleStore.remove(temp);
                                                                    }
                                                                }
                                                            }
                                                        ],
                                                        dockedItems: [
                                                            {
                                                                xtype: 'pagingtoolbar',
                                                                store: roleStore,
                                                                dock: 'bottom',
                                                                displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                                                                emptyMsg: '没有数据显示',
                                                                displayInfo: true
                                                            }
//                                                            {
//                                                                xtype:'toolbar',
//                                                                dock:'top',
//                                                                items:['->',{
//                                                                    xtype:'textfield',
//                                                                    fieldLabel:'角色名',
//                                                                    labelWidth:60,
//                                                                    name:'cName',
//                                                                    emptyText:'请输入角色名'
//                                                                },
//                                                                    {
//                                                                    text:'查询',
//                                                                    icon:'ext/images/view.png',
//                                                                    handler:function(){
//                                                                        roleParam.name=this.up().down('[name=cName]').getValue();
//                                                                        //获取过滤条件
//                                                                        var temp=[];
//                                                                        haveRoleStore.each(function(r){
//                                                                            temp.push(r.get('name'));
//                                                                        });
//
//                                                                        roleParam.names=temp;
//
//                                                                        roleStore.loadPage(1);
//                                                                        accountParam.name='';
//                                                                    }
//                                                                }
//                                                                ]
//                                                            }
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
                                                                text: '　确　定　',
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
                            }
                        ]
                    },
                    {
                        id: 'accoutp',
                        title: '账户',
                        xtype: 'grid',
                        store: haveAccountStore,
                        columns: [
                            {text: "账户名称", flex: 1, sortable: true, dataIndex: 'account'},
                            {text: "账户姓名", width: 120, sortable: true, dataIndex: 'showName'},
                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                renderer: function (v) {
                                    return permission[v - 1].value;
                                }
                            },{header: '操作',
                                width: 40,
                                xtype: 'actioncolumn',
                                items: [
                                    {
                                        iconCls: 'action-column-margin',
                                        icon: 'resource/images/icons/deletelayer_16x16.png',
                                        tooltip: '删除',
                                        align: 'center',
                                        handler: function (grid, rowIndex, colIndex) {
                                            haveAccountStore.removeAt(rowIndex);
                                        }
                                    }
                                ]}
                        ],
                        dockedItems: [
                            {
                                xtype: 'toolbar',
                                dock: 'top',
                                items: [ '->',
                                    {
                                        text: '选择账户',
                                        icon: 'ext/images/add.png',
                                        tooltip: '选择账户',
                                        handler: function () {

                                            //获取过滤条件
                                            var temp=[];
                                            haveAccountStore.each(function(r){
                                                temp.push(r.get('account'));
                                            });

                                            accountParam.accounts=temp;
                                            //被选角色加载，过滤已选角色
                                            accountStore.load();

                                            Ext.widget('window', {
                                                title: '选择账户',
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
                                                        store: accountStore,
                                                        xtype: 'gridpanel',
                                                        plugins: Ext.create('Ext.grid.plugin.RowEditing', {
                                                            clicksToEdit: 1,
                                                            saveBtnText:'保存',
                                                            autoCancel : false,
                                                            cancelBtnText : '取消'
                                                        }),
                                                        columns: [
                                                            {
                                                                text: '编码',
                                                                dataIndex: 'id',
                                                                flex: 120
                                                            },
                                                            {
                                                                text: '登陆账户',
                                                                align: 'left',
                                                                width: 120,
                                                                sortable: false,
                                                                dataIndex: 'account'
                                                            },
                                                            {
                                                                text: '姓名',
                                                                align: 'left',
                                                                width: 120,
                                                                sortable: false,
                                                                dataIndex: 'showName'
                                                            },
                                                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                                                renderer: function (v) {
                                                                    return permission[v - 1].value;
                                                                },
                                                                editor: Ext.widget('combo', {
                                                                        typeAhead: true,
                                                                        transform: 'permission',
                                                                        valueField: 'key',
                                                                        displayField: 'value',
                                                                        store: Ext.create('Ext.data.Store', {
                                                                            fields: ['key', 'value'],
                                                                            data: permission
                                                                        })
                                                                    }
                                                                ),
                                                                listClass: 'x-combo-list-small'
                                                            },
                                                            {
                                                                xtype: 'checkcolumn',
                                                                header: '选择',
                                                                dataIndex: 'checked',
                                                                width: 60,
                                                                listeners: {
                                                                    checkchange: function (t, r, c, e) {
                                                                        var temp = this.up('gridpanel').getStore().getAt(r);
                                                                        if(c)
                                                                            haveAccountStore.add(temp);
                                                                        else
                                                                            haveAccountStore.remove(temp);
                                                                    }
                                                                }
                                                            }
                                                        ],
                                                        dockedItems: [
                                                            {
                                                                xtype: 'pagingtoolbar',
                                                                store: accountStore,
                                                                dock: 'bottom',
                                                                displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                                                                emptyMsg: '没有数据显示',
                                                                displayInfo: true
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
                                                                text: ' 确 定 ',
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
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    });

    //提交
    function extracted(nextUrl) {
//合并角色账户权限
        var parms = [];

        haveRoleStore.each(function (rec) {
            parms.push({name: rec.get('name'), type: 1, permission: rec.get('permission') - 1});
        })

        haveAccountStore.each(function (rec) {
            parms.push({name: rec.get('account'), type: 2, permission: rec.get('permission') - 1});
        })

        Ext.Ajax.request({
            url: rootPath + 'component/main/' + pid + '/permission',
            jsonData: {rpList: parms},
            method: 'POST',
            success: function (r, e) {
                var result = Ext.decode(r.responseText);
                if (result.success) {
                    window.location.href = nextUrl;
                } else {
                    window.location.href = rootPath + 'component/main/view';
                }
            },
            failure: function (r, e) {

            }
        });
    }

    Ext.fly('next').on('click', function () {
        var nextUrl=rootPath + 'component/main/' + pid + '/navigate';
        extracted(nextUrl);
    })
    Ext.fly('finish').on('click', function () {
        var nextUrl=rootPath + 'component/main/'+pid+'/create/finish';
        extracted(nextUrl);
    })
})