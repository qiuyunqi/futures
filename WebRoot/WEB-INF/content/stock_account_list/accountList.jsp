<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－股票账户－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.accountLisAll{position: relative;}
.accountLisAll>span{margin-right:15px;}
.accountLisUp{position:absolute;display: block;width:9px;height:5px;background: url("../images_new/icoup.png") no-repeat;top:8px;right:0;cursor: pointer;} 
.accountLisDown{position:absolute;display: block;width:9px;height:5px;background: url("../images_new/icodown.png") no-repeat;top:16px;right:0;cursor: pointer;} 
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    股票账户
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_account_list/accountList.htm" method="post">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						  	<td>
						  		<input id="createTimeUp" name="createTimeUp" type="hidden" value="${createTimeUp}">
						  		<input id="createTimeDown" name="createTimeDown" type="hidden" value="${createTimeDown}">
						  		<input id="updateTimeUp" name="updateTimeUp" type="hidden" value="${updateTimeUp}">
						  		<input id="updateTimeDown" name="updateTimeDown" type="hidden" value="${updateTimeDown}">
						        <div class="form_cont">
						            <div class="lf_font fl">手机号：</div>
						            <div class="fl input"><input id="phone" name="phone" value="${phone}" type="text" placeholder=""></div>
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
						            <div class="lf_font fl">资金账号：</div>
						            <div class="fl input"><input id="" name="capitalAccount" value="${capitalAccount}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <%-- <td>
						        <div class="form_cont">
						            <div class="lf_font fl">状态：</div>
						            <div class="fl select">
							            <select style="width:178px;" name="state">
							            <option value="">所有</option>
			                            <option value="0"  <c:if test="${state==0}">selected</c:if>>正在操盘</option>
							            <option value="1"  <c:if test="${state==1}">selected</c:if>>暂停</option>
							            <option value="2"  <c:if test="${state==2}">selected</c:if>>申请开启委托中</option>
							            <option value="3"  <c:if test="${state==3}">selected</c:if>>申请暂停委托中</option>
							            <option value="4"  <c:if test="${state==4}">selected</c:if>>清算（删除）</option>
							            </select>
						            </div>
						            <div class="clr"></div>
						        </div>
				    		</td> --%>
				    		<td>
						        <div class="form_cont">
						            <div class="lf_font fl">创建时间：</div>
						            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" style="width:75px;"><i class="riqi"></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" style="width:75px;"><i class="riqi"></i></div>
						            <div class="clr"></div>
						        </div>
				   			</td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryAccounts();" class="search">查询</a><a href="${ctx}/stock_account_list/accountList.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    <div class="state">
    	    	<a class="accountBtn operator" href="${ctx}/stock_account_list/accountList.htm?state=0">正在操盘</a>
				<a class="accountBtn stopBtn" href="${ctx}/stock_account_list/accountList.htm?state=1">暂停</a>
				<a class="accountBtn squateBtn" href="${ctx}/stock_account_list/accountList.htm?state=4">清算（删除）</a>
				<a class="accountBtn unpublished" href="${ctx}/stock_account_list/accountList.htm?isPublish=0">账户未发布</a>
				<a class="accountBtn allBtn" href="${ctx}/stock_account_list/accountList.htm">全部</a>
				<div class="clear"></div>
    	    </div>
          <div class=" yhlb_title">股票账户列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>用户姓名</th>
				    <th>开户姓名</th>
				    <th>开户券商</th>
				    <th>营业部</th>
				    <th>资金账号</th>
				    <th>账户类型</th>
				    <th>账户状态</th>
				    <th>融券宝</th>
				    <th>交易密码</th>
				    <th><div class="accountLisAll"><span>创建时间</span><i id="accountLisUpFir" class="accountLisUp" onclick="createTimeAsc()"></i><i id="accountLisDownFir" class="accountLisDown" onclick="createTimeDesc()"></i></div></th>
				    <th><div class="accountLisAll"><span>更新时间</span><i id="accountLisUpSec" class="accountLisUp" onclick="updateTimeAsc()"></i><i id="accountLisDownSec" class="accountLisDown" onclick="updateTimeDesc()"></i></div></th>
				    <th>是否删除</th>
				    <th>是否发布</th>
				    <th>是否缴纳保证金</th>
				    <th>审核状态</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${stockList}" var="stock" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${stock[0]}</td>
					    <td>${stock[9]}</td>
					    <td>${stock[10]}</td>
					    <td>${stock[1]}</td>
					    <td>${stock[2]}</td>
					    <td>${stock[3]}</td>
					    <td>${stock[4]}</td>
					    <td>${stock[5]==1?'普通账户':stock[5]==2?'融资融券':'信用账户'}</td>
					    <c:if test="${stock[13]==4}">
						    <td style="${stock[11]==0?'color:green':stock[11]==1?'color:orange':stock[11]==4?'color:red':''}">${stock[11]==0?'正在操盘':stock[11]==1?'暂停':stock[11]==2?'申请开启委托中':stock[11]==3?'申请暂停委托中':stock[11]==4?'申请结算中':'清算（删除）'}</td>
					    </c:if>
					    <c:if test="${stock[13]!=4}">
						    <td></td>
					    </c:if>
					     <td>${stock[15] == 1 ? "已发布" : "未发布"}</td>
					    <td>${stock[6]}</td>
					    <td><fmt:formatDate value="${stock[7]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${stock[14]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td>${stock[8]==1?'已删除':'未删除'}</td>
					  	<td>${stock[15]==1?'已发布':'未发布'}</td>
					  	<td>${stock[16]==1?'已缴纳':'未缴纳'}</td>
					    <td>${stock[13]==0?'审核中':stock[13]==1?'审核成功':stock[13]==2?'审核失败':stock[13]==3?'接单中':stock[13]==4?'接单成功':''}</td>
					  	<td>
					  		<c:if test="${stock[13]==0 || stock[13]==2}">
						  		<domi:privilege url="/stock_account_list/newAccountAjax.htm"><a href="javascript:void(0);" onclick="updateStock('${stock[12]}','${stock[13]}')">审核</a></domi:privilege>
						    </c:if>
						    <c:if test="${stock[13]==1 || stock[13]==3 || stock[13]==4}">
						  		<domi:privilege url="/stock_account_list/newAccountInfoAjax.htm"><a href="javascript:void(0);" onclick="updateStock('${stock[12]}','${stock[13]}')">修改</a></domi:privilege>
						    </c:if>
					  	</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty stockList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_account_list/accountList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="phone" value="${phone}"/>
				<domi:paramTag name="userName" value="${userName}"/>
		        <domi:paramTag name="accountUserId" value="${accountUserId}"/>
		        <domi:paramTag name="capitalAccount" value="${capitalAccount}"/>
		        <domi:paramTag name="state" value="${state}"/>
		        <domi:paramTag name="createTimeUp" value="${createTimeUp}"/>
		        <domi:paramTag name="createTimeDown" value="${createTimeDown}"/>
		        <domi:paramTag name="updateTimeUp" value="${updateTimeUp}"/>
		        <domi:paramTag name="updateTimeDown" value="${updateTimeDown}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
