package talk.alvin.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import talk.alvin.gui.action.LoginFrameAction;
import talk.alvin.gui.control.button.LinkButton;
import talk.alvin.gui.control.button.TButton;
import talk.alvin.gui.control.label.LinkLabel;
import talk.alvin.util.Language;
import talk.alvin.util.ObjectManager;
import talk.alvin.util.Resource;

/**
 * 登陆窗体
 * 
 * @author Administrator
 * 
 */
public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private JTextField loginIdTextField;
	private JPasswordField passwordField;
	private JCheckBox savePassCheckBox;
	private JCheckBox autoLoginCheckBox;
	private JLabel errorLabel;
	// 用户请求的处理
	private LoginFrameAction loginAction = (LoginFrameAction) ObjectManager.instance
			.getObject(LoginFrameAction.class);

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle(Language.instance.getValue("login.frame.title"));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		JLabel titleLabel = new JLabel();
		Icon icon = Resource.instance.getIcon("frame/logo.jpg");
		titleLabel.setIcon(icon);
		setBounds(100, 100, icon.getIconWidth() + 3, 250);
		contentPane.add(titleLabel, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.setBackground(new Color(230, 255, 255));

		JLabel loginIdLabel = new JLabel(Language.instance
				.getValue("login.frame.loginId"));
		loginIdLabel.setOpaque(false);
		// 主要的结构box
		Box mainBox = Box.createVerticalBox();
		errorLabel = new JLabel(" ");
		errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mainBox.add(errorLabel);
		// 添加三个横向布局的box
		Box box = Box.createHorizontalBox();
		box.add(loginIdLabel);
		box.add(Box.createHorizontalStrut(5));
		loginIdTextField = new JTextField();
		loginIdTextField.setColumns(15);
		EmptyBorder border = new EmptyBorder(new Insets(1, 1, 1, 1));
		loginIdTextField.setBorder(border);
		box.add(loginIdTextField);
		box.add(Box.createHorizontalStrut(5));
		LinkButton registerLabel = new LinkButton(Language.instance
				.getValue("login.frame.register"));
		registerLabel.addActionListener(loginAction);
		registerLabel.setActionCommand("clickRegisterButtonAction");
		box.add(registerLabel);
		
		mainBox.add(box);
		mainBox.add(Box.createVerticalStrut(10));
		// 第二个
		box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(15));
		box = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel(Language.instance
				.getValue("login.frame.password"));
		box.add(passwordLabel);
		box.add(Box.createHorizontalStrut(5));
		passwordField = new JPasswordField();
		passwordField.setBorder(border);
		passwordField.setColumns(15);
		box.add(passwordField);
		box.add(Box.createHorizontalStrut(5));
		LinkLabel forgetpasswordLabel = new LinkLabel(Language.instance
				.getValue("login.frame.getpass"));
		box.add(forgetpasswordLabel);
		mainBox.add(box);
		mainBox.add(Box.createVerticalStrut(25));
		// 第三个
		box = Box.createHorizontalBox();
		JLabel stateLabel = new JLabel(Language.instance
				.getValue("login.frame.stateLabel"));
		box.add(stateLabel);
		JLabel userStateButton = new JLabel("");
		userStateButton.setSize(20, 20);
		userStateButton.setIcon(Resource.instance
				.getIcon("frame/loginStateSet.jpg"));
		userStateButton.setOpaque(false);
		userStateButton.setBorder(null);
		box.add(Box.createHorizontalStrut(5));
		box.add(userStateButton);
		box.add(Box.createHorizontalStrut(30));
		savePassCheckBox = new JCheckBox(Language.instance
				.getValue("login.frame.saveButton"));
		savePassCheckBox.setOpaque(false);
		savePassCheckBox.setBorder(null);
		box.add(savePassCheckBox);
		box.add(Box.createHorizontalStrut(30));
		autoLoginCheckBox = new JCheckBox(Language.instance
				.getValue("login.frame.autoLoginButton"));
		autoLoginCheckBox.setOpaque(false);
		autoLoginCheckBox.setBorder(null);
		box.add(autoLoginCheckBox);
		box.add(Box.createHorizontalStrut(5));
		mainBox.add(box);

		mainPanel.add(mainBox);

		buttonPanel.setBackground(new Color(210, 255, 230));

		buttonPanel.setLayout(new BorderLayout());
		box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(5));
		buttonPanel.add(box, BorderLayout.NORTH);
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(20));
		TButton loginSetButton = new TButton(Resource.instance
				.getIcon("frame/set_Us_en.bmp"));
		loginSetButton.setBorder(null);
		loginSetButton.setOpaque(false);
		box.add(loginSetButton);
		box.add(Box.createHorizontalStrut(icon.getIconWidth() / 2));
		TButton loginButton = new TButton(loginAction);
		loginButton.setActionCommand("clickUserLoginButtonAction");
		loginButton.setIcon(Resource.instance
				.getIcon("frame/loginButton_US_en.bmp"));
		loginButton.setBorder(null);
		loginButton.setOpaque(false);
		box.add(loginButton);
		buttonPanel.add(box, BorderLayout.CENTER);
		box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(5));
		buttonPanel.add(box, BorderLayout.SOUTH);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JCheckBox getSavePassCheckBox() {
		return savePassCheckBox;
	}

	public JTextField getLoginIdTextField() {
		return loginIdTextField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JCheckBox getAutoLoginCheckBox() {
		return autoLoginCheckBox;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

}
