/*��Ϣ�ķ�װ��*/
package vo;

import java.io.Serializable;
public class Message implements Serializable{
	private String type;		//��Ϣ����
	private Object content;		//��Ϣ����
	private String to;			//���շ�
	private String from;		//���ͷ�
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
