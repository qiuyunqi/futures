<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<div class="fucheng">
<form id="exportForm" action="${ctx }/admin_list_program/exportExcel.htm">
    <input id="programId" name="programId" type="hidden" value="${programId}"/>
    <input id="programType" name="programType" type="hidden" value="${programType}"/>
    <input id="status" name="status" type="hidden" value="${status}"/>
    <input id="userName" name="userName" type="hidden" value="${userName}"/>
    <input id="safeMoney1" name="safeMoney1" type="hidden" value="${safeMoney1}"/>
    <input id="safeMoney2" name="safeMoney2" type="hidden" value="${safeMoney2}"/>
    <input id="doubleNum1" name="doubleNum1" type="hidden" value="${doubleNum1}"/>
    <input id="doubleNum2" name="doubleNum2" type="hidden" value="${doubleNum2}"/>
    <input id="matchMoney1" name="matchMoney1" type="hidden" value="${matchMoney1}"/>
    <input id="matchMoney2" name="matchMoney2" type="hidden" value="${matchMoney2}"/>
    <input id="cycleNum1" name="cycleNum1" type="hidden" value="${cycleNum1}"/>
    <input id="cycleNum2" name="cycleNum2" type="hidden" value="${cycleNum2}"/>
    <input id="feePecent1" name="feePecent1" type="hidden" value="${feePecent1}"/>
    <input id="feePecent2" name="feePecent2" type="hidden" value="${feePecent2}"/>
    <input id="managerMoney1" name="managerMoney1" type="hidden" value="${managerMoney1}"/>
    <input id="managerMoney2" name="managerMoney2" type="hidden" value="${managerMoney2}"/>
	<input id="time1" name="time1" type="hidden" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>"/>
	<input id="time2" name="time2" type="hidden" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>"/>
    <div class=" fc_top" >
    	<b class="fl fc_top_font">批量导出方案管理</b>
        <div class="fl"></div>
    </div>
	<div style="font-size:14px; margin:40px 0 20px;text-align: center;">当前共有<span style="font-weight:700; color:#57b2ef">${totalCount}</span>条方案管理信息，导出可能需要一段时间，导出时请耐心等待。</div>
</form>
</div>
<div class=" but"><a href="javascript:void(0);" onclick="exportForm();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>

<script type="text/javascript">
function exportForm(){
	 $('#exportForm').submit();
	 $.fancybox.close();
}
</script>