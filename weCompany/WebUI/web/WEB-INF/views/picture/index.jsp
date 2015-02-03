<%@ page import="java.util.Random" %>
<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/18
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/uploadify/uploadify.css">
    <style type="text/css">
        <!--
        * {
            margin: 0;
            padding: 0;
            font-size: 12px;
            text-decoration: none;
        }

        .imageList {
            float: left;
            display: block;
            width: 180px;
            height: 200px;
            padding: 10px;
        }

        .selected {
            background-color: #ffa8a8;
        }

        .overitem {
            background-color: #E7E7E7;
        }

        .imageList p {
            text-align: center;
            overflow: hidden;
            line-height: 22px;
        }

        .ellipsis {
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
            -moz-binding: url('ellipsis.xml#ellipsis');
            white-space: nowrap;
            overflow: hidden;
        }

        .upload {
            padding: 10px auto;
        }

        -->
    </style>
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
    <script language="javascript" type="text/javascript" src="/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var url = location.href;
            var id = url.substring(url.indexOf("?") + 1, url.length).split('=');

            $('.imageList').hover(function () {
                $(this).addClass("overitem");
            }, function () {
                $(this).removeClass("overitem");
            }).click(function () {
                $(this).toggleClass("selected")
            });
            $('#btnRemove').click(function () {
                var imgs = $('.selected > img');
                if (imgs.length == 0) {
                    $.messager.alert('友情提示', '请选择要删除的文件！', 'error');
                    return;
                }
                var titles = '';
                var fs = [];
                $.each(imgs, function (i, d) {
                    titles += $(d).attr('title') + "<br>";
                    fs.push($(d).attr('title'));
                });
                $.messager.confirm('确认', '您确认删除' + titles + '?', function (r) {
                    if (r) {
                        $.ajax({
                            type: 'post',
                            url: '/fileMg/fileRM',
                            data: {id: decodeURIComponent(id[1]), fs: fs},
                            success: function (d) {
                                if (d.success) {
                                    location.reload(true);
                                } else {
                                    $.messager.alert('提示', d.message, 'info');
                                }
                            }
                        })
                    }
                });
            });

            //避免在谷歌浏览器中崩溃
            setTimeout(function () {
                $('#uploadify').uploadify({
                    buttonText: '上传',
                    swf: '/uploadify/uploadify.swf',
                    uploader: '/fileMg/upload',
                    fileTypeExts: '*.gif; *.jpg; *.png; *.doc;',
                    width: 50,
                    formData: {id: decodeURIComponent(id[1])},
                    height: 20,
                    queueSizeLimit: 20,
                    onUploadError: function (file, errorCode, errorMsg, errorString) {
                        $.messager.alert('友情提示', '出错了！', 'error');
                    },
                    onQueueComplete: function (queueData) {
                        //假加载
                        var value = $('#q').progressbar('getValue');
                        if (value < 100) {
                            value += Math.floor(Math.random() * 10);
                            $('#q').progressbar('setValue', value);
                            setTimeout(arguments.callee, 200);
                        }
                        location.reload(true);
                    }
                });
            }, 10);
        });
    </script>
</head>
<body class="easyui-layout" style="margin: 6px;">
<div region="center" title="所有文件">
    <c:if test="${files.size()>0}">
        <c:forEach items="${files}" var="f">
            <div class="imageList">
                <img width="160" height="160" src="/upload${f.value}" title="${f.key}">
                <br>

                <p class="ellipsis">${f.key}</p>
            </div>
        </c:forEach>
    </c:if>
</div>
<div region="south" style="height:40px">
    <table width="100%">
        <tr>
            <td width="100"><input type="file" name="uploadify" id="uploadify" multiple="true"/></td>
            <td align="center">
                <div id="q" class="easyui-progressbar" style="width: 500px;"></div>
            </td>
            <td width="100"><a href="javascript:void(0)" class="easyui-linkbutton" id="btnRemove">删除文件</a></td>
        </tr>
    </table>
</div>
</body>
</html>
