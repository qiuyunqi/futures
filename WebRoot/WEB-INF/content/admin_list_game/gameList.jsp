<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－期货大赛－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="11"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">期货大赛<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_game/gameList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">报名时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="accountName" name="accountName" value="${accountName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
					<td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="sendMsg();" class="search">短信群发</a><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_game/gameList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  
				</tbody></table>
			</form>

            </div>
          <div class=" yhlb_title">期货大赛列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>交易账户</th>
				    <th>操盘资金</th>
				    <!-- <th>周盈利 </th>
				    <th>月盈利</th> -->
				    <th>报名时间</th>
				    <%--
				    <th style=" text-align:center">操作</th>
				    --%>
				    </tr>
				  <c:forEach items="${list}" var="game" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td<%--  style="color:#137490" <c:if test="${empty rageConfig||!empty rageConfig.jd.jd22}">onclick="gameInfo(${game.id});"</c:if> --%>>${game.fuUser.id}</td>
						    <td>${game.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${game.fuUser.userName}');">${game.fuUser.userName}</td>
						    <td>${game.tradeAccount}</td>
						    <td><fmt:formatNumber value="${empty game.gameMoney?0:game.gameMoney}" pattern="#,###,##0.00"/></td>
						    <%-- <td><fmt:formatNumber value="${empty game.gameIncomeWeek?0:game.gameIncomeWeek}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${empty game.gameIncomeMonth?0:game.gameIncomeMonth}" pattern="#,###,##0.00"/></td> --%>
						    <td><fmt:formatDate value="${game.gameTime}" pattern="yy-MM-dd HH:mm:ss"/></td>
						 	<%--
						 	<td style="text-align:center"><div class=" caozuo"><a href="javascript:void(0);" onclick="gameInfo(${game.id});">设置盈利</a></div></td>
						 	--%>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty list}">
				  <tr>
					<td colspan="7">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_game/gameList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="time" value="${time1}"/>
						<domi:paramTag name="time" value="${time2}"/>
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>

function gameInfo(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_game/inComeInfoAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
function sendMsg(){
	$.fancybox.open({
        href : '${ctx}/admin_op_game/gameMessageAjax.htm',
        type : 'ajax',
        padding : 10
	});
}
</script>
