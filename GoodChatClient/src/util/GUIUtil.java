//…Ë÷√√Ê∞Âæ”÷–

package util;

import java.awt.*;

public class GUIUtil {
	public static void toCenter(Component comp) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration()
				.getBounds();
		comp.setLocation(((int) rec.getWidth() - comp.getWidth()) / 2,
				((int) rec.getHeight() - comp.getHeight()) / 2);
	}
	public static void toCenter(Component child,Component parent){
		child.setLocation( ((int)(parent.getWidth()-child.getWidth()))/2,
				((int)(parent.getHeight()-child.getHeight()))/2 ) ;
	}
}
