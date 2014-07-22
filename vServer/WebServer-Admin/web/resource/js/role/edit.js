/**
 * 编辑角色信息
 * User: dpyang
 * Date: 13-2-6
 * Time: 下午1:24
 */
Ext.onReady(function(){

    var winPermission,winAccount,pagesize=15;

    //权限模型
    Ext.define('Permissions', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'key', type: 'string'},
            {name:'fullkey',type:'string'},
            {name:'text',type:'string'},
            {name:'remark',type:'string'},
            {name: 'permission', type: 'string'}
        ]
    });

    //帐号模型
    Ext.define('Accounts', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id',type:'string'},
            {name: 'account',type:'string'},
            {name: 'showName',type:'string'},
            {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
        ],
        idProperty: 'id'
    });

    //所有权限模型(树)
    Ext.define('Permission', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'key', type: 'string'},
            {name:'fullKey',type:'string'},
            {name:'text',type:'string'},
            {name:'remark',type:'string'},
            {name: 'permission', type: 'int'}
        ]
    });

    //权限信息集合
    var pStore = Ext.create('Ext.data.Store', {
        model: 'Permissions'
    });

    var aStore=Ext.create('Ext.data.Store',{
        model:'Accounts'
    })

    //转换
    function convertPermission(val) {
        switch(val)
        {
            case 'Allow':
                return '允许'
            case 'Hiddle':
                return '隐藏'
            case 'Deny':
                return '拒绝'
            default:
                return '未知'
        }
    }

    //选择权限信息
    function selectPermissions(){
        if(!winPermission){
            //在模型之中增加一个复选属性,这里增加了这个字段就可以使用Tree.getChecked
            Ext.define('Permissions', {
                extend: 'Ext.data.Model',
                fields: [
                    {name:'id',type:'string'},
                    {name:'key',type:'string'},
                    {name:'text',type:'string'},
                    {name:'remark',type:'string'},
                    {name:'fullKey',type:'string'},
                    {name:'checked', type:'bool', defaulValue:false }
                ],
                idProperty: 'Id'
            });

            //权限树形节点模型
            var store = Ext.create('Ext.data.TreeStore', {
                model: 'Permissions',
                proxy: {
                    type: 'ajax',
                    url: rootPath + 'permission/list/get',
                    simpleSortMode: true
                },
                autoLoad:true,
                listeners:{
                    load:function(st, root){
                        function temp(node) {
                            if(node == null) return;
                            node.set('iconCls','node-left')//设置子节点样式
                            if(node.childNodes != null) {
                                for(var i=0; i<node.childNodes.length; i++) {
                                    temp(node.childNodes[i]);
                                    node.set('iconCls','node-close');//设置
                                }
                            }
                        }
                        temp(root);
                    },
                    expand:function(st){
                        st.set('iconCls','node-open');
                    },
                    collapse:function(st){
                        st.set('iconCls','node-close')
                    }
                }
            });

            //临时储存选中的权限的模型
            var temp=[];

            //这里只是需要定义显示的三个字段或者模型中存在的字段就可以
            var treeGrid = Ext.create('Ext.tree.Panel', {
                border:false,
                useArrows: true,
                rootVisible: false,
                store: store,
                multiSelect: true,
                singleExpand: true,
                columns: [{
                    xtype: 'treecolumn',
                    text: '权限节点',
                    flex: 2,
                    sortable: true,
                    dataIndex: 'key'
                },{
                    text: '权限名称',
                    flex: 1,
                    sortable: true,
                    dataIndex: 'text',
                    align: 'left'
                },{
                    text: '权限说明',
                    flex: 1,
                    dataIndex: 'remark',
                    sortable: false
                }
                ]
            });

            //创建窗体
            winPermission = Ext.create('widget.window', {
                title: '选择权限',
                closeAction: 'hide',
                width: 600,
                height: 400,
                minWidth: 600,
                minHeight: 400,
                layout: 'fit',
                modal:true,
                resizable: true,
                items: treeGrid,
                buttons:[{
                    text:'确定',
                    handler:function(){
                        this.up('window').hide();

                        //这里取得选中的行
                        var rcs = treeGrid.getChecked();

                        //如果重复选择就把以前的删除然后再插入
                        if(temp.length>0)
                            temp.splice(0,temp.length)

                        for(var i=0; i<rcs.length; i++) {
                            temp.push({
                                key:rcs[i].get('key'),
                                fullkey:rcs[i].get('fullKey'),
                                text:rcs[i].get('text'),
                                remark:rcs[i].get('remark'),
                                permission:'3'
                            });
                        }
                        permissionstore.loadData(temp);
                    }
                },{
                    text:'取消',
                    handler:function(){
                        this.up('window').hide();
                    }
                }]
            });
        }
        winPermission.show();
    }

    //选择帐号信息
    function selectAccount(){
        if(!winAccount){
            Ext.define('Accounts', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'id'},
                    {name: 'account'},
                    {name: 'showName'},
                    {name:'checked',type:'bool',defaultValue:false},
                    {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
                ],
                idProperty: 'id'
            });

            var sAccountStore=Ext.create('Ext.data.Store',{
                model: 'Accounts',
                pageSize:  pagesize ,
                proxy: {
                    type: 'ajax',
                    url: rootPath + 'account/list/get',
                    reader: {
                        root: 'entity.content',
                        totalProperty: 'entity.totalElements'
                    },
                    simpleSortMode: true
                },
                autoLoad:true
            })

            var selModel = Ext.create('Ext.selection.CheckboxModel',{
                //checkOnly:false
                //injectCheckbox:'check'
            });

            var grid = Ext.create('Ext.grid.Panel', {
                store: sAccountStore,
                border:false,
                loadMask: true,
                selModel: selModel,
                columnLines: true,
                disableSelection: false,
                columns:[{
                    id: 'topic',
                    text: "登录帐号",
                    dataIndex: 'account',
                    flex: 1,
                    sortable: false
                },{
                    text: "昵称",
                    dataIndex: 'showName',
                    width: 70,
                    align: 'center',
                    sortable: true
                },{
                    id: 'last',
                    text: "创建时间",
                    dataIndex: 'createTime',
                    xtype:'datecolumn',
                    format: 'Y-m-d H:i:s',
                    width: 150,
                    sortable: true
                }],
                bbar: Ext.create('Ext.PagingToolbar', {
                    store: sAccountStore,
                    displayInfo: true,
                    prependButtons: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示',
                    listeners:{
                        //翻页前把当页信息保存到全局变量
                        beforechange:function(t,e){
                            //检查当前页面是否有选中的信息
                            var temp=grid.getSelectionModel().getSelection();//获取模型数组
                            for(var i=0;i<temp.length;i++){
                                selectdata.push({
                                    id:temp[i].get('id'),
                                    account:temp[i].get('account'),
                                    showName:temp[i].get('showName'),
                                    createTime:temp[i].get('createTime')
                                });
                            }
                        },
                        //翻页后检查当页和全局页是否有相同项
                        change:function(t,e){
//                            var temp=grid.getStore();
//                            for(var i=0;i<selectdata.length;i++){
//                                for(var j=0;j<temp.getCount();j++)
//                                {
//                                    if(selectdata[i].get('id')==temp[j].get('id'))
//                                    {
//                                        temp.getSelectionMode().select(j,true,false);
//                                    }
//                                }
//                            }
                        }
                    }
                })
            });

            winAccount = Ext.create('widget.window', {
                title: '选择帐号',
                closeAction: 'hide',
                width: 600,
                height: 400,
                minWidth: 600,
                minHeight: 400,
                layout: 'fit',
                modal:true,
                resizable: true,
                items: grid,
                buttons:[{
                    text:'确定',
                    handler:function(){
                        var temp=grid.getSelectionModel().getSelection();
                        for(var i=0;i<temp.length;i++){
                            selectdata.push({
                                id:temp[i].get('id'),
                                account:temp[i].get('account'),
                                showName:temp[i].get('showName'),
                                createTime:temp[i].get('createTime')
                            });
                        }
                        this.up('window').hide();
                        accountStore.loadData(selectdata);
                    }
                },{
                    text:'取消',
                    handler:function(){
                        this.up('window').hide();
                    }
                }]
            });
        }
        winAccount.show();
    }

    //所有权限信息(树形)
    var treeStore = Ext.create('Ext.data.TreeStore', {
        model: 'Permission',
        proxy: {
            type: 'ajax',
            url: rootPath + 'permission/list/get',
            simpleSortMode: true
        },
        autoLoad:true,
        listeners:{
            load:function(st, root){
                function temp(node) {
                    if(node == null) return;

                    //如果节点的fullkey和permissionkeys[i]的fullkey相等
                    for(i=0;i<permissionkeys.length;i++){
                        if(node.get('fullKey')==permissionkeys[i].key){
                            pStore.add({
                                key:node.get('key'),
                                fullkey:node.get('fullKey'),
                                text:node.get('text'),
                                remark:node.get('remark'),
                                permission:permissionkeys[i].permission
                            })
                            break;
                        }
                    }

                    //node.set('iconCls','node-left')//设置子节点样式
                    if(node.childNodes != null) {
                        for(var i=0; i<node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                            //node.set('iconCls','node-close');//设置
                        }
                    }
                }
                temp(root);
            }
        }
    });

    treeStore.load();

    var tabs = Ext.widget({
        xtype: 'form',
        id: 'tabForm',
        border: false,
        layout:'fit',
        fieldDefaults: {
            labelWidth: 75,
            msgTarget: 'side'
        },
        items: {
            xtype:'tabpanel',
            activeTab: 0,
            border:false,
            defaults:{
                layout: 'anchor'
            },
            items:[{
                title:'基本信息',
                defaultType: 'textfield',
                defaults: {
                    padding: 10,
                    anchor: '100%'
                },
                items: [{
                    fieldLabel: '角色名称',
                    id:'name',
                    name: 'name',
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    value: name
                },{
                    xtype:'textarea',
                    fieldLabel: '角色描述',
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'describe',
                    id:'describe',
                    flex: 1,
                    value: describe
                }]
            },{
                xtype:'panel',
                id:'perpanel',
                border:false,
                layout:'fit',
                title:'权限列表',
                items:[{
                    id:'treePanel',
                    store: pStore,
                    xtype:'gridpanel',
                    border:false,
                    columns:[{
                        header: '权限节点',
                        width:160,
                        sortable: false,
                        dataIndex: 'fullkey'
                    },{
                        header: '权限名称',
                        width:160,
                        dataIndex: 'text',
                        sortable: false
                    },{
                        header:'权限说明',
                        flex:1,
                        dataIndex:'remark'
                    },{
                        header:'权限',
                        width:60,
                        renderer:convertPermission,
                        dataIndex:'permission'
                    }],
                    tbar:['->',{
                        text:'增加权限',
                        icon: rootPath+'resource/images/icons/add16.png',
                        handler:selectPermissions
                    }],
                    bbar:Ext.create('Ext.PagingToolbar',{
                        store: pStore,
                        displayInfo: true,
                        displayMsg: 'Displaying topics {0} - {1} of {2}',
                        emptyMsg: "没有数据显示"
                    })
                }]
            },{
                xtype:'panel',
                id:'accountpanel',
                layout: 'fit',
                title:'帐号列表',
                border:false,
                items:[{
                    id:'acoountPanel',
                    store: aStore,
                    border:false,
                    xtype:'gridpanel',
                    singleExpand: true,
                    rootVisible: false,
                    columns:[{
                        header: '登录名',
                        width:160,
                        sortable: false,
                        dataIndex: 'account'
                    },{
                        header: '昵称',
                        width:160,
                        dataIndex: 'showName',
                        sortable: false
                    },{
                        header:'创建时间',
                        flex:1,
                        xtype:'datecolumn',
                        format:'Y-m-D H:i:s',
                        dataIndex:'createTime'
                    }]
                }],
                tbar:['->',{
                    text:'增加帐号',
                    icon: rootPath+'resource/images/icons/add16.png',
                    handler:selectAccount
                }],
                bbar:Ext.create('Ext.PagingToolbar',{
                    store: aStore,
                    displayInfo: true,
                    displayMsg: 'Displaying topics {0} - {1} of {2}',
                    emptyMsg: "没有数据显示"
                })
            }]
        }
    });

    var panel=Ext.create('Ext.panel.Panel',{
        title:'角色详细信息',
        height:450,
        frame:true,
        defaults:{margins:'0 0 5 0'},
        renderTo:Ext.get('roledetailedByExt'),
        layout:'fit',
        items:tabs
    });
})
