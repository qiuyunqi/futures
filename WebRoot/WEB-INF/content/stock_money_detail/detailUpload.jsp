<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套者流水上传－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">解套者流水上传</div>
            <div class="form">
	            <form id="fileUploadForm" action="" method="post" enctype="multipart/form-data">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
				  		<tbody>
				  			<tr>
				    			<td>
				     				<div class="lf_font fl">选择文件：</div>       
				     			</td>
				     			<td>
				     				<input type="file" name="excelfile" id="excelfile">
				     			</td>
				     			<td>
				     				<domi:privilege url="/stock_money_detail/fileUpload.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="uploadFile();" class="sure fl">流水上传</a></div>
									</domi:privilege>
				     			</td>
				     			<td>
				     				<domi:privilege url="/stock_money_detail/detailProcess.htm">
										<c:if test="${!empty batchList}">
					     					<div class = "fl"><a href="javascript:void(0);" onclick="processDetail();" class="sure fl" id="confirm">流水处理</a></div>
				     					</c:if>
									</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
            </div>
            <div class="form">
	            <form id="batchForm" action="${ctx}/stock_money_detail/detailUpload.htm" method="post" enctype="multipart/form-data">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
				  		<tbody>
				  			<tr>
				     			<td>
						            <div class="lf_font fl">批次号：</div>
						            <div class="fl input"><input id="batchId" name="batchId" value="${batchId}" type="text" placeholder=""></div>
						       		<div class="clr"></div>
						        </td>
				     			<td>
				     				<domi:privilege url="/stock_money_detail/detailUpload.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="queryBatch();" class="sure fl">查询批次</a></div>
									</domi:privilege>
				     			</td>
				     			<td>
				     				<domi:privilege url="/stock_money_detail/deleteBatch.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="deleteBatch();" class="sure fl">删除批次</a></div>
									</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
            </div>
            <div class=" yhlb_title">股票流水临时记录</div>
	            <div class="yhlb">
	            	 <table width="100%">
						  <tbody>
							  <tr>
							    <th>&nbsp;</th>
							    <th>日期</th>
							    <th>手机号</th>
							    <th>资金账号</th>
							    <th>今日盈利(元)</th>
							    <th>本周管理费(元)</th>
							    <th>备注</th>
							    <th>批次号</th>
							  </tr>
							  <c:forEach items="${batchList}" var="batch" varStatus="row">
						  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
								    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
								    <td><fmt:formatDate value="${batch.tradetime}" pattern="yyyy-MM-dd"/></td>
								    <c:if test="${empty batch.fuUser}">
								    	<td style="color: red">
									    	${batch.phone}(用户不存在)
									    </td>	
								    </c:if>
								    <c:if test="${!empty batch.fuUser}">
								    	<td>${batch.phone}</td>	
								    </c:if>
								    <c:if test="${empty batch.fuStockAccount}">
								    	<td style="color: red">
									    	${batch.capitalAccount}(资金账户不存在)
									    </td>	
								    </c:if>
								    <c:if test="${!empty batch.fuStockAccount}">
								    	<td>${batch.capitalAccount}</td>	
								    </c:if>
								    <td style=" text-align:right">${batch.nowProfit}</td>
								    <td style=" text-align:right">${batch.manageFee}</td>
								    <td>${batch.remark}</td>
								    <td>${batch.batchNum}</td>
								 </tr>
							  </c:forEach>
							  <c:if test="${empty batchList}">
								  <tr>
									<td colspan="8">
							        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">暂无记录</p></div>
							        </td>
								  </tr>
							  </c:if>
						</tbody>
					</table>
	          </div>
	          
	    <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/stock_money_detail/detailUpload.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
				<domi:paramTag name="batchNum" value="${batchNum}"/>
			</domi:pagination>
		</div>  
	          
        </div>
    </div>
</div>
</body>
</html>
<script>
function uploadFile(){
	var fileText = $('input[type="file"]').val();
	if(fileText==''|| fileText==null){
	    alert('请先选择文件！');
	    return false;
	}else{
		$.ajaxFileUpload({
            url:"${ctx}/stock_money_detail/fileUpload.htm",            
	        secureuri:true,
	        dataType: 'text',
	        fileElementId:'excelfile',      
	        success: function(data, status){
	        	if(data == "success"){
	        		alert("上传成功！");
	        	}else{
					alert("上传失败！");	        		
	        	}
	        	location.href = location.href;
	        },
	        error: function (data, status, e){
	        	alert("上传失败！");
	        	location.href = location.href;
	        }
        });
	}
}
function processDetail(){
	$("#confirm").attr({"disabled":"disabled"});
	$.post('${ctx}/stock_money_detail/detailProcess.htm','',function(data){
		if(data == "success"){
			alert("处理成功！");
		}else{
			alert("处理失败！");
		}
	    location.href = location.href;
	});
}
function queryBatch(){
	$('#batchForm').submit();
}
function deleteBatch(){
	var data = $('#batchForm').serialize();
	$.post('${ctx}/stock_money_detail/deleteBatch.htm',data,function(d){
		alert(d);
	    location.href = location.href;
	});
}

</script>
