/**
 * 选择权限
 * User: dpyang
 * Date: 13-1-24
 * Time: 下午12:00
 */
Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    var panelEl = Ext.get('permissionPanel');

    var win;

    //自定义模型权限模型
    Ext.define('Permission', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'key', type: 'string'},
            {name:'fullkey',type:'string'},
            {name:'text',type:'string'},
            {name:'remark',type:'string'},
            {name: 'permission', type: 'int', defaulValue: 3}
        ]
    });

    //被选中的权限节点和权限
    var permissionstore=Ext.create('Ext.data.ArrayStore',{
        model: 'Permission'
    });

    //选择权限
    function selectPermissions(){
        if(!win){
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
            win = Ext.create('widget.window', {
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
        win.show();
    }

    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1
    });

    var grid=Ext.create('Ext.grid.Panel', {
        title: '权限信息',
        height: 434,
        store: permissionstore,
        frame: true,
        tbar: Ext.create('Ext.Toolbar',{
            items: ['->',{
                text: '增加权限',
                icon: rootPath+'resource/images/icons/add16.png',
                handler:selectPermissions
            }]
        }),
        renderTo: panelEl,
        selModel: {
            selType: 'cellmodel'
        },
        columns:[{
            header: '权限节点',
            align    : 'left',
            width    : 70,
            flex: 1,
            sortable : false,
            dataIndex: 'fullkey'
        }, {
            header:'权限名称',
            align:'left',
            width:60,
            flex:1,
            dataIndex:'text'
        },{
            header:'权限',
            align:'left',
            width:80,
            flex:1,
            dataIndex:'remark'
        },{
            header: '权限',
            width    : 60,
            align    : 'center',
            sortable : false,
            dataIndex:'permission',
            renderer:function convertPermission(val) {
                switch(val)
                {
                    case 1:
                        return '允许'
                    case 2:
                        return '隐藏'
                    case 4:
                        return '拒绝'
                    case 3:
                        return '未知'
                }
            },
            editor: Ext.create('Ext.form.ComboBox',{
                typeAhead: true,
                transform: 'permission',
                valueField: 'key',
                displayField: 'ke',
                store:Ext.create('Ext.data.Store', {
                    fields: ['key', 'ke'],
                    data: [
                        {key:1,ke:"允许"},
                        {key:2,ke:"隐藏"},
                        {key:3,ke:"未知"},
                        {key:4,ke:"拒绝"}
                    ]
                }),
                listClass: 'x-combo-list-small'
            })
        }],
        plugins: [cellEditing]
    });

    //提交以前把选择的权限循环生成html代码
    Ext.fly('next').on('click',function(){

        var param = {
            name: Ext.get('name').getValue(),
            describe: Ext.get('remark').getValue(),
            permissions:[]
        };

        //原来判断未知就不插入
        permissionstore.each(function(record){
            var p = record.get('permission');
            //if( p != 3) {
                param.permissions.push({
                    fullkey:record.get('fullkey'),
                    permission: p
                });
            //}
        });

        Ext.getBody().mask('正在提交');

        Ext.Ajax.request({
            url: rootPath + 'role/select/permissions/create',
            jsonData:param,
            method:'POST',
            success:function(response){
                var obj = Ext.JSON.decode(response.responseText);
                if(obj.entity=='success')
                    location.href=rootPath+'role/select/account/create';
            }
        });

        Ext.getBody().unmask();
    });
});