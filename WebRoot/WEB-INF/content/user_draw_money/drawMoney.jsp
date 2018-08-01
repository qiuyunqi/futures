<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－我要提款</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background:#004e99;">
<c:set var="pg" value="4"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
	<div class="downban"></div>
	<div class="mgrzx">
	<%@include file="../common/left.jsp" %>
		<div class="mgrzxr">
 			<div class="h20"><span class="centerBg">用户中心</span><span class="safeBg" style="text-indent:25px;">提现</span></div>
 			<div class="mgrrmain">
		      <div id="one_1" class="display-b">
		        <div class="rzfbzz">
		          <div class="rzfbb"><span class="ny_bt_cont">从账户余额提款到银行卡</span><span style=" color:#df0704; font-size:12px; margin-left:10px;">*请在工作时间：9：00 - 17：00内申请办理，敬请悉知</span></div>
		          <form id="drawForm">
		          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
		            <tr>
		              <th width="120">提款金额：</th>
		              <td><input name="money" type="input" class="textput" id="money1" style="width:265px;margin-right:20px;" placeholder="请输入提款金额" onkeyup="checkMoney(this.value)" onafterpaste="this.value=this.value.replace(/\D/g,'')"><span>当前可用余额：<fmt:formatNumber value="${accountBalance}" pattern="#,###,##0.00"/></span>元</td>
		            </tr>
		            <tr>
		              <th>选择银行卡：</th>
		              <td>
		              	<select name="cardId" style="width:250px;height:30px;" onchange="toBank(this.value);">
				             <option value="">选择银行卡</option>
				             <option value="0">添加银行卡</option>
				             <c:forEach items="${bankCardList}" var="bankCard">
				             <option value="${bankCard.id}">${bankCard.bankName} ${fn:substring(bankCard.cardNumber,0,4)}<c:forEach begin='4' end='${(fn:length(bankCard.cardNumber)-5)<0?4:fn:length(bankCard.cardNumber)-5}'>*</c:forEach>${fn:substring(bankCard.cardNumber,fn:length(bankCard.cardNumber)-4,fn:length(bankCard.cardNumber))}</option>
				             </c:forEach>
			             </select>
		              </td>
		            </tr>
		            <tr>
		              <th>提款密码：</th>
		              <td>
		              <c:if test="${empty fuUser.drawPassword}">
		            	<div style="line-height:32px;width:265px;" class="textput"><a style="color:red;" href="javascript:void(0);" onclick="drawPassword();" >设置提款密码</a></div>
		              </c:if>
		              <c:if test="${!empty fuUser.drawPassword}">
		            	<input  style="width:265px;" name="drawPassword" type="password" class="textput" id="draw" value=""/>
		              </c:if>
		              </td>
		            </tr>
		            <tr>
		              <th>手机号：</th>
		              <td>
		              
		              <div class="fl radio textput" style="width:95px;">
		            	${fuUser.phone}
		              </div>
		              <div class="fl hqyzm"><a href="javascript:void(0);" onclick="sendCode();" id="msgbtn" >发送验证码</a></div><div class="clr">
		              </td>
		            </tr>
		            <tr>
		              <th>验证码：</th>
		              <td><input name="phoneCode" id="code" type="input" class="textput textCen" style="width:100px;" placeholder="验证码"></td>
		            </tr>
		            <tr>
		              <th></th>
		              <td><a href="javascript:void(0)" class="bcbut" onclick="saveDraw();return false;">提交</a></td>
		            </tr>
		          </table>
		          </form>
		        </div>
		      </div>
    		</div>
    		
    		<div class="downban"></div>
    		<div class="zfbListTits">
		       <div class="zfbListTit">提款记录</div>
		       <div class="zfbListTitBtns"><a class="zfbListTitBtn" href="${ctx}/user_draw_money/drawMoney.htm?status=0">未审核</a><a class="zfbListTitBtn" href="${ctx}/user_draw_money/drawMoney.htm?status=2">成功</a><a class="zfbListTitBtn" href="${ctx}/user_draw_money/drawMoney.htm">全部</a></div>
		    </div>
    		<div class="mgrrmain">
		      <div id="one_1" class="display-b">
		        <div class="rzczjl">
		          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
			          <tr>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" >时间</td>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">开户银行</td>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">银行卡号</td>   
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">金额（元）</td>  
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">状态</td>  
			          </tr>
			           <c:forEach items="${drawList}" var="draw" varStatus="row">
			          <tr>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${draw.drawTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${draw.fuBankCard.bankName}</td>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${fn:substring(draw.fuBankCard.cardNumber,0,4)}<c:forEach begin='4' end='${fn:length(draw.fuBankCard.cardNumber)-5}'>*</c:forEach>${fn:substring(draw.fuBankCard.cardNumber,fn:length(draw.fuBankCard.cardNumber)-4,fn:length(draw.fuBankCard.cardNumber))}</td>
			            <td height="35" align="right" style="text-align: right;"<c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${draw.drawMoney}" pattern="#,###,##0.00"/>&nbsp</td>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${draw.status==0?'未审核':draw.status==1?'审核中':draw.status==2?'成功':'拒绝'}</td>
			          </tr>
			          </c:forEach>
		          </table>
		          <c:if test="${empty drawList}">
		            <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
		          </c:if>
		        </div>
		        <div class="page">
					<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
						url="${ctx}/user_draw_money/drawMoneyList.htm?totalCount=${totalCount}"
						totalNum="${totalCount}" curPageNum="${pageNo}"
						formId="pageForm">
					</domi:pagination>
				</div>
		      </div>
    		</div>
    	</div>	
    </div>
    <div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
function checkMoney(drawMoney){
	if(isNaN(drawMoney)){
		jAlert("输入的金额只能是数字！","提示",function(){
			$("#money1").val("");
			$("#money1").focus();
        });
		return false;
	}
	if(drawMoney > ${accountBalance}){
		$("#money1").val(${accountBalance});
		return false;
	}
	if(drawMoney!=null&&drawMoney!=''&&drawMoney<0){
		$("#money1").val(0);
		return false;
	}
}
//选中银行卡变色
$(function(){
   $(".bankCard").each(function(){
     $(".bankList").click(function(){
       $(this).addClass("bankvisited");
        $(this).nextAll().removeClass("bankvisited");
        $(this).prevAll().removeClass("bankvisited");
       $(this).nextAll().addClass("bankList bordr brRadius");
       $(this).prevAll().addClass("bankList bordr brRadius");
     });
   });
});

function toBank(val){
	if(val==0){
       location.href = "${ctx}/user_draw_money/bankManager.htm";
	}	
}

function saveDraw(){
	//等于0未穿仓，1已穿仓不能提款
	<c:if test="${fuUser.isAcrossCabin==1}">
		$.alerts.okButton='确定';
		jAlert('对不起，您的账户已穿仓，暂时不能提款','提示',function(){
			return false;
		});
	</c:if>
	<c:if test="${fuUser.isAcrossCabin==0}">
	  var data = $("#drawForm").serialize();
	  $.post("${ctx}/user_draw_money/saveDrawMoneyAjax.htm",data,function(d){
		    if(d==-9){
		    	jAlert("请输入提款金额","提示",function(){
	  				$("#money1").focus();
	            });
	            return null;
	        }
		    if(d==-10){
		    	jAlert("输入金额必须为正数","提示",function(){
	  				$("#money1").focus();
	            });
	            return null;
	        }
		    if(d==-7){
	            sureInfo("确定","账户余额不足，不能进行提款操作","提示");
	            return null;
	        }
	        if(d==-3){
	        	sureInfo("确定","请选择提款银行卡","提示");
	            return null;
	        }
	        if(d==-4){
	        	jAlert("请填写手机验证码","提示",function(){
	  				$("#code").focus();
	            });
	            return null;
	        }
	        if(d==-5){
	        	jAlert("手机验证码输入出错","提示",function(){
	  				$("#code").focus();
	            });
	            return null;
	        }
	        if(d==-6){
	        	jAlert("请输入提款密码","提示",function(){
	  				$("#draw").focus();
	            });
	            return null;
	        }
	        if(d==-8){
	        	jAlert("提款密码输入出错","提示",function(){
	  				$("#draw").focus();
	            });
	            return null;
	        }
	        if(d==-11){
				//身份认证
				jAlert("请完成实名认证，认证通过才能提款","提示",function(){
					$.fancybox.open({
			 			href : '${ctx}/user_center/personNameAjax.htm',
			 			type : 'ajax',
			 			padding : 5 ,
				 	});
			 	});
				return null;
			} 
	        if(d==1){
	        	jAlert("提款申请成功，请耐心等待审核结果！","提示",function(){
	        	    location.href=location.href;
	        	});
		    }
	  });
	</c:if>
}

var sending=false;
var start=60;
function sendCode(){
	  $.post("${ctx}/user_draw_money/sendMsgAjax.htm",null,function(d){
		  sending=true;
		  setTimeout(countTime,1000);
		  $("#msgbtn").css({color:'#ccc'});
		  $("#msgbtn").text('60s后重试');
	  });
}



function countTime(){
		start--;
		if(start<=0){
			$("#msgbtn").css({color:'#fff'});
			$("#msgbtn").text("发送验证码");
			$("#msgbtn").attr("onclick", "sendCode();");
			sending=false;
			start=60;
			return;
		}
		$('#msgbtn').text(start+"s后重试");
		setTimeout(countTime,1000);
		if(start>0){
			$("#msgbtn").attr("onclick", "");
		}
}

function drawPassword(){
	$.fancybox.open({
			href : '${ctx}/user_center/drawPasswordAjax.htm',
			type : 'ajax',
			padding : 5 
		});
}
</script>
