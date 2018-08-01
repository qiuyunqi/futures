<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<meta name="google" content="notranslate">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="yes">
<meta name="x5-fullscreen" content="true">
<meta name="browsermode" content="application">
<meta name="x5-page-mode" content="app">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－我要找券</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
#title{width:70%;}
input[type=checkbox] {
	visibility: hidden;
}
input ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #646473;
		}
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #646473;
		}
input::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #646473;
		}
input:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #646473;
}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
			<form id="findTicketForm" method="post">
		       <div class="ftTit">
		       		<label class="blueCol smallSize">找券标题：</label>
		       		<input id="title" name="title" class="soSmaSize" type="text" value="" maxlength="25" placeholder="标题请不要过长哦，限25个字"/>
		       </div>
		       <div class="ftBody">
		       		<label class="blueCol smallSize">需求描述：</label>
		       		<textarea id="description" name="description" class="soSmaSize" rows="10"  placeholder="描述一下要找的券，将股票市值，可用资金以及收益分配比例做说明，限1000字。" ></textarea>
		       </div>
		       <div class="checksYqb">
					    <div class="monyLogBox">
							<div class="checkboxFive">
								<input type="checkbox" value="1" id="checkboxFiveInput" name="" checked="checked"/>
								<label for="checkboxFiveInput"></label>
							</div>
							<span>我已阅读并签订</span>
							<a class="monyCheckTit" href="javascript:void(0);" onclick="window.open ('${ctx}/agreement_yqbClear.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')">《余券宝合作协议》</a>
						</div>
					</div>
				<a class="giveYqb" href="javascript:void(0);" onclick="addTicket()">提交</a>
			</form>
			</div>
		</div>
	</div>
</body>
</html>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
//标题限制25个字
 $(function(){
    $("#title").keyup(function(){
	    var area=$(this);
	    var max=parseInt(area.attr("maxlength"),25); //获取maxlength的值
		    if(max>0){
		    if(area.val().length>max){ //textarea的文本长度大于maxlength
		    area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
	    	}
	    }
	});
    //复制的字符处理问题
    $("#title").blur(function(){
	    var area=$(this);
	    var max=parseInt(area.attr("maxlength"),25); //获取maxlength的值
		    if(max>0){
		    if(area.val().length>max){ //textarea的文本长度大于maxlength
		    area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
		    }
   		 }
   	 });
    });
//立即支付
	function addTicket(){
		var f=$("#checkboxFiveInput").attr("checked");
		if(f!='checked'){
			layer.open({
 			    content: "阅读并同意余劵宝合作协议！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;
		}
		var title=$("#title").val();
		if(!title){
			layer.open({
 			    content: "找劵标题不能为空！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			        $("#title").focus();
 			    }
 			});
			return false;	
		}
		var description=$("#description").val();
		if(!description){
			layer.open({
 			    content: "需求描述不能为空！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			        $("#description").focus();
 			    }
 			});
			return false;	
		}
		var data=$("#findTicketForm").serialize();
		$.post("${ctx}/wxyqb/saveFindTicket.htm?userId="+${fuUser.id},data,function(d){
 			 location.href="${ctx}/wxyqb/findIntroduceSuccess.htm";
		})
	}
</script>