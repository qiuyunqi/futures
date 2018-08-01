<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－方案资金明细－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="5"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">方案资金明细<domi:privilege url="/admin_list_money_program/exportExcelAjax.htm"><a href="javascript:void(0);" onclick="exportInfo();" class="fr add">导出</a></domi:privilege></div>
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_money_program/moneyFlowList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">明细ID：</div>
				            <div class="fl input"><input id="id" name="id" type="text" value="${id}" placeholder=""></div>
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
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input id="programId" name="programId" type="text" value="${programId}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">类型：</div>
				            <div class="fl select">
				            <select style="width:178px;" id="dictionaryId" name="dictionaryId">
				            <option value="">所有</option>
				            <option value="28" <c:if test="${dictionaryId==28}">selected</c:if>>方案支出</option>
				            <option value="29" <c:if test="${dictionaryId==29}">selected</c:if>>利润提取</option>
				            <option value="30" <c:if test="${dictionaryId==30}">selected</c:if>>方案出金</option>
				            <option value="31" <c:if test="${dictionaryId==31}">selected</c:if>>加保证金</option>
				            <option value="32" <c:if test="${dictionaryId==32}">selected</c:if>>方案结算</option>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">金额：</div>
				            <div class="fl input"><input id="money1" name="money1" type="text" value="${money1}"  placeholder="" style="width:65px;" id="money1"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="money2" name="money2" type="text" value="${money2}"  placeholder="" style="width:65px;" id="money2"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">时间：</div>
				            <div class="fl input"><input id="date1" name="date1" type="text" placeholder="" value="<fmt:formatDate value='${date1}' pattern='yyyy-MM-dd'/>" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="date2" name="date2" type="text" placeholder="" value="<fmt:formatDate value='${date2}' pattern='yyyy-MM-dd'/>" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">备注：</div>
				            <div class="fl input"><input id="comment" name="comment" value="${comment}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_money_program/moneyFlowList.htm" class="remove" style="margin-left:30px;">清空</a></div>
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
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">方案资金明细</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>明细ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>方案ID</th>
				    <th>众期账号</th>
				    <th>类型</th>
				    <th>金额 </th>
				    <th>备注</th>
				    <th>时间</th>
				    <th>操作</th>
				  </tr>
				    <c:forEach items="${detailList}" var="detail" varStatus="row">
					<tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td>${detail.id}</td>
				    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByAccount('${detail.fuUser.accountName}');">${detail.fuUser.accountName}</td>
				    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${detail.fuUser.userName}');">${detail.fuUser.userName}</td>
				    <td>${detail.fuProgram.id}</td>
				    <td>${detail.fuProgram.fuServer.id}_${detail.fuProgram.tradeAccount}</td>
				    <td>${detail.fuDictionary.name}</td>
				    <td><fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/></td>
				    <td>${detail.comment}</td>
				    <td><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td><domi:privilege url="/admin_list_money_program/exportExcelAjax.htm"><a href="${ctx}/admin_list_money_program/exportExcel.htm?id=${detail.id}" >导出Excel</a></domi:privilege></td>
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
				url="${ctx}/admin_list_money_program/moneyFlowList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="id" value="${id}"/>
				<domi:paramTag name="programId" value="${programId}"/>
				<domi:paramTag name="accountName" value="${accountName}"/>
				<domi:paramTag name="userName" value="${userName}"/>
				<domi:paramTag name="dictionaryId" value="${dictionaryId}"/>
				<domi:paramTag name="comment" value="${comment}"/>
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
//导出
function exportInfo(){
	var id=$("#id").val();
	var userName=$("#userName").val();
	var programId=$("#programId").val();
	var dictionaryId=$("#dictionaryId").val();
    var money1=$("#money1").val();
    var money2=$("#money2").val();
    var date1=$("#date1").val();
    var date2=$("#date2").val();
    var comment=$("#comment").val();
	$.fancybox.open({
			href : '${ctx}/admin_list_money_program/exportExcelAjax.htm?id='+id+'&userName='+userName
			+'&programId='+programId+'&dictionaryId='+dictionaryId+'&money1='+money1+'&money2='+money2
			+'&date1='+date1+'&date2='+date2+'&comment='+comment,
			type : 'ajax'
	});
}

function doSearch(){
	if(isNaN($("#money1").val())||isNaN($("#money2").val())){
		sureInfo("确定","输入金额必须为数字","提示");
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
