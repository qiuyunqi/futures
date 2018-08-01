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
<title>${title}－我找的券</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div class="findTicList">
		        	<table class="findTicIn" cellpadding="0" cellspacing="0" width="100%" border="0">
		        		<c:forEach items="${accounts }" var="stock">
							      <tr>
							      	<td class=" smallSize firstTd">
							      		<div class="upTh">
							      			<span class="blueCol siz">${stock.openEquity }：${stock.capitalAccount}<%-- ***${fn:substring(stock.capitalAccount,3,5)} --%></span>
							      			<c:if test="${stock.transactionStatus==0 }">
							      				<a class="czIng">正在操作</a>
							      			</c:if>
							      			<c:if test="${stock.transactionStatus==1 }">
							      				<a class="bacIng">已退券</a>
							      			</c:if>
							      		</div>
							      		<div class="downLis">
							      			<div class="leftDown">
							      				<span class="leftDoFir">股票市值：<fmt:formatNumber value="${empty stock.available?0:stock.available}" pattern="#,###,##0.00"/>元</span><span>可用资金：<fmt:formatNumber value="${empty stock.ableMoney?0:stock.ableMoney}" pattern="#,###,##0.00"/>元</span>
							      			</div>
							      			<div class="rgtDown"><a href="${ctx}/wxyqb/accountInfo.htm?id=${stock.id}"><img class="lftJt" src="../images_yqb/mejtou.png"/></a></div>
							      		</div>
							      		<div class="ticketDat">发布时间：<fmt:formatDate value="${stock.createTime }" pattern="yyyy.MM.dd"/></div>
							      	</td>
						         </tr>
				         </c:forEach>
				         <c:if test="${empty accounts }">
					         <tr>
						         <td>
						         	<div class="noData">
						         		<img src="../images_yqb/ganta.png"/>
						         		<span>暂无券</span>
						         	</div>
						         </td>
						      </tr>
					    </c:if>
				    </table>
		        </div>
			</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/dropload.min.js" type="text/javascript"></script>
</html>
<script>
//下拉加载更多
$(function(){
    var counter = 1;
    // 每页展示5个
    var num = 5;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $('.findTicList').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新-自定义内容</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新-自定义内容</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中-自定义内容...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">暂无更多评论</div>'
        },
        loadUpFn : function(me){
            $.ajax({
                type: 'POST',
                url: '${ctx}/wxyqb/findTicketData.htm',
                data: {userId: ${fuUser.id}},
                dataType: 'json',
                success: function(data){
                    var result = '';
                    for(var i = 0; i < data.array.length; i++){
                        var arrText = [];
			           	arrText.push(" <tr><td class='smallSize firstTd'>");
			          	arrText.push("<div class='upTh'><span class='blueCol siz'>"+data.array[i].openEquity+"："+data.array[i].openEquity+"</span>");
			          	if(data.array[i].transactionStatus==0){
			          		arrText.push("<a class='czIng'>"+data.array[i].status+"</a></div>");
			          	}
			          	if(data.array[i].transactionStatus==1){
			          		arrText.push("<a class='bacIng'>"+data.array[i].status+"</a></div>");
			          	}
			          	arrText.push("<div class='downLis'><div class='leftDown'><span class='leftDoFir'>股票市值："+data.array[i].available+"元</span><span>可用资金："+data.array[i].ableMoney+"元</span></div>");
			          	arrText.push("<div class='rgtDown'><a href='${ctx}/wxyqb/accountInfo.htm?id="+data.array[i].id+"'><img class='lftJt' src='../images_yqb/mejtou.png'/></a></div></div>");
			          	arrText.push("<div class='ticketDat'>发布时间："+data.array[i].createTime+"</div></td></tr>");
                        result +=  arrText.join('');
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $('.findTicIn').html(result);
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        },
        loadDownFn : function(me){
            $.ajax({
                type: 'POST',
                url: '${ctx}/wxyqb/findTicketData.htm',
                data: {userId: ${fuUser.id}},
                dataType: 'json',
                success: function(data){
                 var length=data.array.length;
                 //判断是否有数据
			        if(length==0){
			        	$(".dropload-down").hide();
			        }else if(length<=5){
			        	$(".dropload-down").hide();
			        }else{
			        $(".dropload-load").show();
			        var result = '';
                    counter++;
                    pageEnd = num * counter;
                    pageStart = pageEnd - num;
                    for(var i = pageStart; i < pageEnd; i++){
                     	var arrText = [];
			           	arrText.push(" <tr><td class='smallSize firstTd'>");
			          	arrText.push("<div class='upTh'><span class='blueCol siz'>"+data.array[i].openEquity+"："+data.array[i].openEquity+"</span>");
			          	if(data.array[i].transactionStatus==0){
			          		arrText.push("<a class='czIng'>"+data.array[i].status+"</a></div>");
			          	}
			          	if(data.array[i].transactionStatus==1){
			          		arrText.push("<a class='bacIng'>"+data.array[i].status+"</a></div>");
			          	}
			          	arrText.push("<div class='downLis'><div class='leftDown'><span class='leftDoFir'>股票市值："+data.array[i].available+"元</span><span>可用资金："+data.array[i].ableMoney+"元</span></div>");
			          	arrText.push("<div class='rgtDown'><a href='${ctx}/wxyqb/accountInfo.htm?id="+data.array[i].id+"'><img class='lftJt' src='../images_yqb/mejtou.png'/></a></div></div>");
			          	arrText.push("<div class='ticketDat'>发布时间："+data.array[i].createTime+"</div></td></tr>");
                        result +=  arrText.join('');
                        if((i + 1) >= data.array.length){
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                            break;
                        }
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $('.findTicIn').append(result);
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
			      };//if end
                
                },//success结束
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }  
            });//ajax结束
        },//上拉结束
        threshold : 50
        
    });//drop结束
});

</script>