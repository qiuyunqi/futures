package com.hongwei.futures.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * spring 工具类
 * @author 充满智慧的威哥
 *
 */
@SuppressWarnings("unchecked")
public class SpringUtil {

	/**
	 * 通过spring得到ServletContext
	 * @return
	 */
	public static ServletContext getServletContext() {
		ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		return servletContext;
	}
	
	/**
	 * 得到真实路径
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path) {
		ServletContext servletContext = getServletContext();
		if (StringUtil.isBlank(path)) {
			path = "/";
		}
		return servletContext.getRealPath(path);
	}
	
	/**
	 * 得到spring bean 
	 * @param <T> spring 3 开始 getBean方法已经支持泛型了，这里目前还是2.5的写法
	 * @param entityClass 类型
	 * @param beanId 自动以类名首字母小写，如有不同可做参数传入
	 * @return
	 */
	public static <T> T getBean(Class<T> entityClass, String beanId) {
		ServletContext servletContext = getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		if (StringUtil.isBlank(beanId)) {
			beanId = StringUtil.firstCharLowerCase(entityClass.getSimpleName());
		}
		Object springBean = webApplicationContext.getBean(beanId);
		return springBean != null ? (T)springBean : null;
	}
	
	public static <T> T getBean(Class<T> entityClass) {
		return getBean(entityClass, null);
	}
	
}
