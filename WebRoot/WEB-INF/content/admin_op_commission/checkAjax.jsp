<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen">
	<div class=" fc_top"> 
    	<b class="fl fc_top_font">添加兑换说明</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="status" value="${status}"/>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">兑换说明：</div>
            <div class="fl textarea"><textarea name="comment" cols="" rows="" placeholder="选填" style="height:100px;"></textarea></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveCheck();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
var sub = false;
function saveCheck(){
	if(sub){
		return;
	}
	sub = true;
	var data = $('#adminForm').serialize();
	$.post('${ctx}/admin_op_commission/checkCommissionAjax.htm',data,function(d){
		    sub = false;
		    if(d==-1){
                sureInfo("确定","您没有操作权限","提示");
                return null;
            }
		    $('#pageForm').size()>0?$('#pageForm').submit():location.href=location.href;
	});
}
</script>
