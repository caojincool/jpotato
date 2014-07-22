/**
 * Created with IntelliJ IDEA.
 * User: gm
 * Date: 12-11-20
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */

Ext.require([
    'Ext.grid.*',
    'Ext.data.*'
]);

Ext.onReady(function(){
    //验证提示
    Ext.QuickTips.init();

    var IsStore = Ext.create('Ext.data.ArrayStore',
            {
                fields: ['ID', 'Name'],
                data: [['0', 'Win7 系统'], ['1', 'Windows XP 系统']]
            }
    );

    Ext.create('Ext.panel.Panel', {
        flex:1,
        renderTo: Ext.get("navigation"),
        title: '',

        items:Ext.create('Ext.form.FormPanel',{
            id:'windowpanel',
            padding:5,
            border:false,
            layout: {
                type: 'table',
                columns: 3
            },
            defaults:{
                xtype: 'textfield',
                labelWidth:60
            },
            items:[{
                id:'CId',
                name: 'CId',
                msgTarget: 'side',  //提示语显示位置，side为右边
                hidden: true
            },
                {
                    fieldLabel: '实例名称',
                    id:'CName',
                    name: 'CName',
                    allowBlank: false,
                    emptyText: '请输入名称',
                    blankText: '请输入名称',
                    msgTarget: 'side'  //提示语显示位置，side为右边
                },{
                    fieldLabel: 'IP',
                    id:'CIP',
                    name: 'CIP',
                    allowBlank: false,
                    emptyText: '请输入IP',
                    blankText: '请输入IP',
                    msgTarget: 'side'  //提示语显示位置，side为右边
                },
                {
                    xtype: 'combo',
                    id:'CsysName',
                    name: 'CsysName',
                    fieldLabel: '使用系统',
                    editable: false,
                    typeAhead: true,
                    resizble: true,
                    triggerAction: 'all',
                    lazyRender: true,
                    mode: 'local',
                    emptyText: '请选择系统',
                    displayField: 'Name',
                    hiddenName: 'ID',
                    allowBlank: false,
                    blankText: '请选择系统',
                    msgTarget: 'side',
                    store: IsStore
                },
                {
                    fieldLabel: '系统说明',
                    id:'CRemark',
                    name: 'CRemark',
                    flex     : 1,
                    allowBlank: false,
                    emptyText: '请输入系统说明',
                    blankText: '请输入系统说明',
                    msgTarget: 'side'  //提示语显示位置，side为右边
                },
                {
                    fieldLabel: '系统地址',
                    id: 'CAddress',
                    name: 'CAddress',
                    allowBlank: false,
                    emptyText: '请输入系统地址',
                    blankText: '请输入系统地址',
                    msgTarget: 'side'  //提示语显示位置，side为右边
                },
                {
                    xtype: 'datefield',
                    id:'CSTime',
                    name: 'CSTime',
                    allowBlank: false,
                    blankText: '请选择日期',
                    fieldLabel: '启用日期',
                    format: 'Y-m-d'
                }
            ]
        }),

        buttonAlign: 'center',
        buttons: [{
            text: '保存',
            iconCls: 'save-icon',
            handler: function () {
                var cname = Ext.getCmp('CName').getValue(),
                        csysname = Ext.getCmp('CsysName').getValue(),
                        cid = Ext.getCmp('CId').getValue(),
                        cstime = Ext.getCmp('CSTime').getValue();


                //验证功能
                if (!Ext.getCmp('windowpanel').getForm().isValid()) {
                    Ext.Msg.alert("提示", "此页数据有错误，请检查无误后提交.");
                    return;
                }
            }
        }, {
            text: '取消',
            iconCls: 'cancel-icon',
            handler: function (b, e) {
                location.href='admin/system/instancelist';
            }
        }]
    });
});