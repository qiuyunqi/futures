<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－充值记录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="mgrzx">
<%@include file="../common/left.jsp" %>
<div class="mgrzxr">
      <div class="mgrrmain">
      <div id="one_1" class="display-b">
      <div class="rzfbzz">
      <form id="rechargeForm">
      <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">选择充值银行</span>
      </div>
       <div class=" xzczyh">
       		<ul>
            	<li class=" xzl"><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_07.gif" width="170" height="52"></a><span class="yxz0"></span></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_09.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_18.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_11.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_16.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/zhcz_17.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/xzczyh_03.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/xzczyh_05.gif" width="170" height="52"></a></li>
                <li><input name="rechargeBank" type="radio" value="" class="xz0"><a href="#"><img src="../qihuo_images/xzczyh_07.gif" width="170" height="52"></a></li>
            </ul>
            <div class="clr"></div>
       </div>
        <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">填写充值金额</span>
            
      </div>
      
      <div class="form czje">
        	<div class="fl form_font">账户余额：</div>
            <div class="fl radio" style="line-height:32px;">
            	<span class="zhye"><fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/></span>元
            </div>
            <div class="clr"></div>
        </div>
      <div class="form czje">
        	<div class="fl form_font">充值金额：</div>
            <div class="fl input input0"><input name="money" type="text" style=" width:130px; margin-right:5px;">元<span style=" font-size:12px; color:#525252; margin-left:15px;">单卡单笔5万，每日累计20万</span></div>
            <div class="clr"></div>
        </div>
        <div style="margin:30px 0 30px 120px;">
            <input name="" type="submit" onclick="saveRecharge();return false;" value="提交" class="tijiao">
        </div>
      </form>
      <div style="margin-left:70px;"><img src="../qihuo_images/wyzf_03.gif" width="652" height="179"></div>
      <div style=" color:#3d3d3d; margin:20px 0 0 70px; line-height:22px;">
    	<p>温馨提示    	  </p>
    	<p>1、为了您的资金安全，您的账户资金将由第三方银行托管；</p>
    	<p>2、充值前请注意您的银行卡充值限制，以免造成不便；</p>
    	<p>3、禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用；</p>		         
    	<p>4、为了您的资金安全，建议充值前进行实名认证、手机绑定、设置提现密码；</p>
    	<p>5、如果充值遇到任何问题，请联系客服：010-53320537，工作时间：8:30-17:00。 </p>
     </div>
  </div>
  </div>
  </div>
    <div class="clr"></div>
</div>
</div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script>
var sub = false;
function saveRecharge(){
  if(sub){
	  return ;
  }
  sub = true;
  var data = $("#rechargeForm").serialize();
  $.post("${ctx}/user_recharge/saveUserRecharge.htm?type=${type}",data,function(d){
	    sub = false;
        if(d==-2){
        	sureInfo("确定","请选择支付银行","提示");
            return null;
        }
        if(d==-3){
        	sureInfo("确定","请填写账号","提示");
            return null;
        }
        if(d==-4){
       	 	sureInfo("确定","请填写充值金额","提示");
           	return null;
       }
       if(d==-5){
      	 	sureInfo("确定","充值金额必须为数字","提示");
           return null;
       }
       if(d==-6){
      	 	sureInfo("确定","充值金额不能小于0","提示");
           return null;
       }
  });
}
</script>
