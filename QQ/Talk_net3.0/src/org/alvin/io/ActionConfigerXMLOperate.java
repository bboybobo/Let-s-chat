package org.alvin.io;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alvin.util.NetInvocationHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * action 配置
 * 
 * @author 唐植超
 * 
 */
public class ActionConfigerXMLOperate {

	public static final ActionConfigerXMLOperate instance = new ActionConfigerXMLOperate();

	private ActionConfigerXMLOperate() {
		try {
			init();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private File action_configer_File = new File(System.getProperty("user.dir")
			.concat(File.separator).concat("conf").concat(File.separator)
			.concat("net-configer.xml"));

	private Map<String, String> clazzMap;

	@SuppressWarnings("unchecked")
	private void init() throws DocumentException {
		clazzMap = new HashMap<String, String>();
		Document doc = XMLOperate.instace.getDocument(action_configer_File);
		if (doc != null) {
			Element root = doc.getRootElement();
			List<Element> list = root.elements("action");
			for (Element el : list) {
				clazzMap.put(el.attributeValue("id"), el
						.attributeValue("class"));
			}
		}
	}

	/**
	 * 得到action 类名
	 * 
	 * @param id
	 * @return
	 */
	private String getClassName(String id) {
		return clazzMap.get(id);
	}

	private Map<String, Object> objMap = new HashMap<String, Object>();

	/**
	 * 获取单例
	 * 
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getClientObject(String clazzId)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String clazz = getClassName(clazzId);
		Object result = objMap.get(clazz);
		if (result == null) {
			Class<?> c = Class.forName(clazz);
			result = new NetInvocationHandler().bind(c.newInstance());
			objMap.put(clazz, result);
			return result;
		}
		return null;
	}

	/**
	 * 获取服务器单例
	 * 
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getServerObject(String clazzId)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		String clazz = getClassName(clazzId);
		Object result = objMap.get(clazz);
		if (result == null) {
			Class<?> c = Class.forName(clazz);
			result = c.newInstance();
			objMap.put(clazz, result);
		}
		return result;
	}
}
