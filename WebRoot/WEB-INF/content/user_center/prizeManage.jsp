<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－奖品管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background: #004e99;">
<c:set value="7" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
<div class="downban"></div>
<div class="mgrzx">
  <%@include file="../common/left.jsp" %>
  <%-- <%@include file="../common/left.jsp" %> --%>
  <div class="mgrzxr">
    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg">奖品管理</span></div>
    <div class="mgrrmain mgrrmaina">
      <div class="prizeDetail">
        <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;">
         	<tr>
         		<th width="30%">中奖时间</th>
         		<th width="50%">奖品</th>
         		<th width="20%">状态(是否领取)</th>
         	</tr>
         	<c:forEach items="${infoList}" var="info">
         	<tr>
         		<td><fmt:formatDate value="${info.drawTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
         		<td>${info.liveJackpot.name}</td>
         		<td>
	         		<c:if test="${info.isReceive == 0}">
	         		<div class="prizeTake">
	         			<span>未领取</span>
	         			<c:if test="${info.liveJackpot.type==0}">
	         			<a class="prizeTake-take" data-id="${info.id}" data-score="${info.liveJackpot.score}" onclick="prizeTake(this)">领取</a>
	         			</c:if>
	         		</div>
	         		</c:if> 
	         		<c:if test="${info.isReceive == 1}">已领取</c:if>
         		</td>
         	</tr>
         	</c:forEach>
        </table>
      </div>
      <div class="page">
		<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
			url="${ctx}/user_center/prizeManage.htm"
			totalNum="${totalCount}" curPageNum="${pageNo}"
			formId="pageForm">
		</domi:pagination>
	  </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
	//领取虚拟奖品
	function prizeTake(ths) {
		var id = $(ths).data("id");
		var score = $(ths).data("score");
		var $td = $(ths).parent().parent();
		$.post("${ctx}/user_center/prizeTake.htm",{id:id,score:score},function(d){
			if (d == 1) {
				$(ths).parent(".prizeTake").hide();
				$td.append("已领取");
			}
		})
	}
</script>
