package jdbc.pool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Properties;

import jdbc.util.JdbcUtil;

//数据库连接池的原理
public class SimpleConnectionPool {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	private static LinkedList<Connection> pool = new LinkedList<Connection>();//存储连接的池
	static{
		try {
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(in);
			driverClassName = props.getProperty("driverClassName");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			Class.forName(driverClassName);
			
			//初始化10个连接到池中
			for(int i=0;i<10;i++){
				Connection conn = DriverManager.getConnection(url, user, password);
				pool.add(conn);
			}
//			System.out.println("初始化的连接有：");
//			for(Connection c:pool)
//				System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static Connection getConnection(){
		if(pool.size()>0){
			Connection conn = pool.remove();//从池中拿到连接，并从池中删除掉
			
//			System.out.println("取出的是："+conn);
//			System.out.println("池中还有：");
//			for(Connection c:pool)
//				System.out.println(c);
			return conn;
		}else{
			System.out.println("对不起！服务器真忙");
			return null;
		}
	}
	public static void release(Connection conn){//把连接放回池中
		pool.add(conn);
//		System.out.println("回来的是：" +conn);
//		System.out.println("回来后池中的连接：");
//		for(Connection c:pool)
//			System.out.println(c);
	}
}
