<%--
  权限信息修改
  User: dpyang
  Date: 13-1-24
  Time: 上午9:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改组件</title>

    <jsp:include page="../_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 500px;
            margin: 10px auto;
        }
    </style>
    <script type="text/javascript">
        function checkSubmit() {
            //验证
            var cpname = document.getElementById("name").value.trim();
            if (cpname == '') {
                alert("请输入类型名称！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="headerspace"></div>
<jsp:include page="../common/top.jsp"/>
<div>
    <div id="context" style="height: 600px;">
        <div class="breadcrumbs">
            <a href="dashboard.html">修改权限信息</a>
            <span>完成</span>
        </div>
        <div style="width: 870px; margin: 15px;">
            <h1 class="pageTitle">修改权限信息</h1>
            <form method="post" action="permission/modification/finish" onsubmit="return checkSubmit();" enctype="multipart/form-data">
                <div class="form_default">
                    <fieldset>
                        <legend>权限信息</legend>
                        <p>
                            <label for="key">权限节点</label>
                            <input type="hidden" class="sf" name="id" value="${model.id}">
                            <input readonly="true" id="key" type="text" class="sf" name="key" value="${model.key}">
                        </p>

                        <p>
                            <label for="name">权限名称</label>
                            <input id="name" type="text" class="sf" name="name" value="${model.name}">
                            <%--<textarea id="name" rows="" cols="" class="mf" name="remark">${category.remark}</textarea>--%>
                        </p>
                        <p>
                            <label for="remark">权限说明</label>
                            <textarea id="remark" rows="" cols="" class="mf" name="remark">${model.remark}</textarea>
                        </p>
                        <p>
                            <button type="button" id="back">返回</button>
                            <script type="text/javascript">
                                Ext.fly("back").on('click',function(){
                                   location.href=rootPath+'index?current=permission/view';
                                })
                            </script>
                            <button type="submit">提交</button>
                        </p>
                    </fieldset>
                </div>
            </form>
        </div>
    </div>
    <br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../common/_footer.jsp"/>
</div>
</body>
</html>