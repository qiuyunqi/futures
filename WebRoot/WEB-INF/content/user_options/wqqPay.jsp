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
<title>${guessType==1?'我看涨':guessType==0?'我看跌':''}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:100%;overflow: hidden; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fff;height:100%;overflow: hidden; }
input{border:none;background: #fff;}
input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
}
i{font-style: normal;}
.app-wrapper{width: 100%;height: 100%;margin:0 auto;}
.app-content{width:100%;height:100%;position: absolute; left: 0; top: 0;background:#ededed;}
label{color:#ff516a;}
.moneyInfo{width:100%;background: #fff;padding: 5% 0 0;}
.bottom-content{width:100%; padding: 5% 0 0;}
.buyMony{font-size:12px;}
.buyMony span{float:right;color:#fdbc40;padding:5px 0;}
.monyNum{font-size:10px;color:#666;margin-top:5%;}
.monyNum span{display:block;}
.monyNumInfo{border:1px solid #dfdfdf;margin-top:2%;}
.monyNumInfo input{padding:5px 0;font-size: 15px;}
#match{width:30%;color:#999;padding:5px 0;font-size:12px;}
.onShow{width: 50%;text-align: left;background-position:0 50% !important;position: relative;left:17%;}
.onError{width: 50%;text-align: left;background-position:0 50% !important;position: relative;left:17%;}
.onFocus{width: 50%;text-align: left;background-position:0 50% !important;position: relative;left:17%;}
.onCorrect{width: 50%;text-align: left;background-position:0 50% !important;position: relative;left:17%;}
.telephone{padding:5px 0;font-size:12px;}
.addNum{width:15%;border-right:1px solid #dfdfdf;}
.minsNum{width:15%;border-left:1px solid #dfdfdf;float: right;}
.userInfo{width:90%; margin-left: 5%;clear: both;background: #fff; padding:2% 0; border-bottom: 1px solid #dfdfdf;}
.userInfo label{display:block;/* width:23%; */float:left;padding: 5px 5px 2% 0;border-bottom: 6px solid #fff;}
.cztBox label{display:block;/* width:23%; */float:left;padding: 5px 5px 2% 0;border-bottom: 6px solid #fff;}
.userNum input{width:70%;color:#999;}
.getNum{font-size:8px;font-size: 11px;background:#76cbf5;float: right;color:white;padding:5px 3px;}
.agree{padding:0 5%;clear:both;}
.yanzheng{width:50%;padding: 5px 0;font-size:12px;}
.ban{height:5%;background: #fff;}
.zf{display: block;font-size:20px;padding:8px;border:1px solid #dfdfdf;margin:5% auto;background: #ff516a;color:#fff; width: 90%;}
.footer{width:90%;margin:25% 5% 0;}
.footer img{display: block; width: 100%;}
.title{font-size:20px;background:#ff516a;padding:2% 0;text-align: center;color:#fff; }
.backIndex{float: left;color:#fff;text-indent: 3%;}
.cztBox{width:90%;margin-left:5%;background: #fff none repeat scroll 0 0;clear: both;padding: 2% 0;}
@media screen and (max-width:240px){
   .userInfo{font-size: 8px;}
   .getNum{font-size: 8px;}
   .title{font-size:12px;}
   .ban{height:1%;}
}
</style>
</head>
<body>
<form id="cztForm" method="post">
	 <div class="app-wrapper" id="wrapper">
        <div class="app-content" >
        <div class="title"><a class="backIndex" href="${ctx}/user_options/wqqIndex.htm"><</a>${guessType==1?'我看涨':guessType==0?'我看跌':''}</div>
           <div class="moneyInfo">
           	<input id="invitationCode" type="hidden" value="412117108616"/>
	           		<div class="userInfo buyMony">
		           		<label >购买金额：</label><input type="text" id="match" name="match" onblur="num(this.value)" placeholder="10合币起购"/>
		           		<span id="shouyi">最高获利：<i id="syMoney">${guessType==1?120:guessType==0?'75':''}</i>%</span>
	           		</div>
	           		<div class="userInfo userNum">
	           			<label id="telNum">手机号：</label>
	           			<input id="phone" name="phone" class="telephone" type="text"/>
	           		</div>
	           		<div class="cztBox">
	           			<div class="onShow" id="phoneTip">请输入手机号</div>
	           		</div>
	           		<div class="userInfo userpass">
	           			<label>验证码：</label>
	           			<div class="yanzhengAll">
	           				<input id="code" name="code" class="yanzheng" type="text"/>
	           				<input id="msgbtn" class="getNum" type="button" value="获取语音验证码" onclick="onSend();_hmt.push(['_trackEvent', 'nav', 'click', 'get_code'])"/>
	           			</div>
	           		</div>
	           		<div class="cztBox">
	           			<div class="onShow" id="codeTip">请输入验证码</div>
	           		</div>
           </div>
           <div class="ban"></div>
           <div class="bottom-content">
           		<div class="agree"><input id="isAgree" style="float:left;" name="rememberAccount" type="checkbox" value="1" checked="checked"><span style="display:block;height:13px;line-height:13px;margin-left: 25px;">我已阅读并同意
	           		<a href="javascript:void(0);" onclick="window.open ('${ctx}/agreement_wqq.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');_hmt.push(['_trackEvent', 'nav', 'click', 'intro'])" class="jkxy">《定向委托投资管理协议》</a></span></div>
	           		<input id="zf" class="zf" type="button" value="立即支付" onclick="onPay();_hmt.push(['_trackEvent', 'nav', 'click', 'pay'])"/>
	           		<div class="footer"><img src="../images/cztFooter.png"/></div>
           </div>
        </div>
    </div>
    <input name="contentsId" type="hidden" value="42"/>
</form>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/formValidator-4.0.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/formValidatorRegex.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/style/validator.css"></link>
<script>
//提示框左移
var wid=$("#telNum").width();
$(".onShow").css("left",wid);

$(document).ready(function(){
	$.formValidator.initConfig({formID:"cztForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#phone").formValidator({onShow:"请输入手机号码",onFocus:"手机号码的格式必须正确",onCorrect:"手机号码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"手机号两边不能有空符号"},onError:"手机号不能为空"}).regexValidator({regExp:"^[1][0-9]{10}$",onError:"你输入的手机号码格式不正确"});
	$("#code").formValidator({onShow:"请输入语音验证码",onFocus:"输入您获取的语音验证码",onCorrect:"语音验证码已填"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"语音验证码两边不能有空符号"},onError:"语音验证码不能为空"});
});
	var p0=document.getElementById('zf')
	p0.style.fontSize=(document.body.clientWidth*0.05)+"px";
	
	//金额输入框失去焦点
	function num(v){
		var v=$("#match").val();
		if(!v || v<=0){
			$("#match").val(10);
		}
		if(v>0 && v<10){
			$("#match").val(10);
		}else if(v%10!=0){
		    var m = v%10;
		    var n=v-m;
		    m=m<5?0:10;
		    n=n+m;
		    $("#match").val(n);
	    }
	}
	
	//发送短信验证码
	var sending=false;
    var start=60;
	function onSend(){
		var phone=$("#phone").val();
		if(!phone){
			$("#phoneTip").attr("class","onError").html("请输入手机号码");
			$("#phone").focus();
			return false;
		}
		var reg = /^1\d{10}$/;
		if (!reg.test($("#phone").val())){
			$("#phoneTip").attr("class","onError").html("你输入的手机号码格式不正确");
			$("#phone").focus();
			return false;
   		}   
   		$.post("${ctx}/user_options/saveSendCode.htm",{"phone":phone},function(d){
   			if(d==-1){
   				$("#phoneTip").attr("class","onError").html("该手机号已经无效");
				$("#phone").focus();
				return false;
			}
			sending=true;
			setTimeout(countTime,1000);
			$("#msgbtn").css({color:'#ccc'});
			$("#msgbtn").val('60s后重试');
   		})
	}
	//短信计时
	function countTime(){
		start--;
		if(start<0){
			$("#msgbtn").css({color:'#fff'});
			$("#msgbtn").val("获取语音验证码");
			$("#msgbtn").attr("onclick", "onSend();");
			sending=false;
			start=60;
			return;
		}
		$("#msgbtn").val(start+"s后重试");
		setTimeout(countTime,1000);
		if(start>0){
			$("#msgbtn").attr("onclick", "");
		}
	}
	
	//立即支付
	function onPay(){
		var match=$("#match").val();
		if(!match){
			$("#match").val(10);
		}
		var f=$("#isAgree").attr("checked");
		if(f!='checked'){
			$.fancybox.open({
	 			href : '${ctx}/user_options/agreeInfoAjax.htm',
	 			type : 'ajax',
	 			padding : 5, 
 			});
			return false;
		}
		var phone=$("#phone").val();
		if(!phone){
			$("#phoneTip").attr("class","onError").html("请输入手机号码");
			$("#phone").focus();
			return false;
		}
		var reg = /^1\d{10}$/;
		if (!reg.test($("#phone").val())){
			$("#phoneTip").attr("class","onError").html("你输入的手机号码格式不正确");
			$("#phone").focus();
			return false;
   		}   
		var code=$("#code").val();
		if(!code){
			$("#codeTip").attr("class","onError").html("请输入语音验证码");
			$("#code").focus();
			return false;
		}
		$("#zf").attr({"disabled":"disabled"});
		var money=parseInt($("#match").val());
		var invitationCode=$("#invitationCode").val();
		$.post("${ctx}/user_options/checkBalance.htm",{"phone":phone,"code":code,"money":money,"invitationCode":invitationCode},function(d){
			if(d==-4){
				$("#phoneTip").attr("class","onError").html("该手机号已经无效");
				$("#phone").focus();
				return false;
			}
			if(d==-1){
				$("#phoneTip").attr("class","onError").html("手机号码错误，请重新输入！");
				$("#phone").focus();
				return false;
			}
			if(d==-2){
				$("#codeTip").attr("class","onError").html("验证码错误，请重新输入！");
				return false;
			}
			if(d==-3){
				$.post("${ctx}/user_options/saveOptionPay.htm",{"phone":phone,"money":money,"guessType":${guessType}},function(d){
				   	location.href="${ctx}/user_options/saveAliPay.htm?feeMoney="+money+"&optionsId="+d;
				});
				return false;
			}
			if(d==1){
				$.post("${ctx}/user_options/saveOptionPayWeb.htm",{"phone":phone,"money":money,"guessType":${guessType}},function(d){
			   		location.href="${ctx}/user_options/wqqSuccessPay.htm?money="+money; 	
				});
				return false;
			}
		})
	}
</script>