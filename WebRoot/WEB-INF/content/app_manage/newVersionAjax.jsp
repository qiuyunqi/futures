<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}版本记录</b>
        <div class="fl"></div>
    </div>
        <form id="versionForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">第几次升级：</div>
            <div class="fl input"><input name="versionCode" value="${version.versionCode}" type="text" ></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">版本号：</div>
            <div class="fl input"><input name="versionName" value="${version.versionName}" type="text" ></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">下载地址：</div>
            <div class="fl input"><input name="downloadUrl" value="${version.downloadUrl}" type="text" ></div>
            <div class="clr"></div>
        </div>    
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">更新信息：</div>
            <%-- <div class="fl input"><input name="updateLog" value="${version.updateLog}" type="text" ></div> --%>
            <div class="fl textarea"><textarea id="replyContent" name="updateLog" cols="" rows="">${version.updateLog}</textarea></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveVersion();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveVersion(){	
	var data = $('#versionForm').serialize();
	$.post('${ctx}/app_manage/saveVersionAjax.htm?id=${version.id}',data,function(d){
        if(d==-2){
            sureInfo("确定","请填写第几次升级","提示");
            return null;
        }
        if(d==-3){
            sureInfo("确定","请填写版本号","提示");
            return null;
        }
        if(d==-4){
            sureInfo("确定","请填写下载地址","提示");
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
