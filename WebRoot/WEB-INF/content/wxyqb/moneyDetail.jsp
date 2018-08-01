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
<title>${title}－我的财富明细</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div class="findTicList">
		        	<table class="detailTicIn" cellpadding="0" cellspacing="0" width="100%" border="0">
					     <!-- <tr>
					      	<td class=" smallSize firstTd">
					      		<div class="upThDe smallSize">
					      			<span class="blueCol">充值</span><span class="gryCol frgt">2016-6-20</span>
					      		</div>
					      		<div class="leftDownDe">
					      			<span>余额：6.15</span><span class="fuZhi">+0.80元</span>
					      		</div>
					      	</td>
				         </tr>	 -->
				         <c:if test="${empty detailList}">
					  	    <tr>
						      	<td>
						         	<div class="noData">
						         		<img src="../images_yqb/ganta.png"/>
						         		<span>暂无明细</span>
						         	</div>
						         </td>
					        </tr>
					  	 </c:if>
					  	 <c:if test="${!empty detailList}">
						      <c:forEach items="${detailList}" var="detail" varStatus="row">
					         	  <tr>
							          <td class=" smallSize firstTd">
							      	      <div class="upThDe smallSize">
							      		      <span class="blueCol">${detail.fuDictionary.name}</span><span class="gryCol frgt"><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd"/></span>
							      		  </div>
							      		  <div class="leftDownDe">
							      			  <span>余额：${detail.accountBalanceAfter}元</span>
							      			  <c:if test="${detail.money >= 0}">
											      <span class="zhangZhi">+${detail.money}元</span>		
											  </c:if>
							      			  <c:if test="${detail.money < 0}">
											      <span class="fuZhi">${detail.money}元</span>		
											  </c:if>
							      		  </div>
							      	  </td>
						          </tr>
							  </c:forEach>
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
    // 每页展示6个
    var num = 6;
    var pageStart = 0,pageEnd = 0;

    // dropload
      $('.findTicList').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh" >↓下拉刷新</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<div class="dropload-noData">暂无财富明细</div>'
        },
        loadUpFn : function(me){
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
        },
        loadDownFn : function(me){
            $.ajax({
                type: 'GET',
                url: '${ctx}/wxyqb/userMoneyDetailList.htm',
                data:{userId:${fuUser.id}},
                dataType: 'json',
                success: function(data){
                 var length=data.lists.length;
                 //判断是否有数据
			        if(length==0){
			        	$("#godBody").show();
			        	$(".dropload-down").hide();
			        }else if(length<=6){
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
			          arrText.push("<div class='upThDe smallSize'><span class='blueCol'>"+data.lists[i].name+"</span><span class='gryCol frgt'>"+data.lists[i].time+"</span></div>");
			          arrText.push("<div class='leftDownDe'><span>"+"余额："+data.lists[i].accountBalanceAfter+"元</span>");
			          if(data.lists[i].money>=0){
			              arrText.push("<span class='zhangZhi'>+"+data.lists[i].money+"元</span></div>");
			          }
			          if(data.lists[i].money<0){
			              arrText.push("<span class='fuZhi'>"+data.lists[i].money+"元</span></div>");
			          }
			          arrText.push("</td></tr>");
                        result +=  arrText.join('');
                        if((i + 1) >= data.lists.length){
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                            break;
                        }
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $('.detailTicIn').append(result);
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