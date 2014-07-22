<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 13-3-9
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>创建账号</title>
    <jsp:include page="../../common/_header.jsp"/>
    <style type="text/css">
        #context {
            width: 900px;
            height: 600px;
            margin: 10px auto;
        }

        .divMargin {
            width: 100%;
            margin: 15px 0px 25px 0px;;
        }

        .cellStyel {
            height: 50px;
            width: 80px;
        }

        .tdimg {
            text-align: center;
        }
    </style>
    <%--begin自定义头像上传控件功能尚未实现--%>
    <%--<script type="text/javascript">--%>
        <%--function $(id) {--%>
            <%--return document.getElementById(id);--%>
        <%--}--%>
        <%--var userAgent = navigator.userAgent.toLowerCase();--%>
        <%--var is_opera = userAgent.indexOf('opera') != -1 && opera.version();--%>
        <%--var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);--%>
        <%--var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);--%>
        <%--var is_mac = userAgent.indexOf('mac') != -1;--%>
        <%--function AC_GetArgs(args, classid, mimeType) {--%>
            <%--var ret = new Object();--%>
            <%--ret.embedAttrs = new Object();--%>
            <%--ret.params = new Object();--%>
            <%--ret.objAttrs = new Object();--%>
            <%--for (var i = 0; i < args.length; i = i + 2) {--%>
                <%--var currArg = args[i].toLowerCase();--%>
                <%--switch (currArg) {--%>
                    <%--case "classid":--%>
                        <%--break;--%>
                    <%--case "pluginspage":--%>
                        <%--ret.embedAttrs[args[i]] = 'http://www.macromedia.com/go/getflashplayer';--%>
                        <%--break;--%>
                    <%--case "src":--%>
                        <%--ret.embedAttrs[args[i]] = args[i + 1];--%>
                        <%--ret.params["movie"] = args[i + 1];--%>
                        <%--break;--%>
                    <%--case "codebase":--%>
                        <%--ret.objAttrs[args[i]] = 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0';--%>
                        <%--break;--%>
                    <%--case "onafterupdate":--%>
                    <%--case "onbeforeupdate":--%>
                    <%--case "onblur":--%>
                    <%--case "oncellchange":--%>
                    <%--case "onclick":--%>
                    <%--case "ondblclick":--%>
                    <%--case "ondrag":--%>
                    <%--case "ondragend":--%>
                    <%--case "ondragenter":--%>
                    <%--case "ondragleave":--%>
                    <%--case "ondragover":--%>
                    <%--case "ondrop":--%>
                    <%--case "onfinish":--%>
                    <%--case "onfocus":--%>
                    <%--case "onhelp":--%>
                    <%--case "onmousedown":--%>
                    <%--case "onmouseup":--%>
                    <%--case "onmouseover":--%>
                    <%--case "onmousemove":--%>
                    <%--case "onmouseout":--%>
                    <%--case "onkeypress":--%>
                    <%--case "onkeydown":--%>
                    <%--case "onkeyup":--%>
                    <%--case "onload":--%>
                    <%--case "onlosecapture":--%>
                    <%--case "onpropertychange":--%>
                    <%--case "onreadystatechange":--%>
                    <%--case "onrowsdelete":--%>
                    <%--case "onrowenter":--%>
                    <%--case "onrowexit":--%>
                    <%--case "onrowsinserted":--%>
                    <%--case "onstart":--%>
                    <%--case "onscroll":--%>
                    <%--case "onbeforeeditfocus":--%>
                    <%--case "onactivate":--%>
                    <%--case "onbeforedeactivate":--%>
                    <%--case "ondeactivate":--%>
                    <%--case "type":--%>
                    <%--case "id":--%>
                        <%--ret.objAttrs[args[i]] = args[i + 1];--%>
                        <%--break;--%>
                    <%--case "width":--%>
                    <%--case "height":--%>
                    <%--case "align":--%>
                    <%--case "vspace":--%>
                    <%--case "hspace":--%>
                    <%--case "class":--%>
                    <%--case "title":--%>
                    <%--case "accesskey":--%>
                    <%--case "name":--%>
                    <%--case "tabindex":--%>
                        <%--ret.embedAttrs[args[i]] = ret.objAttrs[args[i]] = args[i + 1];--%>
                        <%--break;--%>
                    <%--default:--%>
                        <%--ret.embedAttrs[args[i]] = ret.params[args[i]] = args[i + 1];--%>
                <%--}--%>
            <%--}--%>
            <%--ret.objAttrs["classid"] = classid;--%>
            <%--if (mimeType) {--%>
                <%--ret.embedAttrs["type"] = mimeType;--%>
            <%--}--%>
            <%--return ret;--%>
        <%--}--%>
        <%--function AC_FL_RunContent() {--%>
            <%--var ret = AC_GetArgs(arguments, "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000", "application/x-shockwave-flash");--%>
            <%--var str = '';--%>
            <%--if (is_ie && !is_opera) {--%>
                <%--str += '<object ';--%>
                <%--for (var i in ret.objAttrs) {--%>
                    <%--str += i + '="' + ret.objAttrs[i] + '" ';--%>
                <%--}--%>
                <%--str += '>';--%>
                <%--for (var i in ret.params) {--%>
                    <%--str += '<param name="' + i + '" value="' + ret.params[i] + '" /> ';--%>
                <%--}--%>
                <%--str += '</object>';--%>
            <%--} else {--%>
                <%--str += '<embed ';--%>
                <%--for (var i in ret.embedAttrs) {--%>
                    <%--str += i + '="' + ret.embedAttrs[i] + '" ';--%>
                <%--}--%>
                <%--str += '></embed>';--%>
            <%--}--%>
            <%--return str;--%>
        <%--}--%>
    <%--</script>--%>
    <%--end自定义头像上传控件功能尚未实现--%>
