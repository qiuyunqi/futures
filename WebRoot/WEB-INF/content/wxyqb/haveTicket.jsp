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
<meta name="mobile-web-app-capable" content="yes">
<meta name="google" content="notranslate">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="yes">
<meta name="x5-fullscreen" content="true">
<meta name="browsermode" content="application">
<meta name="x5-page-mode" content="app">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－我有券</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
input[type=checkbox] {
	visibility: hidden;
}
input ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #646473;
		}
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #646473;
		}
input::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #646473;
		}
input:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #646473;
}
.infoLi{width:100% !important;margin:0 !important;background: #fff;}
.lineTx, .listTiBtn{background: #fff;margin:0 !important;}
.uploadTxt{height:60px;line-height: 60px;color:#000 !important;padding-left: 12px;}
.imgWidt{border-top: 1px solid #ededed;border-bottom: 1px solid #ededed;}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div>
		         <!-- 上传begin -->
				    <form id="uploadForm">
					<div class="containImg">
						<div class="infoLi">
							<div class="listTit lineTx ">
								<span class="uploadTxt" style="font-size:14px">请上传股票账户截图</span>
								<a class="imgWidt lineTx" href="javascript:void();" id="imgUpload">
									<c:if test="${empty session.srcId}">
										<img class="imgWidt" src="../images_new/wxLoad1.png"/>
									</c:if>
									<c:if test="${not empty session.srcId}">
										<img class="imgWidt" src="${session.srcId}"/>
									</c:if>
									
								</a>
							</div>
							<div class="listTit lineTx ">
								<span class="uploadTxt" style="font-size:14px">上传股票账户截图范例 </span>
								<a id="upLoadImg" class="imgWidt lineTx">
									<img class="imgWidt" src="../images_new/wxLoad2.png" style="border-bottom:0;"/>
									<span>点击查看大图</span>
								</a>
							</div>
							<input id="serverId" type="hidden"/>
							<input id="appId" type="hidden" value="${appId}"/>
							<input id="timestamp" type="hidden" value="${timestamp}"/>
							<input id="nonceStr" type="hidden" value="${nonceStr}"/>
							<input id="signature" type="hidden" value="${signature}"/>
						</div>
					</div>
					</form>
					<!-- 大图 -->
					<div class="hide bigImg">
						<!-- <img class="clos" style="margin: 0;position: fixed;top: 0;right:5%;" src="../images_new/clos.png"/> -->
						<img class="imgWidtBig" style="margin: 0;position: fixed;top: 50%;left:5%;" src="../images_new/wxLoad2.png"/>
					</div>
					 <!-- 上传end -->
		        <form action="" method="post">
		        	<table class="havticketInfo" cellpadding="0" cellspacing="0" width="100%" border="0">
					      <tr>
					      	<th>开户券商：</th>
					      	<td><input type="text" name="openEquity" /></td>
				         </tr>	
					      <tr>
					      	<th>营业部：</th>
					      	<td><input type="text" name="salesDept" /></td>
				         </tr>	
					      <tr>
					      	<th>账户类型：</th>
					      	<td>
					      		<select name="accountType">
								   <option value="1" style="width:200px">普通账户</option>
								   <option value="2" style="width:200px">融资融券</option>
								   <option value="3" style="width:200px">信用账户</option>
								 </select>
					      	</td>
				         </tr>	
					      <tr>
					      	<th>收益模式：</th>
					      	<td>
					      		<select name="profitModel">
								   <option value="0" style="width:200px">稳定收益</option>
								   <option value="1" style="width:200px">保本分成</option>
								   <option value="2" style="width:200px">固收+分成</option>
								 </select>
					      	</td>
				         </tr>	
					      <tr>
					      	<th>收益分配：</th>
					      	<td><input class="syfp"  type="text" name="availableSplit" placeholder="我要收益的10%"/><span class="frgt"></span></td>
				         </tr>	
					      <tr>
					      	<th>资金账号：</th>
					      	<td><input type="text" name="capitalAccount" /></td>
				         </tr>	
					     <!--  <tr>
					      	<th>资金密码：</th>
					      	<td><input type="text" name="capitalPassword" /></td>
				         </tr>	 -->
					      <tr>
					      	<th>留言：</th>
					      	<td><textarea rows="2" name="message" placeholder="限25字" ></textarea></td>
				         </tr>	
				    </table>
				   
				    <div class="checkYqb">
					    <div class="monyLogBox">
							<div class="checkboxFive">
								<input type="checkbox" value="1" id="checkboxFiveInput" name="" checked="checked"/>
								<label for="checkboxFiveInput"></label>
							</div>
							<span>我已阅读并签订</span>
							<a class="monyCheckTit" href="javascript:void(0);" onclick="window.open ('https://www.hhr360.com/agreement_yqbClear.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')">《余券宝合作协议》</a>
						</div>
					</div>
					<a class="fullAstyYqb" >提交</a>
		        </form>
		        </div>
			</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<!-- 复制apply页面js begin -->
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
		
		$(".bigImg").click(function(){
			$(this).hide();
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
<!-- 复制apply页面js end -->

<script type="text/javascript">

/* $(function() {
	if (${data == 0}) {
		location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toHaveTicket#wechat_redirect";
	}
}); */
	function check() {
		var openEquity = $("input[name='openEquity']").val();
		var salesDept = $("input[name='salesDept']").val();
		var accountType = $("select[name='accountType']").val();
		var profitModel = $("select[name='profitModel']").val();
		var availableSplit = $("input[name='availableSplit']").val();
		var message = $("textarea[name='message']").val();
		var picValue1 = $("#imgUpload").find("img").attr("src");
		if(picValue1 == null || picValue1 == ""){
			 layer.open({
				 content: "请上传图片",
				 btn: ["确定"],
				 shadeClose: false,
				 yes: function(){
					 layer.closeAll();
				 }
			 });
	         return null;
		}
		
		if (!openEquity) {
			layer.open({
 			    content: "请填写开户劵商",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		if (!salesDept) {
			layer.open({
 			    content: "请填写营业部",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		if (!accountType) {
			layer.open({
 			    content: "请选择账户类型",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		if (!profitModel) {
			layer.open({
 			    content: "请选择收益模式",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		if (!availableSplit) {
			layer.open({
 			    content: "请填写收益分配",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		
		if (message && message.length > 25) {
			layer.open({
 			    content: "留言最多25个字，请减少一些",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;			
		}
		return true;
	}
	
	$(".fullAstyYqb").click(function () {
		var openEquity = $("input[name='openEquity']").val();
		var salesDept = $("input[name='salesDept']").val();
		var accountType = $("select[name='accountType']").val();
		var profitModel = $("select[name='profitModel']").val();
		var availableSplit = $("input[name='availableSplit']").val();
		var capitalAccount = $("input[name='capitalAccount']").val();
		var message = $("textarea[name='message']").val();
		var serverId = $("#serverId").val();
		if (check()) {
			$.post("${ctx}/wxyqb/saveTicket.htm", {serverId: serverId, openEquity: openEquity, salesDept: salesDept, accountType: accountType, message: message,
					profitModel: profitModel, availableSplit: availableSplit, capitalAccount: capitalAccount}, function(data){
						if (data == 1) {
							layer.open({
				 			    content: "请填写开户劵商",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						
						if (data == 2) {
							layer.open({
				 			    content: "请填写营业部",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						if (data == 3) {
							layer.open({
				 			    content: "请选择账户类型",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						
						if (data == 4) {
							layer.open({
				 			    content: "请选择收益模式",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						
						if (data == 5) {
							layer.open({
				 			    content: "请填写收益分配",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						
						if (data == 6) {
							layer.open({
				 			    content: "留言最多25个字，请减少一些",
				 			    btn: ["确定"],
				 			    shadeClose: false,
				 			    yes: function(){
				 			        layer.closeAll();
				 			    }
				 			});
							return null;			
						}
						if (data == 7) {
							location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toHaveTicket#wechat_redirect";
						}
						if (data == 8) {
							location.href="${ctx}/wxyqb/haveIntroduceSuccess.htm";
						}
			});
		} 
	});

</script>
</html>