package talk.alvin.manager.impl;

import java.util.List;

import org.alvin.bean.Message;

import talk.alvin.bean.SimpleUser;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.ISearchActionManager;
import talk.alvin.util.MessageBox;

public class SearchActionManagerImpl extends BaseManager implements
		ISearchActionManager {

	@SuppressWarnings("unchecked")
	public List<SimpleUser> clickSearchFirendsListButtonAction(String id,
			String account) {
		if ("".equals(id) && "".equals(id)) {
			MessageBox.promptWarning("请输入查询信息！");
			return null;
		} else if (!id.matches("^\\d+$")) {
			MessageBox.promptWarning("账号必须是数字！");
			return null;
		}
		Message mess = new Message();
		mess.setClassNameId("userInfoManager");
		mess.setFunctionName("doSearchFirendByConditionAction");
		mess.setUserObject(new Object[] { id, account });
		List<SimpleUser> userObject = (List) biz.process(mess).getUserObject();
		return userObject;

	}

	public SimpleUser clickAddFirendButtonAction(String firendId, String myId,
			int groupIndex) {
		Message mess = new Message();
		mess.setClassNameId("userInfoManager");
		mess.setFunctionName("doAddFirendButtonAction");
		mess.setUserObject(new Object[] { firendId, myId, 0 });
		return (SimpleUser) biz.process(mess).getUserObject();
	}

}
