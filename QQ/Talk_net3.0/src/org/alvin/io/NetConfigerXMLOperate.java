package org.alvin.io;

import java.io.File;
import java.io.IOException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 服务器配置xml操作
 * 
 * @author 唐植超
 * 
 */
public class NetConfigerXMLOperate {

	public static final NetConfigerXMLOperate instance = new NetConfigerXMLOperate();

	private NetConfigerXMLOperate() {
		try {
			init();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private Document doc = null;
	private Element root = null;
	private File confile = new File(System.getProperty("user.dir").concat(
			File.separator).concat("conf").concat(File.separator).concat(
			"net.xml"));

	private void init() throws DocumentException {
		doc = XMLOperate.instace.getDocument(confile);
		if (doc != null) {
			root = doc.getRootElement();
		}
	}

	/**
	 * 拿去某个节点的文本
	 * 
	 * @param elementName
	 * @return
	 */
	public String getText(String elementName) {
		try {
			if (getRootElement() != null) {
				return root.elementText(elementName);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 拿去某个节点属性的文本
	 * 
	 * @param elementName
	 * @param attName
	 * @return
	 */
	public String getAttrbute(String elementName, String attName) {
		try {
			if (getRootElement() != null) {
				Element e = root.element(elementName);
				if (e != null) {
					return e.getText();
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 修改某个节点的文本
	 * 
	 * @param elementName
	 * @param value
	 */
	public void updateText(String elementName, String value) {
		try {
			if (getRootElement() != null) {
				Element e = root.element(elementName);
				if (e == null) {
					e = root.addElement(elementName);
				}
				e.setText(value);
				XMLOperate.instace.saveDocument(doc, confile);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更改某个节点的属性的值
	 * 
	 * @param elementName
	 * @param attName
	 * @param value
	 */
	public void updateAttributeText(String elementName, String attName,
			String value) {
		try {
			if (getRootElement() != null) {
				Element e = root.element(elementName);
				if (e == null) {
					e = root.addElement(elementName);
				}
				Attribute att = e.attribute(attName);
				if (att == null) {
					e.addAttribute(attName, value);
					att = e.attribute(attName);
				}
				att.setText(value);
				XMLOperate.instace.saveDocument(doc, confile);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Element getRootElement() throws DocumentException {
		return root;
	}
}
