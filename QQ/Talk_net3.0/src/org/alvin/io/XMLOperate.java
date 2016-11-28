package org.alvin.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml操作类
 * 
 * @author 唐植超
 * 
 */
public enum XMLOperate {
	
	instace;
	
	/**
	 * 打开xml
	 * 
	 * @param f
	 * @return
	 * @throws DocumentException
	 */
	public Document getDocument(File f) throws DocumentException {
		return new SAXReader().read(f);
	}

	/**
	 * 保存xml
	 * 
	 * @param doc
	 * @param f
	 * @throws IOException
	 */
	public void saveDocument(Document doc, File f) throws IOException {
		OutputFormat of = OutputFormat.createPrettyPrint();
		of.setEncoding("utf-8");
		XMLWriter write = new XMLWriter(new BufferedWriter(new PrintWriter(f)),
				of);
		write.write(doc);
		write.flush();
		write.close();
	}

	/**
	 * 读取对象
	 * 
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 */
	public Object readObject(File f) throws FileNotFoundException {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(f)));
		Object result = d.readObject();
		d.close();
		return result;
	}

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @param f
	 * @throws FileNotFoundException
	 */
	public void saveObject(Object obj, File f) throws FileNotFoundException {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(f)));
		e.writeObject(obj);
		e.flush();
		e.close();
	}
}
