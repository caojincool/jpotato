/**
 * 创建角色之选择帐号
 * User: dpyang
 * Date: 13-1-30
 * Time: 上午9:26
 */
Ext.Loader.setConfig({
    enabled: true
});
Ext.Loader.setPath('Ext.ux',rootPath+ 'ext/ux');

Ext.require([
    'Ext.selection.CellModel',
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.form.*',
    'Ext.ux.CheckColumn'
]);

if (window.location.search.indexOf('scopecss') !== -1) {
    Ext.scopeResetCSS = true;
}

Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
    var panelEl = Ext.get('accountPanel');
    var win;
    var pagesize=15;
    //选中的数据
    var selectdata=new Array();

    //已选帐号的模型
    Ext.define('Account', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id'},
            {name: 'account'},
            {name: 'showName'},
            {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
        ],
        idProperty: 'id'
    });

    //已选帐号的数据集
    var accountStore = Ext.create('Ext.data.Store', {
        model: 'Account'
    });

    //选择帐号
    function selectAccount(){
        if(!win){
            Ext.define('Accounts', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'id'},
                    {name: 'account'},
                    {name: 'showName'},
                    {name:'checked',type:'bool',defaultValue:false},
                    {name: 'createTime', type: 'date',  dateFormat: 'c', defaultValue: null}
                ],
                idProperty: 'id'
            });

            var sAccountStore=Ext.create('Ext.data.Store',{
                model: 'Accounts',
                pageSize:  pagesize ,
                proxy: {
                    type: 'ajax',
                    url: rootPath + 'account/list/get',
                    reader: {
                        root: 'entity.content',
                        totalProperty: 'entity.totalElements'
                    },
                    simpleSortMode: true
                },
                autoLoad:true
            })

            var selModel = Ext.create('Ext.selection.CheckboxModel',{
                //checkOnly:false
                //injectCheckbox:'check'
            });

            var grid = Ext.create('Ext.grid.Panel', {
                store: sAccountStore,
                border:false,
                loadMask: true,
                selModel: selModel,
                columnLines: true,
                disableSelection: false,
                columns:[{
                    id: 'topic',
                    text: "登录帐号",
                    dataIndex: 'account',
                    flex: 1,
                    sortable: false
                },{
                    text: "昵称",
                    dataIndex: 'showName',
                    width: 70,
                    align: 'center',
                    sortable: true
                },{
                    id: 'last',
                    text: "创建时间",
                    dataIndex: 'createTime',
                    xtype:'datecolumn',
                    format: 'Y-m-d H:i:s',
                    width: 150,
                    sortable: true
                }],
                bbar: Ext.create('Ext.PagingToolbar', {
                    store: sAccountStore,
                    displayInfo: true,
                    prependButtons: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示',
                    listeners:{
                        //翻页前把当页信息保存到全局变量
                        beforechange:function(t,e){
                            //检查当前页面是否有选中的信息
                            var temp=grid.getSelectionModel().getSelection();//获取模型数组
                            for(var i=0;i<temp.length;i++){
                                selectdata.push({
                                    id:temp[i].get('id'),
                                    account:temp[i].get('account'),
                                    showName:temp[i].get('showName'),
                                    createTime:temp[i].get('createTime')
                                });
                            }
                        },
                        //翻页后检查当页和全局页是否有相同项
                        change:function(t,e){
//                            var temp=grid.getStore();
//                            for(var i=0;i<selectdata.length;i++){
//                                for(var j=0;j<temp.getCount();j++)
//                                {
//                                    if(selectdata[i].get('id')==temp[j].get('id'))
//                                    {
//                                        temp.getSelectionMode().select(j,true,false);
//                                    }
//                                }
//                            }
                        }
                    }
                })
            });

            win = Ext.create('widget.window', {
                title: '选择帐号',
                closeAction: 'hide',
                width: 600,
                height: 400,
                minWidth: 600,
                minHeight: 400,
                layout: 'fit',
                modal:true,
                resizable: true,
                items: grid,
                buttons:[{
                    text:'确定',
                    handler:function(){
                        var temp=grid.getSelectionModel().getSelection();
                        for(var i=0;i<temp.length;i++){
                            selectdata.push({
                                id:temp[i].get('id'),
                                account:temp[i].get('account'),
                                showName:temp[i].get('showName'),
                                createTime:temp[i].get('createTime')
                            });
                        }
                        this.up('window').hide();
                        accountStore.loadData(selectdata);
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

    var panel = Ext.create('Ext.grid.Panel', {
        title:'账号信息',
        height:434,
        frame: true,
        store: accountStore,
        renderTo:panelEl,
        tbar:['->', {
            text:'选择账号',
            iconCls:'add-16',
            handler:selectAccount
        }],
        columns:[{
            text: '编号',
            align    : 'center',
            width    : 120,
            sortable : false,
            dataIndex: 'id'
        }, {
            text     : '账号',
            width     : 160,
            sortable : true,
            dataIndex: 'account'
        }, {
            text     : '昵称',
            width    : 150,
            sortable : true,
            dataIndex: 'showName'
        }, {
            text     : '创建时间',
            flex:1,
            sortable : true,
            xtype    : 'datecolumn',
            format   : 'Y-m-d H:i:s',
            dataIndex: 'createTime'
        }]
    });

    //提交到后台
    Ext.fly('next').on('click',function(){
         var parms={
             ras:[]
         };
        accountStore.each(function(record){
            var accounid=record.get('account');

            parms.ras.push({
                accountId:accounid,
                roleId:Ext.get('roleid').getValue()
            })
        });

        Ext.getBody().mask('正在提交...')
        Ext.Ajax.request({
            url:rootPath+'role/select/account/create',
            method:'POST',
            jsonData:parms,
            success:function(response){
                var obj=Ext.JSON.decode(response.responseText);
                var text=obj.entity;
                if(text=='success')
                    location.href=rootPath+'role/finash/create';
            }
        })
        Ext.getBody.unmask();
    })
})