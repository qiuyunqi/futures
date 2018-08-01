<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－添加配资</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style type="text/css">
.benj{
	width:100%;
	height:auto;
	margin-left:9px;
	font-size:18px;
	color:#454545;
	font-weight:bold;
	text-align:center;
	line-height: 45px;
	border-top:2px solid #dfdfdf;
	text-align:left;
	height: 80px;
}
.sp{text-indent:8px;}
.spa{
	color:#f1982f;
	font-size:25px;
	display:block;
	margin-left:8px;
    float: left;
    height: 35px;
    line-height: 35px;
 }
.agree{
	margin-top:15px;
	texe-aligin:center;
}
.ty{
	width:260px;
	margin-left:18px;
}
.kydq{color:#999999;}
.kydqRed,.kydqRedL{color:red;}
.kydqRed{margin-left:15px;}
#acBalance{height: 20px;line-height: 20px;border:none; width: 85px;color: #999999;background: white;}
.ptkeyzj{width:170px;height:35px;line-height: 32px;text-indent: 5px;float:right;}
.ptkeyzj>span{font-size:14px;font-weight: normal;color: #999999;}
.hqhpz{max-width: 1318px !important;}
/*滑块*/
 .tooltip {
    position: absolute;
    top: 100%;
    left: 50%;
    margin-top: 10px;
    font-weight: 700;
    -webkit-transform: translateX(-50%);
    -ms-transform: translateX(-50%);
    transform: translateX(-50%);
    padding: 2px 6px;
    font-size: 14px;
    text-align: center;
    color: #3b4b53;
    background: #fff;
    border-radius: 3px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}
.tooltip:before {
    content: '';
    position: absolute;
    bottom: 100%;
    left: 50%;
    border-width: 4px;
    margin-left: -4px;
    border-color: transparent transparent #fff transparent;
    border-style: solid;
}
.noUi-pips{display:none;}
.pzfter{margin:0 auto;}
@media screen and (max-width: 1322px){
	.hhead{max-width: 1318px !important;width: 1318px;}
	.peizfoot{width:1318px !important;}
	.hgrzxb{width:1318px;}
	.hbanner{ max-width: 1318px !important;}
}
@media \0screen{
	.hhead{max-width: 1318px !important;width: 1318px;}
	.pzfter{max-width: 1318px !important;width: 1318px;background: #1e1b29;}
	.hgrzxb{width:1318px;}
	.hbanner{ max-width: 1318px !important;}
}
</style>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="hqhpz">
<form id="programForm">
  <div class="hqhtop">
    <input id="addMatchId" type="hidden" value="${addMatchId}"/>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <th>融资资金：</th>
        <td>
            <div id="slider-connect" >
        		<div class="slider-range-min"></div>
    		</div>
	        <a href="javascript:void(0)" class="qhspzj tenw" >10万</a>
	        <a href="javascript:void(0)" class="qhspzj fivew" >50万</a>
	        <a href="javascript:void(0)" class="qhspzj onehw" >100万</a>
	        <a href="javascript:void(0)" class="qhspzj fivehw" >500万</a>
	        <a href="javascript:void(0)" class="qhspzj onesw" >1000万</a>
	        <input id="match" name="match" type="text" value="1" onkeyup="changeMatch(this.value)"  onafterpaste="this.value=this.value.replace(/\D/g,'')" class="qhspzj qhspzjk" style="width:60px;"/>
	        <div class="hqhfy"><img src="../images_hhr/zhpz01.png" onclick="matchAdd()"/><img src="../images_hhr/zhpz01a.png" onclick="matchDec()"/></div>
	        <input id="matchMoney" name="matchMoney" type="hidden">
        </td>
      </tr>
      <tr>
        <th>资金倍率：</th>
        <td>
            <a id="num1" href="javascript:void(0)" class="qhzjbl qhzjbll">1<span>倍</span></a>
	        <a id="num2" href="javascript:void(0)" class="qhzjbl">2</a>
	        <a id="num3" href="javascript:void(0)" class="qhzjbl">3</a>
	        <a id="num5" href="javascript:void(0)" class="qhzjbl">5</a>
	        <a id="num10" href="javascript:void(0)" class="qhzjbl num10" original-title="5倍以上只能做股指">10</a>
	        <input id="num" name="num" type="hidden" value="1"/>
        </td>
      </tr>
      <tr>
        <th>方案类型：</th>
        <td>
	        <a href="javascript:void(0)" id="programType1" onclick="changeProgramType(1);return false;" <c:if test="${programType==1}">class="qhjysj qhjysja"</c:if><c:if test="${programType==2}">class="qhjysj"</c:if>>按日操作</a>     
	        <a href="javascript:void(0)" id="programType2" onclick="changeProgramType(2);return false;" <c:if test="${programType==1}">class="qhjysj"</c:if><c:if test="${programType==2}">class="qhjysj qhjysja"</c:if>style="width:112;margin-left:-6px;">按月操作</a>
	        <input id="programType" name="programType" type="hidden" value="${programType}"/>
        </td>
      </tr>
      <tr id="rpTR">
        <th>使用周期：</th>
        <td>
	        <a id="cycle1" href="javascript:void(0)" class="qhzjbl qhzjbll" onclick="chooseCycle1(1);">1<span>天</span></a>
	        <a id="cycle3" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle1(3);">3</a>
	        <a id="cycle5" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle1(5);">5</a>
	        <a id="cycle10" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle1(10);">10</a>
	        <a id="cycle15" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle1(15);">15</a>
	        <input name="cycleNum1" id="cycleNum1" type="text" class="qhzjbl qhzjblk" readonly="readonly" onkeyup="changeCycle1(this.value);" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="1"/>
	        <div class="hqhfy"><img src="../images_hhr/zhpz01.png" onclick="dayAdd()"/><img src="../images_hhr/zhpz01a.png" onclick="dayDec()"/></div>
        </td>
      </tr>
      <tr id="ypTR">
        <th>使用周期：</th>
        <td>
	        <a id="cyc1" href="javascript:void(0)" class="qhzjbl qhzjbll" onclick="chooseCycle2(1);">1<span>个月</span></a>
	        <a id="cyc2" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle2(2);">2</a>
	        <a id="cyc3" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle2(3);">3</a>
	        <a id="cyc6" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle2(6);">6</a>
	        <a id="cyc12" href="javascript:void(0)" class="qhzjbl" onclick="chooseCycle2(12);">12</a>
	        <input name="cycleNum2" id="cycleNum2" type="text" class="qhzjbl qhzjblk" readonly="readonly" onkeyup="changeCycle2(this.value);" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="1"/>
	        <div class="hqhfy"><img src="../images_hhr/zhpz01.png" onclick="monthAdd()"/><img src="../images_hhr/zhpz01a.png" onclick="monthDec()"/></div>
        </td>
      </tr>
      
      <tr>
        <th>交易时间：</th>
        <td>
        <c:if test="${weekday!=1&&weekday!=7}">
        <a href="javascript:void(0);" id="tradeType1" class="qhjysj" onclick="changeTradeType(1);return false;">今天</a><a href="javascript:void(0)" id="tradeType2" class="qhjysj qhjysja" onclick="changeTradeType(2);return false;">下个交易日</a>
        </c:if>
        <c:if test="${weekday==1||weekday==7}">
        <a href="javascript:void(0);" id="tradeType1" class="qhjysj nowDate" original-title="周末只能选择下个交易日">今天</a><a href="javascript:void(0)" class="qhjysj qhjysja">下个交易日</a>
        </c:if>
      </tr>
    </table>
    <div class="jyed">
    	<div id="dianjuan">
	    	<span class="jetxt">点劵：</span>
	    	<input id="point" type="text" class="jeye" onkeyup="changeIntegral(this.value)"/>*100
    	</div>
    	<div class="kydq">(可用<span id="kydqDs" class="kydqDs">0</span>点)<span class="kydqRed">-¥</span><span id="kydqRedL" class="kydqRedL">0.00</span></div>
    </div>
    <div class="jyall"><input id="allScore" type="checkbox" onchange="changeScore()"/>使用点劵<a href="javascript:void(0);" class=" wenti" original-title="点劵只能用来冲抵风险保证金，且不能超过风险保证金的50%"></a></div>
    <input id="integral" name="integral" type="hidden" value="0"/>
  </div>
  <div class="hqhbottom">
    <div class="hqhbtab">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr class="bdboder">
        <th style="color:#006699;font-size:18px;font-weight:bold;width:110px;">风险保证金：</th>
        <td><span id="safeInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="在您操盘出现亏损后用于支付亏损金，结束时如无亏损则全额退还，保证金越低收益越大，同时风险也越大" ></a></td>
        <input type="hidden" name="safeMoney" id="safeMoney"/>
      </tr>
      <!-- 添加使用积分数量 -->
      <tr class="bdboder">
        <th>使用点劵：</th>
        <td><span id="scoreInfo">0</span>点<a href="javascript:void(0);" class=" wenti" original-title="点劵只能用来冲抵风险保证金，且不能超过风险保证金的50%"></a></td>
      </tr>
      <tr class="bdboder">
        <th>总操盘资金：</th>
        <td><span id="totalInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="总操盘资金等于融资资金与风险保证金之和"></a></td>
      </tr>
      <tr class="bdboder">
        <th>亏损预警线：</th>
        <td><span id="warnInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="当总操盘资金低于警戒线以下时，需要尽快补充风险保证金，以免低于亏损平仓线被平仓"></a></td>
      </tr>
      <tr class="bdboder">
        <th>亏损平仓线：</th>
        <td><span id="flatInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="当总操盘资金低于平仓线以下时，我们将有权对您的期货持仓进行平仓，为避免平仓发生，请时刻关注风险保证金是否充足"></a></td>
      </tr>
      <tr class="bdboder">
        <th>约定收益率：</th>
        <td><span id="rateInfo1"><fmt:formatNumber value="${empty pam.feeDay?0:pam.feeDay}" pattern="#,###,##0.00"/>元/万</span><span id="rateInfo2"><fmt:formatNumber value="${empty pam.feePercent?0:pam.feePercent*100}" pattern="#,###,##0.00"/>%</span><a href="javascript:void(0);" class=" wenti" original-title="利率为收取风险保证金的利息百分比"></a></td>
      </tr>
      <tr class="bdboder">
        <th>约定收益：</th>
        <td><span id="managerInfo">0</span><span style="font-size:14px;" id="managerType">元/天</span><a href="javascript:void(0);" class=" wenti" original-title="日配不隔夜，每天收盘前自动平仓；月配5倍以内，自有资金商品2倍/股指3倍隔夜持仓，5倍以上不隔夜"></a></td>
        <input type="hidden" name="managerMoney" id="managerMoney"/>
      </tr>
      <tr class="bdboder">
        <th>开始交易时间：</th>
        <td>
	        <c:if test="${weekday!=1&&weekday!=7}">    
		        <div id="span2" class="fl radio" style="margin:0px 0 0 210px;">
		           <span style="margin-right:5px">今天</span>
		        </div>
		        <div id="span3" class="fl radio" style="margin:0px 0 0 168px;">
		           <span style="margin-right:5px">下个交易日<a href="javascript:void(0);" class="wenti" style="margin-right:-13px;" original-title="一般选择下个交易日，如看中行情急需交易，可直接选择今天交易（今天开始收取账户管理费）"></a></span>
		        </div>
		           <input type="hidden" name="tradeTimeType" id="tradeTypeHidden" value="2"/>  
	        </c:if>
	        <c:if test="${weekday==1||weekday==7}">
		        <div class="fl radio" style="margin:0px 0 0 168px">
		           <span style="margin-right:5px">下个交易日<a href="javascript:void(0);" class="wenti" style="margin-right:-13px;" original-title="选择下个交易日，即从下个交易日起开始收取账户管理费"></a></span>
		           <input type="hidden" name="tradeTimeType" id="tradeTypeHidden" value="2"/> 
		        </div>
	        </c:if>
        </td>
      </tr>
    </table>
    </div>
    
    <div class="benj">
    	<p class="sp">您需要投入的本金：</p><span id="proMoney" class="spa"></span>
    	<div class="ptkeyzj">
	    	<span>可用资金:</span>
	    	<input type="text" disabled="disabled" name="acBalance" id="acBalance" value="<fmt:formatNumber value="${fuUser.accountBalance}" pattern="#0.00"/>"/>
    	</div>
    </div>
    <div id="ljpz" class="hqhbljpz">
       <a href="javascript:void(0);" onclick="stepProgram();">确认扩充融贷</a>
    </div>
    <div class="ty">
	    <input class="agree" id="isAgree" name="isAgree" type="checkbox" value="1"/> 我已阅读并同意
	    <a href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" class="jkxy">《投资网站服务协议》</a>
    </div>
    </div>
  </div>
  </form>
</div>
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
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script src="../js_hhr/jquery.nouislider.all.min.js"></script>
<link href="../js_hhr/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
changePro();

//实盘资金的输入限制
function changeMatch(money){
	if(isNaN(money)){
		jAlert("输入的融资资金只能是数字！","提示",function(){
			$("#match").val(1);
			$(".slider-range-min").val(1);
			$("#match").focus();
			changePro();
        });
		return false;
	}
	if(money>1000){
		$("#match").val(1000);
		$(".slider-range-min").val(1000);
		changePro();
		return;
	}
	if(money!=null&&money!=''&&money<1){
		$("#match").val(1);
		$(".slider-range-min").val(1);
		changePro();
		return;
	}
	if(money.indexOf(".")>-1){
		$("#match").val(Math.round(money));
		$(".slider-range-min").val(Math.round(money));
		changePro();
	}else{
		$(".slider-range-min").val(money);
		changePro();
	}
}

//实盘资金加按钮
function matchAdd(){
	  var num=parseInt($("#match").val())+1;
	  if(num>=1000){
	      $("#match").val(1000);
	      changeMatch($("#match").val());
	  }else{
	      $("#match").val(num);
	      changeMatch($("#match").val());
	  }
	  $(".slider-range-min").val(num);
};


//实盘资金减按钮
function matchDec(){
	  var num_dec=parseInt($("#match").val())-1;
	  if(num_dec<1){
	      $("#match").val(1);
	      changeMatch($("#match").val());
	  }else{
	      $("#match").val(num_dec);
	      changeMatch($("#match").val());
	  }
	  $(".slider-range-min").val(num_dec);
};


//选择资金倍率
function chooseNum(num){
    if(!num||num==0){
        num=1;
    }
	$("#num").val(num);
	$("#num"+num).parent("td").children("a").attr("class","qhzjbl");
	$("#num"+num).attr("class","qhzjbl qhzjbll");
	$("#num"+num).prevAll().attr("class","qhzjbl qhzjbll");
	changePro();
}


//改变方案类型（日配，月配）
function changeProgramType(type){
	if(type==1){
		$("#ypTR").hide();
		$("#rpTR").show();
		$("#programType1").attr("class","qhjysj qhjysja");
		$("#programType2").attr("class","qhjysj");
		$("#programType").val(1);
		$("#managerType").text("元/天");
		$("#rateInfo1").show();
		$("#rateInfo2").hide();
		chooseCycle1(1);
	}else{
		$("#rpTR").hide();
		$("#ypTR").show();
		$("#programType2").attr("class","qhjysj qhjysja");
		$("#programType1").attr("class","qhjysj");
		$("#programType").val(2);
		$("#managerType").text("元/月");
		$("#rateInfo1").hide();
		$("#rateInfo2").show();
		chooseCycle2(1);
	}
	changePro();
}

//选择资金使用时间(日配)
function chooseCycle1(cycle){
	var tradeTimeType=$("#tradeTypeHidden").val();
	$.post("${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum1="+cycle+"&tradeTimeType="+tradeTimeType,null,function(d){
		if(d==-1){
			$.alerts.okButton="确定";
			jAlert("子方案周期不能超过主方案的结束日期！","提示",function(){
        	});
        	return false;
		}
		if(!cycle||cycle==0){
        	cycle=1;
	    }
		$("#cycleNum1").val(cycle);
		if(cycle==2||cycle==4||cycle==6||cycle==7||cycle==8||cycle==9||cycle==11||cycle==12||cycle==13||cycle==14){
			$("#cycle1").parent("td").children("a").attr("class","qhzjbl");
			$("#cycleNum1").attr("style","background-color:#2db1e1;color:white;");
		}else{
			$("#cycleNum1").attr("style","background-color:#fff;color:#4B4B4B;");
			$("#cycle"+cycle).parent("td").children("a").attr("class","qhzjbl");
			$("#cycle"+cycle).attr("class","qhzjbl qhzjbll");
			$("#cycle"+cycle).prevAll().attr("class","qhzjbl qhzjbll");
		}
		changePro();
	});
}

//选择资金使用时间(月配)
function chooseCycle2(cycle){
	var tradeTimeType=$("#tradeTypeHidden").val();
	$.post("${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum2="+cycle+"&tradeTimeType="+tradeTimeType,null,function(d){
		if(d==-1){
			$.alerts.okButton="确定";
			jAlert("子方案周期不能超过主方案的结束日期！","提示",function(){
				if(cycle==1){
					changeProgramType(1);
				}
        	});
        	return false;
		}
		if(!cycle||cycle==0){
	        cycle=1;
	    }
		$("#cycleNum2").val(cycle);
		if(cycle==4||cycle==5||cycle==7||cycle==8||cycle==9||cycle==10||cycle==11){
			$("#cyc1").parent("td").children("a").attr("class","qhzjbl");
			$("#cycleNum2").attr("style","background-color:#2db1e1;color:white;");
		}else{
			$("#cycleNum2").attr("style","background-color:#fff;color:#4B4B4B;");
			$("#cyc"+cycle).parent("td").children("a").attr("class","qhzjbl");
			$("#cyc"+cycle).attr("class","qhzjbl qhzjbll");
			$("#cyc"+cycle).prevAll().attr("class","qhzjbl qhzjbll");
		}
		changePro();
	});	
}

//天数加按钮
function dayAdd(){
	var num=parseInt($("#cycleNum1").val())+1;
	var tradeTimeType=$("#tradeTypeHidden").val();
	$.post("${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum1="+num+"&tradeTimeType="+tradeTimeType,null,function(d){
		if(d==-1){
			$.alerts.okButton="确定";
			jAlert("子方案周期不能超过主方案的结束日期！","提示",function(){
        	});
        	return false;
		}
		if(num>=15){
		      $("#cycleNum1").val(15);
		      changeCycle1($("#cycleNum1").val());
		 }else{
		      $("#cycleNum1").val(num);
		      changeCycle1($("#cycleNum1").val());
		}
	});
};

//天数减按钮
function dayDec(){
	  var num_dec=parseInt($("#cycleNum1").val())-1;
	  if(num_dec<1){
	      $("#cycleNum1").val(1);
	      changeCycle1($("#cycleNum1").val());
	  }else{
	      $("#cycleNum1").val(num_dec);
	      changeCycle1($("#cycleNum1").val());
	  }
};

//月份加按钮
function monthAdd(){
	  var num=parseInt($("#cycleNum2").val())+1;
	  var tradeTimeType=$("#tradeTypeHidden").val();
	  $.post("${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum2="+num+"&tradeTimeType="+tradeTimeType,null,function(d){
		  if(d==-1){
		  	  $.alerts.okButton="确定";
			  jAlert("子方案周期不能超过主方案的结束日期！","提示",function(){
        	  });
        	  return false;
		  }
		  if(num>=12){
		      $("#cycleNum2").val(12);
		      changeCycle2($("#cycleNum2").val());
		  }else{
		      $("#cycleNum2").val(num);
		      changeCycle2($("#cycleNum2").val());
		  }
	  });
};

//月份减按钮
function monthDec(){
	  var num_dec=parseInt($("#cycleNum2").val())-1;
	  if(num_dec<1){
	      $("#cycleNum2").val(1);
	      changeCycle2($("#cycleNum2").val());
	  }else{
	      $("#cycleNum2").val(num_dec);
	      changeCycle2($("#cycleNum2").val());
	  }
};

//日配周期的输入限制
function changeCycle1(cycle){
	if(isNaN(cycle)){
		jAlert("输入的周期天数只能是数字！","提示",function(){
			$("#cycleNum1").val("");
			$("#cycleNum1").focus();
			chooseCycle1(1);
        });
		return false;
	}
	if(cycle>15){
		$("#cycleNum1").val(15);
		chooseCycle1(15);
		return false;
	}
	if(cycle!=null&&cycle!=''&&cycle<1){
		$("#cycleNum1").val(1);
		chooseCycle1(1);
		return false;
	}
	if(cycle.indexOf(".")>-1){
		$("#cycleNum1").val(Math.round(cycle));
		var str = Math.round(cycle);
		chooseCycle1(str);
	}else{
		chooseCycle1(cycle);
	}
}
//月配周期的输入限制
function changeCycle2(cycle){
	if(isNaN(cycle)){
		jAlert("输入的周期月数只能是数字！","提示",function(){
			$("#cycleNum2").val("");
			$("#cycleNum2").focus();
			chooseCycle2(1);
        });
		return false;
	}
	if(cycle>12){
		$("#cycleNum2").val(12);
		chooseCycle2(12);
		return false;
	}
	if(cycle!=null&&cycle!=''&&cycle<1){
		$("#cycleNum2").val(1);
		chooseCycle2(1);
		return false;
	}
	if(cycle.indexOf(".")>-1){
		$("#cycleNum2").val(Math.round(cycle));
		var str = Math.round(cycle);
		chooseCycle2(str);
	}else{
		chooseCycle2(cycle);
	}
}


//选择交易时间（今天/下个交易日）
function changeTradeType(type){
	if(type==1){
		$("#tradeType1").attr("class","qhjysj qhjysja");
		$("#tradeType2").attr("class","qhjysj");
		$("#span2").show();
		$("#span3").hide();
		$("#tradeTypeHidden").val(1);
	}else{
		var tradeTimeType=2;
		var cycleNum1=parseInt($("#cycleNum1").val());
		var cycleNum2=parseInt($("#cycleNum2").val());
		var programType=$("#programType").val();
		var location="";
		if(programType==1){
			location="${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum1="+cycleNum1+"&tradeTimeType="+tradeTimeType;
		}
		if(programType==2){
			location="${ctx}/user_program/changeCycleNumAjax.htm?addMatchId=${addMatchId}&cycleNum2="+cycleNum2+"&tradeTimeType="+tradeTimeType;
		}
		$.post(location,null,function(d){
			if(d==-1){
				$.alerts.okButton="确定";
				jAlert("子方案周期不能超过主方案的结束日期！","提示",function(){
					changeTradeType(1);
	        	});
	        	return false;
			}
			$("#tradeType1").attr("class","qhjysj");
			$("#tradeType2").attr("class","qhjysj qhjysja");
			$("#span2").hide();
			$("#span3").show();
			$("#tradeTypeHidden").val(2);
		});
	}
}

//选择是否使用点劵
function changeScore(){
    //不使用点劵
    if($("#allScore").attr("checked")==undefined){
		$(".jyed").css({"visibility":"hidden"});
	}
	//使用点劵
	if($("#allScore").attr("checked")=="checked"){
		$(".jyed").css({"visibility":"visible"});
	}
	changePro();
}

//改变积分的文本框的值
function changeIntegral(score){
    if(isNaN(score)){
		jAlert("输入的点劵只能是数字！","提示",function(){
			$("#point").val("");
			$("#point").focus();
			changePro();
        });
		return false;
	}
	var maxScore=parseInt($("#kydqDs").text()/100);
	if(score>maxScore){
		$("#point").val(maxScore);
		changePro();
		return false;
	}
	if(score!=null&&score!=''&&score<0){
		$("#point").val(0);
		changePro();
		return false;
	}
	if(score.indexOf(".")>-1){
		$("#point").val(Math.round(score));
		var str = Math.round(score);
		$("#point").val(str);
	}
	changePro();
}

//初始化操作
$(function(){
	$(".jyed").css({"visibility":"hidden"});
	$("#rateInfo1").hide();
	$("#rateInfo2").show();
	
	//滑块移动
	var slider=$(".slider-range-min").noUiSlider({start: 1, range:{'min': [1],'20%': [10],'40%': [50],'60%': [100],'80%': [500],'max': [1000]}
                }).Link('lower').to('-inline-<div class="tooltip"></div>', function(value) {
                    $(this).html('<span>'  + float2int(value) +  '</span>');
                    $("#match").val(float2int(value));
                    changePro();
                });
				function float2int(value) {
			        return value | 0;
			    }
	//手动输入     
	$("#match").change(function(){
		var match=$("#match").val();
	    $(".slider-range-min").val(match);
	    changeMatch($(this).val());
	});
	<c:if test="${!empty programType}">
		<c:if test="${programType=='1'}">
		    changeProgramType(1);
		</c:if>
		<c:if test="${programType=='2'}">
		    changeProgramType(2);
		</c:if>
	</c:if>
	<c:if test="${!empty matchMoney}">
		$(".slider-range-min").val(${matchMoney});
	</c:if>
	<c:if test="${!empty doubleNum}">
		chooseNum('${doubleNum}');
	</c:if> 
	<c:if test="${num>0}">
		chooseNum('${num}');
	</c:if>
	<c:if test="${programType=='1'}">
	<c:if test="${cycleNum1>0}">
		chooseCycle1(${cycleNum1});
	</c:if>
	</c:if>
	<c:if test="${programType=='2'}">
	<c:if test="${cycleNum2>0}">
		chooseCycle2(${cycleNum2});
	</c:if>
	</c:if>
	<c:if test="${!empty tradeTimeType}">
		changeTradeType('${tradeTimeType}');
	</c:if>
	<c:if test="${integral>0}">
	    $("#allScore").attr("checked","checked");
		$(".jyed").css({"visibility":"visible"});
		$("#point").val(${integral/100});
		$("#integral").val(${integral});
		$("#scoreInfo").text(${integral});
		var a=parseInt($("#safeInfo").text());
		var b=parseInt($("#managerInfo").text());
		$("#proMoney").text("¥"+formatCurrency(a+b-${integral}));
		changeIntegral(${integral/100});
	</c:if>
});

$(document).ready(function(){
	$(".wenti").tipsy({gravity: 's'}); 
	$(".num10").tipsy({gravity: 's'}); 
	$(".nowDate").tipsy({gravity: 's'}); 
});

/** 
 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.45' 
 * @type String 
 */  
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  

//计算右边栏目各种费用的值
function changePro(){
	$("#span2").hide();
	$("#span3").show();
	
	var matchMoney=$("#match").val();
	$("#matchMoney").val(matchMoney);
	var num=parseInt($("#num").val());
	if(!matchMoney||!num){
		$("#safeInfo").text(0);
		$("#totalInfo").text(0);
		$("#warnInfo").text(0);
		$("#flatInfo").text(0);
		$("#managerInfo").text(0);
		$("#proMoney").text("¥0.00");
	}else{
		//风险保证金
		var safeInfo=Math.round(matchMoney*10000/num);
		$("#safeInfo").text(safeInfo);
		//总操盘资金
		var totalInfo=Math.round(matchMoney*10000+safeInfo);
		$("#totalInfo").text(totalInfo);
		//亏损警戒线
		var param1=0;
		<c:if test="${!empty pam.warnlinePercent}">
			param1=${pam.warnlinePercent};
		</c:if>
		var warnInfo=Math.round(matchMoney*10000+safeInfo*param1);
		$("#warnInfo").text(warnInfo);
		//亏损平仓线
		var param2=0;
		<c:if test="${!empty pam.flatlinePercent}">
			param2=${pam.flatlinePercent};
		</c:if>
		var flatInfo=Math.round(matchMoney*10000+safeInfo*param2);
		$("#flatInfo").text(flatInfo);
		
		
		//账户管理费
		var programType=$("#programType").val();
		if(programType==1){
			$("#managerType").text("元/天");
			var param3=0;
			<c:if test="${!empty pam.feeDay}">
				param3=${pam.feeDay};
			</c:if>
			var managerInfo=Math.round(matchMoney*param3);
			$("#managerInfo").text(managerInfo);
		}else{
			$("#managerType").text("元/月");
			var cycleNum2=$("#cycleNum2").val();
			if(cycleNum2>3){
				var param4=0;
				<c:if test="${!empty pam.interestPercent}">
					param4=${pam.interestPercent};
				</c:if>
				var managerInfo=Math.round(matchMoney*10000*param4);
				$("#managerInfo").text(managerInfo);
			}else{
				var param5=0;
				<c:if test="${!empty pam.feePercent}">
					param5=${pam.feePercent};
				</c:if>
				var managerInfo=Math.round(matchMoney*10000*param5);
				$("#managerInfo").text(managerInfo);
			};
		};
		
		
		//方案费用
		var a=parseInt($("#safeInfo").text());
		var b=parseInt($("#managerInfo").text());
		var c;
		if(!$("#point").val()||$("#allScore").attr("checked")==undefined){
		    c=0;
		}else{
		    c=parseInt($("#point").val()*100);
		}
		//使用积分数量
	    $("#scoreInfo").text(c);
	    $("#integral").val(c);
		$("#proMoney").text("¥"+formatCurrency(a+b-c));
		
		//点劵
		if(${fuUser.integral}>a/2){
			$("#kydqDs").text(a/2);
		}else{
			$("#kydqDs").text(${fuUser.integral});
		}
		
		var maxScore=parseInt($("#kydqDs").text());
		var score=parseInt($("#point").val());
		if(score>maxScore/100){
			$("#point").val(parseInt(maxScore/100));
			$("#scoreInfo").text(parseInt(maxScore));
			$("#integral").val(maxScore);
			$("#proMoney").text("¥"+formatCurrency(a+b-maxScore));
		}
		
		$("#kydqRedL").text(formatCurrency($("#point").val()*100));
	};
}


//立即配资
function stepProgram(){
	var f=$("#isAgree").attr("checked");
	if(f!='checked'){
		$.fancybox.open({
 			href : '${ctx}/index_info/agreeInfoAjax.htm',
 			type : 'ajax',
 			padding : 5, 
 		});
		return false;
	}
	var num=$("#num").val();
	if(Number(num)==0){
		$.alerts.okButton='确定';
		jAlert('请输入您的资金倍率！','提示',function(){
			$("#num").focus();
		});
		return false;
	}
	var matchMoney=$("#match").val();
	if(Number(matchMoney)==0){
		$.alerts.okButton='确定';
		jAlert('请输入您的融资资金！','提示',function(){
			$("#match").focus();
		});
		return false;
	}
	if(matchMoney<0.1){
		$.alerts.okButton='确定';
		jAlert('自有资金太少不适合配资，最少1000元！','提示',function(){
			$("#match").focus();
		});
		return false;
	}
	var cycleNum1=$("#cycleNum1").val();
	var cycleNum2=$("#cycleNum2").val();
	var programType=$("#programType").val();
	if(programType==1&&Number(cycleNum1)==0){
		$.alerts.okButton='确定';
		jAlert('请输入您的资金使用周期！','提示',function(){
			$("#cycleNum1").focus();
		});
		return false;
	}
	if(programType==2&&Number(cycleNum2)==0){
		$.alerts.okButton='确定';
		jAlert('请输入您的资金使用周期！','提示',function(){
			$("#cycleNum2").focus();
		});
		return false;
	}
	var tradeTimeType=$("#tradeTypeHidden").val();
	var integral=$("#integral").val();
	if(!integral){
	    integral=0;
	}
	var a=parseInt($("#safeInfo").text());
	if(Number(integral)>(a/2)){
		$.alerts.okButton='确定';
		jAlert('使用的点劵不能超过风险保证金金额的50%！','提示',function(){
		});
		return false;
	}	
	var addMatchId=$("#addMatchId").val();	
	var data=$("#programForm").serialize();
	$.post("${ctx}/user_program/addProgramAjax.htm",data,function(d){
		if(d==-5){
			lgInfo(1);	
			return false;
		}
		if(d==-2){
			$.alerts.okButton='确定';
			jAlert('请选择或直接输入融资资金！','提示',function(){
				$("#match").focus();
			});
			return false;
		}
		if(d==-3){
			sureInfo('确定','请选择资金使用时间！','提示');
			return false;
		}
		if(d==-4){
			sureInfo('确定','系统参数还没有设置，请联系客服！','提示');
			return false;
		}
		location.href="${ctx}/user_program/addProgramAjax.htm?addMatchId="+addMatchId+"&matchMoney="+matchMoney+"&num="+num+"&programType="+programType+"&tradeTimeType="+tradeTimeType+"&cycleNum1="+cycleNum1+"&cycleNum2="+cycleNum2+"&integral="+integral;
	});
}
</script>