//升降序按钮特效
$(function(){
  	$(".accountLisUp").click(function(){
  	  $(this).hide();
  	  $(this).next(".accountLisDown").show();
  	});
  	
  	$(".accountLisDown").click(function(){
  	  $(this).hide();
  	  $(this).prev(".accountLisUp").show();
  	});
  	
  	if($("#createTimeUp").val()==1){
  		$("#accountLisUpFir").hide();
		$("#accountLisDownFir").show();
  	}
  	
  	if($("#createTimeDown").val()==1){
  		$("#accountLisUpFir").show();
		$("#accountLisDownFir").hide();
  	}
  	
  	if($("#updateTimeUp").val()==1){
  		$("#accountLisUpSec").hide();
		$("#accountLisDownSec").show();
  	}
  	
  	if($("#updateTimeDown").val()==1){
  		$("#accountLisUpSec").show();
		$("#accountLisDownSec").hide();
  	}
});


function createTimeAsc(){
	$('#createTimeUp').val("1");
	$('#createTimeDown').val("");
	$('#updateTimeUp').val("");
	$('#updateTimeDown').val("");
	$('#searchForm').submit();
}

function createTimeDesc(){
	$('#createTimeUp').val("");
	$('#createTimeDown').val("1");
	$('#updateTimeUp').val("");
	$('#updateTimeDown').val("");
	$('#searchForm').submit();
}

function updateTimeAsc(){
	$('#createTimeUp').val("");
	$('#createTimeDown').val("");
	$('#updateTimeUp').val("1");
	$('#updateTimeDown').val("");
	$('#searchForm').submit();
}

function updateTimeDesc(){
	$('#createTimeUp').val("");
	$('#createTimeDown').val("");
	$('#updateTimeUp').val("");
	$('#updateTimeDown').val("1");
	$('#searchForm').submit();
}

function queryAccounts(){
	$('#searchForm').submit();
}

function updateStock(id, status){
	if(status==1 || status==3 || status==4){
		$.fancybox.open({
	          href : "${ctx}/stock_account_list/newAccountInfoAjax.htm?id="+id,
	          type : 'ajax',
	          padding : 10
		});
	}else{
		$.fancybox.open({
	          href : "${ctx}/stock_account_list/newAccountAjax.htm?id="+id,
	          type : 'ajax',
	          padding : 10
		});
	}
} 
</script>
