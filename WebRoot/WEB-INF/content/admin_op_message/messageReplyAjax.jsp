<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="personForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">回复留言</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">留言人：</div>
            <div class="fl input none" >${message.fuUser.userName}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">留言内容：</div>
            <div class="fl input none">${message.content}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">回复内容：</div>
            <div class="fl textarea"><textarea id="replyContent" name="replyContent" cols="" rows="">${message.replyContent}</textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">备注：</div>
            <div class="fl textarea"><textarea id="messageRemark" name="messageRemark" cols="" rows="">${message.replyMark}</textarea></div>
            <div class="clr"></div>
        </div>
        
        
        
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function saveInfo(){
		var data=$("#personForm").serialize();
		$.post("${ctx}/admin_op_message/saveReplyMessageAjax.htm?id=${id}",data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请输入回复内容！","提示");
				return false;
			}
			$('#pageForm').size()>0?$('#pageForm').submit():location.href=location.href;
		});
	}
</script>
