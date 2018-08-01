<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<script src="${ctx}/js/DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="fuchen" style="width: 600px">
<form id="programForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">方案风控信息补充</b>
        <div class="fl"></div>
    </div>
    	<div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">交易地址：</div>
            <div class="fl select"><select id="serverId" name="serverId">
            	<option value="">--请选择--</option>
            	<c:forEach items="${serverList}" var="sv" varStatus="row">
            		<option <c:if test="${program.fuServer.id==sv.id}">selected="selected"</c:if>  value="${sv.id}">${sv.serverRealName}</option>
            	</c:forEach>
            </select></div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">交易账户：</div>
            <div class="fl input"><input style="width: 278px" id="tradeAccount" name="tradeAccount" value="${program.tradeAccount}" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">交易密码：</div>
            <div class="fl input"><input style="width: 278px" id="tradePassword" name="tradePassword" value="${program.tradePassword}" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">权属人(营销员)：</div>
            <div class="fl input"><input style="width: 278px" id="comment" name="comment" value="${program.comment}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">居间人：</div>
            <div class="fl input"><input style="width: 278px" id="mediator" name="mediator" value="${program.mediator}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">收取管理费日期：</div>
            <div class="fl input"><input style="width: 278px" id="nextManageMoneyTime" name="nextManageMoneyTime" value="<fmt:formatDate value="${program.nextManageMoneyTime}" pattern="yyyy-MM-dd"/>" type="text" placeholder="" onclick="WdatePicker({maxDate:'', dateFmt:'yyyy-MM-dd'})"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">返佣：</div>
            <div class="fl input"><input style="width: 278px" id="returnCommission" name="returnCommission" value="${program.returnCommission}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">商品期货手续费：</div>
            <div class="fl input"><input style="width: 278px" id="goodsFee" name="goodsFee" value="${program.goodsFee}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">股指期货手续费：</div>
            <div class="fl input"><input style="width: 278px" id="stockIndexFee" name="stockIndexFee" value="${program.stockIndexFee}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">保证金率：</div>
            <div class="fl input"><input style="width: 278px" id="safeMoneyRate" name="safeMoneyRate" value="${program.safeMoneyRate}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">隔夜商品保证金占用：</div>
            <div class="fl input"><input style="width: 278px" id="overnightGoodRate" name="overnightGoodRate" value="${program.overnightGoodRate}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">隔夜沪指保证金占用：</div>
            <div class="fl input"><input style="width: 278px" id="overnightStockIndexRate" name="overnightStockIndexRate" value="${program.overnightStockIndexRate}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">小长假持仓标准：</div>
            <div class="fl input"><input style="width: 278px" id="longHolidayRate" name="longHolidayRate" value="${program.longHolidayRate}" type="text" placeholder="" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">备注：</div>
            <div class="fl textarea"><textarea rows="3" cols="50" id="remark" name="remark">${program.remark}</textarea></div>
            <div class="clr"></div>
        </div>
        
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>

<script>
	var sub=false;
	function saveInfo(){
		if(sub)
			return;
		sub=true;
		var data=$("#programForm").serialize();
		$.post("${ctx}/admin_op_program/saveProgramAjax.htm?id=${id}",data,function(d){
			sub=false;
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请选择交易地址！","提示");
				return false;
			}
			if(d==-3){
				sureInfo("确定","请输入交易账户！","提示");
				return false;
			}
			if(d==-4){
				sureInfo("确定","请输入交易密码！","提示");
				return false;
			}
			jAlert("要素表修改成功！","提示",function(){
				location.href=location.href;
            });
		});
	}
</script>
