<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－余券宝收益龙虎榜－安全配资服务平台</title>
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
    	        余券宝收益龙虎榜<domi:privilege url="/admin_list_wxyqb/newYqbRankAjax.htm"><a href="javascript:void(0);" onclick="addYqbRank('');" class="fr add">添加</a></domi:privilege>
    	    </div>
    	    
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_wxyqb/yqbRank.htm" method="post">
	            	<table width="80%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						  	<td>
						        <div class="form_cont">
						            <div class="lf_font fl">股票代码：</div>
						            <div class="fl input"><input id="stockName" name="code" value="${code}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">股票名称：</div>
						            <div class="fl input"><input id="stockName" name="stockName" value="${stockName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">团队名称：</div>
						            <div class="fl input"><input id="transactionName" name="transactionName" value="${transactionName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryYqbRank();" class="search">查询</a><a href="${ctx}/admin_list_wxyqb/yqbRank.htm" class="remove" style="margin-left:30px;">清空</a></div>
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
				     				<domi:privilege url="/admin_list_wxyqb/yqbRankReset.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="yqbRankReset();" class="sure fl">余劵宝收益清空</a></div>
									</domi:privilege>
				     			</td>
				     			<td>
				     				<domi:privilege url="/admin_list_wxyqb/yqbRankUpload.htm">
										<div class = "fl"><a href="javascript:void(0);" onclick="yqbRankUpload();" class="sure fl">余券宝收益上传</a></div>
									</domi:privilege>
				     			</td>
				  			</tr>
						</tbody>
					</table>
				</form> 
            </div>
            
          <div class=" yhlb_title">余券宝收益龙虎榜列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>股票代码</th>
				    <th>股票名称</th>
				    <th>月收益</th>
				    <th>团队名称</th>
				    <th>排序</th>
				    <th>热度</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${yqbRankList}" var="rank" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${rank.code}</td>
					    <td>${rank.stockName}</td>
					    <td>${rank.monthProfit}</td>
					    <%-- <td><fmt:formatNumber value="${empty rank.monthProfit?0:rank.monthProfit*100}" pattern="#,###,##0.00"/>%</td>
					    <td><fmt:formatNumber value="${empty rank.removes?0:rank.removes}" pattern="#,###,##0.00"/>%</td> --%>
					    <td>${rank.transactionName}</td>
					    <td>${rank.serialNo}</td>
					    <td><c:forEach begin="1" end="${rank.heat}"><img src="../images_yqb/star.png"/></c:forEach></td>
					 	<td><domi:privilege url="/admin_list_wxyqb/newYqbRankAjax.htm"><a href="javascript:void(0);" onclick="addYqbRank(${rank.id });" >编辑</a></domi:privilege>&nbsp;|&nbsp;<domi:privilege url="/admin_list_wxyqb/delRank.htm"><a href="javascript:void(0);"  onclick="delRank(${rank.id });">删除</a></domi:privilege></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty yqbRankList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_wxyqb/yqbRank.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="code" value="${code}"/>
		        <domi:paramTag name="stockName" value="${stockName}"/>
		        <domi:paramTag name="transactionName" value="${transactionName}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryYqbRank(){
	$('#searchForm').submit();
}

function addYqbRank(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_wxyqb/newYqbRankAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function delRank(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_list_wxyqb/delRank.htm?id="+id,null,function(d){
        if(d==1){
            sureInfo("确定","删除成功","提示");
            location.href = location.href;
        }
	});
} 

function yqbRankUpload(){
	jAlert("正在上传，请稍候！","提示",function(){});
	var fileText = $('input[type="file"]').val();
	if(fileText==''|| fileText==null){
	    alert('请先选择文件！');
	    return false;
	}else{
		$.ajaxFileUpload({
            url:"${ctx}/admin_list_wxyqb/yqbRankUpload.htm",            
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

function yqbRankReset(){
	$.post("${ctx}/admin_list_wxyqb/yqbRankReset.htm",null,function(data){
		jAlert("清空成功！","提示",function(){
			location.href=location.href;
		});
	})
}
</script>
