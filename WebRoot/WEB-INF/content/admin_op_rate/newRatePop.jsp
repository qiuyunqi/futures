<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}用户费率</b>
        <div class="fl"></div>
    </div>
        <form id="rateForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户名：</div>
            <div class="fl input"><input name="accountName" type="text" value="${fuRate.fuUser.accountName}" placeholder="用户名" <c:if test="${!empty id}">readonly="readonly"</c:if> id="name"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">亏损警报线百分比：</div>
            <div class="fl input"><input name="warnlinePercent" type="text" value="<fmt:formatNumber value="${fuRate.warnlinePercent}" pattern="#,###,##0.000000" />" placeholder="亏损警报线百分比"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">亏损平仓线百分比：</div>
            <div class="fl input"><input name="flatlinePercent" type="text" value="<fmt:formatNumber value="${fuRate.flatlinePercent}" pattern="#,###,##0.000000" />" placeholder="亏损平仓线百分比"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">获取佣金比例：</div>
            <div class="fl input"><input name="commissionPercent" type="text" value="<fmt:formatNumber value="${fuRate.commissionPercent}" pattern="#,###,##0.000000" />" placeholder="获取佣金比例" id="money1"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">日配利息，每日每万：</div>
            <div class="fl input"><input name="feeDay" type="text" value="<fmt:formatNumber value="${fuRate.feeDay}" pattern="#,###,##0.00" />" placeholder="每日利息百分比" id="money2"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">每月利息百分比：</div>
            <div class="fl input"><input name="feePercent" type="text" value="<fmt:formatNumber value="${fuRate.feePercent}" pattern="#,###,##0.000000" />" placeholder="每月利息百分比" id="money3"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">3个月利息百分比：</div>
            <div class="fl input"><input name="interestPercent" type="text" value="<fmt:formatNumber value="${fuRate.interestPercent}" pattern="#,###,##0.000000" />" placeholder="3个月利息百分比" id="money4"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">日配最多方案数目：</div>
            <div class="fl input"><input name="shortNum" type="text" value="${fuRate.shortNum}" placeholder="日配最多方案数目" id="money5"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月配最多方案数目：</div>
            <div class="fl input"><input name="longNum" type="text" value="${fuRate.longNum}" placeholder="月配最多方案数目" id="money6"></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveRate();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
var sub = false;
function saveRate(){
	if(sub){
		return;
	}
	sub = true;
	var data = $('#rateForm').serialize();
	$.post('${ctx}/admin_op_rate/saveRateAjax.htm?id=${id}',data,function(d){
		    sub = false;
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
            if(d==-5){
            	jAlert("输入金额必须为正数","提示",function(){
	  				$("#money1").focus();
	            });
                return null;
            }
            if(d==-10){
                sureInfo("确定","系统错误，请联系管理员!","提示");
                return null;
            }
            window.location.reload();   
	});
}
</script>