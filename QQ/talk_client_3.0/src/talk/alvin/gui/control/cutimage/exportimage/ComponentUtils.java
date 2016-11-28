package talk.alvin.gui.control.cutimage.exportimage;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * 
 * @author  zhangtao
 * @msn		zht_dream@hotmail.com
 * @mail    zht_dream@hotmail.com
 * Let's Swing together.
 * 
 */
public class ComponentUtils {
	public ComponentUtils() {

	}

	/**
	 * Prints the component with the size of the component
	 * 
	 * @param	component	the component which is needed to print
	 * @return	The image of the component
	 */
	public static BufferedImage printComponent(JComponent component) {
		return printComponent(component, -1, -1);
	}

	/**
	 * Prints the component with the appointed size
	 * 
	 * @param	component	the component which is needed to print
	 * @return	The image of the component
	 */
	public static BufferedImage printComponent(JComponent component, int imgW, int imgH) {
		Dimension componentSize = component.getSize();
		if (imgW < 0) {
			imgW = componentSize.width;
		}
		if (imgH < 0) {
			imgH = componentSize.height;
		}
		int xgap = 2;
		int ygap = 2;
		BufferedImage image = new BufferedImage(imgW + xgap * 2, imgH + ygap * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.translate(xgap, ygap);
		g.scale(imgW * 1.0 / componentSize.width, imgH * 1.0 / componentSize.height);
		component.print(g);
		return image;
	}

	/**
	 * Saves an images to the file. 
	 * 
	 * @param	image	the image that needs to be saved.
	 * @param	parent	the parent of the fileChooser.
	 */
	public static void exportImage(BufferedImage image, JComponent parent) {
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new ImageFilter());
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAccessory(new ImagePreview(chooser));
		int returnVal = chooser.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = chooser.getSelectedFile().getAbsolutePath();
			String formatName = chooser.getFileFilter().getDescription();
			ImageOutputStream outputStream = null;
			File file = new File(getFormatedFileName(fileName, formatName));
			try {
				outputStream = ImageIO.createImageOutputStream(file);
				ImageIO.write(image, formatName, outputStream);
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getFormatedFileName(String fileName, String formatName) {
		int index = fileName.lastIndexOf(".");
		if (index >= 0 && !fileName.endsWith(".")) {
			return fileName;
		}
		return fileName + "." + formatName;
	}
}
