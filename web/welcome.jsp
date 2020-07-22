<%@ page import="com.songxuguang.entity.Merchandise" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>乐佩防脱洗发水销售管理系统</title>
    <style type="text/css">
        .myposition{
            position: relative;
            left: 100px;
        }
    </style>
    <script type="text/javascript" src="jquery/jquery-3.50.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function select() {
        location.href="http://localhost:8080/myWeb2/employee";
    }
</script>
<%--<script type="text/javascript">
    function addSP() {
        location.href="http://localhost:8080/myWeb2/addSP";
    }
</script>--%>

<script type="text/javascript">
    function chakan() {
        location.href="<%=request.getContextPath()%>/select";
    }
</script>

<%--
<button type="button" class="btn btn-danger"data-toggle="modal" data-target="#delModal">
    <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>删除
</button>
--%>



<!-- 添加员工信息窗口 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!-- 头 -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增员工信息</h4>
            </div>
            <!--体 -->
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/addsubmit" method="post">
                    <div class="form-group">
                        <label for="brand">id</label>
                        <input type="text" name="id" class="form-control" id="brand" placeholder="请输入id">
                    </div>
                    <div class="form-group">
                        <label for="price">名字</label>
                        <input type="text" name="name" class="form-control" id="price" placeholder="请输入名字">
                    </div>
                    <div class="form-group">
                        <label for="capacity">年龄</label>
                        <input type="text" name="age" class="form-control" id="capacity" placeholder="请输入年龄">
                    </div>
                    <div class="form-group">
                        <label for="code">性别</label>
                        <input type="text" name="sex" class="form-control" id="code" placeholder="请输入性别">
                    </div>
                    <div class="form-group">
                        <label for="number">家庭住址</label>
                        <input type="text" name="address" class="form-control" id="number" placeholder="请输入家庭住址">
                    </div>
                    <div class="form-group">
                        <label for="number">电话号码</label>
                        <input type="text" name="tel" class="form-control" id="number" placeholder="请输入电话号码">
                    </div>
                    <div>
                        <input type="submit" value="提交">
                    </div>

                </form>
            </div>
            <!-- 脚-->
            <%--<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">更新</button>
            </div>--%>
        </div>
    </div>
</div>

<!-- 新增 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!-- 头 -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增产品</h4>
            </div>
            <!--体 -->
            <div class="modal-body">
                <form action="http://localhost:8080/myWeb2/addSP" method="post">
                    <div class="form-group">
                        <label for="brand">id</label>
                        <input type="text" name="id" class="form-control" id="brand" placeholder="请输入品牌id">
                    </div>
                    <div class="form-group">
                        <label for="brand">品牌</label>
                        <input type="text" name="brand" class="form-control" id="brand" placeholder="请输入新增的品牌">
                    </div>
                    <div class="form-group">
                        <label for="price">价格</label>
                        <input type="text" name="price" class="form-control" id="price" placeholder="请输入商品的价格">
                    </div>
                    <div class="form-group">
                        <label for="capacity">容量</label>
                        <input type="text" name="capacity" class="form-control" id="capacity" placeholder="请输入商品的容量">
                    </div>
                    <div class="form-group">
                        <label for="code">编码</label>
                        <input type="text"  name="coding" class="form-control" id="code" placeholder="请输入商品编码">
                    </div>
                    <div class="form-group">
                        <label for="number">库存数量</label>
                        <input type="text" name="repertory" class="form-control" id="number" placeholder="请输入库存数量">
                    </div>
                    <div>
                        <input type="submit" value="submit">
                    </div>
                </form>

            </div>
            <!-- 脚-->

        </div>
    </div>
</div>

<div>
    <div class="myposition">
        <h1>乐佩防脱洗发水销售管理系统<small>防脱发,选乐配</small></h1>
    </div>

    <!-- 按钮 -->
    <div class="myposition">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal"onclick="chakan()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>查看所有商品信息
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal" >
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增商品
        </button>
        <button type="button" class="btn btn-primary"data-toggle="modal" data-target="#editModal">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>添加员工信息
        </button>
        <%--<button type="button" class="btn btn-danger"data-toggle="modal" data-target="#delModal">
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>删除
        </button>--%>
        <button type="button" class="btn btn-default" onclick="select()">
            <span class="glyphicon glyphicon-import" aria-hidden="true"></span>员工信息
        </button>
        <%--<button type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出选中
        </button>
        <button type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出所有
        </button>--%>
    </div>
    <!-- 表格 -->
    <div class="myposition" style="top: 30px;">
        <table class="table table-striped">
            <tr>
                <th><input type="checkbox"></th>
                <th>序号</th>
                <th>品牌</th>
                <th>价格</th>
                <th>容量</th>
                <th>编码</th>
                <th>库存数量</th>
            </tr>
            <%--<tr>
                <td><input type="checkbox"/></td>
                <td>1</td>
                <td>海飞丝</td>
                <td>25</td>
                <td>400ml</td>
                <td>162365465</td>
                <td>200</td>

            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>1</td>
                <td>乐佩</td>
                <td>25</td>
                <td>200ml</td>
                <td>123695465</td>
                <td>200</td>

            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>1</td>
                <td>霸王</td>
                <td>50</td>
                <td>100ml</td>
                <td>125465465</td>
                <td>203</td>

            </tr>--%>
        </table>
    </div>

    <!-- 翻页组件 -->
    <div class="myposition" style="top: 30px;">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="disabled">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

</body>
</html>
