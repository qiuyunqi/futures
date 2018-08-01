<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<div class="footYqb">
	    <ul>
	    	<li><a id="homeYqb" href="${ctx}/wxyqb/newIndex.htm"><i class="homeYqb"></i>主页</a></li>
	    	<li><a id="feedYqb" href="${ctx}/wxyqb/forTicket.htm"><i class="feedYqb"></i>供券</a></li>
	    	<li><a id="findYqb" href="${ctx}/wxyqb/findTicket.htm"><i class="findYqb"></i>找券</a></li>
	    	<li><a id="mineYqb" href="${ctx}/wxyqb/meIndex.htm"><i class="mineYqb"></i>我的</a></li>
	    </ul>
	    <input id="appId" type="hidden" value="${appId}"/>
		<input id="timestamp" type="hidden" value="${timestamp}"/>
		<input id="nonceStr" type="hidden" value="${nonceStr}"/>
		<input id="signature" type="hidden" value="${signature}"/>
</div>
<script>
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?cb76fdd0655f5b05731cbe36b6b46309";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();
</script>

<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script language="javascript" type="text/javascript">
//alert('https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toNewIndex_${fuUser.invitationCode}#wechat_redirect');
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
		    title: '余券宝融资融券平台, 找券找资金，金融信息对接，上余券宝', // 分享标题  显示的内容全靠这个
		    desc: '找券找资金，金融信息对接，上余券宝', // 分享描述  根本没有用
		    link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toNewIndex_${fuUser.invitationCode}#wechat_redirect', // 分享链接
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
		    title: '余券宝融资融券平台', // 分享标题
		    desc: '找券找资金，金融信息对接，上余券宝', // 分享描述
		    link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toNewIndex_${fuUser.invitationCode}#wechat_redirect', // 分享链接
		    imgUrl: 'https://www.hhr360.com/images_czt/wxyqb.jpg', // 分享图标
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