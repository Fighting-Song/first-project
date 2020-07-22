package com.songxuguang.javaweb.servlet;

import com.songxuguang.entity.DBUtil;
import com.songxuguang.entity.Merchandise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "deleteServlet")
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取删除的客户id
        String id=request.getParameter("id");
        //连接数据库删除数据
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;
        try{
            conn=DBUtil.getConnection();
            DBUtil.beginTransaction(conn);

            String sql="delete from merchandise where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            count=ps.executeUpdate();
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
}
