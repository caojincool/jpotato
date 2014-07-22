<%--
  公式设计器显示视图
  User: xudong
  Date: 14-2-13
  Time: 下午2:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!doctype html>
<html>
<head>
    <title>公式设计器</title>
    <base href="<%= rootPath %>">
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <script type="text/javascript" src="jquery/jquery.js"></script>
    <script type="text/javascript" src="jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="jquery/splitter/jquery.splitter.js"></script>
    <script type="text/javascript" src="jquery/jplumb/jquery.jsPlumb.js"></script>
    <script type="text/javascript" src="jquery/handsontable/jquery.handsontable.js"></script>
    <script type="text/javascript" src="resource/js/iservice/formula/designer.js"></script>

    <link href="jquery/themes/base/jquery-ui.css" rel="stylesheet"/>
    <link href="jquery/splitter/jquery.splitter.css" rel="stylesheet"/>
    <link href="jquery/handsontable/jquery.handsontable.css" rel="stylesheet"/>
    <link href="resource/js/iservice/formula/designer.css" rel="stylesheet" />
</head>
<body>
<div class="main" id="main">
    <div class="flowchart" id="designer">
        <div class="window" id="component_1">

            <div class="component">
                <div class="component-header">头部信息</div>
                <div class="component-body">
                    <table>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                    </table>
                </div>
            </div>



            <div class="ep"></div>
        </div>
        <div class="window" id="component_2">
            <div class="component">
                <div class="component-header">头部信息</div>
                <div class="component-body">
                    <table>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="ep"></div>
        </div>

        <div class="window" id="component_3">
            <div class="component">
                <div class="component-header">头部信息</div>
                <div class="component-body">
                    <table>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>A</td>
                            <td>数据</td>
                        </tr>
                    </table>
                </div>
            </div>



            <div class="ep"></div>
        </div>
    </div>
    <div class="formula-setting" id="setting">

    </div>
</div>



</body>
</html>
