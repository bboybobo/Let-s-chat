//主界面的容器类
/*总体采用边界布局，北面用 空布局，中间用卡片布局*/
package gui;
import javax.swing.*;
public class MainPanel {
	public JPanel northPanle = new JPanel();
	public JPanel centerPanle = new JPanel();
	public MainPanel() {
		// TODO Auto-generated constructor stub
	}

	public JPanel getNorthPanle(){
		//北面布局用空布局
		northPanle.setLayout(null);
		
		return northPanle;
	}
	
	public JPanel getCenterPanel(){
		//中间用卡片布局
		
		return centerPanle;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
