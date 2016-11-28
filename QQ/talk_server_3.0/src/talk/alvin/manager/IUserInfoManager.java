package talk.alvin.manager;

import org.alvin.bean.Message;

public interface IUserInfoManager {

	Message doValidateUserInfoAction(Message mess);

	Message doRegisterUserInfoAction(Message mess);

	Message doSearchFirendByConditionAction(Message mess);

	Message doAddFirendButtonAction(Message mess);
}
