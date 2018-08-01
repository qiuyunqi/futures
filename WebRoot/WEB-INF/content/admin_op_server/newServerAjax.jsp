<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}服务器</b>
        <div class="fl"></div>
    </div>
        <form id="adminForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器名称：</div>
            <div class="fl input"><input name="serverName"  type="text" placeholder="服务器名称"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">真实名称：</div>
            <div class="fl input"><input name="serverRealName"  type="text" placeholder="服务器真实名称"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器优先级：</div>
            <div class="fl input"><input name="serverPriority"  type="text" value=""></div>
            <div class="clr"></div>
        </div>    
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器IP：</div>
            <div class="fl input"><input name="serverIp"  type="text"  placeholder="服务器IP,如192.168.1.1"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器端口号：</div>
            <div class="fl input"><input name="portNumber"  type="text"  placeholder="服务器端口,如80"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库IP：</div>
            <div class="fl input"><input name="dbIp"  type="text"  placeholder="数据库ip地址"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库端口：</div>
            <div class="fl input"><input name="dbPort"  type="text"  placeholder="数据库端口"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库名：</div>
            <div class="fl input"><input name="dbName"  type="text"  placeholder="数据库名称"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户名：</div>
            <div class="fl input"><input name="dbUsername"  type="text"  placeholder="数据库用户名"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">密码：</div>
            <div class="fl input"><input name="dbPassword"  type="password"  placeholder="数据库密码"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库只读账号：</div>
            <div class="fl input"><input name="dbReadUserName"  type="text"  placeholder="数据库用户名" value="${fuServer.dbReadUserName }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库只读账号：</div>
            <div class="fl input"><input name="dbReadPassWord"  type="password"  placeholder="数据库密码" value="${fuServer.dbReadPassWord }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">是否为模拟：</div>
            <div class="fl input">
            <select id="isSimTrader" name="isSimTrader">
	            <option value="0">真实</option>
	            <option value="1">模拟</option>
	        </select>
            </div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器金额：</div>
            <div class="fl input"><input name="serverMoney"  type="text" placeholder="服务器金额"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">客户端类型：</div>
            <div class="fl input">
            <select id="clientType" name="clientType">
	            <option value="1">众期金融资产管理平台</option>
	            <option value="2">博易大师－鑫管家版</option>
	            <option value="3">博易大师－金牛版</option>
	        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">期货公司：</div>
            <div class="fl input"><input name="futureCompany"  type="text"  placeholder="期货公司"></div>
            <div class="clr"></div>
        </div>
   		<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开始序号：</div>
            <div class="fl input"><input name="startNumber"  type="text" placeholder="开始序号"></div>
            <div class="clr"></div>
        </div>    
          		<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色ID：</div>
            <div class="fl input"><input name="usertypeId"  type="text" ></div>
            <div class="clr"></div>
        </div> 
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">结算组ID：</div>
            <div class="fl input"><input name="clearingId"  type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>  
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户组ID：</div>
            <div class="fl input"><input name="openuserId"  type="text" placeholder=""></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">&nbsp;</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">1倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">2倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">3倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">4倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">5倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">6倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">7倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">8倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">9倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">10倍</div>
            <div  class="clr"></div>
        </div>  
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">日配组ID1-10：</div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId1" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId2" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId3" type="text" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId4" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId5" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId6" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId7" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId8" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId9" type="text" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId10" type="text"  /></div>
            <div  class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">&nbsp;</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">1倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">2倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">3倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">4倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">5倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">6倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">7倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">8倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">9倍</div>
            <div class="fl input input0" style="border: none;width: 37px;text-align: center">10倍</div>
            <div  class="clr"></div>
      
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月配组ID1-10：</div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId1" type="text" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId2" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId3" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId4" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId5" type="text" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId6" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId7" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId8" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId9" type="text"  /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId10" type="text"  /></div>
            <div  class="clr"></div>
        </div>             
       

        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveAdmin();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function selectKF(){
	 $("input[name='kf']").each(function(){//反选
	       $(this).prop("checked",!$(this).prop("checked"));
	   });
}

function selectYH(){
	 $("input[name='yh']").each(function(){//反选
	       $(this).prop("checked",!$(this).prop("checked"));
	   });
}

function selectJD(){
	 $("input[name='jd']").each(function(){//反选
	       $(this).prop("checked",!$(this).prop("checked"));
	   });
}

var sub = false;
function saveAdmin(){
	if(sub){
		return;
	}
	sub = true;
	var data = $('#adminForm').serialize();
	$.post('${ctx}/admin_op_server/saveServerAjax.htm',data,function(d){
		    sub = false;
            if(d==-2){
                sureInfo("确定","请填写服务器名称","提示");
                return null;
            }
            if(d==-3){
                sureInfo("确定","请输入优先级","提示");
                return null;
            }
            if(d==-4){
                sureInfo("确定","请输入Ip地址","提示");
                return null;
            }
            if(d==-5){
                sureInfo("确定","请输入服务器金额","提示");
                return null;
            }
            if(d==-6){
                sureInfo("确定","请输入开始序列号","提示");
                return null;
            }
            if(d==-7){
                sureInfo("确定","请输入交易站点名称","提示");
                return null;
            }
            if(d==-8){
                sureInfo("确定","请输入结算组","提示");
                return null;
            }
            if(d==-9){
                sureInfo("确定","请输入开户组","提示");
                return null;
            }
            if(d==-10){
                sureInfo("确定","请输完整日配Id","提示");
                return null;
            }
            if(d==-11){
                sureInfo("确定","请输完整月配Id","提示");
                return null;
            }
            if(d==-12){
                sureInfo("确定","请输入数据库ip","提示");
                return null;
            }
            if(d==-13){
                sureInfo("确定","请输入数据库端口","提示");
                return null;
            }
            if(d==-14){
                sureInfo("确定","请输入数据库名称","提示");
                return null;
            }
            if(d==-15){
                sureInfo("确定","请输入数据库用户名","提示");
                return null;
            }
            if(d==-16){
                sureInfo("确定","请输入数据库密码","提示");
                return null;
            }
            if(d==-17){
                sureInfo("确定","数据库无法连接","提示");
                return null;
            }
            if(d==-18){
                sureInfo("确定","请输入端口","提示");
                return null;
            }
            if(d==-19){
                sureInfo("确定","请输入数据库只读账号","提示");
                return null;
            }
            if(d==-20){
                sureInfo("确定","请输入数据库只读密码","提示");
                return null;
            }
            if(d==1){
                jAlert("成功添加！","提示",function(){
					location.href = location.href;
		        });
            }
	});
}
</script>
