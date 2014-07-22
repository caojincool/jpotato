/**
 * 创建关于web组件的页面布局信息
 * User: dpyang
 * Date: 13-3-19
 * Time: 下午1:40
 */
Ext.onReady(function () {

    Ext.QuickTips.init();

    var panelEl = Ext.get('webEditors');
    //开始参数的类型
    var dataTypeStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'cate'],
        data: [
            {id: 0, cate: '未知'},
            {id: 1, cate: '文本字段'},
            {id: 2, cate: '整数'},
            {id: 3, cate: '小数'},
            {id: 4, cate: '数据'},
            {id: 5, cate: '时间'},
            {id: 6, cate: '多媒体'},
            {id: 7, cate: '逻辑'},
            {id: 8, cate: '表格主键'},
            {id: 9, cate: '更新时间标记'},
            {id: 10, cate: '固定编码'},
            {id: 11, cate: 'HTML'},
            {id: 12, cate: '图片'},
            {id: 13, cate: '文件集'},
            {id: 14, cate: 'XML'},
            {id: 15, cate: '操作日期'},
            {id: 16, cate: '单据号'},
            {id: 17, cate: '单据子序列号'},
            {id: 18, cate: '外键'}
        ]
    });
    //开始参数的类型
    var paramTypeStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'cate'],
        data: [
            {id: 0, cate: '不同步'},
            {id: 1, cate: '同步'},
            {id: 2, cate: '向上不同步'}
        ]
    });
    //查询条件
    var tableTypeStore = Ext.create('Ext.data.Store', {
        fields: ['id', 'cate'],
        data: [
            {id: 1, cate: '获取全部表'},
            {id: 2, cate: '获取操作日期之后'},
            {id: 3, cate: '获取当前表'}
        ]
    });
//父组件开始参数模型
    Ext.define('startParamsModel', {
        extend: 'Ext.data.Model',
        fields: ['col','name', 'category', 'sync'],
        idProperty: 'col'
    });
    var storeParam1 = {
        pid: pid,   //用于删除挂载组件传参用
        cate: 1
    };
