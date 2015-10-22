package jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.util.JdbcUtil;

import org.junit.Test;

//��ʾ��������ĸ��뼶��
//���뼶�������������֮��
public class TransactionIsolationDemo {
	@Test
	public void test(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);//һ��Ҫ�ڿ�������ǰ���ø��뼶�𣬷�����Ч
			conn.setAutoCommit(false);
			//�Ȳ�ѯaaa�����
			stmt = conn.prepareStatement("select * from account where name='aaa'");
			rs = stmt.executeQuery();
			if(rs.next())
				System.out.println("��ʼʱ����"+rs.getString("money"));
			
			Thread.sleep(10*1000);
			
			stmt = conn.prepareStatement("select * from account where name='aaa'");
			rs = stmt.executeQuery();
			if(rs.next())
				System.out.println("����δ�ύ����ʱ����"+rs.getString("money"));
			
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
