//空布局的测试
package test;

import javax.swing.*;

public class NullLayout extends JFrame {
	//private JPanel jPanel = new JPanel();
	//private JButton bt1 = new JButton("按钮1");

	public NullLayout() {
		//jPanel.setLayout(null);
		//bt1.setBounds(10, 20, 100, 50);
		//jPanel.add(bt1);
		this.add(getPanel());

		this.setSize(400, 400);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel getPanel(){
		JPanel jPanel = new JPanel();
		JButton bt1 = new JButton("按钮1");
		jPanel.setLayout(null);
		bt1.setBounds(10, 20, 100, 50);
		jPanel.add(bt1);
		
		return jPanel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NullLayout();
	}

}

