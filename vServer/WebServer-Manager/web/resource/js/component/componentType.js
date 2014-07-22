/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-16
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
Ext.onReady(function () {
    Ext.QuickTips.init();

    Ext.define('Category', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid',     type: 'string'},
            {name: 'name',     type: 'string'},
            {name: 'category', type: 'string'},
            {name:'remark',     type:'string'},
            {name:'createUser',type:'string'}
        ]
    });

    var store = Ext.create('Ext.data.TreeStore', {
        model: 'Category',
        proxy: {
            type: 'ajax',
            //the store will get the content from the .json file
            url: 'component/componenttype/list/get'
        },
        folderSort: true
    });

    //Ext.ux.tree.TreeGrid is no longer a Ux. You can simply use a tree.TreePanel
    var tree = Ext.create('Ext.tree.Panel', {
        title: '类别管理',
        width: '100%',
        height:conheight,
        renderTo: Ext.get("navigation"),
        rootVisible: false,
        enableColumnHide:false,
        sortableColumns:false,
        store: store,
        multiSelect: false,
        singleExpand: false,
        //the 'columns' property is now 'headers'
        columns: [{
            xtype: 'treecolumn', //this is so we know which column will show the tree
            text: '类别名称',
            flex: 2,
            sortable: true,
            dataIndex: 'name'
        },{
            text: '类别',
            flex: 1,
            dataIndex: 'category',
            sortable: true
        },{
            text: '说明',
            flex: 1,
            dataIndex: 'remark',
            sortable: true
        },{
            text: '创建',
            flex: 1,
            dataIndex: 'createUser',
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
                    pid=grid.selModel.store.data.getAt(rowIndex).data.pid;
                    Ext.getCmp("typename").reset();
                    Ext.getCmp("componenttype").reset();
                    Ext.getCmp("componentremark").reset();
                    Ext.getCmp("comicon").reset();
                    win.show();
                }
            }
//
//            ,{
//                icon   : 'ext/images/cog_edit.png',
//                tooltip:'修改',
//                handler: function(grid, rowIndex, colIndex) {
//                    pid=grid.selModel.store.data.getAt(rowIndex).data.pid;
//                    win.show();
//                    Ext.getCmp('typename').setValue(grid.selModel.store.data.getAt(rowIndex).data.name);
//                }
//            }
                , {
                icon   : 'ext/images/delete.png',
                tooltip:'删除',
                handler: function(grid, rowIndex, colIndex) {
                    pid=grid.selModel.store.data.getAt(rowIndex).data.pid;
                    Ext.MessageBox.confirm("警告","确定要删除该项及子集",function(e){
                        if(e=="yes"){
                            Ext.Ajax.request({
                                url: 'component/componenttype/delete?pid='+pid,
                                method: 'GET',
                                success: function (response, options) {
                                    Ext.MessageBox.alert('成功', '删除成功');
                                    store.reload();
                                },
                                failure: function (response, options) {
                                    Ext.MessageBox.alert('失败', '请求超时或网络故错误编号' + response.status);
                                }
                            });
                        }
                    });
                }
            }
            ]}
        ]
    });

    tree.expandAll();

   var pid="";

    // Setup the form panel
    var formPanel = Ext.create('Ext.form.Panel', {
        bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
        id:'formPane',
        name:'formPane',
        labelWidth : 100,
        width      : 325,
        margin: 5,
        standardSubmit:true,
        border:false,
        defaults:{
            xtype:'textfield'
        },
        items      : [{
            fieldLabel:'类别名称',
            id:'typename',
            name:'typename',
            anchor:'90%'
        },{
            fieldLabel:'类别',
            id:'componenttype',
            name:'componenttype',
            anchor:'90%'
        },{
            fieldLabel:'说明',
            id:'componentremark',
            name:'componentremark',
            anchor:'90%'
        },{
             xtype: 'filefield',
             id: 'comicon',
             name:'comicon',
             emptyText: '请选择文件',
             fieldLabel: '图标',
             anchor:'90%',
             buttonText:'浏览...'
        }]
    });

    win = Ext.create('Ext.window.Window', {
        title:'添加类别',
        height:190,
        width:340,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items:formPanel,
        buttonAlign: 'center',
        buttons:[{
            text:'确定',
            handler:function(){
                if(jQuery.trim(Ext.getCmp('typename').getValue())==''){
                    Ext.Msg.alert('系统提示','请输入类别名称！');
                    return;
                }
                if(jQuery.trim(Ext.getCmp('componenttype').getValue())==''){
                    Ext.Msg.alert('系统提示','请输入类别！');
                    return;
                }
                if(jQuery.trim(Ext.getCmp('comicon').getValue())==''){
                    Ext.Msg.alert('系统提示','请选择图标！');
                    return;
                }

//                Ext.Ajax.request({
//                    url : 'component/componenttype/add?pid='+pid,
//                    isUpload : true,
//                    form : 'formPane',
//                    success : function(response) {
//                        Ext.MessageBox.alert("上传成功，文本文件内容：", response.responseText);
//                        store.reload();
//                        win.close();
//                    }
//                });

//                formPanel.form.url= 'component/componenttype/add?pid='+pid;
                formPanel.form.submit({waitMsg : ' ......',
                      url: 'component/componenttype/add?pid='+pid

//                    success:function(action,form){
//                        store.reload();
//
//                        win.close();
//                    },
//                    failure:function(action,form){
//                        Ext.MessageBox.alert('错误',form.result.message);
//                    }

                });
            }
        }, {
            text:'取消',
            handler:function(){
                win.close();
            }
        }]
    });
});