<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－操盘规则</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

	<div style="height:1px;"></div>
	<div class=" zx_title">操盘规则<span style="margin-left:560px;font-size:14px;color:#808080;">产品资金由武汉坤州大德提供</span></div>
	<form id="addProgramForm">
    <div class=" rpfa" style="width: 842px;">
    <input name="addMatchId" type="hidden" value="${addMatchId}"/>
    <input name="subMatchId" type="hidden" value="${subMatchId}"/>
    <input name="matchMoney" type="hidden" value="${matchMoney}"/>
    <input name="num" type="hidden" value="${num}"/>
    <input name="programType" type="hidden" value="${programType}"/>
    <input name="tradeTimeType" type="hidden" value="${tradeTimeType}"/>
    <input name="cycleNum1" type="hidden" value="${cycleNum1}"/>
    <input name="cycleNum2" type="hidden" value="${cycleNum2}"/>
    <input name="integral" type="hidden" value="${integral}"/>
    
    <table width="842" border="0" cellspacing="0" cellpadding="0">
     <tbody>
	    <tr>
	      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${totalMoney}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
	      <td width="80" rowspan="2" align="center"><span class="equal ">=</span></td>
	      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${matchMoney*10000}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
	      <td width="80" rowspan="2" align="center"><span class="add ">+</span></td>
	      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${safeMoney}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
	    </tr>
	    <tr>
	      <td align="center"><span class="zcpzj">账户总资金</span></td>
	      <td align="center"><span class="zcpzj">融资资金</span></td>
	      <td align="center"><span class="zcpzj">风险保证金</span></td>
	    </tr>
    </tbody>
   </table>
   </div>
   <div class="rpfa0 rpfa1">
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tbody>
    <tr>
      <td><span class=" a1">亏损预警线：</span><span class="a2"><fmt:formatNumber value="${warnLine}" pattern="#,###,##0.00"/></span></td>
      <td><span class=" a1">亏损平仓线：</span><span class="a2"><fmt:formatNumber value="${closeLine}" pattern="#,###,##0.00"/></span></td>
    </tr>
    <tr>
      <td><span class=" a1">开始交易时间：</span><span class="a2">${tradeTimeType==1?'今天':'下个交易日'}</span></td>
      <td><span class=" a1">操作时间：</span><span class="a2">
      	<c:if test="${programType==1}">
			${cycleNum1}天
		</c:if>
		<c:if test="${programType==2}">
			${cycleNum2}个月
		</c:if>
		</span>
		</td>
    </tr>
    <tr>
      <td><span class=" a1">投资方向：</span><span class="a2">只能交易主力合约或次主力合约</span></td>
      <td><span class=" a1">仓位要求：</span><span class="a2">不限制</span></td>
    </tr>
    <tr>
      <td><span class=" a1">盈利分配：</span><span class="a2">全部归您	</span></td>
      <td><span class=" a1">约定收益：</span><span class="a2"><fmt:formatNumber value="${managerMoney}" pattern="#,###,##0.00"/>元/${programType==1?'天':'月'}</span></td>
    </tr>
  </tbody>
</table>

      </div>
      
      <div class=" zx_title">操盘规则</div>
      <div class=" rpfa" style="width: 842px;">
        <table width="842" border="0" cellspacing="0" cellpadding="0">
		  <tbody>
		    <tr>
		      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${(managerMoney+safeMoney)-integral}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
		      <td width="80" rowspan="2" align="center"><span class="equal ">=</span></td>
		      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${safeMoney}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
		      <td width="80" rowspan="2" align="center"><span class="add ">+</span></td>
		      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${managerMoney}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
		      <td width="80" rowspan="2" align="center"><span class="add ">-</span></td>
		      <td width="150" align="center"><span class=" zcpzj_num"><fmt:formatNumber value="${empty integral?0.00:integral}" pattern="#,###,##0.00"/></span><span class="yuan">元</span></td>
		    </tr>
		    <tr>
		      <td align="center"><span class="zcpzj">支付金额</span></td>
		      <td align="center"><span class="zcpzj">风险保证</span></td>
		      <td align="center"><span class="zcpzj">约定收益</span></td>
		      <td align="center"><span class="zcpzj">消费点劵</span></td>
		    </tr>
		  </tbody>
      </table>
      </div>
      <div class=" ycgl">目前您的账户余额为：<fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00"/>元</div>
      <div style="text-align:center; margin:30px 0 60px 0px;">
      <c:if test="${empty addMatchId&&empty subMatchId}"><a id="sure" href="javascript:void(0);" onclick="saveProgram();" class=" sure sure1">确认开户</a></c:if>
      <c:if test="${!empty addMatchId}"><a id="sure" href="javascript:void(0);" onclick="saveProgram();" class=" sure sure1">确认扩充融贷</a></c:if>
      <c:if test="${!empty subMatchId}"><a id="sure" href="javascript:void(0);" onclick="saveSubProgram();" class=" sure sure1">确认减配</a></c:if>
      <a href="javascript:void(0);" onclick="backUpdate();" class="cancel cancel1">返回修改</a>
  </div>
  <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
  </form>
<div class="ddwdesc clearfix">
	<ul>
    	<li><img src="../qihuo_images/xtz_03.jpg" width="64" height="64"><span>即时申请，即时交易</span></li>
        <li><img src="../qihuo_images/xtz_05.jpg" width="64" height="64"><span>收益最大可放大10倍</span></li>
        <li><img src="../qihuo_images/xtz_07.jpg" width="64" height="64"><span>100%真实的实盘交易</span></li>
        <li><img class="pzliimgF" src="../qihuo_images/xtz_12.jpg" width="64" height="64"><span>最低1000元起即可申请</span></li>
        <!-- <li><span><img src="../qihuo_images/xtz_13.jpg" width="64" height="64"></span>2天-30天，想用几天就几天</li> -->
        <li class="clearfixLi"><img class="pzliimg" src="../qihuo_images/xtz_14.jpg" width="64" height="64" ><span>严格止损，控制资金风险</span></li>
    </ul>
    <div class="clr"></div>
