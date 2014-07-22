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
            {name: 'pid'},           //权限名称
            {name: 'category'},         //权限说明
            {name: 'parentPid'}
        ],
        idProperty: 'id'
    });

    var store = Ext.create('Ext.data.TreeStore', {
        model: 'Permissions',
        proxy: {
            type: 'ajax',
            url:  'component/'+category+'/'+pid+'/tree'
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
        height:250,
        renderTo:  Ext.fly('resourceTree'),
        items:[{
            title:'子组件列表',
            id:'treePanel',
            margins: '2',
            store: store,
            xtype:'treepanel',
            singleExpand: true,
            rootVisible: false,
            columns:[{
                xtype: 'treecolumn',
                text: '组件编码',
                flex:1,
                sortable: false,
                dataIndex: 'pid'
            },{
                text: '组件名称',
                width:160,
                dataIndex: 'text',
                sortable: false
            },{
                text: '组件类型',
                width:300,
                dataIndex: 'category',
                sortable: false,
                renderer:function(category) {
                    return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="'+rootPath+'component/componenttype/icon/'+category+'" /> ' + categorys[category];
                }
            },{
                text: '操作',
                width:300,
                dataIndex: 'pid',
                sortable: false,
                renderer:function(pid, meta, rc) {

                    var cate=rc.get("category");
                    cate=Ext.util.Format.lowercase(cate);
                   return '<a href="/component/'+cate+'/'+pid+'/edit?cate='+rc.get("category")+'" target="_blank"><img style="vertical-align: middle;margin-right: 5px;" name="img" src="resource/images/icons/small/black/edit.png" /></a> ';

                }
            }]
        }]
    });


});