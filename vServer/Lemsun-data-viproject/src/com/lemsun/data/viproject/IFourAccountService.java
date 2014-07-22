package com.lemsun.data.viproject;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 4代帐套操作类
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:28
 */
public interface IFourAccountService {

    /**
     * 获取4代帐套
     * @param jdbcTemplate
     * @param accountname 根据名称的查询条件,没有测查询所有帐套
     * @return
     */
    List<FourAccount> getFourAccount(JdbcTemplate jdbcTemplate, String accountname);

    /**
     * 根据CODE获取帐套
     * @param jdbcTemplate
     * @param code
     * @return
     */
    FourAccount getFourAccountBycode(JdbcTemplate jdbcTemplate, String code);
}
