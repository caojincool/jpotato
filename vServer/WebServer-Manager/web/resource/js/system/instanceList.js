/**
 * Created with IntelliJ IDEA.
 * User: gm
 * Date: 12-11-16
 * Time: 上午8:56
 * To change this template use File | Settings | File Templates.
 */

Ext.require([
    'Ext.grid.*',
    'Ext.data.*'
]);

Ext.define('Company', {
    extend: 'Ext.data.Model',
    fields: ['id', 'name', 'ip', 'sysName','address','time']
});

Ext.define('Category', {
    extend: 'Ext.data.Model',
    fields: ['cpid', 'cname']
});

Ext.onReady(function(){
    //验证提示
    Ext.QuickTips.init();

//    var Time = function (v) {
//        return new Date(Date.parse('yyyy-MM-dd'));
//    };

    storeParam = {
        name:'',
        sysName:''
    };

    var cusstore = Ext.create('Ext.data.Store', {
        model: 'Company',
        remoteSort:true,
        pageSize:10,
        proxy: {
            type: 'ajax',
            url: 'system/instance/list/get',
            extraParams:storeParam,
            reader:{
                type:'json',
                root:'entity',
                totalProperty:'totalCount'
            },
            simpleSortMode:true
        },
        autoLoad:true
    });

    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.get("navigation"),
        width:'100%',
        title:'搜索',
        height:65,
        frame:true,
        region:'center',
        style:'border-bottom-width: 0px;',
        layout: 'column',
        defaults:{
            margin:'5',
            labelWidth:60,
            width:200
        },

        items:[{
            xtype: 'textfield',
            fieldLabel: '实例名称',
            id:'txtName',
            name: 'txtName',
            emptyText: '请输入实例名称'
        },{
            xtype: 'combo',
            id:'txtCombox',
            name: 'txtCombox',
            fieldLabel: '使用系统',
            editable: false,  //false则不可编辑，默认为 true
            typeAhead: true,  //延迟查询，配合typeAheadDelay:3000,使用，默认250
            resizble: true,
            triggerAction: 'all',  //请设置为”all”,否则默认为”query”的情况下，你选择某个值后，再此下拉时，只出现匹配选项，如果设为”all”的话，每次下拉均显示全部选项
            lazyRender: true,
            mode: 'local',
            emptyText: '请选择系统',
            displayField: 'cname',   //显示文本字段
            valueField:'cpid',        //value值字段
            hiddenName: 'cpid',       //真正提交时此combo的name，请一定要注意
            msgTarget: 'side',
            store: Ext.create('Ext.data.Store', {
                model: 'Category',
                remoteSort:true,
                proxy: {
                    type: 'ajax',
                    url: 'system/instance/list/getcategory',
                    extraParams:storeParam,
                    reader:{
                        type:'json',
                        root:'entity'
                    },
                    simpleSortMode:true
                }
            })
        },{
            xtype:'button',
            text: '搜索',
            width:50,
            handler: function () {

                storeParam.name=Ext.getCmp('txtName').getValue();
                storeParam.sysName=Ext.getCmp('txtCombox').getValue();

                //更新Store
                cusstore.setProxy({
                    type: 'ajax',
                    url: 'system/instance/list/get',
                    extraParams:storeParam,
                    reader:{
                        type:'json',
                        root:'entity',
                        totalProperty:'totalCount'
                    },
                    simpleSortMode:true
                });
                //更新数据后，默认显示第一页，很重要
                cusstore.loadPage(1);
            }
        }]
    });


    var grid4 = Ext.create('Ext.grid.Panel', {
        id:'gd',
        store:cusstore,
        enableColumnHide : false,
        sortableColumns:false,
        height:conheight-65,
        width:'100%',
        columns: [
            { header: 'ID',width:180, dataIndex: 'id'},
            { header: '实例名称',flex     : 1, dataIndex: 'name'},
            { header: 'IP', dataIndex: 'ip' },
            { header: '使用系统', dataIndex: 'sysName' },
            { header: '地址',flex     : 1, dataIndex: 'address' },
            { header: '启用日期',width:120, dataIndex: 'time'},

            {
                xtype:'actioncolumn',
                header:'操作',
                align: 'center',
                width:100,
                items: [{
                    icon: 'ext/images/cog_edit.png',  // Use a URL in the icon config
                    tooltip: '修改',
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = grid.getStore().getAt(rowIndex);
                        location.href=""+rootPath+"system/instance/"+rec.get('id')+"/update";
                    }
                }, '-' , {
                    icon: 'ext/images/delete.png',  // Use a URL in the icon config
                    tooltip: '删除',
                    handler: function(grid, rowIndex, colIndex) {
                        Ext.MessageBox.confirm("确认",'是否确认删除？',function(btn){
                            if('yes' == btn){
                                var rec = grid.getStore().getAt(rowIndex);
                                location.href=""+rootPath+"system/instance/"+rec.get('id')+"/delete";
                            }
                        });
                    }
                }, '-' , {
                    icon: 'ext/images/view.png',  // Use a URL in the icon config
                    tooltip: '查看',
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = grid.getStore().getAt(rowIndex);
                        location.href=""+rootPath+"system/instance/"+rec.get('id')+"/preview";
                    }
                }]
            }
        ],

        loadMask: { msg: '正在加载数据，请稍等……' },
        renderTo: Ext.get("navigation"),

        tbar: Ext.create('Ext.Toolbar',{
            items: [{
                text: '新增',
                icon: 'ext/images/add.png',
                handler: function () {
                    location.href= ""+rootPath+"system/instance/create";
                }
            }]
        }),

//        bbar: Ext.create('Ext.PagingToolbar',{
//            pageSize: 5,
//            store:cusstore,
//            prependButtons: true,
//            displayInfo: true,
//            displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
//            emptyMsg: '没有数据显示'
//        })

        dockedItems: [{
            xtype: 'pagingtoolbar',
            store: cusstore,
            dock: 'bottom',
            displayInfo: true
        }]
    });

});