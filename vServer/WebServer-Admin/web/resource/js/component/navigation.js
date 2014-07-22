Ext.onReady(function () {
    Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', '../ext/ux');
    Ext.require('Ext.ux.CheckColumn');

    var pageSize = 50;

    var storeParam1 = {
        id: '',   //用于删除挂载组件传参用
        navPid: ''
    };

    //查询条件
    var resourceParam = {
        navPid:'',
        name: '',
        category: '',
        pid:''
    };

    //导航对象
    Ext.define('navigation', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'objid', type: 'string'},
            {name: 'pid', type: 'string'},
            {name: 'name', type: 'string'},
            {name: 'createUser', type: 'string'} ,
            {name: 'category', type: 'string'},
            {name: 'remark', type: 'string'},
            {name: 'updateTime', type: 'string'},
            {name: 'navResourceTotal',type:'int'}
        ],
        idProperty:'objid'
    });

    //导航组件
    Ext.define('NavigateComponentModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'string'},
            {name: 'resourcePid'},
            {name: 'name'},
            {name: 'category', type: 'string'},
            {name: 'createUser'},
            {name: 'updateTime'},
            {name: 'system', type: 'bool'}
        ],
        idProperty:'id'
    });

    //导航树集合
    var store = Ext.create('Ext.data.TreeStore', {
        model: 'navigation',
        proxy: {
            type: 'ajax',
            url: 'component/navigation/list/get'
        },
        folderSort: true,
        listeners: {
            load: function (st, root) {
                function temp(node) {
                    if (node == null) return;
                    node.set('iconCls', 'files-16');
                    if (node.childNodes != null) {
                        for (var i = 0; i < node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                        }
                    }
                }

                temp(root);
            }
        }
    });

    //导航对应分页组件集合
    var cusstore = Ext.create('Ext.data.Store', {
        model: 'NavigateComponentModel',
        remoteSort: true,
        pageSize: pageSize,
        proxy: {
            type: 'ajax',
            url: 'component/navigation/list/getncdata',
            extraParams: storeParam1,
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false
    });

    //组件类别集合
    var categoryTreeStore = Ext.create('Ext.data.TreeStore', {
        fields: ['id', 'pid', 'text', 'remark', 'category','name'],
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
        listeners: {
            load: function (st, root) {
                function temp(node) {
                    if (node == null) return;
                    var icon = rootPath + 'component/componenttype/icon/' + node.get('category').toLowerCase();
                    node.set('icon', icon);
                    if (node.childNodes != null) {
                        for (var i = 0; i < node.childNodes.length; i++) {
                            temp(node.childNodes[i]);
                    }
                }
                }

                temp(root);
            }
        },
        autoLoad: false
    });

    //被选组件模型
    Ext.define('Resource', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'},
            {name: 'createUser'},
            {name: 'updateTime', type: 'date', dateFormat: 'c', defaultValue: null},
            {name: 'hasNav', type: 'bool', defaultValue: false}  //是否已挂载
        ],
        idProperty: 'pid'
    });

    //被选组件数据
    var resourceStore = Ext.create('Ext.data.Store', {
        model: 'Resource',
        pageSize: pageSize,
        proxy: {
            type: 'ajax',
            url: 'component/navigation/get/checkedNavResource',
            reader: {
                root: 'entity.content',
                extraParams: resourceParam,
                totalProperty: 'entity.totalElements'
            },
            simpleSortMode: true
        },
        autoLoad: false,
        listeners: {
            beforeload: function (store, options) {
                resourceParam.page = options.page;
                Ext.apply(store.proxy.extraParams, resourceParam);
            }
        }
    });

    //一个导航下所有的组件集合,用在挂载组件与被选组件对比
    var allNavComponentStore = Ext.create('Ext.data.Store', {
        model: 'NavigateComponentModel',
        autoLoad: false
    });

    //重置
    function resetMethod() {
        Ext.getCmp("filename").reset();
        Ext.getCmp("fileremark").reset();
    }

    var objid = '';
    var pid = "";

    //导航面板
    var navTree = Ext.create('Ext.tree.Panel', {
        region: 'west',
        margins: '0 2 0 0',
        split: true,
        width: '25%',
        rootVisible: false,   //是否显示根节点，默认为true。
        enableColumnHide: false,
        sortableColumns: false,
        store: store,
        multiSelect: false,
        singleExpand: false,   //是否一次只展开树中的一个节点，默认为true。
        columns: [
            {
                xtype: 'treecolumn',
                header: '组件',
                flex: 2,
                sortable: true,
                dataIndex: 'name',
                renderer:function(name, meta, rc){
                    return name+'<span class="navItem">[' + rc.get('navResourceTotal') + ']</span>';
                }
            }
        ],
        tbar: Ext.create('Ext.Toolbar', {
            items: ['->', {
                text: '新增',
                icon: 'ext/images/add.png',
                id: 'dhadd',
                disabled: true,
                tooltip: '新增导航',
                handler: function () {

                    resetMethod();   //添加时，清空窗体里的值

                    win.show();
                }
            }, '-', {
                text: '删除',
                icon: 'ext/images/delete.png',
                id: 'dhdel',
                disabled: true,
                tooltip: '删除导航',
                handler: function () {
                    if (pid == "" || pid == null) {
                        Ext.MessageBox.alert('提示', '请选择要删除的导航！');
                        return;
                    }
                    if (pid == "NAV00000000") {
                        Ext.MessageBox.alert('提示', '不能删除导航根节点！');
                        return;
                    }

                    Ext.MessageBox.confirm("警告", "确定要删除该项及子集", function (e) {
                        if (e == "yes") {
                            Ext.Ajax.request({
                                url: 'component/navigation/delete?pid=' + pid,
                                method: 'GET',
                                success: function (response, options) {
                                    Ext.MessageBox.alert('成功', '删除成功');

                                    //清空导航pid，否则再点击挂载组件，会将已删除的pid传入过去
                                    pid = '';
                                    //清空导航父名称
                                    Ext.getCmp("dhname").reset();
                                    //设置三个按钮不可用
                                    Ext.getCmp('dhadd').setDisabled(true);
                                    Ext.getCmp('dhdel').setDisabled(true);
                                    Ext.getCmp('gzzjbtn').setDisabled(true);

                                    store.reload();

                                    //更新Store
                                    cusstore.setProxy({
                                        type: 'ajax',
                                        url: 'component/navigation/list/getncdata',
                                        extraParams: storeParam1,
                                        reader: {
                                            type: 'json',
                                            root: 'entity.content',
                                            totalProperty: 'entity.totalElements'
                                        },
                                        simpleSortMode: true
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
        }),
        listeners: {
            selectionchange: function (sm, selectedRecord) {
                if (selectedRecord.length) {
                    //设置添加导航时，展示的导航节点信息
                    Ext.getCmp('dhname').setValue(selectedRecord[0].data.name);

                    pid = selectedRecord[0].data.pid;
                    storeParam1.navPid = selectedRecord[0].data.pid;

                    //更新Store
                    cusstore.setProxy({
                        type: 'ajax',
                        url: 'component/navigation/list/getncdata',
                        extraParams: storeParam1,
                        reader: {
                            type: 'json',
                            root: 'entity.content',
                            totalProperty: 'entity.totalElements'
                        },
                        simpleSortMode: true
                    });
                    cusstore.loadPage(1);

                    Ext.getCmp('dhadd').setDisabled(false);
                    Ext.getCmp('dhdel').setDisabled(false);
                    Ext.getCmp('gzzjbtn').setDisabled(false);
                }
            }
        }
    });

    var wjpanel = Ext.create('Ext.form.Panel', {
        id: 'wjpanel',
        border: false,
        frame: true,
        region: 'center',
        bodyStyle: 'padding: 10px; background-color: #DFE8F6',
        labelWidth: 60,
        defaults: {
            xtype: 'textfield'
        },
        items: [
            {
                fieldLabel: '导航父节点',
                disabled: true,
                id: 'dhname',
                name: 'dhname',
                anchor: '98%'
            },
            {
                fieldLabel: '文件夹名称',
                id: 'filename',
                name: 'filename',
                anchor: '98%'
            },
            {
                xtype: 'textareafield',
                grow: true,
                fieldLabel: '文件夹说明',
                heigth: 80,
                id: 'fileremark',
                name: 'fileremark',
                anchor: '98%'
            }
        ]});

    //新增导航窗体
    win = Ext.create('Ext.window.Window', {
        title: '添加导航',
        height: 240,
        width: 400,
        modal: true,
        closeAction: 'hide',
        frame: true,
        layout: 'fit',
        bodyBorder: false,
        items: wjpanel,
        buttons: [
            {
                text: '确定',
                handler: function () {
                    //添加文件
                    if (Ext.getCmp('filename').getValue() == '') {
                        Ext.Msg.alert('系统提示', '请输入文件名称！');
                        return;
                    }
                    wjpanel.form.url = 'component/navigation/addwj?parentpid=' + pid;

                    wjpanel.form.submit({
                        waitMsg: ' ......',
                        success: function (action, form) {

                            Ext.Msg.alert('成功', "操作成功");
                            store.reload();

                            //更新Store
                            cusstore.setProxy({
                                type: 'ajax',
                                url: 'component/navigation/list/getncdata',
                                extraParams: storeParam1,
                                reader: {
                                    type: 'json',
                                    root: 'entity.content',
                                    totalProperty: 'entity.totalElements'
                                },
                                simpleSortMode: true
                            });
                            cusstore.loadPage(1);

                            win.close();
                        },
                        failure: function (action, form) {
                            Ext.Msg.alert('错误', action.failureType);
                        }
                    });
                }
            },
            {
                text: '取消',
                handler: function () {
                    win.close();
                }
            }
        ]
    });

    var storepaging1 = Ext.create('Ext.data.Store', {
        model: 'navigation',
        autoLoad: true,
        pageSize: pageSize,
        proxy: {
            type: 'ajax',
            url: 'component/navigation/list/getncdata',
            extraParams: storeParam1,
            reader: {
                reader: {
                    type: 'json',
                    root: 'entity',
                    totalProperty: 'totalCount'
                }
            }
        }
    });

    var isfirstload = true;
    storepaging1.on('beforeload', function (store1, options) {
        //必须加这个判断，否则，分页会出现很难找的问题
        if (!isfirstload) {

            //更新Store
            cusstore.setProxy({
                type: 'ajax',
                url: 'component/navigation/list/getncdata',
                extraParams: storeParam1,
                reader: {
                    type: 'json',
                    root: 'entity.content',
                    totalProperty: 'entity.totalElements'
                },
                simpleSortMode: true
            });
            cusstore.loadPage(1);
        }
        isfirstload = false;
    });

    var grid4 = Ext.create('Ext.grid.Panel', {
        region: 'center',
        xtype: 'panel',
        margins: '0 0 0 2',
        store: cusstore,
        enableColumnHide: false,
        sortableColumns: false,
        width: '70%',
        columns: [
            { header: '组件PID', width: 120, dataIndex: 'resourcePid'},
            { header: '组件名称', flex: 1, dataIndex: 'name' },
            {
                text: '类型名称',
                width: 120,
                dataIndex: 'category',
                renderer: function (category) {
                    return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="' + rootPath + 'component/componenttype/icon/' + category + '" /> ' + categorys[category];
                }
            },
            { header: '创建人', width: 100, dataIndex: 'createUser' },
            {
                text: '操作',
                width: 100,
                align: 'center',
                sortable: false,
                xtype: 'actioncolumn',
                layout: 'vbox',
                items: [
                    {
                        tooltip: '查看详细信息',
                        iconCls: 'action-column-margin',
                        icon: 'resource/images/icons/eye_16x16.png',
                        handler: function (grid, rowIndex) {

                            var rec = grid.getStore().getAt(rowIndex);
                            var category = rec.get('category');

                            var url = '';
                            if (category == 'TABELGP5' || category == 'WPFSKIN') {
                                var url = 'ilemsun://command/resource=' + rec.get('resourcePid').toUpperCase() + '&token=&sfdsd=slskdj&action=open';
                                window.open(url);
                            } else if (category == 'WEBSKIN') {
                                var url = clientUrl +"/"+ rec.get('resourcePid').toUpperCase();
                                window.open(url);
                            } else if (category == 'IMAGE'){
                                var url = clientUrl +"/"+ rec.get('resourcePid').toUpperCase();
                                window.open(url);
                            }else{
                                Ext.Msg.alert('提示', '此组件预览功能正在开发阶段...\r\n请关注开发论坛');
                            }
                        }
                    },
                    {
                        tooltip: '编辑',
                        iconCls: 'action-column-margin',
                        icon: 'ext/images/cog_edit.png',
                        handler: function (grid, rowindex) {
                            var rec = grid.getStore().getAt(rowindex);

                            var temp = rec.get('category').toLowerCase();

                            if (temp == 'tabelgp4' || temp == 'tabelgp5') {
                                Ext.Msg.alert('提示', '表组件的编辑正在开发中\r\n请关注开发论坛!');
                            } else if (temp == 'dbtabel') {
                                Ext.Msg.alert('提示', '表组件数据编辑正在开发中\r\n请关注开发论坛!');
                            } else {
                                window.top.open(rootPath + "component/" +
                                    temp + "/" + rec.get('resourcePid') + "/edit",'blank');
                            }
                        }
                    },
                    {
                        tooltip: '移除',
                        iconCls: 'action-column-margin',
                        icon: 'resource/images/icons/deletelayer_16x16.png',
                        handler: function (grid, rowIndex) {
                            Ext.MessageBox.confirm("确认", '是否确认移出导航？', function (btn) {
                                if ('yes' == btn) {
                                    var rec = grid.getStore().getAt(rowIndex);
                                    var navigation={
                                            resourcePid:rec.get('resourcePid'),
                                            navPid:navTree.getSelectionModel().getSelection()[0].get('pid')
                                    };

                                    //更新Store
                                    Ext.Ajax.request({
                                        url: 'component/navigation/list/delete',
                                        method: 'POST',
                                        jsonData: navigation,
                                        success: function (r, e) {
                                            var temp = Ext.decode(r.responseText);
                                            if (temp.success) {
                                                grid.getStore().remove(rec);
                                            } else {
                                                Ext.Msg.alert('提示', temp.message);
                                            }
                                        }
                                    })
                                }
                            });
                        }
                    }
                ]
            }
        ],
        loadMask: { msg: '正在加载数据，请稍等……' },

        tbar: Ext.create('Ext.Toolbar', {
            items: ['->', {
                text: '挂载组件',
                icon: 'ext/images/add.png',
                tooltip: '挂载组件',
                id: 'gzzjbtn',
                disabled: true,
                handler: function () {
                    //获取当前选中导航节点下的所有已存在的组件
                    var id = navTree.getSelectionModel().getSelection()[0].get('pid');
                    resourceParam.navPid=id;
                    resourceStore.load();
                    Ext.widget('window', {
                        title: '挂载组件',
                        icon: 'ext/images/add.png',
                        width: 800,
                        height: 500,
                        modal: true,
                        layout: 'border',
                        frame: true,
                        resizable: false,
                        closeAction: 'hide',
                        items: [
                            {
                                title: '显示类别',
                                region: 'west',
                                margins: '2 2 2 2',
                                width: 200,
                                split: true,
                                xtype: 'treepanel',
                                useArrows: true,
                                rootVisible: false,
                                displayField: 'text',
                                store: categoryTreeStore,
                                listeners: {
                                    itemclick: function (view, rc, item) {
                                            var category = rc.get('category');
                                            var grid = this.up().down('gridpanel');
                                            if (category == 'ROOT') {
                                                grid.setTitle('显示全部的组件信息');
                                            }
                                            else {
                                                resourceParam.pid='';
                                                grid.setTitle('显示 ('+ rc.get('text')+ ') 下的组件信息');
                                            }
                                            resourceParam.category = category;
                                            resourceParam.name = '';
                                            resourceParam.navPid=navTree.getSelectionModel().getSelection()[0].get('pid');
                                            resourceStore.loadPage(1);
                                    }
                                }
                            },
                            {
                                title: '组件列表',
                                itemId: 'resourcegrid',
                                region: 'center',
                                margins: '2 2 2 2',
                                store: resourceStore,
                                xtype: 'gridpanel',
                                viewConfig: {
                                    stripeRows: true,
                                    enableTextSelection: true,
                                    getRowClass: function (record, rowIndex, rowParams, store) {
                                        return record.get("hasNav") == true ? "unenable" : "canenable";
                                    }
                                },
                                plugins: Ext.create('Ext.grid.plugin.RowEditing', {
                                    clicksToMoveEditor: 1
                                }),
                                columns: [
                                    {
                                        text: '组件名称',
                                        dataIndex: 'name',
                                        flex: 1
                                    },
                                    {
                                        text: '编号',
                                        align: 'left',
                                        width: 120,
                                        sortable: false,
                                        dataIndex: 'pid'
                                    },
                                    {
                                        text: '类型',
                                        width: 60,
                                        dataIndex: 'category',
                                        renderer: function (category) {
                                            return '<img style="vertical-align: middle;margin-right: 5px;" name="img" src="' + rootPath + 'component/componenttype/icon/' + category + '" /> ';
                                        }
                                    },
                                    {
                                        text: '更新时间',
                                        width: 120,
                                        sortable: true,
                                        xtype: 'datecolumn',
                                        format: 'Y-m-d H:i:s',
                                        dataIndex: 'updateTime'
                                    },
                                    {
                                        text: '创建人员',
                                        width: 80,
                                        sortable: true,
                                        align: 'center',
                                        dataIndex: 'createUser'
                                    },
                                    {
                                        xtype: 'checkcolumn',
                                        header: '选择',
                                        dataIndex: 'hasNav',
                                        width: 60,
                                        listeners: {
                                            checkchange: function (t, r, c, e) {
                                                var temp = this.up('gridpanel').getStore().getAt(r);
                                                var index = allNavComponentStore.find('resourcePid', temp.get('pid'));

                                                if (temp.get('hasNav') == true) {
                                                    allNavComponentStore.removeAt(index)
                                                    allNavComponentStore.add({resourcePid:temp.get('pid')});
                                                } else {
                                                    allNavComponentStore.removeAt(index);
                                                }
                                            }
                                        }
                                    }
                                ],
                                dockedItems: [
                                    {
                                        xtype: 'pagingtoolbar',
                                        store: resourceStore,
                                        dock: 'bottom',
                                        displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
                                        emptyMsg: '没有数据显示',
                                        displayInfo: true
                                    },
                                    {
                                        xtype: 'toolbar',
                                        dock: 'top',
                                        items: ['->', '组件名称:', {
                                            xtype: 'textfield',
                                            name: 'cName',
                                            emptyText: '请输入组件名称'
                                        }, {
                                            text: '查询',
                                            icon: 'ext/images/view.png',
                                            handler: function () {
                                                var temp=this.up().down('[name=cName]').getValue();
                                                var reg=/^\d+$/;

                                                if(reg.test(temp)){
                                                    resourceParam.name='';
                                                    resourceParam.pid=temp;
                                                }else{
                                                    resourceParam.pid='';
                                                    resourceParam.name =temp;
                                                }
                                                //resourceParam.pid=navTree.getSelectionModel().getSelection()[0].get('pid');
                                                resourceStore.loadPage(1);
                                                resourceParam.name = '';
                                            }
                                        }]
                                    }
                                ],
                                listeners: {
                                    selectionchange: function (sm, sr) {
                                        if (sr.length) {
                                        }
                                    }
                                }
                            }
                        ],
                        dockedItems: [
                            {
                                xtype: 'toolbar',
                                dock: 'bottom',
                                ui: 'footer',
                                items: [
                                    {
                                        xtype: 'component',
                                        flex: 1
                                    },
                                    {
                                        xtype: 'button',
                                        text: '确定',
                                        handler: function () {
                                            var temp = []
                                            var navPid=navTree.getSelectionModel().getSelection()[0].get('pid');
                                            allNavComponentStore.each(function (r) {
                                                temp.push({
                                                    navPid: navPid,
                                                    resourcePid: r.get('resourcePid')
                                                })
                                            });

                                            Ext.Ajax.request({
                                                url: 'component/navigation/addzj',
                                                method: 'POST',
                                                jsonData: {components: temp},
                                                success: function (r, e) {
                                                    var temp = Ext.decode(r.responseText);
                                                    if (temp.success) {
                                                        storeParam1.navPid=navPid;
                                                        allNavComponentStore.removeAll();
                                                        cusstore.load();
                                                    } else {
                                                        Ext.Msg.alert('提示', temp.message);
                                                    }
                                                }
                                            })


                                            this.up('window').hide();
                                        }
                                    },
                                    {
                                        xtype: 'button',
                                        text: '取消',
                                        handler: function () {
                                            this.up('window').hide();
                                        }
                                    }
                                ]
                            }
                        ]
                    }).show();
                }
            }]
        }),
        bbar: Ext.create('Ext.PagingToolbar', {
            pageSize: pageSize,
            store: cusstore,
            prependButtons: true,
            displayInfo: true,
            displayMsg: '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg: '没有数据显示'
        })
    });

    Ext.widget('panel', {
        id: 'navPanel',
        title: '导航管理',
        margin:5,
        height:790,
        frame: true,
        layout: 'border',
        renderTo: Ext.getBody(),
        items: [navTree, grid4]
    });

    Ext.getBody().on('DOMNodeRemovedFromDocument', function () {
        Ext.getCmp('navPanel').destroy();
        delete categorys;
    });

    Ext.EventManager.onWindowResize(function () {
//        Ext.getCmp('navPanel').doLayout();
    });
});


