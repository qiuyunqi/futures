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
<title>${title}－找券信息管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.findYqb{background: url("../images_yqb/findYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#findYqb{color:#fad400;}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div class="ticketLists">
			        <table class="ticketYqb" cellpadding="0" cellspacing="0" width="100%" border="0">
			        	<c:forEach items="${ticketList}" var="ticket">
					      <tr>
					      	<td onclick="location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id=${ticket.id}'">
					        	<div class="tbLftFind">
					        		<div class="blueCol siz">${ticket.title}</div>
					        		<div class="soSmaSize">发布时间：<fmt:formatDate value="${ticket.createTime}" pattern="yyyyMMdd HH:mm:ss"/></div>
					        	</div>
					      	</td>
					      	<td onclick="location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id=${ticket.id}'"><div class="tbRgt"><img src="../images_yqb/jtou.png"/></div></td>
					      	<c:if test="${ticket.isDel==1}">
					      	<td class="redBg bigSz whiteCol"><a class="forTicHei" href="${ctx}/wxyqb/ticketManagerClose.htm?id=${ticket.id}"><span class="whiteCol">删除</span></a></td>
					      	</c:if>
					      	<c:if test="${ticket.isDel==0}">
					      	<td class="grayBg bigSz whiteCol"><span>已删除</span></td>
					      	</c:if>
				         </tr>	
				         </c:forEach>
				         <c:if test="${empty ticketList }">
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
			<div class="yqbJg"></div>
		</div>
	</div>
	<%@include file="../wxyqb/footer_yqb.jsp" %>
</body>
</html>
<link href="${ctx}/css/wxYqb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js_hhr/dropload.min.js"></script>
<script type="text/javascript">
$(function(){
//点击加号出现下拉
    $(".addTick").click(function(){
    	$(".hidNav").toggle();
    });
});
</script>
<script>
//下拉加载更多
$(function(){
    var counter = 1;
    // 每页展示5个
    var num = 5;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $('.ticketLists').dropload({
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
                url: '${ctx}/wxyqb/ticketManagerData.htm',
                dataType: 'json',
                success: function(data){
                    var result = '';
                    for(var i = 0; i < data.array.length; i++){
                       	var arrText = [];
			          	arrText.push("<tr onclick=\"location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id="+data.array[i].id+"'\"><td><div class='tbLftFind'>");
			          	arrText.push("<div class='blueCol siz'>"+data.array[i].title+"</div>");
			          	arrText.push("<div class='soSmaSize'>发布时间："+data.array[i].createTime+"</div></div></td>");
			          	arrText.push("<td onclick=\"location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id="+data.array[i].id+"'\"><div class='tbRgt'><img src='../images_yqb/jtou.png'/></div></td>");
			          	if(data.array[i].isDel==1){
			          		arrText.push("<td class='redBg bigSz whiteCol'><a class='forTicHei' href='${ctx}/wxyqb/ticketManagerClose.htm?id="+data.array[i].id+"'><span class='whiteCol'>删除</span></a></td></tr>");
			          	}
			          	if(data.array[i].isDel==0){
			          		arrText.push("<td class='grayBg bigSz whiteCol'><span>已删除</span></td></tr>");
			          	}
                        result +=  arrText.join('');
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $('.ticketYqb').html(result);
                        // 每次数据加载完，必须重置
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
                url: '${ctx}/wxyqb/ticketManagerData.htm',
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
			          	arrText.push("<tr onclick=\"location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id="+data.array[i].id+"'\"><td><div class='tbLftFind'>");
			          	arrText.push("<div class='blueCol siz'>"+data.array[i].title+"</div>");
			          	arrText.push("<div class='soSmaSize'>发布时间："+data.array[i].createTime+"</div></div></td>");
			          	arrText.push("<td onclick=\"location.href='${ctx}/wxyqb/ticketManagerDetail.htm?id="+data.array[i].id+"'\"><div class='tbRgt'><img src='../images_yqb/jtou.png'/></div></td>");
			          	if(data.array[i].isDel==1){
			          		arrText.push("<td class='redBg bigSz whiteCol'><a class='forTicHei' href='${ctx}/wxyqb/ticketManagerClose.htm?id="+data.array[i].id+"'><span class='whiteCol'>删除</span></a></td></tr>");
			          	}
			          	if(data.array[i].isDel==0){
			          		arrText.push("<td class='grayBg bigSz whiteCol'><span>已删除</span></td></tr>");
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
                        $('.ticketYqb').append(result);
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
			      };//if end
                
                },//success结束
                error: function(xhr, type){
                    // 即使加载出错，也得重置
                    me.resetload();
                }  
            });//ajax结束
        },//上拉结束
        threshold : 50
        
    });//drop结束
});
</script>
