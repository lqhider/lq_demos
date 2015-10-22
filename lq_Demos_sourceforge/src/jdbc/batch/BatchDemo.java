package jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//������
/*
create table t3(
	id int primary key,
	name varchar(100)
);
 */
public class BatchDemo {
	@Test//��t3�в���2����¼��ɾ������1��
	//���Ҫִ�е���������䲻ͬ��ʹ��Statement����������
	public void testBatch1(){
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			String sql1 = "insert into t3 (id,name) values(1,'aa1')";
			String sql2 = "insert into t3 (id,name) values(2,'aa2')";
			String sql3 = "delete from t3 where id=1";
			stmt.addBatch(sql1);//�ڲ�ά����һ��List���ӵ�List��
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			
			int ii[] = stmt.executeBatch();//Ԫ����ÿ�����Ӱ�쵽������
			for(int i:ii)
				System.out.println(i);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
	}
	@Test
	//��t3�в���100����¼
	//���������ȫ��ͬ��ֻ�ǲ�����ͬ��ʹ��PreparedStatement
	public void testBatch2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JdbcUtil.getConnection();
			String sql = "insert into t3 (id,name) values(?,?)";
			stmt = conn.prepareStatement(sql);
			for(int i=1;i<=100;i++){
				stmt.setInt(1, i);
				stmt.setString(2, "aaaa"+i);
				stmt.addBatch();
			}
			stmt.executeBatch();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
	}
	
	//ִ��һ��������¼��������,ÿһǧ����¼ִ��һ��������,��������̫��Ļ�,���ڴ����
	//���Խ��:mysql���ݿ�Ҫִ��4����Сʱ,��oracle���ݿ�ֻ��20��
	@Test
	public void testBatch3(){
		long time = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JdbcUtil.getConnection();
			String sql = "insert into t3 (id,name) values(?,?)";
			stmt = conn.prepareStatement(sql);
			for(int i=1;i<=1000001;i++){
				stmt.setInt(1, i);
				stmt.setString(2, "aaaa"+i);
				stmt.addBatch();
				if(i%1000==0){
					stmt.executeBatch();
					stmt.clearBatch();//���
				}
			}
			stmt.executeBatch();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
		System.out.println("��ʱ��"+((System.currentTimeMillis()-time)/1000)+"��");
	}
}
