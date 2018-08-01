<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}K线记录</b>
        <div class="fl"></div>
    </div>
        <form id="klineForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">日期：</div>
            <div class="fl input"><input name="tradingDay"  type="text" value="${kline.tradingDay}"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:20151008)</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">合约编号：</div>
            <div class="fl input"><input name="instrumentId"  type="text" value="${kline.instrumentId}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">最高价：</div>
            <div class="fl input"><input name="highestPrice"  type="text" value="<fmt:formatNumber value="${kline.highestPrice}" pattern="#0.00"/>" id="val1"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">最低价：</div>
            <div class="fl input"><input name="lowestPrice"  type="text" value="<fmt:formatNumber value="${kline.lowestPrice}" pattern="#0.00"/>" id="val2"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开盘价：</div>
            <div class="fl input"><input name="openPrice"  type="text" value="<fmt:formatNumber value="${kline.openPrice}" pattern="#0.00"/>" id="val3"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">收盘价：</div>
            <div class="fl input"><input name="closePrice"  type="text" value="<fmt:formatNumber value="${kline.closePrice}" pattern="#0.00"/>" id="val4"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveKline(${kline.id});" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveKline(){
	var val1 = $("#val1").val();
	var val2 = $("#val2").val();
	var val3 = $("#val3").val();
	var val4 = $("#val4").val();
	if(isNaN(val1)||isNaN(val2)||isNaN(val3)||isNaN(val4)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	var data = $('#klineForm').serialize();
	$.post('${ctx}/admin_list_kline/saveKlineAjax.htm?id=${id}',data,function(d){
	   if(d==-2){
           sureInfo("确定","请输入日期","提示");
           return null;
       }
       if(d==-3){
           sureInfo("确定","请输入合约编号","提示");
           return null;
       }
       if(d==-4){
           sureInfo("确定","请输入最高价","提示");
           return null;
       }
       if(d==-5){
           sureInfo("确定","请输入最低价","提示");
           return null;
       }
       if(d==-6){
           sureInfo("确定","请输入开盘价","提示");
           return null;
       }
       if(d==-7){
           sureInfo("确定","请输入收盘价","提示");
           return null;
       }
       if(d==-8){
           sureInfo("确定","每个合约每日的K线记录唯一","提示");
           return null;
       }
       if(d==1){
           jAlert("${empty id?'添加':'修改'}"+"成功！","提示",function(){
			   location.href = location.href;
     	   });
       }
	});
}
</script>
