<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
.quali_img {width:400px}
.open_night_in{margin-left:95px;}
.open_night_in .is_in_night{width:110px;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}产品交易参数</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品交易品种：</div>
            <div class="fl input"><input name="tradeVariety"  type="text" value="${tradeParam.tradeVariety}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">主账户日交易笔数：</div>
            <div class="fl input"><input name="dayTradeNum"   type="text"  value="<fmt:formatNumber value="${tradeParam.dayTradeNum}" pattern="#"/>" id="val1"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">笔/日</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户保证金：</div>
            <div class="fl input"><input name="safeMoney"   type="text"  value="<fmt:formatNumber value="${tradeParam.safeMoney}" pattern="#0.00"/>" id="val2"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品管理费：</div>
            <div class="fl input"><input name="manageMoney"   type="text"  value="<fmt:formatNumber value="${tradeParam.manageMoney}" pattern="#0.00"/>" id="val3"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元/笔</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品盈利分成比例：</div>
            <div class="fl input"><input name="productPercent"   type="text"  value="<fmt:formatNumber value="${tradeParam.productPercent*100}" pattern="#0.00"/>" id="val4"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开始交易时间：</div>
            <div class="fl input"><input name="tradeTime"  type="text" value="${tradeParam.tradeTime}"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:9:15:00)</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">收盘时间：</div>
            <div class="fl input"><input name="closeTime"  type="text" value="${tradeParam.closeTime}"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:15:12:00)</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">强行平仓时间：</div>
            <div class="fl input"><input name="breakCloseTime"  type="text" value="${tradeParam.breakCloseTime}" id="val31"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:15:12:00)</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">涨跌幅风控线：</div>
            <div class="fl input"><input name="riskPercent"   type="text"  value="<fmt:formatNumber value="${tradeParam.riskPercent*100}" pattern="#0.00"/>" id="val5"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">止损风控线：</div>
            <div class="fl input"><input name="stopLossPercent"   type="text"  value="<fmt:formatNumber value="${tradeParam.stopLossPercent*100}" pattern="#0.00"/>" id="val6"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">止盈风控线：</div>
            <div class="fl input"><input name="stopProfitPercent"   type="text"  value="<fmt:formatNumber value="${tradeParam.stopProfitPercent*100}" pattern="#0.00"/>" id="val7"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">主账户安全可持：</div>
            <div class="fl input" style="border:none;"><input name=""  type="text"  value="<fmt:formatNumber value="${tradeParam.mainPosition*tradeParam.mainSafePercent}" pattern="#" />" id="vala"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">手</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">主账户日最大可持仓：</div>
            <div class="fl input"><input name="mainPosition"   type="text"  value="<fmt:formatNumber value="${tradeParam.mainPosition}" pattern="#"/>" id="valb"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">手</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">主账户安全持仓比例：</div>
            <div class="fl input"><input name="mainSafePercent"   type="text"  value="<fmt:formatNumber value="${tradeParam.mainSafePercent*100}" pattern="#0.00"/>" id="valc"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户最大可持仓(总)：</div>
            <div class="fl input" style="border:none;"><input name=""  type="text"  value="<fmt:formatNumber value="${tradeParam.mainPosition*tradeParam.mainSafePercent*tradeParam.userAddTimes}" pattern="#0.00" />" id="vald"></div>
             <div class="fl" style="line-height:33px;padding-left:5px;">手</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户单边(对冲前)虚增倍数(总)：</div>
            <div class="fl input"><input name="userAddTimes"   type="text"  value="<fmt:formatNumber value="${tradeParam.userAddTimes}" pattern="#"/>" id="vale"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">倍</div>
            <div class="clr"></div>
        </div>
		<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">单用户日最大可交易次数：</div>
            <div class="fl input" style="border:none;"><input name=""  type="text"  value="<fmt:formatNumber value="${tradeParam.dayBaseNum*tradeParam.dayBaseFactor}" pattern="#" />" id="val10"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">单用户日基础交易笔数额度：</div>
            <div class="fl input"><input name="dayBaseNum"  type="text"  value="<fmt:formatNumber value="${tradeParam.dayBaseNum}" pattern="#"/>" id="val8"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">笔</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">单用户日交易笔数风控系数：</div>
            <div class="fl input"><input name="dayBaseFactor"   type="text"  value="<fmt:formatNumber value="${tradeParam.dayBaseFactor}" pattern="#0.00"/>" id="val9"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">当日交易笔数：</div>
            <div class="fl input"><input name="todayBuyVolume"  type="text"  value="<fmt:formatNumber value="${tradeParam.todayBuyVolume}" pattern="#"/>" id="val20"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">笔</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
           <!--  <div class="lf_font fl">是否开启晚间开盘：</div> -->
            <div class="fl input open_night_in">开启晚盘:<input class="is_in_night" type="radio" name="isOpenNight" checked="checked" value="0">关闭晚盘:<input class="is_in_night" type="radio" name="isOpenNight" value="1"></div>
            <div class="clr"></div>
        </div>
        <div id= "openNight" class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">晚间开盘时间：</div>
            <div class="fl input"><input name="nightOpenTime"  type="text" value="${tradeParam.nightOpenTime}" id="val21"></div>
            <div class="clr"></div>
        </div>
        <div id= "closeNight" class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">晚间收盘时间：</div>
            <div class="fl input"><input name="nightCloseTime"  type="text" value="${tradeParam.nightCloseTime}" id="val22"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">合约名称：</div>
            <div class="fl input"><input name="instrumentName"  type="text" value="${tradeParam.instrumentName}" id="val23"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">市场名称：</div>
            <div class="fl input"><input name="market"  type="text" value="${tradeParam.market}" id="val24"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">点位单价：</div>
            <div class="fl input"><input name="smallestPriceChange"   type="text"  value="<fmt:formatNumber value="${tradeParam.smallestPriceChange}" pattern="#0.00"/>" id="val25"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">最小跳动点位：</div>
            <div class="fl input"><input name="changePoints"   type="text"  value="<fmt:formatNumber value="${tradeParam.changePoints}" pattern="#0.00"/>" id="val30"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品开盘的时间段：</div>
            <div class="fl input"><input name="timeSlot"  type="text" value="${tradeParam.timeSlot}"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:9:15-11:30#13:30-15:30#21:00-01:30)</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1"> 
            <div class="lf_font fl">选择图片：</div>
       	  		<a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg1"/></a>
            <div class="clr"></div>
            <div class="form quali_img" id="div1" <c:if test="${empty tradeParam.productIco}"> style="display: none;"</c:if>
	   			<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow1" src="${tradeParam.productIco}" width="300" height="200"></span>
	       		<input type="hidden" name="productIco" id="picValue1" value="${tradeParam.productIco}"/>
	       		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
	   		</div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveParameter(${tradeParam.id});" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
