package talk.alvin.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象管理
 * 
 * @author 唐植超
 * 
 */
public enum ObjectManager {
	
	instance;
	
	/** 保存数据的map */
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	/** 保存对象的map */
	private Map<Class<?>, Object> objectMap = new HashMap<Class<?>, Object>();

	public Object getAttribute(String key) {
		return dataMap.get(key);
	}

	public Object setAttribute(String key, Object value) {
		return dataMap.put(key, value);
	}

	public Object removeAttribute(String key) {
		return dataMap.remove(key);
	}

	public void clearAllAttribute() {
		dataMap.clear();
	}

	public Object getObject(Class<?> clazz) {
		Object result = objectMap.get(clazz);
		if (result == null) {
			try {
				result = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			objectMap.put(clazz, result);
		}
		return result;
	}
	
	public void clearAllObject(){
		objectMap.clear();
	}
}
