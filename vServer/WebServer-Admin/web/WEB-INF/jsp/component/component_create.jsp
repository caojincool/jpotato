<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  组件创建基础信息界面
  User: Xudong
  Date: 13-1-10
  Time: 上午11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>创建组件</title>
    <jsp:include page="../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation/form.css" />
    <style type="text/css">
        form{
            margin: 0px;
            background-color: transparent;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../common/noTop.jsp"/>
<div class="detailscontent" >
    <div class="breadcrumbs">
        <a href="javascript:void(0);">填写基础信息</a>
        <a href="javascript:void(0);"> <span>编辑组件内容</span></a>
        <a href="javascript:void(0);"> <span>编辑预览图和详情</span></a>
        <a href="javascript:void(0);">  <span>组件权限分配</span></a>
        <a href="javascript:void(0);"><span>加入导航</span></a>
        <span>完成</span>
    </div>
        <div style="margin: 15px 0px;">
            <h1 class="pageTitle">填写基础信息</h1>
            <form id="frmComponent" method="post" action="">
                <div class="form_default">
                    <fieldset>
                        <legend style="font-size: 16px;">填写基础信息</legend>
                        <p>
                            <label for="category">组件类型选择</label>
                            <select id="category" name="category" class="ssf"   >
                                <c:forEach var="cate" items="${categorys}" >
                                    <option value="${cate.category}"  ${cate.category==type?'selected=\"selected\"':''}>${cate.name}</option>
                                </c:forEach>
                            </select>
                        </p>
                        <p>
                            <label for="name">组件名称</label>
                            <input type="text" id="name" class="sf -required" name="name">
                        </p>
                        <p>
                            <label for="name">业务编码</label>
                            <input type="text" id="businessCode" class="sf -required" name="businessCode">
                        </p>
                        <p>
                            <label for="name">是否系统组件</label>
                            <input type="checkbox" id="isSystem"  name="system">
                        </p>

                        <p>
                            <label for="param">参数设置</label>
                            <textarea rows="" id="param" cols="" class="mf" name="strParams"></textarea>
                        </p>
                        <p>
                            <label for="remark">说明文字</label>
                            <textarea rows="" id="remark" cols="" class="mf" name="remark"></textarea>
                        </p>

                    </fieldset>
                    <div class="bottombtn">
                        <p>
                            <button type="button" onclick="closeWin();">取消</button>
                            <button type="submit">创 建</button>
                        </p>
                    </div>
                </div>
            </form>
        </div>

</div>
<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>