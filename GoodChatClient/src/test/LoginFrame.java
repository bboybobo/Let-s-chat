//登录界面的测试类
package test;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
public class LoginFrame extends JFrame{
	
	
	
	public LoginFrame() {

		JPanel mainPanel = getPanel();
		this.setContentPane(mainPanel);
		this.setSize(540,410);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel getPanel(){ 
		JPanel mainPanel = new JPanel();
		JTextField tfAccount = new JTextField();
		JTextField tfPassword = new JTextField();
		JButton btLogin = new JButton("登 录");
		JButton btRegister = new JButton("注 册");
		JLabel blTouxiang = new JLabel();
		JLabel lbNorth = new JLabel("Let's Chat!☺");
		
		mainPanel.setLayout(null);
		
		tfAccount.setBounds(195, 165, 240, 30);
		tfPassword.setBounds(195, 200, 240, 30);
		btLogin.setBounds(195, 240, 100, 30);
		//btLogin.setBackground(Color.blue);
		//btLogin.setForeground(Color.white);
		btRegister.setBounds(335,240,100,30);
		//btRegister.setBackground(Color.blue);
		//btRegister.setForeground(Color.white);
		
		blTouxiang.setIcon(new ImageIcon("image/loginImg/login1.jpg"));
		blTouxiang.setBounds(65,165,110,110);
		lbNorth.setFont(new Font("", Font.BOLD, 20));
		lbNorth.setForeground(Color.red);
		lbNorth.setBounds(200,50,300,100);
		
		mainPanel.add(tfAccount);
		mainPanel.add(tfPassword);
		mainPanel.add(btLogin);
		mainPanel.add(btRegister);
		mainPanel.add(blTouxiang);
		mainPanel.add(lbNorth);
		mainPanel.setBackground(Color.pink);
		
		return mainPanel;
		
	}
	public static void main(String[] args) {
		new LoginFrame();
	}

}
