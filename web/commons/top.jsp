<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Title</title>
    <style type="text/css">
        div.top
        {
            height: 50px;
            /*border: 1px solid red;*/
        }
        div.nav
        {
            height: 20px;
            background: gainsboro;
        }
        div.nav ul li
        {
            float: left;
            list-style: none;
        }
        div.nav ul li a
        {
            font-size:13px; color: gray;
            display:block;
            width: 60px;
            outline:0;
            text-decoration: none;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="top">
    <div class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp" class="nav1">网站首页</a></li>
            <li class="nav2">欢迎进入本网站</li>
            <li><a href="shopServlet?method=forwardPage&page=shopping_cart&pageNo=${shoppage.pageNo }"class="nav5">购物车</a></li>


            <c:if test="${sessionScope.user!= null}" >

            <li><a href="shopServlet?method=getPage&pageNo=${shoppage.pageNo }" class="nav1">
                    ${sessionScope.user.us_truename}
            </a></li>
            </c:if>

            <c:if test="${sessionScope.user == null}">
               <li><a href="shopServlet?method=forwardPage&page=login&pageNo=${shoppage.pageNo }" class="nav1">
                   未登录
               </a>
               </li>




            </c:if>
<%--            <li><a href="#" class="nav6">收藏夹</a>--%>
<%--                <div class="dropdown_1column">--%>
<%--                    <div class="col_1">--%>
<%--                        <ul class="simple">--%>
<%--                            <li>--%>
<%--                                <a href="#">收藏的宝贝</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--            <li class="dropdown">--%>
<%--                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">--%>
<%--                    <span class="glyphicon glyphicon-user" aria-hidden="true">--%>
<%--                    </span>&nbsp;${requestScope.user}--%>
<%--                    <span class="caret">--%>
<%--                </span>--%>
<%--                </a>--%>
<%--                <ul class="dropdown-menu">--%>
<%--                    <li><a href="#">我的账号</a></li>--%>
<%--                    <li><a href="#">我的订单</a></li>--%>
<%--                    <li><a href="#">账号余额</a></li>--%>
<%--                    <li role="separator" class="divider"></li>--%>
<%--                    <li><a href="#">退出</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>
        </ul>
    </div>
</div>
</body>
</html>
