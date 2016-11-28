package talk.alvin.bean;

/**
 * 简单user对象，用于传输和显示的载体
 * 
 * @author 唐植超
 * 
 */
public class SimpleUser extends BaseBean {

	private static final long serialVersionUID = 1L;
	// 个人说明
	private String remake;
	// 图像标号
	private String faceId;
	// 当前状态
	private String state;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
