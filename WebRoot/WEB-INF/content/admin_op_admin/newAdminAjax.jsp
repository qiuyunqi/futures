<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}账号</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">账号：</div>
            <div class="fl input"><input name="account" type="text" value="${fuAdmin.account}" placeholder="登录账号" id="account"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">密码：</div>
            <div class="fl input"><input name="password" type="password" value="${fuAdmin.password}" placeholder="密码" id="password"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">确认密码：</div>
            <div class="fl input"><input name="confirmPassword" type="password" placeholder="确认密码" id="confirmPassword"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">姓名：</div>
            <div class="fl input"><input name="name" type="text" value="${fuAdmin.name}" placeholder="姓名" id="name"></div>
            <div class="clr"></div>
        </div>    
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">职位：</div>
            <div class="fl input"><input name="jobDesc" type="text"  value="${fuAdmin.jobDesc}" placeholder="职位" id="position"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色：</div>
	            <div class="fl select"><select id="roleId" name="roleId" style="width:178px;">
	            	<c:forEach items="${roleList}" var="role">
	            	<option <c:if test="${rolename==role.roleName}">selected="selected"</c:if> value="${role.id}">${role.roleName}</option>
	            	</c:forEach>
	            </select></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">邮箱：</div>
            <div class="fl input"><input name="email" type="text" value="${fuAdmin.email}" placeholder="邮箱" id="email"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">手机号：</div>
            <div class="fl input"><input name="phone" type="text" value="${fuAdmin.phone}" placeholder="手机号" id="phone"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">登录错误次数：</div>
            <div class="fl input"><input name="loginErrorTimes" type="text"  value="<fmt:formatNumber value="${fuAdmin.loginErrorTimes}" pattern="#"/>" id="val1"></div>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">禁止登录时间：</div>
            <div class="fl input"><input name="forbidLoginTime" type="text" value="<fmt:formatDate value='${fuAdmin.forbidLoginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" placeholder=""  onclick="WdatePicker({dateFmt : 'yyyy-MM-dd HH:mm:ss'});"><i class="riqi" ></i></div>
            <div class="clr"></div>
        </div>
        
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveAdmin();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
var sub = false;
function saveAdmin(){
	if(sub){
		return;
	}
	sub = true;
	var val1 = $("#val1").val();
	if(isNaN(val1)){
		sureInfo("确定","格式错误，请输入数字！","提示");
        return null;
	}
	var data = $('#adminForm').serialize();
	$.post('${ctx}/admin_op_admin/saveAdminAjax.htm?id=${id}',data,function(d){
		    sub = false;
		    if(d==-1){
                sureInfo("确定","您没有操作权限","提示");
                return null;
            }
            if(d==-2){
            	jAlert("请填写账号","提示",function(){
	  				$("#account").focus();
	            });
                return null;
            }
            if(d==-3){
                sureInfo("确定","该账号已存在","提示");
                return null;
            }
            if(d==-4){
            	jAlert("请填写密码","提示",function(){
	  				$("#password").focus();
	            });
                return null;
            }
            if(d==-5){
            	jAlert("请填写确认密码","提示",function(){
	  				$("#confirmPassword").focus();
	            });
                return null;
            }
            if(d==-6){
            	jAlert("两次密码输入不一致","提示",function(){
	  				$("#confirmPassword").focus();
	            });
                return null;
            }
            if(d==-7){
            	jAlert("请填写姓名","提示",function(){
	  				$("#name").focus();
	            });
                return null;
            }
            if(d==-8){
            	jAlert("请填写职位","提示",function(){
	  				$("#position").focus();
	            });
                return null;
            }
            if(d==-9){
            	jAlert("请填写邮箱","提示",function(){
	  				$("#email").focus();
	            });
                return null;
            }
            if(d==1){
                jAlert("保存成功！","提示",function(){
     			   location.href = location.href;
          	   });
            }
	});
}
</script>
