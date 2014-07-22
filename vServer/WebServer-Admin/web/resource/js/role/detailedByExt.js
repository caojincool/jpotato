/**
 * 角色详细信息之ext布局
 * User: dpyang
 * Date: 13-2-4
 * Time: 上午9:38
 */
Ext.onReady(function(){

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
    var panel=Ext.create('Ext.tab.Panel',{
        title:'角色详细信息',
        height:450,
        frame:true,
        defaults:{margins:'0 0 5 0'},
        renderTo:Ext.get('roledetailedByExt'),
        items:[{
            xtype:'panel',
            title:'基本信息',
            layout: {
                type:'vbox',
                padding:'5',
                align:'center'
            },
            items:[{
                xtype:'label',
                text: '角色名称：'+name,
                width:850,
                padding:'20 0 0 0',
                border:true,
                flex:1
            },{
                xtype:'label',
                text: '角色描述：'+describe,
                border:true,
                width:850,
                flex:5
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
            bbar:Ext.create('Ext.PagingToolbar',{
                store: aStore,
                displayInfo: true,
                displayMsg: 'Displaying topics {0} - {1} of {2}',
                emptyMsg: "没有数据显示"
            })
        }]
    });
})
