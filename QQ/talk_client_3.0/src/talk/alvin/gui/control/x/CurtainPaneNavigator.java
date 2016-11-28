/*
 * ikakeibo 0.6.0
 * Copyright (C)2009 wateray and contributors
 * wateray@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package talk.alvin.gui.control.x;

/**
 * @author wateray
 * @create 2009-5-2
 */
public class CurtainPaneNavigator {

	public static CurtainPaneNavigator thisNavigator;

	// private JPanel cutainPane;

	private CurtainPaneNavigator() {
	};

	public static CurtainPaneNavigator getInstance() {
		if (thisNavigator == null) {
			thisNavigator = new CurtainPaneNavigator();
		}
		return thisNavigator;
	}

	// public JPanel getCurtainPanelView() {
	// if (cutainPane == null) {
	// cutainPane = new JPanel(new BorderLayout());
	// CurtainPane cp = getCurtainPane();
	// cp.setAnimated(true);
	//
	// // show the first layer.
	// cp.setSelectedPane(0);
	// cutainPane.add(cp, BorderLayout.CENTER);
	//
	// JCheckBox box = getAnimationCheckBox(cp);
	// cutainPane.add(box, BorderLayout.SOUTH);
	// }

	// return cutainPane;
	// }

	// private JCheckBox getAnimationCheckBox(final CurtainPane cp) {
	// final JCheckBox box = new JCheckBox("Enable Animation");
	// box.setSelected(true);
	// box.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// cp.setAnimated(box.isSelected());
	// }
	// });
	// return box;
	// }

}
