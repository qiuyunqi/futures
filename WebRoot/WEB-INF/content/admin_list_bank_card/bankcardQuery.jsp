<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户认证管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="11"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">银行卡查询</div>
            <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_bank_card/bankcardQuery.htm" method="post">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">开户人：</div>
						            <div class="fl input"><input id="accountName" name="accountName" value="${accountName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">银行卡卡号：</div>
						            <div class="fl input"><input id="cardNumber" name="cardNumber" value="${cardNumber}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryBankCard();" class="search">查询</a><a href="${ctx}/admin_list_bank_card/bankcardQuery.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>
            
	          <div class=" yhlb_title">银行卡列表</div>
	            <div class="yhlb">
	            	 <table width="100%">
					  <tbody>
					  <tr>
					    <th>&nbsp;</th>
					    <th>记录ID</th>
					    <th>开户人</th>
					    <th>开户行</th>
					    <th>开户行所在地</th>
					    <th>银行卡卡号</th>
					    <th>网点支行名称</th>
					    <th>操作</th>
					  </tr>
					  <c:forEach items="${bankCardList}" var="bankCard" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${bankCard.id}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${bankCard.accountName}');">${bankCard.accountName}</td>
						    <td>${bankCard.bankName}</td>
						    <td>${bankCard.bankAddress}</td>
						    <td>${bankCard.cardNumber}</td>
						    <td>${bankCard.bankBranchName}</td>
					  	    <td><domi:privilege url="/admin_op_bank_card/updateCardAjax.htm"><a href="javascript:void(0);" onclick="checkInfo(${bankCard.id});" >修改</a></domi:privilege></td>
						 </tr>
					  </c:forEach>
					  <c:if test="${empty bankCardList}">
						  <tr>
							<td colspan="8">
					        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
					        </td>
						  </tr>
					  </c:if>
					</tbody></table>
	
	          </div>
            
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_bank_card/bankcardQuery.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
					<domi:paramTag name="accountName" value="${accountName}"/>
					<domi:paramTag name="cardNumber" value="${cardNumber}"/>
				</domi:pagination>
			</div>
             <div class="clr"></div> 
            
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryBankCard(){
	$('#searchForm').submit();
}
function searchInfoByUser(accountName){
	$("#accountName").val(accountName);
	$('#searchForm').submit();
}
function checkInfo(id){
	$.fancybox.open({
        href : "${ctx}/admin_op_bank_card/updateCardAjax.htm?id="+id,
        type : 'ajax',
        padding : 10
	});
}
</script>
