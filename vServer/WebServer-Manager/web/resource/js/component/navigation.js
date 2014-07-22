/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-16
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
Ext.onReady(function () {
    Ext.QuickTips.init();

    Ext.define('navigation', {
        extend: 'Ext.data.Model',
        fields: [{name: 'objid',     type: 'string'},
            {name: 'pid',     type: 'string'},
            {name: 'name',     type: 'string'},
            {name: 'createUser', type: 'string'} ,
            {name:'category',type:'string'},
            {name:'remark',type:'string'},
            {name:'updateTime',type:'string'}]
    });

    Ext.define('NavigateComponentModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'id',type:'string'},
            {name:'resourcePid'},
            {name:'name'},
            {name:'createUser'},
            {name:'updateTime'},
            {name:'parentid'}
        ]
    });

    var pageSize = 2;
    var store = Ext.create('Ext.data.TreeStore', {
        model: 'navigation',
        proxy: {
            type: 'ajax',
            url: 'component/navigation/list/get'
        },
        folderSort: true,
        listeners:{
            load:function(st, root){

                function temp(node) {
                    if(node == null) return;

                    node.expanded = true;
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

    //重置
    function resetMethod(){
        Ext.getCmp("filename").reset();
        Ext.getCmp("fileremark").reset();
        Ext.getCmp("navpid").reset();
        Ext.getCmp("navname").reset();
        Ext.getCmp("navremark").reset();
    }

    var typeString = function (v) {
        return v == 'NAV' ? '文件夹' : '组件';
    };

    var tree = Ext.create('Ext.tree.Panel', {
        region:'west',
        margins: '0 2 0 0',
        split:true,
        width: '30%',
        height:conheight,
        rootVisible: false,   //是否显示根节点，默认为true。
        enableColumnHide:false,
        sortableColumns:false,
        store: store,
        multiSelect: false,
        singleExpand: false,   //是否一次只展开树中的一个节点，默认为true。
        columns: [{
            xtype: 'treecolumn',
            header: '组件',
            flex: 2,
            sortable: true,
            dataIndex: 'name'
        },{
            header:'操作',
            xtype: 'actioncolumn',
            width: 60,
            align: 'center',
            items: [{
                    icon   : 'ext/images/delete.png',
                    tooltip:'删除',
                    handler: function(grid, rowIndex, colIndex) {
                        pid=grid.selModel.store.data.getAt(rowIndex).data.pid;

                        Ext.MessageBox.confirm("警告","确定要删除该项及子集",function(e){
                            if(e=="yes"){

                                if(pid=='NAV00000000'){
                                    Ext.MessageBox.alert('提示', '不能删除根节点！');
                                    return;
                                }

                                Ext.Ajax.request({
                                    url: 'component/navigation/delete?pid='+pid,
                                    method: 'GET',
                                    success: function (response, options) {
                                        Ext.MessageBox.alert('成功', '删除成功');
                                        store.reload();

                                        //更新Store
                                        cusstore.setProxy({
                                            type: 'ajax',
                                            url: 'component/navigation/list/getncdata',
                                            reader:{
                                                type:'json',
                                                root:'entity'
                                            },
                                            simpleSortMode:true
                                        });
                                        cusstore.loadPage(1);
                                    },
                                    failure: function (response, options) {
                                        Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                    }
                                });
                            }
                        });
                    }
                }]
        }],
        tbar: Ext.create('Ext.Toolbar',{
            items: [{
                text: '新增导航',
                icon: 'ext/images/add.png',
                handler: function () {

                    resetMethod();   //添加时，清空窗体里的值

                    pid='';
                    win.show();
                }
            }]
        })
    });

    tree.expandAll();


    var objid='';
    var pid="";

    tree.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
//            objid=selectedRecord[0].data.objid;
            pid = selectedRecord[0].data.pid;
            storeParam1.parentid=selectedRecord[0].data.objid;
            //更新Store
            cusstore.setProxy({
                type: 'ajax',
                url: 'component/navigation/list/getncdata',
                extraParams:storeParam1,
                reader:{
                    type:'json',
                    root:'entity',
                    totalProperty:'totalCount'
                },
                simpleSortMode:true
            });
            cusstore.loadPage(1);
        }
    });

    //------------------------------------组件信息相关开始------------------------------------//
    var pageSize1 = 20;
    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'pid'},
            {name:'name'},
            {name:'category'},
            {name:'createUser'},
            {name: 'updateTime'}
        ],
        idProperty: 'pid'
    });
    storeParam = {
        name:'',
        category:''
    };
    var datastore = Ext.create('Ext.data.TreeStore', {
        model: 'Resource',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            url: 'component/componentmain/list/get?page=1&limit='+pageSize1
        },
        folderSort: true
    });
    var storepaging =Ext.create('Ext.data.Store', {
        model: 'Resource',
        autoLoad:true,
        pageSize:pageSize1,
        proxy: {
            type:'ajax',
            url: 'component/componentmain/list/getpaging',
            extraParams:storeParam,
            reader: {
                root: 'content',
                totalProperty: 'totalElements'
            }
        }
    });
    var isfirst=true;
    storepaging.on('beforeload', function (store, options) {
        if(!isfirst) {
            datastore.proxy.url= 'component/componentmain/list/get?page='+options.page+'&limit='+options.limit;
            datastore.load({
                params:storeParam
            });
        }
        isfirst=false;
    });

    //组件树形列表数据
    var tree1 = Ext.create('Ext.tree.Panel', {
        width: '100%',
        height:conheight-65,
        rootVisible: false,
        store: datastore,
        enableColumnHide:false,
        sortableColumns:false,
        multiSelect: false,
        singleExpand: true,
        columns: [{
            xtype: 'treecolumn',
            text: '组件',
            flex: 2,
            sortable: true,
            dataIndex: 'name'
        },{
            text: 'pid',
            flex: 1,
            dataIndex: 'pid',
            sortable: true
        },{
            text: '类型',
            flex: 1,
            dataIndex: 'category',
            sortable: true
        }],
        tbar: Ext.create('Ext.panel.Panel', {
            width:'100%',
            height:65,
            frame:true,
            region:'center',
            style:'border-bottom-width: 0px;',
            layout: 'column',
            title: '搜索',
            defaults:{
                margin:'5',
                labelWidth:60,
                width:200
            },
            items: [{
                xtype: 'textfield',
                fieldLabel: '组件名称',
                id:'comname',
                name: 'comname',
                emptyText: '请输入组件名称'
            },{
                xtype: 'textfield',
                fieldLabel: '类型',
                id:'typename',
                name: 'typename',
                emptyText: '请输入类型'
            },{
                xtype: 'button',
                text : '查询',
                width:50,
                listeners: {
                    click: function() {
                        isfirst=false;
                        storeParam.name=Ext.getCmp('comname').getValue();
                        storeParam.category =Ext.getCmp('typename').getValue();
                        storepaging.load({
                            params:storeParam
                        });
                    }
                }
            }]
        }),
        bbar: Ext.create('Ext.PagingToolbar',{
            pageSize: pageSize1,
            store: storepaging,
            prependButtons: true,
            displayInfo: true,
            displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg: '没有数据显示'
        })
    });

    //组件列表选择项变化 -- 赋值
    var cypid,cyname;
    tree1.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
            cypid=selectedRecord[0].data.pid;
            cyname=selectedRecord[0].data.name;
            //赋值给搜索输入框，让用户可以查看目前选中的是哪条组件信息
            Ext.getCmp('comname').setValue(cyname);
            Ext.getCmp('typename').setValue(selectedRecord[0].data.category);
        }
    });

    //窗体承载组件树形列表数据
    win1 = Ext.create('Ext.window.Window', {
        id:'cuswindow',
        title:'组件信息',
        height:500,
        width:550,
        modal:true,
        constrain: true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        buttonAlign: 'center',
        resizble: false,
        items: tree1 ,         //将组件树形列表数据赋值给此窗体items
        buttons:[{
            text:'确定',
            handler:function(){
                Ext.getCmp('navpid').setValue(cypid);
                Ext.getCmp('navname').setValue(cyname);
                win1.close();
            }
        }, {
            text:'取消',
            handler:function(){
                win1.close();
            }
        }]
    });
    //------------------------------------组件信息相关结束------------------------------------//


    var wjpanel = Ext.create('Ext.form.Panel', {
                id:'wjpanel',
                region     : 'center',
                bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
                labelWidth : 100,
                defaults:{
                    xtype:'textfield'
                },
                items      : [{
                    fieldLabel:'文件名称',
                    id:'filename',
                    name:'filename',
                    anchor: '90%'
                },{
                    fieldLabel:'文件说明',
                    id:'fileremark',
                    name:'fileremark',
                    anchor: '90%'
                }]});
    //新增导航窗体
    win = Ext.create('Ext.window.Window', {
        title:'添加导航',
        height:140,
        width:400,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items:wjpanel,
        buttons:[{
            text:'确定',
            handler:function(){
                //添加文件
                if(jQuery.trim(Ext.getCmp('filename').getValue())==''){
                    Ext.Msg.alert('系统提示','请输入文件名称！');
                    return;
                }
                wjpanel.form.url= 'component/navigation/addwj?parentpid='+pid;

                wjpanel.form.submit({
                    waitMsg : ' ......',
                    success:function(action,form){
                        Ext.Msg.alert('成功', "操作成功");
                        store.reload();

                        //更新Store
                        cusstore.setProxy({
                            type: 'ajax',
                            url: 'component/navigation/list/getncdata',
                            extraParams:storeParam1,
                            reader:{
                                type:'json',
                                root:'entity',
                                totalProperty:'totalCount'
                            },
                            simpleSortMode:true
                        });
                        cusstore.loadPage(1);

                        win.close();
                    },
                    failure:function(action,form){
                        Ext.Msg.alert('错误', action.failureType);
                    }
                });
            }
        }, {
            text:'取消',
            handler:function(){
                win.close();
            }
        }]
    });

    var zjpanel = Ext.create('Ext.form.Panel', {
                id:'zjpanel',
                region     : 'center',
                bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
                labelWidth : 100,
                defaults:{
                    xtype:'textfield'
                },
                items      : [{
                    fieldLabel:'组件pid',
                    id:'navpid',
                    name:'navpid',
                    anchor: '90%',
                    readOnly:true
                },{
                    fieldLabel:'组件名称',
                    id:'navname',
                    name:'navname',
                    anchor: '90%'
                },{
                    fieldLabel:'组件说明',
                    id:'navremark',
                    name:'navremark',
                    anchor: '90%'
                },{
                    id:'btn1',
                    name: 'btn1',
                    text: '查询组件',
                    margin: '15 10 10 20',
                    xtype: 'button',
                    handler:function(){
                        win1.show();
                    }
                }]
            });
    //新增组件窗体
    winzj = Ext.create('Ext.window.Window', {
        title:'添加组件',
        height:200,
        width:400,
        modal:true,
        closeAction:'hide',
        frame:true,
        layout:'fit',
        bodyBorder:false,
        items: zjpanel,
        buttons:[{
            text:'确定',
            handler:function(){

                //添加组件
                if(Ext.getCmp('navpid').getValue()==''){
                    Ext.Msg.alert('系统提示','请先选择组件！');
                    return;
                }
                if(jQuery.trim(Ext.getCmp('navname').getValue())==''){
                    Ext.Msg.alert('系统提示','请输入组件名称！');
                    return;
                }

                zjpanel.form.url= 'component/navigation/addzj?parentpid='+pid;

                zjpanel.form.submit({
                    waitMsg : ' ......',
                    success:function(action,form){
                        Ext.Msg.alert('成功', "操作成功");
                        store.reload();

                        //更新Store
                        cusstore.setProxy({
                            type: 'ajax',
                            url: 'component/navigation/list/getncdata',
                            extraParams:storeParam1,
                            reader:{
                                type:'json',
                                root:'entity',
                                totalProperty:'totalCount'
                            },
                            simpleSortMode:true
                        });
                        cusstore.loadPage(1);

                        winzj.close();
                    },
                    failure:function(action,form){
                        Ext.Msg.alert('错误', action.failureType);
                    }
                });
            }
        }, {
            text:'取消',
            handler:function(){
                winzj.close();
            }
        }]
    });


    storeParam1 = {
        id:'',   //用于删除挂载组件传参用
        parentid:''
    };

    var cusstore = Ext.create('Ext.data.Store', {
        model: 'NavigateComponentModel',
        remoteSort:true,
        pageSize:pageSize,
        proxy: {
            type: 'ajax',
            url: 'component/navigation/list/getncdata',
            extraParams:storeParam1,
            reader:{
                type:'json',
                root:'entity',
                totalProperty:'totalCount'
            },
            simpleSortMode:true
        },
        autoLoad:true
    });

    var storepaging1 =Ext.create('Ext.data.Store', {
        model: 'navigation',
        autoLoad:true,
        pageSize:pageSize,
        proxy: {
            type:'ajax',
            url: 'component/navigation/list/getncdata',
            extraParams:storeParam1,
            reader: {
                reader:{
                    type:'json',
                    root:'entity',
                    totalProperty:'totalCount'
                }
            }
        }
    });

    var isfirstload=true;
    storepaging1.on('beforeload', function (store1, options) {
        //必须加这个判断，否则，分页会出现很难找的问题
        if(!isfirstload){

            //更新Store
            cusstore.setProxy({
                type: 'ajax',
                url: 'component/navigation/list/getncdata',
                extraParams:storeParam1,
                reader:{
                    type:'json',
                    root:'entity',
                    totalProperty:'totalCount'
                },
                simpleSortMode:true
            });

            cusstore.loadPage(1);

        }
        isfirstload=false;
    });

    var grid4 = Ext.create('Ext.grid.Panel', {
        region:'center',
        xtype: 'panel',
        margins: '0 0 0 2',
        store:cusstore,
        enableColumnHide : false,
        sortableColumns:false,
        height:conheight-65,
        width:'70%',
        columns: [
            { header: '组件名称',flex     : 1, dataIndex: 'name'},
            { header: '组件pid',width:150, dataIndex: 'resourcePid'},
            { header: '创建人',flex     : 1, dataIndex: 'createUser' },
            {
                xtype:'actioncolumn',
                header:'操作',
                align: 'center',
                width:60,
                items: [ {
                    icon: 'ext/images/delete.png',  // Use a URL in the icon config
                    tooltip: '删除',
                    handler: function(grid, rowIndex, colIndex) {
                        Ext.MessageBox.confirm("确认",'是否确认删除？',function(btn){
                            if('yes' == btn){
                                var rec = grid.getStore().getAt(rowIndex);
                                storeParam1.id=rec.get('id');
                                storeParam1.parentid=rec.get('parentid');
                                //更新Store
                                cusstore.setProxy({
                                    type: 'ajax',
                                    url: 'component/navigation/list/delete',
                                    extraParams:storeParam1,
                                    reader:{
                                        type:'json',
                                        root:'entity',
                                        totalProperty:'totalCount'
                                    },
                                    simpleSortMode:true
                                });

                                cusstore.loadPage(1);
                            }
                        });
                    }
                }]
            }],
        loadMask: { msg: '正在加载数据，请稍等……' },

        tbar: Ext.create('Ext.Toolbar',{
            items: [{
                text: '挂载组件',
                icon: 'ext/images/add.png',
                handler: function () {

                    resetMethod();   //添加时，清空窗体里的值

                    Ext.getCmp('navpid').setDisabled(true);
                    winzj.show();
                }
            }]
        }),
        bbar: Ext.create('Ext.PagingToolbar',{
            pageSize: pageSize,
            store: cusstore,
            prependButtons: true,
            displayInfo: true,
            displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg: '没有数据显示'
        })
    });

    var nav = Ext.get('navigation');
    nav.setHeight(550, true);
    Ext.widget('panel',{
        id:'navPanel',
        frame: true,
        layout: 'border',
        height:550,
        renderTo: nav,
        items:[tree,grid4]
    });

    Ext.EventManager.onWindowResize(function(){
        Ext.getCmp('navPanel').doLayout();
    });

});
