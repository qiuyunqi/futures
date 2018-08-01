<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－添加开户账号</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style type="text/css">
	body{background: #c7c7c7;}
	.mfzc_bg_cont{ border:1px solid #dfdfdf;border-radius:20px;margin:60px auto !important;height:580px !important;}
	.mfzc_matter{margin: 40px auto !important;}
	.title{height:50px;background: #f7f7f7;text-align: center;line-height: 50px;color:black;border-bottom:1px solid #dfdfdf;border-radius:20px 20px 0 0;font-size: 18px;}
	.number{height: 38px;margin: 0 0 0 10px;vertical-align: middle;width: 70px; float: left;line-height: 38px;}
	.wbk{margin-left:80px;}
	.hy{margin-left: 100px;}
	.next{margin-top:0px !important;} 
	.jiaoyibtn{margin-top:25px;}
	.jiaoyibtn a{width:155px;height:40px;background: #00bbff;color:#fff;text-align: center;line-height: 40px;font-size: 15px;float:left;}
	.backIndex{margin:0 20px 0 123px;}
</style>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class=" mfzc_add" id="div1">
	<div class="mfzc_bg_cont">
	 <div class="title">添加交易账号</div>
      <div class=" mfzc_matter">
            <div class="biaodan zhuce_tbl">
            	<form id="stockForm">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody>
				    <tr>
				      <td><div class="number">开户姓名：</div><div class="wbk" ><input name="openUser" id="openUser" type="text" placeholder="开户姓名"></div></td>
				      <td><div class="biaodan_font onShow" id="phoneTip" style="width: 250px;padding-left: 5px">请输入开户姓名<a href="javascript:void(0);" class=" wenti" original-title="为了你的账号安全，只能绑定开户姓名本人的交易账号。获取更多帮助，请致电超级合伙人:010-53320537"></a></div></td>
				    </tr>
				    <tr>
				      <td><div class="number">开户券商：</div><div class="wbk" ><input name="openEquity" id="openEquity" type="text" placeholder="开户劵商"></div></td>
				      <td ><div class="onShow" id="nickNameTip"  style="width: 250px;padding-left: 15px">请输入开户券商</div></td>
				    </tr>
				    <tr>
				      <td><div class="number">营业部：</div><div class="wbk" ><input name="salesDept" id="salesDept" type="text" placeholder="营业部"></div></td>
				      <td ><div class="fl biaodan_font onShow" id="passwordTip"  style="width: 250px;padding-left: 5px">请输入营业部名称</div></td>
				    </tr>
				    <tr>
				      <td><div class="number">资金账号：</div><div class="wbk" ><input name="capitalAccount" id="capitalAccount" type="text" placeholder="资金账号"></div></td>
				      <td ><div class="fl biaodan_font onShow" id="repasswordTip"  style="width: 250px;padding-left: 5px">请输入资金账号</div></td>
				    </tr>
				    <tr>
				      <td><div class="number">账户类型：</div>
				      <select name="accountType" id="accountType" style="width:340px;height:30px;">
				      	<option value="1">普通账户</option>
				      	<option value="2">融资融券</option>
				      	<option value="3">信用账户</option>
				      </select></td>
				      <td><div class="onShow" id="invitationCodeTip"  style="width: 250px;padding-left: 15px">请选择账户类型</div></td>
				    </tr>
				    <tr>
				      <td><div class="number">交易密码：</div><div class="wbk" ><input name="capitalPassword" id="capitalPassword" type="password" placeholder="交易密码"></div></td>
				      <td><div class="onShow" id="phoneCodeTip"  style="width: 250px;padding-left: 15px">请输入交易密码</div></td>
				    </tr>
				  </tbody>
				</table>
				</form>
            </div>
            <div class="hy"><input id="isAgree" name="isAgree" type="checkbox" value="1" checked="checked"/> 我已阅读并签订<a href="javascript:void(0);" onclick="window.open ('${ctx}/agreement_stock.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" class="jkxy">《解套者联盟合作协议》</a></div>
             <div class="jiaoyibtn">
		  		<a class="backIndex" href="${ctx}/user_stock/stockIndex.htm">取消</a>
		  		<a href="javascript:void(0)" onclick="saveStock()" class="next">完成</a>
		    </div> 
      </div>
    </div>
</div>
<div class="downban"></div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script src="../js_hhr/jquery.nouislider.all.min.js"></script>
<link href="../js_hhr/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$(".wenti").tipsy({gravity: 's'}); 
});

function saveStock(){
	var f=$("#isAgree").attr("checked");
	if(f!='checked'){
		sureInfo("确定","请阅读并同意《解套者联盟合作协议》！","提示");
		return false;
	}
	var openUser=$("#openUser").val();
	if(!openUser){
		jAlert("请输入开户姓名！","提示",function(){
			$("#openUser").focus();
        });
		return false;
	}
	var openEquity=$("#openEquity").val();
	if(!openEquity){
		jAlert("请输入开户劵商！","提示",function(){
			$("#openEquity").focus();
        });
		return false;
	}
	var salesDept=$("#salesDept").val();
	if(!salesDept){
		jAlert("请输入营业部名称！","提示",function(){
			$("#salesDept").focus();
        });
		return false;
	}
	var capitalAccount=$("#capitalAccount").val();
	if(!capitalAccount){
		jAlert("请输入资金账号！","提示",function(){
			$("#capitalAccount").focus();
        });
		return false;
	}
	var capitalPassword=$("#capitalPassword").val();
	if(!capitalPassword){
		jAlert("请输入交易密码！","提示",function(){
			$("#capitalPassword").focus();
        });
		return false;
	}
	var data=$("#stockForm").serialize();
	$.post("${ctx}/user_stock/saveStockAccount.htm",data,function(d){
		if(d==-1){
			jAlert("资金账号已存在，请重新输入！","提示",function(){
				$("#capitalAccount").val("");
				$("#capitalAccount").focus();
	        });
			return false;
		}
		jAlert("账户添加成功！","提示",function(){
			location.href="${ctx}/user_stock/stockIndex.htm";
        });
	});
}
</script>
