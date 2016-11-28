package talk.alvin.gui.action;

import java.awt.event.ActionEvent;

import talk.alvin.bean.UserInfoBean;
import talk.alvin.gui.GUIManager;
import talk.alvin.gui.frame.LoginFrame;
import talk.alvin.manager.ILoginActionManager;
import talk.alvin.manager.ManagerManager;
import talk.alvin.util.MessageBox;
import talk.alvin.util.ObjectManager;
import talk.alvin.util.StringUtil;

/**
 * 用户处理
 * 
 * @author Administrator
 * 
 */
public class LoginFrameAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILoginActionManager loginManager = ManagerManager.instance.loginActionManager;

	public void clickUserLoginButtonAction(ActionEvent e) {
		LoginFrame frame = (LoginFrame) GUIManager.loginFrame.getFrame();
		String loginName = frame.getLoginIdTextField().getText().trim();
		String passWord = new String(frame.getPasswordField().getPassword());
		boolean result;
		if (!loginName.matches("^\\d{5,10}$")) {
			MessageBox.promptMessage("请输入正确的用户名！");
			frame.getLoginIdTextField().requestFocus();
			return;
		}
		if (!passWord.matches("^.{6,20}$")) {
			MessageBox.promptMessage("请输入正确的密码！");
			frame.getPasswordField().requestFocus();
			return;
		}
		// 前台加密
		passWord = StringUtil.instance.encoderByMd5(passWord);
		result = loginManager.doLogin(loginName, passWord);
		if (result) {
			Object obj = ObjectManager.instance.getAttribute("currentUser");
			initMainFrame((UserInfoBean) obj);
			GUIManager.mainFrame.show();
			GUIManager.loginFrame.hide();
		}
	}

	/**
	 * 点击注册按钮
	 * 
	 * @param e
	 */
	public void clickRegisterButtonAction(ActionEvent e) {
		GUIManager.registerFrame.show();
		GUIManager.loginFrame.hide();
	}
}
