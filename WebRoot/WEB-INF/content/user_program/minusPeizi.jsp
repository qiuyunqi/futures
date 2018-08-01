<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－减少配资</title>
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
}
.sp{text-indent:8px;}
.spa{
	color:#f1982f;
	font-size:25px;
	display:block;
	width:102px;
	margin-left:8px;
 }
.agree{
	margin-top:15px;
	texe-aligin:center;
}
.ty{
	width:260px;
	margin-left:18px;
}
#acBalance{height: 20px;line-height: 20px;border:none; width: 85px;color: #999999;background: white;}
.ptkeyzj{height:35px;line-height: 32px;text-indent: 5px;}
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
  <div class="hqhtop" style="height:350px;">
    <input id="subMatchId" type="hidden" value="${subMatchId}"/>
    <input id="programType" type="hidden" value="${programType}"/>
    <input id="tradeTimeType" type="hidden" value="${tradeTimeType}"/>
    <input id="cycleNum1" type="hidden" value="${cycleDay}"/>
    <input id="cycleNum2" type="hidden" value="${cycleMonth}"/>
    <input id="integral" type="hidden" value="0"/>
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
	         <input id="match" name="match" type="text" value="1" onkeyup="changeMatch(this.value)" class="qhspzj qhspzjk match" style="width:60px;" original-title="无法超出原融资资金"/>
	        <div class="hqhfy"><img src="../images_hhr/zhpz01.png" onclick="matchAdd()"/><img src="../images_hhr/zhpz01a.png" onclick="matchDec()"/></div>
	        <input id="matchMoney" name="matchMoney" type="hidden">
        </td>
      </tr>
      <tr>
        <th>资金倍率：</th>
        <td>
            <a id="num1" href="javascript:void(0)" class="qhzjbl qhzjbll" onclick="chooseNum(1);">1<span>倍</span></a>
	        <a id="num2" href="javascript:void(0)" class="qhzjbl" onclick="chooseNum(2);">2</a>
	        <a id="num3" href="javascript:void(0)" class="qhzjbl" onclick="chooseNum(3);">3</a>
	        <a id="num5" href="javascript:void(0)" class="qhzjbl" onclick="chooseNum(5);">5</a>
	        <a id="num10" href="javascript:void(0)" class="qhzjbl num10" onclick="chooseNum(10);" original-title="5倍以上只能做股指">10</a>
	        <input id="num" name="num" type="hidden" value="1"/>
        </td>
      </tr>
      <tr>
        <th>可用资金：</th>
        <td>
	        <span><fmt:formatNumber value="${ownMoney<0?0.00:ownMoney}" pattern="#,###,##0.00"/>元 <a href="javascript:void(0);" class=" wenti" original-title="可用资金：交易账户动态权益     - 方案原始融资资金"></a></span>
        </td>
      </tr>
      <tr>
        <th>减配须知：</th>
        <td>
	        <span style="color:#FF6633;font-size:16px;">减配后的风险保证金必须低于交易账户当前可用资金，减配后原有方案会自动变更成新方案，强平线和预警线相应调整，客户账号保持不变，约定收益将不会重复收取。减配出的客户资金将默认为客户保证金留在账户中，如需提取，可按照提取利润规则操作。</span>
        </td>
      </tr>
    </table>
  </div>
  <div class="hqhbottom" style="height:410px;">
    <div class="hqhbtab" style="margin: 0 2px 20px;">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr class="bdboder">
        <th style="color:#006699;font-size:18px;font-weight:bold;width:165px;padding-left:0;">减配后风险保证金：</th>
        <td><span id="safeInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="在您操盘出现亏损后用于支付亏损金，结束时如无亏损则全额退还，保证金越低收益越大，同时风险也越大" ></a></td>
        <input type="hidden" name="safeMoney" id="safeMoney"/>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配后总操盘资金：</th>
        <td><span id="totalInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="总操盘资金等于融资资金与风险保证金之和"></a></td>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配后预警线：</th>
        <td><span id="warnInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="当总操盘资金低于警戒线以下时，需要尽快补充风险保证金，以免低于亏损平仓线被平仓"></a></td>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配后平仓线：</th>
        <td><span id="flatInfo">0</span>元<a href="javascript:void(0);" class=" wenti" original-title="当总操盘资金低于平仓线以下时，我们将有权对您的期货持仓进行平仓，为避免平仓发生，请时刻关注风险保证金是否充足"></a></td>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配后约定收益率：</th>
        <td><span id="rateInfo1"><fmt:formatNumber value="${empty pam.feeDay?0:pam.feeDay}" pattern="#,###,##0.00"/>元/万</span><span id="rateInfo2"><fmt:formatNumber value="${empty pam.feePercent?0:pam.feePercent*100}" pattern="#,###,##0.00"/>%</span><a href="javascript:void(0);" class=" wenti" original-title="利率为收取风险保证金的利息百分比"></a></td>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配后约定收益：</th>
        <td><span id="managerInfo">0</span><span style="font-size:14px;" id="managerType">元/天</span><a href="javascript:void(0);" class=" wenti" original-title="日配不隔夜，每天收盘前自动平仓；月配5倍以内，自有资金商品2倍/股指3倍隔夜持仓，5倍以上不隔夜"></a></td>
        <input type="hidden" name="managerMoney" id="managerMoney"/>
      </tr>
      <tr class="bdboder">
        <th style="padding-left:0">减配生效时间：</th>
        <td>
	        <span style="margin-right:12px">下一个交易时间段<a href="javascript:void(0);" class="wenti" style="margin-right:-13px;" original-title="交易时段为 9:00-11:30 13:00-15:15 20:00-23:00"></a></span>
	        <input type="hidden" name="tradeTimeType" id="tradeTypeHidden" value="2"/>  
        </td>
      </tr>
    </table>
    </div>
    <div class="ptkeyzj">
	    	<span>平台可用资金:</span>
	    	 <input type="text" disabled="disabled" name="acBalance" id="acBalance" value="<fmt:formatNumber value="${fuUser.accountBalance}" pattern="#0.00"/>"/>
    		<input type="hidden" id="proMoney" />
    	</div>
   
    
    <div id="ljpz" class="hqhbljpz">
       <a href="javascript:void(0);" onclick="stepProgram();">确认减配</a>
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
	if(money>${match}){
		$("#match").val('${match}');
		$(".slider-range-min").val('${match}');
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
		  var num_add=parseInt($("#match").val())+1;
		  if(num_add>${match}){
		  	  $("#match").val('${match}');
		  	  num_add=$("#match").val();
		  }else{
		      $("#match").val(num_add);
		  }
		  changePro();
		  $(".slider-range-min").val(num_add);
};


