package jdbc.pool;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import jdbc.util.JdbcUtil;

//一个标准的数据源
public class MyDataSource1 implements DataSource {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//从池中获取一个连接
	@Override
	public synchronized Connection getConnection() throws SQLException {
		if(pool.size()>0){
			final Connection conn = pool.remove();//原有对象
			//返回动态代理对象
			return (Connection)Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					if("close".equals(method.getName())){
						return pool.add(conn);
					}else{
						return method.invoke(conn, args);
					}
				}
			});
		}else{
			throw new RuntimeException("对比起！服务器真忙");
		}
	}

	
	
	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
//	public void release(Connection conn){
//		pool.add(conn);
//	}



	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
