package org.alvin.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.alvin.bean.Message;
import org.alvin.client.NetClient;

/**
 * 代理方法具体实现
 * 
 * @author 唐植超
 * 
 */
public class NetInvocationHandler implements InvocationHandler {

	// 要处理的对象
	private Object delegate;

	public Object bind(Object delegate) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				delegate.getClass().getInterfaces(), this);
	}

	public Object invoke(Object obj, Method method, Object[] params)
			throws Throwable {
		Method clientMethod = NetClient.class.getDeclaredMethod("process",
				Message.class);
		clientMethod.setAccessible(true);
		// 处理 得到结果
		Message mess = (Message) clientMethod.invoke(NetClient.instance,
				params[0]);
		// 得到接受的数据 并处理
		return method.invoke(delegate, mess);
	}
}
