package talk.alvin.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import talk.alvin.gui.action.RegisterFrameAction;
import talk.alvin.gui.control.button.TButton;
import talk.alvin.gui.control.label.ValidateCodeLabel;
import talk.alvin.util.Language;
import talk.alvin.util.ObjectManager;

/**
 * 注册窗口
 * 
 * @author 唐植超
 * 
 */
public class RegisterFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nickNameTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;
	private JPasswordField confirmPasswordText;
	private JTextField birthdayTextField;
	private JLabel errorLabel;
	private JRadioButton boyRadioButton;
	private JRadioButton grilRadioButton;
	private JPasswordField registerPasswordField;
	private JComboBox starComboBox;
	private JComboBox bloodComboBox;
	private JTextArea agreementTextArea;
	private JCheckBox acceptCheckBox;
	private JTextField ageTextField;
	private JTextField validataCodeTextField;
	private ValidateCodeLabel showCodeLabel;
	private TButton okButton;

	private Action registerAction = (Action) ObjectManager.instance
			.getObject(RegisterFrameAction.class);

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setBounds(100, 100, 500, 563);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		setTitle(Language.instance.getValue("register.frame.title"));

		JLabel registerTitleLable = new JLabel(
				"<html>&nbsp;<p/>&nbsp;<p/>&nbsp;&nbsp;<p/>&nbsp;<p/>&nbsp;</html>");
		registerTitleLable.setBackground(new Color(230, 255, 255));
		registerTitleLable.setOpaque(false);
		registerTitleLable.setBorder(null);
		contentPane.add(registerTitleLable, BorderLayout.NORTH);
		// registerTitleLable.setIcon(Talk_System
		// .readIcon("icon/frame/registerLog.jpg"));

		JLabel registerLeftLable = new JLabel("            ");
		contentPane.add(registerLeftLable, BorderLayout.WEST);

		JPanel buttonPanel = new JPanel();
		Box buttonBox = Box.createHorizontalBox();
		buttonPanel.setBackground(new Color(210, 255, 230));
		// buttonBox.add(Box
		// .createHorizontalStrut(getWidth() / 2 + getWidth() / 7));

		okButton = new TButton(Language.instance
				.getValue("register.okButton.text"));
		okButton.setEnabled(false);
		okButton.addActionListener(registerAction);
		okButton.setActionCommand("clickRegisterOKButtonAction");
		buttonBox.add(okButton);
		buttonBox.add(Box.createHorizontalStrut(5));
		TButton cancelButton = new TButton(Language.instance
				.getValue("register.exitButton.text"));
		cancelButton.addActionListener(registerAction);
		cancelButton.setActionCommand("clickCancelRegisterButtonAction");
		buttonBox.add(cancelButton);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(buttonBox, BorderLayout.EAST);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		errorLabel = new JLabel("");
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 5;
		gbc_errorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		panel.add(errorLabel, gbc_errorLabel);

		JLabel nickNameLabel = new JLabel(Language.instance
				.getValue("register.nickNameLabel.text"));
		GridBagConstraints gbc_nickNameLabel = new GridBagConstraints();
		gbc_nickNameLabel.anchor = GridBagConstraints.EAST;
		gbc_nickNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nickNameLabel.gridx = 1;
		gbc_nickNameLabel.gridy = 1;
		panel.add(nickNameLabel, gbc_nickNameLabel);

		nickNameTextField = new JTextField();
		GridBagConstraints gbc_nickNameTextField = new GridBagConstraints();
		gbc_nickNameTextField.gridwidth = 3;
		gbc_nickNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nickNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nickNameTextField.gridx = 2;
		gbc_nickNameTextField.gridy = 1;
		panel.add(nickNameTextField, gbc_nickNameTextField);
		nickNameTextField.setColumns(10);

		JLabel ageLabel = new JLabel(Language.instance
				.getValue("register.agelabel.text"));
		GridBagConstraints gbc_ageLabel = new GridBagConstraints();
		gbc_ageLabel.anchor = GridBagConstraints.EAST;
		gbc_ageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ageLabel.gridx = 1;
		gbc_ageLabel.gridy = 2;
		panel.add(ageLabel, gbc_ageLabel);

		ageTextField = new JTextField();
		GridBagConstraints gbc_ageTextField = new GridBagConstraints();
		gbc_ageTextField.gridwidth = 3;
		gbc_ageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageTextField.gridx = 2;
		gbc_ageTextField.gridy = 2;
		panel.add(ageTextField, gbc_ageTextField);
		ageTextField.setColumns(10);

		JLabel genderLabel = new JLabel(Language.instance
				.getValue("register.genderLabel.text"));
		GridBagConstraints gbc_genderLabel = new GridBagConstraints();
		gbc_genderLabel.anchor = GridBagConstraints.EAST;
		gbc_genderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_genderLabel.gridx = 1;
		gbc_genderLabel.gridy = 3;
		panel.add(genderLabel, gbc_genderLabel);

		boyRadioButton = new JRadioButton(Language.instance
				.getValue("register.boyRadioButton.text"));
		boyRadioButton.setOpaque(false);
		boyRadioButton.setSelected(true);
		GridBagConstraints gbc_boyRadioButton = new GridBagConstraints();
		gbc_boyRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_boyRadioButton.gridx = 2;
		gbc_boyRadioButton.gridy = 3;
		panel.add(boyRadioButton, gbc_boyRadioButton);

		grilRadioButton = new JRadioButton(Language.instance
				.getValue("register.grilRadioButton.text"));
		grilRadioButton.setOpaque(false);
		GridBagConstraints gbc_grilRadioButton = new GridBagConstraints();
		gbc_grilRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_grilRadioButton.gridx = 3;
		gbc_grilRadioButton.gridy = 3;
		panel.add(grilRadioButton, gbc_grilRadioButton);
		ButtonGroup bg = new ButtonGroup();
		bg.add(boyRadioButton);
		bg.add(grilRadioButton);

		JLabel passwordLabel = new JLabel(Language.instance
				.getValue("register.passwordLabel.text"));
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 4;
		panel.add(passwordLabel, gbc_passwordLabel);

		registerPasswordField = new JPasswordField();
		GridBagConstraints gbc_registerPasswordField = new GridBagConstraints();
		gbc_registerPasswordField.gridwidth = 3;
		gbc_registerPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_registerPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerPasswordField.gridx = 2;
		gbc_registerPasswordField.gridy = 4;
		panel.add(registerPasswordField, gbc_registerPasswordField);

		JLabel confirmPasswordLabel = new JLabel(Language.instance
				.getValue("register.confirmPasswordLabel.text"));
		GridBagConstraints gbc_confirmPasswordLabel = new GridBagConstraints();
		gbc_confirmPasswordLabel.anchor = GridBagConstraints.EAST;
		gbc_confirmPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordLabel.gridx = 1;
		gbc_confirmPasswordLabel.gridy = 5;
		panel.add(confirmPasswordLabel, gbc_confirmPasswordLabel);

		confirmPasswordText = new JPasswordField();
		GridBagConstraints gbc_confirmPasswordText = new GridBagConstraints();
		gbc_confirmPasswordText.gridwidth = 3;
		gbc_confirmPasswordText.insets = new Insets(0, 0, 5, 0);
		gbc_confirmPasswordText.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmPasswordText.gridx = 2;
		gbc_confirmPasswordText.gridy = 5;
		panel.add(confirmPasswordText, gbc_confirmPasswordText);

		JLabel emailLabel = new JLabel(Language.instance
				.getValue("register.emailLabel.text"));
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 6;
		panel.add(emailLabel, gbc_emailLabel);

		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.gridwidth = 3;
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 6;
		panel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel addressLabel = new JLabel(Language.instance
				.getValue("register.addressLabel.text"));
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.anchor = GridBagConstraints.EAST;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 1;
		gbc_addressLabel.gridy = 7;
		panel.add(addressLabel, gbc_addressLabel);

		addressTextField = new JTextField();
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_addressTextField.gridwidth = 3;
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 2;
		gbc_addressTextField.gridy = 7;
		panel.add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(10);

		JLabel starLabel = new JLabel(Language.instance
				.getValue("register.starLabel.text"));
		GridBagConstraints gbc_starLabel = new GridBagConstraints();
		gbc_starLabel.anchor = GridBagConstraints.EAST;
		gbc_starLabel.insets = new Insets(0, 0, 5, 5);
		gbc_starLabel.gridx = 1;
		gbc_starLabel.gridy = 8;
		panel.add(starLabel, gbc_starLabel);

		Vector<String> startVector = new Vector<String>();
		for (int i = 1; i < 13; i++) {
			startVector.add(Language.instance.getValue("star_" + i));
		}
		starComboBox = new JComboBox(startVector);
		GridBagConstraints gbc_starComboBox = new GridBagConstraints();
		gbc_starComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_starComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_starComboBox.gridx = 2;
		gbc_starComboBox.gridy = 8;
		panel.add(starComboBox, gbc_starComboBox);

		JLabel bloodLabel = new JLabel(Language.instance
				.getValue("register.bloodLabel.text"));
		GridBagConstraints gbc_bloodLabel = new GridBagConstraints();
		gbc_bloodLabel.insets = new Insets(0, 0, 5, 5);
		gbc_bloodLabel.gridx = 3;
		gbc_bloodLabel.gridy = 8;
		panel.add(bloodLabel, gbc_bloodLabel);

		Vector<String> bloodVector = new Vector<String>();
		String blood = "";
		for (int i = 1; i < 6; i++) {
			if (blood != null) {
				bloodVector.add(Language.instance.getValue("blood_" + i));
			}
		}
		bloodComboBox = new JComboBox(bloodVector);
		GridBagConstraints gbc_bloodComboBox = new GridBagConstraints();
		gbc_bloodComboBox.anchor = GridBagConstraints.WEST;
		gbc_bloodComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_bloodComboBox.gridx = 4;
		gbc_bloodComboBox.gridy = 8;
		panel.add(bloodComboBox, gbc_bloodComboBox);

		JLabel birthdayLabel = new JLabel(Language.instance
				.getValue("register.birthdayLabel.text"));
		GridBagConstraints gbc_birthdayLabel = new GridBagConstraints();
		gbc_birthdayLabel.anchor = GridBagConstraints.EAST;
		gbc_birthdayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_birthdayLabel.gridx = 1;
		gbc_birthdayLabel.gridy = 9;
		panel.add(birthdayLabel, gbc_birthdayLabel);

		birthdayTextField = new JTextField();
		GridBagConstraints gbc_birthdayTextField = new GridBagConstraints();
		gbc_birthdayTextField.gridwidth = 3;
		gbc_birthdayTextField.insets = new Insets(0, 0, 5, 0);
		gbc_birthdayTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthdayTextField.gridx = 2;
		gbc_birthdayTextField.gridy = 9;
		panel.add(birthdayTextField, gbc_birthdayTextField);
		birthdayTextField.setColumns(10);

		JLabel validateCodeLabel = new JLabel(Language.instance
				.getValue("register.validataCodeLabel.text"));
		GridBagConstraints gbc_validateCodeLabel = new GridBagConstraints();
		gbc_validateCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_validateCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_validateCodeLabel.gridx = 1;
		gbc_validateCodeLabel.gridy = 10;
		panel.add(validateCodeLabel, gbc_validateCodeLabel);

		validataCodeTextField = new JTextField();
		GridBagConstraints gbc_validataCodeTextField = new GridBagConstraints();
		gbc_validataCodeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_validataCodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_validataCodeTextField.gridx = 2;
		gbc_validataCodeTextField.gridy = 10;
		panel.add(validataCodeTextField, gbc_validataCodeTextField);
		validataCodeTextField.setColumns(10);

		showCodeLabel = new ValidateCodeLabel();
		GridBagConstraints gbc_showCodeLabel = new GridBagConstraints();
		gbc_showCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_showCodeLabel.gridx = 3;
		gbc_showCodeLabel.gridy = 10;
		panel.add(showCodeLabel, gbc_showCodeLabel);

		TButton changeCodeButton = new TButton(Language.instance
				.getValue("register.changeCodeButton.text"));
		changeCodeButton.setToolTipText(Language.instance
				.getValue("register.changeCodeButton.tooltext"));
		changeCodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCodeLabel.changeCode();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.WEST;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 4;
		gbc_button_1.gridy = 10;
		panel.add(changeCodeButton, gbc_button_1);

		JLabel agreementLabel = new JLabel(Language.instance
				.getValue("register.agreementLabel.text"));
		GridBagConstraints gbc_infomationLabel = new GridBagConstraints();
		gbc_infomationLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_infomationLabel.gridheight = 2;
		gbc_infomationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_infomationLabel.gridx = 1;
		gbc_infomationLabel.gridy = 11;
		panel.add(agreementLabel, gbc_infomationLabel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 11;
		panel.add(scrollPane, gbc_scrollPane);

		agreementTextArea = new JTextArea();
		agreementTextArea.setLineWrap(true);
		agreementTextArea.setEditable(false);
		agreementTextArea.setText(Language.instance
				.getValue("register.agreementTextArea.text"));
		agreementTextArea.setForeground(Color.GRAY);
		scrollPane.setViewportView(agreementTextArea);

		acceptCheckBox = new JCheckBox(Language.instance
				.getValue("register.acceptCheckBoxButton.text"));
		acceptCheckBox.setOpaque(false);
		acceptCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButton.setEnabled(acceptCheckBox.isSelected());
			}
		});
		GridBagConstraints gbc_acceptCheckBox = new GridBagConstraints();
		gbc_acceptCheckBox.anchor = GridBagConstraints.WEST;
		gbc_acceptCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_acceptCheckBox.gridx = 2;
		gbc_acceptCheckBox.gridy = 13;
		panel.add(acceptCheckBox, gbc_acceptCheckBox);

		setLocationRelativeTo(null);
	}

	public JTextField getNickNameTextField() {
		return nickNameTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JPasswordField getConfirmPasswordText() {
		return confirmPasswordText;
	}

	public JTextField getBirthdayTextField() {
		return birthdayTextField;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public JRadioButton getBoyRadioButton() {
		return boyRadioButton;
	}

	public JRadioButton getGrilRadioButton() {
		return grilRadioButton;
	}

	public JPasswordField getRegisterPasswordField() {
		return registerPasswordField;
	}

	public JComboBox getStarComboBox() {
		return starComboBox;
	}

	public JComboBox getBloodComboBox() {
		return bloodComboBox;
	}

	public JTextArea getAgreementTextArea() {
		return agreementTextArea;
	}

	public JCheckBox getAcceptCheckBox() {
		return acceptCheckBox;
	}

	public JTextField getAgeTextField() {
		return ageTextField;
	}

	public JTextField getValidataCodeTextField() {
		return validataCodeTextField;
	}

	public ValidateCodeLabel getShowCodeLabel() {
		return showCodeLabel;
	}

	public TButton getOkButton() {
		return okButton;
	}

}
