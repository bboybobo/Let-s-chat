package talk.alvin.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 文件读写操作
 * 
 * @author Administrator
 * 
 */
public class FileOperateIO extends BaseIO {
	/**
	 * 得到文件中的文本内容
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public String getContent(File f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		String content = "";
		while ((line = reader.readLine()) != null) {
			content = content.concat(line);
		}
		return content;
	}

	/**
	 * 往文件里写入内容
	 * 
	 * @param f
	 * @param content
	 * @throws FileNotFoundException
	 */
	public void wirteContent(File f, String content)
			throws FileNotFoundException {
		PrintWriter out = new PrintWriter(f);
		out.print(content);
		out.flush();
		out.close();
	}

}
