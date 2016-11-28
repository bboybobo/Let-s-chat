/*登录面板*/
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class LoginPanel extends JPanel{
	/*定义控件***************************************************/
	public JPanel mainPanel = new JPanel();							//主面板
	public JTextField tfAccount = new JTextField();					//账号输入框
	public JPasswordField pfPassword = new JPasswordField();		//密码输入框
	public JButton btLogin = new JButton("登 录");					
	public JButton btRegister = new JButton("注 册");
	public JLabel lbTouxiang = new JLabel();						//放头像
	public JLabel lbNorth = new JLabel("Let's Chat!☺");				//上面的logo
	
	public JPanel getPanel(){ 
		
		//设置面板为空布局
		mainPanel.setLayout(null);
		//设置各个控件的位置，大小和样式
		tfAccount.setBounds(195, 165, 240, 30);
		pfPassword.setBounds(195, 200, 240, 30);
		btLogin.setBounds(195, 240, 100, 30);
		btRegister.setBounds(335,240,100,30);
		lbTouxiang.setIcon(new ImageIcon("image/loginImg/login1.jpg"));
		lbTouxiang.setBounds(65,165,110,110);
		lbNorth.setFont(new Font("", Font.BOLD, 20));
		lbNorth.setForeground(Color.red);
		lbNorth.setBounds(200,50,300,100);
		
		//添加各个控件
		mainPanel.add(tfAccount);
		mainPanel.add(pfPassword);
		mainPanel.add(btLogin);
		mainPanel.add(btRegister);
		mainPanel.add(lbTouxiang);
		mainPanel.add(lbNorth);
		
		//设置整个面板的背景色
		mainPanel.setBackground(Color.pink);
		
		return mainPanel;
	}
}
