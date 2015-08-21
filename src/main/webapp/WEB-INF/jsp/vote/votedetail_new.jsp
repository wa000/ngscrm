<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8" />
<title>问卷详情</title>
<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>

<body>
<form:form modelAttribute="domain" method="post" action="${pageContext.request.contextPath}/showvote/showvoteresult" >
<table>
<tr>
	<td>
	    <ul>
		<c:forEach items="${domain.voteDetailList }" var="item">
	    <li style="list-style-type:none;">
	    	<form:radiobutton path="selectId" value="${item.voteId }@_@${item.itemId }" />
	    	${item.content }
	    </li>
		</c:forEach>
	    </ul>
	</td>
</tr>
<input name="voteId" value="${domain.voteId }" hidden="true" />
<tr>
	<td>
		${domain.detailTypeBean.content } : <textarea name="content" ></textarea>
	</td>
</tr>
<tr>
<td><input type="submit" value="提交"/></td>
</tr>
</table>
</form:form>
</body>