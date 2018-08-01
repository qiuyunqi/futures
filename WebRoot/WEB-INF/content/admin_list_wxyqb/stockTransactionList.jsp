<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title} -交易员列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">交易员列表<%-- <domi:privilege url="/admin_op_wqq_contents/addContents.htm"><a href="javascript:void(0);" class="fr add" onclick="addTransaction('')">添加</a></domi:privilege> --%></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_wxyqb/stockTransactionList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">团队名称：</div>
				            <div class="fl input"><input id="name" name="name" value="${name}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select"><select style="width:178px;" id="isVerification" name="isVerification">
				            <option value="">全部</option>
				            	<option <c:if test="${isVerification==0}">selected="selected"</c:if> value="0">未审核</option>
				            	<option <c:if test="${isVerification==1}">selected="selected"</c:if> value="1">通过</option>
				            	<option <c:if test="${isVerification==2}">selected="selected"</c:if> value="2">拒绝</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">评级：</div>
				            <div class="fl select"><select style="width:178px;" id="rating" name="rating">
				            <option value="">全部</option>
				            	<option <c:if test="${rating==1}">selected="selected"</c:if> value="1">1星</option>
				            	<option <c:if test="${rating==2}">selected="selected"</c:if> value="2">2星</option>
				            	<option <c:if test="${rating==3}">selected="selected"</c:if> value="3">3星</option>
				            	<option <c:if test="${rating==4}">selected="selected"</c:if> value="4">4星</option>
				            	<option <c:if test="${rating==5}">selected="selected"</c:if> value="5">5星</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_wxyqb/stockTransactionList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">交易员列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>姓名</th>
				    <th>团队名称</th>
				    <th>手机号</th>
				    <th>是否删除</th>
				    <th>状态</th>
				    <th>星级</th>
				    <th>申请时间</th>
				    <th style="text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${list}" var="transac" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${transac.fuUser.userName}</td>
						    <td>${empty transac.name?'':transac.name}</td>
						    <td>${empty transac.fuUser?'':transac.fuUser.phone}</td>
						    <td>${transac.isDel==0?'已删除':'未删除'}</td>
						    <td>${transac.isVerification==0?'未审核':transac.isVerification==1?'通过':'拒绝'}</td>
						    <td>${transac.rating}星</td>
						    <td><fmt:formatDate value="${transac.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						 	<td style="text-align:center">
						 	<div class="caozuo">
						 	<domi:privilege url="/admin_op_wqq_contents/addContents.htm">
						 	<a href="javascript:void(0);" onclick="updateTransaction('${transac.id}')">修改</a>
						 	</domi:privilege>
						 	</div>
						 	</td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty list}">
				  <tr>
					<td colspan="9">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_wxyqb/stockTransactionList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="name" value="${name}"/>
						<domi:paramTag name="isVerification" value="${isVerification}"/>
						<domi:paramTag name="rating" value="${rating}"/>
						<domi:paramTag name="userName" value="${userName}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function updateTransaction(id){
	$.fancybox.open({
        href : "${ctx}/admin_op_wxyqb/updateTransactionAjax.htm?id="+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
</script>
