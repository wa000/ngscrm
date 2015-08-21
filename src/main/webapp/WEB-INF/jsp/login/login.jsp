<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登陆</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/login.css"/>
<script src="${pageContext.request.contextPath}/theme/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript" src="login.js"></script>
</head>
<body>
<div id="home">
	<form:form id="login" class="current1" modelAttribute="domain" method="post" action="${pageContext.request.contextPath}/welcome/login/checkinfo" >
        <h3>用户登入</h3>
        <img class="avator" src="${pageContext.request.contextPath}/theme/img/login/saber.png" width="96" height="96"/>
        <label>邮箱/名称<input type="text" name="staffId" style="width:215px;" /><span>邮箱为空</span></label>
        <label>密码<input type="password" name="password"  /><span>密码为空</span></label>
        <button type="submit">管理员登入</button>
        <button type="button" onclick="showVoteList()">投票</button>
        <button type="button" onclick="showPairresult()">pair结果</button>
    </form:form>
</div>
</body>
</html>
<script>
$(function(){
	<%-- 
    /**
     * jquery方法：addClass()
     * addClass() 方法向被选元素添加一个或多个类。该方法不会移除已存在的 class 属性，仅仅添加一个或多个 class 属性。
     * 如需添加多个类，请使用空格分隔类名。
     */
	 --%>
    $("#login").addClass("current");    
    <%--
    /**
     * 正则检验邮箱
     * email 传入邮箱
     * return true 表示验证通过
     */
     --%>
    function check_email(email) {
        if (/^[\w\-\.]+@[\w\-]+(\.[a-zA-Z]{2,4}){1,2}$/.test(email)){
            return true;
        }
    }
    <%--
    /**
     * input 按键事件keyup
     */
     --%>
    $("input[name]").keyup(function(e){
        //禁止输入空格  把空格替换掉(空格的ASCII为32)
        if($(this).attr('name')=="pass" && e.keyCode==32){
            $(this).val(function(i,v){
                return $.trim(v);
            });
        }
        if($.trim($(this).val())!=""){
            $(this).nextAll('span').eq(0).css({display:'none'}); 
        }
    });
    
    //错误信息
    var succ_arr=[];
    <%--
    /**
     * input失去焦点事件focusout
     * 这跟blur事件区别在于，他可以在父元素上检测子元素失去焦点的情况。
     */
     --%>
    $("input[name]").focusout(function(e){
        var msg="";
        if($.trim($(this).val())==""){
            if($(this).attr('name')=='userName'){
                succ_arr[0]=false;
                msg="登入名为空";
            }else if($(this).attr('name')=='pass'){
                succ_arr[1]=false;
                msg="密码为空";
            }     
        }else{
            if($(this).attr('name')=='userName'){
                succ_arr[0]=true;       
            }else if($(this).attr('name')=='pass'){
                succ_arr[1]=true;
            }
        }
        $(this).nextAll('span').eq(0).css({display:'block'}).text(msg);
    });
    <%-- 
    /**
     * Ajax用户注册
     */
    --%>
    $("button[type='button']").click(function(){
        $("input[name]").focusout();  //让所有的input标记失去一次焦点来设置msg信息
        for (x in succ_arr){
            if(succ_arr[x]==false) return;
        }
        //$("#login").removeClass("current");    
        var data=$('#login').serialize(); //序列化表单元素
        <%--
        /**
         * 有兴趣的可以到这里 自行发送Ajax请求 实现注册功能
         */
         --%>
    });
    
});

function showVoteList()
{
	window.location = "${pageContext.request.contextPath}/showvote/showvotepage";
}

function showPairresult()
{
	window.location = "${pageContext.request.contextPath}/showvote/showPairResult";
}

</script>