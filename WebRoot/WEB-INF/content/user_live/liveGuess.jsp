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
<title>${title}－看直播抽大奖</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:auto;}
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fff;height:auto; }
input{border:none;background: #fff;-webkit-appearance: none;}
input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
}
::-webkit-input-placeholder { /* WebKit browsers */
    color:    #fff;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color:    #fff;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
    color:    #fff;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
    color:    #fff;
}
i{font-style: normal;}
.left{text-align: left;}
.right{text-align: right;}
.center{text-align:center;}
.app-wrapper{width: 100%;height: auto;margin:0 auto;}
h4{background:#004e99;padding:5% 0;color:#fff;text-align: center;font-size:1rem;}
.banner{width:100%;height:auto;}
.banner img{display: block;width:100%;}
.question,.lists{width:100%;}
.questionBody{width:90%;margin:5%;background: #ff6155;border-radius:10px;padding:5% 0;box-shadow: 1px 1px #cdc0b0;}
h6{font-size:0.9rem;text-align: center;}
.questionBody>h6{color:#fff;margin:0 auto 5%;}
.questionBody input{width:90%;margin:0 5%;padding:3% 0;background: #ff7a70;color:#fff;text-align: center;border:1px solid #de5a50; font-size:0.9rem;box-shadow: 0 0 #fff;}
.tj{display: block;margin: 5% auto 0;background: #fdfb80;padding:3% 7%;width:30%;text-align: center;}
.lists{background:#b4e2fa;}
.listsBody{width:90%;margin-left:5%;padding:5% 0;}
.listsBody>h6{color:#ff6155;}
.listsBody .list{margin-top:5%;font-size:0.8rem;}
.listsBody .list th{border-bottom:1px solid #666666;color:#666;}
.listsBody .list td{padding-top:5%;}
.tsk{position: absolute;background: #000;opacity:0.7;color:#fff;z-index:999;width:80%;left:10%;top:40%;padding:5% 0;text-align: center; background: #000 none repeat scroll 0 0;border-radius: 10px;color: #fff;font-size: 1.4em;font-weight: 500;left: 10%;padding: 5% 0;text-align: center;top: 40%;width: 80%;display: none;}
@media screen and (max-width:240px){
	.listsBody .list{font-size:0.6rem;}
}
</style>
</head>
<body>
	<article class="app-wrapper">
		<!-- <h4>看直播抽大奖</h4> -->
		<article class="banner">
			<figure><img src="../images_czt/liveBan.png"/></figure>
		</article>
		<article class="question">
		<form id="liveGuessForm">
			<input name="user_id" type="hidden" value="${user_id }"/>
			<input name="liveDrawId" type="hidden" value="${draw.id}"/>
			<section class="questionBody">
				<h6>今日问题：${empty draw.question?'':draw.question}</h6>
				<c:if test="${draw!=null && draw.question!=null && flag==1}">
				<input id="guessAnswer" name="guessAnswer" class="sr" type="text" placeholder="请输入您的竞猜"/>
				<a class="tj" href="javascript:void(0);" onclick="saveLiveGuess()">提交</a>
				</c:if>
			</section>
		</form>
		</article>
		<c:if test="${drawLast!=null && listGuess!=null}">
		<article class="lists">
			<section class="listsBody">
				<h6>看往期中奖名单（上期答案<span><fmt:formatNumber value="${empty drawLast.answer?0:drawLast.answer}" pattern="#,###,##0.00"/></span>）</h6>
				<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="left"><fmt:formatDate value="${drawLast.publishTime}" pattern="yyyy.MM.dd"/></th>
			        <th class="right" colspan="2">每人中奖金额：<fmt:formatNumber value="${empty money?0:money}" pattern="#,###,##0.00"/>元</th>
			       </tr>
			       <tr>
			       		<td class="left"><c:if test="${listGuess[0]!=null}">${fn:substring(listGuess[0].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[0].fuUser.phone,7,11)}</c:if></td>
			       		<td class="center"><c:if test="${listGuess[1]!=null}">${fn:substring(listGuess[1].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[1].fuUser.phone,7,11)}</c:if></td>
			       		<td class="right"><c:if test="${listGuess[2]!=null}">${fn:substring(listGuess[2].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[2].fuUser.phone,7,11)}</c:if></td>
			       </tr>
			       <tr>
			       		<td class="left"><c:if test="${listGuess[3]!=null}">${fn:substring(listGuess[3].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[3].fuUser.phone,7,11)}</c:if></td>
			       		<td class="center"><c:if test="${listGuess[4]!=null}">${fn:substring(listGuess[4].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[4].fuUser.phone,7,11)}</c:if></td>
			       		<td class="right"><c:if test="${listGuess[5]!=null}">${fn:substring(listGuess[5].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[5].fuUser.phone,7,11)}</c:if></td>
			       </tr>
			       <tr>
			       		<td class="left"><c:if test="${listGuess[6]!=null}">${fn:substring(listGuess[6].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[6].fuUser.phone,7,11)}</c:if></td>
			       		<td class="center"><c:if test="${listGuess[7]!=null}">${fn:substring(listGuess[7].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[7].fuUser.phone,7,11)}</c:if></td>
			       		<td class="right"><c:if test="${listGuess[8]!=null}">${fn:substring(listGuess[8].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[8].fuUser.phone,7,11)}</c:if></td>
			       </tr>
			       <tr>
			       		<td class="left"><c:if test="${listGuess[9]!=null}">${fn:substring(listGuess[9].fuUser.phone,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(listGuess[9].fuUser.phone,7,11)}</c:if></td>
			       		<td class="center"></td>
			       		<td class="right"></td>
			       </tr>
				</table>
			</section>
		</article>
		</c:if>
		<article id="tsk" class="tsk">
			答案只能是正数
		</article>
	</article>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/formValidator-4.0.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/formValidatorRegex.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/style/validator.css"></link>
<script type="text/javascript">
function saveLiveGuess(){
	if(isNaN($("#guessAnswer").val()) || parseInt($("#guessAnswer").val())<0 || !$("#guessAnswer").val()){
		$("#tsk").text("答案只能是正数");
		$("#tsk").show();
		$("#tsk").fadeOut(3000,function(){
			$("#answer").focus();
		});
		return false;
	}
	var data=$("#liveGuessForm").serialize();
	$.post("${ctx}/user_live/saveLiveGuess.htm",data,function(d){
		if(d==-1){
			$("#tsk").text("请勿重复提交竞猜结果");
			$("#tsk").show();
			$("#tsk").fadeOut(3000,function(){
				$("#answer").focus();
			});
			return false;
		}
		$("#tsk").text("竞猜提交成功");
		$("#tsk").show();
		$("#tsk").fadeOut(3000,function(){
			location.href="${ctx}/user_live/guessSuccess.htm?user_id=${user_id}&guessAnswer="+$('#guessAnswer').val();
		});
	})
}
</script>
