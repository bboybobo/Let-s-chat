package talk.alvin.gui.control;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 画图板
 * 
 * @author 唐植超
 * 
 */
public class DrawPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public DrawPane() {
		setBackground(Color.white);
	}

	public void paintComponents(Graphics g) {
		super.paintComponent(g);
	}

	public void update(Graphics g) {
		paintComponent(g);
	}
}
