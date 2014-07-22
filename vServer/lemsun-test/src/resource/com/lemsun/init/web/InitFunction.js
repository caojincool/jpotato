var utils = com.lemsun.client.core.Utils;

/**
 * 返回组件编码的字符串
 * @param pid 组件编码
 * @return {*}
 */
function checkpid(pid) {
    if (pid == undefined || pid == null || pid == '')
        return '';
    if (typeof pid != 'string') {
        pid = pid.toString();
    }
    return pid;
}

/**
 * 提供WEB上的简化组件导航连接 如果是一个WPF组件. 生成ilemsun协议, 如果是一个WEB组件生成组件地址.
 * @param pid 组件id, 不区分大小写. 可以输入组件末尾编码
 * @param isSystem 是否是系统组件如果不提供默认是非系统组件客户自定义组
 * @constructor 返回组件的href
 */
function href(pid, isSystem) {

    if(typeof pid != 'string') pid=pid.toString();

    if (isSystem == undefined)
        isSystem = false;

    return utils.getHref(utils.getLemsunResource(pid, isSystem));
}
var 组件转换路径 = href;

/**
 * 提供web组件的简化显示
 * @param pid 组件id, 不区分大小写. 可以输入组件末尾编码
 * 可选参数对象:如果该组件有开始参数且需要覆盖,可以设置可选参数
 * @return {string} 返回web组件内容
 */
function show(pid) {
    var content = '', lmsResource = null;
    if(typeof pid != 'string') pid=pid.toString();

    var p = {};

    pid = utils.parsePid(pid,false);

    if (!utils.isResourceId(pid))
        return content = '组件号码错误!';

    if (scope.resource.pid == pid)
        return content = 'show函数不能嵌套当前组件';

    lmsResource = utils.getLemsunResource(pid, isSystem);

    if (lmsResource != null && lmsResource.category == 'WEBSKIN') {
        var webPageResourceService = utils.getBeanByType(com.lemsun.client.core.service.IWebPageResourceService);
        var viewService = utils.getBeanByType(com.lemsun.client.core.service.IViewService);
        var webPageResource = webPageResourceService.getWebPageResource(pid);

        var view = viewService.getView(webPageResource, null);

        //如果有第二个参数
        if(arguments.length > 1 && typeof arguments[1] == 'object'){
            var arg=arguments[1];
            for(var temp in arg){
                var param=scope.getParam();
                 if(param.containsKey(name)){
                     var  target = param.get(name);
                     target.setValue(arg[temp]);
                 }
            }
        }
        content = view.renderContext(scope.getParam(), scope, scope.getRequest(), scope.getResponse());

    } else if (lmsResource != null && lmsResource.category == 'IMAGE') {
        content = '<img title="image" src="/' + lmsResource.pid + '">';
    }
    else {
        content = pid + '组件不是web组件或者资源组件';
    }

    return content;
}

/**
 * 把获取的表数据转换成<ul><li><a href='?'>?</a></li></ul>的列�
 * 通常用在新闻列表�
 * @param t 通过执行公式获取的表格数�这里执行的公式只能返回前两列数据,�
 * 第一列表示href链接编码,第二列表示显示的标题内容
 * @param pid 处理新闻内容的组�
 * @return {string}
 */
function showlist(t, pid) {
    if (t.data != undefined) {
        pid = href(pid);
        writer.write('<ul>');
        for (var i = 0, j = 0; i < t.getRowCount(); i++, j++) {
            var row = t.data.get(i);
            writer.write('<li>');
            writer.write("<a href='/" + pid + "?id=" + row.get(0) + "'>");
            writer.write(row.get(1));
            writer.write("</a>");
            writer.write('</li>');
        }
        writer.write("</ul>");
    }
    return '';
}

/**
 *
 * @return {String}
 */
function getformtoken() {
    return request.getSession().getAttribute("page_token");
}

/**
 * 焦煤住房公积金专用函�
 * 登录函数
 */
function logon(gjdw, cid, pwd) {
    //根据员工归集单位选择不同的数据源
    var sqlRunnerService = utils.getBeanByType(com.lemsun.client.core.service.ISqlRunnerService);
    var dpwd = sqlRunnerService.select(gjdw, "select pass from SYSTEM_BS_JOBCODE where code='" + cid + "'");
    if (dpwd != null) {
        if (dpwd.data.get(0).get(0) == pwd) {
            request.getSession().setAttribute('gjdw', gjdw);
            request.getSession().setAttribute('cid', cid);
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}
/**
 * 执行sql语句
 * @param pid
 * @param sql
 * @return {*}
 */
function excuteSql(pid, name, email, message) {
    var sqlRunnerService = utils.getBeanByType(com.lemsun.client.core.service.ISqlRunnerService);
    var sql = "INSERT INTO C000000444_XX (D, E, F) VALUES ( '" + name + "', '" + email + "', '" + message + "')";
    var resoult = sqlRunnerService.excute(pid, sql);
    return resoult;
}

function signout() {
    request.getSession().removeAttribute("gjdw");
    request.getSession().removeAttribute('cid');
    response.sendRedirect('/login');
}

/**
 * 根据数据源组件执行sql语句返回查询结果
 * @param gjdw 数据源组件编
 * @param sql  查询的sql语句
 * @return 查询的表格二维数
 */
function getSql(gjdw, sql) {
    var sqlRunnerService = utils.getBeanByType(com.lemsun.client.core.service.ISqlRunnerService);
    var info = sqlRunnerService.select(gjdw, sql);
    return info;
}

/**
 * 创建表格
 * @param t
 */
function showtable(t) {
    writer.write('<tbody>');
    for (var i = 0; i < t.getRowCount(); i++) {
        var r = t.data.get(i);
        writer.write('<tr>');
        for (var j = 0; j < t.getColCount(); j++) {
            writer.write('<td bgcolor="#EEF7FE" class="style1">' + r.get(j) + '</td>')
        }
        writer.write('</tr>')
    }
    writer.write('</tbody>');
    return '';
}

