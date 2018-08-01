<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.surea{background: #2db1e1 none repeat scroll 0 0;border-radius: 2px;color: #fff;display: inline-block;font-size: 14px;font-weight: 600;height: 34px;line-height: 34px;margin: 0 5px;padding: 0 10px;text-align: center;}
.cancela{background: #888888 none repeat scroll 0 0;border-radius: 2px;color: #fff;display: inline-block;font-size: 14px;font-weight: 600; height: 34px;line-height: 34px;margin: 0 5px;padding: 0 10px;text-align: center;}
</style>
<div class=" fucheng" style="width:100%; ">
<div class=" fucheng_title" style="margin-bottom:0px;"><span>是否同意借款协议</span><div class="clr"></div></div>
<div style="width:100%;height:200px;-webkit-overflow-scrolling:touch;overflow-y: scroll;"><iframe width="100%;" height="100%"  frameborder="0" scrolling="auto" src="${ctx}/agreement_wqq.jsp" ></iframe></div>
<div style="text-align:center; padding-bottom:20px;margin-top: 7%;">
        <a href="javascript:void(0);" onclick="agreeInfo();" class="surea" style="float:left;margin-left:32%;">同意</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancela" style="float:left;">取消</a>
    </div>
</div>
<script>
	function agreeInfo(){
		$('#isAgree').attr('checked','checked');
		$.fancybox.close();
	}
</script>