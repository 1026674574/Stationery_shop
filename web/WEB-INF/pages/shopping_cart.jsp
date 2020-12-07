<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>购物车</title>
    <link href="${pageContext.request.contextPath}/jsp/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/jsp/css/shopping_cart.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/cartValidate.js"></script>
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<div class="content">
    <div class="title_nav">
        <ul>
            <li class="shopding_name">商品名</li>
            <li class="shopping_number">数量</li>
            <li class="shopping_price">价格</li>
            <li class="operation">操作</li>
        </ul>
    </div>
<c:forEach items = "${sessionScope.ShoppingCart.items }" var = "item">
    <div class="shopping">
        <div class="picture"><img src="${item.shop.sh_picpth}" alt="..."></div>
        <div class="describe">
            <ul>
                <li class="name">${item.shop.sh_name}</li>
                <li class="number">
                    <input  step="${item.quantity }" type="text" size="1" name="${item.shop.sh_id }" value="${item.quantity }" style="width:50px;height:30px;">
                </li>
                <li class="price">${item.shop.sh_price}</li>
                <li class="delete">
                    <a href="shopServlet?method=remove&pageNo=${param.pageNo}&id=${item.shop.sh_id}">删除</a>
                </li>
            </ul>
        </div>
    </div>
</c:forEach>
    <div id="totalMoney" style="font-weight:bold;">总金额：￥ ${sessionScope.ShoppingCart.totalMoney }</span>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-8"></div>
        <div class="col-xs-12 col-md-4 text-right" style="padding-left:30px;">
            <a href="shopServlet?method=getPage&pageNo=${param.pageNo }"class="btn btn-default" role="button">继续购物</a>
            <a href="shopServlet?method=clear" class="btn btn-danger" role="button">清空购物车</a>
            <a href="shopServlet?method=cash" class="btn btn-primary" role="button">结账</a>
        </div>
    </div>
</div>
</div>
<%@ include file="/commons/foot.jsp"%>
</body>
</html>
