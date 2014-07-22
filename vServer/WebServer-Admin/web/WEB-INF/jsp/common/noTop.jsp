<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function hightOnMenu(elemId,curCss){
        if(!document.getElementById) return false;
        if(!document.getElementsByName) return false;
        if(!document.getElementById(elemId)) return false;
        var elemId=document.getElementById(elemId);
        var links=elemId.getElementsByTagName("a");
        for(var i=0;i<links.length;i++){
            links[i].onclick=function(){
                for(var j=0;j<links.length;j++){
                    links[n].className="";
                    this
                }
            }
        }
    }
    function exitFun(){
        window.location.href="/ExitSystem";
    }
</script>
<div class="header">
    <div style=" position:absolute; top:-6px; left:0px;width:220px; height: 80px;  overflow: hidden;">
        <%--<img src="../../resource/images/logo5.png" alt="Logo" style="width:220px; height: 80px;">--%>
         <a  href="index">
                <object id="flash_id" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="220" height="90" align="center">
                    <param name="movie" value="resource/swf/logo.swf">
                    <param name="quality" value="high">
                    <param name="wmode" value="transparent">
                    <embed src="resource/swf/logo.swf" width="220" height="80" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>
                </object>
         </a>
    </div>

    <div class="accountinfo">
        <img alt="Avatar" src="resource/images/avatar.png">

        <div class="info" >
            <style type="text/css">
                .info a{color: #bbd0e8;}
            </style>
            <table style="font-size: 12px;">
                <tr>
                    <td style="color: #f1f1f1" colspan="3">${account.showName} (${account.account})</td>
                </tr>
                <tr>
                    <td colspan="3"> <a href="javascript:setofbooks();" id="setofbooks" >2013年4月4日 帐套A</a></td>
                </tr>
                <tr style="padding: 5px;">
                    <td> <c:if test="${not empty account}">
                        <a href="ilemsun://command/?command=open&token=${account.token}" style="border-right: 1px solid #f1f1f1;">启动程序&nbsp;&nbsp;</a>
                    </c:if>
                    </td>
                    <td >
                        <a href="/account/edit?account=${account.id}" style="border-right: 1px solid #f1f1f1;" >&nbsp;&nbsp;修改信息&nbsp;&nbsp;</a>
                    </td>
                    <td ><a href="javascript:exitFun();">&nbsp;&nbsp;退出</a></td>
                </tr>
            </table>
        </div><!-- info -->
    </div><!-- accountinfo -->
</div>