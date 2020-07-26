package com.datanew.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static String oracleDriverClassName = "oracle.jdbc.driver.OracleDriver";
    private static String mysqlDriverClassName = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    public static Connection getConnectionByDBType(String dbType,String url,String username,String password) throws Exception{
        String driverClassName = null;
        if("oracle".equalsIgnoreCase(dbType)){
            driverClassName = oracleDriverClassName;
        }else if("mysql".equalsIgnoreCase(dbType)){
            driverClassName = mysqlDriverClassName;
        }
        return getConnection(driverClassName,url,username,password);
    }

    public static Connection getConnection(String driverClassName,String url,String username,String password) throws Exception{
//        try {
            Class.forName(driverClassName);
            Properties props =new Properties();
            props.put("remarksReporting","true");
            props.put("user", username);
            props.put("password", password);
            conn = DriverManager.getConnection(url,props);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return conn;
    }
}
