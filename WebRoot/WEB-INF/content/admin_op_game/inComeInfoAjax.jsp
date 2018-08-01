<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="gameForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">设置期货大赛盈利</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">期货大赛时间：</div>
            <div class="fl input none"><fmt:formatDate value="${pro.game.gameTime}" pattern="yyyy-MM"/></div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">用户：</div>
            <div class="fl input none">${pro.fuUser.accountName}</div>
            <div class="clr"></div>
        </div>
        
    	<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">周盈利：</div>
            <div class="fl input"><input id="gameIncomeWeek" name="gameIncomeWeek" value="<fmt:formatNumber value="${pro.gameIncomeWeek}" pattern="#,###,##0.00" />" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月盈利：</div>
            <div class="fl input"><input id="gameIncomeMonth" name="gameIncomeMonth" value="<fmt:formatNumber value="${pro.gameIncomeMonth}" pattern="#,###,##0.00" />" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
     	<div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function saveInfo(){
		var data=$("#gameForm").serialize();
		$.post("${ctx}/admin_op_game/saveInComeInfoAjax.htm?id=${id}",data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请输入周盈利！","提示");
				return false;
			}
			if(d==-3){
				sureInfo("确定","请输入月盈利！","提示");
				return false;
			}
			$('#pageForm').size()>0?$('#pageForm').submit():location.href=location.href;
		});
	}
</script>
