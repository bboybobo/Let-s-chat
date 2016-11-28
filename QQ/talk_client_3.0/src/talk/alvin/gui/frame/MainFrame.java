package talk.alvin.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import talk.alvin.gui.action.MainFrameAction;
import talk.alvin.gui.control.button.TButton;
import talk.alvin.gui.control.label.LinkLabel;
import talk.alvin.gui.control.x.CurtainPane;
import talk.alvin.util.Language;
import talk.alvin.util.ObjectManager;
import talk.alvin.util.Resource;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

/**
 * 登陆后的主窗体
 * 
 * @author Administrator
 * 
 */
public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CurtainPane friendListPane;
	private LinkLabel userNameLabel;
	private JTextField remarkTextField;
	private JLabel faceLabel;

	private JScrollPane firentScrollPane;

	private MainFrameAction mainFrameAction = (MainFrameAction) ObjectManager.instance
			.getObject(MainFrameAction.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setResizable(false);
		setTitle(Language.instance.getValue("login.frame.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		// 上中下三个面板
		JPanel upPanel = new JPanel();
		upPanel.setBackground(new Color(230, 255, 255));
		upPanel.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(230, 255, 255));
		mainPanel.setLayout(new BorderLayout());
		JPanel downPanel = new JPanel();
		downPanel.setBackground(new Color(230, 255, 255));
		downPanel.setLayout(new BorderLayout());
		contentPane.add(upPanel, BorderLayout.NORTH);

		Box titleBox = Box.createHorizontalBox();
		faceLabel = new JLabel("");
		// faceLabel.setIcon(Talk_System.readIcon("face/1.bmp"));
		titleBox.add(faceLabel);
		titleBox.add(Box.createHorizontalStrut(20));
		Box infoMationBox = Box.createVerticalBox();
		titleBox.add(infoMationBox);
		userNameLabel = new LinkLabel("");
		userNameLabel.setHorizontalAlignment(JLabel.LEFT);
		infoMationBox.add(userNameLabel);
		remarkTextField = new JTextField("111111111111");
		remarkTextField.setOpaque(false);
		remarkTextField.setBorder(null);
		infoMationBox.add(remarkTextField);

		upPanel.add(titleBox);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(230, 255, 255));
		mainPanel.add(panel, BorderLayout.NORTH);

		titleBox = Box.createHorizontalBox();

		titleBox.add(Box.createHorizontalStrut(20));
		TButton button_10 = new TButton();
		button_10.setOpaque(false);
		button_10.setContentAreaFilled(false);
		button_10.setBorder(null);
		button_10.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		titleBox.add(button_10);

		TButton button_11 = new TButton();
		button_11.setOpaque(false);
		button_11.setContentAreaFilled(false);
		button_11.setBorder(null);
		button_11.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		titleBox.add(button_11);

		TButton button_12 = new TButton();
		button_12.setOpaque(false);
		button_12.setContentAreaFilled(false);
		button_12.setBorder(null);
		button_12.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		titleBox.add(button_12);

		TButton button_13 = new TButton();
		button_13.setOpaque(false);
		button_13.setContentAreaFilled(false);
		button_13.setBorder(null);
		button_13.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		titleBox.add(button_13);
		panel.add(titleBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 255, 255));
		mainPanel.add(panel_1, BorderLayout.SOUTH);

		Box bottomBox = Box.createHorizontalBox();

		bottomBox.add(Box.createHorizontalStrut(20));
		TButton button_21 = new TButton();
		button_21.setOpaque(false);
		button_21.setContentAreaFilled(false);
		button_21.setBorder(null);
		button_21.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_21);

		TButton button_22 = new TButton();
		button_22.setOpaque(false);
		button_22.setContentAreaFilled(false);
		button_22.setBorder(null);
		button_22.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_22);

		TButton button_23 = new TButton();
		button_23.setOpaque(false);
		button_23.setContentAreaFilled(false);
		button_23.setBorder(null);
		button_23.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_23);

		TButton button_24 = new TButton();
		button_24.setOpaque(false);
		button_24.setContentAreaFilled(false);
		button_24.setBorder(null);
		button_24.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_24);

		panel_1.setLayout(new BorderLayout());
		panel_1.add(bottomBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230, 255, 255));
		mainPanel.add(panel_2, BorderLayout.WEST);

		Box leftBox = Box.createVerticalBox();
		panel_2.add(leftBox);

		TButton button_17 = new TButton();
		button_17.setOpaque(false);
		button_17.setContentAreaFilled(false);
		button_17.setBorder(null);
		button_17.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		leftBox.add(button_17);
		leftBox.add(Box.createVerticalStrut(5));

		TButton button_18 = new TButton();
		button_18.setOpaque(false);
		button_18.setContentAreaFilled(false);
		button_18.setBorder(null);
		button_18.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		leftBox.add(button_18);
		leftBox.add(Box.createVerticalStrut(5));

		TButton button_19 = new TButton();
		button_19.setOpaque(false);
		button_19.setContentAreaFilled(false);
		button_19.setBorder(null);
		button_19.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		leftBox.add(button_19);
		leftBox.add(Box.createVerticalStrut(5));

		TButton button_20 = new TButton();
		button_20.setOpaque(false);
		button_20.setContentAreaFilled(false);
		button_20.setBorder(null);
		button_20.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		leftBox.add(button_20);
		leftBox.add(Box.createVerticalStrut(5));

		firentScrollPane = new JScrollPane();
		firentScrollPane.setBackground(new Color(230, 255, 255));
		firentScrollPane.setOpaque(false);
		firentScrollPane.setBorder(null);
		mainPanel.add(firentScrollPane, BorderLayout.CENTER);

		contentPane.add(downPanel, BorderLayout.SOUTH);

		friendListPane = new CurtainPane();
		firentScrollPane.setViewportView(friendListPane);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(friendListPane, popupMenu);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		popupMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("New menu item");
		popupMenu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("New menu item");
		popupMenu.add(menuItem_2);
		
		JMenu menu = new JMenu("New menu");
		popupMenu.add(menu);
		
		JMenuItem menuItem_3 = new JMenuItem("New menu item");
		popupMenu.add(menuItem_3);
		// for (int i = 0; i < 3; i++) {
		// ListPane listPane = new ListPane();
		// for (int j = 0; j < 4; j++) {
		// listPane.addItem("alvin " + i, Resource.instance.getFace("1"));
		// }
		// friendListPane.addPane("croupName" + i, Resource.instance
		// .getIcon("frame/qq.gif"), listPane);
		// }

		bottomBox = Box.createHorizontalBox();
		downPanel.add(bottomBox);
		TButton button_3 = new TButton();
		button_3.setOpaque(false);
		button_3.setContentAreaFilled(false);
		button_3.setBorder(null);
		button_3.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_3);

		TButton showSearchButton = new TButton();
		showSearchButton.setToolTipText(Language.instance
				.getValue("main.frame.search.tooltext"));
		showSearchButton.setOpaque(false);
		showSearchButton.setContentAreaFilled(false);
		showSearchButton.setBorder(null);
		showSearchButton.setIcon(Resource.instance.getIcon("frame/search.gif"));
		showSearchButton.addActionListener(mainFrameAction);
		showSearchButton.setActionCommand("clickShowSearchFrameButtonAction");
		bottomBox.add(showSearchButton);

		TButton button_33 = new TButton();
		button_33.setOpaque(false);
		button_33.setContentAreaFilled(false);
		button_33.setBorder(null);
		button_33.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_33);

		TButton button_34 = new TButton();
		button_34.setOpaque(false);
		button_34.setContentAreaFilled(false);
		button_34.setBorder(null);
		button_34.setIcon(Resource.instance.getIcon("frame/loginStateSet.jpg"));
		bottomBox.add(button_34);

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point(size.width - getWidth() - 10, 10));
	}

	public LinkLabel getUserNameLabel() {
		return userNameLabel;
	}

	public JLabel getFaceLabel() {
		return faceLabel;
	}

	public JTextField getRemarkTextField() {
		return remarkTextField;
	}

	public void resetFirend(CurtainPane curpane) {
		this.friendListPane = curpane;
		firentScrollPane.setViewportView(curpane);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
