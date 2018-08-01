<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" ny_bt ny_bt0">
                <span class="pbsx"></span><span class="ny_bt_cont">确认操盘规则</span>
            </div>
            <div class=" zhuanzhang">
			  <table border="0" cellspacing="0" cellpadding="0">
			  <tbody><tr>
			    <td width="150" height="50" align="right">操盘须知：</td>
			    <td width="280" height="50">
			        只能购买主力合约或者次主力合约
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">资金倍率：</td>
			    <td width="280" height="50">
			    		<c:if test="${!empty ppId}"><input type="hidden" value="${num}" name="num"/></c:if>
				        <div class=" select"><select name="num" id="num" onchange="changePro();" <c:if test="${!empty ppId}">disabled="disabled"</c:if> >
				        <option <c:if test="${num==1}">selected="selected"</c:if> value="1">1倍（股指、商品）</option>
				        <option <c:if test="${num==5}">selected="selected"</c:if> value="5">5倍（股指、商品）</option>
				        <option <c:if test="${num==10}">selected="selected"</c:if> value="10">10倍（仅股指）</option>
				        </select></div>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">风险保证金：</td>
			    <td width="280" height="50">
			        <span class="bzj" id="safeInfo"><fmt:formatNumber value="${safeInfo}" pattern="#,###,##0.00"/></span><span style="margin:0 10px">元</span><a href="javascript:void(0);"  class=" wenti" original-title="在您操盘出现亏损后用于支付亏损金，结束时如无亏损全额退还，保证金越低收益越大，同时风险也越大"></a>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">总操盘资金：</td>
			    <td width="280" height="50">
			        <span class="bzj" id="totalInfo"><fmt:formatNumber value="${totalInfo}" pattern="#,###,##0.00"/></span><span style="margin:0 10px">元</span>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">亏损警戒线：</td>
			    <td width="280" height="50">
			        <span class="bzj" id="warnInfo"><fmt:formatNumber value="${warnInfo}" pattern="#,###,##0.00"/></span><span style="margin:0 10px">元</span><a href="javascript:void(0);"  class=" wenti" original-title="当总操盘资金低于警戒线以下时，只能平仓不能建仓，需要尽快补充风险保证金，以免低于亏损平仓线被平仓"></a>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">亏损平仓线：</td>
			    <td width="280" height="50">
			        <span class="bzj bzj0" id="flatInfo"><fmt:formatNumber value="${flatInfo}" pattern="#,###,##0.00"/></span><span style="margin:0 10px">元</span><a href="javascript:void(0);"  class=" wenti" original-title="当总操盘资金低于平仓线以下时，我们将有权把您的股票进行平仓，为避免平仓发生，请时刻关注风险保证金是否充足"></a>
			    </td>
			  </tr>
			   <tr>
			    <td width="150" height="50" align="right">资金使用时间：</td>
			    <td width="280" height="50">
			        <div class="fl radio" style="margin-top:7px;">
			            	<input <c:if test="${programType==1}">checked="checked"</c:if> name="programType" type="radio" value="1" onclick="changePro();"><span style="margin-right:5px">天</span>
			        </div>
			        <div class="fl select"><select name="cycleNum1" id="cycleNum1" onchange="changePro();" style="width:60px;">
			        <c:forEach begin="1" end="15" var="i" varStatus="row">
			        	<option <c:if test="${cycleNum1==i}">selected="selected"</c:if> value="${i}">${i}天</option>
			        </c:forEach>
			        </select></div>
			        <div class="fl radio" style="margin:7px 0 0 8px;">
			            	<input <c:if test="${programType==2}">checked="checked"</c:if> name="programType" type="radio" value="2" onclick="changePro();"><span style="margin-right:5px">月</span>
			        </div>
			        <div class="fl select"><select name="cycleNum2" id="cycleNum2" onchange="changePro();" style="width:60px; float:left">
			        <c:forEach begin="1" end="12" var="i" varStatus="row">
			        	<option <c:if test="${cycleNum2==i}">selected="selected"</c:if> value="${i}">${i}个月</option>
			        </c:forEach>
			        </select></div>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">账户管理费：</td>
			    <td width="280" height="50">
			        <span class=" zh0" id="managerInfo"><fmt:formatNumber value="${managerInfo}" pattern="#,###,##0.00"/></span><span style=" font-size:12px;" id="dsp">${programType==1?'/天':'/月'}</span><span><span class="col_ft" id="msp" style="margin-left:10px;"><c:if test="${programType==1}">不隔夜，每天收盘前自动平仓</c:if><c:if test="${programType==2}">管理费率${num}倍隔夜持仓</c:if></span></span>
			    </td>
			  </tr>
			  <tr>
			    <td width="150" height="50" align="right">开始交易时间：</td>
			    <td width="280" height="50">
			    <c:if test="${weekday!=1&&weekday!=7}">
			        <div class="fl radio" style="margin-top:7px;">
			            	<input name="tradeTimeType" <c:if test="${tradeTimeType==1}">checked="checked"</c:if>  type="radio"  value="1"><span style="margin-right:5px">今天</span>
			        </div>
			        
			        <div class="fl radio" style="margin:7px 0 0 8px;">
			            	<input name="tradeTimeType" <c:if test="${tradeTimeType==2}">checked="checked"</c:if> type="radio" value="2"><span style="margin-right:5px">下个交易日<a href="javascript:void(0);" class=" wenti" style="margin:0 5px;" original-title="一般选择下个交易日，如看中行情急需交易，可直接选择今天交易（今天开始收取账户管理费）"></a></span>
			       </div>
			    </c:if>
			    <c:if test="${weekday==1||weekday==7}">
			        
			        <div class="fl radio" style="margin:7px 0 0 8px;">
			            	<input name="tradeTimeType" <c:if test="${tradeTimeType==2}">checked="checked"</c:if> type="radio" value="2" checked="checked"><span style="margin-right:5px">下个交易日<a href="javascript:void(0);" class=" wenti" style="margin:0 5px;" original-title="选择下个交易日，即从下个交易日起开始收取账户管理"></a></span>
			       </div>
			    </c:if>    
			    </td>
			  </tr>
			</tbody>
		</table>
      </div>
<script>
$('#totalLine').stop();
$('.nodes').hide();
$('#blueLine').css({height:'${( num==10 ? 7 : num)/(( num==10 ? 7 : num)+1)*100}%'});
$('#orangeLine').css({height:'${1/(( num==10 ? 7 : num)+1)*100}%'});
$('#matchMoneyNode').text('${totalInfo-safeInfo}');
$('#safeMoneyNode').text('${safeInfo}');
$('#closeLineNode').text('${flatInfo}');
$('#warnLineNode').text('${warnInfo}');
$('#totalLine').css({height:'0px'});
$('#totalLine').animate({height:'500px'},function(){
	$('.nodes').fadeIn();
});
</script>