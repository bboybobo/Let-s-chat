package talk.alvin.bean.resoucebean;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import talk.alvin.bean.BaseBean;
import talk.alvin.util.Language;

/**
 * 资源类的父类
 * 
 * @author 唐植超
 * 
 */
public class BaseResouceBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private Properties resouce;

	public BaseResouceBean(String pathKey) {
		resouce = new Properties();
		loadData(Language.instance.getValue(pathKey));
	}

	private void loadData(String path) {
		try {
			resouce = new Properties();
			BufferedInputStream bf = new BufferedInputStream(
					new FileInputStream(path));
			resouce = new Properties();
			resouce.load(bf);
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拿取值的方法
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return resouce.getProperty(key);
	}
}
