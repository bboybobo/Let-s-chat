package talk.alvin.gui.control.button;

import javax.swing.JButton;

import talk.alvin.util.Language;

public class ImageButton extends JButton {

	private static final long serialVersionUID = 1L;

	public ImageButton(String iconKey, String toolTopKey) {
		// setIcon(Resource.instance.getIcon(iconKey));
		setText(Language.instance.getValue(toolTopKey));
		setToolTipText(Language.instance.getValue(toolTopKey));
		// setOpaque(true);
		// setBorder(null);
	}
}
