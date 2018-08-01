<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－添加资格认证</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

    <div class="mgrzx">
    <%@include file="../common/left.jsp" %>
    <div class="mgrzxr">
    <div class="h20"></div>
    <div class="mgrrul">
      <ul>
        <li id="one3" onclick="setTab(this);" class="mgractive">资格认证</li>
      </ul>
    </div>
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzfbzz">
          <form id="cardForm">
	          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
	            <tr>
	              <th width="100">真实姓名：</th>
	              <td>${fuUser.userName}</td>
	            </tr>            
	            <tr>
	              <th>资格证号：</th>
	              <td><input name="qualiNum" type="input" value="${quali.qualiNum}" class="textput" id="qualiNum"></td>
	            </tr>
	            <tr>
	              <input name="userId" type="hidden" value="${fuUser.id}" id="userId"/>
	            </tr>
	            
	            <tr>
	              <th>证件类型：</th>
	              <td>
	              	<div class="form_cont form_cont0 form_cont01" style="">
			            <div class="fl quali_type">
			            	<select id="type" name="type">
			            		<option <c:if test="${quali.type==1}">selected="selected"</c:if>value="1">期货从业资格</option>
			            		<option <c:if test="${quali.type==2}">selected="selected"</c:if>value="2">证券从业资格</option>
			            	</select>
			            </div>
			        </div>
	              </td>
	            </tr>
	            <tr>
	              <th>证件照片：</th>
	              <td>
	              	  <div class="form czp">
	              	  	<a href="javascript:void(0);"><input type="file" name="imgFile" id="uploadImg1"/></a>
		              </div>		              
	              </td>
	              <td>
	              	<div class="form quali_img" id="div1" <c:if test="${empty quali.qualiPic}">style="display: none;"</c:if>
		    			<span class="sctp" style="margin-left: 116px"><img style="margin-top: -15px" id="picShow1" src="${quali.qualiPic}" width="300" height="200"></span>
		        		<input type="hidden" name="qualiPic" id="picValue1" value="${quali.qualiPic}"/>
		        		<div id="imgQueue1" style="position: absolute;margin-left: 65px"></div>
		    		</div>
	              </td>
	            </tr>
	            <tr>
	              <th></th>
	              <td>
	              	<a href="javascript:void(0);" onclick="saveCard();" class="quali_sure">保存</a>
	              	<a href="${ctx}/user_center/userQuali.htm" class="cancel">取消</a>
	              </td>
	            </tr>
	          </table>
          </form>
                    
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>

<script>
function saveCard(){
  var data = $("#cardForm").serialize();
  $.post("${ctx}/user_center/saveQualiInfoAjax.htm?id=${id}",data,function(d){
	   if(d==-2){
	       sureInfo("确定","请输入资格证号","提示");
	       return null;
	   }
	   
	   if(d==-3){
	       sureInfo("确定","同类型证书只能有1份","提示");
	       return null;
	   }
	   
	   if(d==-4){
	       sureInfo("确定","资格证号不能重复","提示");
	       return null;
	   }
	   
	   if(d==-5){
	       sureInfo("确定","资格证号无效","提示");
	       return null;
	   }
	   
	   jAlert("保存成功！","提示",function(){
			location.href = location.href;
       });
       
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
