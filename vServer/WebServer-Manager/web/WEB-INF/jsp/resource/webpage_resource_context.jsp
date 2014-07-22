<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zongxudong
  Date: 12-10-26
  Time: 上午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../_header.jsp" />
    <meta http-equiv="pragma" content="no-cache">
    <script type="text/javascript">
        var rpid = '${resource.pid}';
        var rname = '${resource.name}';
    </script>
    <script type="text/javascript" src="editor/editor_config.js"></script>
    <script type="text/javascript" src="editor/editor_all.js"></script>
    <script type="text/javascript" src="editor/third-party/SyntaxHighlighter/shCore.js"></script>
    <link rel="stylesheet" href="editor/themes/default/ueditor.css" />
    <link rel="stylesheet" href="editor/third-party/SyntaxHighlighter/shCoreDefault.css" />
</head>
<body>



    <form id="form1" action="resource/${resource.pid}/context/update" method="post">
        <%--<script type="text/plain" id="editor" name="context">${context}</script>--%>
        
        <textarea id="editor" name="context">${context}</textarea>

        <br />
        <button>更新数据</button>
    </form>



<script type="text/javascript">

    SyntaxHighlighter.highlight();

    var editor = new baidu.editor.ui.Editor();
    editor.render("editor");
</script>
<script type="text/javascript">
    function doSubmit() {
        editor.sync();
        document.getElementById('form1').submit()
    }
</script>

</body>
</html>