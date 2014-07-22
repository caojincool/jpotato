package com.lemsun.data.viproject.repository;

import com.lemsun.data.viproject.FourIndex;
import com.lemsun.data.viproject.util.FourCommon;
import com.lemsun.data.viproject.util.ReportType;
import com.lemsun.data.viproject.util.UnitDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FourIndexRepository {
    private static final Logger log = LoggerFactory.getLogger(FourIndexRepository.class);


    /**
     * 获取目录
     * @param jdbcTemplate
     * @param code
     * @return
     */
    public FourIndex getFourAccountBycode(JdbcTemplate jdbcTemplate, String code)
    {
        String sql="SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS WHERE A1=?";
        List<FourIndex> viNavigates= jdbcTemplate.query(sql,
                new Object[]{
                        code
                },
                new FourIndexRowMapper());
        return viNavigates.size()==0?null:viNavigates.get(0);
    }

    /**
     *
     * 加载帐套下的所有目录
     * @param jdbcTemplate
     * @param viNavigate
     * @param date
     * @return
     */
    public FourIndex getFourIndexByAccount(JdbcTemplate jdbcTemplate,FourIndex viNavigate,Date date)
    {
        String sqlsun="SELECT sr.A1, rt.A4, rt.A9,rt.A10, rt.A14,rt.A26, rt.B3, rt.B2 FROM R_"+viNavigate.getCode()+" rt,SYSTEM_REPORTS sr WHERE sr.B3=rt.B2 AND sr.A9=?";

        List<FourIndex> vsuns= jdbcTemplate.query(sqlsun,new Object[]{ReportType.Index}, new FourIndexRowMapper()
        );
        AddFourIndexChild(jdbcTemplate,vsuns,date);
        viNavigate.setChild(vsuns);
        return viNavigate;
    }

    /**
     * 根据B3（GUID）获取SYSTEM_REPORTS记录
     * @param jdbcTemplate
     * @param b3
     * @return
     */
    public FourIndex getFourIndexParentByA1(JdbcTemplate jdbcTemplate,String b3)
    {
        if(b3.lastIndexOf(".")==-1)return null;
        String sql="SELECT A1, A4, A9,A10, A14,A26,A40, B3, B2 FROM SYSTEM_REPORTS WHERE A1=?";
        List<FourIndex> viNavigates= jdbcTemplate.query(sql,
                new Object[]{
                        b3.substring(0,b3.lastIndexOf("."))
                },
                new FourIndexRowMapper());
        return viNavigates.size()==0?null:viNavigates.get(0);
    }


    //加载树的子节点
    private void AddFourIndexChild(JdbcTemplate jdbcTemplate,  List<FourIndex> fourIndexList,Date date) {
        for(FourIndex v:fourIndexList)
        {
            String datasource= FourCommon.getClientNameByA26(v.getAccountSymbol());
            if(!StringUtils.isEmpty(datasource))//外接表
            {
                UnitDataSource unitDataSource=UnitDataSource.getUnitDataSourceByJdbc(jdbcTemplate,datasource);
                if(unitDataSource!=null){
                    JdbcTemplate flowJdbcTemplate=new JdbcTemplate(unitDataSource.getBasicDataSourc());
                    //暂未处理
                    // loadViNavigateChild(flowJdbcTemplate,date,v);
                }

            }else if(v.getCategory()== ReportType.Index){//目录
                loadViNavigateChild(jdbcTemplate, date, v);
            }
        }
    }
    private void loadViNavigateChild(JdbcTemplate jdbcTemplate, Date date, FourIndex v) {
        try{
//            String a1byb3="SELECT A1 FROM SYSTEM_REPORTS WHERE B3=?";
//            List<String> a1list= jdbcTemplate.query(a1byb3,new Object[]{v.getBooksGuid()},new RowMapper<String>() {
//                @Override
//                public String mapRow(ResultSet resultSet, int i) throws SQLException {
//                    return resultSet.getString("A1");
//                }
//            });
//            if(a1list==null||a1list.size()==0)return;;
            String sqlindex="SELECT sr.A1, rt.A4, rt.A9,rt.A10, rt.A14,rt.A26, rt.B3, rt.B2 FROM "+FourCommon.getFourTableName(v.getCode() /*a1list.get(0)*/, date, v.getNameFormat())+" rt,SYSTEM_REPORTS sr WHERE sr.B3=rt.B2 AND sr.A9=?";
            List<FourIndex> vsunindex= jdbcTemplate.query(sqlindex,new Object[]{ReportType.Index}, new FourIndexRowMapper() );
            if(vsunindex!=null)v.setChild(vsunindex);
            AddFourIndexChild(jdbcTemplate, vsunindex,date);
        }catch (Exception ex)
        {
            // if(log.isDebugEnabled())log.debug("查询4代表目录树错误");
        }
    }


    class FourIndexRowMapper implements RowMapper
    {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            if(resultSet==null)return null;
            FourIndex fourIndex=new FourIndex();
            fourIndex.setCode(resultSet.getString("A1"));
            fourIndex.setAccountSymbol(resultSet.getString("A4"));
            fourIndex.setCategory(resultSet.getInt("A9"));
            fourIndex.setNameFormat(resultSet.getString("A10"));
            fourIndex.setName(resultSet.getString("A14"));
            fourIndex.setHangingSheet(resultSet.getString("A26"));
//            navigate.setSkinSolution(resultSet.getString("A40"));
            fourIndex.setBooksGuid(resultSet.getString("B2"));
            fourIndex.setReportGuid(resultSet.getString("B3"));
            return fourIndex;
        }
    }
}
