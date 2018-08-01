<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－管理费交付记录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style type="text/css">
	body{background: #fff;}
	.success{width:800px;margin:100px auto 0;}
	.hgrsccs{width:800px;border:1px solid #a0a0a0;clear: both;}
	.tit{color:#2db1e1;float:right;padding-bottom: 10px;}
	.hgrsccs span{display: block;margin:30px auto;text-align: center;font-size: 45px;}
	.hgrsccs span img{ margin-right: 15px;margin-top: -5px;}
</style>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="hgrzx">
	<div class="success">
		<a class="tit"  href="${ctx}/user_stock/stockIndex.htm">返回解套者联盟首页>>></a>
		<div class="hgrsccs">
		    <span><img src="../images/jiaofeisccs.png"/>缴费成功。</span>
		 </div> 
	</div>
	   
</div>
<div class="downban"></div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script src="../js_hhr/jquery.nouislider.all.min.js"></script>
<link href="../js_hhr/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />
