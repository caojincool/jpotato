/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-11
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */

var sexStore = Ext.create('Ext.data.Store', {
    fields: [ 'text'],
    data : [
        { "text":'男'},
        { "text":'女'}
    ]
});

var sexComboBox = Ext.create('Ext.form.ComboBox', {
    fieldLabel: '性别',
    emptyText:'请选择性别',
    name:'sex',
    width:300,
    editable: false,
    store: sexStore,
    displayField: 'text',
    valueField: 'text'
});

var accountForm = Ext.create('Ext.form.Panel', {
    title: '人员信息编辑',
    frame:true,
    buttonAlign: "center",
    aotuheight:true,
    fieldDefaults: {
        msgTarget: 'side',
        labelWidth: 75,
        labelAlign:'right'
    },
    defaults: {
        anchor: '100%'
    },
    items: [{
        xtype:'fieldset',
        title: '基本信息(必填)',
        collapsible: true,
        defaultType: 'textfield',
        defaults:{
            width:300
        },
        layout: {
            type: 'table',
            columns:3
        },
        items :[
            {
                fieldLabel: '姓名',
                name: 'account',
                allowBlank:false
            },{
                fieldLabel: '工号',
                name: 'pid',
                allowBlank:false
            },{
                fieldLabel: '密码',
                name: 'password',
                allowBlank:false
            },{
                fieldLabel: '电话',
                name: 'phone',
                allowBlank:false
            }]
    },{
        xtype:'fieldset',
        title: '扩展信息',
        collapsible: true,
        defaultType: 'textfield',
        defaults:{
            width:300
        },
        layout: {
            type: 'table',
            columns:3
        },
        items :[,{
            fieldLabel: '出生日期',
            xtype:'datefield',
            format:'Y/m/d',
            name: 'birthday',
            editable: false
        },{
            xtype:sexComboBox
        },{
            fieldLabel: '民族',
            name: 'nation'
        },{
            fieldLabel: '籍贯',
            name: 'hometown'
        },{
            fieldLabel: '联系住址',
            name: 'address'
        },{
            fieldLabel: '邮编',
            name: 'postalcode'
        },{
            fieldLabel: 'QQ/MSN',
            name: 'mobile'
        },{
            fieldLabel: 'E-mail',
            name: 'email',
            vtype:'email'
        }]
    },{
        xtype:'fieldset',
        title: '用户组信息',
        collapsible: true,
        defaultType: 'textfield',
        defaults:{
            width:900
        },
        layout: {
            anchor:'100%'
        },
        items :[{
            fieldLabel:'所属用户组',
            name:'roles',
            readOnly:true
        }]
    },{
        xtype:'fieldset',
        title: '备注信息',
        collapsible: true,
        defaultType: 'textarea',
        defaults:{
            width:900
        },
        layout: {
            anchor:'100%'
        },
        items :[{
            fieldLabel:'备注',
            name:'remark'
        }]
    }],
    buttons: [{
        text: '保存',
        handler:function(){
            if(accountForm.getForm().isValid()){
                accountForm.submit({
                    url:'account/save?isModify',
                    waitMsg : ' ......',
                    success:function(action,form){
                        Ext.Msg.show({
                            title:'服务器信息',
                            msg:'操作成功',
                            buttons: Ext.Msg.YES
                        });
                        location.href= rootPath + "account/accounts";
                    },
                    failure:function(action,form){
                        Ext.MessageBox(action.failureType);

                    }});
            }else{
                Ext.Msg.show({
                    title:'服务器信息',
                    msg:'请检查表单',
                    buttons: Ext.Msg.YES
                });
            }
        }
    },{
        text: '返回',
        handler:function(){
            location.href= rootPath + "account/accounts";
        }
    }]
});

Ext.onReady(function(){

    accountForm.render("navigation");

    if(id != 'null'){
        accountForm.getForm().load({
            url:'account/getOne',
            params:{id:id}
        });
    }
});