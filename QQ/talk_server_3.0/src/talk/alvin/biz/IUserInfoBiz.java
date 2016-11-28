package talk.alvin.biz;

import java.util.List;

import talk.alvin.bean.SimpleUser;
import talk.alvin.bean.UserInfoBean;

public interface IUserInfoBiz {

	public UserInfoBean doLogin(String name, String pass);

	public String doRegister(UserInfoBean user);

	public List<SimpleUser> doSearch(String id, String account);

	public SimpleUser doAddFirend(String firendId, String myId, int grouIndex);
}
