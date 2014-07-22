package com.lemsun.data.viproject.repository;

import com.lemsun.data.viproject.FourTableGroup;
import com.lemsun.data.viproject.util.FourCommon;
import com.lemsun.data.viproject.util.ReportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FourTableGroupRepository {
    private static final Logger log = LoggerFactory.getLogger(FourTableGroupRepository.class);
    /**
     * 获取目录下的表组
     * @param jdbcTemplate
     * @param tableName
     * @return
     */
    public List<FourTableGroup> getFourTableGroupByIndex(JdbcTemplate jdbcTemplate,String tableName)
    {
        try{
            String sqlindex="SELECT sr.A1, rt.A4, rt.A9,rt.A10, rt.A14,rt.A26, rt.B3, rt.B2 FROM "+ tableName+" rt,SYSTEM_REPORTS sr WHERE sr.B3=rt.B2 AND sr.A9!=?";
            return jdbcTemplate.query(sqlindex,new Object[]{ReportType.Index}, new FourTableGroupRowMapper() );
        }catch (Exception ex){
            return new ArrayList<>();
        }
    }

    /**
     * 获取4代下的所有表组
     * @param jdbcTemplate
     * @return
     */
    public List<FourTableGroup> getAllFourTableGroup(JdbcTemplate jdbcTemplate) {
        String sql="SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS WHERE A9!="+ReportType.None+" AND A9!="+ReportType.System+" AND A9!="+ReportType.Index;
        List<FourTableGroup> fourTableGroups= jdbcTemplate.query(sql, new FourTableGroupRowMapper());
        return fourTableGroups;
    }

    /**
     * 获取表组
     * @param jdbcTemplate
     * @param code
     * @return
     */
    public FourTableGroup getFourTableGroupBycode(JdbcTemplate jdbcTemplate, String code)
    {
        String sql="SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS WHERE A1=?";
        List<FourTableGroup> fourTableGroups= jdbcTemplate.query(sql,
                new Object[]{
                        code
                },
                new FourTableGroupRowMapper());
        return fourTableGroups.size()==0?null:fourTableGroups.get(0);
    }



    class FourTableGroupRowMapper implements RowMapper
    {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            if(resultSet==null)return null;
            FourTableGroup fourTableGroup=new FourTableGroup();
            fourTableGroup.setCode(resultSet.getString("A1"));
            fourTableGroup.setAccountSymbol(resultSet.getString("A4"));
            fourTableGroup.setCategory(resultSet.getInt("A9"));
            fourTableGroup.setNameFormat(resultSet.getString("A10"));
            fourTableGroup.setName(resultSet.getString("A14"));
            fourTableGroup.setHangingSheet(resultSet.getString("A26"));
//            navigate.setSkinSolution(resultSet.getString("A40"));
            fourTableGroup.setBooksGuid(resultSet.getString("B2"));
            fourTableGroup.setReportGuid(resultSet.getString("B3"));
            return fourTableGroup;
        }
    }
}
