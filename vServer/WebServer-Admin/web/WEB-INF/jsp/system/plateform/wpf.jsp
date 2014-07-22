<%--
  WPF平台信息界面
  User: gm
  Date: 13-1-17
  Time: 上午9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>WPF平台信息</title>

</head>

<body class="bodygrey">


<div style="display: none;" id="cboxOverlay"></div><div style="padding-bottom: 20px; padding-right: 0px; display: none;" class="" id="colorbox"><div id="cboxWrapper"><div><div style="float: left;" id="cboxTopLeft"></div><div style="float: left;" id="cboxTopCenter"></div><div style="float: left;" id="cboxTopRight"></div></div><div style="clear: left;"><div style="float: left;" id="cboxMiddleLeft"></div><div style="float: left;" id="cboxContent"><div style="width: 0px; height: 0px; overflow: hidden; float: left;" id="cboxLoadedContent"></div><div style="float: left;" id="cboxLoadingOverlay"></div><div style="float: left;" id="cboxLoadingGraphic"></div><div style="float: left;" id="cboxTitle"></div><div style="float: left;" id="cboxCurrent"></div><div style="float: left;" id="cboxNext"></div><div style="float: left;" id="cboxPrevious"></div><div style="float: left;" id="cboxSlideshow"></div><div style="float: left;" id="cboxClose"></div></div><div style="float: left;" id="cboxMiddleRight"></div></div><div style="clear: left;"><div style="float: left;" id="cboxBottomLeft"></div><div style="float: left;" id="cboxBottomCenter"></div><div style="float: left;" id="cboxBottomRight"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div>



<div class="maincontent" style="margin-left: 0px;">
<div id="divwpf" style="margin: 5px;">

    <form method="post" action="system/category/wpf/update" onsubmit="return CheckSubmit();" id="formupdate">
        <div class="form_default">
            <fieldset style="padding: 10px;margin:10px;">
                <p style="display: none;">
                    <label for="category">PId:</label>
                    <input style="width: 350px;background-color:#F7F7F7;" type="text" id="category" name="category" value="${listone.category}" readonly="true" class="redonly"/>
                </p>

                <p>
                    <label>系统名称:</label><b>${listone.getName()}</b>
                    <%--<input style="width: 350px;" type="text" id="cname" name="cname" value="${listone.getName()}" readonly="true" class="redonly"/>--%>
                </p>
                <p>
                    <label>修改日期:</label><span><fmt:formatDate value="${listone.getUpdateTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    <%--<input style="width: 350px;" type="text" id="cupdateTime" name="cupdateTime" value="${listone.getUpdateTime()}" readonly="true" class="redonly"/>--%>
                </p>
                <p>
                    <label>创建人 : </label><span>${listone.getCreateUser()}</span>
                    <%--<input style="width: 350px;" type="text" id="ccreateUser" name="ccreateUser" value="${listone.getCreateUser()}" readonly="true" class="redonly"/>--%>
                </p>
                <p>
                    <label for="ckey">Key :</label>
                    <input style="width: 350px;" type="text" id="ckey" name="ckey" value="${listone.getKey()}" />
                </p>

                <p>
                    <label for="cstartResource">起始页挂载资源 :</label>
                    <input type="text" id="cstartResource" name="cstartResource"  style="width: 350px;"
                           value="${listone.getStartResource()}" class="inputNessCss" />
                </p>

                <p>
                    <label for="cstartscript">开始脚本 :</label>
                    <textarea id="cstartscript" name="cstartscript" rows="10" cols="128"
                              >${listone.getStartScript()}</textarea>
                </p>

                <p>
                    <label for="cendscript">结束脚本 :</label>
                    <textarea id="cendscript" name="cendscript" rows="10" cols="128"
                              >${listone.getEndScript()}</textarea>
                </p>

                <p>
                    <label for="cparam">附加集合 :</label>
                    <textarea id="cparam" name="cparam" rows="10" cols="128"
                              >${listone.getParamstring()}</textarea>
                </p>
            </fieldset>
            <div class="bottombtn form_default">

                <button  type="reset"  >重置</button>
                <button  type="submit">保存</button>
            </div>
        </div>
    </form>

</div>
    </div>
<br>

     <script type="text/javascript">



          String.prototype.trim = function () {
              return this.replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
          }

          function CheckSubmit(){
              //验证Key是否为空
              var sckey= document.getElementById("ckey").value.trim();
              if(sckey=='')
              {
                  alert("Key为必填项！");
                  return false;
              }
              //验证起始页挂载资源是否为空
              var scstartResource= document.getElementById("cstartResource").value.trim();
              if(scstartResource=='')
              {
                  alert("起始页挂载资源为必填项！");
                  return false;
              }
              //验证开始脚本是否为空
              var scstartscript= document.getElementById("cstartscript").value.trim();
              if(scstartscript=='')
              {
                  alert("请输入开始脚本！");
                  return false;
              }
              //验证开始脚本是否为空
              var scendscript= document.getElementById("cendscript").value.trim();
              if(scendscript=='')
              {
                  alert("请输入结束脚本！");
                  return false;
              }
              //验证附加集合
              var s = document.getElementById("cparam").value.trim();
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
//                         alert(sfirst1[j]+"~~"+sfirst1[k]);
                          if(sfirst1[j].trim() == sfirst1[k].trim())
                          {
                              alert("集合元素名称有重复！");
                              return false;
                          }
                      }
                  }
              }
          }
    </script>
</body>
</html>