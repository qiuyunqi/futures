<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－后台账号管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                  排行榜显示数据
    	    	<domi:privilege url="/admin_op_indexdata/newAddIndexDataAjax.htm"><a href="javascript:void(0);" onclick="addData();" class="fr add" style="margin-left: 10px;">添加</a></domi:privilege>
    	    </div>
          <div class=" yhlb_title">排行榜显示数据：</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>交易类型</th>
				    <th>交易对象</th>
				    <th>详情</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${FuIndexData}" var="fuIndexData" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
				    <td class="num">${row.index+1}</td>
				    <td style="color:#137490">${fuIndexData.userName}</td>
				    <td>${fuIndexData.transactionType==1?'买入':fuIndexData.transactionType==2?'申请':'盈利'}</td>
				    <td>${fuIndexData.transactionObject}</td>
				    <td><c:if test="${fuIndexData.transactionType==1}">
				    		${fuIndexData.userName} 买入 ${fuIndexData.transactionObject} 公司 ${fuIndexData.transactionNum}手
				    	</c:if>
				    	<c:if test="${fuIndexData.transactionType==2}">
				    		${fuIndexData.userName} 申请 ${fuIndexData.applyMoney} 元 
				    	</c:if>				    	
				    	<c:if test="${fuIndexData.transactionType==3}">
				    		${fuIndexData.userName} 斩获 ${fuIndexData.getGain}% 盈利 ${fuIndexData.getMoney} 元
				    	</c:if>
				    </td>
					<td><domi:privilege url="/admin_op_indexdata/delIndexDataListAjax.htm"><a href="javascript:void(0);"  onclick="delServer(${fuIndexData.id });">删除</a></domi:privilege></td>
				  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty FuIndexData}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_indexdata/IndexDataList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
			</domi:pagination>
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

function addData(){ 
	$.fancybox.open({
          href : '${ctx}/admin_op_indexdata/newAddIndexDataAjax.htm',
          type : 'ajax',
          padding : 10
	});
}


function delServer(id){
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
        return null;
    }
	$.post("${ctx}/admin_op_indexdata/delIndexDataListAjax.htm?id="+id,null,function(d){
		  if(d==1){
              sureInfo("确定","删除成功","提示");
              location.href = location.href;
          }
	});
}


</script>
