<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－充值申请完成</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div style="height:40px"></div>
<div style=" width:450px; margin:0 auto">
      		<div class="fl"><img src="${ctx}/qihuo_images/cg_03.png" width="70" height="70"></div>
        <div class="fl zccg">
        	<p style=" font-size:26px; line-height:70px; font-weight:600; color:#343434">
        	<c:if test="${Pay=='ok'}">充值申请完成，</c:if><c:if test="${!empty program.addMatchId}">扩充融资</c:if><c:if test="${!empty program.subMatchId}">减配</c:if>方案发起成功</p>
        </div>
           <div class="clr"></div>
      </div>
      <c:if test="${empty program.subMatchId}">
      <div style="text-align:center; margin:40px 0 25px;">工作时间1个小时内完成交易账户开户，其他时间为下个交易日09：15前，完成交易账户开户后，我们将短信通知您</div>
      </c:if>
      <div style="text-align:center; margin:0px 0 30px 20px;">
   	<span>您现在可以进入：</span>
     <a id="programDetail" href="${ctx}/user_program/programDetail.htm?id=${id}" class="sure" style="margin-right:20px">方案页面</a>
     <a href="${ctx}/user_center/centerIndex.htm" class="sure" style="margin-right:20px">个人中心</a>
     <a href="${ctx}/index_guide/softwareDownload.htm" class="sure" style="margin-right:20px">软件下载</a>
 </div>
   <div class=" zdtx zdtx1">
        	<div>重点提醒：请在账户余额内存足够的资金以便按约定收益分成</div>
            <div>资金风控：亏损警戒线<span style="color:#f28208; font-weight:600"><fmt:formatNumber value="${program.warnLine}" pattern="#,###,##0.00"/></span>元，亏损平仓线<span style="color:#c30300; font-weight:600"><fmt:formatNumber value="${program.closeLine}" pattern="#,###,##0.00"/></span>元</div>
            <div>开户服务器：${empty program.tradeServiceName?'暂无':program.tradeServiceName}</div>
            <div>开户IP地址：${empty program.tradeIp?'暂无':program.tradeIp}</div>
            <div>服务器交易端口：${empty program.tradePort?'暂无':program.tradePort}</div>
            <div style="margin:8px 0"><span style="color:#c30300;font-weight:600 ">交易账户：通过短信下发通知、敬请留意。</span>(为了您的资金安全，请妥善保管好密码)</div>
            <div><a href="${ctx}/index_guide/softwareDownload.htm" class=" gzsm" style="color:#555555; text-decoration:none">进入交易软件下载页面</a></div>
  </div> 
<div class="ddwdesc clearfix">
	<ul>
    	<li><img src="../qihuo_images/xtz_03.jpg" width="64" height="64"><span>即时申请，即时交易</span></li>
        <li><img src="../qihuo_images/xtz_05.jpg" width="64" height="64"><span>收益最大可放大10倍</span></li>
        <li><img src="../qihuo_images/xtz_07.jpg" width="64" height="64"><span>100%真实的实盘交易</span></li>
        <li><img class="pzliimgF" src="../qihuo_images/xtz_12.jpg" width="64" height="64"><span>最低1000元起即可申请</span></li>
        <!-- <li><span><img src="../qihuo_images/xtz_13.jpg" width="64" height="64"></span>2天-30天，想用几天就几天</li> -->
        <li class="clearfixLi"><img class="pzliimg" src="../qihuo_images/xtz_14.jpg" width="64" height="64" ><span>严格止损，控制资金风险</span></li>
    </ul>
    <div class="clr"></div>
</div>
<div class="ddwdetail">
  	<h3>操盘须知</h3>
  	<div class="pzhr"></div>
  	<p class="addpeizp">全程只需分成约定收益，<br>无其他任何费用，期市<br>有风险，投资需谨慎！<br>市场风险莫测，务请谨<br>慎行事！</p>
   <!--  <p>2、资金使用期限：最短1天，最长30天，按照约定收益分成，默认每天自动延期，自动从账户余额扣除约定收益（余额不足时我们有权自动终止方案和从交易账户内出金扣除约定收益）；</p> -->
    <p class="addpeizp" >风险保证金：在您操盘出<br>现亏损后用于支付亏损金<br>，结束时如无亏损全额退<br>还，保证金比例越低收益<br>越大，同时风险也越大；</p>
    <p class="addpeizp" >亏损警戒线：当总操盘资<br>金低于警戒线以下时，只<br>能平仓不能建仓，需要尽<br>快补充风险保证金，以免<br>低于亏损平仓线被平仓；</p>
    <p class="addpeizp" >亏损平仓线：当总操盘资<br>金低于平仓线以下时，我<br>们将有权把您的期货进行<br>平仓，为避免平仓发生，<br>请时刻关注风险保证金是<br>否充足；</p>
    <p style="width: 16%;float:left;" >开始交易时间：一般选择<br>下个交易日，如看中行情<br>急需交易，可直接选择今<br>天交易（今天开始计算约<br>定收益分成）。</p>
</div>
<div class="downban"></div>
<%@include file="../common/bottom.jsp" %>
<%@include file="../common/footer.jsp" %>
</body>
</html>












