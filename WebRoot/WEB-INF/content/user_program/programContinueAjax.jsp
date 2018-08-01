<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
	<form id="continueForm">
	<div class=" fucheng_title"><span>续约</span><div class="clr"></div></div>
    <div class=" ktqlr" style="text-align: left;text-indent: 30px;"><span class="b1">当前余额：</span>
    <span class="b2"><fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00" /></span><span class="b1">元</span></div>		
    <div class="form marg0" style=" padding:10px 0 0">
            <div style=" width:30px; float:left"></div>
        	<div class="fl form_font lh">续约周期：</div>
            <div class="fl select select1">
            <input type="hidden" id="id" value="${id}">
            <select name="cycleNum" id="cycleNum" style="width:70px;height:40px;" onchange="changeNum()">
            <c:if test="${program.programType==1}">
            		<c:forEach begin="1" end="15" var="i" varStatus="row">
			        	<option value="${i}">${i}天</option>
			        </c:forEach>
            </c:if>
            <c:if test="${program.programType==2}">
            		<c:forEach begin="1" end="12" var="i" varStatus="row">
			        	<option value="${i}">${i}个月</option>
			        </c:forEach>
            </c:if>
            </select>续约后到期日：<span id="closeDate">${closeDate}</span></div>
            <div class="clr"></div>
    </div>
    <div style="text-align:center; margin:0px 0px 0px 20px; padding-bottom:30px;">
    <div style="text-align:center; margin:20px 0 ; ">
        <a id="sure" href="javascript:void(0);" onclick="saveContinue();" class=" queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
    </div>
    <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
    </form>
    <div class=" zhuyi_0">
    	<p class="zhuyi">注意:</p>
    	<p>1.您可以随时续约方案。以免到期自动结算。</p>
        <p>2.续约周期是计算周末的。</p>
    </div>            
</div>
<script>
function changeNum(){
    var data=$("#continueForm").serialize();
	$.post("${ctx}/user_program/getProgramCloseDate.htm?id=${id}",data,function(d){
	     var json=eval("("+d+")");
	     $("#closeDate").text(json.closeDate);
	})
}

	$("#load").hide();
	function saveContinue(){
		$("#load").show();
		$("#sure").attr("onclick", "");
		var data=$("#continueForm").serialize();
		$.post("${ctx}/user_program/saveProgramContinueAjax.htm?id=${id}",data,function(d){
			var json=eval("("+d+")");
			if(json.code==-1){
				$.alerts.okButton="确定";
				jAlert("增配子方案的结束时间不能超过主方案的结束时间！","提示",function(){
					location.href=location.href;
				});
				return false;
			}
			if(json.code==-2){
				sureInfo('确定','请选择续约周期！','提示');
				return false;
			}
			$.alerts.okButton="确定";
			jAlert("续约请求成功，请耐心等待审核结果！","提示",function(){
				location.href=location.href;
			});
		});
	}
	
</script>