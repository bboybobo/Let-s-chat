//注册界面
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

public class RegisterFrame extends JFrame implements ActionListener{
/******************************定义控件************************************************/
	private JLabel lbAccount = new JLabel("请您输入账号");
	private JTextField tfAccount = new JTextField(10);
	private JLabel lbPassword1 = new JLabel("请您输入密码");
	private JPasswordField pfPassword1 = new JPasswordField(10);
	private JLabel lbPassword2 = new JLabel("输入确认密码");
	private JPasswordField pfPassword2 = new JPasswordField(10);
	private JLabel lbName = new JLabel("请您输入姓名");
	private JTextField tfName = new JTextField(10);
	private JLabel lbDept = new JLabel("请您选择部门");
	private JComboBox<String> cbDept = new JComboBox<String>();			//下拉菜单
	private JButton btLogin = new JButton("登录");
	private JButton btRegister = new JButton("注册");
	private JButton btExit = new JButton("退出");
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public RegisterFrame() {
		/********************************界面初始化***********************************************/
		super("注册");
		this.setLayout(new FlowLayout());
		this.add(lbAccount);
		this.add(tfAccount);
		this.add(lbPassword1);
		this.add(pfPassword1);
		this.add(lbPassword2);
		this.add(pfPassword2);
		this.add(lbName);
		this.add(tfName);
		this.add(lbDept);
		this.add(cbDept);
		cbDept.addItem("财务部");
		cbDept.addItem("行政部");
		cbDept.addItem("客户服务部");
		cbDept.addItem("销售部");
		this.add(btRegister);
		this.add(btLogin);
		this.add(btExit);
		this.setSize(240,220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		GUIUtil.toCenter(this);
		
		/**************************************增加监听********************************************/
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);
		btExit.addActionListener(this);
		
	}

	/*处理注册，从输入框得到用户信息，封装为一个客户对象，并封装为注册信息发送给服务器，接收服务器发回的消息，分析解释*/
	public void register(){
		Customer cus = new Customer();
		cus.setAccount(tfAccount.getText());
		cus.setPassword(new String(pfPassword1.getPassword()));
		cus.setName(tfName.getText());
		cus.setDept((String)cbDept.getSelectedItem());
		Message msg = new Message();
		msg.setType(Conf.REGISTER);
		msg.setContent(cus);
		try {
			socket = new Socket(Main.serverIP,Main.port);
			//有顺序
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			Message receiveMsg = null;
			oos.writeObject(msg);
			receiveMsg = (Message)ois.readObject();
			String type = receiveMsg.getType();
			if(type.equals(Conf.REGISTERFAIL)){
				JOptionPane.showMessageDialog(this, "该账号已被注册");
			}else {
				JOptionPane.showMessageDialog(this, "注册成功");
			}
			socket.close();
		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "注册时发生异常");
			System.exit(-1);
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btLogin){
			this.dispose();
			new LoginFrame();
		}else if(ae.getSource() == btRegister){
			String password1 = new String(pfPassword1.getPassword());
			String password2 = new String(pfPassword2.getPassword());
			if(!password1.equals(password2)){
				JOptionPane.showMessageDialog(this, "两个密码不相同");
				return;
			}
			this.register();
		}else {
			JOptionPane.showMessageDialog(this, "谢谢光临");
			System.exit(0);
		}
	}

}
