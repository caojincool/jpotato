<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-8
  Time: 下午3:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>添加组件-导入4代表组</title>
<jsp:include page="../_header.jsp"/>
<link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
<script type="text/javascript">
Ext.onReady(function () {
    Ext.QuickTips.init();
    var  pageSize = 16;
    Ext.define('account', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'code'},
            {name:"categoryChs"},
            {name:'accountSymbol'},
            {name:'category'},
            {name:'nameFormat'},
            {name:'disabled'},
            {name:'accountState'},
            {name:'update4Date'},
            {name:'import5Date'},
            {name: 'name'},
            {name: 'skinSolution'} ,
            {name: 'booksGuid'},
            {name: 'reportGuid'}

        ],
        idProperty: 'code'
    });
    var nowdate=new Date();
    var getTimeString=function(time){
        return time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate();
    }
    storeParam = {
        code:'',
        tablegroup:'',
        time:getTimeString(nowdate)
    };




    var toppanel= Ext.create('Ext.panel.Panel', {
        width:'100%',
        title:'搜索',
        height:65,
        frame:true,
        region:'north',
        layout: 'column',
        defaults:{
            margin:'5',
            labelWidth:60,
            width:200
        },
        items:[{
            xtype: 'datefield',
            anchor: '100%',
            fieldLabel: '时间',
            value:nowdate,
            name: 'accounttime',
            id:'accounttime',
            maxValue: nowdate
        },{
            xtype:'button',
            text: '更改',
            width:50,
            listeners: {
                click: function() {
                    storeParam.time=Ext.getCmp("accounttime").getValue();
                    datastore.load({
                        params:storeParam
                    });
                }
            }
        }]
    });


    var datastore = Ext.create('Ext.data.TreeStore', {
        model: 'account',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            url: rootPath+'component/componentmain/four/accountnavigate?dbpid=${dbpid}&account=${account}'
        },
        folderSort: true
    });

    var tablegroupstore= Ext.create('Ext.data.Store', {
        model: 'account',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            url: rootPath+'component/componentmain/four/accounttablegrup?dbpid=${dbpid}&account=${account}'
        },
        autoLoad:true
    });
    var lefttree = Ext.create('Ext.tree.Panel', {
        width: '30%',
        height:conheight-130,
        margins: '0 1 0 0',
        rootVisible: false,
        split:true,
        store: datastore,
        enableColumnHide:false,
        sortableColumns:false,
        multiSelect: false,
        singleExpand: true,
        region:"west",
        columns: [{
            xtype: 'treecolumn',
            text: '表组',
            icon:rootPath+'ext/resources/themes/images/default/tree/folder.gif',
            flex: 1,
            dataIndex: 'name'
        }],
        tbar: Ext.create('Ext.Toolbar',{
            items: [{
                text: '全部导入',
                icon: 'ext/images/add.png',
                handler: function () {
                    Ext.Ajax.request({
                        url :rootPath+"component/componentmain/addtablegrup",
                        params : {
                            dbpid:'${dbpid}',
                            account:'${account}',
                            date:Ext.getCmp("accounttime").getValue()
                        },
                        method : 'GET',
                        success : function(resp,opts) {
                            var respText = Ext.decode(resp.responseText);
                            if(respText.success)
                                Ext.Msg.confirm("完成",  respText.message,function(btn){
                                    if(btn=='yes'){
                                        window.location.href=rootPath+"component/componentmain";
                                    }
                                },this);
                            else
                                Ext.Msg.alert("失败", respText.message);
                        },
                        failure : function() {
                            Ext.Msg.alert("提示", "方法调用失败");
                        }
                    });
                }
            }]
        })
    });

    lefttree.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
            storeParam.code  = selectedRecord[0].data.code;
            storeParam.time=Ext.getCmp("accounttime").getValue();

            tablegroupstore.load({
                params:storeParam
            });
        }
    });

    var righttree=Ext.create('Ext.grid.Panel', {
        width:'70%',
        height:conheight-130,
        margins: '0 0 0 1',
        store: tablegroupstore,
        enableColumnHide : false,
        sortableColumns:false,
        region:"center",
        loadMask: { msg: '正在加载数据，请稍等……' },
        columns: [{
            text: '表组',
            flex: 2,
            dataIndex: 'name'
        },{
            text: '类型',
            flex: 1,
            dataIndex: 'categoryChs'
        },{
            text: '导入5代时间',
            flex: 1,
            dataIndex: 'import5Date'
        },{
            text: '4代更新时间',
            flex: 1,
            dataIndex: 'update4Date'
        },{
            text: '状态',
            flex: 1,
            dataIndex: 'accountState'
        },{
            text: '<input onclick="selectAllcheckbox(this);" type="checkbox" />导入',
            xtype: 'templatecolumn',
            flex: 1,
            tpl: '<input name="importcheck" onclick="selectSingleCheck(this)" code="{code}" type="checkbox" {checked}/>'
        }]
    });

    righttree.store.on('load',function(store){
        for(var j=0;j<store.data.items.length;j++){
            store.data.items[j].data.checked="";
        }
        for(var j=0;j<store.data.items.length;j++){
            for(var i=0;i<codearray.length;i++){
                if(codearray[i]==store.data.items[j].data.code){
                    store.data.items[j].data.checked="checked";
                }
            }
        }
        righttree.getView().refresh();

    });

    var formPanel = Ext.create('Ext.form.Panel', {
        defaults:{
            xtype:'textfield'
        },
        items: [
            {
                fieldLabel:'codes',
                name:'codes',
                id:'codes'
            } ,
            {
                fieldLabel:'dbpid',
                name:'dbpid',
                id:'dbpid'
            }
        ]
    });


    var bottompanel=Ext.create('Ext.panel.Panel', {
        height:65,
        frame:true,
        region:'south',
        items:[{
            xtype:'button',
            text: '下一步',
            width:60,
            style:'float:right;margin-top:15px;margin-right:50px;',
            handler: function () {
                var codes="";
                for(var i=0;i<codearray.length;i++)
                {
                    codes+=codearray[i]+"||";
                }
                if(codes=="")
                {
                    Ext.Msg.alert("提示","请选择要导入的表组");
                }else
                {

                    Ext.getCmp("codes").setValue(codes);
                    Ext.getCmp("dbpid").setValue("${dbpid}");
                    formPanel.form.url=rootPath+'component/componentmain/addtablegrup';

                    formPanel.form.submit({waitMsg : ' ......',
                        success : function(response, options) {
                            var respText = Ext.decode(options.response.responseText);
                            if(respText.success)
                                Ext.Msg.confirm("完成",  respText.message,function(btn){
                                    if(btn=='yes'){
                                        formPanel.form.url=rootPath+'component/componentmain/addjurisdiction';
                                        formPanel.form.standardSubmit=true;
                                        formPanel.form.submit();
                                      //  window.location.href=rootPath+"component/componentmain/addjurisdiction?dbpid=${dbpid}&account=${account}";
                                    }
                                },this);
                            else
                                Ext.Msg.alert("失败", respText.message);
                        },
                        failure : function() {
                            Ext.Msg.alert("提示", "方法调用失败");
                        }});
                }
            }
        },{
            xtype:'button',
            text: '上一步',
            width:60,
            style:'float:right;margin-top:15px;margin-right:20px;',
            handler: function () {
                window.location.href=""+rootPath+"component/componentmain/importfour?dbpid=${dbpid}";
            }

        }]
    });

    Ext.widget('panel',{
        id:'importaccpanel',
        frame: true,
        layout: 'border',
        height:conheight,
        renderTo: Ext.get("navigation"),
        items:[toppanel,lefttree,righttree,bottompanel]
    });
    Ext.EventManager.onWindowResize(function(){
        Ext.getCmp('importaccpanel').doLayout();
    });
});

var codearray=new Array();
function selectAllcheckbox(obj)
{
    var checkdtvalue=Ext.get(obj).getAttribute("checked");
    Ext.select('*[name=importcheck]').each(function(){
        this.dom.checked = checkdtvalue;
        if(checkdtvalue){
            pushArray(Ext.get(this).getAttribute("code"));
        }else{
            removeArray(Ext.get(this).getAttribute("code"));
        }
    });
}
function pushArray(code)
{
    var havecode=false;
    for(var i=0;i<codearray.length;i++){
        if(codearray[i]==code){
            havecode=true;
            break;
        }
    }
    if(!havecode){
        codearray.push(code);
    }
}
function removeArray(code){
    for(var i=0;i<codearray.length;i++){
        if(codearray[i]==code){
            codearray.splice(i,1);
            break;
        }
    }
}

function selectSingleCheck(obj)
{
    var checkdtvalue=Ext.get(obj).getAttribute("checked");
    if(checkdtvalue){
        pushArray(Ext.get(obj).getAttribute("code"));
    }else{
        removeArray(Ext.get(obj).getAttribute("code"));
    }
}

</script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=导入表组"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <div id="navigation">
        </div>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>