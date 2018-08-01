<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－方案管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="12"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">待结算方案管理</div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_over_program/overProgramList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input id="programId" name="programId" value="${programId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案类型：</div>
				            <div class="fl select"><select style="width:178px;" id="programType" name="programType">
				            	<option value="">全部</option>
				            	<option <c:if test="${programType==1}">selected="selected"</c:if>  value="1">短线高手</option>
				            	<option <c:if test="${programType==2}">selected="selected"</c:if> value="2">趋势之王</option>
				            	<option <c:if test="${programType==3}">selected="selected"</c:if> value="3">期货大赛</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案状态：</div>
				            <div class="fl select"><select style="width:178px;" id="status" name="status">
				            	<option value="">全部</option>
				            	<option <c:if test="${status==2}">selected="selected"</c:if> value="2">交易中</option>
				            	<option <c:if test="${status==4}">selected="selected"</c:if> value="4">等待结算</option>
				            	<option <c:if test="${status==5}">selected="selected"</c:if> value="5">交易结束</option>
				            </select></div>
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
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">保证金：</div>
				            <div class="fl input"><input id="safeMoney1" name="safeMoney1" value="${safeMoney1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="safeMoney2" name="safeMoney2" value="${safeMoney2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">配资杠杆：</div>
				            <div class="fl input"><input id="doubleNum1" name="doubleNum1" value="${doubleNum1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="doubleNum2" name="doubleNum2" value="${doubleNum2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">借款金额：</div>
				            <div class="fl input"><input id="matchMoney1" name="matchMoney1" value="${matchMoney1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="matchMoney2" name="matchMoney2" value="${matchMoney2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">借贷周期：</div>
				            <div class="fl input"><input id="cycleNum1" name="cycleNum1" value="${cycleNum1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="cycleNum2" name="cycleNum2" value="${cycleNum2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">利率：</div>
				            <div class="fl input"><input id="feePecent1" name="feePecent1" value="${feePecent1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="feePecent2" name="feePecent2" value="${feePecent2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">总利息：</div>
				            <div class="fl input"><input id="managerMoney1" name="managerMoney1" value="${managerMoney1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="managerMoney2" name="managerMoney2" value="${managerMoney2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">结算时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" class="search" onclick="searchInfo();">搜索</a><a href="${ctx}/admin_list_over_program/overProgramList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  
				  
				</tbody></table>
			</form>
			
            </div>
          <div class=" yhlb_title">待结算方案列表</div>
            <div class="yhlb">
            
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>方案ID</th>
				    <th>真实姓名</th>
				    <th>期货账号</th>
				    <th>基金</th>
				    <th>期货公司</th>
				    <th>类型</th>
				    <th>保证金</th>
				    <th>配资杠杆 </th>
				    <th>借款</th>
				    <th>周期</th>
				    <th>利率 </th>
				    <th>总利息</th>
				    <th>状态</th>
				    <th>管理员</th>
				    <th>结算时间/到期时间</th>
				    <th style=" text-align:center">操作</th>
				    <th style=" text-align:center">查看详情</th>
				    </tr>
  
					  <c:forEach items="${programList}" var="program" varStatus="row">
					  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490" <c:if test="${empty rageConfig||!empty rageConfig.jd.jd2}">onclick="window.location='${ctx}/admin_op_program/overProgramDetail.htm?id=${program.id}'"</c:if> >${program.id}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${program.fuUser.userName}');">${program.fuUser.userName}</td>
						    <td>${program.fuServer.id}_${program.tradeAccount}</td>
						    <td>${empty program.fuServer.serverRealName?'---':program.fuServer.serverRealName}</td>
						    <td>${empty program.fuServer.futureCompany?'---':program.fuServer.futureCompany}</td>
						    <td>${program.programType==1?'短线高手':program.programType==2?'趋势之王':'期货大赛'}</td>
						    <td><fmt:formatNumber value="${empty program.safeMoney?0:program.safeMoney}" pattern="#,###,##0.00"/></td>
						    <td>${program.doubleNum}</td>
						    <td><fmt:formatNumber value="${empty program.matchMoney?0:program.matchMoney}" pattern="#,###,##0.00"/></td>
						    <td>${program.cycleNum}${program.programType==1?'天':'个月'}</td>
						    <td>${program.moneyPercent}</td>
						    <td><fmt:formatNumber value="${empty program.manageMoney?0:program.manageMoney}" pattern="#,###,##0.00"/></td>
						  	<td>${program.status==-2?'已删除':program.status==-1?'已关闭':program.status==0?'待审核':program.status==1?'审核中':program.status==2?'交易中':program.status==3?'拒绝开户':program.status==4?'等待结算':program.status==5?'交易结束':program.status==6?'结算中':'已穿仓'}</td>
						  	<td>${empty program.adminClear.account?'----':program.adminClear.account}</td>
						  	<td><fmt:formatDate value="${program.clearTime}" pattern="yyyy-MM-dd HH:mm:ss"/>${empty program.clearTime?'---':''}/<fmt:formatDate value="${program.closeTime}" pattern="yyyy-MM-dd"/></td>
						  	<td style="text-align:center"><div class="caozuo">
						  	<domi:privilege url="/admin_op_program/programCloseAjax.htm">
						  	<c:if test="${program.status==2||program.status==4}"><a href="javascript:void(0);" onclick="closeProgram(${program.id});">结算</a></c:if>
						  	</domi:privilege>
						  	</div></td>
						  	<td style="text-align:center"><div class="caozuo">
						  	<domi:privilege url="/admin_op_program/overProgramDetail.htm">
						  	<a href="${ctx}/admin_op_program/overProgramDetail.htm?id=${program.id}">查看详情</a>
						  	</domi:privilege>
						  	</div></td>
						  </tr>
					  </c:forEach>
					  <c:if test="${empty programList}">
					  <tr>
						<td colspan="18">
				        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
				        </td>
					  </tr>
					  </c:if>
					  
					</tbody></table>

          </div>
            
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_over_program/overProgramList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="programId" value="${programId}"/>
						<domi:paramTag name="programType" value="${programType}"/>
						<domi:paramTag name="status" value="${status}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="safeMoney1" value="${safeMoney1}"/>
						<domi:paramTag name="safeMoney2" value="${safeMoney2}"/>
						<domi:paramTag name="doubleNum1" value="${doubleNum1}"/>
						<domi:paramTag name="doubleNum2" value="${doubleNum2}"/>
						<domi:paramTag name="matchMoney1" value="${matchMoney1}"/>
						<domi:paramTag name="matchMoney2" value="${matchMoney2}"/>
						<domi:paramTag name="cycleNum1" value="${cycleNum1}"/>
						<domi:paramTag name="cycleNum2" value="${cycleNum2}"/>
						<domi:paramTag name="feePecent1" value="${feePecent1}"/>
						<domi:paramTag name="feePecent2" value="${feePecent2}"/>
						<domi:paramTag name="managerMoney1" value="${managerMoney1}"/>
						<domi:paramTag name="managerMoney2" value="${managerMoney2}"/>
						<domi:paramTag name="time1" value="${time1}"/>
						<domi:paramTag name="time2" value="${time2}"/>
				</domi:pagination>
			</div>
            <div class="clr"></div> 
            
        </div>
    </div>
</div>
</body>
</html>
<script>
//方案结算
function closeProgram(id){
	$.fancybox.open({
          href : '${ctx}/admin_op_program/programCloseAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function searchInfo(){
	if(isNaN($("#programId").val())){
		sureInfo("确定","输入方案ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#safeMoney1").val())||isNaN($("#safeMoney2").val())){
		sureInfo("确定","输入保证金必须为数字","提示");
		return false;
	}
	if(isNaN($("#doubleNum1").val())||isNaN($("#doubleNum2").val())){
		sureInfo("确定","输入配资杠杆必须为数字","提示");
		return false;
	}
	if(isNaN($("#matchMoney1").val())||isNaN($("#matchMoney2").val())){
		sureInfo("确定","输入借款金额必须为数字","提示");
		return false;
	}
	if(isNaN($("#cycleNum1").val())||isNaN($("#cycleNum2").val())){
		sureInfo("确定","输入借贷周期必须为数字","提示");
		return false;
	}
	if(isNaN($("#feePecent1").val())||isNaN($("#feePecent2").val())){
		sureInfo("确定","输入利率必须为数字","提示");
		return false;
	}
	if(isNaN($("#managerMoney1").val())||isNaN($("#managerMoney2").val())){
		sureInfo("确定","输入总利息必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
