<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div id="hmenu">
  <div class="hmenu">
    <div class="hlogo">
	    <c:if test="${empty fuUser}">
	    <a href="${ctx}/user_login/userLogin.htm">
	    <img src="../images_hhr/logo.png" title="首页"/>
	    </a>
	    </c:if>
	    <c:if test="${!empty fuUser}">
	    <a href="${ctx}/user_center/centerIndex.htm">
	    <img src="../images_hhr/logo.png" title="个人中心"/>
	    </a>
	    </c:if>
    </div>
    <ul class="hmenuul">
      	<li><a href="${ctx}/user_center/centerIndex.htm" <c:if test="${jsptype=='centerIndex'||jsptype=='programDetail'}">class="hmenuula"</c:if>>个人中心</a></li>
	      <c:if test="${!empty fuUser}">
	       <li><a href="${ctx}/user_program/addPeizi.htm?programType=2" <c:if test="${jsptype=='addPeizi'||jsptype=='addProgramAjax'||jsptype=='programSuccessAjax'}">class="hmenuula"</c:if>">MOM</a></li>
	      </c:if>
      	<c:if test="${!empty fuUser}">
       <%-- <li id="alla">
       	<a id="all"  href="${ctx}/user_stock/stockIndex.htm" <c:if test="${jsptype=='stockIndex'}">class="hmenuula"</c:if>>解套者联盟</a>
       	<!-- 二级菜单 -->
       	<div id="newDi">
       		<a href="${ctx}/index_guide/stockExpand.htm">产品介绍</a>
       		<a href="${ctx}/user_stock/addUserInfo.htm">预约指导</a>
       		<a href="${ctx}/user_stock/addStock.htm">添加账户</a>
       		<a href="${ctx}/index_guide/tCollege.htm">T学院</a>
       		<a href="${ctx}/index_guide/newGuide.htm">联盟FAQ</a>
       	</div>
       </li> --%>
      </c:if>
      <li class="smallhb"><a href="${ctx}/index_guide/qhGame.htm" <c:if test="${jsptype=='qhGame'}">class="hmenuula"</c:if>>"小合杯"</a></li>
      <li><a href="${ctx}/index_options/optionSolicitation.htm">涨跌赢</a></li>
      <%-- <li><a href="${ctx}/user_stock/stockOpenAccount.htm" <c:if test="${jsptype=='stockOpenAccount'}">class="hmenuula"</c:if>>股票开户</a></li> --%>
      <li><a href="${ctx}/user_message/messageInfo.htm" <c:if test="${jsptype=='messageInfo'}">class="hmenuula"</c:if>>我的留言</a></li>
    </ul>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#all").mouseover(function(){
		$("#newDi").show();
	});
	$("#newDi").mouseover(function(){
		$("#newDi").show();
	});
	$("#alla").mouseout(function(){
		$("#newDi").hide();
	});
});
</script>