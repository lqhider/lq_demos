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
			//利用conn做一些dao操作
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
					conn.close();//该方法应做到，不要关闭连接，而应该还回池中
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
