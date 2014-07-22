<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-4
  Time: 下午1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加组件-挂载导航</title>
    <jsp:include page="../_header.jsp"/>
    <link rel="stylesheet" media="screen" href="resource/css/userdef.css"/>
    <link rel="stylesheet" media="screen" href="resource/css/froms_dl.css"/>
    <script type="text/javascript">
        function returnurl()
        {
            var category = document.getElementById("category").value;
            var pid = document.getElementById("pid").value;
            window.top.location.href=rootPath+ "component/componentmain/quanxian?category="+category+"&pid="+pid;
        }
    </script>
</head>

<body class="bodygrey">
<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp?titlename=挂载导航"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>
    <div class="maincontent">

        <div id="navigation">
            <form action="component/componentmain/daohangsubmit" method="post" enctype="multipart/form-data">
                <div></div>
                <input id="pid" name="pid" value="${resource.pid}" style="display: none;"/>
                <input id="category" name="category" value="${resource.category}" style="visibility: collapse;"/>
                <div>
                    <table class="selftable">
                        <tr>
                            <td class="selftablelef">组件名称:</td>
                            <td>${resource.name}</td>
                        </tr>
                        <tr>
                            <td>组件类型:</td>
                            <td>${resource.category}</td>
                        </tr>

                        <tr>
                            <td >导航类型:</td>
                            <td style="width: 200px;" ><select name="categorytype" tabindex="2">
                                <c:forEach var="category" items="${navigation}" varStatus="status" >
                                    <option value="${navigation[status.count-1].getPid()}">${navigation[status.count-1].getName()}</option>
                                </c:forEach>
                            </select></td>
                        </tr>

                    </table></div>
                <div>
                    <input class="selfbutton" type="button" onclick="javascript:returnurl();" value="上一步">

                    <input class="selfbutton" type="submit"  value="完成">
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