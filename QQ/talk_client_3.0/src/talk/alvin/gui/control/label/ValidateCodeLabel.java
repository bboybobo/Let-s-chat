package talk.alvin.gui.control.label;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 验证码控件
 * 
 * @author Administrator
 * 
 */
public class ValidateCodeLabel extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	/** 字体 */
	private static final String[] fontTypes = { "\u5b8b\u4f53",
			"\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53",
			"\u96b6\u4e66" };
	/** 去除相像的字符 */
	private static final String CODE_WORDS = "AaBbCcDdEeFfGgHhJjKkLMmNnPpQqRrSsTtUuVvWwXxYy2345678";
	/** 字体的长度 */
	int fontTypesLength = fontTypes.length;

	public ValidateCodeLabel() {
		changeCode();
		setToolTipText("点击切换验证码");
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		changeCode();
	}

	public void changeCode() {
		setIcon(new ImageIcon(randImage()));
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(HAND_CURSOR);
	}

	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * 随机产生颜色
	 * 
	 * @return
	 */
	private Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private BufferedImage randImage() {
		BufferedImage image = new BufferedImage(120, 20,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();

		Random random = new Random();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 120, 20);

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			int start = random.nextInt(CODE_WORDS.length());
			String rand = CODE_WORDS.substring(start, start + 1);
			sRand += rand;
			// 设置字体的颜色
			g.setColor(getRandColor(random, 10, 150));
			// 设置字体
			g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)],
					Font.BOLD, 18 + random.nextInt(6)));
			// 将此汉字画到图片上
			g.drawString(rand, 15 * i + 10 + random.nextInt(8), 15);
		}
		g.setColor(Color.black);
		for (int i = 0; i < 4; i++) {
			g.drawLine(random.nextInt(120), random.nextInt(20), random
					.nextInt(120), random.nextInt(20));
		}
		System.setProperty("randCode", sRand);
		g.dispose();
		return image;
	}
}
