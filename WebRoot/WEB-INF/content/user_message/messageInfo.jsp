<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－在线留言</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background:#004e99;">
<c:set value="8" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %> 
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
	<div class="downban"></div>
	<div class="mgrzx">
	<%-- <%@include file="../common/left.jsp" %> --%>
		<%@include file="../common/left.jsp" %>
		<div class="mgrzxr">
		    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg">在线留言</span></div>
		    <div class="mgrrmain mgrrmaina">
		      <div class="rzxly" id="grselect">
		      <form id="messageForm">
		        <table cellpadding="0" cellspacing="0" border="0" width="100%">
		          <tr>
		            <th>类型：</th>
		            <td><select name="type" id="type">
		                <option value="">--请选择留言类型--</option>
			            <option value="1">App</option>
			            <option value="2">网站</option>
			            <!-- <option value="3">期货大赛</option> -->
			            <!-- <option value="3">MOM</option> -->
			            <option value="4">投诉建议</option>
			            <option value="5">其他反馈</option>
		              </select></td>
		          </tr>
		          <tr>
		            <th valign="top">内容：</th>
		            <td><textarea class="rzxlyarea" name="content" id="content" placeHolder="请输入您的留言内容"></textarea></td>
		          </tr>
		          <tr>
		            <th></th>
		            <td><input type="submit" value="添加" onclick="saveMessage();return false;" class="bcbut brRadius" style="background-color:#0092ff;border:1px solid #0092ff;"/></td>
		          </tr>
		         </table>
		         </form>
		      </div>
		    </div>
		     <div class="downban"></div>
		    <div class="mgrrmain mgrrmaina">
		      <form >
		          <table cellpadding="0" cellspacing="0" border="0" width="100%">
		          <tr>
		            <th></th>
		            <td><div id="pageDiv" ></div></td>
		          </tr>
		        </table>
		       </form>
		      </div> 
		</div>
     </div>
     <div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
jQuery.ajax({
	url:"${ctx}/user_message/messageAjax.htm",
	type:"post",
	cache:false,
	success:function(data){
		  if(data)
		   {
			  jQuery("#pageDiv").html(data);
		   }
	}
});
function saveMessage(){
	var data=$("#messageForm").serialize();
	$.post("${ctx}/user_message/saveMessageAjax.htm",data,function(d){
		if(d==-2){
			sureInfo('确定','请选择留言类型！','提示');
			return false;
		}
		if(d==-3){
			sureInfo('确定','请输入留言内容！','提示');
			return false;
		}
		location.href=location.href;
	});
	
}
function delInfo(messageId){
	jConfirm("您确定要删除吗？","删除提示",function(res){
		if(res){
			$.post("${ctx}/user_message/delMessageAjax.htm?id="+messageId,null,function(){
				$("#d"+messageId).remove();
			});
		}
	});
}
</script>