//获取某个组件的开始参数
    var startParamsStore = Ext.create('Ext.data.Store', {
        model: 'startParamsModel',
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: 'component/tabelgp5/'+pid+'/getAllTableColumns',
            extraParams: storeParam1,

            simpleSortMode: true
        },
        listeners: {
            beforeload: function (store, options) {
                Ext.apply(store.proxy.extraParams, storeParam1);
            }
        },
        autoLoad: false
    });
    startParamsStore.load();
    var webResourceform = Ext.widget('form', {
        height: 750,
        frame: true,
        layout: 'border',
        items: [

            {
                xtype: 'gridpanel',
                region: 'center',
                margins: '2 2',
                height: 200,
                store: startParamsStore,
                tbar: [
                    {
                        iconCls: 'add-16',
                        text: '创建',
                        handler: function (el) {
                            createStartParamWin(el);
                        }
                    },
                    '->',   Ext.create('Ext.form.field.ComboBox', {
                        fieldLabel: '查询条件',
                        id:"category",
                        displayField: 'cate',
                        valueField:'id',
                        width:250,
                        labelWidth: 70,
                        store: tableTypeStore,
                        queryMode: 'local',
                        listeners: {
                            afterRender: function(combo) {
                                combo.setValue(1);
                            }
                        }
                    }), {
                        icon: 'ext/images/view.png',
                        handler: function () {
                            var category=Ext.getCmp("category").getValue();
                            storeParam1.cate =category;
                            startParamsStore.load();
                        }
                    }
                ],
                viewConfig: {
                    stripeRows: true,
                    forceFit: true,
                    enableRowBody: true,
                    getRowClass: function (record) {
                        return record.get('parent') == true ? 'child-row' : '';
                    }
                },
                columns: [
                    {
                        text: '物理名称',
                        width: 120,
                        dataIndex: 'col'
                    },
                    {
                        text: '别名',
                        width: 80,
                        dataIndex: 'name'
                    },
                    {
                        text: '列类型',
                        width: 60,
                        dataIndex: 'category',
                        renderer: function (val) {
                            return  dataTypeStore.data.items[val].data.cate;
                        }
                    },
                    {
                        text: '同步方式',
                        flex: 1,
                        dataIndex: 'sync',
                        draggable: false,
                        renderer: function (val) {
                            return  paramTypeStore.data.items[val].data.cate;
                        }
                    },
                    {
                        text: '操作',
                        width: 40,
                        xtype: 'actioncolumn',
                        layout: 'vbox',
                        align: 'center',
                        width: 60,
                        items: [
//                                            {
//                                                tooltip: '预览',
//                                                iconCls: 'action-column-margin',
//                                                icon: 'ext/images/cog_edit.png',
//                                                handler: function (grid, rowIndex, colIndex) {
//                                                    var rec = grid.getStore().getAt(rowIndex);
//                                                    preViewStartParam(rec.data);
//                                                }
//                                            },
                            {
                                tooltip: '编辑',
                                iconCls: 'action-column-margin',
                                icon: 'ext/images/cog_edit.png',
                                handler: function (grid, rowIndex, colIndex) {
                                    var rec = grid.getStore().getAt(rowIndex);
                                    var category = rec.data.category;
                                    if(category != 8&&category != 9&&category != 15&&category != 16&&category != 17){
                                         createStartParamWin(rec);
                                    }else{
                                        Ext.Msg.alert("系统提示","该列不能编辑！");
                                    }
                                }
                            },
                            {
                                tooltip: '删除',
                                itemId: 'btnDel',
                                iconCls: 'action-column-margin',
                                icon: 'resource/images/icons/deletelayer_16x16.png',
                                handler: function (grid, rowIndex, colIndex) {
                                    var rec = grid.getStore().getAt(rowIndex);
                                    var category = rec.data.category;
                                    if(category != 8&&category != 9&&category != 15&&category != 16&&category != 17){
                                            startParamsStore.remove(rec);
                                    }else{
                                        Ext.Msg.alert("系统提示","该列不能删除！");
                                    }
                                }
                            }
                        ]
                    }
                ]
            }

        ],
        renderTo: panelEl
    })
    //创建开始参数窗口
    function createStartParamWin(el) {
        var win = Ext.create('Ext.Window', {
            height: 200,
            width: 400,
            title: '创建或者编辑列',
            plain: true,
            modal: true,
            layout: 'form',
            defaultType: 'textfield',
            bodyPadding: 5,
            items: [
                {
                    fieldLabel: '物理名称',
                    itemId: 'paramName',
                    value: (el.data == undefined) ? '' : el.data.col,
                    listeners: {
                        change: function (t, n, o, e) {
                            if (t.value != '') {
                                t.up().down('button[text=确定]').enable()
                            }
                            if(startParamsStore.data!=null){
                                startParamsStore.each(function (r) {
                                        if (r.get('col') == n) {
                                            t.up().down('button[text=确定]').disable();
                                        }
                                    }
                                )}
                        }
                    }
                },
                {
                    fieldLabel: '列类型',
                    itemId: 'colCate',
                    xtype: 'combo',
                    displayField: 'cate',
                    valueField: 'id',
                    store: dataTypeStore,

                    value: (el.data == undefined) ? 0 : el.data.category
                },
                {
                    fieldLabel: '同步方式',
                    itemId: 'paramCate',
                    xtype: 'combo',
                    displayField: 'cate',
                    valueField: 'id',
                    store: paramTypeStore,

                    value: (el.data == undefined) ? 1 : el.data.sync
                }
            ],
            buttons: [
                {
                    text: '确定',
                    iconCls: 'ok-16',
                    disabled: (el.data == undefined),
                    handler: function () {
                        var row = {
                            col: win.getComponent('paramName').getValue(),
                            category: win.getComponent('colCate').getValue(),
                            sync: win.getComponent('paramCate').getValue()
                        };
                        if (el.data != undefined)
                            startParamsStore.remove(el);
                        startParamsStore.add(row);
                        win.close();
                    }
                },
                {
                    text: '取消',
                    iconCls: 'cancel-16',
                    handler: function () {
                        win.close();
                    }
                }
            ]
        });

        win.show();
    }
    //保存
    Ext.fly('btnOk').on('click', function () {
        executeSave( function (){
            location.href = "/component/main/operatingResults?success=true";
        });
    });
    function executeSave(callback){
        //开始参数
        var  webPageParams = [];
        startParamsStore.t
        if (startParamsStore.data != null) {
            startParamsStore.each(function (record) {
                var webPageParam = {
                    col: record.get('col'),
                    name: record.get('name'),
                    category: record.get('category'),
                    sync: record.get('sync')
                };
                webPageParams.push(webPageParam);
            });
        }
        //获取参数

        storeParam1.cols = webPageParams;

        Ext.Ajax.request({
            url: rootPath + 'component/tabelgp5/'+pid+'/edit',
            jsonData: storeParam1,
            method: 'POST',
            success: function (r, e) {
                var result = Ext.decode(r.responseText);
                if (result.success) {
                    callback();
                } else {
                    Ext.Msg.alert("操作失败！");
                }
            },
            failure: function (r, e) {
                Ext.Msg.alert("操作失败！");
            }
        });
    }
});