<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style type="text/css">
	.form_cont0 .lf_font{width:180px !important;}
	.quali_img {width:400px;margin: 0 auto;}
	.open_night_in{margin-left:95px;}
	.open_night_in .is_in_night{width:110px;}
</style>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'编辑'}广告位</b>
        <div class="fl"></div>
    </div>
        <form id="adForm">
        <div class="form_cont form_cont0 form_cont1"> 
            <div class="lf_font fl">广告图片：</div>
       	  		<a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg1"/></a>
            <div class="clr"></div>
            <div class="form quali_img" id="div1" <c:if test="${empty ad.imageUrl}"> style="display: none;"</c:if>
	   			<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow1" src="${ad.imageUrl}" width="320" height="180"></span>
	       		<input type="hidden" name="imageUrl" id="picValue1" value="${ad.imageUrl}"/>
	       		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
	   		</div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">图片属性：</div>
            <div class="fl input"><input name="imageAttr" type="text" value="${ad.imageAttr}" id="hrefUrl"></div>
            <span>没有就不填写</span>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">广告外链：</div>
            <div class="fl input"><input name="hrefUrl" type="text" value="${ad.hrefUrl}" id="hrefUrl"></div>
            <span>没有就不填写</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">广告位置：</div>
	            <div class="fl select">
	            	<select name="posit" style="width:178px;">
		            	<option <c:if test="${ad.position == 1}">selected="selected"</c:if> value="1">首页广告位</option>
		            	<option <c:if test="${ad.position == 2}">selected="selected"</c:if> value="2">看跌涨广告位</option>
	            	</select>
	            </div>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">广告顺序：</div>
            <div class="fl input"><input name="orderNum" type="text" value="${ad.orderNum}"></div>
            <span>可以不填 默认会是最前顺序</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">跳转到那个产品：</div>
	            <div class="fl select">
	            	<select name="fuProductId" style="width:178px;">
	            		<option value="">不跳转</option>
	            		<c:forEach items="${productList}" var="product">
		            		<option <c:if test="${product.productId == ad.fuProductId}">selected="selected"</c:if> value="${product.productId}">${product.name}</option>
		            	</c:forEach>
	            	</select>
	            </div>
            <div class="clr"></div>
        </div>
        
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveAd(${ad.id});" class="sure fl">保存</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
function saveAd(){
	var picValue1 = $('#picValue1').val();
	if(picValue1 == null || picValue1 == ""){
		 sureInfo("确定","请选择广告图片","提示");
         return null;
	}
	var data = $('#adForm').serialize();
	$.post('${ctx}/admin_ad/saveAdAjax.htm?id=${id}',data,function(d){
       if(d==-2){
           sureInfo("确定","请上传图片","提示");
           return null;
       }
       if(d==1){
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
