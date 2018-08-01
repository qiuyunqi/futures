package com.hongwei.futures.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * 反射的Utils函数集合.
 * 
 */
public class ReflectionUtil {

	private static Log log = LogFactory.getLog(ReflectionUtil.class);

	private ReflectionUtil() {
	}

	/**
	 * 直接读取对象属性,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常" + e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常: "+ e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	public static Field getDeclaredField(final Object object, final String fieldName) throws NoSuchFieldException {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(final Class clazz, final String fieldName) throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
	}

	/**
	 * 提取集合中的对象的属性,组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertityName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection, final String propertyName)
			throws Exception {

		List list = new ArrayList();

		for (Object obj : collection) {
//			list.add(PropertyUtils.getProperty(obj, propertyName));
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertityName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) throws Exception {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}
}