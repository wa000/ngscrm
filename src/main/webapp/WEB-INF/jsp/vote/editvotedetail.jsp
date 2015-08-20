<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>编辑投票项</title>
	<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>投票项</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${domain.voteDetailList }" var="item">
				<tr>
					<td>${item.content }</td>
					<td>
						${item.typeName }
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/vote/deleteVotedetail?voteid=${voteId}&itemid=${item.itemId}" >删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick = "addVotedetail(${voteId})" >新增</button>
	<button onclick = "showManagerPage()" >返回</button>
</body>
<script type="text/javascript">
	function addVotedetail(voteId)
	{
		window.location = "${pageContext.request.contextPath}/vote/addvotedetail?voteId=" + voteId;
	}
	
	function showManagerPage()
	{
		window.location = "${pageContext.request.contextPath}/vote/manager";
	}
</script>
</html>