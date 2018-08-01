<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}余券宝收益记录</b>
        <div class="fl"></div>
    </div>
        <form id="rankForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">股票代码：</div>
            <div class="fl input"><input name="code"  type="text" value="${rank.code}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">股票名称：</div>
            <div class="fl input"><input name="stockName"  type="text" value="${rank.stockName}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月收益：</div>
            <div class="fl input"><input name="monthProfit"  type="text" value="${rank.monthProfit}" ></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">回撤：</div>
            <div class="fl input"><input name="removes"  type="text" value="${rank.removes}" ></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">团队名称：</div>
            <div class="fl input"><input name="transactionName"  type="text" value="${rank.transactionName}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">排序号：</div>
            <div class="fl input"><input name="serialNo"  type="text" value="${rank.serialNo}" id="val1"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
        	<div class="lf_font fl">热度：</div>
          	<div class="fl select"><select style="width:178px;" name="heat">
	          	<option <c:if test="${rank.heat==1}">selected="selected"</c:if> value="1">1星</option>
	          	<option <c:if test="${rank.heat==2}">selected="selected"</c:if> value="2">2星</option>
	          	<option <c:if test="${rank.heat==3}">selected="selected"</c:if> value="3">3星</option>
	          	<option <c:if test="${rank.heat==4}">selected="selected"</c:if> value="4">4星</option>
	          	<option <c:if test="${rank.heat==5}">selected="selected"</c:if> value="5">5星</option>
          	</select></div>
         	<div class="clr"></div>
         </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveRank(${rank.id});" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveRank(){
	/* var val1 = $("#val1").val();
	var val2 = $("#val2").val();
	if(isNaN(val1)||isNaN(val2)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	} */
	var val1 = $("#val1").val();
	if(isNaN(val1)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	var data = $('#rankForm').serialize();
	$.post('${ctx}/admin_list_wxyqb/saveYqbRankAjax.htm?id=${id}',data,function(d){
	   if(d==-1){
	       sureInfo("确定","请输入股票代码","提示");
	       return null;
	   }	
	   if(d==-2){
           sureInfo("确定","请输入股票名称","提示");
           return null;
       }
       if(d==-3){
           sureInfo("确定","请输入月收益","提示");
           return null;
       }
       if(d==-4){
           sureInfo("确定","请输入回撤","提示");
           return null;
       }
       if(d==-5){
           sureInfo("确定","请输入团队名称","提示");
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
