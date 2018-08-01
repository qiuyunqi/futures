<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－帮助中心</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<style type="text/css">
 @media screen and (max-width: 1024px){
 			.navLf2{background: #eee none repeat scroll 0 0;float: left;width: 180px;position: fixed;top:10px;border: 1px solid #b7b7b7;left:75px;}
           .container-outer{
                min-width:800px;margin:0 auto;max-width: 1200px;position: relative;width:8%;left:90px;
            }
            .list_img>img{
            	display: block;width:95%;
            }
            .zysy li {
             margin: 0 20px;width: 43%;
            }
        }
	
	.navLf2{background: #eee none repeat scroll 0 0;float: left;width: 180px;position: fixed;top:10px;border: 1px solid #b7b7b7;}
	.content-list{padding: 0 !important;}
	.newg_icont{display: block;width:19px;height:11px;background: url("../images_hhr/helpUp.png") no-repeat center;float: right;margin: 5px 53px;cursor: pointer;}
</style>

<body>
<c:set value="3" var="ipg"></c:set>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
<div id="hbannerb">
  <div class="hbanner"></div>
</div>
<div class="container-outer">
	<div class="content">
		<div class="navLf" id="navLf">
			<ul>
				<li class="guide-cpsm"><a href="#">帮助中心</a></li>
				<li><a href="#one">注册及登录问题</a></li>
				<li><a href="#two">充值和提现</a></li>
				<li><a href="#three">MOM问题</a></li>
				<li><a href="#four">点券商城</a></li>
				<li><a href="#five">解套者联盟</a></li>
			</ul>
		</div>
	<div class="resign-form">
		<div class="content-list">
			<div class="list_one">
				<div class="list_title">
					<a id="one" name="1"></a>
					<div class="num_y">注册及登录问题</div>
				</div>
				<div class="list_imgban"></div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnAa"><i class="newgiude_ico"></i><span>什么是超级合伙人？</span><em class="newgiude_icont iocntA"></em></p>
		       		<ol class="newgiude_qs firstA">
		       			<li>超级合伙人联盟，是一个基于人脉与资源共享的综合金融服务平台。我们旨在让人脉真有价值，让人脉创造价值，通过合伙共享成就。</li>
		       		</ol>
		       		 <input class="hidvalsA" type="hidden" value="0"/>
				</div>
				<div class="list_img">
		       		<p class="sub_tit  btnAb"><i class="newgiude_ico"></i><span>邀请码有什么作用？</span><em class="newgiude_icont iocntB"></em></p>
		       		<ol class="newgiude_qs firstB">
		       			<li>超级合伙人平台采取邀请注册制，每位合伙人都需要被邀请才能加入。邀请码对于维系合伙人的人脉结构具有重要作用，每位合伙人享受的服务、利益都与邀请码息息相关。</li>
		       		</ol>
		       		 <input class="hidvalsB" type="hidden" value="0"/>
				</div>
				<div class="list_img">
		       		<p class="sub_tit btnAc"><i class="newgiude_ico"></i><span>邀请码如何获取？</span><em class="newgiude_icont iocntC"></em></p>
		       		<ol class="newgiude_qs firstC">
		       			<li>您注册时的邀请码可以通过已注册的朋友或在线客服获取。注册成功之后登陆网站个人中心，在个人中心点击“邀请码”旁的问号图标，能看到您的邀请码；也可以通过下载超级合伙人app查看您的邀请码。</li>
		       		</ol>
		       		 <input class="hidvalsC" type="hidden" value="0"/>
				</div>
				<div class="list_img">
		       		<p class="sub_tit btnAd"><i class="newgiude_ico"></i><span>怎样邀请别人注册？</span><em class="newgiude_icont iocntD"></em></p>
		       		<ol class="newgiude_qs firstD">
		       			<li>帮助朋友下载超级合伙人app或者告诉朋友访问“超级合伙人”网站，并且将您的邀请码发送给朋友，待其注册成功后，您就完成了一次邀请。</li>
		       		</ol>
		       		 <input class="hidvalsD" type="hidden" value="0"/>
				</div>
				<div class="list_img">
		       		<p class="sub_tit btnAe"><i class="newgiude_ico"></i><span>收不到验证短信，注册不了，是什么原因？</span><em class="newgiude_icont iocntE"></em></p>
		       		<ol class="newgiude_qs firstE">
		       			<li>收不到验证短信，有可能是您的手机安装了短信拦截程序，请您将手机中的短信拦截程序暂时关闭掉，再进行注册。如有问题，请联系客服：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsE" type="hidden" value="0"/>
				</div>
				<div class="list_img">
		       		<p class="sub_tit btnAf"><i class="newgiude_ico"></i><span>怎样修改登录/提款密码？</span><em class="newgiude_icont iocntF"></em></p>
		       		<ol class="newgiude_qs firstF">
		       			<li class="newgiude_qsla">您可以通过网站“我的账户”> “安全信息”修改登录和提款密码；还可以通过超级合伙人app进行修改。</li>
		       		</ol>
		       		 <input class="hidvalsF" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
			
			<div class="list_one">
				<div class="list_title">
					<a id="two" name="2"></a>
					<div class="num_y">充值和提现</div>
				</div>
			<div class="clear"></div>
			<div class="list_imgban"></div>	
				<div class="list_img nwg">
		       		<p class="sub_tit btnBa"><i class="newgiude_ico"></i><span>充值以后，为什么个人中心看不到资金？</span><em class="newgiude_icont iocntBa"></em></p>
		       		<ol class="newgiude_qs secondA">
		       			<li>个人账户充值以后，后台会根据银行转账到账时间为准进行审核（使用银联在线充值一般即时到账，使用银行转账方式充值到账稍慢），一般半个小时内到账，如半个小时后资金未到达您的个人中心，请与客服联系。联系电话：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsBa" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnBb"><i class="newgiude_ico"></i><span>提现以后，资金多长时间到账？</span><em class="newgiude_icont iocntBb"></em></p>
		       		<ol class="newgiude_qs secondB">
		       			<li>账户提现实行T+1到账，即当日提交提现申请，第二个工作日之内到账。如有异常，请与客服联系，联系电话：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsBb" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnBc"><i class="newgiude_ico"></i><span>提现与提盈的区别？</span><em class="newgiude_icont iocntBc"></em></p>
		       		<ol class="newgiude_qs secondC">
		       			<li>提现指的是您的资金从网站平台转账至您的个人银行卡；提盈指的是从交易账户提取盈利资金至网站平台。</li>
		       			<li>提盈时间： 实时到账。</li>
		       			<li>提现时间： 最晚T+1到账。</li>
		       		</ol>
		       		 <input class="hidvalsBc" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
			<div class="list_one">
				<div class="list_title">
					<a id="three" name="3"></a>
					<div class="num_y">MOM问题</div>
				</div>
				<div class="clear"></div>
				<div class="list_imgban"></div>	
				<div class="list_img nwg">
		       		<p class="sub_tit btnCa"><i class="newgiude_ico"></i><span>产品说明书</span><em class="newgiude_icont iocntCa"></em></p>
		       		<ol class="newgiude_qs thirdA">
		       			<li>MOM投资模式即管理人的管理人基金（Manager of Mangers）模式，由MOM基金管理人并不直接管理基金，而是通过长期跟踪、研究基金经理投资过程，挑选长期贯彻自身投资理念、投资风格稳定并取得超额回报的基金经理，以投资子账户委托形式让他们负责投资管理的一种投资模式。</li>
		       		</ol>
		       		 <input class="hidvalsCa" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCb"><i class="newgiude_ico"></i><span>申请MOM方案后为什么没有收到账号和密码？</span><em class="newgiude_icont iocntCb"></em></p>
		       		<ol class="newgiude_qs thirdB">
		       			<li>方案申请时间：每个交易日8:40-16:00，其他时间申请方案将在次日生效。</li>
		       			<li>方案开设期货账户时间一般为5-10分钟，因此方案开户后5-10钟之后您才能收到账号和密码。如有问题，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCb" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCc"><i class="newgiude_ico"></i><span>为什么期货账号不能登录多个软件？</span><em class="newgiude_icont iocntCc"></em></p>
		       		<ol class="newgiude_qs thirdC">
		       			<li>超级合伙人平台的期货账户分属不同的交易系统，因此每个账户只能对应一个操作软件，不能共用，如您对操作软件有特殊要求，请联系400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCc" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCd"><i class="newgiude_ico"></i><span>MOM方案清算规则</span><em class="newgiude_icont iocntCd"></em></p>
		       		<ol class="newgiude_qs thirdD">
		       			<li>清算方案是指停止MOM方案并终止交易，系统回收实盘资金并将交易账户的剩余资金退还至网站平台个人中心。</li>
		       			<li>清算申请时间：每日【9:00~15:00】。</li>
		       			<li>清算条件：清算时必须保证交易账户没有任何持仓，有持仓则无法进行系统清算。</li>
		       			<li>如有问题，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCd" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCe"><i class="newgiude_ico"></i><span>增减融资规则/减少融资、增加融资不成功？</span><em class="newgiude_icont iocntCe"></em></p>
		       		<ol class="newgiude_qs thirdE">
		       			<li>每个方案可以进行一次增加融资或减少融资，增/减融资时间为8:40-16:00。</li>
		       		</ol>
		       		 <input class="hidvalsCe" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCf"><i class="newgiude_ico"></i><span>提取利润规则</span><em class="newgiude_icont iocntCf"></em></p>
		       		<ol class="newgiude_qs thirdF">
		       			<li>提取利润时间有9:00-14:50，15:16-16:00，21:00-23:30三个时间段；</li>
		       			<li>提取条件：账户有盈利，且盘中风险度低于80%。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCf" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCj"><i class="newgiude_ico"></i><span>MOM方案/期货账号使用期限是如何计算的？</span><em class="newgiude_icont iocntCj"></em></p>
		       		<ol class="newgiude_qs thirdJ">
		       			<li>MOM方案的合同期限以自然月计算，如2015年8月15日申请MOM方案，则MOM方案收息日为每月14日，MOM方案清算需要在当月14日之前提交申请。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCj" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCh"><i class="newgiude_ico"></i><span>一个账号可以申请几个MOM方案？</span><em class="newgiude_icont iocntCh"></em></p>
		       		<ol class="newgiude_qs thirdH">
		       			<li>每一个平台账号最多可以申请10个MOM方案。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCh" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCi"><i class="newgiude_ico"></i><span>日结MOM方案和月结MOM方案的区别？</span><em class="newgiude_icont iocntCi"></em></p>
		       		<ol class="newgiude_qs thirdI">
		       			<li>日结MOM方案指的是按天计算使用期限的方案，月结MOM方案指的是按月计算使用期限的方案。</li>
		       			<li>日结MOM方案不能隔夜持仓，月结MOM方案可以隔夜持仓；</li>
		       			<li>日结MOM方案按日收息，月结MOM方案按月收息，费率优惠程度不一样。</li>
		       			<li>如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCi" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnCg"><i class="newgiude_ico"></i><span>如何办理MOM方案续约？</span><em class="newgiude_icont iocntCg"></em></p>
		       		<ol class="newgiude_qs thirdG">
		       			<li>MOM方案到期前，可以通过MOM方案”管理“>”续约“按钮进行续约操作，若MOM方案到期时未做任何操作，则系统默认方案续约。如有疑问，请联系：400-0230-898。</li>
		       		</ol>
		       		 <input class="hidvalsCg" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
			
			<div class="list_one">
				<div class="list_title">
					<a id="four" name="4"></a>
					<div class="num_y">点券商城</div>
				</div>
				<div class="clear"></div>
				<div class="list_imgban"></div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnDa"><i class="newgiude_ico"></i><span>什么是点券？</span><em class="newgiude_icont iocntDa"></em></p>
		       		<ol class="newgiude_qs fourA">
		       			<li>点券是超级合伙人联盟为了弥补客户在充值时被支付平台收取从而损失掉的资金补偿计划。点券为网站虚拟币，不能取出，只能按规定使用。</li>
		       		</ol>
		       		 <input class="hidvalsDa" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnDb"><i class="newgiude_ico"></i><span>如何获取点券？</span><em class="newgiude_icont iocntDb"></em></p>
		       		<ol class="newgiude_qs fourB">
		       			<li>当客户充值时会产生手续费，超级合伙人联盟以实际产生的手续费金额与点券进行1:1的补偿，每1元手续费会得到1点券的补偿（不进行四舍五入计算，仅取用整数位）。目前无法直接主充值点券。</li>
		       		</ol>
		       		 <input class="hidvalsDb" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnDc"><i class="newgiude_ico"></i><span>点券有什么用？</span><em class="newgiude_icont iocntDc"></em></p>
		       		<ol class="newgiude_qs fourC">
		       			<li>点券冲抵风险保证金时，1点券＝1元保证资金，不可超过保证金总额的50%。举个例子说明：客户小明要用1000元保证金申请5000元实盘资金，总计6000元，使用100点券后，小明只需提供900元保证金，就可以获得总计5900元的操盘资金，小明相当于用更少的钱融到了实盘资金。</li>
		       		</ol>
		       		 <input class="hidvalsDc" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnDd"><i class="newgiude_ico"></i><span>哪些地方可以使用点券？</span><em class="newgiude_icont iocntDd"></em></p>
		       		<ol class="newgiude_qs fourD">
		       			<li>目前点券只可用来抵充保证金。未来开通点券商城后，可以兑换服务或者商品。 </li>
		       		</ol>
		       		 <input class="hidvalsDd" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
			
			<div class="list_one">
				<div class="list_title">
					<a id="five" name="4"></a>
					<div class="num_y">解套者联盟</div>
				</div>
				<div class="clear"></div>
				<div class="list_imgban"></div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEa"><i class="newgiude_ico"></i><span>什么是解套者联盟？</span><em class="newgiude_icont iocntEa"></em></p>
		       		<ol class="newgiude_qs fiveA">
		       			<li>解套者联盟是一项金融创新服务，利用日内股票回转交易产生浮动收益，坤州大德投资管理有限公司为交易进行亏损赔付担保，合伙人将交易账户委托给“解套者联盟交易团队”进行操盘，每日收盘后第二个交易日结清盈亏，保证每日账户原有股票种类不变，保证每日股票数量不变，保证用户不承担任何亏损，合伙人与平台共同分享交易盈利。</li>
		       		</ol>
		       		 <input class="hidvalsEa" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEb"><i class="newgiude_ico"></i><span>如何加入解套者联盟？</span><em class="newgiude_icont iocntEb"></em></p>
		       		<ol class="newgiude_qs fiveB">
		       			<li>首先注册成为超级合伙人联盟用户，完成实名认证，选择解套者联盟业务，上传相关账户信息后，通过审核后将通知您加入成功，下一交易日即可开始。</li>
		       		</ol>
		       		 <input class="hidvalsEb" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEc"><i class="newgiude_ico"></i><span>客户获利原理是什么？</span><em class="newgiude_icont iocntEc"></em></p>
		       		<ol class="newgiude_qs fiveC">
		       			<li>解套者联盟交易团队在不改变账户股票品种和数量的前提下，进行多次日内回转短线交易，利用行情波动提高股票资产利用率，通过高胜率实现最终盈利。每日收盘时，交易团队保证交易账户股票品种和数量没有任何改变，而交易账户内的可用资金增加了，也就是获得了盈利。</li>
		       		</ol>
		       		 <input class="hidvalsEc" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEd"><i class="newgiude_ico"></i><span>解套者联盟如何获利？</span><em class="newgiude_icont iocntEd"></em></p>
		       		<ol class="newgiude_qs fiveD">
		       			<li>解套者联盟通过为客户的证券账户创造盈利而获利。解套者联盟获得盈利的60%，但需承担亏损风险；客户获得盈利的40%，无需承受任何亏损风险。 </li>
		       		</ol>
		       		 <input class="hidvalsEd" type="hidden" value="0"/>
				</div>
				
				<div class="list_img nwg">
		       		<p class="sub_tit btnEe"><i class="newgiude_ico"></i><span>什么是预期收益？</span><em class="newgiude_icont iocntEe"></em></p>
		       		<ol class="newgiude_qs fiveE">
		       			<li>解套者联盟交易团队利用客户的证券交易账户进行日内交易，每日盈亏随着行情的大小可能会出现浮动，所以预期收益是我们对交易团队历史业绩的一个估算平均值。 </li>
		       		</ol>
		       		 <input class="hidvalsEe" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEf"><i class="newgiude_ico"></i><span>如果交易亏损怎么办？</span><em class="newgiude_icont iocntEf"></em></p>
		       		<ol class="newgiude_qs fiveF">
		       			<li>解套者联盟通过为客户的证券账户创造盈利而获利。解套者联盟获得盈利的60%，但需承担亏损风险；客户获得盈利的40%，无需承受任何亏损风险。 </li>
		       		</ol>
		       		 <input class="hidvalsEf" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEg"><i class="newgiude_ico"></i><span>每日结算制度是怎样的？</span><em class="newgiude_icont iocntEg"></em></p>
		       		<ol class="newgiude_qs fiveG">
		       			<li>客户的证券账户按单个交易日为周期进行结算。在参与解套者联盟业务后，如出现亏损，联盟对客户第二个交易日结算完成赔付，如结算完成出现盈利，客户对联盟按规则退还赔付金后，将收益部分进行分成。 </li>
		       		</ol>
		       		 <input class="hidvalsEg" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEh"><i class="newgiude_ico"></i><span>没有股票持仓能参加吗？</span><em class="newgiude_icont iocntEh"></em></p>
		       		<ol class="newgiude_qs fiveH">
		       			<li>如果客户没有股票持仓，您可以成为价值投资者，对看好的股票进行建仓，之后按照解套者联盟联盟规则，由解套者联盟交易团队为您进行增值服务。 </li>
		       		</ol>
		       		 <input class="hidvalsEh" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEi"><i class="newgiude_ico"></i><span>为什么要使用合伙人账户结算？</span><em class="newgiude_icont iocntEi"></em></p>
		       		<ol class="newgiude_qs fiveI">
		       			<li>方便用户查询实时的结算信息，同时给客户提供了一个简便的结算途径，使客户不再需要频繁出入证券交易账户内的资金。 </li>
		       		</ol>
		       		 <input class="hidvalsEi" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEj"><i class="newgiude_ico"></i><span>为什么是盈利分成？</span><em class="newgiude_icont iocntEj"></em></p>
		       		<ol class="newgiude_qs fiveJ">
		       			<li>盈利分成模式将客户与交易团队变成利益共同体，最大程度激发交易团队的主观能动性，为客户创造收益。 </li>
		       		</ol>
		       		 <input class="hidvalsEj" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEk"><i class="newgiude_ico"></i><span>为什么有时候客户要退还赔付金？</span><em class="newgiude_icont iocntEk"></em></p>
		       		<ol class="newgiude_qs fiveK">
		       			<li>赔付金对于客户来说是一种保险，是为避免客户对短暂亏损产生担忧而设立的客户权益保障机制。当联盟为客户扭亏为盈时，客户应当退还相应的赔付金，然后净盈利部分进行分成，以保障双方利益。 </li>
		       		</ol>
		       		 <input class="hidvalsEk" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEl"><i class="newgiude_ico"></i><span>如何保障安全？</span><em class="newgiude_icont iocntEl"></em></p>
		       		<ol class="newgiude_qs fiveL">
		       			<li>证券账户为客户本人所有，客户对证券账户拥有最终控制权，用户可以在解套者联盟交易团队进行交易的同时登陆证券交易账户，在不干扰交易的前提下进行监控，如出现违反相关协议的情况，用户可以第一时间做出反应。 </li>
		       		</ol>
		       		 <input class="hidvalsEl" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEm"><i class="newgiude_ico"></i><span>需要指定的券商吗？</span><em class="newgiude_icont iocntEm"></em></p>
		       		<ol class="newgiude_qs fiveM">
		       			<li>目前不做特别要求。但是在指定券商开户，解套者联盟将作为一个整体，拥有散户所没有的整体议价能力，为客户争取到更低的佣金。 </li>
		       		</ol>
		       		 <input class="hidvalsEm" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEn"><i class="newgiude_ico"></i><span>为何交易账户要有少量可用资金？</span><em class="newgiude_icont iocntEn"></em></p>
		       		<ol class="newgiude_qs fiveN">
		       			<li>账户内留有少量的可用资金，才能实现日内回转交易。解套者联盟交易团队可以灵活利用这部分资金完成先买后卖的操作。 </li>
		       		</ol>
		       		 <input class="hidvalsEn" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEo"><i class="newgiude_ico"></i><span>为什么要求交易佣金不高于万3？</span><em class="newgiude_icont iocntEo"></em></p>
		       		<ol class="newgiude_qs fiveO">
		       			<li>由于解套者联盟交易团队是利用客户股票持仓进行日内回转交易，日均换手率较高，过高的佣金率会导致大量盈利被交易佣金抵消，降低客户收益。 </li>
		       		</ol>
		       		 <input class="hidvalsEo" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEp"><i class="newgiude_ico"></i><span>为什么需要实名认证？</span><em class="newgiude_icont iocntEp"></em></p>
		       		<ol class="newgiude_qs fiveP">
		       			<li>为避免他人盗用客户信息，参加解套者联盟时要求实名认证。客户所上传的证券交易账户必须为客户本人所有。 </li>
		       		</ol>
		       		 <input class="hidvalsEp" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEq"><i class="newgiude_ico"></i><span>股票波动率对收益的影响？</span><em class="newgiude_icont iocntEq"></em></p>
		       		<ol class="newgiude_qs fiveQ">
		       			<li>由于解套者联盟交易团队采用日内回转交易策略，越高的股票波动率将提供更多的交易机会和盈利空间，可以更好的放大交易盈利。波动率较低的股票不利于交易获利。 </li>
		       		</ol>
		       		 <input class="hidvalsEq" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEr"><i class="newgiude_ico"></i><span>如何暂停或取消账户托管？</span><em class="newgiude_icont iocntEr"></em></p>
		       		<ol class="newgiude_qs fiveR">
		       			<li>客户可以选择T日删除已委托的证券交易账户，T+1日解套者交易团队将停止交易，如需继续，可再次选择加入解套者联盟。 </li>
		       		</ol>
		       		 <input class="hidvalsEr" type="hidden" value="0"/>
				</div>
				<div class="list_img nwg">
		       		<p class="sub_tit btnEs"><i class="newgiude_ico"></i><span>如何不再烦恼盈利每天转账？</span><em class="newgiude_icont iocntEs"></em></p>
		       		<ol class="newgiude_qs fiveS">
		       			<li>由于每日结算制度，解套者联盟交易团队T日为客户产生交易盈利，在T+1日结算完成后，客户应于T+2日前将盈利分成或者应退赔付转入解套者联盟交易团队指定账户。为减少客户操作，客户可一次性向超级合伙人联盟网站账户充值，以后产生的各项结算，可通过网站账户统一结算，不再需要额外转账。 </li>
		       		</ol>
		       		 <input class="hidvalsEs" type="hidden" value="0"/>
				</div>
				<div class="list_imgban"></div>
			</div>
		</div>
	</div>
	</div >
</div >
<div class="downban"></div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>
<link rel="stylesheet" type="text/css" href="style6s.css" media="screen and (max-width: 1920px)">
<script>
var fenziactive="<img src='../images_hhr/helpUp.png' class='newgiude_icont'/>";
var fenzizc="<img src='../images_hhr/helpDown.png' class='newg_icont'/>";
$(".fenzi").hide();
window.onscroll=function(){
	var scrolltop = document.documentElement.scrollTop+document.body.scrollTop;
	if(scrolltop<250){
		$("#navLf").attr("class","navLf");
	}else{
		$("#navLf").attr("class","navLf2");
	}
};

function up(btn,vale,hidiv,btna){
	$(btn).bind("click",function(){
		if($(vale).val()==0){
		        $(hidiv).show();
	        	$(btna).addClass("newg_icont");
	            $(vale).val(1);
	        }else if($(vale).val()==1){
	        $(hidiv).hide();
		        $(btna).removeClass("newg_icont");
	            $(vale).val(0);
	        	
	        }
	});
}

$(document).ready(function(){
	up(".btnAa",".hidvalsA",".firstA",".iocntA");
	up(".btnAb",".hidvalsB",".firstB",".iocntB");
	up(".btnAc",".hidvalsC",".firstC",".iocntC");
	up(".btnAd",".hidvalsD",".firstD",".iocntD");
	up(".btnAe",".hidvalsE",".firstE",".iocntE");
	up(".btnAf",".hidvalsF",".firstF",".iocntF");
	up(".btnBa",".hidvalsBa",".secondA",".iocntBa");
	up(".btnBb",".hidvalsBb",".secondB",".iocntBb");
	up(".btnBc",".hidvalsBc",".secondC",".iocntBc");
	up(".btnCa",".hidvalsCa",".thirdA",".iocntCa");
	up(".btnCb",".hidvalsCb",".thirdB",".iocntCb");
	up(".btnCc",".hidvalsCc",".thirdC",".iocntCc");
	up(".btnCd",".hidvalsCd",".thirdD",".iocntCd");
	up(".btnCe",".hidvalsCe",".thirdE",".iocntCe");
	up(".btnCf",".hidvalsCf",".thirdF",".iocntCf");
	up(".btnCj",".hidvalsCj",".thirdJ",".iocntCj");
	up(".btnCh",".hidvalsCh",".thirdH",".iocntCh");
	up(".btnCi",".hidvalsCi",".thirdI",".iocntCi");
	up(".btnCg",".hidvalsCg",".thirdG",".iocntCg");
	up(".btnDa",".hidvalsDa",".fourA",".iocntDa");
	up(".btnDb",".hidvalsDb",".fourB",".iocntDb");
	up(".btnDc",".hidvalsDc",".fourC",".iocntDc");
	up(".btnDd",".hidvalsDd",".fourD",".iocntDd");
	up(".btnEa",".hidvalsEa",".fiveA",".iocntEa");
	up(".btnEb",".hidvalsEb",".fiveB",".iocntEb");
	up(".btnEc",".hidvalsEc",".fiveC",".iocntEc");
	up(".btnEd",".hidvalsEd",".fiveD",".iocntEd");
	up(".btnEe",".hidvalsEe",".fiveE",".iocntEe");
	up(".btnEf",".hidvalsEf",".fiveF",".iocntEf");
	up(".btnEg",".hidvalsEg",".fiveG",".iocntEg");
	up(".btnEh",".hidvalsEh",".fiveH",".iocntEh");
	up(".btnEi",".hidvalsEi",".fiveI",".iocntEi");
	up(".btnEj",".hidvalsEj",".fiveJ",".iocntEj");
	up(".btnEk",".hidvalsEk",".fiveK",".iocntEk");
	up(".btnEl",".hidvalsEl",".fiveL",".iocntEl");
	up(".btnEm",".hidvalsEm",".fiveM",".iocntEm");
	up(".btnEn",".hidvalsEn",".fiveN",".iocntEn");
	up(".btnEo",".hidvalsEo",".fiveO",".iocntEo");
	up(".btnEp",".hidvalsEp",".fiveP",".iocntEp");
	up(".btnEq",".hidvalsEq",".fiveQ",".iocntEq");
	up(".btnEr",".hidvalsEr",".fiveR",".iocntEr");
	up(".btnEs",".hidvalsEs",".fiveS",".iocntEs");
});
</script>
