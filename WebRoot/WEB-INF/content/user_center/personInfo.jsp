<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－个人信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
</head>
<body>
<c:set value="6" var="pg"></c:set>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="mgrzx">
  <%@include file="../common/left.jsp" %>
  <div class="mgrzxr">
    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg">个人信息</span></div>
    <div class="mgrrmain mgrrmaina">
      <div class="rgrxx"  id="grselect">
      <form id="formInfo">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
          <tr>
            <th width="80">用户名：</th>
            <td>${fuUser.accountName}</td>
          </tr>
          <tr>
            <th width="80">邀请码：</th>
            <td>${fuUser.invitationCode}</td>
          </tr>
          <tr>
            <th>性别：</th>
            <td>
               <input <c:if test="${fuUser.gender==0}">checked="checked"</c:if> type="radio" name="gender" value="0"/><span class="marr20">男</span>
               <input <c:if test="${fuUser.gender==1}">checked="checked"</c:if> type="radio" name="gender" value="1"/><span class="marr20">女</span>
            </td>
          </tr>
          <tr>
            <th>婚姻状况：</th>
            <td>
              <input name="isMarriage" <c:if test="${fuUser.isMarriage==0}">checked="checked"</c:if> type="radio" value="0"/>
              <span class="marr20">暂不填写</span>
              <input name="isMarriage" <c:if test="${fuUser.isMarriage==1}">checked="checked"</c:if> type="radio" value="1"/>
              <span class="marr20">未婚</span>
              <input name="isMarriage" <c:if test="${fuUser.isMarriage==2}">checked="checked"</c:if> type="radio" value="2"/>
              <span class="marr20">已婚</span>
              <input name="isMarriage" <c:if test="${fuUser.isMarriage==3}">checked="checked"</c:if> type="radio" value="3"/>
              <span class="marr20">离异</span>
              <input name="isMarriage" <c:if test="${fuUser.isMarriage==4}">checked="checked"</c:if> type="radio" value="4"/>
              <span class="marr20">丧偶</span>
           </td>
          </tr>
          <tr>
            <th>居住地址：</th>
            <td>
              <select name="provinceId" id="provinceId" onchange="selectCity(this.value);">
                <option value="">--请选择省份--</option>
                <c:forEach items="${provinceList}" var="pro" varStatus="row">
            		<option <c:if test="${pro.id==fuUser.provinceId}">selected="selected"</c:if>  value="${pro.id}">${pro.provinceName}</option>
            	</c:forEach>
              </select>
              <select name="cityId" id="cityId">
                <option value="">--请选择城市--</option>
                <c:forEach items="${cityList}" var="city" varStatus="row">
            		<option <c:if test="${city.id==fuUser.cityId}">selected="selected"</c:if>  value="${city.id}">${city.cityName}</option>
            	</c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <th></th>
            <td><input name="liveAddress" id="liveAddress" value="${fuUser.liveAddress}" type="text" placeholder="请填写详细信息" class="textput" style="width:248px" /></td>
          </tr>
          <tr>
            <th>最高学历：</th>
            <td><select name="maxDegree" id="maxDegree">
                <option value="">--请选择学历--</option>
                <option <c:if test="${fuUser.maxDegree==0}">selected="selected"</c:if> value="0">初中及以下</option>
	            <option <c:if test="${fuUser.maxDegree==1}">selected="selected"</c:if> value="1">高中</option>
	            <option <c:if test="${fuUser.maxDegree==2}">selected="selected"</c:if> value="2">大专</option>
	            <option <c:if test="${fuUser.maxDegree==3}">selected="selected"</c:if> value="3">本科</option>
	            <option <c:if test="${fuUser.maxDegree==4}">selected="selected"</c:if> value="4">本科以上</option>
              </select></td>
          </tr>
          <tr>
            <th width="100">合伙人类别：</th>
            <td>
               <c:if test="${fuUser.hhrType==0}">终端</c:if>
               <c:if test="${fuUser.hhrType==1}">渠道</c:if>
            </td>
          </tr>
          <c:if test="${empty fuUser.nickName}">
          <tr>
            <th>昵称：</th>
            <td><input name="nickName" id="nick" type="text" placeholder="请填写昵称（设置后将不能修改）" class="textput"/></td>
          </tr>
          </c:if>
          <c:if test="${!empty fuUser.nickName}">
          <tr>
            <th>昵称：</th>
            <td>${fuUser.nickName}</td>
          </tr>
          </c:if>
          <tr>
            <th>所属行业：</th>
            <td><input name="businessType" id="businessType" value="${fuUser.businessType}" type="text" placeholder="请填写所属行业" class="textput" /></td>
          </tr>
          <tr>
            <th>职业：</th>
            <td><input name="positionName" id="positionName" value="${fuUser.positionName}" type="text" placeholder="请填写职位" class="textput" /></td>
          </tr>
          <tr>
            <th>月收入：</th>
            <td><input name="salary" id="salary" value="${fuUser.salary}" type="text" placeholder="请填写月收入（正整数）" class="textput" /></td>
          </tr>
          <tr>
            <th></th>
            <td><a href="javascript:void(0);" onclick="saveInfo();" class="bcbut">保存</a><a href="${ctx}/user_center/centerIndex.htm" class="qxbut">取消</a></td>
          </tr>
        </table>
        </form>
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
function selectCity(provinceId){
	$.post("${ctx}/user_center/selectCityAjax.htm?provinceId="+provinceId,null,function(d){
		$("#cityId").html(d);
	});	
}
function saveInfo(){
	//验证合伙人类别
	/* var hhrType = $("#hhrType").val();
	if(hhrType == "3"){
		$.alerts.okButton='确定';
		jAlert('请选择合伙人类别！','提示',function(){
			$("#hhrType").focus();
		});
		return false;
	} */
	
    //验证昵称
    var reg = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{1,13}[a-zA-Z0-9\u4e00-\u9fa5]$/;       
    if (!reg.test($("#nick").val())){
        $.alerts.okButton='确定';
		jAlert('输入的昵称格式不正确，3-15位字母、中文（占两位）、数字或下划线，且不能以下划线和数字开头，不能以下划线结尾！','提示',function(){
			$("#nick").val("");
		    $("#nick").focus();
		});
		return false;
    }   
    //验证收入 
	var sr=$("#salary").val();
	if(isNaN(sr)){
		$.alerts.okButton='确定';
		jAlert('月收入只能填入数字！','提示',function(){
			$("#salary").focus();
		});
		return false;
	}
	var data=$("#formInfo").serialize();
	$.post("${ctx}/user_center/savePersonInfoAjax.htm",data,function(d){
		var json=eval("("+d+")");
		if(d==-1){
			jAlert('昵称已存在，请更换！','提示',function(){
				$("#nickName").focus();
			});
			return false;
		}
		if(d==1){
			$.alerts.okButton='确定';
			jAlert("个人信息保存成功！","提示",function(){
                location.href=location.href;
			});
		}
	});
}
</script>
