package jdbc.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//如何调用存储过程
/*
用字符串welcome---与传入的参数进行连接后返回
delimiter $$

CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255), INOUT inOutParam varchar(255))
BEGIN
    SELECT CONCAT('welcome---', inputParam) into inOutParam;
END $$

delimiter ;
 */
//此处学习的目标：会调用存储过程
public class InvokeProcedureDemo {
	@Test
	public void test(){
		Connection conn = null;
		CallableStatement stmt = null;//
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareCall("{call demoSp(?,?)}");//这是学习java的关键
			//存储过程的输入参数，给值即可。输出参数，要告诉它参数的类型
			stmt.setString(1, "to shanghai");
			stmt.registerOutParameter(2, Types.VARCHAR);
			stmt.execute();//执行存储过程的调用
			String value = stmt.getString(2);
			System.out.println(value);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
	}
}
