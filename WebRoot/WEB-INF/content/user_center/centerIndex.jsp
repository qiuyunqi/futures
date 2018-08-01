<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<title>${title}－个人中心</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>

<script language="javascript" src="../js_hhr/jquery-1.6.min.js"></script>
<script language="javascript" src="../js_hhr/header.js"></script>
<style>
.hgrzxt{margin-bottom:0 !important;}
.bodybg{background: #004e99;}
</style>
</head>
<body class="bodybg">
<c:set value="0" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
<div class="downban"></div>
<div class="hgrzx">
<%@include file="../common/left.jsp" %>
<div class="mgrzxr">
   <div class="hbanner hgrzxb">
    <div class="hgrzxbl"><img style="width:80px;height:80px;" src="${empty fuUser.userAvatar?'../images_hhr/default.png':fuUser.userAvatar}"/></div>
    <div class="hgrzxbc">
    	<div class="hgrzCenter">
	  		<div class="center-all">
	       			<span class="center-morn"><%if(new Date().getHours()>=0 && new Date().getHours()<=12){%>上午好<%}else{%>下午好<%}%>，</span>
	       			<span class="center-name">${empty fuUser.nickName?'佚名':fuUser.nickName}</span>
	       	</div>	
	       	<!-- 修改名称输入框 -->
	       	<div class="center-change">
			    <input type="text" name="userName" placeholder="限8字以内" />
			    <a href="javascript:void(0);" id="updateName" class="centerChan-btn">保存</a>
			</div>
	       	<a class="qianm-btn"><img src="../images_hhr/pen.png"></a>
       </div>	
       	<br/>
       <span>上次登陆时间：<fmt:formatDate value="${fuUser.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
       <a href="javascript:void(0)" onclick="logout();" style="border-radius: 5px;color: #fff;font-size: 14px;margin-left: 5px;padding: 1px 10px;background:#f96900;">退出</a>
    </div>
    <div class="hgrzxbr">
    <span class="yhm_0 fl" style="padding-left:15px;">安全等级</span>
    <span class="aqdj fl"><li class="aqdj_cont" style="width: ${fuUser.safeLevel==1?'25%':fuUser.safeLevel==2?'35%':fuUser.safeLevel==3?'50%':fuUser.safeLevel==4?'80%':'100%'}"></li></span>
    <span class="dj fl">${fuUser.safeLevel==1?'弱':fuUser.safeLevel==2?'低':fuUser.safeLevel==3?'中':fuUser.safeLevel==4?'高':'极高'}</span>
    <span style="margin-left:25px;">邀请码：<a href="javascript:void(0);" class=" wenti" original-title="${fuUser.invitationCode }" ></a></span>
    </div>
    <div class="clear"></div>
  </div>
  <div class="hwdzhrto">
  	   <table cellpadding="0" cellspacing="0" border="0" width="100%" >
	        <tr>
	          <td>账户总资产（元）<br /><span><fmt:formatNumber value="${empty fuUser.accountTotalMoney?0:fuUser.accountTotalMoney}" pattern="#,###,##0.00"/></span></td>
	          <td style="border-left:1px solid #cfcfcf;border-right:1px solid #cfcfcf;">昨日盈亏（元）<br />
	          		<span>
	          			<c:if test="${dayProfits>=0}"><span class="zheng">+<fmt:formatNumber value="${dayProfits}" pattern="#,###,##0.00"/></span></c:if>
	        		    <c:if test="${dayProfits<0}"><span class="fu"><fmt:formatNumber value="${dayProfits}" pattern="#,###,##0.00"/></span></c:if>
	          		</span></td>
	          <td>累计盈亏（元）<br />
	           		<span>
			        	<c:if test="${empty fuStockMoneyInfo.profitInfo || fuStockMoneyInfo.profitInfo>=0}"><span class="zheng">+<fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/></span></c:if>
			          	<c:if test="${fuStockMoneyInfo.profitInfo<0}"><span class="fu"><fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/></span></c:if>
			        </span>
	        </tr>
	  </table>
  </div>
  <div class="downban"></div>
  <div class="hwdzhrtab">
      <div class="hwdtabt">
      <form id="searchForm" method="post" action="${ctx}/user_center/centerIndex.htm?pid=${pid}">
        <div class="hwdtabt-span"><strong>资金明细</strong>本页收入<span>${incomeCount}</span>笔，共<span><fmt:formatNumber value="${empty incomeMoney?0:incomeMoney}" pattern="#,###,##0.00"/></span>元；支出<span>${spendCount}</span>笔，共<span><fmt:formatNumber value="${empty spendMoney?0:spendMoney}" pattern="#,###,##0.00"/></span>元</div>
        <div class="hwdtabts">
            <c:if test="${empty pid}">
            <select name="dictionaryId" onchange="location.href='${ctx}/user_center/centerIndex.htm?flag=${flag}&dictionaryId='+this.value">
            	 <option value="">全部明细</option>
	   			 <c:forEach items="${dictionaries}" var="dictionar">
	             <option value="${dictionar.id}" <c:if test="${dictionaryId==dictionar.id}">selected</c:if>>${dictionar.name}</option>
            	 </c:forEach>
             </select>
            </c:if>
      		<c:if test="${!empty pid}">
            <select name="dictionaryId" onchange="location.href='${ctx}/user_center/centerIndex.htm?flag=${flag}&pid=${pid}&dictionaryId='+this.value">
            	<option value="">全部明细</option>
            	<c:forEach items="${dictionaries}" var="dictionar">
              	<option value="${dictionar.id}" <c:if test="${dictionaryId==dictionar.id}">selected</c:if>>${dictionar.name}</option>
            	</c:forEach>
            </select>
       		</c:if>
        </div>
        <div class="hwdtabts hwdtabtt">
            <select name="flag">
            <option value="">时间</option>
            <option value="1" <c:if test="${flag==1}">selected</c:if>>近一个星期</option>
            <option value="2" <c:if test="${flag==2}">selected</c:if>>近一个月</option>
            <option value="3" <c:if test="${flag==3}">selected</c:if>>近一年</option>
            </select>
            <a href="javascript:void(0);" onclick="doSearch();"><img src="../images_new/wdzh03.png"/></a>
         </div>
        </form>
      </div>
      <div class="hwdtabbd">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
          <tr>
            <th>时间</th>
            <th>类型</th>
            <th style="text-align:right;">收入（元）</th>
            <th style="text-align:right;">支出（元）</th>
            <th style="background:none;text-align:right;">余额（元）</th>
          </tr>
          <c:forEach items="${detailList}" var="detail" varStatus="row">
          <tr>
            <td height="35" align="center" <c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td align="center" height="35" style="line-height:35px;<c:if test="${row.index==fn:length(detailList)-1}">border-bottom:none;</c:if>">${detail.fuDictionary.name}</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${detail.isIncome ? detail.money : 0}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${detail.isIncome ? 0 : detail.money}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${empty detail.accountBalanceAfter ? 0 : detail.accountBalanceAfter}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
          </tr>
          </c:forEach>
        </table>
        <c:if test="${empty detailList}">
        <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
        </c:if>
      </div>
      <div class="page">
		<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/user_center/centerIndex.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm">
			<domi:paramTag name="pid" value="${pid}"/>
			<domi:paramTag name="dictionaryId" value="${dictionaryId}"/>
			<domi:paramTag name="flag" value="${flag}"/>
		</domi:pagination>
	</div>
    </div>
</div>
</div>
<div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/qihuo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    $(function(){
	    $(".wenti").tipsy({gravity: 's'}); 
    });

    function closePro(id){
    	jConfirm("确认要关闭此方案吗？","操作提示",function(res){
			if(res){
				$.post("${ctx}/user_program/programClose.htm?id="+id,null,function(d){
					location.href=location.href;
				});
			}
		});
    }
    function delpro(id){
    	jConfirm("确认要删除此方案吗？","操作提示",function(res){
			if(res){
				$.post("${ctx}/user_program/programDelete.htm?id="+id,null,function(d){
					if(d==-1){
						jAlert("方案状态已变更，暂时不能删除！","提示",function(){
							location.href=location.href;
			            });
						return false;
					}
					jAlert("方案删除成功！","提示",function(){
						location.href=location.href;
			        });
				});
			}
		});
    }
	function detail(flag,id){
		if(flag==1){
			var f=$("#short"+id).css("display");
			if(f=='none'){
				$("#short"+id).slideDown(500);
				$("#shortIcon"+id).removeClass("jt_1").addClass("jt_0");
			}else{
				$("#short"+id).slideUp(500);
				$("#shortIcon"+id).removeClass("jt_0").addClass("jt_1");
			};
		}else{
			var f=$("#long"+id).css("display");
			if(f=='none'){
				$("#long"+id).slideDown(500);
				$("#longIcon"+id).removeClass("jt_1").addClass("jt_0");
			}else{
				$("#long"+id).slideUp(500);
				$("#longIcon"+id).removeClass("jt_0").addClass("jt_1");
			};
		};
	};
	
	
	function toBbs(){
	<c:if test="${!empty fuUser&&empty fuUser.nickName}">
    	$.alerts.okButton='确定';
		jAlert('对不起，请先设置网站昵称，然后登录论坛激活您的账号！（论坛账号是你手机号，密码是手机号后六位）','提示',function(){
		    location.href="${ctx}/user_center/personInfo.htm";
		});
	</c:if>
	<c:if test="${!empty fuUser.nickName||empty fuUser}">
    	window.open('http://bbs.hhr360.com');
	</c:if>
}
}
</script>
<script>
function doSearch(){
	$("#searchForm").submit();
};

