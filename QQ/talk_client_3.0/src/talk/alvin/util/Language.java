package talk.alvin.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 语言类
 * 
 * @author 唐植超
 * 
 */
public enum Language {

	instance;

	Locale lang = Locale.getDefault();

	ResourceBundle rs = ResourceBundle.getBundle("lang/talk", lang);

	/**
	 * 拿到对应的国际化值
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		try {
			String result = rs.getString(key);
			if (result == null || result.equals("")) {
				return key;
			} else {
				return result;
			}
		} catch (Exception e) {
			return key;
		}
	}
}
