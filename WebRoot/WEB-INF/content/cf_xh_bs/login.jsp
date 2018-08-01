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
<title>${title}－注册报名</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/money.css" rel="stylesheet" type="text/css" />
<style>
 input{border:none;background: #fff;-webkit-appearance: none;}
        input[type="button"], input[type="submit"], input[type="reset"] {
            -webkit-appearance: none;
        }
input[type=checkbox] {
	visibility: hidden;
}
@media screen and (max-width:240px){
	.userReTitl{width: 55px; font-size: 10px;}
	.myReg{width: 35%;}
}
.monyCode {width : 70px; height: 38px;}
</style>
</head>
<body >
<form id="loginForm" name="loginForm">
<div class="moneyBody">
	<div class="moneyCont">
		<div class=" moneyMatter">
		  		<div class="moneyReg">
		  			<div class="moneyRgb">
		  				<a class="moneyRegZc actCol" href="javascript:void(0);">1.注册报名</a><a class="moneyLogi defaultCol" href="javascript:void(0);" style="margin-left:-5px;">2.股票开户</a>
		  			</div>
		  		</div>
		  		<div class="moneyDan clear">
					<div class="moneyTl">
						 <span class="userReTitl textCen">手机号码</span><div class="fl myReg"><input name="phone" id="phone"  type="text" placeholder="用户名/手机号码" /></div>
					</div>
					<div style="font-size: 12px" class="onShow" id="accountNameTip" ></div>
					<div class="moneyTl">
						 <span class="userReTitl textCen">验 证 码</span><div class="fl myReg"><input name="password" id="password"  type="text" placeholder="验证码" /></div>
						 <input class="monyCode" value="获取验证码" />
					</div>
					<div style="font-size: 12px" class="onShow" id="passwordTip" ></div>
					<div class="monyLogBox">
						<div class="checkboxFive">
							<input type="checkbox" value="1" id="checkboxFiveInput" name="" checked="checked"/>
							<label for="checkboxFiveInput"></label>
						</div>
						<a class="monyCheckTit" href="${ctx}/cf_xh_bs/dangerRule.htm">风险及免责</a>
					</div>
					<div class="monyLogBox">
						<div class="checkboxFive">
							<input type="checkbox" value="2" id="checkboxOneInput" name="" checked="checked"/>
							<label for="checkboxOneInput"></label>
						</div>
						<a class="monyCheckTit" href="${ctx}/cf_xh_bs/disclaimerRule.htm">比赛申请免责声明</a>
					</div>
				</div>
		        <input type="button" id="login" class="monyNext" value="下一步" >
		        <div class="monyFoot">
		        	 <a class="monyStoc" href="${ctx}/cf_xh_bs/stockAccount.htm">已报名，直接去开户</a><br/>
		        	 <a class="monyBack" href="${ctx}/cf_xh_bs/index.htm">返回</a>
		        </div>
		</div>
    </div>
</div>
</form>
<script  type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript">
	
	// 验证form表单
	function check() {
		var falg = true;
		phone = $("#phone").val();
		password = $("#password").val();
		if (phone == null || "" == phone) {
			falg = false;
		}
		if(password == null || "" == password) {
			falg = false;
		}
		return falg;
		
	}
	$("#login").click(function() {
		if(check()) {
			$.post("${ctx}/cf_xh_bs/registermactch.htm", {"phone": phone, "code": password}, function(data) {
				if (data == 1) {
					layer.open({
						content: "请输入手机号码",
						btn: ['确定']
					});
				} else if (data == 2) {
					layer.open({
						content: "请输入验证码",
						btn: ['确定']
					});
				} else if (data == 3) {
					layer.open({
						content: "您输入的验证码错误",
						btn: ['确定']
					});
				} else if (data ==4) {
					layer.open({
						content: "您已经报过名了",
						btn: ['确定']
					});
				} else if (data == 5) {
					location.href="${ctx}/cf_xh_bs/stockAccount.htm";
				} else {
					layer.open({
						content: "请您联系客服寻找技术人员",
						btn: ['确定']
					});
				}
			});
		} else {
			layer.open({
				content: "请输入手机号码和验证码",
				btn: ['确定']
			});
		}
	});
	
	var wait = 60;
	function time() {
		if (wait == 0) {
			$(".monyCode").removeAttr("disabled");
			$(".monyCode").css("background", "#fff");
			$(".monyCode").val("重新发送");
			wait = 60;
		} else {
			$(".monyCode").attr('disabled', 'javascript:return false;');
			$(".monyCode").css("background", "#c1c1c1");
			wait--;
			$(".monyCode").val("重新发送"+wait);
			setTimeout("time()", 1000);
		}
		
	}
	// 点击获取验证码
	$(".monyCode").click(function() {
		phone = $("#phone").val();
		if (null == phone || "" == phone) {
			layer.open({
				content: "请输入手机号码",
				btn: ['确定']
			});
			
			return false;
		}
		time();
		$.post("${ctx}/cf_xh_bs/sendCode.htm", {"phone": phone}, function(data) {
			if (data == 1) {
				layer.open({
					content: "请联系管理员解冻你的封印",
					btn: ['确定']
				});
			} else if (data == 2) {
				layer.open({
					content: "您点击过于频繁, 请稍后再试",
					btn: ['确定']
				});
			}
			
		});
	});
</script>
</body>
</html>
<script type="text/javascript">
	//控制字大小	
	var p0=document.getElementById('lineTit')
	p0.style.fontSize=(document.body.clientWidth*0.04)+"px";
</script> 	