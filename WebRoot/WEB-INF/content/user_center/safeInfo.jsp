<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－安全信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background: #004e99;">
<c:set value="7" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
<div class="downban"></div>
<div class="mgrzx">
  <%@include file="../common/left.jsp" %>
  <%-- <%@include file="../common/left.jsp" %> --%>
  <div class="mgrzxr">
    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg">安全设置</span></div>
    <div class="mgrrmain mgrrmaina">
      <div class="raqxx">
        <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;">
          <tr>
	            <th width="80">
	               <c:if test="${fuUser.isCheckCard==2}"><img src="../images_new/pass.png" /></c:if>
	               <c:if test="${fuUser.isCheckCard!=2}"><img src="../images_new/noPass.png" /></c:if>
	            </th>
	            <td>
	        	<c:if test="${fuUser.isCheckCard>0}">
	        		<span class="fl">实名认证<span class="mglf">${fn:substring(fuUser.cardNumber,0,2)}**** **** **** **${fn:substring(fuUser.cardNumber,fn:length(fuUser.cardNumber)-2,-1)}</span></span>
	        	</c:if>
	        	<c:if test="${fuUser.isCheckCard!=2}">
	            	<span style=" color:#FFCC00; float:left;">${fuUser.isCheckCard==0?'未认证':fuUser.isCheckCard==1?'待审核':fuUser.isCheckCard==2?'已认证':'信息有误'}</span>
	            </c:if>
	           </td>
               <td class="raqxxr">
               <c:if test="${fuUser.isCheckCard!=2}">
	                <a class="fr sczp" href="javascript:void(0);" onclick="checkPerson();">${fuUser.isCheckCard==0?'实名认证':'修改信息'}</a>
	            </c:if>
	            <c:if test="${fuUser.isCheckCard==2}">
	                <a class="fr gayBtn" href="javascript:void(0);">已设置</a>
	            </c:if>
               </td>
          </tr>
          <tr>
	            <th>
                  <img src="../images_new/pass.png" />
                </th>
	            <td>
		            <span class="fl">绑定手机<span class="mglf">${fn:substring(fuUser.phone,0,3)} **** ${fn:substring(fuUser.phone,fn:length(fuUser.phone)-4,-1)}</span></span>
	            </td>
	            <td class="raqxxr"><a class="fr gayBtn" href="javascript:void(0);">已设置</a></td>
          </tr>
          <tr>
	            <th><c:if test="${fuUser.isBindEmail==1}"><img src="../images_new/pass.png" /></c:if><c:if test="${fuUser.isBindEmail==0}"><img src="../images_new/noPass.png" /></c:if></th>
	            <td><span class="fl">安全邮箱<span class="mglf">${fuUser.isBindEmail==1?fuUser.email:'未绑定'}</span></span></td>
	            <td class="raqxxr"><a class="fr" href="javascript:void(0);" onclick="bindEmail();">${fuUser.isBindEmail==1?'修改':'绑定'}</a></td>
          </tr>
          <tr>
	            <th><img src="../images_new/pass.png" /></th>
	            <td>登录密码<span class="mglf">已设置</span></td>
	            <td class="raqxxr"><a class="fr" href="javascript:void(0);" onclick="editPassword();">已设置</a></td>
          </tr>
          <tr>
	            <th>
	              <c:if test="${empty fuUser.drawPassword}">
	            	<img src="../images_new/noPass.png" />
	             </c:if>
	             <c:if test="${!empty fuUser.drawPassword}">
	            	 <img src="../images_new/pass.png" />
	             </c:if>
                </th>
	            <td>提款密码<span class="mglf">${empty fuUser.drawPassword?'尚未设置，请尽快设置保证支付和体现安全':'已设置'}</span></td>
	            <td class="raqxxr">
	            <c:if test="${empty fuUser.drawPassword}">
	            	<a class="fr sczp" href="javascript:void(0);" onclick="drawPassword();">设置</a>
	            </c:if>
	            <c:if test="${!empty fuUser.drawPassword}">
	            	 <a class="fr sczp" href="javascript:void(0);" onclick="findDrawPwd();">忘记密码</a><a class="fr sczp" href="javascript:void(0);" onclick="drawPassword();">修改&nbsp;&nbsp;</a>
	            </c:if>
	            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
	//身份认证
	function checkPerson(){
		$.fancybox.open({
 			href : '${ctx}/user_center/personNameAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//绑定邮箱
	function bindEmail(){
		$.fancybox.open({
 			href : '${ctx}/user_center/bindEmailAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//绑定手机
	function bindPhone(){
		$.fancybox.open({
 			href : '${ctx}/user_center/bindPhoneAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	//修改密码
	function editPassword(){
		$.fancybox.open({
 			href : '${ctx}/user_center/editPasswordAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	function drawPassword(){
		$.fancybox.open({
 			href : '${ctx}/user_center/drawPasswordAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
	function findDrawPwd(){
		$.fancybox.open({
 			href : '${ctx}/user_center/findDrawPwdAjax.htm',
 			type : 'ajax',
 			padding : 5 
 		});
	}
</script>
