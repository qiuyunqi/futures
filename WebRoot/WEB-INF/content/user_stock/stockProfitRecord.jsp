<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－累计盈亏</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ include file="../common/css.jsp"%> 
<style type="text/css">
	body{background: #efefef;}
	.title{margin-bottom:20px;}
	.hwdtabbd table{border:0 !important;}
	.hwdtabt{text-align: center;font-size: 19px !important;height:57px !important;border:0 !important;width:480px;margin:0 auto;}
	.hwdtabt>span{border-bottom:1px solid #d0d0d0;padding:0 50px 10px;color:#333 !important;position: relative;}
	.hwdtabbd th{height: 145px !important;background: #f7f7f7 url("../images/mony.png") no-repeat scroll left bottom !important;border:0 !important; }
	.hwdtabbd th span{font-size: 86px;}
	.zheng{color:#ff9900;}
	.fu{color:green;}
	.pay{border:1px solid #2db1e1;border-radius:20px;padding:2px 15px;background:#2db1e1;color:#fff;font-size:11px;margin-right: 20px;float:right;}
	.hwdtabbd td:last-child {text-align: center  !important;}
	.hwdzhrtab{margin-bottom: 15px;}
	.fenge{display: block;width:1px;height:21px;background:#d0d0d0;float:right;}
	.hgrzxt td{height:0 !important;text-align: center;}
	.hgrzxt .tdyMony{border:1px solid #a0a0a0;background: #f7f7f7;} 
	.list{border:1px solid #a0a0a0;background: #fff;margin-top:20px;padding:20px 20px 0;} 
	.list th{padding:10px 0;color:#909090;}
	.list td{padding:30px 0;border-top:1px solid #d0d0d0;width: 33%;}
	.list td span{color:#ff9900;} 
	.ysProfit{ font-style:normal;color:#ff9900;}
	.bg{display: block;width:36px;height:22px;float:left;}
	.yl{background: url("../images/yl.png") no-repeat center;position: absolute;top:5px;left:0;}
	.wj{background: url("../images/wj.png") no-repeat center;position: absolute;top:2px;left:-30px;font-size:13px;}
	.yk{background: url("../images/yk.png") no-repeat center;position: absolute;top:0;left:-30px;font-size:13px;}
	.ykMoy{position: relative;}
	.wjMoy{position: relative;}
	.tit{color:#2db1e1;}
	.title>span{color:#a6a6a6;}
	.zheng:hover {color:#00bbff;}
	.fu:hover {color:#00bbff;}
</style>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="hgrzx">
	<div class="title"><span>当前位置：</span><a class="tit" href="${ctx}/user_center/centerIndex.htm">个人中心</a>><a class="tit"  href="${ctx}/user_stock/stockIndex.htm">解套者联盟</a>>累计盈亏</div>
	<div class="hgrzxt">
	    <table cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr>
	        <td class="tdyMony">
	          <div class="hwdtabt"><span><i class="bg yl"></i>昨 日 盈 亏（元）</span></div>
		    	<div class="hwdtabbd">
			      <table cellpadding="0" cellspacing="0" border="0" width="100%">
			        <tr>
			          <th colspan ="2">
			          <c:if test="${dayProfits>=0}"><a class="today" href="${ctx}/user_stock/stockProfitRecord.htm"><span class="zheng">+<fmt:formatNumber value="${dayProfits}" pattern="#,###,##0.00"/></span></a></c:if>
	        		  <c:if test="${dayProfits<0}"><a class="today" href="${ctx}/user_stock/stockProfitRecord.htm"><span class="fu"><fmt:formatNumber value="${dayProfits}" pattern="#,###,##0.00"/></span></a></c:if>
			          </th>
			        </tr>
			        <tr>
			          	<td align="center" style="border-bottom:0px;padding:10px 0;" colspan="1"><span class="today ykMoy" ><span style="color:#333;"><i class="bg yk"></i>累计盈亏：</span><a class="today ykMoy" href="${ctx}/user_stock/stockProfitRecord.htm">
			          	<c:if test="${empty fuStockMoneyInfo.profitInfo || fuStockMoneyInfo.profitInfo>=0}"><span class="zheng">+<fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/>元</span></c:if>
			          	<c:if test="${fuStockMoneyInfo.profitInfo<0}"><span class="fu"><fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/>元</span></c:if>
			          	</a></span><i class="fenge"></i></td>
					    <td align="center" style="border-bottom:0px;padding:10px 0;" colspan="1"><span class="today ykMoy" ><span style="color:#333;"><i class="bg wj"></i>未缴费用：</span><a href="${ctx}/user_stock/stockManageRecord.htm"><span class="zheng"><fmt:formatNumber value="${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo}" pattern="#,###,##0.00"/>元</span></a></span><a class="pay" onclick="onekeypay(${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo})">一键缴费</a></td>
					</tr>
			      </table>
			    </div>
	        </td>
	      </tr>
	    </table>
	  </div>    
	  <div class="hgrzxt list" >
	    <table cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr>
	      	<th>日期</th>
	      	<th>开户券商</th>
	      	<th>昨日盈亏</th>
	      </tr>
	      <c:forEach items="${details}" var="detail">
	      <tr>
	        <td><fmt:formatDate value="${detail.tradeTime}" pattern="yyyy-MM-dd"/></td>
	        <td>${detail.fuStockAccount.openEquity}${detail.fuStockAccount.capitalAccount}</td>
	        <td>
	        <c:if test="${detail.nowProfit>0}"><span style="color:#ff9900">+<fmt:formatNumber value="${detail.nowProfit}" pattern="#,###,##0.00"/>元</span></c:if>
	        <c:if test="${detail.nowProfit<=0}"><span style="${detail.nowProfit<0?'color:green':'color:#666'}"><fmt:formatNumber value="${detail.nowProfit}" pattern="#,###,##0.00"/>元</span></c:if>
	        </td>
	      </tr>
	      </c:forEach>
	    </table>
	  </div>                                                                                                                                                                                                                                                                                                 
</div>
<div class="downban"></div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script src="../js_hhr/jquery.nouislider.all.min.js"></script>
<link href="../js_hhr/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />
<script>
function onekeypay(money){
	if(parseInt(money)<=0){
		jAlert('未缴费用为0，无需支付！','提示',function(){
		});
		return false;
	}
	$.fancybox.open({
		href : '${ctx}/user_stock/oneKeyPay.htm?money='+money,
		type : 'ajax',
		padding : 0 
	});
}
</script>