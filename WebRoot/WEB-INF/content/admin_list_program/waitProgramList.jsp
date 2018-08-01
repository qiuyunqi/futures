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
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">待审核方案
    	    	<domi:privilege url="/admin_list_program/exportExcelAjax.htm">
    	    		<a href="javascript:void(0);" onclick="exportInfo();" class="fr add">导出</a>
    	    	</domi:privilege>
    	    </div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_program/waitProgramList.htm" method="post">
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
				            	<option <c:if test="${programType==1}">selected="selected"</c:if>  value="1">期货日配</option>
				            	<option <c:if test="${programType==2}">selected="selected"</c:if> value="2">期货月配</option>
				            	<option <c:if test="${programType==3}">selected="selected"</c:if> value="3">期货大赛</option>
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
				            <div class="lf_font fl">发起时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" class="search" onclick="searchInfo();">搜索</a><a href="${ctx}/admin_list_program/waitProgramList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">方案列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
					<tr>
				    <th>&nbsp;</th>
				    <th>方案ID</th>
				    <th>基金</th>
				    <th>期货公司</th>
				    <th>使用软件</th>
				    <th>期货账号</th>
				    <th>真实姓名 </th>
				    <th>方案状态 </th>
				    <th>账户总额</th>
				    <th>公司资金</th>
				    <th>客户资金 </th>
				    <th>销售比例</th>
				    <th>隔夜商品</th>
				    <th>隔夜股指</th>
				    <th>预警线</th>
				    <th>强平线</th>
				    <th>利率</th>
				    <th>日息/月息</th>
				    <th>权属人</th>
				    <!-- <th>居间人</th> -->
				    <th>开始日期</th>
				    <th>结算日期</th>
				    <th>备注</th>
				    <th style="text-align:center">操作</th>
				    </tr>
					<s:iterator value="programList" var="program" status="row">
					  		<tr style="cursor:pointer;"  onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490" <domi:privilege url="/admin_op_program/programDetail.htm">onclick="window.location='${ctx}/admin_op_program/programDetail.htm?id=${program.id}'"</domi:privilege> >${program.id}</td>
						    <td>${empty program.fuServer.serverRealName?'---':program.fuServer.serverRealName}</td>
						    <td>${empty program.fuServer.futureCompany?'---':program.fuServer.futureCompany}</td>
						    <td>${empty program.fuServer.futureCompany?'---':program.fuServer.clientType==1?'众期':program.fuServer.clientType==2?'鑫管家':'金牛'}</td>
						    <td>${program.fuServer.id}_${program.tradeAccount}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${program.fuUser.userName}');">${program.fuUser.userName}</td>
						    <td>${program.status==-2?'已删除':program.status==-1?'已关闭':program.status==0?'待审核':program.status==1?'审核中':program.status==2?'交易中':program.status==3?'拒绝开户':program.status==4?'等待结算':program.status==5?'交易结束':program.status==6?'结算中':'已穿仓'}</td>
						    <td><fmt:formatNumber value="${empty program.totalMatchMoney?0:program.totalMatchMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${empty program.matchMoney?0:program.matchMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${empty program.safeMoney?0:program.safeMoney}" pattern="#,###,##0.00"/></td>
						    <td>1：${program.doubleNum}</td>
						    <td>${program.overnightGoodRate}</td>
						    <td>${program.overnightStockIndexRate}</td>
						    <td><fmt:formatNumber value="${empty program.warnLine?0:program.warnLine}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${empty program.closeLine?0:program.closeLine}" pattern="#,###,##0.00"/></td>
						    <td><c:if test="${program.programType==1}">${program.moneyPercent}</c:if><c:if test="${program.programType==2}">${program.moneyPercent*100}%</c:if></td>
						  	<td><fmt:formatNumber value="${empty program.manageMoney?0:program.manageMoney}" pattern="#,###,##0.00"/></td>
						  	<td>${empty program.fuUser.recommend.userName?'---':program.fuUser.recommend.userName}</td>
						  	<!-- <td>---</td> -->
						  	<td><c:if test="${empty program.tradeTime}">---</c:if><c:if test="${!empty program.tradeTime}"><fmt:formatDate value="${program.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
						  	<td><c:if test="${empty program.clearTime}">---</c:if><c:if test="${!empty program.clearTime}"><fmt:formatDate value="${program.clearTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if></td>
						  	<td>${program.remark}</td>
						  	<td style="text-align:center"><div class=" caozuo">
						  	<domi:privilege url="/admin_op_program/programDetail.htm">
						  	<a href="${ctx}/admin_op_program/programDetail.htm?id=${id}">查看详情</a>
						  	</domi:privilege>
						  	</div></td>
						  </tr>
					  </s:iterator>  
					  <c:if test="${empty programList}">
					  <tr>
						<td colspan="23">
				        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
				        </td>
					  </tr>
					  </c:if>
					</tbody>
				</table>
           </div>
            
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_program/waitProgramList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="programId" value="${programId}"/>
						<domi:paramTag name="programType" value="${programType}"/>
						<domi:paramTag name="status" value="0"/>
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
			<input id="totalCount" name="totalCount" type="hidden" value="${totalCount}"/>
            <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
//导出
function exportInfo(){
	var programId=$("#programId").val();
	var programType=$("#programType").val();
	var status=0;
	var userName=$("#userName").val();
    var safeMoney1=$("#safeMoney1").val();
    var safeMoney2=$("#safeMoney2").val();
    var doubleNum1=$("#doubleNum1").val();
    var doubleNum2=$("#doubleNum2").val();
    var matchMoney1=$("#matchMoney1").val();
    var matchMoney2=$("#matchMoney2").val();
    var cycleNum1=$("#cycleNum1").val();
    var cycleNum2=$("#cycleNum2").val();
    var feePecent1=$("#feePecent1").val();
    var feePecent2=$("#feePecent2").val();
    var managerMoney1=$("#managerMoney1").val();
    var managerMoney2=$("#managerMoney2").val();
    var time1=$("#time1").val();
    var time2=$("#time2").val();
	$.fancybox.open({
			href : '${ctx}/admin_list_program/exportExcelAjax.htm?programId='+programId+'&programType='+programType
			+'&status='+status+'&userName='+userName+'&safeMoney1='+safeMoney1+'&safeMoney2='+safeMoney2
			+'&doubleNum1='+doubleNum1+'&doubleNum2='+doubleNum2+'&matchMoney1='+matchMoney1+'&matchMoney2='+matchMoney2
			+'&cycleNum1='+cycleNum1+'&cycleNum2='+cycleNum2+'&feePecent1='+feePecent1+'&feePecent2='+feePecent2
			+'&managerMoney1='+managerMoney1+'&managerMoney2='+managerMoney2+'&time1='+time1+'&time2='+time2,
			type : 'ajax'
		});
}

function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
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
</script>
