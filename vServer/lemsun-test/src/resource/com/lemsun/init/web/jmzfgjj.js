/**
 * 焦煤住房公积金查询系统函数库
 * User: dp
 * Date: 13-6-19
 * Time: 下午4:10
 */

/**
 * 焦煤住房公积金专用函数
 * 登录函数
 */
function logon(gjdw, cid, pwd) {
    //根据员工归集单位选择不同的数据源
    var sqlRunnerService = utils.getBeanByType(com.lemsun.client.core.service.ISqlRunnerService);
    var dpwd = sqlRunnerService.select(gjdw, "select pass from SYSTEM_BS_JOBCODE where code='" + cid + "'")
    if (dpwd != null) {
        if (dpwd == pwd) {
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
