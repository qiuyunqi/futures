<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户直播竞猜列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">直播竞猜</div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_live/liveGuessList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">是否中奖：</div>
				            <div class="fl select"><select style="width:178px;" id="isWinning" name="isWinning">
				            	<option value="">全部</option>
				            	<option <c:if test="${isWinning==0}">selected="selected"</c:if> value="0">未中奖</option>
				            	<option <c:if test="${isWinning==1}">selected="selected"</c:if> value="1">已中奖</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_live/liveGuessList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">直播竞猜列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>抽奖id</th>
				    <th>竞猜答案</th>
				    <th>竞猜时间</th>
				    <th>是否中奖</th>
				    <th>获奖金额</th>
				    <th>获奖时间</th>
				    </tr>
				  <c:forEach items="${list}" var="guess" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${guess.fuUser.userName}</td>
						    <td>${guess.liveDraw.id}</td>
						    <td><fmt:formatNumber value="${empty guess.guessAnswer?0:guess.guessAnswer}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatDate value="${guess.guessTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${guess.isWinning==1?'已中奖':'未中奖'}</td>
						    <td><fmt:formatNumber value="${empty guess.awardsMoney?'':guess.awardsMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatDate value="${guess.awardsTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty list}">
				  <tr>
					<td colspan="8">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_live/liveGuessList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="isWinning" value="${isWinning}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function searchInfo(){
	$('#searchForm').submit();
}
</script>
