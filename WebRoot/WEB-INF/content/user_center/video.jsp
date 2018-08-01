<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－网站操作在线视频教程</title>
<meta name="description" content="Step by step guide: Convert video files to HTML5 video" />
<meta name="keywords" content="html5, dvd, video, software, freeware, guides, tutorials, download" />
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<style>
.video_name{text-align: center;font-size: 30px; color: #333;}
.player{ background-color: transparent;margin: 15px 0 1px;padding: 0;}
.goog{width:200px;float:left;text-align: center;}
.fire span{display:block;text-indent: 57px;}
</style>
</head>
<body class="bodybg">
<c:set value="0" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="hgrzx">
  <div class="hgrzxt">
 	 <div class="video_name">网站操作在线视频教程</div>
 	 <div class="player">
	    <video class="videoM" width="1200" height="auto" controls="controls" preload="auto" poster="../images_hhr/videoload.png">
	    <source src="../video_hhr/newHelp.mp4"  type='video/mp4;  codecs="avc1.42E01E, mp4a.40.2"'>
		<source src="../video_hhr/newHelp.ogv"  type='video/ogg;  codecs="theora, vorbis"'>
	    	您的浏览器不支持此种视频格式。 
	    	视频支持以下浏览器：
	    	<div>
		    	<div class="goog"><a  href="http://rj.baidu.com/soft/detail/14744.html?ald"><img class="google" src="../images_hhr/google.png"/><span>谷歌浏览器</span></a></div>
		    	<div class="fire"><a  href="http://www.firefox.com.cn/"><img class="firefox" src="../images_hhr/firefox.png"/ ><span>火狐浏览器</span></a></div>
	    	</div>
	    </video>
    </div>
  </div>
<div class="clear"></div>
<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>

