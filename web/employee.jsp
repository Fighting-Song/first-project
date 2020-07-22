<%@ page import="com.songxuguang.entity.Merchandise" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.songxuguang.entity.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>员工信息</title>
    <style type="text/css">
        .myposition{
            position: relative;
            left: 500px;
        }
    </style>
</head>
<body>

<h3 align='center'>员工列表</h3>
<hr width='60%'>"
<table border='1' align='center' width='50%' class='table table-hover'>
    <tr align='center'>
        <th><input type="checkbox"></th>
        <th>id</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>家庭住址</th>
        <th>电话号码</th>
        <th>删除</th>
    </tr>
        <%
            List<Employee> employeeList=(List<Employee>)request.getAttribute("employeeList");
            int id=0;
            for(Employee e:employeeList){
        %>
    <tr>
        <th><input type="checkbox" name="answer"></th>
        <td><%=(++id)%></td>
        <td><%=e.getName()%></td>
        <td><%=e.getAge()%></td>
        <td><%=e.getSex()%></td>
        <td><%=e.getAddress()%></td>
        <td><%=e.getTel()%></td>
        <%--<td>
            <a href ="<%=request.getContextPath()%>/deleteE?id=<%=e.getId()%>">删除</a>
        </td>--%>
        <td><input type="button" name="delete" value="删除" onclick="checkAllBox1()"formmethod="post"></td>
    </tr>
    <script type="text/javascript">
        function checkAllBox1() {
            var answer = document.getElementsByName("answer");
            for(var i = 0;i < answer.length;i++)
            {
                if (answer[i].checked == true) {
                    location.href = "<%=request.getContextPath()%>/deleteE?id=<%=e.getId()%>"
                }
            }
        }
    </script>
        <%
            }
        %>
    <div class="myposition">
    <a href="<%=request.getContextPath()%>/select" >返回主页面</a>
    </div>

</body>
</html>