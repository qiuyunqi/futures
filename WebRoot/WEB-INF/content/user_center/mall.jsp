<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－点券商城</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<c:set value="8" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%-- <%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
 --%>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
	<div class="downban"></div>
	<div class="mgrzx" style="margin:0 auto;">
	  <%@include file="../common/left.jsp" %>
	  <div class="mgrzxr" >
	    	<div class="mallImg">
			    <img src="../images_new/mallwsx.jpg" alt="未上线"  style="display: block;width:100%;"/>
			</div>
		</div>
	</div>
	<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
