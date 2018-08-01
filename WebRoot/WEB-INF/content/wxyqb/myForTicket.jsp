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
<title>${title}－我的供券</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
				<div class="myTic  cenTxt "><span class="blok cenTxt witColr smallSize">昨日收益(日)</span><span class="blok bigSiz witColr cenTxt"><c:if test="${dayProfits>=0}">+</c:if><c:if test="${dayProfits<0}">-</c:if><fmt:formatNumber value="${dayProfits}" pattern="#,###,##0.00"/></span></div>
				<div class="myProfit  cenTxt ">累计盈亏:<fmt:formatNumber value="${empty fuStockMoneyInfo.profitInfo?0:fuStockMoneyInfo.profitInfo}" pattern="#,###,##0.00"/>元</div>
				<div class="infoLi">
				 	<div class="addUall"><span class="smallSize">股票账户</span><span class="blueCol sSmalSiz yHfaMi frgt"><a href="${ctx}/wxyqb/haveTicket.htm">+添加账户</a></span></div>
				</div>
				<!-- 证券列表 -->
				<div class="bgWhite myFortick">
					<table class="ticketListYqb" cellpadding="0" cellspacing="0" width="100%" border="0">
						<c:forEach items="${stockList}" var="stock">
					    <tr>
					    	<td>
						      	<div class=" coloGray smallSize padBoit">${stock[1]}：${stock[2]}</div>
								<div class="profitLists"><span class="padTop coloGray flatL soSmaSize">昨日盈利</span>
								<c:if test="${stock[3]>=0}">
									<span class=" flatR coloRed smBiSiz">+<fmt:formatNumber value="${stock[3]}" pattern="#,###,##0.00"/>元</span>
								</c:if>
								<c:if test="${stock[3]<0}">
									<span class=" flatR greeCol smBiSiz">-<fmt:formatNumber value="${stock[3]}" pattern="#,###,##0.00"/>元</span>
								</c:if>
								</div>
					      	</td>
					    </tr>
					    </c:forEach>
					     <c:if test="${empty stockList }">
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
		<div class="jiange"></div>
		<div class="jiange"></div>
		<div class="footerFt bgWhite center">
			<span class="wjMoney">未缴费用：<fmt:formatNumber value="${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo}" pattern="#,###,##0.00"/>元</span>
			<a id="yjMonet" class="yjMonet yHfaMi siz" href="javascript:void(0);" onclick="onekeypay(${empty fuStockMoneyInfo.noneFeeInfo?0:fuStockMoneyInfo.noneFeeInfo})">一键缴费</a>
		</div>
	</div>
</div>
	<!-- 设置一键缴费弹出层 -->
		<div id="fuchen" class="hideNew fuchen">
			<div class="fucBg">
				<div class="forTicFc">
						<div class="jfTxt smallSize blueCol">一键缴费，提现，查看更多账户详情<br/>请下载APP后进行操作</div>
						<div class="btnAllA">
						  	<a class="wxCancel grBg witColr smallSize"  style="margin-left:4%;" onclick="quxiao()">取消</a>
						  	<a class="wxSured witColr smallSize" href="https://hhr360.com/app">下载App</a>
						</div>
				</div>
			</div>
		</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js_hhr/dropload.min.js"></script>
</html>
<script type="text/javascript">
//控制间隔部分高度
	$(function(){
	 var hgt=$(".footerFt").height();
	 $(".jiange").height(hgt);
	});
//弹窗隐藏
	$(".fuchen").hide();
	$(function(){
		$(".yjMonet").click(function(){
			$(".fuchen").show();
		});
		
		$(".wxCancel").click(function(){
			$(".fuchen").hide();
		});
	});
</script>
<script>
function quxiao(){
	$("#fuchen").hide();
}
function onekeypay(money){
	$("#fuchen").show();
}
</script>
<script>
//下拉加载更多
$(function(){
    var counter = 1;
    // 每页展示5个
    var num = 5;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $('.myFortick').dropload({
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
                url: '${ctx}/wxyqb/forTicketData.htm',
                data: {userId: ${userId}},
                dataType: 'json',
                success: function(data){
                    var result = '';
                    for(var i = 0; i < data.array.length; i++){
                        var arrText = [];
			          	arrText.push("<tr><td><div class='coloGray smallSize padBoit'>"+data.array[i].openEquity+"："+data.array[i].capitalAccount+"</div>");
			          	arrText.push("<div class='profitLists'><span class='padTop coloGray flatL soSmaSize'>昨日盈利</span>")
			          	if(data.array[i].nowProfit>=0){
			          		arrText.push("<span class=' flatR coloRed smBiSiz'>+"+data.array[i].nowProfit+"元</span></div></td></tr>");
			          	}
			          	if(data.array[i].nowProfit<0){
			          		arrText.push("<span class=' flatR greeCol smBiSiz'>-"+data.array[i].nowProfit+"元</span></div></td></tr>");
			          	}
                        result +=  arrText.join('');
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $('.ticketListYqb').html(result);
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
                url: '${ctx}/wxyqb/forTicketData.htm',
                data: {userId: ${userId}},
                dataType: 'json',
                success: function(data){
                 var length=data.array.length;
                 //判断是否有数据
			        if(length==0){
			        	$("#godBody").show();
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
			          	arrText.push("<tr><td><div class='coloGray smallSize padBoit'>"+data.array[i].openEquity+"："+data.array[i].capitalAccount+"</div>");
			          	arrText.push("<div class='profitLists'><span class='padTop coloGray flatL soSmaSize'>昨日盈利</span>")
			          	if(data.array[i].nowProfit>=0){
			          		arrText.push("<span class=' flatR coloRed smBiSiz'>+"+data.array[i].nowProfit+"元</span></div></td></tr>");
			          	}
			          	if(data.array[i].nowProfit<0){
			          		arrText.push("<span class=' flatR greeCol smBiSiz'>-"+data.array[i].nowProfit+"元</span></div></td></tr>");
			          	}
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
                        $('.ticketListYqb').append(result);
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