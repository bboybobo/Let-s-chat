package talk.alvin.gui.action;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import talk.alvin.bean.SendMessageBean;
import talk.alvin.bean.SimpleUser;
import talk.alvin.bean.UserInfoBean;
import talk.alvin.gui.control.editor.RecieveMessageTextPane;
import talk.alvin.gui.control.editor.SendMessageTextPane;
import talk.alvin.gui.frame.ChatFrame;
import talk.alvin.manager.IChatActionManager;
import talk.alvin.manager.ManagerManager;
import talk.alvin.util.ObjectManager;
import talk.alvin.util.StringUtil;

public class ChatFrameAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private IChatActionManager chatActionManager = ManagerManager.instance.chatActionManager;

	private ChatFrame frame;

	public ChatFrameAction(SimpleUser user) {
		frame = new ChatFrame(user, this);
	}

	protected void execute(ActionEvent e) {
		frame.setVisible(true);
	}

	public void doTalk(ActionEvent e) {
		SimpleUser sendUser = frame.getUser();
		UserInfoBean recieveUser = (UserInfoBean) ObjectManager.instance
				.getAttribute("currentUser");
		List<SendMessageBean> messList = chatActionManager
				.doRecieveTalkInfoAction(sendUser.getId(), recieveUser.getId());
		RecieveMessageTextPane reciveText = frame.getRecaveTextArea();
		StringBuffer content = new StringBuffer("");
		for (SendMessageBean sendMess : messList) {
			content.append(sendMess.getSendUser().getName());
			content.append(sendMess.getSendTime());
			content.append("\n");
			content.append(sendMess.getMessageContent());
			content.append("\r\n");
			reciveText.insertString(content.toString(), sendMess.getAttrSet());
		}
		int len = reciveText.getText().length() - 1;
		reciveText.setSelectionStart(len);
		reciveText.setSelectionEnd(len);
	}

	public void sendMessage(ActionEvent e) {
		SendMessageTextPane sendText = frame.getSendMessageTextArea();
		RecieveMessageTextPane reciveText = frame.getRecaveTextArea();

		String sendString = sendText.getText();
		StringBuffer content = new StringBuffer("");

		if (sendString.equals("")) {
			return;
		}
		UserInfoBean userInfo = (UserInfoBean) ObjectManager.instance
				.getAttribute("currentUser");

		SendMessageBean sendMess = new SendMessageBean();
		sendMess.setMessageContent(sendString);
		sendMess.setReceiveUser(frame.getUser());
		sendMess.setSendUser(userInfo.getFirendInfoMap().get(userInfo.getId()));
		sendMess
				.setSendTime(StringUtil.instance.formatDateToString(new Date()));
		sendMess.setFontName(sendText.getFontName());
		sendMess.setFontColor(sendText.getFontColor());
		sendMess.setFontSize(sendText.getFontSize());
		sendMess.setFontStyle(sendText.getFontStyle());
		sendText.setText("");
		content.append(sendMess.getSendUser().getName());
		content.append(sendMess.getSendTime());
		content.append("\n");
		content.append(sendMess.getMessageContent());
		content.append("\r\n");

		reciveText.insertString(content.toString(), sendText.getAttrSet());

		chatActionManager.doSendTalkInfoAction(sendMess);
	}

	public void closeChat(ActionEvent e) {
		frame.setVisible(false);
	}

}
