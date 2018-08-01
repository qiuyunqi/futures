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
<title>${title}－更多详情</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
html{width: 100%;height:100%; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fefefe;height:100%; }
.center{text-align: center;margin:0 !important;}
.white{color:#fff;}
.sucContain{position: absolute;width:100%;height:auto;}
.containImg{width:100%;height:auto;}
.infoList{width:100%;}
.listTit{font-size:1.5em;position: relative;margin: 5% 0;}
.listBg{display:block;width:1.5em;height:1.5em;float:left;margin: 0 2%;}
.oranTxt{color:#fdbc40;}
.redTxt{color:#ff6600;}
.blueTxt{color:#01aaad;}
.pinkTxt{color:#b184b9;}
.greenTxt{color:#5883d9;}
.orange{background: url("../images_yqb/moreInfo1.png") no-repeat center/100%;}
.red{background: url("../images_yqb/moreInfo2.png") no-repeat center/100%;}
.blue{background: url("../images_yqb/moreInfo3.png") no-repeat center/100%;}
.pink{background: url("../images_yqb/moreInfo4.png") no-repeat center/100%;}
.green{background: url("../images_yqb/moreInfo5.png") no-repeat center/100%;}
.oranBg{background: #fdbc40;}
.redBg{background: #ff6600;}
.blueBg{background: #01aaad;}
.pinkBg{background: #b184b9;}
.greenBg{background: #5883d9;}
.listsYqb{margin:0 2% 5%;font-size:1.2em;}
.listsYqb td{vertical-align: top;}
.circle{display:block;width:1em;height:1em;float:left;border-radius:1em;}
.padTop{padding:0.2em;}
.infoFooter{width:100%;padding:2% 0;font-size:1.5em;}
.shadow{height:1em;width:100%;background: #ededed;}
@media screen and (max-width:240px){
	.containImg{margin-top: 5%;font-size:1em;}
	.infoFooter{font-size:1.3em;}
}
@media screen and (min-width:768px){
    .containImg{margin-top: 5%;}
    .phoneNum{text-align: center;}
}
</style>
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<div class="infoList">
				<div class="listTit oranTxt"><i class="listBg orange"></i>收益说明</div>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle oranBg"></span></td>
				      	<td style="border:0;text-align: left;">股票收益：股票账户可用股票市值部分（后简称“股票市值”），享受年化8%稳定收益；</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle oranBg"></span></td>
				      	<td style="border:0;text-align: left;">资金收益：股票账户可用资金部分（后简称“可用资金”），享受年化12%稳定收益。<br/>（备注：可用资金部分最高支付股票市值15%额度的资金收益，超出部分的可用资金无收益）</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle oranBg"></span></td>
				      	<td style="border:0;text-align: left;">举例：某投资者提交股票账户，股票账户内含有200万股票市值和40万可用资金。用户可享受可用资金激励部分为（200万*15%）=30万。则用户实际年化收益为：200万*8%+30万*12%=19.6万元，用户实际每周获得3770元左右。</td>
				      </tr>
				     </table>
				</p>
			</div>
			<div class="infoFooter center white oranBg">同股同数，年化8% + 稳定收益</div>
			<div class="shadow"></div>
			<div class="infoList">
				<div class="listTit redTxt"><i class="listBg red"></i>结算说明</div>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle redBg"></span></td>
				      	<td style="border:0;text-align: left;">T+1交易日起产生收益，T+2交易日即可查看收益；</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle redBg"></span></td>
				      	<td style="border:0;text-align: left;">收益每周结算，结算后投资者依据合约支付管理费。</td>
				      </tr>
				     </table>
				</p>
			</div>
			<div class="infoFooter center white redBg">本息保障，每周结算</div>
			<div class="shadow"></div>
			<div class="infoList">
				<div class="listTit blueTxt"><i class="listBg blue"></i>风险说明</div>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle blueBg"></span></td>
				      	<td style="border:0;text-align: left;">加入余券宝的所有资产始终留存在投资者的股票账户内，不发生任何转移，无信用风险。</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle blueBg"></span></td>
				      	<td style="border:0;text-align: left;">余券宝操作过程中，所有收益全部留存在投资者股票账户内，投资者依据合约支付管理费，无安全风险。</td>
				      </tr>
				     </table>
				</p>
			</div>
			<div class="infoFooter center white blueBg">银行的安全，P2P的收益</div>
			<div class="shadow"></div>
			<div class="infoList">
				<div class="listTit pinkTxt"><i class="listBg pink"></i>退出说明</div>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle pinkBg"></span></td>
				      	<td style="border:0;text-align: left;">合约到期后，投资者进入余券宝，点击“删除账号”，即可完成退出操作；</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle pinkBg"></span></td>
				      	<td style="border:0;text-align: left;">如合约到期后，投资者未进行退出操作，则默认为续约一个月；</td>
				      </tr>
				     </table>
				</p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle pinkBg"></span></td>
				      	<td style="border:0;text-align: left;">如合约未到期，投资者因故退出余券宝，则需缴纳股票市值部分0.5%额度的违约金。</td>
				      </tr>
				     </table>
				</p>
			</div>
			<div class="infoFooter center white pinkBg">合约到期，删除账号后退出</div>
			<div class="shadow"></div>
			<div class="infoList">
				<div class="listTit greenTxt"><i class="listBg green"></i>适宜账户</div>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle greenBg" style="margin-right: 0.2em;margin-top: 0.2em;"></span>余券宝针对A股股票账户进行操作；</td>
				      </tr>
				     </table>
				     </p>
				<p>
					<table class="listsYqb" cellpadding="0" cellspacing="0" width="96%" border="0" >
				      <tr>
				      	<td class="padTop"><span class="circle greenBg"></span></td>
				      	<td style="border:0;text-align: left;">股票账户内，股票市值（不含ST、停牌等）不低于50万元，可用资金不低于股票市值的10%，手续费率不高于3%% 。</td>
				      </tr>
				     </table>
				</p>
			</div>
			<div class="infoFooter center white greenBg">长线投资用户的最佳选择</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var p0=document.getElementById('containTxt')
	p0.style.fontSize=(document.body.clientWidth*0.06)+"px";
	
	var p1=document.getElementById('containAnswer')
	p1.style.fontSize=(document.body.clientWidth*0.05)+"px";
	
	var p2=document.getElementById('fenx')
	p2.style.fontSize=(document.body.clientWidth*0.04)+"px";
</script> 	
	
