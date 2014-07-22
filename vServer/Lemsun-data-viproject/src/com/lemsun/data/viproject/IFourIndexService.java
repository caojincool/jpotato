package com.lemsun.data.viproject;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * 4代目录操作类
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
public interface IFourIndexService {

    /**
     * 根据帐套获取目录   (树型记录)
     * @param jdbcTemplate
     * @param accountCode
     * @param date
     * @return
     */
    FourIndex getFourIndexTree(JdbcTemplate jdbcTemplate, String accountCode, Date date);
}
