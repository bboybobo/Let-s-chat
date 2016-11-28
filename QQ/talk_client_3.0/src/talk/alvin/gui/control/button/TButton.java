package talk.alvin.gui.control.button;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import talk.alvin.gui.control.ComponentUtil;

/**
 * 绋嬪簭涓墍鏈夌殑button
 * 
 * @author Administrator
 * 
 */
public class TButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	// 鍒涘缓涓嶅甫鏈夎缃枃鏈垨鍥炬爣鐨勬寜閽��
	public TButton() {
		super();
		addMouseListener(this);
	}

	// 鍒涘缓涓�涓寜閽紝鍏跺睘鎬т粠鎵�鎻愪緵鐨� Action 涓幏鍙栥��
	public TButton(Action a) {
		super(a);
		addMouseListener(this);
	}

	// 鍒涘缓涓�涓甫鍥炬爣鐨勬寜閽��
	public TButton(Icon icon) {
		super(icon);
		addMouseListener(this);
	}

	// 鍒涘缓涓�涓甫鏂囨湰鐨勬寜閽��
	public TButton(String text) {
		super(text);
		addMouseListener(this);
	}

	// 鍒涘缓涓�涓甫鍒濆鏂囨湰鍜屽浘鏍囩殑鎸夐挳銆�
	public TButton(String text, Icon icon) {
		super(text, icon);
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		setCursor(ComponentUtil.HAND);
	};

	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent e) {
	};

	public void mouseReleased(MouseEvent e) {

	}
}
