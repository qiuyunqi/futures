<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style type="text/css">
	.hidden{background-color:black; background-color: rgba(0,0,0,0.5);  padding:10px;position: absolute;z-index: 99;top:200px;width:420px;height:280px;border-radius: 15px;}
	.hidden img{display: block;height: 100%;width: 100%;}
</style>
<div class="fuchen" style="width:1000px;background:#f9f9f9;height:700px; ">
	<div class=" fc_top" style="width:1000px;"> 
    	<b class="fl fc_top_font">修改股票账户</b>
        <div class="fl"></div>
    </div>
        <form id="stockUpdateForm" style="float:left; width:350px;">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户姓名：</div>
            <div class="fl input"><input name="openUser" type="text" value="${stock.openUser}" placeholder="开户姓名" id="openUser"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户劵商：</div>
            <div class="fl input"><input name="openEquity" type="text" value="${stock.openEquity}" placeholder="开户券商" id="openEquity"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">营业部：</div>
            <div class="fl input"><input name="salesDept" type="text" value="${stock.salesDept}" placeholder="营业部" id="salesDept"></div>
            <div class="clr"></div>
        </div>
	    <c:if test="${stock.examineStatus==1 || stock.examineStatus==3 || stock.examineStatus==4}">
	    	<%-- <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">账户类型：</div>
		            <div class="fl select">
		            <select id="accountType" name="accountType" style="width:178px;">
		            	<option <c:if test="${stock.accountType==1}">selected="selected"</c:if> value="1">普通账户</option>
					    <option <c:if test="${stock.accountType==2}">selected="selected"</c:if> value="2">融资融券</option>
					    <option <c:if test="${stock.accountType==3}">selected="selected"</c:if> value="3">信用账户</option>
		            </select></div>
	            <div class="clr"></div>
	        </div> --%>
	    	<div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">资金账号：</div>
	            <div class="fl input"><input name="capitalAccount" type="text" value="${stock.capitalAccount}" placeholder="资金账号" id="capitalAccount"></div>
	            <div class="clr"></div>
	        </div>    
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">交易密码：</div>
	            <div class="fl input"><input name="capitalPassword" type="text"  value="${capitalPassword}" placeholder="交易密码" id="capitalPassword"></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">有效持仓市值：</div>
	            <div class="fl input"><input name="available" type="text" value="<fmt:formatNumber value="${stock.available}" pattern="#0.00"/>" id="available"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">可用资金：</div>
	            <div class="fl input"><input name="ableMoney" type="text" value="<fmt:formatNumber value="${stock.ableMoney}" pattern="#0.00"/>" id="ableMoney"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">账户佣金：</div>
	            <div class="fl input"><input name="commission" type="text" value="${stock.commission}" placeholder="账户佣金" id="commission"></div>
	            <div class="clr"></div>
	        </div>  
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">收益模式：</div>
		            <div class="fl select">
		            <select id="profitModel" name="profitModel" style="width:178px;">
		            	<option <c:if test="${stock.profitModel==0}">selected="selected"</c:if> value="0">稳定收益</option>
					    <option <c:if test="${stock.profitModel==1}">selected="selected"</c:if> value="1">保本收益</option>
					    <option <c:if test="${stock.profitModel==2}">selected="selected"</c:if> value="2">固收+分成</option>
		            </select></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">股票收益：</div>
	            <div class="fl input"><input name="availableSplit" type="text" value="${stock.availableSplit}" placeholder="年化8%/三七分成" id="availableSplit"></div>
	            <div class="clr"></div>
	        </div>   
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">资金收益：</div>
	            <div class="fl input"><input name="ableMoneySplit" type="text" value="${stock.ableMoneySplit}" placeholder="年化12%" id="ableMoneySplit"></div>
	            <div class="clr"></div>
	        </div>   
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">是否发布：</div>
	            <div class="fl input">
	            	<input <c:if test="${stock.isPublish==0}">checked="checked"</c:if> name="isPublish" type="radio" value="0"  style="width:15px;"/><span style="margin:0 5px 0 0;"> 未发布</span>
	            	<input <c:if test="${stock.isPublish==1}">checked="checked"</c:if> name="isPublish" type="radio" value="1"  style="width:15px;"/> 发布到融券大厅
	            </div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">交易团队：</div>
	            <div class="fl select">
		            <select id=transactionId name="transactionId" style="width:178px;"> 
		            	<option value="">匹配交易团队中</option>
	            		<c:forEach items="${baseList}" var="base">
		            		<option <c:if test="${stock.transactionId==base.id}">selected="selected"</c:if> value="${base.id}">${base.name}</option>
		            	</c:forEach>
		            </select></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">账户状态：</div>
		            <div class="fl select"><select id="state" name="state" style="width:178px;">
		            	<option value="0"  <c:if test="${stock.state==0}">selected="selected"</c:if>>正在操盘</option>
			            <option value="1"  <c:if test="${stock.state==1}">selected="selected"</c:if>>暂停</option>
			            <option value="2"  <c:if test="${stock.state==2}">selected="selected"</c:if>>申请开启委托中</option>
			            <option value="3"  <c:if test="${stock.state==3}">selected="selected"</c:if>>申请暂停委托中</option>
			            <option value="4"  <c:if test="${stock.state==4}">selected="selected"</c:if>>清算（删除）</option>
		            </select></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">是否缴纳保证金：</div>
		            <div class="fl select"><select id="isPayMargin" name="isPayMargin" style="width:178px;">
		            	<option value="0"  <c:if test="${stock.isPayMargin==0}">selected="selected"</c:if>>未缴纳</option>
			            <option value="1"  <c:if test="${stock.isPayMargin==1}">selected="selected"</c:if>>已缴纳</option>
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
		<c:if test="${stock.examineStatus==1 || stock.examineStatus==3 || stock.examineStatus==4}">
			<div class="content" style="width:600px;margin-left:360px;">
			    <div class=" rt_cont" style="width:600px;margin:0;">
			    	<div class="rt_cont_mat">
			    		<div class="rt_cont_title">
			    	                    股票管理
			    	    </div>
			    	    <form id="shareForm">
			    	    	<input id="shareId" type="hidden" value="">
					        <div class="form_cont form_cont0 form_cont1" style="height:30px;">
					            <div class="lf_font fl" style="width:60px;">股票名称：</div>
					            <div class="fl input"><input name="shareName"  type="text"  value="" id="shareName"></div>
					            <div class="clr"></div>
					        </div>
					        <div class="form_cont form_cont0 form_cont1" style="height:30px;">
					            <div class="lf_font fl" style="width:60px;">股票代码：</div>
					            <div class="fl input"><input name="shareCode"  type="text"  value="" id="shareCode"></div>
					            <div class="clr"></div>
					        </div>
					        <div class="form_cont form_cont0 form_cont1" style="height:30px;float:left;">
					            <div class="lf_font fl" style="width:60px;">股票数量：</div>
					            <div class="fl input"><input name="shareNumber" type="text"  value="<fmt:formatNumber value="" pattern="#"/>" id="shareNumber"></div>
					            <div class="clr"></div>
					        </div>
					        <div class="form_cont form_cont0 form_cont1" style="height:30px;margin-left:260px;">
	       			    	    <domi:privilege url="/stock_account_list/saveStockShare.htm"><a href="javascript:void(0);" onclick="addShare();" class="fr add" style="float:left; margin-top:10px;">保存</a></domi:privilege>
					        </div>
				        </form>
			          <div class=" yhlb_title" style="margin-top:30px;">股票列表</div>
			            <div id="table" class="yhlb">
			          </div>
			        </div>
			    </div>
			</div>
		</c:if>
