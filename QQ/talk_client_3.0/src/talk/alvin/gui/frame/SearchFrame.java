package talk.alvin.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import talk.alvin.gui.action.SearchFrameAction;
import talk.alvin.gui.control.button.TButton;
import talk.alvin.gui.model.SearchFriendTableModel;
import talk.alvin.util.Language;
import talk.alvin.util.ObjectManager;

/**
 * 查找窗体
 * 
 * @author Administrator
 * 
 */
public class SearchFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JTextField loginIdTextField;
	private JTextField nickNameTextField;
	private JRadioButton exactSearchRadioButton;
	private JRadioButton conditionsRadioButton;
	private JTable table;
	private JPanel searchPane;
	private JPanel resultPane;
	private JTabbedPane tabbedPane;
	private TButton addFriendButton;

	private SearchFrameAction searchFrameAction = (SearchFrameAction) ObjectManager.instance
			.getObject(SearchFrameAction.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setResizable(false);
		setTitle(Language.instance.getValue("search.frame.title"));
		setBounds(100, 100, 398, 347);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(230, 250, 250));
		searchPane = getSearchPanel();
		resultPane = getResultPanel();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		changePanel(searchPane);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}

	/**
	 * 改变面板
	 * 
	 * @param panel
	 */
	public void changePanel(JPanel panel) {
		tabbedPane.removeAll();
		tabbedPane.addTab(Language.instance
				.getValue("search.frame.tabbedPanel.title"), null, panel, null);
	}

	public JTextField getLoginIdTextField() {
		return loginIdTextField;
	}

	public JTextField getNickNameTextField() {
		return nickNameTextField;
	}

	public JRadioButton getExactSearchRadioButton() {
		return exactSearchRadioButton;
	}

	public JRadioButton getConditionsRadioButton() {
		return conditionsRadioButton;
	}

	private JPanel getResultPanel() {
		JPanel resultPanel = new JPanel();
		resultPanel.setBackground(new Color(230, 250, 250));
		GridBagLayout gbl_resultPanel = new GridBagLayout();
		gbl_resultPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_resultPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_resultPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_resultPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		resultPanel.setLayout(gbl_resultPanel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridheight = 9;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		resultPanel.add(horizontalStrut, gbc_horizontalStrut);

		JLabel resultLabel = new JLabel(Language.instance
				.getValue("search.frame.search.button.text"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		resultPanel.add(resultLabel, gbc_label);

		Box horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.gridheight = 9;
		gbc_horizontalBox.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalBox.gridx = 4;
		gbc_horizontalBox.gridy = 0;
		resultPanel.add(horizontalBox, gbc_horizontalBox);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		resultPanel.add(scrollPane, gbc_scrollPane);

		table = new JTable(new SearchFriendTableModel());
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							if (table.getSelectedRow() > -1) {
								addFriendButton.setEnabled(true);
							} else {
								addFriendButton.setEnabled(false);
							}
						}
					}
				});

		addFriendButton = new TButton("Add");
		addFriendButton.addActionListener(searchFrameAction);
		addFriendButton.setActionCommand("clickAddFirendButtonAction");
		addFriendButton.setEnabled(false);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 8;
		resultPanel.add(addFriendButton, gbc_button_1);

		TButton button = new TButton(Language.instance
				.getValue("search.frame.cancel.button.text"));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 8;
		resultPanel.add(button, gbc_button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		Box horizontalBox_1 = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox_1 = new GridBagConstraints();
		gbc_horizontalBox_1.gridwidth = 3;
		gbc_horizontalBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalBox_1.gridx = 1;
		gbc_horizontalBox_1.gridy = 9;
		resultPanel.add(horizontalBox_1, gbc_horizontalBox_1);
		return resultPanel;
	}

	private JPanel getSearchPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 250, 250));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 42, 79, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.gridwidth = 6;
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 0;
		panel.add(horizontalStrut_2, gbc_horizontalStrut_2);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridheight = 11;
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		panel.add(horizontalStrut, gbc_horizontalStrut);

		JLabel label = new JLabel(Language.instance
				.getValue("search.frame.findWays.text"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 1;
		panel.add(separator, gbc_separator);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridheight = 11;
		gbc_horizontalStrut_1.gridx = 5;
		gbc_horizontalStrut_1.gridy = 1;
		panel.add(horizontalStrut_1, gbc_horizontalStrut_1);

		ButtonGroup bg = new ButtonGroup();
		exactSearchRadioButton = new JRadioButton(Language.instance
				.getValue("search.frame.exact.search.text"));
		exactSearchRadioButton.setEnabled(false);
		exactSearchRadioButton.setSelected(true);
		exactSearchRadioButton.setOpaque(false);
		GridBagConstraints gbc_exactSearchRadioButton = new GridBagConstraints();
		gbc_exactSearchRadioButton.anchor = GridBagConstraints.WEST;
		gbc_exactSearchRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_exactSearchRadioButton.gridx = 1;
		gbc_exactSearchRadioButton.gridy = 2;
		panel.add(exactSearchRadioButton, gbc_exactSearchRadioButton);

		conditionsRadioButton = new JRadioButton(Language.instance
				.getValue("search.frame.conditions.search.text"));
		conditionsRadioButton.setEnabled(false);
		conditionsRadioButton.setOpaque(false);
		GridBagConstraints gbc_conditionsRadioButton = new GridBagConstraints();
		gbc_conditionsRadioButton.gridwidth = 2;
		gbc_conditionsRadioButton.anchor = GridBagConstraints.WEST;
		gbc_conditionsRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_conditionsRadioButton.gridx = 1;
		gbc_conditionsRadioButton.gridy = 3;
		panel.add(conditionsRadioButton, gbc_conditionsRadioButton);

		bg.add(exactSearchRadioButton);
		bg.add(conditionsRadioButton);

		JLabel label_1 = new JLabel(Language.instance
				.getValue("search.frame.account.text"));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 5;
		panel.add(label_1, gbc_label_1);

		loginIdTextField = new JTextField();
		GridBagConstraints gbc_loginIdTextField = new GridBagConstraints();
		gbc_loginIdTextField.gridwidth = 4;
		gbc_loginIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_loginIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginIdTextField.gridx = 1;
		gbc_loginIdTextField.gridy = 6;
		panel.add(loginIdTextField, gbc_loginIdTextField);
		loginIdTextField.setColumns(10);

		JLabel label_2 = new JLabel(Language.instance
				.getValue("search.frame.nickName.text"));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 7;
		panel.add(label_2, gbc_label_2);

		nickNameTextField = new JTextField();
		GridBagConstraints gbc_nickNameTextField = new GridBagConstraints();
		gbc_nickNameTextField.gridwidth = 4;
		gbc_nickNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nickNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nickNameTextField.gridx = 1;
		gbc_nickNameTextField.gridy = 8;
		panel.add(nickNameTextField, gbc_nickNameTextField);
		nickNameTextField.setColumns(10);

		JLabel userCountLabel = new JLabel(" ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 10;
		panel.add(userCountLabel, gbc_label_3);

		TButton cancelButton = new TButton(Language.instance
				.getValue("search.frame.cancel.button.text"));
		cancelButton.addActionListener(searchFrameAction);

		TButton searchButton = new TButton("Search");
		searchButton.addActionListener(searchFrameAction);
		searchButton.setActionCommand("clickSearchFirendsListButtonAction");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.gridx = 3;
		gbc_searchButton.gridy = 11;
		panel.add(searchButton, gbc_searchButton);
		cancelButton.setActionCommand("clickCancelButtonAction");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 4;
		gbc_button_1.gridy = 11;
		panel.add(cancelButton, gbc_button_1);
		return panel;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public JTable getTable() {
		return table;
	}

	public JPanel getSearchPane() {
		return searchPane;
	}

	public JPanel getResultPane() {
		return resultPane;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setLoginIdTextField(JTextField loginIdTextField) {
		this.loginIdTextField = loginIdTextField;
	}

	public void setNickNameTextField(JTextField nickNameTextField) {
		this.nickNameTextField = nickNameTextField;
	}

}
