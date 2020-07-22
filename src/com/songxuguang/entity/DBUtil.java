package com.songxuguang.entity;
import java.sql.*;
/*
 * JDBC工具类,简化JDBC编程
 * */
public class DBUtil {
    /*
     * 工具类中的构造方法都是私有的,
     * 因为工具类中的方法都是静态的,不需要new对象,直接采用类名调用
     * */
    //放在静态代码块中,静态代码快只在类时执行,并且只执行一次
    private  DBUtil(){}
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //这里要扔出异常,因为外面释放资源要catch
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false", "root", "1207071819");

        return conn;
    }

    //关闭资源
    /*
     * conn 连接对象
     * ps 数据操作对象
     * rs 结果集
     * */
    public static void close(Connection conn, Statement ps, ResultSet rs) throws SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
        /*开启事务*/
        public static void beginTransaction (Connection conn) throws SQLException {
            if (conn != null) {
                conn.setAutoCommit(false);
            }
        }

        /*提交事务*/
        public static void commitTransaction(Connection conn) throws SQLException{
            if (conn!=null) {
                conn.commit();
            }
        }
        /*回滚事务*/
        public static void rollbackTransaction(Connection conn){
            if(conn!=null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}