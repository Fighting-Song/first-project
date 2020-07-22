package com.songxuguang.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
out.print("        <!DOCTYPE html>");
out.print("<html>");
out.print("	<head>");
out.print("		<meta charset='utf-8'>");
out.print("		<title>添加测试</title>");
out.print("	</head>");
out.print("	<body>");
out.print("		<font color='#0000FF'>请输入您要添加的数据</font><br>");
out.print(	"	<form action='http://localhost:8080/myWeb2/addsubmit' method='post'>");
out.print("                员工id<input type='text' name='id1'></input><br>");
out.print("                员工名字<input type='text' name='username1'></input><br>");
out.print("                密码<input type='text' name='password1'></input>");
out.print("		<input type='submit' name='submit' value='提交'/>");
out.print("		</form>");
out.print("	</body>");
out.print("</html>");
    }
}
