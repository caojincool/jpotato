<%--
  Created by IntelliJ IDEA.
  User: dpyang
  Date: 2014/11/22
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>洋芋网站管理系统</title>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.2.6/themes/gray/easyui.css"/>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/style/manager.css"/>
    <script type="text/javascript">
        $(function () {
            $('#tt2').tree({
                checkbox: true,
                url: '/load',
                onClick: function (node) {
                    $(this).tree('toggle', node.target);
                    $('#parent').text(node.id);
                    $('input[name="text"]').val(node.text);
                },
                onContextMenu: function (e, node) {
                    e.preventDefault();
                    $('#tt2').tree('select', node.target);
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            });
        });
        function reload() {
            var node = $('#tt2').tree('getSelected');
            if (node) {
                $('#tt2').tree('reload', node.target);
            } else {
                $('#tt2').tree('reload');
            }
        }
        function getChildren() {
            var node = $('#tt2').tree('getSelected');
            if (node) {
                var children = $('#tt2').tree('getChildren', node.target);
            } else {
                var children = $('#tt2').tree('getChildren');
            }
            var s = '';
            for (var i = 0; i < children.length; i++) {
                s += children[i].text + ',';
            }
            alert(s);
        }
        function getChecked() {
            var nodes = $('#tt2').tree('getChecked');
            var s = '';
            for (var i = 0; i < nodes.length; i++) {
                if (s != '') s += ',';
                s += nodes[i].text;
            }
            alert(s);
        }
        function getSelected() {
            var node = $('#tt2').tree('getSelected');
            alert(node.text);
        }
        function collapse() {
            var node = $('#tt2').tree('getSelected');
            $('#tt2').tree('collapse', node.target);
        }
        function expand() {
            var node = $('#tt2').tree('getSelected');
            $('#tt2').tree('expand', node.target);
        }
        function collapseAll() {
            var node = $('#tt2').tree('getSelected');
            if (node) {
                $('#tt2').tree('collapseAll', node.target);
            } else {
                $('#tt2').tree('collapseAll');
            }
        }
        function expandAll() {
            var node = $('#tt2').tree('getSelected');
            if (node) {
                $('#tt2').tree('expandAll', node.target);
            } else {
                $('#tt2').tree('expandAll');
            }
        }
        function append() {
            var node = $('#tt2').tree('getSelected');
            if (node != null) {
                $('input[name="parent"]').val(node.id);
                $('#parent').text(node.text);
                var txt = $('input[name="text"]').val();
                var c = true;
                $.each($('#tt2').tree('getChildren', node.target), function (i, p) {
                    if (p.text == txt) {
                        alert("您输入的名称已存在！");
                        c = false;
                        return c;
                    }
                })
                if (c) {
                    $('#tt2').tree('append', {
                        parent: node.target,
                        data: [
                            {
                                text: $('input[name="text"]').val(),
                                checked: false
                            }
                        ]
                    });
                }
            } else {
                alert("请选择父节点！");
            }
        }
        function remove() {
            var node = $('#tt2').tree('getSelected');
            $('#tt2').tree('remove', node.target);
        }
        function update() {
            var node = $('#tt2').tree('getSelected');
            if (node) {
                node.text = '<span style="font-weight:bold">new text</span>';
                node.iconCls = 'icon-save';
                $('#tt2').tree('update', node);
            }
        }
        function isLeaf() {
            var node = $('#tt2').tree('getSelected');
            var b = $('#tt2').tree('isLeaf', node.target);
            alert(b)
        }
    </script>
</head>
<body>
<h2>Tree</h2>

<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip"></div>
    <div>Click the node and drag it to other position.</div>
</div>

<p>Create from HTML markup</p>
<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
    <li>
        <span>Folder</span>
        <ul>
            <li state="closed">
                <span>Sub Folder 1</span>
                <ul>
                    <li>
                        <span><a href="#">File 11</a></span>
                    </li>
                    <li>
                        <span>File 12</span>
                    </li>
                    <li>
                        <span>File 13</span>
                    </li>
                </ul>
            </li>
            <li>
                <span>File 2</span>
            </li>
            <li>
                <span>File 3</span>
            </li>
            <li>File 4</li>
            <li>File 5</li>
        </ul>
    </li>
    <li>
        <span>File21</span>
    </li>
</ul>
<p>Create from JSON data</p>

<div>
    <form id="frmTree">
        父节点：<label id="parent"></label>
        <input type="hidden" name="id"/>
        当前节点
        <input type="text" name="parent">
        新增节点名称
        <input type="text" name="text"/>
    </form>
</div>
<div style="margin:10px;">
    <a href="#" onclick="reload()" class="easyui-linkbutton l-btn">reload</a>
    <a href="#" onclick="getChildren()" class="easyui-linkbutton l-btn">getChildren</a>
    <a href="#" onclick="getChecked()" class="easyui-linkbutton l-btn">getChecked</a>
    <a href="#" onclick="getSelected()" class="easyui-linkbutton l-btn">getSelected</a>
    <a href="#" onclick="collapse()" class="easyui-linkbutton l-btn">collapse</a>
    <a href="#" onclick="expand()" class="easyui-linkbutton l-btn">expand</a>
    <a href="#" onclick="collapseAll()" class="easyui-linkbutton l-btn">collapseAll</a>
    <a href="#" onclick="expandAll()" class="easyui-linkbutton l-btn">expandAll</a>
    <a href="#" onclick="append()" class="easyui-linkbutton l-btn">append</a>
    <a href="#" onclick="remove()" class="easyui-linkbutton l-btn">remove</a>
    <a href="#" onclick="update()" class="easyui-linkbutton l-btn">update</a>
    <a href="#" onclick="isLeaf()" class="easyui-linkbutton l-btn">isLeaf</a>
</div>

<ul id="tt2"></ul>

<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="append()" iconCls="icon-add">Append</div>
    <div onclick="remove()" iconCls="icon-remove">Remove</div>
    <div class="menu-sep"></div>
    <div onclick="expand()">Expand</div>
    <div onclick="collapse()">Collapse</div>
</div>
</body>
</html>
