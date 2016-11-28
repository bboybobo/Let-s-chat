package talk.alvin.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import talk.alvin.bean.UserInfoBean;
import talk.alvin.gui.GUIManager;
import talk.alvin.gui.control.label.ValidateCodeLabel;
import talk.alvin.gui.frame.RegisterFrame;
import talk.alvin.manager.IRegisterActionManager;
import talk.alvin.manager.ManagerManager;
import talk.alvin.util.Language;
import talk.alvin.util.MessageBox;
import talk.alvin.util.StringUtil;

/**
 * 处理用户注册
 * 
 * @author 唐植超
 * 
 */
public class RegisterFrameAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private IRegisterActionManager registerActionManager = ManagerManager.instance.registerActionManager;

	public void clickRegisterOKButtonAction(ActionEvent e) {
		if (validateInput()) {
			String passwordS;
			RegisterFrame rf = (RegisterFrame) GUIManager.registerFrame
					.getFrame();
			JTextField nickNameTextField = rf.getNickNameTextField();
			JTextField ageTextField = rf.getAgeTextField();
			boolean gender = rf.getBoyRadioButton().isSelected();
			JPasswordField password = rf.getRegisterPasswordField();
			// JPasswordField confirmPassword = rf.getConfirmPasswordText();
			JTextField emailTextField = rf.getEmailTextField();
			// JTextField addressTextField = rf.getAddressTextField();
			JComboBox starComboBox = rf.getStarComboBox();
			JComboBox bloodComboBox = rf.getBloodComboBox();
			// JTextField birthDay = rf.getBirthdayTextField();
			// JTextField validataCodeTextField = rf.getValidataCodeTextField();
			// ValidateCodeLabel validateLabel = rf.getShowCodeLabel();
			// String tempText = nickNameTextField.getText().trim();
			UserInfoBean userInfo = new UserInfoBean();
			userInfo.setNickName(nickNameTextField.getText());
			userInfo.setAge(ageTextField.getText().trim());
			userInfo.setSex(gender ? Language.instance
					.getValue("register.boyRadioButton.text")
					: Language.instance
							.getValue("register.grilRadioButton.text"));
			// 前台加密
			passwordS = StringUtil.instance.encoderByMd5(new String(password
					.getPassword()));
			userInfo.setPassword(passwordS);
			userInfo.setMail(emailTextField.getText().trim());

			// userInfo.setAddress(addressTextField.getText().trim());
			userInfo.setStarId(starComboBox.getSelectedIndex());
			userInfo.setBloodId(bloodComboBox.getSelectedIndex());
			// userInfo.setBirthday(birthDay.getText().trim());
			userInfo.setFaceId("1");
			// userInfo.setFriendly(Language.instance.getValue("friendly.f"));
			// userInfo.setLogin(false);
			registerActionManager.doRegister(userInfo);
		}
	}

	public void clickCancelRegisterButtonAction(ActionEvent e) {
		GUIManager.registerFrame.hide();
		GUIManager.loginFrame.show();
	}

	/**
	 * 验证输入
	 * 
	 * @return
	 */
	private boolean validateInput() {
		RegisterFrame rf = (RegisterFrame) GUIManager.registerFrame.getFrame();
		JTextField nickNameTextField = rf.getNickNameTextField();
		JTextField ageTextField = rf.getAgeTextField();
		JPasswordField password = rf.getRegisterPasswordField();
		JPasswordField confirmPassword = rf.getConfirmPasswordText();
		JTextField emailTextField = rf.getEmailTextField();
		JTextField validataCodeTextField = rf.getValidataCodeTextField();
		ValidateCodeLabel validateLabel = rf.getShowCodeLabel();
		String tempText = nickNameTextField.getText().trim();
		if (!tempText.matches("^\\w{6,20}$")) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.nickNameError"), rf);
			return false;
		}
		tempText = ageTextField.getText().trim();
		if (!tempText.matches("^\\d+$")) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.ageIsNotNumber"), rf);
			return false;
		}
		if (Integer.parseInt(tempText) > 150 || Integer.parseInt(tempText) < 18) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.ageRangeError"), rf);
			return false;
		}
		tempText = new String(password.getPassword());
		if (!tempText.matches("^.{6,20}$")) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.passwordError"), rf);
			return false;
		}
		if (!tempText.equals(new String(confirmPassword.getPassword()))) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.confirmPasswordError"), rf);
			return false;
		}
		tempText = emailTextField.getText().trim();
		if (!tempText
				.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.emailFormart"), rf);
			return false;
		}
		// 地址不验证
		// 星座 血型 不验证
		// 日期不验证
		tempText = validataCodeTextField.getText().trim();
		if (tempText.length() != 4) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.validateFormart"), rf);
			return false;
		}
		String code = System.getProperty("randCode");
		if (!tempText.equalsIgnoreCase(code)) {
			MessageBox.promptMessage(Language.instance
					.getValue("register.action.validateCodeError"), rf);
			validateLabel.changeCode();
			return false;
		}
		return true;
	}
}
