<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－积分管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background: #004e99;">
<c:set value="7" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
<div class="downban"></div>
<div class="mgrzx">
  <%@include file="../common/left.jsp" %>
  <%-- <%@include file="../common/left.jsp" %> --%>
  <div class="mgrzxr">
    <div class="h20"><span class="centerBg">积分管理</span><span class="safeBg">积分赠送</span></div>
    <div class="credit-content">
    	<ul>
    		<li>
    			<input id="accountName" type="text" placeholder="请输入要赠送的用户名" onmouseout="checkUser(this.value)"/>
    			<span>可赠送的积分为：<i><fmt:formatNumber value='${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral>0.5?fuUser.qidaIntegral+0.0001-0.5:0}' pattern='#,###,##0'/></i></span>
    		</li>
    		<li>
    			<input id="score" type="text" placeholder="请输入积分" onkeyup="changeMatch(this.value)" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
    			<a class="sendCredit" onclick="giveScore()">赠送积分</a>
    		</li>
    	</ul>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
	//检测用户名
	function checkUser(accountName){
		if(accountName!=null && accountName!=""){
			$.post("${ctx}/user_center/checkUser.htm",{accountName:accountName},function(data){
				if(data=="-1"){
					$.alerts.okButton="确定";
					jAlert("用户名不存在，请重新输入！","提示",function(){
		                $("#accountName").focus();
					});
				}
				if(data=="-2"){
					$.alerts.okButton="确定";
					jAlert("请勿赠送给自己！","提示",function(){
		                $("#accountName").focus();
					});
				}
			})
		}
	}
	
	//赠送积分的输入限制
	function changeMatch(score){
		if(isNaN(score)){
			$.alerts.okButton="确定";
			jAlert("输入的金额只能是正整数！","提示",function(){
				$("#score").val(1);
				$("#score").focus();
		    });
			return false;
		}
		if(score>${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral+0.0001-0.5}){
			$("#score").val(Math.round(${empty fuUser.qidaIntegral?0:fuUser.qidaIntegral+0.0001-0.5}));
			return false;
		}
		if(score!=null && score!='' && score<1){
			$("#score").val(1);
			return false;
		}
		if(score.indexOf(".")>-1){
			$("#score").val(Math.round(score));
		}
	}
	
	//赠送积分
	function giveScore(){
		if(!$("#accountName").val()){
			$.alerts.okButton="确定";
			jAlert("用户名不能为空！","提示",function(){
                $("#accountName").focus();
			});
			return false;
		}
		if(!$("#score").val()){
			$.alerts.okButton="确定";
			jAlert("积分不能为空！","提示",function(){
                $("#score").focus();
			});
			return false;
		}
		if($("#accountName").val()=="${fuUser.accountName}"){
			$.alerts.okButton="确定";
			jAlert("请勿赠送给自己！","提示",function(){
				$("#accountName").focus();
			});
			return false;
		}
		$.post("${ctx}/user_center/giveScore.htm",{score:$("#score").val(),accountName:$("#accountName").val()},function(data){
			if(data=="-1"){
				$.alerts.okButton="确定";
				jAlert("未登录！","提示",function(){
				});
			}
			if(data=="-2"){
				$.alerts.okButton="确定";
				jAlert("请勿赠送给自己！","提示",function(){
					$("#accountName").focus();
				});
			}
			$.alerts.okButton="确定";
			jAlert("赠送成功！","提示",function(){
                location.href=location.href;
			});
		})
	}
</script>
