package talk.alvin.gui.control.cutimage.screen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;

import talk.alvin.gui.control.cutimage.about.AboutDialog;
import talk.alvin.gui.control.cutimage.about.HtmpLabel;
import talk.alvin.gui.control.cutimage.exportimage.ComponentUtils;
import talk.alvin.gui.frame.ChatFrame;

/**
 * 
 * @author zhangtao
 * @msn zht_dream@hotmail.com
 * @mail zht_dream@hotmail.com Let's Swing together.
 */
public class ScreenCapture extends JPanel {

	private static final long serialVersionUID = 1L;
	private JWindow captureWindow = new JWindow();
	private OverViewPanel overView = new OverViewPanel();
	private Capturer contentPane = new Capturer(this);
	private GraphicsEnvironment ge = GraphicsEnvironment
			.getLocalGraphicsEnvironment();
	private GraphicsDevice gd = ge.getDefaultScreenDevice();
	private ChatFrame chatFrame;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.setContentPane(new ScreenCapture(null));
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public ScreenCapture(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
		captureWindow.setContentPane(contentPane);
		initGUI();
	}

	private void initGUI() {

		JPanel operatePanel = new JPanel();
		BoxLayout layout = new BoxLayout(operatePanel, BoxLayout.LINE_AXIS);
		operatePanel.setLayout(layout);
		operatePanel.add(Box.createGlue());
		operatePanel.add(Box.createHorizontalStrut(5));
		final JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(120, 25));
		comboBox.setMaximumSize(new Dimension(120, 25));
		comboBox.addItem("隐藏当前窗体");
		comboBox.addItem("不隐藏本窗体");
		operatePanel.add(comboBox);
		operatePanel.add(Box.createHorizontalStrut(5));
		JButton button = new JButton("抓捕图像");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 1) {
					if (gd.isFullScreenSupported()) {
						contentPane.setScreenImage();
						gd.setFullScreenWindow(captureWindow);
					}
				} else {
					setWindowMinimize(getWindowForComponent(ScreenCapture.this));
					if (gd.isFullScreenSupported()) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						contentPane.setScreenImage();
						gd.setFullScreenWindow(captureWindow);
					}
				}
			}
		});
		operatePanel.add(button);
		operatePanel.add(Box.createHorizontalStrut(5));
		button = new JButton("保存图像");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage selectedImage = overView.getSelectedImage();
				if (selectedImage != null) {
					ComponentUtils.exportImage(selectedImage,
							ScreenCapture.this);
				}
			}
		});
		operatePanel.add(button);
		operatePanel.add(Box.createHorizontalStrut(5));
		operatePanel.add(Box.createGlue());
		HtmpLabel htmpLabel = new HtmpLabel("关于我们");
		final AboutDialog aboutDialog = new AboutDialog();
		htmpLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutDialog.setVisible(true);
			}
		});
		operatePanel.add(htmpLabel);
		operatePanel.add(Box.createHorizontalStrut(5));

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(overView));
		this.add(operatePanel, BorderLayout.NORTH);
	}

	/**
	 * 还原窗体
	 */
	public void resume() {
		gd.setFullScreenWindow(null);
		Window windowForComponent = getWindowForComponent(chatFrame);
		returnWindow(windowForComponent);
	}

	public void returnWindow(Window window) {
		captureWindow.setVisible(false);
		if (window instanceof Frame) {
			Frame frame = (Frame) window;
			frame.setExtendedState(JFrame.NORMAL);
			frame.setVisible(true);
		}
		if (window instanceof Dialog) {
			Dialog dialog = (Dialog) window;
			dialog.setVisible(true);
		}
	}

	public void setWindowMinimize(Window window) {
		if (window instanceof Frame) {
			Frame frame = (Frame) window;
			frame.setExtendedState(JFrame.ICONIFIED);
		}
		if (window instanceof Dialog) {
			Dialog dialog = (Dialog) window;
			dialog.setVisible(false);
		}
	}

	private Window getWindowForComponent(Component component) {
		if (component == null) {
			return null;
		}
		if (component instanceof Frame || component instanceof Dialog) {
			return (Window) component;
		}
		return getWindowForComponent(component.getParent());
	}

	public JWindow getCaptureWindow() {
		return captureWindow;
	}

	public OverViewPanel getOverView() {
		return overView;
	}

	/**
	 * 截图
	 * 
	 * @param e
	 * @param index
	 */
	public void cutImage(ActionEvent e, int index) {
		if (index == 1) {
			if (gd.isFullScreenSupported()) {
				contentPane.setScreenImage();
				gd.setFullScreenWindow(captureWindow);
			}
		} else {
			setWindowMinimize(getWindowForComponent(chatFrame));
			if (gd.isFullScreenSupported()) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				contentPane.setScreenImage();
				gd.setFullScreenWindow(captureWindow);
			}
		}
	}

	public ChatFrame getChatFrame() {
		return chatFrame;
	}

}
