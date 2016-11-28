/*
 * ListPane.java
 *
 * Created on June 8, 2007, 9:17 PM
 */

package talk.alvin.gui.control.x;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ListPane extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6807890033041814417L;

	private static final int HORZ_PAD = 12;
	private static final int VERT_PAD = 6;

	/**
	 * Creates new form ListPane
	 */
	public ListPane() {
		initComponents();
		Border b = BorderFactory.createEmptyBorder(VERT_PAD, HORZ_PAD,
				VERT_PAD, HORZ_PAD);
		setBorder(b);
		setBackground(Color.white);
	}

	private void initComponents() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 5, 10);
		setLayout(layout);
	}

	public void addItem(Object obj, Icon icon) {
		addItem(obj, icon, null);
	}

	public void addItem(Object obj, Icon icon, final Action action) {
		LinkLabel lblItem = new LinkLabel(obj, icon, action);
		add(lblItem);
	}

	public void addButtonItem(String text, Icon icon) {
		JButton btnItem = new JButton();
		btnItem.setBorderPainted(false);
		btnItem.setBorder(BorderFactory.createCompoundBorder());
		btnItem.setBackground(GuiUtils
				.getLookAndFeelColor("backgroundFillColor"));
		if (icon != null) {
			btnItem.setIcon(icon);
		}
		btnItem.setForeground(GuiUtils.getLookAndFeelColor("foregroundColor"));
		btnItem.setText(text);
		add(btnItem);
	}

	public void addListItem() {
		String[] data = { "one", "two", "three", "four" };
		JList lstItem = new JList(data);
		add(lstItem);
	}
}
