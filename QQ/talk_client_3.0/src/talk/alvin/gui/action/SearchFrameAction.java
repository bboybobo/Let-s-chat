package talk.alvin.gui.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTable;

import talk.alvin.bean.SimpleUser;
import talk.alvin.bean.UserInfoBean;
import talk.alvin.gui.GUIManager;
import talk.alvin.gui.frame.SearchFrame;
import talk.alvin.gui.model.SearchFriendTableModel;
import talk.alvin.manager.ISearchActionManager;
import talk.alvin.manager.ManagerManager;
import talk.alvin.util.MessageBox;
import talk.alvin.util.ObjectManager;

/**
 * 查找好友
 * 
 * @author 唐植超
 * 
 */
public class SearchFrameAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ISearchActionManager searchManager = ManagerManager.instance.searchActionManager;

	/**
	 * 添加好友
	 * 
	 * @param e
	 */
	public void clickAddFirendButtonAction(ActionEvent e) {
		SearchFrame searchFrame = (SearchFrame) GUIManager.searchFrame
				.getFrame();
		JTable table = searchFrame.getTable();
		int groupIndex = 0;
		int selectRow = table.getSelectedRow();
		if (selectRow == -1) {
			MessageBox.promptWarning("情选择一个用户！");
			return;
		}
		SearchFriendTableModel model = (SearchFriendTableModel) table
				.getModel();
		SimpleUser user = model.getSelectedItem(selectRow);
		UserInfoBean currentUser = (UserInfoBean) ObjectManager.instance
				.getAttribute("currentUser");
		if (currentUser.getFirendInfoMap().containsKey(user.getId())) {
			MessageBox.promptMessage("该好友已经存在！");
			return;
		}
		SimpleUser firend = searchManager.clickAddFirendButtonAction(user
				.getId(), currentUser.getId(), groupIndex);
		if (firend != null) {
			String groupName = currentUser.getGroupList().get(groupIndex);
			List<String> firendList = currentUser.getFriendMap().get(groupName);
			firendList.add(firend.getId());
			currentUser.getFirendInfoMap().put(firend.getId(), firend);
			MessageBox.promptMessage("添加好友成功！");
			initMainFrame(currentUser);
		} else {
			MessageBox.promptMessage("添加失败，请检查网络！");
		}
		searchFrame.changePanel(searchFrame.getSearchPane());
	}

	public void clickSearchFirendsListButtonAction(ActionEvent e) {
		SearchFrame searchFrame = (SearchFrame) GUIManager.searchFrame
				.getFrame();
		String id = searchFrame.getLoginIdTextField().getText().trim();
		String nickName = searchFrame.getNickNameTextField().getText().trim();
		List<SimpleUser> userList = searchManager
				.clickSearchFirendsListButtonAction(id, nickName);
		JTable table = searchFrame.getTable();
		SearchFriendTableModel model = (SearchFriendTableModel) table
				.getModel();
		model.updateContent(userList);
		searchFrame.changePanel(searchFrame.getResultPane());
	}

	public void clickCancelButtonAction(ActionEvent e) {
		GUIManager.searchFrame.hide();
	}

}
