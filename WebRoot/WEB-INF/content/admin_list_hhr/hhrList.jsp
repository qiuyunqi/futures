<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－合伙人管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.ztree{height: 810px;width: 300px;overflow-y: auto;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="13"/>
<div class="content hhtent" >
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">合伙人管理</div>
            <div id="userTree" class="ztree"></div>
        </div>
    </div>
</div>

<div class="fuchen" style="width: 900px; height:500px ;position:absolute;top:130px;left:600px;" >
	<form id="personForm">
    	<div class="rt_cont_mat">
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
					    <!-- 
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">手机号码：</div>
					            <div class="fl input"><input id="pPhone" name="pPhone" value="${pPhone}" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					     -->
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">子ID：</div>
					            <div class="fl input"><input id="subId" name="subId" value="" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">-->移到该PID：</div>
					            <div class="fl input"><input id="parentId" name="parentId" value="${parentId}" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <!-- 
					    <td>
					        <div class="form_cont">
					            <div class="fl"><a href="javascript:void(0);" onclick="searchParent();" class="search">搜索</a></div>
					        </div>
					    </td>
					     -->
					    <domi:privilege url="/admin_op_check_person/saveParentUserAjax.htm">
						    <td>
						        <div class="form_cont">
						            <div class="fl" style=""><a href="javascript:void(0);" onclick="confirm(globalId);" class="search">确认</a></div>
						        </div>
						    </td>
						</domi:privilege>
						 <domi:privilege url="/admin_op_check_person/updateHhrAjax.htm">
						    <td>
						        <div class="form_cont">
						            <div class="fl"><a href="javascript:void(0);" onclick="updateHhr();" class="search">统计合伙人</a></div>
						        </div>
						    </td>
					    </domi:privilege>
					  </tr>
					</tbody></table>
				</form>
            </div>
          <div class=" yhlb_title">用户列表</div>
          <div id="table" class="yhlb"></div>  
        </div>
	</form>
</div>

</body>
</html>
<script>
    var globalId = null;
	var setting = {
        async : {  
            enable : true, 
            url : "${ctx}/admin_list_hhr/hhrTree.htm",        
            autoParam : [ "id", "name" ]                    
        }, 
        data:{ 
            simpleData : {  
                enable : true,  
                idKey : "id",     
                pIdKey : "pId",
                rootPId: 0
            }  
  		},  
        callback : {  
            onClick : function(event, treeId, treeNode, clickFlag) {  
                if(true) {
                	globalId = treeNode.id;
                }  
            },  
            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数  
            onAsyncSuccess : function(event, treeId, treeNode, msg){  
            	
            }
        }  
	};  
	
	function searchParent(){
		if($("#pPhone").val()==null || $("#pPhone").val() == ""){
			jAlert("未按手机号码搜索！","提示",function(){
				return false;
	        });	
		}else{
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
		if($("#subId").val()==null || $("#subId").val() == ""){
			jAlert("没有子用户！","提示",function(){
				return false;
	        });	
		}else if($("#parentId").val()==null || $("#parentId").val() == ""){
			jAlert("没有父用户！","提示",function(){
				return false;
	        });	
		}else{
			$.post("${ctx}/admin_op_check_person/saveParentUserAjax.htm?id="+$("#subId").val()+"&parentId="+$("#parentId").val(),'',function(d){
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
	
	function updateHhr(){
		$.post("${ctx}/admin_op_check_person/updateHhrAjax.htm",'',function(d){
			jAlert("设置成功！","提示",function(){
				$.fancybox.close();
				return true;
	        });
		});		
	}
	
	$(function(){
		$.fn.zTree.init($("#userTree"), setting);
	});	
	
	
	
</script>
