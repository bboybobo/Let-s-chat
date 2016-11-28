package talk.alvin.biz;

import java.beans.PropertyChangeEvent;

import talk.alvin.AbstractBaseObject;
import talk.alvin.io.FileOperateIO;
import talk.alvin.io.IOManager;
import talk.alvin.io.XMLOperateIO;

public class BaseBiz extends AbstractBaseObject {

	protected static XMLOperateIO xmlIo = IOManager.instance.xmlIO;
	protected static FileOperateIO fileIO = IOManager.instance.fileIo;

	public void propertyChange(PropertyChangeEvent evt) {

	}

}
