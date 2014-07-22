/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-22
 * Time: 上午8:53
 * To change this template use File | Settings | File Templates.
 */

Ext.Loader.setConfig({enabled:true});

var roleName;

var permissionStore = Ext.create('Ext.data.Store', {
    fields: [ 'text'],
    data : [
        { "text":'允许'},
        { "text":'拒绝'},
        { "text":'隐藏'}
    ]
});

var permissionComboBox = Ext.create('Ext.form.ComboBox', {
    fieldLabel: '权限状态',
    labelAlign:'right',
    emptyText:'请分配权限',
    name:'state',
    width:300,
    editable: false,
    store: permissionStore,
    displayField: 'text',
    valueField: 'text'
});

    var stateForm = Ext.create('Ext.form.Panel',{
        defaultType:'textfield',
        frame:true,
        fieldDefaults:{
            labelWidth:60,
            labelAlign:'right'
        },
        layout:{
            type:'table',
            columns:1
        },
        items: [
            {
                fieldLabel: 'id',
                name: 'id',
                readOnly:true,
                hidden:true
            },{
                fieldLabel: '角色名',
                readOnly:true,
                name: 'roleName'
            },{
                fieldLabel: '权限名',
                readOnly:true,
                name: 'name',
                allowBlank:false
            },{
                xtype:permissionComboBox
            }]
    });

var stateWin  = Ext.create('Ext.window.Window', {
    title: '角色信息',
    aotuheight: true,
    closeAction:'hide',
    width: 400,
    items:stateForm,
    buttons: [{
        text: '保存',
        handler:function(){
            if(stateForm.getForm().isValid()){
                stateForm.form.url = 'role/setPermission';

                stateForm.submit({
                    waitMsg : ' ......',
                    success:function(action,form){
                        Ext.Msg.show({
                            title:'服务器信息',
                            msg:'操作成功',
                            buttons: Ext.Msg.YES
                        });
                        if(!permissionWin.isHidden()){
                            permissionWin.hide();
                        }
                        permissionStateStore.load();
                        stateWin.hide();
                    },
                    failure:function(action,form){
                        Ext.Msg.show({
                            title:'服务器信息',
                            msg:'操作失败,该角色名已存在',
                            buttons: Ext.Msg.YES
                        });
                    }});
            }
        }
    },{
        text: '取消',
        handler:function(){
            stateWin.hide();
        }
    }]
})

Ext.define('permissionSate', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id'},
        {name: 'name'},
        {name: 'state'}
    ]
});

var permissionStateStore = Ext.create('Ext.data.Store', {
    layout:'fit',
    model: 'permissionSate',
    proxy:{
        type:'ajax',
        url:'role/getPermission',
        reader:{
            type:'json',
            totalProperty: 'totalCount',
            root:'data'
        }
    }
});

var permissionStateGrid = Ext.create('Ext.grid.Panel',{
    layout:'fit',
    height:conheight - 64,
    store:permissionStateStore,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    columns:[
        { dataIndex:'id' ,hidden:true},
        {header:'权限名', width:'50%',  dataIndex:'name'},
        {header:'状态', width:'50%', dataIndex:'state',flex:1}
    ],
    listeners:{celldblclick:function(){
        var rec = permissionStateGrid.getSelectionModel().getSelection();
        stateForm.getForm().reset();
        stateForm.getForm().loadRecord(rec[0]);
        stateForm.getForm().findField('roleName').setValue(roleName);
        stateWin.show();
    }},
    tbar:Ext.create('Ext.toolbar.Toolbar',{
        items:[
            {
                xtype:Ext.create('Ext.Button', {
                    text: '新增',
                    handler:function(){
                        permissionWin.show();
                    }
                })
            },{
                xtype:Ext.create('Ext.Button', {
                    text: '设为默认',
                    listeners:{click:function(){
                        var record = permissionStateGrid.getSelectionModel().getSelection();
                        var id = [];

                        if(record.length == 0){
                            Ext.MessageBox.alert('服务器信息', '至少选中一条记录');
                        }else if(record.length > 0){
                            for(var i = 0;i < record.length;i++){
                                id.push(record[i].get('id'))
                            }
                            id.join(',');

                            Ext.MessageBox.confirm("服务器信息","设为默认会把该权限从列表中移除,确定要这样设置吗",function(e){
                                if(e=="yes"){
                                    Ext.Ajax.request({
                                        url: 'role/removePermission?id='+id+"&roleName="+roleName,
                                        method: 'POST',
                                        success: function (response, options) {
                                            Ext.MessageBox.alert('成功', '删除成功');
                                            permissionStateStore.reload();
                                        },
                                        failure: function (response, options) {
                                            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                        }
                                    });
                                }
                            });
                        }
                    }}
                })
            }
        ]
    }),
    bbar:Ext.create('Ext.PagingToolbar',{
        displayInfo: true,
        store:permissionStateStore,
        emptyMsg:"没有数据",
        displayMsg:"显示从{0}条数据到{1}条数据，共{2}条数据"
    })

});

var permissionStateWin  = Ext.create('Ext.window.Window', {
    title: '配置角色权限',
    height: 400,
    width: 600,
    closeAction:'hide',
    layout: 'fit',
    items:[{
        xtype:permissionStateGrid
    }]
})

    //定义tree model
    Ext.define('permission', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'name',     type: 'string'},
            {name: 'remark',     type: 'string'}
        ]
    });

//定义treestroe
    var permissionTreeStore = Ext.create('Ext.data.TreeStore', {
        model: 'permission',
        proxy: {
            type: 'ajax',
            url: 'permission/getData'
        },
        folderSort: true
    });

