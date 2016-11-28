package talk.alvin.io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * xml 操作
 * 
 * @author Administrator
 * 
 */
public class XMLOperateIO extends BaseIO {
	/**
	 * 序列化对象
	 * 
	 * @param f
	 * @param obj
	 * @throws FileNotFoundException
	 */
	public void writeObject(File f, Object obj) throws FileNotFoundException {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(f)));
		e.writeObject(obj);
		e.flush();
		e.close();
	}

	/**
	 * 反系列化
	 * 
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 */
	public Object getObject(File f) throws FileNotFoundException {
		Object obj = null;
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(f)));
		obj = d.readObject();
		d.close();
		return obj;
	}
}
