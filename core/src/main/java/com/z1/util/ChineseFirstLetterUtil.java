package com.z1.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文小写首字母工具类
 * @author dean Huang
 * @since 0.0.1
 *
 */

public class ChineseFirstLetterUtil {
	
	/**
	 * 获取中文大写首字母（无符号）
	 * @param c
	 * @return
	 */
	public static String chineseToFirstLetterNoSymbol(String c) {
		return ChineseFirstLetterUtil.chineseToFirstLetter(c)
			.replaceAll("[^a-z|A-Z|0-9|\\_]", "");
	}
	
	/**
	 * 获取中文首字母（有符号）
	 * @param c
	 * @return
	 */
	public static String chineseToFirstLetter(String c) {
        String string = "";
		char b;
		int a = c.length();
		for (int k = 0; k < a; k++) {
			b = c.charAt(k);
			String d = String.valueOf(b);
			String str = converterToFirstSpell(d);
			String s = str.toLowerCase();
			String g = s;
			char h;
			for (int y = 0; y <= 0; y++) {
				h = g.charAt(0);
				string += h;
			}
		}
		return string;
	}

	/**
	 * 获取指定串的首字母
	 * @param chines
	 * @return
	 */
	private static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			String s = String.valueOf(nameChar[i]);
			if (s.matches("[\\u4e00-\\u9fa5]")) {
				try {
					String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
					pinyinName += mPinyinArray[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

}
