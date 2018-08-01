<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超级合伙人</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<%@include file="../common/cssback.jsp" %>
</head>
<style type="text/css">
	.form_cont0 .lf_font{width:180px !important;}
	.quali_img {width:400px;margin: 0 auto;}
	.open_night_in{margin-left:95px;}
	.open_night_in .is_in_night{width:110px;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">短信息群发</b>
        <div class="fl"></div>
    </div>
        <form id="adForm">
         <%-- div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品名称：</div>
            <div class="fl input"><input name="productName" type="text" value="${fuProduct.name}"></div>
            <div class="clr"></div>
        </div> --%>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">运营商：</div>
	            <div class="fl select">
	            	<select name="operator" style="width:178px;">
		            	<option selected="selected" value="1">融合通信</option>
		            	<!-- <option>看跌涨广告位</option> -->
	            	</select>
	            </div>
            <div class="clr"></div>
        </div>
        
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">手机号码：</div>
            <div class="fl input"><textarea name="phones" rows="4"cols="43"></textarea></div>
            <span>多个号码请换行</span>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
           <div class="lf_font fl">短信消息：</div>
           <div class="fl input"><textarea name="message" rows="4"cols="54"></textarea></div>
           <div class="clr"></div>
        </div>
        
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveProduct(${fuProduct.id});" class="sure fl">发送短信</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
function saveProduct(){
	
	var data = $('#adForm').serialize();
	$.post('${ctx}/temp_code/sendInfo.htm',data,function(d){
	   if(d == -1){
           sureInfo("确定","产品名称不能为空","提示");
           return null;
       }
       if(d == -2){
           sureInfo("确定","产品描述不能为空","提示");
           return null;
       }
       if(d == -3){
           sureInfo("确定","产品收益不能为空","提示");
           return null;
       }
       if(d == -4){
           sureInfo("确定","产品收益描述不能为空","提示");
           return null;
       }
       if(d == -5){
           sureInfo("确定","请上传图片100*100","提示");
           return null;
       }
       
       if(d == 1){
           jAlert("保存成功！","提示",function(){
			   location.href = location.href;
     	   });
       }
	});
}

jQuery("#uploadImg1").uploadify({
	'uploader' : '${ctx}/js/uploadify-v2.1.4/uploadify.swf',
	'script' : '${ctx}/upload/img.htm',
	'cancelImg' : '${ctx}/js/uploadify-v2.1.4/cancel.png',
	'fileDataName' : 'imgFile', //相当于  file 控件的 name
	'auto' : true,
	'multi' : false,
	'queueID':'imgQueue1',
	'buttonImg' : '${ctx}/qihuo_images/xzwj_03.gif',
	'height' : '28',
	'width' : '82',
	'fileDesc' : '能上传的图片类型:jpg,gif,bmp,jpeg,png', //出现在上传对话框中的文件类型描述
	'fileExt' : '*.jpg;*.gif;*.bmp;*.jpeg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	'sizeLimit' : 3*1024*1024,
	onComplete:function(event,queueID,fileObj,response,data){
			var jsondata = eval("("+response+")");
			if(jsondata.error==1){
				Dialog.alert(jsondata.message);
				return false;
			}
			$("#picShow1").attr("src",jsondata.saveDir);
			$("#picValue1").val(jsondata.saveDir);
			$("#div1").show();
			$("#span1").html("已上传文件："+jsondata.fileName);
		},
		'onSelect' : function(event,queueID, fileObj) {
			if (fileObj.size > 5*1024*1024) {
				Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
				jQuery("#uploadImg1").uploadifyClearQueue();
			}
		}
});
</script>
</html>