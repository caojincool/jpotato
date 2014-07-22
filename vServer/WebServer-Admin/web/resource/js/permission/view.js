/**
 * 权限浏览
 * User: dpyang
 * Date: 13-1-22
 * Time: 上午8:51
 */
Ext.onReady(function(){
    Ext.QuickTips.init();


    Ext.define('Permissions', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id'},            //权限数据库编码,唯一码
            {name: 'text'},          //权限Key可能重复
            {name: 'key'},           //权限名称
            {name: 'remark'}         //权限说明
        ],
        idProperty: 'id'
    });

    var store = Ext.create('Ext.data.TreeStore', {
        model: 'Permissions',
        proxy: {
            type: 'ajax',
            url: rootPath + 'permission/list/get'
        },
        folderSort: true,
        listeners:{
            load:function(st, root){
                function temp(node) {
                    if(node == null) return;
                    node.set('iconCls','node-left')//设置子节点样式
                   // Ext.Msg.alert('info',node.text);
                    if(node.childNodes != null) {
                        for(var i=0; i<node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                            //
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

    Ext.widget('panel',{
        id:'typePanel',
        frame: true,
        margin:5,
        layout: 'fit',
        height:790,
        renderTo: Ext.getBody(),
        items:[{
            title:'权限列表',
            id:'treePanel',
            margins: '2',
            store: store,
            xtype:'treepanel',
            singleExpand: true,
            rootVisible: false,
            columns:[{
                xtype: 'treecolumn',
                text: '权限节点',
                flex:1,
                sortable: false,
                dataIndex: 'key'
            },{
                text: '权限名称',
                width:160,
                dataIndex: 'text',
                sortable: false
            },{
                text: '权限说明',
                width:300,
                dataIndex: 'remark',
                sortable: false
            },{
                text     : '操作',
                width    : 70,
                align    : 'center',
                sortable : false,
                xtype    : 'actioncolumn',
                layout: 'vbox',
                items:[{
                    tooltip: '编辑',
                    iconCls:'action-column-margin',
                    icon: 'resource/images/icons/settings_16x16.png',
                    handler:function(grid, rowIndex, colIndex){
                        location.href=rootPath+"permission/modification?id="+grid.getStore().getAt(rowIndex).get('id');
                    }
                }]
            }]
        }]
    });

    Ext.getBody().on('DOMNodeRemovedFromDocument', function(){
        Ext.getCmp('permissionPanel').destroy();
    });

    Ext.EventManager.onWindowResize(function(){
        Ext.getCmp('permissionPanel').doLayout();
    });
});