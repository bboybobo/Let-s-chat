package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.Conf;
import util.FileOpe;
import vo.Customer;
import vo.Message;

public class ChatThread extends Thread{
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private Customer customer = null;
	private Server server;
	private boolean canRun = true;
	public ChatThread(Socket socket,Server server) throws Exception{
		this.socket = socket;
		this.server = server;
		//�������䲻�ܵߵ�������������������������
		
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void run(){
		try {
			while(canRun){
				Message msg = (Message)ois.readObject();
				/*������Ϣ���ͣ�������Ӧ����ת��*/
				String type = msg.getType();
				if(type.equals(Conf.LOGIN)){
					this.handleLogin(msg);
				}else if(type.equals(Conf.REGISTER)){
					this.handleRegister(msg);
				}else if(type.equals(Conf.MESSAGE) || type.startsWith(Conf.FILE) ){
					this.handleMessage(msg);
				}
			}
			
		} catch (Exception e) {
			this.hanleLogout();
		}
	}
	
	
	/*����Ϣ���͸�ָ���ͻ���*/
	public void sendMessage(Message msg,String to) throws Exception{
		//����ÿһ����Ӧ�Ŀͻ����̣߳�������Ŀͻ����˺Ŷ�ӦҪ���͵��˺�to������Ϣ���͸�����
		//��to��Conf.ALLʱ���������Ϣ���͸�ÿ���ͻ���
		for(ChatThread ct : server.getClients()){
			if(ct.customer.getAccount().equals(to) || to.equals(Conf.ALL)){
				ct.oos.writeObject(msg);
			}
		}
	}
	
	//�����¼��Ϣ
	public void handleLogin(Message msg)throws Exception{
		Customer loginCustomer = (Customer)msg.getContent();
		String account = loginCustomer.getAccount();
		String pawweord = loginCustomer.getPassword();
		Customer cus = FileOpe.getCustomerByaccount(account);
		Message newMsg = new Message();
		//���û�иÿͻ��������벻��ȷʱ�����͵�½ʧ�ܵ���Ϣ
		if(cus == null || !cus.getPassword().equals(pawweord)){
			newMsg.setType(Conf.LOGINFAIL);		//������Ϣ����Ϊ��½ʧ��
			oos.writeObject(newMsg);			//������Ϣ���ͻص�¼�û�
			canRun = false;						//���߳�����
			socket.close();						//�رո��׽���
			return;
		}
		//���û�����������ȷʱ�������̷߳���clients���ͻ��̣߳�����
		server.getClients().add(this);
		//����customer���������û����ϣ�userList��
		this.customer = cus;
		server.getUserList().add(this.customer);
		//���������������û��б���Ϣ�������û�
		newMsg.setType(Conf.USERLIST);
		newMsg.setContent(server.getUserList().clone());
		this.sendMessage(newMsg, Conf.ALL);
		
		server.setTitle("��ǰ���ߣ�"+ server.getClients().size() +"��");
	}
	
	//����ע����Ϣ
	public void handleRegister(Message msg)throws Exception{
		Customer registerCustomer = (Customer)msg.getContent();
		String account = registerCustomer.getAccount();
		Customer cus = FileOpe.getCustomerByaccount(account);
		Message newMsg = new Message();
		if(cus!=null){//������˻��ѱ�ע�ᣬ���ͻ�ע��ʧ����Ϣ
			newMsg.setType(Conf.REGISTERFAIL);
			
		}else{//ע��ɹ�
			String password = registerCustomer.getPassword();
			String name = registerCustomer.getName();
			String dept = registerCustomer.getDept();
			FileOpe.insertCustomer(account, password, name, dept);
			newMsg.setType(Conf.REGISTERSUCESS);
		}
		oos.writeObject(newMsg);			//����Ϣ���͸�ע���û�
		canRun = false;			//ע����֮��ر��߳�
		socket.close();
		
	}
	
	//������Ϣת��
	public void handleMessage(Message msg) throws Exception{
		String to = msg.getTo();
		sendMessage(msg, to);
	}
	
	//�����������û�����һ���ÿͻ��������ߵ���Ϣ
	public void hanleLogout(){
		Message logoutMessage = new Message();
		logoutMessage.setType(Conf.LOGOUT);
		logoutMessage.setContent(this.customer);
		server.getClients().remove(this);
		server.getUserList().remove(this.customer);
		try{
			sendMessage(logoutMessage, Conf.ALL);
			canRun = false;
			socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		server.setTitle("��ǰ���ߣ�"+ server.getClients().size() +"��");
	}
	
	
}
