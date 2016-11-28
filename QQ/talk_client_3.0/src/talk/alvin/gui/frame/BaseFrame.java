package talk.alvin.gui.frame;

import javax.swing.JFrame;

import talk.alvin.gui.IComponent;
import talk.alvin.util.Resource;

/**
 * 窗体父类
 * 
 * @author 唐植超
 * 
 */
public abstract class BaseFrame extends JFrame implements IComponent {
	public BaseFrame() {
		setIconImage(Resource.instance.getImage("frame/qq.gif"));
	}

	private static final long serialVersionUID = 1L;

}
