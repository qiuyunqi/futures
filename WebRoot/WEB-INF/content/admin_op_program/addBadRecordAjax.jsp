<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px;">
<form id="badForm">
	<div class=" fc_top" style="width: 600px;padding-bottom:0;">
    	<b class="fl fc_top_font">添加不良记录</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">方&nbsp案&nbspID：</div>
            <div class="fl input none">${program.id}</div>
            <div class="clr"></div>
        	</div>
        </div>
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">用&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp户：</div>
            <div class="fl input none">${program.fuUser.accountName}</div>
            <div class="clr"></div>
        	</div>
        </div>
        <div class="form_cont form_cont0 form_cont01">
            <div class="lf_font fl">类&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp型：</div>
            <div class="fl select"><select id="type" name="type" style="width:180px;">
            	<option value="">--请选择--</option>
            	<option value="1">投资本金</option>
            	<option value="2">管理费</option>
            	<option value="3">其他</option>
            </select></div>
            <div class="clr"></div>
        	</div>
        
    	<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">涉及金额：</div>
            <div class="fl input"><input id="money" name="money" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">时&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp间：</div>
            <div class="fl input"><input id="time" name="time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">不良详情：</div>
            <div class="fl textarea"><textarea id="badDetail" name="badDetail" cols="" rows=""></textarea></div>
            <div class="clr"></div>
        </div>
        
     <div class=" but"><a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function saveInfo(){
		var data=$("#badForm").serialize();
		$.post("${ctx}/admin_op_program/saveBadRecordAjax.htm?id=${id}",data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			if(d==-2){
				sureInfo("确定","请选择类型！","提示");
				return false;
			}
			if(d==-3){
				sureInfo("确定","请选择时间！","提示");
				return false;
			}
			if(d==-4){
				sureInfo("确定","请输入不良详情！","提示");
				return false;
			}
			location=location;
		});
	}
</script>
