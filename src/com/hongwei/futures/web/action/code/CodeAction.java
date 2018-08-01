package com.hongwei.futures.web.action.code;

import java.io.ByteArrayInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.hongwei.futures.util.Code;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;

public class CodeAction extends BaseAction {
	private static final long serialVersionUID = 699684042579846557L;
	private ByteArrayInputStream inputStream;

	/**
	 * 获取验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "showCode", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "image/jpeg", "inputName", "inputStream" }) })
	public String showCode() {
		try {
			Code rdnu = Code.newInstance();
			this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片

			WebUtil.addCookie(getHttpServletResponse(), "yoyoshop_random",
					rdnu.getString(), 0);// 取得随机字符串放入HttpSession
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

}
