package talk.alvin.manager;

import java.beans.PropertyChangeEvent;

import talk.alvin.AbstractBaseObject;
import talk.alvin.biz.BizManager;
import talk.alvin.biz.ITalkInfoBiz;
import talk.alvin.biz.IUserInfoBiz;

public class BaseManager extends AbstractBaseObject {

	protected IUserInfoBiz userInfoBiz = BizManager.instance.userInfoBiz;
	protected ITalkInfoBiz talkBiz = BizManager.instance.talkBiz;

	public void propertyChange(PropertyChangeEvent evt) {

	}

}
