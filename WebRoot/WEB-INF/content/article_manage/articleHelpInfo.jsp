<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－帮助中心管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    帮助中心管理
    	       <domi:privilege url="/article_manage/newArticleHelp.htm"><a href="javascript:void(0);" onclick="addArticle('');" class="fr add">添加</a></domi:privilege>
    	    </div>
          <div class=" yhlb_title">帮助中心列表</div>
            <div class="yhlb">
            	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th width="20%">问题</th>
				    <th width="40%">答案</th>
				    <th>时间</th>
				    <th>创建人</th>
				    <th>状态</th>
				    <th>类别</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${articleList}" var="article" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${article.title}</td>
					    <td>${article.content}</td>
					    <td><fmt:formatDate value="${article.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td>${article.fuAdmin.name}</td>
					    <td>${article.state==0?'已删除':'未删除'}</td>
					    <td>${article.fuDictionary.name}</td>
					 	<td>
					 		<domi:privilege url="/article_manage/newArticleHelp.htm">
						 	<a href="javascript:void(0);" onclick="addArticle(${article.id });">编辑</a>
						 	</domi:privilege>
						 	&nbsp;|&nbsp;
						 	<domi:privilege url="/article_manage/deleteArticle.htm">
						 	<a href="javascript:void(0);" onclick="deleteArticle(${article.id });">删除</a>
						 	</domi:privilege>
					 	</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty articleList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/article_manage/articleHelpInfo.htm?totalCount=${totalCount}"
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
function addArticle(id){
	$.fancybox.open({
          href : '${ctx}/article_manage/newArticleHelp.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}


function deleteArticle(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
	$.post("${ctx}/article_manage/deleteArticle.htm?id="+id,null,function(d){
		  if(d==1){
              sureInfo("确定","删除成功","提示");
              location.href = location.href;
          }
	});
}
</script>
