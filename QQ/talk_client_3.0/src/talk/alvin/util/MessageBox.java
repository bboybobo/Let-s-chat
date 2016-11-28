package talk.alvin.util;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * 消息框
 * 
 * @author 唐植超
 * 
 */
public class MessageBox {

	private MessageBox() {
	}

	public static final int SELECT_YES = JOptionPane.YES_OPTION;
	public static final int SELECT_NO = JOptionPane.NO_OPTION;
	public static final int SELECT_CANCEL = JOptionPane.CANCEL_OPTION;

	/**
	 * 消息框
	 * 
	 * @param msg
	 */
	public static void promptMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, Language.instance
				.getValue("dialog.message"), JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 警告框
	 * 
	 * @param msg
	 */
	public static void promptWarning(String msg) {
		JOptionPane.showMessageDialog(null, msg, Language.instance
				.getValue("dialog.warn"), JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * 错误框
	 * 
	 * @param msg
	 */
	public static void promptError(String msg) {
		JOptionPane.showMessageDialog(null, msg, Language.instance
				.getValue("dialog.error"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 二项选择框
	 * 
	 * @param msg
	 * @return
	 */
	public static int confirmDialog(String msg) {
		int res = JOptionPane.showConfirmDialog(null, msg, Language.instance
				.getValue("dialog.confirm"), JOptionPane.YES_NO_OPTION);
		return res;
	}

	/**
	 * 三项选择框
	 * 
	 * @param msg
	 * @return
	 */
	public static int chooserConfigDialog(String msg) {
		int res = JOptionPane.showConfirmDialog(null, msg, Language.instance
				.getValue("dialog.confirm"), JOptionPane.YES_NO_CANCEL_OPTION);
		return res;
	}

	/**
	 * 消息框
	 * 
	 * @param msg
	 * @param comp
	 */
	public static void promptMessage(String msg, Component comp) {
		JOptionPane.showMessageDialog(comp, msg, Language.instance
				.getValue("dialog.message"), JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * 警告框
	 * 
	 * @param msg
	 * @param comp
	 */
	public static void promptWarning(String msg, Component comp) {
		JOptionPane.showMessageDialog(comp, msg, Language.instance
				.getValue("dialog.warn"), JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * 错误框
	 * 
	 * @param msg
	 * @param comp
	 */
	public static void promptError(String msg, Component comp) {
		JOptionPane.showMessageDialog(comp, msg, Language.instance
				.getValue("dialog.error"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 二项选择框
	 * 
	 * @param msg
	 * @param comp
	 * @return
	 */
	public static int confirmDialog(String msg, Component comp) {
		int res = JOptionPane.showConfirmDialog(comp, msg, Language.instance
				.getValue("dialog.confirm"), JOptionPane.YES_NO_OPTION);
		return res;
	}

	/**
	 * 三项选择框
	 * 
	 * @param msg
	 * @param comp
	 * @return
	 */
	public static int chooserConfigDialog(String msg, Component comp) {
		int res = JOptionPane.showConfirmDialog(comp, msg, Language.instance
				.getValue("dialog.confirm"), JOptionPane.YES_NO_CANCEL_OPTION);
		return res;
	}

	/**
	 * 输入框
	 * 
	 * @param title
	 * @param value
	 * @return
	 */
	public static String inputDialog(String title, String value) {
		String res = JOptionPane.showInputDialog(null, Language.instance
				.getValue(title), Language.instance.getValue(value));
		return res;
	}

	/**
	 * 输入框
	 * 
	 * @param title
	 * @param value
	 * @param comp
	 * @return
	 */
	public static String inputDialog(String title, String value, Component comp) {
		String res = JOptionPane.showInputDialog(comp, Language.instance
				.getValue(title), Language.instance.getValue(value));
		return res;
	}
}
