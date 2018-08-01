<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style type="text/css">
	.hidden{background-color:black; background-color: rgba(0,0,0,0.5);  padding:10px;position: absolute;z-index: 99;top:200px;width:420px;height:280px;border-radius: 15px;}
	.hidden img{display: block;height: 100%;width: 100%;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">修改股票账户</b>
        <div class="fl"></div>
    </div>
        <form id="stockUpdateForm">
        <%-- <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户姓名：</div>
            <div class="fl input"><input name="openUser" type="text" value="${stock.openUser}" placeholder="开户姓名" id="openUser"></div>
            <div class="clr"></div>
        </div> --%>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户劵商：</div>
            <div class="fl input"><input name="openEquity" type="text" value="${stock.openEquity}" placeholder="开户券商" id="openEquity"></div>
            <div class="clr"></div>
        </div>
        <%-- <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">营业部：</div>
            <div class="fl input"><input name="salesDept" type="text" value="${stock.salesDept}" placeholder="营业部" id="salesDept"></div>
            <div class="clr"></div>
        </div> --%>
        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">账户类型：</div>
		            <div class="fl select">
		            <select id="accountType" name="accountType" style="width:178px;">
		            	<option <c:if test="${stock.accountType==1}">selected="selected"</c:if> value="1">普通账户</option>
					    <option <c:if test="${stock.accountType==2}">selected="selected"</c:if> value="2">融资融券</option>
					    <option <c:if test="${stock.accountType==3}">selected="selected"</c:if> value="3">信用账户</option>
		            </select></div>
	            <div class="clr"></div>
        </div>
        <c:if test="${stock.examineStatus==0 || stock.examineStatus==2}">
	        <div class="form_cont form_cont0">
	            <div class="lf_font fl">股票账号截图：</div>
	            <div class="fl photo">
	            	<c:if test="${empty stock.stockImage}">
	            	无
		            </c:if>
		            <c:if test="${!empty stock.stockImage}">
		            <a  class="back" href="javascript:void(0);"  title="股票账号截图">
		            	<img src="${stock.stockImage}" width="410" height="552" alt="" >
		            </a>
		            <div class="hidden second">
		            	<img src="${stock.stockImage}"  alt="" title="再次点击图片关闭">
		            </div>
		            </c:if>
	            </div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">审核状态：</div>
		            <div class="fl select"><select id="examineStatus" name="examineStatus" style="width:120px;">
		            	<option value="1"  <c:if test="${stock.examineStatus==1}">selected="selected"</c:if>>初审成功</option>
			            <option value="2"  <c:if test="${stock.examineStatus==2}">selected="selected"</c:if>>初审失败</option>
			            <option value="4"  <c:if test="${stock.examineStatus==4}">selected="selected"</c:if>>接单成功</option>
		            </select></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">是否缴纳保证金：</div>
		            <div class="fl select"><select id="isPayMargin" name="isPayMargin" style="width:120px;">
		            	<option value="0"  <c:if test="${stock.isPayMargin==1}">selected="selected"</c:if>>未缴纳</option>
			            <option value="1"  <c:if test="${stock.isPayMargin==2}">selected="selected"</c:if>>已缴纳</option>
		            </select></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">留&nbsp;&nbsp;言：</div>
	            <div class="fl textarea"><textarea id="message" name="message" style="width:200px; " cols="" rows="" placeholder="给交易员的话，限25字" maxlength="25">${stock.message}</textarea></div>
	            <div class="clr"></div>
        </div>
	    </c:if>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/stock_account_list/saveUpdateStock.htm"><a href="javascript:void(0);" onclick="saveStock();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
$(".second").hide();
	$(function(){
		$(".back").bind("click",function(){
			$(".second").show();
		})
		$(".second").bind("click",function(){
			$(this).hide();
		})
	});

function saveStock(){
	/* var openUser=$("#openUser").val();
	if(!openUser){
		jAlert("请输入开户姓名！","提示",function(){
			$("#openUser").focus();
        });
		return false;
	} */
	var openEquity=$("#openEquity").val();
	if(!openEquity){
		jAlert("请输入开户劵商！","提示",function(){
			$("#openEquity").focus();
        });
		return false;
	}
	/* var salesDept=$("#salesDept").val();
	if(!salesDept){
		jAlert("请输入营业部名称！","提示",function(){
			$("#salesDept").focus();
        });
		return false;
	} */
	var data = $("#stockUpdateForm").serialize();
	$.post("${ctx}/stock_account_list/saveUpdateStock.htm?id=${id}",data,function(d){
	    if(d==-1){
		jAlert("资金账号已存在，请重新输入！","提示",function(){
			$("#capitalAccount").val("");
			$("#capitalAccount").focus();
        });
		return false;
		}
		jAlert("账户修改成功！","提示",function(){
			location.href=location.href;;
        });
	});
}
</script>
