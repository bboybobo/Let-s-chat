package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.*;

import util.Conf;
import vo.Customer;
import vo.Message;


public class ChatFrame extends JFrame implements Runnable,ActionListener{
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private boolean canRun = true;
	private String account;
	private JLabel lbUser = new JLabel("在线人员名单");
	private List lstUser = new List();
	private JLabel lbMsg = new JLabel("聊天记录");
	private JTextArea taMsg = new JTextArea();
	private JScrollPane spMsg = new JScrollPane(taMsg);
	private JButton btSendFile = new JButton("发送文件");		//发送文件
	private JTextField tfMsg = new JTextField();
	private JButton btSend = new JButton("发送");
	private JPanel plUser = new JPanel(new BorderLayout());
	private JPanel plMsg = new JPanel(new BorderLayout());
	private JPanel plUser_Msg = new JPanel(new GridLayout(1,2));
	private JPanel plSend = new JPanel(new BorderLayout());
	
	public ChatFrame(ObjectInputStream ois,ObjectOutputStream oos,Message receiveMessage,String account) {
		// TODO Auto-generated constructor stub
		this.ois = ois;
		this.oos = oos;
		this.account  = account;
		this.initFrame();
		this.initUserList(receiveMessage);
		new Thread(this).start();
		
	}
	
	public void initFrame(){
		this.setTitle("当前在线："+account);
		this.setBackground(Color.magenta);
		plUser.add(lbUser,BorderLayout.NORTH);
		plUser.add(lstUser,BorderLayout.CENTER);
		plUser_Msg.add(plUser);
		
		lstUser.setBackground(Color.pink);
		
		plMsg.add(lbMsg,BorderLayout.NORTH);
		plMsg.add(spMsg,BorderLayout.CENTER);
		plUser_Msg.add(plMsg);
		taMsg.setBackground(Color.pink);
		
		plSend.add(btSendFile,BorderLayout.WEST);
		plSend.add(tfMsg,BorderLayout.CENTER);
		plSend.add(btSend,BorderLayout.EAST);
		tfMsg.setBackground(Color.yellow);
		
		this.add(plUser_Msg,BorderLayout.CENTER);
		this.add(plSend,BorderLayout.SOUTH);
		
		btSend.addActionListener(this);
		btSendFile.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setVisible(true);
		
	}
	
	//根据消息初始化在线用户列表
	public void initUserList(Message message){
		lstUser.removeAll();
		lstUser.add(Conf.ALL);
		lstUser.select(0);
		Vector<Customer> userListVector = (Vector<Customer>)message.getContent();
		for(Customer cus : userListVector){
			lstUser.add(cus.getAccount() + "," + cus.getName() +"," + cus.getDept());
			
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
			
			if(ae.getSource() == btSend){
				Message msg = new Message();
				msg.setType(Conf.MESSAGE);
				msg.setContent(account + "说：" + tfMsg.getText());
				msg.setFrom(account);
				String toInfo = lstUser.getSelectedItem();
				msg.setTo(toInfo.split(",")[0]);
				oos.writeObject(msg);
				tfMsg.setText("");
			}else if(ae.getSource() == btSendFile){
				JFileChooser fc = new JFileChooser();
		    	int returnVal = fc.showOpenDialog(this);
		    	if (returnVal == JFileChooser.APPROVE_OPTION) {
		    		Message msg = new Message();
		    		
		            File file = fc.getSelectedFile();		//得到选中的文件
		            String fileName = file.getName();		//得到文件名
		            msg.setType(Conf.FILE+","+fileName);	//在消息类型后面烧带上文件名
		            FileInputStream fis = new FileInputStream(file);
	            	byte[] data = new byte[(int)file.length()];
	            	fis.read(data);							//将文件内容读到data中
	            	fis.close();
	            	msg.setContent(data);					//将消息内容设置为data
	            	
	            	msg.setFrom(account);
					String toInfo = lstUser.getSelectedItem();
					msg.setTo(toInfo.split(",")[0]);
					oos.writeObject(msg);					//发送消息
					taMsg.append("文件已发送"+"\n");				//告知用户文件已发送
		    	}else {
		    		taMsg.append("已取消文件发送"+"\n");
				}
			}
			
			
 		} catch (Exception e) {
			// TODO: handle exception
 			JOptionPane.showMessageDialog(this, "消息发送异常");
		}
	}
	public void run() {
		try {
			while(canRun){
				Message msg = (Message)ois.readObject();
				if(msg.getType().equals(Conf.MESSAGE)){
					//向taMsg中添加内容
					taMsg.append(msg.getContent() + "\n");
				}else if(msg.getType().equals(Conf.USERLIST)){
					this.initUserList(msg);
				}else if (msg.getType().equals(Conf.LOGOUT)) {
					Customer cus = (Customer)msg.getContent();
					lstUser.remove(cus.getAccount() + "," + cus.getName()+","+ cus.getDept());
				}else if(msg.getType().startsWith(Conf.FILE)){
					//收到文件，先让用户选择是否接收，若接收，则新建一个文件，接收；若拒绝，则返回一个拒绝的消息
					String fileName = msg.getType().split(",")[1];//得到文件名
					
					File file = null;
					if(!new File("g:/" +this.account).exists()){
						file = new File("g:/" +this.account);
						file.mkdir();
					}
					
					File receiveFile = new File("g:/" + this.account + "/" +fileName);
					byte[] data = (byte[])msg.getContent();
					FileOutputStream fos = new FileOutputStream(receiveFile);
					fos.write(data);
					fos.close();
					//
					taMsg.append("已接受来自"+ msg.getFrom()+ "的文件："+fileName + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			canRun = false;
			JOptionPane.showMessageDialog(this, "对不起，您被迫下线");
			System.exit(-1);
		}
	}

}
