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
<title>${title}－余券宝</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<c:set var="jsptype" value="yqbInfo"/>
<style>
	.background{width:100%;min-width: inherit;min-width:1000px;}
	.blue{background: #8ed8ff;position: relative;}
	.center{margin:0 auto;}
	.yellow{background: #f5f5f5;}
	.white{background: #fff;}
	.blues{background: #58c4ff;}
	.fenLin{position: absolute;z-index: 3;}
	.codeBody{position: relative;}
	.yqbCode{position: absolute;z-index:22;width: 140px;right:0;}
</style>
</head>
<body >
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
		<div class="background">
			<div class="blue">
				<div class="codeBody blue center">
					<img class="yqbCode" src="../images_new/yqbInfoCde.png" />
					<img src="../images_new/wbYq1.png" width="100%"/> 
			    </div> 
			</div>
			<div class="fenLin"><img src="../images_new/wbYqBor.png" width="100%"/></div>
			<div class="white">
				<div class="white center">
					<img src="../images_new/wbYq2.png" width="100%"/> 
				</div>
			</div>
			<div class="white">
				<div class="yellow center">
					<img src="../images_new/wbYq3.png" width="100%"/> 
				</div>
			</div>
			<div class="yellow">
				<div class="white center">
					<img src="../images_new/wbYq4.png" width="100%"/> 
				</div>
			</div>
			<div class="fenLin"><img src="../images_new/wbYqBor.png" width="100%"/></div>
			<div class="blues">
				<div class="blues center">
					<img src="../images_new/webYq4.png" width="100%"/> 
				</div>
			</div>
			<div class="clear"></div>
		</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
