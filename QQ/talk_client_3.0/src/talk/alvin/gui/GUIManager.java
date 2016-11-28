package talk.alvin.gui;

import talk.alvin.gui.frame.BaseFrame;
import talk.alvin.gui.frame.LoginFrame;
import talk.alvin.gui.frame.MainFrame;
import talk.alvin.gui.frame.RegisterFrame;
import talk.alvin.gui.frame.SearchFrame;
import talk.alvin.util.ObjectManager;

/**
 * 窗体管理类
 * 
 * @author 唐植超
 * 
 */
public enum GUIManager {

	loginFrame(LoginFrame.class),

	mainFrame(MainFrame.class),

	registerFrame(RegisterFrame.class),

	searchFrame(SearchFrame.class);

	private BaseFrame frame;

	private GUIManager(Class<?> clazz) {
		this.frame = (BaseFrame) ObjectManager.instance.getObject(clazz);
	}

	public BaseFrame getFrame() {
		return frame;
	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
}
