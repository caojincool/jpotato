/**
 * 导航挂载组件extjs文件
 * User: gm
 * Date: 13-1-11
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
var win = null;
Ext.onReady(function(){

    Ext.define('Category', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id'},
            {name: 'pid'},
            {name: 'name'},
            {name: 'remark'},
            {name: 'category'}
        ],
        idProperty: 'id'
    });

    var treeStore = Ext.create('Ext.data.TreeStore', {
        model: 'Category',
        root: {
            text: 'Ext JS',
            id: 'src',
            expanded: true
        },
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

    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'},
            {name: 'createUser'},
            {name: 'allowRoles'},
            {name: 'updateTime', type: 'date',  dateFormat: 'c', defaultValue: null},
            {name:'hasNav'}  //是否已挂载
        ],
        idProperty: 'id'
    });

    Ext.define('CombCategory', {
        extend: 'Ext.data.Model',
        fields: ['category', 'name']
    });

    //导航pid,用于过滤此导航已存在的挂载组件
    var navigatpid = document.getElementById("parentpid").value;

    var resourceParam = {
        name:'',
        category:''
    };

    var pagesize = 50;
    var store = Ext.create('Ext.data.Store', {
        model: 'Resource',
        pageSize:pagesize,
        proxy: {
            type: 'ajax',
            url: rootPath + 'component/navigation/list/getfornavgation?navigatpid='+navigatpid,
            reader: {
                root: 'entity.content',
                extraParams:resourceParam,
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad:true
    });

    //窗体
    win = Ext.create('Ext.window.Window', {
        title:'选择组件',
        height:460,
        width:800,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout: 'fit',
        bodyBorder:false,
        items: Ext.widget('panel',{
            border:false,
            layout:'border',
            id:'cuspanel',
            items:[{
                title:'查询条件',
                region: 'north',
                height: 80,
                margins: '2',
                items: Ext.create('Ext.form.Panel', {
                    height: 55,
                    bodyBorder:false,
                    layout:'column',
                    items: [{
                        columnWidth: .90,
                        border:false,
                        height:55,
                        layout: {
                            type: 'hbox',
                            align: 'middle'
                        },
                        items:[{
                            xtype: 'label',
                            text: '组件名称:',
                            margins: '0 10 0 20'
                        },{
                            xtype: 'textfield',
                            id:'comname',
                            name: 'comname',
                            width:140,
                            emptyText: '请输入组件名称'
                        },{
                                xtype: 'label',
                                text: '组件类型:',
                                margins: '0 10 0 20'
                            },{
                                xtype: 'combo',
                                id:'txtCombox',
                                name: 'txtCombox',
                                layout:'fit',
                                editable: false,  //false则不可编辑，默认为 true
                                typeAhead: true,  //延迟查询，配合typeAheadDelay:3000,使用，默认250
                                resizble: true,
                                triggerAction: 'all',  //请设置为”all”,否则默认为”query”的情况下，你选择某个值后，再此下拉时，只出现匹配选项，如果设为”all”的话，每次下拉均显示全部选项
                                lazyRender: true,
                                mode: 'local',
                                emptyText: '请选择组件类型',
                                displayField: 'name',   //显示文本字段
                                valueField:'category',        //value值字段
                                hiddenName: 'category',       //真正提交时此combo的name，请一定要注意
                                store: Ext.create('Ext.data.Store', {
                                    model: 'CombCategory',
                                    remoteSort:true,
                                    proxy: {
                                        type: 'ajax',
                                        url: rootPath + 'component/main/list/getcategory',
                                        extraParams:resourceParam,
                                        reader:{
                                            type:'json',
                                            root:'entity'
                                        },
                                        simpleSortMode:true
                                    }
                                })
                            }

                        ]
                    },{
                        columnWidth: .10,
                        height:55,
                        border:false,
                        bodyStyle: 'background:#ffffff',
                        layout: 'border',
                        items:[{
                            xtype: 'button',
                            region: 'center',
                            margins:'10',
                            iconAlign:'left',
                            iconCls:'search-22',
                            scale: 'medium',         //加上这句，图标才能显示完整，否则只显示16px宽度大小
                            text : '查询',
                            tooltip:'查询',
                            width:40,
                            height:30,
                            listeners: {
                                click: function() {
                                    resourceParam.name=Ext.getCmp('comname').getValue();
                                    resourceParam.category=Ext.getCmp('txtCombox').getValue();
                                    store.reload({
                                        params:resourceParam
                                    });
                                }
                            }
                        }]
                    }]
                })
            }, {
                title:'显示类别',
                region:'west',
                margins: '0 2 0 2',
                width: 200,
                split:true,
                xtype:'treepanel',
                useArrows: true,
                rootVisible: false,
                displayField:'name',
                store: treeStore,
                listeners:{
                    itemclick:function(view, rc, item){
                        var category = rc.get('category');

                        var grid = Ext.getCmp('cuspanel').getComponent('resourcegrid');
                        if(category == 'SYS') {
                            resourceParam.category = '';
                            grid.setTitle('显示全部的组件信息');
                        }
                        else {
                            resourceParam.category = category;
                            grid.setTitle('显示 (' + rc.get('name') + ') 下的组件信息');
                        }
                        store.reload({
                            params:resourceParam
                        });
                    }
                }
            },{
                title:'组件列表',
                itemId: 'resourcegrid',
                region: 'center',
                margins: '0 2 2 0',
                store: store,
                xtype:'gridpanel',
                viewConfig: {
                    stripeRows: true,
                    enableTextSelection: true,
                    getRowClass: function(record, rowIndex, rowParams, store){
                        return record.get("hasNav")==true ? "unenable" : "canenable";
                    }
                },
                columns:[{
                    text: '组件名称',
                    dataIndex:'name',
                    flex: 1
                },{
                    text     : '编号',
                    align    : 'left',
                    width    : 120,
                    sortable : false,
                    dataIndex: 'pid'
                }, {
                    text     : '类型名称',
                    width: 120,
                    dataIndex: 'category',
                    renderer:function(category) {
                        return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="'+rootPath+'component/componenttype/icon/'+category+'" /> ' + categorys[category];
                    }
                },
                    {
                    text     : '更新时间',
                    width    : 120,
                    sortable : true,
                    xtype    : 'datecolumn',
                    format   : 'Y-m-d H:i:s',
                    dataIndex: 'updateTime'
                }, {
                    text     : '创建人员',
                    width    : 80,
                    sortable : true,
                    align    : 'center',
                    dataIndex: 'createUser'
                }],
                bbar: Ext.create('Ext.PagingToolbar', {
                    store: store,
                    pageSize:pagesize,
                    prependButtons: true,
                    displayInfo: true,
                    displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                    emptyMsg: '没有数据显示'
                }),
                listeners:{
                    selectionchange:function(sm, sr){
                        if (sr.length) {
                            //判断只有hasNav不为true的才赋值，因为为true表示此导航下已挂载此组件
                            if(sr[0].data.hasNav!=true){
                                Ext.getCmp('compid2').setValue(sr[0].data.pid);
                                Ext.getCmp('comname2').setValue(sr[0].data.name);
                                Ext.getCmp('typename2').setValue(sr[0].data.category);
                            }
                        }
                    }
                }
            },{
                region: 'south',
                height: 30,
                margins: '2',
                layout: {
                    type: 'hbox',
                    align: 'middle'
                },
                items: [{
                    xtype: 'label',
                    text: '已选组件PID:',
                    margins: '0 10 0 25'
                },{
                    xtype: 'textfield',
                    id:'compid2',
                    name: 'compid2',
                    width:140,
                    readOnly:true
                },,{
                    xtype: 'label',
                    text: '已选组件名称:',
                    margins: '0 10 0 20'
                },{
                    xtype: 'textfield',
                    id:'comname2',
                    name: 'comname2',
                    width:140,
                    readOnly:true
                },{
                    xtype: 'label',
                    text: '已选组件类型:',
                    margins: '0 10 0 20'
                },{
                    xtype: 'textfield',
                    id:'typename2',
                    name: 'typename2',
                    width:140,
                    readOnly:true
                }]
            }]
        }),
        buttons:[{
            text:'确定',
            iconCls:'ok-16',
            handler:function(){
                document.getElementById("navpid").value=Ext.getCmp('compid2').getValue();
                document.getElementById("navname").value=Ext.getCmp('comname2').getValue();
                document.getElementById("navtype").value=Ext.getCmp('typename2').getValue();
                win.close();
            }
        }, {
            text:'取消',
            iconCls:'cancel-16',
            handler:function(){
                win.close();
            }
        }]
    });
});