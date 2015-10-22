package jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import jdbc.util.JdbcUtil;

import org.junit.Test;


/*
create table account(
	id int primary key auto_increment,
	name varchar(40),
	money float
)character set utf8 collate utf8_general_ci;

insert into account(name,money) values('aaa',1000);
insert into account(name,money) values('bbb',1000);
insert into account(name,money) values('ccc',1000);
 */
//ͨ��aaa��bbbת����ϰ����Ļ���ʹ��
public class TransactionDemo1 {
	@Test
	public void test1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			//����Ĭ�����Զ�������
			conn = JdbcUtil.getConnection();
			//�ֹ���������
			conn.setAutoCommit(false);//����Ŀ�ʼ���൱��start transaction
			stmt = conn.prepareStatement("update account set money=money-100 where name='bbb'");
			stmt.executeUpdate();
//			int i=1/0;
			stmt = conn.prepareStatement("update account set money=money+100 where name='aaa'");
			stmt.executeUpdate();
			conn.commit();//�ύ����commit
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//����Ļع�
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
	}
	
	@Test
	//aaa--->bbb+100          bbb--->ccc+100
	public void test2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		Savepoint sp = null;
		try{
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("update account set money=money-100 where name='aaa'");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("update account set money=money+100 where name='bbb'");
			stmt.executeUpdate();
			
			sp = conn.setSavepoint();//���ûع���
			
			stmt = conn.prepareStatement("update account set money=money-100 where name='bbb'");
			stmt.executeUpdate();
			int i=1/0;//bbb��cccת��ʱ�����쳣
			stmt = conn.prepareStatement("update account set money=money+100 where name='ccc'");
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback(sp);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcUtil.release(null, stmt, conn);
		}
	}
}
