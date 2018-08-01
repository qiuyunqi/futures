<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:600px;">
	<form id="picForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>实名认证-上传照片</span><div class="clr"></div></div>
		  <p style="color:#be0200; margin:10px 0 0 20px;">正常审核周期为24小时，上传身份证照片后审核周期为半小时之内。</p>
		  <p style="color:#be0200; margin-left:20px;">上传文件类型支持：jpg,gif,bmp,单个文件不超过2M</p>
		    <div class="form czp">
		        <span>身份证正面照片：</span>
		        <a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg1"/></a>
		        
		       	  <span id="span1">${empty fuUser.cardBeforePic?'未选择任何文件':'已上传文件：'}</span>
		    </div>
		    <div class="form czp" id="div1" <c:if test="${empty fuUser.cardBeforePic}">style="display: none;"</c:if> >
		    	<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow1" src="${fuUser.cardBeforePic}" width="82" height="28"></span>
		        <input type="hidden" name="cardBeforePic" id="picValue1" value="${fuUser.cardBeforePic}"/>
		        <div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
		    </div>
		    
		    <div class="form czp">
		        <span>身份证背面照片：</span>
		        <a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg2"/></a>
		        <span id="span2">${empty fuUser.cardBehindPic?'未选择任何文件':'已上传文件：'}</span>
		    </div>
		    <div class="form czp" id="div2" <c:if test="${empty fuUser.cardBehindPic}">style="display: none;"</c:if> >
		    	<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow2" src="${fuUser.cardBehindPic}" width="82" height="28"></span>
		        <input type="hidden" name="cardBehindPic" id="picValue2" value="${fuUser.cardBehindPic}"/>
		        <div id="imgQueue2" style="position: absolute;margin-left: 65px"></div>
		    </div>
		    
		    <div class="form czp">
		        <span>手持身份证照片：</span>
		        <a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg3"/></a>
		        
		       	  <span id="span3">${empty fuUser.cardHandPic?'未选择任何文件':'已上传文件：'}</span>
		    </div>
		    <div class="form czp" id="div3" <c:if test="${empty fuUser.cardHandPic}">style="display: none;"</c:if> >
		    	<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow3" src="${fuUser.cardHandPic}" width="82" height="28"></span>
		        <input type="hidden" name="cardHandPic" id="picValue3" value="${fuUser.cardHandPic}"/>
		        <div id="imgQueue3" style="position: absolute;margin-left: 65px"></div>
		    </div>
		    <div class=" smwz">
		    	<div class=""></div>
		    </div>
		    <div style="text-align:center; margin:30px 0 30px 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="saveName();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	   	 </div>
    </form>
</div>

<%--<div class=" biaodan fc_bd" style="margin-left:20px">
	            	<div class="fl lf_mc">图片:</div>
	                <div class="fl rt_bd sctp"><img id="picShow"  src="${empty pic?'/images/25589649_1378972044036_1440x900.jpg':pic }" width="420" height="263" /><a href="javascript:void(0);" class="sctp_img"><input type="file" name="imgFile" id="uploadImg"/></a></div>
	                <p style="color:gray;font-size:12px;">(建议尺寸：360像素*200像素)</p>
	                <input type="hidden" name="pic" id="picValue" value="${empty pic?'/images/25589649_1378972044036_1440x900.jpg':pic}""/>
	                <div id="imgQueue" style="position: absolute;margin-left: 65px"></div>
	                <div class="clr"></div>
	        </div> 
--%>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>

<script>
	function saveName(){
		var data=$("#picForm").serialize();
		$.post("${ctx}/user_center/savePersonCardAjax.htm",data,function(d){
			if(d==-2){
				sureInfo('确定','请上传身份证正面照！','提示');
				return false;
			}
			if(d==-3){
				sureInfo('确定','请上传身份证背面照！','提示');
				return false;
			}
			if(d==-4){
				sureInfo('确定','请上传身份证手持照！','提示');
				return false;
			}
			location=location;
		});
	}
	jQuery("#uploadImg1").uploadify({
		'uploader' : '${ctx}/js/uploadify-v2.1.4/uploadify.swf',
		'script' : '${ctx}/upload/img.htm',
	<%--	'scriptData':{width:600,height:600},--%>
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
	jQuery("#uploadImg2").uploadify({
		'uploader' : '${ctx}/js/uploadify-v2.1.4/uploadify.swf',
		'script' : '${ctx}/upload/img.htm',
	<%--	'scriptData':{width:600,height:600},--%>
		'cancelImg' : '${ctx}/js/uploadify-v2.1.4/cancel.png',
		'fileDataName' : 'imgFile', //相当于  file 控件的 name
		'auto' : true,
		'multi' : false,
		'queueID':'imgQueue2',
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
				$("#picShow2").attr("src",jsondata.saveDir);
				$("#picValue2").val(jsondata.saveDir);
				$("#div2").show();
				$("#span2").html("已上传文件："+jsondata.saveDir);
			},
			'onSelect' : function(event,queueID, fileObj) {
				if (fileObj.size > 5*1024*1024) {
					Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
					jQuery("#uploadImg2").uploadifyClearQueue();
				}
			}
	});
	jQuery("#uploadImg3").uploadify({
		'uploader' : '${ctx}/js/uploadify-v2.1.4/uploadify.swf',
		'script' : '${ctx}/upload/img.htm',
	<%--	'scriptData':{width:600,height:600},--%>
		'cancelImg' : '${ctx}/js/uploadify-v2.1.4/cancel.png',
		'fileDataName' : 'imgFile', //相当于  file 控件的 name
		'auto' : true,
		'multi' : false,
		'queueID':'imgQueue3',
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
				$("#picShow3").attr("src",jsondata.saveDir);
				$("#picValue3").val(jsondata.saveDir);
				$("#div3").show();
				$("#span3").html("已上传文件："+jsondata.saveDir);
			},
			'onSelect' : function(event,queueID, fileObj) {
				if (fileObj.size > 5*1024*1024) {
					Dialog.alert("图片"+ fileObj.name+ " 应小于5M");
					jQuery("#uploadImg3").uploadifyClearQueue();
				}
			}
	});
</script>