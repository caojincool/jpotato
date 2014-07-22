package com.lemsun.data.viproject;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * 4代表组操作类
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface IFourTableGroupService {

    /**
     * 根据code获取 SYSTEM_REPORTS
     * @param jdbcTemplate
     * @param code
     * @return
     */
    FourTableGroup getVinavigateBycode(JdbcTemplate jdbcTemplate, String code);


    /**
     * 根据目录类型的帐套获取目录下的表组
     * @param jdbcTemplate
     * @param indexCode
     * @param date
     * @return
     */
    List<FourTableGroup> getFourTableGroupByIndex(JdbcTemplate jdbcTemplate, String indexCode, Date date);

    /**
     * 获取所有表组 (用于一次性导入)
     * @param jdbcTemplate
     * @return
     */
    List<FourTableGroup> getAllNvNavigate(JdbcTemplate jdbcTemplate);
}
