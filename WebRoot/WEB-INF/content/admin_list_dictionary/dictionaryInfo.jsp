<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－字典管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.ztree{height: 810px;width: 300px;overflow-y: auto;}
</style>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    字典管理
    	    </div>
    	    <div id="dicTree" class="ztree"></div>
        </div>
    </div>
</div>

<div class="fuchen" style="position:absolute;top:130px;left:600px;" >
	<form id="personForm">
    	<div class="rt_cont_mat">
    		<div>树中新增的节点默认已启用，如要修改节点的启用状态，请在节点维护完成后再选择启用状态并确定</div>
            <div id = "form" class="form">
	            <form id="searchForm" action="" method="post">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
							    <td>
							        <input id="dicId" type="hidden" value="">
							        <div class="lf_font fl">是否启用：</div>
						            <div class="fl input">
							            <select id="isEnabled" name="isEnabled">
								            <option value=""></option>
								            <option value="1">是</option>
								            <option value="0">否</option>
								        </select>
						            </div>
							    </td>
							    <domi:privilege url="/admin_list_dictionary/saveDictionaryAjax.htm">
								    <td>
								        <div class="form_cont">
								            <div class="fl" style=""><a href="javascript:void(0);" onclick="updateIsEnabled();" class="search">确定</a></div>
								        </div>
								    </td>
								</domi:privilege>
						  </tr>
					</tbody></table>
				</form>
            </div>
        </div>
	</form>
</div>

</body>
</html>
<script>
	var setting = {
	    async : {  
	        enable : true, 
	        url : "${ctx}/admin_list_dictionary/dicTree.htm",        
	        autoParam : [ "id", "name" ]                    
	    }, 
	    data:{ 
	        simpleData : {  
	            enable : true,  
	            idKey : "id",     
	            pIdKey : "pId",
	            rootPId: -1
	        }  
			},  
	    callback : {  
	        beforeEditName: beforeEditName,
			beforeRename: beforeRename,
			onRename: onRename,
			beforeRemove: beforeRemove,
			onRemove: onRemove,
			onClick: zTreeOnClick
	    },
	    view : {
	    	fontCss : setFontCss,
	    	addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
	    },
	    edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
	};  
	
	$(function(){
		$.fn.zTree.init($("#dicTree"), setting);
	});	

	function zTreeOnClick(event, treeId, treeNode) {
		$("#dicId").val(treeNode.id);
		$("#isEnabled").val(treeNode.isEnabled);
	};
	
	function setFontCss(treeId, treeNode) {
		return treeNode.isEnabled == 0? {color:"red"} : {};
	};
	
	function beforeEditName(treeId, treeNode) {
		//alert("beforeEditName");
		var zTree = $.fn.zTree.getZTreeObj("dicTree");
		zTree.selectNode(treeNode);
	}
	
	function beforeRename(treeId, treeNode, newName, isCancel) {
		//alert("treeNode.name: "+treeNode.name+", newName: "+newName);
		//alert("beforeRename");
		if (newName.length == 0) {
			alert("节点名称不能为空！");
			var zTree = $.fn.zTree.getZTreeObj("dicTree");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	
	function onRename(e, treeId, treeNode, isCancel) {
		//alert("onRename");
		$.ajax({
			url: "${ctx}/admin_list_dictionary/commitDictionaryAjax.htm",
			type: "post",
			data: {id:treeNode.isNew==true?null:treeNode.id,pid:treeNode.getParentNode().id,dictionaryName:treeNode.name,isEnabled:treeNode.isEnabled}, 
			success: function(data, textStatus){
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				if(data==1){
		            jAlert("保存成功","提示",function(){
		            	treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
		     	    });
		        }
			},
			error: function(){
				
			}
		});
	}
	
	function beforeRemove(treeId, treeNode) {
		//alert("beforeRemove");
		var zTree = $.fn.zTree.getZTreeObj("dicTree");
		zTree.selectNode(treeNode);
		return confirm("确认删除节点 " + treeNode.name + " 吗？");
	}
	
	function onRemove(e, treeId, treeNode) {
		//alert("onRemove");
		$.ajax({
			url: "${ctx}/admin_list_dictionary/delDictionary.htm",
			type: "post",
			data: {id:treeNode.id}, 
			success: function(data, textStatus){
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				if(data==1){
		            jAlert("删除成功","提示",function(){
		            	treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
		     	    });
		        }
			},
			error: function(){
				
			}
		});
	}
	
	function showRemoveBtn(treeId, treeNode) {
		if(treeNode.level == 0){
			return false;
		}
		if(treeNode.isParent == true){
			return false;
		}
		return true;
	}
	
	function showRenameBtn(treeId, treeNode) {
		if(treeNode.level == 0){
			return false;
		}
		return true;
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("dicTree");
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++), isEnabled:1, isNew:true});
			return false;
		});
	};
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	
	function updateIsEnabled() {
		var dId = $("#dicId").val();
		var isEn = $("#isEnabled").val();
		if(dId == null || dId == ""){
			alert("请选择树节点！");
			return false;
		}
		if(dId == 0){
			alert("根节点不允许修改！");
			return false;
		}
		if(isEn == null || isEn == ""){
			alert("请选择启用状态！");
			return false;
		}
		$.ajax({
			url: "${ctx}/admin_list_dictionary/saveDictionaryAjax.htm",
			type: "post",
			data: {id:dId,isEnabled:isEn}, 
			success: function(data, textStatus){
				if(data==1){
		            jAlert("保存成功","提示",function(){
		            	location.href = location.href;
		     	    });
		        }
			},
			error: function(){
				
			}
		});
	}
</script>
