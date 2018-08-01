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
<title>${title}－设置昵称</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
input ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #646473 !important;
		}
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #646473 !important;
		}
input::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #646473 !important;
		}
input:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #646473 !important;
}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
				<div class="setName"><input id="nickname" class="textInde" type="text" placeholder="设置昵称"/></div>
				<span onclick="saveNickName(${fuUser.id})" class="sveBt">保存</span>
			</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script type="text/javascript">
function saveNickName(id){
	//验证昵称
    var reg = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{1,13}[a-zA-Z0-9\u4e00-\u9fa5]$/;       
    if (!reg.test($("#nickname").val())){
    	layer.open({
		    content: '输入的昵称格式不正确，3-15位字母、中文（占两位）、数字或下划线，且不能以下划线和数字开头，不能以下划线结尾！',
		    btn: ['确定'],
		    shadeClose: false,
		    yes: function(){
		        layer.closeAll();
		        $("#nickname").val("");
		    	$("#nickname").focus();
		    }
		});
		return false;
    }   
    var nickname = $("#nickname").val();
	if(!nickname){
		layer.open({
		    content: '请输入昵称！',
		    btn: ['确定'],
		    shadeClose: false,
		    yes: function(){
		        layer.closeAll();
		    }
		});
		return false;
	}
	$.post("${ctx}/wxyqb/saveNickName.htm?userId="+id+"&nickName="+nickname,null,function(d){
		if(d==-1){
			layer.open({
			    content: '昵称已存在，请更换！',
			    btn: ['确定'],
			    shadeClose: false,
			    yes: function(){
			        layer.closeAll();
			        $("#nickName").focus();
			    }
			});
			return false;
		}
        if(d==1){
	        layer.open({
   			    content: '保存成功！',
   			    btn: ['确定'],
   			    shadeClose: false,
   			    yes: function(){
   			        layer.closeAll();
   			        location.href = "${ctx}/wxyqb/userInfo.htm";
   			    }
   			});
        }
	});
}
</script>