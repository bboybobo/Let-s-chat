/*聊天表情界面*/
package gui;

import java.awt.GridLayout;

import javax.swing.*;

public class EmojiWindow extends JWindow{
	private  JLabel[] emoLabels = new JLabel[60];
	private GridLayout gridLayout = new GridLayout(10,6,2,2);
	private ImageIcon[] icons;
	public EmojiWindow() {
		
	}

	public void init(){
		for(int i=1; i<60; i++){
			
			emoLabels[i].setIcon(icons[i]);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
