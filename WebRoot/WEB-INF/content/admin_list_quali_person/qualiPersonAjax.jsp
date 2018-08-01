<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="personForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">资格认证</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">真实姓名：</div>
            <div class="fl input none">${empty quali.userName?'无':quali.userName}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">资格证号：</div>
            <div class="fl input none">${empty quali.qualiNum?'无':quali.qualiNum}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">资格证照片：</div>
            <div class="fl photo">
            	<c:if test="${empty quali.qualiPic}">
            	无
	            </c:if>
	            <c:if test="${!empty quali.qualiPic}">
	            	<img src="${quali.qualiPic}" width="410" height="552" alt="">
	            </c:if>
            </div>
            <div class="clr"></div>
        </div>
                   
        <div class="form_cont form_cont0 form_cont01" style="margin-top: 20px">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl select"><select id="flag" name="flag">
            	<option <c:if test="${quali.isChecked==0}">selected="selected"</c:if>  value="0">未认证</option>
            	<option <c:if test="${quali.isChecked==1}">selected="selected"</c:if> value="1">待认证</option>
            	<option <c:if test="${quali.isChecked==2}">selected="selected"</c:if> value="2">已认证</option>
            	<option <c:if test="${quali.isChecked==3}">selected="selected"</c:if> value="3">信息有误</option>
            </select></div>
            <div class="clr"></div>
        </div>        
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function saveInfo(){
		var data=$("#personForm").serialize();
		$.post("${ctx}/admin_list_quali_person/saveQualiInfoAjax.htm?id=${id}",data,function(d){
			jAlert("保存成功！","提示",function(){
				location.href = location.href;
	        });
		});
	}
</script>
