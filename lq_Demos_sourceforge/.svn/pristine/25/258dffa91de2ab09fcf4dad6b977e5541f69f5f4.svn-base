package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//获取数据库自动生成的主键:主键的值最好由自己的程序来维护
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
			stmt = conn.prepareStatement("insert into t1 (name) values(?)");//, Statement.RETURN_GENERATED_KEYS);//获取数据库插入的主键的值
			stmt.setString(1, "gfy");
			stmt.executeUpdate();
			//以下就是获取数据库生成的主键的值
			rs = stmt.getGeneratedKeys();//一行一列
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
