<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}收益排行榜信息</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">手机号：</div>
            <div class="fl input"><input id="userName" name="userName"  type="text" placeholder="手机号"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">类型：</div>
            <div id="r" class="fl checkbox">
            <!-- <span class=" radio"><input type="radio" name="transactionType" value="1"></span><span class="checkb_font">买入</span>
            <span class=" radio"><input type="radio" name="transactionType" value="2"></span><span class="checkb_font">申请</span> -->
            <span class=" radio"><input type="radio" name="transactionType" value="3" checked="checked"></span><span class="checkb_font">盈利</span>
            </div>
            <div class="clr"></div>
        </div> 
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">交易对象：</div>
            <div class="fl input"><input name="transactionObject"  type="text"  placeholder="交易公司"></div>
            <div class="clr"></div>
        </div >
	    <div id="t1" class="form_cont form_cont0 form_cont1"  style="display: none;" >
            <div class="lf_font fl">购入（手）：</div>
            <div class="fl input"><input name="transactionNum"  type="text" placeholder="售出或者购入几手"></div>
            <div class="clr"></div>
	   </div>
        <div id="t2" style="display: none;" >  
	   		<div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">申请金额：</div>
	            <div class="fl input"><input name="applyMoney"  type="text" placeholder="申请的资金,如1万、10万"></div>
	            <div class="clr"></div>
	        </div> 
        </div>
        <div id="t3"style="display: none;" >     
	         <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">盈利：</div>
	            <div class="fl input"><input name="getMoney"  type="text" placeholder="盈利金额"></div>
	            <div class="clr"></div>
	        </div>  
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">斩获：</div>
	            <div class="fl input"><input name="getGain"  type="text" placeholder="斩获百分比">%</div>
	            <div class="clr"></div>
	        </div>  
		</div>
        <div class="but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_indexdata/addIndexData.htm"><a href="javascript:void(0);" onclick="saveIndexData();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveIndexData(){
	var reg = /^1\d{10}$/;
	if (!reg.test($("#userName").val())){
		sureInfo("确定","你输入的手机号码格式不正确","提示");
		$("#userName").focus();
		return false;
 	}   
	var data = $('#adminForm').serialize();
	$.post('${ctx}/admin_op_indexdata/addIndexData.htm',data,function(d){
            if(d==-2){
                sureInfo("确定","请填写用户名","提示");
                return null;
            }
            if(d==-3){
                sureInfo("确定","请输入类型","提示");
                return null;
            }
            if(d==-4){
                sureInfo("确定","请输入交易对象","提示");
                return null;
            }
            if(d==-5){
                sureInfo("确定","请输入交易笔数","提示");
                return null;
            }
            if(d==-6){
                sureInfo("确定","请输入申请金额","提示");
                return null;
            }
            if(d==-7){
                sureInfo("确定","请输入盈利金额","提示");
                return null;
            }
            if(d==-8){
                sureInfo("确定","请输入斩获百分比","提示");
                return null;
            }
            if(d==1){
                sureInfo("确定","成功添加","提示");
            }
            location.href = location.href;
	});
}

$(function(){
	 /* $("#r").click(function(){
	 	var val=$('input:radio[name="transactionType"]:checked').val();
		if(val=='1'){
			$("#t2").hide();
			$("#t3").hide();
			$("#t1").show();
		}
		if(val=='2'){
			$("#t1").hide();
			$("#t3").hide();
			$("#t2").show();
		} 
		if(val=='3'){*/
			$("#t1").hide();
			$("#t2").hide();
			$("#t3").show();
		//}
	 //});
});
</script>
