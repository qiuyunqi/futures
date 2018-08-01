<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="ALL" />
<meta name="googleot" content="ALL" />
<meta name="revisit-after" content="7 days" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套者联盟</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
	.background{width:100%;min-width: inherit;min-width:1000px;}
	.blue{background: #0f51b2;position: relative;}
	.center{ width:100%;margin:0 auto;}
	.yellow{background: #ffea35;}
	.pink{background: #c879b2;}
	.red{background: #ff8b7e;}
	.wathet{background: #76b7fb;}
	.whited{background: #fafaee;}
	.jtbtn{position: absolute;z-index: 99;top: 55%; width:100%;margin:0 auto;}
	#show_title{display: block;margin:0 auto;width:1200px;}
</style>
</head>
<body >
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
		<div class="background">
			<div class="blue">
				<div class="blue center">
					<img src="../images/stockExpand  (1).png" width="100%"/> 
					<div class="jtbtn" >
				<c:if test="${empty fuUser}"><a id="show_title" href="javascript:void(0);" onclick="lgInfo(1)"><img src="../images/jtbtn.png"/></a></c:if>
				<c:if test="${!empty fuUser}"><a id="show_title" href="${ctx}/user_stock/stockIndex.htm"><img src="../images/jtbtn.png"/></a></c:if>
			</div> 
				</div>
			</div>
			<div class="white">
				<div class="white center">
					<img src="../images/stockExpand  (2).png" width="100%"/> 
				</div>
			</div>
			<div class="yellow">
				<div class="yellow center">
					<img src="../images/stockExpand  (3).png" width="100%"/> 
				</div>
			</div>
			<div class="pink">
				<div class="pink center">
					<img src="../images/stockExpand  (4).png" width="100%"/> 
				</div>
			</div>
			<div class="red">
				<div class="red center">
					<img src="../images/stockExpand  (5).png" width="100%"/> 
				</div>
			</div>
			<div class="wathet">
				<div class="wathet center">
					<img src="../images/stockExpand  (6).png" width="100%"/> 
				</div>
			</div>
			<div class="whited">
				<div class="whited center">
					<img src="../images/stockExpand  (7).png" width="100%"/> 
				</div>
			</div>
			<div class="clear"></div>
		</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
	var p0=document.getElementById('show_title')
    p0.style.width=(document.body.clientWidth*0.142)+"px";
    
    function lgInfo(flag){
		$.fancybox.open({
			href : '${ctx}/user_login/userLoginAjax.htm?flag='+flag,
			type : 'ajax',
			padding : 0 ,
			scrolling:'no',
		});
	}
</script>
