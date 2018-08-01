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
<c:set var="second" value="9"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">结算</div>
            <div class="form">
	            <form id="fileUploadForm" action="" method="post" enctype="multipart/form-data">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
				  		<tbody>
				  			<tr>
				    			<td>
				     				<div class="lf_font fl">选择结算文件：</div>       
				     			</td>
				     			<td>
				     				<input type="file" name="excelfile" id="excelfile">
				     			</td>
				     			<td>
				     				<domi:privilege url="/admin_list_settlement/fileUpload.htm">
				     				<div class = "fl"><a href="javascript:void(0);" onclick="uploadFile();" class="sure fl">上传文件</a></div>
				     				</domi:privilege>
				     			</td>
				     			<td>
				     				<domi:privilege url="/admin_list_settlement/doSettlement.htm">
				     				<c:if test="${!empty batchList}"><div class = "fl"><a href="javascript:void(0);" onclick="doSettlement();" class="sure fl" id="confirm">确认结算</a></div></c:if>
				     				</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
            </div>
            
            <div class="form">
	            <form id="batchForm" action="${ctx}/admin_list_settlement/settlementList.htm" method="post" enctype="multipart/form-data">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
				  		<tbody>
				  			<tr>
				     			<td>
						            <div class="lf_font fl">批次号：</div>
						            <div class="fl input"><input id="batchNum" name="batchNum" value="${batchNum}" type="text" placeholder=""></div>
						       		<div class="clr"></div>
						        </td>
				     			<td>
				     				<div class = "fl"><a href="javascript:void(0);" onclick="queryBatch();" class="sure fl">查询批次</a></div>
				     			</td>
				     			<td>
				     				<domi:privilege url="/admin_list_settlement/deleteBatch.htm">
				     				<div class = "fl"><a href="javascript:void(0);" onclick="deleteBatch();" class="sure fl">删除批次</a></div>
				     				</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
            </div>
                       
            <div class=" yhlb_title">结算文件明细记录</div>
	            <div class="yhlb">
	            	 <table width="100%">
						  <tbody>
							  <tr>
							    <th>&nbsp;</th>
							    <th>用户ID</th>
							    <th>真实姓名</th>
							    <th>配资金额</th>
							    <th>姓名</th>
							    <th>上传日期</th>
							    <th>批次号</th>
							  </tr>
							  <c:forEach items="${batchList}" var="batch" varStatus="row">
						  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
								    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
								    <td>${batch.fuUser.id}</td>
								    <td>${batch.fuUser.userName}</td>
								    <td><fmt:formatNumber value="${empty batch.money?0:batch.money}" pattern="#,###,##0.00"/></td>
								    <td>${batch.fuUser.userName}</td>
		   						  	<c:if test="${!empty batch.createDate}"><td><fmt:formatDate value="${batch.createDate}" pattern="yyyy-MM-dd"/></td></c:if>
								    <td>${batch.batchNum}</td>
								 </tr>
							  </c:forEach>
							  <c:if test="${empty batchList}">
								  <tr>
									<td colspan="8">
							        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
							        </td>
								  </tr>
							  </c:if>
						</tbody>
					</table>
	          </div>
	          
	        <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_settlement/settlementList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
					<domi:paramTag name="batchNum" value="${batchNum}"/>
				</domi:pagination>
			</div>  
            <div class="clr"></div> 
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
            url:"${ctx}/admin_list_settlement/fileUpload.htm",            
	        secureuri:true,
	        //dataType: 'json',
	        fileElementId:'excelfile',      
	        success: function(data, status){
	        	alert("上传成功！");
	        	location.href = location.href;
	        },
	        error: function (data, status, e){
	        	alert("上传失败！");
	        	location.href = location.href;
	        }
        });
	}
}

function doSettlement(){
	$("#confirm").attr({"disabled":"disabled"});
	$.post('${ctx}/admin_list_settlement/doSettlement.htm','',function(d){
		alert("结算成功");
	    location.href = location.href;
	});
}

function queryBatch(){
	$('#batchForm').submit();
}

function deleteBatch(){
	//var batch = $("input[name='batchNum']").val();
	var data = $('#batchForm').serialize();
	$.post('${ctx}/admin_list_settlement/deleteBatch.htm',data,function(d){
		alert(d);
		//sureInfo("确定",d,"提示");
	    location.href = location.href;
	});
}

</script>
