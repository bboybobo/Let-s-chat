package talk.alvin.manager;

import java.util.List;

import talk.alvin.bean.SendMessageBean;

public interface IChatActionManager {

	public void doSendTalkInfoAction(SendMessageBean sendBean);

	public List<SendMessageBean> doRecieveTalkInfoAction(String sendId,
			String recieveId);
}
