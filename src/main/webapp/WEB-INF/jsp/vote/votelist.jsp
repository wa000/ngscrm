<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page isELIgnored="false"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8" />
<title>调查问卷</title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/theme/css/css-table.css" />
<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/style-table.js"></script>
</head>

<body>
	<table id="travel">
	    <caption>调查问卷</caption>
	    <thead>
	        <tr>
	            <th>标题</th>
	            <th>创建时间</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${voteList }" var="item">
	        <tr>
	            <td>${item.title }</td>
	            <td>${item.createDate }</td>
	            <td><a href="javascript:openPage(${item.id });">查看</a></td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
</body>

<script>
function openPage(id)
{
	window.open("${pageContext.request.contextPath}/vote/showlist/showvotedetail?voteid=" + id);
}
</script>