package talk.alvin.gui.action;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import talk.alvin.bean.SimpleUser;
import talk.alvin.bean.UserInfoBean;
import talk.alvin.gui.GUIManager;
import talk.alvin.gui.control.x.CurtainPane;
import talk.alvin.gui.control.x.ListPane;
import talk.alvin.gui.frame.MainFrame;
import talk.alvin.util.Resource;

/**
 * action 父类
 * 
 * @author 唐植超
 * 
 */
public abstract class BaseAction extends AbstractAction {

	public final void actionPerformed(ActionEvent e) {
		try {
			Method m = this.getClass().getDeclaredMethod(e.getActionCommand(),
					ActionEvent.class);
			if (m != null) {
				m.invoke(this, e);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			execute(e);
		}
	}

	protected void execute(ActionEvent e) {
		System.out.println("action 虚方法");
	}

	/**
	 * 初始化用户信息 显示到主界面
	 * 
	 * @param user
	 */
	protected void initMainFrame(UserInfoBean user) {
		// 拿到主界面
		MainFrame frame = (MainFrame) GUIManager.mainFrame.getFrame();
		// 循环group 添加
		List<String> groups = user.getGroupList();
		List<String> firends = null;
		ListPane listPanel;
		String groupName;
		SimpleUser firend;
		CurtainPane pane = new CurtainPane();
		for (int i = 0; i < groups.size(); i++) {
			groupName = groups.get(i);
			listPanel = new ListPane();
			firends = user.getFriendMap().get(groupName);
			if (firends != null) {
				for (String id : firends) {
					firend = user.getFirendInfoMap().get(id);
					// 添加项
					Icon icon = Resource.instance.getFace(firend.getFaceId());
					SimpleUser suser = user.getFirendInfoMap().get(id);
					Action action = new ChatFrameAction(suser);
					listPanel.addItem(id, icon, action);
				}
			}

			// 添加组
			pane.addPane(groupName, Resource.instance.getIcon("frame/qq.gif"),
					listPanel);
		}
		frame.resetFirend(pane);
		// 头像
		frame.getFaceLabel().setIcon(
				Resource.instance.getFace(user.getFaceId()));
		// 窗口标题
		frame.setTitle(user.toString());
		// 个人签名
		frame.getRemarkTextField().setText(user.getRemark());
	}
}
