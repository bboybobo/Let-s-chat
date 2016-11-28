package talk.alvin.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户类的父类
 * 
 * @author 唐植超
 * 
 */
public class UserInfoBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	// 所有的组
	private List<String> groupList = new ArrayList<String>();
	// 组和好友的关系
	private Map<String, List<String>> friendMap = new LinkedHashMap<String, List<String>>();
	// [userId ,UserInfoBean]
	private Map<String, SimpleUser> firendInfoMap = new LinkedHashMap<String, SimpleUser>();
	// 邮件
	private String mail;
	// 电话
	private String phone;
	// 手机
	private String handPhone = "";
	// 血型
	private int bloodId = 0;
	// 星座
	private int starId = 0;
	// 生日年份
	private String birthdayYear;
	// 生日月份
	private String birthdayMouth;
	// 生日日期
	private String birthdayDay;
	// 表情编号
	private String faceId;
	// 描述
	private String description;
	// 说明
	private String remark;
	// 性别
	private String sex;
	// 等级
	private int level = 1;
	// 公司
	private String company;
	// 国家
	private String country = "中国";
	// 省份
	private String province = "湖南";
	// 城市
	private String city = "常德";
	// 真实姓名
	private String nickName;
	// 验证密码
	private String password;
	// 年龄
	private String age;
	// 是否能登陆
	private boolean login = false;

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public UserInfoBean() {

	}

	public List<String> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}

	public Map<String, List<String>> getFriendMap() {
		return friendMap;
	}

	public void setFriendMap(Map<String, List<String>> friendMap) {
		this.friendMap = friendMap;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHandPhone() {
		return handPhone;
	}

	public void setHandPhone(String handPhone) {
		this.handPhone = handPhone;
	}

	public int getBloodId() {
		return bloodId;
	}

	public void setBloodId(int bloodId) {
		this.bloodId = bloodId;
	}

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public String getBirthdayMouth() {
		return birthdayMouth;
	}

	public void setBirthdayMouth(String birthdayMouth) {
		this.birthdayMouth = birthdayMouth;
	}

	public String getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		if (remark == null || remark.length() == 0) {
			return "晕，这么懒啊，写个签名看看……";
		} else {
			return remark;
		}
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, SimpleUser> getFirendInfoMap() {
		return firendInfoMap;
	}

	public void setFirendInfoMap(Map<String, SimpleUser> firendInfoMap) {
		this.firendInfoMap = firendInfoMap;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String toString() {
		return nickName.concat("(" + id + ")");
	}
}
