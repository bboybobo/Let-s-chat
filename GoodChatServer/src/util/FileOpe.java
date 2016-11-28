/*�ļ���������װ�Կͻ���Ϣ���ж�д�ļ�*/
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
			pps.load(reader);		//�����ļ�
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "�ļ������쳣");
			System.exit(0);
		}finally{
			try {
				reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//д�ļ�
	private static void listInfo(){
		PrintStream ps = null;
		try {
			ps = new PrintStream(fileName);
			pps.list(ps);		//������ļ�
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�ļ������쳣");
			System.exit(0);
		}finally{
			try {
				ps.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//���ļ��ж�ȡ�ͻ���Ϣ
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
	
	//���ͻ���Ϣ���뵽�ļ�
	public static void insertCustomer(String account,String password, String name, String dept){
		pps.setProperty(account, password+"#"+name+"#"+dept);
		listInfo();
	}
}
