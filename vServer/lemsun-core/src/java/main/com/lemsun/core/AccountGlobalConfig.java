package com.lemsun.core;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 用户设的全局变量, 提供给用户使用的默认参数, 或者配置信息等 <br />
 * User: 宗旭东
 * Date: 13-3-6
 * Time: 下午2:44
 */
public class AccountGlobalConfig {

    private Date defaultActionDate = new Date();
    private String accountCode;
    private static final Logger log = LoggerFactory.getLogger(AccountGlobalConfig.class);

    /**
     * 获取账号操作的默认时间RecordTable
     * @return 默认时间
     */
    public Date getDefaultActionDate() {
        return defaultActionDate;
    }

    /**
     * 修改账号的默认时间
     * @param defaultActionDate 默认时间
     */
    public void setDefaultActionDate(Date defaultActionDate) {
        this.defaultActionDate = defaultActionDate;

        if(log.isInfoEnabled()) {
            log.info("用户的默认操作日期发生改变: " + DateFormatUtils.format(defaultActionDate, "yyyyMMdd"));
        }

    }

    /**
     * 获取账号操作的帐套代码
     * @return 帐套代码
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 修改账号操作的帐套代码
     * @param accountCode 帐套代码
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
