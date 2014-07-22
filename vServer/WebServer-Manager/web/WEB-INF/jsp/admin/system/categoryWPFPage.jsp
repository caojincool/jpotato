<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-11-30
  Time: 上午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>系统管理-系统类型管理-WPF系统</title>
    <jsp:include page="../_header.jsp"/>

    <SCRIPT type=text/javascript src="resource/js/jquery-1.7.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery-ui-1.8.16.custom.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.validate.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.colorbox-min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.flot.min.js"></SCRIPT>

    <link rel="stylesheet" media="screen" href="resource/css/froms_dl.css"/>

    <style type="text/css">
            /*.wordOn{background:url(password_input_bg.jpg) center center no-repeat;}*/
        .wordOn{
            border-color: red;
            /*background:red center center no-repeat;*/
        }
        .ss{
            border-color: #0078b6;
        }
        .redonly{
            background: #e6e6e6;
        }
    </style>


    <script type="text/javascript">
        $(document).ready(function(){

            $('.SubmitButton')
                    .mouseover(function(){
                        $(this).addClass('ss');
                    })
                    .mouseout(function(){
                        $(this).removeClass('ss');
                    })
            $('.cssinput')
                    .focus(function(){
                        $(this).addClass('ss');
                    })
                    .blur(function(){
                        $(this).removeClass('ss');
                    })
            $('.inputNessCss')
                    .focus(function(){
                        $(this).removeClass('wordOn');
                        $(this).addClass('ss');
                    })
                    .blur(function(){
                        $(this).removeClass('ss');
                        if($.trim($(this).val())=='')
                        {
                            $(this).addClass('wordOn');
                        }
                    })
        });

        function CheckSubmit(){
            //验证起始页挂载资源是否为空
            var scstartResource= $.trim($('#cstartResource').val());
            if(scstartResource=='')
            {
                alert("起始页挂载资源为必填项！");
                return false;
            }
            //验证开始脚本是否为空
            var scstartscript= $.trim($('#cstartscript').val());
            if(scstartscript=='')
            {
                alert("请输入开始脚本！");
                return false;
            }
            //验证开始脚本是否为空
            var scendscript= $.trim($('#cendscript').val());
            if(scendscript=='')
            {
                alert("请输入结束脚本！");
                return false;
            }
            //验证附加集合
            var s = $.trim($('#cparam').val());
            //替换&
            var sj = s.replace(/&/g,',');
            //替换\n
            var sk = sj.replace(/\n/g,',');

            var s2 = sk.split(',');

            var sfirst='';
            for(var i=0;i< s2.length;i++)
            {
                sfirst += s2[i].split('=')[0]+",";
            }
            var sfirst1 = sfirst.substring(0,sfirst.length-1).split(',');
            if(sfirst1.length>0){
                var j=0;
                for(j=0;j<sfirst1.length;j++)
                {
                    for(var k=j+1;k<sfirst1.length;k++)
                    {
                        if($.trim(sfirst1[j]) == $.trim(sfirst1[k]))
                        {
                            alert("集合元素名称有重复！");
                            return false;
                        }
                    }
                }
            }
        }
    </script>

</head>

<body class="bodygrey">

