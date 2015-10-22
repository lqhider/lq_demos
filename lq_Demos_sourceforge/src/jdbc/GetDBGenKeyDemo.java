package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//��ȡ���ݿ��Զ����ɵ�����:������ֵ������Լ��ĳ�����ά��
//create database day16;
//use day16;
//create table t1(
//	id int primary key auto_increment,
//	name varchar(100)
//);
public class GetDBGenKeyDemo {
	@Test
	public void test(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("insert into t1 (name) values(?)");//, Statement.RETURN_GENERATED_KEYS);//��ȡ���ݿ�����������ֵ
			stmt.setString(1, "gfy");
			stmt.executeUpdate();
			//���¾��ǻ�ȡ���ݿ����ɵ�������ֵ
			rs = stmt.getGeneratedKeys();//һ��һ��
			if(rs.next()){
				System.out.println(rs.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
	}
}
