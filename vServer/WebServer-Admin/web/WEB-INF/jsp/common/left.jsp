<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .leftmenu li a.image {
        background-image: url("${rootPath}component/componenttype/icon/IMAGE");
    }

    .leftmenu li a.db {
        background-image: url("${rootPath}component/componenttype/icon/db");
    }

    .leftmenu li a.script {
        background-image: url("${rootPath}component/componenttype/icon/script");
    }

    .leftmenu li a.wpf {
        background-image: url("${rootPath}component/componenttype/icon/wpfskin");
    }

    .leftmenu li a.reporter {
        background-image: url("${rootPath}component/componenttype/icon/reporter");
    }

    .leftmenu li a.web {
        background-image: url("${rootPath}component/componenttype/icon/webskin");
    }

    .leftmenu li a.webscript {
        background-image: url("${rootPath}component/componenttype/icon/webscript");
    }

    .leftmenu li a.wpfscript {
        background-image: url("${rootPath}component/componenttype/icon/WPFSCRIPT");
    }
    .leftmenu li a.task {
        background-image: url("${rootPath}component/componenttype/icon/WPFSCRIPT");
    }
    .leftmenu li a.tabelgp5 {
        background-image: url("${rootPath}component/componenttype/icon/TABELGP5");
    }

    .leftmenu li a.dbtabel5 {
        background-image: url("${rootPath}component/componenttype/icon/DBTABEL5");
    }

    .leftmenu li a.resource {
        background-image: url("${rootPath}component/componenttype/icon/RESOURCE");
    }

    .leftmenu li a.navigator {
        background-image: url("${rootPath}resource/images/icons/navigation_16.png");
    }

    .leftmenu li a.all {
        background-image: url("${rootPath}resource/images/icons/resource_16.png");
    }

    .leftmenu li a.flowtemplate {
        background-image: url("${rootPath}resource/images/icons/flow_chart_16.png");
    }

    .leftmenu li a.flowintance {
        background-image: url("${rootPath}resource/images/icons/flow_block_16.png");
    }

    .leftmenu li a.plan {
        background-image: url("${rootPath}resource/images/icons/plan_16.png");
    }

    .leftmenu li a.planlog {
        background-image: url("${rootPath}resource/images/icons/log_16.png");
    }

    .leftmenu li a.user {
        background-image: url("${rootPath}resource/images/icons/user_16.png");
    }

    .leftmenu li a.audit {
        background-image: url("${rootPath}resource/images/icons/audit_16.png");
    }

    .leftmenu li a.role {
        background-image: url("${rootPath}resource/images/icons/role_16.png");
    }

    .leftmenu li a.set_of_books {
        background-image: url("${rootPath}resource/images/icons/set_of_books_20.png");
    }

    .leftmenu li a.permission {
        background-image: url("${rootPath}resource/images/icons/permission_16.png");
    }

    .leftmenu li a.package_b {
        background-image: url("${rootPath}resource/images/icons/package_b_16.png");
    }

    .leftmenu li a.data_import {
        background-image: url("${rootPath}resource/images/icons/data_4_20.png");
    }

    .leftmenu li a.app_import {
        background-image: url("${rootPath}resource/images/icons/login_statistic.png");
    }

    .leftmenu li a.user_statistic {
        background-image: url("${rootPath}resource/images/icons/user_statistic_16.png");
    }

    .leftmenu li a.task_statistic {
        background-image: url("${rootPath}resource/images/icons/task_statistic_16.png");
    }

    .leftmenu li a.vector_statisti {
        background-image: url("${rootPath}resource/images/icons/vector_statistic_16.png");
    }

    .leftmenu li a.plan_instance {
        background-image: url("${rootPath}resource/images/icons/plan_instance_16.png");
    }

    .leftmenu li a.resource_statistics {
        background-image: url("${rootPath}resource/images/icons/resource_statistics_16.png");
    }

    .leftmenu li a.registration {
        background-image: url("${rootPath}resource/images/icons/registration_16.png");
    }

    .leftmenu li a.category_edit {
        background-image: url("${rootPath}resource/images/icons/category_edit_16.png");
    }

    .leftmenu li a.category_settings {
        background-image: url("${rootPath}resource/images/icons/category_settings_16.png");
    }

    .leftmenu li a.system_instance {
        background-image: url("${rootPath}resource/images/icons/system_instance.png");
    }

    .leftmenu li a.base {
        background-image: url("${rootPath}resource/images/icons/base_16.png");
    }

    .leftmenu li a.sms {
        background-image: url("${rootPath}resource/images/icons/sms_16.png");
    }

    .leftmenu li a.email {
        background-image: url("${rootPath}resource/images/icons/email_16.png");
    }
