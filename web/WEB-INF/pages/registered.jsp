<%--
  Created by IntelliJ IDEA.
  User: asus1
  Date: 2020/12/9
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href="${pageContext.request.contextPath}/jsp/css/registered.css" rel="stylesheet">
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<FORM action="${pageContext.request.contextPath}/userServlet?method=registered" method=post>
<div class="content">
    <div class="login">
        <ul>
            <li class="title">注册</li>
        </ul>
        <ul>
            <li class="front">用户名</li>
            <li class="queen"><input type="text" placeholder="输入用户名" name="name" required></li>
        </ul>
        <ul>
            <li class="front">密码</li>
            <li class="queen"><input type="password" placeholder="输入用户密码" name="password" required></li>
        </ul>
        <ul>
            <li class="front">姓名</li>
            <li class="queen"><input type="text" placeholder="输入拥护姓名" name="truename" required></li>
        </ul>
        <ul>
            <li class="front">电话号码</li>
            <li class="queen"><input type="text" placeholder="输入用户电话号码" name="phone"></li>
        </ul>
        <ul>
            <li class="front">地址</li>
            <li class="queen"><input type="text" placeholder="输入用户地址" name="address"></li>
        </ul>
        <ul>
            <button type="submit" class="registered" >注册</button>
            <button type="reset" class="reset">重置</button>
        </ul>
    </div>
</div>
</FORM>
<%@ include file="/commons/foot.jsp"%>
</body>
</html>
