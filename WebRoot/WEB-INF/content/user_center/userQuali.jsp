<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资格认证</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

    <div class="mgrzx">
    <%@include file="../common/left.jsp" %>
    <div class="mgrzxr">
    <div class="h20"></div>
    <div class="mgrrul">
      <ul>
        <li id="one3" onclick="setTab(this);" class="mgractive">资格认证</li>
      </ul>
    </div>
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzczjl">
          <div class="rzfbb"><span class="ny_bt_cont">资格证书（${fn:length(qualiUserList)}份）</span><a href="${ctx}/user_center/userQualiInfo.htm" class="fr query" style="width:100px;background:#2db1e1;color:white;">添加资格证</a></div>
          <form id="drawForm">
          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
            <tr>
            <td height="35" style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="99">真实姓名</td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="97">资格证号</td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="97">证件类型</td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="110">认证状态</td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="110">操作</td>
          </tr>
          <c:forEach items="${qualiUserList}" var="quali" varStatus="row">
	          <tr >
	            <td height="35" align="center" <c:if test="${row.index==fn:length(qualiUserList)-1}">style="border-bottom:none;"</c:if>>${quali.userName}</td>
	            <td align="center" height="35" style=" line-height:35px;<c:if test="${row.index==fn:length(qualiUserList)-1}">border-bottom:none;</c:if>">${quali.qualiNum}</td>
   	            <td align="center" height="35" style=" line-height:35px;<c:if test="${row.index==fn:length(qualiUserList)-1}">border-bottom:none;</c:if>">${quali.type==1?'期货从业资格':quali.type==2?'证券从业资格':'信息有误'}</td>
	            <td align="center" height="35" style=" line-height:35px;<c:if test="${row.index==fn:length(qualiUserList)-1}">border-bottom:none;</c:if>">${quali.isChecked==0?'未认证':quali.isChecked==1?'待认证':quali.isChecked==2?'已认证':'信息有误'}</td>
	            <td align="center" <c:if test="${row.index==fn:length(qualiUserList)-1}">style="border-bottom:none;"</c:if>>
	            <a href="${ctx}/user_center/userQualiInfo.htm?id=${quali.id}" class=" gzsm " style="color:#2db1e1;">修改</a>    |   <a href="javascript:void(0);" onclick="delQualiAjax('${quali.id}');"  style="color:#2db1e1;"  style="color:#2db1e1;" class=" gzsm ">删除</a>
	            </td>
	          </tr>
          </c:forEach>
          </table>
          <c:if test="${empty qualiUserList}">
           <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
          </c:if>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
function delQualiAjax(id){
	jConfirm("您确定要删除吗？","删除提示",function(res){
		if(res){
			$.get("${ctx}/user_center/delQualiAjax.htm?id="+id,null,function(){
				jAlert("资格证删除成功！","提示",function(){
					location.href = location.href;
		        });
		    });
		}
	});
}
</script>
