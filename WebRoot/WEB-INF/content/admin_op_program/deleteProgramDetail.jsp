<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－方案管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="13"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">已删除方案详情<a href="${ctx}/admin_list_delete_program/deleteProgramList.htm" class="fr add">返回</a></div>
            
          <div class=" yhlb_title" style="text-align: center;">《投顾合同要素表》--${program.programType==1?'期货日配':program.programType==2?'期货月配':'期货大赛'}（${program.status==-2?'已删除':program.status==-1?'已关闭':program.status==0?'待审核':program.status==1?'审核中':program.status==2?'交易中':program.status==3?'拒绝开户':program.status==4?'等待结算':program.status==5?'交易结束':program.status==6?'结算中':'已穿仓'}）</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				  	<td bgcolor="#f6f6f6" width="7%" style="text-align:center;">序号</td>
				  	<td bgcolor="#f6f6f6" width="7%" style="text-align:center;">项目</td>
				  	<td bgcolor="#f6f6f6" width="85%" style="text-align:center;">具体内容</td>
				  </tr>
				  <tr>
				  	<td>1</td>
				  	<td>客户基本情况</td>
				  	<td>
				  		<table width="100%">
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">姓名</td>
				  				<td width="20%">${program.fuUser.userName}(${program.fuUser.accountName})</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">客户电话</td>
				  				<td width="20%">${program.fuUser.phone}</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">分账户号</td>
				  				<td width="20%">${empty program.tradeAccount?'暂无':program.tradeAccount}(密码：${empty program.tradePassword?'暂无':program.tradePassword})</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">期货公司</td>
				  				<td>${empty program.fuServer.serverRealName?'暂无':program.fuServer.serverRealName}</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">开户姓名</td>
				  				<td></td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">主账户号</td>
				  				<td></td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">客户ID</td>
				  				<td>${program.fuUser.id}</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">方案ID</td>
				  				<td>${program.id}</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">IP地址</td>
				  				<td>${empty program.tradeIp?'暂无':program.tradeIp}:${empty program.tradePort?'暂无':program.tradePort}</td>
				  			</tr>
				  		</table>
				  	</td>
				  </tr>
				  <tr>
				  	<td>2</td>
				  	<td>合同要素</td>
				  	<td>
				  		<table width="100%">
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">合同期限：</td>
				  				<td colspan="5" style="text-align:left"><fmt:formatDate value="${program.tradeTime}" pattern="yyyy年MM月dd日"/>~<fmt:formatDate value="${program.closeTime}" pattern="yyyy年MM月dd日"/>----${program.programType==1?'自动顺延':program.programType==2?'':''}</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">客户本金（小写）：</td>
				  				<td width="20%"><fmt:formatNumber value="${empty program.safeMoney?0:program.safeMoney}" pattern="#,###,##0.00"/>元 </td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">配资资金（小写）：</td>
				  				<td width="20%"><fmt:formatNumber value="${empty program.matchMoney?0:program.matchMoney}" pattern="#,###,##0.00"/>元</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">账户总额：</td>
				  				<td width="20%"><fmt:formatNumber value="${empty program.totalMatchMoney?0:program.totalMatchMoney}" pattern="#,###,##0.00"/>元</td>
				  			</tr>
				  			<!-- <tr>
				  				<td colspan="6" style="text-align:left">客户本金（小写）：<fmt:formatNumber value="${empty program.safeMoney?0:program.safeMoney/100}" pattern="#,###,##0.00"/>元  ， 配资资金（小写）：<fmt:formatNumber value="${empty program.matchMoney?0:program.matchMoney/100}" pattern="#,###,##0.00"/>元 ， 账户总额：<fmt:formatNumber value="${empty program.matchMoney?0:program.totalMatchMoney/100}" pattern="#,###,##0.00"/>万元 </td>
				  			</tr>
				  			<tr>
				  				<td colspan="6" style="text-align:left">配资比例：1：${program.doubleNum}&nbsp;&nbsp;月息：               元   收息时间：每月       日，日息           %，月息          %</td>
				  			</tr> -->
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">配资比例：</td>
				  				<td width="20%">1：${program.doubleNum}<c:if test="${program.doubleNum==10}">(仅股指)</c:if></td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">月息：</td>
				  				<td width="20%">
				  				<c:if test="${program.programType==1}">
				  				</c:if>
				  				<c:if test="${program.programType==2}">
				  				<fmt:formatNumber value="${empty program.matchMoney?0:program.matchMoney*program.moneyPercent}" pattern="#,###,##0.00"/>
				  				</c:if>
				  				元</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">费率：</td>
				  				<td width="20%">
				  				<c:if test="${program.programType==1}">
				  				日息<fmt:formatNumber value="${empty program.moneyPercent?0:program.moneyPercent}" pattern="#,###,##0.00"/>元
				  				</c:if>
				  				<c:if test="${program.programType==2}">
				  				月息${program.moneyPercent*100}%
				  				</c:if>
				  				</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">营销员</td>
				  				<td width="20%">${program.comment}</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">居间人</td>
				  				<td width="20%">${program.mediator}</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">返佣（手续费、利息）：</td>
				  				<td width="20%">${program.returnCommission}</td>
				  			</tr>
				  		</table>
				  	</td>
				  </tr>
				  <tr>
				  	<td>3</td>
				  	<td>风控设置标准</td>
				  	<td>
				  		<table width="100%">
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">保证金</td>
				  				<td width="33%"><fmt:formatNumber value="${empty program.safeMoney?0:program.safeMoney}" pattern="#,###,##0.00"/>元</td>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">出金</td>
				  				<td width="40%">超过<fmt:formatNumber value="${empty program.totalMatchMoney?0:program.totalMatchMoney}" pattern="#,###,##0.00"/>元,可出<fmt:formatNumber value="${empty program.totalMatchMoney?0:program.totalMatchMoney}" pattern="#,###,##0.00"/>元以上</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">手续费</td>
				  				<td>股指：${program.stockIndexFee}，商品：${program.goodsFee}</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">保证金率</td>
				  				<td>${program.safeMoneyRate}</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">预警线</td>
				  				<td><fmt:formatNumber value="${empty program.warnLine?0:program.warnLine}" pattern="#,###,##0.00"/>元</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">隔夜商品保证金占用</td>
				  				<td>${program.overnightGoodRate}</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">强平线</td>
				  				<td><fmt:formatNumber value="${empty program.closeLine?0:program.closeLine}" pattern="#,###,##0.00"/>元</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">隔夜沪指保证金占用</td>
				  				<td>${program.overnightStockIndexRate}</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left" bgcolor="#f6f6f6">其它</td>
				  				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				  				<td style="text-align:left" bgcolor="#f6f6f6">小长假持仓标准</td>
				  				<td>${program.longHolidayRate}</td>
				  			</tr>
				  		</table>
				  	</td>
				  </tr>
				  <tr>
				  	<td>4</td>
				  	<td>收款清单</td>
				  	<td>
				  		<table width="100%">
	  						<tr>
	  							<td style="text-align:left" bgcolor="#f6f6f6" width="13%">收款账户信息：</td>
				  				<td width="33%">&nbsp;&nbsp;</td>
	  							<td width="13%" style="text-align:left" bgcolor="#f6f6f6">付款账户信息：</td>
				  				<td width="40%">&nbsp;&nbsp;</td>
	  						</tr>
	  						<tr>
	  							<td style="text-align:left" bgcolor="#f6f6f6">开户名：</td>
				  				<td>&nbsp;&nbsp;</td>
	  							<td style="text-align:left" bgcolor="#f6f6f6">开户名：</td>
				  				<td>&nbsp;&nbsp;</td>
	  						</tr>
	  						<tr>
	  							<td style="text-align:left" bgcolor="#f6f6f6">开户行：</td>
				  				<td>&nbsp;&nbsp;</td>
	  							<td style="text-align:left" bgcolor="#f6f6f6">开户行：</td>
				  				<td>&nbsp;&nbsp;</td>
	  						</tr>
	  						<tr>
	  							<td style="text-align:left" bgcolor="#f6f6f6">银行账号：</td>
				  				<td>&nbsp;&nbsp;</td>
	  							<td style="text-align:left" bgcolor="#f6f6f6">银行账号：</td>
				  				<td>&nbsp;&nbsp;</td>
	  						</tr>
				  			<tr>
				  				<td style="text-align:left" width="13%" bgcolor="#f6f6f6">金额：</td>
				  				<td colspan="3" style="text-align:left">${bigAmg}（￥<fmt:formatNumber value="${empty smallAmg?0:smallAmg}" pattern="#,###,##0.00"/>）</td>
				  			</tr>
				  			<tr>
				  				<td style="text-align:left;height: 80px" width="13%" bgcolor="#f6f6f6">其他：</td>
				  				<td colspan="3">&nbsp;&nbsp;</td>
				  			</tr>
				  		</table>
				  	</td>
				  </tr>
				  <tr>
				  	<td style="text-align:left;height: 80px">备注：</td>
				  	<td colspan="2" style="text-align:left;height: 80px">${program.remark}</td>
				  </tr>
				  <tr>
				  	<td colspan="3">
				  		<table align="right" width="100%">
					  		<tr>
						  		<td>开户经办人：</td>
						  		<td>${empty program.fuAdmin.name?'----':program.fuAdmin.name}</td>
						  		<td>开户经办时间：</td>
						  		<td>${empty program.checkTime?'----':program.checkTime}</td>
						  		<td>开户核对人：</td>
						  		<td>${empty program.fuAdmin.name?'----':program.fuAdmin.name}</td>
						  		<td>开户核对时间：</td>
						  		<td>${empty program.checkTime?'----':program.checkTime}</td>
					  		</tr>
				  		</table>
				  	</td>
				  </tr>
				  </tbody>
				  </table>
			</div>
		</div>
	          <div class=" yhlb_title">管理费记录</div>
	            <div class="yhlb">
	            	 <table width="100%">
					  <tbody>
					  <tr>
					    <th>&nbsp;</th>
					    <th>产生途径</th>
					    <th>服务周期</th>
					    <th>付费金额(元)</th>
					    <th>账户余额(元)</th>
					    <th>状态</th>
					    <th>审核人</th>
					    <th>审核备注</th>
					    <th>付费时间</th>
					    <th>审核时间</th>
					  </tr>
					  <c:forEach items="${feeList}" var="fee" varStatus="row">
					  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
							    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
							    <td>${fee.feeType}</td>
							    <td><fmt:formatDate value="${fee.beginTime}" pattern="MM月dd日"/>${empty fee.endTime?'----':''}至 <fmt:formatDate value="${fee.endTime}" pattern="MM月dd日"/>${empty fee.endTime?'----':''}</td>
							    <td><fmt:formatNumber value="${empty fee.money?0:fee.money}" pattern="#,###,##0.00"/></td>
							    <td><fmt:formatNumber value="${empty fee.accountBalance?0:fee.accountBalance}" pattern="#,###,##0.00"/></td>
							    <td>${fee.state==0?'待审核':fee.state==1?'审核中':fee.state==2?'同意':'拒绝'}</td>
							    <td>${empty fee.fuAdmin?'----':fee.fuAdmin.name}</td>
							    <td>${fee.comment}</td>
							    <td><fmt:formatDate value="${fee.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>${empty fee.payTime?'----':''}</td>
							    <td><fmt:formatDate value="${fee.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>${empty fee.checkTime?'----':''}</td>
							 </tr>
					  </c:forEach>
					 <c:if test="${empty feeList}">
					    <tr>
						  <td colspan="10">
				        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有管理费记录</p></div>
				          </td>
					    </tr>
					  </c:if>
					</tbody>
					</table>
	          </div> 
		
          <div class=" yhlb_title">续约记录</div>
             <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>续约ID</th>
				    <th>续约周期 </th>
				    <th>状态</th>
				    <th>审核人</th>
				    <th>审核说明</th>
				    <th>申请时间</th>
				    <th>审核时间</th>
				  </tr>
				  <c:forEach items="${continueList}" var="con" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${con.id}</td>
						    <td>${con.cycle}${con.fuProgram.programType==1?'天':'个月'}</td>
						    <td>${con.result==0?'待审核':con.result==1?'审核中':con.result==2?'同意':'拒绝'}</td>
						    <td>${empty con.fuAdmin?'----':con.fuAdmin.name}</td>
						    <td>${con.comment}</td>
						    <td><fmt:formatDate value="${con.time}" pattern="yy-MM-dd HH:mm:ss"/>${empty con.time?'----':''}</td>
						    <td><fmt:formatDate value="${con.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty con.checkTime?'----':''}</td>
						 </tr>
				  </c:forEach>
				 <c:if test="${empty continueList}">
				  <tr>
					<td colspan="10">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有续约记录</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody>
				</table>
          </div>
	          <div style="height: 100px"></div>  
        </div>
    </div>
    <div class=" bottom">
	    <a href="${ctx}/admin_list_delete_program/deleteProgramList.htm" class="exit fr">关闭</a>
	    <a href="${ctx}/admin_op_program/exportExcel.htm?id=${id}" class="exit fr">导出excel</a>
	    <div class="clr"></div>
    </div>
</div>
</body>
</html>
<script>
	//加不良记录
	function addBadRecord(){
		$.fancybox.open({
	          href : '${ctx}/admin_op_program/addBadRecordAjax.htm?id=${id}',
	          type : 'ajax',
	          padding : 10
		});
	}
	//方案开户，开户失败
	function opProgram(flag){
		$.fancybox.open({
	          href : '${ctx}/admin_op_program/programAjax.htm?id=${id}&addMatchId=${program.addMatchId}&flag='+flag,
	          type : 'ajax',
	          padding : 10
		});
	}
	//告知开户
	function tellOpenProgram(programId){
		$.fancybox.open({
	          href : '${ctx}/admin_op_program/tellOpenAjax.htm?id='+programId,
	          type : 'ajax',
	          padding : 10
		});
	}
	//修改要素表
	function updateProgram(programId){
		$.fancybox.open({
	          href : '${ctx}/admin_op_program/updateProgramAjax.htm?id='+programId,
	          type : 'ajax',
	          padding : 10
		});
	}
</script>