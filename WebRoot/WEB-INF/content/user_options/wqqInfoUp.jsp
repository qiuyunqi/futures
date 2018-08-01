<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－看涨详情</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:100%; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fff;height:100%;}
i{font-style: normal;}
.app-wrapper{
    width: 100%;
    height: 100%;
    margin:0 auto;
}
.app-content{height:100%;width:100%;position: absolute; left: 0; top: 0%;}
.infoBtn{background:#f6f6f8;}
.infoBtn a{ text-align: center;display: block;color:#fff;width: 50%;float: left;padding:4% 0;margin-bottom:2%;color:black;}
.zhang{background:#fff;border-right:1px solid #ededed;border-bottom:2px solid #004e99;}
.die{background:#fff;}
.ctxName{margin:5% 0;padding: 0 10%;}
.ctxName p{margin-bottom:5%;color:#005e91;}
.ctxName p span{color:#999;}
.infoList>span{font-size:1rem;padding:2% 5%;color:#fff;background: #76cbf4;border-radius:0 50px 50px 0;}
.infoList p{padding:5% 10%;font-size:0.8rem;}
.infoList img{display: block;margin-left: 5%;width: 90%;}
.sysm p{padding: 5% 10% 0;}
.sysm{margin-bottom:12%;}
@media screen and (max-width:240px){
	.infoBtn a{font-size:10px;}
	.infoBtn{height: 12%;}
	.infoList span{font-size: 0.9rem;}
	.coordinate_xUl li{font-size: 0.5rem;}
	.widtFir{width:12%;background: green;font-size:0.2rem !important; }
}
/*坐标轴*/
.coordinate{position: relative;}
.coordinateAll{padding: 5% 10% 0; }
.coordinateAll h5{text-align:center;font-size: 0.8rem;margin-bottom:5%;}
.coordinateAll .leftBor{border-right:1px solid #797979;width:10%；}
.coordinate_x{border-right:1px solid #363636;padding:5% 0 0;width: 15%;position: relative;float:left;}
.coordinate_xUl li span{float:right;}
.coordinate_xUl li{height: 40px;line-height: 40px;}
.coordinate_xUl li:last-child{height: 10px;}
.coordinate_x .upJt{position: absolute;top:-10px;right: -4px;}
.coordinate_yUl li{height:39px;line-height:39px;border-bottom:1px solid #fff;border-left:1px solid #383838;}
.coordinate_y{width:84%;margin-left: 15%;position: relative;padding:5% 0 0;}
.coordinate_y .rgtJt{position: absolute;right:0;bottom:-6px;}
.coordinate_yUl {margin-top:20px;border-bottom:1px solid #363636;}
.coordinate_ybg{color:#fff;text-align: right;}
.botmTit{width:84%;margin-left: 15%;float:right;text-align: right;position: absolute;}
.botmTitFir{width:50%;float:left;}
.botmTitSec{width:24%;float:left;}
 .botmTitThi{width:20%;float:left;}
 .xuxian{position: absolute;z-index:99;bottom: 0;width:100%;height:370px;}
 .xuxian li{width:1px;height:370px;border-right:1px dashed #383838;float:left;}
 .xuxian li:first-child{width:50%;}
 .xuxian li:last-child{width:24%;}
.widtFir{width:12%;background: green;font-size:0.52rem;}
.widsec{width:30%;background: #fc635d;}
.widThir{width:50%;background: #fc635d;}
.widfor{width:52%;background: #fc635d;}
.widfiv{width:54%;background: #fc635d;}
.widsix{width:56%;background: #fc635d;}
.widsev{width:58%;background: #fc635d;}
.wideig{width:60%;background: #fc635d;}
.widnin{width:62%;background: #fc635d;}
.widTen{width:64%;background: #fc635d;}
.widdFir{width:66%;background: #fc635d;}
.widdsec{width:68%;background: #fc635d;}
.widdThir{width:74%;background: #fc635d;}
.widdfor{width:76%;background: #fc635d;}
.widdfiv{width:78%;background: #fc635d;}
.widdsix{width:80%;background: #fc635d;}
.widdsev{width:82%;background: #fc635d;}
.widdeig{width:84%;background: #fc635d;}
.widdnin{width:86%;background: #fc635d;}
.widdTen{width:88%;background: #fc635d;}
.widtFirs{width:90%;background: #fc635d;}
.widsecs{width:92%;background: #fc635d;}
.widThirs{width:94%;background: #fc635d;}
</style>
</head>
<body onmousewheel="return false;">
	 <div class="app-wrapper" id="wrapper">
        <div class="app-content" >
       		<div class="infoBtn"><a class="zhang" href="${ctx}/user_options/wqqInfoUp.htm">看涨详情</a><a class="die" href="${ctx}/user_options/wqqInfoDown.htm">看跌详情</a></div>
        	<div class="clear"></div>
        	<div class="ctxName">
        		<p><span>产品名称：</span>${contents.name }</p>
        		<p><span>申购时间：</span>${upDetail.buyBeginTime}至${upDetail.buyEndTime}中午12点</p>
        		<p><span>封闭时间：</span>${upDetail.closeBeginTime}至${upDetail.closeEndTime}</p>
        		<p><span>加入条件：</span>10元起购，以10元的倍数递增</p>
        	</div>
        	<div class="infoList">
        		<span>产品简介</span>
        		<p>本产品与${contents.OOI}挂钩，随${contents.OOI}上涨获得收益。一切以最终涨幅（${upDetail.closeEndTime}收盘价）为准，与封闭期内涨跌幅无关。</p>
        	</div>
        	<div class="infoList">
        		<span>涨幅公式</span>
        		<p>（ ${upDetail.closeEndTime}收盘价-${upDetail.buyEndTime}收盘价）/ ${upDetail.closeEndTime}收盘价，正数为上涨。</p>
        	</div>
        	<div class="infoList sysm">
        		<span>收益说明</span>
        		<p>1.涨幅对应的收益为下表所示${upDetail.up_dw_num}档，涨幅最终达到哪一档，用户既可以获得对应收益，最高可获得高达${upDetail.up_max_bs}倍投资本金的净回报！</p>
        		<p>2.最终涨幅超过${upDetail.up_max_zf}%时，向用户返还本息合计${upDetail.up_dw9_val}%。</p>
        		<p>3.最终涨幅小于${upDetail.up_min_zf}%时，客户损失全部投资本金。</p>
        		<div class="coordinateAll">
        			<h5>${contents.OOI}涨幅/收益（${upDetail.closeEndTime}-${upDetail.buyEndTime}）</h5>
        			<div class="coordinate">
        				<div class="coordinate_x">
        				<span class="upJt">∧</span>
        					<ul class="coordinate_xUl">
        						<li>涨幅</li>
        						<li>${upDetail.up_dw9}${empty upDetail.up_dw9?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw8}${empty upDetail.up_dw8?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw7}${empty upDetail.up_dw7?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw6}${empty upDetail.up_dw6?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw5}${empty upDetail.up_dw5?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw4}${empty upDetail.up_dw4?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw3}${empty upDetail.up_dw3?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw2}${empty upDetail.up_dw2?'':'%'}<span>-</span></li>
        						<li>${upDetail.up_dw1}${empty upDetail.up_dw1?'':'%'}</li>
        					</ul>
        				</div>
        				<div class="coordinate_y">
        					<ul class="coordinate_yUl">
        						<li class="coordinate_ybg frt"><span class="coordinate_yVal">${upDetail.up_dw9_val}</span>%</li>
        						<li class="coordinate_ybg sed"><span class="coordinate_yVal">${upDetail.up_dw8_val}</span>%</li>
        						<li class="coordinate_ybg thd"><span class="coordinate_yVal">${upDetail.up_dw7_val}</span>%</li>
        						<li class="coordinate_ybg fort"><span class="coordinate_yVal">${upDetail.up_dw6_val}</span>%</li>
        						<li class="coordinate_ybg fve"><span class="coordinate_yVal">${upDetail.up_dw5_val}</span>%</li>
        						<li class="coordinate_ybg sixd"><span class="coordinate_yVal">${upDetail.up_dw4_val}</span>%</li>
        						<li class="coordinate_ybg seve"><span class="coordinate_yVal">${upDetail.up_dw3_val}</span>%</li>
        						<li class="coordinate_ybg egt"><span class="gren"></span><span class="coordinate_yVal">${upDetail.up_dw2_val}</span>%</li>
        						<li class="coordinate_ybg egt"><span class="gren"></span><span class="coordinate_yVal">${upDetail.up_dw1_val}</span>%</li>
        					</ul>
        					<span class="rgtJt">></span>
        					<div class="xuxian">
        					<ul>
        						<li></li>
        						<li></li>
        					</ul>
        				</div>
        				</div>
        				<ul class="botmTit">
        					<li class="botmTitFir">保本</li>
        					<li class="botmTitSec">翻倍</li>
        					<li class="botmTitThi">收益</li>
        				</ul>
        			</div>
        		</div>
        		<!-- <img src="../images_czt/shouyi.png"/>  -->
        	</div>
        	<div class="infoList">
        		<span>结算说明</span>
        		<p>${upDetail.clearTime}完成所有预算，向用户返还投资收益。</p>
        	</div>
        </div>
    </div>
</body>
</html>
<script>

	var p0=document.getElementById('ljqg')
	 p0.style.fontSize=(document.body.clientWidth*0.05)+"px";
	var p1=document.getElementById('syTime')
	 p1.style.fontSize=(document.body.clientWidth*0.05)+"px";
   var p2=document.getElementById('appTxt')
	 p2.style.left=(document.body.clientWidth*0.3)+"px";
	 p2.style.fontSize=(document.body.clientWidth*0.05)+"px";
	
</script>
<script>
//坐标轴
	$(document).ready(function(){
  
    $(".coordinate_yVal").each(function(){
    if($(this).text()==-100){
    	$(this).parent().addClass("widtFir");
    }else if($(this).text()==50){
    	$(this).parent().addClass("widsec");
    }else if($(this).text()==100){
    	$(this).parent().addClass("widThir");
    }else if($(this).text()==110){
    	$(this).parent().addClass("widfor");
    }else if($(this).text()==120){
    	$(this).parent().addClass("widfiv");
    }else if($(this).text()==130){
    	$(this).parent().addClass("widsix");
    }else if($(this).text()==140){
    	$(this).parent().addClass("widsev");
    }else if($(this).text()==150){
    	$(this).parent().addClass("wideig");
    }else if($(this).text()==160){
    	$(this).parent().addClass("widnin");
    }else if($(this).text()==170){
    	$(this).parent().addClass("widTen");
    }else if($(this).text()==180){
    	$(this).parent().addClass("widdFir");
    }else if($(this).text()==190){
    	$(this).parent().addClass("widdsec");
    }else if($(this).text()==200){
    	$(this).parent().addClass("widdThir");
    }else if($(this).text()==210){
    	$(this).parent().addClass("widdfiv");
    }else if($(this).text()==220){
    	$(this).parent().addClass("widdsix");
    }else if($(this).text()==230){
    	$(this).parent().addClass("widdsev");
    }else if($(this).text()==240){
    	$(this).parent().addClass("widdeig");
    }else if($(this).text()==250){
    	$(this).parent().addClass("widdnin");
    }else if($(this).text()==260){
    	$(this).parent().addClass("widdTen");
    }else if($(this).text()==270){
    	$(this).parent().addClass("widtFirs");
    }else if($(this).text()==280){
    	$(this).parent().addClass("widsecs");
    }else if($(this).text()==290){
    	$(this).parent().addClass("widThirs");
    }
    });
  
});
</script>