<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－登录入口－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="top2_cont">
    	<div class=""><img src="${ctx}/images_qhht/qihuo_03.png" width="214" height="46"></div>
</div>
<div class="denglu_banener">
<div class=" cwxt_cont">
    <form id="loginForm" onsubmit="login();return false;">
    <div class="dl_cont fr">
    	<div class=" dl_title">登录</div>
        <div class="load_bd load_bd0">
        	<div class="fl"><i class=" zh"></i></div>
            <div class="fl"></div>
            <div class="fl load_bd_cont"><input name="account" type="text" value="${account}" placeholder="账户"></div>
            <div class="clr"></div>
        </div>
        <div class="load_bd">
        	<div class="fl"><i class="mm"></i></div>
            <div class="fl"></div>
            <div class="fl load_bd_cont"><input name="password" type="password" placeholder="密码"></div>
            <div class="clr"></div>
        </div>
        <div class=" dl_cont0" style="margin:-5px 0 0 24px;">
        	<input name="isRemain" type="checkbox" value="true"><span style="margin-right:20px;">记住账号</span>
            <input name="isAuto" type="checkbox" value="true"><span>下次自动登录</span>
<%--            <a href="#" class="wjmm">忘记密码</a>--%>
        </div>
        <input type="submit" class="dl_but" id="loginClick" value="登录">
<%--        <div class="myzc"><span>还没有账号？</span>--%>
<%--        <a href="#">免费注册</a>--%>
        </div>
    </div>
    </form>
    <div class="clr"></div>
</div>
</div>
</body>
</html>
<script type="text/javascript">
var sub = false;
function login(){
	if(sub){
		return ;
	}
	sub = true;
  	var data = $("#loginForm").serialize();
  	$.post("${ctx}/admin_login/doAdminLoginAjax.htm",data,function(d){
	    sub = false;
        if(d==-2){
       	    sureInfo("确定","请输入账号","提示");
            return ;
        }
        if(d==-3){
        	sureInfo("确定","请输入密码","提示");
            return ;
        }
        if(d==-4){
        	sureInfo("确定","账号不存在","提示");
            return ;
        }
        if(d==-5){
        	sureInfo("确定","密码输入错误","提示");
            return ;
        }
        if(d==-6){
        	sureInfo("确定","您的IP地址在黑名单中,无法登录","提示");
            return ;
        }
        if(d==-7){
        	sureInfo("确定","账号未解禁,无法登录","提示");
            return ;
        }
        if(d==-8){
        	sureInfo("确定","密码连续错误5次,禁止登录1天","提示");
            return ;
        }
        location.href = "${ctx}/admin_login/indexHome.htm";
    });
}

$("#loginForm").keydown(function(e){
	 var e = e || event,
	 keycode = e.which || e.keyCode;
	 if (keycode==13) {
	  $("#loginClick").trigger("click");
	 }
});
</script>
