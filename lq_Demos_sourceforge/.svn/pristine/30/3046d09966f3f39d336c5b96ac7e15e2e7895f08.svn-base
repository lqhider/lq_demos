package jdbc.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
//	private static ComboPooledDataSource cpds = new ComboPooledDataSource("intergalactoApp");
	
	public static Connection getConnection(){
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
