package org.alvin.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ServerSocketFactory;

import org.alvin.bean.Message;
import org.alvin.io.ActionConfigerXMLOperate;
import org.alvin.io.NetConfigerXMLOperate;
import org.dom4j.DocumentException;

/**
 * 服务器 [初始化 ，加载和实例化 ， 服务 ， 销毁]
 * 
 * @author 唐植超
 * 
 */
public enum NetServer {

	instace;

	/** 服务器 */
	private ServerSocket server;
	/** 线程池 */
	private ExecutorService threadPool;
	/** 是否销毁了 */
	private boolean destroy = false;

	/**
	 * 初始化
	 */
	private void init() {
		System.out.println("初始化服务器");
		try {
			// 服务器初始化
			NetConfigerXMLOperate xml = NetConfigerXMLOperate.instance;
			int port = Integer.parseInt(xml.getText("Port"));
			int person = Integer.parseInt(xml.getText("Person"));
			InetAddress ip = InetAddress.getByName(xml.getText("Address"));
			server = ServerSocketFactory.getDefault().createServerSocket(port,
					person, ip);
			// 线程池初始化
			threadPool = Executors.newFixedThreadPool(Integer.parseInt(xml
					.getText("ThreadPool-Num")));
			// 加载
			load();
			System.out.println("服务器启动成功");
			// 处理
			process();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("初始化服务器失败");
			destroy();
		}
	}

	/**
	 * 加载信息
	 * 
	 * @throws DocumentException
	 */
	private void load() throws DocumentException {
		System.out.println("加载信息");
	}

	/**
	 * 进入服务
	 * 
	 * @param client
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void service(Socket client) {
		// System.out.println("进入服务");
		try {
			Message mess = recevie(client);
			mess = request(mess);
			send(client, mess);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 请求调用方法
	 * 
	 * @param mess
	 * @return
	 */
	private Message request(Message mess) {
		try {
			ActionConfigerXMLOperate configer = ActionConfigerXMLOperate.instance;
			Object obj = configer.getServerObject(mess.getClassNameId());
			Method function = obj.getClass().getDeclaredMethod(
					mess.getFunctionName(), Message.class);
			mess = (Message) function.invoke(obj, mess);
			return mess;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 信息处理入口
	 * 
	 * @param client
	 */
	private void process() {
		// 等待客户端
		try {
			if (server == null) {
				System.out.println("服务器未启动");
				return;
			}
			while (!destroy) {
				threadPool.execute(new ServerThread(server.accept()));
			}
			// 销毁服务器
			destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 销毁信息
	 */
	private void destroy() {
		// 序列化信息
		saveData();
		// 停止服务器
		System.out.println("服务器销毁");
		stop();
	}

	/**
	 * 启动服务器
	 */
	public void start() {
		System.out.println("启动服务器");
		System.out.println("名称：java网络编程框架");
		System.out.println("版本：3.0.0.0");
		System.out.println("作者：唐植超");
		System.out.println("邮箱：alvin198761@163.com");
		System.out.println("QQ：641652148");
		System.out.println("欢迎使用,同时欢迎与作者联系");
		destroy = false;
		init();
	}

	/**
	 * 停止服务器
	 */
	public void stop() {
		System.out.println("停止服务器");
		try {
			destroy = true;
			Thread.sleep(3000);// 停止3秒 执行其他线程停止
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 退出系统
		System.exit(0);
	}

	/**
	 * 保存信息
	 */
	private void saveData() {
		System.out.println("保存信息");
	}

	/**
	 * 接收客户端传来的信息
	 * 
	 * @param client
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private Message recevie(Socket client) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		Message mess = (Message) ois.readObject();
		// ois.close();
		return mess;
	}

	/**
	 * 发送信息到客户端
	 * 
	 * @param client
	 * @param mess
	 * @throws IOException
	 */
	private void send(Socket client, Message mess) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(client
				.getOutputStream());
		oos.writeObject(mess);
		oos.flush();
		// oos.close();
	}

	/**
	 * 服务器处理线程
	 * 
	 * @author Administrator
	 * 
	 */
	private class ServerThread implements Runnable {

		private Socket client;

		public ServerThread(Socket client) {
			this.client = client;
		}

		public void run() {
			if (client != null) {
				// System.out.println("进入处理");
				// System.out.println("IP地址：" + client.getInetAddress());
				// System.out.println("端口：" + client.getPort());
				// System.out.println("时间："
				// + FormartUtil.instance.formatDateToString(new Date()));
				service(client);
			}
		}
	}
}
