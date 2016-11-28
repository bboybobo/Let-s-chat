package talk.alvin.gui.control.editor;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class RecieveMessageTextPane extends JTextPane {

	private static final long serialVersionUID = 1L;

	public RecieveMessageTextPane() {
		setEditable(false);
	}

	public void insertString(String text, SimpleAttributeSet sattrs) {
		StyledDocument doc = getStyledDocument();
		try {
			doc.insertString(doc.getLength(), text, sattrs);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