//实盘资金减按钮
function matchDec(){
		  var num_dec=parseInt($("#match").val())-1;
		  if(num_dec<1){
		      $("#match").val(1);
		  }else if(num_dec>${match}){
		  	  $("#match").val('${match}');
		  	  num_dec=$("#match").val();
		  }else{
		      $("#match").val(num_dec);
		  }
		  changePro();
		  $(".slider-range-min").val(num_dec);
};


//选择资金倍率
function chooseNum(num){
	var matchMoney=$("#match").val();
	$.post("${ctx}/user_program/changeDoubleNumAjax.htm?subMatchId=${subMatchId}&num="+num+"&matchMoney="+matchMoney,null,function(d){
		if(d==-1){
			$.alerts.okButton="确定";
			jAlert("减配后的风险保证金不能超过交易账户的可用资金！","提示",function(){
        	});
        	return false;
		}
	    if(!num||num==0){
	        num=1;
	    }
		$("#num").val(num);
		$("#num"+num).parent("td").children("a").attr("class","qhzjbl");
		$("#num"+num).attr("class","qhzjbl qhzjbll");
		$("#num"+num).prevAll().attr("class","qhzjbl qhzjbll");
		changePro();
	});
}
 
function findMaxMatch(){
 	var num=$("#num").val();
 	$.post("${ctx}/user_program/findMaxMatch.htm?subMatchId=${subMatchId}&num="+num,null,function(d){
        var matchNum=d;
        var match=$("#match").val();
		if(parseInt(match)>parseInt(matchNum)){
			$(".slider-range-min").val(matchNum);
		}else{
			return false;
		}
	});
}

//初始化操作
$(function(){
	$("#rateInfo1").hide();
	$("#rateInfo2").show();
	
	//滑块移动
	var match=${match};
	var slider=$(".slider-range-min").noUiSlider({start:[1, match], range:{'min':[1],'20%':[10],'40%':[50],'60%':[100],'80%':[500],'max':[1000]}
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
	
	$(".slider-range-min").val('${match}');
	
	
	<c:if test="${!empty matchMoney}">
		$(".slider-range-min").val(${matchMoney});
	</c:if>
	<c:if test="${doubleNum>0&&num<=0}">
		chooseNum('${doubleNum}');
	</c:if>
	<c:if test="${doubleNum>0&&num>0}">
		chooseNum('${num}');
	</c:if>
});

$(document).ready(function(){
	$(".wenti").tipsy({gravity: 's'}); 
	$(".num10").tipsy({gravity: 's'}); 
	$(".match").tipsy({gravity: 's'}); 
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
	var matchMoney=$("#match").val();
	$("#matchMoney").val(matchMoney);
	var num=parseInt($("#num").val());
	if(!matchMoney||!num){
		$("#safeInfo").text(0);
		$("#totalInfo").text(0);
		$("#warnInfo").text(0);
		$("#flatInfo").text(0);
		$("#managerInfo").text(0);
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
			}
		}
	}
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
	var safeInfo=$("#safeInfo").text();
	var ownMoney=${ownMoney};
	if(Number(safeInfo)>Number(ownMoney)){
		$.alerts.okButton='确定';
		jAlert('减配后的风险保证金必须低于交易账户当前可用资金,请重新设置方案！','提示',function(){
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
	var subMatchId=$("#subMatchId").val();	
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
		location.href="${ctx}/user_program/addProgramAjax.htm?subMatchId="+subMatchId+"&matchMoney="+matchMoney+"&num="+num+"&programType="+programType+"&tradeTimeType="+tradeTimeType+"&cycleNum1="+cycleNum1+"&cycleNum2="+cycleNum2+"&integral="+integral;
	});
}
</script>