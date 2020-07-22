package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.DBUtil;
import com.songxuguang.entity.Merchandise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SelectServlet")
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out=response.getWriter();
        /*out.print( "<!DOCTYPE html>");
        out.print( "<html lang='en'>");
        out.print( "<head>");
        out.print( "<meta charset='UTF-8'>");
        out.print( "<title>员工信息</title>");
        out.print( "</head>");
        out.print( "<body>");
        out.print( "<h3 align='center'>员工列表</h3>");
        out.print( "<hr width='60%'>");
        out.print( "<table border='1' align='center' width='50%'>");
        out.print( "<tr align='center'>");
        out.print( "<th>id</th>");
        out.print( "<th>loginName</th>");
        out.print( "<th>password</th>");
        out.print( "<th>操作</th>");
        out.print( "</tr>");*/
        /*out.print( "<tr align='center'>");
        out.print( "<th>123</th>");
        out.print( "<th>宋</th>");
        out.print( "</tr>");*/
        //JDBC
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Merchandise> merchandiseList=new ArrayList<Merchandise>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                    "root",
                    "1207071819");
            String sql="select id,brand,price,capacity,coding,repertory from merchandise";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String id=rs.getString("id");
                String brand=rs.getString("brand");
                String price=rs.getString("price");
                String capacity=rs.getString("capacity");
                String coding=rs.getString("coding");
                String repertory=rs.getString("repertory");

                Merchandise merchandise=new Merchandise();
                merchandise.setId(id);
                merchandise.setBrand(brand);
                merchandise.setPrice(price);
                merchandise.setCapacity(capacity);
                merchandise.setCoding(coding);
                merchandise.setRepertory(repertory);

                merchandiseList.add(merchandise);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            /*if (rs != null) {
                try {
                    DBUtil.close(conn, ps, rs);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/
            try {
                DBUtil.close(conn,ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
               /* try {
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
            }*/

        //转发
        request.setAttribute("merchandiseList",merchandiseList);
        request.getRequestDispatcher("/welcome-chakan.jsp").forward(request,response);
    }
}
