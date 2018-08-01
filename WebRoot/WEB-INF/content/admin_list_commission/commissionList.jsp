<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－佣金兑换－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="5"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">佣金兑换</div>
    	     <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_commission/commissionList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户：</div>
				            <div class="fl input"><input id="accountName" name="accountName" type="text" value="${accountName}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">借贷用户：</div>
				            <div class="fl input"><input id="extendName" name="extendName" type="text" value="${extendName}" placeholder="" style="width:272px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				      <td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input name="programId" type="text" value="${programId}" placeholder="" ></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">结果说明：</div>
				            <div class="fl input"><input name="remark" type="text" value="${remark}" placeholder=""></div>
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
				            <div class="fl input"><input name="time1" type="text" placeholder=""  value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>"style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="time2" type="text" placeholder=""  value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="status">
				            <option value="">所有</option>
				            <option value="0" <c:if test="${status==0}">selected</c:if>>未处理</option>
				            <option value="1" <c:if test="${status==1}">selected</c:if>>已兑换</option>
				            <option value="2" <c:if test="${status==2}">selected</c:if>>拒绝</option>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_commission/commissionList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">佣金兑换</div>
            <div class="yhlb">
            	 <table width="100%">
  <tbody><tr>
    <th width="2%">&nbsp;</th>
    <th width="10%">用户</th>
    <th width="10%">借贷用户</th>
    <th width="5%">方案ID</th>
    <th width="5%">申请兑换</th>
    <th width="5%">状态</th>
    <th width="10%">审核人</th>
    <th width="20%">兑换结果说明</th>
    <th width="20%" >申请时间/审核时间</th>
    <th style=" text-align:center">操作</th>
  </tr>
    <c:forEach items="${commissionList}" var="commission" varStatus="row">
	<tr onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
    <td class="num">${row.index+1}</td>
    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${commission.fuUserByUserId.accountName}');">${commission.fuUserByUserId.accountName}</td>
    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser2('${commission.fuUserByExtendUserId.accountName}');">${commission.fuUserByExtendUserId.accountName}</td>
    <td >${commission.fuProgram.id}</td>
    <td><fmt:formatNumber value="${commission.commissionMoney}" pattern="#,###,##0.00"/></td>
    <td>${commission.status==0?'未处理':commission.status==1?'已兑换':'拒绝'}</td>
    <td>${empty commission.fuAdmin?'----':commission.fuAdmin.name}</td>
    <td >${commission.remark}</td>
    <td><fmt:formatDate value="${commission.time}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${commission.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty commission.checkTime?'----':''}</td>
    <domi:privilege url="/admin_op_commission/checkAjax.htm">
     <td style="text-align:center"><div class=" caozuo">
	    <c:if test="${commission.status==0}">
	    <a href="javascript:void(0);" onclick="setStatus('${commission.id}','1');">兑换</a><span>|</span><a href="javascript:void(0);" onclick="setStatus('${commission.id}','2');">拒绝</a>
	    </c:if></div></td>
    </domi:privilege>
  </tr>
	  </c:forEach>
            <c:if test="${empty commissionList}">
			  <tr >
				<td colspan="10">
		        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
		        </td>
		      </tr>
	      </c:if>
	</tbody></table>
          </div>
          <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
					url="${ctx}/admin_list_commission/commissionList.htm?totalCount=${totalCount}"
					totalNum="${totalCount}" curPageNum="${pageNo}"
					formId="pageForm">
					<domi:paramTag name="accountName" value="${name}"/>
					<domi:paramTag name="extendName" value="${extendName}"/>
					<domi:paramTag name="status" value="${status}"/>
					<domi:paramTag name="remark" value="${remark}"/>
					<domi:paramTag name="money1" value="${money1}"/>
					<domi:paramTag name="money2" value="${money2}"/>
					<domi:paramTag name="time1" value="${time1}"/>
					<domi:paramTag name="time2" value="${time2}"/>
					<domi:paramTag name="programId" value="${programId}"/>
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
function setStatus(id,status){
	$.fancybox.open({
		href:'${ctx}/admin_op_commission/checkAjax.htm?id='+id+'&status='+status,
		type:'ajax',
		padding:10
	})
}
function searchInfoByUser(accountName){
	$("#accountName").val(accountName);
	doSearch();
}
function searchInfoByUser2(extendName){
	$("#extendName").val(extendName);
	doSearch();
}
</script>
