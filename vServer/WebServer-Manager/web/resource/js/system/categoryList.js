/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-16
 * Time: 上午8:56
 * To change this template use File | Settings | File Templates.
 */

Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.window.MessageBox',
    'Ext.selection.CheckboxModel'
]);

Ext.onReady(function(){
    Ext.define('Company', {
        extend: 'Ext.data.Model',
        fields: [ 'CId','CName', 'CRemark','CCode', 'CList' ]
    });

    //验证提示
    Ext.QuickTips.init();

    var IsUpdate = "";

    win1 = Ext.create('Ext.window.Window', {
        title:'添加附加集合',
        height:140,
        width:300,
        modal:true,
        constrain: true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        buttonAlign: 'center',
        resizble: false,
        items:Ext.create('Ext.form.Panel', {
            region     : 'center',
            bodyStyle  : 'padding:10px; background-color: #DFE8F6',
            labelWidth : 75,
            width      : 300,
            items      : [
                Ext.create('Ext.form.field.Text', {
                    id:'textField1',
                    fieldLabel : '集合名称',
                    name       : 'name'
                }),
                Ext.create('Ext.form.field.Text', {
                    id:'textField2',
                    fieldLabel : '值',
                    name       : 'value'
                })
            ]
        }),
        buttons:[{
            text:'确定',
            handler:function(){
                var name= Ext.fly('textField1').getValue();
                Ext.fly('CList').val=name;
                win1.close();
            }
        }, {
            text:'取消',
            handler:function(){
                win1.close();
            }
        }]
    });

    var window = Ext.create('Ext.Window',{
        modal: true,
        constrain: true,
        width: 552,
        height: 350,
        title: '系统类型管理',
        closeAction: 'hide',
        iconCls: 'user-icon',
        resizble: false,
        layout: 'fit',
        buttonAlign: 'center',
        items: Ext.create('Ext.form.FormPanel',{
            margin:'10px',
            id: "windowpanel",
            labelWidth: 60,
            border: false,
            frame: true,
            items: [
                {
                    name: 'CId',
                    msgTarget: 'side',  //提示语显示位置，side为右边
                    anchor: '80%',
                    hidden: true
                },
                {
                    xtype: 'textfield',
                    fieldLabel: '系统类型名称',
                    name: 'CName',
                    allowBlank: false,
                    emptyText: '请输入系统类型名称',
                    blankText: '请输入系统类型名称',
                    msgTarget: 'side',  //提示语显示位置，side为右边
                    anchor: '80%'        //控件宽度比例
                },
                {
                    xtype: 'textfield',
                    fieldLabel: '系统类型说明',
                    name: 'CRemark',
                    allowBlank: false,
                    emptyText: '请输入系统类型说明',
                    blankText: '请输入系统类型说明',
                    msgTarget: 'side',  //提示语显示位置，side为右边
                    anchor: '80%'        //控件宽度比例
                },
                {
                    xtype: 'textfield',
                    fieldLabel: '系统类型编码',
                    name: 'CCode',
                    allowBlank: false,
                    emptyText: '请输入系统类型编码',
                    blankText: '请输入系统类型编码',
                    msgTarget: 'side',  //提示语显示位置，side为右边
                    anchor: '80%'        //控件宽度比例
                },
                {
                    enable:false,
                    xtype: 'textfield',
                    fieldLabel: '附加集合',
                    name: 'CList',
                    allowBlank: false,
                    emptyText: '请输入附加集合',
                    blankText: '请输入附加集合',
                    msgTarget: 'side',  //提示语显示位置，side为右边
                    anchor: '80%'
                },
                {
                    name: 'btn1',
                    editable: false,
                    text: '添加集合',
                    margin: '15 10 10 20',
                    xtype: 'button',
                    handler:function(){
                        win1.show();
                    }
                }
            ]
        }),
        buttons: [{
            text: '保存', iconCls: 'save-icon', handler: function () {
                var cname = Ext.fly('CName').getValue(),
                        csysname = Ext.fly('CsysName').getValue(),
                        cid = Ext.fly('CId').getValue(),
                        cstime = Ext.fly('CSTime').getValue();
                //验证功能
                if (!Ext.getCmp('windowpanel').getForm().isValid()) {
                    Ext.Msg.alert("提示", "此页数据有错误，请检查无误后提交.");
                    return;
                }

//                //表示新增
//                if (IsUpdate == "") {
//                    Ext.Ajax.request({
//                        url: '../KMS/CreateG_Test.aspx?web=addCourse',
//                        params: { courseName: courseName, isExam: isExam, description: description },
//                success: function (response, opts) {
//                    var obj = Ext.decode(response.responseText);
//                    if (!obj.HasError) {
//                    window.hide();
//                    Ext.Msg.alert('提示', obj.msg);
//                    store.load();
//                    }
//                else {
//                    Ext.Msg.alert('提示', '课程信息在保存过程中出现错误，请联系管理员！');
//                    }
//                }
//                });
//                }
//                //表示修改
//                else {
//                    Ext.Ajax.request({
//                        url: '../KMS/CreateG_Test.aspx?web=EditCourse',
//                        params: { G_ID: G_Id, courseName: courseName, isExam: isExam, description: description },
//                success: function (response, opts) {
//                    var obj = Ext.decode(response.responseText);
//                    if (!obj.HasError) {
//                    window.hide();
//                    Ext.Msg.alert('提示', obj.msg);
//                    store.load();
//                    }
//                else {
//                    Ext.Msg.alert('提示', '课程信息在保存过程中出现错误，请联系管理员！');
//                    }
//                }
                //});
                //}

            }
        }, {
            text: '取消', iconCls: 'cancel-icon', handler: function (b, e) { window.hide(); }
        }]
    });

    var typeString = function (v) {
        return v == '0' ? 'Web 系统' : 'WPF 系统';
    };
    var Time = function (v) {
        return v;
        //return new Date(Date.parse(v.replace(/-/g, '/'))).format('Y年m月d日');
    };

    // Array data for the grids
    var data = [
        ['1','Web 系统','系统一','Code1','[name:test1],[address:chongqing],[count:10]'],
        ['2','WPF 系统','系统二','Code2','[name:test2],[address:chongqing],[count:20]']
    ];

    var getLocalStore = function() {
        return Ext.create('Ext.data.ArrayStore', {
            model: 'Company',
            data: data
        });
    };

    var grid4 = Ext.create('Ext.grid.Panel', {
        id:'button-grid',
        store: getLocalStore(),
        enableColumnHide : false,
        sortableColumns:false,
        height:conheight,
        columns: [
            { header: 'ID', width: 50, dataIndex: 'CId', sortable: true },
            { header: '系统类型名称', dataIndex: 'CName', width: 100 },
            { header: '说明', dataIndex: 'CRemark', flex: 1 },
            { header: '编码', dataIndex: 'CCode', width: 100 },
            { header: '附加集合', dataIndex: 'CList' ,flex: 1 , xtype: 'templatecolumn', tpl: '<input type="text" readonly="true" value="{CList}" style="width:100%; height: 100%;"/>'},

//            { header: '操作', dataIndex: 'CList' ,flex: 1 , xtype: 'templatecolumn', tpl: '<a onclick="window.show();" >编辑</a>'},

            {
                xtype:'actioncolumn',
                header:'操作',
                align: 'center',
                width:50,
                items: [{
                    icon: 'ext/images/cog_edit.png',  // Use a URL in the icon config
                    tooltip: '修改',
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = grid.getStore().getAt(rowIndex);
                        alert(rec.get('CId'));
                        window.show();
                    }
                }]
            }
        ],

        loadMask: { msg: '正在加载数据，请稍等……' },
        renderTo: Ext.get("navigation"),
        title:'类型管理'
    });
});