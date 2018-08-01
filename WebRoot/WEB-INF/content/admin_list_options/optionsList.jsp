<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－微期权－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
	.remark{
            width:130px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    .hidden{position: absolute;z-index: 99;background: #99CCFF;text-align:left;padding:2px;}
</style>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    微期权购买记录
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_options/optionsList.htm" method="post">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
						            <div class="lf_font fl">抢购时间：</div>
						            <div class="fl input"><input name="panicTime1" type="text" value="<fmt:formatDate value='${panicTime1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="panicTime2" type="text" value="<fmt:formatDate value='${panicTime2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">支付时间：</div>
						            <div class="fl input"><input name="payTime1" type="text" value="<fmt:formatDate value='${payTime1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="payTime2" type="text" value="<fmt:formatDate value='${payTime2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
						  <tr>
						  	<td>
						        <div class="form_cont">
						            <div class="lf_font fl">是否支付：</div>
						            <div class="fl select"><select style="width:178px;" id="isPay" name="isPay">
						            	<option value="">全部</option>
						            	<option <c:if test="${isPay==0}">selected="selected"</c:if> value="0">未支付</option>
						            	<option <c:if test="${isPay==1}">selected="selected"</c:if> value="1">已支付</option>
						            </select></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">购买类型：</div>
						            <div class="fl select"><select style="width:178px;" id="guessType" name="guessType">
						            	<option value="">全部</option>
						            	<option <c:if test="${guessType==0}">selected="selected"</c:if> value="0">买跌</option>
						            	<option <c:if test="${guessType==1}">selected="selected"</c:if> value="1">买涨</option>
						            </select></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="querySmallOptions();" class="search">查询</a><a href="${ctx}/admin_list_options/optionsList.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">微期权购买记录</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>用户姓名</th>
				    <th>购买类型</th>
				    <th>期数</th>
				    <th>订单号</th>
				    <th>购买金额(元)</th>
				    <th>订单收益</th>
				    <th>抢购时间</th>
				    <th>支付时间</th>
				    <th>是否支付</th>
				  </tr>
				  <c:forEach items="${optionsList}" var="options" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${options.fuUser.id}</td>
					    <td>${options.fuUser.phone}</td>
					    <td>${options.fuUser.userName}</td>
					    <td>${options.guessType==1?'买涨':options.guessType==0?'买跌':''}</td>
					    <td>${options.wqqContents.name}</td>
					    <td>${options.tradeNo}</td>
					    <td>${options.money}</td>
					    <td><fmt:formatNumber value="${empty options.orderIncome?'0.00':options.orderIncome}" pattern="#,###,##0.00"/></td>
					    <td><fmt:formatDate value="${options.panicTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${options.payTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td>${options.isPay==0?'未支付':options.isPay==1?'已支付':''}</td> 
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty optionsList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_options/optionsList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
		        <domi:paramTag name="phone" value="${phone}"/>
		        <domi:paramTag name="panicTime1" value="${panicTime1}"/>
		        <domi:paramTag name="panicTime2" value="${panicTime2}"/>
		        <domi:paramTag name="payTime1" value="${payTime1}"/>
		        <domi:paramTag name="payTime2" value="${payTime2}"/>
		        <domi:paramTag name="isPay" value="${isPay}"/>
		        <domi:paramTag name="guessType" value="${guessType}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function querySmallOptions(){
	$('#searchForm').submit();
}
</script>
