<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-24
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE" />
<link rel="stylesheet" href="${rootPath}resource/codemirror/lib/codemirror.css">
<link href="${rootPath}resource/codemirror/addon/hint/show-hint.css" rel="stylesheet">
<script src="${rootPath}resource/codemirror/lib/codemirror.js"></script>
<script src="${rootPath}resource/codemirror/addon/edit/matchbrackets.js"></script>
<script src="${rootPath}resource/codemirror/addon/edit/continuecomment.js"></script>

<script src="${rootPath}resource/codemirror/addon/hint/xml-hint.js"></script>

<script src="${rootPath}resource/codemirror/addon/edit/closetag.js"></script>
<script src="${rootPath}resource/codemirror/mode/xml/xml.js"></script>
<script src="${rootPath}resource/codemirror/mode/javascript/javascript.js"></script>
<script src="${rootPath}resource/codemirror/mode/css/css.js"></script>
<script src="${rootPath}resource/codemirror/mode/htmlmixed/htmlmixed.js"></script>

<jsp:include page="../_header.jsp" />
<style type="text/css">
    .CodeMirror {
        border:1px solid #c4c4c4;
        height: 400px;
        background-color: white;
    }
    .CodeMirror-gutter{
        background-color: white;
    }
.form_default label{
    width:30px;
    margin-right: 10px
}</style>