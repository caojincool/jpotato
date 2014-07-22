<%--
  Created by IntelliJ IDEA.
  User: dp
  Date: 13-6-19
  Time: 下午3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>组件包创建</title>
    <jsp:include page="../../common/_header.jsp"/>
    <script src="${rootPath}jquery/validation/vanadium.js"></script>
    <script src="${rootPath}jquery/validation/form.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${rootPath}jquery/validation/form.css" />
    <style type="text/css">
        .dateshow input[type="text"] {
            margin: 0;
            padding: 0
        }
            /*导航样式*/
        .dh{
            background: url("/resource/images/separator2.png") no-repeat right center;
            margin-right: 10px;display: inline-block;
            color: #069;
            padding: 5px 20px 5px 0;
        }
    </style>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div class="detailscontent">

        <div class="breadcrumbs">
            <a href="javascript:void(0);" style="padding-bottom: 0;">创建组件包基本信息</a>
            <span class="dh">创建文件目录结构及挂载组件</span>
            <span class="dh">完成</span>
        </div>
        <div class="marginBreadcrumbs">

            <h1 class="pageTitle" >创建组件包基本信息</h1>

            <form method="post" action="package/add"
                  id="formupdate">

                <div class="form_default" >
                    <fieldset>
                        <legend style="font-size: 16px;">创建组件包基本信息</legend>

                        <p style="color: red;">
                            <label style="color: #000000;" for="name">组件包名称:</label>
                            <input type="text" class="sf -required" id="name" name="name" value="">
                        </p>



                        <p>
                            <label for="startFace">开始页面:</label>
                            <input type="text" class="sf" id="startFace" name="startFace" value="">
                        </p>

                        <p>
                            <label for="importScript">导入脚本 :</label>
                            <textarea id="importScript" name="importScript" rows="2" cols="35"
                                    ></textarea>
                        </p>

                        <p>
                            <label for="exportScript">导出脚本 :</label>
                            <textarea id="exportScript" name="exportScript" rows="2" cols="35"
                                    ></textarea>
                        </p>

                        <p>
                            <label for="remark">描述 :</label>
                            <textarea id="remark" name="remark" rows="2" cols="35"
                                    ></textarea>
                        </p>




                    </fieldset>
                    <div class="bottombtn">
                        <button  type="button" onclick="javascript:window.location.href=''+rootPath+'package/main/view';">返回</button>
                        <button  type="reset">重置</button>
                        <button  type="submit">下一步</button>
                    </div>
                </div>

            </form>
        </div>


    <br clear="all"/>
</div>

<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>