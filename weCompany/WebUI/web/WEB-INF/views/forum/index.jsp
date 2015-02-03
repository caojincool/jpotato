<%--
  Created by IntelliJ IDEA.
  User: dp
  Date: 14-11-13
  Time: 下午2:47
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

    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
    <script type="text/javascript">
        $(function(){

            $('#forum').datagrid({
                title:'栏目管理',
                iconCls:'icon-save',
                nowrap: false,
                striped: true,
                url:'/article/forum/list',
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
                columns:[[{title:'中文名称',field:'name',width:100,aligh:'left'},
                        {title:'英文名称',field:'nameEn',width:100,aligh:'left'},
                        {title:'显示',field:'enabled',width:20,aligh:'center',
                            formatter:function(value,rec){
                                return value?"显示":"隐藏";
                        }}
                ]],
                pagination:true,
                rownumbers:true,
                onLoadSuccess:function(d){
                    $('#btndel').linkbutton('disable');
                    $('#btnedit').linkbutton('disable');
                },
                onClickRow:function(i,date){
                    var lh=$('#forum').datagrid("getSelections").length;
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
                        this.href="/article/forum/view";
                    }
                },{
                    id:'btndel',
                    text:'删除',
                    disabled:true,
                    iconCls:'icon-cut',
                    handler:function(){
                        var sls=$('#forum').datagrid("getSelections");
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
                                url: '/forum/remove',
                                data:{ids:ids},
                                success: function(){
                                    $('#forum').datagrid('reload');
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
                        var id=$('#forum').datagrid("getSelected").id;
                        this.href="/forum/view?id="+id;
                    }
                }]
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
