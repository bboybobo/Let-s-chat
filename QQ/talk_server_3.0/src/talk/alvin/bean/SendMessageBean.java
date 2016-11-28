package talk.alvin.bean;

import java.awt.Color;
import java.awt.Font;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * 信息发送类
 * 
 * @author 唐植超
 * 
 */
public class SendMessageBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String messageContent;

	private String sendTime;

	private SimpleUser sendUser;

	private SimpleUser receiveUser;

	private String fontName;

	private int fontSize;

	private int fontStyle;

	private Color fontColor;

	public String toString() {
		return sendUser.toString().concat("\t").concat(sendTime).concat("\r\n")
				.concat(messageContent).concat("\r\n");
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public SimpleUser getSendUser() {
		return sendUser;
	}

	public void setSendUser(SimpleUser sendUser) {
		this.sendUser = sendUser;
	}

	public SimpleUser getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(SimpleUser receiveUser) {
		this.receiveUser = receiveUser;
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

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
}
