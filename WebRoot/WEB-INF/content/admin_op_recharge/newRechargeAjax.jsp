<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">添加充值记录</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户名：</div>
            <div class="fl input"><input name="accountName" type="text" value="" placeholder="用户名" id="name"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">转账银行：</div>
            <div class="fl input"><input name="rechargeBank" type="text" value="" placeholder="转账的银行名称(选填)"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">转账账号：</div>
            <div class="fl input"><input name="rechargeAccount" type="text" value="" placeholder="转账的银行账号(选填)"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">转账金额：</div>
            <div class="fl input"><input name="money" type="text" value="" placeholder="转账金额" id="money1"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">备注：</div>
            <div class="fl textarea"><textarea id="checkRemark" name="checkRemark" cols="" rows=""></textarea></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;">
        <domi:privilege url="/admin_op_recharge/saveRechargeAjax.htm">
        <a href="javascript:void(0);" onclick="saveAdmin();" class="sure fl">确认</a>
        </domi:privilege>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveAdmin(){
	jConfirm("确认同意充值？","操作提示",function(res){
		if(res){
			var data = $('#adminForm').serialize();
			$.post('${ctx}/admin_op_recharge/saveRechargeAjax.htm',data,function(d){
			    if(d==-1){
	                sureInfo("确定","您没有操作权限","提示");
	                return null;
	            }
	            if(d==-2){
	            	jAlert("请填写用户名","提示",function(){
		  				$("#name").focus();
		            });
	                return null;
	            }
	            if(d==-3){
	                sureInfo("确定","该用户名不存在","提示");
	                return null;
	            }
	            if(d==-4){
	            	jAlert("请填写金额","提示",function(){
		  				$("#money1").focus();
		            });
	                return null;
	            }
	            //if(d==-5){
	            //	jAlert("输入金额必须为正数","提示",function(){
		  		//		$("#money1").focus();
		        //    });
	            //    return null;
	            //}
	            location.href = location.href;
			});
		}
	});
}
</script>
