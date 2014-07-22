Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux', rootPath+'ext/ux/');
Ext.require(['*']);
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ux.PreviewPlugin',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);
Ext.define('State', {
    extend: 'Ext.data.Model',
    fields: [
        {type: 'string', name: 'abbr'},
        {type: 'string', name: 'name'},
        {type: 'string', name: 'slogan'}
    ]
});
var states = [
    {"abbr":"AL","name":"Alabama","slogan":"The Heart of Dixie"},
    {"abbr":"AK","name":"Alaska","slogan":"The Land of the Midnight Sun"},
    {"abbr":"AZ","name":"Arizona","slogan":"The Grand Canyon State"}
];

function createStore() {

    return Ext.create('Ext.data.Store', {
        autoDestroy: true,
        model: 'State',
        data: states
    });
}
Ext.onReady(function() {

    Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
    Ext.define('ResouceModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'},
            {name: 'descrption'},
            {name: 'businessCode'},
            {name: 'updateTime', type: 'date', dateFormat: 'c', defaultValue: null}

        ],
        idProperty: 'pid'
    });
    Ext.define('CategoryModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'pid'},
            {name: 'name'},
            {name: 'category'}
        ],
        idProperty: 'pid'
    });
    var categoryStore = Ext.create('Ext.data.Store', {
        model: "CategoryModel",
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: 'help/document/getCategorys',
            reader: {
                type: 'json',
                root: 'entity.content'
            }
        }
        ,autoLoad: true
    });

    var storeParam = {
        search: '',   //用于删除挂载组件传参用
        category: ''
    };
    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        pageSize: 20,
        model: 'ResouceModel',
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: 'help/document/getData',
            extraParams: storeParam,
            reader: {
                type: 'json',
                root: 'entity.content',
                totalProperty: 'entity.totalElements'
            },
            // sends single sort as multi parameter
            simpleSortMode: true
        },  listeners: {
            beforeload: function (store, options) {
                storeParam.page = options.page;
                Ext.apply(store.proxy.extraParams, storeParam);
            }
        }
    });

    // pluggable renders
    function renderTopic(value, p, record) {
        return Ext.String.format(
            '组件编号：{0}</br>' +
                '业务编码：{3}</br>' +
                '组件名称：<img style="vertical-align: middle;margin-right: 5px;" name="img" src="' + rootPath
                + 'component/componenttype/icon/' + record.data.category + '" /> {1}</br>' +
                '更新日期：{2}</br>',
            record.data.pid,
            record.data.name,
            Ext.Date.dateFormat(record.data.updateTime, 'Y-m-j'),
            record.data.businessCode

        );
    }
    function renderIcon(value, p, r) {
        return '<img style="cursor:hand;"   src="help/document/'+value+'/preView?size=0">';
    }
    var grid = Ext.create('Ext.grid.Panel', {

        region: 'west',
        id: 'west-panel', // see Ext.getCmp() below
        title:"查询结果",
        split: true,
        width: 450,
        minWidth: 175,
        maxWidth: 400,
        collapsible: true,
        animCollapse: true,
        store: store,
        disableSelection: true,
        loadMask: true,
        margins:'4',
        // grid columns
        columns:[
            {
                id: 'pid',
                text: "预览图",
                dataIndex: 'pid',
                width:85,
                renderer: renderIcon,
                sortable: false
            },
            {
            id: 'name',
            text: "组件信息",
            dataIndex: 'name',
             width:150,
            renderer: renderTopic,
            sortable: false
        }, {
                text: "说明",
                dataIndex: 'descrption',
                width:140,
                sortable: false
            }
        ],
        // paging bar on the bottom
        bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg: '展示 {0} - {1} 共 {2}',
            emptyMsg: "未查询到匹配数据"
        })

    });
   var tabs= Ext.create('Ext.tab.Panel', {
        region: 'center',
        margins:'4',
        itemId:"tabs",
        deferredRender: false,
        activeTab: 0,
        items: [
            {
                title: '文档首页',
                autoScroll: true,
                html:"后期完善页面内容！"
            }
        ]
    });
    grid.addListener("cellclick",function( ts, td,cellIndex,record){
              if(cellIndex==0){
                  var pid= record.data.pid;
                  var name= record.data.name;


                  var  tab;
                  var flag=false;
                  for (var i = 0; i < tabs.items.getCount(); i++) {
                      var tabTitle = tabs.items.items[i].title;
                      //str表示点击时创建tab的title，判断tabpanel中的tab的title是否等于str
                      if (tabTitle == name) {
                          flag = true;
                          tab = tabs.items.items[i];
                          break;
                      }
                  }
                  if(flag){
                      tabs.setActiveTab(tab)
                  }else{
                      if(tabs.items.getCount()<21){
                          tabs.add({
                              itemId:pid,
                              closable: true,
                              html:"<iframe src='help/document/"+pid+"/detailsContext' width='100%' height='800'></iframe>",
                              title: name
                          }).show();
                      }
                  }
              }

    });
    // trigger the data store load

    Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

    var viewport = Ext.create('Ext.Viewport', {
        id: 'border-example',
        layout: 'border',
        items: [
            Ext.create('Ext.Component', {
                region: 'north',
                height: 50,
                html:'<br/></a><img style="vertical-align: middle;margin-right: 5px;" name="img" src="resource/images/help.png"/>'
            }),
            {
                region: 'center',
                margins:'2',
                layout: 'border',
                tbar:[
                    Ext.create('Ext.form.field.ComboBox', {
                        id:"category",
                        fieldLabel: '组件类型',
                        multiSelect: false,
                        displayField: 'name',
                        valueField:'category',
                        labelWidth: 130,
                        editable:false,
                        width:400,
                        store: categoryStore,
                        queryMode: 'local'
                    }),'-',
                    Ext.create('Ext.form.field.ComboBox', {
                        fieldLabel: '帐套',
                        multiSelect: true,
                        displayField: 'name',
                        width:400,
                        labelWidth: 130,
                        store: createStore(),
                        queryMode: 'local'
                    }),
                    '->', '查询内容:', {
                        id:"cName",
                        xtype: 'textfield',
                        name: 'cName',
                        emptyText: '请输入组件名称'
                    }, {
                        icon: 'ext/images/view.png',
                        handler: function () {
                           var search=Ext.getCmp("cName").getValue();
                           var category=Ext.getCmp("category").getValue();
                            storeParam.search =search;
                            storeParam.category=category;
                            store.loadPage(1);
                        }
                    }],
                items:[grid, tabs
                ]
            }
        ]
    });
    if(curPid!=''){
        storeParam.search=curPid;
        tabs.add({
            itemId:curPid,
            closable: true,
            html:"<iframe src='help/document/"+curPid+"/detailsContext' width='100%' height='800'></iframe>",
            title: curName
        }).show();
    }
    store.loadPage(1);
});
