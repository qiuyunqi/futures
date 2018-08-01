<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body style="background: #f2f2f2">
<c:set value="2" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

		<div class=" zhzb">
			<div class="zhzb_cont">
				<div class="zhzb_cont_lf fl">
					<span class="fl big_title">${program.programType==1?'日操作方案':program.programType==2?'月操作方案':'期货大赛'}</span><span
						class="fr dh">方案编号${program.id}</span>
					<div class="clr"></div>
					<div class=" rpfa">
		            	<table width="610" border="0" cellspacing="0" cellpadding="0">
						  <tbody>
						    <tr>
						      <td width="150" align="center"><span class=" zcpzj_num" style="font-size: 26px"><fmt:formatNumber value="${program.totalMatchMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
						      <td width="70" rowspan="2" align="center"><span class="equal ">=</span></td>
						      <td width="150" align="center"><span class=" zcpzj_num" style="font-size: 26px"><fmt:formatNumber value="${program.matchMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
						      <td width="80" rowspan="2" align="center"><span class="add ">+</span></td>
						      <td width="150" align="center"><span class=" zcpzj_num" style="font-size: 26px"><fmt:formatNumber value="${program.safeMoney}" pattern="#,###,##0.00" /></span><span class="yuan">元</span></td>
						    </tr>
						    <tr>
						      <td align="center"><span class="zcpzj">账户总资金</span></td>
						      <td align="center"><span class="zcpzj">融资资金</span></td>
						      <td align="center"><span class="zcpzj">风险保证金</span></td>
						    </tr>
						  </tbody>
						</table>
		          </div>
					<div class="rpfa0">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td>
										<span class=" a1">亏损预警线：</span><span class="a2"><c:if
												test="${empty program.warnLine||program.warnLine==0}">无</c:if><c:if test="${!empty program.warnLine&&program.warnLine!=0}"><fmt:formatNumber value="${program.warnLine}"
													pattern="#,###,##0.00" />元</c:if>
													</span>
									</td>
									<td>
										<span class=" a1">亏损平仓线：</span><span class="a2"><c:if
												test="${empty program.closeLine||program.closeLine==0}">无</c:if><c:if test="${!empty program.closeLine&&program.closeLine!=0}"><fmt:formatNumber value="${program.closeLine}"
													pattern="#,###,##0.00" />元</c:if>
													</span>
									</td>
								</tr>
								<tr>
									<td>
										<span class=" a1">开始交易时间：</span><span class="a2"><fmt:formatDate value="${program.tradeTime}" pattern="yyyy-MM-dd"/></span>
										</td>
									<td>
										<span class=" a1">结束交易时间：</span><span class="a2"><fmt:formatDate value="${program.closeTime}" pattern="yyyy-MM-dd"/></span>
									</td>
								</tr>
								<tr>
									<td>
										<span class=" a1">投资方向：</span><span class="a2">主力,次主力合约</span>
									</td>
									<td>
										<span class=" a1">仓位要求：</span><span class="a2">不限制</span>
									</td>
								</tr>
								<tr>
									<td>
										<span class=" a1">盈利分配：</span><span class="a2">全部归您 </span>
									</td>
									<td>
										<span class=" a1"> 约定收益：</span>
										<span class="a2"><c:if test="${empty program.manageMoney||program.manageMoney==0}">免费</c:if>
										<c:if test="${!empty program.manageMoney&&program.manageMoney!=0}"><fmt:formatNumber value="${program.manageMoney}" pattern="#,###,##0.00" />元</c:if>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				
				
				
				
				
				<c:if test="${program.status==0||program.status==1}">
						<div class="zhzb_cont_rt fr">
							<a href="javascript:void(0);" onclick="window.history.go(-1)" class="fl back_0">&lt; 返回</a>
							<a class=" xyfb fr" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')">协议范本 &gt;</a>
							<div class="clr"></div>
							<a href="javascript:void(0);" class="zhzbz">正在开户中</a>
							<div class=" sjtr sjtr0">
						      	<div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
						      	<div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp} : ${empty program.tradePort?'暂无':program.tradePort}</div>
						      	<div>交&nbsp;易&nbsp;账&nbsp;户：${empty program.tradeAccount?'暂无':program.tradeAccount}</div>
					      	</div>
						</div>
				</c:if>
				
				<c:if test="${program.status==2&&program.programType<3}">
					<div class="zhzb_cont_rt fr" style="height:520px;">
						<a href="javascript:void(0);" onclick="window.history.go(-1)" class="fl back_0">&lt; 返回</a>
			        	<a class=" xyfb fr" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')">协议范本 &gt;</a><div class="clr"></div>
			            <div class="zhzbz zhzbz0"><p class=" zbz">正在交易中</p><p class="xcfxsj">下次付息时间：<fmt:formatDate value="${empty program.nextManageMoneyTime?'':program.nextManageMoneyTime}" pattern="yyyy-MM-dd"/></p></div>
			            <div class=" sjtr sjtr0">
			            	<span class=" sjtr_0">保证金实际投入：</span><span class="sjtr_1">
			            	<fmt:formatNumber value="${(program.safeMoney+program.addSafeMoney)}" pattern="#,###,##0.00" /></span>
			            	<span class=" sjtr_0">元</span>
			            	<div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
					      	<div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp} : ${empty program.tradePort?'暂无':program.tradePort}</div>
					      	<div>交&nbsp;易&nbsp;账&nbsp;户：${empty program.tradeAccount?'暂无':program.tradeAccount}</div>
			            </div>
			           <div class=" pl" style="margin-top: 5px">
			            	<c:if test="${program.programWay!=2}">
				            	<a href="javascript:void(0);" onclick="addSafeMoney();">追加保证金 </a>
				                <a href="javascript:void(0);" onclick="drawMoney();" style="font-size:12px;">提取利润到网站账户 </a>
				                <a href="javascript:void(0);" onclick="toContinue();">续约 </a>
				                <a href="javascript:void(0);" onclick="overProgram();">提前清算</a>
				                <c:if test="${empty sonProgram}">
				                <c:if test="${empty program.addMatchId}">
					            <c:if test="${empty program.subMatchId}">
					            <a href="${ctx}/user_program/appendPeizi.htm?programType=1&addMatchId=${program.id}">增配</a>
					            <a href="javascript:void(0);" onclick="subPeiziAjax()">减配</a>
					            <div style="height:5px;"></div>
					            </c:if>
					            </c:if>
					            </c:if>
			                </c:if>
			            </div>
			           <!-- 交易软件下载 -->
			           	<div class="khddownload" >
			           	    <c:if test="${program.fuServer.clientType==1}">
				           		<div class="zqlogo"><img src="../images_hhr/stldzq.png" /></div>
				           		<div class="zqdloader">
				           			<span>众期金融资产管理平台期货客户端</span>
				           			<a href="http://app.hhr360.com/pc/zqSetup10033-hhr360.zip" class="xzjylj">立 即 下 载</a>
				           		</div>
			           		</c:if>
			           		<c:if test="${program.fuServer.clientType==2}">
			           			<div class="zqlogo"><img src="../images_hhr/stldbs.png" /></div>
				           		<div class="zqdloader">
				           			<span>博易大师－方正中期版</span>
				           			<a href="http://app.hhr360.com/pc/pobo_Setup528_fzzq.zip" class="xzjylj">立 即 下 载</a>
				           		</div>
			           		</c:if>
			           		<c:if test="${program.fuServer.clientType==3}">
			           			<div class="zqlogo"><img src="../images_hhr/stldbs.png" /></div>
				           		<div class="zqdloader">
				           			<span>博易大师－大有期货版</span>
				           			<a href="http://app.hhr360.com/pc/pobo_Setup528_dyqh.zip" class="xzjylj">立 即 下 载</a>
				           		</div>
			           		</c:if>
			           	</div> 
			      </div>
		      </c:if>
		      
		      
		      <c:if test="${program.status>=4&&program.status<=6}">
		      	<c:if test="${program.status==4||program.status==6}">
						<div class="zhzb_cont_rt fr">
							<a href="javascript:void(0);" onclick="window.history.go(-1)" class="fl back_0">&lt; 返回</a>
							<a class=" xyfb fr" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')">协议范本 &gt;</a>
							<div class="clr"></div>
							<a href="javascript:void(0);" class="zhzbz">${program.status==4?'方案待结算':'方案结算中'}</a>
							<div class=" sjtr sjtr0">
								<div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
					      		<div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp}</div>
					      		<div>交&nbsp;易&nbsp;账&nbsp;户：${empty program.tradeAccount?'暂无':program.tradeAccount}</div>
					      	</div>
						</div>
				 </c:if>
				 <c:if test="${program.status==5}">
				 	<div class="zhzb_cont_rt fr">
				 		<a href="javascript:void(0);" onclick="window.history.go(-1)" class="fl back_0">&lt; 返回</a>
			        	<a class=" xyfb fr" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')">协议范本 &gt;</a><div class="clr"></div>
			            <div class="zhzbz zhzbz0"><p class="zbz">方案已完结</p></div>
			            <div class=" sjtr sjtr0">
							<div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
				      		<div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp}</div>
				      		<div>交&nbsp;易&nbsp;账&nbsp;户：${empty program.tradeAccount?'暂无':program.tradeAccount}</div>
				      	</div>
			            <div class=" sjtr">
			            	<span class=" sjtr_0">结算金额：</span>
			            	<span class="sjtr_1"><fmt:formatNumber value="${program.income}" pattern="#,###,##0.00" /></span>
			            	<span class=" sjtr_0">元</span>
			            </div>
			            <c:if test="${program.status==5&&program.programWay!=2}">
			            	<div class=" tzyl">
				            	<span class="tzyl_num">${(program.income-program.safeMoney)>0?'+':''}
				            	<fmt:formatNumber value="${(program.income-program.safeMoney)}" pattern="#,###,##0.00" /></span>
				                <span class="tzyl_yuan">元</span>
				            </div>
			            </c:if>
			      	</div>
				 </c:if>
		      </c:if>
		      
		      
		      <c:if test="${program.status==7}">
						<div class="zhzb_cont_rt fr">
							<a href="javascript:void(0);" onclick="window.history.go(-1)" class="fl back_0">&lt; 返回</a>
							<a class=" xyfb fr" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')">协议范本 &gt;</a><div class="clr"></div>
							<div class="zhzbz zhzbz0"><p class="zbz">方案已穿仓</p></div>
							<div class=" sjtr sjtr0">
				            	<span class=" sjtr_0">保证金实际投入：</span><span class="sjtr_1">
				            	<fmt:formatNumber value="${(program.safeMoney+program.addSafeMoney)}" pattern="#,###,##0.00" /></span>
				            	<span class=" sjtr_0">元</span>
				            	<div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
						      	<div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp} : ${empty program.tradePort?'暂无':program.tradePort}</div>
						      	<div>交&nbsp;易&nbsp;账&nbsp;户：${empty program.tradeAccount?'暂无':program.tradeAccount}</div>
			            	</div>
					      	<div class="pl" style="margin-top:5px">
				            	<a href="javascript:void(0);" onclick="addSafeMoney();">追加保证金</a>
				                <a href="javascript:void(0);" onclick="toContinue();">续约 </a>
			            	</div>
						</div>
				</c:if>
				<div class="clr"></div>
			</div>



			<div class=" zhcz_title">
				<ul>
					<li id="li1" class="yxz2" 
					onclick="$(this).addClass('yxz2');$('#li2').removeClass('yxz2');$('#li3').removeClass('yxz2');$('#li4').removeClass('yxz2');$('#li5').removeClass('yxz2');$('#div1').show();$('#div2').hide();$('#div3').hide();$('#div4').hide();$('#div5').hide();">
						<a href="javascript:void(0);" >约定收益记录</a>
					</li>
					<li id="li3" 
					onclick="$(this).addClass('yxz2');$('#li1').removeClass('yxz2');$('#li2').removeClass('yxz2');$('#li4').removeClass('yxz2');$('#li5').removeClass('yxz2');$('#div3').show();$('#div1').hide();$('#div2').hide();$('#div4').hide();$('#div5').hide();">
						<a href="javascript:void(0);" >保证金记录</a>
					</li>
					<li id="li4"
					onclick="$(this).addClass('yxz2');$('#li1').removeClass('yxz2');$('#li2').removeClass('yxz2');$('#li3').removeClass('yxz2');$('#div4').show();$('#li5').removeClass('yxz2');$('#div1').hide();$('#div2').hide();$('#div3').hide();$('#div5').hide();">
						<a href="javascript:void(0);" >利润提取记录</a>
					</li>
					<li id="li5" 
					onclick="$(this).addClass('yxz2');$('#li1').removeClass('yxz2');$('#li2').removeClass('yxz2');$('#li3').removeClass('yxz2');$('#li4').removeClass('yxz2');$('#div5').show();$('#div1').hide();$('#div2').hide();$('#div3').hide();$('#div4').hide();">
						<a href="javascript:void(0);">增配记录</a>
					</li>
					<li id="li2" 
					onclick="$(this).addClass('yxz2');$('#li1').removeClass('yxz2');$('#li3').removeClass('yxz2');$('#li4').removeClass('yxz2');$('#li5').removeClass('yxz2');$('#div2').show();$('#div1').hide();$('#div3').hide();$('#div4').hide();$('#div5').hide();">
						<a href="javascript:void(0);">交易账号密码</a>
					</li>
				</ul>
				<div class="clr"></div>
			</div>

	     <div class="zhzb_cont">
			
			    <!-- 管理费记录  -->
				<div class="grglzh_tab" style="margin:0; border:0;" id="div1">
		          <table border="0" cellspacing="0" cellpadding="0" width="100%">
		          <tbody>
		          <tr style="font-size:14px;color:#808080;">
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100" height="35">分成周期</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">分成金额(元)</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="120">账户余额(元)</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">分成时间</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">产生途径</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">状态</td>
		          </tr>
		          <c:forEach items="${feeList}" var="fee"  varStatus="row">
	          		<tr <c:if test="${(fn:length(feeList)-1)==row.index}">style="border-bottom: none;"</c:if> >
			            <td width="100px" align="center" height="35">
			            	<c:if test="${empty fee.flag || fee.flag!=1}">${fee.payCycle}${fee.fuProgram.programType==1?'天':'个月'}</c:if>
			            	<c:if test="${!empty fee.flag && fee.flag==1}">${fee.payCycle}天</c:if>
			            </td>
			            <td width="100px" align="center" height="35" style="line-height:35px;"><fmt:formatNumber value="${empty fee.money?0:fee.money}" pattern="#,###,##0.00"/></td>
			            <td width="120px" align="center"><fmt:formatNumber value="${empty fee.accountBalance?0:fee.accountBalance}" pattern="#,###,##0.00"/></td>
			            <td width="100px" align="center"><fmt:formatDate value="${fee.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			           	<td width="100px" align="center">${program.programType==1?'日操作方案分成':'月操作方案分成'}</td>
			            <td width="100px" align="center">${fee.state==0?'待审核':fee.state==1?'审核中':fee.state==2?'成功':'拒绝'}</td>
			        </tr>
		          </c:forEach>
		          <c:if test="${empty feeList}">
	          		<tr style="border-bottom: none;">
			          	<td colspan="4" align="center" height="35px">暂无数据</td>
			        </tr>
		          </c:if>
		        </tbody>
		        </table>
		        </div>
		        
		        
		        <!-- 保证金记录  -->
		        <div class="grglzh_tab" style="margin:0; border:0;display: none;" id="div3">
		          <table border="0" cellspacing="0" cellpadding="0" width="100%">
		          <tbody>
		          <tr>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="120px" height="35">保证金(元)</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="110px">支付时间</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100px">产生途径</td>
		          	<td style="border-bottom:#e3e3e3 solid 1px;padding-right:20px" align="center" bgcolor="#f8f8f8" width="100px">状态</td>
		          </tr>
		          <c:forEach items="${safeList}" var="sf"  varStatus="row">
	          		<tr <c:if test="${(fn:length(safeList)-1)==row.index}">style="border-bottom: none;"</c:if> >
			            <td width="120px" align="center" height="35"><fmt:formatNumber value="${empty sf.money?0:sf.money}" pattern="#,###,##0.00"/></td>
			            <td width="110px" align="center"><fmt:formatDate value="${sf.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td width="100px" align="center">${sf.remark}</td>
			            <td width="100px" align="center" style="padding-right:20px">${sf.state==0?'待审核':sf.state==1?'审核中':sf.state==2?'成功':'拒绝'}</td>
			        </tr>
		          </c:forEach>
		          <c:if test="${empty safeList}">
	          		<tr style="border-bottom:none;">
			          	<td colspan="3" align="center" height="35px">暂无数据</td>
			        </tr>
		          </c:if>
		        </tbody>
		        </table>
		        </div>
		        
		        
		        <!-- 利润提取记录  -->
		        <div class="grglzh_tab" style="margin:0; border:0;display: none;" id="div4">
		          <table border="0" cellspacing="0" cellpadding="0" width="100%">
		          <tbody>
		          <tr>
		            <td style="border-bottom:#e3e3e3 solid 1px;text-indent:20px" align="center" bgcolor="#f8f8f8" width="120px" height="35">提取金额（元）</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="110px">提取时间</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;padding-right:20px" align="center" bgcolor="#f8f8f8" width="50px">状态</td>
		          </tr>
		          <c:forEach items="${drawList}" var="draw"  varStatus="row">
	          		<tr <c:if test="${(fn:length(drawList)-1)==row.index}">style="border-bottom:none;"</c:if> >
			            <td width="120px"  align="center" style="text-indent:20px" height="35"><fmt:formatNumber value="${empty draw.money?0:draw.money}" pattern="#,###,##0.00"/></td>
			            <td width="110px" align="center"><fmt:formatDate value="${draw.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td width="50px" align="center" style="padding-right:20px">${draw.state==0?'待审核':draw.state==1?'审核中':draw.state==2?'成功':'拒绝'}</td>
			        </tr>
		          </c:forEach>
		          <c:if test="${empty drawList}">
	          		<tr style="border-bottom: none;">
			          	<td colspan="3" align="center" height="35px">暂无数据</td>
			        </tr>
		          </c:if>
		        </tbody>
		        </table>
		        </div>
		        
		        
		        <!-- 增配记录  -->
		        <div class="grglzh_tab" style="margin:0; border:0;display: none;" id="div5">
		          <table border="0" cellspacing="0" cellpadding="0" width="100%">
		          <tbody>
		          <tr>
		         	<td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100" height="35" >方案编号（ID）</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">方案类型</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">融资金额(元)</td>	
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">方案周期</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">创建时间</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">方案状态</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">总预警线(元)</td>
		            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="100">总强平线(元)</td>
		          </tr>
		          <c:forEach items="${upProgramList}" var="upProgram"  varStatus="row">
	          		<tr>
	          			<td width="100px" align="center" height="35">${upProgram.id}</td>
			            <td width="100px" align="center" height="35">增配</td>
			            <td width="100px" align="center" height="35" style="line-height:35px;"><fmt:formatNumber value="${empty upProgram.matchMoney?0:upProgram.matchMoney}" pattern="#,###,##0.00"/></td>
			            <td width="100px" align="center">${upProgram.cycleNum}${upProgram.programType==1?'天':'个月'}</td>
			            <td width="100px" align="center"><fmt:formatDate value="${upProgram.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td width="100px" align="center">${upProgram.status==7?'已穿仓':upProgram.status==6?'结算中':upProgram.status==5?'交易结束':upProgram.status==4?'等待结算':upProgram.status==3?'拒绝开户':upProgram.status==2?'交易中':upProgram.status==1?'审核中':upProgram.status==0?'待审核':upProgram.status==-1?'已关闭':'已删除'}</td>
			        	<td width="100px" align="center"><c:if test="${upProgram.status==2||upProgram.status==4||upProgram.status==6}"><fmt:formatNumber value="${upProgram.warnLine+program.warnLine}" pattern="#,###,##0.00"/></c:if><c:if test="${upProgram.status<2||upProgram.status==3||upProgram.status==5}"><fmt:formatNumber value="${program.warnLine}" pattern="#,###,##0.00"/></c:if></td>
			        	<td width="100px" align="center"><c:if test="${upProgram.status==2||upProgram.status==4||upProgram.status==6}"><fmt:formatNumber value="${upProgram.closeLine+program.closeLine}" pattern="#,###,##0.00"/></c:if><c:if test="${upProgram.status<2||upProgram.status==3||upProgram.status==5}"><fmt:formatNumber value="${program.closeLine}" pattern="#,###,##0.00"/></c:if></td>
			        </tr>
			      </c:forEach>
		          <c:if test="${empty upProgramList}">
          		  <tr style="border-bottom: none;">
		          	<td colspan="8" align="center" height="35px">暂无数据</td>
		          </tr>
		          </c:if>
		        </tbody>
		        </table>
		        </div>
		        
				
		        <!-- 交易账号和密码  -->
		        <div class=" zdtx zdtx0" id="div2" style="display: none;">
		        	<div>重点提醒：请在账户余额内存足够的资金以便按约定收益分成</div>
		            <div>资金风控：亏损警戒线<span style="color:#f28208; font-weight:600"><fmt:formatNumber value="${program.warnLine}" pattern="#,###,##0.00"/></span>元，亏损平仓线<span style="color:#c30300; font-weight:600"><fmt:formatNumber value="${program.closeLine}" pattern="#,###,##0.00"/></span>元</div>
		            <div>开户服务器：${empty program.fuServer.serverName?'暂无':program.fuServer.serverName}</div>
		            <div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp}</div>
		            <div>服务器交易端口：${empty program.tradePort?'暂无':program.tradePort}</div>
		            <div style="margin:8px 0"><span style="color:#c30300;font-weight:600 ">交易账户：通过短信下发通知、敬请留意。</span>(为了您的资金安全，请妥善保管好密码)</div>
		            <div><a href="${ctx}/index_guide/softwareDownload.htm" class="gzsm" style="color:#555555; text-decoration:none">进入交易软件下载页面</a></div>
		        </div>
		        
			</div>
		</div>
	<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
	//追加保证金
	function addSafeMoney(){
		$.fancybox.open({
 			href : '${ctx}/user_program/addConfirmMoneyAjax.htm?id=${program.id}',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//提取利润
	function drawMoney(){
		var location;
	    if(${!empty upProgram}&&${upProgram.status==2}){//有交易中的增配子方案
	    	location="${ctx}/user_program/drawMoneyAjax.htm?id=${upProgram.id}&drawType=1";//提取有增配子方案的主方案的利润
	    }else{//单独主方案或者增减配方案
	    	location="${ctx}/user_program/drawMoneyAjax.htm?id=${program.id}&drawType=2";
	    }
		$.fancybox.open({
 			href : location,
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//续约
	function toContinue(){
		$.fancybox.open({
 			href : '${ctx}/user_program/programContinueAjax.htm?id=${program.id}',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//结算操作
	function overProgram(){
	    if(${program.addMatchId>0}){
	    	$.alerts.okButton="确定";
			jAlert("增配子方案不能提前结算，到期后会自动结算！","提示",function(){
			});
			return false;
	    }
	    var location;
	    if(${!empty upProgram}&&${upProgram.status==2}){//有交易中的增配子方案
	    	location="${ctx}/user_program/overProgramAjax.htm?id=${upProgram.id}";//结算有增配子方案的主方案
	    }else{//单独主方案或者减配方案
	    	location="${ctx}/user_program/overProgramAjax.htm?id=${program.id}";
	    }
		$.fancybox.open({
 			href : location,
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//减配
	function subPeiziAjax(){
		$.post('${ctx}/user_program/subPeiziAjax.htm?id=${program.id}',null,function(d){
			var json=eval("("+d+")");
			if(json.result!="1"){
				$.alerts.okButton='确定';
				jAlert(json.result,'提示',function(){
				});
				return false;
			}
			location.href="${ctx}/user_program/minusPeizi.htm?subMatchId=${id}";			
		});
	}
	
	/* //降级方案
	function downProgram(){
		$.fancybox.open({
 			href : '${ctx}/user_program/downPageAjax.htm?id=${program.id}',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//升级方案
	function upProgram(){
		$.fancybox.open({
 			href : '${ctx}/user_program/upPageAjax.htm?id=${program.id}',
 			type : 'ajax',
 			padding : 5 
 		});
	} */
</script>