$('input:radio').change(function(){
	$rv = $('input:radio:checked').val();
	if($rv == 1){
		$('#openNight').hide();
		$('#closeNight').hide();
	}else{
		$('#openNight').show();
		$('#closeNight').show();
	}
});
$("#val8").keyup(function(){
	var baseNumStr = $("#val8").val();
	var baseFactorStr = $("#val9").val();
	var baseNum = Number(baseNumStr);
	var baseFactor = Number(baseFactorStr);
	$("#val10").val(baseNum*baseFactor);
});

$("#val9").keyup(function(){
	var baseNumStr = $("#val8").val();
	var baseFactorStr = $("#val9").val();
	var baseNum = Number(baseNumStr);
	var baseFactor = Number(baseFactorStr);
	$("#val10").val(baseNum*baseFactor);  
});

$("#valb").keyup(function(){
	var valbStr = $("#valb").val();
	var valcStr = $("#valc").val();
	var valeStr = $("#vale").val();
	var valbNum = Number(valbStr);
	var valcNum = Number(valcStr);
	var valeNum = Number(valeStr);
	$("#vala").val(valbNum*valcNum/100);  
	$("#vald").val(valbNum*valcNum*valeNum/100);  
});

$("#valc").keyup(function(){
	var valbStr = $("#valb").val();
	var valcStr = $("#valc").val();
	var valeStr = $("#vale").val();
	var valbNum = Number(valbStr);
	var valcNum = Number(valcStr);
	var valeNum = Number(valeStr);
	$("#vala").val(valbNum*valcNum/100);  
	$("#vald").val(valbNum*valcNum*valeNum/100);  
});

$("#vale").keyup(function(){
	var valbStr = $("#valb").val();
	var valcStr = $("#valc").val();
	var valeStr = $("#vale").val();
	var valbNum = Number(valbStr);
	var valcNum = Number(valcStr);
	var valeNum = Number(valeStr);
	$("#vala").val(valbNum*valcNum/100);  
	$("#vald").val(valbNum*valcNum*valeNum/100);  
});

