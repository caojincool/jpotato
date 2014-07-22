<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 13-1-17
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>导入4代数据库--选择表组</title>
    <jsp:include page="../../_header.jsp" />
</head>
<body>
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div>
    <div id="context" class="context" style="height: 750px;">
        <div class="breadcrumbs">
            <a href="component/importfour/db">选择帐套</a>
            <span>选择表组</span>
        </div>
        <div style="width: 870px; margin: 15px;">

           <div id="navigation" class="defaultcontent">

            </div>
            <div class="form_default">
            <p>
                <button type="submit" onclick="upsetup()" style=" margin-left: 550px;">上一步</button>
                <button type="submit" onclick="nextsetup()" style=" margin-left: 50px;">下一步</button>
            </p>
            </div>
        </div>

    </div>

</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
</body>
</html>
<script type="text/javascript">
Ext.onReady(function () {
    Ext.QuickTips.init();
    var conheight=600;
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
            maxValue: nowdate,
            listeners:{
                change:function(){
                     if(Ext.getCmp("accounttime").getErrors()=="")
                     {
                         codearray.splice(0,codearray.length);
                         storeParam.time=Ext.getCmp("accounttime").getValue();
                         datastore.load({
                             params:storeParam
                         });
                         tablegroupstore.clearData();
                         tablegroupstore.load();
                     }
                }
            }
        }]
    });


    var datastore = Ext.create('Ext.data.TreeStore', {
        model: 'account',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            url: rootPath+'component/importfour/accountnavigate?dbpid=${dbpid}&account=${vaccount}'
        },
        folderSort: true,
        listeners:{
            load:function(st, root){
                function temp(node) {
                    if(node == null) return;
                    node.set('iconCls', 'files-16');
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

    var tablegroupstore= Ext.create('Ext.data.Store', {
        model: 'account',
        proxy: {
            type: 'ajax',
            extraParams:storeParam,
            url: rootPath+'component/importfour/accounttablegrup?dbpid=${dbpid}&account=${vaccount}'
        },
        autoLoad:true
    });
    var lefttree = Ext.create('Ext.tree.Panel', {
        width: '30%',
        height:conheight-65,
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
                        url :rootPath+"component/importfour/addtablegrup",
                        params : {
                            dbpid:'${dbpid}',
                            account:'${vaccount}'
                        },
                        method : 'GET',
                        success : function(resp,opts) {
                            var respText = Ext.decode(resp.responseText);
                            if(respText.success)
                                Ext.Msg.confirm("完成",  respText.message,function(btn){
                                    if(btn=='yes'){
                                        window.location.href=rootPath+"index";
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
        height:conheight-65,
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



    Ext.widget('panel',{
        id:'importaccpanel',
        frame: true,
        layout: 'border',
        height:conheight,
        renderTo: Ext.get("navigation"),
        items:[toppanel,lefttree,righttree]
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

function upsetup(){
    window.location.href=""+rootPath+"component/importfour/db";
}
function nextsetup(){
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
                },
                {
                    fieldLabel:'accound',
                    name:'account',
                    id:'account'
                }
            ]
        });
        Ext.getCmp("codes").setValue(codes);
        Ext.getCmp("dbpid").setValue("${dbpid}");
        Ext.getCmp("account").setValue("${vaccount}");
        formPanel.form.url=rootPath+'component/importfour/addtablegrup';
        formPanel.form.standardSubmit=true;
        formPanel.form.submit();

    }
}

</script>