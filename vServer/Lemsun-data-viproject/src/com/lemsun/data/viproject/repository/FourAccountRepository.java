package com.lemsun.data.viproject.repository;

import com.lemsun.data.viproject.FourAccount;
import com.lemsun.data.viproject.util.ReportType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FourAccountRepository {
    private static final Logger log = LoggerFactory.getLogger(FourAccountRepository.class);
    /**
     * 得到帐套
     * @param jdbcTemplate
     * @return  帐套
     */
    public List<FourAccount> getFourAccount(JdbcTemplate jdbcTemplate,String accountname)
    {
        StringBuilder sql=new StringBuilder("SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS sr WHERE  A9 = "+ ReportType.Account+" ");
        ArrayList<Object> parms=new ArrayList<>();
        if(!StringUtils.isEmpty(accountname)){
            sql.append("AND A14 like ? ");
            parms.add("%"+accountname+"%");
        }
        sql.append(" AND PATINDEX('%.%',A1)=0  ORDER BY A1");

        List< FourAccount> viNavigates =  jdbcTemplate.query(sql.toString(),parms.toArray(),new FourAccountRowMapper());
        return viNavigates;
    }

    public FourAccount getFourAccountBycode(JdbcTemplate jdbcTemplate, String code)
    {
        String sql="SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS WHERE A1=?";
        List<FourAccount> viNavigates= jdbcTemplate.query(sql,
                new Object[]{
                        code
                },
                new FourAccountRowMapper());
        return viNavigates.size()==0?null:viNavigates.get(0);
    }




    class FourAccountRowMapper implements RowMapper
    {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            if(resultSet==null)return null;
            FourAccount fourAccount=new FourAccount();
            fourAccount.setCode(resultSet.getString("A1"));
            fourAccount.setAccountSymbol(resultSet.getString("A4"));
            fourAccount.setCategory(resultSet.getInt("A9"));
            fourAccount.setNameFormat(resultSet.getString("A10"));
            fourAccount.setName(resultSet.getString("A14"));
            fourAccount.setHangingSheet(resultSet.getString("A26"));
//            navigate.setSkinSolution(resultSet.getString("A40"));
            fourAccount.setBooksGuid(resultSet.getString("B2"));
            fourAccount.setReportGuid(resultSet.getString("B3"));
            return fourAccount;
        }
    }
}
