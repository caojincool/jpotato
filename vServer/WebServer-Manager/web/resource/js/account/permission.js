/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-18
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */

Ext.onReady(function () {

    var pid="";
    var isModify=false;

    Ext.define('permission', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id',     type: 'string'},
            {name: 'name',     type: 'string'},
            {name: 'remark',     type: 'string'}
        ]
    });

    var permissionStore = Ext.create('Ext.data.TreeStore', {
        model: 'permission',
        proxy: {
            type: 'ajax',
            url: 'permission/getData'
        },
        folderSort: true
    });

    var permissionTree = Ext.create('Ext.tree.Panel', {
        title: '权限浏览',
        width: '100%',
        height:conheight+1,
        renderTo: Ext.get("navigation"),
        rootVisible: false,
        enableColumnHide:false,
        sortableColumns:false,
        multiSelect: false,
        store: permissionStore,
        multiSelect: false,
        tbar:Ext.create('Ext.toolbar.Toolbar',{
            items:[{
                xtype:Ext.create('Ext.Button', {
                    text: '重置所有权限到只有根',
                    handler:function(){
                        location.href= rootPath + "permission/resetPermission";
                    }
                })
            }]
        }),
        singleExpand: true,
        columns: [
        {
            hidden:true,
            dataIndex: 'pid'
        },{
            xtype: 'treecolumn',
            header: '权限名称',
            flex: 1,
            sortable: true,
            dataIndex: 'name'
        },{
            header: '描述',
            flex: 1,
            dataIndex: 'remark',
            sortable: true,
            align: 'center'
        }
            ,{
            header:'操作',
            xtype: 'actioncolumn',
            width: 70,
            align: 'center',
            items: [{
                icon: 'ext/images/add.png',
                tooltip:'添加',
                handler:function(grid, rowIndex, colIndex){
                    pid = grid.selModel.store.data.getAt(rowIndex).data.id;
                    permissionWin.title="添加";
                    Ext.getCmp("name").reset();
                    Ext.getCmp("remark").reset();
                    isModify=false;
                    permissionWin.show();
                }
            },{
                icon   : 'ext/images/cog_edit.png',
                tooltip:'修改',
                handler: function(grid, rowIndex, colIndex) {
                    pid = grid.selModel.store.data.getAt(rowIndex).data.id;
                    var rec = grid.selModel.store.data.getAt(rowIndex)
                    isModify=true;
                    permissionWin.title="修改";
                    permissionForm.getForm().loadRecord(rec);
                    permissionWin.show();
                }
            },{
                icon   : 'ext/images/delete.png',
                tooltip:'删除',
                handler: function(grid, rowIndex, colIndex) {
                    pid= grid.selModel.store.data.getAt(rowIndex).data.id;
                    Ext.MessageBox.confirm("警告","确定要删除该项及子集",function(e){
                        if(e=="yes"){
                            Ext.Ajax.request({
                                url: 'permission/delete?pid='+pid,
                                method: 'GET',
                                success: function (response, options) {
                                    Ext.MessageBox.alert('成功', '删除成功');
                                    permissionStore.reload();
                                },
                                failure: function (response, options) {
                                    Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                }
                            });
                        }
                    });
                }
            }]}
        ]
    });

    var permissionForm = Ext.create('Ext.form.Panel', {
        region     : 'center',
        bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
        labelWidth : 100,
        defaults:{
            xtype:'textfield'
        },
        items      : [
            {
                fieldLabel:'权限名称',
                id:'name',
                name:'name'
            },{
                fieldLabel:'描述',
                id:'remark',
                name:'remark'
            }
        ]
    });
    var permissionWin = Ext.create('Ext.window.Window', {
        title:'添加权限',
        height:300,
        width:300,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items:permissionForm,
        buttons:[{
            text:'确定',
            handler:function(){
                if(isModify){
                    permissionForm.form.url= 'permission/update?pid=' + pid;
                }else{
                    permissionForm.form.url= 'permission/add?parentpid=' + pid;
                }

                permissionForm.form.submit({
                    waitMsg : ' ......',
                    success:function(action,form){
                        Ext.Msg.alert('成功', "操作成功");
                        permissionStore.reload();
                        permissionWin.close();
                    },
                    failure:function(action,form){
                        Ext.Msg.alert('错误', action.failureType);
                    }});
            }
        }, {
            text:'取消',
            handler:function(){
                permissionWin.close();
            }
        }]
    });
});
