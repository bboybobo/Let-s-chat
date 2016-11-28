package talk.alvin.biz.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import talk.alvin.bean.SendMessageBean;
import talk.alvin.bean.SimpleUser;
import talk.alvin.biz.BaseBiz;
import talk.alvin.biz.ITalkInfoBiz;
import talk.alvin.util.Resource;

public class TalkInfoBizImpl extends BaseBiz implements ITalkInfoBiz {

	@SuppressWarnings("unchecked")
	public boolean doSaveTalkMessage(SendMessageBean sendBean) {
		SimpleUser sendUser = sendBean.getSendUser();
		String path = Resource.instance.getTalkDataPath(sendUser.getId())
				.concat(".txt");
		List<SendMessageBean> messageList;
		try {
			File f = new File(path);
			if (f.exists()) {
				messageList = (List<SendMessageBean>) xmlIo.getObject(f);
			} else {
				messageList = new ArrayList<SendMessageBean>();
			}
			messageList.add(sendBean);
			xmlIo.writeObject(f, messageList);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SendMessageBean> doReciveTalkMessage(String sendId,
			String reciveId) {
		String path = Resource.instance.getTalkDataPath(sendId).concat(".txt");
		File file = new File(path);
		List<SendMessageBean> resultList = new ArrayList<SendMessageBean>();
		if (file.exists()) {
			try {
				List<SendMessageBean> messageList = (List<SendMessageBean>) xmlIo
						.getObject(file);
				for (SendMessageBean smb : messageList) {
					if (smb.getReceiveUser().getId().equals(reciveId)) {
						resultList.add(smb);
					}
				}
				messageList.removeAll(resultList);
				xmlIo.writeObject(file, messageList);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
}
