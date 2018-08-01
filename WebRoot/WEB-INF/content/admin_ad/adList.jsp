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
	.yhlb table tr img {width: 160px; height:90px; }
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="4"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                     后台广告位管理列表
    	        <domi:privilege url="/admin_ad/saveAdAjax.htm">
    	    	<a href="javascript:void(0);" onclick="addAd('');" class="fr add">添加</a>
    	    	</domi:privilege>
    	    </div>
            <%-- <div class="form">
                <form id="searchForm" action="${ctx}/admin_ad/adList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody><tr>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">账号：</div>
					            <div class="fl input"><input name="account" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">真实姓名：</div>
					            <div class="fl input"><input name="name" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl"></div>
					            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_admin/adminList.htm" class="remove" style="margin-left:30px;">清空</a></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					  </tr>
					</tbody>
				</table>
				</form>
            </div> --%>
          <div class=" yhlb_title">广告列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>编号</th>
				    <th>广告图片</th>
				    <th>图片属性</th>
				    <th>广告外链</th>
				    <th>广告位置</th>
				    <th>顺序</th>
				    <th style=" text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${adList}" var="ad" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td><img src="${ad.imageUrl}"/></td>
				    <td>${ad.imageAttr}</td>
				    <td>${ad.hrefUrl}</td>
				    <td>${ad.posName}</td>
				    <td>${ad.orderNum}</td>
				    <td style="text-align:center">
				    	<div class=" caozuo">
				    	<domi:privilege url="/admin_ad/delAdAjax.htm">
					    <a href="javascript:void(0);" onclick="delAd('${ad.id}', '${ad.isDelete}');">
					    	<c:if test="${ad.isDelete == 0}">删除</c:if>
					   		<c:if test="${ad.isDelete == 1}">恢复</c:if>
					    </a>
					    <span>|</span>
					    </domi:privilege>
					    <domi:privilege url="/admin_ad/saveAdAjax.htm">
					    <a href="javascript:void(0);" onclick="addAd('${ad.id}');">修改</a>
					    </domi:privilege>
					    </div>
				    </td>
				  </tr>
				  </c:forEach>
				   <c:if test="${empty adList}">
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

function addAd(adId){
	$.fancybox.open({
          href : "${ctx}/admin_ad/newAdAjax.htm?id="+adId,
          type : 'ajax',
          padding : 10
	});
}

function delAd(adId, delStatus){
	if(delStatus == 0){
		var alterMessage = "您确定要删除吗？";
	}else {
		var alterMessage = "您确定要恢复吗？";
	}
	jConfirm(alterMessage, "删除提示", function(res){
		if(res){
			$.get('${ctx}/admin_ad/delAdAjax.htm?id='+adId+'&isDelete='+delStatus, null, function(d){
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
