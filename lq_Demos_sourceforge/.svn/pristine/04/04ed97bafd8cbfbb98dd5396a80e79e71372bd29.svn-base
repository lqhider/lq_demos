package jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.util.JdbcUtil;

import org.junit.Test;

//演示控制事务的隔离级别
//隔离级别必须用在事务之中
public class TransactionIsolationDemo {
	@Test
	public void test(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);//一定要在开启事务前设置隔离级别，否则无效
			conn.setAutoCommit(false);
			//先查询aaa的余额
			stmt = conn.prepareStatement("select * from account where name='aaa'");
			rs = stmt.executeQuery();
			if(rs.next())
				System.out.println("开始时的余额："+rs.getString("money"));
			
			Thread.sleep(10*1000);
			
			stmt = conn.prepareStatement("select * from account where name='aaa'");
			rs = stmt.executeQuery();
			if(rs.next())
				System.out.println("别人未提交事务时的余额："+rs.getString("money"));
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.release(rs, stmt, conn);
		}
	}
}
