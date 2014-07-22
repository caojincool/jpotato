/**
 * Created with IntelliJ IDEA.
 * User: ssy
 * Date: 12-11-20
 * Time: 上午9:12
 * To change this template use File | Settings | File Templates.
 */
Ext.Loader.setConfig({enabled:true});

var id;

var mainForm = Ext.create('Ext.form.Panel',{
    defaultType:'textfield',
    frame:true,
    fieldDefaults:{
        labelWidth:60,
        labelAlign:'right'
    },
    layout:{
        type:'table',
        columns:2
    },
    items: [
        {
            fieldLabel: '账号',
            name: 'account'
        },
        {
            fieldLabel: '密码',
            name: 'password'
        }]
});

var subordinateForm = Ext.create('Ext.form.Panel',{
    defaultType:'textfield',
    frame:true,
    fieldDefaults:{
        labelWidth:60,
        labelAlign:'right'
    },
    layout:{
        type:'table',
        columns:2
    },
    items: [
        {
            fieldLabel: '性别',
            name: 'sex'
        },
        {
            fieldLabel: '电话',
            name: 'phone'
        }]
});

Ext.define('role', {
    extend: 'Ext.data.Model',
    idProperty: 'role',
    defaultType:'textfield',
    fields: [
        {name: 'name'}
    ]
});

var unassignRoleStore = Ext.create('Ext.data.Store', {
    layout:'fit',
    model: 'role',
    proxy:{
        type:'ajax',
        url:'role/getUnassignRole',
        reader:{
            type:'json',
            totalProperty: 'totalCount',
            root:'data'
        }
    }
});

var unassignRoleGrid = Ext.create('Ext.grid.Panel',{
    layout:'fit',
    width:300,
    height:conheight+100,
    store:unassignRoleStore,
    columns:[
        {header:'角色名', width:'25%',  dataIndex:'name',flex:1},
        {
            header:'操作',
            xtype: 'actioncolumn',
            width: 70,
            align: 'center',
            items: [{
                icon: 'ext/images/add.png',
                tooltip:'添加',
                handler:function(grid, rowIndex, colIndex){
                    var name = grid.selModel.store.data.getAt(rowIndex).data.name;

                    Ext.Ajax.request({
                        url: 'account/setRole?id='+ id + '&name=' +name,
                        success: function (response, options) {
                            Ext.MessageBox.alert('成功', '添加成功');
                            assignedRoleStore.reload();
                            unassignRoleStore.reload();
                        },
                        failure: function (response, options) {
                            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                        }
                    });
                }
            }]
        }
    ],
    bbar:Ext.create('Ext.PagingToolbar',{
        store:unassignRoleStore
    })
});

Ext.define('assignedRoleStore', {
    extend: 'Ext.data.Model',
    defaultType:'textfield',
    fields: [
        {name: 'name'}
    ]
});

var assignedRoleStore = Ext.create('Ext.data.Store', {
    layout:'fit',
    model: 'assignedRoleStore',
    proxy:{
        type:'ajax',
        url:'account/getRole',
        reader:{
            type:'json',
            totalProperty: 'totalCount',
            root:'data'
        }
    }
});

var assignedRoleGrid = Ext.create('Ext.grid.Panel',{
    layout:'fit',
    store:assignedRoleStore,
    width:300,
    height:conheight+100,
    columns:[
        {header:'角色名', width:'50%',  dataIndex:'name',flex:1},
        {
            header:'操作',
            xtype: 'actioncolumn',
            width: 70,
            align: 'center',
            items: [{
                icon: 'ext/images/delete.png',
                handler:function(grid, rowIndex, colIndex){
                    var name = grid.selModel.store.data.getAt(rowIndex).data.name;

                    Ext.Ajax.request({
                        url: 'account/removeRole?id='+ id + '&name=' +name,
                        success: function (response, options) {
                            Ext.MessageBox.alert('成功', '删除成功');
                            assignedRoleStore.reload();
                            unassignRoleStore.reload();
                        },
                        failure: function (response, options) {
                            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                        }
                    });
                }
            }]
        }
    ],
    bbar:Ext.create('Ext.PagingToolbar',{
        store:assignedRoleStore
    })
});

var roleWin  = Ext.create('Ext.window.Window', {
    title: '角色信息列表',
    height: 380,
    width: 680,
    closeAction:'hide',
    defaults:{
        margin:'5'
    },
    layout:'column',
    items:[{
            xtype:'fieldset',
            title: '已拥有的角色',
            collapsible: true,
            items:assignedRoleGrid
        },{
            xtype:'fieldset',
            title: '未拥有的角色',
            collapsible: true,
            items:unassignRoleGrid
        }],
    listeners:{hide:function(){
        roleWin.removeAll(false);
    }},
    buttons:[{
        text:'test',
        handler:function(){
            roleWin.removeAll(false);
            roleWin.add(mainForm);
        }
    }]
})



