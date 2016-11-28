package talk.alvin.manager;

import java.beans.PropertyChangeEvent;

import talk.alvin.AbstractBaseObject;
import talk.alvin.biz.BizManager;
import talk.alvin.biz.IBiz;

/**
 * 门面类的父类
 * 
 * @author 唐植超
 * 
 */
public class BaseManager extends AbstractBaseObject {

	protected static IBiz biz = BizManager.instance.userInfoBiz;

	public void propertyChange(PropertyChangeEvent evt) {

	}

}
