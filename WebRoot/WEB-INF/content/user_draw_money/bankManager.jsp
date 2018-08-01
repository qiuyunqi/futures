<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－银行卡管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<style>
.hwdAbout{margin-bottom:20px;}
</style>
</head>
<body style="background: #004e99;">
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<div class="contain">
<div class="downban"></div>
<div class="mgrzx">
<%@include file="../common/left.jsp" %>
 <div class="mgrzxr">
    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg" style="float:left;margin-left: -25px;text-indent: 35px;">银行卡管理</span></div>
    <div class="bankCard cenFnt">
    	<c:forEach items="${bankCardList}" var="bank" varStatus="row">
	    <div  class="bankList bordr brRadius" >
	     <div class="bankListIcon"><a href="javascript:void(0);" onclick="delCard('${bank.id}');"><img src="../images_new/clos.png"/></a><a href="javascript:void(0);" onclick="editCard('${bank.id}');"><img src="../images/edit.png"/></a></div>
	   	 <div class="bankListLogo"> 
		   	 <img src="../images_new/bankBg.png"/>
		   	 <span>${bank.bankName}</span>
	   	 </div>
	      <span>${fn:substring(bank.cardNumber,0,4)}<c:forEach begin='4' end='${(fn:length(bank.cardNumber)-5)<0?4:fn:length(bank.cardNumber)-5}'>*</c:forEach>${fn:substring(bank.cardNumber,fn:length(bank.cardNumber)-4,fn:length(bank.cardNumber))}</span>
	    </div>
	    </c:forEach>
	    <div  class="bankList bordr brRadius" >
	   	 <div class="bankListAdd"> 
	   	 	<a href="javascript:void(0);" onclick="addCard()">
	   	 		<img src="../images_new/addBank1.png"/><br/>
		   		 <span>添加银行卡</span>
	   	 	</a>
	   	 </div>
	    </div>
    </div>
    <div class="downban"></div>
    <div class="mgrrmain" id="addBank">
      <div id="one_1" class="display-b">
      <div class="bankTitl" id="tjorxg">添加银行卡</div>
        <div class="rzfbzz">
          <form id="cardForm">
          <table cellpadding="0" cellspacing="0" border="0" width="45%" style="font-size:17px;color:#808080;margin:0 auto;">
            <tr>
              <th width="100">开户名：</th>
              <td>${fuUser.userName}</td>
            </tr>
            <tr>
              <th>银行卡号：</th>
              <td><input name="cardNumber" type="input" value="" class="textput" id="cardNum" placeholder="请输入银行卡卡号"></td>
            </tr>
            <tr>
              <th>确认卡号：</th>
              <td><input name="reCardNumber" type="input" class="textput" id="reCardNum"  placeholder="请确认银行卡卡号" onkeydown="fncKeyStop(event)" onpaste="return false" oncontextmenu = "return false;"></td>
            </tr>
            <c:if test="${empty fuUser.drawPassword}">
            <tr>
              <th>提款密码：</th>
              <td><input name="drawPassword" type="password" class="textput" id="draw" value=""/></td>
            </tr>
            </c:if>
            <tr>
              <th>开户银行：</th>
              <td>
              <div class="fl select">
	            <select name="bankId" id="bank" onchange="$('#bankP').hide();">
	            <option value="">选择银行</option>
	            <c:forEach items="${bankList}" var="bank">
	               <option value="${bank.id}">${bank.bankName}</option>
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
	            <option value="${province.id}">${province.provinceName}</option>
	            </c:if>
	            </c:forEach>
              </select>
              <select name="cityId" id="city" onchange="changeCity(this.value);" style="width:100px;height:30px;vertical-align: middle;"><option value="">请选择城市</option></select>
             </div>
              </td>
            </tr>
            <tr>
              <th>开户支行：</th>
              <td> <input name="bankBranchName" type="input" value="" class="textput" style="height:30px;vertical-align: middle;" placeholder="请输入支行（必填）" id="bankBranchName"></td>
            </tr>
            <tr>
            <tr>
              <th></th>
              <td style="font-size:12px;">例如：张江支行<br/>如果你不确定或忘记开户行，请直接电话向银行咨询。</td>
            </tr>
            <tr>
              <th></th>
              <td>
              <input name="id" id="bankid" value="" type="hidden"/>
              <a href="javascript:void(0);" onclick="saveCard();" class="fl sure">保存</a>
              <a href="${ctx}/user_draw_money/bankManager.htm" class="cancel">取消</a>
              </td>
            </tr>
          </table>
          </form>
        </div>
      </div>
    </div>
 </div>
 <div class="downban"></div>
</div>
<%@include file="../common/footer.jsp" %>
</div>
</body>
</html>
<script>
$(function(){
	$("#addBank").hide();
   var hgt=$(".bankCard").height();
   var cousult=parseInt(($(".bankList").size())/4)+1;
   $(".bankCard").height(hgt*cousult);
});

function addCard(){
	$.post("${ctx}/user_draw_money/bankCardInfo.htm",null,function(d){
		if(d==-1){
			//身份认证
			jAlert("请完成实名认证，认证通过才能添加银行卡","提示",function(){
				$.fancybox.open({
		 			href : '${ctx}/user_center/personNameAjax.htm',
		 			type : 'ajax',
		 			padding : 5 ,
			 	});
		 	});
			return null;
		} 
		$("#addBank").show();
		$("#tjorxg").text("添加银行卡");
		$("#cardNum").val("");
		$("#reCardNum").val("");
		$("#bank").val("");
		changePro("");
		$("#province").val("");
		$("#city").val("");
		$("#bankBranchName").val("");
		$("#bankid").val("");
	});
}
function delCard(id){
	jConfirm("您确定要删除吗？","删除提示",function(res){
		if(res){
			$.get("${ctx}/user_draw_money/delCardAjax.htm?id="+id,null,function(){
				jAlert("银行卡删除成功！","提示",function(){
					location.href = location.href;
		        });
		    });
		}
	});
}
var bankCity;
function editCard(id){
	$("#bankP").hide();
	$.post("${ctx}/user_draw_money/bankCardInfo.htm?id="+id,null,function(data){
		var json=eval('('+data+')');
		$("#addBank").show();
		$("#tjorxg").text("修改银行卡");
		$("#cardNum").val(json.cardNumber);
		$("#reCardNum").val(json.cardNumber);
		$("#bank").val(json.bankNameId);
		changePro(json.bankProvince);
		$("#province").val(json.bankProvince);
		bankCity=json.bankCity;
		$("#bankid").val(json.bankId);
		$("#bankBranchName").val(json.bankBranchName);
	});
}
</script>
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
				var cc=bankCity;
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
    $.post("${ctx}/user_draw_money/saveBankCardAjax.htm",data,function(d){
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
