<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
.quali_img {width:400px;margin: 0 auto;}
.open_night_in{margin-left:95px;}
.open_night_in .is_in_night{width:110px;}
</style>
<div class="fuchen" style="width:1000px;">
	<div class=" fc_top" style="width:100%;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'编辑'}帮助中心</b>
        <div class="fl"></div>
    </div>
        <form id="articleForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">标题：</div>
            <div class="fl input"><input name="title"  type="text" value="${article.title}" id="title"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">内容：</div>
            <form style="margin: 0;">
				<textarea name="content" id="content" style="width:700px;height:200px;visibility:hidden; display: block;"></textarea>
			</form>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">帮助类型：</div>
            <div class="fl select">
            <select id="dictionaryId" style="width:150px;" name="dictionaryId">
            	<c:forEach items="${dictionaries }" var="dic">
            		<option <c:if test="${article.fuDictionary.id==dic.id}">selected="selected"</c:if> value="${dic.id}">${dic.name}</option>
            	</c:forEach>
            </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/article_manage/saveArticleHelpAjax.htm"><a href="javascript:void(0);" onclick="saveArticle(${article.id});" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<link href="${ctx}/js/uploadify-v2.1.4/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/kindeditor-4.1.9/themes/default/default.css" />
<script charset="utf-8" src="${ctx}/js/kindeditor-4.1.9/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/js/kindeditor-4.1.9/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.min.js"></script>
<script>
var editor;
editor = KindEditor.create("#content",{
	resizeType : 1,
	allowPreviewEmoticons : false,
	allowImageUpload : false,
	items : [
		'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		'insertunorderedlist', '|',  'link']
});
editor.html("${article.content}");

function saveArticle(){
	var data = $('#articleForm').serialize();
	var str = editor.html();
	var dictionaryId=$("#dictionaryId").val();
	$.post('${ctx}/article_manage/saveArticleHelpAjax.htm?id=${id}&content='+str+'&dictionaryId='+dictionaryId,data,function(d){
       if(d==-1){
           sureInfo("确定","请输入标题","提示");
           return null;
       }
       if(d==-2){
           sureInfo("确定","请输入内容","提示");
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
