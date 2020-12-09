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
        /*div.nav ul li*/
        /*{*/
        /*    float: left;*/
        /*    list-style: none;*/
        /*}*/
        ul.nav_ul{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
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
        div.nav ul li a:hover
        {
            color: red;
            text-decoration: underline;
            background: white;
        }
        div.nav ul li:hover .dropdown_1column
        {
            left:88%;
            top:15px;
        }
        div.dropdown_1column
        { /* 下拉菜单边框颜色*/
            margin: 0 auto;
            float: left;
            position: absolute;
            left: -999em;
            text-align: left;
            border: 1px solid darkgray;
            border-top: none;
            background: white;
            width: 80px;
        }
        div.nav li:hover div a
        { /* 下拉菜单文字颜色*/
            font-size:12px;color:#444;
        }

        div.nav li:hover div a:hover
        {
            color:red;
            text-decoration: underline;
        }
        /*下拉带单鼠标停留*/
        div.nav li ul
        {
            list-style:none;
            padding:10px 5px;
            margin:0;
        }
        div.nav li ul li
        {
            font-size:12px;
            line-height:26px;
            position:relative;
            padding:0;
            margin:0;
            float:none;
            text-align:left;
            width:130px;
        }
        div.nav li ul li:hover
        {
            background:none;
            border:none;
            padding:0;
            margin:0;
        }
    </style>
</head>
<body>
<div class="top">
    <div class="nav">
        <ul class="nav_ul">
            <li><a href="${pageContext.request.contextPath}/index.jsp">网站首页</a></li>
            <li class="nav2">欢迎进入本网站</li>
            <li><a href="shopServlet?method=forwardPage&page=shopping_cart&pageNo=${shoppage.pageNo }">购物车</a></li>
            <c:if test="${sessionScope.user!= null}" >
            <li><a href="shopServlet?method=getPage&pageNo=${shoppage.pageNo }">
                    ${sessionScope.user.us_truename}
            </a>
                <div class="dropdown_1column">
                    <div class="col_1">
                        <ul class="simple">
                            <li><a href="#">我的订单</a></li>
                            <li><a href="#">账号余额</a></li>
                            <li><a href="#">我的收藏</a></li>
                            <li><a href="#">退出</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            </c:if>

            <c:if test="${sessionScope.user == null}">
               <li><a href="shopServlet?method=forwardPage&page=login&pageNo=${shoppage.pageNo }">
                   未登录
               </a>
               </li>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
