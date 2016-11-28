package talk.alvin.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import talk.alvin.bean.SimpleUser;
import talk.alvin.gui.action.ChatFrameAction;
import talk.alvin.gui.control.button.LinkButton;
import talk.alvin.gui.control.button.TButton;
import talk.alvin.gui.control.cutimage.screen.ScreenCapture;
import talk.alvin.gui.control.editor.RecieveMessageTextPane;
import talk.alvin.gui.control.editor.SendMessageTextPane;
import talk.alvin.gui.control.label.LinkLabel;
import talk.alvin.util.FontUtil;
import talk.alvin.util.Language;
import talk.alvin.util.Resource;

/**
 * 聊天窗体
 * 
 * @author 唐植超
 * 
 */
public class ChatFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/** 该窗口聊天的用户 */
	private SimpleUser user;

	private SendMessageTextPane sendMessageTextArea;
	private RecieveMessageTextPane recaveTextArea;

	private ChatFrameAction action;

	private Timer timer;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public ChatFrame(SimpleUser user, ChatFrameAction action) {
		this.action = action;
		this.user = user;
		setTitle(Language.instance.getValue("chat.frame.title")
				+ user.getName());
		setResizable(false);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(new Color(230, 255, 255));
		setContentPane(contentPane);

		// 将窗体分为 上 中 右
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		upPanel.setBackground(new Color(230, 255, 255));
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(230, 255, 255));
		mainPanel.setLayout(new BorderLayout());
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(230, 255, 255));

		contentPane.add(upPanel, BorderLayout.NORTH);

		Box upBox = Box.createVerticalBox();
		JPanel uPanel = new JPanel();
		JPanel uPanel2 = new JPanel();
		uPanel2.setLayout(new BorderLayout());
		uPanel2.setOpaque(false);
		upBox.add(uPanel);
		upBox.add(Box.createVerticalStrut(5));
		upBox.add(uPanel2);
		upBox.add(Box.createVerticalStrut(5));

		Box u2Box = Box.createHorizontalBox();
		TButton button_1 = new TButton();
		button_1.setIcon(Resource.instance.getIcon("frame/tv.png"));
		button_1.setOpaque(false);
		button_1.setBorder(null);
		button_1.setContentAreaFilled(false);
		u2Box.add(button_1);

		TButton button_5 = new TButton();
		button_5.setIcon(Resource.instance.getIcon("frame/phone.png"));
		button_5.setOpaque(false);
		button_5.setBorder(null);
		button_5.setContentAreaFilled(false);
		u2Box.add(button_5);
		uPanel2.add(u2Box);
		upPanel.add(upBox, BorderLayout.CENTER);

		Box uBox = Box.createHorizontalBox();
		uPanel.setLayout(new BorderLayout());
		uPanel.setOpaque(false);
		uPanel.add(uBox);

		JLabel label_1 = new JLabel();
		label_1.setIcon(Resource.instance.getIcon("face/1.bmp"));
		uBox.add(label_1);
		label_1.setOpaque(false);
		label_1.setBorder(null);

		uBox.add(Box.createHorizontalStrut(20));
		Box titleBox = Box.createVerticalBox();
		uBox.add(titleBox);
		LinkButton button = new LinkButton("");
		button.setHorizontalAlignment(JLabel.LEFT);
		titleBox.add(button);

		JTextField button_4 = new JTextField("", 100);
		button_4.setOpaque(false);
		button_4.setBorder(null);
		titleBox.add(button_4);

		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 255, 255));
		panel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.SOUTH);

		Box bottomBox = Box.createHorizontalBox();

		LinkLabel label = new LinkLabel("这里是我要打广告的地方");
		bottomBox.add(label);
		bottomBox.add(Box.createHorizontalStrut(60));
		TButton sendButton = new TButton();
		sendButton.setOpaque(false);
		sendButton.setBorder(null);
		sendButton.setContentAreaFilled(false);
		sendButton.setIcon(Resource.instance.getIcon("frame/sendChat.bmp"));
		sendButton.addActionListener(action);
		sendButton.setActionCommand("sendMessage");
		bottomBox.add(sendButton);
		bottomBox.add(Box.createHorizontalStrut(15));

		TButton recieveButton = new TButton();
		recieveButton.setIcon(Resource.instance.getIcon("frame/closeChat.bmp"));
		recieveButton.setOpaque(false);
		recieveButton.setBorder(null);
		recieveButton.setContentAreaFilled(false);
		recieveButton.addActionListener(action);
		recieveButton.setActionCommand("closeChat");
		bottomBox.add(recieveButton);
		bottomBox.add(Box.createHorizontalStrut(15));

		panel.add(bottomBox);

		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		sendMessageTextArea = new SendMessageTextPane();
		JScrollPane scrollPane = new JScrollPane(sendMessageTextArea);
		scrollPane.setPreferredSize(new Dimension(200,100));
		// scrollPane.setMaximumSize(new Dimension(100, 200));
		// scrollPane.setMinimumSize(new Dimension(100, 200));
		sendMessageTextArea.setPreferredSize(new Dimension(200, 100));
		panel_1.add(scrollPane, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BorderLayout());
		Box operateSendMessageBox = Box.createVerticalBox();
		Box sendMessOperateTopBox = Box.createHorizontalBox();
		panel_3.setBackground(new Color(230, 255, 255));
		panel_2.add(panel_3, BorderLayout.SOUTH);

		JComboBox fontComboBox = new JComboBox(FontUtil.instance.loadFont());
		fontComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sendMessageTextArea.setFontName(e.getItem().toString());
			}
		});
		sendMessOperateTopBox.add(fontComboBox);
		sendMessOperateTopBox.add(Box.createHorizontalStrut(5));
		JComboBox fontSizeComboBox = new JComboBox(FontUtil.instance.loadSize());
		fontSizeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sendMessageTextArea.setFontSize((Integer) e.getItem());
			}
		});
		sendMessOperateTopBox.add(fontSizeComboBox);
		sendMessOperateTopBox.add(Box.createHorizontalStrut(5));
		JComboBox fontStyleComboBox = new JComboBox(FontUtil.instance
				.loadStyle());
		fontStyleComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = ((JComboBox) e.getSource()).getSelectedIndex();
				sendMessageTextArea.setFontStyle(FontUtil.instance
						.getFontStye(index));
			}
		});
		sendMessOperateTopBox.add(fontStyleComboBox);
		JComboBox colorComboBox = new JComboBox(FontUtil.instance.loadColor());
		colorComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = ((JComboBox) (e.getSource())).getSelectedIndex();
				sendMessageTextArea.setFontColor(FontUtil.instance
						.getColor(index));
			}
		});
		sendMessOperateTopBox.add(colorComboBox);
		operateSendMessageBox.add(sendMessOperateTopBox);
		operateSendMessageBox.add(Box.createVerticalStrut(5));
		Box sendMessageBottomBox = Box.createHorizontalBox();
		// sendMessageBottomBox.setAlignmentX(Box.LEFT_ALIGNMENT);
		TButton button_6 = new TButton("表情");
		sendMessageBottomBox.add(button_6);
		sendMessageBottomBox.add(Box.createHorizontalStrut(5));
		TButton button_3 = new TButton("图片");
		sendMessageBottomBox.add(button_3);
		sendMessageBottomBox.add(Box.createHorizontalStrut(5));
		TButton cutImagebutton = new TButton("截图");
		final ScreenCapture cutImagePane = new ScreenCapture(this);
		cutImagebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutImagePane.cutImage(e, comboBox.getSelectedIndex());
			}
		});
		sendMessageBottomBox.add(cutImagebutton);
		sendMessageBottomBox.add(Box.createHorizontalStrut(5));
		comboBox = new JComboBox(new Object[] { "隐藏本窗口", "不隐藏本窗口" });

		sendMessageBottomBox.add(comboBox);
		sendMessageBottomBox.add(Box.createHorizontalStrut(100));
		operateSendMessageBox.add(sendMessageBottomBox);
		operateSendMessageBox.add(Box.createVerticalStrut(5));
		panel_3.add(operateSendMessageBox);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		recaveTextArea = new RecieveMessageTextPane();
		// recaveTextArea.setEditable(false);
		scrollPane_1.setViewportView(recaveTextArea);
		scrollPane_1.setMaximumSize(new Dimension(200, 500));
		scrollPane_1.setMinimumSize(new Dimension(200,500));
		contentPane.add(rightPanel, BorderLayout.EAST);

		Box rightBox = Box.createVerticalBox();
		JLabel label_2 = new JLabel();
		label_2.setIcon(Resource.instance.getIcon("frame/qqshow_1.jpg"));
		rightBox.add(label_2);
		rightBox.add(Box.createVerticalStrut(20));
		JLabel label_3 = new JLabel();
		label_3.setIcon(Resource.instance.getIcon("frame/qqshow_2.jpg"));
		rightBox.add(label_3);
		rightPanel.add(rightBox, BorderLayout.CENTER);

		setLocationRelativeTo(null);

		timer = new Timer(1, timerAction());
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
		if (b) {
			timer.start();
		} else {
			timer.stop();
		}
	}

	public SimpleUser getUser() {
		return user;
	}

	public SendMessageTextPane getSendMessageTextArea() {
		return sendMessageTextArea;
	}

	public void setSendMessageTextArea(SendMessageTextPane sendMessageTextArea) {
		this.sendMessageTextArea = sendMessageTextArea;
	}

	public RecieveMessageTextPane getRecaveTextArea() {
		return recaveTextArea;
	}

	public void setRecaveTextArea(RecieveMessageTextPane recaveTextArea) {
		this.recaveTextArea = recaveTextArea;
	}

	private ActionListener timerAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					action.doTalk(e);
				} catch (Exception ex) {
					ex.printStackTrace();
					action.doTalk(e);
					System.gc();
				}
			}
		};
	}
}
