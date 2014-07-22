Ext.onReady(function(){

    var panelEl = Ext.get('tabelgp');

    var panel = Ext.widget('panel',{
        id:'tabelgp',
        frame: true,
        layout: 'border',
        height:650,
        renderTo: panelEl,
        items: [{
            title:'基本信息设置',
            region:'north',
            layout:'column',
            height: 210,
            margin: '0 0 5 0',
            items:[{
                columnWidth: 1/3,
                margin: '0 5 0 5',
                xtype: 'fieldset',
                title:'必填信息',
                defaults: {
                    width: 240,
                    labelWidth: 90
                },
                defaultType: 'textfield',
                items: [{
                    fieldLabel: '类型',
                    name: 'category'
                },{
                    fieldLabel: '报表名称',
                    name: 'category'
                },{
                    fieldLabel: '报表编码',
                    name: 'category'
                }]
            }, {
                columnWidth: 1/3,
                margin: '0 5 0 0',
                xtype: 'fieldset',
                title:'同步设置',
                defaults: {
                    width: 240,
                    labelWidth: 90
                },
                defaultType: 'textfield',
                items: [{
                    fieldLabel: 'Name',
                    name: 'company'
                },{
                    fieldLabel: 'Price',
                    name: 'price'
                },{
                    fieldLabel: '% Change',
                    name: 'pctChange'
                },{
                    xtype: 'datefield',
                    fieldLabel: 'Last Updated',
                    name: 'lastChange'
                }]
            }, {
                columnWidth: 1/3,
                margin: '0 5 0 0',
                xtype: 'fieldset',
                title:'其他设置',
                defaults: {
                    width: 240,
                    labelWidth: 90
                },
                defaultType: 'textfield',
                items: [{
                    fieldLabel: 'Name',
                    name: 'company'
                },{
                    fieldLabel: 'Price',
                    name: 'price'
                },{
                    fieldLabel: '% Change',
                    name: 'pctChange'
                },{
                    xtype: 'datefield',
                    fieldLabel: 'Last Updated',
                    name: 'lastChange'
                }]
            }]
        },{
            title: '栏目信息',
            tbar:[{
                iconCls:'add-16',
                text:'创建'
            }, '->', {
                iconCls:'remove-16',
                text:'删除'
            }],
            html : 'Hello',
            region:'west',
            margins: '0 2 0 0',
            width: 300,
            split:true
        },{
            title: '功能选项',
            region: 'center',     // center region is required, no width/height specified
            xtype: 'panel',
            layout: 'fit',
            margins: '0 0 0 2'
        }]
    });


    Ext.EventManager.onWindowResize(function(){
        panel.doLayout();
    });

});