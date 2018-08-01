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
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">用户管理<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_user/userList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户ID：</div>
				            <div class="fl input"><input id="userId" name="userId" value="${userId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="accountName" name="accountName" value="${accountName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">手机号码：</div>
				            <div class="fl input"><input id="phone" name="phone" value="${phone}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">证件号码：</div>
				            <div class="fl input"><input id="cardNumber" name="cardNumber" value="${cardNumber}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">电子邮箱：</div>
				            <div class="fl input"><input id="email" name="email" value="${email}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">借入金额：</div>
				            <div class="fl input"><input id="matchMoney1" name="matchMoney1" value="${matchMoney1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="matchMoney2" name="matchMoney2" value="${matchMoney2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">借出金额：</div>
				            <div class="fl input"><input id="safeMoney1" name="safeMoney1" value="${safeMoney1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="safeMoney2" name="safeMoney2" value="${safeMoney2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户状态：</div>
				            <div class="fl select"><select style="width:178px;" id=type name="state">
				            	<option value="">全部</option>
				            	<option <c:if test="${state==0}">selected="selected"</c:if> value="0">禁用</option>
				            	<option <c:if test="${state==1}">selected="selected"</c:if> value="1">启用</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				   </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">邀请码：</div>
				            <div class="fl input"><input id="invitationCode" name="invitationCode" value="${invitationCode}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">注册时间：</div>
				            <div class="fl input"><input id="time1" name="time1" type="text" value="${time1}" placeholder="" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" type="text" value="${time2}" placeholder="" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi" ></i></div>
 				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">登录时间：</div>
				            <div class="fl input"><input id="time3" name="time3" type="text" value="<fmt:formatDate value='${time3}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time4\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time4" name="time4" type="text" value="<fmt:formatDate value='${time4}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time3\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi" ></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				  <td>
				        <div class="form_cont fl">
				            <div class="lf_font fl ">模糊搜索 &nbsp;</div>
				            <div class="fl checkbox"><span class="xuankang"><input name="searchType" <c:if test="${searchType==0}">checked="checked"</c:if> type="checkbox" value="0" style="width:13px;"></span></div>
				        </div>
				        <div class="clr"></div>
				    </td>
				  <td>
				        <div class="form_cont">
				            <div class="lf_font fl">注册IP：</div>
				            <div class="fl input"><input id="registerIp" name="registerIp" value="${registerIp}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				   </td>
				  <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_user/userList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">用户列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>昵称</th>
				    <th>余额</th>
				    <th>借入/借出 </th>
				    <th>用户状态</th>
				    <th>注册IP</th>
				    <th>邀请码</th>
				    <th>注册时间</th>
				    <th>最后登陆</th>
				  	<th style=" text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${userList}" var="user" varStatus="row">
			  		<tr onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
					    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
					    <td style="color:#137490;cursor:pointer;" <domi:privilege url="/admin_list_user/userDetail.htm">onclick="window.location='${ctx}/admin_list_user/userDetail.htm?id=${user.id}'"</domi:privilege>>${user.accountName}</td>
					    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${user.userName}');">${user.userName}</td>
					    <td width="10%">${empty user.nickName?'':user.nickName}</td>
					    <td><fmt:formatNumber value="${empty user.accountBalance?0:user.accountBalance}" pattern="#,###,##0.00"/></td>
					    <td><fmt:formatNumber value="${empty user.matchMoney?0:user.matchMoney}" pattern="#,###,##0.00"/>/<fmt:formatNumber value="${empty user.safeMoney?0:user.safeMoney}" pattern="#,###,##0.00"/></td>
					    <td>${empty user.state?'':user.state==0?'禁用':'启用'}</td>
					    <td width="10%">${empty user.registerIp?'':user.registerIp}</td>
					    <td width="16%">${user.invitationCode}</td>
					    <td width="10%"><fmt:formatDate value="${user.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td width="10%"><fmt:formatDate value="${user.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					 	<td style="text-align:center">
					 		<div class="caozuo">
							 	<domi:privilege url="/admin_list_user/userDetail.htm">
							 		<a href="${ctx}/admin_list_user/userDetail.htm?id=${user.id}">查看资料</a>
							 	</domi:privilege>
							 	<domi:privilege url="/admin_list_user/resetUserPassword.htm">
							 		<a href="javascript:void(0);" onclick="reset('${user.id}');">重置密码</a>
							 	</domi:privilege>
					 		</div>
					 	</td>
					 </tr>
				  </c:forEach>
				  <c:if test="${empty userList}">
				  <tr>
					<td colspan="12">
			        	<div class="empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_user/userList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="userId" value="${userId}"/>
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="phone" value="${phone}"/>
						<domi:paramTag name="cardNumber" value="${cardNumber}"/>
						<domi:paramTag name="email" value="${email}"/>
						<domi:paramTag name="matchMoney1" value="${matchMoney1}"/>
						<domi:paramTag name="matchMoney2" value="${matchMoney2}"/>
						<domi:paramTag name="time1" value="${time1}"/>
						<domi:paramTag name="time2" value="${time2}"/>
						<domi:paramTag name="time3" value="${time3}"/>
						<domi:paramTag name="time4" value="${time4}"/>
						<domi:paramTag name="safeMoney1" value="${safeMoney1}"/>
						<domi:paramTag name="safeMoney2" value="${safeMoney2}"/>
						<domi:paramTag name="searchType" value="${searchType}"/>
						<domi:paramTag name="state" value="${state}"/>
						<domi:paramTag name="registerIp" value="${registerIp}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function searchInfo(){
	if(isNaN($("#userId").val())){
		sureInfo("确定","输入用户ID必须为数字","提示");
		return false;
	}
	
	if(isNaN($("#matchMoney1").val())||isNaN($("#matchMoney2").val())){
		sureInfo('确定','借入金额只能输入数字！','提示');
		return false;
	}
	if(isNaN($("#safeMoney1").val())||isNaN($("#safeMoney2").val())){
		sureInfo('确定','借出金额只能输入数字！','提示');
		return false;
	}
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
function reset(id){
	$.fancybox.open({
        href : '${ctx}/admin_list_user/resetUserPassword.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
</script>
