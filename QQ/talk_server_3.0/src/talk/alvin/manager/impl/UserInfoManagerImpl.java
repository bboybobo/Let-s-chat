package talk.alvin.manager.impl;

import org.alvin.bean.Message;

import talk.alvin.bean.UserInfoBean;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.IUserInfoManager;

/**
 * 用户登录处理
 * 
 * @author 唐植超
 * 
 */
public class UserInfoManagerImpl extends BaseManager implements
		IUserInfoManager {

	public Message doValidateUserInfoAction(Message mess) {
		UserInfoBean user = (UserInfoBean) mess.getUserObject();
		mess.setUserObject(userInfoBiz
				.doLogin(user.getId(), user.getPassword()));
		return mess;
	}

	public Message doRegisterUserInfoAction(Message mess) {
		UserInfoBean user = (UserInfoBean) mess.getUserObject();
		mess.setUserObject(userInfoBiz.doRegister(user));
		return mess;
	}

	public Message doSearchFirendByConditionAction(Message mess) {
		Object[] arr = (Object[]) mess.getUserObject();
		String id = (String) arr[0];
		String account = (String) arr[1];
		mess.setUserObject(userInfoBiz.doSearch(id, account));
		return mess;
	}

	public Message doAddFirendButtonAction(Message mess) {
		Object[] arr = (Object[]) mess.getUserObject();
		String firendId = (String) arr[0];
		String myId = (String) arr[1];
		mess.setUserObject(userInfoBiz.doAddFirend(firendId, myId, 0));
		return mess;
	}
}
