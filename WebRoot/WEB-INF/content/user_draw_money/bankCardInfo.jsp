<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－添加银行卡</title>
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
        <li id="one3" onclick="setTab(this);" class="mgractive"><a href="${ctx}/user_draw_money/bankManager.htm">银行卡管理</a></li>
      </ul>
    </div>
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzfbzz">
          <form id="cardForm">
          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
            <tr>
              <th width="100">开户名：</th>
              <td>${fuUser.userName}</td>
            </tr>
            <tr>
              <th>开户银行：</th>
              <td>
              <div class="fl select">
	            <select name="bankId" id="bank" onchange="$('#bankP').hide();">
	            <option value="">选择银行</option>
	            <c:forEach items="${bankList}" var="bank">
	               <option value="${bank.id}"  <c:if test="${card.bankNameId==bank.id}">selected</c:if>>${bank.bankName}</option>
	            </c:forEach>
	            </select>
             </div>
             <p class="fl" style="color:red;line-height:32px;display:none;" id="bankP">请先选择开户银行</p>
             </td>
            </tr>
            <tr>
              <th>开户地址：</th>
              <td>
              <select name="provinceId" id="province" onchange="changePro(this.value);" style="width:100px;height:30px;vertical-align: middle;">
	            <option value="">请选择省份</option>
	             <c:forEach items="${provinceList}" var="province">
	             <c:if test="${province.id!=29}">
	            <option value="${province.id}" <c:if test="${card.bankProvince==province.id}">selected</c:if>>${province.provinceName}</option>
	            </c:if>
	            </c:forEach>
              </select>
              <select name="cityId" id="city" onchange="changeCity(this.value);" style="width:100px;height:30px;vertical-align: middle;"><option value="">请选择城市</option></select>
              <input name="bankBranchName" type="input" value="${card.bankBranchName}" style="width:350px;height:30px;vertical-align: middle;" placeholder="请输入网点支行（必填）" id="bankBranchName"></div>
              </td>
            </tr>
            <tr>
              <th>银行卡号：</th>
              <td><input name="cardNumber" type="input" value="${card.cardNumber}" class="textput" id="cardNum"></td>
            </tr>
            <tr>
              <th>确认卡号：</th>
              <td><input name="reCardNumber" type="input" class="textput" id="reCardNum" onkeydown="fncKeyStop(event)" onpaste="return false" oncontextmenu = "return false;"></td>
            </tr>
            <c:if test="${empty fuUser.drawPassword}">
            <tr>
              <th>提款密码：</th>
              <td><input name="drawPassword" type="password" class="textput" id="draw" value=""/></td>
            </tr>
            </c:if>
            <tr>
              <th></th>
              <td>
              <a href="javascript:void(0);" onclick="saveCard();" class="sure">保存</a>
              <a href="${ctx}/user_draw_money/bankManager.htm" class="cancel">取消</a>
              </td>
            </tr>
          </table>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
if(${!empty id}){
	$(function(){
		$.get("${ctx}/user_sys/allProvinceAjax.htm",null,function(d){
			if(d!=null||d!=''){
				var json = eval('(' + d + ')'); 
				for(var i=0;i<json.length;i++){
					if(json[i].id!=29){
						var p='${card.bankProvince}';
						if(p==json[i].id){
							var info=$("<option selected='selected' value='"+json[i].id+"' >"+json[i].name+"</option>");
							$("#province").append(info);
							changePro(p);
						}else{
							var info=$("<option  value='"+json[i].id+"' >"+json[i].name+"</option>");
							$("#province").append(info);
						}
					}
				}
			}
		});
	});
}

