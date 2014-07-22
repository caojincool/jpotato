<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-13
  Time: 下午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-修改表组</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <script type="text/javascript">
        Ext.onReady(function () {
            Ext.QuickTips.init();
            Ext.define('ViNavigate', {
                extend: 'Ext.data.Model',
                fields: [
                    {name:'code'},
                    {name:'category'},
                    {name:'nameFormat'},
                    {name:'name'}
                ],
                idProperty: 'code'
            });
            var datastore = Ext.create('Ext.data.TreeStore', {
                model: 'ViNavigate',
                proxy: {
                    type: 'ajax',
                    url: 'component/componentmain/four/getnavigate?pid=${resource.pid}'
                },
                folderSort: true
            });

            var tree1 = Ext.create('Ext.tree.Panel', {
                width: '100%',
                rootVisible: false,
                store: datastore,
                enableColumnHide:false,
                sortableColumns:false,
                multiSelect: false,
                singleExpand: true,
                columns: [{
                    xtype: 'treecolumn',
                    text: '表组',
                    flex: 2,
                    sortable: true,
                    dataIndex: 'name'
                },{
                    text: 'code',
                    flex: 1,
                    dataIndex: 'code',
                    sortable: true
                }]
            });
            var seldata;
            tree1.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
                if (selectedRecord.length) {
                    seldata=selectedRecord[0].data;
                }
            });
            var win = Ext.create('Ext.window.Window', {
                title:'添加表组',
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
                items: tree1 ,
                buttons:[{
                    text:'确定',
                    handler:function(){
                        if(seldata!=null){
                            document.getElementById('tableCategory').value= seldata.category;
                            document.getElementById('key').value= seldata.code;
                            document.getElementById('code').value= seldata.code;
                            document.getElementById('nameFormat').value= seldata.nameFormat;
                            win.close();
                        }
                    }
                }, {
                    text:'取消',
                    handler:function(){
                        win.close();
                    }
                }]
            });
            Ext.get("selecttg").on("click",function(){
                win.show();
            });
        });
        <%--jQuery(document).ready(function () {--%>
            <%--jQuery("#tableCategory").find("option").each(function(){--%>
                        <%--if(jQuery(this).val()=='${resource.tableCategory}')--%>
                        <%--{--%>
                            <%--jQuery(this).attr("selected","selected");--%>
                        <%--}--%>
                    <%--}--%>
            <%--);--%>
        <%--});--%>
        function editdb()
        {
            Ext.Ajax.request({
                url :"component/componentmain/tablegroup/update",
                params : {
                    pid:'${resource.pid}',
                    tableCategory:Ext.get('tableCategory').getValue(),
                    key:Ext.get('key').getValue(),
                    code:Ext.get('code').getValue(),
                    nameFormat:Ext.get('nameFormat').getValue()
                },
                method : 'POST',
                success : function(resp,opts) {
                    var respText = Ext.decode(resp.responseText);
                    if(respText.success)
                        Ext.Msg.alert("成功", "修改成功");
                    else
                        Ext.Msg.alert("失败", respText.message);
                },
                failure : function() {
                    Ext.Msg.alert("提示", "方法调用失败");
                }
            });
        }
        function returnurl()
        {
            window.top.location.href=rootPath+ "component/componentmain";
        }
    </script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=修改表组"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <div id="navigation">
            <div></div>
            <div>
                <table class="selftable">
                    <tr>
                        <td class="selftablelef">名称</td>
                        <td>${resource.name}</td>
                    </tr>
                    <tr>
                        <td>选择表组</td>
                        <td><input id="selecttg"  class="selfbutton" type="button" value="选择"/></td>
                    </tr>
                    <tr>
                        <td>表类型</td>
                        <td><input id="tableCategory" type="text" value="${resource.tableCategory}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>集合主键</td>
                        <td><input id="key" type="text" value="${resource.key}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>编码</td>
                        <td><input id="code" type="text" value="${resource.code}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>名称格式化</td>
                        <td><input id="nameFormat" type="text" value="${resource.nameFormat}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>版本号</td>
                        <td>${resource.version}</td>
                    </tr>
                </table></div>
            <div>
                <input class="selfbutton" type="button" onclick="javascript:editdb();" value="修改">
                <input class="selfbutton" type="button" onclick="javascript:returnurl();" value="返回">
            </div>
        </div>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>