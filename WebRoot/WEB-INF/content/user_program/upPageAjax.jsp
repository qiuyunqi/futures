<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:800px">
<form id="upForm"> 
	<div class=" fucheng_title"><span>升级方案</span>
	  <div class="clr"></div></div>
    
        <div class=" dl_cont0" style="margin:15px 0 -20px 20px;">
            <input onclick="changeRadio('${program.doubleNum}',${n},${h},${program.programType});" type="radio" id="upType1" name="upType" value="1" checked="checked">
        	<span style="margin-right:20px;">每日临时升级</span>
            <input onclick="changeRadio('${program.doubleNum}',${n},${h},${program.programType});" type="radio" id="upType2" name="upType" value="2">
            <span>升级到结算日</span>
      </div>   
        <div class="zhuyi_0 zhuyi_00">
                	<p>1.升级方案是指在您原有方案的基础上，加入新的方案配比到资金量更大的账户。</p>
                    <p>2.每日临时升级是指，当天升级。按日方案收取利息，收盘后自动回收资金。</p>
                    <p>3.升级到方案结算日是指，资金升级到方案结束交易日。利息计算按升级天数/30*当前月息*升级金额。</p>
                    <p>4.可升级的条件为，盘中无持仓或者收盘后。</p>
      </div> 
      <div class="" style="margin:20px 0 10px 20px"><span class="b1">您目前可用余额：</span><span><fmt:formatNumber value='${empty fuUser.accountBalance?0:fuUser.accountBalance}' pattern='#,##0.00'/></span><span class="b1">元</span></div>
      <div class="form" style="padding:0 0 0px 20px;">
        	<div class="fl sjje">请问您需要升级的金额：</div>
            <div class="fl input tqlr"><input name="money" id="upMoney" type="text" onkeyup="changePro(this.value,'${program.doubleNum}',${n},${h},${program.programType});"></div>
            <div class="fl lh0">元</div>
            <div class="clr"></div>
    </div>       
    <div class=" zhuyi_0 zhuyi_00">
                	<p>需缴纳的手续费为：<span id="upManagerMoney">0</span>元</p>
                    <p>升级后的方案为</p>
      </div>  
      
      <div class=" rpfa" style="width: 800px">
            	<table width="800px" border="0" cellspacing="0" cellpadding="0">
  <tbody>
    <tr>
      <td width="150" align="right"><span class=" zcpzj_num" id="upTotalMoney"><fmt:formatNumber value="${program.totalMatchMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
      <td width="80" rowspan="2" align="center"><span class="equal">=</span></td>
      <td width="150" align="center"><span class=" zcpzj_num" id="upMatchMoney"><fmt:formatNumber value="${program.matchMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
      <td width="80" rowspan="2" align="center"><span class="add ">+</span></td>
      <td width="150" align="left"><span class=" zcpzj_num" id="upSafeMoney"><fmt:formatNumber value="${program.safeMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
    </tr>
    <tr>
      <td align="right"><span class="zcpzj">总操盘资金</span></td>
      <td align="center"><span class="zcpzj">实盘资金</span></td>
      <td align="left"><span class="zcpzj">风险保证金</span></td>
    </tr>
    <tr>
      <td align="right ">&nbsp;</td>
      <td align="center">&nbsp;</td>
      <td colspan="3" align="center">&nbsp;</td>
      </tr>
    <tr>
      <td align="right "><b class=" yjx">预警线：</b><b id="upWarnLine">0</b><b>元</b></td>
      <td align="center"></td>
      <td colspan="3" align="center"><b class="yjx">平仓线：</b><b id="upCloseLine">0</b><b>元</b></td>
      </tr>
  </tbody>
</table>

      </div>
      
        <div style="text-align:center; margin:30px 0 0px 20px; padding-bottom:0px;">
        <a href="javascript:void(0);" onclick="upProgramInfo();" class=" queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
    </div>
      <div class=" zhuyi_0">
                	<p class="zhuyi">升级说明:</p>
                	<p>1.实盘资金 = 输入的升级金额 + 原倍数</p>
                    <p>2.风险保证金 = 原风险保证金 + 输入的升级金额</p>
                    <p>3.每日临时升级的利息计算为 （ 实盘资金 / 万 ） * 利息额</p>
                    <p>4.升级到结算日：利息就按上面的公式计算利息。</p>
      </div>  
</form>        
</div>

