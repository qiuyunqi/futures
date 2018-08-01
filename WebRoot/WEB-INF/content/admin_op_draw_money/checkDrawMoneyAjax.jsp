<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen">
	<div class=" fc_top"> 
    	<b class="fl fc_top_font">提款记录审核</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">账户余额：</div>
            <div class="lf_fonts fl" style="line-height:35px;"><fmt:formatNumber value="${empty user.accountBalance?0.00:user.accountBalance}" pattern="#,###,##0.00"/>元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">客户卡号：</div>
            <div class="fl input none" style="text-align: left;">${draw.fuBankCard.cardNumber }</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none" style="text-align: left;"><input type="radio" name="status" value="2" checked="checked" onclick="changeStatus(this.value)"/>同意<input type="radio" name="status" value="3" onclick="changeStatus(this.value)"/>拒绝</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">备注内容：</div>
            <div class="fl textarea"><textarea name="comment" cols="" rows="" placeholder="选填" style="height:100px;"></textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">短信提醒：</div>
            <div class="fl textarea">
            <textarea id="msg" cols="" rows="">您的账户提款审核通过，提款金额为<fmt:formatNumber value="${empty draw.drawMoney?0.00:draw.drawMoney}" pattern="#,###,##0.00"/>元。</textarea>
            <select id="msgSelect">
            	<option value="对不起！您的银行卡信息有误，无法办理汇款，请正确填写银行卡号及分支行信息，再进行提款申请，谢谢！">银行卡信息有误</option>
            	<option value="您好！由于网站升级，暂不能进行充值/提款操作，升级完成后恢复正常操作，给您带来不便深感歉意！">由于网站升级</option>
            	<option value="您好！由于您尚未进行实名认证，无法为您办理汇款，为保障您的资金安全，请进行实名认证后再提交提款申请，谢谢！">未进行实名认证</option>
            	<option value="对不起！您的提款金额低于10元，暂无法办理您的提款需求，请再次申请时，提高提现金额，如有其它需求，请致电010-53320537，我们竭诚为您服务！">提款金额低于10元</option>
            	<option value="公司系统检测出您的账户有套现风险，需要暂时冻结账户进行核查">您的账户有套现风险</option>
            	<option value="经核查您的账户套现成立，扣除手续费后7个工作日内才能提现成功，如果继续有套现行为将会封锁您的账户">经核查您的账户套现成立</option>
            </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;">
        <domi:privilege url="/admin_op_draw_money/checkDrawAjax.htm">
        	<a href="javascript:void(0);" onclick="saveCheck();" class="sure fl">确认</a>
        </domi:privilege>
        <domi:privilege url="/admin_op_draw_money/noCheckDrawAjax.htm">
        	<a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a>
        </domi:privilege>
        <div class="clr">
        </div></div>
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

function saveCheck(){
	jConfirm("确认提款审核结果？","操作提示",function(res){
		if(res){
			var smsg="";
			if(state==2){
				smsg = encodeURI($("#msg").val(), "UTF-8");
			}else{
				smsg = encodeURI($("#msgSelect").val(), "UTF-8");
			}
	
			var data=$("#adminForm").serialize();
			$.post("${ctx}/admin_op_draw_money/checkDrawAjax.htm?id=${id}&msg="+smsg,data,function(d){
			    if(d==-1){
		           sureInfo("确定","您没有操作权限","提示");
		           return null;
		        }
			    location.href=location.href;
			});
		}
	});
}

function quxiao(){
	var data=$("#adminForm").serialize();
	$.post("${ctx}/admin_op_draw_money/noCheckDrawAjax.htm?id=${id}",data,function(d){
		$.fancybox.close();
		location.href=location.href;
	});
}

//浏览器关闭之前
/* window.onbeforeunload = function(){
    var n = window.event.screenX - window.screenLeft;
    var b = n > document.documentElement.scrollWidth - 20;
    if (b && window.event.clientY < 0 || window.event.altKey){//关闭页面
    	quxiao();
    }
    else{//刷新页面
    	quxiao();
    }
} */
</script>
