package talk.alvin.gui.control.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;

import talk.alvin.gui.control.ComponentUtil;

/**
 * 超链接按钮
 * 
 * @author Administrator
 * 
 */
public class LinkButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	public LinkButton(String text) {
		super(text);
		setForeground(Color.blue);
		init();

	}

	public LinkButton(Action action) {
		super(action);
		init();
	}

	private void init() {
		setForeground(Color.blue);
		setBorder(null);
		setOpaque(false);
		setContentAreaFilled(false);
		addMouseListener(this);
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

	public void mouseClicked(MouseEvent e) {

	}
}
