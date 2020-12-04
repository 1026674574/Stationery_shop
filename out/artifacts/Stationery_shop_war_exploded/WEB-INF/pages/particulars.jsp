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
                <li class="price">${shop.sh_price}</li>
                <li class="collection">${shop.sh_number}</li>
                <li class="button"><a href="#" style="cursor:pointer"><button class="buy">立即购买</button></a>
                    <a href="#" style="cursor: pointer"><button class="into">加入购物车</button></a></li>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/commons/foot.jsp"%>
</body>
<script>var num_jia = document.getElementById("num-jia");
var num_jian = document.getElementById("num-jian");
var input_num = document.getElementById("input-num");

num_jia.onclick = function() {
input_num.value = parseInt(input_num.value)+1;
}

num_jian.onclick = function() {

if(input_num.value <= 0) {
input_num.value = 0;
} else {
input_num.value = parseInt(input_num.value)-1;
}

}</script>
</html>
