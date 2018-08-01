<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<script src="${ctx}/js/DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="fuchen" style="width: 600px">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">微期权收益承兑</b>
        <div class="fl"></div>
    </div>
    <form id="wqqForm">
    	<input name="contentsId" type="hidden" value="${contentsId}"/>
        <input id="wqqstate" type="hidden" value="${state}"/>
        <div id="state1">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">竞猜结果：</div>
            <div class="fl input none"><input type="radio" name="guessType" value="0" checked="checked"/>跌<input type="radio" name="guessType" value="1"/>涨</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">承兑系数：</div>
            <div class="fl input"><input style="width: 278px" id="acceptFactor" name="acceptFactor"  type="text" placeholder="必填"></div>
            <div class="clr"></div>
        </div>
     	<div class=" but"><domi:privilege url="/admin_op_wqq_contents/saveWqqFactorAjax.htm"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</div>
	</form>
	<div id="state2">
    	<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">购买总人数（人）：</div>
            <div class="fl input none">${personCount}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">承兑总金额（元）：</div>
            <div class="fl input none"><fmt:formatNumber value="${totalIncome}" pattern="#,###,##0.00"/></div>
            <div class="clr"></div>
        </div>
     <div class=" but"><a href="javascript:void(0);" onclick="$.fancybox.close();" class="sure fl">关闭</a></div>
	</div>
</div>
<script>
	function saveInfo(){
		var acceptFactor=$("#acceptFactor").val();
		if(!acceptFactor || isNaN(acceptFactor) || parseFloat(acceptFactor)<0){
			jAlert("承兑系数必须大于或等于0！","提示",function(){
				$("#acceptFactor").focus();
	        });
			return false;
		}
		jConfirm("确认收益承兑？","操作提示",function(res){
			if(res){
				var data=$("#wqqForm").serialize();
				$.post("${ctx}/admin_op_wqq_contents/saveWqqFactorAjax.htm",data,function(d){
					jAlert("收益承兑成功！","提示",function(){
						location.href=location.href;
		            });
				});
			}
		})
	}
	
	$(function(){
		var state=$("#wqqstate").val();
		if(state==2){
			$("#state1").show();
			$("#state2").hide();
		}
		if(state==3){
			$("#state2").show();
			$("#state1").hide();
		}
	})
</script>
