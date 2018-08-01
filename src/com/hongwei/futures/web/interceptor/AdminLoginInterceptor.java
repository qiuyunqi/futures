package com.hongwei.futures.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.util.WebUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminLoginInterceptor extends AbstractInterceptor {
	
	/**
	 * pc后台登录拦截器
	 */
	private static final long serialVersionUID = -2207435662130379836L;
	
	@Autowired
	public FuAdminService fuAdminService;
	

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = (HttpServletRequest) arg0.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		String referer = request.getHeader("Referer");
		arg0.getInvocationContext().put("referer", referer);
		FuAdmin admin = null;
		Long  adminId = null;
		String uri = request.getRequestURI();
		if(WebUtil.getCookieByName(request, "admin_token")!=null){//自动登录
			String token = WebUtil.getCookieByName(request, "admin_token");
			admin = fuAdminService.findLoginByToken(token);
			if(admin!=null){
				//登录记录用户信息
				if(admin.getRageConfig()!=null){
				    JSONObject obj = JSONObject.fromObject(admin.getRageConfig());
				    arg0.getInvocationContext().put("rageConfig",obj);
				    if(admin.getType()!=null&&admin.getType()!=1){
					    if(uri.indexOf("/admin_list_message")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_message")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_sms_log")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_home")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_indexdata")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_user")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_user")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_draw_money")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_draw_money")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_recharge")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_recharge")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_money_flow")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_money_trade")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_commission")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_commission")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_check_person")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_check_person")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_rate")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_rate")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_program")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_program")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_over_program")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_fee")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_fee")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_safe_money")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_safe_money")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_draw_profits")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_draw_profits")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_money_program")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_continue")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_continue")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_list_bad_record")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
	//				    if(uri.indexOf("/admin_op_continue")!=-1){
	//				    	return WebUtil.returnCode(arg0, "nopermission");
	//				    }
					    if(uri.indexOf("/admin_list_game")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if(uri.indexOf("/admin_op_game")!=-1){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
					    if((uri.indexOf("/admin_list_admin")!=-1||uri.indexOf("/admin_list_parameter")!=-1||uri.indexOf("/admin_list_server")!=-1||uri.indexOf("/admin_op_admin")!=-1||uri.indexOf("/admin_op_parameter")!=-1||uri.indexOf("/admin_op_server")!=-1)&&(admin.getType()!=1)){
					    	return WebUtil.returnCode(arg0, "nopermission");
					    }
				    }
				}
				adminId = admin.getId();
				arg0.getInvocationContext().getValueStack().setValue("admin", admin);
				arg0.getInvocationContext().getValueStack().setValue("adminId", adminId);
				return arg0.invoke();
			}
		}
		return WebUtil.returnCode(arg0, "reAdminLogin");
	}

}
