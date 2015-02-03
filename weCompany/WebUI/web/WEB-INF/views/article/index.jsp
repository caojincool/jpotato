<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/16
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#category').datagrid({
                title:'文章管理',
                iconCls:'icon-save',
                nowrap: false,
                striped: true,
                url:'/article/category/list',
                method:'post',
                sortName: 'name',
                sortOrder: 'desc',
                remoteSort: true,
                fitColumns:true,
                fit:true,
                style:{padding:5},
                idField:'id',
                frozenColumns:[[
                    {field:'id',checkbox:true}
                ]],
                columns:[[
                    {title:'名称',field:'name',width:100,aligh:'left'},
                    {title:'所属栏目',field:'forumName',width:80,aligh:'left'},
                    {title:'所属类别',field:'categoryName',width:80,aligh:'left'},
                    {title:'关键词',field:'keyWord',width:80,aligh:'left'},
                    {title:'点击次数',field:'viewCount',width:80,aligh:'left'},
                    {title:'创建时间',field:'createDate',width:80,aligh:'left'},
                    {title:'更新时间',field:'updateDate',width:80,aligh:'left'},
                    {title:'显示',field:'enabled',width:20,aligh:'center',
                        formatter:function(value,rec){
                            return value?"显示":"隐藏";
                        }
                    }
                ]],
                pagination:true,
                rownumbers:true,
                onLoadSuccess:function(d){
                    $('#btndel').linkbutton('disable');
                    $('#btnedit').linkbutton('disable');
                },
                onClickRow:function(i,date){
                    var lh=$('#category').datagrid("getSelections").length;
                    if(lh==0) {
                        $('#btndel').linkbutton('disable');
                        $('#btnedit').linkbutton('disable');
                    }else if(lh==1){
                        $('#btndel').linkbutton('enable');
                        $('#btnedit').linkbutton('enable');
                    }else{
                        $('#btndel').linkbutton('enable');
                        $('#btnedit').linkbutton('disable');
                    }

                },
                toolbar:[{
                    id:'btnadd',
                    text:'新增',
                    iconCls:'icon-add',
                    handler:function(){
                        this.href="/article/view";
                    }
                },{
                    id:'btndel',
                    text:'删除',
                    disabled:true,
                    iconCls:'icon-cut',
                    handler:function(){
                        var sls=$('#category').datagrid("getSelections");
                        var msg="",ids="";
                        $.each(sls,function(i,d){
                            msg+= d.name+",";
                            ids+= d.id+",";
                        })
                        msg=msg.substr(0,msg.length-1);
                        ids=ids.substr(0,ids.length-1);

                        if(confirm("您确认要删除:"+msg+"吗？")){
                            $.ajax({
                                method:'get',
                                url: '/category/remove',
                                data:{ids:ids},
                                success: function(){
                                    $('#category').datagrid('reload');
                                }
                            });
                        }

                    }
                },'-',{
                    id:'btnedit',
                    text:'编辑',
                    disabled:true,
                    iconCls:'icon-save',
                    handler:function(){
                        var id=$('#category').datagrid("getSelected").id;
                        this.href="/category/view?id="+id;
                    }
                }]
            });
        });
    </script>
    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
</head>

<body class="easyui-layout layout">
<div border="false" region="center">
    <table id="category"></table>
</div>
</body>
</html>
