<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
</style>
<div class="fuchen" style="width:600px;">
	<div class=" fc_top" style="width:600px;"> 
    	<b class="fl fc_top_font">下单</b>
        <div class="fl"></div>
    </div>
        <form id="orderForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户ID：</div>
            <div class="fl input"><input name="userId"   type="text"  value="" pattern="#"/ id="val5"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">合约编号：</div>
            <div class="fl input">
	            <select id="instrumentId" name="instrumentId">
		            <c:forEach var="trdParam" items="${paramList}" varStatus="status">
					    <option>${trdParam.tradeVariety}</option>
					</c:forEach>
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <!-- <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">合约编号：</div>
            <div class="fl input"><input name="instrumentId"   type="text"  value="" id="val1"></div>
            <div class="clr"></div>
        </div> -->
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">买卖方向：</div>
            <div class="fl input">
	            <select id="direction" name="direction">
		            <option value="0">买</option>
		            <option value="1">卖</option>
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开平方向：</div>
            <div class="fl input">
	            <select id="offsetFlag" name="offsetFlag">
		            <option value="0">开仓</option>
		           <!--  <option value="1">平仓</option> -->
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">止盈金额：</div>
            <div class="fl input"><input name="stopProfit"   type="text"  value="" pattern="#0.00"/ id="val2"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">止损金额：</div>
            <div class="fl input"><input name="stopLoss"   type="text"  value="" pattern="#0.00"/ id="val3"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="commitOrder();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function commitOrder(){ 
	var val2 = $("#val2").val();
	var val3 = $("#val3").val();
	var val5 = $("#val5").val();
	if(isNaN(val2)||isNaN(val3)||isNaN(val5)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	var data = $('#orderForm').serialize();
	$.post('${ctx}/admin_list_order/commitOrderAjax.htm',data,function(d){
	   if(d==-2){
	       sureInfo("确定","请输入用户ID","提示");
           return null;
       }
	   if(d==-3){
           sureInfo("确定","请输入合约编号","提示");
           return null;
       }
       if(d==-4){
           sureInfo("确定","请输入止盈金额","提示");
           return null;
       }
       if(d==-5){
           sureInfo("确定","请输入止损金额","提示");
           return null;
       }
       if(d==-6){
           sureInfo("确定","风控处理失败","提示");
           return null;
       }
       if(d==-7){
           sureInfo("确定","用户余额不足","提示");
           return null;
       }
       if(d==-8){
           sureInfo("确定","行情波动较大,不允许下单","提示");
           return null;
       }
       if(d==1){
           jAlert("下单成功","提示",function(){
			   location.href = location.href;
     	   });
       }
	});
}
</script>
