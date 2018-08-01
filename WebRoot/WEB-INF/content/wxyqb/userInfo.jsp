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
<title>${title}－个人信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div class="myList">
		        	<!-- 头像 -->
		        	<!-- <div class="userTx"><img src="../images_yqb/meTouX.png"/></div> -->
		        	<div class="userTx">
			        	<a href="javascript:void();" id="imgUpload">
							<c:if test="${empty fuUser.userAvatar}">
								<img src="../images_yqb/meTouX.png"/>
							 </c:if>
			            	<c:if test="${!empty fuUser.userAvatar}">
								<img src="${fuUser.userAvatar}"/>
							</c:if>
						</a>
					</div>
		            <input id="serverId" type="hidden"/>
					<input id="appId" type="hidden" value="${appId}"/>
					<input id="timestamp" type="hidden" value="${timestamp}"/>
					<input id="nonceStr" type="hidden" value="${nonceStr}"/>
					<input id="signature" type="hidden" value="${signature}"/>
					<!-- 头像end -->
		        	<table class="useInfo" cellpadding="0" cellspacing="0" width="90%" border="0">
					      <tr onclick="window.location.href='${ctx}/wxyqb/setName.htm?userId=${fuUser.id}'">
					      	<td class="usIf"><a><img class="meTx" src="../images_yqb/nic.png"/></a></td>
					      	<td class="blueCol smallSize">昵称</td>
					      	<td class="fltRgt smallSize"><span>${fuUser.nickName}</span><img class="lftJt" src="../images_yqb/mejtou.png"/></td>
				         </tr>	
					      <tr onclick="window.location.href='${ctx}/wxyqb/unwrapPhone.htm'">
					      	<td class="usIf"><img class="meTx" src="../images_yqb/number.png"/></td>
					      	<td class="blueCol smallSize">手机</td>
					      	<td class="fltRgt smallSize"><span>${fuUser.phone}</span><img class="lftJt" src="../images_yqb/mejtou.png"/></td>
				         </tr>	
					      <tr >
					      	<td class="usIf"><img class="meTx" src="../images_yqb/codeNum.png"/></td>
					      	<td class="blueCol smallSize">我的邀请码</td>
					      	<td class="fltRgt smallSize"><span>${fuUser.invitationCode}</span><img class="lftJt" src="../images_yqb/mejtou.png"/></td>
				         </tr>	
					     <!--  <tr>
					      	<td class="usIf"><img class="meTx" src="../images_yqb/pre.png"/></td>
					      	<td class="blueCol smallSize">绑定上级邀请人</td>
					      	<td class="fltRgt"><span style="visibility: hidden;">hidden</span><img class="lftJt" src="../images_yqb/mejtou.png"/></td>
				         </tr>	 -->
				    </table>
		        </div>
			</div>
		</div>
	</div>
	
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
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
	        if(localIds == null || localIds == ""){
	    		 alert("请选择头像图片！");
	             return null;
	    	}
	        $("#imgUpload").find("img").attr("src", localIds);
			$.post("${ctx}/wxyqb/setImageSrcId.htm", {"srcId": localIds.toString()}, function(d) {
			
			});
	       	wx.uploadImage({
				localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
			  	isShowProgressTips: 1, // 默认为1，显示进度提示       
			  	success: function (res) {
			    	var serverId = res.serverId; // 返回图片的服务器端ID
			    	$("#serverId").val(serverId);
			    	$.post('${ctx}/wxyqb/saveUserAvatar.htm', {"serverId" : serverId, "userId":'${fuUser.id}' }, function(d){
			    		if(d == 1){
			    			alert("头像上传失败！")
			    			return null;
			    		}
			    		if(d == 2){
			    			alert("设置成功！");
			    			window.location.href = "${ctx}/wxyqb/userInfo.htm";
			    		}
			    	});
			  	},
			  	fail: function (res) {
			  		alert(res);
			  	}
			});
	    }
	});  
});
</script> 