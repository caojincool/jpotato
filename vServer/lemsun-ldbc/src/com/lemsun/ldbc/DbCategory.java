package com.lemsun.ldbc;

/**
 * 关系型数据库类型
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:12
 */
public enum DbCategory {

	//TODO 完善数据库驱动类
	SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver", "sqlserver"),
	MYSQL("", "mysql"),
	ORCALE("", "orcale"),
	DB2("", "db2");

    public static DbCategory getDbcategory(String name)
    {
        DbCategory dbCategory=null;
        switch (name)
        {
             case "SQLSERVER":
                 dbCategory=DbCategory.SQLSERVER;
                 break;
            case "MYSQL":
                dbCategory=DbCategory.MYSQL;
                break;
            case "ORCALE":
                dbCategory=DbCategory.ORCALE;
                break;
            case "DB2":
                dbCategory=DbCategory.DB2;
                break;
        }
        return dbCategory;
    }


	DbCategory(String driverClassName, String name)
	{
		this.driverClassName = driverClassName;
        this.name = name;
	}

	private String driverClassName;
    private String name;


    /**
     * 获取驱动的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取驱动类的名称
     */
    public String getDriverClassName() {
		return driverClassName;
	}
}
