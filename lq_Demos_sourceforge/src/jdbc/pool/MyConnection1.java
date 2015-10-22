package jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 * ͨ���̳�Ĭ���������İ�װ���ģʽ
 * 
 * a����дһ���࣬�̳�Ĭ�ϵ�������
 * b������һ�����ã���ס����д���ʵ��
 * c�����幹�췽�������뱻��д���ʵ��
 * d������Ҫ��д�ķ�������д����
 * @author wzhting
 *
 */
//��дcom.mysql.jdbc.Connection�е�close�������ڵ���ʱ�����ǹر����Ӷ��Ƿ��س���
public class MyConnection1 extends ConnectionWrapper {
	private Connection conn;
	private LinkedList<Connection> pool;
	public MyConnection1(Connection conn,LinkedList<Connection> pool){
		super(conn);
		this.conn = conn;
		this.pool = pool;
	}
	@Override
	public void close() throws SQLException {
		pool.add(conn);
	}
	
}
