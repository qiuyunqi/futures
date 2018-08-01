<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.queren {
    width: 150px;
    height: 42px;
    line-height: 42px;
    text-align: center;
    background: #ea811b;
    display: inline-block;
    color: #fff;
    font-size: 14px;
    font-weight: 600;
    margin: 0 auto;
    border-radius: 2px;
    margin: 0 15px;
}
.quxiao {
    width: 150px;
    height: 42px;
    line-height: 42px;
    text-align: center;
    background: #878787;
    display: inline-block;
    color: #fff;
    font-size: 14px;
    font-weight: 600;
    margin: 0 auto;
    border-radius: 2px;
    margin: 0 15px;
}
</style>
<div class="fuchen fucheng0" style="width:500px;">
	  <div class=" fucheng_title" style="width:100%;height:40px;line-height: 40px;color:#fff;background: #2db1e1;"><span style="margin-left:20px;font-size:14px;font-weight: 600;">支付未缴费用</span><div class="clr"></div></div>
	  <div class="form marg0"  style="background: #fff;padding:0 0 0 40px;border:0;">
        	<div class="fl form_font lh" style="line-height: 40px;">未缴费用：</div>
        	<div class="fl input lh" style="color:#ff9900;font-size:24px;width: 300px;border:0;padding:0;"><fmt:formatNumber value="${money}" pattern="#,###,##0.00" />元</div>
            <div class="clr"></div>
     </div>
      <div class=" form marg0" style="background: #fff;border:0;padding:0 0 0 40px;margin:0;">
      	<div class="fl form_font lh" style="line-height: 30px;">账户余额：</div>
      	<div class="fl input lh" style="width: 300px;border:0;padding:0;line-height: 30px;"><fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00" />元</div>
        <div class="clr"></div>
      </div>
     <div style="text-align:center; margin:30px 0 0px 20px;">
        <a id="sure" href="javascript:void(0);" onclick="savePay(${money});" class="queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
     </div>
</div>
<script>
function savePay(money){
	jConfirm("确认支付？","操作提示",function(res){
		if(res){
			$.post("${ctx}/stock_money_info/saveOneKeyPay.htm?money="+money+"&userId=${fuUser.id}",null,function(d){
				if(d==-1){
					jAlert("账户余额为0.00元，请先充值！","提示",function(){
					});
				}
				if(d==1){
					jAlert("缴费支付成功！","提示",function(){
						location.href=location.href;
					});
				}
			});
		}
	})
}
</script>