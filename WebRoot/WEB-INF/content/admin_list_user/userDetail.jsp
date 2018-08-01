<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style type="text/css">
	.tr_name{display: none; border-radius:5px; border:1px solid #c6c6c6; padding-left:2px; line-height:16px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">用户资料<a href="${ctx}/admin_list_user/userList.htm" class="fr add">返回</a></div>
            
          <div class=" yhlb_title">账户信息</div>
            <div class="yhlb">
            
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <td bgcolor="#f6f6f6" width="13%">用户ID</td>
				    <td style="text-align:left" width="20%">${u.id}</td>
				    <td bgcolor="#f6f6f6" width="13%">真实姓名</td>
				    <td style="text-align:left" width="20%">${u.userName}</td>
				    <td bgcolor="#f6f6f6" width="13%">昵称</td>
				    <td style="text-align:left" width="20%">${empty u.nickName?'':u.nickName}</td>
				  </tr>
				  <tr>
				  	<td bgcolor="#f6f6f6" width="13%">最后登录时间</td>
				    <td style="text-align:left" width="20%"><fmt:formatDate value="${u.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td bgcolor="#f6f6f6">注册时间</td>
				    <td style="text-align:left" ><fmt:formatDate value="${u.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td bgcolor="#f6f6f6">邀请码</td>
				    <td style="text-align:left" >${u.invitationCode}</td>
				  </tr>
				  <tr>
				  	<td bgcolor="#f6f6f6" width="13%">是否穿仓</td>
				    <td style="text-align:left;" width="20%">
				    <select name="isAcrossCabin" id="isAcrossCabin">
		                <option <c:if test="${u.isAcrossCabin==0}">selected="selected"</c:if> value="0">未穿仓</option>
			            <option <c:if test="${u.isAcrossCabin==1}">selected="selected"</c:if> value="1">已穿仓</option>
	              	</select>
	              	<domi:privilege url="/admin_list_user/saveIsAcrossCabinAjax.htm">
	              		<a style="color:#137490;margin-left:20px" onclick="setIsAcrossCabin(${u.id});">确定</a>
	              	</domi:privilege>
	              	</td>
	              	<td bgcolor="#f6f6f6" width="13%">是否启用</td>
				    <td style="text-align:left" width="20%">
				    <select name="state" id="state">
		                <option <c:if test="${u.state==0}">selected="selected"</c:if> value="0">禁用</option>
			            <option <c:if test="${u.state==1}">selected="selected"</c:if> value="1">启用</option>
	              	</select>
	              	<domi:privilege url="/admin_list_user/saveSateAjax.htm">
	              		<a style="color:#137490;margin-left:20px" onclick="setState(${u.id});">确定</a>
	              	</domi:privilege>
				    </td>
				    <td bgcolor="#f6f6f6" width="13%">上级合伙人</td>
				    <td style="text-align:left" width="20%">${empty parentUser.userName?'未知':parentUser.userName} _ ${parentUser.phone}</td>
				  </tr>
				  <tr>
				  	<td bgcolor="#f6f6f6" width="13%">奇答等级</td>
				    <td style="text-align:left;" width="20%">
				    <select name="qidaRank" id="qidaRank">
		                <option <c:if test="${empty u.qidaRank or u.qidaRank==0}">selected="selected"</c:if> value="0">会员</option>
			            <option <c:if test="${u.qidaRank==1}">selected="selected"</c:if> value="1">专家</option>
	              	</select>
	              	<domi:privilege url="/admin_list_user/saveQidaRankAjax.htm">
	              		<a style="color:#137490;margin-left:20px" onclick="saveQidaRankAjax(${u.id});">确定</a>
	              	</domi:privilege>
				  </tr>
				</tbody>
				</table>
		</div>
		 <div class=" yhlb_title">资金信息</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				  	<td bgcolor="#f6f6f6" width="13%">当前总资产</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.accountTotalMoney?0:u.accountTotalMoney}" pattern="#,###,##0.00"/>元</td>
				  	<td bgcolor="#f6f6f6" width="13%">当前实盘资金</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.matchMoney?0:u.matchMoney}" pattern="#,###,##0.00"/>元</td>
				    <td bgcolor="#f6f6f6" width="13%">当前风险保证金</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.safeMoney?0:u.safeMoney}" pattern="#,###,##0.00"/>元</td>
				  </tr>
				  <tr>
				  	<td bgcolor="#f6f6f6" width="13%">可用余额</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.accountBalance?0:u.accountBalance}" pattern="#,###,##0.00"/>元</td>
				    <td bgcolor="#f6f6f6" width="13%">冻结金额</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.freezeMoney?0:u.freezeMoney}" pattern="#,###,##0.00"/>元</td>
				    <td bgcolor="#f6f6f6" width="13%">已兑佣金/总佣金</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.exchangeMoney?0:u.exchangeMoney}" pattern="#,###,##0.00"/>元/<fmt:formatNumber value="${empty u.commissionTotal?0:u.commissionTotal}" pattern="#,###,##0.00"/>元</td>
				  </tr>
				  <tr>
				    <td bgcolor="#f6f6f6" width="13%">总管理费</td>
				    <td style="text-align:left" width="20%"><fmt:formatNumber value="${empty u.feeTotal?0:u.feeTotal}" pattern="#,###,##0.00"/>元</td>
				    <td bgcolor="#f6f6f6" width="13%">取出金额/存入金额</td>
				    <td style="text-align:left" width="20%" colspan="3"><fmt:formatNumber value="${empty u.drawMoney?0:u.drawMoney}" pattern="#,###,##0.00"/>元/<fmt:formatNumber value="${empty u.rechargeMoney?0:u.rechargeMoney}" pattern="#,###,##0.00"/>元</td>
				  </tr>
				  
				</tbody></table>
				
		</div>
		 <div class=" yhlb_title">个人信息</div>
            <div class="yhlb">
            
            	 <table width="100%">
				  <tbody><tr>
				    <td bgcolor="#f6f6f6" width="13%">真实姓名</td>
				    <td style="text-align:left" width="20%">${u.userName}</td>
				    <td bgcolor="#f6f6f6" width="13%">证件号</td>
				    <td style="text-align:left" width="20%">身份证/${empty u.cardNumber?'未填写':u.cardNumber}</td>
				    <td bgcolor="#f6f6f6" width="13%">实名认证</td>
				    <td style="text-align:left" width="20%">${u.isCheckCard==2?'身份信息已验证':u.isCheckCard==3?'身份信息有误':u.isCheckCard==1?'身份信息待验证':'身份信息未验证'}<a href="javascript:void(0);" onclick="checkPerson(${u.id});" style="color:#137490;margin-left: 20px">认证</a></td>
				  </tr>
				  <tr>
				    <td bgcolor="#f6f6f6">手机号码</td>
				    <td style="text-align:left">${u.phone}</td>
				    <td bgcolor="#f6f6f6">邮箱地址</td>
				    <td style="text-align:left">${empty u.email?'未填写':u.email}</td>
				    <td bgcolor="#f6f6f6">性别/婚姻</td>
				    <td style="text-align:left">${empty u.gender?'未填写':u.gender==0?'男':'女'}/${empty u.isMarriage||u.isMarriage==0?'未填写':u.isMarriage==1?'未婚':u.isMarriage==2?'已婚':u.isMarriage==3?'离异':'丧偶'}</td>
				  </tr>
				  <tr>
				    <td bgcolor="#f6f6f6">所在省市</td>
				    <td style="text-align:left">${u.provinceName}-${u.cityName}</td>
				    <td bgcolor="#f6f6f6">联系地址</td>
				    <td style="text-align:left">${empty u.liveAddress?'未填写':u.liveAddress}</td>
				    <td bgcolor="#f6f6f6">教育水平</td>
				    <td style="text-align:left">${empty u.maxDegree?'未填写':u.maxDegree==0?'初中及以下':u.maxDegree==1?'高中':u.maxDegree==2?'大专':u.maxDegree==3?'本科':'本科以上'}</td>
				  </tr>
				  <tr>
				    <td bgcolor="#f6f6f6">从事行业</td>
				    <td style="text-align:left">${empty u.businessType?'未填写':u.businessType}</td>
				    <td bgcolor="#f6f6f6">所属职位</td>
				    <td style="text-align:left">${empty u.positionName?'未填写':u.positionName}</td>
				    <td bgcolor="#f6f6f6">收入水平</td>
				    <td style="text-align:left">${u.salary}元/月</td>
				  </tr>
				</tbody></table>
			</div>
		<div class=" yhlb_title">合伙人信息</div>
		 <div class="yhlb">
           	<table width="100%">
			  <tbody>
			  <tr>
			    	<td bgcolor="#f6f6f6" width="13%">类别设置</td>
					<td style="text-align:left" width="87%"> 
						<select name="hhrType" id="hhrType">
			                <option value="3">--请选择合伙人类别--</option>
			                <option <c:if test="${u.hhrType==0}">selected="selected"</c:if> value="0">终端</option>
				            <option <c:if test="${u.hhrType==1}">selected="selected"</c:if> value="1">渠道</option>
				            <option id="op" <c:if test="${u.hhrType==2}">selected="selected"</c:if> value="2">交易团队</option>
				            <option <c:if test="${u.hhrType==3}">selected="selected"</c:if> value="3">销售人员</option>
		              	</select>
		              	<input class="tr_name" type="text" name="name" value="${transaction.name}"/>
    	          		<domi:privilege url="/admin_list_user/savePersonHhrTypeAjax.htm">
		              		<a style="color:#137490;margin-left: 20px" onclick="setHhrType(${u.id})";>确定</a>
	           			</domi:privilege>
					</td>
			  </tr>
			</tbody>
			</table>
		</div>
		<div class=" yhlb_title">上级合伙人</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>手机号</th>
				    <th>姓名</th>
				    <th>合伙人级别</th>
				  </tr>
				  <c:forEach items="${parentList}" var="p" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${p.id}</td>
					    <td>${p.phone}</td>
					    <td>${p.userName}</td>
					    <td>${p.hhrLevel}</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
          <c:if test="${empty parentList}">
				  <div style="text-align:center;">无上级合伙人！</div>
		  </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
