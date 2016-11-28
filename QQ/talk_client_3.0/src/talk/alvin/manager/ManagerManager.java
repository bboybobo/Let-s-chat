package talk.alvin.manager;

import talk.alvin.manager.impl.ChatActionManagerImpl;
import talk.alvin.manager.impl.LoginActionManagerImpl;
import talk.alvin.manager.impl.RegisterActionManagerImpl;
import talk.alvin.manager.impl.SearchActionManagerImpl;

/**
 * 门面管理
 * 
 * @author 唐植超
 * 
 */
public enum ManagerManager {

	instance;

	public ILoginActionManager loginActionManager;

	public IRegisterActionManager registerActionManager;

	public ISearchActionManager searchActionManager;
	
	public IChatActionManager chatActionManager;

	private ManagerManager() {
		changeWorkFlow("default");
	}

	public void changeWorkFlow(String type) {
		loginActionManager = new LoginActionManagerImpl();
		registerActionManager = new RegisterActionManagerImpl();
		searchActionManager = new SearchActionManagerImpl();
		chatActionManager = new ChatActionManagerImpl();
	}
}
