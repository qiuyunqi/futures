<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－推广链接</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style=" background:#f2f2f2">
<%@include file="../common/userTop.jsp" %>
<div class="center">
	 <c:set var="pg" value="9"></c:set>
	<%@include file="../common/left.jsp" %>
    <div class="fl rt_cont">
    	<div class="rt_cont_title">客服电话：010-53320537&nbsp;&nbsp;&nbsp;&nbsp;工作时间：8:30-17:00</div>
        <div class=" dxgs">
        	<ul>
            	<li><a href="${ctx}/user_promote/promoteDetail.htm">推广详情</a></li>
                <li  class="yxz"><a href="${ctx}/user_promote/promoteLink.htm">推广链接</a></li>
                <li><a href="${ctx}/user_promote/visitRecord.htm">访问记录</a></li>
                <li><a href="${ctx}/user_promote/myUsers.htm">我的用户</a></li>
            </ul>
            <div class="clr"></div>
        </div>
        <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">推广链接</span>
            
        </div>
      	  <p class="wzys">以下网址是您对外界进行推广的地址，您可以通过朋友、QQ、微信、博客、论坛或自己的网站进行推广，所有通过该地址访问过来的人，注册后就都属于您的用户，而当这些用户在本站配资时，您就可以赚取佣金了，详细的推广情况可到访问记录里查看。</p>
      <div class="form czje">
        	<div class="fl form_font">您的推广链接：</div>
            <div class="fl fzljbd">
            	<input type="text" value="https://www.kunzhoudade.com/promote?u=${u}"  id="link1_txt">
            </div>
            <input name="" type="button" data-clipboard-target="link1_txt" value="复制链接" class="tijiao fzlj fl" id="link_clip_button1"><span id="copyAlert1" style="line-height:30px;margin-left:10px;color:red;"></span>
            <div class="clr"></div>
        </div> 
       
        <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">广告图片</span>
      </div>
      	  <p class="wzys" style="margin-bottom:10px;">选择适合您推广尺寸的广告素材，广告图片会自动根据各类活动自动更新。</p>
      <div class="form bdtz">
        	<div class="fl form_font" style="width:50px;">横幅：</div>
            <div class="fl radio">
            	<input name="cross" type="radio" value="" checked  onclick="changImg('950','90');"><span>950*90 </span>
                <input name="cross" type="radio" value="" onclick="changImg('760','90');"><span>760*90</span>
                <input name="cross" type="radio" value="" onclick="changImg('728','90');"><span>728*90</span>
                <input name="cross" type="radio" value="" onclick="changImg('640','90');"><span>640*90 </span>
                <input name="cross" type="radio" value="" onclick="changImg('300','100');"><span>300*100</span>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form bdtz">
        	<div class="fl form_font" style="width:50px;">矩形：</div>
            <div class="fl radio">
            	<input name="cross" type="radio" value="" onclick="changImg('336','280');"><span>336*280 </span>
                <input name="cross" type="radio" value="" onclick="changImg('300','250');"><span>300*250</span>
                <input name="cross" type="radio" value="" onclick="changImg('250','250');"><span>250*250</span>
                <input name="cross" type="radio" value="" onclick="changImg('200','200');"><span>200*200</span>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form bdtz">
        	<div class="fl form_font" style="width:50px;">竖幅：</div>
            <div class="fl radio">
            	<input name="cross" type="radio" value="" onclick="changImg('160','600');"><span>160*600</span>
                <input name="cross" type="radio" value="" onclick="changImg('120','600');"><span>120*600 </span>
                <input name="cross" type="radio" value="" onclick="changImg('120','240');"><span>120*240</span>
            </div>
            <div class="clr"></div>
        </div>
        
      <div class="form czje">
        	<div class="fl form_font">图片推广代码：</div>
            <div class="fl fzljbd" >
            <input type="text" id="link2_txt" value="&lt;a href=&quot;https://www.kunzhoudade.com/promote?u=${u}&quot; target=&quot;_blank&quot;&gt;&lt;img src=&quot;https://www.kunzhoudade.com/qihuo_images/promote/950x90.jpg&quot;  border=&quot;0&quot;/&gt;&lt;/a&gt;" >
            </div>
            <input name="" type="button" value="复制链接" data-clipboard-target="link2_txt" class="tijiao fzlj fl" id="link_clip_button2"><span id="copyAlert2" style="line-height:30px;margin-left:10px;color:red;"></span>
            <div class="clr"></div>
        </div> 
        <div class=" tptg"><img src="${ctx}/qihuo_images/promote/950x90.jpg"  alt="" id="showImg"></div>
  </div>
    <div class="clr"></div>
</div>
</body>
</html>

<script type="text/javascript">
function changImg(crow,vertical){
	var path = "${ctx}/qihuo_images/promote/";
	var text_path_1 = "<a href=\"https://www.kunzhoudade.com/promote?u=${u}\" target=\"_blank\"><img src=\"http://www.kunzhoudade.com/qihuo_images/promote/";
	var text_path_2 = "\" border=\"0\"/></a>";
	var suffix = ".jpg";
	$('#link2_txt').val(text_path_1+crow+"x"+vertical+suffix+text_path_2);
	$('#showImg').attr('src',path+crow+"x"+vertical+suffix);
}

var clip = new ZeroClipboard(document.getElementById("link_clip_button1"), {
	  moviePath:"${ctx}/js/ZeroClipboard.swf",
	  hoverClass:"zeroclipboard-is-hover"
});
	clip.on( 'complete', function(client, args) {
	 sureInfo("确定","链接复制成功，直接粘贴使用","提示");
});

var clip = new ZeroClipboard(document.getElementById("link_clip_button2"), {
		  moviePath:"${ctx}/js/ZeroClipboard.swf",
		  hoverClass:"zeroclipboard-is-hover"
});

</script>
