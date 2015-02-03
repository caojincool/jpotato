<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2015/1/26
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>洋芋文章管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/metro/easyui.css" id="swicth-style">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
    <script type="text/javascript" src="/script/public.js"></script>
    <script type="text/javascript">
        $(function(){
            var root={id:'/',text:'根节点',state:'open'};
            //手风琴折叠
            $('#adn').accordion({
                animate:true,
                fit:true,
                border:false,
                onSelect:function(title,index){
                    if(title=='文件管理'){
                        $.ajax({
                            type:'POST',
                            cache:true,
                            url:'/fileMg/folder',
                            data:{id:'/'},
                            success:function(d){
                                root.children=d;
                                $('#folder').tree({
                                    animate: true,
                                    data:[root],
                                    onDblClick:function(node){
                                        $(this).tree('beginEdit',node.target);
                                    },
                                    onAfterEdit:function(node){
                                        $.ajax({
                                            type:'POST',
                                            url:'/fileMg/editFolder',
                                            data:{id:node.id,text:node.text,parent:node.attributes.parent},
                                            success:function(r){
                                                if(r.success==false) {
                                                    var temp=node.id.split('/');
                                                    var oName=temp[temp.length-2];
                                                    $.messager.alert('错误', r.message,'error');
                                                    $(this).tree('update',{target:node.target,text: oName});
                                                }
                                            }
                                        });
                                    },
                                    onClick: function(node){
                                        var href = '/fileMg/fileShow?id='+encodeURIComponent(node.id);
                                        var title = '文件列表';
                                        addTab(title, href);
                                    },
                                    onBeforeLoad:function(row,d){
                                        if(row){
                                            $(this).tree('options').url='/fileMg/folder'
                                        }
                                    }
                                });
                            }
                        })
                    }
                }
            });

            //新建目录
            $('#btnAddNode').click(function(){
                var node=$('#folder').tree('getSelected');
                if(node==null){
                    $.messager.alert('错误', '请选择目录','error');
                    return;
                }
                var nNode={
                    id:node.id+"新建文件夹/",
                    text:'新建文件夹',
                    attributes:[{parent:node.id}],
                    state:'open'
                };

                $.ajax({
                    type:'POST',
                    url:'/fileMg/addFolder',
                    data:{id:nNode.id,text:nNode.text,parent:nNode.attributes.parent},
                    success:function(r){
                        if(r.success==true) {
                            $('#folder').tree('append',{
                                parent:node.target,
                                data:[nNode]
                            });
                        }else{
                            $.messager.alert('错误', r.message,'error');
                        }
                    }
                });
            });
            //删除目录
            $('#btnRmNode').click(function(){
                var node=$('#folder').tree('getSelected');
                if(node==null){
                    $.messager.alert('错误', '请要删除选择目录','error');
                    return;
                }
                if(node.id=="/"){
                    $.messager.alert('提示', '根目录不能删除！','info');
                    return;
                }
                $.messager.confirm('确认','删除目录将该目录下文件及其子目录文件全部删除，请慎用！',function(r){
                    if (r){
                        $.ajax({
                            type:'POST',
                            url:'/fileMg/rmDir',
                            data:{id:node.id},
                            success: function (r) {
                                if(r.success){
                                    $('#folder').tree('remove',node.target);
                                }
                            }
                        })
                    }
                });
            });
            //刷新目录
            $('#btnRfNode').click(function(){
                var froot=$('#folder').tree('getRoot');
                $('#folder').tree('reload',froot.target);
            });

        })
    </script>
</head>
<body class="easyui-layout">
<div region="north" border="true" class="cs-north">
    <div class="cs-north-bg">
        <div class="cs-north-logo">洋芋网站管理</div>
        <ul class="ui-skin-nav">
            <li class="li-skinitem" title="gray"><span class="gray" rel="gray"></span></li>
            <li class="li-skinitem" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li>
            <li class="li-skinitem" title="pepper-grinder"><span class="pepper-grinder" rel="pepper-grinder"></span></li>
            <li class="li-skinitem" title="dark-hive"><span class="dark-hive" rel="dark-hive"></span></li>
        </ul>
    </div>
</div>
<div region="west" border="true" split="true" title="Navigation" class="cs-west">
    <div id="adn">
        <div title="系统设置" data-options="selected:true">
            <p><a href="javascript:void(0);" src="demo/easyloader/basic.html" class="cs-navi-tab">公司信息</a></p>
            <p><a href="javascript:void(0);" src="/imageMg" class="cs-navi-tab">主页设置</a></p>
        </div>
        <div title="内容设置">
            <p><a href="javascript:void(0);" src="/article/forumMg" class="cs-navi-tab">栏目管理</a></p>
            <p><a href="javascript:void(0);" src="/article/categoryMg" class="cs-navi-tab">类别管理</a></p>
            <p><a href="javascript:void(0);" src="/article/index" class="cs-navi-tab">文章管理</a></p>
        </div>
        <div title="文件管理" >
            <div class="easyui-layout" fit="true">
                <div region="north" border="false">
                    <a href="javascript:void(0);" id="btnAddNode" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" title="新建"></a>
                    <a href="javascript:void(0);" id="btnRmNode" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" title="删除"></a>
                    <a href="javascript:void(0);" id="btnRfNode" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" title="刷新"></a>
                </div>
                <div region="center" border="false">
                    <ul id="folder" class="easyui-tree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        <div title="Home">
            <div class="cs-home-remark">
                <h1>jQuery EasyUI 1.3.2 Demo</h1> <br>
                制作：purediy <br>
                博客：<a href="http://www.cnblogs.com/purediy" target="_blank">http://www.cnblogs.com/purediy</a><br>
                说明：jQuery EasyUI 1.3.2 Demo分类整理。
            </div>
        </div>
    </div>
</div>
<div region="south" border="false" class="cs-south">@tianshaojie@gmail.com</div>
<div id="mm" class="easyui-menu cs-tab-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
</div>
</body>
</html>
