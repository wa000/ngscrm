<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%> 

<html>
登陆失败!
<br />
<button onclick="showLoginPage()" >返回</button>

<script type="text/javascript">
	function showLoginPage()
	{
		window.location = "${pageContext.request.contextPath}/welcome/login";
	}
</script>

</html>