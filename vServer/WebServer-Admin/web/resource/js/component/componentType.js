Ext.onReady(function(){
    Ext.QuickTips.init();


    Ext.define('Category', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id'},
            {name: 'icon'},
            {name: 'text'},
            {name: 'remark'},
            {name: 'category'}
        ],
        idProperty: 'id'
    });

    var store = Ext.create('Ext.data.TreeStore', {
        model: 'Category',
        proxy: {
            type: 'ajax',
            url: rootPath + 'component/componenttype/list/get'
        },
        folderSort: true,
        listeners:{
            load:function(st, root){
                function temp(node) {
                    if(node == null) return;
                    var icon = rootPath + 'component/componenttype/icon/' + node.get('category').toLowerCase();
                    node.set('icon', icon);
                    if(node.childNodes != null) {
                        for(var i=0; i<node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                        }
                    }
                }
                temp(root);
            }
        }
    });

    Ext.widget('panel',{
        id:'typePanel',
        frame: true,
        margin:5,
        layout: 'fit',
        height:"645",
        renderTo: Ext.getBody(),
        items:[{
            title:'组件类型列表',
            id:'treePanel',
            margins: '2',
            store: store,
            xtype:'treepanel',
            singleExpand: true,
            rootVisible: false,
            columns:[{
                xtype: 'treecolumn',
                text: '类别名称',
                flex:1,
                sortable: false,
                dataIndex: 'text'
            },{
                text: '类别代码',
                width:160,
                dataIndex: 'category',
                sortable: false
            },{
                text: '说明',
                width:300,
                dataIndex: 'remark',
                sortable: false
            },{
                xtype: 'actioncolumn',
                width: 70,
                header:'操作',
                align: 'center',
                items: [{
                    icon: 'resource/images/icons/settings_16x16.png',
                    tooltip:'修改',
                    handler:function(grid, rowIndex, colIndex){
                        window.parent.location.href=rootPath+"component/componenttype/modification?id="+grid.getStore().getAt(rowIndex).get('id');
                     }
                },{
//                    icon: 'resource/icons/add16.png',
//                    tooltip:'添加',
//                    handler:function(grid, rowIndex, colIndex){
//                        location.href=rootPath+"component/componenttype/addtype?id="+grid.getStore().getAt(rowIndex).get('pid');
//                    }
                }]
            }]
        }]
    });

    Ext.on('DOMNodeRemovedFromDocument', function(){
        Ext.getCmp('typePanel').destroy();
    });

    Ext.EventManager.onWindowResize(function(){
        Ext.getCmp('typePanel').doLayout();
    });
});

