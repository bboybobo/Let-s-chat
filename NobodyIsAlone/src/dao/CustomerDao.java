/*��Customer�����������ݿ⣬��������ɾ��ĵķ�װ*/
package dao;

import java.sql.*;

import javax.swing.JOptionPane;


import beans.Customer;
public class CustomerDao {
	private Connection conn = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	public CustomerDao() {
		
	}
	
	//�������ݿ�
	public void connectDatabase(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";  
			this.conn = DriverManager.getConnection(url,"scott","bb1356");
			
		}catch(Exception ex){
			System.out.println("���ݿ�����ʧ��");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	//��ѯ
	public Customer getCus(String account) throws SQLException{
		Customer cus = new Customer();
		
		connectDatabase();
		String sql = "select * from customer where account = ?";
		this.pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		this.rs = pstat.executeQuery();
		while(rs.next()){
			cus.setAccount(account);
			cus.setPassword(rs.getString("password"));
			cus.setAvatar(rs.getString("avatar"));
			cus.setSign(rs.getString("sign"));
		}
		conn.close();	pstat.close();	rs.close();
		
		return cus;
	}
	//���
	public void insertCus(Customer cus) throws SQLException{
		String account = cus.getAccount();
		String password = cus.getPassword();
		String avatar = cus.getAvatar();
		String sign = cus.getSign();
		
		connectDatabase();
		String sql = "insert into customer values(?,?,?,?)";
		this.pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.setString(2, password);
		pstat.setString(3, avatar);
		pstat.setString(4, sign);
		pstat.execute();
		
		conn.close();	pstat.close();
	}
	
	//�޸�
	public void updateCus(Customer cus) throws SQLException{
		String account = cus.getAccount();
		String password = cus.getPassword();
		String avatar = cus.getAvatar();
		String sign = cus.getSign();
		
		connectDatabase();
		String sql = "update customer set password=?, avatar=?,sign=? where account = ?";
		this.pstat = conn.prepareStatement(sql);
		
		pstat.setString(1, password);
		pstat.setString(2, avatar);
		pstat.setString(3, sign);
		pstat.setString(4, account);
		int i = pstat.executeUpdate();
		if(i==0){
			JOptionPane.showMessageDialog(null, "��������ʱ���ִ���");
		}
		conn.close();	pstat.close();
	}
	
	
	//ɾ��
	public void deleteCus(Customer cus) throws SQLException{
		String account = cus.getAccount();
		
		connectDatabase();
		String sql = "delete from customer where account = ?";
		this.pstat = conn.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.execute();
		
		conn.close();	pstat.close();
	}
	
	/*
	public static void main(String[] args) throws SQLException{
		CustomerDao cd = new CustomerDao();
		Customer cus1 = new Customer("123","123","123","123");
		//cd.insertCus(cus1);
		//System.out.println("�Ѳ���һ��");
		//cus1.setPassword("111");
		//cd.updateCus(cus1);
		//System.out.println("�Ѹ���һ��");
		System.out.println(cd.getCus("123").getPassword());
		cd.deleteCus(cus1);
		System.out.println("��ɾ��һ��");
	}*/
}
