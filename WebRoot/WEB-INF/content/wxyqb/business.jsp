<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0">
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
<title>${title}－我要成为交易员</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
input[type=checkbox] {
	visibility: hidden;
}
input ::-webkit-input-placeholder { /* WebKit browsers */
		    color:    #646473;
		}
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    #646473;
		}
input::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    #646473;
		}
input:-ms-input-placeholder { /* Internet Explorer 10+ */
		    color:    #646473;
}
.agr_Buss{border: 1px solid #D4D4D4;margin: 0px auto;padding: 30px 20px 30px;overflow-y: scroll;}
h2{text-align: center;}
.listsYqb tr td{padding:5px 0;line-height: 22px;}
.listsYqb tr td p{padding:0 0 5px 0 ;}
.hid{visibility: hidden;}
</style>
</head>
<body>
	<div id="contract_hz">
    	<div class="agr_Buss">
    	<h2>余券宝交易员（团队）入驻方案</h2>
			<p>
				<table class="listsYqb" cellpadding="0" cellspacing="0" width="100%" border="0" >
			      <tr>
			      	<td align="left" valign="top" style="border:0;text-align: left;">一、</td>
			      	<td style="border:0;text-align: left;">申请步骤</td>
			      </tr>
			      <tr>
			      	<td align="left" valign="top" style="border:0;text-align: left;"></td>
			      	<td style="border:0;text-align: left;">拨打客服热线，在客服指导下完成入驻流程。<br/>完成所有流程后由平台发放指定账号和团队编号。</td>
			      </tr>
			     </table>
			</p>
			<p>
				<table class="listsYqb" cellpadding="0" cellspacing="0" width="100%" border="0" >
			      <tr>
			      	<td align="left" valign="top" style="border:0;text-align: left;">二、</td>
			      	<td style="border:0;text-align: left;">需要提供的材料</td>
			      </tr>
			      <tr>
			      	<td align="left" valign="top" style="border:0;text-align: left;"></td>
			      	<td style="border:0;text-align: left;">身份证扫描件（个人）或有资格的企业营业执照（团队），登记手机号、可供资金流转的银行卡信息。</td>
			      </tr>
			     </table></p>
			<p></p>
			<p>
				<table class="listsYqb" cellpadding="0" cellspacing="0" width="100%" border="0" >
			      <tr>
			      	<td align="left" valign="top" style="border:0;text-align: left;">三、</td>
			      	<td style="border:0;text-align: left;">合作细节</td>
			      </tr>
			      <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">1、抢券流程</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<p><span class="hid">1、</span>每当有新的待匹配券源信息，平台将以短信通知入驻交易员登记的手机号，通过指定合作账号进行抢券。点击抢券后，需在1小时内缴纳相应保证金，保证金支付完成后，获得券源操作权限。</p>
				      	<p><span class="hid">2、</span>如有已抢券但未支付相应保证金的订单，则不可再次抢券，直到上一个订单放弃或已支付才可再次参与抢券。</p>
					</td>
				  </tr>
				   <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">2、保证金制度</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<span class="hid">1、</span>保证金默认标准为券源信息实际可用资产的10%（包括可用市值和可用资金）正常退券完成后，保证金将于3个工作日后解冻，可提取或进行其他券源保证金支付。
					</td>
				  </tr>
				   <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">3、签约方式</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<span class="hid">1、</span>抢券同时通过电子三方合同进行一对一匹配。
					</td>
				  </tr>
				   <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">4、团队评级</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<span class="hid">1、</span>按照团队接票的收益率、已缴纳保证金量、已管理资产规模、主动退券频率、违反风控约定等参数对所有交易员进行评级，并公布到平台上。随着等级的提高，可获得优质券源优先匹配权和下调保证金比例等优惠。
					</td>
				  </tr>
				   <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">5、发布信息</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<span class="hid">1、</span>入驻的交易员可发布需要的券源信息以及可提供的收益方式，如有券源匹配，支付相应保证金后由平台提供操作权限。
					</td>
				  </tr>
				  <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">6、结算流程</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<p><span class="hid">1、</span>周结算：每周第一个工作日结算上周交易数据，第二个工作日开始进行客户催款工作，平台收到客户支付的收益后扣除平台管理费后支付给交易员。原则上时间不超过5个工作日，如超过5个工作日未支付，交易员有权无条件退券。</p>
						<p><span class="hid">1、</span>月结算：每自然月第一个工作日结算上周交易数据，第二个工作日进行客户催款工作，平台收到客户支付的收益后扣除平台管理费后支付给交易员。原则上时间不超过5个工作日，如超过5个工作日未支付，交易员有权无条件退券。</p>
					</td>
				  </tr>
				  <tr>
				      <td align="left" valign="top" style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">7、退券相关</td>
				  </tr>
				  <tr>
				      <td style="border:0;text-align: left;"></td>
				      <td style="border:0;text-align: left;">
				      	<p><span class="hid">1、</span>a.抢券成功后无故连续3个交易日未进行交易的</p>
				      	<p><span class="hid">1、</span>b.未提前2个交易日通知平台而要退券的</p>
				      	<p><span class="hid">1、</span>c.存在违反风控规则的异常行为（包括但不限于买卖非标的证券、超额卖出标的证券等）有以上所列行为之一的交易员，将被强制退券并按实际损失扣除最低1%最高10%的保证金。</p>
					</td>
				  </tr>
			     </table></p>
			<p></p>
        </div>
    </div>
	<div class="yqbJg"></div> 
	<div class="yqbJg"></div> 
	<div class="rob" style="height:99px;background: #ededed;">
		<p class="blueCol smallSize cler">提交成功后，我们会在一个工作</br>日内安排专人与您洽谈合作。</p>
		<a class="order" onclick="addTrasction()">提交申请</a>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function addTrasction(){
		layer.open({
		    content: "确认申请成为交易员？",
		    btn: ["确定","取消"],
		    shadeClose: false,
		    yes: function(){
				$.post("${ctx}/wxyqb/saveStockTrasction.htm?userId="+${fuUser.id},null,function(d){
					if(d==-1){
						layer.open({
			 			    content: "您已经是交易员，请不要重复申请！",
			 			    btn: ["确定"],
			 			    shadeClose: false,
			 			    yes: function(){
			 			        layer.closeAll();
			 			    }
			 			});
						return false;
					}
		 			 location.href="${ctx}/wxyqb/businessSuccess.htm";
				})
			}
		})
	}
</script>
</html>
