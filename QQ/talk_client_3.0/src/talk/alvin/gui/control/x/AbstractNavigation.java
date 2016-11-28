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

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 * @author wateray
 * @create 2009-5-1
 */
public abstract class AbstractNavigation {

	// Logger logger = Log.getLogger();

	/**
	 * Scroll pane used for tree
	 */
	private JScrollPane scrollPane;

	/**
	 * @return the title of this view
	 */
	public abstract String getTitle();

	/**
	 * @return the icon of this view
	 */
	public abstract ImageIcon getIcon();

	/**
	 * @return the tooltip of this view in tabbed pane
	 */
	public abstract String getTooltip();

	/**
	 * @return the JTree that contains this view
	 */
	public abstract JTree getTree();

	/**
	 * Return tree renderer of this view
	 * 
	 * @return
	 */
	protected abstract TreeCellRenderer getTreeRenderer();

	/**
	 * 
	 * @return the tree popup menu of this view
	 */
	public abstract JPopupMenu getTreePopupMenu();

	/**
	 * Returns scroll pane of tree
	 * 
	 * @return
	 */
	public final JScrollPane getTreeScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTree());
		}
		return scrollPane;
	}

	/**
	 * Returns the data to be shown in the view. It depends on the view mode
	 * 
	 * @param viewMode
	 * @return
	 */
	// protected abstract Map<String, ?> getViewData(ViewMode viewMode);
	/**
	 * Returns <code>true</code> if the view supports organize information in
	 * different view modes
	 * 
	 * @return
	 */
	public abstract boolean isViewModeSupported();

	/**
	 * Enables or disables tree popup menu items of this view
	 * 
	 * @param e
	 */
	public abstract void updateTreePopupMenuWithTreeSelection(MouseEvent e);

}
