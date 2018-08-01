<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<c:if test="${empty messageList}">
		        <div class="opinion" style="width:75%;height: 100px;text-align: center;line-height: 100px">
		        	您还没有留言记录！
		        </div>
	        </c:if>
<c:forEach items="${messageList}" var="message" varStatus="row">
  	<div class="opinion" id="d${message.id}">
   	<div class="opinion_title">
       	<span>${message.type==1?'App':message.type==2?'网站':message.type==3?'解套者联盟':message.type==4?'投诉建议':'其他反馈'} </span>
           <!-- <a href="javascript:void(0);" onclick="delInfo(${message.id});"  class=" gzsm fr" style="margin-right:15px;">删除</a> -->
           <span class="fr"><fmt:formatDate value="${message.time}" pattern="yyyy-MM-dd"/></span>
           <div class="clr"></div>
       </div>
       <div class="fl yjnr">
       	<span>内容：</br></span><span class="smaMarginLf" style="display: block;word-wrap:break-word;word-break:break-all;">${message.content}</span>
       </div>
       <div class="fl yjnr0">
       	<span>回复：</br></span><span class="smaMarginLf" style="display: block;word-wrap:break-word;word-break:break-all;">${empty message.replyContent?'<span style="color:#FFCC00;">未回复</span>':message.replyContent}</span>
       </div>
       <div class="clr"></div>
   </div>
  </c:forEach>
    <div class="opinion" style="border: none;">
        <div class="page">
	<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/user_message/messageAjax.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" divId="pageDiv">
	</domi:pagination>
	</div>
	</div>
