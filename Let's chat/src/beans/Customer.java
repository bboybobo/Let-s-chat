/*客户（用户）*/
package beans;


public class Customer{

	private String account;			//账号，唯一性
	private String password;		//密码
	private String avatar;			//头像
	private String sign;			//个性签名
	
	//空的构造函数
	public Customer() {
	
	}

	//带参数的构造函数
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
