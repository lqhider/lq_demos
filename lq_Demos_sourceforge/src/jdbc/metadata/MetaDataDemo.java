package jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jdbc.util.DBCPUtil;

import org.junit.Test;

public class MetaDataDemo {
	@Test
	//获取数据库的元数据信息DataBaseMetaData
	public void test1() throws SQLException{
		Connection conn = DBCPUtil.getConnection();
		DatabaseMetaData dbmd  = conn.getMetaData();
		System.out.println(dbmd.getURL());
		System.out.println(dbmd.getUserName());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.getDatabaseProductVersion());
		System.out.println(dbmd.getDriverName());
		System.out.println(dbmd.getDriverVersion());
		System.out.println(dbmd.isReadOnly());
		conn.close();
	}
	@Test
	//获取SQL语句中的占位符信息ParameterMetaData
	//获取sql中的占位符的个数
	public void test2() throws SQLException{
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("????????????????");
		ParameterMetaData pmd = stmt.getParameterMetaData();
		System.out.println(pmd.getParameterCount());
		stmt.close();
		conn.close();
	}
	@Test
	//结果集的元数据信息：ResultSetMetaData
	public void test3() throws SQLException{
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from account");
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		//结果中有几列
		int count = rsmd.getColumnCount();
		System.out.println(count);
		
		for(int i=0;i<count;i++){
			System.out.println("列名："+rsmd.getColumnName(i+1)+"\t类型："+rsmd.getColumnType(i+1));
		}
		
		rs.close();
		stmt.close();
		conn.close();
	}
}
