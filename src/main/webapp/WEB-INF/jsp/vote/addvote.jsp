<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%> 

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>增加投票</title>
	<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
	<form:form modelAttribute="domain" action="${pageContext.request.contextPath}/vote/doaddvote" >
		<table>
			<tr>
				<td>标题</td>
				<td><input name="addTitle" /></td>
			</tr>
		</table>
		<input type="submit" value="确定" />
	</form:form>
</body>
</html>