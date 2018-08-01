<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style type="text/css">
	.hidden{background-color:black; background-color: rgba(0,0,0,0.5);  padding:10px;position: absolute;z-index: 99;top:200px;width:420px;height:280px;border-radius: 15px;}
	.hidden img{display: block;height: 100%;width: 100%;}
</style>
<div class="fuchen" style="width: 600px">
<form id="personForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">身份认证</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">真实姓名：</div>
            <div class="fl input none">${empty u.userName?'无':u.userName}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
            <div class="lf_font fl">证件号：</div>
            <div class="fl input none">${empty u.cardNumber?'无':u.cardNumber}</div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0">
            <div class="lf_font fl">正面照片：</div>
            <div class="fl photo">
            	<c:if test="${empty u.cardBeforePic}">
            	无
	            </c:if>
	            <c:if test="${!empty u.cardBeforePic}">
	            <a class="front" href="javascript:void(0);"  title="正面照片">
	            	<img src="${u.cardBeforePic}" width="410" height="552" alt="" >
	            </a>
	            <div class="hidden first">
	            	<img src="${u.cardBeforePic}"  alt="" title="再次点击图片关闭">
	            </div>
	            </c:if>
            </div>
            <div class="clr"></div>
        </div>
           
     <div class="form_cont form_cont0">
            <div class="lf_font fl">背面照片：</div>
            <div class="fl photo">
            	<c:if test="${empty u.cardBehindPic}">
            	无
	            </c:if>
	            <c:if test="${!empty u.cardBehindPic}">
	            <a  class="back" href="javascript:void(0);"  title="背面照片">
	            	<img src="${u.cardBehindPic}" width="410" height="552" alt="" >
	            </a>
	            <div class="hidden second">
	            	<img src="${u.cardBehindPic}"  alt="" title="再次点击图片关闭">
	            </div>
	            </c:if>
            </div>
            <div class="clr"></div>
        </div>
    <div class="form_cont form_cont0">
            <div class="lf_font fl">手持身份证照片：</div>
            <div class="fl photo">
            	<c:if test="${empty u.cardHandPic}">
            	无
	            </c:if>
	            <c:if test="${!empty u.cardHandPic}">
	            <a class="card" href="javascript:void(0);"  title="手持身份证照片">
	            <img src="${u.cardHandPic}" width="410" height="552" alt="" >
	            </a>
	            <div class="hidden third">
	            	<img src="${u.cardHandPic}"  alt="" title="再次点击图片关闭">
	            </div>	
	            </c:if>
            </div>
            <div class="clr"></div>
        </div>
        
        <div class="form_cont form_cont0 form_cont01" style="margin-top: 20px">
            <div class="lf_font fl">审核结果：</div>
            <div class="fl select"><select id="flag" name="flag">
            	<option <c:if test="${u.isCheckCard==0}">selected="selected"</c:if>  value="0">未认证</option>
            	<option <c:if test="${u.isCheckCard==1}">selected="selected"</c:if> value="1">待验证</option>
            	<option <c:if test="${u.isCheckCard==2}">selected="selected"</c:if> value="2">已验证</option>
            	<option <c:if test="${u.isCheckCard==3}">selected="selected"</c:if> value="3">信息有误</option>
            </select></div>
            <div class="clr"></div>
        </div>
        
     <div class=" but">
     <domi:privilege url="/admin_op_check_person/saveCheckDetailAjax.htm">
     <a href="javascript:void(0);" onclick="saveInfo();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>

<script>
$(".first").hide();
$(".second").hide();
$(".third").hide();
	$(function(){
		$(".front").bind("click",function(){
			$(".first").show();
		})
		$(".back").bind("click",function(){
			$(".second").show();
		})
		$(".card").bind("click",function(){
			$(".third").show();
		})
		$(".first").bind("click",function(){
			$(this).hide();
		})
		$(".second").bind("click",function(){
			$(this).hide();
		})
		$(".third").bind("click",function(){
			$(this).hide();
		})
		
	});

	function saveInfo(){
		var data=$("#personForm").serialize();
		$.post("${ctx}/admin_op_check_person/saveCheckDetailAjax.htm?id=${id}",data,function(d){
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			$('#pageForm').size()>0?$('#pageForm').submit():location.href=location.href;
		});
	}
</script>
