<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－联系我们</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<c:set var="jsptype" value="map"/>
<style>
body{background:#004e99;}
.intro{display: block;}
.intro .map a{color: #0092ff;}
.contaier{height:680px;}

</style>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
<div class="contain">
	<div class="downban"></div>
	<div class="contaer">
	<%@include file="../common/aboutUs_left.jsp" %>
				<div class="contaier">
					<div class="contaier_bod">
							<div class="lst-on">联系我们</div>
						   	<div class="list_txt zxr">
								<ul>
									<li >客服热线：010-53320537（周一至周五，8：30-17：00）</li>
									<li >客服邮箱：help@hhr360.com</li>
									<li >招聘邮箱：hr@hhr360.com</li>
									<li >地址：北京市海淀区高粱桥斜街28号</li>
								</ul>
							</div>
							<div class="introims"><img src="../images_hhr/mapsmall.png"/></div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="downban"></div>
		</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
