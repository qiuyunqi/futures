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
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－app客户端帮助中心</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<script src="../js_hhr/jquery-1.11.1.min.js"></script>
<style>
body{width: 100%;height: 100%;background: #f0eff4;}
.app-wrapper{width: 100%;position: absolute;}
#appnav{position: fixed;width: 100%;z-index:99;}
#appnav li{float:left;width:20%;text-align: center;border-bottom:1px solid #dfdee3;box-sizing: border-box;}
#appnav li a{display: block;padding:17px 0;}
/* #appnav li:hover{background: #f0eff4;} */

.navbgA{background:white ;color:#87868b;}
.MyselectA{background: #0094ff;color:#fff;}
.navbg a span{display: block;}
.navbr{border-right: 1px solid #dfdee3;}
.navbro{border-right: 1px solid #0094ff;}
/* .appOne{margin-top:18%;} */
.list_title{height: 0px !important;line-height:0px !important;border-bottom:0px !important;background: white; }
.list_title .num_y{padding: 20px 0 30px;clear: both;}
.newgiude_icont{margin: 4px 0px !important;background-size:70%; }
.list_img .newgiude_ico{margin: 4px 10px 8px 0px !important;float:left;}
.article{width:94%;margin:0 auto;left: 3%;position: absolute;top: 55px; }
.newgiude_qs{padding:0 !important; clear: both;}
.list_img{font-size:0.85em !important;padding-top:0 !important;}
.newgiude_qs li{font-size:1em !important;}
.list_imgban{padding:0 !important;}
.num_y{font-size:1em !important;}
.list_one{border:none;}
.height{height:20px;}
.list_one .nwg{clear: both;}
.newg_icont{display: block;width:19px;height:11px;background: url("../images_hhr/helpUp.png") no-repeat center;float: right;margin: 5px 53px;cursor: pointer;background-size:70%; }
.longtxt{width:85%;display: block;float:left;padding-bottom:10px;}
</style>
</head>
<body>
	<div class="app-wrapper" id="apfs">
		<div id="appnav">
			<ul>
				<li class="navbg" href="#"><a href="#" class="navbgA"><span class="navbr">帮助中心</span></a></li>
				<c:forEach items="${dictionaries }" var="dic" varStatus="row">
				<li class="navbg" href="#help_${row.index+1}"><a class="navbgA" href="#help_${row.index+1}"><span class="navbr">${dic.name}</span></a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="height"></div>
		<div class="article">
		
		<c:forEach items="${list}" var="ls" varStatus="row">
			<div class="list_one">
				<div class="list_title">
					<a id="help_${row.index+1}" name="${row.index+1}"></a>
					<div class="num_y" id="tagname">${ls[0]}</div>
				</div>
				<div class="list_imgban"></div>
				<c:forEach items="${ls[1]}" var="article">
				<div class="list_img nwg">
		       		<p class="sub_tit"><span class="longtxt">${article.title}</span><em class="newgiude_icont"></em></p>
		       		<ol class="newgiude_qs">
		       			<li>${article.content}</li>
		       		</ol>
				</div>
				</c:forEach>
				<div class="list_imgban"></div>
			</div>
		</c:forEach>
			
			<div class="list_one">
				<div class="list_title">
					<a id="three" name="3"></a>
					<div class="num_y">MOM问题</div>
				</div>
				<div class="clear"></div>
				 
				<div class="list_img nwg">
		       		<p class="sub_tit btnCa"><i class="newgiude_ico"></i><span class="longtxt">产品说明书</span><em class="newgiude_icont iocntCa"></em></p>
		       		<ol class="newgiude_qs thirdA">
		       			<li>MOM投资模式即管理人的管理人基金（Manager of Mangers）模式，由MOM基金管理人并不直接管理基金，而是通过长期跟踪、研究基金经理投资过程，挑选长期贯彻自身投资理念、投资风格稳定并取得超额回报的基金经理，以投资子账户委托形式让他们负责投资管理的一种投资模式。</li>
		       		</ol>
		       		 <input class="hidvalsCa" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCb"><i class="newgiude_ico"></i><span class="longtxt">申请MOM方案后为什么没有收到账号和密码？</span><em class="newgiude_icont iocntCb"></em></p>
		       		<ol class="newgiude_qs thirdB">
		       			<li>方案申请时间：每个交易日8:40-16:00，其他时间申请方案将在次日生效。</li>
		       			<li>方案开设期货账户时间一般为5-10分钟，因此方案开户后5-10钟之后您才能收到账号和密码。如有问题，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCb" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCc"><i class="newgiude_ico"></i><span class="longtxt">为什么期货账号不可以登录多个软件？</span><em class="newgiude_icont iocntCc"></em></p>
		       		<ol class="newgiude_qs thirdC">
		       			<li>超级合伙人平台的期货账户分属不同的交易系统，因此每个账户只能对应一个操作软件，不能共用，如您对操作软件有特殊要求，请联系400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCc" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCd"><i class="newgiude_ico"></i><span class="longtxt">MOM方案清算规则</span><em class="newgiude_icont iocntCd"></em></p>
		       		<ol class="newgiude_qs thirdD">
		       			<li>清算方案是指停止MOM方案并终止交易，系统回收实盘资金并将交易账户的剩余资金退还至网站平台个人中心。</li>
		       			<li>清算申请时间：每日【9:00~15:00】。</li>
		       			<li>清算条件：清算时必须保证交易账户没有任何持仓，有持仓则无法进行系统清算。</li>
		       			<li>如有问题，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCd" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCe"><i class="newgiude_ico"></i><span class="longtxt">增减融资规则/减少融资、增加融资不成功？</span><em class="newgiude_icont iocntCe"></em></p>
		       		<ol class="newgiude_qs thirdE">
		       			<li>每个方案可以进行一次增加融资或减少融资，增/减融资时间为8:40-16:00。</li>
		       		</ol>
		       		 <input class="hidvalsCe" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCf"><i class="newgiude_ico"></i><span class="longtxt">提取利润规则</span><em class="newgiude_icont iocntCf"></em></p>
		       		<ol class="newgiude_qs thirdF">
		       			<li>提取利润时间有9:00-14:50，15:16-16:00，21:00-23:30三个时间段；</li>
		       			<li>提取条件：账户有盈利，且盘中风险度低于80%。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCf" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCj"><i class="newgiude_ico"></i><span class="longtxt">MOM方案/期货账号使用期限是如何计算的？</span><em class="newgiude_icont iocntCj"></em></p>
		       		<ol class="newgiude_qs thirdJ">
		       			<li>MOM方案的合同期限以自然月计算，如2015年8月15日申请MOM方案，则MOM方案收息日为每月14日，MOM方案清算需要在当月14日之前提交申请。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCj" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCh"><i class="newgiude_ico"></i><span class="longtxt">一个账号允许申请多少个MOM方案？</span><em class="newgiude_icont iocntCh"></em></p>
		       		<ol class="newgiude_qs thirdH">
		       			<li>每一个平台账号最多可以申请10个MOM方案。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCh" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCi"><i class="newgiude_ico"></i><span class="longtxt">日结MOM方案和月结MOM方案的区别？</span><em class="newgiude_icont iocntCi"></em></p>
		       		<ol class="newgiude_qs thirdI">
		       			<li>日结MOM方案指的是按天计算使用期限的方案，月结MOM方案指的是按月计算使用期限的方案。</li>
		       			<li>日结MOM方案不能隔夜持仓，月结MOM方案可以隔夜持仓；</li>
		       			<li>日结MOM方案按日收息，月结MOM方案按月收息，费率优惠程度不一样。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCi" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCg"><i class="newgiude_ico"></i><span class="longtxt">如何办理MOM方案续约？</span><em class="newgiude_icont iocntCg"></em></p>
		       		<ol class="newgiude_qs thirdG">
		       			<li>MOM方案到期前，可以通过MOM方案”管理“>”续约“按钮进行续约操作，若MOM方案到期时未做任何操作，则系统默认方案续约。如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCg" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
			
		</div>
	</div>
</body>
</html>
<script>

var p1=document.getElementById('appnav');
    p1.style.fontSize=(document.body.clientWidth*0.035)+"px";
	
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

$("#appnav li a").click(function() {
	$("#appnav li a").removeClass("MyselectA");
	$("#appnav li a").addClass("navbgA");
	$("#appnav li a span").removeClass("navbro");
	$("#appnav li a span").addClass("navbr");
	$(this).removeClass("navbgA");
	$(this).addClass("MyselectA");
	$(this).children().removeClass("navbr");
	$(this).children().addClass("navbro");
	});


</script>
