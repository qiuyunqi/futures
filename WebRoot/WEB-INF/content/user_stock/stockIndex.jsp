<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套联盟主页</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style type="text/css">
	body{background: #efefef;}
	.leftNav{position: absolute;margin-top:120px;}
	.leftNav li{margin-bottom:20px;}
	.stockWrapper{ color: #333;}
	.hwdtabbd table{border:0 !important;}
	.hwdtabt{text-align: center;font-size: 19px !important;height:57px !important;border:0 !important;width:480px;margin:0 auto;}
	.hwdtabt>span{border-bottom:1px solid #d0d0d0;padding:0 50px 10px;color:#333 !important;position: relative;}
	.lastTr{background: #efefef !important;}
	.hwdtabbd th{height: 145px !important;background: #f7f7f7 url("../images/mony.png") no-repeat scroll left bottom !important;border:0 !important; }
	.hwdtabbd th span{font-size: 86px;}
	.pay{border:1px solid #2db1e1;border-radius:20px;padding:2px 15px;background:#2db1e1;color:#fff;font-size:11px;margin-left: 10px;}
	.fenge{display: block;width:1px;height:21px;background:#d0d0d0;float:right;}
	.hwdtabbd td:last-child {text-align: center  !important;}
	.jiaoyibtn{margin-top:20px;}
	.jiaoyibtn a{display: block;width:355px;height:60px;background: #fff;color:#2db1e1;text-align: center;line-height: 60px;margin: 0 auto;font-size: 20px;border-radius: 35px;border:1px solid #2db1e1;}
	.hwdzhrtab{margin-bottom: 15px;}
	.hgrzxt td{height:0 !important;text-align: center;}
	.hgrzxt .tdyMony{border:1px solid #a0a0a0;background: #f7f7f7;width:650px;}
	.list{border:1px solid #a0a0a0;background: #fff;margin-top:20px;padding:20px;}
	.list th{padding:10px 0;border-bottom:1px solid #d0d0d0;font-weight: normal;color: #666;}
	.list td{padding:30px 0;border-bottom:1px solid #d0d0d0;}
	.list td span{color:#ff9900;}
	.xiala{border:1px solid #a0a0a0;}
	.xiala li:hover{background:#2db1e1; }
	.xiala li:hover a{color:#fff;}
	.userInfo{color:#2db1e1 !important;display: block;}
	.xiala{position: absolute;z-index: 99;background: #fff none repeat scroll 0 0;top: 0;right:100px;}
	.xiala li{padding:5px 9px;}
	.xlImg{position: absolute;right: 100px;top: 5px;}
	.hgrzxtzh{background: #fff;padding: 30px 0;}
	.hgrzxtzh>img{display: block;margin:0 auto;}
	.hgrzxtzhbut{padding-left: 20px !important;border-bottom: 1px solid #dfdfdf;margin-top:0 !important;height: 50px;line-height: 50px;}
	.hgrzxtzhbut span{float:right;color:#ff9900;margin-right:20px;}
	.zheng{color:#ff9900;}
	.details{float: right;color:#00bbff;margin-right:15px;}
	.zheng:hover {color:#00bbff;}
	.fu:hover {color:#00bbff;}
	.fu{color:green;}
	.btnh{float:right;}
	.ztwt{background: #00bbff none repeat scroll 0 0;border: 0 none;border-radius: 5px;color: white;margin: 15px 15px 0;vertical-align: middle;line-height: 30px;padding: 0 10px;}
	.teacher{border:1px solid #a0a0a0;background: #fff;margin-top: 40px;padding: 0 0 10px;}
	.teacher li{width:31%;float:left;background: #fff;padding-right: 3%;padding-bottom: 15px;}
	.teacherImg{width:300px;margin:5px auto;}
	.hwdtabT{display: block;width:216px;height:69px;background: url("../images/tStudy.png") no-repeat center;position: relative;top:-27px;margin: 0 auto;}
	.teacherInfo{ background: black none repeat scroll 0 0;bottom: 0;color: white;height: 30px;left: 0;line-height: 30px;margin: 0 auto;opacity: 0.7;position: absolute;text-align: center;width: 300px;z-index: 88;filter:alpha(opacity=70);}
	.teaqcherBtn a{background: #fff;border:1px solid #2db1e1;border-radius:20px;padding:7px;color:#2db1e1;display: block;text-align: center;width:200px;margin:20px auto;}
	.teacherList{border:1px solid #a0a0a0;background: #f7f7f7;border-radius: 15px;margin-top:10px;padding:0 20px;}
	.hgrTac{padding:0 100px;}
	.headImg{display:block;width:74px;float:left; margin: 5px 10px 5px 0px;height:74px;}
	.hgt{height:80px;line-height: 80px;margin-left: 90px;border-left:1px solid #a0a0a0;}
	.hgts{height:110px;padding:10px 0 0;}
	.userName{border-bottom:1px solid #a0a0a0;}
	.date{float:right;margin-right:20px;}
	.userQA{text-indent: 15px;}
	.teacherAnswer{text-indent: 15px;}
	.usInfo{position: relative;}
	.bg{display: block;width:36px;height:22px;float:left;}
	.yl{background: url("../images/yl.png") no-repeat center;position: absolute;top:5px;left:0;}
	.wj{background: url("../images/wj.png") no-repeat center;position: absolute;top:2px;left:-30px;font-size:13px;}
	.yk{background: url("../images/yk.png") no-repeat center;position: absolute;top:0;left:-30px;font-size:13px;}
	.ykMoy{position: relative;}
	.wjMoy{position: relative;}
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
  <div class="stockWrapper">
	  <div class="hgrzxt">
	    <table cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr>
	        <td class="tdyMony" colspan ="2">
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
			          	<td align="center" style="border-bottom:0px;padding:10px 0;" colspan="1"><span class="today ykMoy" ><i class="bg yk"></i><span style="color:#333;">累计盈亏：</span><a href="${ctx}/user_stock/stockProfitRecord.htm">
			          	<c:if test="${empty fuStockMoneyInfo.profitInfo || fuStockMoneyInfo.profitInfo>=0}"><span class="zheng">+<fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/>元</span></c:if>
			          	<c:if test="${fuStockMoneyInfo.profitInfo<0}"><span class="fu"><fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/>元</span></c:if>
			          	</a></span><i class="fenge"></i></td>
					    <td align="center" style="border-bottom:0px;padding:10px 0;text-align:right;" colspan="1"><span class="today ykMoy" ><span style="color:#333;"><i class="bg wj"></i>未缴费用：</span><a href="${ctx}/user_stock/stockManageRecord.htm"><span class="zheng"><fmt:formatNumber value="${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo}" pattern="#,###,##0.00"/>元</span></a></span><a class="pay" onclick="onekeypay(${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo})">一键缴费</a></td>
					</tr>
			      </table>
			    </div>
	        </td>
	        <th></th>
	        <td  class="lastTr" width="200" align="center"><a href="${ctx}/index_guide/stockExpand.htm"><img class="moreInfo" src="../images/more.png" /></a></td>
	      </tr>
	    </table>
	  </div>
	  <div class="hgrzxt list">
	    <table cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr>
	      	<th></th>
	      	<th>开户券商</th>
	      	<th>昨日盈亏</th>
	      	<th></th>
	      </tr>
	      <c:forEach items="${stockList}" var="stock">
	      <tr>
	        <td  style="width:25%;">
	         <img src="${stock[5]==0?'../images/openBtn.png':stock[5]==1?'../images/stopBtn.png':stock[5]==2?'../images/aplyOp.png':stock[5]==3?'../images/splySt.png':'../images/closeBtn.png'}"/>
	        </td>
	        <td style="width:25%;">${stock[1]}${stock[2]}</td>
	        <td style="width:25%;">
	        	  <c:if test="${stock[3]>0}"><span>+<fmt:formatNumber value="${stock[3]}" pattern="#,###,##0.00"/>元</span></c:if>
	        	  <c:if test="${stock[3]<=0}"><span style="${stock[3]<0?'color:green':'color:#666'}"><fmt:formatNumber value="${stock[3]}" pattern="#,###,##0.00"/>元</span></c:if>
	        </td>
	        <td  style="width:25%;">
	        	<div class="usInfo">
	        		<span class="userInfo">账号详情</span><img class="xlImg" src="../images/xiala.png"/>
	        		<ul class="xiala">
			        	<li class="xialaSmall"><a href="${ctx}/user_stock/stockInfo.htm?id=${stock[0]}">账号详情</a></li>
			        	<c:if test="${stock[5]==0}"><li class="xialaSmall"><a href="javascript:void(0)" onclick="weituo(${stock[0]},'暂停委托')">暂停委托</a></li></c:if>
			        	<c:if test="${stock[5]==1}"><li class="xialaSmall"><a href="javascript:void(0)" onclick="weituo(${stock[0]},'开启委托')">开启委托</a></li></c:if>
			        	<li class="xialaSmall"><a href="javascript:void(0)" onclick="delStock(${stock[0]})">删除账号</a></li>
		       		 </ul>
	        	</div>
	        </td>
	      </tr>
	      </c:forEach>
	    </table>
	     <div class="jiaoyibtn">
		  	<a href="javascript:void(0)" onclick="addStock()">添加交易账号</a>
		  </div> 
	  </div>   
	 <!--T学院      2015.12.17-->  
	 <div class="teacher">
	  	<span class="hwdtabT"></span>  
	  	 <table cellpadding="0" cellspacing="0" width="100%" border="0">
	      <tr>
			<td align="center" style="border-bottom:0px;padding:10px 0;"><div class="teacherImg"><img src="../images/teacherOne.png"/></div><div class="teaqcherBtn"><a href="${ctx}/user_stock/addUserInfo.htm?guideTeacher=1">预约指导</a><a href="${ctx}/index_guide/tCollege.htm">进入直播间</a></div></td>
			<td align="center" style="border-bottom:0px;padding:10px 0;"><div class="teacherImg"><img src="../images/teacherTwo.png"/></div><div class="teaqcherBtn"><a href="${ctx}/user_stock/addUserInfo.htm?guideTeacher=2">预约指导</a><a href="${ctx}/index_guide/tCollege.htm">进入直播间</a></div></td>
			<td align="center" style="border-bottom:0px;"><div class="teacherImg"><img src="../images/teacherThree.png"/></div><div class="teaqcherBtn"><a href="${ctx}/user_stock/addUserInfo.htm?guideTeacher=3">预约指导</a><a href="${ctx}/index_guide/tCollege.htm">进入直播间</a></div></td>
	      </tr>
	    </table>
	    <!-- 我的指导 -->
		    <div class="hgrTac">
		    	<div class="hgrzxtzh"><img src="../images/tTitle.png"/></div>
		    	<c:forEach items="${messageList}" var="message">
		    	<div class="teacherList">
		    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
				      <tr>
				        <td>
				          <div class="hgts userName">
				          	<img class="headImg" src="${empty fuUser.userAvatar?'../images_hhr/default.png':fuUser.userAvatar}"/>
				          	<div class="hgt userQA">${message.content}</div>
				          	<div class="date"><fmt:formatDate value="${message.time}" pattern="yy-MM-dd HH:mm:ss"/></div>
				          </div>
				          <div class="hgts teacherName">
				          	<img class="headImg" src="${message.guideTeacher==1?'../images/teacherOne.jpg':message.guideTeacher==2?'../images/teacherTwo.jpg':'../images/teacherThree.jpg'}"/>
				          	<div class="hgt teacherAnswer">${empty message.replyContent?'暂无':message.replyContent}</div>
				         	<div class="date"><c:if test="${empty message.replyTime}">未回复</c:if><fmt:formatDate value="${message.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				         </div>
				        </td>
				      </tr>
				    </table>
		    	</div>
			 </c:forEach>
		  </div>    
	  </div>  
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
<script type="text/javascript">
	$(".xiala").hide(); 
	$(document).ready(function(){
		$(".usInfo").mouseover(function(){
			$(this).children(".xiala").show();
		});
		$(".usInfo").mouseout(function(){
			$(this).children(".xiala").hide();
		});
	});

	function addStock(){
		$.post("${ctx}/user_stock/addStock.htm",null,function(d){
			if(d==-1){
				lgInfo(1);	
				return false;
			}
			if(d==-2){
				sureInfo("确定","系统参数还没有设置，请联系客服！","提示");
				return false;
			}
			/* if(d==-3){
				//身份认证
				$.fancybox.open({
		 			href : '${ctx}/user_center/personNameAjax.htm',
		 			type : 'ajax',
		 			padding : 5 ,
			 	});
				return false;
			} */
			location.href="${ctx}/user_stock/addStock.htm";
		});
	}
	
	function weituo(id,valueStr){
		var str;
		var operationStatus;
		if(valueStr=="暂停委托"){
			str="申请暂停委托";
			operationStatus=4;
		}
		if(valueStr=="开启委托"){
			str="申请开启委托";
			operationStatus=3;
		}
		jConfirm("确认"+str+"？","操作提示",function(res){
			if(res){
				$.post("${ctx}/user_stock/saveStockState.htm?id="+id+"&operationStatus="+operationStatus,null,function(d){
					jAlert(str+"成功，系统将在23:30处理该申请！","提示",function(){
						location.href=location.href;
			        });
				});
			}
		});
	}
	
	
	function delStock(id){
		jConfirm("确认删除账号？","操作提示",function(res){
			if(res){
				$.post("${ctx}/user_stock/saveStockState.htm?id="+id+"&operationStatus=5",null,function(d){
					jAlert("账户删除申请成功，系统将在23:30处理该申请！","提示",function(){
						location.href="${ctx}/user_stock/stockIndex.htm";
			        });
				});
			}
		});
	}
	
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
};
</script>
