package talk.alvin.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Encoder;

public enum StringUtil {

	instance;

	private SimpleDateFormat formatDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 返回ＭＤ５密文
	 * 
	 * @param str
	 * @return
	 */
	public String encoderByMd5(String str) {
		/*
		 * 只能加密密码之类的密文 存入数据库之前进行加密 比较密码时，
		 * 
		 * 不能解密，将密码加密并与数据库中的密文对比
		 */
		String newstr;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode(md5.digest(str.getBytes()));
			return newstr;
		} catch (Exception e) {
			return null;
		}
	}

	public String formatDateToString(Date date) {
		return formatDate.format(date);
	}
}
