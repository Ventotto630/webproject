package com.dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
public interface Basedao {
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xiangzy_db_uni");
        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("“Ï≥££∫" + e);
        }
        return dataSource;
    }
    public default Connection getConnection() {
        DataSource dataSource = getDataSource();
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("“Ï≥££∫" + sqle);
        }
        return conn;
    }
}