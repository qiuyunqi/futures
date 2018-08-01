<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
	<form id="drawForm">
		<div class=" fucheng_title"><span>提取利润</span><div class="clr"></div></div>
   		<!-- 时间滑块 begin-->
	    <div class="timeWrapper">
	       <div id="yincdiv" class="yincdiv">
	       		<div id="fubiao" class="fubiao"><span>当前时间</span><img src="../images_hhr/xiabiao.png"/></div>
	       </div>
	       <div class="timeKd">
	       		<a class="timeOne" href="javascript:void(0)">1</a>
	       		<a class="timeTwo" href="javascript:void(0)">可提盈</a>
	       		<a class="timeThree" href="javascript:void(0)">风控时间</a>
	       		<a class="timeFour" href="javascript:void(0)">可提盈</a>
	       		<a class="timeFive" href="javascript:void(0)">1</a>
	       </div>
	       <div class="timeTxt">
	       		<span>00:00:00</span>
	       		<span class="timeTxt-spt">09:15:00</span>
	       		<span class="timeTxt-spthr">14:50:00</span>
	       		<span class="timeTxt-spfo">15:16:00</span>
	       		<span class="timeTxt-spfi">16:00:00</span>
	       		<span class="timeTxt-spsi">24:00:00</span>
	       </div>
	    </div>
   		<div class=" ktqlr" style="color:#990000;">
    		<span class="b1">截止&nbsp;<fmt:formatDate value="<%=new Date()%>" pattern="HH:mm:ss"/>，</span>
    		<span class="b1">您最大可提盈利&nbsp;¥<fmt:formatNumber value='${empty benefit?0.00:benefit}' pattern='#,###,##0.00'/>元</span>
   		</div>
        <div class="form marg0" style="margin:0 0 3 0px 50px;">
       	   <div class="fl form_font lh">提取利润：</div>
           <div class="fl input tqlr"><input id="money" name="money" type="text" onkeyup="changeDraw(this.value)"></div>
           <div class="fl lh0">元</div>
           <div class="clr"></div>
	    </div>
        <div style="text-align:center; margin:30px 0 0px 20px;">
        <a id="sure" href="javascript:void(0);" onclick="saveDraw();" class="queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
        </div>
        <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
	</form>
	<div class=" zhuyi_0">
       	<p class="zhuyi">注意:</p>
       	<p>你可以提取的利润计算方式为可提取利润=账户动态权益 – 初始账户权益</p>
       	<p>操作方案盈利状态，可按规则提取盈利至网站账户。</p>
		<p>若方案涉及到增减配，系统将会根据增减配内容综合判断用户的提盈申请。</p>
		<p>用户的提现申请，通过审核后，系统将在下一个工作日将相应资金转入用户的银行卡。</p>
       	<div class="cjrule">
       		<p class="sub_tit"><em class="list_icon"></em><h4>提盈规则</h4></p>
       		<p class="cjrulep">基于风控安全的考虑，用户提取盈利的金额须满足下列规则。</p>
       		<ol class="list-item">
       			<li>1、该操作方案，交易账户无持仓的情况：<br>&nbsp&nbsp&nbsp&nbsp&nbsp<span>提盈金额</span><=（动态权益-融资资金-客户保证金）;</li>
       			<li>2、该操作方案，交易账户有持仓的情况：
       				<ul >
       					<li class="ultype">盘中时间段（9:15～14:50）
       						<ol>
       							<li><span class="sred">提盈金额</span><=（动态权益-融资资金-客户保证金）;</li>
       							<li><span class="sred">提盈金额</span><=可用资金；</li>
       							<li>持仓占用保证金<=（客户保证金＋融资资金）＊80％</li>
       						</ol>
       					</li>
       					<li class="ultype">盘后时间段（15:16～16:00）
       						<ol>
       							<li>1) 若，客户保证金－持仓占用保证金／隔夜倍数<=0：
       							
       							<p><span class="sred">提盈金额</span><=（动态权益－融资资金－客户保证金）－（持仓占用保证金／隔夜倍数－客户保证金）；</p></li>
       							<li>2) 若，客户保证金－持仓占用保证金／隔夜倍数>0：
       							<p><span class="sred">提盈金额</span><=动态权益－融资资金－客户保证金；</p></li>
       						</ol>
       					</li>
       					<li class="ultype">其他时间段
       						<ol>
       							<li class="ultype-ol">其他时间段的提盈申请，系统将会在下一个提盈时间段进行自动处理。</li>
       						</ol>
       					</li>
       				</ul>
       			</li>
       		</ol>
       		 <input class="hidval" type="hidden" value="0"/>
       	</div>
       	<div class="cjtime">
       		<p class="sub_tit"><em class="list_icont"></em><h4>提盈时间</h4></p>
       		<ol class="list-itemTW">
       			<li>1、盘中提盈时间段（9:15～14:50），即时提盈至网站账户；</li>
       			<li>2、盘后提盈时间段（15:16～16:00），即时提盈至网站账户；</li>
       			<li>3、其他时间，用户可提出申请，该申请会在下一提盈时间段生效。</li>
       		</ol>
       		 <input class="hidvals" type="hidden" value="0"/>
       	</div>
    </div> 
</div>


