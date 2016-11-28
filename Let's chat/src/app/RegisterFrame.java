//注册
package app;

import gui.RegisterPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame{
	RegisterPanel registerPanel = new RegisterPanel();
	public RegisterFrame() {
		super("欢迎注册!");
		JPanel mainPanel = registerPanel.getPanel();
		this.setContentPane(mainPanel);
		this.setSize(660,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);		//居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public static void main(String[] args) {
		new RegisterFrame();
	}

}
