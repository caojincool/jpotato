<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gm
  Date: 12-11-20
  Time: 下午2:10
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

    <link rel="stylesheet" type="text/css" media="screen" href="ext/resources/css/ext-all.css" />

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

         Ext.onReady(function(){
             //创建启用日期控件
             Ext.create('Ext.form.DateField',{
                         id:'qyDate',         //后台获取值时，名称
                         name:'qyDate',
                         height:24,
                         width:200,
                         format:"Y-m-d",
                         allowBlank: false,
                         editable:false,      //是否允许编辑日期控件
                         emptyText:'请选择日期...',
                         blankText: '请选择日期...',
                         msgTarget: 'side',    //提示语显示位置，side为右边
                         renderTo:'divqydate'  //指向启用日期div
                     }
             );
             //创建结束日期控件
             Ext.create('Ext.form.DateField',{
                         id:'jsDate',        //后台获取值时，名称
                         name:'jsDate',
                         height:24,
                         width:200,
                         format:'Y-m-d',
                         allowBlank: false,
                         editable:false,     //是否允许编辑日期控件
                         emptyText:'请选择日期...',
                         blankText: '请选择日期...',
                         msgTarget: 'side',   //提示语显示位置，side为右边
                         renderTo:'divjsdate' //指向结束日期div
                     }
             );
         });

         function CheckSubmit(){
             //验证实例名称
             var sname= $.trim($('#name').val());
             if(sname=='')
             {
                 alert("实例名称为必填项！");
                 return false;
             }
             //验证日期
             var sqydate = Ext.getCmp('qyDate').getValue();
             var sjsdate = Ext.getCmp('jsDate').getValue();
             if(sqydate==null)
             {
                 alert("请选择启用日期！");
                 return false;
             }
             if(sjsdate==null)
             {
                 alert("请选择结束日期！");
                 return false;
             }
             if(sqydate>sjsdate)
             {
                 alert("启用日期不能大于结束日期！");
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
    <%--<script type=text/javascript src="/resource/js/system/instanceOperate.js"></script>--%>
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
        <form method="post" action="system/instance/create/save" onsubmit="return CheckSubmit();" id="formupdate">
            <fieldset style="width :99%; margin :10px 0 10px 10px; padding: 5px; border :1px solid #666; background :#FFFFFF;">
                <p>实例信息</p>
                <dl>
                    <div>
                        <table style="width: 100% ;height:80px">
                            <tr>
                                <td width="70">实例名称:</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="name" name="name" tabindex="1" class="inputNessCss" /> *</td>
                                <td >系统类型:</td>
                                <td ><select name="categorytype" tabindex="2">
                                    <c:forEach var="category" items="${categorylist}" varStatus="status" >
                                        <option value="${categorylist[status.count-1].getPid()}">${categorylist[status.count-1].getName()}</option>
                                    </c:forEach>
                                </select></td>
                                <td >说明:</td>
                                <td><input type="text" id="remark" name="remark" tabindex="4" class="inputcss" /></td>
                            </tr>
                            <tr>
                                <td>地址:</td>
                                <td><input type="text" id="address" name="address" tabindex="5" class="inputcss" /></td>

                                <td>启用日期:</td>
                                <td style="color: red;"><div id="divqydate" name="divqydate" tabindex="6"  /> <p style="color: red;float: right;margin-right: 50px;">*</p></td>

                                <td>结束日期:</td>
                                <td style="color: red;"><div id="divjsdate" name="divjsdate" tabindex="7"  /> <p style="color: red;float: right;margin-right: 50px;">*</p></td>
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
                                <td width="70">IP地址:</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="ip" name="ip" tabindex="8" class="inputcss"/> *</td>

                                <td width="75">通讯密钥:</td>
                                <td width="235" style="color: red;"><input type="text" id="txKey" name="txKey" tabindex="9" class="inputNessCss"/> *</td>

                                <td style="visibility: hidden;">通讯密钥:</td>
                                <td style="visibility: hidden;"><input type="text"/></td>
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
                                <td width="70">使用人 :</td>
                                <td width="235" style="color: red;">
                                    <input type="text" id="User" name="User" tabindex="10" class="inputNessCss" /> *</td>


                                <td width="75">机器码 :</td>
                                <td width="235" style="color: red;"><input type="text" id="jiqicode" name="jiqicode" tabindex="11" class="inputNessCss" /> *</td>

                                <td style="visibility: hidden;">通讯密钥:</td>
                                <td style="visibility: hidden;"><input type="text"/></td>
                            </tr>
                        </table>
                    </div>

                    <div>
                        <p style="color:#000000;">注册信息 :</p>
                        <textarea style="margin-left: 10px" id="regRemark" name="regRemark" rows="11" cols="138" tabindex="12" class="inputcss" ></textarea>
                    </div>

                </dl>

                <input type="submit" value="提交" tabindex="13" id="SubmitButton" class="SubmitButton" />

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