<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－期货大赛首页</title>
<%@ include file="../common/css.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</head>
<body style="background:#fafafa">
<c:set value="2" var="ipg"></c:set>
<%@ include file="../common/userTop.jsp" %>
<div class="banner banner1">
	<ul>
    	<li><a href="javascript:void(0);" class=" banner_02"></a></li>
    </ul>
    <div class=" bmrs">
    	<div class="bmrs_cent">
            <div class="bmrs_cont fl"><fmt:formatDate value="${g2.gameTime}" pattern="yyyy年MM月"/>${empty g2.gameTime?'（暂无）':''}期货大赛报名人数：<i><fmt:formatNumber value="${empty g2.joinNum?0:g2.joinNum}" pattern="#,###,###"/></i> 人<div class=" xjb"></div></div>
            <div class="bmrs_cont fr"><fmt:formatDate value="${g1.gameTime}" pattern="yyyy年MM月"/>${empty g1.gameTime?'（暂无）':''}期货大赛报名人数：<i><fmt:formatNumber value="${empty g1.joinNum?0:g1.joinNum}" pattern="#,###,###"/></i> 人</div>
            <div class="clr"></div>
        </div>
    </div>
</div>


<div class=" matter">
	
    <div class="matter_cont">
    	<div class=" lct lct_pos"><img src="${ctx}/qihuo_images/tz31_03.png" width="867" height="129">
      		<div class=" sjpm"><fmt:formatDate value="${time1}" pattern="MM月dd号"/></div>
            <div class=" sjpm0"><fmt:formatDate value="${time2}" pattern="MM月dd号"/></div>
            <div class=" sjpm1"><fmt:formatDate value="${time3}" pattern="MM月dd号"/></div>
      </div>
      <div class=" qhyl"><img src="${ctx}/qihuo_images/qhds_09.png" width="627" height="69" /></div>
      <div class=" mingci">
      	   <div class="fl first"><div class=" award"><span class="symbol">￥</span><span>30000</span></div><p class="xjjl">(现金奖励)</p></div>
           <div class="fl second"><div class=" award"><span class="symbol">￥</span><span>20000</span></div><p class="xjjl">(现金奖励)</p></div>
           <div class="fl third"><div class=" award"><span class="symbol">￥</span><span>10000</span></div><p class="xjjl">(现金奖励)</p></div>
           <div class="clr"></div>
      </div>
       
       <div class=" ylph">
       		<div class="fl">
            	<div class=" zzcy"><span class=" zs fl">您交</span><span class=" cs fl"><fmt:formatNumber value="${safeMoney}" pattern="#,###,###.##"/></span><span class=" zs fl">元比赛保证金</span><span class="hs fr">(结束时如无亏损全额返还,如亏损扣除亏损剩余返还)</span><div class="clr"></div></div>
                <div class=" zzcy"><span class=" zs fl">配送</span><span class=" cs fl"><fmt:formatNumber value="${matchMoney}" pattern="#,###,###.##"/></span><span class=" zs fl">元实盘资金</span><div class="clr"></div></div>
				<div class=" zzcy"><span class=" zs fl">总共</span><span class=" cs fl"><fmt:formatNumber value="${totalMoney}" pattern="#,###,###.##"/></span><span class=" zs fl">元实盘资金</span><span class="hs fr">(由您操盘，盈利归您，亏损在您的保证金中扣除))</span><div class="clr"></div></div>
                <div class=" zzcy"><span class=" zs fl">比赛</span><span class=" cs fl">1</span><span class=" zs fl">个月</span><span class="hs hs0 fr">(11月30日公布战绩，发放奖金)</span></div>
                <div class=" zzcy"><span class=" zs fl">盈利归你，亏损自负</span><span class="hs fr">(结束时如无亏损全额返还,如亏损扣除亏损剩余返还)</span><div class="clr"></div></div>
                <div class=" guize">如您不清楚规则，或有其他疑问，请联系客服：<span class="dhhm">010-53320537，工作时间：8:30-17:00</span></div>
                <a href="javascript:void(0);" onclick="toJoin();" class=" sure wycy">我要参与</a>
            </div>
            <div class="fr phb">
            	<div class=" yl_title"><span>本周</span>盈利排行榜</div>
                <div class="tgyjb_cont phb_cont">
           		<ul>
           			<c:forEach items="${weekList}" var="week" varStatus="row">
           				<li><span class="pm pm0 ${row.index<=2?'dym':''} fl">${row.index+1}</span><span class="fl">${week.fuUser.accountName}</span><span class="fr"><fmt:formatNumber value="${empty week.gameIncomeWeek?0:week.gameIncomeWeek}" pattern="#,###,###.00"/>元</span><div class="clr"></div></li>
           			</c:forEach>
                	
                    
                </ul>
           </div>
           <div class=" yl_title"><span>本月</span>盈利排行榜</div>
           <div class="tgyjb_cont phb_cont">
           		<ul>
                	<c:forEach items="${monthList}" var="month" varStatus="row">
           				<li><span class="pm pm0 ${row.index<=2?'dym':''} fl">${row.index+1}</span><span class="fl">${month.fuUser.accountName}</span><span class="fr"><fmt:formatNumber value="${empty month.gameIncomeMonth?0:month.gameIncomeMonth}" pattern="#,###,###.00"/>元</span><div class="clr"></div></li>
           			</c:forEach>
                </ul>
           </div>
            </div>
            <div class="clr"></div>
       </div>
        
     </div> 
</div>

<%@include file="../common/footer.jsp" %>

</body>

</html>
<script>
	function toJoin(){
		<c:if test="${empty fuUser}">
			lgInfo(0);	
			return false;
		</c:if>
		jConfirm("您确定要参与吗？","参与期货大赛提示",function(res){
			if(res){
				$.post("${ctx}/user_game/saveGameInfoAjax.htm",null,function(d){
					if(d==-2){
						sureInfo('确定','系统参数中的比赛金额还没有设置，请联系客服！','提示');
						return false;
					}
					if(d==-3){
						$.fancybox.open({
					 			href : '${ctx}/user_center/personNameAjax.htm?flag=1&methodName=toJoin',
					 			type : 'ajax',
					 			padding : 5 
					 	});
						/*$.alerts.okButton="去认证";
						$.alerts.cancelButton="返回";
						
						jConfirm("请先进行实名认证后，再进行操作！","操作提示",function(res){
							if(res){
								location.href="${ctx}/user_center/safeInfo.htm";
							}
						});*/
						return false;
						toJoin();
					}
					if(d==-4){
						sureInfo('确定','您已经参加了比赛！','提示');
						return false;
					}
					
					if(d==-5){
						$.alerts.okButton="去充值";
						$.alerts.cancelButton="返回";
						
						jConfirm("您的期货大赛需要支付<fmt:formatNumber value='${safeMoney/100}' pattern='#,###.00'/>元,当前余额为<fmt:formatNumber value='${empty fuUser.accountBalance?0:fuUser.accountBalance/100}' pattern='#,###.00'/>元.您还需要支付<fmt:formatNumber value='${(safeMoney-fuUser.accountBalance)/100}' pattern='#,###.00'/>元。","操作提示",function(res){
							if(res){
								location.href="${ctx}/user_recharge/recharge.htm?money=${(safeMoney-fuUser.accountBalance)/100}";
							}
						});
						return false;
					}
					if(d==-6){
						sureInfo('确定','该期货大赛不存在！','提示');
						return false;
					}
					if(d==1){
						sureInfo('确定','参加比赛成功！','提示');
					}
					
				});
			}
			
		});
	}
</script>
