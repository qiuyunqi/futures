<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－删除开户账户</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style type="text/css">
	body{background: #efefef;}
	.mgrzx{margin:0 auto;}
	.mfzc_mat{ margin: 20px auto;}
	.mgrrmaina{background: #fff;height: 120px;}
	.mgrrmain{background: #fff;}
	.backIndex{margin-bottom:15px !important;}
	.jiaoyibtn{width: 580px;margin: 0 auto;}
	.rgrxx{ padding:20px 0 !important;float:right;width: 80%;font-size: 17px !important;}
	.jiaoyibtn a{width:155px;height:40px;background: #00bbff;color:#fff;text-align: center;line-height: 40px;font-size: 15px;float:left;}
	.backIndex{margin:0 20px 0 125px;}
	.title{margin-bottom:20px;}
	.tit{color:#2db1e1;}
	.title>span{color:#a6a6a6;}
	.stockState{width:200px;float:left;text-align: center;}
</style>
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="hgrzx">
	<div class="title"><span>当前位置：</span><a class="tit" href="${ctx}/user_center/centerIndex.htm">个人中心</a>><a class="tit"  href="${ctx}/user_stock/stockIndex.htm">解套者联盟</a>>账号详情</div>
	<div class="mfzc_mat">
    <div class="mgrrmain mgrrmaina">
      <div class="stockState"> <img src="${fuStockAccount.state==0?'../images/openBtnBig.png':fuStockAccount.state==1?'../images/stopBtnBig.png':stock[5]==2?'../images/aplyOpBig.png':fuStockAccount.state==3?'../images/splyStBig.png':'../images/closeBtnBig.png'}"/></div>
      <div class="rgrxx"  id="grselect">
      <form id="formInfo">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
          <tr>
            <td>开户姓名：${fuStockAccount.openUser }</td>
            <td>开户券商：${fuStockAccount.openEquity }</td>
            <td>营业部：${fuStockAccount.salesDept }</td>
          </tr>
          <tr>
            <td>账户类型 ：${fuStockAccount.accountType==1?'普通账户':fuStockAccount.accountType==2?'融资融券':'信用账户' }</td>
            <td>资金账号：${fuStockAccount.capitalAccount }</td>
            <td></td>
          </tr>
        </table>
        </form>
      </div>
    </div>
  </div>                                                                                                                                                                                                                                                                                             
</div>
<div class="downban"></div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script src="../js_hhr/jquery.nouislider.all.min.js"></script>
<link href="../js_hhr/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.tipsy.js"></script>
<link href="${ctx}/css/tipsy.css" rel="stylesheet" type="text/css" />

