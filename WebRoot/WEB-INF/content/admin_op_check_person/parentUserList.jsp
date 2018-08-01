<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 900px; height:500px " >
	<form id="personForm">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">上级用户</div>
            <div id = "form" class="form">
	            <form id="searchForm" action="" method="post">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody><tr>
					    <%-- <td>
					        <div class="form_cont">
					            <div class="lf_font fl">用户名：</div>
					            <div class="fl input"><input id="pName" name="pName" value="${pName}" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td> --%>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">手机号码：</div>
					            <div class="fl input"><input id="pPhone" name="pPhone" value="${pPhone}" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">所选用户ID：</div>
					            <div class="fl input"><input id="parentId" readonly="readonly" name="parentId" value="${parentId}" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <!-- <div class="lf_font fl"></div> -->
					            <div class="fl"><a href="javascript:void(0);" onclick="searchParent();" class="search">搜索</a></div>
					            <!-- <div class="clr"></div> -->
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="fl" style=""><a href="javascript:void(0);" onclick="confirm(${u.id});" class="search">确认</a></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="fl"><a href="javascript:void(0);" onclick="$.fancybox.close();" class="search">取消</a></div>
					        </div>
					    </td>
					  </tr>
					</tbody></table>
				</form>
            </div>
          <div class=" yhlb_title">用户列表</div>
          <div id="table" class="yhlb"></div>  
        </div>
	</form>
</div>


<script>
function searchParent(){
	if($("#pPhone").val()==null || $("#pPhone").val() == ""){
		jAlert("未按手机号搜索！","提示",function(){
			return false;
        });	
	}else{
		//var data = $('#searchForm').serialize();
		$.post('${ctx}/admin_op_check_person/parentUser.htm?pName='+$('#pName').val()+'&pPhone='+$('#pPhone').val(),'',function(d){	
			var dataArray = eval(d);
			var tableStr = "<table id='parentTable' width='100%'><tr><th>用户ID</th><th>用户名</th><th>姓名</th><th>邀请码</th><th>手机号码</th></tr>";
	        for(var i=0; i<dataArray.length; i++){
	       	    tableStr = tableStr + "<tr><td>"+dataArray[i].ID+"</td>"+"<td>"+ dataArray[i].ACCOUNT_NAME +"</td>"+"<td>"+dataArray[i].USER_NAME + "</td>"+"<td>"+dataArray[i].INVITATION_CODE +"</td>"+"<td>"+dataArray[i].PHONE +"</td></tr>";
	        }
	      	tableStr = tableStr + "</table>";
	      	$("#table").html(tableStr);  
	      	var trs = $("#parentTable").find("tr");
			for(var i=0; i< trs.length; i++){
				var tr = trs.eq(i);    //循环获取每一行    
				var td = tr.find("td");  
				$("#parentId").val(td.eq(0).text());    
			}	
		});
	}	
}

function confirm(id){
	if($("#parentId").val()==null || $("#parentId").val() == ""){
		jAlert("没有用户！","提示",function(){
			return false;
        });	
	}else{
		$.post("${ctx}/admin_op_check_person/saveParentUserAjax.htm?id="+id+"&parentId="+$("#parentId").val(),'',function(d){
			if(d==-1){
				jAlert("当前用户还有下层用户，无法设置！","提示",function(){
					return false;
		        });
			}else{
				jAlert("设置成功！","提示",function(){
					$.fancybox.close();
					return true;
		        });
			}
		});		
	}	
}
</script>