<script>
function ready(){
		if(${benefit==0}){
			$("#money").attr("disabled","disabled");
		}else{
			$("#money").removeAttr("disabled");
		}
		
		var now = new Date();
	    var hours = now.getHours();
	    var minutes = now.getMinutes();
	    var mm=parseInt(hours)*60+parseInt(minutes);
		var marginleft=0;
		
        var strNow = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
        var date0 = strNow+" 00:00:01";
		date0 = new Date(Date.parse(date0.replace(/-/g, "/")));
		var date1 = strNow+" 09:15:00";
		date1 = new Date(Date.parse(date1.replace(/-/g, "/")));
		var date2 = strNow+" 14:50:00";
		date2 = new Date(Date.parse(date2.replace(/-/g, "/")));
		var date3 = strNow+" 15:16:00";
		date3 = new Date(Date.parse(date3.replace(/-/g, "/")));
		var date4 = strNow+" 16:00:00";
		date4 = new Date(Date.parse(date4.replace(/-/g, "/")));
		var date5 = strNow+" 24:00:00";
		date5 = new Date(Date.parse(date5.replace(/-/g, "/")));
		if(now>=date0&&now<=date1){
			marginleft=(mm/555*100)+"px";
		}else if(now>date1&&now<=date2){
			marginleft=((mm-555)/335*100+100)+"px";
		}else if(now>date2&&now<=date3){
			marginleft=((mm-890)/26*58+200)+"px"; 
		}else if(now>date3&&now<=date4){
			marginleft=((mm-916)/44*70+258)+"px"; 
		}else if(now>date4&&now<=date5){
			marginleft=((mm-960)/480*122+328)+"px"; 
		}
		$("#fubiao").css("margin-left",marginleft);
	}	

 ready();




//改变金额的文本框的值
function changeDraw(drawMoney){
    if(isNaN(drawMoney)){
		jAlert("输入的金额只能是数字！","提示",function(){
			$("#money").val("");
			$("#money").focus();
        });
		return false;
	}
	if(drawMoney>${empty benefit?0.00:benefit}){
		$("#money").val(${benefit});
		return false;
	}
	if(drawMoney!=null&&drawMoney!=''&&drawMoney<0){
		$("#money").val(0);
		return false;
	}
}


	$("#load").hide();
	function saveDraw(){
		if(${benefit==0}){
			jAlert("当前可提取利润为0元！","提示",function(){
				location.href=location.href;
        	});
        	return false;
		}
	    $.alerts.okButton="确定";
		$.alerts.cancelButton="返回";
	    jConfirm("确定提取利润？","操作提示",function(res){
			if(res){
				$("#load").show();
				$("#sure").attr("onclick", "");
				var data=$("#drawForm").serialize();
				$.post("${ctx}/user_program/saveDrawMoneyAjax.htm?id=${id}&drawType=${drawType}",data,function(d){
				    var json=eval("("+d+")");
					if(d==-2){
						$.alerts.okButton='确定';
						jAlert("请输入您要提取的金额！","提示",function(){
						    location.href=location.href;
						});
						return false;
					}
					if(d==-3){
						$.alerts.okButton='确定';
						jAlert("对不起，您还有待处理的提盈任务，暂时不能提取盈利！","提示",function(){
							location.href=location.href;
						});
						return false;
					}
					if(d==-4){
						$.alerts.okButton='确定';
						jAlert("对不起，提取的金额不能超过最大可提盈金额，请重新打开提取利润窗口！","提示",function(){
							location.href=location.href;
						});
						return false;
					}
					if(json.result=="非提盈时间"){
						$.alerts.okButton="确定";
						jAlert("非提盈时间，提盈申请失败，提盈编号："+json.id+"，提盈执行失败，您的申请时间不在规定时间，导致您的方案无法正常提盈。提盈申请时间为：工作日9:15~14:50/15:16~16:00，请在规定时间进行提盈申请。如有任何疑问，请拨打客服咨询电话：010-53320537。","提示",function(){
							location.href=location.href;
						});
						return false;
					}
					$.alerts.okButton="确定";
					jAlert("提取利润申请成功，请刷新网页等待处理结果！","提示",function(){
						location.href=location.href;
					});
				    /* $.fancybox.open({
			 			href : '${ctx}/user_program/tips.htm?profitsId='+json.result,
			 			type : 'ajax',
			 			padding : 5 ,
			 			scrolling:'no',
			 		}); */
				});
			}
		});
	}





	$(document).ready(function(){
		$(".list_icon").bind("click",function(){
            if($(".hidval").val()==0){
               $(".list-item").show();
               $(".list_icon").addClass("list_icon lisactive");
               $(".hidval").val(1);
            }else{
               $(".list-item").hide();
               $(".list_icon").removeClass("lisactive");
               $(".hidval").val(0);
            }
         });
		
		$(".list_icont").bind("click",function(){
            if($(".hidvals").val()==0){
               $(".list-itemTW").show();
               $(".list_icont").addClass("list_icont lisactivet");
               $(".hidvals").val(1);
            }else{
               $(".list-itemTW").hide();
               $(".list_icont").removeClass("lisactivet");
               $(".hidvals").val(0);
            }
         });
	});
	
//禁用回车事件
document.onkeydown = function(event) {  
    var target, code, tag;  
    if (!event) {  
        event = window.event; //针对ie浏览器  
        target = event.srcElement;  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "TEXTAREA") { return true; }  
            else { return false; }  
        }  
    }else {  
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "INPUT") { return false; }  
            else { return true; }   
       }  
    }    
};  	
</script>