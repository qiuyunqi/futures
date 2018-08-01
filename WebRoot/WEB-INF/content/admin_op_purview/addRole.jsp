<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">新增角色</b>
        <div class="fl"></div>
    </div>
        <form id="roleForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色编号：</div>
            <div class="fl input"><input id="roleCode" name="roleCode" type="text" placeholder="角色编号"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色名称：</div>
            <div class="fl input"><input id="roleName" name="roleName" type="text" placeholder="角色名称"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色说明：</div>
            <div class="fl input"><input id="roleDesc" name="roleDesc" type="text" placeholder="角色说明"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">上级角色：</div>
	            <div class="fl select"><select id="parentId" name="parentId" style="width:178px;">
	            	<c:forEach items="${roleList}" var="role">
	            	<option value="${role.id}">${role.roleName}</option>
	            	</c:forEach>
	            </select></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_purview/saveRoleAjax.htm"><a href="javascript:void(0);" onclick="saveRole();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveRole(){
	var data = $('#roleForm').serialize();
	$.post('${ctx}/admin_op_purview/saveRoleAjax.htm',data,function(d){
		    if(d==-1){
                jAlert("请填写角色编号","提示",function(){
	  				$("#roleCode").focus();
	            });
                return null;
            }
            if(d==-2){
            	jAlert("请填写角色名称","提示",function(){
	  				$("#roleName").focus();
	            });
                return null;
            }
            if(d==-3){
            	jAlert("请选择上级角色","提示",function(){
	  				$("#parentId").focus();
	            });
                return null;
            }
            jAlert("角色添加成功","提示",function(){
				window.location.reload();
        	});
	});
}
</script>