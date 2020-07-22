package com.songxuguang.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CheckLoginStatusServlet")
public class CheckLoginStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从Cookie中获取所有的Cookie
        Cookie[] cookies=request.getCookies();
        String username=null;
        String password=null;
        if(cookies!=null){
            //遍历Cookie
            for(Cookie cookie:cookies){
                String cookieName=cookie.getName();
                String cookieValue=cookie.getValue();
                if("username".equals(cookieName)){
                    username=cookieValue;
                }else if("password".equals(cookieName)){
                    password=cookieValue;
                }
            }
        }
        if(username !=null&&password!=null){
            //连接数据库JDBC验证用户名和密码
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            boolean loginsuccess=false;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                        "root",
                        "1207071819");
                conn.setAutoCommit(false);
                String sql="select * from t_user where username=? and password=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                rs=ps.executeQuery();
                if(rs.next()){
                    loginsuccess=true;
                }
                conn.commit();
            }catch (Exception e){
                if(conn!=null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            }finally {
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(loginsuccess){
                //登陆成功跳转到成功页面
                response.sendRedirect(request.getContextPath()+"/welcome.jsp");
            }else{
                //登陆失败跳转到失败页面
                response.sendRedirect(request.getContextPath()+"/login_error.html");
            }

        }else{
            //跳转到登陆页面
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
    }
}
