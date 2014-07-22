<%--
  Created by IntelliJ IDEA.
  User: Xudong
  Date: 13-1-12
  Time: 上午9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>5 代数据表创建</title>
    <jsp:include page="../../common/_header.jsp"/>
    <script type="text/javascript" src="${rootPath}resource/ClientBin/Silverlight.js"></script>
    <script type="text/javascript">
        var dbList = ${dbs};
        var lr =${lmsResource};
        var edit = { edit: false };
        var host = null;
        var desginUrl = "../../../interface/${plateform}/formula/desginer?formula=";

        var flag=1;//判断保存成功跳转 1代表next 其他代表完成
        /**
         * 表格编辑器加载后添加事件
         */
        function onSLLoad(sender, args) {
            $("#submitBtn").show();
            host = sender.getHost();
            var editor = host.Content.editor;

            editor.OpenFormulaDesigner = function (s, args) {

                var formula = args.Formula;
                var cate = args.Cate;

                //alert(formula);

                ShowDesgin(formula);
                //editor.SetFormulaHelper(cate, "测试目标");

            };

            //表格编辑保存后发生的事件, result 为保存结�
            //result.Save 保存后的事件, result.BeforeSave 保存前的事件

            editor.Save = function (s, result) {



                //result 为保存结� result.IsSuccess 保存是否成功. result.Message 保存失败后返回的信息
                var json = result.Context;
                var obj = eval('(' + json + ')');
                if(obj.success) {
                    if(flag==1){
                        location.href="/component/main/"+lr.pid+"/imageAndDetails/add";
                    }else{
                        location.href='/component/main/'+lr.pid+'/create/finish';
                    }

                }else {
                    var arr=new Array();
                    arr.push('<div class="notification msgerror">')
                    arr.push('<p>保存失败!</p>')
                    arr.push('</div>')
                    jQuery("#messageErr").html( arr.join(""));
                    jQuery("#next").removeAttr("disabled");
                    jQuery("#finish").removeAttr("disabled");
                }

            }

        }
        function save() {
            var editor = host.Content.editor;
            jQuery("#messageErr").html("");
            jQuery("#next").attr("disabled", "disabled");
            jQuery("#finish").attr("disabled", "disabled");



            editor.ExecuteSave();
        }
        function doSave() {

            save();
        }
        function doFinish() {
            flag=2;
            save();

        }
    </script>
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

            var errMsg = "Unhandled Error in Silverlight Application " + appSource + "\n";

            errMsg += "Code: " + iErrorCode + "    \n";
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
                    errMsg += "Position: " + args.charPosition + "     \n";
                }
                errMsg += "MethodName: " + args.methodName + "     \n";
            }

            throw new Error(errMsg);
        }
    </script>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>

<div>
    <div class="detailscontent">
        <div id="messageErr" class="messageErrs">

        </div>
        <div class="breadcrumbs">
            <a href="javascript:void(0);">填写基础信息</a>
            <a href="javascript:void(0);">编辑组件内容</a>
            <a href="javascript:void(0);"><span>编辑预览图和详情</span></a>
            <a href="javascript:void(0);"><span>组件权限分配</span></a>
            <a href="javascript:void(0);"><span>加入导航</span></a>
            <span>完成</span>
        </div>


        <h1 class="pageTitle">编辑5代数据表内容</h1>


        <div id="tabelgp">

        </div>
        <div id="silverlightControlHost" style="margin: 10px auto; width: 1000px; height: 800px; ">
            <object data="data:application/x-silverlight-2," type="application/x-silverlight-2" width="100%"
                    height="100%">
                <param name="source" value="${rootPath}resource/ClientBin/SL.Lemsun.Control.TableCreater.xap"/>
                <param name="onError" value="onSilverlightError"/>
                <param name="background" value="white"/>
                <param name="minRuntimeVersion" value="5.0.61118.0"/>
                <param name="autoUpgrade" value="true"/>
                <param name="enableHtmlAccess" value="true"/>
                <param name="onLoad" value="onSLLoad" />
                <param name="initParams" value="submit=${rootPath}component/tabelgp5/create/table"/>
                <a href="http://go.microsoft.com/fwlink/?LinkID=149156&v=5.0.61118.0" style="text-decoration:none">
                    <img src="http://go.microsoft.com/fwlink/?LinkId=161376" alt="Get Microsoft Silverlight"
                         style="border-style:none"/>
                </a>
            </object>
            <iframe id="_sl_historyFrame" style="visibility:hidden;height:0px;width:0px;border:0px"></iframe>
        </div>

        <div class="bottombtn">
            <button class="button button_red" type="button" onclick="del();" >删除</button>
            <button id="next" class="button button_blue" type="button" onclick="doSave();" >下一步</button>
            <button id="finish" class="button button_black"   type="button" onclick="doFinish();" >完成</button>
        </div>
    </div>

</div>
<br clear="all"/>
</div>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
<jsp:include page="../common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>

<div id="desgin-dialog" title="公式设计器">
    <div style="margin: 5px">
        <iframe id="formulaDesgin" width="100%" height="100%" src="interface/${plateform}/formula/desginer"></iframe>
    </div>
</div>

<script type="text/javascript">
    $('#desgin-dialog').dialog({
        autoOpen:false,
        modal: true,
        height: 400,
        width: 500,
        buttons:{
            "确定":function() {
                $(this).dialog('close');
            },
            "取消":function() {
                $(this).dialog('close');
            }
        }
    });

    function ShowDesgin(formula) {
        var url = desginUrl + encodeURIComponent(formula);
        //$('#formulaDesgin').attr("src", url);
        //$('#desgin-dialog').dialog('open');
        var params = {};

        if(window.ActiveXObject){
            showModalDialog(url, params, "resizable=yes;help=no;status=no;");
        }
        else {
            window.open(url, params, "resizable=yes;help=no;status=no;");
        }


    }

</script>

</body>
</html>