</style>
<div class="sidebar">
    <c:choose>
        <c:when test="${head==1}">
            <div id="accordion">
                <h3 class="open">导航管理</h3>
                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <LI  ${left==10?'class="current"':''}>
                            <A class="menuitem navigator" href="component/navigation/view">导航管理</A>
                        </LI>
                    </UL>
                </div>
                <h3 class="open">组件管理</h3>

                <div style="DISPLAY: block" class="content">
                    <h4 ${left==0?'class="open"':''}>全部组件</h4>

                    <div style="DISPLAY: ${left==0?'block':'none'}" class="content">
                        <UL class=leftmenu>
                            <LI  ${left==0?'class="current"':''}>
                                <A class="menuitem all" href="component/main/view">全部组件管理</A></LI>
                        </UL>
                    </div>
                    <h4 ${left>=1 && left<10?'class="open"':''}>显示组件</h4>
                    <div style="DISPLAY:${left>=1 && left<10?'block':'none'}" class="content">
                        <UL class=leftmenu>
                            <LI  ${left==1?'class="current"':''}>
                                <A class="menuitem web" href="component/webskin/view">网页页面</A>
                            </LI>
                            <LI  ${left==2?'class="current"':''}>
                                <A class="menuitem wpf" href="component/wpfskin/view">程序界面</A>
                            </LI>
                            <LI  ${left==3?'class="current"':''}>
                                <A class="menuitem reporter" href="component/reporter/view">填报界面</A>
                            </LI>
                        </UL>
                    </div>
                    <h4  ${left>=11 && left<=20 ?'class="open"':''}>执行组件</h4>

                    <div style="DISPLAY: ${left>=11 && left<=20?'block':'none'}" class="content">
                        <UL class=leftmenu>

                            <li  ${left==11?'class="current"':''}>
                                <a class="menuitem script" href="component/script/view">全局脚本管理</a>
                            </li>
                            <LI  ${left==13?'class="current"':''}>
                                <A class="menuitem webscript" href="component/webscript/view">网页脚本管理</A>
                            </LI>
                            <li  ${left==15?'class="current"':''}>
                                <a class="menuitem wpfscript" href="component/wpfscript/view">程序脚本管理</a>
                            </li>
                            <li  ${left==16?'class="current"':''}>
                                <a class="menuitem task" href="component/rtscript/view">填报脚本</a>
                            </li>
                            <li  ${left==17?'class="current"':''}>
                                <a class="menuitem task" href="component/taskscript/view">计划任务脚本管理</a>
                            </li>
                            <li  ${left==19?'class="current"':''}>
                                <a class="menuitem task" href="component/task/view">计划任务</a>
                            </li>

                        </UL>
                    </div>
                    <h4 ${left>=21 && left<=30?'class="open"':''}>数据组件</h4>

                    <div style="DISPLAY: ${left>=21 && left<=30?'block':'none'}" class="content">
                        <UL class=leftmenu>

                            <li  ${left==21?'class="current"':''}>
                                <a class="menuitem image" href="component/image/view">图片管理</a>
                            </li>
                            <li  ${left==22?'class="current"':''}>
                                <a class="menuitem resource" href="component/resource/view">文件管理</a>
                            </li>
                            <li  ${left==24?'class="current"':''}>
                                <a class="menuitem tabelgp5" href="component/tabelgp5/view">表组管理</a>
                            </li>
                            <li  ${left==25?'class="current"':''}>
                                <a class="menuitem db" href="component/db/view">数据库配置</a></li>
                        </UL>
                    </div>

                </div>
            </div>
        </c:when>
        <c:when test="${head==2}">
            <div id="accordion">
                <h3 class="open">导入导出</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class="leftmenu">
                        <LI ${left==1?'class="current"':''}>
                            <A class="menuitem package_b" href="package/main/view">组件包管理</A></LI>
                        <li ${left==2?'class="current"':''}>
                            <a class="menuitem app_import" href="package/fourProgram/view">4代程序导入</a></li>
                        <LI ${left==3?'class="current"':''}>
                            <A class="menuitem data_import" href="package/fourData/view">4代数据导入</A></LI>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:when test="${head==3}">
            <div id="accordion">
                <h3 class="open">流程管理</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class="leftmenu">
                        <LI ${left==1?'class="current"':''}>
                            <A class="menuitem flowtemplate" href="flow/template/view">流程模板管理</A>
                        </LI>
                        <li ${left==2?'class="current"':''}>
                            <a class="menuitem flowintance" href="flow/instance/view">流程实例管理</a>
                        </li>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:when test="${head==4}">
            <div id="accordion">
                <h3 class="open">计划任务</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <LI ${left==1?'class="current"':''}><A class="menuitem plan"
                                                                            href="task/instance/view">计划实例</A>
                        </LI>
                        <LI ${left==3?'class="current"':''}><A class="menuitem planlog"
                                                                            href="task/log/view">计划日志</A>
                        </LI>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:when test="${head==5}">
            <div id="accordion">
                <h3 class="open">系统设置</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <li ${left==2?'class="current"':''}>
                            <a class="menuitem registration" href="javascript:loadPage('package/main/view')">注册管理</a>
                        </li>
                        <LI ${left==3?'class="current"':''}>
                            <A class="menuitem category_edit" href="system/instance">系统实例管理</A>
                        </LI>
                        <li ${left==4?'class="current"':''}>
                            <a class="menuitem category_settings" href="component/componenttype/view">组件分类类型</a>
                        </li>
                        <LI ${left==5?'class="current"':''}>
                            <A class="menuitem system_instance" href="system/category">系统类型</A>
                        </LI>
                        <LI ${left==1?'class="current"':''}>
                            <A class="menuitem base" href="system/basicSettings/edit">基础设置</A>
                        </LI>
                    </UL>
                </div>
            </div>
            <div id="accordion">
                <h3 class="open">通讯设置</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <LI  ${left==6?'class="current"':''}>
                            <A class="menuitem sms" href="system/basicSettings/SMS">短信设置</A>
                        </LI>
                        <LI  ${left==7?'class="current"':''}>
                            <A class="menuitem email" href="system/basicSettings/email">邮箱设置</A>
                        </LI>
                    </UL>
                </div>
            </div>

        </c:when>
        <c:when test="${head==6}">
            <div id="accordion">
                <h3 class="open">报表统计</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <LI ${left==1?'class="current"':''}>
                            <A class="menuitem user_statistic" href="report/user/view">用户登陆统计</A>
                        </LI>
                        <li ${left==2?'class="current"':''}>
                            <a class="menuitem resource_statistics" href="report/component/view">组件使用统计</a>
                        </li>
                        <LI ${left==3?'class="current"':''}>
                            <A class="menuitem plan_instance" href="report/flow/view">流程使用统计</A>
                        </LI>
                        <LI ${left==4?'class="current"':''}>
                            <A class="menuitem task_statistic" href="report/task/view">定时任务统计</A>
                        </LI>
                        <LI ${left==5?'class="current"':''}>
                            <A class="menuitem vector_statisti" href="report/system/view">系统实例访问统计</A>
                        </LI>
                    </ul>
                </div>
            </div>
        </c:when>
        <c:when test="${head==7}">
            <div id="accordion">
                <h3 class="open">安全管理</h3>

                <div style="DISPLAY: block" class="content">
                    <UL class=leftmenu>
                        <LI ${left==1?'class="current"':''}>
                            <A class="menuitem user" href="account/view">账号管理</A></LI>
                        <li ${left==2?'class="current"':''}>
                            <a class="menuitem audit" href="javascript:loadPage('package/main/view')">账号审核</a>
                        </li>
                        <LI ${left==3?'class="current"':''}>
                            <A class="menuitem role" href="role/view">角色管理</A>
                        </LI>
                        <LI ${left==4?'class="current"':''}>
                            <A class="menuitem set_of_books" href="setofbooks/index">帐套管理</A>
                        </LI>
                        <li ${left==5?'class="current"':''}>
                            <a class="menuitem permission" href="permission/view">权限管理</a>
                        </li>
                    </ul>
                </div>
            </div>
        </c:when>
    </c:choose>
</div>
