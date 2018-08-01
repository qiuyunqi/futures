<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="freeForm">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">管理费审核</b>
	        <div class="fl"></div>
	    </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">账户余额：</div>
            <div class="lf_fonts fl" style="line-height:35px;"><fmt:formatNumber value="${empty user.accountBalance?0.00:user.accountBalance}" pattern="#,###,##0.00"/>元</div>
            <input name="money" type="hidden" value="${fee.money}"/>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none"><input type="radio" name="states" value="2" checked="checked" onclick="changeState(this.value)"/>同意<input type="radio" name="states" value="3" onclick="changeState(this.value)"/>拒绝</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">备&nbsp;&nbsp;注：</div>
            <div class="fl textarea"><textarea id="comment" name="comment" cols="" rows=""></textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">短信提醒：</div>
            <div class="fl textarea"><textarea id="msg" cols="" rows="">您的账户扣除方案[${fee.fuProgram.id }]的管理费审核通过，管理费金额为<fmt:formatNumber value="${empty fee.money?0.00:fee.money}" pattern="#,###,##0.00"/>元。</textarea></div>
            <div class="clr"></div>
        </div>
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo($('#msg').val());" class="sure fl">确认</a><a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function changeState(state){
		if(state==2){
			$("#msg").text('您的账户扣除方案[${fee.fuProgram.id }]的管理费审核通过，管理费金额为<fmt:formatNumber value="${empty fee.money?0.00:fee.money}" pattern="#,###,##0.00"/>元。');
		}else{
			$("#msg").text('您的账户扣除方案[${fee.fuProgram.id }]的管理费被拒绝。');
		}
	}
	function saveInfo(msg){
		var data=$("#freeForm").serialize();
		var states = $("input[name='states']:checked").val();
		$("#btn").text("短信发送中...");
		$.post("${ctx}/admin_op_fee/saveFeeAjax.htm?id=${id}&msg="+msg+"&state="+states,data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请选择你的审核结果！","提示");
				return false;
			}
			if(d==-3){
				sureInfo("确定","对不起账户余额不足,请先进行对此账户出金申请的审核！","提示");
				return false;
			}
			if(d==-4){
				sureInfo("确定","对不起此记录已被审核！","提示");
				return false;
			}
			sureInfo("确定","短信发送成功！","提示");
			location.href=location.href;
		});
	}
	
	
	function quxiao(){
		var data=$("#freeForm").serialize();
		$.post("${ctx}/admin_op_fee/noCheckFeeAjax.htm?id=${id}",data,function(d){
			$.fancybox.close();
			location.href=location.href;
		});
	}
</script>
