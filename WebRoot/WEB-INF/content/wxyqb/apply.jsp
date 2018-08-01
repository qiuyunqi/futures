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
<title>${title}－余券宝</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/newwx/newwx.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.addMyBtn {background: #999; color: #000; pointer-events: none;}
</style>
</head>
<body>
	<div class="sucContain">
		<form id="uploadForm">
		<div class="containImg">
			<div class="infoLi">
				<div class="listTit lineTx bgColor">
					<span class="uploadTxt">请上传股票账户截图</span>
					<a class="imgWidt lineTx" href="javascript:void();" id="imgUpload">
						<c:if test="${empty session.srcId}">
							<img class="imgWidt" src="../images_new/wxLoad1.png"/>
						</c:if>
						<c:if test="${not empty session.srcId}">
							<img class="imgWidt" src="${session.srcId}"/>
						</c:if>
						
					</a>
				</div>
				<div class="listTit lineTx bgColor">
					<span class="uploadTxt">上传股票账户截图范例 </span>
					<a id="upLoadImg" class="imgWidt lineTx">
						<img class="imgWidt" src="../images_new/wxLoad2.png"/>
						<span>点击查看大图</span>
					</a>
				</div>
				<input id="serverId" type="hidden"/>
				<input id="appId" type="hidden" value="${appId}"/>
				<input id="timestamp" type="hidden" value="${timestamp}"/>
				<input id="nonceStr" type="hidden" value="${nonceStr}"/>
				<input id="signature" type="hidden" value="${signature}"/>
				<div class="hy">
					<input id="isAgree" name="isAgree" type="checkbox" value="1" checked="checked" border="0"/>我已阅读并签订
					<!-- <a href="javascript:void(0);" onclick="window.open ('https://www.hhr360.com/agreement_yqbFb.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" class="jkxy">
						《余券宝合作协议》
					</a> -->
					<a href="javascript:void(0);" onclick="window.open ('https://www.hhr360.com/agreement_yqbClear.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" class="jkxy">
						《余券宝合作协议》
					</a>
				</div>
				<a class="fullAsty" onclick="saveApply();">提交账户</a>
			</div>
		</div>
		</form>
		<!-- 大图 -->
		<div class="hide bigImg">
			<img class="clos" src="../images_new/clos.png"/>
			<img class="imgWidtBig" src="../images_new/wxLoad2.png"/>
		</div>
		<!-- 绑定手机号弹层，目前被隐藏-->
		<div class="hide fuchen" id="show_phone">
			<form action="bindPhone">
			<div class="fucBg">
				<div class="fucTxt">
					<div class="fucBgHead"><img class="imgWidt" src="../images_new/wxNumber.png"/></div>
					<span class="fucBgTip">为方便交易团队与您联系，请填写手机号</span>
				</div>
				<div class="fucBgTxt">
						<div class="userIn userNum">
			           			<label id="telNum"> 手机号：</label>
			           			<input id="phone" name="phone" class="telephone" type="text"/>
			           		</div>
			           		<div class="userIn userpass">
			           			<label> 验证码：</label>
			           			<div class="yanzhengAll">
			           				<input id="code" name="code" class="yanzheng" type="text"/>
			           				<input id="msgbtn" class="getNum" type="button" value="获取验证码"/>
			           			</div>
			           		</div>
						<div class="btnAlls">
						  	<a class="wxCancel">取消</a>
						  	<a class="wxSure">确定</a>
						</div>
				</div>
			</div>
			
			</form>
		</div>
	</div>
</body>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript">
//绑定手机号弹窗隐藏
	$(".fuchen").hide();
	//点击查看大图
	$(".bigImg").hide();
	$(function(){
		$("#upLoadImg").click(function(){
			$(".bigImg").show();
		});
		
		$(".clos").click(function(){
			$(".bigImg").hide();
		});
	});
</script>

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
    jsApiList: ['chooseImage', 'uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
		//判断当前客户端版本是否支持指定JS接口
		wx.checkJsApi({
		    jsApiList: ['chooseImage', 'uploadImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		        // 以键值对的形式返回，可用的api值true，不可用为false
		        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		        if(!res["checkResult"]["chooseImage"]) {
		        	alert("当前客户端不支持上传图片");
		        }
		    }
		});
});
$("#imgUpload").click(function() {
	wx.chooseImage({
	    count: 1, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        $("#imgUpload").find("img").attr("src", localIds);
			$.post("${ctx}/wxyqb/saveImage.htm", {"srcId": localIds.toString()}, function(d) {
			
			});
	       	wx.uploadImage({
				localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
			  	isShowProgressTips: 1, // 默认为1，显示进度提示
			  	success: function (res) {
			    	var serverId = res.serverId; // 返回图片的服务器端ID
			    	$("#serverId").val(serverId);
			  	},
			  	fail: function (res) {
			  		alert(res);
			  	}
			});
	    }
	});  
});
</script> 

