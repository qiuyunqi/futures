<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="data">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">结算方案</b>
	        <div class="fl"></div>
	    </div>
  	   <div class="form_cont form_cont0">
          <div class="lf_font fl">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</div>
          <div class="fl input none">${program.fuUser.userName}</div>
          <div class="clr"></div>
       </div>
       <div class="form_cont form_cont0">
          <div class="lf_font fl">方案&nbsp;&nbsp;&nbsp;ID：</div>
          <div class="fl input none">${program.id}</div>
          <div class="clr"></div>
       </div>
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认结算</a><a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>
<script>
	function saveInfo(){
	    jConfirm("确定结算方案？","操作提示",function(res){
			if(res){
				var data=$("#data").serialize();
				$.post("${ctx}/admin_op_program/closeProgramAjax.htm?id=${id}",data,function(d){
					var json=eval("("+d+")");
					if(d==-1){
						jAlert("该方案有持仓，无法结算！","提示",function(){
							location.href=location.href;
				    	});
				    	return false;
					}
					if(d==-2||d==-3){
						jAlert("非结算时间，无法结算！（工作日15:16～16:20）","提示",function(){
							location.href=location.href;
				    	});
				    	return false;
					}
					if(d==-4){
						jAlert("增配子方案不能提前结算，到期后会自动结算！","提示",function(){
							location.href=location.href;
				    	});
				    	return false;
					}
					if(json.result!="清算成功"){
						jAlert(json.result,"提示",function(){
							location.href=location.href;
				    	});
				    	return false;
					}
					$.alerts.okButton="确定";
				    jAlert("结算申请成功，请刷新网页等待处理结果！","提示",function(){
						location.href="${ctx}/admin_list_over_program/overProgramList.htm";
				    });
				});
			}
		});
	}
	
	function quxiao(){
		$.fancybox.close();
	}
</script>
