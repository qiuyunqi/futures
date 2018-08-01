<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
<form id=safeForm>
	<input id="status" type="hidden" value="${pro.status}">
	<div class=" fucheng_title"><span>追加保证金</span><div class="clr"></div></div>
    	<div class=" ktqlr" style="text-align: left;text-indent: 70px;"><span class="b1">当前余额：</span><span class="b2">
    	<fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00" /></span><span class="b1">元</span></div>
      <div class="form marg0" style="margin:0 0 3 0px 50px;">
        	<div class="fl form_font lh">追加金额：</div>
            <div class="fl input tqlr"><input id="money" name="money" type="text"></div>
            <div class="fl lh0">元</div>
            <div class="clr"></div>
     </div>
     <div style="text-align:center; margin:30px 0 0px 20px;">
        <a id="sure" href="javascript:void(0);" onclick="saveSafe();" class="queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
     </div>
     <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
</form>
	<div class=" zhuyi_0">
    	<p class="zhuyi">注意:</p>
    	<p>1.您可以随时将余额内的资金实时追加到账户内权益。</p>
        <p>2.请确保在账户预警后及时追加，以免到达平仓线。</p>
    </div>    
</div>
<script>
	$(function(){
		if($("#status").val()==7){
			$("#money").attr("placeholder","请追加"+formatCurrency(${money})+"元解除穿仓状态")
		}
	});
	
	/** 
	 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
	 * 
	 * @param num 数值(Number或者String) 
	 * @return 金额格式的字符串,如'1,234,567.45' 
	 * @type String 
	 */  
	function formatCurrency(num) {  
	    num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))  
	    num = "0";  
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num*100+0.50000000001);  
	    cents = num%100;  
	    num = Math.floor(num/100).toString();  
	    if(cents<10)  
	    cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	    num = num.substring(0,num.length-(4*i+3))+','+  
	    num.substring(num.length-(4*i+3));  
	    return (((sign)?'':'-') + num + '.' + cents);  
	} 

	$("#load").hide();
	function saveSafe(){
		var m=$("#money").val();
		if(isNaN(m)){
			$.alerts.okButton='确定';
			jAlert('追加保证金的金额只能填入数字！','提示',function(){
				$("#money").focus();
			});
			return false;
		}
		if(m.indexOf(".")>-1){
			$.alerts.okButton='确定';
			jAlert('追加保证金的金额不能为小数！','提示',function(){
				$("#money").focus();
			});
			return false;
		}
		if(m<0){
			$.alerts.okButton='确定';
			jAlert('追加保证金的金额不能为负数！','提示',function(){
				$("#money").focus();
			});
			return false;
		}
	    jConfirm("确定追加保证金？","操作提示",function(res){
			if(res){
				$("#load").show();
				$("#sure").attr("onclick", "");
				var data=$("#safeForm").serialize();
				$.post("${ctx}/user_program/saveConfirmMoneyAjax.htm?id=${id}",data,function(d){
					if(d==-2){
						$.alerts.okButton='确定';
						jAlert('请输入您要追加保证金的金额！','提示',function(){
							location.href=location.href;
						});
						return false;
					}
					if(d==-3){
						var money=$("#money").val();
						var mn=money-${empty fuUser.accountBalance?0:fuUser.accountBalance};
					
						$.alerts.okButton="去充值";
						$.alerts.cancelButton="返回";
						
						jConfirm("您的要追加的金额为"+numeral(money).format('0,0.00')+"元,当前余额为<fmt:formatNumber value='${empty fuUser.accountBalance?0:fuUser.accountBalance}' pattern='#,###,##0.00'/>元.您还需要支付"+numeral(mn).format('0,0.00')+"元。","操作提示",function(res){
							if(res){
								location.href="${ctx}/user_recharge/recharge.htm?money="+mn;
							}
						});
						return false;
					}
					$.alerts.okButton="确定";
					jAlert("追加保证金申请成功，请刷新网页等待处理结果！","提示",function(){
						location.href=location.href;
					});
				});
			}
		});
	}
	
	
//禁用回车事件
document.onkeydown = function(event) {  
    var target, code, tag;  
    if (!event) {  
        event = window.event; //针对ie浏览器  
        target = event.srcElement;  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "TEXTAREA") { return true; }  
            else { return false; }  
        }  
    }else {  
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "INPUT") { return false; }  
            else { return true; }   
       }  
    }    
};  
	
</script>