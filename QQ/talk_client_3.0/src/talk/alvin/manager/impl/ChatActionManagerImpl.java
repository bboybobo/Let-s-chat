package talk.alvin.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.alvin.bean.Message;

import talk.alvin.bean.SendMessageBean;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.IChatActionManager;

public class ChatActionManagerImpl extends BaseManager implements
		IChatActionManager {

	@SuppressWarnings("unchecked")
	public List<SendMessageBean> doRecieveTalkInfoAction(String sendId,
			String recieveId) {
		Message mess = new Message();
		mess.setClassNameId("talkInfoManager");
		mess.setFunctionName("doRecieveTalkInfo");
		mess.setUserObject(new Object[] { sendId, recieveId });
		mess = biz.process(mess);
		return (ArrayList<SendMessageBean>) mess.getUserObject();
	}

	public void doSendTalkInfoAction(SendMessageBean sendBean) {
		Message mess = new Message();
		mess.setClassNameId("talkInfoManager");
		mess.setFunctionName("doSendTalkInfo");
		mess.setUserObject(sendBean);
		biz.process(mess);
	}

}
