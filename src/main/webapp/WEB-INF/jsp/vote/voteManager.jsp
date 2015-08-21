<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%> 

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>投票管理页面</title>
	<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
</head>

<body>
	<table border="1">
		<thead>
			<tr>
				<td>投票id</td>
				<td>标题</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${domain.voteList }" var="item">
			<tr>
				<td>
					${item.voteId }
				</td>
				<td>
					${item.title }
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/vote/showvotedetailedit?voteId=${item.voteId }" >编辑</a>
					<a href="${pageContext.request.contextPath}/vote/deleteVote?voteid=${item.voteId }" >删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="addVote()" >新增投票</button>
	<br />
	<br />
	<br />
	<button onclick="generatePair()" >随机生成pair</button>
</body>
<script type="text/javascript">
function addVote()
{
	window.location = "${pageContext.request.contextPath}/vote/addvote";
}

function generatePair()
{
	window.location = "${pageContext.request.contextPath}/showvote/generatePair";
}
</script>
</html>