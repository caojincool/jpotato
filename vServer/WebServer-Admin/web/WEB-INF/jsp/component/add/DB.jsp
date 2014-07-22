<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-10
  Time: 下午2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建账号</title>
    <jsp:include page="../../common/_header.jsp" />
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation/form.css" />
</head>
<body  class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
    <div class="breadcrumbs">
        <a href="javascript:void(0);">填写基础信息</a>
        <a href="javascript:void(0);">编辑组件内容</a>
        <a href="javascript:void(0);"> <span>编辑预览图和详情</span></a>
        <a href="javascript:void(0);">  <span>组件权限分配</span></a>
        <a href="javascript:void(0);"><span>加入导航</span></a>
        <span>完成</span>
    </div>
        <h1 class="pageTitle">数据库连接内容</h1>
        <form method="post" action="component/db/doAdd">
            <div class="form_default">
                <fieldset style="width :100%; margin :5px 5px 5px 0; padding: 5px; border :1px solid #666; background :#F7F7F7;">
                    <legend style="font-size: 16px;">数据源信息</legend>
                    <p>
                        <label for="name">组件名称</label>
                        <input type="text" class="sf" name="name" value="${resource.name}" id="name"/>
                    </p>
                    <p>
                        <label for="dbtype">数据库产品</label>
                        <select name="dbtype"  id="dbtype" class="ssf"  onchange="getDbNames()">
                            <option value="SQLSERVER" selected="selected">SQLSERVER</option>
                            <option value="MYSQL">MYSQL</option>
                            <option value="ORCALE">ORCALE</option>
                            <option value="DB2">DB2</option>
                        </select>
                    </p>
                    <p>
                        <label for="server">服务器地址</label>
                        <input type="text" class="sf -required" name="server" id="server" onchange="getDbNames()"/>
                    </p>
                    <p>
                        <label for="username">用户名</label>
                        <input type="text" class="sf" name="username" id="username" onchange="getDbNames()"/>
                    </p>
                    <p>
                        <label for="password">登陆密码</label>
                        <input type="password" class="sf" name="password" id="password" onchange="getDbNames()"/>
                    </p>
                    <p>
                        <label for="maxActive">最大连接数</label>
                        <input type="text" class="sf" name="maxActive" id="maxActive" value="500"/>
                    </p>
                    <p>
                        <label for="maxWait">最小连接数</label>
                        <input type="text" class="sf" name="maxWait" id="maxWait" value="1"/>
                    </p>
                    <p>
                        <label for="maxIdea">等待时间</label>
                        <input type="text" class="sf" name="maxIdea" id="maxIdea" value="5000"/>
                    </p>
                    <p>
                        <label for="dbName">选择数据库</label>
                        <select name="dbName" class="ssf"  id="dbName">
                        </select>
                        <script type="text/javascript">
                            function getDbNames() {
                                jQuery("#dbName").empty();
                                var param = {
                                    dbtype:jQuery('#dbtype').val(),
                                    server:jQuery('#server').val(),
                                    username:jQuery('#username').val(),
                                    password:jQuery('#password').val(),
                                    maxActive:jQuery('#maxActive').val(),
                                    maxWait:jQuery('#maxWait').val(),
                                    maxIdea:jQuery('#maxIdea').val()
                                };
                                jQuery.ajax({ url: rootPath + 'component/db/names/get',
                                    dataType: "json",
                                    type:"POST",
                                    data:param,
                                    success: function(res){
                                        var data = res.entity;
                                        if(data == null) return;
                                        for(var i=0; i<data.length; i++) {
                                            jQuery("#dbName").append("<option value=\'"+data[i]+"\'>"+data[i]+"</option>")
                                        }
                                    }
                                });
                            }
                        </script>
                    </p>
                </fieldset>
            </div>
            <div class="bottombtn">
                <button class="button button_red" type="button" onclick="del();" >删除</button>
                <button class="button button_blue" type="submit" onclick="next();" >下一步</button>
                <button class="button button_black" type="submit" onclick="finish();">完成</button>
            </div>
        </form>
</div>
<br clear="all" />
<div class="footer">
    <jsp:include page="../../common/_footer.jsp" />
</div>
<jsp:include page="../common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${resource.pid}"></jsp:param>
    <jsp:param name="category" value="${resource.category}"></jsp:param>
</jsp:include>
</body>
</html>