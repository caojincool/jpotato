<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/16
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){

            $('#category').datagrid({
                title:'栏目类别管理',
                iconCls:'icon-save',
                nowrap: false,
                striped: true,
                url:'/category/categoryList',
                method:'post',
                sortName: 'name',
                sortOrder: 'desc',
                remoteSort: true,
                fitColumns:true,
                fit:true,
                idField:'id',
                frozenColumns:[[
                    {field:'id',checkbox:true}
                ]],
                columns:[[
                    {title:'中文名称',field:'name',width:100,aligh:'left'},
                    {title:'英文名称',field:'nameEn',width:100,aligh:'left'},
                    {title:'所属栏目',field:'forumName',width:80,aligh:'left'},
                    {title:'类型',field:'ctype',width:40,aligh:'left',
                        formatter:function(value,rec){
                            var v='';
                            if(value=='ARTICLELIST'){
                                v="<p title='显示文章标题'>文章列表</p>";
                            }else if(value=='ARTICLE'){
                                v="<p title='显示文章内容'>文章</p>";
                            }else{
                                v="<p title='显示产品列表'>产品列表</p>";
                            }
                            return v;
                        }
                    },
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
                        this.href="/category/view";
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
<div style="padding: 8px;" border="false" region="center">
    <table id="category"></table>
</div>
</div>
</body>
</html>
