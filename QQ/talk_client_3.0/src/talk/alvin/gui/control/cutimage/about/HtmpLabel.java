package talk.alvin.gui.control.cutimage.about;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author  zhangtao
 * @msn		zht_dream@hotmail.com
 * @mail    zht_dream@hotmail.com
 * Let's Swing together.
 */
public class HtmpLabel extends JLabel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color overColor = Color.ORANGE;
	private boolean drawLine = true;
	private String command = null;
	private Color originColor = Color.BLUE.brighter();
	private boolean isPressed = false;
	private BasicStroke stroke = null;

	public HtmpLabel(String text) {
		super(text);
		this.setForeground(originColor);
		setHorizontalAlignment(SwingConstants.CENTER);
		addMouseListener(this);
	}

	public Color getOverColor() {
		return overColor;
	}

	public void setOverColor(Color oc) {
		overColor = oc;
	}

	public boolean isDrawLine() {
		return drawLine;
	}

	public void setDrawLine(boolean b) {
		drawLine = b;
	}

	public String getExecCommand() {
		return command;
	}

	public void setExecCommand(String command) {
		this.command = command;
	}

	public void addActionListener(ActionListener l) {
		listenerList.add(ActionListener.class, l);
	}

	public void mouseEntered(MouseEvent e) {
		if (originColor == null) {
			originColor = getForeground();
		}
		if (overColor != null && isEnabled()) {
			setForeground(overColor);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (originColor != null && isEnabled()) {
			setForeground(originColor);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (!isEnabled())
			return;
		Object[] listeners = listenerList.getListenerList();
		if (listeners.length <= 0) {
			return;
		}
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "", e.getWhen(), e.getModifiers());
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ActionListener.class) {
				((ActionListener) listeners[i + 1]).actionPerformed(event);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		isPressed = true;
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		isPressed = false;
		repaint();
	}

	public void paintComponent(Graphics g) {
		int odd = 0;
		Icon icon = getIcon();
		if (icon != null) {
			odd += icon.getIconWidth() + getIconTextGap();
		}
		FontMetrics fm = g.getFontMetrics(g.getFont());
		int cw = fm.stringWidth(getText());
		int ah = fm.getAscent(), dh = fm.getDescent();
		int x = odd + (getWidth() - cw - odd) / 2;
		int y = (getHeight() + ah + dh) / 2 - 2;
		if (drawLine) {
			g.setColor(isEnabled() ? getForeground() : Color.gray);
			g.drawLine(x, y, x + cw - 1, y);
		}
		if (isPressed && isEnabled()) {
			Graphics2D g2d = (Graphics2D) g;
			Stroke oldstroke = g2d.getStroke();
			g2d.setColor(Color.black);
			if (stroke == null) {
				stroke = new BasicStroke(1, 0, 0, 10.0F, new float[] { 1, 1 }, 0);
			}
			g2d.setStroke(stroke);
			g2d.drawRect(x, y - ah, cw, ah);
			g2d.setStroke(oldstroke);
		}
		super.paintComponent(g);
	}
}