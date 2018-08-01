<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="freeForm">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">修改银行卡</b>
	        <div class="fl"></div>
	    </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">开户人：</div>
            <div class="fl input none" >${bankCard.accountName}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">开户行：</div>
            <div class="fl input none">${bankCard.bankName}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">银行卡号：</div>
            <div class="fl input none">${bankCard.cardNumber}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">所在地：</div>
            <div class="fl input none">${bankCard.bankAddress}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">支行名称：</div>
            <div class="fl input"><input name="bankBranchName" type="text" value="${bankCard.bankBranchName}"/></div>
            <div class="clr"></div>
        </div>
     <div class=" but"><domi:privilege url="/admin_op_bank_card/saveBankCard.htm"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
function saveInfo(){
	jConfirm("确认修改？","操作提示",function(res){
		if(res){
			var data=$("#freeForm").serialize();
			$.post("${ctx}/admin_op_bank_card/saveBankCard.htm?id=${id}",data,function(d){
				jAlert("修改成功！","提示",function(){
					location.href=location.href;
	            });
			});
		}
	})
}
</script>
