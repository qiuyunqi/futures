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
<title>${title}－首届"小合杯"(高校组)登录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:auto;}
body{width: 100%;height: 100%;background: #0f64e3;}
input[type=”button”], input[type=”submit”], input[type=”reset”] {
-webkit-appearance: none;
}
        .app-wrapper{width: 100%;height: 100%;margin:0 auto;}
        .app-content{height:100%;margin:0 auto;}
        .app-txt{position:absolute;width:100%;height:auto;  z-index:-1;}
        .app-txtimg{width:100%;height:100%;}
        .aapbtn{  width: inherit;position: absolute;top:111px;left:7.5%;width:85%;}
        .aapbtn p span{color:white;}
        .telphone{border:1px solid #fff;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5px;margin-bottom:10px;padding:0 0 0 10px;}
        .code{border:1px solid #fff;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5px; margin-bottom:10px;padding:0 0 0 10px;}
        .number{background:rgba(0,0,0,0) ;border:0;color:white;padding:10px 0;}
        .password{background:rgba(0,0,0,0) ;border:0;color:white;vertical-align: middle;padding:10px 0;width:46%;}
        #hqcode{border-left:1px solid #fff;border-bottom:0;border-top:0;border-right:0;padding: 11px 2px 11px 2px;font-size:10px;vertical-align: middle;background-color:transparent;color:white;float:right;text-align: center;}
        .tj{width:100%;background:#ffd800;border:none;color:#675700;padding:10px;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5px;margin-top:20px;}
</style>
</head>
<body>
	<div class="app-wrapper">
		<div class="app-content">
			<div class="app-txt">
				<img src="../images_hhr/signBackground.jpg" class="app-txtimg"/>
			</div>
			<form action="" method="post">
				<div id="aapbtn" class="aapbtn">
					<p id="telphone" class="telphone"><span>手机号：</span><input type="text" name="number" class="number" autocomplete="off"/></p>
					<p id="code" class="code"><span>验证码：</span><input type="password" name="password" class="password" autocomplete="off"/><i></i><input type="button" id="hqcode" value="获取验证码" ></p>
					<input class="tj" type="submit" value="立即报名"/>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	var p1=document.getElementById('aapbtn');
	p1.style.top=(document.body.clientWidth*0.8)+"px";
	
	var p2back=document.getElementById('telphone');
    p2back.style.fontSize=(document.body.clientWidth*0.04)+"px";
	p2back.style.top=(document.body.clientWidth*0.84)+"px";
	
	var p3back=document.getElementById('code');
    p3back.style.fontSize=(document.body.clientWidth*0.04)+"px";
	p3back.style.top=(document.body.clientWidth*0.84)+"px";
	
	/* var p4back=document.getElementById('hqcode');
    p4back.style.fontSize=(document.body.clientWidth*0.03)+"px"; */
	</script>
</body>
</html>
