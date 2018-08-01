<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:1000px; ">
<div class=" fucheng_title" style="margin-bottom:0px;"><span>是否同意借款协议</span><div class="clr"></div></div>
<iframe width="1000" height="600" frameborder="0" scrolling="auto" src="${ctx}/agreement.jsp"></iframe>
<div style="text-align:center; margin:0px 0 0 20px; padding-bottom:30px;">
        <a href="javascript:void(0);" onclick="agreeInfo();" class="sure" style="float:left;margin-left:32%">同意</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel" style="float:left;">取消</a>
    </div>
</div>
<script>
	function agreeInfo(){
		$('#isAgree').attr('checked','checked');
		$.fancybox.close();
	}
</script>