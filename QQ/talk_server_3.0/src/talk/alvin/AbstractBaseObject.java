package talk.alvin;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import talk.alvin.util.Log;

/**
 * 对象的父类
 * 
 * @author 唐植超
 * 
 */
public abstract class AbstractBaseObject implements PropertyChangeListener {

	private PropertyChangeSupport support;
	protected static Log log = Log.instance;

	public AbstractBaseObject() {
		support = new PropertyChangeSupport(this);
		support.addPropertyChangeListener(this);
	}

	public void firePropertyChange() {
		support.firePropertyChange("factoryType", true, false);
	}
}