</div>
<div class="ddwdetail">
  	<h3>操盘须知</h3>
  	<div class="pzhr"></div>
  	<p class="addpeizp">全程只需分成约定收益，<br>无其他任何费用，期市<br>有风险，投资需谨慎！<br>市场风险莫测，务请谨<br>慎行事！</p>
   <!--  <p>2、资金使用期限：最短1天，最长30天，按照约定收益分成，默认每天自动延期，自动从账户余额扣除约定收益（余额不足时我们有权自动终止方案和从交易账户内出金扣除约定收益）；</p> -->
    <p class="addpeizp" >风险保证金：在您操盘出<br>现亏损后用于支付亏损金<br>，结束时如无亏损全额退<br>还，保证金比例越低收益<br>越大，同时风险也越大；</p>
    <p class="addpeizp" >亏损警戒线：当总操盘资<br>金低于警戒线以下时，只<br>能平仓不能建仓，需要尽<br>快补充风险保证金，以免<br>低于亏损平仓线被平仓；</p>
    <p class="addpeizp" >亏损平仓线：当总操盘资<br>金低于平仓线以下时，我<br>们将有权把您的期货进行<br>平仓，为避免平仓发生，<br>请时刻关注风险保证金是<br>否充足；</p>
    <p style="width: 16%;float:left;" >开始交易时间：一般选择<br>下个交易日，如看中行情<br>急需交易，可直接选择今<br>天交易（今天开始计算约<br>定收益分成）。</p>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
//确认开户
$("#load").hide();
function saveProgram(){
	$("#load").show();
	$("#sure").attr("onclick", "");
	var data=$("#addProgramForm").serialize();
	$.post('${ctx}/user_program/saveShortProgramAjax.htm?ppId=${ppId}',data,function(d){
		var json=eval('('+d+')');
		if(json.code==-1){
			sureInfo('确定','系统参数还没有设置，请联系客服！','提示');
			return false;
		}
		if(json.code==-2){
			sureInfo('确定','超出了交易个数！','提示');
			return false;
		}
		if(json.code==-3){
			//身份认证
			$.fancybox.open({
		 			href : '${ctx}/user_center/personNameAjax.htm?flag=1&methodName=saveProgram',
		 			type : 'ajax',
		 			padding : 5 ,
		 	});
			return false;
		}
		if(json.code==-4){
			jAlert("个人账户余额不足，请先充值！","提示",function(){
	        	location.href="${ctx}/user_recharge/onlinePay.htm";
	        });
			return false;
		}
		if(json.code==-5){
			lgInfo(1);	
			return false;
		}
		if(json.code==-6){
			sureInfo("确定","个人账户已穿仓，请先为已穿仓的方案追加保证金解除穿仓状态！","提示");
			return false;
		}
		$.post("${ctx}/user_program/programSuccessAjax.htm?id="+json.message,null,function(d){
			location.href="${ctx}/user_program/programSuccessAjax.htm?id="+json.message;
		});
	});
}

//减配确认
function saveSubProgram(){
	$("#load").show();
	$("#sure").attr("onclick", "");
	var data=$("#addProgramForm").serialize();
	$.post('${ctx}/user_program/saveSubProgramAjax.htm',data,function(d){
		var json=eval('('+d+')');
		if(json.code==-1){
			sureInfo('确定','系统参数还没有设置，请联系客服！','提示');
			return false;
		}
		if(json.code==-2){
			sureInfo("确定","个人账户已穿仓，请先为已穿仓的方案追加保证金解除穿仓状态！","提示");
			return false;
		}
		if(json.code==-3){
			//身份认证
			$.fancybox.open({
	 			href : '${ctx}/user_center/personNameAjax.htm?flag=1&methodName=saveProgram',
	 			type : 'ajax',
	 			padding : 5 ,
		 	});
			return false;
		}
		if(json.code==-5){
			lgInfo(1);	
			return false;
		}
		$.post("${ctx}/user_program/programSuccessAjax.htm?id="+json.programId,null,function(d){
			location.href="${ctx}/user_program/programSuccessAjax.htm?id="+json.programId;
		});
	});
}

//返回修改
function backUpdate(){
	//增配
	<c:if test="${addMatchId>0}">
		location.href="${ctx}/user_program/appendPeizi.htm?addMatchId=${addMatchId}&matchMoney=${matchMoney}&num=${num}&programType=${programType}&tradeTimeType=${tradeTimeType}&cycleNum1=${cycleNum1}&cycleNum2=${cycleNum2}&integral=${integral}";
	</c:if>
	//减配
	<c:if test="${subMatchId>0}">
		location.href="${ctx}/user_program/minusPeizi.htm?subMatchId=${subMatchId}&matchMoney=${matchMoney}&num=${num}&programType=${programType}&tradeTimeType=${tradeTimeType}&cycleNum1=${cycleNum1}&cycleNum2=${cycleNum2}&integral=${integral}";
	</c:if>
	//正常配资
	<c:if test="${addMatchId==null&&subMatchId==null}">
		location.href="${ctx}/user_program/addPeizi.htm?matchMoney=${matchMoney}&num=${num}&programType=${programType}&tradeTimeType=${tradeTimeType}&cycleNum1=${cycleNum1}&cycleNum2=${cycleNum2}&integral=${integral}";
	</c:if>
}

</script>