<script type="text/javascript">
function saveApply(){
	var picValue1 = $("#imgUpload").find("img").attr("src");
	if(picValue1 == null || picValue1 == ""){
		 sureInfo("确定","请选择广告图片","提示");
         return null;
	}
	var serverId = $("#serverId").val();
	$.post('${ctx}/wxyqb/saveYqb.htm', {"serverId" : serverId}, function(d){
		if(d == 1){
	   		$("#show_phone").show();   	
			return null;
       	}
		if(d == 2){
			sureInfo("确定","上传图片失败","提示");
			return null;
		}
		if(d == 3){
			location.href = "${ctx}/wxyqb/saveAccount.htm";
		}
	});
}

var times = 60;
function timeCount() {
	times = times-1;
	$("#msgbtn").val("剩余"+times+"s");
	$("#msgbtn").addClass("addMyBtn");
	$("#msgbtn").css({"background" : "#999", "color" : "#000"});
	if(times == 0) {
		clearTimeout(t);
		$("#msgbtn").val("重新获取验证码");
		times = 60;
		$("#msgbtn").removeClass("addMyBtn");
		$("#msgbtn").css({"background" : "#ffebb7 none repeat scroll 0 0", "color" : "#ff7616"});
	}else {
		t = setTimeout("timeCount()",1000);
	}
}


$(".wxSure").click(function() {
	var phone = $("input[name='phone']").val();
	var code = $("input[name='code']").val();
	if("" == phone || null == phone) {
		alert("请填写正确的手机号码");
		return null;
	}
	if("" == code || null == code) {
		alert("请填写正确的验证码");
		return null;
	}
	$.post("${ctx}/wxyqb/bindPhone.htm", {"phone": phone, "phoneCode": code}, function(data) {
		if(data == 0) {
			alert("请填写手机号码");
			return ;
		}
		
		if(data == 1) {
			alert("请填写验证码");
			return ;
		}
		if (data == 2) {
			alert("请重新发送验证码 ");
			return ;
		}
		if (data == 3) {
			alert("验证码输入错误 ");
			return ;
		}
		if (data == 4) {
			saveApply();
			return ;
		}
		if (data == 5) {
			alert("非法路径, 请联系管理员");
			return ;
		}
	});
});

$("#msgbtn").click(function() {
	var phone = $("input[name='phone']").val();
	if("" == phone || null == phone) {
		alert("请填写手机号码");
		return null;
	}
	
	timeCount();
	// 发送验证码
	$.post("${ctx}/wxyqb/sendCode.htm", {"phone": phone}, function(data) {
		if(data == 0) {
			alert("请填写手机号码");
			return ;
		}
		
		if(data == 1) {
			alert("请联系管理员解冻你的封印");
			return ;
		}
		if (data == 2) {
			alert("您点击过于频繁, 请稍后再试");
			return ;
		}
	});
});

$(".wxCancel").click(function() {
	$("#show_phone").hide();
});
</script>
</html>