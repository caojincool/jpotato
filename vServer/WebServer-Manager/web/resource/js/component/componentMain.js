/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-16
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
//
//

Ext.onReady(function () {
    Ext.QuickTips.init();
    var  pageSize = 2;
    var parentId="";
    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'pid'},
            {name:'name'},
            {name:'category'},
            {name:'createUser'},
            {name: 'updateTime'}
        ],
        idProperty: 'pid'
    });

    storeParam = {
        name:'',
        category:''
    };
    var datastore = Ext.create('Ext.data.TreeStore', {
        model: 'Resource',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            //the store will get the content from the .json file
            url: 'component/componentmain/list/get?page=1&limit='+pageSize
        },
        folderSort: true
    });

    var storepaging =Ext.create('Ext.data.Store', {
        model: 'Resource',
        autoLoad:true,
        pageSize:pageSize,
        proxy: {
            type:'ajax',
            url: 'component/componentmain/list/getpaging',
            extraParams:storeParam,
            reader: {
                root: 'content',
                totalProperty: 'totalElements'
            }
        }
    });

    var isfirst=true;
    storepaging.on('beforeload', function (store, options) {
        if(!isfirst) {
            datastore.proxy.url= 'component/componentmain/list/get?page='+options.page+'&limit='+options.limit;
            datastore.load({
                params:storeParam
            });
        }
        isfirst=false;
    });


    Ext.create('Ext.panel.Panel', {
        width:'100%',
        height:65,
        frame:true,
        region:'center',
        style:'border-bottom-width: 0px;',
        layout: 'column',
        renderTo: Ext.get("navigation"),
        title: '组件管理',
        defaults:{
            margin:'5',
            labelWidth:60,
            width:200
        },
        items: [{
            xtype: 'textfield',
            fieldLabel: '组件名称',
            id:'comname',
            name: 'comname',
            emptyText: '请输入组件名称'
        },{
            xtype: 'textfield',
            fieldLabel: '类型',
            id:'typename',
            name: 'typename',
            emptyText: '请输入类型'
        },
            {
                xtype: 'button',
                text : '查询',
                width:50,
                listeners: {
                    click: function() {
                        // this == the button, as we are in the local scope
                        isfirst=false;
                        storeParam.name=Ext.getCmp('comname').getValue();
                        storeParam.category =Ext.getCmp('typename').getValue();
                        storepaging.load({
                            params:storeParam
                        });
                    }
                }

            }]
    });
    //Ext.ux.tree.TreeGrid is no longer a Ux. You can simply use a tree.TreePanel

    var tree = Ext.create('Ext.tree.Panel', {
        width: '100%',
        height:conheight-65,
        renderTo: Ext.get("navigation"),
        rootVisible: false,
        store: datastore,
        enableColumnHide:false,
        sortableColumns:false,
        multiSelect: false,
        singleExpand: false,
        //the 'columns' property is now 'headers'
        columns: [{
            xtype: 'treecolumn', //this is so we know which column will show the tree
            text: '组件',
            flex: 2,
            sortable: true,
            dataIndex: 'name'
        }, {
            text: 'pid',
            flex: 1,
            dataIndex: 'pid',
            sortable: true
        },{
            text: '创建人',
            flex: 1,
            dataIndex: 'createUser',
            sortable: true
        },{
            text: '类型',
            flex: 1,
            dataIndex: 'category',
            sortable: true
        }, {
            text: '创建时间',
            flex: 1,
            dataIndex: 'updateTime',
            sortable: true
        },{
            xtype: 'actioncolumn',
            width: 70,
            header:'操作',
            align: 'center',
            items: [{
                icon: 'ext/images/add.png',
                tooltip:'添加',
                handler:function(grid, rowIndex, colIndex){
                    parentId=grid.selModel.store.data.getAt(rowIndex).data.pid;
                    var category= grid.selModel.store.data.getAt(rowIndex).data.category;
                    typstore.removeAll();
                    Ext.getCmp("compontentname").reset();
                    Ext.getCmp('category').setValue("");
                    Ext.getCmp('category').setRawValue("");
                    typstore.proxy.url=typurl+'?parentid='+parentId+'&category='+category;
                    typstore.load();
                    win.show();
                }
            },{
                icon   : 'ext/images/cog_edit.png',
                tooltip:'修改',
                handler: function(grid, rowIndex, colIndex) {
                    var pid=grid.selModel.store.data.getAt(rowIndex).data.pid;
                    var category= grid.selModel.store.data.getAt(rowIndex).data.category;
                    window.top.location.href=rootPath+ "component/componentmain/update?category="+category+"&pid="+pid;
                }
            },{
                icon   : 'ext/images/delete.png',
                tooltip:'删除',
                handler: function(grid, rowIndex, colIndex) {
                    var clickvalue=grid.selModel.store.data.getAt(rowIndex).data.pid;
                    Ext.MessageBox.confirm("警告","确定要删除该项及子集",function(e){
                        if(e=="yes"){
                            Ext.Ajax.request({
                                url: 'component/componentmain/delete?pid='+clickvalue,
                                method: 'GET',
                                success: function (response, options) {
                                    Ext.MessageBox.alert('成功', '删除成功');
                                    storepaging.load({
                                        params:storeParam
                                    });
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
        tbar: Ext.create('Ext.Toolbar',{
            items: [{
                text: '新增',
                icon: 'ext/images/add.png',
                handler: function () {
                    parentId="";
                    typstore.removeAll();
                    Ext.getCmp("compontentname").reset();
                    Ext.getCmp('category').setValue("");
                    Ext.getCmp('category').setRawValue("");
                    typstore.proxy.url=typurl;
                    typstore.load();
                    win.show();
                }
            }]
        }),
        bbar: Ext.create('Ext.PagingToolbar',{
            pageSize: pageSize,
            store: storepaging,
            prependButtons: true,
            displayInfo: true,
            displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg: '没有数据显示'
        })
    });

    tree.expandAll();




    Ext.define('Category', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid',     type: 'string'},
            {name: 'name',     type: 'string'},
            {name: 'category', type: 'string'},
            {name:'createUser',type:'string'}
        ]
    });
    var typurl='component/componenttype/list/getnotrootcategory';
    var typstore = Ext.create('Ext.data.Store', {
        model: 'Category',
        proxy: {
            type: 'ajax',
            //the store will get the content from the .json file
            url: typurl
        },
        folderSort: true
    });

    // Setup the form panel
    var formPanel = Ext.create('Ext.form.Panel', {
        region     : 'center',
        bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
        standardSubmit:true,
        labelWidth : 100,
        defaults:{
            xtype:'textfield'
        },
        items: [
            {
                fieldLabel:'组件名称',
                name:'compontentname',
                id:'compontentname'
            },{
                xtype: 'combo',
                fieldLabel:'组件类型',
                editable: false,
                displayField: 'category',
                hiddenName: 'pid',
                name:'category',
                id:'category',
                store: typstore
            }
        ]
    });
    win = Ext.create('Ext.window.Window', {
        title:'添加组件',
        height:300,
        width:300,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items:formPanel,
        buttons:[{
            text:'确定',
            handler:function(){
                if(Ext.getCmp('category').getValue()=='')
                {
                    Ext.Msg.alert('提示', '请选择类型');
                    return;
                }
                if(Ext.util.Format.trim(Ext.getCmp('compontentname').getValue())=='')
                {
                    Ext.Msg.alert('提示', '请输入组件名称');
                    return;
                }
                Ext.Msg.confirm('提示', '组件将立即创建,请认真填写相关信息',function(btn){
                    if(btn=='yes'){
                        formPanel.form.url='component/componentmain/add?parentid='+parentId;
                        formPanel.form.submit({waitMsg : ' ......'});
                    }
                });

//                formPanel.form.submit({ target:'_blank'});
            }
        }, {
            text:'取消',
            handler:function(){
                win.close();
            }
        }]
    });

});
