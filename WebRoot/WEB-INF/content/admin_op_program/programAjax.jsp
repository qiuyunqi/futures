<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:600px">
<form id="programForm">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">方案审核</b>
	        <div class="fl"></div>
	    </div>
	    <c:if test="${flag==3}">
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0" style="margin-left:80px;">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none">拒绝</div>
            <div class="clr"></div>
        </div>
        </c:if>
        
        <c:if test="${flag==2}">
        <c:if test="${addMatchId>0}">
        <div class="form_cont form_cont0">
    		<div class="form_cont form_cont0" style="margin-left:80px;">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none">同意（增配子方案共用主方案的交易账户）</div>
            <div class="clr"></div>
        </div>
        </c:if>
        <c:if test="${addMatchId<0}">
        <div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl input none">同意（非增配子方案可以自由选择交易地址）</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">交易地址：</div>
            <div class="fl select">
	            <select id="serverId" name="serverId">
	            	<option value="0">--请选择--</option>
	            	<c:forEach items="${serverList}" var="sv" varStatus="row">
	            		<option <c:if test="${program.fuServer.id==sv.id}">selected="selected"</c:if>  value="${sv.id}">${sv.serverRealName}</option>
	            	</c:forEach>
	            </select>
            </div>
            <div class="clr"></div>
        </div>
        </c:if>
        </c:if>
     <div class=" but"><a id="sure" href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="quxiao()" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
<div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
</div>
<script>
	$("#load").hide();
	function saveInfo(){
		if($("#serverId").val()==0){
	        sureInfo("确定","请选择交易地址！","提示");
			return false;
		}
		//开户成功
		<c:if test="${flag==2}">
			$.alerts.okButton="确定";
			$.alerts.cancelButton="返回";
			jConfirm("确认同意开户？","操作提示",function(res){
				if(res){
					$("#sure").attr("onclick", "");
					$("#load").show();
					var data=$("#programForm").serialize();
					$.post("${ctx}/admin_op_program/checkProgramAjax.htm?id=${id}&addMatchId=${addMatchId}&flag=${flag}",data,function(d){
						if(d==-1){
							sureInfo("确定","您没有操作权限！","提示");
							return false;
						}
						if(d==-2){
							sureInfo("确定","请选择交易地址！","提示");
							return false;
						}
						if(d==-3){
							jAlert("当前时间为服务器预留结算时间，不能开户！","提示",function(){
								location.href=location.href;
				            });
							return false;
						}
						if(d==-4){
							jAlert("尚未到方案规定的开始交易日期，不能开户！","提示",function(){
								location.href=location.href;
				            });
							return false;
						}
						if(d==-5){
						    jAlert("该用户余额不足！","提示",function(){
								location.href=location.href;
				             });
							return false;
						}
						if(d==-6||d==-7){
						    jAlert("该服务器金额不足！","提示",function(){
								location.href=location.href;
				             });
							return false;
						}
						if(d==-5){
						    jAlert("该用户已穿仓！","提示",function(){
								location.href=location.href;
				             });
							return false;
						}
						jAlert("同意开户成功，请刷新网页等待处理结果！","提示",function(){
							location.href=location.href;
				        });
				    });
				}
			});
		</c:if>
		//开户失败
		<c:if test="${flag==3}">
			$.alerts.okButton="确定";
			$.alerts.cancelButton="返回";
			jConfirm("确认拒绝开户？","操作提示",function(res){
				if(res){
					var data=$("#programForm").serialize();
					$.post("${ctx}/admin_op_program/checkProgramAjax.htm?id=${id}&flag=${flag}",data,function(d){
						if(d==-1){
							sureInfo("确定","您没有操作权限！","提示");
							return false;
						}
						jAlert("拒绝开户成功！","提示",function(){
							location.href=location.href;
				        });
			    	});
				}
			});
		</c:if>
	}
	
	
	function quxiao(){
		$.fancybox.close();
		location.href=location.href;
	}
	
	//浏览器关闭之前
	/* window.onbeforeunload = function(){
	    var n = window.event.screenX - window.screenLeft;
	    var b = n > document.documentElement.scrollWidth - 20;
	    if (b && window.event.clientY < 0 || window.event.altKey){//关闭页面
	    	quxiao();
	    }
	    else{//刷新页面
	    	quxiao();
	    }
	} */
</script>
