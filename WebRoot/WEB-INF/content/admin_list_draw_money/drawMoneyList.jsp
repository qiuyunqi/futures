<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－待提款审核－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">提款审核</div>
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_draw_money/drawMoneyList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">提款ID：</div>
				            <div class="fl input"><input name="id" type="text"  value="${id}" placeholder=""></div>
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
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select">
				            <select style="width:178px;" name="status">
				            <option value="">所有</option>
<%--				            <option value="0">未处理</option>--%>
                            <option value="0"  <c:if test="${status==0}">selected</c:if>>待审核</option>
				            <option value="1"  <c:if test="${status==1}">selected</c:if>>审核中</option>
				            <option value="2"  <c:if test="${status==2}">selected</c:if>>同意</option>
				            <option value="3"  <c:if test="${status==3}">selected</c:if>>拒绝</option>
				            </select>
				            </div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">备注：</div>
				            <div class="fl input"><input name="comment" value="${comment}" type="text" placeholder=""></div>
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
				            <div class="lf_font fl">提款时间：</div>
				            <div class="fl input"><input id="time1" name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}',dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}',dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
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
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_draw_money/drawMoneyList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">提款列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th width="2%">&nbsp;</th>
				    <th width="5%">提款ID</th>
				    <th width="8%">用户名</th>
				    <th width="5%">真实姓名</th>
				    <th width="30%">银行</th>
				    <th width="5%">金额 </th>
				    <th width="5%">状态</th>
				    <th width="5%">审核人</th>
				    <th width="10%">审核备注</th>
				    <th width="15%">提款时间/审核时间</th>
				    <th>用户状态</th>
				    <th style=" text-align:center" >操作</th>
				  </tr>
				    <s:iterator value="drawList" var="draw"  status="row">
					<tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td style="color:#137490">${draw.id}</td>
				    <td>${draw.fuUser.accountName}</td>
				    <td style="color:#137490" onclick="searchInfoByUser('${draw.fuUser.userName}');">${draw.fuUser.userName}</td>
				    <td>${draw.fuBankCard.bankName}&nbsp;&nbsp;&nbsp;${draw.fuBankCard.bankAddress}&nbsp;&nbsp;&nbsp;${draw.fuBankCard.cardNumber}</td>
				    <td><fmt:formatNumber value="${draw.drawMoney}" pattern="#,###,##0.00"/></td>
				    <td>${draw.status==0?'待审核':draw.status==1?'审核中':draw.status==2?'同意':'拒绝'}</td>
				    <td>${empty draw.fuAdmin?'----':draw.fuAdmin.name}</td>
				    <td>${draw.comment}</td>
				    <td><fmt:formatDate value="${draw.drawTime}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${draw.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty draw.checkTime?'----':''}</td>
					<td>${draw.fuUser.state==0?'已禁用':draw.fuUser.state==1?'未禁用':''}</td>
					<td style="text-align:center"><div class=" caozuo">
				    <domi:privilege url="/admin_op_draw_money/checkDrawMoneyAjax.htm">
					    <c:if test="${draw.status==0}"><a href="javascript:void(0);" onclick="setStatus(${draw.id});">审核</a></c:if>
					    <c:if test="${draw.status==1&&admin==draw.fuAdmin}"><a href="javascript:void(0);" onclick="setStatus(${draw.id});">审核</a></c:if>
				    </domi:privilege>
				    <domi:privilege url="/admin_op_draw_money/exportExcel.htm">
				    <a href="${ctx}/admin_op_draw_money/exportExcel.htm?id=${id}">导出Excel</a>
				    </domi:privilege>
				    </div>
					</td>
			 		</tr>
  					</s:iterator>
  	<c:if test="${empty drawList}">
   		<tr >   
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
				url="${ctx}/admin_list_draw_money/drawMoneyList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
				<domi:paramTag name="id" value="${id}"/>
				<domi:paramTag name="phone" value="${phone}"/>
				<domi:paramTag name="userName" value="${userName}"/>
				<domi:paramTag name="status" value="${status}"/>
				<domi:paramTag name="comment" value="${comment}"/>
				<domi:paramTag name="money1" value="${money1}"/>
				<domi:paramTag name="money2" value="${money2}"/>
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

function setStatus(id){
	/* var data=$("#searchForm").serialize();
	$.post("${ctx}/admin_op_draw_money/checkDrawMoneyAjax.htm?id="+id,data,function(d){
		if(d==-1){
			sureInfo("确定","此记录被其他用户审核中！","提示");
			location.href=location.href;
            return;
		}else{ */
			$.fancybox.open({//弹窗
				href:'${ctx}/admin_op_draw_money/checkDrawMoneyAjax.htm?id='+id,
				type:'ajax',
				padding:10
			});
	/* 	}
	}); */
}

function searchInfoByUser(userName){
	$("#userName").val(userName);
	doSearch();
}
</script>
