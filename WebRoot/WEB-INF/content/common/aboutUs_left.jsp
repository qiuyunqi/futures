<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="hwdAbout" id="navLf">
	<div class="aboutTab">
	  <div class="aboutTit"><a><i class="indexAbout"></i>关于我们</a></div>
	  <ul class="aboutMenu intro">
       <li class="intrCompany"><a href="${ctx}/about">公司简介</a></li>
       <li class="map"><a href="${ctx}/contact">联系我们</a></li>
     </ul>
	</div>
   	 <div class="aboutTab ">
	  <div class="aboutTit"><a><i class="indexHelp"></i>帮助中心</a></div>
	  <ul class="aboutMenu helpCen">
       <li class="helpCenLi" ><a href="${ctx}/help">注册和登录</a></li>
       <li class="helpCenLi"><a href="${ctx}/help">充值和提现</a></li>
       <li class="helpCenLi"><a href="${ctx}/help">余券宝</a></li>
       <li class="helpCenLi"><a href="${ctx}/help">微期权</a></li>
     </ul>
	</div>
	<div class="aboutTab">
	  <div class="aboutTit"><a><i class="indexSaf"></i>保障和协议</a></div>
	  <ul class="aboutMenu safeXY">
       <li class="agreement_hhr"><a class="bankvi" href="${ctx}/index_guide/agreement_hhr.htm">服务协议</a></li>
       <li class="agreement_yqbClear"><a class="bankvi" href="${ctx}/index_guide/agreement_yqbClear.htm">余券宝合作协议</a></li>
       <li class="agreement_wqq"><a class="bankvi" href="${ctx}/index_guide/agreement_wqq.htm">微期权委托协议</a></li>
     </ul>
	</div>
</div>
<script>
  $(".aboutTab").each(function(){
  	$(".aboutTit").click(function(){
    $(this).next().toggle();
  });
});

window.onscroll=function(){
	var scrolltop = document.documentElement.scrollTop+document.body.scrollTop;
	if(scrolltop<250){
		$("#navLf").attr("class","hwdAbout");
	}else{
		$("#navLf").attr("class","hwdAbout2");
	}
};
</script>