</div>
<script>
(function(){
	getStockShareList();
})();

function getStockShareList(){
	$.post('${ctx}/stock_account_list/stockShareList.htm?id=${id}','',function(d){	
		var dataArray = eval(d);
		var tableStr = "<table id='shareTable' width='100%'><tr><th>股票名称</th><th>股票代码</th><th>股票数量(股)</th><th>操作</th></tr>";
        for(var i=0; i<dataArray.length; i++){
        	var linkStr = "<domi:privilege url='/stock_account_list/editStockShare.htm'><a href='javascript:void(0);' onclick='editShare("+dataArray[i].SHARE_ID+");' >编辑</a></domi:privilege>&nbsp;&nbsp;<domi:privilege url='/stock_account_list/deleteStockShare.htm'><a href='javascript:void(0);'  onclick='deleteShare("+ dataArray[i].SHARE_ID +");'>删除</a></domi:privilege>";
       	    tableStr = tableStr + "<tr><td>"+dataArray[i].SHARE_NAME+"</td>"+"<td>"+ dataArray[i].SHARE_CODE +"</td>"+"<td>"+dataArray[i].SHARE_NUMBER +"</td>"+"<td>"+linkStr+"</td></tr>";
        }
      	tableStr = tableStr + "</table>";
      	$("#table").html(tableStr);  
	});
}