//创建treepanel
    var permissionTree = Ext.create('Ext.tree.Panel', {
        rootVisible: false,
        store: permissionTreeStore,
        enableColumnHide:false,
        sortableColumns:false,
        multiSelect: false,
        width:650,
        layout:'fit',
        columns: [{
            xtype: 'treecolumn',
            text: '权限名称',
            width:'50%',
            sortable: true,
            dataIndex: 'name'
        },{
            text: '描述',
            width:'50%',
            sortable: true,
            dataIndex: 'remark'
        }],
        listeners:{celldblclick:function( ){
            var rec = permissionTree.getSelectionModel().getSelection();
            stateForm.getForm().reset();
            stateForm.getForm().loadRecord(rec[0]);
            stateForm.getForm().findField('roleName').setValue(roleName);
            stateWin.show();
        }}
    });

//tree所在的window
    var permissionWin  = Ext.create('Ext.window.Window', {
        title: '配置角色权限',
        height: 300,
        width: 800,
        closeAction:'hide',
        layout: 'fit',
        items:[{
            xtype:permissionTree
        }]
    })

Ext.onReady(function(){


    function show(winname){
        roleForm.getForm().reset();
        winname.show();
    }


    //新增数据的form
    var roleForm = Ext.create('Ext.form.Panel', {
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
                fieldLabel: '角色名',
                name: 'name',
                allowBlank:false
            },{
                fieldLabel: '备注',
                name: 'remark'
            }]
    })

    Ext.define('role', {
        extend: 'Ext.data.Model',
        idProperty: 'role',
        defaultType:'textfield',
        fields: [
            {name: 'name'},
            {name: 'count'},
            {name: 'remark'}
        ]
    });

    var roleStore = Ext.create('Ext.data.Store', {
        layout:'fit',
        model: 'role',
        pageSize:20,
        proxy:{
            type:'ajax',
            url:'role/getData',
            reader:{
                type:'json',
                totalProperty: 'totalElements',
                root:'content'
            }
        }
    });

    var searchForm = Ext.create('Ext.form.Panel', {
        title:'角色信息',
        frame:true,
        width:'100%',
        height:65,
        frame:true,
        style:'border-bottom-width: 0px;',
        region:'center',
        layout:  'column',
        defaults:{
            xtype: 'textfield',
            margin:'5',
            labelWidth:50,
            width:150
        },

        items: [{
            fieldLabel: '角色名',
            name: 'name'
        },{
            xtype:'button',
            text:'搜索',
            name: 'search',
            width:100,
            handler:function(){

                roleStore.setProxy({

                    type:'ajax',
                    url:'role/getData',
                    extraParams:{
                        name:searchForm.getForm().findField('name').getValue()
                    },
                    reader:{
                        type:'json',
                        totalProperty: 'totalElements',
                        root:'content'
                    }
                })
                roleStore.loadPage(1);
            }
        }]
    });

    var roleGrid = Ext.create('Ext.grid.Panel',{
        layout:'fit',
        height:conheight - 64,
        store:roleStore,
        columns:[
            {header:'角色名', width:'25%',  dataIndex:'name'},
            {header:'角色组人数', width:'15%', dataIndex:'count'},
            {header:'描述', width:'40%', dataIndex:'remark'},
            {
                header:'操作',
                flex:1,
                xtype: 'actioncolumn',
                width: 50,
                items: [{
                    icon   : 'ext/images/cog_edit.png',
                    handler: function(grid, rowIndex, colIndex) {
                        roleName = grid.getStore().getAt(rowIndex).get('name');
                        permissionStateStore.setProxy({
                            type:'ajax',
                            url:'role/getPermission',
                            extraParams:{
                                name:roleName
                            },
                            reader:{
                                type:'json',
                                totalProperty: 'totalCount',
                                root:'data'
                            }
                        }),
                        permissionStateStore.load();
                        permissionStateWin.show();
                    }
                },{
                    icon   : 'ext/images/delete.png',
                    handler: function(grid, rowIndex, colIndex) {
                        var name = grid.getStore().getAt(rowIndex).get('name');

                        Ext.MessageBox.confirm("服务器信息","是否删除数据",function(e){
                            if(e=="yes"){
                                Ext.Ajax.request({
                                    url: 'role/delete?name='+name,
                                    method: 'POST',
                                    success: function (response, options) {
                                        Ext.MessageBox.alert('成功', '删除成功');
                                        roleStore.reload();
                                    },
                                    failure: function (response, options) {
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
                            show(roleCreateWin);
                        }
                    })
                }
            ]
        }),
        bbar:Ext.create('Ext.PagingToolbar',{
            displayInfo: true,
            store:roleStore,
            emptyMsg:"没有数据",
            displayMsg:"显示从{0}条数据到{1}条数据，共{2}条数据"
        })

    });

//新增数据的window
    var roleCreateWin  = Ext.create('Ext.window.Window', {
        title: '角色信息',
        aotuheight: true,
        closeAction:'hide',
        width: 450,
        items:roleForm,
        buttons: [{
            text: '保存',
            handler:function(){
                if(roleForm.getForm().isValid()){

                    roleForm.form.url = 'role/save';

                    roleForm.submit({
                        waitMsg : ' ......',
                        success:function(action,form){
                            Ext.Msg.show({
                                title:'服务器信息',
                                msg:'操作成功',
                                buttons: Ext.Msg.YES
                            });
                            roleStore.reload();
                            roleCreateWin.hide();
                        },
                        failure:function(action,form){
                            Ext.Msg.show({
                                title:'服务器信息',
                                msg:'操作失败,该角色名已存在',
                                buttons: Ext.Msg.YES
                            });
                        }});
                }
            }
        },{
            text: '取消',
            handler:function(){
                roleCreateWin.hide();
            }
        }]
    })

    roleStore.load();
    searchForm.render("navigation");
    roleGrid.render("navigation");
});