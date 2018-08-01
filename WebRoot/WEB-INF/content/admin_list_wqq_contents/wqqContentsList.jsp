<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title} -直播抽奖列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">微期权目录<domi:privilege url="/admin_op_wqq_contents/addContents.htm"><a href="javascript:void(0);" class="fr add" onclick="addContents('')">添加</a></domi:privilege></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_wqq_contents/wqqContentsList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">名称：</div>
				            <div class="fl input"><input id="name" name="name" value="${name}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">创建时间：</div>
				            <div class="fl input"><input id="createTime1" name="createTime1" value="<fmt:formatDate value="${createTime1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="createTime2" name="createTime2" value="<fmt:formatDate value="${createTime2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'createTime1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select"><select style="width:178px;" id="state" name="state">
				            	<option value="">全部</option>
				            	<option <c:if test="${state==0}">selected="selected"</c:if> value="0">未上线</option>
				            	<option <c:if test="${state==1}">selected="selected"</c:if> value="1">已上线</option>
				            	<option <c:if test="${state==2}">selected="selected"</c:if> value="2">已下线</option>
				            	<option <c:if test="${state==3}">selected="selected"</c:if> value="3">已承兑</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_wqq_contents/wqqContentsList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">微期权列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>ID</th>
				    <th>名称</th>
				    <th>承兑系数</th>
				    <th>创建人</th>
				    <th>创建时间</th>
				    <th>承兑人</th>
				    <th>承兑时间</th>
				    <th>上线人</th>
				    <th>上线时间</th>
				    <th>下线人</th>
				    <th>下线时间</th>
				    <th>标的物</th>
				    <th>涨跌幅度</th>
				    <th>开始日</th>
				    <th>结束日</th>
				    <th>开始日收盘价</th>
				    <th>结束日收盘价</th>
				    <th>状态</th>
				    <th style="text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${wqqContentslist}" var="contents" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${contents.id}</td>
						    <td>${empty contents.name?'':contents.name}</td>
						    <td><fmt:formatNumber value="${empty contents.acceptFactor?'':contents.acceptFactor}" pattern="#,###,##0.00"/></td>
						    <td>${contents.createAdmin.name}</td>
						    <td><fmt:formatDate value="${contents.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${empty contents.acceptAdmin?'':contents.acceptAdmin.name}</td>
						    <td><fmt:formatDate value="${contents.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${empty contents.upAdmin?'':contents.upAdmin.name}</td>
						    <td><fmt:formatDate value="${contents.upTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${empty contents.downAdmin?'':contents.downAdmin.name}</td>
						    <td><fmt:formatDate value="${contents.downTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${contents.OOI}</td>
						    <td>${contents.updownRegion}</td>
						    <td><fmt:formatDate value="${contents.beginTime}" pattern="yyyy-MM-dd"/></td>
						    <td><fmt:formatDate value="${contents.endTime}" pattern="yyyy-MM-dd"/></td>
						    <td>${contents.beginPrice}</td>
						    <td>${contents.endPrice}</td>
						    <td>${contents.state==0?'未上线':contents.state==1?'已上线':contents.state==2?'已下线':'已承兑'}</td>
						 	<td style="text-align:center"><div class="caozuo">
						 	<domi:privilege url="/admin_op_wqq_contents/addContents.htm">
						 	<a href="javascript:void(0);" onclick="addContents('${contents.id}')">修改</a>
						 	</domi:privilege>
						 	<c:if test="${contents.state==0}">
						 	<domi:privilege url="/admin_op_wqq_contents/saveUpdateState.htm">
						 	<a href="javascript:void(0);" onclick="updateState(${contents.id},1)">上线</a>
						 	</domi:privilege>
						 	</c:if>
						 	<c:if test="${contents.state==1}">
						 	<domi:privilege url="/admin_op_wqq_contents/saveUpdateState.htm">
						 	<a href="javascript:void(0);" onclick="updateState(${contents.id},2)">下线</a>
						 	</domi:privilege>
						 	</c:if>
						 	<c:if test="${contents.state==2||contents.state==3}">
						 	<domi:privilege url="/admin_op_wqq_contents/wqqFactorAjax.htm">
						 	<a href="javascript:void(0);" onclick="incomeAccept(${contents.id},${contents.state})">收益承兑</a>
						 	</domi:privilege>
						 	</c:if>
						 	</div></td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty wqqContentslist}">
				  <tr>
					<td colspan="20">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_wqq_contents/wqqContentsList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="name" value="${name}"/>
						<domi:paramTag name="createTime1" value="${createTime1}"/>
						<domi:paramTag name="createTime2" value="${createTime2}"/>
						<domi:paramTag name="state" value="${state}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function addContents(id){
	$.fancybox.open({
        href : "${ctx}/admin_op_wqq_contents/addContents.htm?id="+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
function updateState(id,state){
	jConfirm("确认操作？","操作提示",function(res){
		if(res){
			$.post("${ctx}/admin_op_wqq_contents/saveUpdateState.htm?id="+id+"&state="+state,null,function(d){
				if(d==-1){
					jAlert("已经存在上线的微期权产品！","提示",function(){
						location.href=location.href;
		        	});
		        	return false;
				}
				jAlert("操作成功！","提示",function(){
					location.href=location.href;
		        });
			});
		}
	});
}

function incomeAccept(id,state){
	if(state==0){
		sureInfo("确定","该产品暂未上线！","提示");
		return false;
	}
	if(state==1){
		sureInfo("确定","请先将产品下线！","提示");
		return false;
	}
	$.fancybox.open({
        href : '${ctx}/admin_op_wqq_contents/wqqFactorAjax.htm?id='+id+'&state='+state,
        type : 'ajax',
        padding : 10
	});
}
</script>
