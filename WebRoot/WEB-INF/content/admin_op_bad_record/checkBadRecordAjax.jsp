<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen">
	<div class=" fc_top"> 
    	<b class="fl fc_top_font">不良记录审核</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">客户姓名：</div>
            <div class="lf_fonts fl" style="line-height:35px;">${empty badCredit.fuUser.userName?'暂无':badCredit.fuUser.userName}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">方案&nbsp;&nbsp;&nbsp;id：</div>
            <div class="fl input none" style="text-align:left;">${badCredit.fuProgram.id}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none" style="text-align:left;"><input type="radio" name="state" value="2" checked="checked"/>已追回<input type="radio" name="state" value="1"/>坏账</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">不良详情：</div>
            <div class="fl textarea"><textarea name="detail"  cols="" rows="" placeholder="选填" style="height:100px;"></textarea></div>
            <div class="clr"></div>
        </div>
        <div class="but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveCheck();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveCheck(){
	var data = $("#adminForm").serialize();
	$.post("${ctx}/admin_op_bad_record/saveBadRecordAjax.htm?id=${id}",data,function(d){
	    if(d==-1){
           sureInfo("确定","此记录已被人审核成功！","提示");
           return null;
        }
        if(d==-2){
			sureInfo("确定","请选择你的审核结果！","提示");
			return false;
		}
		if(d==-3){
			sureInfo("确定","该记录的方案已穿仓，请先确定方案状态！","提示");
			return false;
		}
        sureInfo("确定","审核成功！","提示");
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
