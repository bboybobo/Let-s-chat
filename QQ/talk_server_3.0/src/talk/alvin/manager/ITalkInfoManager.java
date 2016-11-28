package talk.alvin.manager;

import org.alvin.bean.Message;

public interface ITalkInfoManager {
	public Message doSendTalkInfo(Message mess);
	
	public Message doRecieveTalkInfo(Message mess);
}
