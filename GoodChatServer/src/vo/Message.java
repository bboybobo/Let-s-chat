/*消息的封装类*/
package vo;

import java.io.Serializable;
public class Message implements Serializable{
	private String type;		//消息类型
	private Object content;		//消息内容
	private String to;			//接收方
	private String from;		//发送方
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
}
