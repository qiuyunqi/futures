<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－股票状态记录－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style type="text/css">
.form_cont{width:240px;}
.lf_font{width:60px;}
.time{width:340px;}
.type{width:220px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="5"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    股票状态记录
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_status_record/statusRecordList.htm" method="post">
	            	<table width=100%" border="0" cellspacing="0" cellpadding="0">
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
						            <div class="lf_font fl">股票账号：</div>
						            <div class="fl input"><input id="" name="capitalAccount" value="${capitalAccount}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont time">
						            <div class="lf_font fl">时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont type">
						            <div class="lf_font fl">类型：</div>
						            <div class="fl select">
						            <select style="width:150px;" name="type">
						            	<option value="">所有</option>
							              <option value="0" <c:if test="${type==0}">selected</c:if>>今日开启申请</option>
					              		  <option value="1" <c:if test="${type==1}">selected</c:if>>今日暂停申请</option>
					              		  <option value="2" <c:if test="${type==2}">selected</c:if>>当前所有暂停申请</option>
					              		  <option value="3" <c:if test="${type==3}">selected</c:if>>提交账户</option>
						            </select>
						            </div>
						            <div class="clr"></div>
						        </div>
				    		</td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="query();" class="search">查询</a><a href="${ctx}/stock_status_record/statusRecordList.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">股票状态记录列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>姓名</th>
				    <th>手机号</th>
				    <th>股票账号</th>
				    <th>用户操作状态</th>
				    <th>操作之后状态</th>
				    <th>改变状态时间</th>
				    <th>处理时间</th>
				    <th>是否处理</th>
				  </tr>
				  <c:forEach items="${recordList}" var="record" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${record.fuUser.id}</td>
					    <td>${record.fuUser.userName}</td>
					    <td>${record.fuUser.phone}</td>
					    <td>${record.fuStockAccount.capitalAccount}</td>
					    <td>${record.operationStatus==0?'申请开启委托':record.operationStatus==1?'申请暂停委托':''}</td>
					    <td>${record.afterStatus==0?'申请开启委托中':record.afterStatus==1?'申请暂停委托中':''}</td>
					    <td><fmt:formatDate value="${record.changeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${record.operationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td>${record.isOperation==0?'未处理':record.isOperation==1?'处理完成':''}</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty recordList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_status_record/statusRecordList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
		        <domi:paramTag name="type" value="${type}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function query(){
	$('#searchForm').submit();
}
</script>
