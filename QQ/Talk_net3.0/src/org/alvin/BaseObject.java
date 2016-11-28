package org.alvin;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * 所有类的父类
 * 
 * @author 唐植超
 * 
 */
public class BaseObject extends Object implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	/** 唯一编号 */
	private String id;
	/** 名称 */
	private String name;
	/** 支持属性更时 发消息 */
	protected PropertyChangeSupport support;

	public BaseObject() {
		support = new PropertyChangeSupport(this);
		id = getTimeId();
	}

	protected BaseObject clone() throws CloneNotSupportedException {
		return (BaseObject) super.clone();
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseObject)) {
			return false;
		}
		return id.equals(((BaseObject) obj).id);
	}

	public String toString() {
		return id + "[" + name + "]";
	}

	public int hashCode() {
		return id.hashCode();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static long currentTime = System.nanoTime();

	private static String getTimeId() {
		return currentTime+++"";
	}

}
