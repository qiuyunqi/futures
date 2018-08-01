<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－后台管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
	.newgiude_icont{margin: 15px 10px 0 !important;}
	.lf_nav_title{font-size:17px;}
	.lf_nav_small{ border-left: 5px solid #efefef;border-bottom: 1px solid #fff;box-shadow: 1px 1px #fff;-moz-box-shadow:1px 1px #fff; -webkit-box-shadow:1px 1px #fff;display: block;height: 50px;text-indent: 22px;line-height:50px;}
	.newg_icont{display: block;width:19px;height:11px;background: url("../images_hhr/helpUp.png") no-repeat center;float: right;margin: 5px 53px;cursor: pointer;}
	.lf_smallNav{display:none;}
	.lf_nav{height:auto !important;}
	.frame{float:right;width:100%;height:100%;}
	.lf_nav ul li{border-bottom: 1px solid #fff;box-shadow: 1px 1px #fff;-moz-box-shadow:1px 1px #fff; -webkit-box-shadow:1px 1px #fff;}
	</style>
</head>
<body>
<c:if test="${empty admin}">
<c:redirect url="${ctx}/admin_login/adminLogin.htm"></c:redirect>
</c:if>
<div class="top">
<div class=" top_title fl">后台管理</div>
    <div class="fr grzx"><span class="geren">您好，${admin.account}&nbsp;&nbsp;[${roleName}]</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="changeIframe('${ctx}/admin_list_user/editPasswordAjax.htm')" class="tuichu">修改密码</a><span class="shuxian">|</span><c:if test="${admin.account != null}"><a href="javascript:void(0);" onclick="logout();" class="tuichu">退出</a></c:if></div>
    <div class="clr"></div>
</div>
<div class="content">
	<div class="lf_nav" style="z-index:999;">
		<s:iterator value="#application.topPrivilegeList">
 			<domi:menu name="${name}">
 				<li class="lf_nav_title" value="1"><a class="lf_nav_small" href="javascript:vodi(0);" ><b>${name}</b><em class="newgiude_icont"></em></a></li>
			</domi:menu>
 			
 				<ul class="lf_smallNav">
	 			<s:iterator value="children" status="row">
	  				<domi:menu name="${name}">
						<li name="urli"><a target="iframepage"  href="javascript:void(0);" onclick="changeIframe('${ctx}${url}')"><i></i>${name}<!-- <span style="color:red;" id="message"></span> --></a></li>
					</domi:menu>
	  			</s:iterator>
  				</ul>
 		</s:iterator>
    </div>
    <div class="frame"><iframe class="video" src="${ctx}/admin_login/welcome.htm" frameborder=0  marginwidth=0 marginheight=0 hspace=0 vspace=0  scrolling=no width="100%" height=100% id="iframepage" name="iframepage" onLoad="iFrameHeight()"  ></iframe></div>
</div>
</body>
</html>
<script type="text/javascript">
	$("li[name='urli']").click(function(){
	$("li[name='urli']").removeClass();
  	$(this).addClass("yxz");
	});

	function logout(){
		$.post("${ctx}/admin_login/logoutAjax.htm",null,function(date){
			if(date == "-1"){
				location.href="${ctx}/admin_login/adminLogin.htm";
			}else if(date == "-2"){
				sureInfo("确定","请您先登录","提示");
				location.href="${ctx}/admin_login/adminLogin.htm";
			}
		});
	};
	
	//iframe自适应高度
	  function iFrameHeight() {
        	var ifm= document.getElementById("iframepage");
        	var subWeb = document.frames ? document.frames["iframepage"].document :ifm.contentDocument;
            if(ifm != null && subWeb != null) {
            	ifm.height = subWeb.body.scrollHeight;
            }
    };
    
    //iframe右边页面跳转，参照最后一个li
	  function changeIframe(url){
        var urlValue = url;
        document.getElementById("iframepage").src = urlValue;
    }
    
	 $(document).ready(function(){
	 //导航栏折叠
		 $(".lf_nav .lf_nav_title").click(function(){
             $(this).find("em").toggleClass("newg_icont");
             $(this).next().toggle();
             var index = $(".lf_nav .lf_nav_title").index(this);
             $(".lf_nav .lf_nav_title").each(function(i){
                 if(i != index){
                     if($(this).next().css("display")=="block"){
                     	$(this).find("em").toggleClass("newg_icont");
                         $(this).next().css("display","none");
                     }
                 }
             });
         });
	});
	
	
	
	
	/* $(function(){
		tongji();
		setInterval(tongji,1000*60);
    }); */
    
    function tongji(){
        $.post('${ctx}/admin_login/staticRemindAjax.htm?first=${first}',null,function(d){
             var remindObj = eval("("+d+")");
             if(${first==1}){
	             if((remindObj.messageCount>0)){
	            	 $('#message').html("("+remindObj.messageCount+")");
	             }
             }
             if(${first==2}){
	             if((remindObj.drawMoneyCount>0)){
	            	 $('#draw').html("("+remindObj.drawMoneyCount+")");
	             }
	             if((remindObj.rechargeCount>0)){
	            	 $('#recharge').html("("+remindObj.rechargeCount+")");
	             }
	             if((remindObj.exchangeCount>0)){
	            	 $('#exchange').html("("+remindObj.exchangeCount+")");
	             }
	             if((remindObj.identityCount>0)){
	            	 $('#identity').html("("+remindObj.identityCount+")");
	             }
	             if((remindObj.qualiCount>0)){
	            	 $('#quali').html("("+remindObj.qualiCount+")");
	             }
             }
             if(${first==3}){
            	 if((remindObj.programCount>0)){
	            	 $('#waitProgram').html("("+remindObj.programCount+")");
	             }
	             if((remindObj.feeCount>0)){
	            	 $('#fee').html("("+remindObj.feeCount+")");
	             }
	             if((remindObj.safeMoneyCount>0)){
	            	 $('#safeMoney').html("("+remindObj.safeMoneyCount+")");
	             }
	             if((remindObj.drawProfitsCount>0)){
	            	 $('#drawProfits').html("("+remindObj.drawProfitsCount+")");
	             }
	             if((remindObj.continueCount>0)){
	            	 $('#continue').html("("+remindObj.continueCount+")");
	             }
	             if((remindObj.overProgramCount>0)){
	            	 $('#overProgram').html("("+remindObj.overProgramCount+")");
	             }
	             if((remindObj.overProgramCount>0)){
	            	 $('#offlineOverProgram').html("("+remindObj.overProgramCount+")");
	             }
	             if((remindObj.badCreditCount>0)){
	            	 $('#badCreditCount').html("("+remindObj.badCreditCount+")");
	             }
             }
        });
    }
</script>