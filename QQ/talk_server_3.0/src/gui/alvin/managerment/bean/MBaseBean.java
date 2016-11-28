package gui.alvin.managerment.bean;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;

import talk.alvin.AbstractBaseObject;


/**
 * 实体类父类
 * 
 * @author 唐植超
 * 
 */
public class MBaseBean extends AbstractBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 唯一编号 */
	protected String id;
	/** 名称 */
	protected String name;

	public void propertyChange(PropertyChangeEvent evt) {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
