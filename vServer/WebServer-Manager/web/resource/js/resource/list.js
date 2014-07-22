//格式化页面
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);

Ext.define('Resource', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'pid'},
        {name:'name'},
        {name:'category'},
        {name:'createUser'},
        {name: 'updateTime', type:'data'}
    ],
    idProperty: 'pid'
});

Ext.onReady(function(){

	Ext.QuickTips.init();

    storeParam = {
        name:'',
        category:[]
    };

    // create the data store
    var store = Ext.create('Ext.data.Store', {
        model: 'Resource',
        autoLoad:true,
		pageSize: 50,
        proxy: {
            type:'ajax',
            url: 'resource/list/get',
            extraParams:storeParam,
            reader: {
                root: 'content',
                totalProperty: 'totalElements'
            },
            simpleSortMode: true
        }
    });


    // create the Grid
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        stateId: 'stateGrid',
        loadMask:true,
		border:false,
		tbar:['查询数据','-', {
            xtype:'textfield',
            width:200,
			text:'',
            listeners: {
                change:function(fd, nv, ov){
                    storeParam.name = nv;
                }
            }
		}, '->', {
			text:'更新数据',
            handler:function(){
                store.reload({
                    params:storeParam
                });
            }
		}, '-', {
			text:'创建资源',
            handler:function(){
                win.show();
            }
		}],
        columns: [{
				text:'编码',
				dataIndex:'pid',
				width:130
			},{
                text     : '名称',
                flex     : 1,
                sortable : true,
                dataIndex: 'name'
            },
            {
                text     : '类型',
                width    : 75,
                dataIndex: 'category'
            },
            {
                text     : '创建人',
                width    : 100,
                sortable : false,
                dataIndex: 'createUser'
            },
            {
                text     : '更新时间',
                width    : 150,
                sortable : true,
                dataIndex: 'updateTime'
            },
            {
				text:'操作',
                menuDisabled: true,
                sortable: false,
                xtype: 'actioncolumn',
                width: 100,
                items: [{
                    icon   : 'resource/icon/note_edit.png',
                    tooltip: '编辑组件',
                    handler: function(grid, rowIndex, colIndex) {
                        var pid = grid.getStore().getAt(rowIndex).get('pid');
                        location.href = "resource/" + pid + "/context";
                    }
                }, {
                    icon   : 'resource/icon/document-preview.png',
                    tooltip: '预览组件',
                    handler: function(grid, rowIndex, colIndex) {
                        var pid = grid.getStore().getAt(rowIndex).get('pid');
                        location.href = "resource/" + pid + "/page";
                    }
                }]
            }
        ],
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true
        },
        bbar:Ext.create('Ext.PagingToolbar', {
            store:store,
            emptyMsg:'没有数据显示'
        })
    });


	var categoryStore = Ext.create('Ext.data.TreeStore', {
        fields:['id', 'text', 'category', 'remark', 'leaf'],
		root: catedata
	});

    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items: [{
            region: 'north',
            xtype:'toolbar',
            margins: '0 0 5 0',
			border:false,
			items:['组件资源管理器', '->', {
				text:'返回首页'
			}, '-', {
				text: '刷新'
			}]
        }, {
            region: 'west',
            collapsible: true,
            margins: '2 2 5 5',
            width: 300,
			minWidth:300,
            xtype: 'tabpanel',
            split:true,
            splitterResize:true,
			minTabWidth:100,
            items:[{
                xtype:'treepanel',
				title:'类型结构',
                border:false,
                rootVisible:false,
				store: categoryStore,
                tbar:['更新', '->', {
                    text:'展开'
                }]
            }, {
                title:'组件导航',
                xtype:'treepanel',
                border:false,
                store: categoryStore
            }]
        }, {
			id:'context',
            region: 'center',
            margins: '2 5 5 2',
            title:'资源列表',
			layout:'fit',
			items:grid
        }]
    });


    win = Ext.create('Ext.window.Window', {
        title:'请选择创建的资源类型',
        height:300,
        width:500,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items:{
            id:'selTree',
            xtype:'treepanel',
            rootVisible:false,
            margin:'5',
            frame:true,
            border:false,
            store: categoryStore
        },
        buttons:[{
            text:'确定',
            handler:function(){
                var rid = win.getComponent('selTree').getSelectionModel().getLastSelected().get('category');
                var form = document.getElementById("form");
                form.action = "resource/" + rid + "/create";
                form.submit();
            }
        }, {
            text:'取消',
            handler:function(){
                win.close();
            }
        }]
    });


});