/*点击修改昵称*/
$(".center-change").hide();
$(".qianm-btn").click(function(){
		var qianm = $(this).parent().attr("class");
		if(qianm == "hgrzCenter"){
			$(this).parent().attr("class","hgrzCenter acv");
			$(".center-change").show();
			$(".center-all").hide();
		}else{
			$(this).parent().attr("class","hgrzCenter");
			$(".center-change").hide();
			$(".center-all").show();
		}
	});
</script>

<script type="text/javascript">
// 修改昵称
$("#updateName").click(function() {
	jConfirm('30天内只能修改一次,请谨慎操作', '确认', function(r) {
	    if (r) {
	    	var $userName = $("input[name='userName']").val();
	    	if (null == $userName || undefined == $userName || "" == $userName) {
	    		jAlert("请输入昵称","提示",function(){});
	    		return false;
	    	}
	    	if ($userName.length > 8) {
	    		jAlert("最多8个汉字","提示",function(){});
	    		return false;
	    	}
	    	$.post("${ctx}/user_login/updateUserName.htm", {userName: $userName}, function(data){
	    		if (data.success == 0) {
	    			jAlert(data.message,"提示",function(){
	    				$("input[name='userName']").val("");
	    		    	$(".qianm-btn").parent().attr("class","hgrzCenter");
	    				$(".center-change").hide();
	    				$(".center-all").show();
	    			});
	    			return false;
	    		} else {
	    			window.location.href="${ctx}/user_center/centerIndex.htm";
	    		}
	    	}, "json");
	    } else {
	    	$("input[name='userName']").val("");
	    	$(".qianm-btn").parent().attr("class","hgrzCenter");
			$(".center-change").hide();
			$(".center-all").show();
	    }
	});
	
});
</script>