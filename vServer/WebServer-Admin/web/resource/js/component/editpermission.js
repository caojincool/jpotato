/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-9-10
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
/**
 * 组件初始化权限
 * User: dp
 * Date: 13-8-30
 * Time: 下午4:45
 */
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.dd.*'
]);
Ext.onReady(function () {

    var panel = Ext.fly('panelpermission');

    Ext.define('role', {
        extend: 'Ext.data.Model',
        fields: ['name',{name: 'permission', type: 'int', defaultValue: 2}],
        idProperty:'name'
    });

    Ext.define('account', {
        extend: 'Ext.data.Model',
        fields: ['id', 'showName', 'account', {name: 'permission', type: 'int', defaultValue: 2}],
        idProperty: 'id'
    });

    var permission = [
        {key: 1, value: "允许"},
        {key: 2, value: "未知"},
        {key: 3, value: "拒绝"},
        {key: 4, value: "隐藏"}
    ];

    Ext.define('merge', {
        extend: 'Ext.data.Model',
        fields: ['id', 'name', 'type', 'permission'],
        idProperty: 'id'
    })

    var accountParam = {
        account: '',
        name: ''
    }

    //合并账户角色集合
    var mergeStore = Ext.create('Ext.data.Store', {
        model: 'merge',
        proxy: {
            type: 'ajax',
            extraParams:{pid:pid},
            url: rootPath + 'component/main/permission',
            reader: {
                type: 'json',
                root: 'entity'
            },
            simpleSortMode: true
        },
        autoLoad: true
    });

    //被选角色集合
    var roleStore = Ext.create('Ext.data.Store', {
        model: 'role',
        proxy: {
            type: 'ajax',
            url: rootPath + 'role/list/get',
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: true,
        listeners:{
//            beforeload: function (store, options) {
//                accountParam.page = options.page;
//                Ext.apply(store.proxy.extraParams, accountParam);
//            },
            load: function (s, records, successful, e) {
                if (successful) {
                    var count = records.length;
                    for (var i = 0; i < count; i++) {
                        mergeStore.each(function (record) {
                            if (record.get('type') == 2 && record.get('name') == records[i].get('name')) {
                                records[i].set('permission', record.get('permission'));
                                return;
                            }
                        })
                    }
                }
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
        autoLoad: true,
        listeners: {
            beforeload: function (store, options) {
                accountParam.page = options.page;
                Ext.apply(store.proxy.extraParams, accountParam);
            },
            load: function (s, records, successful, e) {
                if (successful) {
                    var count = records.length;
                    for (var i = 0; i < count; i++) {
                        mergeStore.each(function (record) {
                            if (record.get('type') == 1 && record.get('name') == records[i].get('account')) {
                                records[i].set('permission', record.get('permission'));

                                return;
                            }
                        })
                    }
                }
            }
        }
    });

    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1
    });

    var rolecellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1
    });

    //整个窗体面板
    Ext.widget('panel', {
        title: '组件权限分配',
        frame: true,
        height: 520,
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
                        id:'rolep',
                        title: '角色',
                        xtype: 'grid',
                        store: roleStore,
                        plugins: [rolecellEditing],
                        columns: [
                            {text: "角色名称", flex: 1, sortable: true, dataIndex: 'name'},
                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                renderer: function (v) {
                                    return permission[v-1].value;
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
                                                    if (h > 0){
                                                        mergeStore.removeAt(h);
                                                        mergeStore.add(temp);
                                                    }else
                                                        mergeStore.add(temp);
                                                } else {
                                                    var h = mergeStore.find('name', name);
                                                    mergeStore.removeAt(h);
                                                }
                                            }
                                        },
                                        store: Ext.create('Ext.data.Store', {
                                            fields: ['key', 'value'],
                                            data:permission
                                        })
                                    }
                                ),
                                listClass: 'x-combo-list-small'
                            }
                        ],
                        dockedItems: [
                            {
                                xtype: 'toolbar',
                                dock: 'top',
                                items: [ '->',
                                    '账户名称:', {
                                        xtype: 'textfield',
                                        name: 'cName',
                                        emptyText: '请输入账户名称'
                                    }, {
                                        text: '查询',
                                        icon: 'ext/images/view.png',
                                        handler: function () {
                                            accountParam.account = this.up().down('[name=cName]').getValue();
                                            accountStore.loadPage(1);
                                        }
                                    }]
                            },
                            {
                                xtype: 'pagingtoolbar',
                                dock: 'bottom',
                                store: roleStore,
                                displayInfo: true,
                                displayMsg: '显示 {0} - {1} 共 {2}',
                                emptyMsg: "没有角色信息"
                            }
                        ]
                    },
                    {
                        id:'accoutp',
                        title: '账户',
                        xtype: 'grid',
                        store: accountStore,
                        plugins: [cellEditing],
                        columns: [
                            {text: "账户名称", flex: 1, sortable: true, dataIndex: 'account'},
                            {text: "账户姓名", width: 120, sortable: true, dataIndex: 'showName'},
                            {text: "权限", width: 60, align: 'center', sortable: true, dataIndex: 'permission',
                                renderer: function (v) {
                                    return permission[v-1].value;
                                }, editor: Ext.widget('combo', {
                                    typeAhead: true,
                                    transform: 'permission',
                                    valueField: 'key',
                                    displayField: 'value',
                                    listeners: {
                                        change: function (t, n, o, e) {
                                            var name = this.up('grid').getSelectionModel().getSelection()[0].get('account');
                                            if (n != 2) {
                                                var temp = {name: name, type: 1, permission: n};
                                                var h = mergeStore.find('name', name);
                                                if (h > 0){
                                                    mergeStore.removeAt(h);
                                                    mergeStore.add(temp);
                                                }else
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
                            }
                        ],
                        dockedItems: [
                            {
                                xtype: 'toolbar',
                                dock: 'top',
                                items: [ '->',
                                    '账户名称:', {
                                        xtype: 'textfield',
                                        name: 'cName',
                                        emptyText: '请输入账户名称'
                                    }, {
                                        text: '查询',
                                        icon: 'ext/images/view.png',
                                        handler: function () {
                                            accountParam.account = this.up().down('[name=cName]').getValue();
                                            accountStore.loadPage(1);
//                                        accountParam.account= '';
                                        }
                                    }
                                ]
                            },
                            {
                                xtype: 'pagingtoolbar',
                                dock: 'bottom',
                                store: accountStore,
                                displayInfo: true,
                                pageSize: 3,
                                displayMsg: ' 显示{0} - {1} 共 {2}',
                                emptyMsg: "无账户信息"
                            }
                        ]
                    }
                ]
            }
        ]
    });

    //提交
    Ext.fly('btnNext').on('click',function(){

        var parms=[];

        mergeStore.each(function(rec){

            parms.push({name:rec.get('name'),type:rec.get('type'),permission:rec.get('permission')-1});
        })

        Ext.Ajax.request({
            url: rootPath + 'component/main/'+pid+'/permission',
            jsonData: {rpList:parms},
            method: 'POST',
            success: function (r, e) {
                var result = Ext.decode(r.responseText);
                if (result.success) {
//                    window.location.href = rootPath+'component/main/'+pid+'/navigate';
                } else {
//                    window.location.href = rootPath + 'index?current=component/main/view';
                }
            },
            failure: function (r, e) {

            }
        });
    })
})