package talk.alvin.biz;

import java.util.List;

import talk.alvin.bean.SendMessageBean;

public interface ITalkInfoBiz {
	public boolean doSaveTalkMessage(SendMessageBean sendBean);

	public List<SendMessageBean> doReciveTalkMessage(String sendId,
			String reciveId);
}
