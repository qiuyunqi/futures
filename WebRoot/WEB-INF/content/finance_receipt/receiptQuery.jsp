<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－收款单据查询－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    收款单据查询
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/finance_receipt/receiptQuery.htm" method="post">
	            	<table width="70%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						  	<td>
						        <div class="form_cont">
						            <div class="lf_font fl">类型：</div>
						            <div class="fl select">
						            <select style="width:178px;" name="detailType">
						            <option value="">所有</option>
						              <option value="12" <c:if test="${detailType==12}">selected</c:if>>合伙人联盟收益</option>
						              <option value="13" <c:if test="${detailType==13}">selected</c:if>>平台赔付</option>
						              <option value="14" <c:if test="${detailType==14}">selected</c:if>>支付管理费</option>
						            </select>
						            </div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl ">用户是否禁用：</div>
						            <div class="fl checkbox"><span class="xuankang"><input name="state" <c:if test="${state==0}">checked="checked"</c:if> type="checkbox" value="0" style="width:13px;" id="state"></span></div>
						        </div>
						        <div class="clr"></div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryReceipt();" class="search">查询</a><a href="${ctx}/finance_receipt/receiptQuery.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">收款明细记录</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>收款类型</th>
				    <th>付款人</th>
				    <th>付款人账号</th>
				    <th>收款金额(元)</th>
				    <th>备注</th>
				    <th>用户状态</th>
				    <th style=" text-align:center" >操作</th>
				  </tr>
				  <c:forEach items="${detailList}" var="detail" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${detail.fuUser.accountName}</td>
					    <td>${detail.fuUser.userName}</td>
					    <td>${detail.fuDictionary.id==12?'合伙人联盟收益':detail.fuDictionary.id==13?'平台赔付':detail.fuDictionary.id==14?'支付管理费':''}</td>
					    <%-- <td>${detail.fuUser.userName}</td>
					    <td>${detail.fuUser.accountName}</td> --%>
					    <td></td>
					    <td></td>
					    <td style=" text-align:right">${detail.money}</td>
					    <td>${detail.comment}</td>
					    <td>${detail.fuUser.state==0?'已禁用':detail.fuUser.state==1?'未禁用':''}</td>
					    <td style="text-align:center"><div class=" caozuo">
						    <domi:privilege url="/finance_receipt/exportExcel.htm"><a href="${ctx}/finance_receipt/exportExcel.htm?id=${detail.id}">导出Excel</a></domi:privilege>
					    </td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty detailList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/finance_receipt/receiptQuery.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="detailType" value="${detailType}"/>
				<domi:paramTag name="time1" value="${time1}"/>
				<domi:paramTag name="time2" value="${time2}"/>
				<domi:paramTag name="state" value="${state}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryReceipt(){
	$('#searchForm').submit();
}
</script>
