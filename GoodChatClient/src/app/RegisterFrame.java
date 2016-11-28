//ע�����
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
/******************************����ؼ�************************************************/
	private JLabel lbAccount = new JLabel("���������˺�");
	private JTextField tfAccount = new JTextField(10);
	private JLabel lbPassword1 = new JLabel("������������");
	private JPasswordField pfPassword1 = new JPasswordField(10);
	private JLabel lbPassword2 = new JLabel("����ȷ������");
	private JPasswordField pfPassword2 = new JPasswordField(10);
	private JLabel lbName = new JLabel("������������");
	private JTextField tfName = new JTextField(10);
	private JLabel lbDept = new JLabel("����ѡ����");
	private JComboBox<String> cbDept = new JComboBox<String>();			//�����˵�
	private JButton btLogin = new JButton("��¼");
	private JButton btRegister = new JButton("ע��");
	private JButton btExit = new JButton("�˳�");
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public RegisterFrame() {
		/********************************�����ʼ��***********************************************/
		super("ע��");
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
		cbDept.addItem("����");
		cbDept.addItem("������");
		cbDept.addItem("�ͻ�����");
		cbDept.addItem("���۲�");
		this.add(btRegister);
		this.add(btLogin);
		this.add(btExit);
		this.setSize(240,220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		GUIUtil.toCenter(this);
		
		/**************************************���Ӽ���********************************************/
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);
		btExit.addActionListener(this);
		
	}

	/*����ע�ᣬ�������õ��û���Ϣ����װΪһ���ͻ����󣬲���װΪע����Ϣ���͸������������շ��������ص���Ϣ����������*/
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
			//��˳��
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			Message receiveMsg = null;
			oos.writeObject(msg);
			receiveMsg = (Message)ois.readObject();
			String type = receiveMsg.getType();
			if(type.equals(Conf.REGISTERFAIL)){
				JOptionPane.showMessageDialog(this, "���˺��ѱ�ע��");
			}else {
				JOptionPane.showMessageDialog(this, "ע��ɹ�");
			}
			socket.close();
		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "ע��ʱ�����쳣");
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
				JOptionPane.showMessageDialog(this, "�������벻��ͬ");
				return;
			}
			this.register();
		}else {
			JOptionPane.showMessageDialog(this, "лл����");
			System.exit(0);
		}
	}

}
