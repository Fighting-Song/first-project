package com.songxuguang.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddSubmitServlet")
public class AddSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String age=request.getParameter("age");
        String sex=request.getParameter("sex");
        String address=request.getParameter("address");
        String tel=request.getParameter("tel");

        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();*/

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        boolean addsuccess=false;
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                    "root",
                    "1207071819");
            conn.setAutoCommit(false);
            //3.获取预编译的数据库操作对象
            String sql="insert into employee(id,name,age,sex,address,tel) values(?,?,?,?,?,?) ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,age);
            ps.setString(4,sex);
            ps.setString(5,address);
            ps.setString(6,tel);
            count=ps.executeUpdate();
            //4.执行sql语句
            //System.out.println(count==1?"修改成功":"修改失败");
            //out.print(count==1?"修改成功":"修改失败");



            //程序能走到这里说明以上没有异常,事务结束,手动提交数据
            conn.commit();//----------------------------------------------手动提交

        } catch (Exception e) {
            if (ps != null) {
                try {
                    conn.rollback();//------------------------------------如果出现一场,回滚事务,保证数据安全
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {//释放资源
            if(rs!=null){
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
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/select");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
