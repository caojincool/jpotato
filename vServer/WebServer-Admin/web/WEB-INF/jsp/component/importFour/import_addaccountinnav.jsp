<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 13-1-17
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>导入4代数据库--加入导航</title>
    <jsp:include page="../../_header.jsp" />
</head>
<body>

<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp" />
<div>
    <div id="context" class="context" style="height: 600px;">
        <div class="breadcrumbs">
            <a href="component/importfour/db">选择帐套</a>
            <a href="component/importfour/selecttable?dblink=${dbpid}&account=${vaccount}">选择表组</a>
            <span>加入导航</span>
        </div>
        <div style="width: 870px; margin: 15px;">

            <div id="navigation" class="defaultcontent">

            </div>
            <div class="form_default">
                <p>
                    <button type="submit" onclick="upsetup()" style=" margin-left: 400px;">上一步</button>
                    <button type="submit" onclick="nextsetup()" style=" margin-left: 50px;">加入导航</button>
                    <button type="submit" onclick="setupcomplete()" style=" margin-left: 50px;">完成</button>
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
var pid="";
Ext.onReady(function () {
    Ext.QuickTips.init();
    var conheight=500;
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
                if(Ext.getCmp('filename').getValue()==''){
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


});
function upsetup(){
    window.location.href=""+rootPath+"component/importfour/selecttable?dblink=${dbpid}&account=${vaccount}";
}
function nextsetup(){
    if(pid=="")
    {
        Ext.Msg.alert('提示', "请选择要挂载的导航目录");
        return;
    }
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
    Ext.getCmp("codes").setValue("${codes}");
    Ext.getCmp("dbpid").setValue("${dbpid}");
    Ext.getCmp("navid").setValue(pid);
    addformPanel.form.url=rootPath+'component/importfour/addcodeinnav';

    addformPanel.form.submit({waitMsg : ' ......',
        success : function(response, options) {
            var respText = Ext.decode(options.response.responseText);
            if(respText.success){
                window.location.href=rootPath+"index";
            }else
                Ext.Msg.alert("失败", respText.message);
        },
        failure : function() {
            Ext.Msg.alert("提示", "方法调用失败");
        }});
}
function setupcomplete()
{
    window.location.href=rootPath+"index";
}

</script>