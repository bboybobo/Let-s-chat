package talk.alvin.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import talk.alvin.bean.SimpleUser;
import talk.alvin.util.Language;

/**
 * 查找好友Table modle
 * 
 * @author Administrator
 * 
 */
public class SearchFriendTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final String[] HEADNAMES = {
			Language.instance.getValue("search.friend.model.header1"),
			Language.instance.getValue("search.friend.model.header2"),
			Language.instance.getValue("search.friend.model.header3") };
	/** 查询的用户列表 */
	private List<SimpleUser> content = new ArrayList<SimpleUser>();

	public int getColumnCount() {
		return HEADNAMES.length;
	}

	public int getRowCount() {
		return content.size();
	}

	public Object getValueAt(int r, int c) {
		SimpleUser userInfo = content.get(r);
		Object value = null;
		switch (c) {
		case 0:
			value = userInfo.getId();
			break;

		case 1:
			value = userInfo.getName();
			break;
		case 2:
			value = "";
			break;
		}
		return value;
	}

	public String getColumnName(int c) {
		return HEADNAMES[c];
	}

	/**
	 * 改变table的内容
	 * 
	 * @param content
	 */
	public void updateContent(List<SimpleUser> content) {
		this.content = content;
		if (content == null) {
			content = new ArrayList<SimpleUser>();
		}
		fireTableDataChanged();
	}

	/**
	 * 得到选择的值
	 * 
	 * @param row
	 * @return
	 */
	public SimpleUser getSelectedItem(int row) {
		if (row > -1 && row < content.size()) {
			return content.get(row);
		}
		return null;
	}

}