$(function() {
	var hhrType = $("#hhrType").find("option:selected").val();
	if(hhrType == 2) {
		$(".tr_name").show();
	}else {
		$(".tr_name").hide();
	}
	
});
function checkPerson(id){
	$.fancybox.open({
          href : '${ctx}/admin_op_check_person/checkDetailAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function setHhrType(id){
	var hhrTypes = $("#hhrType").val();
	var names = $(".tr_name").val();
	if(hhrType == "3"){
		$.alerts.okButton='确定';
		jAlert('请选择合伙人类别！','提示',function(){
			$("#hhrType").focus();
		});
		return false;
	}	
	$.post(
			"${ctx}/admin_list_user/savePersonHhrTypeAjax.htm",
			{id:"${u.id}", hhrType: hhrTypes, name: names},
			function(data){
		if (data["is_success"] == 1) {
			jAlert(data["message"],"提示",function(){
				location.href=location.href;
	        });
		}else {
			return false;
		}
 	}, "json");
 }

function setIsAcrossCabin(id){
	var isAcrossCabin = $("#isAcrossCabin").val();
	$.post("${ctx}/admin_list_user/saveIsAcrossCabinAjax.htm?id="+id+"&isAcrossCabin="+isAcrossCabin,null,function(d){
		$.alerts.okButton="确定";
		jAlert("设置成功！","提示",function(){
			location.href=location.href;
        });
	});
}

function saveQidaRankAjax(id){
	var qidaRank = $("#qidaRank").val();
	$.post("${ctx}/admin_list_user/saveQidaRankAjax.htm?id="+id+"&qidaRank="+qidaRank,null,function(d){
		$.alerts.okButton="确定";
		jAlert("设置成功！","提示",function(){
			location.href=location.href;
        });
	});
}

function setState(id){
	var state = $("#state").val();
	$.post("${ctx}/admin_list_user/saveSateAjax.htm?id="+id+"&state="+state,null,function(d){
		if(d==-1){
			jAlert("该手机号已关联其他启用账号，无法启用！","提示",function(){
        	});
			return false;
		}
		$.alerts.okButton="确定";
		jAlert("设置成功！","提示",function(){
			location.href=location.href;
        });
	});
}

/* function setParentUser(id){
	$.fancybox.open({
          href : '${ctx}/admin_op_check_person/parentUserList.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
} */
$("#hhrType").change(function() {
	var typeValue = $("#hhrType").find("option:selected").val();
	if(typeValue == 2) {
		$(".tr_name").css("display", "inline-block");
	}else {
		$(".tr_name").hide();
	}
});
</script>
