<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style=" background:#f2f2f2">
<c:set value="1" var="pg"></c:set>
<%@include file="../common/userTop.jsp" %>

<div class="center">
	<%@include file="../common/left.jsp" %>
    <div class="fl rt_cont">
    	<div class="rt_cont_title">客服电话：010-53320537&nbsp;&nbsp;&nbsp;&nbsp;工作时间：8:30-17:00</div>
        
        <div class=" dxgs">
        	<ul>
            	<li class="${empty flag||flag==1?'yxz':''}"><a href="${ctx}/user_program/programList.htm?programType=1&flag=1">进行中</a></li>
                <li class="${empty flag||flag==2?'yxz':''}"><a href="${ctx}/user_program/programList.htm?programType=1&flag=2">待结算</a></li>
                <li class="${empty flag||flag==3?'yxz':''}"><a href="${ctx}/user_program/programList.htm?programType=1&flag=3">已流单</a></li>
                <li class="${empty flag||flag==4?'yxz':''}"><a href="${ctx}/user_program/programList.htm?programType=1&flag=4">已结束</a></li>
            </ul>
            <div class="clr"></div>
        </div>
        
        <div class=" ny_bt">
        	<span class="pbsx fl"></span><span class="ny_bt_cont fl">进行中${fn:length(programList)}笔</span>
            <span style="color:#076b5e; font-size:12px; float:left">(每日收取管理费)</span>
            <a href="${ctx}/index_guide/applyProgress.htm" class=" gzsm fr">期货日配规则说明</a>
            <div class="clr"></div>
        </div>
        <div class="grglzh_tab" style="margin:13px 20px 20px;*margin-top: -10px">
          <table border="0" cellspacing="0" cellpadding="0" width="100%">
          <tbody><tr>
            <td height="35" style="border-bottom:#e3e3e3 solid 1px;" align="right" bgcolor="#f8f8f8" width="120px">融资资金(元)  </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="right" bgcolor="#f8f8f8" width="120px">初始保证金(元) </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="right" bgcolor="#f8f8f8" width="150px">每天账户管理费(元)  </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="120px">已交易天数(天)   </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="120px">开始交易时间</td> 
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" >操作</td>            
          </tr>
	          <c:forEach items="${programList}" var="pro" varStatus="row">
	          		<tr>
			            <td height="35" align="right" style="border-bottom: 0"><fmt:formatNumber value="${pro.matchMoney}" pattern="#,###,##0.00"/></td>
			            <td align="right" style="border-bottom: 0"><fmt:formatNumber value="${pro.safeMoney}" pattern="#,###,##0.00"/></td>
			            <td align="right" style="border-bottom: 0"><fmt:formatNumber value="${pro.moneyPercent*pro.matchMoney}" pattern="#,###,##0.00" /></td>
			            <td align="center" style="border-bottom: 0">${dayList[row.index]}天</td>
			            <td align="center" style="border-bottom: 0">
			            	<c:if test="${pro.programWay==1}">
			            		${pro.status==7?'已穿仓':pro.status==6?'结算中':pro.status==5?'交易结束':pro.status==4?'等待结算':pro.status==3?'拒绝开户':pro.status==2?'':pro.status==1?'审核中':'待审核'}
			            	</c:if>
			            	<c:if test="${pro.programWay!=1}">
			            		${pro.status==7?'已穿仓':pro.status==6?'结算中':pro.status==5?'交易结束':pro.status==4?'等待结算':pro.status==3?'拒绝开户':pro.status==2?'':pro.status==1?'审核中':'待审核'}
			            	</c:if>
			            	<c:if test="${pro.status==2}">
			            		<fmt:formatDate value="${pro.tradeTime}" pattern="yyyy-MM-dd"/>
			            	</c:if>
			            </td>
			            <td align="center" style="border-bottom: 0"><a href="${ctx}/user_program/programDetail.htm?id=${pro.id}" class=" guanli">管理</a><a href="javascript:void(0);" onclick="detail(${pro.id});" class=" xq xq0">账户<i class="jt_1" id="icon${pro.id}"></i></a></td>
			         </tr>
			         <tr>
	          		<td colspan="6" style="${row.index==(fn:length(programList)-1)?'border-bottom:0;':''}">
		            	<div id="tr${pro.id}" class=" zdtx" style="display: none;">
				        	<div>重点提醒：请在账户余额内存足够的资金以便扣除管理费</div>
				            <div>资金风控：亏损警戒线<span style="color:#f28208; font-weight:600"><fmt:formatNumber value="${pro.warnLine}" pattern="#,###,##0.00"/></span>元，亏损平仓线<span style="color:#c30300; font-weight:600"><fmt:formatNumber value="${pro.closeLine}" pattern="#,###,##0.00"/></span>元</div>
				            <div>开户服务器：${empty pro.tradeServiceName?'暂无':pro.tradeServiceName}</div>
				            <div>开户IP地址：${empty pro.tradeIp?'暂无':pro.tradeIp}</div>
				            <div>服务器交易端口：${empty pro.tradePort?'暂无':pro.tradePort}</div>
				            <div style="margin:8px 0"><span style="color:#c30300;font-weight:600 ">交易账户：通过短信下发通知、敬请留意。</span>(为了您的资金安全，请妥善保管好密码)</div>
				            <div><a href="${ctx}/index_guide/softwareDownload.htm" class=" gzsm" style="color:#555555; text-decoration:none">进入交易软件下载页面</a></div>
				        </div>
			            </td>
	            	</tr>
	          </c:forEach>
	          <c:if test="${empty programList}">
	          <tr>
	            <td height="70px" align="center"  colspan="6" style="border-bottom:0;">暂时没有数据</td>
	          </tr>
	          </c:if>
        </tbody></table>
        </div> 
    </div>
    <div class="clr"></div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx }/js/fancybox/jquery.fancybox.pack.js?v=2.1.4"></script>
<link rel="stylesheet" href="${ctx }/js/fancybox/jquery.fancybox.css?v=2.1.4" type="text/css" media="screen" />
<script>
function detail(id){
	var f=$("#tr"+id).css("display");
	if(f=='none'){
		$("#tr"+id).slideDown(500);
		$("#icon"+id).removeClass("jt_1").addClass("jt_0");
	}else{
		$("#tr"+id).slideUp(500);
		$("#icon"+id).removeClass("jt_0").addClass("jt_1");
	}
}
</script>
