<%--
  Created by IntelliJ IDEA.
  User: dp
  Date: 14-11-13
  Time: 下午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
    <script>
        $(function(){
            $('#forum').datagrid({
                title:'栏目管理',
                iconCls:'icon-save',
                nowrap: false,
                striped: true,
                url:'/jquery-easyui-1.2.6/demo/datagrid_data.json',
                sortName: 'code',
                sortOrder: 'desc',
                remoteSort: true,
                fitColumns:true,
                fit:true,
                idField:'code',
                frozenColumns:[[
                    {field:'ck',checkbox:true},
                    {title:'code',field:'code',width:80,sortable:true}
                ]],
                columns:[[
                    {title:'Base Information',colspan:3},
                    {field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
                        formatter:function(value,rec){
                            return '<span style="color:red">Edit Delete</span>';
                        }
                    }
                ],[
                    {field:'name',title:'Name',width:120},
                    {field:'addr',title:'Address',width:220,rowspan:2,sortable:true,
                        sorter:function(a,b){
                            return (a>b?1:-1);
                        }
                    },
                    {field:'col4',title:'Col41',width:150,rowspan:2}
                ]],
                pagination:true,
                rownumbers:true,
                toolbar:[{
                    id:'btnadd',
                    text:'Add',
                    iconCls:'icon-add',
                    handler:function(){
                        $('#btnsave').linkbutton('enable');
                        alert('add')
                    }
                },{
                    id:'btncut',
                    text:'Cut',
                    iconCls:'icon-cut',
                    handler:function(){
                        $('#btnsave').linkbutton('enable');
                        alert('cut')
                    }
                },'-',{
                    id:'btnsave',
                    text:'Save',
                    disabled:true,
                    iconCls:'icon-save',
                    handler:function(){
                        $('#btnsave').linkbutton('disable');
                        alert('save')
                    }
                }]
            });
            var p = $('#forum').datagrid('getPager');
            $(p).pagination({
                onBeforeRefresh:function(){
                    alert('before refresh');
                }
            });
        });
    </script>
</head>
<body class="easyui-layout layout">
<div style="padding: 8px;" border="false" region="center">
    <table id="forum"></table>
</div>
</div>
</body>
</html>
