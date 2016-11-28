//注册界面的容器类：采用空布局
package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterPanel {
	/*定义主面板和控件***************************************************/
	public JPanel mainPanel = new JPanel();							//主面板
	
	public JLabel lbNorth = new JLabel("Let's Chat!☺");				//上面的logo
	public JLabel lbAccount = new JLabel(" 昵 称:");						//账号
	public JTextField tfAccount = new JTextField();					//账号输入框
	public JLabel lbPassword = new JLabel(" 密 码:");						
	public JPasswordField pfPassword = new JPasswordField();		//密码输入框
	public JLabel lbConfirmPwd = new JLabel("确认密码:");	
	public JPasswordField pfConfirmPwd = new JPasswordField();
	public JLabel lbAvatar = new JLabel("个性签名:");	
	public JTextArea taAvatar = new JTextArea();					//个性签名输入框
	
	public JButton btRegister = new JButton("注 册");
	public JButton btLogin = new JButton("登 录");					
	
	
	
	
	public JPanel getPanel(){ 
		
		//设置面板为空布局
		mainPanel.setLayout(null);
		//设置各个控件的位置，大小和样式
		lbNorth.setFont(new Font("", Font.BOLD, 30));
		lbNorth.setForeground(Color.red);
		lbNorth.setBounds(265,50,200,100);
		
		//账号
		lbAccount.setBounds(165, 135, 60, 30);
		tfAccount.setBounds(225, 135, 240, 30);
		//密码
		lbPassword.setBounds(165,170,60,30);
		pfPassword.setBounds(225, 170, 240, 30);
		//确认密码
		lbConfirmPwd.setBounds(165, 205, 60, 30);
		pfConfirmPwd.setBounds(225, 205, 240, 30);
		//个性签名
		lbAvatar.setBounds(165,240,60,30);
		taAvatar.setBounds(225, 240, 240, 60);
		
		//注册登录按钮
		btRegister.setBounds(225, 305, 100, 30);
		btLogin.setBounds(375,305,100,30);
	
	
		
		//添加各个控件
		mainPanel.add(lbNorth);
		
		mainPanel.add(lbAccount);
		mainPanel.add(tfAccount);
		
		mainPanel.add(lbPassword);
		mainPanel.add(pfPassword);
		
		mainPanel.add(lbConfirmPwd);
		mainPanel.add(pfConfirmPwd);
		
		mainPanel.add(lbAvatar);
		mainPanel.add(taAvatar);
		
		mainPanel.add(btLogin);
		mainPanel.add(btRegister);
		
		
		//设置整个面板的背景色
		mainPanel.setBackground(Color.pink);
		
		return mainPanel;
	}
	

}
