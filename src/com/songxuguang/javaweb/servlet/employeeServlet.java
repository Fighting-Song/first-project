package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.DBUtil;
import com.songxuguang.entity.Employee;
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
public class employeeServlet extends HttpServlet {
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
        List<Employee> employeeList=new ArrayList<Employee>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                    "root",
                    "1207071819");
            String sql="select id,name,age,sex,address,tel from employee";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String id=rs.getString("id");
                String name=rs.getString("name");
                String age=rs.getString("age");
                String sex=rs.getString("sex");
                String address=rs.getString("address");
                String tel=rs.getString("tel");

                Employee employee=new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setAge(age);
                employee.setSex(sex);
                employee.setAddress(address);
                employee.setTel(tel);

                employeeList.add(employee);
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
        request.setAttribute("employeeList",employeeList);
        request.getRequestDispatcher("/employee.jsp").forward(request,response);
    }
}


/*package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.DBUtil;
import com.songxuguang.entity.Merchandise;
import com.songxuguang.entity.User;

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
public class employeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print( "<!DOCTYPE html>");
        out.print( "<html lang='en'>");
        out.print( "<head>");
        out.print( "<meta charset='UTF-8'>");
        out.print( "<title>员工信息</title>");
        out.print( "</head>");
        out.print( "<body>");
        out.print( "<h3 align='center'>员工列表</h3>");
        out.print( "<hr width='60%'>");
        out.print( "<table border='1' align='center' width='50%' class='table table-hover'>");
        out.print(       "<tr align='center'>");
        out.print(      "<th>id</th>");
        out.print(      "<th>姓名</th>");
        out.print(      "<th>年龄</th>");
        out.print(      "<th>性别</th>");
        out.print(      "<th>家庭住址</th>");
        out.print(      "<th>电话号码</th>");
        out.print(      "<th>操作</th>");
        out.print(      "</tr>");
        *//*out.print( "<tr align='center'>");
        out.print( "<th>123</th>");
        out.print( "<th>宋</th>");
        out.print( "</tr>");*//*
        //JDBC
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int i=0;
        List<Merchandise> merchandiseList=new ArrayList<Merchandise>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctext?useSSL=false",
                    "root",
                    "1207071819");
            String sql="select id,name,age,sex,address,tel from employee";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String id=rs.getString("id");
                String name=rs.getString("name");
                String age=rs.getString("age");
                String sex=rs.getString("sex");
                String address=rs.getString("address");
                String tel=rs.getString("tel");
                out.print( "</tr align='center'>");
                out.print( "<td>"+(++i)+"</td>");
                out.print( "<td>"+name+"</td>");
                out.print( "<td>"+age+"</td>");
                out.print( "<td>"+sex+"</td>");
                out.print( "<td>"+address+"</td>");
                out.print( "<td>"+tel+"</td>");
                out.print( "<td><a href='http://localhost:8080/myWeb2/add'>添加</a></td>");
                out.print( "</tr>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            *//*if (rs != null) {
                try {
                    DBUtil.close(conn, ps, rs);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*//*
            try {
                DBUtil.close(conn,ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
               *//* try {
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
            }*//*
               out.print("</table>");
            out.print("</body>");
            out.print("</html>");
        //转发
       *//* request.setAttribute("merchandiseList",merchandiseList);
        request.getRequestDispatcher("/welcome-chakan.jsp").forward(request,response);*//*
    }
}*/
