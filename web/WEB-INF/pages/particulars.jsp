<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校园文件店</title>
    <link href="${pageContext.request.contextPath}/jsp/css/particulars.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<div class="content">
    <div class="main">
        <div class="picture"><img src="${shop.sh_picpth}"></div>
        <div class="describe">
            <ul>
                <li class="name">${shop.sh_name}</li>
                <li class="text">${shop.sh_text} </li>
                <li class="price"><span>价格:</span>${shop.sh_price}</li>
                <li class="collection"><span>销售量:</span>${shop.sh_number}</li>
                <a href="userServlet?method=Like&shop=${shop.sh_id}"><li class="shoucang"></li></a>
                <li class="button">
                    <a href="#" style="cursor:pointer"><button class="buy">立即购买</button></a>
                    <a href="shopServlet?method=addToCartInside&pageNo=${shoppage.pageNo }&id=${shop.sh_name}&name=${shop.sh_name}" style="cursor: pointer"><button class="into">加入购物车</button>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/commons/foot.jsp"%>
</body>

</html>
