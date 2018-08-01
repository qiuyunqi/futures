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
<title>${title}－交易员收益排行榜</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.homeYqb{background: url("../images_yqb/yqbIndexAct.png") no-repeat scroll center center / 100% auto !important;}
#homeYqb{color:#fad400;}
.starsYqb{width:100%;}
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
.is_display {
	display: none;
}
@media screen and (max-width:240px){
.listYqbTirger tr th {font-size: 10px !important;}
.listYqbTirger tr td {font-size: 10px !important;}
}
</style>
</head>
<body style="background: #fff;">
	<div class="sucContain">
		<div class="containImg">
			<!-- <div class="tirgerSer">
				<div class="searchTir">
		            <input id="stockCode" class="sercInput bsiz"  type="search" list="mydata" placeholder="检查股票" value="" autocomplete="off">
		            <div class="sercImg" onclick="searchEvent()" ></div>
		        </div>
			</div> -->
			<div class="topLists">
				<div class="tirgerList">
					<table class="listYqbTirger" cellpadding="0" cellspacing="0" width="100%" border="0">
				      <tr>
				        <th>排序</th>
				        <th>交易员</th>
				        <th>月收益</th>
				        <th>管理规模</th>
				        <th>评级</th>
				       </tr>
				       <c:forEach items="${transRankList}" var="trans">
					       <tr>
					       	<c:if test="${trans.serialNo==1}">
					        <td><i class="topList topFir"></i></td>
					        </c:if>
					        <c:if test="${trans.serialNo==2}">
					        <td><i class="topList topSec"></i></td>
					        </c:if>
					        <c:if test="${trans.serialNo==3}">
					        <td><i class="topList topThi"></i></td>
					        </c:if>
					        <c:if test="${trans.serialNo>3}">
					        <td>${trans.serialNo}</td>
					        </c:if>
					        <td>${trans.transactionName}</td>
					        <td>${trans.monthProfit }</td>
					        <td>${trans.managerScale }</td>
					        <td class="starsYqb"><c:forEach begin="1" end="${empty trans.fuTransaction.rating?0:trans.fuTransaction.rating}"><img src='../images_yqb/star.png' /></c:forEach><c:forEach begin="${empty trans.fuTransaction.rating?0:trans.fuTransaction.rating}" end="4"><img src='../images_yqb/nostar.png' /></c:forEach></td>
					       </tr>
				       </c:forEach>
				    </table>
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
<script src="${ctx}/js/layer/layer.js" type="text/javascript" ></script>
<script src="${ctx}/js/dropload.min.js" type="text/javascript"></script>
<script type="text/javascript">
function searchEvent(){
	$.post("${ctx}/wxyqb/searchProfit.htm", {query: code}, function(data){
			var trans = data.transRankList;
			if (trans.length > 0) {
				$(".listYqbTirger").remove();
			}else {
				return false;
			}
			var html = "<div class='tirgerList'>"+
							"<table class='listYqbTirger' cellpadding='0' cellspacing='0' width='100%' border='0'>"+
		      					"<tr>"+
		      						"<th>排序</th>"+
		        					"<th>交易员</th>"+
							        "<th>月收益</th>" +
							        "<th>管理规模</th> " +
							        "<th>评级</th> " +
		      					"</tr>";
			
              		for (var i=0; i<trans.length; i++) {
	    	   			html += "<tr><td>";
   						if (trans[i]['serialNo'] == 1) {
   							html += "<i class='topList topFir'></i>";
   						}else if(trans[i]['serialNo']==2){
   							html += "<i class='topList topSec'></i>";
   						}else if(trans[i]['serialNo']==3){
   							html += "<i class='topList topThi'></i>";
   						}else{
   							html += trans[i]['serialNo'];
   						}
   						html += "</td><td>"+trans[i]['transactionName']+"</td>"+
   						"<td>"+trans[i]['monthProfit']+"</td>"+
   						"<td>"+trans[i]['managerScale']+"</td>"+
   						"<td class='starsYqb'>"; 
						for (var j = 0 ; j < trans[i]['rating']; j++) { 
							html += "<img src='../images_yqb/star.png' />";
						}
						for (var k = 0 ; k < (5-trans[i]['rating']); k++) { 
							html += "<img src='../images_yqb/nostar.png' />";
						}
   						html += "</td></tr>";
               		}
					html += "</table></div>";
					$(".topLists").append(html);
		});
}



