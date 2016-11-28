package app;
import gui.LoginPanel;

import javax.swing.*;

import util.GUIUtil;
public class LoginFrame extends JFrame{
	LoginPanel loginPanel = new LoginPanel();
	public LoginFrame() {
		super("Nobody is alone!");
		JPanel mainPanel = loginPanel.getPanel();
		this.setContentPane(mainPanel);
		this.setSize(540,410);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);		//居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	public static void main(String[] args) {
		new LoginFrame();
	}*/

}
