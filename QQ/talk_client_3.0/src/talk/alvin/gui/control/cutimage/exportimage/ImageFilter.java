package talk.alvin.gui.control.cutimage.exportimage;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author  zhangtao
 * @msn		zht_dream@hotmail.com
 * @mail    zht_dream@hotmail.com
 * Let's Swing together.
 */
public class ImageFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals("png")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public String getDescription() {
		return "png";
	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
