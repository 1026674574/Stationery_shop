<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404错误</title>
	<link href="${pageContext.request.contextPath}/jsp/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/jsp/css/index.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/js/bootstrap.min.js"></script>
<%--<%@ include file="/commons/queryCondition.jsp" %>--%>
</head>
<body>

<%@ include file="/commons/top.jsp"%>
		
	<div class="container">
		<div class="jumbotron" style="margin-top:30px;">
  			<h1>404!页面未能够找到</h1>
  			<p>抱歉，页面好像去火星啦！</p>
  			<p><a class="btn btn-primary btn-lg" href="index.jsp" role="button">返回</a></p>
		</div>
	</div>




<%@ include file="/commons/foot.jsp"%>
</body>
</html>