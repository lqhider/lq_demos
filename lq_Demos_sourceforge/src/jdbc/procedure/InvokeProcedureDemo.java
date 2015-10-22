package jdbc.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import jdbc.util.JdbcUtil;

import org.junit.Test;


//��ε��ô洢����
/*
���ַ���welcome---�봫��Ĳ����������Ӻ󷵻�
delimiter $$

CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255), INOUT inOutParam varchar(255))
BEGIN
    SELECT CONCAT('welcome---', inputParam) into inOutParam;
END $$

delimiter ;
 */
//�˴�ѧϰ��Ŀ�꣺����ô洢����
public class InvokeProcedureDemo {
	@Test
	public void test(){
		Connection conn = null;
		CallableStatement stmt = null;//
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareCall("{call demoSp(?,?)}");//����ѧϰjava�Ĺؼ�
			//�洢���̵������������ֵ���ɡ����������Ҫ����������������
			stmt.setString(1, "to shanghai");
			stmt.registerOutParameter(2, Types.VARCHAR);
			stmt.execute();//ִ�д洢���̵ĵ���
			String value = stmt.getString(2);
			System.out.println(value);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
	}
}
