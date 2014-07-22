<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>加入导航</title>
    <jsp:include page="../_header.jsp" />
</head>
<body>
<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp" />
<div>
    <div class="detailscontent">
        <div class="breadcrumbs">
            <a href="javascript:void(0);">填写基础信息</a>
            <a href="javascript:void(0);">编辑组件内容</a>
            <a href="javascript:void(0);">编辑预览图和详情</a>
            <a href="javascript:void(0);">组件权限分配</a>
            <a href="javascript:void(0);">加入导航</a>
            <span>完成</span>
        </div>
        <div style="margin: 15px 0px;">
            <h1 class="pageTitle">组件加入导航</h1>
                <div id="navigation" class="defaultcontent">
                </div>

            <div class="bottombtn">
                <button class="button button_red" type="button" onclick="del();" >删除</button>
                <button id="finish" class="button button_black"   type="button" onclick="nextsetup()" >完成</button>
            </div>
        </div>

    </div>
    <br clear="all" />
</div>
<br/>
<div class="extfooter">
    <jsp:include page="../common/_footer.jsp" />
</div>
<jsp:include page="common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>
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

    //重置
    function resetMethod(){

        Ext.getCmp("filename").reset();
        Ext.getCmp("fileremark").reset();
    }

    var tree = Ext.create('Ext.tree.Panel', {
        renderTo: Ext.get("navigation"),
        region:'center',
        margins: '2',
        title:'选择挂载导航',
        split:true,
        frame: true,
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
            flex: 1,
            sortable: true,
            dataIndex: 'name'
        }],

        tbar: Ext.create('Ext.Toolbar',{
            items: ['->',{
                text: '新增',
                icon: 'ext/images/add.png',
                id:'dhadd',
                disabled:true,
                tooltip:'新增导航',
                handler: function () {

                    resetMethod();   //添加时，清空窗体里的值

                    win.show();
                }
            },'-',{
                text:'删除',
                icon   : 'ext/images/delete.png',
                id:'dhdel',
                disabled:true,
                tooltip:'删除导航',
                handler: function(){
                    if(pid=="" || pid==null){
                        Ext.MessageBox.alert('提示', '请选择要删除的导航！');
                        return;
                    }
                    if(pid=="NAV00000000"){
                        Ext.MessageBox.alert('提示', '不能删除导航根节点！');
                        return;
                    }

                    Ext.MessageBox.confirm("警告","确定要删除该项及子集",function(e){
                        if(e=="yes"){

                            Ext.Ajax.request({
                                url: 'component/navigation/delete?pid='+pid,
                                method: 'GET',
                                success: function (response, options) {
                                    Ext.MessageBox.alert('成功', '删除成功');
                                    tree.setTitle('');
                                    //清空导航pid，否则再点击挂载组件，会将已删除的pid传入过去
                                    pid='';
                                    //清空导航父名称
                                    Ext.getCmp("dhname").reset();
                                    //设置三个按钮不可用
                                    Ext.getCmp('dhadd').setDisabled(true);
                                    Ext.getCmp('dhdel').setDisabled(true);

                                    store.reload();
                                },
                                failure: function (response, options) {
                                    Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
                                }
                            });
                        }
                    });
                }
            }]
        })
    });

    tree.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
            //设置添加导航时，展示的导航节点信息
            Ext.getCmp('dhname').setValue(selectedRecord[0].data.name);

            pid = selectedRecord[0].data.pid;
            tree.setTitle('已选中 (' + selectedRecord[0].data.name + ') 导航');

            Ext.getCmp('dhadd').setDisabled(false);
            Ext.getCmp('dhdel').setDisabled(false);
        }
    });

    var wjpanel = Ext.create('Ext.form.Panel', {
        id:'wjpanel',
        border:false,
        region     : 'center',
        bodyStyle  : 'padding: 10px; background-color: #DFE8F6',
        labelWidth : 60,
        defaults:{
            xtype:'textfield'
        },
        items: [{
            fieldLabel:'导航父节点',
            disabled:true,
            id:'dhname',
            name:'dhname',
            anchor: '98%'
        },{
            fieldLabel:'文件夹名称',
            id:'filename',
            name:'filename',
            anchor: '98%'
        },{
            xtype     : 'textareafield',
            grow      : true,
            fieldLabel:'文件夹说明',
            heigth:80,
            id:'fileremark',
            name:'fileremark',
            anchor: '98%'
        }]});
    //新增导航窗体
    win = Ext.create('Ext.window.Window', {
        title:'添加导航',
        height:240,
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
                wjpanel.form.url= 'component/navigation/addwj?parentpid='+pid;

                wjpanel.form.submit({
                    waitMsg : ' ......',
                    success:function(action,form){

                        Ext.Msg.alert('成功', "操作成功");
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

    Ext.get("navigation").on('DOMNodeRemovedFromDocument', function(){
        tree.destroy();
    });

});


function nextsetup(){
    if(pid=="")
    {
        window.location.href=rootPath+'component/main/${pid}/create/finish';
        return;
    }
    var addformPanel = Ext.create('Ext.form.Panel', {
        defaults:{
            xtype:'textfield'
        },
        items: [{
                fieldLabel:'pid',
                name:'pid',
                id:'pid'
            },{
                fieldLabel:'category',
                name:'category',
                id:'category'
            },{
                fieldLabel:'navid',
                name:'navid',
                id:'navid'
            }]
    });
    Ext.getCmp("pid").setValue("${pid}");
    Ext.getCmp("category").setValue("${category}");
    Ext.getCmp("navid").setValue(pid);
    addformPanel.form.url=rootPath+'component/main/navigate/add';
    addformPanel.form.standardSubmit=true;
    addformPanel.form.submit();
}
</script>