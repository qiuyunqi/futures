<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
  <div class="dindex" id="dindex">
    <form id="ajaxForm">
    <div class="denglu">
      <div class="dlclose"><a href="javascript:void(0)" onclick="$.fancybox.close();"><img src="../images_hhr/denglu03.png" /></a></div>
      <div class="dlname"><input type="text" name="accountName" id="accountName" value="${accountName}" placeholder="用户名"/></div>
      <div class="dlname dlpassword"><input name="password" id="password" type="password" value="${password}" placeholder="密码" /></div>
      <div class="dlwjmm"><input style="margin-left:-50px;vertical-align:middle;" name="rememberAccount" type="checkbox" <c:if test="${!empty accountName}">checked="checked"</c:if>  value="1"/><span style="color:#cfcfcf;margin-left:-70px;">记住账号</span><a href="${ctx}/user_login/findPwd.htm" style="margin-left:80px;">忘记登录密码</a></div>
      <div id="login" class="dlbut" onclick="toLgn();return false;" style="cursor:pointer;"><a href="javascript:void(0)">登录</a></div>
      <div class="dlmfzc"><a href="${ctx}/register">邀请注册</a></div>
      <div id="loadgif" class="loginLoad"><img src="../images_hhr/loading.gif" /></div>
    </div> 
    </form> 
  </div>
<script type="text/javascript" src="${ctx}/js/jquery.alert.js"></script>
<script type="text/javascript">
//网页内按下回车触发
$(document).keypress(function(e) { 
     // 回车键事件 
     if(e.keyCode == 13) { 
  	   $("#login").click(); 
     } 
}); 

$("#loadgif").hide();
function toLgn(){
	var data=$("#ajaxForm").serialize();
	$.post("${ctx}/user_login/doLoginAjax.htm",data,function(d){
		var json=eval('('+d+')');
		if(json.code==-1){
			jAlert("对不起，您的网络异常，如有疑问，请联系客服！","提示",function(){
	        });
			return false;
		}
		if(json.code==-2){
			sureInfo('确定','请输入账号或手机号或邮箱！','提示');
			return false;
		}
		if(json.code==-3){
			sureInfo('确定','请输入密码！','提示');
			return false;
		}
		if(json.code==-4){
			sureInfo('确定','登陆账号不存在,请注册！','提示');
			return false;
		}
		if(json.code==-5){
			sureInfo('确定','密码不正确！','提示');
			return false;
		}
		if(json.code==1){
			if(${flag==1}){
				$.fancybox.close();
				location.href=location.href;
			}else{
				$("#loadgif").show();
				var jsIframe = document.createElement("iframe");
                jsIframe.style.display = "none";//把jsIframe隐藏起来
                document.body.appendChild(jsIframe);
                with(window.frames[window.frames.length - 1]){
	                document.open();
	                document.write(json.ucsynlogin); //执行JS代码
	                document.close();
                }
                window.setTimeout(function(){location.href="${ctx}/user_center/centerIndex.htm"},1000);
			}
		}
	});
}
</script>