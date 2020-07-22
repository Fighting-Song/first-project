package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "addSPServlet")
public class addSPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取删除的客户id
        // @WebServlet(name = "AddSubmitServlet")
        // public class addSPServlet extends HttpServlet {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String brand = request.getParameter("brand");
        String price = request.getParameter("price");
        String capacity = request.getParameter("capacity");
        String coding = request.getParameter("coding");
        String repertory = request.getParameter("repertory");

       // response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //boolean addsuccess = false;
        int count=0;
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                    "root",
                    "1207071819");
            conn.setAutoCommit(false);
            //3.获取预编译的数据库操作对象
            String sql = "insert into merchandise(id,brand,price,capacity,coding,repertory) values(?,?,?,?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, brand);
            ps.setString(3, price);
            ps.setString(4, capacity);
            ps.setString(5, coding);
            ps.setString(6, repertory);
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
        } finally {//释放资源
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
        if (count == 1) {
            response.sendRedirect(request.getContextPath()+"/select");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            }
        }




       /* //连接数据库删除数据
        Connection conn=null;
        PreparedStatement ps=null;
        //ResultSet rs=null;
        //boolean addsuccess=false;
        int count=0;
        try{
            conn= DBUtil.getConnection();
            DBUtil.beginTransaction(conn);
            String sql="insert into merchandise values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            //ps.setString(1,id);
            ps.setString(1,id);
            ps.setString(2,brand);
            ps.setString(3,price);
            ps.setString(4,capacity);
            ps.setString(5,coding);
            ps.setString(6,repertory);
            count =ps.executeUpdate();

            DBUtil.commitTransaction(conn);
        }catch(Exception e){
            DBUtil.rollbackTransaction(conn);
            e.printStackTrace();
        }finally {
            try {
                DBUtil.close(conn,ps,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(count==1){
            //删除成功,再次跳转到显示客户页面
            //重定向到/select
            //request.getRequestDispatcher("/select").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/select");
        }
        }
    }*/

