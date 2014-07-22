<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-9
  Time: 下午5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-加入导航</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <script type="text/javascript">
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
            var store = Ext.create('Ext.data.TreeStore', {
                model: 'navigation',
                proxy: {
                    type: 'ajax',
                    url:rootPath+ 'component/navigation/list/get'
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
            var tree = Ext.create('Ext.tree.Panel', {
                renderTo: Ext.get("navigation"),
                region:'center',
                margins: '2',
                split:true,
                width: '100%',
                height:conheight-65,
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
                        icon   : rootPath+'ext/images/delete.png',
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
                                        url: rootPath+'component/navigation/delete?pid='+pid,
                                        method: 'GET',
                                        success: function (response, options) {
                                            Ext.MessageBox.alert('成功', '删除成功');
                                            store.reload();

                                            //更新Store
                                            cusstore.setProxy({
                                                type: 'ajax',
                                                url: rootPath+'component/navigation/list/getncdata',
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
                        icon: rootPath+'ext/images/add.png',
                        handler: function () {

                            resetMethod();   //添加时，清空窗体里的值
                            win.show();
                        }
                    }]
                })
            });
            //重置
            function resetMethod(){
                Ext.getCmp("filename").reset();
                Ext.getCmp("fileremark").reset();
            }
            var pid="";
            tree.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
                if (selectedRecord.length) {
                    pid = selectedRecord[0].data.pid;
                }
            });
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
                }
                ]});
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
                        wjpanel.form.url= rootPath+'component/navigation/addwj?parentpid='+pid;

                        wjpanel.form.submit({
                            waitMsg : ' ......',
                            success:function(action,form){
                                Ext.Msg.alert('成功', "操作成功");
                                pid="";
                                store.reload();
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

            var addformPanel = Ext.create('Ext.form.Panel', {
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
                    },{
                        fieldLabel:'navid',
                        name:'navid',
                        id:'navid'
                    }
                ]
            });

            Ext.create('Ext.panel.Panel', {
                renderTo: Ext.get("navigation"),
                height:65,
                frame:true,
                region:'center',

                layout: 'column',
                items:[{
                    xtype:'button',
                    text: '完成添加',
                    width:60,
                    style:'float:right;margin-top:15px;margin-right:50px;',
                    handler: function () {
                        Ext.getCmp("codes").setValue("${codes}");
                        Ext.getCmp("dbpid").setValue("${dbpid}");
                        Ext.getCmp("navid").setValue(pid);
                        addformPanel.form.url=rootPath+'component/componentmain/addcodeinnav';

                        addformPanel.form.submit({waitMsg : ' ......',
                            success : function(response, options) {
                                var respText = Ext.decode(options.response.responseText);
                                if(respText.success){
                                    window.location.href=rootPath+"component/componentmain";
                                }else
                                    Ext.Msg.alert("失败", respText.message);
                            },
                            failure : function() {
                                Ext.Msg.alert("提示", "方法调用失败");
                            }});
                    }
                }]
            });

        });
    </script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=加入导航"/>
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