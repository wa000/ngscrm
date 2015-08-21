<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>pair分配结果</title>
	<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
	由 系统管理员 在 ${time } 随机生成
	<table border="1">
	<c:forEach items="${pairResultlist }" var="item" >
	<tr>
		<td>${item.pair }</td>
	</tr>	
	</c:forEach>
	</table>
</body>
</html>