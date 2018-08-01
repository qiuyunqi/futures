<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－股票开户</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
body{background: #efefef;}
#hbannera{background: rgba(0, 0, 0, 0) url("../images/kaihuBg.png") no-repeat center;height:480px;}
#hbannera .hbanner{background: rgba(0, 0, 0, 0) url("../images/kaihuBg.png") no-repeat center;height:480px;}
</style>
</head>
<body>
<c:set value="3" var="ipg"></c:set>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
<div id="hbannera">
  <div class="hbanner"></div>
</div>
<div class=" matter">
    <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">段文武</i>
    		<img class="kaihuImg" src="../images/dwwName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th>从业年限：8年</th>
			      	<th width="310">从业资格证号：S0340113030068</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">华中科技大学金融学士学位，东莞证券明星客户经理，从事证券行业8年的时间，全方位服务（股票操作技术的探讨，经验的分享，股指，期权，基金，新三板账户开通，新三板主板上市，公司融资定增项目……），股票7*24小时指导手机低佣开户，3分钟搞定属于自己的定制账户（定制一个好的客户经理，定制一个好的操作思路……）。</div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>18310150480</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
    <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">李芳 </i>
    		<img class="kaihuImg" src="../images/lfName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th style="visibility: hidden;">从业年限：8年</th>
			      	<th width="310">从业资格证号：S0340114080067</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">专修金融与证券，能吃苦耐劳，有着良好的人际交往与沟通能力。掌握了较强的专业知识并把理论运用到了实践中，本人现在已是东莞证券的明星客户经理，可以做的业务有股票，基金，股指，期货，新三板账户开通，新三板、主版上市，公司融资定增项目，还可以为量身定做适合您的理财产品，没有做不到的只有您想不到的，欢迎来电咨询。</div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>18810526716</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">王盾 </i>
    		<img class="kaihuImg" src="../images/wdName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th>从业年限：4年</th>
			      	<th width="310">从业资格证号：S0340113040079</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">本人在证券行业从事了4年，现在东莞证券。有丰富专业的金融投资经验，股票推荐方面2012年至今无一亏损，对于机构庄家的操作手法甚是了解。我这可做股票开户，各种金融理财产品，公司融资或上市〔主板，新三板〕。</div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>15210536485</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">马婷婷 </i>
    		<img class="kaihuImg" src="../images/mttName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th>从业年限：3年</th>
			      	<th width="310">从业资格证号：S0340113010058</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">主修投资与管理专业，从事证券工作三年，现任东莞证券高级客户经理，具备专业的股票分析以及投资组合管理能力。如果您有股票，基金，理财，新三板等方面的困扰，我是您最佳的选择，为您打造一个限量版的金融能者。</div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>13520926547</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">秦毫 </i>
    		<img class="kaihuImg" src="../images/qhName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th>从业年限：5年</th>
			      	<th width="310">从业资格证号：S0340415060018</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">2013年7月毕业于吉林财经大学，本科，专业金融学，2010年取得证券从业资格证，2013年取得期货从业资格证；毕业后毅然选择证券行业，从事经纪业务，喜欢与客户沟通交流，并擅于客户关系开发与维护，目前已经积累一定客户规模。 </div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>13653975385</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">杨洋 </i>
    		<img class="kaihuImg" src="../images/yyName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th style="visibility: hidden;">从业年限：5年</th>
			      	<th width="310">从业资格证号：S0340113080036</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">毕业于中央财经大学金融专业，热爱并致力于证券业，现任职东莞证券金牌客户经理，（名下托管客户三千余人，资产1.5亿，客户满意度高），对客户真诚热情是我服务的一贯宗旨，最终实现托管客户的财富增值！主要负责：证券账户开通 、股票交易的指导及意见 、基金债券、资管产品、新三板机构业务！ 财富热线：15726606758。 </div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>15726606758</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">李丹 </i>
    		<img class="kaihuImg" src="../images/ldName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th>从业年限：5年</th>
			      	<th width="310">从业资格证号：S0340411212007</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">东莞证券明星客户经理，从事证券金融行业5年，股票7*24小时指导手机开户（股指，期权，基金，新三板账户开通，新三板主板上市，公司融资定增项目……），您的选择，共同创造更多的财富！ </div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>18610966800</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
     <div class="hgrzx">
    	<div class="stoa-list">
    		<i class="kaihuName">陈天琪 </i>
    		<img class="kaihuImg" src="../images/ctqName.png"/>
    		<div class="stwdlod-txt">
	    		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			      	<th>证券公司：东莞证券</th>
			      	<th style="visibility: hidden;">从业年限：5年</th>
			      	<th width="310">从业资格证号：S0340115040102</th>
			      </tr>
			      <tr>
			        <td colspan="3">
			         	<div class="khTxt">我的性格外向，为人坦率、热情、讲求原则；处事乐观、专心、细致、头脑清醒；富有责任心、乐于助人。 
我还是一个正直忠诚、勤奋求实的人，会不断追求人格的自我完善；明显的特点是乐观自信、温和开朗、因此，我人际关系和谐，适应环境能力较强。正因为这些增强了我的实干精神。在生活中无论你有什么困难我都很乐意去帮助你；在工作上，如果你有股票开户，理财以及新三板上市等等金融方面的需求，都可以来找我。</div>
			         	<c:if test="${empty fuUser}"><div class="yykh"><a href="javascript:void(0);" onclick="lgInfo(1);">预约开户</a></div></c:if><c:if test="${!empty fuUser}"><div class="khNum"><a><i class="callIcon"></i><span>17710222450</span></a></div></c:if>
			        </td>
			      </tr>
			    </table>
    		</div>
    	</div>
    </div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript">

function lgInfo(flag){
	$.fancybox.open({
		href : '${ctx}/user_login/userLoginAjax.htm?flag='+flag,
		type : 'ajax',
		padding : 0 ,
		scrolling:'no',
	});
}
</script>
