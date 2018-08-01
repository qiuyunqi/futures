<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.bell{cursor: pointer;display:block;width:15px;padding-right: 15px;padding-top: 5px;}
.message{position: relative;}
.messageNum{font-size:10px; display: block;width:16px;height:15px;background: url("../images_hhr/message.png") no-repeat;text-align: center;position: absolute;z-index: 3;top:1px;margin-left:10px;line-height: 15px;}
</style>
<div id="hhead">
<div class="hhead">
<ul class="navind">
  <li class="navli">
	  <a href="javascript:void(0)">010-53320537</a>
  </li>
  <li class="navli">
	  <a href="${ctx}/about">关于</a>|
  </li>
  <li class="navli">
    <a href="javascript:void(0)" onclick="toBbs();">论坛</a>|
  </li>
  <li class="navli">
	  <a href="${ctx}/index_guide/softwareDownload.htm">软件下载</a>|
  </li>
  <li class="navli">
	  |<a href="${ctx}/help">帮助中心</a>|
  </li>
  <c:if test="${!empty fuUser}">
  <li class="navli" id="message">
  	 <a href="javascript:void(0)" style="display:block;" onclick="toMoneyDetail()">
  		<img class="bell" src="../images_hhr/bell.png">
     	<c:if test="${counts>0}"><span class="messageNum">${counts}</span></c:if>
     </a>
  </li>
   <li class="navli allbd" id="allbd">
  	 <span class="nh">您好，</span>
  	 <a id="nickName" class="pptv" href="${ctx}/user_center/centerIndex.htm">${empty fuUser.nickName?'佚名':fuUser.nickName}
      <span id="down"><img src="../images_hhr/down.png"></span>
      <span id="up"><img src="../images_hhr/up.png"></span>
     </a>
      <!-- 个人信息层，默认隐藏 -->
	  <ul id="newDiv" class="gerenxl">
		  	<div class="grwrap">
				<li class="grup">
					<a class="aim" href="javascript:void(0)" style="margin-top:18px;"><img style="width:60px;height:60px;" src="${empty fuUser.userAvatar?'../images_hhr/default.png':fuUser.userAvatar}"></a>
					<div class="grtxt">
						<p>账户余额 <span style="color:red"><fmt:formatNumber value="${fuUser.accountBalance}" pattern="###,###,###,##0.00"/></span>元</p>
						<p>可用点券 <span style="color:red"><fmt:formatNumber value="${fuUser.integral}" pattern="###,###,###,##0.00"/></span>点</p>
						<div class="grta">
							<ul>
							<li><a href="${ctx}/user_recharge/onlinePay.htm">充值</a></li>
			 				<li><a href="${ctx}/user_draw_money/drawMoney.htm">提现</a></li>
			 				<li><a href="${ctx}/user_program/addPeizi.htm?programType=2">MOM</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li class="grmid">
					<ul>
				 		<li><a href="${ctx}/user_center/personInfo.htm">个人信息</a></li>
				 		<li><a href="${ctx}/user_center/safeInfo.htm">安全信息</a></li>
					</ul>
				</li>
				<li class="grdw">
				 	<a href="${ctx}/user_center/centerIndex.htm" >个人中心</a>
					<a href="javascript:void(0)" onclick="logout();" style="margin-left:125px;">退出</a>
				</li>
			</div>
	  	</ul> 
	  </li>
  </c:if>
  
  <c:if test="${empty fuUser}">
	  <li class="navli">
		  <a href="javascript:void(0);" onclick="lgInfo(0);">登录</a>
		  <a href="${ctx}/register">注册</a>
	  </li>
  </c:if>
</ul>
</div>
</div>
<script type="text/javascript">
$("#down").hide(); 
 $(document).ready(function(){
	$("#nickName").mouseover(function(){
		$("#newDiv").show();
		$("#down").show();
		$("#up").hide();
	})
	$("#newDiv").mouseover(function(){
		$("#newDiv").show();
		$("#down").show();
		$("#up").hide();
	})
	$(".allbd").mouseout(function(){
		$(this).children("ul").hide();
		$("#down").hide();
		$("#up").show();
	})
});     


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
        window.setTimeout(function(){location.href="${ctx}/login";},1000); 
	});
}
function toBbs(){
    window.open('http://bbs.hhr360.com');
}
//刷新资金变更消息数目
$(function(){
	$.post("${ctx}/user_login/fushMoneyDetail.htm",null,function(data){
	})
})
//跳转到个人中心账户预览，然后清零资金变更消息
function toMoneyDetail(){
	$.post("${ctx}/user_login/toMoneyDetail.htm",null,function(data){
		location.href="${ctx}/user_money_detail/moneyDetailList.htm";
	})
}


//百度统计脚本
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?cb76fdd0655f5b05731cbe36b6b46309";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();
</script>


