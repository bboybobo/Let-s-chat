package application;

import java.awt.EventQueue;

import org.alvin.client.NetClient;

import talk.alvin.gui.GUIManager;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIManager.loginFrame.show();
					NetClient.instance.start();
				} catch (Exception e) {
					e.printStackTrace();
					NetClient.instance.stop();
				}
			}
		});
	}

}
