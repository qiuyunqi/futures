<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="freeForm">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">充值审核</b>
	        <div class="fl"></div>
	    </div>
	    <input type="hidden" name="type" value="${recharge.type}"/>
	    <div class="form_cont form_cont0">
            <div class="lf_font fl">收款账户：</div>
            <div class="fl input none">
            <c:if test="${recharge.proceedsType==0||recharge.proceedsType==null}">非银行转账，无收款账户</c:if>
            <c:if test="${recharge.proceedsType==1}">小合建行：3100 1587 6150 5003 2541</c:if>
            <c:if test="${recharge.proceedsType==2}">建设银行：6217 0000 1007 7384 314</c:if>
            </div>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">账户余额：</div>
            <div class="lf_fonts fl" style="line-height:35px;"><fmt:formatNumber value="${empty user.accountBalance?0.00:user.accountBalance}" pattern="#,###,##0.00"/>元</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none"><input type="radio" name="rechargeStatus" value="2" checked="checked" onclick="changeStatus(this.value)"/>同意<input type="radio" name="rechargeStatus" value="3" onclick="changeStatus(this.value)"/>拒绝</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">短信提醒：</div>
            <div class="fl textarea">
            <textarea id="msg" cols="" rows="">您的订单[${recharge.orderNum }]审核通过，充值金额为<fmt:formatNumber value='${empty recharge.rechargeMoney?0.00:recharge.rechargeMoney}' pattern='#,###,##0.00'/>元。</textarea>
            <select id="msgSelect">
            	<option value="对不起！您的转账金额与充值申请金额不符，请正确填写您的充值金额，并再次提交充值申请，谢谢！">充值申请金额不符</option>
            	<option value="您好！由于在24小时内未收到您的充值款项，系统自动关闭了您的充值申请，如有异常，请致电010-53320537，我们竭诚为您服务！">未收到您的充值款项</option>
            </select>
            </div>
            <div class="clr"></div>
        </div>
     <div class=" but">
     <domi:privilege url="/admin_op_recharge/saveRechargeAjaxTwo.htm">
     	<a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a>
     </domi:privilege>
     <domi:privilege url="/admin_op_recharge/noCheckRechargeAjax.htm">
     	<a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a>
     </domi:privilege>
     <div class="clr"></div></div>
</form>
</div>
<script>
$("#msg").show();
$("#msgSelect").hide();
var state=2;
function changeStatus(status){
	if(status==2){
		$("#msg").show();
		$("#msgSelect").hide();
	}else{
		$("#msg").hide();
		$("#msgSelect").show();
	}
	state=status;
}



function saveInfo(){
	jConfirm("确认充值审核结果？","操作提示",function(res){
		if(res){
			var smsg="";
			if(state==2){
				smsg = encodeURI($("#msg").val(), "UTF-8");
			}else{
				smsg = encodeURI($("#msgSelect").val(), "UTF-8");
			}
			
			var data=$("#freeForm").serialize();
			$.post("${ctx}/admin_op_recharge/saveRechargeAjaxTwo.htm?id=${id}&msg="+smsg,data,function(d){
				if(d==-1){
					sureInfo("确定","您没有操作权限！","提示");
					return false;
				}
				if(d==-2){
					sureInfo("确定","请选择你的审核结果！","提示");
					return false;
				}
				if(d==-3){
					sureInfo("确定","对不起此记录已被审核！","提示");
					return false;
				}
				location.href=location.href;
			});
		}
	});
}

function quxiao(){
	var data=$("#freeForm").serialize();
	$.post("${ctx}/admin_op_recharge/noCheckRechargeAjax.htm?id=${id}",data,function(d){
		$.fancybox.close();
		location.href=location.href;
	});
}

</script>
