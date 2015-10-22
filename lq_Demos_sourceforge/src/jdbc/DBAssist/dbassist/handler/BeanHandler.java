package jdbc.DBAssist.dbassist.handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//ǰ�᣺JavaBean�е��ֶ������ݿ���е��ֶ�һ��
//�ô�����ֻ�ʺϽ������ֻ��һ����¼�����

import jdbc.DBAssist.dbassist.exception.DBAssistException;


public class BeanHandler implements ResultSetHandler {
	private Class clazz;
	public BeanHandler(Class clazz){
		this.clazz = clazz;
	}
	@Override
	public Object handler(ResultSet rs) {
		try {
			Object bean = clazz.newInstance();//Ŀ��JavaBeanʵ��
			if(rs.next()){
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for(int i=0;i<count;i++){
					String columnName = rsmd.getColumnName(i+1);
					Object columnValue = rs.getObject(i+1);
					Field f = clazz.getDeclaredField(columnName);
					f.setAccessible(true);
					f.set(bean, columnValue);
				}
				return bean;
			}else
				return null;
		} catch (Exception e) {
			throw new DBAssistException(e);
		}
	}

}
