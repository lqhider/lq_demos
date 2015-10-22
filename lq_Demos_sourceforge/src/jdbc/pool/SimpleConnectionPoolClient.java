package jdbc.pool;

import java.sql.Connection;

public class SimpleConnectionPoolClient {

	public static void main(String[] args) {
		Connection c1 = SimpleConnectionPool.getConnection();
		//��c1��DAO�Ĳ���
		Connection c2 = SimpleConnectionPool.getConnection();
		//��c2��DAO�Ĳ���
		Connection c3 = SimpleConnectionPool.getConnection();
		//��c3��DAO�Ĳ���
		
		SimpleConnectionPool.release(c1);
		SimpleConnectionPool.release(c3);
		SimpleConnectionPool.release(c2);
	}

}
