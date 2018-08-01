<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<option value="">--请选择城市--</option>
<c:forEach items="${cityList}" var="city">
	<option <c:if test="${city.id==cityId}">selected="selected"</c:if>  value="${city.id}">${city.cityName}</option>
</c:forEach>
