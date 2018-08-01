<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen fucheng0" style="width:500px;">
	  <div class=" fucheng_title"><span>支付未缴费用</span><div class="clr"></div></div>
	  <div class="form marg0">
        	<div class="fl form_font lh">未缴费用：</div>
        	<div class="fl input lh" style="color:#ff9900;font-size:24px;width: 300px;"><fmt:formatNumber value="${money}" pattern="#,###,##0.00" />元</div>
            <div class="clr"></div>
     </div>
      <div class=" form marg0">
      	<div class="fl form_font lh">账户余额：</div>
      	<div class="fl input lh" style="width: 300px;"><fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00" />元</div>
        <div class="clr"></div>
      </div>
     <div style="text-align:center; margin:30px 0 0px 20px;">
        <a id="sure" href="javascript:void(0);" onclick="savePay(${money});" class="queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
     </div>
</div>
<script>
function savePay(money){
	jConfirm("确认支付未缴费用？","操作提示",function(res){
		if(res){
			$.post("${ctx}/user_stock/saveOneKeyPay.htm?money="+money,null,function(d){
				if(d==-1){
					jAlert("账户余额为0.00元，请先充值！","提示",function(){
					});
				}
				if(d==1){
					jAlert("支付未缴费用成功！","提示",function(){
					location.href=location.href;
					});
				}
			});
		}
	})
}
</script>