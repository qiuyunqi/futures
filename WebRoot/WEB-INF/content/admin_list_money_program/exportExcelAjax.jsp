<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<div class="fucheng">
<form id="exportForm" action="${ctx }/admin_list_money_program/exportAllExcel.htm">
    <input id="id" name="id" type="hidden" value="${id}"/>
    <input id="accountName" name="accountName" type="hidden" value="${accountName}"/>
    <input id="userName" name="userName" type="hidden" value="${userName}"/>
    <input id="programId" name="programId" type="hidden" value="${programId}"/>
    <input id="dictionaryId" name="dictionaryId" type="hidden" value="${dictionaryId}"/>
    <input id="money1" name="money1" type="hidden" value="${money1}"/>
    <input id="money2" name="money2" type="hidden" value="${money2}"/>
    <input id="date1" name="date1" type="hidden" value="<fmt:formatDate value="${date1}" pattern="yyyy-MM-dd"/>"/>
    <input id="date2" name="date2" type="hidden" value="<fmt:formatDate value="${date2}" pattern="yyyy-MM-dd"/>"/>
    <input id="comment" name="comment" type="hidden" value="${comment}"/>
    <div class=" fc_top" >
    	<b class="fl fc_top_font">批量导出方案资金明细</b>
        <div class="fl"></div>
    </div>
	<div style="font-size:14px; margin:40px 0 20px;text-align: center;">当前共有<span style="font-weight:700; color:#57b2ef">${totalCount}</span>条方案资金明细，导出可能需要一段时间，导出时请耐心等待。</div>
</form>
</div>
<div class=" but"><a href="javascript:void(0);" onclick="exportForm();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>

<script type="text/javascript">
function exportForm(){
	 $('#exportForm').submit();
	 $.fancybox.close();
}
</script>