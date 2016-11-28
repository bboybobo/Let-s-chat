/*文件操作，封装对客户信息进行读写文件*/
package util;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import vo.Customer;

public class FileOpe {
	private static String fileName = "cus.inc";
	private static Properties pps;
	static{
		pps = new Properties();
		FileReader reader = null;
		try{
			reader = new FileReader(fileName);
			pps.load(reader);		//载入文件
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "文件操作异常");
			System.exit(0);
		}finally{
			try {
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//写文件
	private static void listInfo(){
		PrintStream ps = null;
		try {
			ps = new PrintStream(fileName);
			pps.list(ps);		//输出到文件
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "文件操作异常");
			System.exit(0);
		}finally{
			try {
				ps.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//从文件中读取客户信息
	public static Customer getCustomerByaccount(String account){
		Customer cus = null;
		String cusInfo = pps.getProperty(account);
		if(cusInfo != null){
			String[] infos = cusInfo.split("#");
			cus = new Customer();
			cus.setAccount(account);
			cus.setPassword(infos[0]);
			cus.setName(infos[1]);
			cus.setDept(infos[2]);
			//System.out.print(account +infos[0] +infos[1] + infos[2] );
		}
		return cus;
	}
	
	//将客户信息输入到文件
	public static void insertCustomer(String account,String password, String name, String dept){
		pps.setProperty(account, password+"#"+name+"#"+dept);
		listInfo();
	}
}
