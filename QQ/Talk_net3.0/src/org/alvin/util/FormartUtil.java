package org.alvin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化工具
 * 
 * @author 唐植超
 * 
 */
public enum FormartUtil {

	instance;

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public String formatDateToString(Date date) {
		return dateFormat.format(date);
	}
}
