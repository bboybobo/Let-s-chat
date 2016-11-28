package org.alvin.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import org.alvin.bean.Message;
import org.alvin.io.NetConfigerXMLOperate;
import org.dom4j.DocumentException;

/**
 * 客户端
 * 
 * @author 唐植超
 * 
 */
public class NetClient {

	public static final NetClient instance = new NetClient();
	/** 端口号 */
	private int port = 0;
	/** ip地址 */
	private String host;

	private NetClient() {
		start();
	}

	/**
	 * 初始化客户端
	 */
	private void init() {
		System.out.println("启动客户端");
		// 加载其他信息
		try {
			load();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载客户端信息
	 * 
	 * @throws DocumentException
	 */
	private void load() throws DocumentException {
		System.out.println("加载信息");
		NetConfigerXMLOperate xml = NetConfigerXMLOperate.instance;
		try {
			port = Integer.parseInt(xml.getText("Port"));
			host = xml.getText("Address");
			InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			stop();
		}
	}

	/**
	 * 接受请求
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private Message recevie(Socket client) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		Message mess = (Message) ois.readObject();
		ois.close();
		client.close();
		return mess;
	}

	/**
	 * 发送请求
	 * 
	 * @throws IOException
	 */
	private void send(Message mess, Socket client) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(client
				.getOutputStream());
		oos.writeObject(mess);
		oos.flush();
		// oos.close();
	}

	/**
	 * 处理
	 */
	protected Message process(Message mess) {
		try {
			// System.out.println("处理请求，接收相应");
			// 初始化客户端
			Socket client = SocketFactory.getDefault().createSocket(host, port);// new
			// Socket(ip, port);
			client.setSoTimeout(60 * 1000);
			send(mess, client);
			return recevie(client);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return process(mess);
		}
		return null;
	}

	/**
	 * 启动客户端
	 */
	public void start() {
		System.out.println("开始启动客户端");
		init();
	}

	/**
	 * 停止客户端
	 */
	public void stop() {
		System.out.println("停止客户端");
		System.exit(0);
	}
}
