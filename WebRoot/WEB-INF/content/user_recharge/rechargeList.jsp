<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－充值记录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

    <div class="mgrzx">
    <%@include file="../common/left.jsp" %>
    <div class="mgrzxr">
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzczjl" style="padding-bottom:0;">
          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
	          <tr>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">时间</td>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">订单号</td>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">充值方式</td>   
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;text-align: right;">金额（元）</td>  
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">状态</td>  
	          </tr>
	           <c:forEach items="${rechargeList}" var="recharge" varStatus="row">
	          <tr>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${recharge.rechargeTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">border-bottom:none;</c:if>">${recharge.orderNum}</td>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>>${recharge.type==1?'银联支付':recharge.type==2?'网银充值':recharge.type==3?'支付宝':'银行转账'}</td>
	            <td height="35" align="right" style="text-align: right;"<c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${recharge.rechargeMoney}" pattern="#,###,##0.00"/>&nbsp</td>
	            <%-- <c:if test="${recharge.type<3}">
	            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><c:if test="${recharge.payStatus==0}"><a href="${ctx }/user_recharge/quickSucced.htm?id=${recharge.id}">未审核</a></c:if><c:if test="${recharge.payStatus==1}">成功</c:if></td>
	            </c:if>
	            <c:if test="${recharge.type>=3}"> --%>
                <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>>${recharge.rechargeStatus==0?'未审核':recharge.rechargeStatus==1?'审核中':recharge.rechargeStatus==2?'成功':'拒绝'}</td>
                <%-- </c:if> --%>
	          </tr>
	          </c:forEach>
          </table>
          <c:if test="${empty rechargeList}">
            <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
          </c:if>
        </div>
        
        <div id="one_1" class="display-b">
        <div class="rzfbzz">
          <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">您可以通过网上银行或银行柜台向投资管理平台转账（手续费最多一笔50元）</span>
          </div>
	      <div class=" zhuanzhang">
	      	<table border="0" cellspacing="0" cellpadding="0">
			  <tbody>
			  <tr>
			    <td><span><img src="../qihuo_images/zhcz_07.gif" width="170" height="52"></span></td>
			    <td width="500">
			        <p>账号：127906754610206</p>
			        <p>开户名：武汉坤州大德投资管理有限公司<span style="margin-left:100px;"><a href="javascript:void(0)" onclick="window.open('https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/Login.aspx')" style="font-size:20px;color:#00CC00;">进入网银</a></span></p>
			        <p>开户行：招商银行股份有限公司武汉首义支行</p>
			    </td>
			  </tr>
			  <tr>
			    <td><span><img src="../qihuo_images/zhcz_09.gif" width="170" height="52"></span></td>
			    <td width="500">
			        <p>账号：6217 0000 1007 7384 314</p>
			        <p>开户名：唐文钊<span style="margin-left:255px;"><a href="javascript:void(0)" onclick="window.open('https://ibsbjstar.ccb.com.cn/app/V5/CN/STY1/login.jsp')" style="font-size:20px;color:#00CC00;">进入网银</a></span></p>
			        <p>开户行：中国建设银行(北京长河湾支行)</p>
			    </td>
			  </tr>
			</tbody>
			</table>
	      </div>  
         <div class=" fgkf">
	    	<div class="fgkf_fl fl">
	        	<div style="margin-bottom:10px;">1、转账时金额最好有些零头（如1000.75），这样我们好确认是您的汇款</div>
	            <div>2、在用户网银转账之后，请务必保留网银转账成功时的截图，并在<b>资金或转账用途中</b><span style="color:#cc0000">备注清自己要转载投资管理平台的用户名，</span>将回单发在QQ客服，以便尽快到账！</div>
	          <a href="javascript:void(0)" onclick="window.open('http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA0OTI0MF8yNTQxNDNfNDAwNjkyODk5OV8yXw');" class="fsgkf">发送给QQ客服</a>
	        </div>
	        <div class="fl" style=" margin:100px 0 0 10px;"><img src="../qihuo_images/yzzg_06.gif" width="50" height="38"></div>
	        <div class="fl"><img src="../qihuo_images/kzddyhzz.png" width="304" height="196"></div>
	        <div class="clr"></div>
	        <div style="margin:30px 0 30px;">
	           <input name="" type="button" value="返回首页" onclick="window.location.href='${ctx}/index_info/index.htm'" class="tijiao">
	        </div>
        </div>   
       <div style="height:100px"></div>
      </div>
    </div>
        
        
        <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/user_recharge/rechargeList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		    </domi:pagination>
		</div>
      </div>
    </div>
    
    
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>