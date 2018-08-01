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
<title>${title}－微期权微店</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:100%; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #e4e8e9;height:100%;}
i{font-style: normal;}
.app-wrapper{
    width: 100%;
    height: 100%;
    margin:0 auto;
}
.app-content{height:100%;width:100%;position: absolute; left: 0; top: 0%;}
.app-img{margin-left:5%; color:#fff;text-align:center;width: 90%;border-radius:10px;background: #fff;}
.appSlide{position: relative; color:#fff;text-align:center;width: 100%;top:11%;color:red;}
.app-txtimg{width:100%;display:block;overflow: hidden;}
.oneImg{width:90%;margin:0 auto 10%;}
.appTxt{display:block;font-size:14px;width:50%;background: #fff;float:left;margin: 3% 0;color:black;}
.appInfo{display: block;position: absolute;width:15px;height:9px;background: url("../images/downJt.png") no-repeat;top:8px;left:-20px; opacity:0.7;filter:alpha(opacity:70);animation:swim 2s ease-in-out 0s infinite alternate;-moz-animation:swim 2s ease-in-out 0s infinite alternate;-webkit-animation:swim 2s ease-in-out 0s infinite alternate;}
 @keyframes swim {
            0%{
            	 opacity:1;
                margin-top:-10px;
            }
            100%{
             	opacity:0.1;
                margin-top:10px;
            }
        }
        @-moz-keyframes swim {
            0%{
             	opacity:1;
                margin-top:-10px;
            }
            100%{
            	opacity:0.1;
                margin-top:10px;
            }
        }
        @-webkit-keyframes swim {
            0%{
             	opacity:1;
                margin-top:-10px;
            }
            100%{
            	opacity:0.1;
                margin-top:10px;
            }
        }
.fixed{position: fixed;bottom: 0;width:100%; }
.qg{display: block;background: red;color:#fff;height:10%;}
.qg span{display: block;text-align: center;}
.ljqg{font-size:8px;}
.syTime{font-size:8px;}
.twopage{height: 100%;}

.moreInfo{font-size:12px;color:#fff;padding:2% 4%;border-radius:5px;background: #2ddcf4;position: absolute;top:7%;right:2%;box-shadow: 0.5px 0.5px 0.5px 0.5px #b53042;}
.jindu span{font-size: 0.7rem;}
.app-content .twopage img.no1 { position: absolute; left: 10px; top: 50px; -webkit-transition: all 1s ease 0s; }
.app-content .twopage.cur img.no1 { -webkit-transform: rotate(720deg); }
.app-content .twopage img.no2 { position: absolute; left: 600px; top: 50px; -webkit-transition: all 1s ease 2s; }
.app-content .twopage.cur img.no2 { left: 30px; top: 100px; -webkit-transform: rotate(720deg); }
@-webkit-keyframes dong {  from {bottom:20px;}to { bottom: 60px; }}
.sy{font-size:14px;display: block;}
.percent{font-size:0.75rem;margin: 3% 0;background: #ff516a;border-radius:10px 10px 0 0;padding:2% 0;}
.percents{font-size:14px;margin: 3% 0;color:black;padding:2% 0;}
.percentsHid{font-size:14px;margin: 3% 0;color:#e14f4d;padding:2% 0;display:none;}
.percents span{color:#ff516a;}
.daojis{width:90%;margin-left:5%;border-radius:10px;background: #fff;text-align: center;}
.buy{font-size:17px;display:block;width:70%;margin:0 auto 2%;}
.buy img{display: block;width:100%;}
.red{color:#ff516a;margin:0;}
.green{color:#01aaad;}
.circle{width:10px;}
.dowban{width:100%;height:2%;background: #fff;background: #e4e8e9;}
.lastA{width:70%;background: #fff;position: relative;left:15%;}
.redbar{overflow: hidden;position: relative;top:0;width: 100%;z-index: 5;height:0.5rem;}
.redbar img{width:100%;height:0.5rem;}
.greenbar{right: 0;position: absolute;top: 0;width: 100%;height:0.5rem;z-index:55;}
.greenbar img{display:block;width:100%;height:0.5rem;}
.vs{position: absolute;top:0;z-index:99;margin-left: -0.2rem;left: 50%;margin-top:-0.2rem;}
.vs img{display:block;width:55%;}
.cztnum{position: absolute;top:0;left:5%;width:90%;z-index:100;margin-top:-0.3rem;}
.vsjind{position: relative;width:100%;}
.redtxt{border-radius:0 0 0 10px;}
.appTxts{border-radius:0 0 10px 0;}
.right{float:right;}
.left{float:left;}
@media screen and (max-width:240px){
	.appInfo{top:3px;}
	.sy{font-size:10px;}
	.buy{font-size:12px;}
	.appTxt{padding: 3% 0;}
	.oneImg{margin: 0 auto 5%;}
	.percent{font-size: 11px;}
	.percents{font-size: 11px;margin: 5% 0;}
}
@media screen and (min-width:768px){
    .appInfo{top:18px;}
}
        .app-contents{width:100%;margin:0 auto;}
        /* .footer{bottom:0;position: absolute;} */
        .app-contents img{display:block;width:100%;height:100%;}
        .qimo8{ overflow:hidden; width:100%;background: #ffbd42;color:red;font-size:14px;}
		.qimo8 .qimo {/*width:99999999px;*/width:8000%; height:30px;}
		.qimo8 .qimo div{ float:left;}
		.qimo8 .qimo ul{float:left; height:30px; overflow:hidden; zoom:1; }
		.qimo8 .qimo ul li{float:left; line-height:30px; list-style:none;}
		.fenx{margin-top: 15%;text-align: center;}
		.fenx span{color:#fdbc40;width:90%;margin-left:5%;display: block;}
		.fenx span img{display:block;width:100%;}
		.fenxBtn{display: block;width:100%;text-align: center;font-size:14px;}
		.fenxBtn img{display: block;width:50%; margin: 0 auto;}
		.fenxBtn span{color:black;}
</style>
</head>
<body>
	 <div class="app-wrapper" id="wrapper">
        <div class="app-content" >
		<div class="app-contents top" id="apfs">
			<img src="../images_czt/cztmai.png" alt="" />
		</div>
		<a id="a_more" class="moreInfo" onclick="_hmt.push(['_trackEvent', 'nav', 'click', 'weidian_more'])" href="${ctx}/user_options/wqqInfoUp.htm">查看更多详情</a> 
		<div id="scrollobj" class="qimo8">
			<div class="qimo">
				<div id="demo1">
					<ul>
						<li>上期iphone6s plus获奖得主：<i>136****4118</i>,关注【小合超级合伙人联盟】官方微信查看详情&nbsp;&nbsp;</li>
						<li>上期iphone6s plus获奖得主：<i>136****4118</i>,关注【小合超级合伙人联盟】官方微信查看详情&nbsp;&nbsp;</li>
						<li>上期iphone6s plus获奖得主：<i>136****4118</i>,关注【小合超级合伙人联盟】官方微信查看详情&nbsp;&nbsp;</li>
						<li>上期iphone6s plus获奖得主：<i>136****4118</i>,关注【小合超级合伙人联盟】官方微信查看详情&nbsp;&nbsp;</li>
						<li>上期iphone6s plus获奖得主：<i>136****4118</i>,关注【小合超级合伙人联盟】官方微信查看详情&nbsp;&nbsp;</li>
					</ul>
				</div>
				<div id="demo2"></div>
				</div>
			</div>
			<script type="text/javascript">
				var demo = document.getElementById("scrollobj");
				var demo1 = document.getElementById("demo1");
				var demo2 = document.getElementById("demo2");
				demo2.innerHTML=document.getElementById("demo1").innerHTML;
				function Marquee(){
					if(demo.scrollLeft-demo2.offsetWidth>=0){
						demo.scrollLeft-=demo1.offsetWidth;
					}
					else{
						demo.scrollLeft++;
					}
				}
				var myvar=setInterval(Marquee,30);
				demo.onmouseout=function (){myvar=setInterval(Marquee,30);}
				demo.onmouseover=function(){clearInterval(myvar);}
			</script>
            <div class="app-img onepage" >
            	<div class="jindu">
            		<p class="percent">股票大盘是涨还是跌(2016.3.16-2016.3.09)</p>
            		<div class="vsjind">
            			<div class="lastA">
	            			<div class="redbar">
	            				<img src="../images_czt/redbar.png" alt="" />
	            			</div>
	            			<div class="greenbar">
	            				<img id="hidImg" src="../images_czt/greenbar.png" alt="" />
	            			</div>
	            			<div id="vs" class="vs">
	            				<img src="../images_czt/vs.png" alt="" />
	            			</div>
	            		</div>
	            		<div class="cztnum">
	            				<span id="cztLft" class="red left" >${upPercent}%</span><span id="cztRgt" class="green right">${dropPecent}%</span>
	            			</div>
	            	</div>
	            	<a class="appTxt redtxt" id="appTxt" onclick="_hmt.push(['_trackEvent', 'nav', 'click', 'weidian_zhang'])" href="http://weidian.com/i/1721128834?wfr=c"><span id="redz" class="buy"><img src="../images_czt/maiBtnRed.png"/></span><span id="redSy" class="sy">最大收益170%</span></a>
	                <a class="appTxt appTxts" id="appTxts" onclick="_hmt.push(['_trackEvent', 'nav', 'click', 'weidian_die'])"  href="http://weidian.com/i/1721129266?wfr=c"><span id="greenD" class="buy"><img src="../images_czt/maiBtn.png"/></span><span id="greenSy" class="sy">最大收益110%</span></a>
            	</div> 
            	<div class="clear"></div>
            </div>
            <div class="daojis">
					<p class="percents"><i id="mjjz">募集截止时间还有</i><span><i id="tm1"></i></span></p> 
            </div>
            <div class="fenx">
				<span class="fenxIco"><img src="../images_czt/fxBtn.png"/></span>
			</div>
            <div class="app-contents footer">
				<img src="../images_czt/cztmaiFooter.png" alt="" />
			</div>
			<input id="appId" type="hidden" value="${appId}"/>
			<input id="timestamp" type="hidden" value="${timestamp}"/>
			<input id="nonceStr" type="hidden" value="${nonceStr}"/>
			<input id="signature" type="hidden" value="${signature}"/>
        </div>
    </div>
</body>
</html>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script language="javascript" type="text/javascript">	 
//通过config接口注入权限验证配置
var appId=$("#appId").val();
var timestamp=$("#timestamp").val();
var nonceStr=$("#nonceStr").val();
var signature=$("#signature").val();
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appId, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
		//判断当前客户端版本是否支持指定JS接口
		wx.checkJsApi({
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		        // 以键值对的形式返回，可用的api值true，不可用为false
		        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    }
		});
		
		//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		wx.onMenuShareTimeline({
		    title: '10元抢iPhone6s猜股市涨跌', // 分享标题
		    desc: '', // 分享描述
		    link: 'https://www.hhr360.com/user_options/wqqWeiDianIndex.htm', // 分享链接
		    imgUrl: 'https://www.hhr360.com/images_czt/wei.png', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		//获取“分享给朋友”按钮点击状态及自定义分享内容接口
		wx.onMenuShareAppMessage({
		    title: '10元抢iPhone6s猜股市涨跌', // 分享标题
		    desc: '', // 分享描述
		    link: 'https://www.hhr360.com/user_options/wqqWeiDianIndex.htm', // 分享链接
		    imgUrl: 'https://www.hhr360.com/images_czt/wei.png', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});	  
})	 
</script>
<script language="javascript" type="text/javascript">
	var cztLft=$("#cztLft").text();
	var cztRgt=$("#cztRgt").text();
	
	if(cztLft=="100%"){
		$("#hidImg").hide(1);
	}else if(cztLft=="0%"&&cztRgt=="0%"){
		$("#hidImg").hide(1);
		$(".redbar").width(cztLft);
	}
	else{
		$(".redbar").width(cztLft);
		$(".greenbar").width(cztRgt);
	}
	$(".vs").css("left",cztLft);

	var p0=document.getElementById('ljqg')
	 p0.style.fontSize=(document.body.clientWidth*0.05)+"px";
	var p1=document.getElementById('syTime')
	 p1.style.fontSize=(document.body.clientWidth*0.05)+"px";
   var p2=document.getElementById('appTxt')
	 p2.style.left=(document.body.clientWidth*0.3)+"px";
	 p2.style.fontSize=(document.body.clientWidth*0.05)+"px";
	  var p3=document.getElementById('vs');
	  p2.style.left=$(".redbar").width(cztLft);
</script>	 
<script language="javascript" type="text/javascript">
var NowTime=new Date(${newDate});// 服务器当前时间
GetRTime();  
function GetRTime(){     
    var EndTime= new Date("March 09 12:00:00 2016"); //截止时间
    var nMS=EndTime.getTime() - NowTime.getTime();     
    var d=Math.floor(nMS/(1000 * 60 * 60 * 24));     
    var h=Math.floor(nMS/(1000*60*60)) % 24;     
    var m=Math.floor(nMS/(1000*60)) % 60;     
    var s=Math.floor(nMS/1000) % 60; 
    if(d>=0){     
        $("#tm1").text(d + "天" + h + "小时" + m + "分" + s + "秒");
        /* $("#appTxt").attr("href", "javascript:void(0)");
        $("#appTxts").attr("href", "javascript:void(0)");
        $("#appTxt").attr("onclick", "Pause()");
        $("#appTxts").attr("onclick", "Pause()");
        $("#mjjz").text("");
        $("#tm1").text("募集结束，暂停销售"); */ 
    }
	if(nMS<=0){
    	$("#appTxt").attr("href", "javascript:void(0)");
        $("#appTxts").attr("href", "javascript:void(0)");
        $("#appTxt").attr("onclick", "Pause()");
        $("#appTxts").attr("onclick", "Pause()");
        $("#mjjz").text("");
        $("#tm1").text("募集结束，暂停销售");   
    }    
    NowTime = new Date(NowTime.valueOf() + 100);  
    setTimeout("GetRTime()",100);    
}  

function Pause(){
	sureInfo("确定","暂停销售","提示");
}

$(function(){
	$.post("${ctx}/user_options/upDownPercent.htm?contentsId=42",null,function(data){
		var json=eval('('+data+')');
		$("#cztLft").text(json.upPercent+"%");
		$("#cztRgt").text(json.dropPecent+"%");
		
		var cztLft=$("#cztLft").text();
		var cztRgt=$("#cztRgt").text();
		if(cztLft=="100%"){
			$("#hidImg").hide(1);
		}else if(cztLft=="0%"&&cztRgt=="0%"){
			$("#hidImg").hide(1);
			$("#vs").hide(1);
			$(".redbar").width(cztLft);
		}else{
			$(".redbar").width(cztLft);
			$(".greenbar").width(cztRgt);
		}
		$(".vs").css("left",cztLft);
	})
})
</script>
