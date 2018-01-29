package com.zy.many.utils;

/**
 * String 工具类
 * 
 * @author zhouyou
 * @version 2017年7月27日16:19:23
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空字符串，不考虑空格的情况
	 * 
	 * @param charSequence
	 * @return
	 */
	public static boolean isBlank(CharSequence charSequence) {
		if (null != charSequence && charSequence.length() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		String string = " ";

		System.out.println(string.isEmpty());

	}
}