function saveParameter(){
	var val1 = $("#val1").val();
	var val2 = $("#val2").val();
	var val3 = $("#val3").val();
	var val4 = $("#val4").val();
	var val5 = $("#val5").val();
	var val6 = $("#val6").val();
	var val7 = $("#val7").val();
	var val8 = $("#val8").val();
	var val9 = $("#val9").val();
	var valb = $("#valb").val();
	var valc = $("#valc").val();
	var vale = $("#vale").val();
	var val20 = $("#val20").val();
	var val25 = $("#val25").val();
	var val30 = $("#val30").val();
	
	if(isNaN(val1)||isNaN(val2)||isNaN(val3)||isNaN(val4)||isNaN(val5)||isNaN(val6)||isNaN(val7)||isNaN(val8)||isNaN(val9)||isNaN(valb)||isNaN(valc)||isNaN(vale)||isNaN(val20)||isNaN(val25)||isNaN(val30)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	
	var data = $('#adminForm').serialize();
	$.post('${ctx}/admin_list_trade_parameter/saveTradeParameterAjax.htm?id=${id}',data,function(d){
       if(d==-2){
           sureInfo("确定","请输入产品交易品种","提示");
           return null;
       }
       if(d==-3){
           sureInfo("确定","请输入主账户日交易笔数","提示");
           return null;
       }
       if(d==-4){
           sureInfo("确定","请输入用户保证金","提示");
           return null;
       }
       if(d==-5){
           sureInfo("确定","请输入产品管理费","提示");
           return null;
       }
       if(d==-6){
           sureInfo("确定","请输入产品盈利分成比例","提示");
           return null;
       }
       if(d==-7){
           sureInfo("确定","请输入开始交易时间","提示");
           return null;
       }
       if(d==-8){
           sureInfo("确定","请输入收盘时间","提示");
           return null;
       }
       if(d==-9){
           sureInfo("确定","请输入涨跌幅风控线","提示");
           return null;
       }
       if(d==-10){
           sureInfo("确定","请输入止损风控线","提示");
           return null;
       }
       if(d==-11){
           sureInfo("确定","请输入止盈风控线","提示");
           return null;
       }
       if(d==-12){
           sureInfo("确定","请输入单用户日基础交易笔数额度","提示");
           return null;
       }
       if(d==-13){
           sureInfo("确定","请输入单用户日交易笔数风控系数","提示");
           return null;
       }
       if(d==-14){
           sureInfo("确定","产品交易品种不能重复","提示");
           return null;
       }
       if(d==-15){
           sureInfo("确定","请输入主账户日最大可持仓","提示");
           return null;
       }
       if(d==-16){
           sureInfo("确定","请输入主账户安全持仓比例","提示");
           return null;
       }
       if(d==-17){
           sureInfo("确定","请输入用户单边虚增倍数","提示");
           return null;
       }
       if(d==-18){
           sureInfo("确定","请输入当日交易笔数","提示");
           return null;
       }
       if(d==-19){
           sureInfo("确定","请输入晚间开盘时间","提示");
           return null;
       }
       if(d==-20){
           sureInfo("确定","请输入晚间收盘时间","提示");
           return null;
       }
       if(d==-21){
           sureInfo("确定","请输入合约名称","提示");
           return null;
       }
       if(d==-22){
           sureInfo("确定","请输入市场名称","提示");
           return null;
       }
       if(d==-23){
           sureInfo("确定","请输入点位单价","提示");
           return null;
       }
       if(d==-30){
           sureInfo("确定","请输入最小跳动点位","提示");
           return null;
       }
       if(d==-31){
           sureInfo("确定","请输入强行平仓时间","提示");
           return null;
       }
       if(d==-24){
           sureInfo("确定","风控处理失败","提示");
           return null;
       }
       if(d == -32){
       		sureInfo("确定", "请输入时间段", "提示");
       }
       if(d==1){
           jAlert("保存成功！","提示",function(){
			   location.href = location.href;
     	   });
       }
	});
}

jQuery("#uploadImg1").uploadify({
	'uploader' : '${ctx}/js/uploadify-v2.1.4/uploadify.swf',
	'script' : '${ctx}/upload/img.htm',
	'cancelImg' : '${ctx}/js/uploadify-v2.1.4/cancel.png',
	'fileDataName' : 'imgFile', //相当于  file 控件的 name
	'auto' : true,
	'multi' : false,
	'queueID':'imgQueue1',
	'buttonImg' : '${ctx}/qihuo_images/xzwj_03.gif',
	'height' : '28',
	'width' : '82',
	'fileDesc' : '能上传的图片类型:jpg,gif,bmp,jpeg,png', //出现在上传对话框中的文件类型描述
	'fileExt' : '*.jpg;*.gif;*.bmp;*.jpeg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	'sizeLimit' : 3*1024*1024,
	onComplete:function(event,queueID,fileObj,response,data){
			var jsondata = eval("("+response+")");
			if(jsondata.error==1){
				Dialog.alert(jsondata.message);
				return false;
			}
			$("#picShow1").attr("src",jsondata.saveDir);
			$("#picValue1").val(jsondata.saveDir);
			$("#div1").show();
			$("#span1").html("已上传文件："+jsondata.fileName);
		},
		'onSelect' : function(event,queueID, fileObj) {
			if (fileObj.size > 5*1024*1024) {
				Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
				jQuery("#uploadImg1").uploadifyClearQueue();
			}
		}
});
</script>
