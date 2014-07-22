<%@ page import="java.io.Console" %>
<%--
  Created by IntelliJ IDEA.
  User: Lucklim
  Date: 12-11-14
  Time: 上午9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<DIV id="accordion">
    <H3 class=open>组件管理</H3>
    <div style="DISPLAY: block" class=content>
        <UL class=leftmenu>
            <!--class=current-->
            <LI><A class=navigator href="component/navigation">导航管理</A></LI>
            <LI><A class=type-icon href="component/componenttype">类别管理</A></LI>
            <LI><A class=ksirtet href="component/componentmain">组件管理</A></LI>
        </UL></div>

    <H3 class=open>系统管理</H3>
    <div style="DISPLAY: block" class=content>
        <UL class=leftmenu>
            <LI><A class=systype href="system/category" >系统类型管理</A></LI>
            <LI><A class=systemins href="system/instance">系统实例管理</A></LI>
        </UL></div>
    <H3 class=open>人员管理</H3>
    <div style="DISPLAY: block" class=content>
        <UL class=leftmenu>
            <LI><A class=users href="account/accounts">账号管理</A></LI>
            <LI><A class=role href="role/roles">角色管理</A></LI>
            <LI><A class=users href="permission/permissions">权限配置</A></LI>
        </UL></div>
    <H3 class=open>关于我们</H3>
    <div style="DISPLAY: block" class=content>
        林木森
        </div>
</DIV>