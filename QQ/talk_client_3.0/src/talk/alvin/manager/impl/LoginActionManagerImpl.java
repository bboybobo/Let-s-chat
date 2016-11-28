package talk.alvin.manager.impl;

import org.alvin.bean.Message;

import talk.alvin.bean.UserInfoBean;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.ILoginActionManager;
import talk.alvin.util.MessageBox;
import talk.alvin.util.ObjectManager;

public class LoginActionManagerImpl extends BaseManager implements
		ILoginActionManager {

	public boolean doLogin(String id, String password) {
		UserInfoBean user = new UserInfoBean();
		user.setId(id);
		user.setPassword(password);

		Message mess = new Message();
		mess.setClassNameId("userInfoManager");
		mess.setFunctionName("doValidateUserInfoAction");
		mess.setUserObject(user);

		mess = biz.process(mess);
		Object resultInfo = mess.getUserObject();
		if (resultInfo instanceof String) {
			MessageBox.promptMessage(resultInfo.toString());
			return false;
		}
		if (resultInfo instanceof UserInfoBean) {
			ObjectManager.instance.setAttribute("currentUser", resultInfo);
			return true;
		}
		MessageBox.promptError("用户名或密码为空");
		log.debug("A don't know class for result Infomation");
		return false;
	}
}
