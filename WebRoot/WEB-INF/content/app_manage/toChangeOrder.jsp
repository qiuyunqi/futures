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
    	<b class="fl fc_top_font">${empty fuProduct.id?'添加':'编辑'}产品</b>
        <div class="fl"></div>
    </div>
        <form id="adForm">
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品名称：</div>
            <div class="fl input"><input name="productName" type="text" value="${fuProduct.name}"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品描述：</div>
            <div class="fl input"><input name="description" type="text" value="${fuProduct.description}"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品收益：</div>
            <div class="fl input"><input name="profit" type="text" value="${fuProduct.profit}"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品收益描述：</div>
            <div class="fl input"><input name="profitDesc" type="text" value="${fuProduct.profitDesc}"></div>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1"> 
            <div class="lf_font fl">产品的logo：</div>
       	  		<a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg1"/></a>
            <div class="clr"></div>
            <div class="form quali_img" id="div1" <c:if test="${empty fuProduct.icon}"> style="display: none;"</c:if>
	   			<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow1" src="${fuProduct.icon}" width="100" height="100"></span>
	       		<input type="hidden" name="icon" id="picValue1" value="${fuProduct.icon}"/>
	       		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
	   		</div>
        </div>
           
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品期数：</div>
	            <div class="fl select">
	            	<select name="contentsId" style="width:178px;">
		           	 	<option value="">不是涨跌赢产品</option>
	            		<c:forEach items="${wqqContents}" var="content">
		            	<option <c:if test="${content.id == fuProduct.wqqContents.id}">selected="selected"</c:if> value="${content.id}">${content.name}</option>
		            	<%-- <option <c:if test="${ad.position == 2}">selected="selected"</c:if> value="2">看跌涨广告位</option> --%>
		            	</c:forEach>
	            	</select>
	            </div>
            <div class="clr"></div>
        </div>    
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品顺序：</div>
            <div class="fl input"><input name="orderNum" type="text" value="${fuProduct.orderNum}"></div>
            <span>可以不填 默认会是最前顺序</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">广告下方的标语：</div>
            <div class="fl input"><input name="adTitle" type="text" value="${fuProduct.adTitle}"></div>
             <span>非必填项</span>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">看涨收益点：</div>
            <div class="fl input"><input name="riseProfit" type="text" value="${fuProduct.riseProfit}"></div>
            <span>非必填项</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">看跌收益点：</div>
            <div class="fl input"><input name="fallProfit" type="text" value="${fuProduct.fallProfit}"></div>
            <span>非必填项</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">看涨看跌的标语：</div>
            <div class="fl input"><input name="title" type="text" value="${fuProduct.title}"></div>
            <span>非必填项</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">截止时间：</div>
            <div class="fl input"><input name="endTime" type="text" value="${fuProduct.endTime}"></div>
            <span>必填项</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品的信息链接：</div>
            <div class="fl input"><input name="infoHref" type="text" value="${fuProduct.infoHref}"></div>
            <span>非必填项</span>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">产品编号：</div>
            <div class="fl input"><input name="productCardId" type="text" value="${fuProduct.productId}"></div>
            <span>非必填项</span>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveProduct(${fuProduct.id});" class="sure fl">保存</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
function saveProduct(){
	var picValue1 = $('#picValue1').val();
	if(picValue1 == null || picValue1 == ""){
		 sureInfo("确定","请选择logo图片 100*100","提示");
         return null;
	}
	var endTime = $("input[name = 'endTime']").val();
	if(null == endTime || "" == endTime) {
		 sureInfo("确定", "截止时间是必填项","提示");
         return null;
	}
	var data = $('#adForm').serialize();
	$.post('${ctx}/app_manage/changeOrder.htm?productId=${fuProduct.id}',data,function(d){
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
