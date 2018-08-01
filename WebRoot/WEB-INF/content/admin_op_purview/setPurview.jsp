<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">配置权限</b>
        <div class="fl"></div>
    </div>
    <input id="roleId" type="hidden" value="${roleId}"/>
    <form id="roleForm">
	  	<!-- 权限窗口 -->  
	    <div id="menuDiv" title="配置权限" style="width:350px;padding:10px">  
	        <div id="tree" class="ztree" style="padding: 10px 20px;"></div>  
	    </div>  
	    <div id="menuWindowfooter" style="padding:5px;text-align:right;">   
	    	<a href="javascript:void(0);" onclick="$.fancybox.close();" class="sure fl" style="width:80px;float:right;">取消</a> 
	        <domi:privilege url="/admin_op_purview/saveRolePurview.htm"><a href="javascript:void(0);" onclick="ajaxSubmit()" class="sure fl" style="width:80px;float:right;">提交</a></domi:privilege> 
	    </div>  
	</form>
</div>

<script>
var globalId = null;
var setting = {
	async : {  
        enable : true, 
        url : "${ctx}/admin_op_purview/treedata.htm?roleId="+$("#roleId").val(),        
        autoParam : ["id", "name"]                    
    }, 
	check : {
		chkboxType:{"Y":"ps","N":"ps"},//勾选checkbox对于父子节点的关联关系,取消勾选时不关联父
		chkStyle:"checkbox",
		enable : true	//是否复选框
	},
	//数据
	data : {
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


$(function(){
	$.fn.zTree.init($("#tree"), setting);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandAll(true);
});

//角色-菜单信息入库
function ajaxSubmit(){
	 var roleId = $("#roleId").val();
	 var treeObj=$.fn.zTree.getZTreeObj("tree");
     var nodes=treeObj.getCheckedNodes(true);
     var purviewArray = "";
     for(var i=0;i<nodes.length;i++){
    	//获取选中节点的值
    	 purviewArray = purviewArray + nodes[i].id + ",";
     }
     purviewArray=purviewArray.substring(0,purviewArray.length-1);
     jConfirm("您确定要修改当前角色的权限吗？","提示",function(res){
		if(res){
			$.post("${ctx}/admin_op_purview/saveRolePurview.htm",{"roleId":roleId,"purviewArray":purviewArray},function(data){
				jAlert("修改成功！","提示",function(){
					location.href=location.href;
        		});
			});
		}
	})
}

</script>
