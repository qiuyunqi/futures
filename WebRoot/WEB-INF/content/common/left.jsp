<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="hwdAbout">
	<div class="hgrzxtzh">账户余额(元):<span style="font-size:21px;"><fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/></span></div>
	<div class="hgrzxtzhbut">
	   <a class="userCcz" href="${ctx}/user_recharge/recharge.htm">充值</a>
	   <a class="userTx" href="${ctx}/user_draw_money/drawMoney.htm">提现</a>
	</div>
	<div class="userCenList">
	  <ul>
	    <li <c:if test="${jsptype=='centerIndex'}">class="hwdzhli"</c:if>><a href="${ctx}/user_center/centerIndex.htm">账户总览</a></li> 
	    <li <c:if test="${jsptype=='safeInfo'}">class="hwdzhli"</c:if>><a href="${ctx}/user_center/safeInfo.htm">安全设置</a></li> 
	    <li <c:if test="${jsptype=='mall'}">class="hwdzhli"</c:if>><a  href="${ctx}/user_center/mall.htm">合伙人收益</a></li> 
	    <li <c:if test="${jsptype=='bankManager'||jsptype=='bankCardInfo'}">class="hwdzhli"</c:if>><a href="${ctx}/user_draw_money/bankManager.htm">银行卡管理</a></li> 
	    <li <c:if test="${jsptype=='messageInfo'}">class="hwdzhli"</c:if>><a href="${ctx}/user_message/messageInfo.htm">在线留言</a></li> 
	    <li <c:if test="${jsptype=='prizeManage'}">class="hwdzhli"</c:if>><a href="${ctx}/user_center/prizeManage.htm">奖品管理</a></li> 
	    <li <c:if test="${jsptype=='creditManage'}">class="hwdzhli"</c:if>><a href="${ctx}/user_center/creditManage.htm">积分管理</a></li> 
	  </ul>
	</div>
</div>
