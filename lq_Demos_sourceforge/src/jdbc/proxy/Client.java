package jdbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		final Chun c = new Chun();

		// 找到Chun的动态代理类（经济人）
		Human h = (Human) Proxy.newProxyInstance(Chun.class.getClassLoader(), c
				.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			// proxy:代理对象的引用 method：当前调用的方法 args：当前方法需要的参数
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// 跳舞出场费不能少于30000
				if ("dance".equals(method.getName())) {
					float money = (Float)args[0];	
					if(money>30000){
						c.dance(money/2);
					}
				}
				// 唱歌出场费不能少于20000
				if ("sing".equals(method.getName())) {
					float money = (Float)args[0];	
					if(money>20000){
						c.sing(money/2);
					}
				}
				return null;
			}
		});
		h.sing(40000);
		h.dance(60000);
		System.out.println(h.getClass().getName());
	}
}
