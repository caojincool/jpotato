<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-9-10
  Time: 上午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

    <jsp:include page="../../common/_header.jsp"/>
    <script type="text/javascript">
        window.UEDITOR_HOME_URL = "${rootPath}resource/ue/";
        //window.$=window.jQuery;
    </script>
    <script type="text/javascript" src="${rootPath}resource/ue/ueditor.config.js"></script>
    <script type="text/javascript" src="${rootPath}resource/ue/ueditor.all.js"></script>
    <script src="jquery/ui/jquery.ui.tabs.js" type="text/javascript"></script>
    <script>
        $(function() {
            $("#tabs").tabs();
        });
    </script>
</head>
<body class="detailsbody">

<div class="headerspace"></div>
<jsp:include page="../../common/noTop.jsp"/>


<div class="detailscontent">
    <div class="breadcrumbs">
        <a href="javascript:void(0);">填写基础信息</a>
        <a href="javascript:void(0);">编辑组件内容</a>
        <a href="javascript:void(0);">编辑预览图和详情</a>
        <a href="javascript:void(0);">  <span>组件权限分配</span></a>
        <a href="javascript:void(0);"><span>加入导航</span></a>
        <span>完成</span>
    </div>

        <div class="marginBreadcrumbs">
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">编辑预览图片</a></li>
                    <li><a href="#tabs-2">编辑详情</a></li>
                </ul>
                <div id="tabs-1">
                    <div class="row">
                        <label><span class="label">当前预览图：</span></label>
                        <img id="avatar" src="/help/document/${pid}/preView?size=0" />
                        <input type="hidden" name="pid" id="pid" value="${pid}">
                        <input type="hidden" name="last_urls" id="last_url" value="${last_urls}">
                    </div>
                    <div class="row">
                        <label><span class="label">上传图片：</span></label>
                        <div style="height: 20px; width: 120px;" class="uploadify" id="avatarUpload">
                            <div id="avatarUpload-button" class="uploadify-button " style="height: 20px; line-height: 20px; width: 120px;">
                                <span class="uploadify-button-text">请选择图片</span>
                            </div>
                        </div>
                        <input type="hidden" id="img" name="srcPath" />
                        <input type="hidden" id="x" name="x" value="0"/>
                        <input type="hidden" id="y" name="y" value="0" />
                        <input type="hidden" id="w" name="width" value="0" />
                        <input type="hidden" id="h" name="height" value="0"/>
                        <div style="padding-top:-10px;">
                            <a href="javascript:$('#avatarUpload').uploadify('upload','*')">开始上传</a> |
                            <a href="javascript:$('#avatarUpload').uploadify('cancel', '*')">取消上传</a>
                        </div>
                    </div>
                    <div class="row imgchoose" style="display:none;">
                        <label><span class="label">切割图片：</span></label>
                        <img src="" id="target">
                    </div>
                    <div class="row imgchoose" style="display:none;">

                        <label><span class="label">预览图：</span></label>
                        <div style="width:150px;height:150px;margin:0px 0 10px;overflow:hidden; float:left;"><img class="preview" id="preview2" src="" /></div>
                        <div style="width:80px;height:80px;margin:70px 0 10px 10px;overflow:hidden; float:left;"><img class="preview" id="preview" src="" /></div>
                    </div>
                </div>
                <div id="tabs-2">
                     <div class="row">
                        <label><span class="label">组件详情：</span></label>
                        <script type="text/plain" id="details" name="details">
                            ${details}
                        </script>
                        <script type="text/javascript">
                            this.editor = UE.getEditor('details', {
                                autoHeightEnabled: false,
                                autoWidthEnabled: true,
                                autoFloatEnabled: false,
                                initialFrameWidth: "100%",
                                initialFrameHeight: 360,
                                imageUrl: rootPath + "help/document/${pid}/attach/files/upload",
                                fileUrl: rootPath + "help/document/${pid}/attach/files/upload",     //附件上传提交地址
                                imageManagerUrl:rootPath + "help/document/${pid}/image/manager",
                                imageManagerPath: '/',
                                filePath:'',
                                imagePath:''
                            });
                        </script>
                    </div>
                </div>

            </div>
            <div class="bottombtn">
                <button class="button button_red" type="button" onclick="del();" >删除</button>
                <button id="next" class="button button_blue" type="button"  >下一步</button>
                <button id="finish" class="button button_black"   type="button"  >完成</button>
            </div>
        </div>

</div>
<br/>

<div class="footer">
    <jsp:include page="../../common/_footer.jsp"/>
</div>
<jsp:include page="../common/deleteDialog.jsp" >
    <jsp:param name="pid" value="${pid}"></jsp:param>
    <jsp:param name="category" value="${category}"></jsp:param>
</jsp:include>
<link href="/jquery/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/jquery/jquery-migrate-1.2.1.js"></script>

<script type="text/javascript" src="/jquery/uploadify/jquery.uploadify-3.1.js"></script>
<script type="text/javascript" src="/jquery/jcrop/jquery.Jcrop.js"></script>
<link rel="stylesheet" href="/jquery/jcrop/jquery.Jcrop.css" type="text/css" />
<script type="text/javascript">
    var last_urls='${last_urls}';
    var pid='${pid}';
    var sessionid='<%=request.getSession().getId()%>';
    var flag=1;
    function jumpPage(preViemImageFlag,detailsFlag) {
        if (preViemImageFlag && detailsFlag) {
               if(flag==1){
                location.href = "/component/main/" + pid + "/permission";
                }else{
                 location.href = "component/main/" + pid + "/create/finish";
               }
        }
    }
</script>
<script type="text/javascript" src="/resource/js/component/editer/preViewImage.js"></script>
</body>
</html>