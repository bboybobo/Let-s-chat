package talk.alvin.manager.impl;

import org.alvin.bean.Message;

import talk.alvin.bean.UserInfoBean;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.IRegisterActionManager;
import talk.alvin.util.MessageBox;

/**
 * 注册管理
 * 
 * @author 唐植超
 * 
 */
public class RegisterActionManagerImpl extends BaseManager implements
		IRegisterActionManager {

	public boolean doRegister(UserInfoBean user) {
		Message mess = new Message();
		mess.setClassNameId("userInfoManager");
		mess.setFunctionName("doRegisterUserInfoAction");
		mess.setUserObject(user);
		mess = biz.process(mess); 
		MessageBox.promptMessage(mess.getUserObject() + "");
		return true;
	}

}
