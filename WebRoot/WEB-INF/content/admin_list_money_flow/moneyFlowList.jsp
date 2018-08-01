<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资金进出记录－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">资金进出记录</div>
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_money_flow/moneyFlowList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">进出ID：</div>
				            <div class="fl input"><input name="id" type="text" value="${id}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" type="text" value="${userName}" placeholder="" style="width:272px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">类型：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="dictionaryId">
				            <option value="">所有</option>
				            <option value="8" <c:if test="${dictionaryId==8}">selected</c:if>>充值存入</option>
				            <option value="9" <c:if test="${dictionaryId==9}">selected</c:if>>提款取出</option>
				            <option value="10" <c:if test="${dictionaryId==10}">selected</c:if>>注册红包</option>
				            <option value="11" <c:if test="${dictionaryId==11}">selected</c:if>>合伙人平台收益</option>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">详情：</div>
				            <div class="fl input"><input name="rechargeType" value="${rechargeType}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">金额：</div>
				            <div class="fl input"><input name="money1" type="text" value="${money1}" placeholder="" style="width:65px;" id="money1"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="money2" type="text" value="${money2}" placeholder="" style="width:65px;" id="money2"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">时间：</div>
				            <div class="fl input"><input name="date1" type="text" value="<fmt:formatDate value='${date1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="date2" type="text" value="<fmt:formatDate value='${date2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				      <td>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_money_flow/moneyFlowList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">资金进出记录</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>进出ID</th>
				    <th>真实姓名</th>
				    <th>类型</th>
				    <th>详情</th>
				    <th>备注</th>
				    <th>金额 </th>
				    <th>账户余额</th>
				    <th>时间</th>
				  </tr>
				    <c:forEach items="${detailList}" var="detail" varStatus="row">
					<tr onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td style="color:#137490">${detail.id}</td>
				    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${detail.fuUser.userName}');">${detail.fuUser.userName}</td>
				    <td>${detail.fuDictionary.name}</td>
				    <td><c:if test="${detail.isIncome&&empty detail.fuBankCard}">${detail.rechargeType}</c:if><c:if test="${detail.isIncome&&!empty detail.fuBankCard}">${detail.fuBankCard.bankName}&nbsp;${detail.fuBankCard.cardNumber}</c:if><c:if test="${!detail.isIncome}">${detail.fuBankCard.bankName}&nbsp;${detail.fuBankCard.cardNumber}</c:if></td>
				    <td>${detail.comment}</td>
				    <td><fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/></td>
				    <td><fmt:formatNumber value="${detail.accountBalanceAfter}" pattern="#,###,##0.00"/></td>
				    <td><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				  </tr>
				  </c:forEach>
				  <c:if test="${empty detailList}">
					<tr>
						<td colspan="9">
				        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
				        </td>
				  </tr>
				</c:if>
				</tbody>
				</table>
          </div>
          <div class="page">
							<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/admin_list_money_flow/moneyFlowList.htm?totalCount=${totalCount}"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
								<domi:paramTag name="id" value="${id}"/>
								<domi:paramTag name="userName" value="${userName}"/>
								<domi:paramTag name="dictionaryId" value="${dictionaryId}"/>
								<domi:paramTag name="rechargeType" value="${rechargeType}"/>
								<domi:paramTag name="money1" value="${money1}"/>
								<domi:paramTag name="money2" value="${money2}"/>
								<domi:paramTag name="date1" value="${date1}"/>
								<domi:paramTag name="date2" value="${date2}"/>
							</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
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
function searchInfoByUser(userName){
	$("#userName").val(userName);
	doSearch();
}
</script>
