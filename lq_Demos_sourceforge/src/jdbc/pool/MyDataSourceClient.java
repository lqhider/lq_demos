package jdbc.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MyDataSourceClient {
	private static DataSource ds = new MyDataSource1();
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = ds.getConnection();
			//����conn��һЩdao����
			System.out.println(conn);
			conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();//�÷���Ӧ��������Ҫ�ر����ӣ���Ӧ�û��س���
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
