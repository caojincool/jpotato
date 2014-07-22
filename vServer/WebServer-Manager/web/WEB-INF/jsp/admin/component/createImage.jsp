<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-13
  Time: 下午2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-修改图片</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <script type="text/javascript">
        function returnurl()
        {
            window.top.location.href=rootPath+ "component/componentmain";
        }
    </script>
</head>

<body class="bodygrey">
<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=修改图片"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">

        <div id="navigation">
            <form action="component/componentmain/image/update" method="post" enctype="multipart/form-data">
                <div></div>
                <input name="pid" value="${resource.pid}" style="display: none;"/>
                <div>
                    <table class="selftable">
                        <tr>
                            <td class="selftablelef">名称:</td>
                            <td>${resource.name}</td>
                        </tr>
                        <tr>
                            <td>图片名:</td>
                            <td>${resource.imageName}</td>
                        </tr>
                        <tr>
                            <td>图片大小:</td>
                            <td>${resource.imageSize} <B>Byte</B></td>
                        </tr>
                        <tr>
                            <td>说明:</td>
                            <td><input id="remark" name="remark" type="text" value="${resource.remark}"/></td>
                        </tr>
                        <tr>
                            <td>选择图片:</td>
                            <td><input id="file" name="file" type="file"/></td>
                        </tr>
                    </table></div>
                <div>
                    <input class="selfbutton" type="button" onclick="javascript:returnurl();" value="返回">

                    <input class="selfbutton" type="submit"  value="下一步">
                </div>
            </form>
        </div>

    </div>
    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>