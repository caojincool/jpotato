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
    <style type="text/css">
        input {
            width: 100px;
        }
    </style>
</head>
<body>
<h1> <span style="color: orange;">创建组件</span> -> 编辑内容(录入公式) -> 设置权限 -> 完成</h1>

    <form action="" method="POST">
        <ul>
            <li> <span>资源名称</span> : <input type="text" name="name" value="" /> </li>
            <li> <span>资源类型</span> : <input type="text" readonly="true" name="categoryName" value="${category.name}" /><input type="hidden" name="category" value="${category.category}"/></li>
            <li>
                <p>${category.remark}</p>
            </li>
            <li>
                <p>备注信息:</p>
                <input name="remark" type="text" style="width: 500px; height: 200px;" />
            </li>

            <li> <span>附加参数(名称)</span> : <input type="text" name="param" value="" /> = <span>附加参数(值)</span> : <input type="text" name="pvalue" value="" /></li>
            <li> <span>附加参数(名称)</span> : <input type="text" name="param" value="" /> = <span>附加参数(值)</span> : <input type="text" name="pvalue" value="" /></li>
            <li> <span>附加参数(名称)</span> : <input type="text" name="param" value="" /> = <span>附加参数(值)</span> : <input type="text" name="pvalue" value="" /></li>

            <li> <input type="submit" value="创建资源"> </li>
        </ul>
    </form>
</body>
</html>