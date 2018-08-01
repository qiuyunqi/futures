<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="continueForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">续约记录审核</b>
        <div class="fl"></div>
    </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">账户余额：</div>
            <div class="lf_fonts fl" style="line-height:35px;"><fmt:formatNumber value="${empty user.accountBalance?0.00:user.accountBalance}" pattern="#,###,##0.00"/>元</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none" style="text-align: left;"><input type="radio" name="result" value="2" checked="checked" onclick="changeStatus(this.value)"/>同意<input type="radio" name="result" value="3" onclick="changeStatus(this.value)"/>拒绝</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">审核说明：</div>
            <div class="fl textarea"><textarea id="comment" name="comment" cols="" rows=""></textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">短信提醒：</div>
            <div class="fl textarea"><textarea id="msg" cols="" rows="">您的方案[${con.fuProgram.id }]续约审核通过。</textarea></div>
            <div class="clr"></div>
        </div>
        
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo($('#msg').val());" class="sure fl">确认</a><a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function changeStatus(status){
		if(status==2){
			$("#msg").text("您的方案["+${con.fuProgram.id}+"]续约审核通过。");
		}else{
			$("#msg").text("您的方案["+${con.fuProgram.id}+"]续约审核被拒绝。");
		}
	}
	function saveInfo(msg){
		var data=$("#continueForm").serialize();
		var smsg = encodeURI(msg, "UTF-8"); 
		$.post("${ctx}/admin_op_continue/saveContinueAjax.htm?id=${id}&msg="+smsg,data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请选择审核结果！","提示");
				return false;
			}
			location.href=location.href;
		});
	}
	
	function quxiao(){
		var data=$("#continueForm").serialize();
		$.post("${ctx}/admin_op_continue/noCheckContinueAjax.htm?id=${id}",data,function(d){
			$.fancybox.close();
			location.href=location.href;
		});
	}
</script>
