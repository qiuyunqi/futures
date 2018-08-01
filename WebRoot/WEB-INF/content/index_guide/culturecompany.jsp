<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－帮助中心</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<c:set var="jsptype" value="culturecompany"/>
<style>
.helpCen{display: block;}
.hwdAbout2{position: fixed;top:80px;width: 193px;}
</style>
</head>
<body class="bodybg">
<c:set value="0" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="downban"></div>
        <div class="contaer">

            <!--侧边导航-->
            <div class="hwdAbout" id="navLf">
                <div class="aboutTab">
                    <div class="aboutTit"><a><i class="indexAbout"></i>关于我们</a></div>
                    <ul class="aboutMenu intro">
                        <li class="intrCompany"><a href="${ctx}/about">公司简介</a></li>
                        <li class="map"><a href="${ctx}/contact">联系我们</a></li>
                    </ul>
                </div>
                <div class="aboutTab">
                    <div class="aboutTit"><a><i class="indexHelp"></i>帮助中心</a></div>
                    <ul class="aboutMenu helpCen">
                    	<c:forEach items="${dictionaries }" var="dic" varStatus="row">
                        <li class="helpCenLi"><a class="bankvi" href="#help_${row.index+1}">${dic.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="aboutTab ">
                    <div class="aboutTit"><a><i class="indexSaf"></i>保障和协议</a></div>
                     <ul class="aboutMenu safeXY">
				       <li class="agreement_hhr"><a class="bankvi" href="${ctx}/index_guide/agreement_hhr.htm">服务协议</a></li>
				       <li class="agreement_yqbClear"><a class="bankvi" href="${ctx}/index_guide/agreement_yqbClear.htm">余券宝合作协议</a></li>
				       <li class="agreement_wqq"><a class="bankvi" href="${ctx}/index_guide/agreement_wqq.htm">微期权委托协议</a></li>
				     </ul>
                </div>
            </div>
            <!--右边内容-->
            <div class="content">
                <div class="resign-form">
                    <div class="content-list">
                    
                    <c:forEach items="${list}" var="ls" varStatus="row">
                        <div class="list_one">
                            <div class="list_title">
                                <a id="help_${row.index+1}" name="${row.index+1}"></a>
                                <div class="num_y">${ls[0]}</div>
                            </div>
                            <div class="list_imgban"></div>
                            <c:forEach items="${ls[1]}" var="article">
                            <div class="list_img nwg">
                                <p class="sub_tit"><i class="newgiude_ico"></i><span>${article.title}</span><em class="newgiude_icont"></em></p>
                                <ol class="newgiude_qs">
                                    <li>${article.content}</li>
                                </ol>
                            </div>
                            </c:forEach>
                            <div class="list_imgban"></div>
                        </div>
					</c:forEach>
					
                    </div>
                </div>
            </div >
        </div >
    <div class="downban"></div>
<%@include file="../common/footer.jsp" %> 
</body>
</html>

<script>
window.onscroll=function(){
	var scrolltop = document.documentElement.scrollTop+document.body.scrollTop;
	if(scrolltop<250){
		$("#navLf").attr("class","hwdAbout");
	}else{
		$("#navLf").attr("class","hwdAbout2");
	}
};

 $(document).ready(function(){
		//左边导航栏
        $(".aboutTab").each(function(){
            $(".aboutTit").click(function(){
                $(this).next().toggle();
            });
        });


        //内容折叠
        $(".list_img .sub_tit").click(function(){
            $(this).find("em").toggleClass("newg_icont");
            $(this).next().toggle();
            var index = $(".list_img .sub_tit").index(this);
            $(".list_img .sub_tit").each(function(i){
                if(i != index){
                    if($(this).next().css("display")=="block"){
                        $(this).find("em").toggleClass("newg_icont");
                        $(this).next().css("display","none");
                    }
                }
            });
        });


	 	$(".helpCenLi").each(function(){
	         $("a").click(function(){
		         $(this).attr("class","bankvis");
		         $(this).parent().nextAll().find("a").attr("class","bankvi");
		         $(this).parent().prevAll().find("a").attr("class","bankvi");
	         });
	    });
});
</script>