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
    <title>修改数据库配置</title>
    <jsp:include page="../../common/_header.jsp"/>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
        <div>
            <div class="breadcrumbs" style="margin-bottom: 15px;">
                <a href="#">编辑数据库连接</a>
            </div>
            <h1 class="pageTitle">数据库连接内容</h1>

            <form method="post" action="">
                <div class="form_default">
                    <fieldset
                            style="width :98.5%; margin :5px 5px 5px 0; padding: 5px; border :1px solid #666; background :#F7F7F7;">
                        <legend style="font-size: 16px;">数据源信息</legend>
                        <p>
                            <label for="name">组件名称</label>
                            <input type="text" class="sf" name="name" value="${resource.name}" id="name"/>
                        </p>
                        <p>
                            <label for="dbtype">数据库产品</label>
                            <input type="hidden" id="dbtype" name="dbtype" value="SQLSERVER"/>
                            <select disabled="true" name="db1type" class="ssf"  onchange="getDbNames()">
                                <option value="SQLSERVER" selected="selected">SQLSERVER</option>
                                <option value="MYSQL">MYSQL</option>
                                <option value="ORCALE">ORCALE</option>
                                <option value="DB2">DB2</option>
                            </select>
                        </p>
                        <p>
                            <label for="server">服务器地址</label>
                            <input type="text" class="sf" name="server" value="${resource.server}" id="server"
                                   onchange="getDbNames()"/>
                        </p>

                        <p>
                            <label for="username">用户名</label>
                            <input type="text" class="sf" name="username" value="${resource.username}" id="username"
                                   onchange="getDbNames()"/>
                        </p>

                        <p>
                            <label for="password">登陆密码</label>
                            <input type="password" class="sf" name="password" value="${resource.password}" id="password" onchange="getDbNames()"/>
                        </p>

                        <p>
                            <label for="maxActive">最大连接数</label>
                            <input type="text" class="sf" name="maxActive" value="${resource.maxActive}"
                                   id="maxActive"/>
                        </p>

                        <p>
                            <label for="maxWait">最小连接数</label>
                            <input type="text" class="sf" name="maxWait" id="maxWait" value="${resource.maxIdea}"/>
                        </p>

                        <p>
                            <label for="maxIdea">等待时间</label>
                            <input type="text" class="sf" name="maxIdea" value="${resource.maxWait}" id="maxIdea"/>
                        </p>

                        <p>
                            <input type="hidden" name="dbName" class="ssf"  value="${resource.dbName}"/>
                            <%--<label for="dbName">选择数据库</label>--%>
                            <%--<select name="dbName" style="width: 150px;" id="dbName">--%>
                            <%--</select>--%>
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
                                getDbNames();
                            </script>
                        </p>

                    </fieldset>
                    <div class="bottombtn">

                        <button type="submit">修改</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
    <br clear="all"/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>