function changePro(proId){
	var bId = $("#bank option:selected").val();
	if(bId==null||bId==''){
        $('#bankP').show();
        $("#province").html("<option value=''>--请选择省份--</option>");
		$.get("${ctx}/user_sys/allProvinceAjax.htm",null,function(d){
			if(d!=null||d!=''){
				var json = eval('(' + d + ')'); 
				for(var i=0;i<json.length;i++){
					    if(json[i].id!=29){
							var info=$("<option  value='"+json[i].id+"' >"+json[i].name+"</option>");
							$("#province").append(info);
					    }
				}
			}
		});
        return ;
	}
	if(proId==null||proId==''){
		return;
	}
	//级联市
	$.get("${ctx}/user_sys/findCityByProvinceIdAjax.htm?proId="+proId,null,function(d){
		if(d!=null||d!=''){
			$("#city").empty();
			$("#city").val('');
<%--			$("#branch").empty();--%>
<%--			$("#branch").val('');--%>
			var json = eval('(' + d + ')'); 
			if(!(proId==26||proId==36||proId==37||proId==40||proId==57)){
				$("#city").append($("<option value=''>--请选择城市--</option>"));
			}
			for(var i=0;i<json.length;i++){
				var cc='${card.bankCity}';
				if(cc==json[i].id){
					var info=$("<option selected='selected' value='"+json[i].id+"' >"+json[i].name+"</option>")
					$("#city").append(info);
					changeCity(cc);
				}else{
					var info=$("<option value='"+json[i].id+"' >"+json[i].name+"</option>")
					$("#city").append(info);
					if(proId==26||proId==36||proId==37||proId==40||proId==57){
						changeCity(json[i].id);
					}
				}
			}
		}
	});
}

function changeCity(cityId){
	var bankId = $("#bank option:selected").val();
	//级联支行
	$.get("${ctx}/user_sys/findBranchByCityIdAjax.htm?bankId="+bankId+"&cityId="+cityId,null,function(d){
		if(d!=null||d!=''){
			$("#branch").empty();
			$("#branch").val('');
			var json = eval('(' + d + ')'); 
 			if(json.length==0){
 				var info=$("<option selected='selected' value=''>暂无营业网点(可不选)</option>")
 				$("#branch").append(info);
 	 		}else{
 	 			$("#branch").append("<option value=''>选择营业网点(可不选)</option>");
				for(var i=0;i<json.length;i++){
					var bra ='${card.bankBranch}';
					if(bra==json[i].id){
						var info=$("<option selected='selected' value='"+json[i].id+"' >"+json[i].name+"</option>")
						$("#branch").append(info);
					}else{
						var info=$("<option value='"+json[i].id+"' >"+json[i].name+"</option>")
						$("#branch").append(info);
					}
				}
 	 		}
		}
	});
}

function saveCard(){
	//验证卡号
    var reg = /^[1-9][0-9]{14,}$/;       
    if (!reg.test($("#cardNum").val())){
        $.alerts.okButton='确定';
		jAlert('输入的卡号格式不正确，非0开头的最少15位数字','提示',function(){
			$("#cardNum").val("");
		    $("#cardNum").focus();
		});
		return false;
    }   
    var data = $("#cardForm").serialize();
    $.post("${ctx}/user_draw_money/saveBankCardAjax.htm?id=${id}",data,function(d){
        if(d==-2){
            sureInfo("确定","请选择开户银行","提示");
            return null;
        }
        if(d==-3){
        	sureInfo("确定","请选择省份","提示");
            return null;
        }
        if(d==-4){
        	sureInfo("确定","请选择市","提示");
            return null;
        }
        if(d==-5){
        	jAlert("请输入营业网点支行","提示",function(){
  				$("#bankBranchName").focus();
            });
            return null;
        }
        if(d==-6){
        	jAlert("请输入银行卡号","提示",function(){
  				$("#cardNum").focus();
            });
            return null;
        }
        if(d==-7){
        	jAlert("两次输入的卡号不一致","提示",function(){
  				$("#reCardNum").focus();
            });
            return null;
        }   
        if(d==-8){
        	sureInfo("确定","该银行卡已经绑定","提示");
            return null;
        }
        if(d==-9){
        	jAlert("请设置提款密码","提示",function(){
  				$("#draw").focus();
            });
            return null;
        }
        jAlert("银行卡保存成功！","提示",function(){
        	location.href = "${ctx}/user_draw_money/bankManager.htm";
        });
  });
}

function fncKeyStop(evt){//禁用文本框粘帖
    if(!window.event){
        var keycode = evt.keyCode; 
        var key = String.fromCharCode(keycode).toLowerCase();
        if(evt.ctrlKey && key == "v"){
          evt.preventDefault(); 
          evt.stopPropagation();
        }
    }
}
</script>
