package jdbc.DBAssist.dbassist.handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBAssist.dbassist.exception.DBAssistException;

//前提：JavaBean中的字段与数据库表中的字段一致
//该处理器只适合结果集中只多条记录的情况
public class BeanListHandler implements ResultSetHandler {
	private Class clazz;
	public BeanListHandler(Class clazz){
		this.clazz = clazz;
	}
	@Override
	public Object handler(ResultSet rs) {
		List beans = new ArrayList();
		try {
			while(rs.next()){
				Object bean = clazz.newInstance();//目标JavaBean实例
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for(int i=0;i<count;i++){
					String columnName = rsmd.getColumnName(i+1);
					Object columnValue = rs.getObject(i+1);
					Field f = clazz.getDeclaredField(columnName);
					f.setAccessible(true);
					f.set(bean, columnValue);
				}
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			throw new DBAssistException(e);
		}
	}

}
