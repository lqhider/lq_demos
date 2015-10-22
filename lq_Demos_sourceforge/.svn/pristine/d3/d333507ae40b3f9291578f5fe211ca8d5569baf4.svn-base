package base.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SomeDaoTest {
	static SomeDao dao = null;
	@BeforeClass
	public static void aa(){//类加载时就调用，只有一次
		System.out.println("aa");
		dao = new SomeDao();
	}
	@AfterClass
	public static void bb(){
		System.out.println("bb");
		dao = null;
	}
//	@Before 
//	public void a(){//每个测试方法执行前会先调用该方法
//		System.out.println("aaa");
//		dao = new SomeDao();
//	}
//	@After
//	public void b(){//每个测试方法执行后会调用该方法
//		System.out.println("bbb");
//		dao = null;
//	}
	
	//如果一个方法是测试方法，必须加上@Test注解
	//测试方法必须没有返回值，且没有任何参数的
	@Test
	public void testSum() {
		int result = dao.sum(1, 2);
		System.out.println(result);
	}

	@Test(timeout=10)//单位是毫秒
	public void testDivide() {
		int result = dao.divide(6, 2);
		Assert.assertEquals(3, result);
	}
	@Test(expected=java.lang.ArithmeticException.class)
	public void testDivide1() {
		int result = dao.divide(6, 0);
	}
}
