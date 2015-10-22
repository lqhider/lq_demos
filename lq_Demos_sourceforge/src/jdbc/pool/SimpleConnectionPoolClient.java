package jdbc.pool;

import java.sql.Connection;

public class SimpleConnectionPoolClient {

	public static void main(String[] args) {
		Connection c1 = SimpleConnectionPool.getConnection();
		//用c1做DAO的操作
		Connection c2 = SimpleConnectionPool.getConnection();
		//用c2做DAO的操作
		Connection c3 = SimpleConnectionPool.getConnection();
		//用c3做DAO的操作
		
		SimpleConnectionPool.release(c1);
		SimpleConnectionPool.release(c3);
		SimpleConnectionPool.release(c2);
	}

}
