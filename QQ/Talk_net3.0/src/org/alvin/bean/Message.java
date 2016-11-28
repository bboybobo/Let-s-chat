package org.alvin.bean;

import org.alvin.BaseObject;
/**
 * 网络传输的对象
 * @author Administrator
 *
 */
public class Message extends BaseObject {
 
	private static final long serialVersionUID = 1L;
	/**action类的ID*/
	private String classNameId;
	/**请求动作*/
	private String functionName;
	/**请求内容*/
	private Object userObject;

	public String getClassNameId() {
		return classNameId;
	}

	public void setClassNameId(String classNameId) {
		this.classNameId = classNameId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Object getUserObject() {
		return userObject;
	}

	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}
}
