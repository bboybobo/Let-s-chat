package app;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.Vector;
import javax.swing.*;
import beans.Customer;
public class Server extends JFrame implements Runnable{
	//�ͻ�������
	private Socket socket = null;
	//�������˽�������
	private ServerSocket serverSocket = null;
	//�����ͻ��˵��߳�
	private Vector<ChatThread> clients = new Vector<ChatThread>();
	//���������û�
	private Vector<Customer> userList = new Vector<Customer>();
	private JButton jbt = new JButton("�رշ�����");
	private boolean canRun = true;
	public Server() throws Exception{
		this.setTitle("��������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(jbt,BorderLayout.NORTH);
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.setBackground(Color.yellow);
		this.setSize(400,250);
		this.setVisible(true);
		//���������ٶ˿ڣ���������
		serverSocket = new ServerSocket(9999);
		//���ܿͻ��˵�����ѭ����ʼ����
		new Thread(this).start();
	}
	public void run() {
		try {
			while(canRun){
				socket = serverSocket.accept();
				ChatThread ct = new ChatThread(socket,this);
				ct.start();
			}
		} catch (Exception e) {
			canRun = false;
			try {
				serverSocket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public Vector<ChatThread> getClients(){
		return clients;
	}
	public Vector<Customer> getUserList(){
		return userList;
	}

}
