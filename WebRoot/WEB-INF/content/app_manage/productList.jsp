<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－管理员管理－安全配资服务平台</title>
<%@include file="../common/cssback.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	.yhlb table tr th {text-align: center;}
	.yhlb table tr td {text-align: center;}
	.yhlb table tr img {width: 100px; height:100px; }
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="4"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                     后台产品位管理列表
    	        <domi:privilege url="/app_manage/toChangeOrder.htm">
    	    	<a href="javascript:void(0);" onclick="toAddProduct('');" class="fr add">添加</a>
    	    	</domi:privilege>
    	    </div>
          <div class=" yhlb_title">产品列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>编号</th>
				    <th>产品名称</th>
				    <th>产品描述</th>
				    <th>产品收益</th>
				    <th>产品收益描述</th>
				    <th>产品的logo</th>
				    <th>产品顺序</th>
				    <th style=" text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${productList}" var="product" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td>${product.name}</td>
				    <td>${product.description}</td>
				    <td>${product.profit}</td>
				    <td>${product.profitDesc}</td>
				    <td><img src="${product.icon}"/></td>
				    <td>${product.orderNum}</td>
				    <td style="text-align:center">
				    	<div class=" caozuo">
				    	<domi:privilege url="/app_manage/isDisplay.htm">
					    <a href="javascript:void(0);" onclick="delProduct('${product.id}', '${product.isDelete}');">
					    	<c:if test="${product.isDelete == 0}">删除</c:if>
					   		<c:if test="${product.isDelete == 1}">恢复</c:if>
					    </a>
					    <span>|</span>
					    </domi:privilege>
					    <domi:privilege url="/app_manage/toChangeOrder.htm">
					    <a href="javascript:void(0);" onclick="toAddProduct('${product.id}');">修改</a>
					    </domi:privilege>
					    </div>
				    </td>
				  </tr>
				  </c:forEach>
				   <c:if test="${empty productList}">
				  <tr >
					<td colspan="8">
			        	<div class="empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
			      </tr>
			      </c:if>
				</tbody>
				</table>
          </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function doSearch(){
	$('#searchForm').submit();
}

function toAddProduct(productId){
	$.fancybox.open({
          href : "${ctx}/app_manage/toChangeOrder.htm?productId="+productId,
          type : 'ajax',
          padding : 10
	});
}

function delProduct(productId, delStatus){
	if(delStatus == 0){
		var alterMessage = "您确定要删除吗？";
	}else {
		var alterMessage = "您确定要恢复吗？";
	}
	jConfirm(alterMessage, "删除提示", function(res){
		if(res){
			$.get('${ctx}/app_manage/isDisplay.htm?productId='+productId+'&delStatus='+delStatus, null, function(d){
			   if(d != 1){
	                sureInfo("确定","数据不正确请联系管理员","提示");
	                return null;
	            }
				location.href = location.href;
			})
		}
	});
}
</script>
