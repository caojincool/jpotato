<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-8
  Time: 下午1:18
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
                    {name:'accountSymbol'},
                    {name:'category'},
                    {name:'nameFormat'},
                    {name: 'name'},
                    {name: 'skinSolution'} ,
                    {name: 'booksGuid'},
                    {name: 'reportGuid'}

                ],
                idProperty: 'code'
            });

            storeParam = {
                name:''
            };

            //获取所有帐套目录
            var datastore = Ext.create('Ext.data.Store', {
                model: 'account',
                proxy: {
                    type: 'ajax',
                    extraParams:storeParam,
                    url: 'component/componentmain/four/getaccount?dbpid=${dbpid}'
                },
                autoLoad:true
            });

            Ext.create('Ext.panel.Panel', {
                renderTo: Ext.get("navigation"),
                width:'100%',
                title:'搜索',
                height:65,
                frame:true,
                region:'center',
                layout: 'column',
                defaults:{
                    margin:'5',
                    labelWidth:60,
                    width:200
                },
                items:[{
                    xtype: 'textfield',
                    fieldLabel: '帐套名称',
                    id:'accName',
                    name: 'accName',
                    emptyText: '请输入帐套名称'
                },{
                    xtype:'button',
                    text: '搜索',
                    width:50,
                    listeners: {
                        click: function() {
                            accountcode="";
                            storeParam.name=Ext.getCmp('accName').getValue();
                            datastore.load({
                                params:storeParam
                            });
                        }
                    }
                }]
            });

            var accountgrid = Ext.create('Ext.grid.Panel', {
                id:'accountgrid',
                store:datastore,
                enableColumnHide : false,
                sortableColumns:false,
                height:conheight-130,
                width:'100%',
                columns: [
                    { header: '名称',width:180, dataIndex: 'name'},
                    { header: 'code',flex : 1, dataIndex: 'code'}
                ],
                loadMask: { msg: '正在加载数据，请稍等……' },
                renderTo: Ext.get("navigation")
            });

            var accountcode="";
            accountgrid.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
                if (selectedRecord.length) {
                    accountcode=selectedRecord[0].data.code;
                }
            });

            Ext.create('Ext.panel.Panel', {
                renderTo: Ext.get("navigation"),
                height:65,
                frame:true,
                region:'center',

                layout: 'column',
                items:[{
                    xtype:'button',
                    text: '下一步',
                    width:60,
                    style:'float:right;margin-top:15px;margin-right:50px;',
                    handler: function () {
                           if(accountcode==""){
                               Ext.Msg.alert('提示', "请先选择帐套");
                           }else
                           {
                               window.location.href=""+rootPath+"component/componentmain/importaccount?dbpid=${dbpid}&account="+accountcode;
                           }

                    }
                }]
            });
        });
    </script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=帐套选择"/>
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