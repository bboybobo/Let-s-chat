/*��װ�ͻ���Ϣ*/
package beans;


public class Customer{

	private String account;			//�û���,����
	private String password;		//����
	private String avatar;			//ͷ�񣬱���·��
	private String sign;			//����ǩ��
	
	//�չ��캯��
	public Customer() {
	
	}

	//�������Ĺ��캯��
	public Customer(String account, String password, String avatar, String sign) {
		this.account = account;
		this.password = password;
		this.avatar = avatar;
		this.sign = sign;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	

}
