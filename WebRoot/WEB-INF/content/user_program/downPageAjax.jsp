<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
	<div class=" fucheng_title"><span>降级方案</span><div class="clr"></div></div>
            	<div style=" text-align:center; padding:50px 0 20px; ">
        	是否确认要降级该方案？
    </div>
                
    <div style="text-align:center; margin:30px 0 0px 20px; padding-bottom:30px;">
        <a href="javascript:void(0);" onclick="downProgramInfo();" class=" queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
    </div>
    <div class=" zhuyi_0">
    	<p class="zhuyi">注意:</p>
    	<p>1.降级方案是指减少资金比例，降低平仓线。</p>
        <p>2.您在没有持仓或收盘后才能使用降级方案。</p>
        <p>3.一旦降级，将扣除您的实盘资金，并不可退还利息，请谨慎操作。</p>
    </div>             
    </div>
<script>
	var sub=false;
	function downProgramInfo(){
		if(sub)
			return;
		sub=true;
		$.get("${ctx}/user_program/downProgramAjax.htm?id=${id}",function(d){
				sub=false;
				if(d==-1){
					sureInfo('确定','当前有持仓，请平仓后再申请降级','提示');
					return;
				}
				if(d==-2){
					sureInfo('确定','当前有未结束的临时升级，无法申请降级','提示');
					return;
				}
				if(d==-3){
					sureInfo('确定','还未达到平仓线，无法申请降级方案','提示');
					return;
				}
				if(d==-4){
					sureInfo('确定','账户金额低于1000元，无法申请降级方案','提示');
					return;
				}	
				location=location;
		});
	}
</script>