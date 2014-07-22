package com.lemsun.data.viproject.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-11
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class UnitDataSource {
    private String clientName;
    private String dataSource;
    private String dataBase;
    private String userId;
    private String password;
    private String cHName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcHName() {
        return cHName;
    }

    public void setcHName(String cHName) {
        this.cHName = cHName;
    }


    public static UnitDataSource getUnitDataSourceByJdbc(JdbcTemplate jdbcTemplate,String clientName)
    {
        String sql="SELECT A2 FROM SYSTEM_SETTINGS WHERE A1 = 'UnitDataSource'";
        List<String> datastr= jdbcTemplate.query(sql,new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("A2");
            }
        });
        if(datastr==null||datastr.size()==0)return null;
        String[] dataSourceSp= datastr.get(0).split("datastr");
        for (String dsp:dataSourceSp)
        {
            String[] sdatasource=dsp.split("□※№");
            if (sdatasource.length==6)
            {
                if(clientName.equals(sdatasource[0])){
                    UnitDataSource unitDataSource=new UnitDataSource();
                    unitDataSource.setClientName(sdatasource[0]);
                    unitDataSource.setDataSource(sdatasource[1]);
                    unitDataSource.setDataBase(sdatasource[2]);
                    unitDataSource.setUserId(sdatasource[3]);
                    unitDataSource.setPassword(sdatasource[4]);
                    unitDataSource.setcHName(sdatasource[5]);
                    return unitDataSource;
                }
            }
        }
        return null;
    }

    public BasicDataSource getBasicDataSourc()
    {
        BasicDataSource basicDataSource=new BasicDataSource();
        String[] linkip=getDataSource().split(":");
        if(linkip.length==1)setDataSource(getDataSource()+":1433");
        String connstr="jdbc:sqlserver://"+getDataSource()+"; DatabaseName="+getDataBase();
        basicDataSource.setUrl(connstr);
        basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        basicDataSource.setUsername(getUserId());
        basicDataSource.setPassword(getPassword());
        return basicDataSource;
    }
}
