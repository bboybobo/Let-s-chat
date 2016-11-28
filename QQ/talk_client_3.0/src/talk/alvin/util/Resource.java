package talk.alvin.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 资源
 * 
 * @author 唐植超
 * 
 */
public enum Resource {

	instance;

	/** 根目录 */
	public static final String REALPATH = System.getProperty("user.dir")
			.concat(File.separator);
	/** 点 */
	public static final String DO = ".";
	/** 换行符 */
	public static final String LINE = System.getProperty("line.separator");
	/** 图标 图片的路径 */
	public static final String ICONPATH = REALPATH.concat("resource").concat(
			File.separator).concat("icons").concat(File.separator);

	/**
	 * 得到图标
	 * 
	 * @param key
	 * @return
	 */
	public Icon getIcon(String key) {
		Image image = getImage(key);
		if (image != null) {
			Icon icon = new ImageIcon(image);
			return icon;
		}
		return null;
	}

	/**
	 * 得到图片
	 * 
	 * @param key
	 * @return
	 */
	public Image getImage(String key) {
		try {
			String path = ICONPATH.concat(Language.instance.getValue(key));
			File imageFile = new File(path);
			if (!imageFile.exists()) {
				imageFile = new File(key);
			}
			BufferedImage image = ImageIO.read(imageFile);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 拿表情
	 * 
	 * @param faceId
	 * @return
	 */
	public Icon getFace(String faceId) {
		String path = ICONPATH.concat("face").concat(File.separator).concat(
				faceId).concat(".bmp");
		System.out.println("user face path :" + path);
		return getIcon(path);
	}

	/**
	 * 配置文件的路径
	 * 
	 * @param name
	 * @return
	 */
	public String getConfigPath(String name) {
		return REALPATH.concat("resource").concat(File.separator).concat(
				"config").concat(File.separator).concat(name);
	}
}
