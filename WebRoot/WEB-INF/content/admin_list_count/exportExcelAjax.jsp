<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<div class="fucheng">
<form id="exportForm" action="${ctx}/admin_list_count/exportExcel.htm">
	<input id="beginTime1" name="beginTime1" type="hidden" value="<fmt:formatDate value="${beginTime1}" pattern="yyyy-MM-dd"/>"/>
	<input id="beginTime2" name="beginTime2" type="hidden" value="<fmt:formatDate value="${beginTime2}" pattern="yyyy-MM-dd"/>"/>
	<input id="endTime1" name="endTime1" type="hidden" value="<fmt:formatDate value="${endTime1}" pattern="yyyy-MM-dd"/>"/>
	<input id="endTime2" name="endTime2" type="hidden" value="<fmt:formatDate value="${endTime2}" pattern="yyyy-MM-dd"/>"/>
    <input id="userName" name="userName" type="hidden" value="${userName}"/>
    <div class=" fc_top" >
    	<b class="fl fc_top_font">批量导出财务报告</b>
        <div class="fl"></div>
    </div>
	<div style="font-size:14px; margin:40px 0 20px;text-align: center;">当前共有<span style="font-weight:700; color:#57b2ef">${totalCount}</span>条财务报告信息，导出可能需要一段时间，导出时请耐心等待。</div>
</form>
</div>
<div class=" but"><a href="javascript:void(0);" onclick="exportForm();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>

<script type="text/javascript">
function exportForm(){
	 $('#exportForm').submit();
	 $.fancybox.close();
}
</script>