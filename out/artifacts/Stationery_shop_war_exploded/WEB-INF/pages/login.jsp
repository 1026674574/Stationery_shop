<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆</title>
    <link href="${pageContext.request.contextPath}/jsp/css/login.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<FORM action=${pageContext.request.contextPath}/userServlet?method=login method=post>
<div class="content">
    <div class="login">
        <ul>
            <li class="title">登陆</li>
        </ul>
        <ul>
            <li class="front">用户名</li>
            <li class="queen">
                <input type="name" placeholder="输入用户名"></li>
        </ul>
        <ul>
            <li class="front">密码</li>
            <li class="queen">
                <input type="password" placeholder="输入用户密码"></li>
        </ul>
        <ul>
            <li class="registered"><a href="registered.html">注册</a></li>
            <li class="forget"><a href="#">忘记密码</a></li>
        </ul>
        <ul>
            <li><input type="submit" value=登陆></li>
        </ul>
    </div>
</div>
</FORM>
<%@ include file="/commons/foot.jsp"%>
</body>
</html>
<%--<%@ include file="/commons/top.jsp"%>--%>
<%--<body>--%>
<%--<div class="above">--%>
<%--    <div class="logo"></div>--%>
<%--    <div class="search">--%>
<%--        <input type="text" size="70" name="search" class="search1">--%>
<%--        <input type="submit" name="submit" value="搜索" class="submit">--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="content">--%>
<%--    <c:forEach items="${shoppage.list}" var="shop">--%>
<%--        <div class="col-sm-6 col-md-4">--%>
<%--            <a href="computerServlet?method=getComputer&pageNo=${shoppage.pageNo }&id=${shop.sh_id}" >--%>
<%--                <div class="thumbnail">--%>
<%--                    <img src="${shop.sh_picpth}" alt="...">--%>
<%--                    <div class="caption">--%>
<%--                        <h3 style="color:black;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${shop.sh_id }&nbsp;${shop.sh_idl}</h3>--%>
<%--                        <p class="prcieNum">￥ ${shop.sh_id }</p>--%>
<%--                        <p>--%>
<%--                                &lt;%&ndash;                            <a href="computerServlet?method=addToCart&pageNo=${computerpage.pageNo }&id=${computer.id}&model=${computer.model}&brand=${computer.brand}" class="btn btn-primary addToCart" role="button"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 加入购物车</a>&ndash;%&gt;--%>
<%--                                &lt;%&ndash;                            <a href="#" class="btn btn-default" role="button"> <span class="glyphicon glyphicon-star" aria-hidden="true" style="color:#FF8F1C;"></span> 收藏</a>&ndash;%&gt;--%>
<%--                        </p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </a>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <a href="particulars.html"><div class="commodity ">商品</div></a>--%>
<%--</div>--%>
<%--</body>--%>
