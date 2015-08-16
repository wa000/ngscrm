<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page isELIgnored="false"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8" />
<title>投票结果</title>
<link href="${pageContext.request.contextPath}/theme/css/jQuery.spider.poll.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	*{margin:0;padding:0;}
	.demo{width:600px;margin:0 auto;}
	.demo .btn{padding:20px 0 20px 175px;}
</style>

<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jQuery.spider.poll.js"></script>

<script type="text/javascript">
var data="${domain.jsonResult}";

$(document).ready(function(){
    $("#poll_a").poll("poll1",{
        title:'投票结果',
        titleColor:'#ff6600',
        width:'600px',
        data:data
    });
});
</script>
</head>

<body>
	<div id="poll_a"></div>
</body>