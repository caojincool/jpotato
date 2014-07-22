<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-19
  Time: 上午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
    var rootPath = '<%= basePath %>';
</script>
<base href="<%=basePath%>">
<link rel="stylesheet" media="screen" href="resource/css/style.css"/>

<!--[if IE 9]>
<link rel="stylesheet" media="screen" href="resource/css/ie9.css"/>
<![endif]-->

<!--[if IE 8]>
<link rel="stylesheet" media="screen" href="resource/css/ie8.css" />
<![endif]-->

<!--[if IE 7]>
<link rel="stylesheet" media="screen" href="resource/css/ie7.css"/>
<![endif]-->

<SCRIPT type=text/javascript src="resource/js/jquery-1.7.min.js"></SCRIPT>

<SCRIPT type=text/javascript src="resource/js/jquery-ui-1.8.16.custom.min.js"></SCRIPT>

<SCRIPT type=text/javascript src="resource/js/jquery.validate.min.js"></SCRIPT>

<SCRIPT type=text/javascript src="resource/js/jquery.colorbox-min.js"></SCRIPT>

<SCRIPT type=text/javascript src="resource/js/jquery.flot.min.js"></SCRIPT>

<SCRIPT type=text/javascript src="resource/js/general.js"></SCRIPT>

<link rel="stylesheet" type="text/css" media="screen" href="ext/resources/css/ext-all.css" />

<script src="ext/ext-all-debug.js" type="text/javascript"></script>
<script src="ext/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