<div class="defaultbody">

    <div class="header">
        <%--<jsp:include page="../_adminTitle.jsp"/>--%>

        <form method="post" action="" id="search">
            <input type="text" name="keyword"> <button class="searchbutton"></button>
        </form>
        <div style="position: absolute;left:-5;top: 5px">
            <object id="flash_id" onmousedown="mousedown()" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="92" height="90" align="center">
                <param name="movie" value="resource/swf/logo.swf">
                <param name="quality" value="high">
                <param name="wmode" value="transparent">
                <embed src="resource/swf/logo.swf" width="92" height="90" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>
            </object>
        </div>
        <div style="padding-left: 65;padding-top:-5;margin-top:-5;font-size: 20;color: #ffffff;font-family: '方正舒体';">
            <label>林木森</label>
            <br>
            <hr width="140" style="margin-left: 0;color: #ffffff;position: absolute;margin-top: 6"/>
            <br>
            <label style="position: absolute;margin-top: -8;margin-left: -4">全面信息化战略</label>
        </div>


        <div class="tabmenu">
            <ul>
                <li><a class="elements" href="system/category/web"><span>网页系统</span></a></li>
                <li class="current"><a class="dashboard" href="system/category/wpf"><span>WPF 系统</span></a></li>
            </ul>
        </div><!-- tabmenu -->

        <div class="accountinfo">
            <img alt="Avatar" src="resource/swf/logoPic.png" width="50" height="50">
            <div class="info">
                <h3>John Doe</h3>
                <small>youremail@domain.com</small>
                <p>
                    <a href="">Account Settings</a> <a href="index.html">Logout</a>
                </p>
            </div><!-- info -->
        </div><!-- accountinfo -->
    </div>

    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>

    <div class="maincontent">

        <form method="post" action="system/category/wpf/update" onsubmit="return CheckSubmit();" id="formupdate">
            <fieldset style="padding: 10px;">
                <p>运行平台: ${listone.getName()}</p>
                <%--<dl style="margin: 10px;">--%>
                    <div>
                        <table style="width: 100% ;height:80px">
                            <tr>
                                <%--<td width="70">ID :</td>--%>
                                <%--<td width="210">--%>
                                <%--<input type="text" name="cid"--%>
                                <%--value="${listone.getId()}" readonly="true" class="redonly" /></td>--%>

                                <td>Pid :</td>
                                <td>
                                    <input type="text" name="cpid"
                                           value="${listone.getPid()}" readonly="true" class="redonly" /></td>

                                <td>系统名称 :</td>
                                <td>
                                    <input type="text" name="cname"
                                           value="${listone.getName()}" readonly="true" class="redonly" /></td>

                                <td>修改日期 :</td>
                                <td>
                                    <input type="text" name="cupdateTime"
                                           value="${listone.getUpdateTime()}" readonly="true" class="redonly" /></td>
                            </tr>
                            <tr>
                                <td width="70">创建人 :</td>
                                <td width="210">
                                    <input type="text" name="ccreateUser"
                                           value="${listone.getCreateUser()}" readonly="true" class="redonly" /></td>

                                <td >Key :</td>
                                <td><input name="ckey" rows="3" cols="138" readonly="true" class="redonly"
                                           class="cssinput" value="${listone.getKey()}"/></td>
                            </tr>
                        </table>

                        <p style="color:#000000;">开始资源 :</p>
                        <p style="color: red; float:left;">*</p>
                        <input type="text" id="cstartResource" name="cstartResource" style="width: 400px;margin-left: 10px;"
                               value="${listone.getStartResource()}" class="inputNessCss" />

                    </div>
                    <br/>
                    <div>
                        <p style="color:#000000;">开始脚本 :</p>
                        <p style="color: red; float:left;">*</p>
                        <textarea id="cstartscript" name="cstartscript" rows="10" cols="158" style="margin-left: 10px"
                                  class="inputNessCss" >${listone.getStartScript()}</textarea>
                    </div>
                    <br/>
                    <div>
                        <p style="color:#000000;">结束脚本 :</p>
                        <p style="color: red; float:left;">*</p>
                        <textarea id="cendscript" name="cendscript" rows="10" cols="158" style="margin-left: 10px"
                                  class="inputNessCss">${listone.getEndScript()}</textarea>
                    </div>

                    <div>

                    </div>
                    <br/>
                    <div>
                        <p style="color:#000000;">附加集合 : 形如 [Key=Value] {name=xiao,age=20,sex=女} ，Key不能重复. ）</p>
                        <textarea id="cparam" name="cparam" rows="8" cols="158" style="margin-left: 18px"
                                  class="cssinput">${listone.getParamstring()}</textarea>
                    </div>
                <%--</dl>--%>

                <input type="submit" value="修改" id="ReturnButton" class="SubmitButton" style="margin-left: 20px;"/>

            </fieldset>
        </form>
    </div>

    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>

</html>