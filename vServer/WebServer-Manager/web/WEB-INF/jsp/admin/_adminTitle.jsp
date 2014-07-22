<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.lemsun.web.Session" %>
<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-14
  Time: 上午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function mousedown(){

    }

</script>
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
    <%--<div class="topheader" align="left" style="position:absolute;left: 75px;top: 50px">--%>
        <%--&lt;%&ndash;<ul class="notebutton">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<li class="note">&ndash;%&gt;--%>
                <%--<a class="messagenotify" href="pages/message.html">--%>
                    <%--<span class="wrap">--%>
                        <%--<span class="thicon msgicon" style="position: absolute;top: 9px"></span>--%>
                        <%--<span class="count" style="position: absolute;left: -5px">3</span>--%>
                    <%--</span>--%>
                <%--</a>--%>
                <%--<a class="alertnotify" href="pages/info.html" style="position: absolute;left: 30px">--%>
                	<%--<span class="wrap">--%>
                    	<%--<span class="thicon infoicon"></span>--%>
                        <%--<span class="count">5</span>--%>
                    <%--</span>--%>
                <%--</a>--%>
            <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<li class="note">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a class="alertnotify" href="pages/info.html">&ndash;%&gt;--%>
                	<%--&lt;%&ndash;<span class="wrap">&ndash;%&gt;--%>
                    	<%--&lt;%&ndash;<span class="thicon infoicon"></span>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<span class="count">5</span>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
    <%--</div><!-- topheader -->--%>


    <!-- logo -->
    <%--<a href=""><img alt="Logo" src="images/logo2.png">${account.name}</a>--%>


    <div class="tabmenu">
        <ul>
            <li class="current"><a class="dashboard" ><span><%=StringUtils.isEmpty(request.getParameter("titlename"))?"管理":request.getParameter("titlename")%></span></a></li>
        </ul>
    </div><!-- tabmenu -->

    <div class="accountinfo">
        <img alt="Avatar" src="resource/swf/logoPic.png" width="50" height="50">
        <div class="info">
            <h3><%--<%=Session.getAccount(request).getAccount().getAccount()%>--%></h3>
            <small>youremail@domain.com</small>
            <p>
                <a href="">Account Settings</a> <a href="index.html">Logout</a>
            </p>
        </div><!-- info -->
    </div><!-- accountinfo -->