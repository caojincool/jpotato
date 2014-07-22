<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <a title="预览" class="iconlink2"
       href="component/main/defaultPreView/${param.pid}" target="_blank"><img
            src="resource/images/icons/eye_16x16.png"></a>
    <a target="_blank" title="修改内容" class="iconlink2" href="component/${fn:toLowerCase(param.category)}/${param.pid}/edit"><img
            src="resource/images/icons/small/black/edit.png"></a>
    <a title="编辑预览图及详情" class="iconlink2"
       href="help/document/${param.pid}/imageAndDetails/update" target="_blank"><img
            src="resource/images/icons/small/black/settings.png"></a>
    <a target="_blank"  title="设置权限" class="iconlink2"
       href="component/main/${param.pid}/permission/edit"><img
            src="resource/images/icons/small/black/settings.png"></a>
    <a target="_blank"  title="删除" class="iconlink2" href="component/${fn:toLowerCase(param.category)}/${param.pid}/delete"><img
            src="resource/images/icons/small/black/minus.png"></a>
