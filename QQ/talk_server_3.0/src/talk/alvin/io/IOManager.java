package talk.alvin.io;

public enum IOManager {

	instance();

	public XMLOperateIO xmlIO;

	public FileOperateIO fileIo;

	private IOManager() {
		changeWorkFlow("default");
	}

	public void changeWorkFlow(String type) {
		xmlIO = new XMLOperateIO();
		fileIo = new FileOperateIO();
	}
}
