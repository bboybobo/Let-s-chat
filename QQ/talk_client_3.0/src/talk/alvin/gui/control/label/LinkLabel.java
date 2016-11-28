package talk.alvin.gui.control.label;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import talk.alvin.gui.control.ComponentUtil;

/**
 * 超链标签
 * 
 * @author Administrator
 * 
 */
public class LinkLabel extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public LinkLabel(String text) {
		setText(text);
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if (!isEnabled())
			return;
		Object[] listeners = listenerList.getListenerList();
		if (listeners.length <= 0) {
			return;
		}
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"", e.getWhen(), e.getModifiers());
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ActionListener.class) {
				((ActionListener) listeners[i + 1]).actionPerformed(event);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		setForeground(Color.red);
		setCursor(ComponentUtil.HAND);
	}

	public void mouseExited(MouseEvent e) {
		setForeground(Color.blue);
		setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * 添加鼠标点击事件
	 * 
	 * @param al
	 */
	public void addActionListener(ActionListener al) {
		listenerList.add(ActionListener.class, al);
	}

	// public void setText(String text) {
	// this.title = text;
	// if ("".trim().equals(text)) {
	// super.setText("");
	// } else {
	// super
	// .setText("<html><span style=\"text-decoration:underline; color:blue\">"
	// + text + "</span></html>");
	// }
	// }
}
