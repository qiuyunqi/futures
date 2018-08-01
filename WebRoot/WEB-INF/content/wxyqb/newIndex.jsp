<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no" name="viewport"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<meta name="google" content="notranslate">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="yes">
<meta name="x5-fullscreen" content="true">
<meta name="browsermode" content="application">
<meta name="x5-page-mode" content="app">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－余券宝</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.homeYqb{background: url("../images_yqb/yqbIndexAct.png") no-repeat scroll center center / 100% auto !important;}
#homeYqb{color:#fad400;}
.infoList{height:130px;}
input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
color: #00abe3;
}
input:-moz-placeholder, textarea:-moz-placeholder {
color: #00abe3;
}
input::-moz-placeholder, textarea::-moz-placeholder {
color: #00abe3;
}
input:-ms-input-placeholder, textarea:-ms-input-placeholder {
color: #00abe3;
} 
.is_display {display: none;}
.topFir {background: rgba(0, 0, 0, 0) url("../images_yqb/tp1.png") no-repeat scroll center center / 20px auto !important;}
.topSec {background: rgba(0, 0, 0, 0) url("../images_yqb/tp2.png") no-repeat scroll center center / 20px auto !important;}
.topThi {background: rgba(0, 0, 0, 0) url("../images_yqb/tp3.png") no-repeat scroll center center / 20px auto !important;}
.indBan{background:#f4eabd; }
@media screen and (max-width:240px){
.listYqb tr th {font-size: 9px !important;}
}
</style>
</head>
<body style="background: #fff;">
	<div class="sucContain">
		<div class="containImg">
			<div class="infoLt">
				<img class="hidBan" src="${ctx}/images_yqb/homeBan.png"/>
				 <div id="slideBox" class="slideBoxA">
				 	<div  class="bd">
				 	    <ul>
				 	  		<li class="indBan bana"><a href="javascript:void(0)"><img src="${ctx}/images_yqb/homeBan.png"/></a></li>
						</ul>
				 	</div>
				</div>
		       <div class="search">
		            <input id="stockCode" class="sercInput bsiz"  type="search" list="mydata" placeholder="检查股票" value="" autocomplete="off">
		            <div class="sercImg" onclick="searchEvent()" ></div>
		        </div>
			</div>
			<div class="navListUp">
				<ul>
					<li><a class="haveKit" href="${ctx}/wxyqb/haveTicket.htm"></a></li>
					<li><a class="needKit" href="javascript:void(0);"></a></li>
				</ul>
			</div>
			<div class="topLists">
				<span id="tigerLis" class="navAct">余券宝收益龙虎榜</span>
				<!-- 龙虎排行榜 -->
				<div class="syList">
					<table class="listYqb" cellpadding="0" cellspacing="0" width="85%" border="0">
				      <tr>
				        <th>排序</th>
				        <th>股票名称</th>
				        <th>月收益</th>
				        <th>热度</th>
				        <th>团队</th>
				       </tr>
				       <tr>
				       		<td colspan="5">
				       			<div class="txtMarquee-top">
									<div class="bd">
										<ul class="infoList">
											<c:forEach items="${yjbRankList}" var="yjbRank" varStatus="index">
												<li>
													<span class="infoOne">
														<c:if test="${index.index == 0}">
															<i class="topList topFir"></i>
														</c:if>
														<c:if test="${index.index == 1}">
															<i class="topList topSec"></i>
														</c:if>
														<c:if test="${index.index == 2}">
															<i class="topList topThi"></i>
														</c:if>
														<c:if test="${index.index != 0 && index.index != 1 && index.index != 2}">
															<i>${index.index+1}</i>
														</c:if>
													</span>
													<span class="infoTwo">${yjbRank.stockName}</span>
													<span class="infoThr">${yjbRank.monthProfit}</span>
													<span class="infoFor starsYqb">
														<c:forEach begin="1" end="${yjbRank.heat}">
															<img src='../images_yqb/star.png' />
														</c:forEach>
													</span>
													<span class="infoFiv">${yjbRank.transactionName}</span>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
				       		</td>
				       </tr>
				    </table>
				    <div class="newTabRgt"><a href="${ctx}/wxyqb/tirgerTop.htm"><span class="moreIndx"><strong>...</strong><br/>更多</span></a></div>
				</div>
			</div>
			<!-- 交易员收益排行榜 -->
			<div class="topLists">
				<li><span id="syLis" class="navAct">交易员收益排行榜</span></li>
				<!-- 收益排行榜 -->
				<div class="syList">
					<table class="listYqb" cellpadding="0" cellspacing="0" width="85%" border="0">
				      <tr>
				         <th>排序</th>
				        <th>交易员</th>
				        <th>月收益</th>
				        <th>管理规模</th>
				        <th>评级</th>
				       </tr>
				   		<tr>
				       		<td colspan="5">
				       			<div class="txtMarquee-top">
									<div class="bd">
										<ul class="infoList">
											<c:forEach items="${transRankList}" var="transRank" varStatus="index">
												<li>
													<span class="infoOne">
														<c:if test="${index.index == 0}">
															<i class="topList topFir"></i>
														</c:if>
														<c:if test="${index.index == 1}">
															<i class="topList topSec"></i>
														</c:if>
														<c:if test="${index.index == 2}">
															<i class="topList topThi"></i>
														</c:if>
														<c:if test="${index.index != 0 && index.index != 1 && index.index != 2}">
															<i>${index.index+1}</i>
														</c:if>
													</span>
													<span class="infoTwo">${transRank.transactionName}</span>
													<span class="infoThr">${transRank.monthProfit}</span>
													<span class="infoFiv">
														${transRank.managerScale}
													</span>
													<span class="infoFor starsYqb">
														<c:forEach begin="1" end="${transRank.fuTransaction.rating}">
															<img src='../images_yqb/star.png' />
														</c:forEach>
													</span>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
				       		</td>
				       </tr>
				    </table>
				    <div class="newTabRgt"><a href="${ctx}/wxyqb/profitTop.htm"><span class="moreIndx"><strong>...</strong><br/>更多</span></a></div>
				</div>
			</div>
			<div class="yqbJg"></div> 
		</div>
	</div>
	<%@include file="../wxyqb/footer_yqb.jsp" %>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js_hhr/jquery-ui.css"></link>
<script type="text/javascript" src="../js_hhr/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js_hhr/jquery.SuperSlide.2.1.1.js"></script>
<script src="${ctx}/js/layer/layer.js" type="text/javascript" ></script>
<script src="${ctx}/js/dropload.min.js" type="text/javascript"></script>
<script type="text/javascript">
document.onkeydown=function(event){
    if (event.keyCode == 13) {
    		searchEvent();
    	}
};

//获取banner高度
var hgt = $(".hidBan").height();
$(".slideBoxA").height(hgt);
$(".bd").height(hgt);
$(".bd").find(".indBan").height(hgt); 
//排行榜滚动  effect: "topMarquee"
$(".txtMarquee-top").slide({mainCell: ".bd ul", autoPlay: true, effect: "topLoop", vis: 1, interTime: 3000});


function searchEvent()  
{  
	$("#ui-id-1").hide();
	$(".ui-helper-hidden-accessible").hide();
	var code = $("#stockCode").val();
	if(code == null || code == ""){
		$('#searchForm').submit();
	}else{
		$.post("${ctx}/wxyqb/searchTirger.htm", {query: code}, function(data){
			var yjb = data.yjbRankList;
			if (yjb.length > 0) {
				$(".topLists").remove();
			}else {
				return false;
			}
			var html ="";
			html += "<div class='topLists'>"+
							"<span id='tigerLis' class='navAct'>余券宝收益龙虎榜</span>"+
							"<div class='syList'>"+
							"<table class='listYqb' cellpadding='0' cellspacing='0' width='85%' border='0'>"+
			      				"<tr>"+
			        				"<th>排序</th>"+
			        				"<th>股票名称</th>"+
							       "<th>月收益</th>"+
							       "<th>热度</th> "+
							       "<th>团队</th> "+
			       				"</tr>"+
			       				"<tr>"+
			       					"<td colspan='5'>"+
			       						"<div class='txtMarquee-top'>"+
										"<div class='bd'>"+
											"<ul class='infoList'>";
			for (var i = 0; i < yjb.length; i++) {
				html += "<li>"+
					   		"<span class='infoOne'>"+ i+1 +"</span>"+
					   		"<span class='infoTwo'>"+yjb[i]['stockName']+"</span>"+
					   		"<span class='infoThr'>"+ yjb[i]['monthProfit'] +"</span>"+
					   		"<span class='infoFor starsYqb'>";
					   		for (var j = 0 ; j < yjb[i]['heat']; j++) { 
			    	   			html += "<img src='../images_yqb/star.png' />";
			    	   		} 

				html += "</span>"+
						"<span class='infoFiv'>"+ yjb[i]['transactionName'] +"</span>"+
						"</li>";
			}
			html += "</ul></div></div></td></tr></table></div></div>";
			$(html).insertAfter($(".navListUp"));
			return false;
		});
	}
} 

//搜索
$(function() {
    var projects =${sources};
 
    $( "#stockCode" ).autocomplete({
      minLength: 0,
      source: projects,
      focus: function( event, ui ) {
        $( "#stockCode" ).val( ui.item.category );
        return false;
      },
      select: function( event, ui ) {
        $("#stockCode" ).val( ui.item.category );
	    $("#ui-id-1").hide();
    	$(".ui-helper-hidden-accessible").hide();
        $.post("${ctx}/wxyqb/getById.htm?id="+ui.item.id, null, function(data) {
        		if (data == 1) {
        			layer.open({
        			    content: '该股票不存在',
        			    btn: ['确定', '取消'],
        			    shadeClose: false,
        			    yes: function(){
        			        layer.closeAll();
        			    }
        			});
        		} else {
        			$(".topLists").remove();
        	  		var yjb = data.yjbRank;
	        	  	var html = "";
					html += "<div class='topLists'>"+
					"<span id='tigerLis' class='navAct'>余券宝收益龙虎榜</span>"+
					"<div class='syList'>"+
					"<table class='listYqb' cellpadding='0' cellspacing='0' width='85%' border='0'>"+
	      				"<tr>"+
	        				"<th>排序</th>"+
	        				"<th>股票名称</th>"+
					       "<th>月收益</th>"+
					       "<th>热度</th> "+
					       "<th>团队</th> "+
	       				"</tr>"+
	       				"<tr>"+
	       					"<td colspan='5'>"+
	       						"<div class='txtMarquee-top'>"+
								"<div class='bd'>"+
									"<ul class='infoList'>";
	    			html += "<li>"+
    					   		"<span class='infoOne'>"+ 1 +"</span>"+
    					   		"<span class='infoTwo'>"+yjb.stockName+"</span>"+
    					   		"<span class='infoThr'>"+ yjb.monthProfit +"</span>"+
    					   		"<span class='infoFor starsYqb'>";
    					   		for (var j = 0 ; j < yjb.heat; j++) { 
    			    	   			html += "<img src='../images_yqb/star.png' />";
    			    	   		} 
	
	    			html += "</span>"+
    						"<span class='infoFiv'>"+ yjb.transactionName +"</span>"+
    						"</li>";
	    			html += "</ul></div></div></td></tr></table></div></div>";
	    			$(html).insertAfter($(".navListUp"));
        		}
        });
        return false;
      }
    })
    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append(  "<a>" + "<span>"+ item.category + "</span> <span class='fgt'>"+ item.code +"</a>")
        .appendTo( ul );
    };
  });
</script> 

<!-- 我要劵 -->
<script type="text/javascript">
	$(".needKit").click(function(){
		$.post("${ctx}/wxyqb/ihavestock.htm", null, function(data) {
			if (data == 1) {
				location.href="https://open.weixin.qq.com/connect/oauth2/authorize?"+
						"appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toihavestock#wechat_redirect";
			} else if (data == 2) { // 不是交易员
				location.href="${ctx}/wxyqb/business.htm";
			} else if (data == 3) {
				layer.open({
	 			    content: "请耐心等候审核通过",
	 			    btn: ["确定"],
	 			    shadeClose: false,
	 			    yes: function(){
	 			        layer.closeAll();
	 			    }
	 			});
				return null;
			} else if (data == 4) { // 是交易员
				location.href="${ctx}/wxyqb/userFindTicket.htm?userId=${sessionScope.fuUser.id}";
			} else {
				location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toNewIndex#wechat_redirect";
			}
			
		});
	});

</script>
</html>