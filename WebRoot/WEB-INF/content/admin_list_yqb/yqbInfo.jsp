<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－余劵宝－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    余劵宝
    	       <a href="javascript:void(0);" onclick="addYqb('');" class="fr add">新增</a>
    	    </div>
          <div class=" yhlb_title"></div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>年化股票市值</th>
				    <th>年化可用资金市值</th>
				    <th>累计市值</th>
				    <th>年化收益对比图</th>
				    <th>协议</th>
				    <th>排行</th>
				    <th>更多详情</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${yqbList}" var="yqb" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${yqb.marketValue}</td>
					    <td>${yqb.available}</td>
					    <td>${yqb.cumulativeVale}</td>
					    <td style="width: 300px;height: 150px;">
						    <div class="form_cont form_cont0 form_cont1" style="width: 200px;height: 150px;margin:10px auto;"> 
						    	<div class="yqbImg" style="width: 200px;height: 150px;">
						    		<img id="picShow2" src="${yqb.profitImage}" width="180" height="150"> 
						    	</div>
					        </div>
					    </td>
					    <td onclick="openHrefUrl('${yqb.agreement}');">${yqb.agreement}</td>
					    <td onclick="openHrefUrl('${yqb.rank}');">${yqb.rank}</td>
					    <td onclick="openHrefUrl('${yqb.moreDetail}');">${yqb.moreDetail}</td>
					 	<td>
						 	<a href="javascript:void(0);" onclick="addYqb(${yqb.id });">编辑</a>&nbsp;|&nbsp;
						 	<a href="javascript:void(0);" onclick="deleteYqb(${yqb.id });">删除</a>
					 	</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty yqbList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script>
function addYqb(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_yqb/newYqbAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function openHrefUrl(url){
	window.open(url,'','height=900, width=1800, top=50%, left=50%, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no');
}

function deleteYqb(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_list_yqb/deleteYqb.htm?id="+id,null,function(d){
		  if(d==1){
              sureInfo("确定","删除成功","提示");
              location.href = location.href;
          }
	});
}
</script>
