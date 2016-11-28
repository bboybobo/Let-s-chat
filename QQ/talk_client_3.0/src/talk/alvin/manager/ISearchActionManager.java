package talk.alvin.manager;

import java.util.List;

import talk.alvin.bean.SimpleUser;

public interface ISearchActionManager {

	public List<SimpleUser> clickSearchFirendsListButtonAction(String id,
			String account);

	public SimpleUser clickAddFirendButtonAction(String firendId, String myId,
			int groupIndex);
}
