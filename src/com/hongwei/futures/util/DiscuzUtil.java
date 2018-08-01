package com.hongwei.futures.util;

import java.util.LinkedList;
import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;

/**
 * ================================================ Discuz! Ucenter API for JAVA
 * ================================================ 测试类 示例：本类实现在如何实现在登入/登出，以及注册。
 * 
 * 更多信息：http://code.google.com/p/discuz-ucenter-api-for-java/ 作者：梁平
 * (no_ten@163.com) 创建时间：2009-2-20
 */
public class DiscuzUtil {

	public static String login(String userName, String pwd) {
		String $ucsynlogin = "";
		try {
			Client uc = new Client();
			String result = uc.uc_user_login(userName, pwd);
			LinkedList<String> rs = XMLHelper.uc_unserialize(result);
			if (rs.size() > 0) {
				int $uid = Integer.parseInt(rs.get(0));
				/*String $username = rs.get(1);
				String $password = rs.get(2);
				String $email = rs.get(3);*/
				if ($uid > 0) {
					$ucsynlogin = uc.uc_user_synlogin($uid);
					System.out.println("论坛登录成功" + $ucsynlogin);
				} else if ($uid == -1) {
					System.out.println("用户不存在,或者被删除");
				} else if ($uid == -2) {
					System.out.println("密码错");
				} else {
					System.out.println("未定义");
				}
			} else {
				System.out.println("Login failed");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("论坛登录失败");
		}
		return $ucsynlogin;
	}

	public static String logout() {
		String $ucsynlogout = "";
		try {
			Client uc = new Client();
			$ucsynlogout = uc.uc_user_synlogout();
			System.out.println("论坛退出成功" + $ucsynlogout);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("论坛退出失败");
		}
		return $ucsynlogout;
	}

	public static void reg(String userName, String password, String email) {
		Client uc = new Client();
		String $returns = uc.uc_user_register(userName, password, email);
		int $uid = Integer.parseInt($returns);
		if ($uid <= 0) {
			if ($uid == -1) {
				System.out.print("用户名不合法" + userName);
			} else if ($uid == -2) {
				System.out.print("包含要允许注册的词语" + userName);
			} else if ($uid == -3) {
				System.out.print("用户名已经存在" + userName);
			} else if ($uid == -4) {
				System.out.print("Email 格式有误");
			} else if ($uid == -5) {
				System.out.print("Email 不允许注册");
			} else if ($uid == -6) {
				System.out.print("该 Email 已经被注册");
			} else {
				System.out.print("未定义");
			}
		} else {
			System.out.println("论坛账号注册成功" + $returns);
		}
	}
	
	/**
	 * 测试代码
	 * @param args
	 */
	public static void main(String[] args) {
		DiscuzUtil d = new DiscuzUtil();
		d.reg("", "", "");
	}

}
