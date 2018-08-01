<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户费率管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="8"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">用户费率管理<domi:privilege url="/admin_op_rate/newRatePop.htm"><a href="#" onclick="addRate('');" class="fr add">添加</a></domi:privilege></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_rate/rateList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户ID：</div>
				            <div class="fl input"><input id="userId" name="userId" value="${userId}" type="text" placeholder=""></div>
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
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_rate/rateList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  
				</tbody>
				</table>
			</form>

            </div>
          <div class=" yhlb_title">用户列表</div>
            <div class="yhlb">
            
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>亏损警报线百分比</th>
				    <th>亏损平仓线百分比</th>
				    <!-- <th>获取佣金比例 </th> -->
				    <th>日配利息，每日每万</th>
				    <th>每个月利息百分比</th>
				    <th>三个月利息百分比</th>
				    <th>短线高手操作最大数</th>
				    <th>趋势之王操作最大数</th>
				    <th>创建时间/更新时间</th>
				  <th style=" text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${rateList}" var="rate" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${rate.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${rate.fuUser.userName}');">${rate.fuUser.userName}</td>
						    <td><fmt:formatNumber value="${empty rate.warnlinePercent?0:rate.warnlinePercent*100}" pattern="#,###,##0.0000"/>%</td>
						    <td><fmt:formatNumber value="${empty rate.flatlinePercent?0:rate.flatlinePercent*100}" pattern="#,###,##0.0000"/>%</td>
						    <!-- <td><fmt:formatNumber value="${empty rate.commissionPercent?0:rate.commissionPercent/100}" pattern="#,###,##0.00"/></td> -->
						    <td><fmt:formatNumber value="${empty rate.feeDay?0:rate.feeDay}" pattern="#,###,##0.00"/>元</td>
						    <td><fmt:formatNumber value="${empty rate.feePercent?0:rate.feePercent*100}" pattern="#,###,##0.0000"/>%</td>
						    <td><fmt:formatNumber value="${empty rate.interestPercent?0:rate.interestPercent*100}" pattern="#,###,##0.0000"/>%</td>
						    <td><fmt:formatNumber value="${rate.shortNum}"/></td>
						    <td><fmt:formatNumber value="${rate.longNum}"/></td>
						    <td><fmt:formatDate value="${rate.createtime}" pattern="yyyy-MM-dd"/>/<fmt:formatDate value="${rate.updatetime}" pattern="yyyy-MM-dd"/></td>
						 	<td style="text-align:center">
							<div class=" caozuo">						    
							 	<domi:privilege url="/admin_op_rate/newRatePop.htm">
						 		<a href="javascript:void(0);" onclick="addRate('${rate.id}');">修改</a>
						 		</domi:privilege>
						 	</div>
						 	</td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty rateList}">
				  <tr>
					<td colspan="13">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				  
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_rate/rateList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="userId" value="${userId}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="searchType" value="${searchType}"/>
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
	if(isNaN($("#userId").val())){
		sureInfo("确定","输入用户ID必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
function addRate(id){
	$.fancybox.open({
          href : '${ctx}/admin_op_rate/newRatePop.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
