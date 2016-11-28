package talk.alvin.manager.impl;

import org.alvin.bean.Message;

import talk.alvin.bean.SendMessageBean;
import talk.alvin.manager.BaseManager;
import talk.alvin.manager.ITalkInfoManager;

public class TalkInfoManagerImpl extends BaseManager implements
		ITalkInfoManager {

	public Message doSendTalkInfo(Message mess) {
		Boolean result;
		result = talkBiz.doSaveTalkMessage((SendMessageBean) mess
				.getUserObject());
		mess.setUserObject(result);
		return mess;
	}

	public Message doRecieveTalkInfo(Message mess) {
		Object[] arr = (Object[]) mess.getUserObject();
		String sendId = (String) arr[0];
		String reciveId = (String) arr[1];
		mess.setUserObject(talkBiz.doReciveTalkMessage(sendId, reciveId));
		return mess;
	}
}
