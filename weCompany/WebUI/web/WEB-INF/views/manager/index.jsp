<%--
  Created by IntelliJ IDEA.
  User: dp
  Date: 14-11-13
  Time: 下午1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>洋芋网站管理系统</title>
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/icon.css">
<script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/style/manager.css"/>
<script type="text/javascript">


    function addTab(title, url) {
        if ($('#tabs').tabs('exists', title)) {
            $('#tabs').tabs('select', title);//选中并刷新
            var currTab = $('#tabs').tabs('getSelected');
            //var url = $(currTab.panel('options').content).attr('src');
            if (url != undefined && currTab.panel('options').title != 'Home') {
                $('#tabs').tabs('update', {
                    tab: currTab,
                    options: {
                        content: createFrame(url)
                    }
                })
            }
        } else {
            var content = createFrame(url);
            $('#tabs').tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
        tabClose();
    }
    function createFrame(url) {
        var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        return s;
    }
    function tabClose() {
        /*双击关闭TAB选项卡*/
        $(".tabs-inner").dblclick(function () {
            var subtitle = $(this).children(".tabs-closable").text();
            $('#tabs').tabs('close', subtitle);
        })
        /*为选项卡绑定右键*/
        $(".tabs-inner").bind('contextmenu', function (e) {
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });

            var subtitle = $(this).children(".tabs-closable").text();

            $('#mm').data("currtab", subtitle);
            $('#tabs').tabs('select', subtitle);
            return false;
        });
    }
    //绑定右键菜单事件
    function tabCloseEven() {
        //刷新
        $('#mm-tabupdate').click(function () {
            var currTab = $('#tabs').tabs('getSelected');
            var url = $(currTab.panel('options').content).attr('src');
            if (url != undefined && currTab.panel('options').title != 'Home') {
                $('#tabs').tabs('update', {
                    tab: currTab,
                    options: {
                        content: createFrame(url)
                    }
                })
            }
        })
        //关闭当前
        $('#mm-tabclose').click(function () {
            var currtab_title = $('#mm').data("currtab");
            $('#tabs').tabs('close', currtab_title);
        })
        //全部关闭
        $('#mm-tabcloseall').click(function () {
            $('.tabs-inner span').each(function (i, n) {
                var t = $(n).text();
                if (t != 'Home') {
                    $('#tabs').tabs('close', t);
                }
            });
        });
        //关闭除当前之外的TAB
        $('#mm-tabcloseother').click(function () {
            var prevall = $('.tabs-selected').prevAll();
            var nextall = $('.tabs-selected').nextAll();
            if (prevall.length > 0) {
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    if (t != 'Home') {
                        $('#tabs').tabs('close', t);
                    }
                });
            }
            if (nextall.length > 0) {
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    if (t != 'Home') {
                        $('#tabs').tabs('close', t);
                    }
                });
            }
            return false;
        });
        //关闭当前右侧的TAB
        $('#mm-tabcloseright').click(function () {
            var nextall = $('.tabs-selected').nextAll();
            if (nextall.length == 0) {
                //msgShow('系统提示','后边没有啦~~','error');
                alert('后边没有啦~~');
                return false;
            }
            nextall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                $('#tabs').tabs('close', t);
            });
            return false;
        });
        //关闭当前左侧的TAB
        $('#mm-tabcloseleft').click(function () {
            var prevall = $('.tabs-selected').prevAll();
            if (prevall.length == 0) {
                alert('到头了，前边没有啦~~');
                return false;
            }
            prevall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                $('#tabs').tabs('close', t);
            });
            return false;
        });

        //退出
        $("#mm-exit").click(function () {
            $('#mm').menu('hide');
        })
    }

    $(function () {
        tabCloseEven();
        $('#dd').dialog({
            closed: true,
            title: '编辑目录名称',
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        alert('ok');
                    }
                },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $('#dd').dialog('close');
                    }
                }
            ]
        });

        $('.cs-navi-tab').click(function () {
            var $this = $(this);
            var href = $this.attr('src');
            var title = $this.text();
            addTab(title, href);
        });
        $('#tt').tree({
            url: '/picture/folder',
            onClick: function (node) {
                var en = node.id != '';
                $('#btnReName').linkbutton(en ? 'enable' : 'disable');
                $('#btnRmdir').linkbutton(en ? 'enable' : 'disable');
                $('#btnMkdir').linkbutton('enable');
                addTab("图片管理", "/picture/index?node=" + node.id);
            }
        });
        $('#btnMkdir').linkbutton({
            disabled: true,
            iconCls: 'icon-add',
            plain: true
        });
        $('#btnMkdir').click(function () {
            $('#dd').dialog('open');
        });
        $('#btnReName').linkbutton({
            disabled: true,
            iconCls: 'icon-edit',
            plain: true
        });
        $('#btnReName').click(function () {
            $('#dd').dialog('open');
        })
        $('#btnRmdir').linkbutton({
            disabled: true,
            iconCls: 'icon-remove',
            plain: true
        });
        $('#btnRmdir').click(function () {
            $('#dd').dialog('open');
        })
    });
</script>
</head>
<body class="easyui-layout">
<div id="dd" icon="icon-save" style="padding:5px;width:260px;height:120px;">
    <label>目录名称：</label>
    <input type="text" name="folder" id="folderName">
</div>

<div region="north" border="true" class="cs-north">
    <div class="cs-north-bg">
        <div class="cs-north-logo">洋芋网站后台管理系统</div>
    </div>
</div>
<div region="west" border="true" split="true" title="导航" class="cs-west">
    <div class="easyui-accordion" fit="true" border="false">

        <div title="主要功能">
            <a href="javascript:void(0);" src="/forum/index" class="cs-navi-tab">栏目管理</a></p>
            <a href="javascript:void(0);" src="/category/index" class="cs-navi-tab">分类管理</a></p>
        </div>
        <div title="图片管理" class="easyui-layout">
            <div region="north" border="false" style="background:#fafafa;height:26px;padding:0px 5px">
            <a href="#" id="btnMkdir" title="创建目录"></a>
            <a href="#" id="btnReName" title="重命名"></a>
            <a href="#" id="btnRmdir" title="删除目录"></a>
        </div>
            <div region="center" border="false">
                <ul id="tt"></ul>
            </div>
        </div>
        <!--动态读取所有栏目-->
        <div title="Menu and Button">

        </div>
    </div>
</div>

<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="Home">
            <div class="cs-home-remark">
                <h1>洋芋网站后台管理系统</h1> <br>
                制作：洋芋 <br>
                博客：<a href="http://www.dtscal.cn" target="_blank">www.dtscal.cn</a><br>
                说明：嗲嗲
            </div>
        </div>
    </div>
</div>

<div region="south" border="false" class="cs-south">作者:洋芋 dpyang@live.com</div>
<!--tab右键-->
<div id="mm" class="easyui-menu cs-tab-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
</div>
<!--tab右键end-->
</body>
</html>
