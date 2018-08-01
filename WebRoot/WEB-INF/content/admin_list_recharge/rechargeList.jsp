<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－充值审核－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="7"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	    	充值审核
    	    	<domi:privilege url="/admin_op_recharge/newRechargeAjax.htm">
    	    	<a href="javascript:void(0);" onclick="addRecharge();" class="fr add">添加充值记录</a>
    	    	</domi:privilege>
    	    </div>
    	   
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_recharge/rechargeList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">充值ID：</div>
				            <div class="fl input"><input name="id" type="text" value="${id}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">手机号：</div>
				            <div class="fl input"><input name="phone" type="text"  value="${phone}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" type="text" value="${userName}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">充值类型：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="type">
				            <option value="">所有</option>
				            <option value="1" <c:if test="${type==1}">selected</c:if>>银联支付</option>
<%--				            <option value="2" <c:if test="${type==2}">selected</c:if>>网银</option>--%>
				            <option value="3" <c:if test="${type==3}">selected</c:if>>支付宝</option>
				            <option value="4" <c:if test="${type==4}">selected</c:if>>银行转账</option>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="rechargeStatus">
				            <option value="">所有</option>
							<%--<option value="0">未处理</option>--%>
                            <option value="0" <c:if test="${rechargeStatus==0}">selected</c:if>>待审核</option>
				            <option value="1" <c:if test="${rechargeStatus==1}">selected</c:if>>审核中</option>
				            <option value="2" <c:if test="${rechargeStatus==2}">selected</c:if>>同意</option>
				            <option value="3" <c:if test="${rechargeStatus==3}">selected</c:if>>拒绝</option>
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
				            <div class="fl input"><input name="money1" type="text" value="${money1}" placeholder="" style="width:65px;" id="money1"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="money2" type="text" value="${money2}" placeholder="" style="width:65px;" id="money2"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">充值时间：</div>
				            <div class="fl input"><input id="time1" name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}',dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}',dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				 <td>
				        <div class="form_cont">
				            <div class="lf_font fl">充值账号：</div>
				            <div class="fl input"><input name="rechargeAccount" value="${rechargeAccount}" type="text" placeholder=""></div>
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
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_recharge/rechargeList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">充值审核</div>
			<div class="yhlb">
			  <table width="100%">
			  <tbody><tr>
			    <th>&nbsp;</th>
			    <th>充值ID</th>
			    <th>用户名</th>
			    <th>真实姓名</th>
			    <th>充值类型</th>
			    <th>付款人</th>
			    <th>付款账号</th>
			    <th>付款金额 </th>
			    <th>订单号</th>
			    <th>付款状态 </th>
			    <th>付款时间 </th>
			    <th>收款账号</th>
			    <th>状态</th>
			    <th>审核人</th>
			    <th>审核备注</th>
			    <th>充值时间/审核时间</th>
			    <th>用户状态</th>
			    <th style=" text-align:center">操作</th>
			  </tr>
			    <s:iterator value="rechargeList" var="recharge" status="row">
				<tr onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
			    <td class="num">${row.index+1}</td>
			    <td style="color:#137490">${recharge.id}</td>
			    <td>${recharge.fuUser.accountName}</td>
			    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${recharge.fuUser.userName}');">${recharge.fuUser.userName}</td>
			    <td>${recharge.type==1?'银联支付':recharge.type==2?'网银':recharge.type==3?'支付宝':'银行转账'}</td>
			    <td >${recharge.rechargeBank}</td>
			    <td >${recharge.rechargeAccount}</td>
			    <td><fmt:formatNumber value="${recharge.rechargeMoney}" pattern="#,###,##0.00"/></td>
			    <td>${empty recharge.orderNum?'':recharge.orderNum}</td>
			    <td ><c:if test="${!empty recharge.payStatus}">${recharge.payStatus==0?'未付款':'已付款'}</c:if></td>
			    <td><fmt:formatDate value="${recharge.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			    <td><c:if test="${recharge.proceedsType==1}">小合建行：3100 1587 6150 5003 2541</c:if><c:if test="${recharge.proceedsType==2}">建设银行：6217 0000 1007 7384 314</c:if><c:if test="${recharge.proceedsType==0||recharge.proceedsType==null}">非银行转账，无收款账户</c:if></td>
			    <td>${recharge.rechargeStatus==0?'待审核':recharge.rechargeStatus==1?'审核中':recharge.rechargeStatus==2?'同意':'拒绝'}</td>
			    <td>${empty recharge.fuAdmin?'----':recharge.fuAdmin.name}</td>
			    <td>${recharge.checkRemark}</td>
			    <td><fmt:formatDate value="${recharge.rechargeTime}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${recharge.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty recharge.checkTime?'----':''}</td>
			    <td>${recharge.fuUser.state==0?'已禁用':recharge.fuUser.state==1?'未禁用':''}</td>
				    <td style="text-align:center"><div class="caozuo">
				    <domi:privilege url="/admin_op_recharge/checkRechargeAjax.htm">
					    <c:if test="${recharge.rechargeStatus==0&&recharge.type>1}"><a href="javascript:void(0);" onclick="checkRecharge(${recharge.id});">审核</a></c:if>
					    <c:if test="${recharge.rechargeStatus==1&&admin==recharge.fuAdmin}"><a href="javascript:void(0);" onclick="checkRecharge(${recharge.id});">审核</a></c:if>
					</domi:privilege>
					<domi:privilege url="/admin_op_recharge/exportExcel.htm">
					<a href="${ctx}/admin_op_recharge/exportExcel.htm?id=${id}">导出Excel</a>
					</domi:privilege>
			    </div>
			    </td>
			  </tr>
			  </s:iterator>
			  <c:if test="${empty rechargeList}">
				<tr>
					<td colspan="18">
			        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
			  </tr>
			</c:if>
			</tbody>
			</table>
          </div>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_recharge/rechargeList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="id" value="${id}"/>
				<domi:paramTag name="phone" value="${phone}"/>
				<domi:paramTag name="userName" value="${userName}"/>
				<domi:paramTag name="rechargeStatus" value="${rechargeStatus}"/>
				<domi:paramTag name="rechargeAccount" value="${rechargeAccount}"/>
				<domi:paramTag name="money1" value="${money1}"/>
				<domi:paramTag name="money2" value="${money2}"/>
				<domi:paramTag name="time1" value="${time1}"/>
				<domi:paramTag name="time2" value="${time2}"/>
				<domi:paramTag name="type"  value="${type}"/>
				<domi:paramTag name="state"  value="${state}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function addRecharge(){
	$.fancybox.open({
          href : '${ctx}/admin_op_recharge/newRechargeAjax.htm',
          type : 'ajax',
          padding : 10
	});
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
function checkRecharge(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_recharge/checkRechargeAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	doSearch();
}
</script>
