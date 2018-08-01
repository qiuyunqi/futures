<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
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
<title>${title}－供券</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
color: #fff;
}
input:-moz-placeholder, textarea:-moz-placeholder {
color: #fff;
}
input::-moz-placeholder, textarea::-moz-placeholder {
color: #fff;
}
input:-ms-input-placeholder, textarea:-ms-input-placeholder {
color: #fff;
} 
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
				 <div class="searchTicket">
		            <input id="stockTicket" class="sercInp bsiz"  type="search" list="mydata" placeholder="搜券" value="" autocomplete="off">
		            <div class="ticketImg" ></div>
		        </div>
		        <!-- 列表 -->
		        <div class="ticketLists">
			        <table class="ticketYqb" cellpadding="0" cellspacing="0" width="100%" border="0">
			        		<%-- <c:forEach items="${accountList}" var="account">
						      <tr>
						      	<td onclick="location.href='${ctx}/wxyqb/forTicketInfo.htm?id=${account.id}'">
						        	<div class="tbLft">
						        		<div class="blueCol siz">${account.message}</div>
						        		<div class="smallSize">市值：${account.available}</div>
						        		<div class="smallSize">分配：客户${account.profitConfig}%</div>
						        		<div class="soSmaSize">发布时间：<fmt:formatDate value="${account.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						        	</div>
						      	</td>
						      	<td><div class="tbRgt"><img src="../images_yqb/jtou.png"/></div></td>
						      	<c:if test="${account.transactionId == null}">
							      	<td class="redBg bigSz whiteCol"><span>抢</span></td>
						      	</c:if>
						      	<c:if test="${account.transactionId != null}">
							      	<td class="grayBg bigSz whiteCol"><span>抢光</span></td>
						      	</c:if>
					         </tr>	
			        		</c:forEach> --%>
				    </table>
		        </div>
			</div>
			<div class="yqbJg"></div>
		</div>
	</div>
	<div class="adBn">
		<a class="addTick" href="${ctx}/wxyqb/haveTicket.htm"><img src="../images_yqb/addNew.png"/></a>
	</div>
	<%@include file="../wxyqb/footer_yqb.jsp" %>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js_hhr/jquery-ui.css"></link>
<script type="text/javascript" src="../js_hhr/jquery-ui.min.js"></script>
<script src="${ctx}/js/dropload.min.js" type="text/javascript"></script>
<!-- <script type="text/javascript">
//搜索
     $(function() {
    var projects = ${sources};
 
    $( "#stockTicket" ).autocomplete({
      minLength: 0,
      source: projects,
      focus: function( event, ui ) {
        $( "#stockTicket" ).val( ui.item.category );
        return false;
      },
      select: function( event, ui ) {
        $( "#stockTicket" ).val( ui.item.category );
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
    	  		$(".ticketYqb").hide();
    	  		var account = data.fuStockAccount;
    	  		var html = "<table class='ticketYqb' cellpadding='0' cellspacing='0' width='100%' border='0'>";
    	  		for (var i=0; i<account.length; i++) {
		      		html += "<tr>"+
		      			"<td onclick='getInfoById("+account[i]['id']+")'>"+
		        				"<div class='tbLft'>"+
					        		"<div class='blueCol siz'>"+account[i]['message']+"</div>"+
					        		"<div class='smallSize'>市值："+account[i]['available']+"</div>"+
					        		"<div class='smallSize'>分配：客户"+account[i]['profitConfig']*100 +"%</div>"+
					        		"<div class='soSmaSize'>发布时间："+ account[i]['createTime']+"</div>"+
		        				"</div>"+
		      			"</td>"+
		      			"<td><div class='tbRgt'><img src='../images_yqb/jtou.png'/></div></td>";
		      		if (account[i]['transactionId'] != null) {
		      			html += "<td class='grayBg bigSz whiteCol'><span>抢光</span></td>";
		      		} else {
		      			html += "<td onclick='getInfoById("+account[i]['id']+")' class='redBg bigSz whiteCol'><span>抢</span></td>";
		      		}
		      			
		        	html += "</tr>";	
     			}
    	  		html += "</table>";
    	  		$(".ticketLists").append(html);
    		}
    	});
        return false;
      }
    });
    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append(  "<a>" + "<span>"+ item.category + "</span>" +  "<span class='fgt'>"+ item.code+ "</span>" + "</a>")
        .appendTo( ul );
    };
    
  });
</script> -->
<script type="text/javascript">
//下拉加载更多
$(function(){
    var counter = 0;
    var num = 5;
    var pageStart = 0,pageEnd = 0;
    // dropload
    $(".ticketLists").dropload({
    	scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh" >↑上拉刷新</div>',
            domNoData  : '<div class="dropload-update">没有更多的数据了</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        loadDownFn : function(me){
        	 $.ajax({
	         type: 'GET',
	         url: '${ctx}/wxyqb/forTicketAjax.htm',
	         dataType: 'json',
	         success: function(dt){
	         	var d = dt.accountList;
       			var html = null;
       			counter++;
       			pageEnd = num * (0 == counter ? 1: counter);
                pageStart = pageEnd - num;
       			for (var i = pageStart; i < pageEnd; i++) {
		      		html += "<tr>"+
		      			"<td onclick='getInfoById("+d[i]['id']+")'>"+
		        				"<div class='tbLft'>"+
					        		"<div class='blueCol siz'>"+d[i]['message']+"</div>"+
					        		"<div class='smallSize'>市值："+d[i]['available']+"</div>"+
					        		"<div class='smallSize'>分配：客户"+d[i]['profitConfig'] +"</div>"+
					        		"<div class='soSmaSize'>发布时间："+ d[i]['createTime']+"</div>"+
		        				"</div>"+
		      			"</td>"+
		      			"<td><div class='tbRgt'><img src='../images_yqb/jtou.png'/></div></td>";
		      		if (d[i]['transactionId'] != null) {
		      			html += "<td class='grayBg bigSz whiteCol'><span>已抢</span></td>";
		      		} else {
		      			html += "<td onclick='getInfoById("+d[i]['id']+")' class='wRob bigSz whiteCol'><span>未抢</span></td>";
		      		}
		        	html += "</tr>";
		        	
		        	if((i + 1) >= d.length){
                         // 锁定
                         me.lock();
                         // 无数据
                         me.noData();
                         break;
                     }
       			}
       			setTimeout(function(){
        			$(".ticketYqb").append(html);
                      me.resetload();
                  },1000);
	         },//success结束
	         error: function(xhr, type){
	             alert('Ajax error!');
	             me.resetload();
	         }  
	     });//ajax结束 
		},//上拉结束
    });//drop结束
});

function getInfoById($id){
	location.href = "${ctx}/wxyqb/forTicketInfo.htm?id="+$id;
}

</script>

</html>