/* 屏蔽网页全选 */
  //document.oncontextmenu=new Function("event.returnValue=false;");
  //document.onselectstart=new Function("event.returnValue=false;");

$(function(){
	$('.banner ul> li').hover(function() {
		if(this.id != ""){
			$('.'+this.id).slideDown(200);
		}
	}, 
	function() {
	   if(this.id != ""){
		$('.'+this.id).stop(true,true).slideUp(200);
	}});

	$("#newskey>li>div").click(function(){
		$(this).addClass("current");
		$(this).parent().siblings().find('div').removeClass("current");
		$(this).parent().find('dl').css({"display":"block"});
		$(this).parent().siblings().find('dl').css({"display":"none"});
	});
	
	$("#newskey>li>dl>dd").click(function(){
		$(this).parent().parent().find("div").removeClass("current");
		$(this).addClass("current").siblings().removeClass("current");
	});
});  
/*
函数名称：setTab
函数参数：obj,为鼠标点击的对象
函数功能：选项卡页面，点击选项卡的不同选项调用不同的页面。
使用页面：关于我们
*/
function setTab(obj){
	var objId = obj.id;
	var objLen = objId.length;
	var eObj = "";
	if(objId != "" || objId != null){
		eObj = objId.substr(0,objLen-1)+"_"+objId.substr(objLen-1,objLen);
	}
	$(obj).addClass('mgractive').siblings().removeClass('mgractive');
	$("#"+eObj).removeClass('display-n').siblings().addClass('display-n');
}

/* 
函数名称：newsTab
函数参数：obj为当前对象 
函数功能：选项卡页面，点击选项卡的不同选项调用不同的页面。
使用页面：新闻中心
*/
function newsTab(obj){
	var objId = obj.id;
	var objLen = objId.length;
	var eObj = "";
	if(objId != "" || objId != null){
		eObj = objId.substr(0,objLen-1)+"_"+objId.substr(objLen-1,objLen);
	}
	$("#"+eObj).removeClass('display-n');
	$("#"+eObj).addClass('display-b').siblings().addClass('display-n');
	$(obj).addClass('light-blue-font').siblings().removeClass('light-blue-font');
}
