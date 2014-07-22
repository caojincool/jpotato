<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-3
  Time: 上午9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>instanceOperate</title>
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
    </style>


    <script type="text/javascript">
         $(document).ready(function(){

             $('.SubmitButton')
                     .mouseover(function(){
                         $(this).addClass('ss');
                     })
                     .mouseout(function(){
                         $(this).removeClass('ss');
                     });

             $('.inputcss')
                     .focus(function(){
                         $(this).addClass('ss');
                     })
                     .blur(function(){
                         $(this).removeClass('ss');
                     });
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
                     });

             //判断ip地址的合法性
             $('#ip').blur(function(){
                 var value=$("#ip").val();
                 var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
                 var reg = value.match(exp);
                 if(reg==null)
                 {
                     $(this).addClass('wordOn');
                     return false;
                 }
                 else{
                     $(this).removeClass('wordOn');
                 }
             });
         });

         function CheckSubmit(){
             //验证实例名称
             var sname= $.trim($('#name').val());
             if(sname=='')
             {
                 alert("实例名称为必填项！");
                 return false;
             }
             //验证IP地址
             var exp1=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
             var reg1 = $("#ip").val().match(exp1);
             if(reg1==null)
             {
                 $(this).addClass('wordOn');
                 alert("IP地址不合法！");
                 return false;
             }
             //验证通讯密钥
             var stxKey = $.trim($('#txKey').val());
             if(stxKey=='')
             {
                 alert("通讯密钥为必填项！");
                 return false;
             }
             //验证使用人
             var suser = $.trim($('#User').val());
             if(suser=='')
             {
                 alert("使用人为必填项！");
                 return false;
             }
             //验证机器码
             var scode= $.trim($('#jiqicode').val());
             if(scode=='')
             {
                 alert("机器码为必填项！");
                 return false;
             }
         }

         function Return(){
             window.location.href=""+rootPath+"system/instance/list";
         }
    </script>

</head>

<body class="bodygrey">

<div class="defaultbody">
    <div class="header">
        <jsp:include page="../_adminTitle.jsp"/>
    </div>
    <div class="sidebar bodyleft">
        <jsp:include page="../_adminLeft.jsp"/>
    </div>

    <div class="maincontent">

        <form method="post" action="system/instance/update/save" onsubmit="return CheckSubmit();" id="formupdate">
            <fieldset>
                <p>实例信息</p>
                <dl>
                    <div>
                        <table style="width: 100% ;height:80px">
                            <tr>
                                <td width="70">实例名称:</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="name" name="name" value="${listone.getName()}" tabindex="1" class="inputNessCss" /> *</td>

                                <td >系统类型:</td>
                                <td><input type="text" name="categorytype" readonly="true" value="${listone.getOwner().getName()}" tabindex="2" class="inputcss" /></td>

                                <td >说明:</td>
                                <td><input type="text" id="remark" name="remark" value="${listone.getRemark()}" tabindex="4" class="inputcss" /></td>
                            </tr>
                            <tr>
                                <td>地址:</td>
                                <td><input type="text" id="address" name="address" tabindex="5" class="inputcss" value="${listone.getAddress()}" /></td>
                                <td>启用日期:</td>
                                <td><input type="text" id="qyDate" name="qyDate" tabindex="6" class="inputcss" value="${listone.getQyDate()}" /></td>
                                <td>结束日期:</td>
                                <td><input type="text" id="jsDate" name="jsDate" tabindex="7" class="inputcss" value="${listone.getJsDate()}" /></td>
                            </tr>
                        </table>

                    </div>
                </dl>
                <br/>
                <p>安全信息</p>
                <dl>
                    <div>
                        <table style="width: 100% ;height:30px;margin-top: 10px;">
                            <tr>
                                <td width="71">IP地址:</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="ip" name="ip" value="${listone.getIp()}" tabindex="8" class="inputNessCss"/> *</td>

                                <td>通讯密钥:</td>
                                <td style="color: red;"><input type="text" id="txKey" name="txKey" value="${listone.getTxKey()}" tabindex="9" class="inputNessCss"/> *</td>

                                <td style="visibility: hidden;">通讯密钥:</td>
                                <td style="visibility: hidden;"><input name="pid" value="${listone.getPid()}" type="text"/></td>
                            </tr>
                        </table>

                    </div>
                </dl>

                <br/>
                <p>相关注册信息</p>
                <dl>
                    <div>
                        <table style="width: 100% ;height:30px">
                            <tr>
                                <td width="71">使用人 :</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="User" name="User" value="${listone.getUser()}" tabindex="10" class="inputNessCss" /> *</td>


                                <td width="70">机器码 :</td>
                                <td style="color: red;">
                                    <input type="text" id="jiqicode" name="jiqicode" value="${listone.getJiqicode()}" tabindex="11" class="inputNessCss" /> *</td>

                                <td style="visibility: hidden;">通讯密钥:</td>
                                <td style="visibility: hidden;"><input type="text"/></td>
                            </tr>
                        </table>
                    </div>

                    <div>
                        <p style="color:#000000;">注册信息 :</p>
                        <textarea style="margin-left: 10px" id="regRemark" name="regRemark" rows="11" cols="138" tabindex="12" class="inputcss" >${listone.getRemark()}</textarea>
                    </div>

                </dl>

                <input type="submit" value="提交" tabindex="13" id="SubmitButton" class="SubmitButton"/>

                <input type="button" value="返回" tabindex="14" id="ReturnButton"  class="SubmitButton"
                       onclick="javascript:Return();" />

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