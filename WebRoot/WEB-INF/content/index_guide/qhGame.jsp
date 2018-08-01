<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－首届"小合杯"宣传</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
	.contaer{background:#198af2;overflow: hidden;border:0;min-width: 1050px !important;padding:0 !important;}
	.backgroud{margin:0 auto;}
	#hbanner{margin-bottom: 5px !important;}
	.download{position:absolute;left:50%;margin-left:-321px;top:37%;}
	.downban{height:1px;}
	.xiangqing{position:absolute;top:70.1%;left:31%;}
	.download img:first-child{margin-right:25px;}
</style>
</head>
<body style="background: #198af2;">
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
<div class="contaer">
			<div class="contaier">
				<div class="contaier_bod">
					<div class="backgroud">
						<img src="../images_hhr/webzhuan1k.jpg" height="100%" width="100%"/> 
						<a class="xiangqing" href="${ctx}/index_guide/qhInformation.htm"><img src="../images_hhr/webzhuanxiang.png"/></a>
						<div class="download"><a href="http://app.hhr360.com/pc/hhr_pc.zip"><img src="../images_hhr/webzhuan1Btn.png"/></a><a id="msgbtn" href="javascript:void(0);" onclick="saveInfo();"><img src="../images_hhr/webzhuancs.png"/></a></div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
var start=60;
var sub=false;
var sending=false;
function saveInfo(){
	if(sub)
		return;
	sub=true;
	if(sending)
		return
	jConfirm("确认参加“小合杯”模拟期指大赛？注意：报名在1分钟内不能重复操作！","操作提示",function(res){
		if(res){
			$.post("${ctx}/user_program/saveProgramGame.htm",null,function(d){
				sub=false;
				var json=eval('('+d+')');
				if(json.code==-1){
					jAlert("请登录您的账号！","提示",function(){
						$.fancybox.open({
				 			href : '${ctx}/user_login/userLoginAjax.htm?flag=1',
				 			type : 'ajax',
				 			padding : 0 ,
							scrolling:'no',
		 				});
					});
					return false;
				}
				if(json.code==-2){
					sureInfo("确定","系统参数还没有设置，请联系客服！","提示");
					return false;
				}
				if(json.code==-4){
					sureInfo("确定","周末不能参加报名！","提示");
					return false;
				}
				if(json.code==-5){
					sureInfo("确定","您已经报名了此次比赛！","提示");//（您的交易账户是"+json.tradeAccount+",密码是手机号后6位。）
					return false;
				}
				if(json.code==-6){
					sureInfo("确定","报名时间为：2015年9月15日~2015年10月14日！","提示");
					return false;
				}
				if(json.code==-7){
					sureInfo("确定","16:20~16:50服务器例行维护，该时间段不能报名！","提示");
					return false;
				}
				sureInfo("确定","报名申请成功，请等待短信通知！","提示");
			});
		}
	});
}

function countTime(){
	start--;
	if(start<0){
		sending=false;
		start=60;
		return;
	}
	setTimeout(countTime,1000);
}
</script>
