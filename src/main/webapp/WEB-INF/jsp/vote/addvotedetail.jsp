<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>新增投票项</title>
	<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
	<form:form modelAttribute="domain" action="${pageContext.request.contextPath}/vote/doaddvotedetail" >
		<input name="voteId" value="${voteId }" hidden="true" />
		<input name="content" title="投票内容" />
		<select name="type" >
			<option value="1" >单选框</option>
			<option value="2" >文本框</option>
		</select>
		<button type="submit" >提交</button>
	</form:form>
</body>
</html>