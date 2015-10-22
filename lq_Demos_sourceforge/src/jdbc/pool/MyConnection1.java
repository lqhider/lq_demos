package jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 * 通过继承默认适配器的包装设计模式
 * 
 * a、编写一个类，继承默认的适配器
 * b、定义一个引用，记住被改写类的实例
 * c、定义构造方法，传入被改写类的实例
 * d、对于要改写的方法，改写即可
 * @author wzhting
 *
 */
//改写com.mysql.jdbc.Connection中的close方法，在调用时，不是关闭连接而是返回池中
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
