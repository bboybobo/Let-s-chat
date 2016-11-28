/*
 * CurtainButtonUI.java
 *
 * Created on June 11, 2007, 2:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package talk.alvin.gui.control.x;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;

/**
 * 
 * @author William Chen
 */
public class CurtainButtonUI extends ComponentUI implements MouseListener,
		MouseMotionListener {
	static final int PREFERED_HEIGHT = 22;// 22
	private static final Dimension PREFERED_SIZE = new Dimension(200,
			PREFERED_HEIGHT);
	private static final int LEADING_TEXT_PADDING = 12;
	private static final int TEXT_ICON_PADDING = 6;

	private boolean pressed;
	private boolean hovered;
	private CurtainButton button;

	/** Creates a new instance of CurtainButtonUI */
	public CurtainButtonUI() {
	}

	public static ComponentUI createUI(JComponent c) {
		return new CurtainButtonUI();
	}

	public void installUI(JComponent c) {
		button = (CurtainButton) c;
		button.addMouseListener(this);
		button.addMouseMotionListener(this);
	}

	public void uninstallUI(JComponent c) {
		button.removeMouseListener(this);
		button.removeMouseMotionListener(this);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		setPressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		setPressed(false);
		button
				.fireActionPerformed(new ActionEvent(button, 0, button
						.getText()));
	}

	public void mouseEntered(MouseEvent e) {
		setHovered(true);
	}

	public void mouseExited(MouseEvent e) {
		setHovered(false);
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		button.repaint();
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
		button.repaint();
	}

	public Dimension getPreferredSize(JComponent c) {
		return PREFERED_SIZE;
	}

	public void paint(Graphics g, JComponent c) {
		paintComponentBackground(g);
		int w = button.getWidth();
		int h = button.getHeight();
		int wd = 0;
		if (button.getIcon() != null)
			wd += button.getIcon().getIconWidth();
		FontMetrics fm = g.getFontMetrics();
		if (button.getText() != null)
			wd += fm.stringWidth(button.getText()) + TEXT_ICON_PADDING;
		int x = (int) (LEADING_TEXT_PADDING + (w - 2 * LEADING_TEXT_PADDING - wd)
				* button.getAlignment());
		if (button.getIcon() != null) {
			int y = (h - button.getIcon().getIconHeight()) / 2;
			button.getIcon().paintIcon(button, g, x, y);
			x += button.getIcon().getIconWidth() + TEXT_ICON_PADDING;
		}
		if (button.getText() != null) {
			int y = (h - fm.getHeight()) / 2 + fm.getAscent();
			g.setColor(button.getForeground());
			g.drawString(button.getText(), x, y);
		}
	}

	protected void paintComponentBackground(Graphics g) {
		int w = button.getWidth();
		int h = button.getHeight();
		Color color = new JPanel().getBackground();
		GradientPaint gp = new GradientPaint(1, 1, new Color(100, 200, 200), 1,
				h - 2, color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gp);
		g2d.fillRect(1, 1, w - 2, h - 2);
		g.setColor(Color.white);
		g.setColor(color);
		g.drawLine(0, 0, w - 1, 0);
		g.drawLine(0, 0, 0, h - 1);
		g.setColor(new Color(100, 200, 200));
		g.drawLine(0, h - 1, w - 1, h - 1);
		g.drawLine(w - 1, 0, w - 1, h - 1);
	}
}
