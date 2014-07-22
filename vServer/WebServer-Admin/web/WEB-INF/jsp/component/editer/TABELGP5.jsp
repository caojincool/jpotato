<%--
  User: Xudong
  Date: 13-1-12
  Time: 上午9:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>修改5代表</title>
    <jsp:include page="../../_header.jsp"/>
    <%--这里是系统内置组件的行样式--%>
    <style type="text/css">
        .child-row .x-grid-cell {
            background-color: #ffe2e2;
            color: #900;
        }
    </style>

    <script type="text/javascript" src="${rootPath}resource/ClientBin/Silverlight.js"></script>
    <script type="text/javascript">
        function onSilverlightError(sender, args) {
            var appSource = "";
            if (sender != null && sender != 0) {
                appSource = sender.getHost().Source;
            }

            var errorType = args.ErrorType;
            var iErrorCode = args.ErrorCode;

            if (errorType == "ImageError" || errorType == "MediaError") {
                return;
            }

            var errMsg = "Unhandled Error in Silverlight Application " +  appSource + "\n" ;

            errMsg += "Code: "+ iErrorCode + "    \n";
            errMsg += "Category: " + errorType + "       \n";
            errMsg += "Message: " + args.ErrorMessage + "     \n";

            if (errorType == "ParserError") {
                errMsg += "File: " + args.xamlFile + "     \n";
                errMsg += "Line: " + args.lineNumber + "     \n";
                errMsg += "Position: " + args.charPosition + "     \n";
            }
            else if (errorType == "RuntimeError") {
                if (args.lineNumber != 0) {
                    errMsg += "Line: " + args.lineNumber + "     \n";
                    errMsg += "Position: " +  args.charPosition + "     \n";
                }
                errMsg += "MethodName: " + args.methodName + "     \n";
            }

            throw new Error(errMsg);
        }
    </script>


    <script type="text/javascript">
        var dbList = ${dbs};
        var lr =${lmsResource};
        var edit = { edit: true };
        var host = null;

        if (lr == null) {
            // Ext.Msg.alert('友情提示', '组件有问题!', function () {
            location.href = '${rootPath}index?current=component/main/view'
            //})
        }

        /**
         * 表格编辑器加载后添加事件
         */
        function onSLLoad(sender, args) {
            host = sender.getHost();
            var editor = host.Content.editor;

            //加载已有的组件信息
            editor.LoadResource(table.text, dbname.text, face.text);

        }

    </script>



</head>
<body class="detailsbody">

<script type="text/plain" id="table">${table}</script>
<script type="text/plain" id="face">${face}</script>
<script type="text/plain" id="dbname">${dbname}</script>

<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div class="detailscontent">
    <div>
        <div class="breadcrumbs">
            <a href="#">编辑5代表</a>
        </div>
        <div style="width: 950px; margin: 15px;">
            <h1 class="pageTitle">5 代数据表创建</h1>

            <div id="silverlightControlHost" style="margin: 10px auto; width: 1000px; height: 800px; ">
                <object data="data:application/x-silverlight-2," type="application/x-silverlight-2" width="100%" height="100%">
                    <param name="source" value="${rootPath}resource/ClientBin/SL.Lemsun.Control.TableCreater.xap"/>
                    <param name="onError" value="onSilverlightError" />
                    <param name="background" value="white" />
                    <param name="minRuntimeVersion" value="5.0.61118.0" />
                    <param name="autoUpgrade" value="true" />
                    <param name="enableHtmlAccess" value="true"/>
                    <param name="onLoad" value="onSLLoad" />
                    <param name="initParams" value="submit=${rootPath}component/tabelgp5/create/table"/>
                    <a href="http://go.microsoft.com/fwlink/?LinkID=149156&v=5.0.61118.0" style="text-decoration:none">
                        <img src="http://go.microsoft.com/fwlink/?LinkId=161376" alt="Get Microsoft Silverlight" style="border-style:none"/>
                    </a>
                </object><iframe id="_sl_historyFrame" style="visibility:hidden;height:0px;width:0px;border:0px"></iframe></div>
            </div>

        <div class="bottombtn">
               <script type="text/javascript" >
                   function UpdateTableSubmit() {
                       var editor = host.Content.editor;
                       var data = editor.GetJsonData();
                       jQuery.ajax({ url: "component/dbtabel5/"+lr.pid+"/edit",
                           dataType: "json",
                           type:"POST",
                           data:{tablejson:data},
                           success: function(res){
                               location.href = "/component/main/operatingResults?success=true";
                           },
                           error:function(){
                               var arr=new Array();
                               arr.push('<div class="notification msgerror">')
                               arr.push('<p>更新失败！ </p>')
                               arr.push('</div>')
                               jQuery("#messageErr").html( arr.join(""));
                           }

                       });

                   }
               </script>
               <button id="finish" class="button button_black"   type="button" onclick="UpdateTableSubmit();" >保存</button>

        </div>

    </div>
    <br clear="all"/>
</div>
<br/>
<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
</body>
</html>