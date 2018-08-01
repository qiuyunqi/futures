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
<title>${title}－让人脉帮你理财</title>
<%@ include file="../common/css.jsp" %> 
<%@include file="../common/qqConsult.jsp" %>
<script type="text/javascript" src="../js_hhr/jquery-1.6.min.js"></script>
<script type="text/javascript" src="../js_hhr/jquery.SuperSlide.2.1.1.js"></script>
<script>
//百度统计脚本
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?cb76fdd0655f5b05731cbe36b6b46309";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body style="background: #f4f4f4;">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
 <div id="slideBox" class="slideBox">
 	<div  class="bd">
 	    <ul>
 	  		<!-- <li class="bdThi"><a href="javascript:void(0)"><img src="../images_new/indexBan03.png" /></a></li> -->
			<li class="bdFir"><a href="${ctx}/yqb" ><img src="../images_new/indexBan1.png" /></a></li>
			<li class="bdSec"><a href="javascript:void(0)"><img src="../images_new/indexBan02.png" /></a></li>
		</ul>
 	</div>
	<!-- 下面是前/后按钮代码 -->
	<a class="prev" href="javascript:void(0)"><img src="../images_new/prev.png"/></a>
	<a class="next" href="javascript:void(0)"><img src="../images_new/next.png"/></a>
</div>
<div class="contaer" style="height:600px;z-index:33;">
	<div class="indexDl">
		   <div class="index_lfDl">
		   <span>下载超级合伙人客户端</span>
		   <a class="downloadIos" href="javascript:void(0)" onclick="window.open('https://itunes.apple.com/us/app/chao-ji-he-huo-ren-lian-meng/id1011772374?l=zh&ls=1&mt=8');"><img src="../images_new/downlIos.png"/></a>
		   <a class="downloadAn" href="javascript:void(0)" onclick="window.open('http://a.app.qq.com/o/simple.jsp?pkgname=com.hhr360.partner');"><img src="../images_new/downlAn.png"/></a>
		</div>
		<div class="index_rgDl">
			<span>APP下载二维码</span>
			<img src="../images_new/apcde.png"/>
		</div>
	</div>
	<div class="cooper">
		<div class="cooperTit" ><span class="intrimg2" ></span></div>
		 <table class="indexTal" cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr class="indexTr">
	        <td><img src="../images_new/aliyun.png"/></td>
	        <td><img src="../images_new/hua.png"/></td>
	        <td><img src="../images_new/zhifubao.png"/></td>
	      </tr>
	      <tr class="indexTs">
	        <td><img src="../images_new/tonglian.png"/></td>
	        <td><img src="../images_new/yinl.png"/></td>
	        <td><img src="../images_new/caimi.png"/></td>
	      </tr>
        </table>
	</div>
</div> 
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
<script type="text/javascript" src="${ctx}/js/fancybox/jquery.fancybox.pack.js?v=2.1.4"></script>
<link rel="stylesheet" href="${ctx}/js/fancybox/jquery.fancybox.css?v=2.1.4" type="text/css" media="screen" />
<script type="text/javascript">
//轮播图
/* jQuery(".slideBox").slide({mainCell:".bd ul",effect:"left",autoPlay:true,easing:"easeOutCirc"}); */
jQuery(".slideBox").slide({mainCell:".bd ul",effect:"fold",autoPlay:true,delayTime:700});
//顶部二维码
$("#show-app").hide(); 
$("#show-wx").hide();  
$(document).ready(function(){
	$("#img1").mouseover(function(){
		//$("#code1").animate({width:167},600).show();
		$("#show-wx").show();
		$("#show-app").hide();
	})
	$("#img1").mouseout(function(){
		//$("#code1").animate({width:0},600);
		$("#show-wx").hide();
		$("#show-app").hide();
	})
	$("#img2").mouseover(function(){
		//$("#code2").animate({width:167},600).show();
		$("#show-app").show();
		$("#show-wx").hide();
	})
	$("#img2").mouseout(function(){
		//$("#code2").animate({width:0},600);
		$("#show-app").hide();
		$("#show-wx").hide();
	})
});   

function toZB(){
	if(${empty fuUser}){
		$.fancybox.open({
			href : '${ctx}/user_login/userLoginAjax.htm?flag=1',
			type : 'ajax',
			padding : 0 ,
			scrolling:'no',
		});
	}else{
		location.href="${ctx}/index_guide/tCollege.htm";
	}
}



function lgInfo(flag){
	$.fancybox.open({
		href : '${ctx}/user_login/userLoginAjax.htm?flag='+flag,
		type : 'ajax',
		padding : 0 ,
		scrolling:'no',
	});
}
function logout(){
	$.post("${ctx}/user_login/logoutAjax.htm",null,function(data){
		var json=eval("("+data+")");
		var jsIframe = document.createElement("iframe");
        jsIframe.style.display = "none";//把jsIframe隐藏起来
        document.body.appendChild(jsIframe);
        with(window.frames[window.frames.length - 1]){
            document.open();
            document.write(json.ucsynlogout); //执行JS代码
            document.close();
        } 
        window.setTimeout(function(){location.href="${ctx}/login"},1000);
	});
}
</script>
</html>
