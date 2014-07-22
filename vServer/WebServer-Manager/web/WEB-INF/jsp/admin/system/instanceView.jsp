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
                     })
                     .readOnly(true);
         });

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

        <fieldset>
            <p>实例信息</p>
            <dl>
                <div>
                    <table style="width: 100% ;height:80px">
                        <tr>
                            <td width="70">实例名称:</td>
                            <td width="210">
                                <input type="text" name="name" value="${listone.getName()}" tabindex="1" class="inputcss" /></td>
                            <td >系统类型:</td>
                            <td ><input type="text" name="systype" tabindex="3" class="inputcss" value="${listone.getOwner().getName()}" /></td>
                            <td >说明:</td>
                            <td><input type="text" name="remark"  tabindex="4" class="inputcss" value="${listone.getRemark()}" /></td>
                        </tr>
                        <tr>
                            <td>地址:</td>
                            <td><input type="text" name="address" tabindex="5" class="inputcss" value="${listone.getAddress()}" /></td>
                            <td>启用日期:</td>
                            <td><input type="text" name="sdate" tabindex="6" class="inputcss" value="${listone.getQyDate()}" /></td>
                            <td>结束日期:</td>
                            <td><input type="text" name="edate" tabindex="7" class="inputcss" value="${listone.getJsDate()}" /></td>
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
                            <td width="210">
                                <input type="text" name="ip" tabindex="8" class="inputcss" value="${listone.getIp()}"/></td>

                            <td>通讯密钥:</td>
                            <td><input type="text" name="remark" tabindex="9" class="inputcss" value="${listone.getTxKey()}"/></td>

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
                            <td width="210">
                                <input type="text" name="rcomname" tabindex="10" class="inputcss" value="${listone.getUser()}" /></td>


                            <td width="74">机器码 :</td>
                            <td><input type="text" name="rcomcode" tabindex="11" class="inputcss" value="${listone.getJiqicode()}" /></td>

                            <td style="visibility: hidden;">通讯密钥:</td>
                            <td style="visibility: hidden;"><input type="text"/></td>
                        </tr>
                    </table>
                </div>

                <div>
                    <p style="color:#000000;">注册信息 :</p>
                    <textarea style="margin-left: 10px" name="rcomremark" rows="11" cols="138" tabindex="12" class="inputcss" >${listone.getRemark()}</textarea>
                </div>
            </dl>

            <input type="button" value="返回" tabindex="13" id="ReturnButton"  class="SubmitButton"
                   onclick="javascript:Return();" />
        </fieldset>
    </div>

    <BR clear=all>
    <div class=footer>
        <jsp:include page="../_footer.jsp"/>
    </div>
</div>
</body>
</html>