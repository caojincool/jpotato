<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>HTML 编辑内容</title>
    <script type="text/javascript">
        var rootPath = '<%= rootPath %>';
        var resourcePid = '${resourcePid}';
        var rowid = '${rowid}';
        var col = '${col}';
        var plateform = '${plateform}';
        window.UEDITOR_HOME_URL = "<%= rootPath %>resource/ue/";
    </script>
    <style type="text/css">
        html, body {
            height: 100%;
            width: 100%;
        }

        #webEditor {
            height: 1000%;
            width: 100%;
        }
    </style>
    <script type="text/javascript" src="<%= rootPath %>resource/ue/ueditor.config.js"></script>
    <script type="text/javascript" src="<%= rootPath %>resource/ue/ueditor.all.js"></script>
    <script type="text/javascript">
        var editor = null;
        function show() {
            editor = UE.getEditor('editor', {
                fullscreen:true,
                imageUrl: rootPath + plateform + 'html/upload/image?pid=' + resourcePid + "&col=" + col + "&rowid=" + rowid,
                //imagePath: rootPath + 'view/html/upload/image/' + resourcePid + "/" + col,
                imagePath:'',
                imageFieldName:'file',
                imageManagerUrl: rootPath + plateform + 'html/image/' + resourcePid + "/" + col + "/" + rowid,
                imageManagerPath: '',
                compressSide:2,         //等比压缩的基准，确定maxImageSideLength参数的参照对象。0为按照最长边，1为按照宽度，2为按照高度
                maxImageSideLength:10000,
                toolbars: [
                ['source', '|', 'undo', 'redo', '|',
                    'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                    'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                    'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                    'directionalityltr', 'directionalityrtl', 'indent', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                    'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                    'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'highlightcode', 'webapp', 'pagebreak', 'template', 'background', '|',
                    'horizontal', 'date', 'time', 'spechars',
                    'wordimage', '|',
                    'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
                    'print', 'preview', 'searchreplace', 'help']
            ]
            });
        }

        function getContent() {
            return editor.getContent();
        }

    </script>
</head>
<body onload="show()">
<script type="text/plain" id="editor">
    ${context}
</script>
</body>
</html>