Ext.onReady(function(){

    Ext.define('account', {
        extend: 'Ext.data.Model',
        defaultType:'textfield',
        fields: [
            {name: 'id'},
            {name: 'pid'},
            {name: 'account'},
            {name: 'phone'},
            {name: 'roles'}
        ]
    });

    var accountStore = Ext.create('Ext.data.Store',{
        layout:'fit',
        model: 'account',
        pageSize:20,
        proxy:{
            type:'ajax',
            url:'account/getData',
            reader:{
                type:'json',
                totalProperty: 'totalElements',
                root:'content'
            }
        }
    });


    var searchForm = Ext.create('Ext.form.Panel', {
        title:'账号信息',
        width:'100%',
        height:65,
        frame:true,
        style:'border-bottom-width: 0px;',
        region:'center',
        layout:  'column',
        fieldDefaults:{
            labelAlign:'right'
        },
        defaults:{
            xtype: 'textfield',
            margin:'5',
            labelWidth:40,
            width:150
        },

        items: [{
            fieldLabel: '账号',
            name: 'account'
        },{
            fieldLabel: '工号',
            name: 'pid'
        }, {
            fieldLabel: '电话',
            name: 'phone'
        },{
            xtype:'button',
            text:'搜索',
            name: 'search',
            width:100,
            handler:function(){
                accountStore.setProxy({

                    type:'ajax',
                    url:'account/getData',
                    extraParams:{
                        pid:searchForm.getForm().findField('pid').getValue(),
                        account:searchForm.getForm().findField('account').getValue(),
                        phone:searchForm.getForm().findField('phone').getValue()
                    },
                    reader:{
                        type:'json',
                        totalProperty: 'totalElements',
                        root:'content'
                    }
                })
                accountStore.loadPage(1);
            }

        }]
    });

    var accountGrid = Ext.create('Ext.grid.Panel',{
        height:conheight - 64,
        enableColumnHide:false,
        sortableColumns:false,
        aotuheight:true,
        layout:'fit',
        store:accountStore,
        selModel: Ext.create('Ext.selection.CheckboxModel'),
        columns:[
            {hidden:true, dataIndex:'id'},
            {header:'账号', width:'10%',  dataIndex:'account'},
            {header:'工号', width:'10%',  dataIndex:'pid'},
            {header:'电话', width:'10%',  dataIndex:'phone'},
            {header:'角色组', width:'35%', dataIndex:'roles'},
            {
                header:'操作',
                flex:1,
                xtype: 'actioncolumn',
                width: 50,
                items: [{
                    icon   : 'ext/images/cog_edit.png',
                    handler: function(grid, rowIndex) {
                        var id = grid.getStore().getAt(rowIndex).get('id');
                        location.href= rootPath + "account/accountCreate?id=" + id;
                    }
                },{
                    icon   : 'ext/images/delete.png',
                    handler: function(grid, rowIndex) {
                        var id = grid.getStore().getAt(rowIndex).get('id');

                        Ext.MessageBox.confirm("服务器信息","是否删除数据",function(e){
                            if(e=="yes"){
                                Ext.Ajax.request({
                                    url: 'account/delete?id='+id,
                                    method: 'POST',
                                    success: function () {
                                        Ext.MessageBox.alert('成功', '删除成功');
                                        accountStore.reload();
                                    },
                                    failure: function (response) {
                                        Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                    }
                                });
                            }
                        });
                    }
                }]}
        ],
        tbar:Ext.create('Ext.toolbar.Toolbar',{
            items:[
                {
                    xtype:Ext.create('Ext.Button', {
                        text: '新增',
                        handler:function(){

                            location.href= rootPath + "account/accountCreate";
                        }
                    })
                },'-',{
                    xtype:Ext.create('Ext.Button', {
                        text: '批量删除',
                        listeners:{click:function(){
                            var record = accountGrid.getSelectionModel().getSelection();
                            var id = [];

                            if(record.length == 0){
                                Ext.MessageBox.alert('服务器信息', '未选中记录');
                            }else if(record.length > 0){
                                for(var i = 0;i < record.length;i++){
                                    id.push(record[i].get('id'))
                                }
                                id.join(',');

                                Ext.MessageBox.confirm("服务器信息","是否删除数据",function(e){
                                    if(e=="yes"){
                                        Ext.Ajax.request({
                                            url: 'account/delete?id='+id,
                                            method: 'POST',
                                            success: function () {
                                                Ext.MessageBox.alert('成功', '删除成功');
                                                accountStore.reload();
                                            },
                                            failure: function (response) {
                                                Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                            }
                                        });
                                    }
                                });
                            }
                        }}
                    })
                },'-',{
                    xtype:Ext.create('Ext.Button', {
                        text: '配置账号角色',
                        handler:function(){
                            var rec = accountGrid.getSelectionModel().getSelection();
                            if(rec.length == 1){

                                id = accountGrid.getSelectionModel().getSelection()[0].get('id');

                                assignedRoleStore.setProxy({

                                    type:'ajax',
                                    url:'account/getRole',
                                    extraParams:{
                                        id:rec[0].get('id')
                                    },
                                    reader:{
                                        type:'json',
                                        totalProperty: 'totalCount',
                                        root:'data'
                                    }
                                })

                                unassignRoleStore.setProxy({
                                    type:'ajax',
                                    url:'role/getUnassignRole',
                                    extraParams:{
                                        id:id
                                    },
                                    reader:{
                                        type:'json',
                                        totalProperty: 'totalCount',
                                        root:'data'
                                    }
                                })

                                unassignRoleStore.load();
                                assignedRoleStore.load();
                                roleWin.show();
                            }else{
                                Ext.MessageBox.alert('失败', '只能选中一行');
                            }
                        }
                    })
                }
            ]
        }),
        bbar:Ext.create('Ext.PagingToolbar',{
            displayInfo: true,
            store:accountStore,
            pageSize:5,
            emptyMsg:"没有数据",
            displayMsg:"显示从{0}条数据到{1}条数据，共{2}条数据"
        })
    });

    accountStore.load();
    searchForm.render( "navigation");
    accountGrid.render( "navigation");
});

