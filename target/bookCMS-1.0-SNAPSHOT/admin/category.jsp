<%--
  Created by IntelliJ IDEA.
  User: ymkj
  Date: 2019/7/11
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>分类列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css">
</head>

<body>
<header>
    <div class="container">

        <nav>
            <a href="" >Java</a>
        </nav>
        <nav>
            <a href="" >前端</a>
        </nav>
        <nav>
            <a href="${pageContext.request.contextPath}/admin/Category/toAdd.do" >分类</a>
        </nav>

    </div>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>图书</h1>
            <p>图书分类列表</p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>分类</th>
                <th>创建时间</th>
                <th>最后修改时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.category.name}</td>
                    <td>${book.createTime}</td>
                    <td>${book.updateTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="${pageContext.request.contextPath}/admin/Book/toAdd.do"><button>新建</button></a>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
</html>