</head>
<body class="detailsbody">
<div class="headerspace"></div>
<jsp:include page="../../common/top.jsp"/>
<div class="detailscontent">
        <div class="breadcrumbs">
            <a href="dashboard.html">扩展头像</a>
            <span>扩展联系方式</span>
            <span>加入角色</span>
            <span>扩展权限</span>
            <span>完成</span>
        </div>
        <div style="width: 870px; margin: 15px;">
            <h1 class="pageTitle">账号基本信息</h1>

            <form method="post" action="account/expand/doExpandAvatar" onsubmit="return submitFun()"
                  enctype="multipart/form-data">
                <div class="form_default">
                    <fieldset>
                        <legend>扩展头像</legend>
                        <div style="width: 100%; margin: 10px;">
                            <div class="divMargin">
                                <table width="100%" border="1">
                                    <tr>
                                        <td width="180px" align="center" valign="middle" rowspan="2">
                                            <img id="avatar"
                                                 onerror="this.onerror=null;this.src='${rootPath}resource/images/avatars/default.jpg';"/>
                                            <br><a href="javascript:;"
                                                   onclick="$('avatarctrl').style.display = ''">修改</a>
                                        </td>
                                        <td class="cellStyel">
                                            <p>登录账户&nbsp;&nbsp;<b>${accountName}</b></p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <%--begin自定义头像上传控件功能尚未实现--%>
                                            <%--<div id="avatarctrl" style="display: none">--%>
                                            <%--<script type="text/javascript">--%>
                                            <%--document.write(AC_FL_RunContent('width', '540', 'height', '253', 'scale', 'exactfit', 'src', '${avatarFlashParam}', 'id', 'mycamera', 'name', 'mycamera', 'quality', 'high', 'bgcolor', '#ffffff', 'wmode', 'transparent', 'menu', 'false', 'swLiveConnect', 'true', 'allowScriptAccess', 'always'));--%>
                                            <%--</script>--%>
                                            <%--</div>--%>
                                            <%--<script type="text/javascript">--%>
                                            <%--function updateavatar(sender, args) {--%>
                                            <%--$('avatar').src = '${localhost}/resource/images/avatars/${accountName}/medium.jpg?random=1' + Math.random();--%>
                                            <%--$('avatarctrl').style.display = 'none';--%>
                                            <%--}--%>
                                            <%--updateavatar();--%>
                                            <%--</script>--%>
                                            <%--end自定义头像上传控件功能尚未实现--%>

                                            <%--begin 临时上传控件--%>
                                            <script type="text/javascript">
                                                document.write(AC_FL_RunContent('width', '450', 'height', '253', 'scale', 'exactfit', 'src', 'http://bbs.ybvv.com/uc_server/images/camera.swf?inajax=1&appid=2&input=7ec004WFcOaJiRU10JzzalKfYx6%2B1i24kASU7%2Fec9%2F%2Bj044UIzmYRiI9lXgXSNpbSj%2FcLEIwDoQpFaYjx2RvsiYIJPwNXTgSdmXimTO4GisvTVyOFU1xqxrFdkFToQ&agent=d32aca07362bfc7fa958c25a5489354c&ucapi=bbs.ybvv.com%2Fuc_server&avatartype=virtual&uploadSize=2048', 'id', 'mycamera', 'name', 'mycamera', 'quality', 'high', 'bgcolor', '#ffffff', 'menu', 'false', 'swLiveConnect', 'true', 'allowScriptAccess', 'always'));</script>
                                            <embed width="450" height="253" scale="exactfit"
                                                   src="http://bbs.ybvv.com/uc_server/images/camera.swf?inajax=1&amp;appid=2&amp;input=7ec004WFcOaJiRU10JzzalKfYx6%2B1i24kASU7%2Fec9%2F%2Bj044UIzmYRiI9lXgXSNpbSj%2FcLEIwDoQpFaYjx2RvsiYIJPwNXTgSdmXimTO4GisvTVyOFU1xqxrFdkFToQ&amp;agent=d32aca07362bfc7fa958c25a5489354c&amp;ucapi=bbs.ybvv.com%2Fuc_server&amp;avatartype=virtual&amp;uploadSize=2048"
                                                   name="mycamera" quality="high" bgcolor="#ffffff" menu="false"
                                                   swliveconnect="true" allowscriptaccess="always"
                                                   type="application/x-shockwave-flash">
                                                <%--end临时上传控件功能--%>

                                            <p>请选择一个新照片进行上传编辑。<br>头像保存后，您可能需要刷新一下本页面(按F5键)，才能查看最新的头像效果 </p>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </fieldset>
                    <div class="bottombtn">
                        <button type="button" id="black">取消</button>
                        <script type="text/javascript">
                            Ext.fly('black').on('click', function () {
                                location.href = rootPath + 'account/view';
                            })
                        </script>
                        <button type="submit">下一步</button>
                    </div>
                </div>
            </form>
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