<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－交易员收益排行榜－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	        交易员收益排行榜<domi:privilege url="/admin_list_wxyqb/newTransRankAjax.htm"><a href="javascript:void(0);" onclick="addTransRank('');" class="fr add">添加</a></domi:privilege>
    	    </div>
    	    
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_wxyqb/transRank.htm" method="post">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">交易员：</div>
						            <div class="fl input"><input id="transactionName" name="transactionName" value="${transactionName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">评级：</div>
						            <div class="fl input"><input id="rating" name="rating" value="${rating}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryTransRank();" class="search">查询</a><a href="${ctx}/admin_list_wxyqb/transRank.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	  
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
				     				<domi:privilege url="/admin_list_wxyqb/transRankUpload.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="transRankUpload();" class="sure fl">交易员收益上传</a></div>
									</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
          </div>
    	    
          <div class=" yhlb_title">交易员收益列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>交易员</th>
				    <th>月收益</th>
				    <th>管理规模</th>
				    <th>交易团队</th>
				    <th>评级</th>
				    <th>排序</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${transRankList}" var="trans" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${trans.transactionName}</td>
					    <td>${trans.monthProfit}</td>
					    <td>${trans.managerScale}</td>
					    <td>${trans.fuTransaction.name}</td>
					    <%-- <td><fmt:formatNumber value="${empty trans.monthProfit?0:trans.monthProfit*100}" pattern="#,###,##0.00"/>%</td>
					    <td><fmt:formatNumber value="${empty trans.managerScale?0:trans.managerScale}" pattern="#,###,##0.00"/></td> --%>
					    <td class="starsYqb">
					    	<c:if test="${trans.fuTransaction.rating == 1}">
						    	<img src="../images_yqb/star.png"/>
						   	</c:if>
						   	<c:if test="${trans.fuTransaction.rating == 2}">
						    	<img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/>
						   	</c:if>
						   	<c:if test="${trans.fuTransaction.rating == 3}">
								<img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/>
						   	</c:if>
						   	<c:if test="${trans.fuTransaction.rating == 4}">
								<img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/>
						   	</c:if>
						   	<c:if test="${trans.fuTransaction.rating == 5}">
								<img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/><img src="../images_yqb/star.png"/>
						   	</c:if>
					    </td>
					    <td>${trans.serialNo}</td>
					 	<td><domi:privilege url="/admin_list_wxyqb/newTransRankAjax.htm"><a href="javascript:void(0);" onclick="addTransRank(${trans.id });" >编辑</a></domi:privilege>&nbsp;|&nbsp;<domi:privilege url="/admin_list_wxyqb/delTransRank.htm"><a href="javascript:void(0);"  onclick="delTransRank(${trans.id });">删除</a></domi:privilege></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty transRankList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_wxyqb/transRank.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="transactionName" value="${transactionName}"/>
		        <domi:paramTag name="rating" value="${rating}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryTransRank(){
	$('#searchForm').submit();
}

function addTransRank(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_wxyqb/newTransRankAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function delTransRank(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_list_wxyqb/delTransRank.htm?id="+id,null,function(d){
        if(d==1){
            sureInfo("确定","删除成功","提示");
            location.href = location.href;
        }
	});
} 

function transRankUpload(){
	var fileText = $('input[type="file"]').val();
	if(fileText==''|| fileText==null){
	    alert('请先选择文件！');
	    return false;
	}else{
		$.ajaxFileUpload({
            url:"${ctx}/admin_list_wxyqb/transRankUpload.htm",            
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
</script>
