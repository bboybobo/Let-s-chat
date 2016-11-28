package talk.alvin;

import java.beans.PropertyChangeEvent;

import talk.alvin.util.ObjectManager;

/**
 * 工作流
 * 
 * @author 唐植超
 * 
 */
public class WorkFlow extends AbstractBaseObject {

	private static final long serialVersionUID = 1L;
	public static final WorkFlow instance = new WorkFlow();

	private WorkFlow() {
	}

	public WorkFlow getInstance() {
		return instance;
	}

	protected String workType;

	public void setWorkType(String workType) {
		this.workType = workType;
		firePropertyChange();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		ObjectManager.instance.clearAllObject();
		// 重新初始化
	}

}
