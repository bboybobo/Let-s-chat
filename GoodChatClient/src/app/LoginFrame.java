/*登录界面*/
package app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import main.Main;

import util.Conf;
import util.GUIUtil;
import vo.Customer;
import vo.Message;

public class LoginFrame extends  JFrame implements ActionListener{
/*******************************定义各个控件******************************************/
	private Icon welcomeIcon = new ImageIcon("welcome.jpg");
	private JLabel lbWelcome = new JLabel(welcomeIcon);
	private JLabel lbAccount = new JLabel("请输入账号");
	private JTextField tfAccount = new JTextField(10);
	private JLabel lbPassword = new JLabel("请输入密码");
	private JPasswordField pfPasswod = new JPasswordField(10);
	private JButton btLogin = new JButton("登录");
	private JButton btRegister = new JButton("注册");
	private JButton btExit = new JButton("退出");
	
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public LoginFrame() {
		super("登录");
		this.setLayout(new FlowLayout());
		this.add(lbWelcome);
		lbWelcome.setSize(100,100);
		this.add(lbAccount);
		this.add(tfAccount);
		this.add(lbPassword);
		this.add(pfPasswod);
		this.add(btLogin);
		this.add(btRegister);
		this.add(btExit);
		
		this.setSize(240,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		GUIUtil.toCenter(this);
		
		/********************增加监听****************************************/
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);
		btExit.addActionListener(this);
	}

	//登录，得到账户和密码，封装为一个客户对象，再封装为一个消息，消息类型是登录，发送给服务器
	public void login(){
		String account = tfAccount.getText();
		Customer cus = new Customer();
		cus.setAccount(account);
		cus.setPassword(new String(pfPasswod.getPassword()) );
		Message msg = new Message();
		msg.setType(Conf.LOGIN);
		msg.setContent(cus);
		try{
			socket = new Socket(Main.serverIP,Main.port);
			//先发送再接收
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//发送登录请求
			oos.writeObject(msg);
			//接收服务端响应
			Message receiveMessage = (Message)ois.readObject();
			//分析服务端响应的消息
			String type = receiveMessage.getType();
			
			if(type.equals(Conf.LOGINFAIL)){
				//登录失败则显示，并关闭连接
				JOptionPane.showMessageDialog(this, "账户不存在或密码错误！");
				socket.close();
				return;
			}
			//登录成功，转入主聊天界面
			JOptionPane.showMessageDialog(this, "登录成功！");
			this.dispose();
			new ChatFrame(ois,oos,receiveMessage,account);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "登录时异常");
			System.exit(-1);
		}
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btLogin){
			this.login();
		}else if(ae.getSource() == btRegister){
			this.dispose();
			new RegisterFrame();
		}else {
			JOptionPane.showMessageDialog(this, "谢谢光临");
			System.exit(0);
		}
	}
	/*
	public static void main(String[] args){
		new LoginFrame();
	}*/

}