<script>
	function upProgramInfo(){
		jConfirm("您确定要升级该方案吗？","操作提示",function(res){
			if(res){
				var m=$("#upMoney").val();
				if(m==null||m==''){
					$.alerts.okButton='确定';
					jAlert('升级金额不能为空！','提示',function(){
						$("#upMoney").focus();
					});
					return false;
				}
				if(isNaN(m)){
					$.alerts.okButton='确定';
					jAlert('升级金额只能输入数字！','提示',function(){
						$("#upMoney").val('');
						$("#upMoney").focus();
					});
					return false;
				}
				var data=$("#upForm").serialize();
				$.post("${ctx}/user_program/saveUpProgramAjax.htm?id=${id}&n=${n}&h=${h}",data,function(d){
					var json = eval("("+d+")");
					if(json.code==-2){
						lgInfo(1);	
						return false;
					}
					if(json.code==-3){
						$.alerts.okButton="去充值";
						$.alerts.cancelButton="返回";
						
						var mn=(json.message-${empty fuUser.accountBalance?0:fuUser.accountBalance});
						var mnStr = numeral(mn).format('0,0.00');
						
						jConfirm("升级方案需要支付"+numeral(json.message).format('0,0.00')+"元,当前余额为<fmt:formatNumber value='${empty fuUser.accountBalance?0:fuUser.accountBalance}' pattern='#,##0.00'/>元.您还需要支付"+mnStr+"元。","操作提示",function(res){
							if(res){
								location.href="${ctx}/user_recharge/recharge.htm?money="+mn;
							}
						});
						return false;
						sureInfo('确定','余额不足！','提示');
						return false;
					}
					$.alerts.okButton="确定";
					$.alerts.cancelButton="返回";
					jConfirm("方案升级成功！","操作提示",function(res){
							if(res){
								location.href=location.href;
							}
					});
				});
				
				
				
			}
		});
	}
	function changeRadio(num,n,h,type){
		var matchMoney=$("#upMoney").val();
		changePro(matchMoney,num,n,h,type);
	}
	
	
	function changePro(matchMoney,num,n,h,type){
		var tmm=${program.totalMatchMoney};
		var mm=${program.matchMoney};
		var sm=${program.safeMoney};
	
		var m=$("#upMoney").val();
		if(isNaN(m)){
			$.alerts.okButton='确定';
			jAlert('升级金额只能输入数字！','提示',function(){
				$("#upMoney").val('');
				$("#upMoney").focus();
			});
			return false;
		}
		
		matchMoney=Number(matchMoney);
		//风险保证金
		var safeInfo=matchMoney/num;
		$("#upSafeMoney").text(numeral(safeInfo+sm).format('0,0.00'));
		//总操盘资金
		var totalInfo=matchMoney+safeInfo;
		$("#upTotalMoney").text(numeral(totalInfo+tmm).format('0,0.00'));
		//实盘资金
		$("#upMatchMoney").text(matchMoney+mm);
		//亏损警戒线
		var param1=0;
		<c:if test="${!empty pam.warnlinePercent}">
			param1=${pam.warnlinePercent};
		</c:if>
		var warnInfo=matchMoney+safeInfo*param1;
		$("#upWarnLine").text(numeral(warnInfo).format('0,0.00'));
		//亏损平仓线
		var param2=0;
		<c:if test="${!empty pam.flatlinePercent}">
			param2=${pam.flatlinePercent};
		</c:if>
		var flatInfo=matchMoney+safeInfo*param2;
		$("#upCloseLine").text(numeral(flatInfo).format('0,0.00'));
	
		
		//账户管理费
		var upType1=$("#upType1").attr("checked");
		if(upType1=="checked"){//临时升级
			var param3=0;
			//日配和月配都一样----一天的日配利息
			<c:if test="${!empty pam.feeDay}">
				param3=${pam.feeDay};
			</c:if>
			var managerInfo=matchMoney*param3;
			$("#upManagerMoney").text(numeral(managerInfo).format('0,0.00'));
		}else{//永久升级
			var param4=0;
			if(type==1){//日配
				<c:if test="${!empty pam.feeDay}">
					param4=${pam.feeDay};
				</c:if>
				var managerInfo=(matchMoney*param4)*n;//含周末的天数
				$("#upManagerMoney").text(numeral(managerInfo).format('0,0.00'));
			}else{//月配
				<c:if test="${!empty program.moneyPercent}">
					param4=${program.moneyPercent};
				</c:if>
				var managerInfo=(matchMoney*param4)/30*h;//h不含周末的天数
				$("#upManagerMoney").text(numeral(managerInfo).format('0,0.00'));
			}
		}		
		
	}
	
</script>