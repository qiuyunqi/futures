<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
    <div class=" fucheng_title"><span>提前清算</span><div class="clr"></div></div>
	<div class=" zhuyi_0">
    	<p class="zhuyi">注意:</p>
    	<p>1.请确保您的交易账户中<span style="color:#CC6600;font-size:18px;">没有任何持仓</span>，才能申请提前清算。</p>
        <p>2.清算是指停止账户交易，系统会取消所有交易权限，回收融资资金，交易账户剩余资金退还至网站账户。</p>
        <p>3.清算时间为工作日的9:00-15:00。</p>
    </div>   
    <div style="text-align:center; margin:20px 0 0px 20px; padding-bottom:10px;">
        <a id="sure" href="javascript:void(0);" onclick="overInfo();" class=" queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
    </div>
    <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
</div>
<script>
	$("#load").hide();
	function overInfo(){
	    jConfirm("确定清算当前方案？（注意：清算主方案会同时清算增配子方案）","操作提示",function(res){
			if(res){
				$("#load").show();
				$("#sure").attr("onclick", "");
				$.post("${ctx}/user_program/saveOverProgramAjax.htm?id=${id}",null,function(d){
					var json=eval("("+d+")");
					if(json.result=="非清算时间"){
						$.alerts.okButton="确定";
						jAlert(json.result+"清算编号："+json.proId+"，清算执行失败，您的申请时间不在规定时间，导致您的方案无法正常清算。清算申请时间为：工作日9:00~15:00，请在规定时间进行清算申请。如有任何疑问，请拨打客服咨询电话：010-53320537。","提示",function(){
							location.href=location.href;
						});
						return false;
					}
					if(json.result!="清算成功"&&json.result!="非清算时间"){
						$.alerts.okButton="确定";
						jAlert(json.result+"清算编号："+json.proId+"，清算执行失败，由于您交易账户仍有持仓（或账户已穿仓），导致您的方案无法正常清算。如有任何疑问，请拨打客服电话进行咨询：010-53320537（周一至周五，8：30-17：00）。","提示",function(){	
							location.href=location.href;
						});
						return false;
					}
					$.alerts.okButton="确定";
					jAlert("清算申请成功，请保证您的交易账户无持仓，处理结果将在盘后短信通知！","提示",function(){
						location.href=location.href;
					});
				});
			}
		});
	}
	
</script>