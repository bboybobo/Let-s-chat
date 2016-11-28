package talk.alvin.util;

/**
 * 日志管理类
 * 
 * @author 唐植超
 * 
 */
public enum Log {
	
	instance;

	public void info(String mess) {
		System.err.println(mess);
	}

	public void debug(String mess) {
		System.err.println(mess);
	}

	public void warn(String mess) {
		System.err.println(mess);
	}

	public void error(String mess) {
		System.err.println(mess);
	}
}
