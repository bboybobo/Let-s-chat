package talk.alvin.biz;

import talk.alvin.biz.impl.TalkInfoBizImpl;
import talk.alvin.biz.impl.UserInfoBizImpl;

public enum BizManager {

	instance;

	public IUserInfoBiz userInfoBiz;

	public ITalkInfoBiz talkBiz;

	private BizManager() {
		changeWorkFlow("default");
	}

	public void changeWorkFlow(String type) {
		userInfoBiz = new UserInfoBizImpl();
		talkBiz = new TalkInfoBizImpl();
	}

}
