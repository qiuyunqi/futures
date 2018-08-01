<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.alert.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.1.1.js"></script>
<div class="fuchen" style="width: 400px">
<form id="programForm">
	<input id="messageType" type="hidden" value="${messageType}"/>
	<div class=" fc_top" style="width: 400px">
    	<b class="fl fc_top_font">获取${messageType}验证码</b>
        <div class="fl"></div>
    </div>
    <div class="clear"></div>
     <div class="form_cont form_cont0 form_cont01" style="margin:10px auto;">
        <div class="lf_font fl"><input id="verifi" style="width:200px;font-size:25px;height:50px;" name="verification" type="text" placeholder="请输入验证码" value=""/></div>
        <div class="fl" style="float:right;"><img id="verification" src="${ctx}/verification.jsp" style="vertical-align:middle;width:150px;height:52px;"/><a href="javascript:void(0)" style="width:150px;height:50px;" onclick="flushVer()">看不清</a></div>
        <div class="clr"></div>
	</div>
    <div class=" but" style="text-align: center;"><a href="javascript:void(0);" id="msgbtn" onclick="checkCode();" class="sure fl" style="margin:0 5px 0 70px;">获取${messageType}验证码</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">关闭</a><div class="clr"></div></div>
</form>
</div>


<script>
    function flushVer(){//刷新验证码
    	document.getElementById("verification").src="${ctx}/verification.jsp?"+new Date().getTime();
    }
    flushVer();
    
    var sending=false;
    var start=60;
	function checkCode(){//先验证验证码图片通过再发送短信
		var data=$("#programForm").serialize();
		$.post('${ctx}/user_login/sendRegMsg.htm?phone=${phone}',data,function(d){
			var json=eval('('+d+')');
			if(json){
				if(json.code==-1){
					$("#verifi").focus();
					return;
				}
				if(json.code==-2){
					$("#verifi").focus();
					return;
				}
				sending=true;
				jAlert("已发送验证码，请注意查收！","提示",function(){
					$("#verifi").val("");//清空用户输入的验证码，避免多次发送短信
					setTimeout(countTime,1000);
					$("#msgbtn").css({color:'#ccc'});
					$("#msgbtn").text('60s后重试');
				});
			}
		});
	}
	
	function countTime(){
		var messageType=$("#messageType").val();
		start--;
		if(start<=0){
			flushVer();
			$("#msgbtn").css({color:'#fff'});
			$("#msgbtn").text("获取"+messageType+"验证码");
			$("#msgbtn").attr("onclick", "checkCode();");
			sending=false;
			start=60;
			return;
		}
		$('#msgbtn').text(start+"s后重试");
		setTimeout(countTime,1000);
		if(start>0){
			$("#msgbtn").attr("onclick", "");
		}
	}
</script>
