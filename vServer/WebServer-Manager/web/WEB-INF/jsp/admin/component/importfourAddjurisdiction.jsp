<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-9
  Time: 下午4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-权限分配</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <script type="text/javascript">
        Ext.onReady(function () {
            Ext.QuickTips.init();
            var formPanel = Ext.create('Ext.form.Panel', {
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
                    }
                ]
            });
            Ext.create('Ext.panel.Panel', {
                renderTo: Ext.get("navigation"),
                height:65,
                frame:true,
                region:'center',

                layout: 'column',
                items:[{
                    xtype:'button',
                    text: '下一步',
                    width:60,
                    style:'float:right;margin-top:15px;margin-right:50px;',
                    handler: function () {
                        Ext.getCmp("codes").setValue("${codes}");
                        Ext.getCmp("dbpid").setValue("${dbpid}");
                        formPanel.form.url=rootPath+'component/componentmain/addaccountinnav';
                        formPanel.form.standardSubmit=true;
                        formPanel.form.submit();
                    }
                }]
            });
        });
    </script>
</head>
<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=权限分配"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">
        <div id="navigation">
        </div>
    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>