$(function(){
	var counter = 1;
	var num = 20;
	var pageStart = 0,pageEnd = 0;
    $('.topLists').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新-自定义内容</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新-自定义内容</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中-自定义内容...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh" >↑上拉刷新</div>',
            domNoData  : '<div class="dropload-update">没有更多的数据了</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        loadUpFn : function(me){
            $.ajax({
                type: 'POST',
                url: '${ctx}/wxyqb/profitData.htm',
        	    data:{start: counter},
 	            dataType: 'json',
                success: function(data){
                    		var trans = data.transRankList;
	                		$(".dropload-up").hide();
	               			var html = null;
                			for (var i=0; i<trans.length; i++) {
			    	   			html += "<tr><td>";
		   						if (trans[i]['serialNo'] == 1) {
		   							html += "<i class='topList topFir'></i>";
		   						}else if(trans[i]['serialNo']==2){
		   							html += "<i class='topList topSec'></i>";
		   						}else if(trans[i]['serialNo']==3){
		   							html += "<i class='topList topThi'></i>";
		   						}else{
		   							html += trans[i]['serialNo'];
		   						}
		   						html += "</td><td>"+trans[i]['transactionName']+"</td>"+
		   						"<td>"+trans[i]['monthProfit']+"</td>"+
		   						"<td>"+trans[i]['managerScale']+"</td>"+
		   						"<td class='starsYqb'>"; 
								for (var j = 0 ; j < trans[i]['rating']; j++) { 
									html += "<img src='../images_yqb/star.png' />";
								}
								for (var k = 0 ; k < (5-trans[i]['rating']); k++) { 
									html += "<img src='../images_yqb/nostar.png' />";
								}
		   						html += "</td></tr>";
	                		}
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $(".listYqbTirger").append(html);
                        me.resetload();
                    },1000);
                },
                error: function(xhr, type){
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        },
        loadDownFn : function(me){
    			 $.ajax({
    	                type: 'POST',
    	                url: '${ctx}/wxyqb/profitData.htm',
        	            data:{start: counter},
    	                dataType: 'json',
    	                success: function(data){
	                		var trans = data.transRankList;
	                		$(".dropload-down").hide();
	               			var html = null;
	               			counter++;
	               			pageEnd = num * (0 == counter ? 1: counter);
	                        pageStart = pageEnd - num;
                			for (var i = pageStart; i < pageEnd; i++) {
			    	   			html += "<tr><td>";
		   						if (trans[i]['serialNo'] == 1) {
		   							html += "<i class='topList topFir'></i>";
		   						}else if(trans[i]['serialNo']==2){
		   							html += "<i class='topList topSec'></i>";
		   						}else if(trans[i]['serialNo']==3){
		   							html += "<i class='topList topThi'></i>";
		   						}else{
		   							html += trans[i]['serialNo'];
		   						}
		   						html += "</td><td>"+trans[i]['transactionName']+"</td>"+
		   						"<td>"+trans[i]['monthProfit']+"</td>"+
		   						"<td>"+trans[i]['managerScale']+"</td>"+
		   						"<td class='starsYqb'>"; 
								for (var j = 0 ; j < trans[i]['rating']; j++) { 
									html += "<img src='../images_yqb/star.png' />";
								}
								for (var k = 0 ; k < (5-trans[i]['rating']); k++) { 
									html += "<img src='../images_yqb/nostar.png' />";
								}
		   						html += "</td></tr>";
	                			if((i + 1) >= trans.length){
	 	                             // 锁定
	 	                             me.lock();
	 	                             // 无数据
	 	                             me.noData();
	 	                             $(".dropload-down").hide();
	 	                             break;
	 	                         }
	                		}
	               			setTimeout(function(){
	               				$(".listYqbTirger").append(html);
	                            me.resetload();
                            },1000);
    	                },//success结束
    	                error: function(xhr, type){
    	                    me.resetload();
    	                }  
    	            });//ajax结束
        },// 下拉结束
        threshold : 50
        
    });//drop结束
})
</script>
</html>
