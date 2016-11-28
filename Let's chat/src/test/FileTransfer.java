/*测试文件的传输*/
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTransfer {

	public FileTransfer() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("img.jpg");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int)file.length()];
		fis.read(data);
		fis.close();
		for(int i=0;i<data.length;i++){  data[i]+=1; }
		FileOutputStream fos = new FileOutputStream("img1.jpg");
		fos.write(data);
		fos.close();	
	}

}
