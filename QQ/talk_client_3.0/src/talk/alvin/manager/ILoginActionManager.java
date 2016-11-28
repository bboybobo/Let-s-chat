package talk.alvin.manager;

public interface ILoginActionManager {
	/**
	 * 用户登录
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	boolean doLogin(String id, String password);
}
