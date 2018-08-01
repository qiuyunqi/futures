<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－安全信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style=" background:#f2f2f2">
<c:set value="7" var="pg"></c:set>
<%@include file="../common/userTop.jsp" %>

<div class="center">
	<%@include file="../common/left.jsp" %>
    <div class="fl rt_cont">
    	<div class="rt_cont_title">客服电话：010-53320537&nbsp;&nbsp;&nbsp;&nbsp;工作时间：8:30-17:00</div>
        
        <div class=" dxgs">
        	<ul>
            	<li class="yxz"><a href="javascript:void(0);">安全信息</a></li>
            </ul>
            <div class="clr"></div>
        </div>
        
        <div class=" aqxx">
        	<span class="zhys smrz0"></span>
        	<c:if test="${fuUser.isCheckCard>0}">
        		<span>实名认证：${fn:substring(fuUser.cardNumber,0,2)}**** **** **** **${fn:substring(fuUser.cardNumber,fn:length(fuUser.cardNumber)-2,-1)}</span>
        	</c:if>
            <c:if test="${fuUser.isCheckCard!=2}">
            	<span style=" color:#db0502">${fuUser.isCheckCard==1?'待审核':'未认证'}</span>
            </c:if>
            <c:if test="${fuUser.isCheckCard!=2}">
            
            <a class="fr sczp" href="javascript:void(0);" onclick="checkPerson();">实名信息</a>
            </c:if>
            <div class=" clr"></div>
        </div>
        <div class=" aqxx">
        	<span class="zhys bdsj"></span>
            <span>绑定手机：${fn:substring(fuUser.phone,0,3)} **** ${fn:substring(fuUser.phone,fn:length(fuUser.phone)-4,-1)}</span>
        </div>
        <div class=" aqxx">
        	<span class="zhys bdyx"></span>
            <span>绑定邮箱：${fuUser.isBindEmail==1?fuUser.email:'未绑定'}</span>
            <a class="fr sczp" href="javascript:void(0);" onclick="bindEmail();">${fuUser.isBindEmail==1?'重新绑定':'绑定'}</a>
            <div class=" clr"></div>
        </div>
        <div class=" aqxx">
        	<span class="zhys dlmm"></span>
            <span>登录密码：已设置</span>
            <a class="fr sczp" href="javascript:void(0);" onclick="editPassword();">修改</a>
            <div class=" clr"></div>
        </div>
    </div>
    <div class="clr"></div>
</div>


</body>




</html>
<script type="text/javascript" src="${ctx }/js/fancybox/jquery.fancybox.pack.js?v=2.1.4"></script>
<link rel="stylesheet" href="${ctx }/js/fancybox/jquery.fancybox.css?v=2.1.4" type="text/css" media="screen" />
<script>
	//身份认证
	function checkPerson(){
		$.fancybox.open({
 			href : '${ctx}/user_center/personNameAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//绑定邮箱
	function bindEmail(){
		$.fancybox.open({
 			href : '${ctx}/user_center/bindEmailAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//修改密码
	function editPassword(){
		$.fancybox.open({
 			href : '${ctx}/user_center/editPasswordAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
</script>
