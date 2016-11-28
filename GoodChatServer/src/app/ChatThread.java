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
		//以下两句不能颠倒，否则会造成阻塞（！！！）
		
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void run(){
		try {
			while(canRun){
				Message msg = (Message)ois.readObject();
				/*分析消息类型，进行相应处理转发*/
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
	
	
	/*将消息发送给指定客户端*/
	public void sendMessage(Message msg,String to) throws Exception{
		//对于每一个响应的客户端线程，如果它的客户端账号对应要发送的账号to，则将消息发送给它。
		//当to是Conf.ALL时，将会把消息发送给每个客户端
		for(ChatThread ct : server.getClients()){
			if(ct.customer.getAccount().equals(to) || to.equals(Conf.ALL)){
				ct.oos.writeObject(msg);
			}
		}
	}
	
	//处理登录信息
	public void handleLogin(Message msg)throws Exception{
		Customer loginCustomer = (Customer)msg.getContent();
		String account = loginCustomer.getAccount();
		String pawweord = loginCustomer.getPassword();
		Customer cus = FileOpe.getCustomerByaccount(account);
		Message newMsg = new Message();
		//如果没有该客户或者密码不正确时，发送登陆失败的消息
		if(cus == null || !cus.getPassword().equals(pawweord)){
			newMsg.setType(Conf.LOGINFAIL);		//设置消息类型为登陆失败
			oos.writeObject(newMsg);			//并将消息发送回登录用户
			canRun = false;						//该线程消亡
			socket.close();						//关闭该套接字
			return;
		}
		//当用户名和密码正确时，将该线程放入clients（客户线程）集合
		server.getClients().add(this);
		//并将customer放入在线用户集合（userList）
		this.customer = cus;
		server.getUserList().add(this.customer);
		//并发送所有在线用户列表信息给所有用户
		newMsg.setType(Conf.USERLIST);
		newMsg.setContent(server.getUserList().clone());
		this.sendMessage(newMsg, Conf.ALL);
		
		server.setTitle("当前在线："+ server.getClients().size() +"人");
	}
	
	//处理注册信息
	public void handleRegister(Message msg)throws Exception{
		Customer registerCustomer = (Customer)msg.getContent();
		String account = registerCustomer.getAccount();
		Customer cus = FileOpe.getCustomerByaccount(account);
		Message newMsg = new Message();
		if(cus!=null){//如果该账户已被注册，则发送回注册失败信息
			newMsg.setType(Conf.REGISTERFAIL);
			
		}else{//注册成功
			String password = registerCustomer.getPassword();
			String name = registerCustomer.getName();
			String dept = registerCustomer.getDept();
			FileOpe.insertCustomer(account, password, name, dept);
			newMsg.setType(Conf.REGISTERSUCESS);
		}
		oos.writeObject(newMsg);			//将消息发送给注册用户
		canRun = false;			//注册完之后关闭线程
		socket.close();
		
	}
	
	//处理消息转发
	public void handleMessage(Message msg) throws Exception{
		String to = msg.getTo();
		sendMessage(msg, to);
	}
	
	//向其他所有用户发送一个该客户端已下线的消息
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
		server.setTitle("当前在线："+ server.getClients().size() +"人");
	}
	
	
}
