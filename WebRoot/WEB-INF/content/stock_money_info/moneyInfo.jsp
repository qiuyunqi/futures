<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套者统计－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style type="text/css">
	body{background: #efefef;}
	.pay{border:1px solid #2db1e1;border-radius:20px;padding:2px 15px;background:#2db1e1;color:#fff;font-size:11px;margin-left: 10px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    解套者统计
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_money_info/moneyInfo.htm" method="post">
	            	<table width="70%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">用户ID：</div>
						            <div class="fl input"><input id="" name="userId" value="${userId}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">手机号：</div>
						            <div class="fl input"><input id="" name="phone" value="${phone}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">创建时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryStockMoneyInfo();" class="search">查询</a><a href="${ctx}/stock_money_info/moneyInfo.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">解套者统计列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>用户姓名</th>
				    <th>盈利统计(元)</th>
				    <!-- <th>可索赔付统计(元)</th> -->
				    <th>应缴费用统计(元)</th>
				    <th>已缴费用统计(元)</th>
				    <th>未缴费用统计(元)</th>
				    <th>创建时间</th>
				    <th>更新时间</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${stockMoneyList}" var="money" varStatus="row">
				  	  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
   					    <td onclick="window.location='${ctx}/stock_money_detail/userDetailQuery.htm?userId=${money.fuUser.id}'">${money.fuUser.id}</td>
					    <td>${money.fuUser.phone}</td>
					    <td>${money.fuUser.userName}</td>
					    <td style=" text-align:right">${money.profitInfo}</td>
					    <%-- <td style=" text-align:right">${money.compensateInfo}</td> --%>
					    <td style=" text-align:right">${money.mustFeeInfo}</td>
					    <td style=" text-align:right">${money.payFeeInfo}</td>
					    <td style=" text-align:right">${money.noneFeeInfo}</td>
					    <td><fmt:formatDate value="${money.createtime}" pattern="yyyy-MM-dd"/></td>
					    <td><fmt:formatDate value="${money.updatetime}" pattern="yyyy-MM-dd"/></td>
					    <td><domi:privilege url="/stock_money_info/oneKeyPay.htm"><a class="pay" href="javascript:void(0);" onclick="oneKeyPay(${empty money.noneFeeInfo?0:money.noneFeeInfo},${money.fuUser.id})">一键缴费</a></domi:privilege></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty stockMoneyList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_money_info/moneyInfo.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryStockMoneyInfo(){
	$('#searchForm').submit();
}
function oneKeyPay(money,id){
	if(parseInt(money)<=0){
		jAlert('未缴费用为0，不需要缴费！','提示',function(){
		});
		return false;
	}
	$.fancybox.open({
		href : '${ctx}/stock_money_info/oneKeyPay.htm?money='+money+'&userId='+id,
		type : 'ajax',
		padding : 10 
	});
};
</script>
