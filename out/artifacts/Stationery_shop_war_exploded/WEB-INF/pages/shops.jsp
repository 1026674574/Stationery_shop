<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>校园文具网</title>
    <link href="${pageContext.request.contextPath}/jsp/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<div class="above">
    <div class="logo"></div>
    <div class="search">
        <input type="text" size="70" name="search" class="search1">
        <input type="submit" name="submit" value="搜索" class="submit">
    </div>
</div>
<div class="content">
    <c:forEach items="${shoppage.list}" var="shop">
        <div class="col-sm-6 col-md-4">
            <a href="shopServlet?method=getShop&pageNo=${shoppage.pageNo }&id=${shop.sh_id}" >
                <div class="thumbnail">
                    <img src="${shop.sh_picpth}" alt="...">
                    <div class="caption">
                        <h3 style="color:black;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${shop.sh_name}</h3>
                        <p class="prcieNum">￥ ${shop.sh_price }</p>
                        <p>
                            <a href="computerServlet?method=addToCart&pageNo=${computerpage.pageNo }&id=${computer.id}&model=${computer.model}&brand=${computer.brand}" class="btn btn-primary addToCart" role="button"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 加入购物车</a>
                            <a href="#" class="btn btn-default" role="button"> <span class="glyphicon glyphicon-star" aria-hidden="true" style="color:#FF8F1C;"></span> 收藏</a>
                        </p>
                    </div>
                </div>
            </a>
        </div>
    </c:forEach>
</div>
<div class="container">
    共 <span class="badge">${shoppage.totalPageNumber }</span> 页
    &nbsp;&nbsp;
    当前第 <span class="badge">${shoppage.pageNo } </span> 页
    &nbsp;&nbsp;
    <c:if test="${shoppage.hasPrev }">
        <a class="btn btn-default" role = "button" href="shopServlet?method=getPage&pageNo=1">首页</a>
        &nbsp;&nbsp;
        <a class="btn btn-default" role = "button" href="shopServlet?method=getPage&pageNo=${shoppage.prevPage }">上一页</a>
    </c:if>
    &nbsp;&nbsp;
    <c:if test="${shoppage.hasNext }">
        <a class="btn btn-default" role = "button" href="shopServlet?method=getPage&pageNo=${shoppage.nextPage }">下一页</a>
        &nbsp;&nbsp;
        <a class="btn btn-default" role = "button" href="shopServlet?method=getPage&pageNo=${shoppage.totalPageNumber }">末页</a>
    </c:if>
    &nbsp;&nbsp;
    转到 <input  type="text" size="1" id="pageNo"  data-dismiss="alert" aria-label="Close"/> 页
</div>

</div>
<%@ include file="/commons/foot.jsp"%>
</body>
</html>
