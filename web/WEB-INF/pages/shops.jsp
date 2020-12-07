<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8" />
    <title>校园文具网</title>
<%--    <link href="${pageContext.request.contextPath}/jsp/css/bootstrap.min.css" type="text/css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/jsp/css/index.css" type="text/css" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        $(function(){

            $("#pageNo").change(function(){
                var val = $(this).val();
                val = $.trim(val);
                //1. 校验 val 是否为数字 1, 2, 而不是 a12, b
                var flag = false;
                var reg = /^\d+$/g;
                var pageNo = 0;
                if(reg.test(val)){
                    //2. 校验 val 在一个合法的范围内： 1-totalPageNumber
                    pageNo = parseInt(val);
                    if(pageNo >= 1 && pageNo <= parseInt("${shoppage.totalPageNumber }")){
                        flag = true;
                    }
                }
                if(!flag){

                    $(".alert").alert();
                    $(this).val("");
                    return;
                }

                //3. 页面跳转
                var href = "shopServlet?method=getPage&pageNo=" + pageNo + "&" + $(":hidden").serialize();
                window.location.href = href;
            });
        })

    </script>
    <script type="text/javascript">
        $(function(){
            $("#back-to-top").click(function() {
                $("html,body").animate({scrollTop:0}, 500);
            });
        })
    </script>
<%--    <%@ include file="/commons/queryCondition.jsp" %>--%>
</head>
<body>
<%@ include file="/commons/top.jsp"%>
<div class="container tips">
    <c:if test="${param.id != null}">
        <div class="alert alert-success tip-success">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>成功！</strong>您已经将 <b>${param.name} </b>加入到购物车中!
            <b><a href="shopServlet?method=forwardPage&page=shopping_cart&pageNo=${shoppage.pageNo }">立即查看购物车</a></b>
        </div>
    </c:if>

</div>
<div class="above">
    <div class="logo"><img src="${pageContext.request.contextPath}/jsp/img/logo.png" alt="..."></div>
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
                    <div style="text-align: center;"><img src="${shop.sh_picpth}" alt="..."></div>
                    <div class="caption">
                        <h3 style="color:black;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${shop.sh_name}</h3>
                        <p class="prcieNum">￥ ${shop.sh_price }</p>
                        <p>
                            <a href="shopServlet?method=addToCart&pageNo=${shoppage.pageNo }&id=${shop.sh_id}&name=${shop.sh_name}" class="btn btn-primary addToCart" role="button"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 加入购物车</a>
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