function saveStock(){
	var examineStatus = "${stock.examineStatus}";
	var openEquity=$("#openEquity").val();
	if(!openEquity){
		jAlert("请输入开户劵商！","提示",function(){
			$("#openEquity").focus();
        });
		return false;
	}
	var salesDept=$("#salesDept").val();
	if(!salesDept){
		jAlert("请输入营业部名称！","提示",function(){
			$("#salesDept").focus();
        });
		return false;
	}
	if(examineStatus==1 || examineStatus==3 || examineStatus==4){
		var capitalAccount=$("#capitalAccount").val();
		if(!capitalAccount){
			jAlert("请输入资金账号！","提示",function(){
				$("#capitalAccount").focus();
	        });
			return false;
		}
		var capitalPassword=$("#capitalPassword").val();
		if(!capitalPassword){
			jAlert("请输入交易密码！","提示",function(){
				$("#capitalPassword").focus();
	        });
			return false;
		}
		var available = $("#available").val();
		var ableMoney = $("#ableMoney").val();
		if(isNaN(available) || isNaN(ableMoney)){
			sureInfo("确定","格式错误，请输入数字！","提示");
	        return null;
		}
	}
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

function addShare(){
	var id = $("#shareId").val();
	validateData();
	var data = $("#shareForm").serialize();
	if(id != null && id != ""){
		$.post("${ctx}/stock_account_list/editStockShare.htm?shareId="+id,data,function(d){
			jAlert("保存成功！","提示",function(){
				getStockShareList();
				$("#shareId").val('');
				$("#shareName").val('');
				$("#shareCode").val('');
				$("#shareNumber").val('');
	        });
		});
	}else{
		$.post("${ctx}/stock_account_list/saveStockShare.htm?id=${id}",data,function(d){
		    /* if(d==-1){
				jAlert("资金账号已存在，请重新输入！","提示",function(){
					$("#capitalAccount").val("");
					$("#capitalAccount").focus();
		        });
				return false;
			} */
			jAlert("保存成功！","提示",function(){
				getStockShareList();
				$("#shareId").val('');
				$("#shareName").val('');
				$("#shareCode").val('');
				$("#shareNumber").val('');
	        });
		});
	}
}

function editShare(id){
	$.post("${ctx}/stock_account_list/queryStockShare.htm?shareId="+id,'',function(d){
	    var arr = d.split(",");
	    $("#shareId").val(id);
		$("#shareName").val(arr[0]);
		$("#shareCode").val(arr[1]);
		$("#shareNumber").val(arr[2]);
	});
}

function deleteShare(id){
	if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
	$.post("${ctx}/stock_account_list/deleteStockShare.htm?shareId="+id,'',function(d){
		if(d==1){
            sureInfo("确定","删除成功","提示");
            getStockShareList();
			$("#shareId").val('');
			$("#shareName").val('');
			$("#shareCode").val('');
			$("#shareNumber").val('');
        }
	});
}

function validateData(){
	var shareName = $("#shareName").val();
	if(!shareName && shareName != ""){
		jAlert("请输入股票名称！","提示",function(){
			$("#shareName").focus();
        });
		return false;
	}
	var shareCode = $("#shareCode").val();
	if(!shareCode && shareCode != ""){
		jAlert("请输入股票代码！","提示",function(){
			$("#shareCode").focus();
        });
		return false;
	}
	var shareNumber = $("#shareNumber").val();
	if(!shareNumber && shareNumber != ""){
		jAlert("请输入股票数量！","提示",function(){
			$("#shareNumber").focus();
        });
		return false;
	}
	if(isNaN(shareNumber)){
		sureInfo("确定","股票数量格式错误！","提示");
        return null;
	}
}
</script>
