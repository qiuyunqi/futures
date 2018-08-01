<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资金交易明细－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">资金交易明细</div>
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_money_trade/moneyTradeList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">明细ID：</div>
				            <div class="fl input"><input id="id" name="id" type="text" value="${id}"  placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" type="text" value="${userName}"  placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">类型：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="dictionaryId" id="dictionaryId">
				               <option value="">全部明细</option>
				   			   <c:forEach items="${dictionaries}" var="dictionar">
				               <option value="${dictionar.id}" <c:if test="${dictionaryId==dictionar.id}">selected</c:if>>${dictionar.name}</option>
			            	   </c:forEach>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">详情：</div>
				            <div class="fl input"><input name="comment" value="${comment}" type="text" placeholder="" id="comment"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="accountName" name="accountName" type="text" value="${accountName}"  placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">金额：</div>
				            <div class="fl input"><input name="money1" type="text"  value="${money1}" placeholder="" style="width:80px;" id="money1"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="money2" type="text"  value="${money2}" placeholder="" style="width:80px;" id="money2"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">时间：</div>
				            <div class="fl input"><input name="date1" type="text"  value="<fmt:formatDate value='${date1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});" id="date1"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="date2" type="text"  value="<fmt:formatDate value='${date2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});" id="date2"><i class="riqi" ></i></div>
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
				    <tr>
				    	<td>
					        <div class="form_cont">
					            <div class="lf_font fl"></div>
					            <div class="fl">
					            	<a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a>
					            	<a href="${ctx}/admin_list_money_trade/moneyTradeList.htm" class="remove" style="margin-left:30px;">清空</a>
					            	<c:if test="${!empty detailList}">
						            	<a href="javascript:void(0);" onclick="exportExcel();" class="search" style="margin-left:30px;">导出</a>
						            </c:if>
					            </div>
					            <div class="clr"></div>
					        </div>
					    </td>
				    </tr>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">资金交易明细</div>
            <div class="yhlb">
            <table width="100%">
		  	<tbody><tr>
		    <th>&nbsp;</th>
		    <th>明细ID</th>
		    <th>用户名</th>
		    <th>真实姓名</th>
		    <th>类型</th>
		    <th>详情</th>
		    <th>金额 </th>
		    <th>可用余额 </th>
		    <th>时间</th>
		    <th>用户状态</th>
  			</tr>
		    <c:forEach items="${detailList}" var="detail" varStatus="row">
			<tr onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
		    <td class="num">${row.index+1}</td>
		    <td>${detail.id}</td>
		    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByAccount('${detail.fuUser.accountName}');">${detail.fuUser.accountName}</td>
		    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${detail.fuUser.userName}');">${detail.fuUser.userName}</td>
		    <td>${detail.fuDictionary.name}</td>
		    <td>${detail.comment}</td>
		    <td><fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/></td>
		    <td><fmt:formatNumber value="${detail.accountBalanceAfter}" pattern="#,###,##0.00"/></td>
		    <td><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    <td>${detail.fuUser.state==0?'已禁用':detail.fuUser.state==1?'未禁用':''}</td>
		  </tr>
		  </c:forEach>
		    <c:if test="${empty detailList}">
			<tr>
				<td colspan="12">
		        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
		        </td>
		  </tr>
		</c:if>
		</tbody>
		</table>
          </div>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_money_trade/moneyTradeList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="id" value="${id}"/>
				<domi:paramTag name="accountName" value="${accountName}"/>
				<domi:paramTag name="userName" value="${userName}"/>
				<domi:paramTag name="dictionaryId" value="${dictionaryId}"/>
				<domi:paramTag name="comment" value="${comment}"/>
				<domi:paramTag name="money1" value="${money1}"/>
				<domi:paramTag name="money2" value="${money2}"/>
				<domi:paramTag name="date1" value="${date1}"/>
				<domi:paramTag name="date2" value="${date2}"/>
				<domi:paramTag name="state" value="${state}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function exportExcel(){
	var id =  $('#id').val();
	var userName =  $('#userName').val();
	var dictionaryId =  $('#dictionaryId').val();
	var comment =  $('#comment').val();
	var accountName =  $('#accountName').val();
	var money1 =  $('#money1').val();
	var money2 =  $('#money2').val();
	var date1 =  $('#date1').val();
	var date2 =  $('#date2').val();
	var state = 1;
	if($('#state').is(":checked") == true){
		state = 0;
	}
	if(isNaN($("#money1").val())){
		jAlert("输入金额必须为数字","提示",function(){
		    $("#money1").focus();
        });
		return false;
	}
	if(isNaN($("#money2").val())){
		jAlert("输入金额必须为数字","提示",function(){
		    $("#money2").focus();
        });
		return false;
	}
	//alert("id: "+id+",userName: "+userName+", dictionaryId: "+dictionaryId+", comment: "+comment+", accountName: "+accountName+", money1: "+money1+", money2: "+money2+", date1: "+date1+", date2: "+date2 );
	window.location.href=encodeURI('${ctx}/admin_list_money_trade/exportExcel.htm?id='+id+'&userName='+userName+'&dictionaryId='+dictionaryId+'&comment='+comment+'&accountName='+accountName+'&money1='+money1+'&money2='+money2+'&date1='+date1+'&date2='+date2+'&state='+state);
}
function doSearch(){
	if(isNaN($("#money1").val())){
		jAlert("输入金额必须为数字","提示",function(){
		    $("#money1").focus();
        });
		return false;
	}
	if(isNaN($("#money2").val())){
		jAlert("输入金额必须为数字","提示",function(){
		    $("#money2").focus();
        });
		return false;
	}
	$('#searchForm').submit();
}
function searchInfoByAccount(accountName){
	$("#accountName").val(accountName);
	doSearch();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	doSearch();
}
</script>
