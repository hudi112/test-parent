package com.z1.util;

import org.apache.commons.lang.StringUtils;

import com.z1.core.Const;

/**
 * KEY生成工具
 * @author Tangxu
 * @since 0.0.1
 *
 */

public class KeyUtil {

	private static final String KEY_DEFAULT = "Z11Z";
	
	/**
	 * 生成KEY字节数组
	 * @param key 关键字密钥
	 * @param bitSize bit位数
	 * @return
	 */
    public static byte[] getBitKey(String key, int bitSize) {
    	return getBitKeyString(key, bitSize).getBytes(Const.UTF8_CHARSET);
    }

    /**
     * 生成KEY串
     * @param key 关键字密钥
     * @param bitSize bit位数
     * @return
     */
    public static String getBitKeyString(final String key, final int bitSize) {
    	String newKey = StringUtils.isBlank(key) ? KEY_DEFAULT : key;
    	
		if (newKey.length() > bitSize) {
			return newKey.substring(0, bitSize);
		}
		else {
			StringBuffer buffer = new StringBuffer(); 
			final int multiple = bitSize / newKey.length();
			for (int i = 0; i < multiple; i++) {
				buffer.append(newKey);
			}
			int remainder = bitSize % newKey.length();
			if (0 != remainder) {
				buffer.append(newKey.substring(0, remainder));
			}
			return buffer.toString();
		}
    }

}
