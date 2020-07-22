package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");
        //获取用户名和密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //连接数据库验证用户名和密码
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean loginsuccess=false;
        //User user=null;
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
                /*//登陆成功,将用户信息包装到实体对象User当中
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));*/
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
        //登陆成功跳转成功页面,失败跳转失败页面
        if(loginsuccess){
            //登陆成功之后,获取用户是否选择了十天之内免登录
            String tenDayAutoLoginFlag=request.getParameter("tenDayAutoLoginFlag");
            if("ok".equals(tenDayAutoLoginFlag)) {
                //创建Cookie对象
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                //设置有效时间
                cookie1.setMaxAge(60 * 60);
                cookie2.setMaxAge(60 * 60);
                //设置关联路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                //发送Cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            /*//获取session对象
            HttpSession session=request.getSession();
            //将登陆成功的用户存储到会话范围
            session.setAttribute("user",user);
            //重定向*/
            response.sendRedirect(request.getContextPath()+"/welcome.jsp");
        }else{
            response.sendRedirect(request.getContextPath()+"/login_error.html");
        }
    }
}
