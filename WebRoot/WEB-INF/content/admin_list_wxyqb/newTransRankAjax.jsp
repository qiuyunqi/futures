<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}交易员收益记录</b>
        <div class="fl"></div>
    </div>
        <form id="transForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">交易员：</div>
            <div class="fl input"><input name="transactionName"  type="text" value="${trans.transactionName}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月收益：</div>
            <div class="fl input"><input name="monthProfit"  type="text" value="${trans.monthProfit}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">管理规模：</div>
            <div class="fl input"><input name="managerScale"  type="text" value="${trans.managerScale}"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">评级：</div>
            <div class="fl input"><input name="rating"  type="text" value="${trans.fuTransaction.rating}" id="val3"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">交易团队：</div>
            <div class="fl input">
	            <select id="fuTransId" name="fuTransId" >
		            <c:forEach items="${transList}" var="fuTrans">
					    <option value="${fuTrans.id}" <c:if test="${trans.fuTransaction.id==fuTrans.id}">selected</c:if>>${fuTrans.name}</option>
					</c:forEach>
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">排序号：</div>
            <div class="fl input"><input name="serialNo"  type="text" value="${trans.serialNo}" id="val1"></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveTrans(${trans.id});" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveTrans(){
	var val1 = $("#val1").val();
	/*var val2 = $("#val2").val(); */
	var val3 = $("#val3").val();
	if(isNaN(val1) || isNaN(val3)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	var data = $('#transForm').serialize();
	$.post('${ctx}/admin_list_wxyqb/saveTransRankAjax.htm?id=${id}',data,function(d){
	   if(d==-2){
           sureInfo("确定","请输入交易员","提示");
           return null;
       }
       if(d==-3){
           sureInfo("确定","请输入月收益","提示");
           return null;
       }
       if(d==-4){
           sureInfo("确定","请输入管理规模","提示");
           return null;
       }
       if(d==-5){
           sureInfo("确定","请输入评级","提示");
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
