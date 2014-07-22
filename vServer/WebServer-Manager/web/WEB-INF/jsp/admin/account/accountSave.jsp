<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-12-21
  Time: 上午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员管理</title>
    <jsp:include page="../_header.jsp"/>

    <SCRIPT type=text/javascript src="resource/js/jquery-1.7.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery-ui-1.8.16.custom.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.validate.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.colorbox-min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="resource/js/jquery.flot.min.js"></SCRIPT>

    <link rel="stylesheet" media="screen" href="resource/css/froms_dl.css"/>

    <link rel="stylesheet" type="text/css" media="screen" href="ext/resources/css/ext-all.css" />

     <script type="text/javascript">
        Ext.onReady(function(){
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
        function overfun(obj){
            obj.style.backgroundColor="#cccccc";
        }
        function outfun(obj){
            obj.style.backgroundColor="#ffffff";
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
        <form action="">
            <fieldset style="width :99%; margin :10px 0 10px 10px; padding: 5px; border :1px solid #666; background :#FFFFFF;">
                <div>
                    <p style="margin-left: 30" align="left">基本信息(必填):</p>
                    <fieldset style="width :92%; margin :10px 0 10px 70px; padding: 5px; border :1px solid #666; background :#FFFFFF;">
                        <table style="height: 80px;width: 100%">
                            <tr>
                                <td width="40">工号:</td>
                                <td width="210" style="color: #ff0000;"><input type="text" name="gh" onmouseover="overfun(this)" onmouseout="outfun(this)">*</td>
                                <td width="70" align="center">姓名:</td>
                                <td width="210" style="color: #ff0000;"><input type="text" name="xm" onmouseover="overfun(this)" onmouseout="outfun(this)">*</td>
                                <td align="center">密码:</td>
                                <td style="color: #ff0000;"><input type="password" name="mm" onmouseover="overfun(this)" onmouseout="outfun(this)">*</td>
                            </tr>
                            <tr>
                                <td width="40">性别:</td>
                                <td style="color: #ff0000;"><select style="width: 200">
                                    <option>男</option>
                                    <option>女</option>
                                </select>*</td>
                                <td width="70" align="center">电话:</td>
                                <td width="210" style="color: #ff0000;"><input type="text" name="dh" onmouseover="overfun(this)" onmouseout="outfun(this)">*</td>
                                <td width="90" align="center">出生日期:</td>
                                <td style="color: red;margin-left: 200"><div id="divjsdate" name="divjsdate" tabindex="7"/><p style="color: red;float: right;margin-right: 8px;"></p></td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
                <div>
                    <p style="margin-left: 30" align="left">扩展信息:</p>
                    <fieldset style="width :92%; margin :10px 0 10px 70px; padding: -3px; border :1px solid #666; background :#FFFFFF;">
                        <table style="height: 80px;width: 100%">
                            <tr>
                                <td width="40">民族:</td>
                                <td width="210"><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                                <td width="70" align="center">籍贯:</td>
                                <td width="210"><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                                <td width="90" align="center">联系住址:</td>
                                <td><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                            </tr>
                            <tr>
                                <td width="40">邮编:</td>
                                <td width="210"><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                                <td width="70" align="center">QQ/MSN:</td>
                                <td width="210"><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                                <td width="90" align="center">E-mail:</td>
                                <td><input type="text" onmouseover="overfun(this)" onmouseout="outfun(this)"></td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
                <div>
                    <p style="margin-left: 30" align="left">用户组信息:</p>
                    <fieldset style="width :92%; margin :10px 0 10px 70px; padding: -3px; border :1px solid #666; background :#FFFFFF;">
                        <table style="height: 30px;width: 100%">
                            <tr>
                                <td width="70">角色选择:</td>
                                <td><select>
                                    <option>管理员</option>
                                    <option>普通</option>
                                    <option>会员</option>
                                </select></td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
                <div>
                    <p style="margin-left: 30" align="left">备注信息:</p>
                    <fieldset style="width :92%; height: 87; margin :10px 0 10px 70px; padding: -3px; border :1px solid #666; background :#FFFFFF;">
                        <div style="margin-top: 5">
                            <label>备注</label>
                        </div>
                        <div style="margin-left: 40;margin-top: -25">
                            <textarea rows="4" cols="125"></textarea>
                        </div>

                    </fieldset>
                </div>

                <div align="center" style="width: 92%">
                    <input style="width: 50" type="submit" value="保存">
                    <input style="width: 50" type="button" value="返回">
                </div>
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