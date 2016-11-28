/*��¼����*/
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
/*******************************��������ؼ�******************************************/
	private Icon welcomeIcon = new ImageIcon("welcome.jpg");
	private JLabel lbWelcome = new JLabel(welcomeIcon);
	private JLabel lbAccount = new JLabel("�������˺�");
	private JTextField tfAccount = new JTextField(10);
	private JLabel lbPassword = new JLabel("����������");
	private JPasswordField pfPasswod = new JPasswordField(10);
	private JButton btLogin = new JButton("��¼");
	private JButton btRegister = new JButton("ע��");
	private JButton btExit = new JButton("�˳�");
	
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public LoginFrame() {
		super("��¼");
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
		
		/********************���Ӽ���****************************************/
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);
		btExit.addActionListener(this);
	}

	//��¼���õ��˻������룬��װΪһ���ͻ������ٷ�װΪһ����Ϣ����Ϣ�����ǵ�¼�����͸�������
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
			//�ȷ����ٽ���
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//���͵�¼����
			oos.writeObject(msg);
			//���շ������Ӧ
			Message receiveMessage = (Message)ois.readObject();
			//�����������Ӧ����Ϣ
			String type = receiveMessage.getType();
			
			if(type.equals(Conf.LOGINFAIL)){
				//��¼ʧ������ʾ�����ر�����
				JOptionPane.showMessageDialog(this, "�˻������ڻ��������");
				socket.close();
				return;
			}
			//��¼�ɹ���ת�����������
			JOptionPane.showMessageDialog(this, "��¼�ɹ���");
			this.dispose();
			new ChatFrame(ois,oos,receiveMessage,account);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "��¼ʱ�쳣");
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
			JOptionPane.showMessageDialog(this, "лл����");
			System.exit(0);
		}
	}
	/*
	public static void main(String[] args){
		new LoginFrame();
	}*/

}
