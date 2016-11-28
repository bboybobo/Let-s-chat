package talk.alvin.gui.control.editor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * 发送信息的文本框
 * 
 * @author 唐植超
 * 
 */
public class SendMessageTextPane extends JTextPane {

	private static final long serialVersionUID = 1L;

	private String fontName = "宋体";
	private int fontSize = 12;
	private int fontStyle = Font.BOLD;
	private Color fontColor = Color.black;

	public SendMessageTextPane() {
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		changeFont();
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		changeFont();
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
		changeFont();
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		changeFont();
	}

	private void changeFont() {
		String text = getText();
		setText("");
		insertText(text);
	}

	public void insertText(String text) {
		StyledDocument doc = getStyledDocument();
		try {
			doc.insertString(doc.getLength(), text, getAttrSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void createStyle(String style, StyledDocument doc, int size,
			int bold, int italic, int underline, Color color, String fontName) {
		Style sys = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		try {
			doc.removeStyle(style);
		} catch (Exception e) {
		} // 先删除这种Style,假使他存在

		Style s = doc.addStyle(style, sys); // 加入
		StyleConstants.setFontSize(s, size); // 大小
		StyleConstants.setBold(s, (bold == 1) ? true : false); // 粗体
		StyleConstants.setItalic(s, (italic == 1) ? true : false); // 斜体
		StyleConstants.setUnderline(s, (underline == 1) ? true : false); // 下划线
		StyleConstants.setForeground(s, color); // 颜色
		StyleConstants.setFontFamily(s, fontName); // 字体
	}

	public SimpleAttributeSet getAttrSet() {

		SimpleAttributeSet attrSet = new SimpleAttributeSet();

		if (fontName != null) {
			StyleConstants.setFontFamily(attrSet, fontName);
		}
		if (fontStyle == Font.BOLD) {
			StyleConstants.setBold(attrSet, true);
		} else if (fontStyle == Font.ITALIC) {
			StyleConstants.setBold(attrSet, true);
		} else if (fontStyle == Font.BOLD + Font.ITALIC) {
			StyleConstants.setBold(attrSet, true);
			StyleConstants.setItalic(attrSet, false);
		} else {
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, false);
		}

		StyleConstants.setFontSize(attrSet, fontSize);

		if (fontColor != null)
			StyleConstants.setForeground(attrSet, fontColor);

		// if (backColor != null)
		// StyleConstants.setBackground(attrSet, backColor);

		return attrSet;

	}
}
