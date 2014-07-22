Ext.Loader.setConfig({
    enabled: true
});Ext.Loader.setPath('Ext.ux', '../ext/ux');

Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.ux.CheckColumn'
]);

Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    var panelEl = Ext.get('rolesPanel');
    var win;

    Ext.define('role', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'name'},
            {name: 'describe'},
            {name:'checked', type:'bool', defaulValue:false }
        ],
        idProperty:'name'
    });

    var selectionStore = Ext.create('Ext.data.Store', {
        model: 'role'
    });

    //选择角色
    function selectionRoles(){
        if(!win){
            //角色集合
            var store = Ext.create('Ext.data.Store', {
                model: 'role',
                pageSize:10,
                proxy: {
                    type: 'ajax',
                    url: rootPath + 'role/list/get',
                    reader: {
                        root: 'entity.content',
                        totalProperty: 'entity.totalElements'
                    },
                    simpleSortMode: true
                },
                autoLoad:true
            });

            //临时储存选中的权限的模型
            var temp=[];

            var rolePanel = Ext.create('Ext.grid.Panel', {
                id:'roleGridPanel',
                border:false,
                loadMask: true,
                store: store,
                columnLines: true,
                disableSelection:false,
                columns: [{
                    text: '角色名称',
                    width: 120,
                    sortable: true,
                    dataIndex: 'name'
                },{
                    text: '角色描述',
                    flex: 1,
                    sortable: true,
                    dataIndex: 'describe',
                    align: 'left'
                },{
                    xtype: 'checkcolumn',
                    stopSelection: false,
                    text: '选择',
                    width: 60,
                    align:'center',
                    dataIndex:'checked',
                    sortable: false
                }],
                bbar:Ext.create('Ext.PagingToolbar', {
                    store: store,
                    displayInfo: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示'
                })
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
                items: rolePanel,
                buttons:[{
                    text:'确定',
                    handler:function(){
                        this.up('window').hide();

                        //这里取得选中的行
                        var rcs = rolePanel.getStore();

                        //如果重复选择就把以前的删除然后再插入
                        if(temp.length>0)
                            temp.splice(0,temp.length)

                        for(var i=0; i<rcs.getCount(); i++) {
                            if(rcs.getAt(i).get('checked')){
                                temp.push({
                                    name:rcs.getAt(i).get('name'),
                                    describe:rcs.getAt(i).get('describe'),
                                    checked:rcs.getAt(i).get('checked')
                                });
                            }
                        }
                        selectionStore.loadData(temp);
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


    var panel = Ext.create('Ext.panel.Panel', {
        id:'rolesPanel',
        layout:'fit',
        height:450,
        frame: true,
        renderTo:panelEl,
        items:[{
            title:'所属角色',
            region: 'center',
            margins: '2 2 0 2',
            store: selectionStore,
            xtype:'gridpanel',
            tbar:['->', {
                text:'选择角色',
                iconCls:'add-16',
                handler:selectionRoles
            }],
            viewConfig: {
                stripeRows: true,
                enableTextSelection: true
            },
            columns:[{
                text     : '角色名称',
                width     : 150,
                sortable : false,
                dataIndex: 'name'
            }, {
                text     : '角色说明',
                flex    : 1,
                sortable : false,
                dataIndex: 'describe'
            }, {
                text     : '操作',
                width    : 80,
                align    : 'center',
                sortable : false,
                xtype    : 'actioncolumn',
                layout: 'vbox',
                items    : [{
                    tooltip: '删除',
                    iconCls:'action-column-margin',
                    icon:'resource/images/icons/deletelayer_16x16.png',
                    handler:function(grid,rowIndex){
                        selectionStore.removeAt(rowIndex)
                    }
                }]
            }]
        }]
    });

    //提交以前把选择的权限循环生成html代码
    Ext.fly('next').on('click',function(){
        //参数
        var parms={
            roles:[]
        };

        selectionStore.each(function(record){
            var role=record.get('name');
            parms.roles.push(role);
        });
        Ext.getBody().mask('正在提交');

        Ext.Ajax.request({
            url: rootPath + 'account/expand/doRoles',
            jsonData:parms,
            method:'POST',
            success:function(response){
                var obj = Ext.JSON.decode(response.responseText);
                if(obj.entity=='success')
                    location.href=rootPath+'account/expand/permissions';
            }
        });

        Ext.getBody().unmask();
    });
});