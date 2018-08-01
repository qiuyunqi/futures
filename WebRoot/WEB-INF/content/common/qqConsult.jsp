<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<div class="hzxzx"><a href="javascript:void(0)" onclick="window.open('http://www.sobot.com/chat/pc/index.html?sysNum=6870ddb5b067480b8d3e7fa80dec3520');" class="erweima zax"><i class="zaixxianzx11"></i><span>在线咨询</span></a></div>
<div class="hzxzx1"><div class="wxcode"><img src="../images_hhr/borwx.png" /></div><a href="javascript:void(0)" id="img1" class="erweima weiwei"><i class="weixincd1"></i><span>微信公众号</span></a></div>
<div class="hzxzx2"><a href="${ctx}/user_center/video.htm" class="erweima vido"><i class="zaixxianvido"></i><span>视频教程</span></a></div>

<style type="text/css">
	.zax:hover{color:white;background:#00a2ff;}
	.zax:hover i{background: url("../images_hhr/zaixxianzxhover.png") no-repeat;}
	.vido:hover{color:white;background:#00a2ff;}
	.vido:hover i{background: url("../images_hhr/vido.png") no-repeat;}
	#img1:hover{color:white;background:#00a2ff;}
	#img1:hover i{background: url("../images_hhr/weixincd1hover.png") no-repeat;}
	
	.img2:hover{color:white;background:#00a2ff;}
	.img2:hover i{background: url("../images_hhr/qaph.png") no-repeat;}
	.wxcode,.appcode{position: absolute; right: 85px;top: -50px;}
	.hzxzx3{top:390px !important;}
	.hzxzx02{position :fixed; right:0; top:400px; z-index:999; display:inline; _position:absolute;margin-top:20px;}
</style>
<script>
$(".wxcode").hide(); 
$(".appcode").hide();
$(document).ready(function(){

	var ua = navigator.userAgent;
	if(ua.indexOf("Android")>=0){
		$(".hzxzx02").hide();
	}
	
	if(ua.indexOf("iPhone")>=0){
		$(".hzxzx3").hide();
	}
	
	$("#img1").mouseover(function(){
		$(".wxcode").show();
	})
	$("#img1").mouseout(function(){
		$(".wxcode").hide();
	})
	$(".img2").mouseover(function(){
		$(".appcode").show();
	})
	$(".img2").mouseout(function(){
		$(".appcode").hide();
	})

});  
</script>