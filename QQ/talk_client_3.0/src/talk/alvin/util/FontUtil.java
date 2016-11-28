package talk.alvin.util;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

public enum FontUtil {

	instance;

	/**
	 * 加载所有字体
	 * 
	 * @return
	 */
	public Vector<String> loadFont() {
		Vector<String> fontList = new Vector<String>();
		// Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
		// .getAllFonts();
		fontList.addElement("仿宋_GB2312");
		fontList.addElement("华文中宋");
		fontList.addElement("华文仿宋");
		fontList.addElement("华文宋体");
		fontList.addElement("华文彩云");
		fontList.addElement("华文新魏");
		fontList.addElement("华文楷体");
		fontList.addElement("华文琥珀");
		fontList.addElement("华文细黑");
		fontList.addElement("华文行楷");
		fontList.addElement("华文隶书");
		fontList.addElement("宋体-PUA");
		fontList.addElement("幼圆");
		fontList.addElement("微软雅黑");
		fontList.addElement("微软雅黑 Bold");
		fontList.addElement("方正姚体");
		fontList.addElement("方正舒体");
		fontList.addElement("楷体_GB2312");
		fontList.addElement("隶书");
		fontList.addElement("黑体");
		return fontList;
	}

	/**
	 * 字体大小
	 * 
	 * @return
	 */
	public Vector<Integer> loadSize() {
		Vector<Integer> sizeList = new Vector<Integer>();
		int i = 6;
		for (; i < 24; i += 2) {
			sizeList.addElement(i);
		}
		for (; i <= 72; i += 12) {
			sizeList.addElement(i);
		}
		return sizeList;
	}

	/**
	 * 字体样式
	 * 
	 * @return
	 */
	public Vector<String> loadStyle() {
		Vector<String> styleList = new Vector<String>();
		styleList.addElement("常规");
		styleList.addElement("斜体");
		styleList.addElement("粗体");
		styleList.addElement("粗斜体");
		return styleList;
	}

	public int getFontStye(int index) {
		if (index == 1) {
			return Font.BOLD;
		}
		if (index == 2) {
			return Font.ITALIC;
		}
		if (index == 3) {
			return Font.ITALIC + Font.BOLD;
		}
		return Font.PLAIN;
	}

	public Vector<String> loadColor() {
		Vector<String> colorList = new Vector<String>();
		colorList.addElement("黑色");
		colorList.addElement("红色");
		colorList.addElement("绿色");
		colorList.addElement("黄色");
		return colorList;
	}

	public Color getColor(int index) {
		if (index == 1) {
			return Color.red;
		}
		if (index == 2) {
			return Color.green;
		}
		if (index == 3) {
			return Color.yellow;
		}
		return Color.black;
	}
}
