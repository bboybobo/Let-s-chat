package talk.alvin.biz;

import org.alvin.io.ActionConfigerXMLOperate;

/**
 * 业务管理类
 * 
 * @author 唐植超
 * 
 */
public enum BizManager {

	instance();

	public IBiz userInfoBiz;

	private BizManager() {
		changeWorkFlow("default");
	}

	public void changeWorkFlow(String type) {
		try {
			this.userInfoBiz = (IBiz) ActionConfigerXMLOperate.instance
					.getClientObject("biz");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
