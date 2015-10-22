package jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//批处理
/*
create table t3(
	id int primary key,
	name varchar(100)
);
 */
public class BatchDemo {
	@Test//向t3中插入2条记录，删除掉第1条
	//如果要执行的批处理语句不同，使用Statement进行批处理
	public void testBatch1(){
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			String sql1 = "insert into t3 (id,name) values(1,'aa1')";
			String sql2 = "insert into t3 (id,name) values(2,'aa2')";
			String sql3 = "delete from t3 where id=1";
			stmt.addBatch(sql1);//内部维护了一个List，加到List中
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			
			int ii[] = stmt.executeBatch();//元素是每条语句影响到的行数
			for(int i:ii)
				System.out.println(i);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
	}
	@Test
	//向t3中插入100条记录
	//由于语句完全相同，只是参数不同，使用PreparedStatement
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
	
	//执行一百万条记录的批处理,每一千条记录执行一次批处理,否则数据太多的话,会内存溢出
	//测试结果:mysql数据库要执行4个多小时,而oracle数据库只用20秒
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
					stmt.clearBatch();//清空
				}
			}
			stmt.executeBatch();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}
		System.out.println("用时："+((System.currentTimeMillis()-time)/1000)+"秒");
	}
}
