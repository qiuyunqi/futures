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
            <div class="fl input"><input name="serverName"  type="text" value="${fuServer.serverName }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">真实名称：</div>
            <div class="fl input"><input name="serverRealName"  type="text" value="${fuServer.serverRealName }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器优先级：</div>
            <div class="fl input"><input name="serverPriority"  type="text" value="${fuServer.serverPriority }"></div>
            <div class="clr"></div>
        </div>    
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器IP：</div>
            <div class="fl input"><input name="serverIp"   type="text"  value="${fuServer.serverIp }" ></div>
            <div class="clr"></div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器端口号：</div>
            <div class="fl input"><input name="portNumber"  type="text"  value="${fuServer.portNumber}" placeholder="服务器端口,如80"></div>
            <div class="clr"></div>
        </div>
        </div><div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库IP：</div>
            <div class="fl input"><input name="dbIp"  type="text"  placeholder="数据库ip地址"  value="${fuServer.dbIp }" ></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库端口：</div>
            <div class="fl input"><input name="dbPort"  type="text"  placeholder="数据库端口" value="${fuServer.dbPort }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">数据库名：</div>
            <div class="fl input"><input name="dbName"  type="text"  placeholder="数据库名称" value="${fuServer.dbName }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">用户名：</div>
            <div class="fl input"><input name="dbUsername"  type="text"  placeholder="数据库用户名" value="${fuServer.dbUsername }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">密码：</div>
            <div class="fl input"><input name="dbPassword"  type="password"  placeholder="数据库密码" value="${fuServer.dbPassword }"></div>
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
	            <option <c:if test="${fuServer.isSimTrader==0}">selected="selected"</c:if> value="0">真实</option>
	            <option <c:if test="${fuServer.isSimTrader==1}">selected="selected"</c:if> value="1">模拟</option>
	        </select>
            </div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">服务器金额：</div>
            <div class="fl input"><input name="serverMoney"  type="text" value="<fmt:formatNumber value="${fuServer.serverMoney}" pattern="#######"></fmt:formatNumber>"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">客户端类型：</div>
            <div class="fl input">
            <select id="clientType" name="clientType">
	            <option <c:if test="${fuServer.clientType==1}">selected="selected"</c:if> value="1">众期金融资产管理平台</option>
	            <option <c:if test="${fuServer.clientType==2}">selected="selected"</c:if> value="2">博易大师－鑫管家版</option>
	            <option <c:if test="${fuServer.clientType==3}">selected="selected"</c:if> value="3">博易大师－金牛版</option>
	        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">期货公司：</div>
            <div class="fl input"><input name="futureCompany"  type="text"  placeholder="期货公司" value="${fuServer.futureCompany }"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">角色ID：</div>
            <div class="fl input"><input name="usertypeId"  type="text" value="${fuServer.usertypeId }"></div>
            <div class="clr"></div>
        </div> 
        
   		<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开始序号：</div>
            <div class="fl input"><input name="startNumber"  type="text" value="${fuServer.startNumber }"></div>
            <div class="clr"></div>
        </div>    
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">结算组：</div>
            <div class="fl input"><input name="clearingId"  type="text" value="${fuServer.clearingId}"></div>
            <div class="clr"></div>
        </div>  
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开户组ID：</div>
            <div class="fl input"><input name="openuserId"  type="text" value="${fuServer.openuserId}"></div>
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
            <div class="lf_font fl">日配组ID：</div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId1" type="text" value="${fuServer.dayId1}" placeholder="5385335" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId2" type="text" value="${fuServer.dayId2}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId3" type="text" value="${fuServer.dayId3}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId4" type="text" value="${fuServer.dayId4}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId5" type="text" value="${fuServer.dayId5}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId6" type="text" value="${fuServer.dayId6}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId7" type="text" value="${fuServer.dayId7}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId8" type="text" value="${fuServer.dayId8}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId9" type="text" value="${fuServer.dayId9}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="dayId10" type="text" value="${fuServer.dayId10}" placeholder="4354123" /></div>
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
        </div> 
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">月配组ID：</div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId1" type="text" value="${fuServer.monthId1}" placeholder="5385335" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId2" type="text" value="${fuServer.monthId2}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId3" type="text" value="${fuServer.monthId3}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId4" type="text" value="${fuServer.monthId4}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId5" type="text" value="${fuServer.monthId5}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId6" type="text" value="${fuServer.monthId6}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId7" type="text" value="${fuServer.monthId7}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId8" type="text" value="${fuServer.monthId8}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId9" type="text" value="${fuServer.monthId9}" placeholder="4354123" /></div>
            <div class="fl input input0" style="margin-left: 0px;width: 35px"><input name="monthId10" type="text" value="${fuServer.monthId10}" placeholder="4354123" /></div>
        <div  class="clr"></div>            
       </div>

        <div class=" but" style="margin-bottom:-20px;">
        	
	        <a href="javascript:void(0);" onclick="saveAdmin(${fuServer.id});" class="sure fl">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a>
	        <div class="clr"></div>
        </div>
		</form>
</div>
<script>

var sub = false;
function saveAdmin(id){
	if(sub){
		return;
	}
	sub = true;
	var data = $('#adminForm').serialize();
	$.post("${ctx}/admin_op_server/updateServer.htm?id="+id,data,function(d){
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
            if(d==-19){
                sureInfo("确定","请输入端口","提示");
                return null;
            }
            if(d==-20){
                sureInfo("确定","请输入数据库只读账号","提示");
                return null;
            }
            if(d==-21){
                sureInfo("确定","请输入数据库只读密码","提示");
                return null;
            }
            if(d==1){
                jAlert("成功修改！","提示",function(){
					location.href = location.href;
		        });
            }
	});
}



</script>
